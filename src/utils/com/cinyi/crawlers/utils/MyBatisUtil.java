package com.cinyi.crawlers.utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * 
 * @ClassName: MyBatisUtil
 * @Description: 使用MyBatis创建数据层的SqlSession对象
 * @author fuda
 * @date 2014年7月10日 下午6:49:10
 * 
 */
public class MyBatisUtil {

	private static SqlSessionFactory xywySqlSessionFactory;
	private static SqlSessionFactory health99SqlSessionFactory;
	private static SqlSessionFactory ypk39SqlSessionFactory;
	
	public static SqlSessionFactory getXywySqlSessionFactory() {
		if (xywySqlSessionFactory == null) {
			InputStream inputStream;
			try {
				inputStream = Resources.getResourceAsStream("xywy-config.xml");
				xywySqlSessionFactory = new SqlSessionFactoryBuilder()
						.build(inputStream);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return xywySqlSessionFactory;
	}
	
	public static SqlSessionFactory getHealth99SqlSessionFactory() {
		if (health99SqlSessionFactory == null) {
			InputStream inputStream;
			try {
				inputStream = Resources.getResourceAsStream("health99-config.xml");
				health99SqlSessionFactory = new SqlSessionFactoryBuilder()
						.build(inputStream);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return health99SqlSessionFactory;
	}
	
	public static SqlSessionFactory getYpk39SqlSessionFactory() {
		if (ypk39SqlSessionFactory == null) {
			InputStream inputStream;
			try {
				inputStream = Resources.getResourceAsStream("ypk39-config.xml");
				ypk39SqlSessionFactory = new SqlSessionFactoryBuilder()
						.build(inputStream);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return ypk39SqlSessionFactory;
	}
}
