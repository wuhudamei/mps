
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity;


public class BizSupplierEmployeeGroup extends DataEntity<BizSupplierEmployeeGroup> {

	private static final long serialVersionUID = 1L;
	private String projectInstallItemName;
	private String supplierName;
	private String supplierId;
	private String employeeGroupId;
	private String employeeGroupId1;
	private String storeid;
	private String worktype;
	private String index;

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