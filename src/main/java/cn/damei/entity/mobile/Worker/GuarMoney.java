package cn.damei.entity.mobile.Worker;

import cn.damei.common.persistence.DataEntity2;

import java.util.Date;

public class GuarMoney extends DataEntity2<GuarMoney>{

	/**
	 * @author qww
	 * 2016-12-7
	 */
	private static final long serialVersionUID = 1L;

	private Date deductTime; // 质保金产生时间
	private Double guaranteeMoneyAmount; // 质保金额
	private String itemCustomer; // 项目经理
	private String customerMessage; // 客户信息
	private String customerName; // 客户姓名

	public Date getDeductTime() {
		return deductTime;
	}

	public void setDeductTime(Date deductTime) {
		this.deductTime = deductTime;
	}

	public Double getGuaranteeMoneyAmount() {
		return guaranteeMoneyAmount;
	}

	public void setGuaranteeMoneyAmount(Double guaranteeMoneyAmount) {
		this.guaranteeMoneyAmount = guaranteeMoneyAmount;
	}

	public String getItemCustomer() {
		return itemCustomer;
	}

	public void setItemCustomer(String itemCustomer) {
		this.itemCustomer = itemCustomer;
	}

	public String getCustomerMessage() {
		return customerMessage;
	}

	public void setCustomerMessage(String customerMessage) {
		this.customerMessage = customerMessage;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
}
