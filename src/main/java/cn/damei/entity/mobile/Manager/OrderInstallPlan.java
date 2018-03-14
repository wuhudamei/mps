package cn.damei.entity.mobile.Manager;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

@SuppressWarnings("serial")
public class OrderInstallPlan extends DataEntity2<OrderInstallPlan> {

	private Integer id;
	private Integer orderId; //订单id
	private Integer orderInstallItemId;//订单安装项id
	private String installItemName; //安装项名称
	private String shigongId; //點單項ID
	private Integer installItemSequence;//安装项顺序
	private Date planIntoDate;//计划进场日期
	private Date applyIntoDate;//申请进场日期
	private Date realIntoDate;//实际进场日期
	private Date realCompleteDate;//实际完工日期
	private Date realAcceptDate;//实际验收日期
	private String status;//状态 1.已生成计划；2.已申请计划；3.已验收 4:已转给供应商 5：已驳回 6：驳回后申请
	private String statusName;//状态名称 1.已生成计划；2.已申请计划；3.已验收
	private String applyIntoRemarks;//申请进场备注
	private String isCompleteDelay;//是否完工延期 0.否；1.是
	private String completeDelayReason;//完工延期原因 1.发生变更；2.材料未送到；3.工人不够；4.物业不让施工；5.其他
	private String completeDelayReasonName; //完工延期原因 名称
	private String completeDelayRemarks;//完工延期描述
	private String remarks;//备注
	private String createByAuthor;//创建人
	private Date createDate;//创建日期时间
	private String updateByAuthor;//更新人
	private Date updateDate; //更新日期时间
	private String delFlag; //是否删除
	private Date applyIntoCreateDatetime; //申请进场创建期时间
	private String installMode; //安装模式
	private String installModeName;//安装模式 名称
	private Date planCompleteDate;//计划完成日期
	private String status1; //安装项状态
	private Integer delayDays;// 延期天数
	private Date supplierIntoDate;// 供应商确认时间

	private String installRequire;// 安装要求

	private String isQualified;// 是否合格

	private Date unqualifiedAcceptTime;// 不合格验收时间
	private String unqualifiedReason;// 不合格原因
	private Integer unqualifiedReasonId;// 不合格原因id
	private String unqualifiedRemarks;// 不合格备注
	private Double firstPassRate;// 一次合格率
	private Integer unqualifiedTimes;// 不合格次数

	// 订单交款类型 1二期 2尾款
	private String secondPhaseMoneyType;
	private String communityName;// 小区名称
	private String buildNumber;// 几号楼
	private String buildUnit;// 几单元
	private String buildRoom;// 哪一室
	private String customerName;// 客户姓名

	private Date rejectStatusDatetime;// 驳回时间
	private String rejectBusinessRemarks;// 驳回原因
	private String rejectRemarks;// 驳回类型
	private String rejectRemarksName;// 驳回类型 名称

	private String isShowInstallDescription;// 是否展示安装说明
	private String installDescription;// 安装说明描述

	
	private String workApplyDayString; //开工第几天申请 字符串类型
	private String orderConfirmStartWorkDateString;//订单开工日期 字符串类型
	private Date allowApplyChecksizeDate; //可申请复尺日期

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
