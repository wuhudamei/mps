package cn.damei.entity.mobile.Worker;

import cn.damei.common.persistence.DataEntity;

/**
 * @author 梅浩 meihao@zzhyun.cn:
 * @version 创建时间：2016年9月20日 下午5:30:31 类说明
 */

public class TaskPackagePic extends DataEntity<TaskPackagePic> {

	private static final long serialVersionUID = 1L;
	
	private Integer orderTaskpackageId;// 订单任务包的id
	private String picturePath;// 图片路径
	
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
