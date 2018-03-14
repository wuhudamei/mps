package cn.damei.entity.mobile.Manager;

import cn.damei.common.persistence.DataEntity2;


public class PurchasePic extends DataEntity2<PurchasePic>{
	
	
	private static final long serialVersionUID = 1L;
	
	private Integer purchaseId;
	private String picUrl;
	
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