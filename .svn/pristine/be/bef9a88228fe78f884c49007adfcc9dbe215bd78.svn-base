package com.cinyi.crawlers.xywy.dao;

import org.apache.ibatis.session.SqlSession;

import com.cinyi.crawlers.utils.MyBatisUtil;
import com.cinyi.crawlers.xywy.entity.Illness;
import com.cinyi.crawlers.xywy.mapper.IllnessMapper;

public class IllnessDao extends BaseDao<IllnessMapper, Illness> {

	public void updatePeopleAndHbbl(Illness ill) {
		SqlSession session = MyBatisUtil.getXywySqlSessionFactory()
				.openSession();
		try {
			IllnessMapper mapper = session.getMapper(IllnessMapper.class);
			mapper.updatePeopleAndBbbl(ill);
			session.commit();
		} finally {
			session.close();
		}
	}
}
