package cn.damei.entity.modules;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.damei.common.persistence.DataEntity2;

public class BizOrderFinanceCollection extends DataEntity2<BizOrderFinanceCollection> {

	private static final long serialVersionUID = 1L;

	private Integer orderId;// 订单Id

	private String collectionType;// 收款类型

	private Double collectionAmount;// 收款金额

	private Date collectionDatetime;// 收款日期

	private String collectionStatus;// 收款状态
	
	private List<String> collectionStatusList = null;
	
	private String collectionStatusStrs;

	private Integer collectionOperatorEmployeeId;// 收款操作人员Id

	private String collectionRemarks;// 收款说明

	private Date collectionOperatorDatetime;// 收款操作时间
	private Integer storeId;
	private String projectMode;
	private Integer enginDepartId;
	private String departmentName;
	private List<Integer> enginDepartIds = new ArrayList<Integer>();
	private String orderNum;
	private String customerName;
	private String customerPhone;
	private String communityName; // 小区名称
	private String buildNumber; // 几号楼
	private String buildUnit; // 几单元
	private String buildRoom; // 哪一室
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
