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
import com.cinyi.crawlers.xywy.dao.SymptomDao;
import com.cinyi.crawlers.xywy.entity.Symptom;

public class CrawlSymptom {

	private static final Logger LOG = Logger.getLogger(CrawlSymptom.class
			.getName());

	public static void main(String[] args) throws IOException {

		LOG.info("开始按字母爬取症状数据，寻医问药网：http://zzk.xywy.com/");

		Document index = WebUtil.getWebDocument("http://zzk.xywy.com/");

		Elements characters = index.select("#Tag > li > a");
		ExecutorService service = Executors.newFixedThreadPool(10);
		for (int i = 0; i < characters.size(); i++) {
			service.execute(new SymptomParser(i + 1, characters.get(i)));
		}
		service.shutdown();
	}
}

/**
 * 
 * @ClassName: SymptomParser
 * @Description: 症状解析器
 * @author fuda
 * @date 2014年7月14日 下午3:16:19
 * 
 */
class SymptomParser extends Thread {

	private static final Logger LOG = Logger.getLogger(SymptomParser.class
			.getName());
	private static final SymptomDao DAO = new SymptomDao();

	// 线程号
	private int index;

	// 文档元素
	private Element ele;

	public SymptomParser(int i, Element ele) {
		this.index = i;
		this.ele = ele;
	}

	public void run() {

		LOG.info("【线程" + this.index + "】启动。");

		Document list = null;
		try {
			list = WebUtil.getWebDocument(ele.absUrl("href"));
		} catch (IOException e1) {
			e1.printStackTrace();
			return;
		}

		if (list == null) {
			LOG.info("无法解析：" + ele.absUrl("href"));
			return;
		}

		LOG.info("开始解析：" + list.select("h3:contains(所有症状)").first());
		Elements lis = list.select("ul[class=ill_list clearfix] > li");
		for (Element li : lis) {

			if (li.select("a") == null)
				continue;

			String name = li.select("a").text();
			String url = li.select("a").attr("href");

			if (name.equals("") || url.equals(""))
				continue;

			Symptom symptom = DAO.selectOneByName(name);

			if (symptom != null) {
				Symptom temp = null;
				try {
					temp = parse(name, url);
					temp.setId(symptom.getId());
					DAO.update(temp);
					LOG.info("【线程" + this.index + "】更新症状：" + symptom.getName());
				} catch (IOException e) {
					e.printStackTrace();
				}

			} else {
				try {
					symptom = parse(name, url);
					DAO.insert(symptom);
					LOG.info("【线程" + this.index + "】新增症状：" + symptom.getName());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		LOG.info("【线程" + this.index + "】停止。");
	}

	private Symptom parse(String name, String url) throws IOException {

		Symptom symptom = new Symptom();

		Document doc = WebUtil.getWebDocument(url);

		// 解析名称
		Element bzName = doc.select("#summarize").first().select("h1").first();
		if (bzName == null) {
			LOG.error("无法解析病症名称!" + url);
			return null;
		}
		symptom.setName(bzName.text());
		symptom.setSpell(PinyinUtil.getFirstSpell(bzName.text()));

		// 解析图片
		Element img = doc.select("img[title=" + name + "]").first();
		if (img != null) {
			byte[] photo = null;
			try {
				photo = WebUtil.getImageByte(img.attr("src"));
			} catch (IOException e) {
				LOG.error(e);
				e.printStackTrace();
			}
			if (photo == null) {
				LOG.error("解析" + name + "的图片时发生异常。");
			}
			symptom.setPhoto(photo);
		}

		// 解析患病部位、所属科室、相关疾病、相关检查、相关症状
		Element dl = doc.select("div[class=main_con]").first()
				.select("dl[class=symptom clearfix]").first();

		for (Element p : dl.select("dd > p")) {

			// 解析患病部位
			if (p.text().contains("患病部位")) {
				symptom.setBodyRef(p.select("span").first().text());
			}

			// 解析所属科室
			if (p.text().contains("所属科室")) {

				StringBuilder specialtyStr = new StringBuilder();
				for (Element a : p.select("span > a")) {
					specialtyStr.append(a.text() + ",");
				}
				if (specialtyStr.length() > 0) {
					symptom.setSpecialtyRef(specialtyStr.substring(0,
							specialtyStr.length() - 1));
				}
			}

			// 解析相关疾病
			if (p.text().contains("相关疾病")) {
				StringBuilder illStr = new StringBuilder();
				for (Element a : p.select("span > a")) {
					illStr.append(a.text() + ",");
				}
				if (illStr.length() > 0) {
					symptom.setIllnessRef(illStr.substring(0,
							illStr.length() - 1));
				}
			}

			// 解析相关检查
			if (p.text().contains("相关检查")) {
				StringBuilder checkupRef = new StringBuilder();
				for (Element a : p.select("span > a")) {
					checkupRef.append(a.text() + ",");
				}
				if (checkupRef.length() > 0) {
					symptom.setCheckupRef(checkupRef.substring(0,
							checkupRef.length() - 1));
				}
			}

			// 解析相关症状
			if (p.text().contains("相关症状")) {
				StringBuilder symptomRef = new StringBuilder();
				for (Element a : p.select("span > a")) {
					symptomRef.append(a.text() + ",");
				}
				if (symptomRef.length() > 0) {
					symptom.setSymptomRef(symptomRef.substring(0,
							symptomRef.length() - 1));
				}
			}
		}

		// 解析症状概述
		Element remark = doc.select("p[class=check_p fsize14]").first();
		if (remark != null) {
			symptom.setRemark(remark.text());
		}

		// 解析症状的其他属性
		Elements lis = doc.select("ul[class=list] > li");
		for (Element li : lis) {

			// 解析症状属性的超链
			Element a = li.select("a").first();
			if (a == null)
				continue;

			// 解析属性标题
			String title = a.attr("title");
			if (!title.equals(name + "原因") && !title.equals(name + "检查")
					&& !title.equals(name + "鉴别诊断")
					&& !title.equals(name + "缓解方法"))
				continue;

			// 解析属性内容
			Document detail = WebUtil.getWebDocument(a.absUrl("href"));
			if (detail == null)
				continue;
			Element content = detail.select("div[class=text-box]").first();

			// 解析原因
			if (title.equals(name + "原因")) {
				symptom.setReason(content == null ? "" : content.text());
			}
			// 解析检查
			if (a.attr("title").equals(name + "检查")) {
				symptom.setCheckupRemark(content == null ? "" : content.text());
			}
			// 解析鉴别诊断
			if (a.attr("title").equals(name + "鉴别诊断")) {
				symptom.setJbzd(content == null ? "" : content.text());
			}
			// 解析缓解方法
			if (a.attr("title").equals(name + "缓解方法")) {
				symptom.setHjff(content == null ? "" : content.text());
			}
		}

		return symptom;
	}
}
