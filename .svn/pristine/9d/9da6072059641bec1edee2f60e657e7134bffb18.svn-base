package com.cinyi.crawlers.xywy.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.cinyi.crawlers.utils.MyBatisUtil;
import com.cinyi.crawlers.xywy.mapper.BaseMapper;

/**
 * 
 * @ClassName: BaseDao
 * @Description: 数据层的基类，包含通用的数据层逻辑
 * @author fuda
 * @date 2014年7月15日 下午2:17:29
 * 
 * @param <M>
 *            Mapper
 * @param <T>
 *            Entity
 */
public class BaseDao<M extends BaseMapper<T>, T> {

	private volatile Class<M> genericClassReferenceCache;

	public List<T> selectAll() {

		SqlSession session = MyBatisUtil.getXywySqlSessionFactory()
				.openSession();
		try {
			M mapper = session.getMapper(getDaoClass());
			List<T> list = mapper.selectAll();

			return list;
		} finally {
			session.close();
		}
	}

	public T selectOneByName(String name) {

		T entity = null;

		SqlSession session = MyBatisUtil.getXywySqlSessionFactory()
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

		SqlSession session = MyBatisUtil.getXywySqlSessionFactory()
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
		SqlSession session = MyBatisUtil.getXywySqlSessionFactory()
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
