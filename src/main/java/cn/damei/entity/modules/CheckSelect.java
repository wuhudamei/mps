
package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity;


public class CheckSelect extends DataEntity<CheckSelect> {
	
	private static final long serialVersionUID = 1L;
	
	private Integer orderId;
	private Integer storeId;
	private String storeName;
	private String projectMode;
	private String projectModeName;
	private Integer engineDepartId;
	private String engineDepartName;
	private String orderNumber;

	private String detailAddress;
	private String communityName;
	private String buildNumber;
	private String buildUnit;
	private String buildRoom;
	private String customerName;
	
	private String itemManager;
	private String orderInspector;
	private String signType;
	
	private Date actualStartDate;
	private Date beginActualStartDate;
	private Date endActualStartDate;
	
	private Integer qcBillId;
	private String qcBillType;
	
	private String isRecheck;
	
	private Integer qcCheckNodeId;
	private String qcCheckNodeName;
	
	private String status;
	
	private Date expectCheckDatetime;
	private Date signCheckDatetime;
	
	private Date checkDatetime;
	private Date beginCheckDatetime;
	private Date endCheckDatetime;
	
	private Date acceptCheckDatetime;
	private Date beginAcceptCheckDatetime;
	private Date endAcceptCheckDatetime;

	private Date assertNodeConfirmDoneStartDate;
	private Date assertNodeConfirmDoneEndDate;

	private Double totalScore;
	private Double gotScore;
	
	private Date beginCreateDate;
	private Date endCreateDate;
	
	private String totalAndGotIsEqual;
	private String delayReasonPm;
	private String delayReasonQc;

	public Date getAssertNodeConfirmDoneStartDate() {
		return assertNodeConfirmDoneStartDate;
	}

	public void setAssertNodeConfirmDoneStartDate(Date assertNodeConfirmDoneStartDate) {
		this.assertNodeConfirmDoneStartDate = assertNodeConfirmDoneStartDate;
	}

	public Date getAssertNodeConfirmDoneEndDate() {
		return assertNodeConfirmDoneEndDate;
	}

	public void setAssertNodeConfirmDoneEndDate(Date assertNodeConfirmDoneEndDate) {
		this.assertNodeConfirmDoneEndDate = assertNodeConfirmDoneEndDate;
	}

	public String getDelayReasonPm() {
		return delayReasonPm;
	}

	public void setDelayReasonPm(String delayReasonPm) {
		this.delayReasonPm = delayReasonPm;
	}

	public String getDelayReasonQc() {
		return delayReasonQc;
	}

	public void setDelayReasonQc(String delayReasonQc) {
		this.delayReasonQc = delayReasonQc;
	}

	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
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
	public String getProjectMode() {
		return projectMode;
	}
	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}
	public String getProjectModeName() {
		return projectModeName;
	}
	public void setProjectModeName(String projectModeName) {
		this.projectModeName = projectModeName;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getDetailAddress() {
		return detailAddress;
	}
	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
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
	public String getOrderInspector() {
		return orderInspector;
	}
	public void setOrderInspector(String orderInspector) {
		this.orderInspector = orderInspector;
	}
	public Date getActualStartDate() {
		return actualStartDate;
	}
	public void setActualStartDate(Date actualStartDate) {
		this.actualStartDate = actualStartDate;
	}
	public Date getBeginActualStartDate() {
		return beginActualStartDate;
	}
	public void setBeginActualStartDate(Date beginActualStartDate) {
		this.beginActualStartDate = beginActualStartDate;
	}
	public Date getEndActualStartDate() {
		return endActualStartDate;
	}
	public void setEndActualStartDate(Date endActualStartDate) {
		this.endActualStartDate = endActualStartDate;
	}
	public Integer getQcBillId() {
		return qcBillId;
	}
	public void setQcBillId(Integer qcBillId) {
		this.qcBillId = qcBillId;
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
	public Date getCheckDatetime() {
		return checkDatetime;
	}
	public void setCheckDatetime(Date checkDatetime) {
		this.checkDatetime = checkDatetime;
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
	public Date getBeginCreateDate() {
		return beginCreateDate;
	}
	public void setBeginCreateDate(Date beginCreateDate) {
		this.beginCreateDate = beginCreateDate;
	}
	public Date getEndCreateDate() {
		return endCreateDate;
	}
	public void setEndCreateDate(Date endCreateDate) {
		this.endCreateDate = endCreateDate;
	}
	public String getTotalAndGotIsEqual() {
		return totalAndGotIsEqual;
	}
	public void setTotalAndGotIsEqual(String totalAndGotIsEqual) {
		this.totalAndGotIsEqual = totalAndGotIsEqual;
	}
	public Integer getEngineDepartId() {
		return engineDepartId;
	}
	public void setEngineDepartId(Integer engineDepartId) {
		this.engineDepartId = engineDepartId;
	}
	public String getEngineDepartName() {
		return engineDepartName;
	}
	public void setEngineDepartName(String engineDepartName) {
		this.engineDepartName = engineDepartName;
	}
	public Date getExpectCheckDatetime() {
		return expectCheckDatetime;
	}
	public void setExpectCheckDatetime(Date expectCheckDatetime) {
		this.expectCheckDatetime = expectCheckDatetime;
	}
	public Date getSignCheckDatetime() {
		return signCheckDatetime;
	}
	public void setSignCheckDatetime(Date signCheckDatetime) {
		this.signCheckDatetime = signCheckDatetime;
	}
	public String getSignType() {
		return signType;
	}
	public void setSignType(String signType) {
		this.signType = signType;
	}
	
	
	
	
	
}