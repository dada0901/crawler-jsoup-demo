package com.cinyi.crawlers.xywy.mapper;

import com.cinyi.crawlers.xywy.entity.SymptomCheckupRef;


public interface SymptomCheckupRefMapper extends BaseMapper<SymptomCheckupRef> {

	void deleteBySymptom(int symptomId);
}
