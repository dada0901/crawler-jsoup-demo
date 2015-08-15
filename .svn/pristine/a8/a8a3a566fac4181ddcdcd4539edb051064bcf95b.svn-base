package com.cinyi.crawlers.ypk39.app;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.cinyi.crawlers.utils.WebUtil;
import com.cinyi.crawlers.ypk39.dao.MedicationDao;
import com.cinyi.crawlers.ypk39.entity.Medication;

public class CrawlMedication {

	private static final Logger LOG = Logger.getLogger(CrawlMedication.class
			.getName());
	private static final MedicationDao DAO = new MedicationDao();

	public static void main(String[] args) throws IOException {

		Document indexDoc = WebUtil
				.getWebDocument("http://ypk.39.net/AllCategory.aspx");

		// 解析药品分类列表
		Elements dls = indexDoc.select("#d1 > dl");

		// 遍历分类列表
		for (Element dl : dls) {
			Element dt = dl.select("dt").first();
			Elements dds = dl.select("dd");
			String parentCategory = dt.text();

			// 遍历药品子分类，解析下面的药品数据
			for (Element a : dds.select("a")) {

				String childUrl = a.absUrl("href");

				// 解析药品列表页
				Document medicationIndex = WebUtil.getWebDocument(a
						.absUrl("href"));

				// 解析药品列表
				Elements medicationList = medicationIndex
						.select("ul[class=search_ul] > li");

				// 解析药品总条数
				int totalCount = 0;
				Element page = medicationIndex.select("span[class=pgleft]")
						.first();
				if (page.text().length() == 0) {
					totalCount = medicationList.size();
				} else {
					String pageContent = page.select("b").last().text();
					totalCount = Integer.parseInt(pageContent.substring(1,
							pageContent.indexOf("条")));
				}

				// 计算总页数
				int totalPage = totalCount % medicationList.size() > 0 ? totalCount
						/ medicationList.size() + 1
						: totalCount / medicationList.size();

				String childCategory = a.text();
				String category = parentCategory + "/" + childCategory;

				// 遍历每一页，爬药品数据
				for (int i = 1; i <= totalPage; i++) {
					// 拼接每页完整的url
					String curUrl = "";
					if (totalPage == 1) {
						curUrl = childUrl;
					} else {
						curUrl = childUrl + i;
					}

					// 解析药品列表页面
					Document medicationListDoc = null;
					try {
						medicationListDoc = WebUtil.getWebDocument(curUrl);
					} catch (Exception e) {
						e.printStackTrace();
					}

					if (medicationListDoc == null)
						continue;

					// 遍历药品列表
					Elements lis = medicationListDoc
							.select("ul[class=search_ul] > li");
					for (Element li : lis) {

						// int time = (int) (Math.random() * 10000);
						// LOGGER.info("线程休眠【" + time / 1000 + "】秒。");
						// try {
						// Thread.sleep(time);
						// } catch (Exception e) {
						// e.printStackTrace();
						// }

						Element strong = li.select("div[class=msgs] > strong")
								.first();
						if (strong == null)
							continue;

						// 解析药品明细的url
						String medicationUrl = strong.select("a").first()
								.absUrl("href");

						// 解析药品明细
						Medication medication = new Medication();

						// 解析名称
						medication.setName(strong.select("a").first().text());

						// 解析分类
						medication.setCategory(category);

						// 解析进口属性
						medication
								.setOrigin(strong.select("i[class=icon4]")
										.first() == null ? "进口" : strong
										.select("i[class=icon4]").first()
										.attr("title"));

						// 解析是否处方药
						medication.setPrescription(strong.select(
								"i[class=icon1]").first() != null ? strong
								.select("i[class=icon1]").first().attr("title")
								: "处方药");

						// 解析药品概述页
						Document deitailDoc = WebUtil
								.getWebDocument(medicationUrl);
						// 解析药品说明书
						Element detail = deitailDoc.select("a[title=详细说明书]")
								.first();
						if (detail == null) {
							LOG.error("无法解析药品详细说明书的超链接：" + medicationUrl);
							continue;
						}

						// 解析药品概述页面左侧div
						Element leftDiv = deitailDoc.select(
								"div[class=gs_left]").first();

						// 解析图片
						// Element img = deitailDoc.select(
						// "div[class=imgbox] > img").first();
						// if (img != null) {
						// byte[] photo = null;
						//
						// try {
						// photo = WebUtil.getImageByte(img.attr("src"));
						// } catch (Exception e) {
						// e.printStackTrace();
						// }
						//
						// if (photo != null) {
						// medication.setPhoto(photo);
						// }
						// }

						Element ul = leftDiv.select("ul[class=showlis]")
								.first();
						if (ul != null) {

							for (Element li2 : ul.select("li")) {

								Element title = li2.select("cite").first();

								// 解析剂型
								if (title.text().contains("剂型")) {
									li2.select("cite").remove();
									medication
											.setFormulation(li2.text().trim());
								}

								// 解析规格
								if (title.text().contains("规格")) {
									Element select = li2.select("select")
											.first();
									if (select != null) {
										Elements options = select
												.select("option");
										StringBuilder str = new StringBuilder();
										for (Element option : options) {
											str.append(option.text().trim()
													+ ",");
										}
										if (str.length() > 0) {
											medication.setSpecification(str
													.subSequence(0,
															str.length() - 1)
													.toString());
										}
									} else {
										li2.select("cite").remove();
										medication.setSpecification(li.text()
												.trim());
									}
								}
							}

							// 解析条形码
							Element barcode = leftDiv.select(
									"div[class=app] > div").first();
							if (barcode != null) {
								medication.setBarcode(barcode.text().trim());
							}
						}

						// 解析药品详细说明书中的各项的值
						Document bookDoc = null;
						try {
							bookDoc = WebUtil.getWebDocument(detail
									.absUrl("href"));
						} catch (Exception e) {
							e.printStackTrace();
						}
						if (bookDoc == null)
							continue;

						// 解析说明书所在的div
						Element divDetail = bookDoc.select("#tab_con_1")
								.first();
						for (Element dl2 : divDetail.select("dl")) {

							String title = dl2.select("dt").first().text()
									.trim();
							String content = dl2.select("dd").first().text()
									.trim();

							// 解析药品名称
							if (title.contains("药品名称")) {
								medication.setFullName(content);
							}

							// 解析成分
							if (title.contains("成份")) {
								medication.setComposition(content);
							}

							// 解析适应症
							if (title.contains("适应症")) {
								medication.setSyz(content);
							}

							// 解析用法用量
							if (title.contains("用法用量")) {
								medication.setYfyl(content);
							}

							// 解析不良反应
							if (title.contains("不良反应")) {
								medication.setBlfy(content);
							}

							// 解析禁忌
							if (title.contains("禁忌")) {
								medication.setBan(content);
							}

							// 解析注意事项
							if (title.contains("注意事项")) {
								medication.setZysx(content);
							}

							// 解析药物相互作用
							if (title.contains("药物相互作用")) {
								medication.setYwhxzy(content);
							}

							// 解析药理作用
							if (title.contains("药理作用")) {
								medication.setYlzy(content);
							}

							// 解析贮藏
							if (title.contains("贮藏")) {
								medication.setStorage(content);
							}

							// 解析有效期
							if (title.contains("有效期")) {
								medication.setValidTime(content);
							}

							// 解析批准文号
							if (title.contains("批准文号")) {
								medication.setApproveNumber(content);
							}

							// 解析生产企业
							if (title.contains("生产企业")) {
								medication.setManufacturer(content);
							}
						}

						DAO.insert(medication);
						LOG.info("【已保存：】" + medication.getName());
					}
				}
			}
		}
	}

}
