package cn.damei.entity.mobile.Manager;

import cn.damei.common.persistence.DataEntity2;



public class BizEvalActivityOrderTaskpackRel extends DataEntity2<BizEvalActivityOrderTaskpackRel> {

	private static final long serialVersionUID = 1L;
	
	private Integer evalActivityId;
	private Integer orderTaskpackId;

	public Integer getEvalActivityId() {
		return evalActivityId;
	}

	public void setEvalActivityId(Integer evalActivityId) {
		this.evalActivityId = evalActivityId;
	}

	public Integer getOrderTaskpackId() {
		return orderTaskpackId;
	}

	public void setOrderTaskpackId(Integer orderTaskpackId) {
		this.orderTaskpackId = orderTaskpackId;
	}
}
