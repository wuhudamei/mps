package cn.damei.entity.mobile.Manager;

import cn.damei.common.persistence.DataEntity2;



public class OrderTaskpackageProcedureVo extends DataEntity2<OrderTaskpackageProcedureVo>{

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer taskpackageId;
	private String packageName;
	private String procedureNo;
	private String procedureName;
	private String measurementUnit;
	private String measurementUnitLabel;
	private String budgetNumber;
	private String realNumber;
	private String synthesizeUnitPrice;
	private String synthesizePrice;
	private String remarks;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getTaskpackageId() {
		return taskpackageId;
	}
	public void setTaskpackageId(Integer taskpackageId) {
		this.taskpackageId = taskpackageId;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getProcedureNo() {
		return procedureNo;
	}
	public void setProcedureNo(String procedureNo) {
		this.procedureNo = procedureNo;
	}
	public String getProcedureName() {
		return procedureName;
	}
	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}
	public String getMeasurementUnit() {
		return measurementUnit;
	}
	public void setMeasurementUnit(String measurementUnit) {
		this.measurementUnit = measurementUnit;
	}
	public String getMeasurementUnitLabel() {
		return measurementUnitLabel;
	}
	public void setMeasurementUnitLabel(String measurementUnitLabel) {
		this.measurementUnitLabel = measurementUnitLabel;
	}
	public String getBudgetNumber() {
		return budgetNumber;
	}
	public void setBudgetNumber(String budgetNumber) {
		this.budgetNumber = budgetNumber;
	}
	public String getRealNumber() {
		return realNumber;
	}
	public void setRealNumber(String realNumber) {
		this.realNumber = realNumber;
	}
	public String getSynthesizeUnitPrice() {
		return synthesizeUnitPrice;
	}
	public void setSynthesizeUnitPrice(String synthesizeUnitPrice) {
		this.synthesizeUnitPrice = synthesizeUnitPrice;
	}
	public String getSynthesizePrice() {
		return synthesizePrice;
	}
	public void setSynthesizePrice(String synthesizePrice) {
		this.synthesizePrice = synthesizePrice;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
