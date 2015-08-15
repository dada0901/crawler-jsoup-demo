package com.cinyi.crawlers.xywy.dao;

import org.apache.ibatis.session.SqlSession;

import com.cinyi.crawlers.utils.MyBatisUtil;
import com.cinyi.crawlers.xywy.entity.SymptomSpecialtyRef;
import com.cinyi.crawlers.xywy.mapper.SymptomSpecialtyRefMapper;

public class SymptomSpecialtyRefDao extends
		BaseDao<SymptomSpecialtyRefMapper, SymptomSpecialtyRef> {

	public void deleteBySymptom(int symptomId) {

		SqlSession session = MyBatisUtil.getXywySqlSessionFactory()
				.openSession();

		try {

			SymptomSpecialtyRefMapper mapper = session
					.getMapper(SymptomSpecialtyRefMapper.class);
			mapper.deleteBySymptom(symptomId);
		} finally {
			session.close();
		}
	}
}
