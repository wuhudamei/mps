package cn.damei.entity.mobile.Manager;

import cn.damei.common.persistence.DataEntity2;

/**
 * 任务包结算Entity
 * @author qww
 * @version 2016-10-15
 */

public class EmpTaskpackageSettlement extends DataEntity2<EmpTaskpackageSettlement>{

	private static final long serialVersionUID = 1L;
	
	private Integer id; // 员工id
	private String realName; // 员工名称
	private String isLeader; // 是否组长
	
	private Double paymentAmount; // 工人薪酬
	private String remarks; // 工人薪酬备注
	private Integer settlementDetailId; // 工人薪酬表id
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getIsLeader() {
		return isLeader;
	}
	public void setIsLeader(String isLeader) {
		this.isLeader = isLeader;
	}
	public Double getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(Double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Integer getSettlementDetailId() {
		return settlementDetailId;
	}
	public void setSettlementDetailId(Integer settlementDetailId) {
		this.settlementDetailId = settlementDetailId;
	}
}
