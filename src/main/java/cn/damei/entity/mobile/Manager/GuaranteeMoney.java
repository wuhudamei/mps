package cn.damei.entity.mobile.Manager;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;



public class GuaranteeMoney extends DataEntity2<GuaranteeMoney>{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer orderTaskpackageId;
	private Integer settlementId;
	private Integer taskpackageTemplatId;
	private Integer employeeId;
	private Integer employeegroupId;
	private Double guaranteeMoneyAmount;
	private Double guaranteeMoneyAmountTotal;
	private String isDeduct;
	private Date deductTime;
	private String remarks;
	private Double guaranteeMoneyBalance;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getOrderTaskpackageId() {
		return orderTaskpackageId;
	}
	public void setOrderTaskpackageId(Integer orderTaskpackageId) {
		this.orderTaskpackageId = orderTaskpackageId;
	}
	public Integer getSettlementId() {
		return settlementId;
	}
	public void setSettlementId(Integer settlementId) {
		this.settlementId = settlementId;
	}
	public Integer getTaskpackageTemplatId() {
		return taskpackageTemplatId;
	}
	public void setTaskpackageTemplatId(Integer taskpackageTemplatId) {
		this.taskpackageTemplatId = taskpackageTemplatId;
	}
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public Integer getEmployeegroupId() {
		return employeegroupId;
	}
	public void setEmployeegroupId(Integer employeegroupId) {
		this.employeegroupId = employeegroupId;
	}
	public Double getGuaranteeMoneyAmount() {
		return guaranteeMoneyAmount;
	}
	public void setGuaranteeMoneyAmount(Double guaranteeMoneyAmount) {
		this.guaranteeMoneyAmount = guaranteeMoneyAmount;
	}
	public Double getGuaranteeMoneyAmountTotal() {
		return guaranteeMoneyAmountTotal;
	}
	public void setGuaranteeMoneyAmountTotal(Double guaranteeMoneyAmountTotal) {
		this.guaranteeMoneyAmountTotal = guaranteeMoneyAmountTotal;
	}
	public String getIsDeduct() {
		return isDeduct;
	}
	public void setIsDeduct(String isDeduct) {
		this.isDeduct = isDeduct;
	}
	public Date getDeductTime() {
		return deductTime;
	}
	public void setDeductTime(Date deductTime) {
		this.deductTime = deductTime;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Double getGuaranteeMoneyBalance() {
		return guaranteeMoneyBalance;
	}
	public void setGuaranteeMoneyBalance(Double guaranteeMoneyBalance) {
		this.guaranteeMoneyBalance = guaranteeMoneyBalance;
	}
	
	
}
