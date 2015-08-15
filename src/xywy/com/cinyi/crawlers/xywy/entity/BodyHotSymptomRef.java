package com.cinyi.crawlers.xywy.entity;

public class BodyHotSymptomRef {

	private int id;
	private int bodyId;
	private int hotSymptomId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBodyId() {
		return bodyId;
	}

	public void setBodyId(int bodyId) {
		this.bodyId = bodyId;
	}

	public int getHotSymptomId() {
		return hotSymptomId;
	}

	public void setHotSymptomId(int hotSymptomId) {
		this.hotSymptomId = hotSymptomId;
	}

	public BodyHotSymptomRef(int bodyId, int hotSymptomId) {
		super();
		this.bodyId = bodyId;
		this.hotSymptomId = hotSymptomId;
	}
}
