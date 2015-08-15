package com.cinyi.crawlers.xywy.dao;

import org.apache.ibatis.session.SqlSession;

import com.cinyi.crawlers.utils.MyBatisUtil;
import com.cinyi.crawlers.xywy.entity.SpecialtyHotIllnessRef;
import com.cinyi.crawlers.xywy.mapper.SpecialtyHotIllnessRefMapper;

public class SpecialtyHotIllnessRefDao extends
		BaseDao<SpecialtyHotIllnessRefMapper, SpecialtyHotIllnessRef> {

	public void deleteBySpecialty(int specialtyId) {

		SqlSession session = MyBatisUtil.getXywySqlSessionFactory()
				.openSession();
		try {

			SpecialtyHotIllnessRefMapper mapper = session
					.getMapper(SpecialtyHotIllnessRefMapper.class);
			mapper.deleteBySpecialty(specialtyId);
		} finally {
			session.close();
		}
	}
}
