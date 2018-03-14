package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;

public class BizEmployeeBankcard2 extends DataEntity2<BizEmployeeBankcard2>{


	private static final long serialVersionUID = 1L;

	private Integer storeId;
	private Integer empId;
	private String idCardNo;
	private String bankId;
	private String branchBank;
	private String bankCardNo;
	private String empRealName;
	private String bankName;
	private String bankNo;
	private String provinceName;
	

	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	public String getIdCardNo() {
		return idCardNo;
	}
	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	public String getBranchBank() {
		return branchBank;
	}
	public void setBranchBank(String branchBank) {
		this.branchBank = branchBank;
	}
	public String getBankCardNo() {
		return bankCardNo;
	}
	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}
	public String getEmpRealName() {
		return empRealName;
	}
	public void setEmpRealName(String empRealName) {
		this.empRealName = empRealName;
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
