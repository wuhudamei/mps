package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;


public class BizAssessRule extends DataEntity2<BizAssessRule> {

	private static final long serialVersionUID = 1L;
	private Integer storeId;
	private String projectMode;
	private Integer bizAssessRuleTypeId;
	private String bizAssessRuleTypeName;
	private String bizAssessRuleDescribe;
	private Double rewardPunishAmount;
	private Integer rewardPunishScore;
	private String rewardPunishTargetEmployeeType;
	private String[] rewardPunishTargetEmployeeTypeArr;
	private Integer isEnabled;

	public String[] getRewardPunishTargetEmployeeTypeArr() {
		if (rewardPunishTargetEmployeeType != null && !rewardPunishTargetEmployeeType.equals("")) {
			rewardPunishTargetEmployeeTypeArr = rewardPunishTargetEmployeeType.split(",");
		}
		return rewardPunishTargetEmployeeTypeArr;
	}

	public void setRewardPunishTargetEmployeeTypeArr(String[] rewardPunishTargetEmployeeTypeArr) {
		this.rewardPunishTargetEmployeeTypeArr = rewardPunishTargetEmployeeTypeArr;
	}

	public String getBizAssessRuleTypeName() {
		return bizAssessRuleTypeName;
	}

	public void setBizAssessRuleTypeName(String bizAssessRuleTypeName) {
		this.bizAssessRuleTypeName = bizAssessRuleTypeName;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}

	public Integer getBizAssessRuleTypeId() {
		return bizAssessRuleTypeId;
	}

	public void setBizAssessRuleTypeId(Integer bizAssessRuleTypeId) {
		this.bizAssessRuleTypeId = bizAssessRuleTypeId;
	}

	public String getBizAssessRuleDescribe() {
		return bizAssessRuleDescribe;
	}

	public void setBizAssessRuleDescribe(String bizAssessRuleDescribe) {
		this.bizAssessRuleDescribe = bizAssessRuleDescribe;
	}

	public Double getRewardPunishAmount() {
		return rewardPunishAmount;
	}

	public void setRewardPunishAmount(Double rewardPunishAmount) {
		this.rewardPunishAmount = rewardPunishAmount;
	}

	public Integer getRewardPunishScore() {
		return rewardPunishScore;
	}

	public void setRewardPunishScore(Integer rewardPunishScore) {
		this.rewardPunishScore = rewardPunishScore;
	}

	public String getRewardPunishTargetEmployeeType() {
		return rewardPunishTargetEmployeeType;
	}

	public void setRewardPunishTargetEmployeeType(String rewardPunishTargetEmployeeType) {
		this.rewardPunishTargetEmployeeType = rewardPunishTargetEmployeeType;
	}

	public Integer getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Integer isEnabled) {
		this.isEnabled = isEnabled;
	}

}
