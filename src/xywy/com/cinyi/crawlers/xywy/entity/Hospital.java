package com.cinyi.crawlers.xywy.entity;

/**
 * 
* @ClassName: Hospital 
* @Description: 医院实体类 
* @author fuda
* @date 2014年7月14日 下午4:09:26 
*
 */
public class Hospital {
	
	private int id;
	private String name;
	private String areaInfo;
	private String remark;
	private String address;
	private String addressWay;
	private String telephone;
	private String officeInfo;

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

	public String getAreaInfo() {
		return areaInfo;
	}

	public void setAreaInfo(String areaInfo) {
		this.areaInfo = areaInfo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddressWay() {
		return addressWay;
	}

	public void setAddressWay(String addressWay) {
		this.addressWay = addressWay;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getOfficeInfo() {
		return officeInfo;
	}

	public void setOfficeInfo(String officeInfo) {
		this.officeInfo = officeInfo;
	}
}
