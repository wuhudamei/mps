package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;


public class BizBusinessRewardPunish extends DataEntity2<BizBusinessRewardPunish>{

	private static final long serialVersionUID = 1L;
	
	private String rewardPunishType;
	
	private String employeeType;
	
	private Integer employeeId;
	
	private String relatedBusinessType;
	
	private Integer relatedBusinessIdInt;
	
	private String relatedBusinessIdVarchar;
	
	private Double rewardPunishAmount;
	
	private String rewardPunishRemarks;
	
	private Date rewardPunishDatetime;
	
	private String rewardPunishStatus;
	
	private Date statusDatetime;
	
	private String settleStage;
	
	private String settleType;
	
	private Integer settleId;

	
	public String getRelatedBusinessType() {
		return relatedBusinessType;
	}

	public void setRelatedBusinessType(String relatedBusinessType) {
		this.relatedBusinessType = relatedBusinessType;
	}

	public String getRewardPunishType() {
		return rewardPunishType;
	}

	public void setRewardPunishType(String rewardPunishType) {
		this.rewardPunishType = rewardPunishType;
	}

	public String getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public Integer getRelatedBusinessIdInt() {
		return relatedBusinessIdInt;
	}

	public void setRelatedBusinessIdInt(Integer relatedBusinessIdInt) {
		this.relatedBusinessIdInt = relatedBusinessIdInt;
	}

	public String getRelatedBusinessIdVarchar() {
		return relatedBusinessIdVarchar;
	}

	public void setRelatedBusinessIdVarchar(String relatedBusinessIdVarchar) {
		this.relatedBusinessIdVarchar = relatedBusinessIdVarchar;
	}

	public Double getRewardPunishAmount() {
		return rewardPunishAmount;
	}

	public void setRewardPunishAmount(Double rewardPunishAmount) {
		this.rewardPunishAmount = rewardPunishAmount;
	}

	public String getRewardPunishRemarks() {
		return rewardPunishRemarks;
	}

	public void setRewardPunishRemarks(String rewardPunishRemarks) {
		this.rewardPunishRemarks = rewardPunishRemarks;
	}

	public Date getRewardPunishDatetime() {
		return rewardPunishDatetime;
	}

	public void setRewardPunishDatetime(Date rewardPunishDatetime) {
		this.rewardPunishDatetime = rewardPunishDatetime;
	}

	public String getRewardPunishStatus() {
		return rewardPunishStatus;
	}

	public void setRewardPunishStatus(String rewardPunishStatus) {
		this.rewardPunishStatus = rewardPunishStatus;
	}

	public Date getStatusDatetime() {
		return statusDatetime;
	}

	public void setStatusDatetime(Date statusDatetime) {
		this.statusDatetime = statusDatetime;
	}

	public String getSettleStage() {
		return settleStage;
	}

	public void setSettleStage(String settleStage) {
		this.settleStage = settleStage;
	}

	public String getSettleType() {
		return settleType;
	}

	public void setSettleType(String settleType) {
		this.settleType = settleType;
	}

	public Integer getSettleId() {
		return settleId;
	}

	public void setSettleId(Integer settleId) {
		this.settleId = settleId;
	}
	
	
}
