package com.cinyi.crawlers.xywy.app;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.cinyi.crawlers.utils.PinyinUtil;
import com.cinyi.crawlers.utils.WebUtil;
import com.cinyi.crawlers.xywy.dao.CheckupDao;
import com.cinyi.crawlers.xywy.entity.Checkup;

/**
 * 
 * @ClassName: CrawlCheckup
 * @Description: 爬寻医问药网的检查项数据
 * @author fuda
 * @date 2014年7月11日 下午6:10:29
 * 
 */
public class CrawlCheckup {

	public static void main(String[] args) throws IOException {

		// 解析按字母查询检查项目首页文档模型
		Document index = WebUtil.getWebDocument("http://jck.xywy.com/");

		// 遍历字母超链接，解析检查项目
		Elements links = index.select("#Tag > li > a");
		ExecutorService service = Executors.newFixedThreadPool(20);
		for (int i = 0; i < links.size(); i++) {
			service.execute(new CheckupParser(i + 1, links.get(i)));
		}
		service.shutdown();
	}
}

class CheckupParser extends Thread {

	private final Logger LOG = Logger.getLogger(CheckupParser.class.getName());
	private static final CheckupDao DAO = new CheckupDao();

	private int index;
	private Element ele;

	public CheckupParser(int i, Element ele) {
		this.index = i;
		this.ele = ele;
	}

	public void run() {

		LOG.info("【线程" + this.index + "】启动！");

		// 解析字母页的文档模型
		Document list = null;
		try {
			list = WebUtil.getWebDocument(ele.absUrl("href"));
		} catch (IOException e) {
			LOG.error(e);
			e.printStackTrace();
		}

		if (list == null)
			return;

		// 解析检查列表
		Elements jianchas = list.select("div[class=matvwss] > dl > dd > a");
		for (Element a : jianchas) {

			String jcName = a.attr("title");
			String jcUrl = a.absUrl("href");
			if (jcName.equals("") || jcUrl.equals(""))
				return;

			Checkup jc = null;
			try {
				jc = parse(jcName, jcUrl);
			} catch (IOException e) {
				LOG.error(e);
				e.printStackTrace();
			}

			if (jc != null) {
				Checkup oldCheckup = DAO.selectOneByName(jc.getName());

				if (oldCheckup == null) {
					DAO.insert(jc);
					LOG.info("【线程" + this.index + "】新增检查项：" + jc);
				} else {
					jc.setId(oldCheckup.getId());
					DAO.update(jc);
					LOG.info("【线程" + this.index + "】修改检查项：" + jc);
				}

			}
		}
		LOG.info("【线程" + this.index + "】停止！");
	}

	private Checkup parse(String name, String url) throws IOException {
		Checkup jc = new Checkup();

		Document doc = WebUtil.getWebDocument(url);

		jc.setName(name);
		jc.setSpell(PinyinUtil.getFirstSpell(name));

		// 解析介绍
		Element jieshao = doc.select(
				"div[class=ddsm fl] > dl > dt:contains(介绍) + dd").first();
		jc.setRemark(jieshao == null ? "" : jieshao.text());

		// 解析正常值
		Element zcz = doc.select(
				"div[class=ddsm fl] > dl > dt:contains(正常值) + dd").first();
		jc.setNormalRange(zcz == null ? "" : zcz.text());

		// 解析临床意义
		Element lcyy = doc.select(
				"div[class=ddsm fl] > dl > dt:contains(临床意义) + dd").first();
		jc.setLcyy(lcyy == null ? "" : lcyy.text());

		// 解析注意事项
		Element zysx = doc.select(
				"div[class=ddsm fl] > dl > dt:contains(注意事项) + dd").first();
		jc.setZysx(zysx == null ? "" : zysx.text());

		// 解析检查过程
		Element jcgc = doc.select(
				"div[class=ddsm fl] > dl > dt:contains(检查过程) + dd").first();
		jc.setJcgc(jcgc == null ? "" : jcgc.text());

		// 解析相关检查项
		Elements checkupRefs = doc.select("div[class=sjzc fl] > ul > li > a");
		StringBuilder tempStr = new StringBuilder();
		for (Element ref : checkupRefs) {
			tempStr.append(ref.attr("title") + ",");
		}
		if (tempStr.length() > 0) {
			jc.setCheckupRef(tempStr.substring(0, tempStr.length() - 1));
		}

		return jc;
	}

}
