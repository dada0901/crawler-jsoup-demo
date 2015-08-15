package com.cinyi.crawlers.xywy.dao;

import org.apache.ibatis.session.SqlSession;

import com.cinyi.crawlers.utils.MyBatisUtil;
import com.cinyi.crawlers.xywy.entity.SymptomBodyRef;
import com.cinyi.crawlers.xywy.mapper.SymptomBodyRefMapper;

public class SymptomBodyRefDao extends
		BaseDao<SymptomBodyRefMapper, SymptomBodyRef> {

	public void deleteBySymptom(int symptomId) {

		SqlSession session = MyBatisUtil.getXywySqlSessionFactory()
				.openSession();
		try {
			SymptomBodyRefMapper mapper = session
					.getMapper(SymptomBodyRefMapper.class);
			mapper.deleteBySymptom(symptomId);
		} finally {
			session.close();
		}
	}
}
