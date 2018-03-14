/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;

/**
 * 评价奖励星级Entity
 * @author qww
 * @version 2017-02-24
 */
public class BizEvalRewardStar extends DataEntity2<BizEvalRewardStar> {
	
	private static final long serialVersionUID = 1L;
	private Integer evalRewardCfgId;		// 评价奖励设置id
	private Integer starLevel;		// 星级级别
	private Double minScore;		// 最小分数
	private Double maxScore;		// 最大分数
	private Double rewardAmount;		// 奖励金额
	private String isEnabled;		// 是否启用
	
	public BizEvalRewardStar() {
		super();
	}

	public BizEvalRewardStar(Integer id){
		super(id);
	}

	public Integer getEvalRewardCfgId() {
		return evalRewardCfgId;
	}

	public void setEvalRewardCfgId(Integer evalRewardCfgId) {
		this.evalRewardCfgId = evalRewardCfgId;
	}
	
	public Integer getStarLevel() {
		return starLevel;
	}

	public void setStarLevel(Integer starLevel) {
		this.starLevel = starLevel;
	}
	
	public Double getMinScore() {
		return minScore;
	}

	public void setMinScore(Double minScore) {
		this.minScore = minScore;
	}
	
	public Double getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(Double maxScore) {
		this.maxScore = maxScore;
	}
	
	public Double getRewardAmount() {
		return rewardAmount;
	}

	public void setRewardAmount(Double rewardAmount) {
		this.rewardAmount = rewardAmount;
	}

	public String getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(String isEnabled) {
		this.isEnabled = isEnabled;
	}
	
}