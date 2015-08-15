package com.cinyi.crawlers.xywy.dao;

import org.apache.ibatis.session.SqlSession;

import com.cinyi.crawlers.utils.MyBatisUtil;
import com.cinyi.crawlers.xywy.entity.IllnessCheckupRef;
import com.cinyi.crawlers.xywy.mapper.IllnessCheckupRefMapper;

public class IllnessCheckupRefDao extends
		BaseDao<IllnessCheckupRefMapper, IllnessCheckupRef> {

	public void deleteByIllness(int illnessId) {

		SqlSession session = MyBatisUtil.getXywySqlSessionFactory()
				.openSession();
		try {
			IllnessCheckupRefMapper mapper = session
					.getMapper(IllnessCheckupRefMapper.class);
			mapper.deleteByIllness(illnessId);
		} finally {
			session.close();
		}
	}
}
