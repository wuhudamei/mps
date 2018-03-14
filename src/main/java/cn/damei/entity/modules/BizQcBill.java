
package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity;


public class BizQcBill extends DataEntity<BizQcBill> {
	
	private static final long serialVersionUID = 1L;
	private Integer qcBillId;
	private String qcBillCode;
	private String qcBillType;
	private String isRecheck;
	private Integer relatedQcBillId;
	private Integer orderId;
	private Integer qcCheckNodeId;
	private String applyRemarks;
	private String status;
	private Integer applyEmployeeId;
	private String  applyEmployeeName;
	private Integer checkEmployeeId;
	private Date checkDatetime;
	private Date expectCheckDatetime;
	private Date acceptCheckDatetime;
	private Double totalScore;
	private Double gotScore;
	private Date beginCheckDatetime;
	private Date endCheckDatetime;
	private Integer recheckTimes;
	
	private String orderNumber;
	private String customerAddress;
	private String communityName;
	private String buildNumber;
	private String buildUnit;
	private String buildRoom;
	private String customerName;
	private String storeId;
	private String managerRealName;
	private String inspectorRealName;
	private Integer orderInspectorId;
	private String checkRealName;
	private String qcCheckNodeName;
	private Date signCheckDatetime;
	private String signAddress;
	private Date actualStartDate;
	private String projectMode;
	private String isEqual;
	
	
	
	
	public String getApplyEmployeeName() {
		return applyEmployeeName;
	}
	public void setApplyEmployeeName(String applyEmployeeName) {
		this.applyEmployeeName = applyEmployeeName;
	}
	public Date getSignCheckDatetime() {
		return signCheckDatetime;
	}
	public void setSignCheckDatetime(Date signCheckDatetime) {
		this.signCheckDatetime = signCheckDatetime;
	}
	public String getIsEqual() {
		return isEqual;
	}
	public void setIsEqual(String isEqual) {
		this.isEqual = isEqual;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public Integer getQcBillId() {
		return qcBillId;
	}
	public void setQcBillId(Integer qcBillId) {
		this.qcBillId = qcBillId;
	}
	public String getQcBillCode() {
		return qcBillCode;
	}
	public void setQcBillCode(String qcBillCode) {
		this.qcBillCode = qcBillCode;
	}
	public String getQcBillType() {
		return qcBillType;
	}
	public void setQcBillType(String qcBillType) {
		this.qcBillType = qcBillType;
	}
	public String getIsRecheck() {
		return isRecheck;
	}
	public void setIsRecheck(String isRecheck) {
		this.isRecheck = isRecheck;
	}
	public Integer getRelatedQcBillId() {
		return relatedQcBillId;
	}
	public void setRelatedQcBillId(Integer relatedQcBillId) {
		this.relatedQcBillId = relatedQcBillId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getQcCheckNodeId() {
		return qcCheckNodeId;
	}
	public void setQcCheckNodeId(Integer qcCheckNodeId) {
		this.qcCheckNodeId = qcCheckNodeId;
	}
	public String getApplyRemarks() {
		return applyRemarks;
	}
	public void setApplyRemarks(String applyRemarks) {
		this.applyRemarks = applyRemarks;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getApplyEmployeeId() {
		return applyEmployeeId;
	}
	public void setApplyEmployeeId(Integer applyEmployeeId) {
		this.applyEmployeeId = applyEmployeeId;
	}
	public Integer getCheckEmployeeId() {
		return checkEmployeeId;
	}
	public void setCheckEmployeeId(Integer checkEmployeeId) {
		this.checkEmployeeId = checkEmployeeId;
	}
	public Date getCheckDatetime() {
		return checkDatetime;
	}
	public void setCheckDatetime(Date checkDatetime) {
		this.checkDatetime = checkDatetime;
	}
	public Date getExpectCheckDatetime() {
		return expectCheckDatetime;
	}
	public void setExpectCheckDatetime(Date expectCheckDatetime) {
		this.expectCheckDatetime = expectCheckDatetime;
	}
	public Date getAcceptCheckDatetime() {
		return acceptCheckDatetime;
	}
	public void setAcceptCheckDatetime(Date acceptCheckDatetime) {
		this.acceptCheckDatetime = acceptCheckDatetime;
	}
	public Double getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(Double totalScore) {
		this.totalScore = totalScore;
	}
	public Double getGotScore() {
		return gotScore;
	}
	public void setGotScore(Double gotScore) {
		this.gotScore = gotScore;
	}
	public Date getBeginCheckDatetime() {
		return beginCheckDatetime;
	}
	public void setBeginCheckDatetime(Date beginCheckDatetime) {
		this.beginCheckDatetime = beginCheckDatetime;
	}
	public Date getEndCheckDatetime() {
		return endCheckDatetime;
	}
	public void setEndCheckDatetime(Date endCheckDatetime) {
		this.endCheckDatetime = endCheckDatetime;
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
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getManagerRealName() {
		return managerRealName;
	}
	public void setManagerRealName(String managerRealName) {
		this.managerRealName = managerRealName;
	}
	public String getInspectorRealName() {
		return inspectorRealName;
	}
	public void setInspectorRealName(String inspectorRealName) {
		this.inspectorRealName = inspectorRealName;
	}
	public String getCheckRealName() {
		return checkRealName;
	}
	public void setCheckRealName(String checkRealName) {
		this.checkRealName = checkRealName;
	}
	public String getQcCheckNodeName() {
		return qcCheckNodeName;
	}
	public void setQcCheckNodeName(String qcCheckNodeName) {
		this.qcCheckNodeName = qcCheckNodeName;
	}
	public String getSignAddress() {
		return signAddress;
	}
	public void setSignAddress(String signAddress) {
		this.signAddress = signAddress;
	}
	public Date getActualStartDate() {
		return actualStartDate;
	}
	public void setActualStartDate(Date actualStartDate) {
		this.actualStartDate = actualStartDate;
	}
	public Integer getOrderInspectorId() {
		return orderInspectorId;
	}
	public void setOrderInspectorId(Integer orderInspectorId) {
		this.orderInspectorId = orderInspectorId;
	}
	public Integer getRecheckTimes() {
		return recheckTimes;
	}
	public void setRecheckTimes(Integer recheckTimes) {
		this.recheckTimes = recheckTimes;
	}
	public String getProjectMode() {
		return projectMode;
	}
	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}
	
	
	
	
		
}