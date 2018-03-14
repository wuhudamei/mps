
package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;


public class BizEvalRewardStar extends DataEntity2<BizEvalRewardStar> {
	
	private static final long serialVersionUID = 1L;
	private Integer evalRewardCfgId;
	private Integer starLevel;
	private Double minScore;
	private Double maxScore;
	private Double rewardAmount;
	private String isEnabled;
	
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