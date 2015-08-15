package com.cinyi.crawlers.xywy.entity;

public class SpecialtyIllnessRef {

	private int id;
	private int specialtyId;
	private int illnessId;

	public SpecialtyIllnessRef(int specialtyId, int illnessId) {
		super();
		this.specialtyId = specialtyId;
		this.illnessId = illnessId;
	}

	public int getIllnessId() {
		return illnessId;
	}

	public void setIllnessId(int illnessId) {
		this.illnessId = illnessId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSpecialtyId() {
		return specialtyId;
	}

	public void setSpecialtyId(int specialtyId) {
		this.specialtyId = specialtyId;
	}
}
