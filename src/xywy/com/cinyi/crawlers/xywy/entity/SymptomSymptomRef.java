package com.cinyi.crawlers.xywy.entity;

public class SymptomSymptomRef {

	private int id;
	private int symptomId;
	private int symptomRefId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public SymptomSymptomRef(int symptomId, int symptomRefId) {
		super();
		this.symptomId = symptomId;
		this.symptomRefId = symptomRefId;
	}

	public int getSymptomId() {
		return symptomId;
	}

	public void setSymptomId(int symptomId) {
		this.symptomId = symptomId;
	}

	public int getSymptomRefId() {
		return symptomRefId;
	}

	public void setSymptomRefId(int symptomRefId) {
		this.symptomRefId = symptomRefId;
	}

}
