package cn.damei.entity.mobile.Manager;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;



public class PurchaseVo    extends DataEntity2<PurchaseVo> {

	
	

	private static final long serialVersionUID = 1L;
	
	
	private Integer id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	private Integer orderId;
	private String purchaseCode;
	private String remarks;
	private Date applyTime;
	private Integer applyPerson;
	
	private Double AuxiliaryAllMoney;
	private Date hopeForTime;
	private String  status;
	private String purchaseType;
	private Integer  totalCount;

	
	private Integer overCount;
	private String  overReasonType;
	private String overWords;
	private Double purchaseCountTotal;
	private String statusDescribe;
	private String statusId;
	
	
	
	public String getStatusId() {
		return statusId;
	}
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
	public String getStatusDescribe() {
		return statusDescribe;
	}
	public void setStatusDescribe(String statusDescribe) {
		this.statusDescribe = statusDescribe;
	}
	public Double getPurchaseCountTotal() {
		return purchaseCountTotal;
	}
	public void setPurchaseCountTotal(Double purchaseCountTotal) {
		this.purchaseCountTotal = purchaseCountTotal;
	}
	public Integer getOverCount() {
		return overCount;
	}
	public void setOverCount(Integer overCount) {
		this.overCount = overCount;
	}
	public String getOverReasonType() {
		return overReasonType;
	}
	public void setOverReasonType(String overReasonType) {
		this.overReasonType = overReasonType;
	}
	public String getOverWords() {
		return overWords;
	}
	public void setOverWords(String overWords) {
		this.overWords = overWords;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	private Date createDate;
	private String delFlag;
	
	
	
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	public String getPurchaseType() {
		return purchaseType;
	}
	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
	}
	private String customerName;
	private String communityName;
	private String buildNumber;
	private String buildUnit;
	private String buildRoom;
	
	
	
	
	
	
	
	
	
	
	
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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
	
	public Integer getApplyPerson() {
		return applyPerson;
	}
	public void setApplyPerson(Integer applyPerson) {
		this.applyPerson = applyPerson;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getPurchaseCode() {
		return purchaseCode;
	}
	public void setPurchaseCode(String purchaseCode) {
		this.purchaseCode = purchaseCode;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Date getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	public Double getAuxiliaryAllMoney() {
		return AuxiliaryAllMoney;
	}
	public void setAuxiliaryAllMoney(Double auxiliaryAllMoney) {
		AuxiliaryAllMoney = auxiliaryAllMoney;
	}
	public Date getHopeForTime() {
		return hopeForTime;
	}
	public void setHopeForTime(Date hopeForTime) {
		this.hopeForTime = hopeForTime;
	}
	

}
