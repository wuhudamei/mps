package cn.damei.entity.mobile.Manager;

import cn.damei.common.persistence.DataEntity2;

/**
 * 任务包结算Entity
 * @author qww
 * @version 2016-10-15
 */

public class OrderTaskpackageVo extends DataEntity2<OrderTaskpackageVo>{

	private static final long serialVersionUID = 1L;

	private Integer id; // 订单任务包id
	private Integer orderId; // 订单id
	private Integer groupId; // 组长id
	private String customerMessage; //客户基本信息
	private String customerName; //客户名称
	private String packageName; // 任务包名称
	private String realName; // 工人组长名称
	private String phone; // 工人组长电话
	private String houseType; // 户型 1:一居室 2:二居室 3:三居室 4:四居室 5:五居室 6:其它居室
	private String coveredArea; // 建筑面积
	private String empGroupid; // 工人组id
	
	private Integer taskPackageTemplatId; // 任务包模板id
	private String guaranteeMoneyAmount; // 质保金额
	private String guaranteeMoneyAmountTotal; // 质保金累计金额
	private String isQualityGuarantee; // 是否扣质保金；0：否，1：是
	private String qualityGuaranteeRate; // 质保金扣除比例;1-100
	private Integer gualityGuaranteeType; // 扣除质保金分类(自定义字段) 1-不扣质保金 2-扣质保金，但是没满两次 3-扣质保金，但是已满2次
	private String packageStateId; // 任务包状态Id
	private Double guaranteeMoneyBalance;//质保金余额
	private String settleStyle;//结算方式  1：包工包料  2：包工
	
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
