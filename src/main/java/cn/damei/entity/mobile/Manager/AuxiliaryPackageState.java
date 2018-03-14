package cn.damei.entity.mobile.Manager;

import cn.damei.common.persistence.DataEntity2;



public class AuxiliaryPackageState extends DataEntity2<AuxiliaryPackageState> {

	private static final long serialVersionUID = 1L;
	
	private Integer orderId;
	private Integer storeId;
	private String projectMode;
	private String empWorkType;
	private String empWorkTypeName;
	private Integer taskPackageTemplatId;
	private Integer orderTaskpackageId;
	private String templatName;
	private String packageStateId;
	private String isCanApplyAuxiliary;
	
	private Double laborAuxiliaryMaterialsBudgetAmount;
	private Integer applyBudgetRatio;
	private Double totalAmount;
	private Double beginAmount;
	private Double afterAmount;
	
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public String getProjectMode() {
		return projectMode;
	}
	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}
	public String getEmpWorkType() {
		return empWorkType;
	}
	public void setEmpWorkType(String empWorkType) {
		this.empWorkType = empWorkType;
	}
	public String getEmpWorkTypeName() {
		return empWorkTypeName;
	}
	public void setEmpWorkTypeName(String empWorkTypeName) {
		this.empWorkTypeName = empWorkTypeName;
	}
	public Integer getTaskPackageTemplatId() {
		return taskPackageTemplatId;
	}
	public void setTaskPackageTemplatId(Integer taskPackageTemplatId) {
		this.taskPackageTemplatId = taskPackageTemplatId;
	}
	public Integer getOrderTaskpackageId() {
		return orderTaskpackageId;
	}
	public void setOrderTaskpackageId(Integer orderTaskpackageId) {
		this.orderTaskpackageId = orderTaskpackageId;
	}
	public String getPackageStateId() {
		return packageStateId;
	}
	public void setPackageStateId(String packageStateId) {
		this.packageStateId = packageStateId;
	}
	public String getIsCanApplyAuxiliary() {
		return isCanApplyAuxiliary;
	}
	public void setIsCanApplyAuxiliary(String isCanApplyAuxiliary) {
		this.isCanApplyAuxiliary = isCanApplyAuxiliary;
	}
	public Double getLaborAuxiliaryMaterialsBudgetAmount() {
		return laborAuxiliaryMaterialsBudgetAmount;
	}
	public void setLaborAuxiliaryMaterialsBudgetAmount(Double laborAuxiliaryMaterialsBudgetAmount) {
		this.laborAuxiliaryMaterialsBudgetAmount = laborAuxiliaryMaterialsBudgetAmount;
	}
	public Integer getApplyBudgetRatio() {
		return applyBudgetRatio;
	}
	public void setApplyBudgetRatio(Integer applyBudgetRatio) {
		this.applyBudgetRatio = applyBudgetRatio;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Double getBeginAmount() {
		return beginAmount;
	}
	public void setBeginAmount(Double beginAmount) {
		this.beginAmount = beginAmount;
	}
	public Double getAfterAmount() {
		return afterAmount;
	}
	public void setAfterAmount(Double afterAmount) {
		this.afterAmount = afterAmount;
	}

	public String getTemplatName() {
		return templatName;
	}

	public void setTemplatName(String templatName) {
		this.templatName = templatName;
	}
}
