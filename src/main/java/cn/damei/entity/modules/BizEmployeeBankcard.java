/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity;

/**
 * 工人银行卡Entity
 * @author haven
 * @version 2016-09-03
 */
public class BizEmployeeBankcard extends DataEntity<BizEmployeeBankcard> {
	
	private static final long serialVersionUID = 1L;
	private String storeId;		// 门店
	private String empId;		// 员工
	private String idCardNo;		// 身份证号
	private String bankId;		// 开户行
	private String branchBank;		// 支行地址
	private String bankCardNo;		// 银行卡号
	private String empRealName;//员工姓名
	private String bankName; //开户行名称
	private String bankNo; //行号
	private String provinceName; //省份
	public String getEmpRealName() {
		return empRealName;
	}

	public void setEmpRealName(String empRealName) {
		this.empRealName = empRealName;
	}

	public BizEmployeeBankcard() {
		super();
	}

	public BizEmployeeBankcard(String id){
		super(id);
	}

	@Length(min=0, max=255, message="门店长度必须介于 0 和 255 之间")
	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	
	@Length(min=0, max=255, message="员工长度必须介于 0 和 255 之间")
	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}
	
	@Length(min=0, max=18, message="身份证号长度必须介于 0 和 18 之间")
	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}
	
	@Length(min=0, max=255, message="开户行长度必须介于 0 和 255 之间")
	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	
	@Length(min=0, max=255, message="支行地址长度必须介于 0 和 255 之间")
	public String getBranchBank() {
		return branchBank;
	}

	public void setBranchBank(String branchBank) {
		this.branchBank = branchBank;
	}
	
	@Length(min=0, max=255, message="银行卡号长度必须介于 0 和 255 之间")
	public String getBankCardNo() {
		return bankCardNo;
	}

	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
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