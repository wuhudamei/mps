package cn.damei.entity.modules;

public class PaymentDetailSplitForExcel {
	private Integer id;
	private String customerName;
	private String customerPhone;
	private String customerAddress;
	private String contractArea;
	private String itemManager;
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
	private String workerName;
	private String advanceRate;
	private String restRate;
	private Double advancePayment;
	private Double restPayment;
	private Double settlementAmount;
	private String packageType;
	private String orderNumber;
	
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public String getContractArea() {
		return contractArea;
	}
	public void setContractArea(String contractArea) {
		this.contractArea = contractArea;
	}
	public String getItemManager() {
		return itemManager;
	}
	public void setItemManager(String itemManager) {
		this.itemManager = itemManager;
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
	public double getPayAmountTotal() {
		return payAmountTotal;
	}
	public void setPayAmountTotal(double payAmountTotal) {
		this.payAmountTotal = payAmountTotal;
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
	public double getPayAmountSplit() {
		return payAmountSplit;
	}
	public void setPayAmountSplit(double payAmountSplit) {
		this.payAmountSplit = payAmountSplit;
	}
	public String getPaymentCode() {
		return paymentCode;
	}
	public void setPaymentCode(String paymentCode) {
		this.paymentCode = paymentCode;
	}
	public String getWorkerName() {
		return workerName;
	}
	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}
	public String getAdvanceRate() {
		return advanceRate;
	}
	public void setAdvanceRate(String advanceRate) {
		this.advanceRate = advanceRate;
	}
	public String getRestRate() {
		return restRate;
	}
	public void setRestRate(String restRate) {
		this.restRate = restRate;
	}
	public Double getAdvancePayment() {
		return advancePayment;
	}
	public void setAdvancePayment(Double advancePayment) {
		this.advancePayment = advancePayment;
	}
	public Double getRestPayment() {
		return restPayment;
	}
	public void setRestPayment(Double restPayment) {
		this.restPayment = restPayment;
	}
	public Double getSettlementAmount() {
		return settlementAmount;
	}
	public void setSettlementAmount(Double settlementAmount) {
		this.settlementAmount = settlementAmount;
	}
	public String getPackageType() {
		return packageType;
	}
	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}
}
