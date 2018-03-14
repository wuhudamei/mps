/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

/**
 * 质检报告Entity
 * @author wyb
 * @version 2016-10-31
 */
public class BizOrderQcBill extends DataEntity2<BizOrderQcBill> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer orderId;//订单id
	private String orderNumber;		// 订单编号
	private String customerName;		// 客户姓名
	private String communityName;		// 小区名称
	private String buildNumber;		// 几号楼
	private String buildUnit;		// 几单元
	private String buildRoom;		// 哪一室
	private String orderStatusNumber;		// 订单状态码  创建订单成功默认状态码 105
	private String orderStatusDescription;		// 订单状态码详情   默认详情 确认订单   状态码105
	private String orderInspector;		// 订单质检员
	private Integer orderInspectorId; //订单质检员id
	private String itemManager;		// 项目经理
	private Integer itemManagerId;	//项目经理id
	private String storeId;		// 门店id
	private String cusManager;		// 客户经理
	private Date actualStartDate; //实际开工日期
	private String isNullActualStartDate;	//实际开工日期是否为空
	
	private Integer signCount;	//签到次数
	private Integer count;	//质检报告数量
	private Integer checkCount;	//约检报告数量
	private Integer selectCheckCount;	//抽检报告数量
	private Integer recheckCount;	//复检报告数量
	private Integer recheckTimes;	//复检次数
	private String projectMode;		//工程模式   1-产业模式；2-传统模式
	
	
	public String getProjectMode() {
		return projectMode;
	}
	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
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
	public String getOrderInspector() {
		return orderInspector;
	}
	public void setOrderInspector(String orderInspector) {
		this.orderInspector = orderInspector;
	}
	public Integer getOrderInspectorId() {
		return orderInspectorId;
	}
	public void setOrderInspectorId(Integer orderInspectorId) {
		this.orderInspectorId = orderInspectorId;
	}
	public String getItemManager() {
		return itemManager;
	}
	public void setItemManager(String itemManager) {
		this.itemManager = itemManager;
	}
	public Integer getItemManagerId() {
		return itemManagerId;
	}
	public void setItemManagerId(Integer itemManagerId) {
		this.itemManagerId = itemManagerId;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getCusManager() {
		return cusManager;
	}
	public void setCusManager(String cusManager) {
		this.cusManager = cusManager;
	}
	public Date getActualStartDate() {
		return actualStartDate;
	}
	public void setActualStartDate(Date actualStartDate) {
		this.actualStartDate = actualStartDate;
	}
	public Integer getSignCount() {
		return signCount;
	}
	public void setSignCount(Integer signCount) {
		this.signCount = signCount;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getCheckCount() {
		return checkCount;
	}
	public void setCheckCount(Integer checkCount) {
		this.checkCount = checkCount;
	}
	public Integer getSelectCheckCount() {
		return selectCheckCount;
	}
	public void setSelectCheckCount(Integer selectCheckCount) {
		this.selectCheckCount = selectCheckCount;
	}
	public Integer getRecheckCount() {
		return recheckCount;
	}
	public void setRecheckCount(Integer recheckCount) {
		this.recheckCount = recheckCount;
	}
	public Integer getRecheckTimes() {
		return recheckTimes;
	}
	public void setRecheckTimes(Integer recheckTimes) {
		this.recheckTimes = recheckTimes;
	}
	public String getIsNullActualStartDate() {
		return isNullActualStartDate;
	}
	public void setIsNullActualStartDate(String isNullActualStartDate) {
		this.isNullActualStartDate = isNullActualStartDate;
	}
	
	

	
	
	
		
}