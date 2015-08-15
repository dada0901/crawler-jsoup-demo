package com.cinyi.crawlers.xywy.entity;

public class SymptomIllnessRef {

	private int id;
	private int symptomId;
	private int illnessId;

	public int getId() {
		return id;
	}

	public SymptomIllnessRef() {
		super();
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSymptomId() {
		return symptomId;
	}

	public SymptomIllnessRef(int symptomId, int illnessId) {
		super();
		this.symptomId = symptomId;
		this.illnessId = illnessId;
	}

	public void setSymptomId(int symptomId) {
		this.symptomId = symptomId;
	}

	public int getIllnessId() {
		return illnessId;
	}

	public void setIllnessId(int illnessId) {
		this.illnessId = illnessId;
	}

}
