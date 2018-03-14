package cn.damei.entity.mobile.Inspector;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

/**
 * 评价奖励任务包表
 * @author Administrator
 *
 */
public class EvalRewardTaskpack extends DataEntity2<EvalRewardTaskpack>{

	
	private static final long serialVersionUID = 1L;
	
	private Integer orderTaskpackageId;	//订单任务包ID
    private Integer groupLeaderEmployeeId; //工人组长员工ID
    private Double rewardAmount; //奖励金额
    private Date rewardDatetime; //奖励日期时间
    
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
