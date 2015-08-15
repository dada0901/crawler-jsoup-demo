package com.cinyi.crawlers.xywy.entity;

public class SymptomBodyRef {

	private int id;
	private int symptomId;
	private int bodyId;

	public int getId() {
		return id;
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

	public int getBodyId() {
		return bodyId;
	}

	public void setBodyId(int bodyId) {
		this.bodyId = bodyId;
	}

	public SymptomBodyRef() {
		super();
	}

	public SymptomBodyRef(int symptomId, int bodyId) {
		super();
		this.symptomId = symptomId;
		this.bodyId = bodyId;
	}
}
