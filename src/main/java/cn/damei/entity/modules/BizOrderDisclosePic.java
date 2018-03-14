package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;

/**
 * 订单交底图片
 * 
 * @author llp
 */
@SuppressWarnings("serial")
public class BizOrderDisclosePic extends DataEntity2<BizOrderDisclosePic> {

	private Integer id;// 主键

	/**
	 * 订单交底id
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
