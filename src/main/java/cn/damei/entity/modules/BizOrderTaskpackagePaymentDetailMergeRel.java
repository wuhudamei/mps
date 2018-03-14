/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;

/**
 * 付款单明细合并关系Entity
 * @author qww
 * @version 2016-10-27
 */
public class BizOrderTaskpackagePaymentDetailMergeRel extends DataEntity2<BizOrderTaskpackagePaymentDetailMergeRel> {
	
	private static final long serialVersionUID = 1L;
	private Integer orderTaskpackagePaymentDetailMergeId;		// 订单任务包付款单明细合并id -- '
	private Integer orderTaskpackagePaymentDetailId;		// 订单任务包付款单明细id -- '
	
	public BizOrderTaskpackagePaymentDetailMergeRel() {
		super();
	}

	public BizOrderTaskpackagePaymentDetailMergeRel(Integer id){
		super(id);
	}

	public Integer getOrderTaskpackagePaymentDetailMergeId() {
		return orderTaskpackagePaymentDetailMergeId;
	}

	public void setOrderTaskpackagePaymentDetailMergeId(Integer orderTaskpackagePaymentDetailMergeId) {
		this.orderTaskpackagePaymentDetailMergeId = orderTaskpackagePaymentDetailMergeId;
	}
	
	public Integer getOrderTaskpackagePaymentDetailId() {
		return orderTaskpackagePaymentDetailId;
	}

	public void setOrderTaskpackagePaymentDetailId(Integer orderTaskpackagePaymentDetailId) {
		this.orderTaskpackagePaymentDetailId = orderTaskpackagePaymentDetailId;
	}
	
}