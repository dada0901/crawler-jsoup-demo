package com.cinyi.crawlers.xywy.mapper;

import java.util.List;

public interface BaseMapper<T> {
	
	List<T> selectAll();

	List<T> selectByName(String name);

	void insert(T entity);

	void update(T entity);
}
