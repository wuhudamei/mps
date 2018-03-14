package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;


public class SendItemMgrDetail extends DataEntity2<SendItemMgrDetail>  {
	

	private static final long serialVersionUID = 1L;
	private String storeId;
	private String storeName;
	
	private Date createDate;
	private Date beginCreateDate;
	private Date endCreateDate;
	
	private String operName;
	
	private String newItemManager;
	private String oldItemManager;
	
	private String projectMode;
	private String projectModeName;
	
	private String engineDepartId;
	private String engineDepartName;
	
	private String orderNumber;
	private Date actualStartDate;
	
	private String communityName;
	private String buildNumber;
	private String buildUnit;
	private String buildRoom;
	
	private String customerName;
	private String customerPhone;
	
	private String designerName;
	private String designerPhone;
	
	private String itemManager;
	private String itemManagerPhone;
	
	private String orderInspector;
	private String orderInspectorPhone;
	

	private String itemManagerId;
	private Integer star;
	private String orderCount;
	private String orderTotalCount;
	private String remarks;
	private String orderId;
	private Integer orderStop;
	private String dailySendSingular;
	private String contractStartDate;
	private String contractEndDate;
	private String customerAddress;
	private String orderAcceptArea;
	private String month;
	private String monthDispathCount;
	private String days;
	private String managerCount;

	
	
	public String getManagerCount() {
		return managerCount;
	}
	public void setManagerCount(String managerCount) {
		this.managerCount = managerCount;
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public String getDispatchDays(){
		return itemManagerId+days;
	}
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getMonthDispathCount() {
		return monthDispathCount;
	}

	public void setMonthDispathCount(String monthDispathCount) {
		this.monthDispathCount = monthDispathCount;
	}

	public String getDispatchMonth(){
		return itemManagerId+month;
	}
	
	public String getOrderAcceptArea() {
		return orderAcceptArea;
	}
	public void setOrderAcceptArea(String orderAcceptArea) {
		this.orderAcceptArea = orderAcceptArea;
	}
	public String getDailySendSingular() {
		return dailySendSingular;
	}
	public void setDailySendSingular(String dailySendSingular) {
		this.dailySendSingular = dailySendSingular;
	}
	public String getContractStartDate() {
		return contractStartDate;
	}
	public void setContractStartDate(String contractStartDate) {
		this.contractStartDate = contractStartDate;
	}
	public String getContractEndDate() {
		return contractEndDate;
	}
	public void setContractEndDate(String contractEndDate) {
		this.contractEndDate = contractEndDate;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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
	public String getOperName() {
		return operName;
	}
	public void setOperName(String operName) {
		this.operName = operName;
	}
	public String getNewItemManager() {
		return newItemManager;
	}
	public void setNewItemManager(String newItemManager) {
		this.newItemManager = newItemManager;
	}
	public String getOldItemManager() {
		return oldItemManager;
	}
	public void setOldItemManager(String oldItemManager) {
		this.oldItemManager = oldItemManager;
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
	public String getEngineDepartId() {
		return engineDepartId;
	}
	public void setEngineDepartId(String engineDepartId) {
		this.engineDepartId = engineDepartId;
	}
	public String getEngineDepartName() {
		return engineDepartName;
	}
	public void setEngineDepartName(String engineDepartName) {
		this.engineDepartName = engineDepartName;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public Date getActualStartDate() {
		return actualStartDate;
	}
	public void setActualStartDate(Date actualStartDate) {
		this.actualStartDate = actualStartDate;
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
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	public String getDesignerName() {
		return designerName;
	}
	public void setDesignerName(String designerName) {
		this.designerName = designerName;
	}
	public String getDesignerPhone() {
		return designerPhone;
	}
	public void setDesignerPhone(String designerPhone) {
		this.designerPhone = designerPhone;
	}
	public String getItemManager() {
		return itemManager;
	}
	public void setItemManager(String itemManager) {
		this.itemManager = itemManager;
	}
	public String getItemManagerPhone() {
		return itemManagerPhone;
	}
	public void setItemManagerPhone(String itemManagerPhone) {
		this.itemManagerPhone = itemManagerPhone;
	}
	public String getOrderInspector() {
		return orderInspector;
	}
	public void setOrderInspector(String orderInspector) {
		this.orderInspector = orderInspector;
	}
	public String getOrderInspectorPhone() {
		return orderInspectorPhone;
	}
	public void setOrderInspectorPhone(String orderInspectorPhone) {
		this.orderInspectorPhone = orderInspectorPhone;
	}
	public String getItemManagerId() {
		return itemManagerId;
	}
	public void setItemManagerId(String itemManagerId) {
		this.itemManagerId = itemManagerId;
	}
	public Integer getStar() {
		return star;
	}
	public void setStar(Integer star) {
		this.star = star;
	}
	public String getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(String orderCount) {
		this.orderCount = orderCount;
	}
	public String getOrderTotalCount() {
		return orderTotalCount;
	}
	public void setOrderTotalCount(String orderTotalCount) {
		this.orderTotalCount = orderTotalCount;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Integer getOrderStop() {
		return orderStop;
	}
	public void setOrderStop(Integer orderStop) {
		this.orderStop = orderStop;
	}
}
