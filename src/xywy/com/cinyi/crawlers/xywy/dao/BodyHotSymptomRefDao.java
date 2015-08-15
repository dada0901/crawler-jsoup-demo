package com.cinyi.crawlers.xywy.dao;

import org.apache.ibatis.session.SqlSession;

import com.cinyi.crawlers.utils.MyBatisUtil;
import com.cinyi.crawlers.xywy.entity.BodyHotSymptomRef;
import com.cinyi.crawlers.xywy.mapper.BodyHotSymptomRefMapper;

public class BodyHotSymptomRefDao extends
		BaseDao<BodyHotSymptomRefMapper, BodyHotSymptomRef> {

	public void deleteByBody(int bodyId) {

		SqlSession session = MyBatisUtil.getXywySqlSessionFactory()
				.openSession();

		try {

			BodyHotSymptomRefMapper mapper = session
					.getMapper(BodyHotSymptomRefMapper.class);
			mapper.deleteByBody(bodyId);
		} finally {
			session.close();
		}
	}
}
