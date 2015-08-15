package com.cinyi.crawlers.xywy.dao;

import org.apache.ibatis.session.SqlSession;

import com.cinyi.crawlers.utils.MyBatisUtil;
import com.cinyi.crawlers.xywy.entity.DoctorIllnessRef;
import com.cinyi.crawlers.xywy.mapper.DoctorIllnessMapper;

public class DoctorIllnessRefDao extends
		BaseDao<DoctorIllnessMapper, DoctorIllnessRef> {

	public void deleteByDoctor(int doctorId) {

		SqlSession session = MyBatisUtil.getXywySqlSessionFactory()
				.openSession();
		try {
			DoctorIllnessMapper mapper = session
					.getMapper(DoctorIllnessMapper.class);
			mapper.deleteByDoctor(doctorId);
		} finally {
			session.close();
		}
	}
}
