/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity;

/**
 * 订单任务包工序Entity
 * @author llp
 * @version 2016-09-23
 */
public class OrderTaskpackageProcedure extends DataEntity<OrderTaskpackageProcedure> {
	
	private static final long serialVersionUID = 1L;
	private Long taskpackageId;		// 所属订单任务包Id
	private String packageName;		// 任务包名称
	private String procedureNo;		// 工序编号
	private String procedureName;		// 工序名称
	private String measurementUnit;		// 计量单位
	private Double laborPrice;		// 人工价
	private Double accessoriesPrice;		// 辅料价格
	private Double synthesizePrice;		// 综合价
	private Double budgetNumber;		// 预算数量
	private BigDecimal realNumber;//真实数量
//	private Double total;		// 总价（预估数量*综合价）
	
	private String orderId;
	private String projectMode;
	private String taskpackageNumber;
	
	private Double laborBudgetAmount;// 人工费预算金额
	private Double auxiliaryMaterialsBudgetAmount; // 辅料费预算金额
	private Double laborAuxiliaryMaterialsBudgetAmount;// 工料费预算总金额
	
	public OrderTaskpackageProcedure() {
		super();
	}

	public OrderTaskpackageProcedure(String id){
		super(id);
	}

	
	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}

	@NotNull(message="所属订单任务包Id不能为空")
	
	
	@Length(min=1, max=100, message="任务包名称长度必须介于 1 和 100 之间")
	public String getPackageName() {
		return packageName;
	}

	
	public Long getTaskpackageId() {
		return taskpackageId;
	}

	public void setTaskpackageId(Long taskpackageId) {
		this.taskpackageId = taskpackageId;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	@Length(min=1, max=100, message="工序编号长度必须介于 1 和 100 之间")
	public String getProcedureNo() {
		return procedureNo;
	}

	public void setProcedureNo(String procedureNo) {
		this.procedureNo = procedureNo;
	}
	
	@Length(min=1, max=100, message="工序名称长度必须介于 1 和 100 之间")
	public String getProcedureName() {
		return procedureName;
	}

	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}
	
	@Length(min=1, max=25, message="计量单位长度必须介于 1 和 25 之间")
	public String getMeasurementUnit() {
		return measurementUnit;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getTaskpackageNumber() {
		return taskpackageNumber;
	}

	public void setTaskpackageNumber(String taskpackageNumber) {
		this.taskpackageNumber = taskpackageNumber;
	}

	public BigDecimal getRealNumber() {
		return realNumber;
	}

	public void setRealNumber(BigDecimal realNumber) {
		this.realNumber = realNumber;
	}

	public void setMeasurementUnit(String measurementUnit) {
		this.measurementUnit = measurementUnit;
	}
	
	@NotNull(message="人工价不能为空")
	public Double getLaborPrice() {
		return laborPrice;
	}

	public void setLaborPrice(Double laborPrice) {
		this.laborPrice = laborPrice;
	}
	
	@NotNull(message="辅料价格不能为空")
	public Double getAccessoriesPrice() {
		return accessoriesPrice;
	}

	public void setAccessoriesPrice(Double accessoriesPrice) {
		this.accessoriesPrice = accessoriesPrice;
	}
	
	@NotNull(message="综合价不能为空")
	public Double getSynthesizePrice() {
		return synthesizePrice;
	}

	public void setSynthesizePrice(Double synthesizePrice) {
		this.synthesizePrice = synthesizePrice;
	}
	
	@NotNull(message="预算数量不能为空")
	public Double getBudgetNumber() {
		return budgetNumber;
	}

	public void setBudgetNumber(Double budgetNumber) {
		this.budgetNumber = budgetNumber;
	}
	
	@NotNull(message="总价（预估数量*综合价）不能为空")
//	public Double getTotal() {
//		return total;
//	}
//
//	public void setTotal(Double total) {
//		this.total = total;
//	}

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

	public Double getLaborAuxiliaryMaterialsBudgetAmount() {
		return laborAuxiliaryMaterialsBudgetAmount;
	}

	public void setLaborAuxiliaryMaterialsBudgetAmount(Double laborAuxiliaryMaterialsBudgetAmount) {
		this.laborAuxiliaryMaterialsBudgetAmount = laborAuxiliaryMaterialsBudgetAmount;
	}
}