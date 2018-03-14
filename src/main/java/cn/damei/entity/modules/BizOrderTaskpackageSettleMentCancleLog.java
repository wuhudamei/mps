package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;

/**
 * 订单任务包结算单撤回日志实体类
 * 
 * @author hyh
 *
 */
public class BizOrderTaskpackageSettleMentCancleLog extends DataEntity2<BizOrderTaskpackageSettleMentCancleLog> {

	private static final long serialVersionUID = 1L;

	private String operateType;

	private Integer bizOrderTaskpackageSettlemenId;

	private String operateRemarks;

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public Integer getBizOrderTaskpackageSettlemenId() {
		return bizOrderTaskpackageSettlemenId;
	}

	public void setBizOrderTaskpackageSettlemenId(Integer bizOrderTaskpackageSettlemenId) {
		this.bizOrderTaskpackageSettlemenId = bizOrderTaskpackageSettlemenId;
	}

	public String getOperateRemarks() {
		return operateRemarks;
	}

	public void setOperateRemarks(String operateRemarks) {
		this.operateRemarks = operateRemarks;
	}

}
