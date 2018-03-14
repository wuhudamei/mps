package cn.damei.entity.mobile.Manager;

import cn.damei.common.persistence.DataEntity2;



public class OrderTaskpackagePic extends DataEntity2<OrderTaskpackagePic>{

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer orderTaskpackageId;
	private String picturePath;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getOrderTaskpackageId() {
		return orderTaskpackageId;
	}
	public void setOrderTaskpackageId(Integer orderTaskpackageId) {
		this.orderTaskpackageId = orderTaskpackageId;
	}
	public String getPicturePath() {
		return picturePath;
	}
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}
}
