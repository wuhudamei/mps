/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;

/**
 * 上传复尺Entity
 * @author wyb
 * @version 2016-10-20
 */
public class BizOrderChecksizePic extends DataEntity2<BizOrderChecksizePic> {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;//主键
	private Integer orderChecksizeId;//订单编号
	private String picUrl;//客户地址
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