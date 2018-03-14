
package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;


public class BizPmStarCommissionCnfgSnap extends DataEntity2<BizPmStarCommissionCnfgSnap> {
	
	private static final long serialVersionUID = 1L;
	private Integer storeId;
	private Integer orderId;
	private Integer pmEmployeeId;
	private String isOldNew;
	private Integer starLever;
	private Double commissionAmount;
	private Double commissionRateMidway;
	private Double commissionRateComplete;
	
	private String orderNumber;
	private String customerName;
	private String customerPhone;
	private String communityName;
	private String buildNumber;
	private String buildUnit;
	private String buildRoom;
	private String itemManager;
	private String itemManagerPhone;
	private Date  sendOrderDate;
	
	
	public String getItemManagerPhone() {
		return itemManagerPhone;
	}

	public void setItemManagerPhone(String itemManagerPhone) {
		this.itemManagerPhone = itemManagerPhone;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
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

	public Date getSendOrderDate() {
		return sendOrderDate;
	}

	public void setSendOrderDate(Date sendOrderDate) {
		this.sendOrderDate = sendOrderDate;
	}

	public BizPmStarCommissionCnfgSnap() {
		super();
	}

	public BizPmStarCommissionCnfgSnap(Integer id){
		super(id);
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	
	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	public Integer getPmEmployeeId() {
		return pmEmployeeId;
	}

	public void setPmEmployeeId(Integer pmEmployeeId) {
		this.pmEmployeeId = pmEmployeeId;
	}
	
	public String getIsOldNew() {
		return isOldNew;
	}

	public void setIsOldNew(String isOldNew) {
		this.isOldNew = isOldNew;
	}
	
	public Integer getStarLever() {
		return starLever;
	}

	public void setStarLever(Integer starLever) {
		this.starLever = starLever;
	}
	
	public Double getCommissionAmount() {
		return commissionAmount;
	}

	public void setCommissionAmount(Double commissionAmount) {
		this.commissionAmount = commissionAmount;
	}
	
	public Double getCommissionRateMidway() {
		return commissionRateMidway;
	}

	public void setCommissionRateMidway(Double commissionRateMidway) {
		this.commissionRateMidway = commissionRateMidway;
	}
	
	public Double getCommissionRateComplete() {
		return commissionRateComplete;
	}

	public void setCommissionRateComplete(Double commissionRateComplete) {
		this.commissionRateComplete = commissionRateComplete;
	}
	
}