package com.cinyi.crawlers.xywy.entity;

/**
 * 
* @ClassName: People 
* @Description: 人群实体类 
* @author fuda
* @date 2014年7月22日 上午10:29:41 
*
 */
public class People {

	private int id;
	private String name;
	private String hotIllnessRef;

	public String getHotIllnessRef() {
		return hotIllnessRef;
	}

	public void setHotIllnessRef(String hotIllnessRef) {
		this.hotIllnessRef = hotIllnessRef;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.getName();
	}

}
