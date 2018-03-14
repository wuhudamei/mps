package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;

/**
 * 订单变更实体类
 * @author hyh
 *
 */
public class BizOrderChange extends DataEntity2<BizOrderChange>{
	
	private static final long serialVersionUID = 1L;
	
	private Integer orderId; //订单Id
	
	private String changeType; // 10：基装增项、20：中期变更增项、30：中期变更减项、40：竣工变更增项、50：竣工变更减项
	
	private Double changeAmount; //变更金额
	
	private Double changeAccountRate; //变更核算比例
	
	private Double changeAccountAmount; //变更核算金额
	
	private String changeRemarks; //变更说明

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getChangeType() {
		return changeType;
	}

	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}

	public Double getChangeAmount() {
		return changeAmount;
	}

	public void setChangeAmount(Double changeAmount) {
		this.changeAmount = changeAmount;
	}

	public Double getChangeAccountRate() {
		return changeAccountRate;
	}

	public void setChangeAccountRate(Double changeAccountRate) {
		this.changeAccountRate = changeAccountRate;
	}

	public Double getChangeAccountAmount() {
		return changeAccountAmount;
	}

	public void setChangeAccountAmount(Double changeAccountAmount) {
		this.changeAccountAmount = changeAccountAmount;
	}

	public String getChangeRemarks() {
		return changeRemarks;
	}

	public void setChangeRemarks(String changeRemarks) {
		this.changeRemarks = changeRemarks;
	}
	

}
