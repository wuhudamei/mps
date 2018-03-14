/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;

/**
 * 付款单批次Entity
 * @author 汪文文
 * @version 2016-10-26
 */
public class BizOrderTaskpackagePaymentSummaryRel extends DataEntity2<BizOrderTaskpackagePaymentSummaryRel> {
	
	private static final long serialVersionUID = 1L;
	
	private Integer orderTaskpackagePaymentSummaryId; // 订单任务包付款汇总单id -- '
	private Integer orderTaskpackagePaymentId; // 订单任务包付款单id -- '
	
	public BizOrderTaskpackagePaymentSummaryRel() {
		super();
	}

	public BizOrderTaskpackagePaymentSummaryRel(Integer id){
		super(id);
	}

	public Integer getOrderTaskpackagePaymentSummaryId() {
		return orderTaskpackagePaymentSummaryId;
	}

	public void setOrderTaskpackagePaymentSummaryId(Integer orderTaskpackagePaymentSummaryId) {
		this.orderTaskpackagePaymentSummaryId = orderTaskpackagePaymentSummaryId;
	}

	public Integer getOrderTaskpackagePaymentId() {
		return orderTaskpackagePaymentId;
	}

	public void setOrderTaskpackagePaymentId(Integer orderTaskpackagePaymentId) {
		this.orderTaskpackagePaymentId = orderTaskpackagePaymentId;
	}
}