package cn.damei.entity.mobile.Manager;

import cn.damei.common.persistence.DataEntity2;

/**
 * biz_order_disclose_pic 订单交底
 * 
 * @author llp 2016/10/18
 */
@SuppressWarnings("serial")
public class OrderDisclosePic extends DataEntity2<OrderDisclosePic> {
	private Integer id;
	/**
	 * 订单ID
	 */
	private Integer orderDiscloseId;

	/**
	 * 交底图片类型
	 */
	private String orderDisclosePicType;

	/**
	 * 交底图片url
	 */
	private String picUrl;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrderDiscloseId() {
		return orderDiscloseId;
	}

	public void setOrderDiscloseId(Integer orderDiscloseId) {
		this.orderDiscloseId = orderDiscloseId;
	}

	public String getOrderDisclosePicType() {
		return orderDisclosePicType;
	}

	public void setOrderDisclosePicType(String orderDisclosePicType) {
		this.orderDisclosePicType = orderDisclosePicType;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

}
