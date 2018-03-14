package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

public class EmployeeAgreementPC extends DataEntity2<EmployeeAgreementPC>{


	private static final long serialVersionUID = 1L;
	
	private String employeeType;
	private String employeeId;
	private String isSignEmployeeAgreement;
	private Date employeeAgreementSignDatetime;
	private Date employeeAgreementReadDatetime;
	
	private String realName;
	private String phone;
	private String storeName;
	private String elactricationName;
	private String projectMode;
	private String workType;
	private String storeId;
	private String elactricationId;
	
	public String getElactricationId() {
		return elactricationId;
	}
	public void setElactricationId(String elactricationId) {
		this.elactricationId = elactricationId;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getElactricationName() {
		return elactricationName;
	}
	public void setElactricationName(String elactricationName) {
		this.elactricationName = elactricationName;
	}
	public String getProjectMode() {
		return projectMode;
	}
	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}
	public String getWorkType() {
		return workType;
	}
	public void setWorkType(String workType) {
		this.workType = workType;
	}
	public String getEmployeeType() {
		return employeeType;
	}
	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getIsSignEmployeeAgreement() {
		return isSignEmployeeAgreement;
	}
	public void setIsSignEmployeeAgreement(String isSignEmployeeAgreement) {
		this.isSignEmployeeAgreement = isSignEmployeeAgreement;
	}
	public Date getEmployeeAgreementSignDatetime() {
		return employeeAgreementSignDatetime;
	}
	public void setEmployeeAgreementSignDatetime(Date employeeAgreementSignDatetime) {
		this.employeeAgreementSignDatetime = employeeAgreementSignDatetime;
	}
	public Date getEmployeeAgreementReadDatetime() {
		return employeeAgreementReadDatetime;
	}
	public void setEmployeeAgreementReadDatetime(Date employeeAgreementReadDatetime) {
		this.employeeAgreementReadDatetime = employeeAgreementReadDatetime;
	}
	
	
	
	
	
	


}
