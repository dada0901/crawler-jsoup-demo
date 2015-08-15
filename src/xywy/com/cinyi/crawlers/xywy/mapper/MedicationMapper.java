package com.cinyi.crawlers.xywy.mapper;

import java.util.List;

import com.cinyi.crawlers.xywy.entity.Medication;
import com.cinyi.crawlers.xywy.entity.MedicationParameter;

public interface MedicationMapper extends BaseMapper<Medication> {
	
	List<Medication> selectAllZzjb();

	List<Medication> selectByNameAndSccj(MedicationParameter params);
	
	List<Medication> selectAllNameAndManufacturerAndBatchNumber(MedicationParameter params);
}
