package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;

/**
 * 订单的自采材料
 * @author hyh
 *
 */
public class BizMaterialSelfbuyVo extends DataEntity2<BizMaterialSelfbuyVo>{
	private static final long serialVersionUID = 1L;

	private String itemManager; //项目经理
	private String itemPhone;  //项目经理手机号
	private String customerName;		// 客户姓名
	private String customerPhone;		// 客户电话
	private String communityName;		// 小区名称
	private String buildNumber;		// 几号楼
	private String buildUnit;		// 几单元
	private String buildRoom;		// 哪一室 
	private String materialName;  //自采材料名称 
	private Double customerPayAmount;//客户交费金额
	private Double settleRate;               //结算比例
	private Double settleAmount;  //实际结算金额
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
