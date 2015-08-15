package com.cinyi.crawlers.xywy.mapper;

import com.cinyi.crawlers.xywy.entity.BodyHotSymptomRef;

public interface BodyHotSymptomRefMapper extends BaseMapper<BodyHotSymptomRef> {

	void deleteByBody(int bodyId);
	
}
