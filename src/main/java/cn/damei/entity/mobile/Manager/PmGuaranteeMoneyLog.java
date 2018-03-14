package cn.damei.entity.mobile.Manager;

import cn.damei.common.persistence.DataEntity2;

import java.util.Date;

/**
 * 任务包结算Entity
 * @author qww
 * @version 2016-10-15
 */

public class PmGuaranteeMoneyLog extends DataEntity2<PmGuaranteeMoneyLog>{

	private static final long serialVersionUID = 1L;

	private Integer pmEmployeeId; // 项目经理员工id
	private String pmEmployeeName;//项目经理名称
	private Integer orderId; // 订单id
	private Double takeoffAmount; // 质保金结算上缴金额
	private Date takeoffDatetime; // 质保金结算上缴日期时间
	private Double takeoffAmountTotal; // 质保金累积扣除金额

	private String communityName; // 小区名称
	private String buildNumber; // 楼号
	private String buildUnit; // 单元号
	private String buildRoom; // 门牌号
	private String customerName; // 客户姓名

	
	public String getPmEmployeeName() {
		return pmEmployeeName;
	}

	public void setPmEmployeeName(String pmEmployeeName) {
		this.pmEmployeeName = pmEmployeeName;
	}

	public PmGuaranteeMoneyLog() {
		super();
	}

	public PmGuaranteeMoneyLog(Integer id){
		super(id);
	}

	public Integer getPmEmployeeId() {
		return pmEmployeeId;
	}

	public void setPmEmployeeId(Integer pmEmployeeId) {
		this.pmEmployeeId = pmEmployeeId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Double getTakeoffAmount() {
		return takeoffAmount;
	}

	public void setTakeoffAmount(Double takeoffAmount) {
		this.takeoffAmount = takeoffAmount;
	}

	public Date getTakeoffDatetime() {
		return takeoffDatetime;
	}

	public void setTakeoffDatetime(Date takeoffDatetime) {
		this.takeoffDatetime = takeoffDatetime;
	}

	public Double getTakeoffAmountTotal() {
		return takeoffAmountTotal;
	}

	public void setTakeoffAmountTotal(Double takeoffAmountTotal) {
		this.takeoffAmountTotal = takeoffAmountTotal;
	}

	public String getCommunityName() {
		return communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}

	public String getBuildNumber() {
		return buildNumber;
	}

	public void setBuildNumber(String buildNumber) {
		this.buildNumber = buildNumber;
	}

	public String getBuildUnit() {
		return buildUnit;
	}

	public void setBuildUnit(String buildUnit) {
		this.buildUnit = buildUnit;
	}

	public String getBuildRoom() {
		return buildRoom;
	}

	public void setBuildRoom(String buildRoom) {
		this.buildRoom = buildRoom;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
}
