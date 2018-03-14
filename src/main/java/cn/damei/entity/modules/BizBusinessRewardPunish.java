package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

/**
 * 奖励惩罚实体类
 * @author hyh
 *
 */
public class BizBusinessRewardPunish extends DataEntity2<BizBusinessRewardPunish>{

	private static final long serialVersionUID = 1L;
	
	private String rewardPunishType;  //奖惩类型  1：奖励 2：惩罚
	
	private String employeeType; //员工类型 1：员工  2：项目经理
	
	private Integer employeeId;//员工Id
	
	private String relatedBusinessType; //关联业务类型  1：准产业项目经理中期结算  2：准产业项目经理竣工结算
	
	private Integer relatedBusinessIdInt;
	
	private String relatedBusinessIdVarchar;
	
	private Double rewardPunishAmount; //奖惩金额
	
	private String rewardPunishRemarks; //奖惩说明
	
	private Date rewardPunishDatetime; //奖惩时间
	
	private String rewardPunishStatus; //奖惩状态
	
	private Date statusDatetime;  //状态时间
	
	private String settleStage; //结算阶段  1：中期结算   2：竣工结算
	
	private String settleType; //结算单类型 1：工人结算  2：项目经理结算
	
	private Integer settleId;  //结算单Id

	
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
