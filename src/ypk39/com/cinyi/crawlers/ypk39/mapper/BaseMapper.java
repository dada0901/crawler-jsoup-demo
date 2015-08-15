package com.cinyi.crawlers.ypk39.mapper;

import java.util.List;

public interface BaseMapper<T> {
	
	List<T> selectByName(String name);

	void insert(T entity);

	void update(T entity);
}
