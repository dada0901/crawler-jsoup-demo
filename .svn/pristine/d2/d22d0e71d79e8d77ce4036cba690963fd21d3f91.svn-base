package com.cinyi.crawlers.xywy.dao;

import org.apache.ibatis.session.SqlSession;

import com.cinyi.crawlers.utils.MyBatisUtil;
import com.cinyi.crawlers.xywy.entity.SpecialtyIllnessRef;
import com.cinyi.crawlers.xywy.mapper.SpecialtyIllnessRefMapper;

public class SpecialtyIllnessRefDao extends
		BaseDao<SpecialtyIllnessRefMapper, SpecialtyIllnessRef> {

	public void deleteBySpecialty(int specialtyId) {

		SqlSession session = MyBatisUtil.getXywySqlSessionFactory()
				.openSession();
		try {

			SpecialtyIllnessRefMapper mapper = session
					.getMapper(SpecialtyIllnessRefMapper.class);
			mapper.deleteBySpecialty(specialtyId);
		} finally {
			session.close();
		}
	}
}
