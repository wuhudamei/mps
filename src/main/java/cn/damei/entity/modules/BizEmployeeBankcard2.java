package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;

public class BizEmployeeBankcard2 extends DataEntity2<BizEmployeeBankcard2>{

	/**
	 * @author wang
	 */
	private static final long serialVersionUID = 1L;
	//private Integer id;
	private Integer storeId;		// 门店
	private Integer empId;		// 员工
	private String idCardNo;		// 身份证号
	private String bankId;		// 开户行
	private String branchBank;		// 支行地址
	private String bankCardNo;		// 银行卡号
	private String empRealName;//员工姓名
	private String bankName; //开户行名称
	private String bankNo; //行号
	private String provinceName; //省份
	
/*	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}*/
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
