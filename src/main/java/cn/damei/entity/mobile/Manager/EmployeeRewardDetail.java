package cn.damei.entity.mobile.Manager;

import cn.damei.common.persistence.DataEntity2;

public class EmployeeRewardDetail extends DataEntity2<EmployeeRewardDetail>{

	private static final long serialVersionUID = 1L;

	private Integer starLevel;
	private Double rewardAmount;
	private Double gotScore;

	public Integer getStarLevel() {
		return starLevel;
	}

	public void setStarLevel(Integer starLevel) {
		this.starLevel = starLevel;
	}

	public Double getRewardAmount() {
		return rewardAmount;
	}

	public void setRewardAmount(Double rewardAmount) {
		this.rewardAmount = rewardAmount;
	}

	public Double getGotScore() {
		return gotScore;
	}

	public void setGotScore(Double gotScore) {
		this.gotScore = gotScore;
	}
}
