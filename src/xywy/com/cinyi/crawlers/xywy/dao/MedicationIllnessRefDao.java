package com.cinyi.crawlers.xywy.dao;

import org.apache.ibatis.session.SqlSession;

import com.cinyi.crawlers.utils.MyBatisUtil;
import com.cinyi.crawlers.xywy.entity.MedicationIllnessRef;
import com.cinyi.crawlers.xywy.mapper.MedicationIllnessRefMapper;

public class MedicationIllnessRefDao extends
		BaseDao<MedicationIllnessRefMapper, MedicationIllnessRef> {

	public void deleteByMedication(int medicationId) {

		SqlSession session = MyBatisUtil.getXywySqlSessionFactory()
				.openSession();

		try {

			MedicationIllnessRefMapper mapper = session
					.getMapper(MedicationIllnessRefMapper.class);
			mapper.deleteByMedication(medicationId);

		} finally {
			session.close();
		}
	}
}
