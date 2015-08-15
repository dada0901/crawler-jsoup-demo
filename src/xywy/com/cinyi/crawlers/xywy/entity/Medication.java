package com.cinyi.crawlers.xywy.entity;

/**
 * 
 * @ClassName: Medication
 * @Description: 药品实体类
 * @author fuda
 * @date 2014年7月14日 下午5:47:51
 * 
 */
public class Medication {

	private int id;

	// 药品名称
	private String name;

	// 通用名
	private String alias;

	// 通用名首字母
	private String spell;

	// 处方药或非处方药
	private String cffcf;

	// 中药或西药
	private String zyxy;

	// 参考价格
	private String ckjg;

	// 生产厂家
	private String sccj;

	// 功能主治
	private String gnzz;

	// 主要成分
	private String zycf;

	// 包装规格
	private String bzgg;

	// 用法用量
	private String yfyl;

	// 图片
	private byte[] photo;

	// 批准文号
	private String pzwh;

	// 主治疾病
	private String zzjb;

	public String getZzjb() {
		return zzjb;
	}

	public void setZzjb(String zzjb) {
		this.zzjb = zzjb;
	}

	public String getPzwh() {
		return pzwh;
	}

	public void setPzwh(String pzwh) {
		this.pzwh = pzwh;
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

	public String getSpell() {
		return spell;
	}

	public void setSpell(String spell) {
		this.spell = spell;
	}

	public String getCffcf() {
		return cffcf;
	}

	public void setCffcf(String cffcf) {
		this.cffcf = cffcf;
	}

	public String getZyxy() {
		return zyxy;
	}

	public void setZyxy(String zyxy) {
		this.zyxy = zyxy;
	}

	public String getCkjg() {
		return ckjg;
	}

	public void setCkjg(String ckjg) {
		this.ckjg = ckjg;
	}

	public String getSccj() {
		return sccj;
	}

	public void setSccj(String sccj) {
		this.sccj = sccj;
	}

	public String getGnzz() {
		return gnzz;
	}

	public void setGnzz(String gnzz) {
		this.gnzz = gnzz;
	}

	public String getZycf() {
		return zycf;
	}

	public void setZycf(String zycf) {
		this.zycf = zycf;
	}

	public String getBzgg() {
		return bzgg;
	}

	public void setBzgg(String bzgg) {
		this.bzgg = bzgg;
	}

	public String getYfyl() {
		return yfyl;
	}

	public void setYfyl(String yfyl) {
		this.yfyl = yfyl;
	}

	public String toString() {
		return this.getName() + "（ " + this.getSccj() + " " + this.getPzwh()
				+ " ）";
	}
}
