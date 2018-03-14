/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;

import java.util.Date;

/**
 * 在册人员Entity
 * 
 * @author wyb
 */
public class BizEmployeeRegistered extends DataEntity2<BizEmployeeRegistered> {

	private static final long serialVersionUID = 1L;
	private String storeId;// 门店
	private String projectMode; // 工程模式
	private String empType; // 人员类别
	private Date startEntryDate; // 录入日期开始
	private Date endEntryDate; // 录入日期结束

	private String storeIdName; //门店名称
	private String month; //月份
	private Integer employeeEntriesCount; //当月录入人数
	private Integer employeeRegisteredCount; //当月在册人数


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