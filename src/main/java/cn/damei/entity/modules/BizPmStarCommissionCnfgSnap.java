/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

/**
 * 项目经理星级提成快照Entity
 * @author 汪文文
 * @version 2016-12-28
 */
public class BizPmStarCommissionCnfgSnap extends DataEntity2<BizPmStarCommissionCnfgSnap> {
	
	private static final long serialVersionUID = 1L;
	private Integer storeId;		// 门店id -- '
	private Integer orderId;		// 订单id -- '
	private Integer pmEmployeeId;		// 项目经理员工id -- '
	private String isOldNew;		// 新老房 -- '0.老房；1.新房
	private Integer starLever;		// 星级 -- '
	private Double commissionAmount;		// 提成金额 -- '
	private Double commissionRateMidway;		// 中期提成比例 -- '
	private Double commissionRateComplete;		// 竣工提成比例 -- '
	
	private String orderNumber;     // 订单编号
	private String customerName;		// 客户姓名
	private String customerPhone;		// 客户电话
	private String communityName;		// 小区名称
	private String buildNumber;		// 几号楼
	private String buildUnit;		// 几单元
	private String buildRoom;		// 哪一室
	private String itemManager;		// 项目经理
	private String itemManagerPhone;//项目经理手机号
	private Date  sendOrderDate;//派单时间
	
	
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