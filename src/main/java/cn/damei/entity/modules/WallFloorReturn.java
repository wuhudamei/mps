/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import cn.damei.common.persistence.DataEntity;

/**
 * 墙地砖退货Entity
 * 
 * @author 张同维
 * @version 2017-09-26
 */
public class WallFloorReturn extends DataEntity<WallFloorReturn> {

	private static final long serialVersionUID = 1L;
	private String sortId; // 序号
	private String orderNumber; // 订单编号
	private String customerName; // 客户姓名
	private String itemManager; // 项目经理
	private String orderId; // 订单id
	private String squareReturn; // 退货面积
	private Date returnDatetime; // 退货日期时间
	private String returnDatetimeString; // 退货日期时间

	public WallFloorReturn() {
		super();
	}

	public WallFloorReturn(String id) {
		super(id);
	}

	@Length(min = 0, max = 11, message = "订单id长度必须介于 0 和 11 之间")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getSquareReturn() {
		return squareReturn;
	}

	public String getSortId() {
		return sortId;
	}

	public String getReturnDatetimeString() {
		return returnDatetimeString;
	}

	public void setReturnDatetimeString(String returnDatetimeString) {
		this.returnDatetimeString = returnDatetimeString;
	}

	public void setSortId(String sortId) {
		this.sortId = sortId;
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

	public String getItemManager() {
		return itemManager;
	}

	public void setItemManager(String itemManager) {
		this.itemManager = itemManager;
	}

	public void setSquareReturn(String squareReturn) {
		this.squareReturn = squareReturn;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getReturnDatetime() {
		return returnDatetime;
	}

	public void setReturnDatetime(Date returnDatetime) {
		this.returnDatetime = returnDatetime;
	}

}