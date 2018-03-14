package cn.damei.entity.mobile.home;

import java.io.Serializable;
import java.util.Date;

public class ProjectProgressVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private String customerPhone;
	private Integer orderId;
	private String customerName;
	private Integer projectNodeId;
	private String projectNodeName;
	private String communityName;
	private String buildNumber;
	private String buildRoom;
	private String buildUnit;
	private String managerName;
	private Integer managerId;
	private Date contractStartDate;
	private Date actualStartDate;
	private String managerPhone;
	private String delayType;
	private String  delayDescription;
	private String delayDays;
	private Integer viewLogCount;
	private String selfNeedSign;
	private String isNeedSign;
	private String logType;
	private Integer logRelatedId;
	private String logRemark;
	public String getLogType() {
		return logType;
	}
	public void setLogType(String logType) {
		this.logType = logType;
	}
	public Integer getLogRelatedId() {
		return logRelatedId;
	}
	public void setLogRelatedId(Integer logRelatedId) {
		this.logRelatedId = logRelatedId;
	}
	public String getLogRemark() {
		return logRemark;
	}
	public void setLogRemark(String logRemark) {
		this.logRemark = logRemark;
	}
	public String getSelfNeedSign() {
		return selfNeedSign;
	}
	public void setSelfNeedSign(String selfNeedSign) {
		this.selfNeedSign = selfNeedSign;
	}
	public String getIsNeedSign() {
		return isNeedSign;
	}
	public void setIsNeedSign(String isNeedSign) {
		this.isNeedSign = isNeedSign;
	}
	public Integer getViewLogCount() {
		return viewLogCount;
	}
	public void setViewLogCount(Integer viewLogCount) {
		this.viewLogCount = viewLogCount;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Integer getProjectNodeId() {
		return projectNodeId;
	}
	public void setProjectNodeId(Integer projectNodeId) {
		this.projectNodeId = projectNodeId;
	}
	public String getProjectNodeName() {
		return projectNodeName;
	}
	public void setProjectNodeName(String projectNodeName) {
		this.projectNodeName = projectNodeName;
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
	public String getBuildRoom() {
		return buildRoom;
	}
	public void setBuildRoom(String buildRoom) {
		this.buildRoom = buildRoom;
	}
	public String getBuildUnit() {
		return buildUnit;
	}
	public void setBuildUnit(String buildUnit) {
		this.buildUnit = buildUnit;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public Integer getManagerId() {
		return managerId;
	}
	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}
	public Date getContractStartDate() {
		return contractStartDate;
	}
	public void setContractStartDate(Date contractStartDate) {
		this.contractStartDate = contractStartDate;
	}
	public Date getActualStartDate() {
		return actualStartDate;
	}
	public void setActualStartDate(Date actualStartDate) {
		this.actualStartDate = actualStartDate;
	}
	public String getManagerPhone() {
		return managerPhone;
	}
	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}
	public String getDelayType() {
		return delayType;
	}
	public void setDelayType(String delayType) {
		this.delayType = delayType;
	}
	public String getDelayDescription() {
		return delayDescription;
	}
	public void setDelayDescription(String delayDescription) {
		this.delayDescription = delayDescription;
	}
	public String getDelayDays() {
		return delayDays;
	}
	public void setDelayDays(String delayDays) {
		this.delayDays = delayDays;
	}
	
}
