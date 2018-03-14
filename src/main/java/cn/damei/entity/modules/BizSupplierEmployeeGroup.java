/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity;

/**
 * 主材安装供应商和工人组Entity
 * 
 * @author ztw
 * @version 2017-07-17
 */
public class BizSupplierEmployeeGroup extends DataEntity<BizSupplierEmployeeGroup> {

	private static final long serialVersionUID = 1L;
	private String projectInstallItemName; // 供应商业务分类
	private String supplierName; // 供应商名称
	private String supplierId; // 供应商ID
	private String employeeGroupId; // 工人组ID
	private String employeeGroupId1; // 工人组ID
	private String storeid; // 门店
	private String worktype; // 工种
	private String index; // 工种

	public BizSupplierEmployeeGroup() {
		super();
	}

	public BizSupplierEmployeeGroup(String id) {
		super(id);
	}

	public String getStoreid() {
		return storeid;
	}

	public void setStoreid(String storeid) {
		this.storeid = storeid;
	}

	public String getWorktype() {
		return worktype;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getEmployeeGroupId1() {
		return employeeGroupId1;
	}

	public void setEmployeeGroupId1(String employeeGroupId1) {
		this.employeeGroupId1 = employeeGroupId1;
	}

	public void setWorktype(String worktype) {
		this.worktype = worktype;
	}

	@Length(min = 0, max = 11, message = "供应商ID长度必须介于 0 和 11 之间")
	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	@Length(min = 0, max = 11, message = "工人组ID长度必须介于 0 和 11 之间")
	public String getEmployeeGroupId() {
		return employeeGroupId;
	}

	public String getProjectInstallItemName() {
		return projectInstallItemName;
	}

	public void setProjectInstallItemName(String projectInstallItemName) {
		this.projectInstallItemName = projectInstallItemName;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setEmployeeGroupId(String employeeGroupId) {
		this.employeeGroupId = employeeGroupId;
	}

}