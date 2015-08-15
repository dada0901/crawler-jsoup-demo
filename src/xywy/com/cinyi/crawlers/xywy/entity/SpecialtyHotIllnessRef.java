package com.cinyi.crawlers.xywy.entity;

public class SpecialtyHotIllnessRef {

	private int id;
	private int specialtyId;
	private int hotIllnessId;

	public SpecialtyHotIllnessRef(int specialtyId, int hotIllnessId) {
		super();
		this.specialtyId = specialtyId;
		this.hotIllnessId = hotIllnessId;
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

	public int getHotIllnessId() {
		return hotIllnessId;
	}

	public void setHotIllnessId(int hotIllnessId) {
		this.hotIllnessId = hotIllnessId;
	}
}
