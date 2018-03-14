package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;

public class BizPurchasePicture extends DataEntity2<BizPurchasePicture>{

	/**
	 * @author wang
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer purchaseId;
	private String picUrl;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(Integer purchaseId) {
		this.purchaseId = purchaseId;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	
}
