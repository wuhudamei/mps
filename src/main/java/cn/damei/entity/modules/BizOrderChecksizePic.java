
package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;


public class BizOrderChecksizePic extends DataEntity2<BizOrderChecksizePic> {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer orderChecksizeId;
	private String picUrl;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getOrderChecksizeId() {
		return orderChecksizeId;
	}
	public void setOrderChecksizeId(Integer orderChecksizeId) {
		this.orderChecksizeId = orderChecksizeId;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	
	
}