/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

/**
 * 主材可申请安装/复尺日期查询Entity
 * @author wyb
 */
public class OrderInstallPlanAdjustment extends DataEntity2<OrderInstallPlanAdjustment> {
	
	private static final long serialVersionUID = 1L;
	
	private Integer orderId;		// 订单id
	private Integer orderInstallItemId;		// 安装项计划id
	private String installItemName;		// 安装项计划名称
	private Date planIntoDate;		// 确认开工后可申请安装日期
	private Date allowApplyChecksizeDate;		// 确认开工后可申请复尺日期
	private String status;		// 状态 
	private Date newPlanApplyDate; //修改后的 计划申请日期
	
	private Integer storeId;	//门店
	private String storeName;  //门店名称
	private String projectMode;		//工程模式
	private String projectModeName;		//工程模式名称
	private Integer engineDepartId;  //区域id
	private String engineDepartName;  //区域名称
	private String orderNumber;  //订单编号
	private String customerName; //客户姓名
	private String customerPhone; //客户电话
	private Integer itemManagerId;	 //项目经理id
	private String itemManager;	 //项目经理名称
	private String itemManagerPhone;	 //项目经理名称电话
	private Date actualStartDate;  //实际开工日期
	
	private String communityName;	 //小区
	private String buildNumber;	 //楼
	private String buildUnit;	 //单元
	private String buildRoom;	 //室
	
	public OrderInstallPlanAdjustment() {
		super();
	}

	public OrderInstallPlanAdjustment(Integer id){
		super(id);
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getOrderInstallItemId() {
		return orderInstallItemId;
	}

	public void setOrderInstallItemId(Integer orderInstallItemId) {
		this.orderInstallItemId = orderInstallItemId;
	}

	public String getInstallItemName() {
		return installItemName;
	}

	public void setInstallItemName(String installItemName) {
		this.installItemName = installItemName;
	}

	public Date getPlanIntoDate() {
		return planIntoDate;
	}

	public void setPlanIntoDate(Date planIntoDate) {
		this.planIntoDate = planIntoDate;
	}

	public Date getAllowApplyChecksizeDate() {
		return allowApplyChecksizeDate;
	}

	public void setAllowApplyChecksizeDate(Date allowApplyChecksizeDate) {
		this.allowApplyChecksizeDate = allowApplyChecksizeDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}

	public String getProjectModeName() {
		return projectModeName;
	}

	public void setProjectModeName(String projectModeName) {
		this.projectModeName = projectModeName;
	}

	public Integer getEngineDepartId() {
		return engineDepartId;
	}

	public void setEngineDepartId(Integer engineDepartId) {
		this.engineDepartId = engineDepartId;
	}

	public String getEngineDepartName() {
		return engineDepartName;
	}

	public void setEngineDepartName(String engineDepartName) {
		this.engineDepartName = engineDepartName;
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

	public Integer getItemManagerId() {
		return itemManagerId;
	}

	public void setItemManagerId(Integer itemManagerId) {
		this.itemManagerId = itemManagerId;
	}

	public String getItemManager() {
		return itemManager;
	}

	public void setItemManager(String itemManager) {
		this.itemManager = itemManager;
	}

	public String getItemManagerPhone() {
		return itemManagerPhone;
	}

	public void setItemManagerPhone(String itemManagerPhone) {
		this.itemManagerPhone = itemManagerPhone;
	}

	public Date getActualStartDate() {
		return actualStartDate;
	}

	public void setActualStartDate(Date actualStartDate) {
		this.actualStartDate = actualStartDate;
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

	public Date getNewPlanApplyDate() {
		return newPlanApplyDate;
	}

	public void setNewPlanApplyDate(Date newPlanApplyDate) {
		this.newPlanApplyDate = newPlanApplyDate;
	}

	
	
}