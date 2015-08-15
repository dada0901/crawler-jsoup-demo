package com.cinyi.crawlers.xywy.mapper;

import com.cinyi.crawlers.xywy.entity.SymptomSpecialtyRef;

public interface SymptomSpecialtyRefMapper extends BaseMapper<SymptomSpecialtyRef> {

	void deleteBySymptom(int symptomId);
	
}
