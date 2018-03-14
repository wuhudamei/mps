package cn.damei.entity.mobile.Manager;

import cn.damei.common.persistence.DataEntity2;



public class BizOrderTaskpackageProcedure extends DataEntity2<BizOrderTaskpackageProcedure>{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer taskpackageId;
	private String packageName;
	private String procedureNo;
	private String procedureName;
	private String measurementUnit;
	private String measurementUnitLabel;
	private Double laborPrice;
	private Double accessoriesPrice;
	private Double synthesizePrice;
	private Double budgetNumber;
	private Double realNumber;
	private Double budgetTotal;
	private Double recheckNumber;
	private Double settlementNumber;
	private String recheckRemarks;
	private String modifyNumberReason;
	private Double laborDudgetAmount;
	private Double laborSettleAmount;
	private Double auxiliaryMaterialsBudgetAmount;
	private Double auxiliaryMaterialsSettleAmount;
	private Double laborAuxiliaryMaterialsBudgetAmount;
	private Double laborAuxiliaryMaterialsSettleAmount;
	

	public Double getLaborDudgetAmount() {
		return laborDudgetAmount;
	}
	public void setLaborDudgetAmount(Double laborDudgetAmount) {
		this.laborDudgetAmount = laborDudgetAmount;
	}
	public Double getLaborSettleAmount() {
		return laborSettleAmount;
	}
	public void setLaborSettleAmount(Double laborSettleAmount) {
		this.laborSettleAmount = laborSettleAmount;
	}
	public Double getAuxiliaryMaterialsBudgetAmount() {
		return auxiliaryMaterialsBudgetAmount;
	}
	public void setAuxiliaryMaterialsBudgetAmount(Double auxiliaryMaterialsBudgetAmount) {
		this.auxiliaryMaterialsBudgetAmount = auxiliaryMaterialsBudgetAmount;
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
	public Double getLaborAuxiliaryMaterialsSettleAmount() {
		return laborAuxiliaryMaterialsSettleAmount;
	}
	public void setLaborAuxiliaryMaterialsSettleAmount(Double laborAuxiliaryMaterialsSettleAmount) {
		this.laborAuxiliaryMaterialsSettleAmount = laborAuxiliaryMaterialsSettleAmount;
	}
	public String getModifyNumberReason() {
		return modifyNumberReason;
	}
	public void setModifyNumberReason(String modifyNumberReason) {
		this.modifyNumberReason = modifyNumberReason;
	}
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
	public Double getSynthesizePrice() {
		return synthesizePrice;
	}
	public void setSynthesizePrice(Double synthesizePrice) {
		this.synthesizePrice = synthesizePrice;
	}
	public Double getBudgetNumber() {
		return budgetNumber;
	}
	public void setBudgetNumber(Double budgetNumber) {
		this.budgetNumber = budgetNumber;
	}
	public Double getRealNumber() {
		return realNumber;
	}
	public void setRealNumber(Double realNumber) {
		this.realNumber = realNumber;
	}
	public Double getRecheckNumber() {
		return recheckNumber;
	}
	public void setRecheckNumber(Double recheckNumber) {
		this.recheckNumber = recheckNumber;
	}
	public Double getSettlementNumber() {
		return settlementNumber;
	}
	public void setSettlementNumber(Double settlementNumber) {
		this.settlementNumber = settlementNumber;
	}
	public String getRecheckRemarks() {
		return recheckRemarks;
	}
	public void setRecheckRemarks(String recheckRemarks) {
		this.recheckRemarks = recheckRemarks;
	}
	public Double getBudgetTotal() {
		return budgetTotal;
	}
	public void setBudgetTotal(Double budgetTotal) {
		this.budgetTotal = budgetTotal;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
