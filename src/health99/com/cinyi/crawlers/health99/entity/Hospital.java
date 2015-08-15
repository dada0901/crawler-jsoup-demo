package com.cinyi.crawlers.health99.entity;

/**
 * 
* @ClassName: Hospital 
* @Description: 医院实体类（99健康网） 
* @author fuda
* @date 2014年7月15日 下午1:58:32 
*
 */
public class Hospital {

	private int id;
	private String name;
	private String alias;
	private String city;
	private String district;
	private String property;
	private String remark;
	private String level;
	private String telephone;
	private String address;
	private String officeList;
	private String website;
	private String postCode;
	private byte[] photo;
	private String busInfo;
	private String medicare;

	public String getMedicare() {
		return medicare;
	}

	public void setMedicare(String medicare) {
		this.medicare = medicare;
	}

	public String getBusInfo() {
		return busInfo;
	}

	public void setBusInfo(String busInfo) {
		this.busInfo = busInfo;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
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

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOfficeList() {
		return officeList;
	}

	public void setOfficeList(String officeList) {
		this.officeList = officeList;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	
	@Override
	public String toString(){
		return this.name;
	}
}
