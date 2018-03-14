/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;

import java.util.*;

/**
 * 工程进度大看板Entity
 * @author wyb
 */
public class BizProjectProgressSummaryData extends DataEntity2<BizProjectProgressSummaryData> {

	private static final long serialVersionUID = 1L;

	private Integer orderId; // 订单id
	private String orderNumber; //订单编号
	private Integer storeId; // 门店id
	private String projectMode; //工程模式
	private Integer engineDepartId; // 区域id
	private String customerName; // 客户姓名
	private String itemManager; // 项目经理
	private String houseIsNew; // 新房老房  1为新房  0为老房  默认老房
	private Date beginActualStartDate; // 实际开工日期 开始
	private Date endActualStartDate; // 实际竣工日期 结束
	private String isScrap; //是否作废
	private String updateStatus;//更新状态
	private Map<String,Object> map = new HashMap<String,Object>();


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

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}

	public Integer getEngineDepartId() {
		return engineDepartId;
	}

	public void setEngineDepartId(Integer engineDepartId) {
		this.engineDepartId = engineDepartId;
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

	public String getHouseIsNew() {
		return houseIsNew;
	}

	public void setHouseIsNew(String houseIsNew) {
		this.houseIsNew = houseIsNew;
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

	public String getIsScrap() {
		return isScrap;
	}

	public void setIsScrap(String isScrap) {
		this.isScrap = isScrap;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public String getUpdateStatus() {
		return updateStatus;
	}

	public void setUpdateStatus(String updateStatus) {
		this.updateStatus = updateStatus;
	}
}