
package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity;


public class CheckDetails extends DataEntity<CheckDetails> {
	
	private static final long serialVersionUID = 1L;
	
	private String storeId;
	private Integer orderId;
	private String orderNumber;

	private String communityName;
	private String buildNumber;
	private String buildUnit;
	private String buildRoom;
	private String customerName;
	private String itemManager;
	
	private Date actualStartDate;
	private Date beginActualStartDate;
	private Date endActualStartDate;
	
	private String checkNodeName;
	private Integer allCount;
	private Integer allCountTwo;
	private Integer nowCount;
	private String projectMode;
	
	private Integer engineDepartId;
	private String engineDepartName;
	
	private Integer checkNodeNameNewId;
	private String checkNodeNameNew;
	
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getAllCountTwo() {
		return allCountTwo;
	}
	public void setAllCountTwo(Integer allCountTwo) {
		this.allCountTwo = allCountTwo;
	}
	public String getProjectMode() {
		return projectMode;
	}
	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
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
	public String getCheckNodeName() {
		return checkNodeName;
	}
	public void setCheckNodeName(String checkNodeName) {
		this.checkNodeName = checkNodeName;
	}
	public Integer getAllCount() {
		return allCount;
	}
	public void setAllCount(Integer allCount) {
		this.allCount = allCount;
	}
	public Integer getNowCount() {
		return nowCount;
	}
	public void setNowCount(Integer nowCount) {
		this.nowCount = nowCount;
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
	public Integer getCheckNodeNameNewId() {
		return checkNodeNameNewId;
	}
	public void setCheckNodeNameNewId(Integer checkNodeNameNewId) {
		this.checkNodeNameNewId = checkNodeNameNewId;
	}
	public String getCheckNodeNameNew() {
		return checkNodeNameNew;
	}
	public void setCheckNodeNameNew(String checkNodeNameNew) {
		this.checkNodeNameNew = checkNodeNameNew;
	}
	
	
	
	
	
}