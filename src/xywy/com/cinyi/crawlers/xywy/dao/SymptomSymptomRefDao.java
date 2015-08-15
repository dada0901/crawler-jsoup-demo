package com.cinyi.crawlers.xywy.dao;

import org.apache.ibatis.session.SqlSession;

import com.cinyi.crawlers.utils.MyBatisUtil;
import com.cinyi.crawlers.xywy.entity.SymptomSymptomRef;
import com.cinyi.crawlers.xywy.mapper.SymptomSymptomRefMapper;

public class SymptomSymptomRefDao extends
		BaseDao<SymptomSymptomRefMapper, SymptomSymptomRef> {

	public void deleteBySymptom(int symptomId) {

		SqlSession session = MyBatisUtil.getXywySqlSessionFactory()
				.openSession();

		try {

			SymptomSymptomRefMapper mapper = session
					.getMapper(SymptomSymptomRefMapper.class);
			mapper.deleteBySymptom(symptomId);
		} finally {
			session.close();
		}
	}
}
