package cn.damei.entity.mobile.Manager;

import cn.damei.common.persistence.DataEntity2;

/**
 * @author 梅浩 meihao@zzhyun.cn:
 * @version 创建时间：2016年9月28日 上午11:18:04 辅料Vo
 */

public class BizEvalRewardOrderTaskpackRel extends DataEntity2<BizEvalRewardOrderTaskpackRel> {

	private static final long serialVersionUID = 1L;
	
	private Integer evalRewardCfgId; // 评价奖励设置id
	private Integer orderTaskpackId; // 订单任务包id

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
