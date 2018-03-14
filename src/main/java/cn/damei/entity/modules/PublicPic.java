package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;

public class PublicPic extends DataEntity2<PublicPic> {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;
	private Integer purchaseId; // 公共ID
	private String picUrl; // 图片路径
	private String picType; // 图片类型

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

	public String getPicType() {
		return picType;
	}

	public void setPicType(String picType) {
		this.picType = picType;
	}

}
