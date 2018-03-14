/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;


/**
 * 工程部主材工期统计表--厂家复尺
 */
public class BizOrderCheckSize extends DataEntity2<BizOrderCheckSize> {
	
	private static final long serialVersionUID = 1L;
	private Integer orderId;		// 订单id
	private String checksizeType;		// 厂家复尺类型
	private Date checksizeDate;		// 最早的厂家复尺时间
	
	

	public BizOrderCheckSize() {
		super();
	}

	public BizOrderCheckSize(Integer id){
		super(id);
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getChecksizeType() {
		return checksizeType;
	}

	public void setChecksizeType(String checksizeType) {
		this.checksizeType = checksizeType;
	}

	public Date getChecksizeDate() {
		return checksizeDate;
	}

	public void setChecksizeDate(Date checksizeDate) {
		this.checksizeDate = checksizeDate;
	}

	

	



	
	
}