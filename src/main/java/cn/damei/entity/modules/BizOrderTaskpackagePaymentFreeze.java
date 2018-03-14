package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;


public class BizOrderTaskpackagePaymentFreeze extends DataEntity2<BizOrderTaskpackagePaymentFreeze> {

	private static final long serialVersionUID = 1L;
	
	private Date operatoTime;
	
	private String operator;

	private Integer bizOrderTaskpackagePaymentId;

	private String orderTaskpackagePaymentType;

	private Double amount;

	private String frozenType;

	private String frozenRemarks;

	private String bizOrderTaskpackagePaymentCode;
	
	private String bizOrderTaskpackageName;

	private String customerName;
	
	private String groupName;

	private String customerMessage;
	

	public Integer getBizOrderTaskpackagePaymentId() {
		return bizOrderTaskpackagePaymentId;
	}

	public void setBizOrderTaskpackagePaymentId(Integer bizOrderTaskpackagePaymentId) {
		this.bizOrderTaskpackagePaymentId = bizOrderTaskpackagePaymentId;
	}

	public String getFrozenType() {
		return frozenType;
	}

	public void setFrozenType(String frozenType) {
		this.frozenType = frozenType;
	}

	public String getFrozenRemarks() {
		return frozenRemarks;
	}

	public void setFrozenRemarks(String frozenRemarks) {
		this.frozenRemarks = frozenRemarks;
	}

	public String getOrderTaskpackagePaymentType() {
		return orderTaskpackagePaymentType;
	}

	public void setOrderTaskpackagePaymentType(String orderTaskpackagePaymentType) {
		this.orderTaskpackagePaymentType = orderTaskpackagePaymentType;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getBizOrderTaskpackagePaymentCode() {
		return bizOrderTaskpackagePaymentCode;
	}

	public void setBizOrderTaskpackagePaymentCode(String bizOrderTaskpackagePaymentCode) {
		this.bizOrderTaskpackagePaymentCode = bizOrderTaskpackagePaymentCode;
	}

	public String getBizOrderTaskpackageName() {
		return bizOrderTaskpackageName;
	}

	public void setBizOrderTaskpackageName(String bizOrderTaskpackageName) {
		this.bizOrderTaskpackageName = bizOrderTaskpackageName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getCustomerMessage() {
		return customerMessage;
	}

	public void setCustomerMessage(String customerMessage) {
		this.customerMessage = customerMessage;
	}

	public Date getOperatoTime() {
		return operatoTime;
	}

	public void setOperatoTime(Date operatoTime) {
		this.operatoTime = operatoTime;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

}
