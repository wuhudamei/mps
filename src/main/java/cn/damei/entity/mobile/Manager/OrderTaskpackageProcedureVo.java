package cn.damei.entity.mobile.Manager;

import cn.damei.common.persistence.DataEntity2;

/**
 * 任务包结算Entity
 * @author qww
 * @version 2016-10-15
 */

public class OrderTaskpackageProcedureVo extends DataEntity2<OrderTaskpackageProcedureVo>{

	private static final long serialVersionUID = 1L;

	private Integer id; // 任务包工序id
	private Integer taskpackageId; // 任务包id
	private String packageName; // 任务包名称
	private String procedureNo; // 工序编号
	private String procedureName; // 工序名称
	private String measurementUnit; // 计算单位
	private String measurementUnitLabel; // 计量单位
	private String budgetNumber; // 预算数量(预算工程量)
	private String realNumber; // 真实数量(实际工程量)
	private String synthesizeUnitPrice; // 综合单价
	private String synthesizePrice; // 结算价(综合价)
	private String remarks; // 备注
	
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