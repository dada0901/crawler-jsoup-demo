package com.cinyi.crawlers.xywy.app;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.cinyi.crawlers.utils.WebUtil;
import com.cinyi.crawlers.xywy.dao.DoctorDao;
import com.cinyi.crawlers.xywy.entity.Doctor;
import com.cinyi.crawlers.xywy.entity.DoctorParameter;

public class CrawlDoctor {

	private static String specialtyName = "";

	public static void main(String[] args) throws IOException {
		// 解析首页文档
		Document index = WebUtil
				.getWebDocument("http://z.xywy.com/zhuanye.htm");

		Elements specialtyLinks;
		if (specialtyName.equals("")) {
			// 解析所有专科下的医生
			specialtyLinks = index
					.select("div[class=depart-box-bd padd10 clearfix] > div > ul > li > a");
		} else {
			// 解析某一个专科下的医生
			specialtyLinks = index.select("div:contains(" + specialtyName
					+ ") + div > div > ul > li > a");
		}

		ExecutorService service = Executors.newCachedThreadPool();
		for (int i = 0; i < specialtyLinks.size(); i++) {
			service.execute(new DoctorParser(i + 1, specialtyLinks.get(i)));
		}
		service.shutdown();
	}
}

class DoctorParser extends Thread {

	private static final Logger LOG = Logger.getLogger(DoctorParser.class
			.getName());
	private static final DoctorDao DAO = new DoctorDao();

	private int index;
	private Element ele;

	public DoctorParser(int i, Element ele) {
		this.index = i;
		this.ele = ele;
	}

	public void run() {

		LOG.info("【线程" + this.index + "】启动！");

		// 解析科室关联专家列表首页
		Document index;
		try {
			index = WebUtil.getWebDocument(ele.absUrl("href"));
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		// 解析更多专家的超链接
		Element more = index.select(
				"a[class=db t9999 exp_btn exp_btn3 sprite-icon mt10 mb15 bc]")
				.first();

		if (more == null)
			return;

		// 解析该专科的专家总数
		Document tempDoc = null;
		try {
			tempDoc = WebUtil.getWebDocument(more.absUrl("href"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (tempDoc == null) {
			LOG.error("无法打开‘更多’列表页面：" + more.absUrl("href"));
			return;
		}

		Element totalSpan = tempDoc.select("#totalpage").first();
		if (totalSpan == null)
			return;

		// 解析总页数
		int totalCount = Integer.parseInt(totalSpan.text());
		int totalPage = totalCount % 40 > 0 ? totalCount / 40 + 1
				: totalCount / 40;

		// 解析分页的url
		String tempUrl = more.absUrl("href").substring(0,
				more.absUrl("href").lastIndexOf("."));

		// 分页解析专家列表
		for (int i = 1; i <= totalPage; i++) {

			String realUrl = tempUrl + "_" + i + ".htm";
			Document doctorDoc = null;
			try {
				doctorDoc = WebUtil.getWebDocument(realUrl);
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (doctorDoc == null)
				continue;

			Elements divList = doctorDoc
					.select("div[class=consult_info oh pt15 pb15 btn-a f12 dotted clearfix]");

			// 遍历专家列表
			for (Element div : divList) {
				Element doctorLink = div.select("div > span > a").first();
				String doctorName = doctorLink.text();
				String doctorUrl = doctorLink.absUrl("href");

				// 解析医生明细
				Doctor doctor = null;
				try {
					doctor = Parse(doctorName, doctorUrl);
				} catch (IOException e) {
					e.printStackTrace();
				}

				if (doctor == null)
					continue;

				DoctorParameter param = new DoctorParameter(doctor.getName(),
						doctor.getHospitalName(), doctor.getOfficeName());
				Doctor oldDoctor = DAO.selectByNameAndHospital(param);

				if (oldDoctor != null) {

					LOG.info("【线程" + this.index + "】已存在医生：" + doctor.getName());
				} else {
					DAO.insert(doctor);
					LOG.info("【线程" + this.index + "】已保存医生：" + doctor.getName()
							+ " （" + doctor.getHospitalName() + " "
							+ doctor.getOfficeName() + " ）");
				}
			}
		}
		LOG.info("【线程" + this.index + "】停止！");
	}

	private Doctor Parse(String doctorName, String doctorUrl)
			throws IOException {

		Doctor doctor = new Doctor();
		doctor.setName(doctorName);

		Document doctorDetail = WebUtil.getWebDocument(doctorUrl);
		if (doctorDetail == null) {
			LOG.error("无法打开‘" + doctorName + "’的明细页面：" + doctorUrl);
			return null;
		}

		// 解析医生详情的div
		Element doctorDiv = doctorDetail.select(
				"div[class=expert_info pl10 pr10 pt15 f13 clearfix]").first();

		for (Element dl : doctorDiv.select("dl")) {

			// 解析职称
			if (dl.select("dt").first().attr("class")
					.equals("zhic sprite-icon pl30 fl graydeep")) {
				Element a = dl.select("dd").first().select("a").first();
				a.remove();
				doctor.setJobTitle(dl.select("dd").first().text());
			}

			// 解析医院及科室
			if (dl.select("dt").first().attr("class")
					.equals("ks_h sprite-icon pl30 fl graydeep")) {
				doctor.setHospitalName(dl.select("dd").first().select("a")
						.first().text());
				doctor.setOfficeName(dl.select("dd").first().select("a").last()
						.text());
			}

			// 解析擅长
			if (dl.select("dt").first().attr("class")
					.equals("good sprite-icon pl30 fl graydeep")) {
				doctor.setGoodAt(dl.select("dd").first().select("p").first()
						.text());
			}

			// 解析治疗疾病
			if (dl.select("dt").first().attr("class")
					.equals("graydeep zljb sprite-icon pl30 fl")) {

				Elements illness = dl.select("dd").first().select("p").last()
						.select("span > a");
				StringBuilder illnessStr = new StringBuilder();
				for (Element ill : illness) {
					illnessStr.append(ill.text() + ",");
				}
				if (illnessStr.length() != 0) {
					doctor.setCureIllness(illnessStr.substring(0,
							illnessStr.length() - 1));
				}
			}
		}

		return doctor;
	}
}
