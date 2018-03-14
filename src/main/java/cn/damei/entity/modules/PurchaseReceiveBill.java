
package cn.damei.entity.modules;

import java.util.Date;
import cn.damei.common.persistence.DataEntity2;


public class PurchaseReceiveBill extends DataEntity2<PurchaseReceiveBill> {
	
	private static final long serialVersionUID = 1L;
	private Integer storeId;
	private String purchaseCode;
	private String purchaseType;
	private Integer purchaseId;
	private String purchaseReceiveBillCode;
	private Date receiveDate;
	private Integer receiveEmployeeId;
	private String receiveName;
	private Date beginReceiveDate;
	private Date endReceiveDate;
	private String customerName;
	private String customerAddress;
	private String communityName;
	private String buildNumber;
	private String buildUnit;
	private String buildRoom;
	private String projectMode;
	private String orderNumber;
	private String purchaseTypeName;
	
	
	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}

	public PurchaseReceiveBill() {
		super();
	}

	public PurchaseReceiveBill(Integer id){
		super(id);
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
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

	public String getReceiveName() {
		return receiveName;
	}

	public void setReceiveName(String receiverName) {
		this.receiveName = receiverName;
	}

	public Date getBeginReceiveDate() {
		return beginReceiveDate;
	}

	public void setBeginReceiveDate(Date beginReceiveDate) {
		this.beginReceiveDate = beginReceiveDate;
	}

	public Date getEndReceiveDate() {
		return endReceiveDate;
	}

	public void setEndReceiveDate(Date endReceiveDate) {
		this.endReceiveDate = endReceiveDate;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
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

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getPurchaseTypeName() {
		return purchaseTypeName;
	}

	public void setPurchaseTypeName(String purchaseTypeName) {
		this.purchaseTypeName = purchaseTypeName;
	}
	
	

}