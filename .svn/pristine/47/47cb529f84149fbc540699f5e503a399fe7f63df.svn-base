package com.cinyi.crawlers.xywy.dao;

import org.apache.ibatis.session.SqlSession;

import com.cinyi.crawlers.utils.MyBatisUtil;
import com.cinyi.crawlers.xywy.entity.BodyHotIllnessRef;
import com.cinyi.crawlers.xywy.mapper.BodyHotIllnessRefMapper;

public class BodyHotIllnessRefDao extends
		BaseDao<BodyHotIllnessRefMapper, BodyHotIllnessRef> {

	public void deleteByBody(int bodyId) {

		SqlSession session = MyBatisUtil.getXywySqlSessionFactory()
				.openSession();

		try {

			BodyHotIllnessRefMapper mapper = session
					.getMapper(BodyHotIllnessRefMapper.class);
			mapper.deleteByBody(bodyId);
		} finally {
			session.close();
		}
	}
}
