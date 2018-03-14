package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;

public class BizOrderTaskpackagePaymentDetailSplitVo extends DataEntity2<BizOrderTaskpackagePaymentDetailSplitVo>{

	/**
	 * @author wang
	 */
	private static final long serialVersionUID = 1L;
	private Integer orderTaskpackagePaymentDetailId;		// 付款单明细id -- '
	private Integer salaryEmployeeId;		// 收款员工id -- '
	private Integer employeeBankcardId;		// 员工银行卡id -- '
	private Integer employeeBankcardRelatedIdcardId;		// 员工银行卡关联身份证id -- '
	private String salaryEmployeeName;		// 收款员工姓名 -- '
	private String salaryEmployeeIdcardNo;		// 收款员工身份证号 -- '
	private String salaryEmployeeBankcard;		// 收款员工银行卡号 -- '
	private double payAmountTotal;		// 付款总金额 -- '
	private String relatedName;		// 关联人姓名 -- '
	private String relatedIdcardNo;		// 关联人身份证号 -- '
	private double payAmountSplit;		// 打款金额 -- '
	private String paymentCode; //付款单编号
	private double restMoney; //本月剩余可打款余额
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
