package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;


@SuppressWarnings("serial")
public class BizOrderDisclosePic extends DataEntity2<BizOrderDisclosePic> {

	private Integer id;


	private Integer orderDiscloseId;


	private String orderDisclosePicType;


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
