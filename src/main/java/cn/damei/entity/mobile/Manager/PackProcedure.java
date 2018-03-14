package cn.damei.entity.mobile.Manager;

import cn.damei.common.persistence.DataEntity;

/**
 * @author 梅浩 meihao@zzhyun.cn:
 * @version 创建时间：2016年9月20日 下午5:30:31 类说明
 */

public class PackProcedure extends DataEntity<PackProcedure> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer proceDureId;// 工序id
	private String procedureName;// 工序名字
	private Double synthesizePrice;// 综合价
	private Integer budgetNumber;// 预估数量
	private String measurementUnit;// 计量单位

	public Integer getBudgetNumber() {
		return budgetNumber;
	}

	public void setBudgetNumber(Integer budgetNumber) {
		this.budgetNumber = budgetNumber;
	}

	private Double laborAuxiliaryMaterialsBudgetAmount; // 总价 预估数量 *综合价
	private String reamrks;// 备注 工序内容
	private Integer packageId;// 关联的任务包id
	private Double laborPrice = 0.0;// 人工结算价
	private Double accessoriesPrice = 0.0;// 辅料结算价
	private Double auxiliaryMaterialsBudgetAmount = 0.0;//辅料费预算金额
	private Double laborBudgetAmount = 0.0;//人工费预算金额

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

	public Double getLaborAuxiliaryMaterialsBudgetAmount() {
		return laborAuxiliaryMaterialsBudgetAmount;
	}

	public void setLaborAuxiliaryMaterialsBudgetAmount(Double laborAuxiliaryMaterialsBudgetAmount) {
		this.laborAuxiliaryMaterialsBudgetAmount = laborAuxiliaryMaterialsBudgetAmount;
	}

	public String getReamrks() {
		return reamrks;
	}

	public void setReamrks(String reamrks) {
		this.reamrks = reamrks;
	}

	public String getMeasurementUnit() {
		return measurementUnit;
	}

	public void setMeasurementUnit(String measurementUnit) {
		this.measurementUnit = measurementUnit;
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

	public Double getAuxiliaryMaterialsBudgetAmount() {
		return auxiliaryMaterialsBudgetAmount;
	}

	public void setAuxiliaryMaterialsBudgetAmount(Double auxiliaryMaterialsBudgetAmount) {
		this.auxiliaryMaterialsBudgetAmount = auxiliaryMaterialsBudgetAmount;
	}

	public Double getLaborBudgetAmount() {
		return laborBudgetAmount;
	}

	public void setLaborBudgetAmount(Double laborBudgetAmount) {
		this.laborBudgetAmount = laborBudgetAmount;
	}
}
