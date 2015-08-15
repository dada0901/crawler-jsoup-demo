package com.cinyi.crawlers.xywy.mapper;

import java.util.List;

import com.cinyi.crawlers.xywy.entity.Doctor;
import com.cinyi.crawlers.xywy.entity.DoctorParameter;

public interface DoctorMapper extends BaseMapper<Doctor> {

	List<Doctor> selectByNameAndHospital(DoctorParameter params);
}
