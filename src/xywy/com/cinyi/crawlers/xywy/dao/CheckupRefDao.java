package com.cinyi.crawlers.xywy.dao;

import org.apache.ibatis.session.SqlSession;

import com.cinyi.crawlers.utils.MyBatisUtil;
import com.cinyi.crawlers.xywy.entity.CheckupRef;
import com.cinyi.crawlers.xywy.mapper.CheckupRefMapper;

public class CheckupRefDao extends BaseDao<CheckupRefMapper,CheckupRef> {
	
	public void deleteByCheckup(int checkupId){
		
		SqlSession session = MyBatisUtil.getXywySqlSessionFactory().openSession();
		
		try {
			CheckupRefMapper mapper = session.getMapper(CheckupRefMapper.class);
			mapper.deleteByCheckup(checkupId);
			
		} finally {
			session.close();
		}
		
	}
}
