
package cn.damei.entity.modules;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity;


public class BizPmAttendSalarySummaryBillRel extends DataEntity<BizPmAttendSalarySummaryBillRel> {
	
	private static final long serialVersionUID = 1L;
	private String pmAttendSalarySummaryBillId;
	private String pmAttendSalaryBillId;
	
	private String attendMonth;
	private String pmAttendSalarySummaryBillCode;
	private String storeId;
	private String salrayBillCount;
	private String status;
	private String statusEmployeeId;
	private Date generatedDatetime;
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