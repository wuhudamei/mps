package cn.damei.entity.mobile.Worker;

import cn.damei.common.persistence.DataEntity;



public class WorkerPackProcedure extends DataEntity<WorkerPackProcedure> {


	private static final long serialVersionUID = 1L;
	private Integer proceDureId;
	private String procedureName;
	private Double synthesizePrice;
	private Integer budgetNumber;
	private String reamrks;
	private Integer packageId;
	private Double laborPrice;
	private Double accessoriesPrice;
	private Double laborBudgetAmount;
	private Double auxiliaryMaterialsBudgetAmount;
	private Double laborSettleAmount;
	private Double auxiliaryMaterialsSettleAmount;
	private Double laborAuxiliaryMaterialsBudgetAmount;
	private Double laborAuxiliaryMaterialsSettleSmount;
	
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	private String unit;
	public Integer getBudgetNumber() {
		return budgetNumber;
	}

	public void setBudgetNumber(Integer budgetNumber) {
		this.budgetNumber = budgetNumber;
	}

	public Integer getPackageId() {
		return packageId;
	}

	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}

	public Integer getProceDureId() {
		return proceDureId;
	}

	public void setProceDureId(Integer proceDureId) {
		this.proceDureId = proceDureId;
	}

	public String getProcedureName() {
		return procedureName;
	}

	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}

	public Double getSynthesizePrice() {
		return synthesizePrice;
	}

	public void setSynthesizePrice(Double synthesizePrice) {
		this.synthesizePrice = synthesizePrice;
	}

	public String getReamrks() {
		return reamrks;
	}

	public void setReamrks(String reamrks) {
		this.reamrks = reamrks;
	}

	public Double getLaborPrice() {
		return laborPrice;
	}

	public void setLaborPrice(Double laborPrice) {
		this.laborPrice = laborPrice;
	}

	public Double getAccessoriesPrice() {
		return accessoriesPrice;
	}

	public void setAccessoriesPrice(Double accessoriesPrice) {
		this.accessoriesPrice = accessoriesPrice;
	}

	public Double getLaborBudgetAmount() {
		return laborBudgetAmount;
	}

	public void setLaborBudgetAmount(Double laborBudgetAmount) {
		this.laborBudgetAmount = laborBudgetAmount;
	}

	public Double getAuxiliaryMaterialsBudgetAmount() {
		return auxiliaryMaterialsBudgetAmount;
	}

	public void setAuxiliaryMaterialsBudgetAmount(Double auxiliaryMaterialsBudgetAmount) {
		this.auxiliaryMaterialsBudgetAmount = auxiliaryMaterialsBudgetAmount;
	}

	public Double getLaborSettleAmount() {
		return laborSettleAmount;
	}

	public void setLaborSettleAmount(Double laborSettleAmount) {
		this.laborSettleAmount = laborSettleAmount;
	}

	public Double getAuxiliaryMaterialsSettleAmount() {
		return auxiliaryMaterialsSettleAmount;
	}

	public void setAuxiliaryMaterialsSettleAmount(Double auxiliaryMaterialsSettleAmount) {
		this.auxiliaryMaterialsSettleAmount = auxiliaryMaterialsSettleAmount;
	}

	public Double getLaborAuxiliaryMaterialsBudgetAmount() {
		return laborAuxiliaryMaterialsBudgetAmount;
	}

	public void setLaborAuxiliaryMaterialsBudgetAmount(Double laborAuxiliaryMaterialsBudgetAmount) {
		this.laborAuxiliaryMaterialsBudgetAmount = laborAuxiliaryMaterialsBudgetAmount;
	}

	public Double getLaborAuxiliaryMaterialsSettleSmount() {
		return laborAuxiliaryMaterialsSettleSmount;
	}

	public void setLaborAuxiliaryMaterialsSettleSmount(Double laborAuxiliaryMaterialsSettleSmount) {
		this.laborAuxiliaryMaterialsSettleSmount = laborAuxiliaryMaterialsSettleSmount;
	}
}
