package com.cinyi.crawlers.xywy.entity;

public class MedicationIllnessRef {

	private int id;
	private int medicationId;
	private int illnessId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public MedicationIllnessRef(int medicationId, int illnessId) {
		super();
		this.medicationId = medicationId;
		this.illnessId = illnessId;
	}

	public int getMedicationId() {
		return medicationId;
	}

	public void setMedicationId(int medicationId) {
		this.medicationId = medicationId;
	}

	public int getIllnessId() {
		return illnessId;
	}

	public void setIllnessId(int illnessId) {
		this.illnessId = illnessId;
	}
}
