/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.TreeEntity;

/**
 * 机构Entity
 * @author ThinkGem
 * @version 2013-05-15
 */
public class Office extends TreeEntity<Office> {

	private static final long serialVersionUID = 1L;
//	private Office parent;	// 父级编号
//	private String parentIds; // 所有父级编号
	private Area area;		// 归属区域
	private String code; 	// 机构编码
//	private String name; 	// 机构名称
//	private Integer sort;		// 排序
	private String type; 	// 机构类型（1：公司；2：部门；3：小组）
	private String grade; 	// 机构等级（1：一级；2：二级；3：三级；4：四级）
	private String address; // 联系地址
	private String zipCode; // 邮政编码
	private String master; 	// 负责人
	private String phone; 	// 电话
	private String fax; 	// 传真
	private String email; 	// 邮箱
	private String useable;//是否可用
	private User primaryPerson;//主负责人
	private User deputyPerson;//副负责人
	private List<String> childDeptList;//快速添加子部门
	
	private String label;
	private String value;

	public Office(){
		super();
//		this.sort = 30;
		this.type = "2";
	}

	public Office(String id){
		super(id);
	}

	@Length(min=0, max=255)
	public String getAddress() {
		return address;
	}

	@NotNull
	public Area getArea() {
		return area;
	}
	public List<String> getChildDeptList() {
		return childDeptList;
	}

	@Length(min=0, max=100)
	public String getCode() {
		return code;
	}
	
	public User getDeputyPerson() {
		return deputyPerson;
	}

	@Length(min=0, max=200)
	public String getEmail() {
		return email;
	}

	@Length(min=0, max=200)
	public String getFax() {
		return fax;
	}

	@Length(min=1, max=1)
	public String getGrade() {
		return grade;
	}

	public String getLabel() {
		return label;
	}

	@Length(min=0, max=100)
	public String getMaster() {
		return master;
	}

	//	@JsonBackReference
//	@NotNull
	public Office getParent() {
		return parent;
	}

	@Length(min=0, max=200)
	public String getPhone() {
		return phone;
	}

public User getPrimaryPerson() {
		return primaryPerson;
	}

	@Length(min=1, max=1)
	public String getType() {
		return type;
	}

	public String getUseable() {
		return useable;
	}

	public String getValue() {
		return value;
	}
	
	@Length(min=0, max=100)
	public String getZipCode() {
		return zipCode;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setArea(Area area) {
		this.area = area;
	}
//
//	@Length(min=1, max=100)
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public Integer getSort() {
//		return sort;
//	}
//
//	public void setSort(Integer sort) {
//		this.sort = sort;
//	}

	public void setChildDeptList(List<String> childDeptList) {
		this.childDeptList = childDeptList;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setDeputyPerson(User deputyPerson) {
		this.deputyPerson = deputyPerson;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setMaster(String master) {
		this.master = master;
	}

	public void setParent(Office parent) {
		this.parent = parent;
	}
//
//	@Length(min=1, max=2000)
//	public String getParentIds() {
//		return parentIds;
//	}
//
//	public void setParentIds(String parentIds) {
//		this.parentIds = parentIds;
//	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setPrimaryPerson(User primaryPerson) {
		this.primaryPerson = primaryPerson;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setUseable(String useable) {
		this.useable = useable;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

//	public String getParentId() {
//		return parent != null && parent.getId() != null ? parent.getId() : "0";
//	}
	
	@Override
	public String toString() {
		return name;
	}
}