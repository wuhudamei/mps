/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity;

/**
 * 项目约检情况查询Entity
 * @author wyb
 * @version 2016-10-31
 */
public class CheckDetails extends DataEntity<CheckDetails> {
	
	private static final long serialVersionUID = 1L;
	
	private String storeId; //门店
	private Integer orderId;//订单id
	private String orderNumber;		// 订单编号

	private String communityName;		// 小区名称
	private String buildNumber;		// 几号楼
	private String buildUnit;		// 几单元
	private String buildRoom;		// 哪一室
	private String customerName;		// 客户姓名
	private String itemManager;		// 项目经理
	
	private Date actualStartDate; //实际开工日期
	private Date beginActualStartDate; // 开始   实际开工日期
	private Date endActualStartDate; // 结束   实际开工日期
	
	private String checkNodeName;	//最新已约检节点名称
	private Integer allCount;	//总节点数
	private Integer allCountTwo;	//总节点数没有模式
	private Integer nowCount;	//已约节点数
	private String projectMode;		//工程模式   1-产业模式；2-传统模式
	
	private Integer engineDepartId;	//区域id
	private String engineDepartName;	//区域名称
	
	private Integer checkNodeNameNewId;	//最新约检验收节点id
	private String checkNodeNameNew;	//最新约检验收节点名称
	
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getAllCountTwo() {
		return allCountTwo;
	}
	public void setAllCountTwo(Integer allCountTwo) {
		this.allCountTwo = allCountTwo;
	}
	public String getProjectMode() {
		return projectMode;
	}
	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
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
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getItemManager() {
		return itemManager;
	}
	public void setItemManager(String itemManager) {
		this.itemManager = itemManager;
	}
	public Date getActualStartDate() {
		return actualStartDate;
	}
	public void setActualStartDate(Date actualStartDate) {
		this.actualStartDate = actualStartDate;
	}
	public Date getBeginActualStartDate() {
		return beginActualStartDate;
	}
	public void setBeginActualStartDate(Date beginActualStartDate) {
		this.beginActualStartDate = beginActualStartDate;
	}
	public Date getEndActualStartDate() {
		return endActualStartDate;
	}
	public void setEndActualStartDate(Date endActualStartDate) {
		this.endActualStartDate = endActualStartDate;
	}
	public String getCheckNodeName() {
		return checkNodeName;
	}
	public void setCheckNodeName(String checkNodeName) {
		this.checkNodeName = checkNodeName;
	}
	public Integer getAllCount() {
		return allCount;
	}
	public void setAllCount(Integer allCount) {
		this.allCount = allCount;
	}
	public Integer getNowCount() {
		return nowCount;
	}
	public void setNowCount(Integer nowCount) {
		this.nowCount = nowCount;
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
	public Integer getCheckNodeNameNewId() {
		return checkNodeNameNewId;
	}
	public void setCheckNodeNameNewId(Integer checkNodeNameNewId) {
		this.checkNodeNameNewId = checkNodeNameNewId;
	}
	public String getCheckNodeNameNew() {
		return checkNodeNameNew;
	}
	public void setCheckNodeNameNew(String checkNodeNameNew) {
		this.checkNodeNameNew = checkNodeNameNew;
	}
	
	
	
	
	
}