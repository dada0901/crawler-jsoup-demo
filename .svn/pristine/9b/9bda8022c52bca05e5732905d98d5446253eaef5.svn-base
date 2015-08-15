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
import com.cinyi.crawlers.xywy.dao.IllnessDao;
import com.cinyi.crawlers.xywy.entity.Illness;

/**
 * 
 * @ClassName: CrawlIllness
 * @Description: 爬寻医问药网的疾病数据
 * @author fuda
 * @date 2014年7月14日 下午12:30:04
 * 
 */
public class CrawlIllness {

	public static final Logger LOG = Logger.getLogger(CrawlIllness.class
			.getName());

	public static void main(String[] args) throws IOException {
		// 解析首页文档模型，按字母查疾病
		Document index = WebUtil
				.getWebDocument("http://jib.xywy.com/html/zimu.html");

		// 解析字母的超链接
		Elements characters = index
				.select("span[class=fl f18 fb pl20 zimu_nav1 darkgreen-a] > a");

		// 遍历子母查疾病
		ExecutorService service = Executors.newFixedThreadPool(5);
		for (int i = 0; i < characters.size(); i++) {
			service.execute(new IllParser(i + 1, characters.get(i)));
		}
		service.shutdown();
	}
}

/**
 * 
 * @ClassName: IllParser
 * @Description: 疾病解析器
 * @author fuda
 * @date 2014年7月14日 下午12:33:14
 * 
 */
class IllParser extends Thread {

	private static final Logger log = Logger.getLogger(IllParser.class
			.getName());
	private static final IllnessDao DAO = new IllnessDao();

	// 线程号
	private int index;

	// 字母的超链接（疾病按首字母分类）
	private Element ele;

	public IllParser(int i, Element ele) {
		this.index = i;
		this.ele = ele;
	}

	public void run() {

		log.info("【线程" + this.index + "】启动！");

		String url = ele.absUrl("href");
		Document characterDoc = null;
		try {
			characterDoc = WebUtil.getWebDocument(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (characterDoc == null) {
			log.error("无法解析url：" + url);
			return;
		}

		// 解析疾病列表
		Elements links = characterDoc.select("div[class=fl jblist-con-ear]")
				.select("a");

		for (Element a : links) {

			String illUrl = a.absUrl("href");

			// 对疾病连接发请求
			Document illDoc = null;
			try {
				illDoc = WebUtil.getWebDocument(illUrl);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (illDoc == null) {
				log.error("无法解析url：" + illUrl);
				return;
			}

			String illName = illDoc.select("div[class=jb-name fYaHei gre]")
					.first().text();

			Illness ill = DAO.selectOneByName(illName);

			if (ill == null) {
				/*
				 * // 解析疾病 Illness jb = null; try { jb = ParseJibing(illName,
				 * illUrl); } catch (IOException e) { e.printStackTrace(); }
				 * 
				 * // 保存疾病 if (jb != null) { DAO.insert(jb); log.info("【线程" +
				 * this.index + "】新增疾病：" + jb.getName()); }
				 */
			} else {

				log.info(ill.getName());

				// 解析疾病
				Illness jb = null;
				try {
					// jb = ParseJibing(illName, illUrl);
					jb = parsePeople(illUrl);

					jb.setId(ill.getId());
					// DAO.update(jb);
					DAO.updatePeopleAndHbbl(jb);
					log.info("【线程" + this.index + "】更新疾病：" + ill.getName());

				} catch (Exception e) {
					log.info(jb.getId());
					log.info(jb.getPeople());
					log.info(jb.getHbbl());
					log.info(jb.getCrfs());
					e.printStackTrace();

				}
			}
		}

		log.info("【线程" + this.index + "】停止！");
	}

	/**
	 * 
	 * @param illName
	 *            疾病名称
	 * @param illUrl
	 *            疾病url
	 * @return 疾病实体对象
	 * @throws IOException
	 */
	private Illness ParseJibing(String illName, String illUrl)
			throws IOException {

		Illness jb = new Illness();

		Document illDoc = WebUtil.getWebDocument(illUrl);
		if (illDoc == null) {
			log.info(illName + " 保存失败：" + illUrl);
			return null;
		}

		jb.setName(illName);
		jb.setSpell(PinyinUtil.getFirstSpell(illName));

		// 解析别名
		Element alias = illDoc.select("span[class=db pr15]").first();
		jb.setAlias(alias == null ? "" : alias.text());

		// 解析图片
		Element img = illDoc.select("img[title=" + illName + "]").first();
		if (img != null) {
			byte[] photo = WebUtil.getImageByte(img.attr("src"));
			if (photo == null)
				log.error("解析" + illName + "的图片时发生异常。");
			jb.setPhoto(photo);
		}

		// 得到‘更多’的超链
		Element gengduo = illDoc.select("a:contains(更多>>)").first();
		if (gengduo == null) {
			log.error(illName + " 保存失败：无法解析‘更多’的超链接。");
			return null;
		}
		illDoc = WebUtil.getWebDocument(gengduo.absUrl("href"));
		if (illDoc == null) {
			log.error(illName + " 保存失败：" + gengduo.absUrl("href"));
			return null;
		}

		// 解析概述
		Element gaishu = illDoc.select(
				"div[class=pl20 pr20 pt20 f14 passage blue-a]").first();
		if (gaishu != null)
			jb.setRemark(gaishu.text());

		// 解析疾病其他属性
		Elements lis = illDoc.select(".common_nav > ul").first() == null ? null
				: illDoc.select(".common_nav > ul").first().select("li");
		if (lis == null)
			return null;

		for (Element li : lis) {
			Element link = li.select("a").first();
			if (link.text().equals("找医生"))
				continue;
			if (link.text().equals("找医院"))
				continue;
			if (link.text().equals("找药品"))
				continue;

			String linkUlr = link.absUrl("href");
			Document tempDoc = WebUtil.getWebDocument(linkUlr);
			if (tempDoc == null)
				continue;

			// 得到该分类的概述
			gaishu = tempDoc.select(
					"div[class=pl20 pr20 pt20 f14 passage blue-a]").first();
			if (gaishu == null)
				continue;

			if (link.text().equals("概述")) {
				jb.setRemark(gaishu.text());
			}
			if (link.text().equals("病因")) {
				jb.setReason(gaishu.text());
			}
			if (link.text().equals("症状")) {
				jb.setZzgs(gaishu.text());
			}
			if (link.text().equals("饮食")) {
				jb.setYinShi(gaishu.text());
			}
			if (link.text().equals("预防")) {
				jb.setYuFang(gaishu.text());
			}
			if (link.text().equals("治疗")) {
				jb.setZlfa(gaishu.text());
			}
			if (link.text().equals("检查")) {
				// 解析关联的检查项
				Elements checkupLinks = tempDoc
						.select("div[class=drugs_box pl20 pr20 pt15 pb10 f14 bc pr] > ul > a");
				StringBuilder checkups = new StringBuilder();
				for (Element checkupLink : checkupLinks) {
					checkups.append(checkupLink.text() + ",");
				}
				if (checkups.length() > 0) {
					jb.setCheckupRef(checkups.substring(0,
							checkups.length() - 1));
				}

				jb.setCheckupRemark(gaishu.text());
			}
			if (link.text().equals("诊断鉴别")) {
				jb.setZdjb(gaishu.text());
			}
			if (link.text().equals("并发症")) {
				jb.setBfz(gaishu.text());
			}
		}

		return jb;
	}

	private static Illness parsePeople(String url) throws IOException {

		Illness ill = new Illness();

		Document illDoc = WebUtil.getWebDocument(url);
		if (illDoc == null) {
			return null;
		}

		Elements ps = illDoc.select("div[class=fl jib-common-sense] > p");
		for (Element p : ps) {
			Element span = p.select("span").first();
			if (span.text().contains("易感人群")) {
				ill.setPeople(p.select("span").last().text());
			}
			if (span.text().contains("患病比例")) {
				ill.setHbbl(p.select("span").last().text());
			}
			if (span.text().contains("传染方式")) {
				ill.setCrfs(p.select("span").last().text());
			}
		}

		return ill;
	}
}
