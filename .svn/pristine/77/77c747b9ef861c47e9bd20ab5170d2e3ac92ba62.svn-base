package com.cinyi.crawlers.xywy.dao;

import org.apache.ibatis.session.SqlSession;

import com.cinyi.crawlers.utils.MyBatisUtil;
import com.cinyi.crawlers.xywy.entity.SymptomIllnessRef;
import com.cinyi.crawlers.xywy.mapper.SymptomIllnessRefMapper;

public class SymptomIllnessRefDao extends
		BaseDao<SymptomIllnessRefMapper, SymptomIllnessRef> {

	public void deleteBySymptom(int symptomId) {

		SqlSession session = MyBatisUtil.getXywySqlSessionFactory()
				.openSession();

		try {

			SymptomIllnessRefMapper mapper = session
					.getMapper(SymptomIllnessRefMapper.class);
			mapper.deleteBySymptom(symptomId);
		} finally {
			session.close();
		}
	}
}
