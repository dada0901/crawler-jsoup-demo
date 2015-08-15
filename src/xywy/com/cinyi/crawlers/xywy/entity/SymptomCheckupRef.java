package com.cinyi.crawlers.xywy.entity;

public class SymptomCheckupRef {

	private int id;
	private int symptomId;
	private int checkupId;

	public int getId() {
		return id;
	}

	public SymptomCheckupRef(int symptomId, int checkupId) {
		super();
		this.symptomId = symptomId;
		this.checkupId = checkupId;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSymptomId() {
		return symptomId;
	}

	public void setSymptomId(int symptomId) {
		this.symptomId = symptomId;
	}

	public int getCheckupId() {
		return checkupId;
	}

	public void setCheckupId(int checkupId) {
		this.checkupId = checkupId;
	}

}
