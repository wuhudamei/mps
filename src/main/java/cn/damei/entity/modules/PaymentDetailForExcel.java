package cn.damei.entity.modules;

import java.util.Date;

public class PaymentDetailForExcel {
	
	private String settlementCode;//结算单编号
	private String summaryCode;//批次编号
	private String customerName;
	private String customerPhone;
	private String customerAddress;
	private String contractArea;
	private String itemManager;
	private String packageName;
	private Date settlementApplyDate;
	private Date settlementExamineDate;
	private Integer orderTaskpackageId;
	private String workerName;
	private String idCard;
	private String advanceRate;
	private String restRate;
	private Double advancePayment;
	private Double restPayment;
	private Double settlementAmount;
	private Double workerGroupSettleAmount;
	private String packageType;
	private String orderNumber;
	
	
	public Double getWorkerGroupSettleAmount() {
		return workerGroupSettleAmount;
	}
	public void setWorkerGroupSettleAmount(Double workerGroupSettleAmount) {
		this.workerGroupSettleAmount = workerGroupSettleAmount;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getPackageType() {
		return packageType;
	}
	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}
	public Integer getOrderTaskpackageId() {
		return orderTaskpackageId;
	}
	public void setOrderTaskpackageId(Integer orderTaskpackageId) {
		this.orderTaskpackageId = orderTaskpackageId;
	}
	public String getSettlementCode() {
		return settlementCode;
	}
	public void setSettlementCode(String settlementCode) {
		this.settlementCode = settlementCode;
	}
	public String getSummaryCode() {
		return summaryCode;
	}
	public void setSummaryCode(String summaryCode) {
		this.summaryCode = summaryCode;
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
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public Date getSettlementApplyDate() {
		return settlementApplyDate;
	}
	public void setSettlementApplyDate(Date settlementApplyDate) {
		this.settlementApplyDate = settlementApplyDate;
	}
	public Date getSettlementExamineDate() {
		return settlementExamineDate;
	}
	public void setSettlementExamineDate(Date settlementExamineDate) {
		this.settlementExamineDate = settlementExamineDate;
	}
	public String getWorkerName() {
		return workerName;
	}
	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
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
	
}
