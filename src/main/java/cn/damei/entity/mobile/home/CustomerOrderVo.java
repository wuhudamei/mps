package cn.damei.entity.mobile.home;

import cn.damei.entity.mobile.home.CustomerBroadCastEntity;

import java.io.Serializable;
import java.util.List;

public class CustomerOrderVo  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
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
	
	public List<CustomerBroadCastEntity> getBroadcastList() {
		return broadcastList;
	}
	public void setBroadcastList(List<CustomerBroadCastEntity> broadcastList) {
		this.broadcastList = broadcastList;
	}
	private Integer orderId;
	private String customerPhone;
	private String customerName;
	private String communityName;
	private String buildNumber;
	private String buildUnit;
	private String buildRoom;
	private Integer count;

	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	private List<CustomerBroadCastEntity> broadcastList;
	
}
