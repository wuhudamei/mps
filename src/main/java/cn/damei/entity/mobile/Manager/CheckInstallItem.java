package cn.damei.entity.mobile.Manager;

import java.util.List;

public class CheckInstallItem {
	private String orderId;
	private List<Integer> orderItemId;
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public List<Integer> getOrderItemId() {
		return orderItemId;
	}
	public void setOrderItemId(List<Integer> orderItemId) {
		this.orderItemId = orderItemId;
	}
	
}
