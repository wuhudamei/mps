/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

public class OrderFullAction extends DataEntity2<OrderFullAction> {

	private static final long serialVersionUID = 1L;
	//订单录入系统日期
	private Date enteringDate;
	//订单录入人
	private String keyboarder;
	//接单日期
	private Date receivingDate;
	//生成任务包日期
	private Date  packageDate;
	//生成任务包人
	private String packageBy;
	//分配项目经理时间
	private Date assignedManagerDate;
	//分配项目经理人
	private String assignedManagerBy; 
	//分配质检员时间
	private Date assignedInspectorDate;
	//分配质检员人
	private String assignedInspectorBy;
	
	//分配时间
	private Date assignedDate;
	//分配人
	private String assignedBy;
	//分配类型
	private String distributeType;
	
	//门店
	private Integer storeId;
	//订单编号
	private String orderNumber;
	
	
	public String getDistributeType() {
		return distributeType;
	}
	public void setDistributeType(String distributeType) {
		this.distributeType = distributeType;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public Date getEnteringDate() {
		return enteringDate;
	}
	public void setEnteringDate(Date enteringDate) {
		this.enteringDate = enteringDate;
	}
	public String getKeyboarder() {
		return keyboarder;
	}
	public void setKeyboarder(String keyboarder) {
		this.keyboarder = keyboarder;
	}
	public Date getReceivingDate() {
		return receivingDate;
	}
	public void setReceivingDate(Date receivingDate) {
		this.receivingDate = receivingDate;
	}
	public Date getPackageDate() {
		return packageDate;
	}
	public void setPackageDate(Date packageDate) {
		this.packageDate = packageDate;
	}
	public String getPackageBy() {
		return packageBy;
	}
	public void setPackageBy(String packageBy) {
		this.packageBy = packageBy;
	}
	public Date getAssignedManagerDate() {
		return assignedManagerDate;
	}
	public void setAssignedManagerDate(Date assignedManagerDate) {
		this.assignedManagerDate = assignedManagerDate;
	}
	public String getAssignedManagerBy() {
		return assignedManagerBy;
	}
	public void setAssignedManagerBy(String assignedManagerBy) {
		this.assignedManagerBy = assignedManagerBy;
	}

	public Date getAssignedInspectorDate() {
		return assignedInspectorDate;
	}
	public void setAssignedInspectorDate(Date assignedInspectorDate) {
		this.assignedInspectorDate = assignedInspectorDate;
	}
	public String getAssignedInspectorBy() {
		return assignedInspectorBy;
	}
	public void setAssignedInspectorBy(String assignedInspectorBy) {
		this.assignedInspectorBy = assignedInspectorBy;
	}
	public Date getAssignedDate() {
		return assignedDate;
	}
	public void setAssignedDate(Date assignedDate) {
		this.assignedDate = assignedDate;
	}
	public String getAssignedBy() {
		return assignedBy;
	}
	public void setAssignedBy(String assignedBy) {
		this.assignedBy = assignedBy;
	}
	
}