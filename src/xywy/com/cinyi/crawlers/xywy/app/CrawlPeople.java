package com.cinyi.crawlers.xywy.app;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.cinyi.crawlers.utils.WebUtil;
import com.cinyi.crawlers.xywy.dao.PeopleDao;
import com.cinyi.crawlers.xywy.entity.People;

/**
 * 
 * @ClassName: CrawlPeople
 * @Description: 爬取人群常见疾病（只爬取首页常见疾病，如果查看人群的更多疾病，可按专科分类作为入口）
 * @author fuda
 * @date 2014年7月22日 上午10:23:47
 * 
 */
public class CrawlPeople {

	private static final PeopleDao DAO = new PeopleDao();
	private static final Logger LOG = Logger.getLogger(CrawlPeople.class);

	public static void main(String[] args) throws IOException {

		// 解析疾病首页文档
		Document doc = WebUtil.getWebDocument("http://jib.xywy.com/");

		// 解析人群常见疾病列表
		Elements dls = doc
				.select("div[class=rq_box f12 deepgray-a clearfix] > dl");

		for (Element dl : dls) {
			People people = new People();
			String pepleName = dl.select("dt").first().text();
			people.setName(pepleName);

			// 解析人群常见疾病
			StringBuilder illnessStr = new StringBuilder();
			for (Element item : dl.select("dd > a")) {
				illnessStr.append(item.text() + ",");
			}
			if (illnessStr.length() > 0) {
				people.setHotIllnessRef(illnessStr.substring(0,
						illnessStr.length() - 1));
			}

			People oldPeople = DAO.selectOneByName(people.getName());
			if (oldPeople == null) {
				DAO.insert(people);
			} else {
				people.setId(oldPeople.getId());
				DAO.update(people);
			}

			LOG.info("人群：" + people);
		}
	}
}
