package com.cinyi.crawlers.xywy.entity;

public class DoctorParameter {
	
	private String name;
	private String hospitalName;
	private String officeName;
	
	public String getName() {
		return name;
	}
	public DoctorParameter(String name, String hospitalName, String officeName) {
		super();
		this.name = name;
		this.hospitalName = hospitalName;
		this.officeName = officeName;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public String getOfficeName() {
		return officeName;
	}
	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}
}
