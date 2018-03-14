package cn.damei.entity.mobile.home;

import cn.damei.entity.mobile.home.CustomerBroadCastEntity;

import java.io.Serializable;

public class BroadCastPicTwoEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private Integer picId;
	private String picUrl;
	private CustomerBroadCastEntity broadcastEntity;
	public Integer getPicId() {
		return picId;
	}
	public void setPicId(Integer picId) {
		this.picId = picId;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public CustomerBroadCastEntity getBroadcastEntity() {
		return broadcastEntity;
	}
	public void setBroadcastEntity(CustomerBroadCastEntity broadcastEntity) {
		this.broadcastEntity = broadcastEntity;
	}
}
