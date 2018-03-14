package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;


public class BizOrderTaskpackageSettlementUpdateLog extends DataEntity2<BizOrderTaskpackageSettlementUpdateLog>{

	private static final long serialVersionUID = 1L;
	
	private Integer orderTaskpackageSettlementId;
	
	private Double amountOld;
	
	private Double amountNew;
	
	private Integer operatorEmployeeId;
	
	private Date operateDatetime;

	public Integer getOrderTaskpackageSettlementId() {
		return orderTaskpackageSettlementId;
	}

	public void setOrderTaskpackageSettlementId(Integer orderTaskpackageSettlementId) {
		this.orderTaskpackageSettlementId = orderTaskpackageSettlementId;
	}

	public Double getAmountOld() {
		return amountOld;
	}

	public void setAmountOld(Double amountOld) {
		this.amountOld = amountOld;
	}

	public Double getAmountNew() {
		return amountNew;
	}

	public void setAmountNew(Double amountNew) {
		this.amountNew = amountNew;
	}

	public Integer getOperatorEmployeeId() {
		return operatorEmployeeId;
	}

	public void setOperatorEmployeeId(Integer operatorEmployeeId) {
		this.operatorEmployeeId = operatorEmployeeId;
	}

	public Date getOperateDatetime() {
		return operateDatetime;
	}

	public void setOperateDatetime(Date operateDatetime) {
		this.operateDatetime = operateDatetime;
	}
	
	
}
