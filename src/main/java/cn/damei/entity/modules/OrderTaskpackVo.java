package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity;

public class OrderTaskpackVo extends DataEntity<OrderTaskpackVo>{
	private static final long serialVersionUID = -2499276448176454118L;
	
	private String id;


	private String packageName;
	

	private String procedureNo;
	

	private String procedureName;
	

	private String budgetNumber;
	

	private String label;
	

	private String synthesizePrice;
	

	private String laborAuxiliaryMaterialsBudgetAmount;
	

	private String remarks;
	

	private String laborPrice;
	

	private String accessoriesPrice;
	
	private String laborBudgetAmount;
	private String auxiliaryMaterialsBudgetAmount;

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
