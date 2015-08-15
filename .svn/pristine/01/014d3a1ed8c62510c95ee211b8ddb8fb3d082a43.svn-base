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
import com.cinyi.crawlers.xywy.dao.MedicationDao;
import com.cinyi.crawlers.xywy.entity.Medication;
import com.cinyi.crawlers.xywy.entity.MedicationParameter;

public class CrawlMedication {

	private static String illnessCategory = "";

	public static void main(String[] args) throws IOException {

		Document index = WebUtil.getWebDocument("http://yao.xywy.com/jibing/");

		Element div = index.select("div[class=brdc8 con-box]").first();

		Elements jibings;
		if (illnessCategory == "") {
			// 解析所有疾病列表
			jibings = div.select("div[class=brdc8 con-box] > div > ul > li");
		} else {
			// 解析某一个分类下的疾病列表
			jibings = div.select("div:contains(" + illnessCategory
					+ ") + div > ul > li");
		}
		// 遍历疾病列表,解析疾病关联的药品
		ExecutorService service = Executors.newCachedThreadPool();
		for (int i = 0; i < jibings.size(); i++) {
			service.execute(new MedicationParser(jibings.get(i)));
		}
		service.shutdown();
	}
}

class MedicationParser extends Thread {

	private static final Logger LOG = Logger.getLogger(MedicationParser.class
			.getName());
	private static final MedicationDao DAO = new MedicationDao();

	private Element ill;

	public MedicationParser(Element ele) {
		this.ill = ele;
	}

	public void run() {
		LOG.info("线程【" + ill.select("a").first().attr("title") + "】启动。");

		Element span = ill.select("span").first();
		Element a = ill.select("a").first();
		if (a == null || span == null)
			return;

		// 解析疾病的url
		String url = a.absUrl("href");
		String tempUrl = url.substring(0, url.lastIndexOf("."));

		// 解析药品总数
		int count = Integer.parseInt(span.text().substring(
				span.text().indexOf("(") + 1, span.text().indexOf("种")));
		// 解析总页数
		int totalPage = count % 10 > 0 ? count / 10 + 1 : count / 10;

		// 遍历所有页码
		for (int i = 1; i <= totalPage; i++) {

			try {
				int sleepTime = (int) (Math.random() * 60000);
				LOG.info("【" + ill.select("a").first().attr("title") + "】休眠"
						+ sleepTime / 1000 + "后开始解析第" + i + "页，共" + totalPage
						+ "页。");
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				LOG.error(e);
			}

			String curUrl = tempUrl + "-" + i + ".htm";

			// 解析药品列表
			Document yaoList = null;
			try {
				yaoList = WebUtil.getWebDocument(curUrl);
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (yaoList == null) {
				continue;
			}

			// 解析药品
			Elements yaos = yaoList
					.select("ul[class=drugList f12 pl10 pr10] > li");
			for (Element yao : yaos) {

				Medication yp = null;
				try {
					yp = parseYao(yao);
				} catch (IOException e) {
					e.printStackTrace();
				}

				if (yp == null)
					continue;

				MedicationParameter param = new MedicationParameter(
						yp.getName(), yp.getSccj(), yp.getPzwh());
				Medication OldMedication = DAO.selectByNameAndSccj(param);

				if (OldMedication != null) {
					yp.setId(OldMedication.getId());
					DAO.update(yp);
					LOG.info("【线程" + ill.select("a").first().attr("title")
							+ "】更新药品:" + yp);
				} else {
					DAO.insert(yp);
					LOG.info("【线程" + ill.select("a").first().attr("title")
							+ "】新增药品：" + yp);
				}
			}
		}
		LOG.info("线程【" + ill.select("a").first().attr("title") + "】结束。");
	}

	private Medication parseYao(Element yao) throws IOException {

		Medication yp = new Medication();

		// 解析药品明细的url
		Element detail = yao
				.select("div[class=fl pl10 drugTXT] > p > span > a").first();
		if (detail == null) {
			LOG.error("无法解析药品明细的url：div[class=fl pl10 drugTXT] > p > span > a");
			return null;
		}
		String detailUrl = detail.absUrl("href");

		// 解析药品名
		yp.setName(detail.text());
		yp.setSpell(PinyinUtil.getFirstSpell(detail.text()));

		// 解析图片
		Element img = yao.select("img").first();
		if (img != null) {
			try {
				yp.setPhoto(WebUtil.getImageByte(img.absUrl("src")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// 解析参考价格
		Element price = yao.select(
				"span[style=float:left;margin-top:1px;margin-left:5px;]")
				.first();
		if (price != null) {
			try {
				String ckjg = price
						.text()
						.substring(price.text().lastIndexOf(" "),
								price.text().indexOf("元")).trim();
				yp.setCkjg(ckjg);
			} catch (Exception e) {
			}
		}

		// 解析药品的其他属性
		Document detailDoc = WebUtil.getWebDocument(detailUrl);
		if (detailDoc == null) {
			// LOGGER.error(detail.text() + "：解析其他属性时发生异常。");
			return null;
		}

		// 解析通用名称
		Element alias = detailDoc.select("li:contains(通用名称) > span > a")
				.first();
		if (alias != null) {
			yp.setAlias(alias.attr("title"));
		}

		// 解析治疗疾病
		Elements ills = detailDoc.select("li:contains(治疗疾病) > a");
		StringBuilder illContent = new StringBuilder();
		for (Element a : ills) {
			illContent.append(a.text() + ",");
		}
		if (illContent.length() > 0) {
			yp.setZzjb(illContent.substring(0, illContent.length() - 1));
		}

		if (alias != null) {
			yp.setAlias(alias.attr("title"));
		}
		// 解析批准文号
		Element pzwh = detailDoc.select("li:contains(批准文号) > span").first();
		if (pzwh != null) {
			yp.setPzwh(pzwh.text());
		}

		Element nameSpan = detailDoc.select("span[class=fl mt5]").first();
		if (nameSpan != null) {

			// 解析处方或非处方药
			String cffcf = nameSpan.text().indexOf("处方") > 0 ? "处方药" : "非处方药";
			yp.setCffcf(cffcf);

			// 解析中药或西药
			String zyxy = nameSpan.text().indexOf("西药") > 0 ? "西药" : "中成药";
			yp.setZyxy(zyxy);
		}

		// 解析生产厂家
		Element sccj = detailDoc.select("li:contains(生产厂家) > span > a").first();
		if (sccj != null) {
			yp.setSccj(sccj.text());
		}

		// 解析功能主治
		Element gnzz = detailDoc.select("li:contains(功能主治) + li").first();
		if (gnzz != null) {
			yp.setGnzz(gnzz.text());
		}

		// 解析主要成分
		Element zycf = detailDoc.select("li:contains(主要成份) + li").first();
		if (zycf != null) {
			yp.setZycf(zycf.text());
		}

		// 解析包装规格
		Element bzgg = detailDoc.select("li:contains(包装规格) + li").first();
		if (bzgg != null) {
			yp.setBzgg(bzgg.text());
		}

		// 解析用法用量
		Element yfyl = detailDoc.select("li:contains(用法用量) + li").first();
		if (yfyl != null) {
			yp.setYfyl(yfyl.text());
		}

		return yp;
	}
}
