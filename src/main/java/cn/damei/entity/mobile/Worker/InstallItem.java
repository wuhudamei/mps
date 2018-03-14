package cn.damei.entity.mobile.Worker;

import java.util.List;

import cn.damei.common.persistence.DataEntity2;

public class InstallItem extends DataEntity2<InstallItem>{
	
	private static final long serialVersionUID = 1L;
	
	private Integer constructBillId;
	private String constructBillCode;
	private String constructBillStatus;
	private String constructBillStatusName;
	private Integer employeeGroupId;
	
	private Integer installBillId;
	private String supplierConfirmIntoDateString;
	private String supplierConfirmCompleteDateString;
	
	private Integer orderInstallPlanId;
	private String installItemName;
	
	private Integer orderId;
	private Integer storeId;
	private String projectMode;
	private String communityName;
	private String buildNumber;
	private String buildUnit;
	private String buildRoom;
	private String detailAddress;
	private Integer itemManagerId;
	private String itemManagerName;
	private String itemManagerPhone;
	
	private String text;
	
	private String lat;
	private String lon;
	
	private	List<String> constructBillStatusList = null;


	public Integer getConstructBillId() {
		return constructBillId;
	}


	public void setConstructBillId(Integer constructBillId) {
		this.constructBillId = constructBillId;
	}


	public String getConstructBillCode() {
		return constructBillCode;
	}


	public void setConstructBillCode(String constructBillCode) {
		this.constructBillCode = constructBillCode;
	}


	public String getConstructBillStatus() {
		return constructBillStatus;
	}


	public void setConstructBillStatus(String constructBillStatus) {
		this.constructBillStatus = constructBillStatus;
	}


	public String getConstructBillStatusName() {
		return constructBillStatusName;
	}


	public void setConstructBillStatusName(String constructBillStatusName) {
		this.constructBillStatusName = constructBillStatusName;
	}


	public Integer getEmployeeGroupId() {
		return employeeGroupId;
	}


	public void setEmployeeGroupId(Integer employeeGroupId) {
		this.employeeGroupId = employeeGroupId;
	}


	public Integer getInstallBillId() {
		return installBillId;
	}


	public void setInstallBillId(Integer installBillId) {
		this.installBillId = installBillId;
	}


	public String getSupplierConfirmIntoDateString() {
		return supplierConfirmIntoDateString;
	}


	public void setSupplierConfirmIntoDateString(String supplierConfirmIntoDateString) {
		this.supplierConfirmIntoDateString = supplierConfirmIntoDateString;
	}


	public String getSupplierConfirmCompleteDateString() {
		return supplierConfirmCompleteDateString;
	}


	public void setSupplierConfirmCompleteDateString(String supplierConfirmCompleteDateString) {
		this.supplierConfirmCompleteDateString = supplierConfirmCompleteDateString;
	}


	public Integer getOrderInstallPlanId() {
		return orderInstallPlanId;
	}


	public void setOrderInstallPlanId(Integer orderInstallPlanId) {
		this.orderInstallPlanId = orderInstallPlanId;
	}


	public String getInstallItemName() {
		return installItemName;
	}


	public void setInstallItemName(String installItemName) {
		this.installItemName = installItemName;
	}


	public Integer getOrderId() {
		return orderId;
	}


	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
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


	public String getDetailAddress() {
		return detailAddress;
	}


	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}


	public Integer getItemManagerId() {
		return itemManagerId;
	}


	public void setItemManagerId(Integer itemManagerId) {
		this.itemManagerId = itemManagerId;
	}


	public String getItemManagerName() {
		return itemManagerName;
	}


	public void setItemManagerName(String itemManagerName) {
		this.itemManagerName = itemManagerName;
	}


	public String getItemManagerPhone() {
		return itemManagerPhone;
	}


	public void setItemManagerPhone(String itemManagerPhone) {
		this.itemManagerPhone = itemManagerPhone;
	}


	public List<String> getConstructBillStatusList() {
		return constructBillStatusList;
	}


	public void setConstructBillStatusList(List<String> constructBillStatusList) {
		this.constructBillStatusList = constructBillStatusList;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public String getLat() {
		return lat;
	}


	public void setLat(String lat) {
		this.lat = lat;
	}


	public String getLon() {
		return lon;
	}


	public void setLon(String lon) {
		this.lon = lon;
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
	
	

	
}
