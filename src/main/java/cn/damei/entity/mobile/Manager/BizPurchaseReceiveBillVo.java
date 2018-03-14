package cn.damei.entity.mobile.Manager;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

public class BizPurchaseReceiveBillVo extends DataEntity2<BizPurchaseReceiveBillVo>{

	private static final long serialVersionUID = 1L;
	
	private Integer purchaseId;
	private String purchaseReceiveBillCode;
	private Date receiveDate;
	private Integer receiveEmployeeId;
	private String purchaseCode;
	private String purchaseType;
	private String customerAddress;
	private String cummunityName;
	private String buildNumber;
	private String builtUnit;
	private String builtRoom;
	private String customerName;
	
	public Integer getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(Integer purchaseId) {
		this.purchaseId = purchaseId;
	}
	public String getPurchaseReceiveBillCode() {
		return purchaseReceiveBillCode;
	}
	public void setPurchaseReceiveBillCode(String purchaseReceiveBillCode) {
		this.purchaseReceiveBillCode = purchaseReceiveBillCode;
	}
	public Date getReceiveDate() {
		return receiveDate;
	}
	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}
	public Integer getReceiveEmployeeId() {
		return receiveEmployeeId;
	}
	public void setReceiveEmployeeId(Integer receiveEmployeeId) {
		this.receiveEmployeeId = receiveEmployeeId;
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
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public String getCummunityName() {
		return cummunityName;
	}
	public void setCummunityName(String cummunityName) {
		this.cummunityName = cummunityName;
	}
	public String getBuildNumber() {
		return buildNumber;
	}
	public void setBuildNumber(String buildNumber) {
		this.buildNumber = buildNumber;
	}
	public String getBuiltUnit() {
		return builtUnit;
	}
	public void setBuiltUnit(String builtUnit) {
		this.builtUnit = builtUnit;
	}
	public String getBuiltRoom() {
		return builtRoom;
	}
	public void setBuiltRoom(String builtRoom) {
		this.builtRoom = builtRoom;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
}
