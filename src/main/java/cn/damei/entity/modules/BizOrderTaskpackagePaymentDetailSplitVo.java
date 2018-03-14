package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;

public class BizOrderTaskpackagePaymentDetailSplitVo extends DataEntity2<BizOrderTaskpackagePaymentDetailSplitVo>{


	private static final long serialVersionUID = 1L;
	private Integer orderTaskpackagePaymentDetailId;
	private Integer salaryEmployeeId;
	private Integer employeeBankcardId;
	private Integer employeeBankcardRelatedIdcardId;
	private String salaryEmployeeName;
	private String salaryEmployeeIdcardNo;
	private String salaryEmployeeBankcard;
	private double payAmountTotal;
	private String relatedName;
	private String relatedIdcardNo;
	private double payAmountSplit;
	private String paymentCode;
	private double restMoney;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getOrderTaskpackagePaymentDetailId() {
		return orderTaskpackagePaymentDetailId;
	}
	public void setOrderTaskpackagePaymentDetailId(Integer orderTaskpackagePaymentDetailId) {
		this.orderTaskpackagePaymentDetailId = orderTaskpackagePaymentDetailId;
	}
	public Integer getSalaryEmployeeId() {
		return salaryEmployeeId;
	}
	public void setSalaryEmployeeId(Integer salaryEmployeeId) {
		this.salaryEmployeeId = salaryEmployeeId;
	}
	public Integer getEmployeeBankcardId() {
		return employeeBankcardId;
	}
	public void setEmployeeBankcardId(Integer employeeBankcardId) {
		this.employeeBankcardId = employeeBankcardId;
	}
	public Integer getEmployeeBankcardRelatedIdcardId() {
		return employeeBankcardRelatedIdcardId;
	}
	public void setEmployeeBankcardRelatedIdcardId(Integer employeeBankcardRelatedIdcardId) {
		this.employeeBankcardRelatedIdcardId = employeeBankcardRelatedIdcardId;
	}
	public String getSalaryEmployeeName() {
		return salaryEmployeeName;
	}
	public void setSalaryEmployeeName(String salaryEmployeeName) {
		this.salaryEmployeeName = salaryEmployeeName;
	}
	public String getSalaryEmployeeIdcardNo() {
		return salaryEmployeeIdcardNo;
	}
	public void setSalaryEmployeeIdcardNo(String salaryEmployeeIdcardNo) {
		this.salaryEmployeeIdcardNo = salaryEmployeeIdcardNo;
	}
	public String getSalaryEmployeeBankcard() {
		return salaryEmployeeBankcard;
	}
	public void setSalaryEmployeeBankcard(String salaryEmployeeBankcard) {
		this.salaryEmployeeBankcard = salaryEmployeeBankcard;
	}
	
	public String getRelatedName() {
		return relatedName;
	}
	public void setRelatedName(String relatedName) {
		this.relatedName = relatedName;
	}
	public String getRelatedIdcardNo() {
		return relatedIdcardNo;
	}
	public void setRelatedIdcardNo(String relatedIdcardNo) {
		this.relatedIdcardNo = relatedIdcardNo;
	}

	public String getPaymentCode() {
		return paymentCode;
	}
	public void setPaymentCode(String paymentCode) {
		this.paymentCode = paymentCode;
	}
	public double getRestMoney() {
		return restMoney;
	}
	public void setRestMoney(double restMoney) {
		this.restMoney = restMoney;
	}
	public double getPayAmountTotal() {
		return payAmountTotal;
	}
	public void setPayAmountTotal(double payAmountTotal) {
		this.payAmountTotal = payAmountTotal;
	}
	public double getPayAmountSplit() {
		return payAmountSplit;
	}
	public void setPayAmountSplit(double payAmountSplit) {
		this.payAmountSplit = payAmountSplit;
	}
	
}
