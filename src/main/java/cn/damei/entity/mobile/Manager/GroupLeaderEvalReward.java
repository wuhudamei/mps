package cn.damei.entity.mobile.Manager;

import cn.damei.common.persistence.DataEntity2;

import java.util.Date;

public class GroupLeaderEvalReward extends DataEntity2<GroupLeaderEvalReward>{

	private static final long serialVersionUID = 1L;

	private Double rewardAmount; // 奖励金额
	private Date rewardDatetime; // 奖励生成时间
	private String itemCustomer; // 项目经理
	private String communityName; // 小区名称
	private String buildNumber; // 几号楼
	private String buildUnit; // 几单元
	private String buildRoom; // 哪一室
	private String customerName; // 客户名称

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

	public String getItemCustomer() {
		return itemCustomer;
	}

	public void setItemCustomer(String itemCustomer) {
		this.itemCustomer = itemCustomer;
	}

	public String getCommunityName() {
		return communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}

	public String getBuildNumber() {
		return buildNumber;
	}

	public void setBuildNumber(String buildNumber) {
		this.buildNumber = buildNumber;
	}

	public String getBuildUnit() {
		return buildUnit;
	}

	public void setBuildUnit(String buildUnit) {
		this.buildUnit = buildUnit;
	}

	public String getBuildRoom() {
		return buildRoom;
	}

	public void setBuildRoom(String buildRoom) {
		this.buildRoom = buildRoom;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
}
