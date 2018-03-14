package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

/**
 * 结算单修改日志实体类
 * @author hyh
 *
 */
public class BizOrderTaskpackageSettlementUpdateLog extends DataEntity2<BizOrderTaskpackageSettlementUpdateLog>{

	private static final long serialVersionUID = 1L;
	
	private Integer orderTaskpackageSettlementId; //结算单Id
	
	private Double amountOld; //原来结算金额
	
	private Double amountNew; //修改后的结算金额
	
	private Integer operatorEmployeeId; //操作人
	
	private Date operateDatetime; //操作时间

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
