
package cn.damei.entity.modules;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import cn.damei.common.persistence.DataEntity;


public class WallFloorReturn extends DataEntity<WallFloorReturn> {

	private static final long serialVersionUID = 1L;
	private String sortId;
	private String orderNumber;
	private String customerName;
	private String itemManager;
	private String orderId;
	private String squareReturn;
	private Date returnDatetime;
	private String returnDatetimeString;

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