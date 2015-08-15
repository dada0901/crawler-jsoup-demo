package com.cinyi.crawlers.xywy.dao;

import org.apache.ibatis.session.SqlSession;

import com.cinyi.crawlers.utils.MyBatisUtil;
import com.cinyi.crawlers.xywy.entity.SymptomCheckupRef;
import com.cinyi.crawlers.xywy.mapper.SymptomCheckupRefMapper;

public class SymptomCheckupRefDao extends
		BaseDao<SymptomCheckupRefMapper, SymptomCheckupRef> {

	public void deleteBySymptom(int symptomId) {

		SqlSession session = MyBatisUtil.getXywySqlSessionFactory()
				.openSession();

		try {

			SymptomCheckupRefMapper mapper = session
					.getMapper(SymptomCheckupRefMapper.class);
			mapper.deleteBySymptom(symptomId);
		} finally {
			session.close();
		}
	}
}
