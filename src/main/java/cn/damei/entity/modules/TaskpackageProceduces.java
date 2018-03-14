package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;

public class TaskpackageProceduces extends DataEntity2<TaskpackageProceduces>{

	private static final long serialVersionUID = -7177343136276999282L;
	
	private Integer id;
	private Integer packageId;
	private String packageName;
	private String procedureNo;
	private String procedureName;
	private Double budgetNumber;
	private Double laborAuxiliaryMaterialsBudgetAmount;
	private String measurementUnit;
	private Double synthesizePrice;
	private Double laborPrice;
	private Double accessoriesPrice;
	private Double laborBudgetAmount;
	private Double auxiliaryMaterialsBudgetAmount;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPackageId() {
		return packageId;
	}
	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
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
	public Double getBudgetNumber() {
		return budgetNumber;
	}
	public void setBudgetNumber(Double budgetNumber) {
		this.budgetNumber = budgetNumber;
	}
	public Double getLaborAuxiliaryMaterialsBudgetAmount() {
		return laborAuxiliaryMaterialsBudgetAmount;
	}
	public void setLaborAuxiliaryMaterialsBudgetAmount(Double laborAuxiliaryMaterialsBudgetAmount) {
		this.laborAuxiliaryMaterialsBudgetAmount = laborAuxiliaryMaterialsBudgetAmount;
	}
	public String getMeasurementUnit() {
		return measurementUnit;
	}
	public void setMeasurementUnit(String measurementUnit) {
		this.measurementUnit = measurementUnit;
	}
	public Double getSynthesizePrice() {
		return synthesizePrice;
	}
	public void setSynthesizePrice(Double synthesizePrice) {
		this.synthesizePrice = synthesizePrice;
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
}
