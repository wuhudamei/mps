package cn.damei.entity.mobile.Manager;

import cn.damei.common.persistence.DataEntity2;



public class OrderTaskpackageVo extends DataEntity2<OrderTaskpackageVo>{

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer orderId;
	private Integer groupId;
	private String customerMessage;
	private String customerName;
	private String packageName;
	private String realName;
	private String phone;
	private String houseType;
	private String coveredArea;
	private String empGroupid;
	
	private Integer taskPackageTemplatId;
	private String guaranteeMoneyAmount;
	private String guaranteeMoneyAmountTotal;
	private String isQualityGuarantee;
	private String qualityGuaranteeRate;
	private Integer gualityGuaranteeType;
	private String packageStateId;
	private Double guaranteeMoneyBalance;
	private String settleStyle;
	
	public String getSettleStyle() {
		return settleStyle;
	}
	public void setSettleStyle(String settleStyle) {
		this.settleStyle = settleStyle;
	}
	public Double getGuaranteeMoneyBalance() {
		return guaranteeMoneyBalance;
	}
	public void setGuaranteeMoneyBalance(Double guaranteeMoneyBalance) {
		this.guaranteeMoneyBalance = guaranteeMoneyBalance;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public String getCustomerMessage() {
		return customerMessage;
	}
	public void setCustomerMessage(String customerMessage) {
		this.customerMessage = customerMessage;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getHouseType() {
		return houseType;
	}
	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}
	public String getCoveredArea() {
		return coveredArea;
	}
	public void setCoveredArea(String coveredArea) {
		this.coveredArea = coveredArea;
	}
	public String getEmpGroupid() {
		return empGroupid;
	}
	public void setEmpGroupid(String empGroupid) {
		this.empGroupid = empGroupid;
	}
	public Integer getTaskPackageTemplatId() {
		return taskPackageTemplatId;
	}
	public void setTaskPackageTemplatId(Integer taskPackageTemplatId) {
		this.taskPackageTemplatId = taskPackageTemplatId;
	}
	public String getGuaranteeMoneyAmount() {
		return guaranteeMoneyAmount;
	}
	public void setGuaranteeMoneyAmount(String guaranteeMoneyAmount) {
		this.guaranteeMoneyAmount = guaranteeMoneyAmount;
	}
	public String getGuaranteeMoneyAmountTotal() {
		return guaranteeMoneyAmountTotal;
	}
	public void setGuaranteeMoneyAmountTotal(String guaranteeMoneyAmountTotal) {
		this.guaranteeMoneyAmountTotal = guaranteeMoneyAmountTotal;
	}
	public String getIsQualityGuarantee() {
		return isQualityGuarantee;
	}
	public void setIsQualityGuarantee(String isQualityGuarantee) {
		this.isQualityGuarantee = isQualityGuarantee;
	}
	public String getQualityGuaranteeRate() {
		return qualityGuaranteeRate;
	}
	public void setQualityGuaranteeRate(String qualityGuaranteeRate) {
		this.qualityGuaranteeRate = qualityGuaranteeRate;
	}
	public Integer getGualityGuaranteeType() {
		return gualityGuaranteeType;
	}
	public void setGualityGuaranteeType(Integer gualityGuaranteeType) {
		this.gualityGuaranteeType = gualityGuaranteeType;
	}
	public String getPackageStateId() {
		return packageStateId;
	}
	public void setPackageStateId(String packageStateId) {
		this.packageStateId = packageStateId;
	}
}
