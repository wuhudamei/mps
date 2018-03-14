package cn.damei.entity.mobile.Manager;

import cn.damei.common.persistence.DataEntity;

public class DelaySheet extends DataEntity<DelaySheet>{
	private String customerName                      ;
	private String communityName                     ;
	private String buildNumber                       ;
	private String buildUnit                         ;
	private String buildRoom                         ;
	private String delayBillStageStatus            ;//延期阶段
	private String delayBillCategoryId             ;
	private String delayBillCategoryIdReson             ;//延期原因
	private String delayBeginDatetime               ;//延期开始时间
	private String delayEndDatetime                 ;//延期结束时间
	private String delayDays                         ;//延期天数
	private String deferredInstruction               ;//延期说明
	private String deferredApplicationDatetime      ;//延期申请时间
	private String status                             ;//状态
	private String statusDatetime                    ;//状态日期时间
	private String delayBillStageStatusName;
	private String orderStatus; //订单状态
	private Integer orderId;//订单ID
	
	private String remarks;//拒绝原因
	private String isScrap;//是否废弃

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
