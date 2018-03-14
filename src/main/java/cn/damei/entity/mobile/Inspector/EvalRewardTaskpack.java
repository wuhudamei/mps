package cn.damei.entity.mobile.Inspector;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;


public class EvalRewardTaskpack extends DataEntity2<EvalRewardTaskpack>{

	
	private static final long serialVersionUID = 1L;
	
	private Integer orderTaskpackageId;
    private Integer groupLeaderEmployeeId;
    private Double rewardAmount;
    private Date rewardDatetime;
    
	public Integer getOrderTaskpackageId() {
		return orderTaskpackageId;
	}
	public void setOrderTaskpackageId(Integer orderTaskpackageId) {
		this.orderTaskpackageId = orderTaskpackageId;
	}
	public Integer getGroupLeaderEmployeeId() {
		return groupLeaderEmployeeId;
	}
	public void setGroupLeaderEmployeeId(Integer groupLeaderEmployeeId) {
		this.groupLeaderEmployeeId = groupLeaderEmployeeId;
	}
	public Double getRewardAmount() {
		return rewardAmount;
	}
	public void setRewardAmount(Double rewardAmount) {
		this.rewardAmount = rewardAmount;
	}
	public Date getRewardDatetime() {
		return rewardDatetime;
	}
	public void setRewardDatetime(Date rewardDatetime) {
		this.rewardDatetime = rewardDatetime;
	}
	

    
}
