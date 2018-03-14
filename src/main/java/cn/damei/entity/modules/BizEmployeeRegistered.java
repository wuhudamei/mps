
package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;

import java.util.Date;


public class BizEmployeeRegistered extends DataEntity2<BizEmployeeRegistered> {

	private static final long serialVersionUID = 1L;
	private String storeId;
	private String projectMode;
	private String empType;
	private Date startEntryDate;
	private Date endEntryDate;

	private String storeIdName;
	private String month;
	private Integer employeeEntriesCount;
	private Integer employeeRegisteredCount;


	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}

	public String getEmpType() {
		return empType;
	}

	public void setEmpType(String empType) {
		this.empType = empType;
	}

	public Date getStartEntryDate() {
		return startEntryDate;
	}

	public void setStartEntryDate(Date startEntryDate) {
		this.startEntryDate = startEntryDate;
	}

	public Date getEndEntryDate() {
		return endEntryDate;
	}

	public void setEndEntryDate(Date endEntryDate) {
		this.endEntryDate = endEntryDate;
	}

	public String getStoreIdName() {
		return storeIdName;
	}

	public void setStoreIdName(String storeIdName) {
		this.storeIdName = storeIdName;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Integer getEmployeeEntriesCount() {
		return employeeEntriesCount;
	}

	public void setEmployeeEntriesCount(Integer employeeEntriesCount) {
		this.employeeEntriesCount = employeeEntriesCount;
	}

	public Integer getEmployeeRegisteredCount() {
		return employeeRegisteredCount;
	}

	public void setEmployeeRegisteredCount(Integer employeeRegisteredCount) {
		this.employeeRegisteredCount = employeeRegisteredCount;
	}
}