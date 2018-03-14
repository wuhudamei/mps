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
public class BizOrderTaskpackagePaymentDetailMergeVo extends DataEntity2<BizOrderTaskpackagePaymentDetailMergeVo> {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id; // 付款单明细合并id
	private String realName; // 工人姓名
	private Double amount; // 金额
	private String status; // 状态
	private String idCardNo; // 身份证号
	private String bankCardNo; //身份证号

	private String phone; // 手机号
	private Integer employeeId; // 工人id
	
	public BizOrderTaskpackagePaymentDetailMergeVo() {
		super();
	}

	public BizOrderTaskpackagePaymentDetailMergeVo(Integer id){
		super(id);
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public String getBankCardNo() {
		return bankCardNo;
	}

	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}
}