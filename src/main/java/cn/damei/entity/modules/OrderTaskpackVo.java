package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity;

public class OrderTaskpackVo extends DataEntity<OrderTaskpackVo>{
	private static final long serialVersionUID = -2499276448176454118L;
	
	private String id;

	/**
	 * 工序归属任务包
	 */
	private String packageName;
	
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
	 * 计量单位
	 */
	private String label;
	
	/**
	 * 工料结算价
	 */
	private String synthesizePrice;
	
	/**
	 * 工料费预算金额
	 */
	private String laborAuxiliaryMaterialsBudgetAmount;
	
	/**
	 * 备注
	 */
	private String remarks;
	
	/**
	 * 人工结算价
	 */
	private String laborPrice;
	
	/**
	 * 辅料结算价
	 */
	private String accessoriesPrice;
	
	private String laborBudgetAmount;// 人工费预算金额
	private String auxiliaryMaterialsBudgetAmount; // 辅料费预算金额

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

	public String getBudgetNumber() {
		return budgetNumber;
	}

	public void setBudgetNumber(String budgetNumber) {
		this.budgetNumber = budgetNumber;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getSynthesizePrice() {
		return synthesizePrice;
	}

	public void setSynthesizePrice(String synthesizePrice) {
		this.synthesizePrice = synthesizePrice;
	}

	public String getLaborAuxiliaryMaterialsBudgetAmount() {
		return laborAuxiliaryMaterialsBudgetAmount;
	}

	public void setLaborAuxiliaryMaterialsBudgetAmount(String laborAuxiliaryMaterialsBudgetAmount) {
		this.laborAuxiliaryMaterialsBudgetAmount = laborAuxiliaryMaterialsBudgetAmount;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLaborPrice() {
		return laborPrice;
	}

	public void setLaborPrice(String laborPrice) {
		this.laborPrice = laborPrice;
	}

	public String getAccessoriesPrice() {
		return accessoriesPrice;
	}

	public void setAccessoriesPrice(String accessoriesPrice) {
		this.accessoriesPrice = accessoriesPrice;
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
}
