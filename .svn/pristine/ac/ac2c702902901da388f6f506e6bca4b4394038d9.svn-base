package com.cinyi.crawlers.xywy.app;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.cinyi.crawlers.utils.WebUtil;
import com.cinyi.crawlers.xywy.dao.BobyPartDao;
import com.cinyi.crawlers.xywy.entity.BodyPart;

/**
 * 
 * @ClassName: CrawlBodyPart
 * @Description: 爬取寻医问药网的身体部位关联的疾病及症状
 * @author fuda
 * @date 2014年7月11日 下午5:20:31
 * 
 */
public class CrawlBodyPart {

	private static final Logger LOG = Logger.getLogger(CrawlBodyPart.class
			.getName());
	private static final BobyPartDao DAO = new BobyPartDao();

	public static void main(String[] args) throws IOException {

		// 解析部位数据首页文档
		Document index = WebUtil
				.getWebDocument("http://jib.xywy.com/html/toubu.html");

		// 所有部位关联疾病的超链接，保存部位数据
		Elements links = index
				.select("div[class=common_bd] > div > ul > li > a");
		for (Element a : links) {
			String name = a.text();
			if (name.equals("全身"))
				continue;

			BodyPart part = DAO.selectOneByName(name);
			if (part == null) {
				part = new BodyPart();
				part.setName(name);
				DAO.insert(part);
				LOG.info("已保存部位：" + name);
			}
		}

		// 爬部位关联的疾病
		for (Element a : links) {

			String partName = a.text();
			if (partName.equals("全身"))
				continue;

			Document illDoc = null;
			try {
				illDoc = WebUtil.getWebDocument(a.absUrl("href"));
			} catch (Exception e) {
				LOG.error(e);
				e.printStackTrace();
			}
			if (illDoc == null) {
				continue;
			}

			BodyPart part = DAO.selectOneByName(partName);

			// 爬部位常见疾病
			Elements hots = illDoc.select("div[class=common_bd pl15 pr15]")
					.first().select("ul > li > a");
			StringBuilder tempHot = new StringBuilder();
			for (Element hot : hots) {
				tempHot.append(hot.text() + ",");
			}
			if (tempHot.length() > 0) {
				part.setHotIllnessRef(tempHot.substring(0, tempHot.length() - 1));
			}

			// 爬部位所有疾病
			Elements alls = illDoc.select("div[class=common_bd pl15 pr15 pt15 pb15]")
					.first().select("ul > li > a");
			StringBuilder tempAll = new StringBuilder();
			for (Element all : alls) {
				tempAll.append(all.text() + ",");
			}
			if (tempAll.length() > 0) {
				part.setIllnessRef((tempAll.substring(0, tempAll.length() - 1)));
			}

			DAO.update(part);
			LOG.info("已保存" + part + "关联的疾病。");
		}

		// 爬部位关联症状首页
		Document index2 = WebUtil
				.getWebDocument("http://zzk.xywy.com/p/toubu.html");

		Elements parts = index2
				.select("div:contains(按部位找症状) + div > ul > li > a");
		for (Element part : parts) {
			String partName = part.text();
			if (partName.equals("排泄部位")) {
				partName = "排泄部";
			}

			String partUrl = part.absUrl("href");
			BodyPart bp = new BodyPart();
			bp = DAO.selectOneByName(partName);
			if (bp == null)
				continue;

			Document doc2 = null;
			try {
				doc2 = WebUtil.getWebDocument(partUrl);
			} catch (Exception e) {
				LOG.error(e);
				e.printStackTrace();
			}

			if (doc2 == null)
				continue;

			// 爬常见症状
			Elements hotSymptoms = doc2
					.select("div:contains(常见症状) + div > ul > li > a");
			StringBuilder hotSymptomStr = new StringBuilder();
			for (Element symptom : hotSymptoms) {
				hotSymptomStr.append(symptom.attr("title") + ",");
			}
			if (hotSymptomStr.length() > 0) {
				bp.setHotSymptomRef(hotSymptomStr.substring(0,
						hotSymptomStr.length() - 1));
			}

			// 爬所有症状
			Elements allSymptoms = doc2.select("div[class=all_illness]")
					.first().select("a");
			StringBuilder allSymptomStr = new StringBuilder();
			for (Element symptom : allSymptoms) {
				allSymptomStr.append(symptom.attr("title") + ",");
			}
			if (allSymptomStr.length() > 0) {
				bp.setSymptomRef(allSymptomStr.substring(0,
						allSymptomStr.length() - 1));
			}

			DAO.update(bp);
			LOG.info("已保存" + bp + "关联的所有症状。");
		}
		LOG.info("执行结束。");
	}

}
