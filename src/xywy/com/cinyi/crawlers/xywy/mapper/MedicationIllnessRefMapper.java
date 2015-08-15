package com.cinyi.crawlers.xywy.mapper;

import com.cinyi.crawlers.xywy.entity.MedicationIllnessRef;

public interface MedicationIllnessRefMapper extends BaseMapper<MedicationIllnessRef> {

	void deleteByMedication(int medicationId);
}
