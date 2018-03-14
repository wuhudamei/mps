/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity;

/**
 * 工资单批次审批Entity
 * @author wl
 * @version 2017-08-10
 */
public class BizPmAttendSalarySummaryBillRel extends DataEntity<BizPmAttendSalarySummaryBillRel> {
	
	private static final long serialVersionUID = 1L;
	private String pmAttendSalarySummaryBillId;		// 工资汇总单ID
	private String pmAttendSalaryBillId;		// 工资单ID
	
	private String attendMonth; //考勤月份
	private String pmAttendSalarySummaryBillCode; //工资批次编号
	private String storeId; //门店Id
	private String salrayBillCount; //工资单数量
	private String status; //工资批次状态
	private String statusEmployeeId; //操作人
	private Date generatedDatetime;		// 汇总生成时间
	private String realName;
	private String enginDepartId;
	private String departmentName;

	public String getEnginDepartId() {
		return enginDepartId;
	}

	public void setEnginDepartId(String enginDepartId) {
		this.enginDepartId = enginDepartId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public BizPmAttendSalarySummaryBillRel() {
		super();
	}

	public BizPmAttendSalarySummaryBillRel(String id){
		super(id);
	}

	@Length(min=0, max=11, message="工资汇总单ID长度必须介于 0 和 11 之间")
	public String getPmAttendSalarySummaryBillId() {
		return pmAttendSalarySummaryBillId;
	}

	public void setPmAttendSalarySummaryBillId(String pmAttendSalarySummaryBillId) {
		this.pmAttendSalarySummaryBillId = pmAttendSalarySummaryBillId;
	}
	
	@Length(min=0, max=11, message="工资单ID长度必须介于 0 和 11 之间")
	public String getPmAttendSalaryBillId() {
		return pmAttendSalaryBillId;
	}

	public void setPmAttendSalaryBillId(String pmAttendSalaryBillId) {
		this.pmAttendSalaryBillId = pmAttendSalaryBillId;
	}

	public String getAttendMonth() {
		return attendMonth;
	}

	public void setAttendMonth(String attendMonth) {
		this.attendMonth = attendMonth;
	}

	public String getPmAttendSalarySummaryBillCode() {
		return pmAttendSalarySummaryBillCode;
	}

	public void setPmAttendSalarySummaryBillCode(String pmAttendSalarySummaryBillCode) {
		this.pmAttendSalarySummaryBillCode = pmAttendSalarySummaryBillCode;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getSalrayBillCount() {
		return salrayBillCount;
	}

	public void setSalrayBillCount(String salrayBillCount) {
		this.salrayBillCount = salrayBillCount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusEmployeeId() {
		return statusEmployeeId;
	}

	public void setStatusEmployeeId(String statusEmployeeId) {
		this.statusEmployeeId = statusEmployeeId;
	}

	public Date getGeneratedDatetime() {
		return generatedDatetime;
	}

	public void setGeneratedDatetime(Date generatedDatetime) {
		this.generatedDatetime = generatedDatetime;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}
	
}