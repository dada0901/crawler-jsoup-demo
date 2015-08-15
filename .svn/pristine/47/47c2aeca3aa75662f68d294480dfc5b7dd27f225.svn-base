package com.cinyi.crawlers.xywy.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.cinyi.crawlers.utils.MyBatisUtil;
import com.cinyi.crawlers.xywy.entity.Medication;
import com.cinyi.crawlers.xywy.entity.MedicationParameter;
import com.cinyi.crawlers.xywy.mapper.MedicationMapper;

public class MedicationDao extends BaseDao<MedicationMapper, Medication> {

	public Medication selectByNameAndSccj(MedicationParameter params) {

		Medication entity = null;

		SqlSession session = MyBatisUtil.getXywySqlSessionFactory()
				.openSession();
		try {
			MedicationMapper mapper = session.getMapper(MedicationMapper.class);
			List<Medication> list = mapper.selectByNameAndSccj(params);

			if (list.size() > 0) {
				entity = list.get(0);
			}

			return entity;
		} finally {
			session.close();
		}
	}

	public List<Medication> selectAllZzjb() {

		SqlSession session = MyBatisUtil.getXywySqlSessionFactory()
				.openSession();

		try {
			MedicationMapper mapper = session.getMapper(MedicationMapper.class);
			return mapper.selectAllZzjb();

		} finally {
			session.close();
		}
	}
}
