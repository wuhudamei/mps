
package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;


public class BizOrderTaskpackagePaymentSummaryRel extends DataEntity2<BizOrderTaskpackagePaymentSummaryRel> {
	
	private static final long serialVersionUID = 1L;
	
	private Integer orderTaskpackagePaymentSummaryId;
	private Integer orderTaskpackagePaymentId;
	
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