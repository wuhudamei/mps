/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import cn.damei.common.persistence.DataEntity;

public class OrderManagerChange extends DataEntity<OrderManagerChange> {
	
	private static final long serialVersionUID = 1L;
	//门店
	private Integer storeId;
	//订单编号
	private String orderNumber;
	//客户姓名
	private String customerName;
	//客户手机号
	private String customerPhone;
	//更换时间
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date changeDate;
	//更换时间段
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endChangeDate;
	//原项目经理
	private String oldManagerName;
	//原项目经理手机号
	private String oldManagerPhone;
	//新项目经理
	private String newManagerName;
	//新项目经理手机号
	private String newManagerPhone;
	//操作人
	private String createName;
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

	public Date getChangeDate() {
		return changeDate;
	}
	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}
	public Date getEndChangeDate() {
		return endChangeDate;
	}
	public void setEndChangeDate(Date endChangeDate) {
		this.endChangeDate = endChangeDate;
	}
	public String getOldManagerName() {
		return oldManagerName;
	}
	public void setOldManagerName(String oldManagerName) {
		this.oldManagerName = oldManagerName;
	}
	public String getOldManagerPhone() {
		return oldManagerPhone;
	}
	public void setOldManagerPhone(String oldManagerPhone) {
		this.oldManagerPhone = oldManagerPhone;
	}
	public String getNewManagerName() {
		return newManagerName;
	}
	public void setNewManagerName(String newManagerName) {
		this.newManagerName = newManagerName;
	}
	public String getNewManagerPhone() {
		return newManagerPhone;
	}
	public void setNewManagerPhone(String newManagerPhone) {
		this.newManagerPhone = newManagerPhone;
	}
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	
	
}