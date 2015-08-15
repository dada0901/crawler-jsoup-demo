package com.cinyi.crawlers.ypk39.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.cinyi.crawlers.ypk39.mapper.BaseMapper;
import com.cinyi.crawlers.utils.MyBatisUtil;

/**
 * 
* @ClassName: BaseDao 
* @Description: 数据层的基类，包含通用的数据层逻辑  
* @author fuda
* @date 2014年7月15日 下午2:18:53 
* 
* @param <M> Mapper
* @param <T> Entity
 */
public class BaseDao<M extends BaseMapper<T>, T> {

	private volatile Class<M> genericClassReferenceCache;

	public T selectOneByName(String name) {

		T entity = null;

		SqlSession session = MyBatisUtil.getHealth99SqlSessionFactory()
				.openSession();
		try {
			M mapper = session.getMapper(getDaoClass());
			List<T> list = mapper.selectByName(name);

			if (list.size() > 0) {
				entity = list.get(0);
			}

			return entity;
		} finally {
			session.close();
		}
	}

	public T insert(T entity) {

		SqlSession session = MyBatisUtil.getHealth99SqlSessionFactory()
				.openSession();
		try {
			M mapper = session.getMapper(getDaoClass());
			mapper.insert(entity);
			session.commit();
			return entity;
		} finally {
			session.close();
		}
	}

	public void update(T entity) {
		SqlSession session = MyBatisUtil.getHealth99SqlSessionFactory()
				.openSession();
		try {
			M mapper = session.getMapper(getDaoClass());
			mapper.update(entity);
			session.commit();
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public final Class<M> getDaoClass() {
		Class<M> klass = genericClassReferenceCache;
		if (klass == null) {
			ParameterizedType parameterizedType = (ParameterizedType) this
					.getClass().getGenericSuperclass();
			klass = (Class<M>) (parameterizedType.getActualTypeArguments()[0]);
			genericClassReferenceCache = klass;
		}
		return klass;
	}
}
