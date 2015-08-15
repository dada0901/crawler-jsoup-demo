package com.cinyi.crawlers.xywy.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.cinyi.crawlers.utils.MyBatisUtil;
import com.cinyi.crawlers.xywy.entity.Doctor;
import com.cinyi.crawlers.xywy.entity.DoctorParameter;
import com.cinyi.crawlers.xywy.mapper.DoctorMapper;

public class DoctorDao extends BaseDao<DoctorMapper, Doctor> {

	public Doctor selectByNameAndHospital(DoctorParameter params) {

		Doctor entity = null;

		SqlSession session = MyBatisUtil.getXywySqlSessionFactory()
				.openSession();
		try {
			DoctorMapper mapper = session.getMapper(DoctorMapper.class);
			List<Doctor> list = mapper.selectByNameAndHospital(params);

			if (list.size() > 0) {
				entity = list.get(0);
			}

			return entity;
		} finally {
			session.close();
		}
	}
	
}
