/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;

import java.util.Date;

/**
 * 工人组长质保金明细Entity
 * 
 * @author qww
 * @version 2017-01-06
 */
public class BizEmployeeGuaranteeMoney extends DataEntity2<BizEmployeeGuaranteeMoney> {

	private static final long serialVersionUID = 1L;

	private String customerName; // 客户姓名
	private String customerAddress; // 客户地址
	private String itemCustomer; // 项目经理
	private Date deductTime; // 上缴质保金日期
	private Double guaranteeMoneyAmount; // 质保金金额

	public BizEmployeeGuaranteeMoney() {
		super();
	}

	public BizEmployeeGuaranteeMoney(Integer id) {
		super(id);
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getItemCustomer() {
		return itemCustomer;
	}

	public void setItemCustomer(String itemCustomer) {
		this.itemCustomer = itemCustomer;
	}

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
}