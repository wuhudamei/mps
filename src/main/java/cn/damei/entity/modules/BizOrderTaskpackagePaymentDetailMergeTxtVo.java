/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;

/**
 * 付款单明细合并Entity
 * @author qww
 * @version 2016-10-27
 */
public class BizOrderTaskpackagePaymentDetailMergeTxtVo extends DataEntity2<BizOrderTaskpackagePaymentDetailMergeTxtVo> {
	
	private static final long serialVersionUID = 1L;
	private Double amount; // 金额
	private String bankCardNo; // 收款人银行卡号
	private String realName; // 收款人姓名
	private String bankName; // 开户行名称
	private String bankNo; // 收款方开户行号
	private String provinceName; // 所属省份
	
	public BizOrderTaskpackagePaymentDetailMergeTxtVo() {
		super();
	}

	public BizOrderTaskpackagePaymentDetailMergeTxtVo(Integer id){
		super(id);
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getBankCardNo() {
		return bankCardNo;
	}

	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
}