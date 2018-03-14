package cn.damei.entity.mobile.Manager;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

public class NodePlan extends DataEntity2<NodePlan>{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer orderId; // 订单编号
	private String nodeName;
	private Integer nodeIndex;
	private Date planDoneDate;
	private Date exeDoneDate;
	private Date realDoneDate; 
	private String isDone;
	private Integer delayDays;
	private String delayType;
	private String delayReason;
	private String createByAuthor;
	private Date createDate;
	private Date updateDate;
	private String updateByAuthor;
	private Integer projectMode;
	private Integer constructionScheduleId;
	/*计划审核日期*/
	private Date planCheckTime;


	public Date getPlanCheckTime() {
		return planCheckTime;
	}

	public void setPlanCheckTime(Date planCheckTime) {
		this.planCheckTime = planCheckTime;
	}

	public Integer getProjectMode() {
		return projectMode;
	}
	public void setProjectMode(Integer projectMode) {
		this.projectMode = projectMode;
	}
	//
	private String communityName;
	private String buildNumber;
	private String buildUnit;
	private String buildRoom;
	private String customerName;
	
	
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
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public Integer getNodeIndex() {
		return nodeIndex;
	}
	public void setNodeIndex(Integer nodeIndex) {
		this.nodeIndex = nodeIndex;
	}
	public Date getPlanDoneDate() {
		return planDoneDate;
	}
	public void setPlanDoneDate(Date planDoneDate) {
		this.planDoneDate = planDoneDate;
	}
	public Date getExeDoneDate() {
		return exeDoneDate;
	}
	public void setExeDoneDate(Date exeDoneDate) {
		this.exeDoneDate = exeDoneDate;
	}
	public Date getRealDoneDate() {
		return realDoneDate;
	}
	public void setRealDoneDate(Date realDoneDate) {
		this.realDoneDate = realDoneDate;
	}
	public String getIsDone() {
		return isDone;
	}
	public void setIsDone(String isDone) {
		this.isDone = isDone;
	}
	public Integer getDelayDays() {
		return delayDays;
	}
	public void setDelayDays(Integer delayDays) {
		this.delayDays = delayDays;
	}
	public String getDelayType() {
		return delayType;
	}
	public void setDelayType(String delayType) {
		this.delayType = delayType;
	}
	public String getDelayReason() {
		return delayReason;
	}
	public void setDelayReason(String delayReason) {
		this.delayReason = delayReason;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
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
	public Integer getConstructionScheduleId() {
		return constructionScheduleId;
	}
	public void setConstructionScheduleId(Integer constructionScheduleId) {
		this.constructionScheduleId = constructionScheduleId;
	}
	
}
