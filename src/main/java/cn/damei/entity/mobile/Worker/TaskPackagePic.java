package cn.damei.entity.mobile.Worker;

import cn.damei.common.persistence.DataEntity;



public class TaskPackagePic extends DataEntity<TaskPackagePic> {

	private static final long serialVersionUID = 1L;
	
	private Integer orderTaskpackageId;
	private String picturePath;
	
	public Integer getorderTaskpackageId() {
		return orderTaskpackageId;
	}
	public void setorderTaskpackageId(Integer orderTaskpackageId) {
		this.orderTaskpackageId = orderTaskpackageId;
	}
	public String getPicturePath() {
		return picturePath;
	}
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

	
}
