
package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;

import java.util.Date;


public class BizEmployeeGuaranteeMoney extends DataEntity2<BizEmployeeGuaranteeMoney> {

	private static final long serialVersionUID = 1L;

	private String customerName;
	private String customerAddress;
	private String itemCustomer;
	private Date deductTime;
	private Double guaranteeMoneyAmount;

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