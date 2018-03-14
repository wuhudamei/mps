
package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;


public class BizOrderTaskpackagePaymentDetailMergeRel extends DataEntity2<BizOrderTaskpackagePaymentDetailMergeRel> {
	
	private static final long serialVersionUID = 1L;
	private Integer orderTaskpackagePaymentDetailMergeId;
	private Integer orderTaskpackagePaymentDetailId;
	
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