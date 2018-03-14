package cn.damei.entity.mobile.Manager;

import cn.damei.common.persistence.DataEntity2;

/**
 * 
 * 该订单的辅料对应的任务包是否已验收
 */

public class AuxiliaryPackageState extends DataEntity2<AuxiliaryPackageState> {

	private static final long serialVersionUID = 1L;
	
	private Integer orderId; // 订单id
	private Integer storeId; // 门店id
	private String projectMode; // 工程模式
	private String empWorkType; // 工种
	private String empWorkTypeName; // 工种名称
	private Integer taskPackageTemplatId; // 模板id
	private Integer orderTaskpackageId; // 订单任务包id
	private String templatName; //任务包模板名称
	private String packageStateId;//任务包状态
	private String isCanApplyAuxiliary;//是否可以申请辅料的标识
	
	private Double laborAuxiliaryMaterialsBudgetAmount;//任务包预算金额
	private Integer applyBudgetRatio;//申请辅料预算比例
	private Double totalAmount;//任务包申请的辅料金额总和（任务包预算金额*申请辅料预算比例）
	private Double beginAmount;//任务包已经申请的辅料金额
	private Double afterAmount;//任务包可以申请的辅料金额
	
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
