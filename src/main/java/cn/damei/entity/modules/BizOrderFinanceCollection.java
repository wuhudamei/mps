package cn.damei.entity.modules;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.damei.common.persistence.DataEntity2;

public class BizOrderFinanceCollection extends DataEntity2<BizOrderFinanceCollection> {

	private static final long serialVersionUID = 1L;

	private Integer orderId;

	private String collectionType;

	private Double collectionAmount;

	private Date collectionDatetime;

	private String collectionStatus;
	
	private List<String> collectionStatusList = null;
	
	private String collectionStatusStrs;

	private Integer collectionOperatorEmployeeId;

	private String collectionRemarks;

	private Date collectionOperatorDatetime;
	private Integer storeId;
	private String projectMode;
	private Integer enginDepartId;
	private String departmentName;
	private List<Integer> enginDepartIds = new ArrayList<Integer>();
	private String orderNum;
	private String customerName;
	private String customerPhone;
	private String communityName;
	private String buildNumber;
	private String buildUnit;
	private String buildRoom;
	private Date signContractDate;
	private Date beginCheckDate;
	private Date endCheckDate;
	private Date sysBeginCheckDate;
	private Date sysEndCheckDate;
	
	
	
	

	public String getCollectionStatusStrs() {
		return collectionStatusStrs;
	}

	public void setCollectionStatusStrs(String collectionStatusStrs) {
		this.collectionStatusStrs = collectionStatusStrs;
	}

	public Date getSysBeginCheckDate() {
		return sysBeginCheckDate;
	}

	public void setSysBeginCheckDate(Date sysBeginCheckDate) {
		this.sysBeginCheckDate = sysBeginCheckDate;
	}

	public Date getSysEndCheckDate() {
		return sysEndCheckDate;
	}

	public void setSysEndCheckDate(Date sysEndCheckDate) {
		this.sysEndCheckDate = sysEndCheckDate;
	}

	public List<String> getCollectionStatusList() {
		return collectionStatusList;
	}

	public void setCollectionStatusList(List<String> collectionStatusList) {
		this.collectionStatusList = collectionStatusList;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}

	public Integer getEnginDepartId() {
		return enginDepartId;
	}

	public void setEnginDepartId(Integer enginDepartId) {
		this.enginDepartId = enginDepartId;
	}

	public List<Integer> getEnginDepartIds() {
		return enginDepartIds;
	}

	public void setEnginDepartIds(List<Integer> enginDepartIds) {
		this.enginDepartIds = enginDepartIds;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
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

	public Date getSignContractDate() {
		return signContractDate;
	}

	public void setSignContractDate(Date signContractDate) {
		this.signContractDate = signContractDate;
	}

	public Date getBeginCheckDate() {
		return beginCheckDate;
	}

	public void setBeginCheckDate(Date beginCheckDate) {
		this.beginCheckDate = beginCheckDate;
	}

	public Date getEndCheckDate() {
		return endCheckDate;
	}

	public void setEndCheckDate(Date endCheckDate) {
		this.endCheckDate = endCheckDate;
	}

	public String getCollectionStatus() {
		return collectionStatus;
	}

	public void setCollectionStatus(String collectionStatus) {
		this.collectionStatus = collectionStatus;
	}

	public Integer getCollectionOperatorEmployeeId() {
		return collectionOperatorEmployeeId;
	}

	public void setCollectionOperatorEmployeeId(Integer collectionOperatorEmployeeId) {
		this.collectionOperatorEmployeeId = collectionOperatorEmployeeId;
	}

	public String getCollectionRemarks() {
		return collectionRemarks;
	}

	public void setCollectionRemarks(String collectionRemarks) {
		this.collectionRemarks = collectionRemarks;
	}

	public Date getCollectionOperatorDatetime() {
		return collectionOperatorDatetime;
	}

	public void setCollectionOperatorDatetime(Date collectionOperatorDatetime) {
		this.collectionOperatorDatetime = collectionOperatorDatetime;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getCollectionType() {
		return collectionType;
	}

	public void setCollectionType(String collectionType) {
		this.collectionType = collectionType;
	}

	public Double getCollectionAmount() {
		return collectionAmount;
	}

	public void setCollectionAmount(Double collectionAmount) {
		this.collectionAmount = collectionAmount;
	}

	public Date getCollectionDatetime() {
		return collectionDatetime;
	}

	public void setCollectionDatetime(Date collectionDatetime) {
		this.collectionDatetime = collectionDatetime;
	}

}
