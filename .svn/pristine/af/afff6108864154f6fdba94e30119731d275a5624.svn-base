package com.cinyi.crawlers.ypk39.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.cinyi.crawlers.utils.MyBatisUtil;
import com.cinyi.crawlers.ypk39.entity.MedicationParameter;
import com.cinyi.crawlers.ypk39.entity.Medication;
import com.cinyi.crawlers.ypk39.mapper.MedicationMapper;

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
}
