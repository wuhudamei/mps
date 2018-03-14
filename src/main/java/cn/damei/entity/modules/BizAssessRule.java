package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;

/**
 * 考核条例细则实体类
 * 
 * @author hyh
 *
 */
public class BizAssessRule extends DataEntity2<BizAssessRule> {

	private static final long serialVersionUID = 1L;
	private Integer storeId; // 门店Id
	private String projectMode; // 工程模式
	private Integer bizAssessRuleTypeId;// 考核条例分类Id
	private String bizAssessRuleTypeName;// 考核条例分类名称
	private String bizAssessRuleDescribe;// 考核条例说明
	private Double rewardPunishAmount;// 奖惩金额
	private Integer rewardPunishScore;// 奖惩分数
	private String rewardPunishTargetEmployeeType;// 奖惩对象员工类型 1:项目经理  2：工人  3：质检员
	private String[] rewardPunishTargetEmployeeTypeArr;//
	private Integer isEnabled; // 是否启用 0：停用 1：启用

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
