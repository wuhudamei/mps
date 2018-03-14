
package cn.damei.entity.modules;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity;


public class OrderTaskpackageProcedure extends DataEntity<OrderTaskpackageProcedure> {
	
	private static final long serialVersionUID = 1L;
	private Long taskpackageId;
	private String packageName;
	private String procedureNo;
	private String procedureName;
	private String measurementUnit;
	private Double laborPrice;
	private Double accessoriesPrice;
	private Double synthesizePrice;
	private Double budgetNumber;
	private BigDecimal realNumber;

	
	private String orderId;
	private String projectMode;
	private String taskpackageNumber;
	
	private Double laborBudgetAmount;
	private Double auxiliaryMaterialsBudgetAmount;
	private Double laborAuxiliaryMaterialsBudgetAmount;
	
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