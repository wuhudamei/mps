package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

/**
 * 付款单冻结/解冻实体类
 * 
 * @author hyh
 *
 */
public class BizOrderTaskpackagePaymentFreeze extends DataEntity2<BizOrderTaskpackagePaymentFreeze> {

	private static final long serialVersionUID = 1L;
	
	private Date operatoTime;
	
	private String operator;

	private Integer bizOrderTaskpackagePaymentId;// 付款单Id

	private String orderTaskpackagePaymentType; // 付款单类型

	private Double amount; // 金额

	private String frozenType;// 冻结解冻类型 1冻结 2解冻

	private String frozenRemarks;// 冻结解冻说明

	private String bizOrderTaskpackagePaymentCode;// 付款单号
	
	private String bizOrderTaskpackageName;// 任务包名称

	private String customerName; // 客户姓名
	
	private String groupName;// 工人组长

	private String customerMessage; //客户地址
	

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
