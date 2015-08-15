package com.cinyi.crawlers.xywy.dao;

import org.apache.ibatis.session.SqlSession;

import com.cinyi.crawlers.utils.MyBatisUtil;
import com.cinyi.crawlers.xywy.entity.BodyIllnessRef;
import com.cinyi.crawlers.xywy.mapper.BodyIllnessRefMapper;

public class BodyIllnessRefDao extends
		BaseDao<BodyIllnessRefMapper, BodyIllnessRef> {

	public void deleteByBody(int bodyId) {

		SqlSession session = MyBatisUtil.getXywySqlSessionFactory()
				.openSession();

		try {

			BodyIllnessRefMapper mapper = session
					.getMapper(BodyIllnessRefMapper.class);
			mapper.deleteByBody(bodyId);
		} finally {
			session.close();
		}
	}
}
