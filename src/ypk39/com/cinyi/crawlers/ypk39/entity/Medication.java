package com.cinyi.crawlers.ypk39.entity;

/**
 * 
* @ClassName: Medication 
* @Description: 药品实体类（39药品通） 
* @author fuda
* @date 2014年7月15日 下午4:07:00 
*
 */
public class Medication {

	private int id;

	// 分类
	private String category;

	// 名称
	private String name;

	// 全称
	private String fullName;

	// 成分
	private String composition;

	// 适应症
	private String syz;

	// 用法用量
	private String yfyl;

	// 不良反应
	private String blfy;

	// 禁忌
	private String ban;

	// 注意事项
	private String zysx;

	// 药物互相作用
	private String ywhxzy;

	// 贮藏
	private String storage;

	// 批准文号
	private String approveNumber;

	// 生产企业
	private String manufacturer;

	// 剂型
	private String formulation;

	// 规格
	private String specification;

	// 条码
	private String barcode;

	// 药品图片
	private byte[] photo;

	// 产地
	private String origin;

	// 处方药
	private String prescription;

	// 药理作用
	private String ylzy;

	// 有效期
	private String validTime;

	public String getYlzy() {
		return ylzy;
	}

	public void setYlzy(String ylzy) {
		this.ylzy = ylzy;
	}

	public String getValidTime() {
		return validTime;
	}

	public void setValidTime(String validTime) {
		this.validTime = validTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPrescription() {
		return prescription;
	}

	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getComposition() {
		return composition;
	}

	public void setComposition(String composition) {
		this.composition = composition;
	}

	public String getSyz() {
		return syz;
	}

	public void setSyz(String syz) {
		this.syz = syz;
	}

	public String getYfyl() {
		return yfyl;
	}

	public void setYfyl(String yfyl) {
		this.yfyl = yfyl;
	}

	public String getBlfy() {
		return blfy;
	}

	public void setBlfy(String blfy) {
		this.blfy = blfy;
	}

	public String getBan() {
		return ban;
	}

	public void setBan(String ban) {
		this.ban = ban;
	}

	public String getZysx() {
		return zysx;
	}

	public void setZysx(String zysx) {
		this.zysx = zysx;
	}

	public String getYwhxzy() {
		return ywhxzy;
	}

	public void setYwhxzy(String ywhxzy) {
		this.ywhxzy = ywhxzy;
	}

	public String getStorage() {
		return storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}

	public String getApproveNumber() {
		return approveNumber;
	}

	public void setApproveNumber(String approveNumber) {
		this.approveNumber = approveNumber;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getFormulation() {
		return formulation;
	}

	public void setFormulation(String formulation) {
		this.formulation = formulation;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
}
