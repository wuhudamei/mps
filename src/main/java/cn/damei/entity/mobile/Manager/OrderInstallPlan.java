package cn.damei.entity.mobile.Manager;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

@SuppressWarnings("serial")
public class OrderInstallPlan extends DataEntity2<OrderInstallPlan> {

	private Integer id;
	private Integer orderId;
	private Integer orderInstallItemId;
	private String installItemName;
	private String shigongId;
	private Integer installItemSequence;
	private Date planIntoDate;
	private Date applyIntoDate;
	private Date realIntoDate;
	private Date realCompleteDate;
	private Date realAcceptDate;
	private String status;
	private String statusName;
	private String applyIntoRemarks;
	private String isCompleteDelay;
	private String completeDelayReason;
	private String completeDelayReasonName;
	private String completeDelayRemarks;
	private String remarks;
	private String createByAuthor;
	private Date createDate;
	private String updateByAuthor;
	private Date updateDate;
	private String delFlag;
	private Date applyIntoCreateDatetime;
	private String installMode;
	private String installModeName;
	private Date planCompleteDate;
	private String status1;
	private Integer delayDays;
	private Date supplierIntoDate;

	private String installRequire;

	private String isQualified;

	private Date unqualifiedAcceptTime;
	private String unqualifiedReason;
	private Integer unqualifiedReasonId;
	private String unqualifiedRemarks;
	private Double firstPassRate;
	private Integer unqualifiedTimes;


	private String secondPhaseMoneyType;
	private String communityName;
	private String buildNumber;
	private String buildUnit;
	private String buildRoom;
	private String customerName;

	private Date rejectStatusDatetime;
	private String rejectBusinessRemarks;
	private String rejectRemarks;
	private String rejectRemarksName;

	private String isShowInstallDescription;
	private String installDescription;

	
	private String workApplyDayString;
	private String orderConfirmStartWorkDateString;
	private Date allowApplyChecksizeDate;

	public String getCompleteDelayReasonName() {
		return completeDelayReasonName;
	}

	public void setCompleteDelayReasonName(String completeDelayReasonName) {
		this.completeDelayReasonName = completeDelayReasonName;
	}

	public Date getUnqualifiedAcceptTime() {
		return unqualifiedAcceptTime;
	}

	public void setUnqualifiedAcceptTime(Date unqualifiedAcceptTime) {
		this.unqualifiedAcceptTime = unqualifiedAcceptTime;
	}

	public String getUnqualifiedReason() {
		return unqualifiedReason;
	}

	public void setUnqualifiedReason(String unqualifiedReason) {
		this.unqualifiedReason = unqualifiedReason;
	}

	public String getUnqualifiedRemarks() {
		return unqualifiedRemarks;
	}

	public void setUnqualifiedRemarks(String unqualifiedRemarks) {
		this.unqualifiedRemarks = unqualifiedRemarks;
	}

	public Double getFirstPassRate() {
		return firstPassRate;
	}

	public void setFirstPassRate(Double firstPassRate) {
		this.firstPassRate = firstPassRate;
	}

	public Integer getUnqualifiedTimes() {
		return unqualifiedTimes;
	}

	public void setUnqualifiedTimes(Integer unqualifiedTimes) {
		this.unqualifiedTimes = unqualifiedTimes;
	}

	public String getInstallModeName() {
		return installModeName;
	}

	public void setInstallModeName(String installModeName) {
		this.installModeName = installModeName;
	}

	public Date getPlanCompleteDate() {
		return planCompleteDate;
	}

	public void setPlanCompleteDate(Date planCompleteDate) {
		this.planCompleteDate = planCompleteDate;
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

	public String getInstallMode() {
		return installMode;
	}

	public void setInstallMode(String installMode) {
		this.installMode = installMode;
	}

	public String getStatus1() {
		return status1;
	}

	public void setStatus1(String status1) {
		this.status1 = status1;
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

	public String getIsQualified() {
		return isQualified;
	}

	public void setIsQualified(String isQualified) {
		this.isQualified = isQualified;
	}



	public String getSecondPhaseMoneyType() {
		return secondPhaseMoneyType;
	}

	public void setSecondPhaseMoneyType(String secondPhaseMoneyType) {
		this.secondPhaseMoneyType = secondPhaseMoneyType;
	}



	public Date getSupplierIntoDate() {
		return supplierIntoDate;
	}

	public void setSupplierIntoDate(Date supplierIntoDate) {
		this.supplierIntoDate = supplierIntoDate;
	}

	public Integer getDelayDays() {
		return delayDays;
	}

	public void setDelayDays(Integer delayDays) {
		this.delayDays = delayDays;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCreateByAuthor() {
		return createByAuthor;
	}

	public void setCreateByAuthor(String createByAuthor) {
		this.createByAuthor = createByAuthor;
	}

	public String getUpdateByAuthor() {
		return updateByAuthor;
	}

	public void setUpdateByAuthor(String updateByAuthor) {
		this.updateByAuthor = updateByAuthor;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getOrderInstallItemId() {
		return orderInstallItemId;
	}

	public void setOrderInstallItemId(Integer orderInstallItemId) {
		this.orderInstallItemId = orderInstallItemId;
	}

	public String getInstallItemName() {
		return installItemName;
	}

	public void setInstallItemName(String installItemName) {
		this.installItemName = installItemName;
	}

	public Integer getInstallItemSequence() {
		return installItemSequence;
	}

	public void setInstallItemSequence(Integer installItemSequence) {
		this.installItemSequence = installItemSequence;
	}

	public Date getApplyIntoDate() {
		return applyIntoDate;
	}

	public void setApplyIntoDate(Date applyIntoDate) {
		this.applyIntoDate = applyIntoDate;
	}

	public Date getPlanIntoDate() {
		return planIntoDate;
	}

	public void setPlanIntoDate(Date planIntoDate) {
		this.planIntoDate = planIntoDate;
	}

	public Date getRealIntoDate() {
		return realIntoDate;
	}

	public void setRealIntoDate(Date realIntoDate) {
		this.realIntoDate = realIntoDate;
	}

	public Date getRealCompleteDate() {
		return realCompleteDate;
	}

	public void setRealCompleteDate(Date realCompleteDate) {
		this.realCompleteDate = realCompleteDate;
	}

	public Date getRealAcceptDate() {
		return realAcceptDate;
	}

	public void setRealAcceptDate(Date realAcceptDate) {
		this.realAcceptDate = realAcceptDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getApplyIntoRemarks() {
		return applyIntoRemarks;
	}

	public void setApplyIntoRemarks(String applyIntoRemarks) {
		this.applyIntoRemarks = applyIntoRemarks;
	}

	public String getIsCompleteDelay() {
		return isCompleteDelay;
	}

	public void setIsCompleteDelay(String isCompleteDelay) {
		this.isCompleteDelay = isCompleteDelay;
	}

	public String getCompleteDelayReason() {
		return completeDelayReason;
	}

	public void setCompleteDelayReason(String completeDelayReason) {
		this.completeDelayReason = completeDelayReason;
	}

	public String getCompleteDelayRemarks() {
		return completeDelayRemarks;
	}

	public void setCompleteDelayRemarks(String completeDelayRemarks) {
		this.completeDelayRemarks = completeDelayRemarks;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public Date getApplyIntoCreateDatetime() {
		return applyIntoCreateDatetime;
	}

	public void setApplyIntoCreateDatetime(Date applyIntoCreateDatetime) {
		this.applyIntoCreateDatetime = applyIntoCreateDatetime;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public Date getRejectStatusDatetime() {
		return rejectStatusDatetime;
	}

	public void setRejectStatusDatetime(Date rejectStatusDatetime) {
		this.rejectStatusDatetime = rejectStatusDatetime;
	}

	public String getRejectBusinessRemarks() {
		return rejectBusinessRemarks;
	}

	public void setRejectBusinessRemarks(String rejectBusinessRemarks) {
		this.rejectBusinessRemarks = rejectBusinessRemarks;
	}

	public String getRejectRemarks() {
		return rejectRemarks;
	}

	public void setRejectRemarks(String rejectRemarks) {
		this.rejectRemarks = rejectRemarks;
	}

	public String getRejectRemarksName() {
		return rejectRemarksName;
	}

	public void setRejectRemarksName(String rejectRemarksName) {
		this.rejectRemarksName = rejectRemarksName;
	}

	public String getIsShowInstallDescription() {
		return isShowInstallDescription;
	}

	public void setIsShowInstallDescription(String isShowInstallDescription) {
		this.isShowInstallDescription = isShowInstallDescription;
	}

	public String getInstallDescription() {
		return installDescription;
	}

	public String getShigongId() {
		return shigongId;
	}

	public void setShigongId(String shigongId) {
		this.shigongId = shigongId;
	}

	public void setInstallDescription(String installDescription) {
		this.installDescription = installDescription;
	}

	public String getInstallRequire() {
		return installRequire;
	}

	public void setInstallRequire(String installRequire) {
		this.installRequire = installRequire;
	}

	public String getWorkApplyDayString() {
		return workApplyDayString;
	}

	public void setWorkApplyDayString(String workApplyDayString) {
		this.workApplyDayString = workApplyDayString;
	}

	public String getOrderConfirmStartWorkDateString() {
		return orderConfirmStartWorkDateString;
	}

	public void setOrderConfirmStartWorkDateString(String orderConfirmStartWorkDateString) {
		this.orderConfirmStartWorkDateString = orderConfirmStartWorkDateString;
	}

	public Date getAllowApplyChecksizeDate() {
		return allowApplyChecksizeDate;
	}

	public void setAllowApplyChecksizeDate(Date allowApplyChecksizeDate) {
		this.allowApplyChecksizeDate = allowApplyChecksizeDate;
	}

	public Integer getUnqualifiedReasonId() {
		return unqualifiedReasonId;
	}

	public void setUnqualifiedReasonId(Integer unqualifiedReasonId) {
		this.unqualifiedReasonId = unqualifiedReasonId;
	}
}
