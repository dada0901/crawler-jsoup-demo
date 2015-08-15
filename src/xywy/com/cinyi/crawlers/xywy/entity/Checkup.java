package com.cinyi.crawlers.xywy.entity;

/**
 * 
 * @ClassName: Checkup
 * @Description: 检查项实体类
 * @author fuda
 * @date 2014年7月11日 下午5:52:10
 * 
 */
public class Checkup {

	private int id;

	// 父节点id
	private String parentId;

	// 检查项名称
	private String name;

	// 拼音首字母
	private String spell;

	// 树中完整路径
	private String treePath;

	// 检查项介绍
	private String remark;

	// 检查项正常值
	private String normalRange;

	// 临床意义
	private String lcyy;

	// 注意事项
	private String zysx;

	// 检查过程
	private String jcgc;

	// 检查相关项
	private String checkupRef;

	public String getCheckupRef() {
		return checkupRef;
	}

	public void setCheckupRef(String checkupRef) {
		this.checkupRef = checkupRef;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpell() {
		return spell;
	}

	public void setSpell(String spell) {
		this.spell = spell;
	}

	public String getTreePath() {
		return treePath;
	}

	public void setTreePath(String treePath) {
		this.treePath = treePath;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getNormalRange() {
		return normalRange;
	}

	public void setNormalRange(String normalRange) {
		this.normalRange = normalRange;
	}

	public String getLcyy() {
		return lcyy;
	}

	public void setLcyy(String lcyy) {
		this.lcyy = lcyy;
	}

	public String getZysx() {
		return zysx;
	}

	public void setZysx(String zysx) {
		this.zysx = zysx;
	}

	public String getJcgc() {
		return jcgc;
	}

	public void setJcgc(String jcgc) {
		this.jcgc = jcgc;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
