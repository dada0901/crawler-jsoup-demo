package com.cinyi.crawlers.xywy.app;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.cinyi.crawlers.utils.WebUtil;
import com.cinyi.crawlers.xywy.dao.SpecialtyDao;
import com.cinyi.crawlers.xywy.entity.Specialty;

/**
 * 
 * @ClassName: CrawlSpecialty
 * @Description: 爬专科数据
 * @author fuda
 * @date 2014年7月22日 上午10:35:07
 * 
 */
public class CrawlSpecialty {

	private static final Logger LOG = Logger.getLogger(CrawlSpecialty.class
			.getName());
	private static final SpecialtyDao DAO = new SpecialtyDao();

	public static void main(String[] args) throws IOException {

		// 解析首页文档模型
		Document index = WebUtil
				.getWebDocument("http://jib.xywy.com/html/keshi.html");

		// 解析所有专科的超链接
		Elements links = index
				.select("div[class=depart_hd sprite-repeat] > span > a");
		for (Element link : links) {

			String name = link.text();
			String url = link.absUrl("href");
			Specialty specialty = DAO.selectOneByName(name);
			if (specialty == null) {
				specialty = new Specialty();
				specialty.setName(name);
				specialty.setTreePath(name);
				DAO.insert(specialty);
				LOG.info("新增专科：" + specialty.getName());
			}

			// 解析子专科页面文档
			Document mainDoc = WebUtil.getWebDocument(url);
			// 解析子专科列表
			Elements childLinks = mainDoc
					.select("div[class=common_bd pt20 pl20 pb10 pr15] > ul > li > span > a");

			// 如果没有子专科
			if (childLinks.size() == 0) {

				// 解析常见疾病
				specialty.setHotIllnessRef(crawlHotIllness(mainDoc));

				// 解析所有疾病
				specialty.setIllnessRef(crawlAllIllness(mainDoc));

				DAO.update(specialty);
			} else {
				// 解析子专科关联疾病
				for (Element childLink : childLinks) {

					if (childLink.text().equals("整形美容科"))
						continue;

					String childName = childLink.attr("title");
					String childUrl = childLink.absUrl("href");

					// 解析子专科关联的疾病
					Document childDoc = WebUtil.getWebDocument(childUrl);

					Specialty oldChildSpecialty = DAO
							.selectOneByName(childName);

					if (oldChildSpecialty == null) {

						// 新增专科
						oldChildSpecialty = new Specialty();
						oldChildSpecialty.setParentId(specialty.getId() + "");
						oldChildSpecialty.setName(childName);
						oldChildSpecialty.setTreePath(specialty.getTreePath()
								+ "/" + childName);
						oldChildSpecialty
								.setHotIllnessRef(crawlHotIllness(childDoc));
						oldChildSpecialty
								.setIllnessRef(crawlAllIllness(childDoc));
						DAO.insert(oldChildSpecialty);
						LOG.info("新增专科：" + oldChildSpecialty.getTreePath());
					} else {

						// 更新专科
						oldChildSpecialty.setParentId(specialty.getId() + "");
						oldChildSpecialty.setTreePath(specialty.getTreePath()
								+ "/" + childName);
						oldChildSpecialty
								.setHotIllnessRef(crawlHotIllness(childDoc));
						oldChildSpecialty
								.setIllnessRef(crawlAllIllness(childDoc));
						DAO.update(oldChildSpecialty);
						LOG.info("更新专科：" + oldChildSpecialty.getTreePath());
					}
				}
			}
		}
		LOG.info("执行结束。");
	}

	private static String crawlHotIllness(Document doc) {

		String str = "";
		Elements hotIllnessLinks = doc
				.select("div[class=departs_itmes pl20 pr20 brdc9e pr clearfix]")
				.first().select("div[class=items_bd pt10 pb10]").first()
				.select("ul > li > a");

		StringBuilder hotIllness = new StringBuilder();
		for (Element hotLink : hotIllnessLinks) {
			hotIllness.append(hotLink.attr("title") + ",");
		}
		if (hotIllness.length() > 0) {
			str = hotIllness.substring(0, hotIllness.length() - 1);
		}

		return str;
	}

	private static String crawlAllIllness(Document doc) {
		String str = "";
		Elements allIllnessLinks = doc
				.select("div[class=departs_itmes pl20 pr20 brdc9e pr clearfix]")
				.last().select("div[class=items_bd pt10 pb10]")
				.select("ul > li > a");
		StringBuilder allIllness = new StringBuilder();
		for (Element allLink : allIllnessLinks) {
			allIllness.append(allLink.attr("title") + ",");
		}
		if (allIllness.length() > 0) {
			str = allIllness.substring(0, allIllness.length() - 1);
		}

		return str;
	}
}
