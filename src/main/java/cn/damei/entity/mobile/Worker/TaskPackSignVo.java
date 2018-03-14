package cn.damei.entity.mobile.Worker;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;



public class TaskPackSignVo extends DataEntity2<TaskPackSignVo>{


	private static final long serialVersionUID = 1L;

	

	private Integer orderId;
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	private String workerLeaderName;
	private Integer workerLeaderId;
	private Integer itemManagerId;
	private String itemManagerName;
	private String itemManagerPhone;
	private String customerName;
	private String customerMessage;
	private Date planStartDate;
	private Date planEndDate;
	private String  packStateName;
	private String packStateId;
	private String signFlag;
	private String packageName;
	private Integer count;
	private String lat;
	private String lon;
	private String settleStyle;
	private String isScrap;
	
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getSignFlag() {
		return signFlag;
	}
	public void setSignFlag(String signFlag) {
		this.signFlag = signFlag;
	}
	public String getWorkerLeaderName() {
		return workerLeaderName;
	}
	public void setWorkerLeaderName(String workerLeaderName) {
		this.workerLeaderName = workerLeaderName;
	}
	public Integer getWorkerLeaderId() {
		return workerLeaderId;
	}
	public void setWorkerLeaderId(Integer workerLeaderId) {
		this.workerLeaderId = workerLeaderId;
	}
	public Integer getItemManagerId() {
		return itemManagerId;
	}
	public void setItemManagerId(Integer itemManagerId) {
		this.itemManagerId = itemManagerId;
	}
	public String getItemManagerName() {
		return itemManagerName;
	}
	public void setItemManagerName(String itemManagerName) {
		this.itemManagerName = itemManagerName;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerMessage() {
		return customerMessage;
	}
	public void setCustomerMessage(String customerMessage) {
		this.customerMessage = customerMessage;
	}
	public Date getPlanStartDate() {
		return planStartDate;
	}
	public void setPlanStartDate(Date planStartDate) {
		this.planStartDate = planStartDate;
	}
	public Date getPlanEndDate() {
		return planEndDate;
	}
	public void setPlanEndDate(Date planEndDate) {
		this.planEndDate = planEndDate;
	}
	public String getPackStateName() {
		return packStateName;
	}
	public void setPackStateName(String packStateName) {
		this.packStateName = packStateName;
	}
	public String getPackStateId() {
		return packStateId;
	}
	public void setPackStateId(String packStateId) {
		this.packStateId = packStateId;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getItemManagerPhone() {
		return itemManagerPhone;
	}
	public void setItemManagerPhone(String itemManagerPhone) {
		this.itemManagerPhone = itemManagerPhone;
	}
	public String getSettleStyle() {
		return settleStyle;
	}
	public void setSettleStyle(String settleStyle) {
		this.settleStyle = settleStyle;
	}
	public String getIsScrap() {
		return isScrap;
	}
	public void setIsScrap(String isScrap) {
		this.isScrap = isScrap;
	}
	
}
