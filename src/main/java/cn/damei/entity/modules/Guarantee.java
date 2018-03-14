package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

public class Guarantee extends DataEntity2<Guarantee>{


	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer orderTaskpackageId;
	private String groupRealname;
	private Integer settlementId;
	private Integer taskpackageTemplatId;
	private Integer employeeId;
	private Integer employeegroupId;
	private double guaranteeMoneyAmount;
	private double guaranteeMoneyAmountTotal;
	private String isDeduct;
	private Date deductTime;
	private String remarks;
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
	public String getGroupRealname() {
		return groupRealname;
	}
	public void setGroupRealname(String groupRealname) {
		this.groupRealname = groupRealname;
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
	public double getGuaranteeMoneyAmount() {
		return guaranteeMoneyAmount;
	}
	public void setGuaranteeMoneyAmount(double guaranteeMoneyAmount) {
		this.guaranteeMoneyAmount = guaranteeMoneyAmount;
	}
	public double getGuaranteeMoneyAmountTotal() {
		return guaranteeMoneyAmountTotal;
	}
	public void setGuaranteeMoneyAmountTotal(double guaranteeMoneyAmountTotal) {
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
	
	
}
