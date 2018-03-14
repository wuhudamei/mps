package cn.damei.entity.mobile.Manager;

import cn.damei.common.persistence.DataEntity;

public class DelaySheet extends DataEntity<DelaySheet>{
	private String customerName                      ;
	private String communityName                     ;
	private String buildNumber                       ;
	private String buildUnit                         ;
	private String buildRoom                         ;
	private String delayBillStageStatus            ;
	private String delayBillCategoryId             ;
	private String delayBillCategoryIdReson             ;
	private String delayBeginDatetime               ;
	private String delayEndDatetime                 ;
	private String delayDays                         ;
	private String deferredInstruction               ;
	private String deferredApplicationDatetime      ;
	private String status                             ;
	private String statusDatetime                    ;
	private String delayBillStageStatusName;
	private String orderStatus;
	private Integer orderId;
	
	private String remarks;
	private String isScrap;

	public String getDelayBillStageStatusName() {
		return delayBillStageStatusName;
	}

	public void setDelayBillStageStatusName(String delayBillStageStatusName) {
		this.delayBillStageStatusName = delayBillStageStatusName;
	}

	public String getIsScrap() {
		return isScrap;
	}
	public void setIsScrap(String isScrap) {
		this.isScrap = isScrap;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getDelayBillCategoryIdReson() {
		return delayBillCategoryIdReson;
	}
	public void setDelayBillCategoryIdReson(String delayBillCategoryIdReson) {
		this.delayBillCategoryIdReson = delayBillCategoryIdReson;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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
	public String getDelayBillStageStatus() {
		return delayBillStageStatus;
	}
	public void setDelayBillStageStatus(String delayBillStageStatus) {
		this.delayBillStageStatus = delayBillStageStatus;
	}
	public String getDelayBillCategoryId() {
		return delayBillCategoryId;
	}
	public void setDelayBillCategoryId(String delayBillCategoryId) {
		this.delayBillCategoryId = delayBillCategoryId;
	}
	
	public String getDelayBeginDatetime() {
		return delayBeginDatetime;
	}
 
	public void setDelayBeginDatetime(String delayBeginDatetime) {
		this.delayBeginDatetime = delayBeginDatetime;
	}
	
	public String getDelayEndDatetime() {
		return delayEndDatetime;
	}
	
	public void setDelayEndDatetime(String delayEndDatetime) {
		this.delayEndDatetime = delayEndDatetime;
	}
	public String getDelayDays() {
		return delayDays;
	}
	public void setDelayDays(String delayDays) {
		this.delayDays = delayDays;
	}
	public String getDeferredInstruction() {
		return deferredInstruction;
	}
	public void setDeferredInstruction(String deferredInstruction) {
		this.deferredInstruction = deferredInstruction;
	}
	public String getDeferredApplicationDatetime() {
		return deferredApplicationDatetime;
	}
	public void setDeferredApplicationDatetime(String deferredApplicationDatetime) {
		this.deferredApplicationDatetime = deferredApplicationDatetime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatusDatetime() {
		return statusDatetime;
	}
	public void setStatusDatetime(String statusDatetime) {
		this.statusDatetime = statusDatetime;
	}

	
}
