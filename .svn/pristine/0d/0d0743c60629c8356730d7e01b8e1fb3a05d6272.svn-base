package com.cinyi.crawlers.health99.app;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.cinyi.crawlers.health99.dao.HospitalDao;
import com.cinyi.crawlers.health99.entity.Hospital;
import com.cinyi.crawlers.utils.WebUtil;

/**
 * 
 * @ClassName: CrawlHospital
 * @Description: 爬医院数据（99健康网）
 * @author fuda
 * @date 2014年7月15日 下午2:10:33
 * 
 */
public class CrawlHospital {

	private static final Logger LOGGER = Logger.getLogger(CrawlHospital.class
			.getName());

	public static void main(String[] args) throws IOException {

		// 解析首页
		Document index = WebUtil.getWebDocument("http://yyk.99.com.cn/");

		// 解析所有地区
		Element divArea = index.select("div[class=lcr_bottom]").first();
		if (divArea == null) {
			LOGGER.error("无法解析地区所在的div：div[class=lcr_bottom]");
			return;
		}

		// 移除热门地区
		Element ulHot = divArea.select("#sl1").first();
		ulHot.remove();

		// 遍历地区，解析地区关联的医院
		for (Element areaLink : divArea.select("ul > li > a")) {
			String areaName = areaLink.text();
			String areaUrl = areaLink.absUrl("href");

			if (areaName.endsWith("市")) {
				parseHospitalByCity(areaName, areaUrl);
			}

			if (areaName.endsWith("省")) {

				// 解析省份页面
				Document provinceDoc = WebUtil.getWebDocument(areaUrl);
				// 解析该省份的城市列表
				Elements divCities = provinceDoc
						.select("div[class=fontlist] > ul > li > a");
				// 遍历城市，解析该城市的医院
				for (Element cityLink : divCities) {
					String cityName = cityLink.text();
					String cityUrl = cityLink.absUrl("href");

					parseHospitalByCity(cityName, cityUrl);
				}
			}
		}
	}

	private static void parseHospitalByCity(String cityName, String cityUrl)
			throws IOException {
		// 解析城市页面
		Document cityDoc = WebUtil.getWebDocument(cityUrl);

		// 解析城市所有城区
		Elements districtList = cityDoc.select("div[class=tablist]");
		// 遍历城区
		for (Element divDistrict : districtList) {
			String divContent = divDistrict.select("h4").first().text();
			String disctrictName = divContent.substring(
					divContent.lastIndexOf(">") + 1, divContent.indexOf("("));

			// 解析该城区的所有医院
			Elements hospitalLinks = divDistrict.select("ul > li > a");
			ExecutorService service = Executors.newFixedThreadPool(2);
			// ExecutorService service = Executors.newCachedThreadPool();
			for (int i = 0; i < hospitalLinks.size(); i++) {
				service.execute(new HospitalParser(cityName, disctrictName,
						hospitalLinks.get(i)));
			}
			service.shutdown();
		}
	}
}

class HospitalParser extends Thread {

	private static final Logger LOG = Logger.getLogger(HospitalParser.class
			.getName());
	private static final HospitalDao DAO = new HospitalDao();

	// 医院所在城市
	private String city;
	// 医院所在城区
	private String district;
	// 医院超链接
	private Element ele;

	public HospitalParser(String city, String district, Element ele) {
		this.city = city;
		this.district = district;
		this.ele = ele;
	}

	public void run() {

		LOG.info("线程【" + ele.attr("title") + "】启动。");

		String hospitalName = ele.attr("title");

		// 解析医院页面
		Document hospitalDoc = null;
		try {
			hospitalDoc = WebUtil.getWebDocument(ele.absUrl("href"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (hospitalDoc == null) {
			LOG.error("【" + hospitalName + "】无法打开页面：" + ele.absUrl("href"));
			return;
		}

		// 解析医院详情页
		Element detailLink = hospitalDoc.select("a:contains([查看详细])").first();
		if (detailLink == null) {
			LOG.error("【" + hospitalName + "】无法解析详情页面：" + ele.absUrl("href"));
			return;
		}

		// 解析医院数据
		Hospital hospital = null;
		try {
			hospital = parse(city, district, ele.text(),
					detailLink.absUrl("href"), hospitalDoc);
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (hospital != null) {
			Hospital oldHospital = DAO.selectOneByName(hospitalName);

			if (oldHospital != null) {
				hospital.setId(oldHospital.getId());
				DAO.update(hospital);
				LOG.info("更新医院：" + hospital);
			} else {
				DAO.insert(hospital);
				LOG.info("新增医院：" + hospital);
			}
		}

		LOG.info("线程【" + ele.attr("title") + "】结束。");
	}

	private Hospital parse(String city, String ditrict, String name,
			String url, Document tempDoc) throws IOException {

		Document hospitalDoc = WebUtil.getWebDocument(url);
		if (hospitalDoc == null) {
			LOG.error("【" + name + "】无法打开详情页：" + url);
			return null;
		}

		Hospital hospital = new Hospital();
		hospital.setName(name);
		hospital.setCity(city);
		hospital.setDistrict(ditrict);

		// 解析科室列表
		Elements specialties = tempDoc
				.select("div[class=hp_docks] > ul > li > a");
		StringBuilder str = new StringBuilder();
		for (Element specialty : specialties) {
			str.append(specialty.text() + ",");
		}
		if (str.length() > 0) {
			hospital.setOfficeList(str.substring(0, str.length() - 1));
		}

		// 解析图片
		Element photo = hospitalDoc.select("div[class=hpi_img] > a > img")
				.first();
		if (photo != null) {
			hospital.setPhoto(WebUtil.getImageByte(photo.absUrl("src")));
		}

		// 解析医院详情的div
		Element divMain = hospitalDoc.select("div[class=mainleft]").first();

		// 解析其他属性
		Element divProperties = divMain.select("div[class=border_wrap]").get(1);
		if (divProperties != null) {
			Element tbProperties = divProperties.select("div").last()
					.select("table").first();
			if (tbProperties != null) {
				Elements trs = tbProperties.select("tr");

				// 解析别名
				hospital.setAlias(trs.get(0).select("td").get(1).text());

				// 解析性质
				hospital.setProperty(trs.get(1).select("td").get(5).text());

				// 解析等级
				hospital.setLevel(trs.get(2).select("td").get(1).text());

				// 解析是否医保
				hospital.setMedicare(trs.get(3).select("td").get(5).text());
			}
		}

		// 解析简介
		Element divRemark = divMain.select("div[class=border_wrap]").get(3);
		if (divRemark != null) {
			hospital.setRemark(divRemark.select("div[class=hpcontent]").text());
		}

		// 解析联系方式
		Element divContract = divMain.select("div[class=leftpad10 contact]")
				.first();
		if (divContract != null) {
			Element table = divContract.select("table").first();
			if (table != null) {
				Elements trs = table.select("tr");

				// 解析网址
				String website = trs.get(0).select("td").last().text();
				if (!website.startsWith("http://yyk.99.com.cn/")) {
					hospital.setWebsite(website);
				}

				// 解析联系电话
				hospital.setTelephone(trs.get(1).select("td").last().text());

				// 解析联系地址
				hospital.setAddress(trs.get(2).select("td").last().text());

				// 解析邮编
				hospital.setPostCode(trs.get(3).select("td").last().text());

				// 解析公交路线
				hospital.setBusInfo(trs.get(4).select("td").last().text());

			}
		}

		return hospital;
	}
}
