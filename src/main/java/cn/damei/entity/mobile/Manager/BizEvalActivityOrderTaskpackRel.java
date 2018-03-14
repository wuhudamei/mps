package cn.damei.entity.mobile.Manager;

import cn.damei.common.persistence.DataEntity2;

/**
 * @author 梅浩 meihao@zzhyun.cn:
 * @version 创建时间：2016年9月28日 上午11:18:04 辅料Vo
 */

public class BizEvalActivityOrderTaskpackRel extends DataEntity2<BizEvalActivityOrderTaskpackRel> {

	private static final long serialVersionUID = 1L;
	
	private Integer evalActivityId; // 评价活动id
	private Integer orderTaskpackId; // 订单任务包id

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
