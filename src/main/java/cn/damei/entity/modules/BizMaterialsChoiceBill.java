/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity2;

/**
 * 选材单表Entity
 * 
 * @author wyb
 * @version 2017-06-13
 */
public class BizMaterialsChoiceBill extends DataEntity2<BizMaterialsChoiceBill> {

	private static final long serialVersionUID = 1L;
	private Integer orderId; // 订单id
	private String orderNumber; // 订单编号
	private Double materialsChoiceTotalAmount; // 选材总价

	private Integer storeId; // 门店id
	private String engineDepartId; // 区域
	private String projectMode; // 区域
	private String counts; // 统计这个区有多少个未处理的订单
	private String isFalg; // 是否显示预算员标志
	private String customerName; // 客户姓名
	private String isdealedMain; // 是否已经处理主材
	private String isdealedWallFloor; // 是否已处理墙地砖
	private String customerPhone; // 客户手机号
	private String communityName; // 小区
	private String buildNumber; // 楼
	private String buildUnit; // 单元
	private String buildRoom; // 室
	private String orderDesignerName; // 订单设计师
	private String orderDesignerPhone; // 订单设计师电话
	private String itemManager; // 项目经理姓名
	private String storeName; // 门店名称
	private String contractNumber; // 合同编号 /区域名称
	private Integer changeBillCount; // 该订单的变更单数量
	private Double wallFloorTileSquareBudget; // 墙地砖预算面积

	private String status; // 页面前地砖是否导入状态标示

	public BizMaterialsChoiceBill() {
		super();
	}

	public BizMaterialsChoiceBill(Integer id) {
		super(id);
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	@Length(min = 0, max = 100, message = "订单编号长度必须介于 0 和 100 之间")
	public String getOrderNumber() {
		return orderNumber;
	}

	public String getStatus() {
		return status;
	}

	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Double getMaterialsChoiceTotalAmount() {
		return materialsChoiceTotalAmount;
	}

	public String getCounts() {
		return counts;
	}

	public void setCounts(String counts) {
		this.counts = counts;
	}

	public String getIsFalg() {
		return isFalg;
	}

	public void setIsFalg(String isFalg) {
		this.isFalg = isFalg;
	}

	public void setMaterialsChoiceTotalAmount(Double materialsChoiceTotalAmount) {
		this.materialsChoiceTotalAmount = materialsChoiceTotalAmount;
	}

	public String getEngineDepartId() {
		return engineDepartId;
	}

	public void setEngineDepartId(String engineDepartId) {
		this.engineDepartId = engineDepartId;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getIsdealedMain() {
		return isdealedMain;
	}

	public void setIsdealedMain(String isdealedMain) {
		this.isdealedMain = isdealedMain;
	}

	public String getIsdealedWallFloor() {
		return isdealedWallFloor;
	}

	public void setIsdealedWallFloor(String isdealedWallFloor) {
		this.isdealedWallFloor = isdealedWallFloor;
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

	public String getOrderDesignerName() {
		return orderDesignerName;
	}

	public void setOrderDesignerName(String orderDesignerName) {
		this.orderDesignerName = orderDesignerName;
	}

	public String getOrderDesignerPhone() {
		return orderDesignerPhone;
	}

	public void setOrderDesignerPhone(String orderDesignerPhone) {
		this.orderDesignerPhone = orderDesignerPhone;
	}

	public String getItemManager() {
		return itemManager;
	}

	public void setItemManager(String itemManager) {
		this.itemManager = itemManager;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	public Integer getChangeBillCount() {
		return changeBillCount;
	}

	public void setChangeBillCount(Integer changeBillCount) {
		this.changeBillCount = changeBillCount;
	}

	public Double getWallFloorTileSquareBudget() {
		return wallFloorTileSquareBudget;
	}

	public void setWallFloorTileSquareBudget(Double wallFloorTileSquareBudget) {
		this.wallFloorTileSquareBudget = wallFloorTileSquareBudget;
	}

}