package cn.damei.entity.mobile.Manager;

import cn.damei.common.persistence.DataEntity2;

/**
 * 任务包结算Entity
 * @author qww
 * @version 2016-10-15
 */

public class BizOrderTaskpackageProcedure extends DataEntity2<BizOrderTaskpackageProcedure>{

	private static final long serialVersionUID = 1L;
	
	private Integer id; // 工序表id
	private Integer taskpackageId; // 任务包id
	private String packageName; // 任务包名称
	private String procedureNo; // 工序编号
	private String procedureName; // 工序名称
	private String measurementUnit; // 计量单位
	private String measurementUnitLabel; // 计量单位
	private Double laborPrice; // 人工价
	private Double accessoriesPrice; // 辅料价格
	private Double synthesizePrice; // 综合价
	private Double budgetNumber; // 预算数量
	private Double realNumber; // 真实数量
	private Double budgetTotal;//预估总价（预估数量*综合价）
	private Double recheckNumber; //复合工程量
	private Double settlementNumber; //结算工程量
	private String recheckRemarks;//复核说明
	private String modifyNumberReason;//后台更改工程量原因
	private Double laborDudgetAmount; //人工预算金额
	private Double laborSettleAmount;//人工费结算金额
	private Double auxiliaryMaterialsBudgetAmount;//辅料费预算金额
	private Double auxiliaryMaterialsSettleAmount;//辅料费结算金额
	private Double laborAuxiliaryMaterialsBudgetAmount;//工料费预算金额
	private Double laborAuxiliaryMaterialsSettleAmount;//工料费结算金额
	

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
