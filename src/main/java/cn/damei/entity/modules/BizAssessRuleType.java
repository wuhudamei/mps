package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;


public class BizAssessRuleType extends DataEntity2<BizAssessRuleType> {

	private static final long serialVersionUID = 1L;
	private Integer storeId;
	private String projectMode;
	private String typeName;
	private String isRewardOrPunish;
	private String rewardPunishTargetType;
	private String isMonthInspection;
	private Integer isEnabled;

	public String getIsMonthInspection() {
		return isMonthInspection;
	}

	public void setIsMonthInspection(String isMonthInspection) {
		this.isMonthInspection = isMonthInspection;
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

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getIsRewardOrPunish() {
		return isRewardOrPunish;
	}

	public void setIsRewardOrPunish(String isRewardOrPunish) {
		this.isRewardOrPunish = isRewardOrPunish;
	}

	public String getRewardPunishTargetType() {
		return rewardPunishTargetType;
	}

	public void setRewardPunishTargetType(String rewardPunishTargetType) {
		this.rewardPunishTargetType = rewardPunishTargetType;
	}

	public Integer getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Integer isEnabled) {
		this.isEnabled = isEnabled;
	}

}
