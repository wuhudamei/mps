package cn.damei.entity.mobile.Manager;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;
import cn.damei.entity.mobile.Manager.AuxiliaryVo;



public class PurchaseTwoVo extends DataEntity2<AuxiliaryVo> {

	
	

	private static final long serialVersionUID = 1L;
	
	
	
	private Integer orderId;
	private String purchaseCode;
	private String remarks;
	private Date applyTime;
	private Integer applyPerson;
	private Integer  purchaseId;
	public Integer getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(Integer purchaseId) {
		this.purchaseId = purchaseId;
	}
	private Double AuxiliaryAllMoney;
	private Date hopeForTime;
	private String  status;
	private Integer totalCount;
	private String purchaseType;
	private  Date createDate;
	
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getPurchaseType() {
		return purchaseType;
	}
	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
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
