package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;


public class BizMaterialSelfbuyVo extends DataEntity2<BizMaterialSelfbuyVo>{
	private static final long serialVersionUID = 1L;

	private String itemManager;
	private String itemPhone;
	private String customerName;
	private String customerPhone;
	private String communityName;
	private String buildNumber;
	private String buildUnit;
	private String buildRoom;
	private String materialName;
	private Double customerPayAmount;
	private Double settleRate;
	private Double settleAmount;
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
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public Double getCustomerPayAmount() {
		return customerPayAmount;
	}
	public void setCustomerPayAmount(Double customerPayAmount) {
		this.customerPayAmount = customerPayAmount;
	}
	public Double getSettleRate() {
		return settleRate;
	}
	public void setSettleRate(Double settleRate) {
		this.settleRate = settleRate;
	}
	public Double getSettleAmount() {
		return settleAmount;
	}
	public void setSettleAmount(Double settleAmount) {
		this.settleAmount = settleAmount;
	}
	
	
}
