package cn.damei.entity.modules;

import java.util.Date;
import java.util.List;

import cn.damei.common.persistence.DataEntity2;

public class MaterialScheduleQueryEntity extends DataEntity2<MaterialScheduleQueryEntity> {


	private static final long serialVersionUID = 1L;
	private Integer storeId;
	private String orderNumber;
	private String communityName;
	private String buildNumber;
	private String buildUnit;
	private String buildRoom;
	private String customerName;
	private String itemManager;
	private String purchaseType;
	private String purchaseCode;
	private String status;
	private Date applyTime;
	private Date beginApplyTime;
	private Date endAapplyTime;
	private Date transferDate;
	private Date beginTransferDate;
	private Date endTransferDate;
	private Date completionDate;
	private Date beginCompletionDate;
	private Date endCompletionDate;
	private Integer  acceptMaterialCount;
	private Integer purchaseId;
	private List<String> purchaseStatusList;
	private Date recieveGoodsDate;
	private String projectMode;
	
	public String getProjectMode() {
		return projectMode;
	}
	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}
	public Date getRecieveGoodsDate() {
		return recieveGoodsDate;
	}
	public void setRecieveGoodsDate(Date recieveGoodsDate) {
		this.recieveGoodsDate = recieveGoodsDate;
	}
	public List<String> getPurchaseStatusList() {
		return purchaseStatusList;
	}
	public void setPurchaseStatusList(List<String> purchaseStatusList) {
		this.purchaseStatusList = purchaseStatusList;
	}
	public Integer getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(Integer purchaseId) {
		this.purchaseId = purchaseId;
	}
	public Integer getAcceptMaterialCount() {
		return acceptMaterialCount;
	}
	public void setAcceptMaterialCount(Integer acceptMaterialCount) {
		this.acceptMaterialCount = acceptMaterialCount;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
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
	public String getPurchaseType() {
		return purchaseType;
	}
	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
	}
	public String getPurchaseCode() {
		return purchaseCode;
	}
	public void setPurchaseCode(String purchaseCode) {
		this.purchaseCode = purchaseCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	public Date getBeginApplyTime() {
		return beginApplyTime;
	}
	public void setBeginApplyTime(Date beginApplyTime) {
		this.beginApplyTime = beginApplyTime;
	}
	public Date getEndAapplyTime() {
		return endAapplyTime;
	}
	public void setEndAapplyTime(Date endAapplyTime) {
		this.endAapplyTime = endAapplyTime;
	}
	public Date getTransferDate() {
		return transferDate;
	}
	public void setTransferDate(Date transferDate) {
		this.transferDate = transferDate;
	}
	public Date getBeginTransferDate() {
		return beginTransferDate;
	}
	public void setBeginTransferDate(Date beginTransferDate) {
		this.beginTransferDate = beginTransferDate;
	}
	public Date getEndTransferDate() {
		return endTransferDate;
	}
	public void setEndTransferDate(Date endTransferDate) {
		this.endTransferDate = endTransferDate;
	}
	public Date getCompletionDate() {
		return completionDate;
	}
	public void setCompletionDate(Date completionDate) {
		this.completionDate = completionDate;
	}
	public Date getBeginCompletionDate() {
		return beginCompletionDate;
	}
	public void setBeginCompletionDate(Date beginCompletionDate) {
		this.beginCompletionDate = beginCompletionDate;
	}
	public Date getEndCompletionDate() {
		return endCompletionDate;
	}
	public void setEndCompletionDate(Date endCompletionDate) {
		this.endCompletionDate = endCompletionDate;
	}
	
}
