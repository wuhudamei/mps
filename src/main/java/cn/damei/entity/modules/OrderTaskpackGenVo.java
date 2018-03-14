package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity;

public class OrderTaskpackGenVo extends DataEntity<OrderTaskpackGenVo>{
	private static final long serialVersionUID = -2499276448176454118L;

	private Integer templatId;
	/**
	 * 任务包模板名称
	 */
	private String templatName;
	
	/**
	 * 任务包模板编号
	 */
	private String templatNumber;
	
	/**
	 * 工序编号 
	 */
	private String procedureNo;
	
	/**
	 * 工序名称
	 */
	private String procedureName;
	
	/**
	 * 预算员确认数量
	 */
	private String budgetNumber;
	
	/**
	 * 定额综合价
	 */
	private String synthesizePrice;
	
	/**
	 * 辅料价格
	 */
	private String accessoriesPrice;
	
	/**
	 * 人工价
	 */
	private String laborPrice;
	
	/**
	 * 单位
	 */
	private String measurementUnit;
	
	/**
	 * 备注
	 */
	private String remarks;
	private String laborAuxiliaryMaterialsBudgetAmount;
	private String taskpackageId;
	private String packageName;
	private String projectMode;
	
	private String laborBudgetAmount;// 人工预算金额
	private String auxiliaryMaterialsBudgetAmount;// 辅料预算金额
	
	public String getTaskpackageId() {
		return taskpackageId;
	}

	public void setTaskpackageId(String taskpackageId) {
		this.taskpackageId = taskpackageId;
	}

	
	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	
	public String getLaborAuxiliaryMaterialsBudgetAmount() {
		return laborAuxiliaryMaterialsBudgetAmount;
	}

	public void setLaborAuxiliaryMaterialsBudgetAmount(String laborAuxiliaryMaterialsBudgetAmount) {
		this.laborAuxiliaryMaterialsBudgetAmount = laborAuxiliaryMaterialsBudgetAmount;
	}

	public String getTemplatName() {
		return templatName;
	}

	public void setTemplatName(String templatName) {
		this.templatName = templatName;
	}

	public String getTemplatNumber() {
		return templatNumber;
	}

	public void setTemplatNumber(String templatNumber) {
		this.templatNumber = templatNumber;
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

	public String getBudgetNumber() {
		return budgetNumber;
	}

	public void setBudgetNumber(String budgetNumber) {
		this.budgetNumber = budgetNumber;
	}

	public String getSynthesizePrice() {
		return synthesizePrice;
	}

	public void setSynthesizePrice(String synthesizePrice) {
		this.synthesizePrice = synthesizePrice;
	}

	public String getAccessoriesPrice() {
		return accessoriesPrice;
	}

	public void setAccessoriesPrice(String accessoriesPrice) {
		this.accessoriesPrice = accessoriesPrice;
	}

	public String getLaborPrice() {
		return laborPrice;
	}

	public void setLaborPrice(String laborPrice) {
		this.laborPrice = laborPrice;
	}

	public String getMeasurementUnit() {
		return measurementUnit;
	}

	public void setMeasurementUnit(String measurementUnit) {
		this.measurementUnit = measurementUnit;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getLaborBudgetAmount() {
		return laborBudgetAmount;
	}

	public void setLaborBudgetAmount(String laborBudgetAmount) {
		this.laborBudgetAmount = laborBudgetAmount;
	}

	public String getAuxiliaryMaterialsBudgetAmount() {
		return auxiliaryMaterialsBudgetAmount;
	}

	public void setAuxiliaryMaterialsBudgetAmount(String auxiliaryMaterialsBudgetAmount) {
		this.auxiliaryMaterialsBudgetAmount = auxiliaryMaterialsBudgetAmount;
	}

	public Integer getTemplatId() {
		return templatId;
	}

	public void setTemplatId(Integer templatId) {
		this.templatId = templatId;
	}
	
	
}
