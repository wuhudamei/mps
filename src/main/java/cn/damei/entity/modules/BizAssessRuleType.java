package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;

/**
 * 考核条例分类实体类
 * @author hyh
 *
 */
public class BizAssessRuleType extends DataEntity2<BizAssessRuleType> {

	private static final long serialVersionUID = 1L;
	private Integer storeId; // 门店Id
	private String projectMode; // 工程模式
	private String typeName; // 分类名称
	private String isRewardOrPunish; // 1:奖励 2:惩罚
	private String rewardPunishTargetType; // 奖惩对象 10:订单 20：员工
	private String isMonthInspection;//是否月度巡检  0：否 1：是
	private Integer isEnabled; // 是否启用  0：停用   1：启用

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
