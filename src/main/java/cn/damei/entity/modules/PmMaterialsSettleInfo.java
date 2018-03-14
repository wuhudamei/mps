package cn.damei.entity.modules;

import java.util.Date;
import java.util.List;

import cn.damei.common.persistence.DataEntity2;


public class PmMaterialsSettleInfo extends DataEntity2<PmMaterialsSettleInfo> {

	private static final long serialVersionUID = 1L;
	private Integer storeId;
	private Integer enginDepartId;
	private String engineDepartName;
	private Integer orderId;
	private String orderNumber;
	private Date auditDate;
	private String settleStatus;
	private String communityName;
	private String buildNumber;
	private String buildUnit;
	private String buildRoom;
	private String customerName;
	private String itemManager;
	private String itemPhone;
	private Integer taskPackageId;
	private String taskPackageNo;
	private String taskPackageName;
	private Integer settleId;
	private Double auxiliaryMaterialsSettleAmount;
	private Double auxiliaryMaterialsAmount;
	private Double sandCementAmount;
	private Double pmMaterialsSettleAmount;
	private List<String> status; 
	
	
	public Integer getSettleId() {
		return settleId;
	}

	public void setSettleId(Integer settleId) {
		this.settleId = settleId;
	}

	public List<String> getStatus() {
		return status;
	}

	public void setStatus(List<String> status) {
		this.status = status;
	}

	public Integer getEnginDepartId() {
		return enginDepartId;
	}

	public void setEnginDepartId(Integer enginDepartId) {
		this.enginDepartId = enginDepartId;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getEngineDepartName() {
		return engineDepartName;
	}

	public void setEngineDepartName(String engineDepartName) {
		this.engineDepartName = engineDepartName;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Date getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}

	public String getSettleStatus() {
		return settleStatus;
	}

	public void setSettleStatus(String settleStatus) {
		this.settleStatus = settleStatus;
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

	public String getItemPhone() {
		return itemPhone;
	}

	public void setItemPhone(String itemPhone) {
		this.itemPhone = itemPhone;
	}

	public Integer getTaskPackageId() {
		return taskPackageId;
	}

	public void setTaskPackageId(Integer taskPackageId) {
		this.taskPackageId = taskPackageId;
	}

	public String getTaskPackageNo() {
		return taskPackageNo;
	}

	public void setTaskPackageNo(String taskPackageNo) {
		this.taskPackageNo = taskPackageNo;
	}

	public String getTaskPackageName() {
		return taskPackageName;
	}

	public void setTaskPackageName(String taskPackageName) {
		this.taskPackageName = taskPackageName;
	}

	public Double getAuxiliaryMaterialsSettleAmount() {
		return auxiliaryMaterialsSettleAmount;
	}

	public void setAuxiliaryMaterialsSettleAmount(Double auxiliaryMaterialsSettleAmount) {
		this.auxiliaryMaterialsSettleAmount = auxiliaryMaterialsSettleAmount;
	}

	public Double getAuxiliaryMaterialsAmount() {
		return auxiliaryMaterialsAmount;
	}

	public void setAuxiliaryMaterialsAmount(Double auxiliaryMaterialsAmount) {
		this.auxiliaryMaterialsAmount = auxiliaryMaterialsAmount;
	}

	public Double getSandCementAmount() {
		return sandCementAmount;
	}

	public void setSandCementAmount(Double sandCementAmount) {
		this.sandCementAmount = sandCementAmount;
	}

	public Double getPmMaterialsSettleAmount() {
		return pmMaterialsSettleAmount;
	}

	public void setPmMaterialsSettleAmount(Double pmMaterialsSettleAmount) {
		this.pmMaterialsSettleAmount = pmMaterialsSettleAmount;
	}

}
