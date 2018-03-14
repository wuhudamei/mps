package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;


public class BizOrderChange extends DataEntity2<BizOrderChange>{
	
	private static final long serialVersionUID = 1L;
	
	private Integer orderId;
	
	private String changeType;
	
	private Double changeAmount;
	
	private Double changeAccountRate;
	
	private Double changeAccountAmount;
	
	private String changeRemarks;

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
