
package cn.damei.entity.modules;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.damei.common.persistence.DataEntity;


public class InspectorConfirm extends DataEntity<InspectorConfirm> {
	
	private static final long serialVersionUID = 1L;
	
	private Integer qcBillId;
	private Integer orderId;
	private String storeId;

	private String communityName;
	private String buildNumber;
	private String buildUnit;
	private String buildRoom;
	private String customerName;
	
	private String itemManager;
	private Integer itemManagerId;
	
	private String checkEmployeeName;
	
	
	private String qcBillCode;
	private Integer qcCheckNodeId;
	private String qcCheckNodeName;
	private String status;
	private String isRecheck;
	private String qcBillType;
	
	
	private Date acceptCheckDatetime;
	private Date beginAcceptCheckDatetime;
	private Date endAcceptCheckDatetime;
	
	private String projectMode;
	
	private String reviewStatus;
	private String reviewRemark;
	private Date reviewDatetime;

	private Integer enginDepartId;
	private String departmentName;
	private List<Integer> enginDepartIds = new ArrayList<Integer>();
	
	
	
	public String getCheckEmployeeName() {
		return checkEmployeeName;
	}

	public void setCheckEmployeeName(String checkEmployeeName) {
		this.checkEmployeeName = checkEmployeeName;
	}

	public Integer getQcBillId() {
		return qcBillId;
	}

	public void setQcBillId(Integer qcBillId) {
		this.qcBillId = qcBillId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
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

	public String getItemManager() {
		return itemManager;
	}

	public void setItemManager(String itemManager) {
		this.itemManager = itemManager;
	}

	public Integer getItemManagerId() {
		return itemManagerId;
	}

	public void setItemManagerId(Integer itemManagerId) {
		this.itemManagerId = itemManagerId;
	}

	public String getQcBillCode() {
		return qcBillCode;
	}

	public void setQcBillCode(String qcBillCode) {
		this.qcBillCode = qcBillCode;
	}

	public Integer getQcCheckNodeId() {
		return qcCheckNodeId;
	}

	public void setQcCheckNodeId(Integer qcCheckNodeId) {
		this.qcCheckNodeId = qcCheckNodeId;
	}

	public String getQcCheckNodeName() {
		return qcCheckNodeName;
	}

	public void setQcCheckNodeName(String qcCheckNodeName) {
		this.qcCheckNodeName = qcCheckNodeName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIsRecheck() {
		return isRecheck;
	}

	public void setIsRecheck(String isRecheck) {
		this.isRecheck = isRecheck;
	}

	public String getQcBillType() {
		return qcBillType;
	}

	public void setQcBillType(String qcBillType) {
		this.qcBillType = qcBillType;
	}

	public Date getAcceptCheckDatetime() {
		return acceptCheckDatetime;
	}

	public void setAcceptCheckDatetime(Date acceptCheckDatetime) {
		this.acceptCheckDatetime = acceptCheckDatetime;
	}

	public Date getBeginAcceptCheckDatetime() {
		return beginAcceptCheckDatetime;
	}

	public void setBeginAcceptCheckDatetime(Date beginAcceptCheckDatetime) {
		this.beginAcceptCheckDatetime = beginAcceptCheckDatetime;
	}

	public Date getEndAcceptCheckDatetime() {
		return endAcceptCheckDatetime;
	}

	public void setEndAcceptCheckDatetime(Date endAcceptCheckDatetime) {
		this.endAcceptCheckDatetime = endAcceptCheckDatetime;
	}

	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}

	public String getReviewStatus() {
		return reviewStatus;
	}

	public void setReviewStatus(String reviewStatus) {
		this.reviewStatus = reviewStatus;
	}

	public String getReviewRemark() {
		return reviewRemark;
	}

	public void setReviewRemark(String reviewRemark) {
		this.reviewRemark = reviewRemark;
	}

	public Date getReviewDatetime() {
		return reviewDatetime;
	}

	public void setReviewDatetime(Date reviewDatetime) {
		this.reviewDatetime = reviewDatetime;
	}

	public Integer getEnginDepartId() {
		return enginDepartId;
	}

	public void setEnginDepartId(Integer enginDepartId) {
		this.enginDepartId = enginDepartId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public List<Integer> getEnginDepartIds() {
		return enginDepartIds;
	}

	public void setEnginDepartIds(List<Integer> enginDepartIds) {
		this.enginDepartIds = enginDepartIds;
	}
	
}