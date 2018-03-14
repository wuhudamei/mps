
package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;


public class BizPmAttendDayOrder extends DataEntity2<BizPmAttendDayOrder> {
	
	private static final long serialVersionUID = 1L;
	
	private Integer orderId;
	private Integer pmEmployeeId;
	private String managerName;
	private Date signDatetime;
	private double signErrorDistance;
	private String isValid;
	private String signStep;
	private String orderNumber;
	private String orderProjectMode;

	private Integer storeId;
	private String storeName;
	private Integer engineDepartId;
	private Date signDate1;
	private Date signDate2;
	private Double conditionDistance1;
	private Double conditionDistance2;
	private String customerAddress;
	private String communityName;
	private String buildNumber;
	private String buildUnit;
	private String buildRoom;
	private String customerName;
	private String signId;

	public String getSignId() {
		return signId;
	}

	public void setSignId(String signId) {
		this.signId = signId;
	}

	public String getBuildNumber() {
		return buildNumber;
	}
	public void setBuildNumber(String buildNumber) {
		this.buildNumber = buildNumber;
	}
	public Integer getEngineDepartId() {
		return engineDepartId;
	}
	public void setEngineDepartId(Integer engineDepartId) {
		this.engineDepartId = engineDepartId;
	}
	
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public String getCommunityName() {
		return communityName;
	}
	public void setCommunityName(String communityName) {
		this.communityName = communityName;
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
	
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getOrderProjectMode() {
		return orderProjectMode;
	}
	public void setOrderProjectMode(String orderProjectMode) {
		this.orderProjectMode = orderProjectMode;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public Date getSignDate1() {
		return signDate1;
	}
	public void setSignDate1(Date signDate1) {
		this.signDate1 = signDate1;
	}
	public Date getSignDate2() {
		return signDate2;
	}
	public void setSignDate2(Date signDate2) {
		this.signDate2 = signDate2;
	}
	public Double getConditionDistance1() {
		return conditionDistance1;
	}
	public void setConditionDistance1(Double conditionDistance1) {
		this.conditionDistance1 = conditionDistance1;
	}
	public Double getConditionDistance2() {
		return conditionDistance2;
	}
	public void setConditionDistance2(Double conditionDistance2) {
		this.conditionDistance2 = conditionDistance2;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getPmEmployeeId() {
		return pmEmployeeId;
	}
	public void setPmEmployeeId(Integer pmEmployeeId) {
		this.pmEmployeeId = pmEmployeeId;
	}
	public Date getSignDatetime() {
		return signDatetime;
	}
	public void setSignDatetime(Date signDatetime) {
		this.signDatetime = signDatetime;
	}
	public double getSignErrorDistance() {
		return signErrorDistance;
	}
	public void setSignErrorDistance(double signErrorDistance) {
		this.signErrorDistance = signErrorDistance;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getSignStep() {
		return signStep;
	}
	public void setSignStep(String signStep) {
		this.signStep = signStep;
	}
	
}