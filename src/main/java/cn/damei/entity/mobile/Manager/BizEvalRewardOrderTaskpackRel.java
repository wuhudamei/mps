package cn.damei.entity.mobile.Manager;

import cn.damei.common.persistence.DataEntity2;



public class BizEvalRewardOrderTaskpackRel extends DataEntity2<BizEvalRewardOrderTaskpackRel> {

	private static final long serialVersionUID = 1L;
	
	private Integer evalRewardCfgId;
	private Integer orderTaskpackId;

	public Integer getEvalRewardCfgId() {
		return evalRewardCfgId;
	}

	public void setEvalRewardCfgId(Integer evalRewardCfgId) {
		this.evalRewardCfgId = evalRewardCfgId;
	}

	public Integer getOrderTaskpackId() {
		return orderTaskpackId;
	}

	public void setOrderTaskpackId(Integer orderTaskpackId) {
		this.orderTaskpackId = orderTaskpackId;
	}
}
