package cn.damei.entity.mobile.Manager;

import cn.damei.common.persistence.DataEntity2;

public class ChecksizePic extends DataEntity2<ChecksizePic>{

	
	private static final long serialVersionUID = 1L;
	

	private int orderChecksizeId;     //订单复尺id
	private String picUrl;		// 复尺图片路径
	public int getOrderChecksizeId() {
		return orderChecksizeId;
	}
	public void setOrderChecksizeId(int orderChecksizeId) {
		this.orderChecksizeId = orderChecksizeId;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	
	
}
