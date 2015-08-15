package com.cinyi.crawlers.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class WebUtil {

	private static final Logger LOG = Logger.getLogger(WebUtil.class.getName());

	public static Document getWebDocument(String url) throws IOException {
		LOG.info("正在请求：" + url);

		Document indexDoc = Jsoup
				.connect(url)
				.timeout(60000)
				.header("Accept",
						"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
				.header("Accept-Charset", "utf-8")
				.header("Accept-Encoding", "gzip,deflate,sdch")
				.header("Connection", "Keep-Alive")
				.header("Cache-Control", "no-cache")
				.userAgent(
						"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31")
				.get();
		return indexDoc;
	}

	public static byte[] getImageByte(String imgUrl) throws IOException {
		byte[] data = null;

		URL tempUrl = new URL(imgUrl);
		URLConnection uc = tempUrl.openConnection();
		uc.setConnectTimeout(60000);
		InputStream is = uc.getInputStream();
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();

		byte[] buff = new byte[4096];

		int count = 0;
		while ((count = is.read(buff, 0, 4096)) > 0) {
			outStream.write(buff, 0, count);
		}
		data = outStream.toByteArray();

		return data;
	}
}
