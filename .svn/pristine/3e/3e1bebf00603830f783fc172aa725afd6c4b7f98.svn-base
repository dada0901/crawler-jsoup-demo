package com.cinyi.crawlers.xywy.app;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.cinyi.crawlers.utils.WebUtil;
import com.cinyi.crawlers.xywy.dao.HospitalDao;
import com.cinyi.crawlers.xywy.entity.Hospital;

/**
 * 
 * @ClassName: CrawlHospital
 * @Description: 爬寻医问药网的医院数据
 * @author fuda
 * @date 2014年7月14日 下午4:18:00
 * 
 */
public class CrawlHospital {

	public static void main(String[] args) throws IOException {

		// 解析首页文档模型
		Document index = WebUtil.getWebDocument("http://z.xywy.com/yiyuan.htm");

		// 解析省份列表
		Elements provinces = index
				.select("div[class=bd f12 btn-a deepgray-a] > div > a");

		// 遍历省份
		ExecutorService service = Executors.newFixedThreadPool(10);
		for (int i = 0; i < provinces.size(); i++) {
			service.execute(new HospitalParser(i + 1, provinces.get(i)));
		}
		service.shutdown();
	}
}

class HospitalParser extends Thread {

	private static final Logger LOG = Logger.getLogger(HospitalParser.class
			.getName());
	private static final HospitalDao DAO = new HospitalDao();

	private int index;
	private Element province;

	public HospitalParser(int i, Element province) {
		this.index = i;
		this.province = province;
	}

	public void run() {

		LOG.info("【线程" + this.index + "】启动。");

		// 解析省份所关联的医院信息
		Document provinceInfo;
		try {
			provinceInfo = WebUtil.getWebDocument(province.absUrl("href"));
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		// 解析城市名称列表
		Elements cities = provinceInfo.select("div[class=brdc8]").first()
				.select("div[class=caption sprite-repeat pl20]");

		// 遍历城市名称
		for (Element city : cities) {

			// 解析城市关联的医院div
			Element div = provinceInfo.select("div[class=brdc8]")
					.select("div:contains(" + city.text() + ") + div").first();

			// 解析所有医院
			Elements hospitals = div.select("ul > li");
			for (Element li : hospitals) {
				Hospital hospital=null;
				try {
					hospital = ParseHospital(li, city.text());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (hospital != null) {
					DAO.insert(hospital);
					LOG.info("【线程" + this.index + "】已保存医院："
							+ hospital.getName());
				}
			}
		}
		LOG.info("【线程" + this.index + "】停止。");
	}

	private Hospital ParseHospital(Element li, String cityName) throws IOException {
		Hospital hospital = new Hospital();

		String name = li.select("a").first().attr("title") + " "
				+ li.select("span").first().text();
		if (name.length() == 0) {
			return null;
		}

		hospital.setName(name);
		hospital.setAreaInfo(cityName);

		// 解析医院其他属性
		Document detail = WebUtil.getWebDocument(li.select("a").first()
				.absUrl("href"));
		if (detail != null) {
			Element div = detail.select(
					"div[class=hospital_info pl10 pr10 pt15 f12 clearfix]")
					.first();
			for (Element dl : div.select("dl")) {

				// 解析医院介绍
				if (dl.text().contains("医院介绍")) {
					Document remarkDoc = WebUtil.getWebDocument(dl.select("a")
							.first().absUrl("href"));
					if (remarkDoc != null) {
						Element divRemark = remarkDoc
								.select("div[class=hospital_introduce pl15 pr10 pt15 pb15 f14 btn-a t2 clearfix]")
								.first();
						String remarkContent = divRemark == null ? ""
								: divRemark.text();
						hospital.setRemark(remarkContent);
					}
				}

				// 解析地址
				if (dl.select("dt").first().attr("class")
						.equals("address sprite-icon pl30 fl")) {
					dl.select("a").first().remove();
					String address = dl.select("p").first() == null ? "" : dl
							.select("p").first().text();
					hospital.setAddress(address);
				}

				// 解析乘车路线
				if (dl.select("dt").first().attr("class")
						.equals("traffic sprite-icon pl30 fl")) {
					dl.select("a").first().remove();
					String way = dl.select("p").first() == null ? "" : dl
							.select("p").first().text();
					hospital.setAddressWay(way);
				}

				// 解析电话
				if (dl.select("dt").first().attr("class")
						.equals("phone sprite-icon pl30 fl")) {
					String phoneNum = dl.select("dd").first() == null ? "" : dl
							.select("dd").first().text();
					hospital.setTelephone(phoneNum);
				}
			}

			// 解析科室列表
			Elements departs = detail.select("#depart_list").first()
					.select("a");
			StringBuilder temp = new StringBuilder();
			for (Element depart : departs) {
				temp.append(depart.text() + ",");
			}
			if (temp.length() > 0) {
				hospital.setOfficeInfo(temp.substring(0, temp.length() - 1));
			}
		}

		return hospital;
	}
}
