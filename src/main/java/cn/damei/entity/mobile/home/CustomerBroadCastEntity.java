package cn.damei.entity.mobile.home;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CustomerBroadCastEntity  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String broadcastName;
	public CustomerOrderVo getOrderEntity() {
		return orderEntity;
	}
	public void setOrderEntity(CustomerOrderVo orderEntity) {
		this.orderEntity = orderEntity;
	}
	public String getBroadcastName() {
		return broadcastName;
	}
	public void setBroadcastName(String broadcastName) {
		this.broadcastName = broadcastName;
	}
	public Integer getBroadcastId() {
		return broadcastId;
	}
	public void setBroadcastId(Integer broadcastId) {
		this.broadcastId = broadcastId;
	}
	public Date getBroadcastTime() {
		return broadcastTime;
	}
	public void setBroadcastTime(Date broadcastTime) {
		this.broadcastTime = broadcastTime;
	}
	
	private CustomerOrderVo    orderEntity;
	private Integer broadcastId;
	private Date broadcastTime;
	private List<BroadCastPicEntity>  picList;
	private String readStatus;


	public String getReadStatus() {
		return readStatus;
	}
	public void setReadStatus(String readStatus) {
		this.readStatus = readStatus;
	}
	public List<BroadCastPicEntity> getPicList() {
		return picList;
	}
	public void setPicList(List<BroadCastPicEntity> picList) {
		this.picList = picList;
	}
}
