/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.mobile.Worker;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

public class UrgeEvaluation extends DataEntity2<UrgeEvaluation> {
	
	private static final long serialVersionUID = 1L;
	
	
	private String itemManager;		// 项目经理
	private int itemManagerId;		// 项目经理id
	private String contractNumber;		// 合同编号
	private String customerName;		// 客户姓名
	private String customerPhone;		// 客户电话
	private String orderStatusNumber;		// 订单状态码  创建订单成功默认状态码 105
	private String orderStatusDescription;		// 订单状态码详情   默认详情 确认订单   状态码105
	
	
	private String communityName;		// 小区名称
	private String buildNumber;		// 几号楼
	private String buildUnit;		// 几单元
	private String buildRoom;		// 哪一室
	private String buildType;		// 房屋类型
	private String houseType;		//房屋户型
	private String house;		//房屋
	private String contractArea;		// 合同面积
	
	private Date contractStartDate;		// 合同开工日期
	private Date contractEndDate;		// 合同竣工日期
	private String contractTime;		// 合同工期
	private Date actualStartDate;		//实际开工日期
	private Date actualEndDate; 		//实际竣工日期
	private String orderBy;				//排序
	
	
	private String projectMode; //工程模式
	private Integer storeId; //门店
	
	private String orderInspectorName;		// 质检员
	private String orderInspectorPhone;		// 质检员电话
	private Integer orderInspectorId;		// 质检员id
	private String packageName; //任务包名称
	private String orderId;
	private String workerId;
	
	
	public String getWorkerId() {
		return workerId;
	}
	public void setWorkerId(String workerId) {
		this.workerId = workerId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getOrderInspectorName() {
		return orderInspectorName;
	}
	public void setOrderInspectorName(String orderInspectorName) {
		this.orderInspectorName = orderInspectorName;
	}
	public String getOrderInspectorPhone() {
		return orderInspectorPhone;
	}
	public void setOrderInspectorPhone(String orderInspectorPhone) {
		this.orderInspectorPhone = orderInspectorPhone;
	}
	public Integer getOrderInspectorId() {
		return orderInspectorId;
	}
	public void setOrderInspectorId(Integer orderInspectorId) {
		this.orderInspectorId = orderInspectorId;
	}
	public String getProjectMode() {
		return projectMode;
	}
	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public String getItemManager() {
		return itemManager;
	}
	public void setItemManager(String itemManager) {
		this.itemManager = itemManager;
	}
	public int getItemManagerId() {
		return itemManagerId;
	}
	public void setItemManagerId(int itemManagerId) {
		this.itemManagerId = itemManagerId;
	}
	public String getContractNumber() {
		return contractNumber;
	}
	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
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
	public String getOrderStatusNumber() {
		return orderStatusNumber;
	}
	public void setOrderStatusNumber(String orderStatusNumber) {
		this.orderStatusNumber = orderStatusNumber;
	}
	public String getOrderStatusDescription() {
		return orderStatusDescription;
	}
	public void setOrderStatusDescription(String orderStatusDescription) {
		this.orderStatusDescription = orderStatusDescription;
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
	public String getBuildType() {
		return buildType;
	}
	public void setBuildType(String buildType) {
		this.buildType = buildType;
	}
	public String getHouseType() {
		return houseType;
	}
	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}
	public String getHouse() {
		return house;
	}
	public void setHouse(String house) {
		this.house = house;
	}
	public String getContractArea() {
		return contractArea;
	}
	public void setContractArea(String contractArea) {
		this.contractArea = contractArea;
	}
	public Date getContractStartDate() {
		return contractStartDate;
	}
	public void setContractStartDate(Date contractStartDate) {
		this.contractStartDate = contractStartDate;
	}
	public Date getContractEndDate() {
		return contractEndDate;
	}
	public void setContractEndDate(Date contractEndDate) {
		this.contractEndDate = contractEndDate;
	}
	public String getContractTime() {
		return contractTime;
	}
	public void setContractTime(String contractTime) {
		this.contractTime = contractTime;
	}
	public Date getActualStartDate() {
		return actualStartDate;
	}
	public void setActualStartDate(Date actualStartDate) {
		this.actualStartDate = actualStartDate;
	}
	public Date getActualEndDate() {
		return actualEndDate;
	}
	public void setActualEndDate(Date actualEndDate) {
		this.actualEndDate = actualEndDate;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
		
}