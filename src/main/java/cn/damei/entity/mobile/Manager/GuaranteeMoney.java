package cn.damei.entity.mobile.Manager;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

/**
 * 任务包结算Entity
 * @author qww
 * @version 2016-10-15
 */

public class GuaranteeMoney extends DataEntity2<GuaranteeMoney>{

	private static final long serialVersionUID = 1L;
	
	private Integer id; // 质保金id
	private Integer orderTaskpackageId; // 订单任务包id
	private Integer settlementId; // 结算单id
	private Integer taskpackageTemplatId; // 任务包模板id
	private Integer employeeId; // 员工id
	private Integer employeegroupId; // 工人组id
	private Double guaranteeMoneyAmount; // 质保金额
	private Double guaranteeMoneyAmountTotal; // 质保金累计金额
	private String isDeduct; // 是否上缴
	private Date deductTime; // 上缴时间
	private String remarks; // 备注
	private Double guaranteeMoneyBalance;//质保金余额
	
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
