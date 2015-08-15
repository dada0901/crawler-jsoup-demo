package com.cinyi.crawlers.xywy.entity;

public class SymptomSpecialtyRef {

	private int id;
	private int SymptomId;
	private int SpecialtyId;

	public SymptomSpecialtyRef() {
		super();
	}

	public SymptomSpecialtyRef(int symptomId, int specialtyId) {
		super();
		SymptomId = symptomId;
		SpecialtyId = specialtyId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSymptomId() {
		return SymptomId;
	}

	public void setSymptomId(int symptomId) {
		SymptomId = symptomId;
	}

	public int getSpecialtyId() {
		return SpecialtyId;
	}

	public void setSpecialtyId(int specialtyId) {
		SpecialtyId = specialtyId;
	}
}
