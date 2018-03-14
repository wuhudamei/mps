package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;

import java.util.Date;
import java.util.List;

public class BizPurchaseMainPanel extends DataEntity2<BizPurchaseMainPanel> {


	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer orderId;
	private Integer storeId;
	private Date applyTime;
	private Date applyReceiveTime;
	private String orderNumber;
	private String purchaseCode;
	private String purchaseType;
	private String receiverName;
	private String receiverPhone;
	private Integer applyEmployee;
	private String applyName;
	private String applyEmployeePhone;

	private String status;
	private String remarks;
	private String customerName;
	private String customerPhone;
	private String customerAddress;
	private String communityName;
	private String buildNumber;
	private String buildUnit;
	private String buildRoom;
	private String itemManager;
	private Integer itemManagerId;
	private String itemManagerPhone;
	private Date beginApplyReceiveTime;
	private Date endApplyReceiveTime;
	private Date beginApplyTime;
	private Date endApplyTime;

	private String projectMode;
	private Double overNumber;
	private String overReasonType;
	private String overReasonWords;
	private String contractArea;
	private Double purchaseCountTotal;
	private Double standardCountTotal;
	private String statusDescribe;
	private String isScrap;

	private String purchaseApplyIndex;

	private List<Integer> purchaseIds = null;

	public List<Integer> getPurchaseIds() {
		return purchaseIds;
	}

	public void setPurchaseIds(List<Integer> purchaseIds) {
		this.purchaseIds = purchaseIds;
	}

	public String getPurchaseApplyIndex() {
		return purchaseApplyIndex;
	}

	public void setPurchaseApplyIndex(String purchaseApplyIndex) {
		this.purchaseApplyIndex = purchaseApplyIndex;
	}

	public String getApplyEmployeePhone() {
		return applyEmployeePhone;
	}

	public void setApplyEmployeePhone(String applyEmployeePhone) {
		this.applyEmployeePhone = applyEmployeePhone;
	}

	public String getStatusDescribe() {
		return statusDescribe;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public void setStatusDescribe(String statusDescribe) {
		this.statusDescribe = statusDescribe;
	}

	public String getContractArea() {
		return contractArea;
	}

	public String getIsScrap() {
		return isScrap;
	}

	public void setIsScrap(String isScrap) {
		this.isScrap = isScrap;
	}

	public void setContractArea(String contractArea) {
		this.contractArea = contractArea;
	}

	public Double getPurchaseCountTotal() {
		return purchaseCountTotal;
	}

	public void setPurchaseCountTotal(Double purchaseCountTotal) {
		this.purchaseCountTotal = purchaseCountTotal;
	}

	public Double getStandardCountTotal() {
		return standardCountTotal;
	}

	public void setStandardCountTotal(Double standardCountTotal) {
		this.standardCountTotal = standardCountTotal;
	}

	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public Date getApplyReceiveTime() {
		return applyReceiveTime;
	}

	public void setApplyReceiveTime(Date applyReceiveTime) {
		this.applyReceiveTime = applyReceiveTime;
	}

	public String getPurchaseCode() {
		return purchaseCode;
	}

	public void setPurchaseCode(String purchaseCode) {
		this.purchaseCode = purchaseCode;
	}

	public String getPurchaseType() {
		return purchaseType;
	}

	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverPhone() {
		return receiverPhone;
	}

	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}

	public Integer getApplyEmployee() {
		return applyEmployee;
	}

	public void setApplyEmployee(Integer applyEmployee) {
		this.applyEmployee = applyEmployee;
	}

	public String getApplyName() {
		return applyName;
	}

	public void setApplyName(String applyName) {
		this.applyName = applyName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public Date getBeginApplyReceiveTime() {
		return beginApplyReceiveTime;
	}

	public void setBeginApplyReceiveTime(Date beginApplyReceiveTime) {
		this.beginApplyReceiveTime = beginApplyReceiveTime;
	}

	public Date getEndApplyReceiveTime() {
		return endApplyReceiveTime;
	}

	public void setEndApplyReceiveTime(Date endApplyReceiveTime) {
		this.endApplyReceiveTime = endApplyReceiveTime;
	}

	public Date getBeginApplyTime() {
		return beginApplyTime;
	}

	public void setBeginApplyTime(Date beginApplyTime) {
		this.beginApplyTime = beginApplyTime;
	}

	public Date getEndApplyTime() {
		return endApplyTime;
	}

	public void setEndApplyTime(Date endApplyTime) {
		this.endApplyTime = endApplyTime;
	}

	public Double getOverNumber() {
		return overNumber;
	}

	public void setOverNumber(Double overNumber) {
		this.overNumber = overNumber;
	}

	public String getOverReasonType() {
		return overReasonType;
	}

	public void setOverReasonType(String overReasonType) {
		this.overReasonType = overReasonType;
	}

	public String getOverReasonWords() {
		return overReasonWords;
	}

	public void setOverReasonWords(String overReasonWords) {
		this.overReasonWords = overReasonWords;
	}

	public String getItemManagerPhone() {
		return itemManagerPhone;
	}

	public void setItemManagerPhone(String itemManagerPhone) {
		this.itemManagerPhone = itemManagerPhone;
	}
}
