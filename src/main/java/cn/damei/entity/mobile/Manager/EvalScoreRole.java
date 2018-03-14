package cn.damei.entity.mobile.Manager;

import cn.damei.common.persistence.DataEntity2;

import java.util.Date;

/**
 * BizEvalTaskpackRoleScore
 * @author Administrator
 *
 */
public class EvalScoreRole extends DataEntity2<EvalScoreRole>{

	private static final long serialVersionUID = 1L;

	private Integer evalScoreId; // 评价得分id
	private String evalRoleType; // 评价角色类型 101-项目经理 102-项目经理自动 201-质检员 202-质检员自动 301-客户 302-客户自动
	private Integer evalByEmployeeId; // 评价人员工id
	private String evalByCusPhone; // 评价人业主手机号
	private Double gotScore; // 得分
	private String evalFeedback; // 评价反馈
	private Date evalDatetime; // 评价日期时间
	private String evalStatus;//评价状态 0：未评价  1:评价已完成
	
	private String evalCycleHours;//系统评价时间
	
	
	
	public String getEvalCycleHours() {
		return evalCycleHours;
	}

	public void setEvalCycleHours(String evalCycleHours) {
		this.evalCycleHours = evalCycleHours;
	}

	public String getEvalStatus() {
		return evalStatus;
	}

	public void setEvalStatus(String evalStatus) {
		this.evalStatus = evalStatus;
	}


	public Integer getEvalScoreId() {
		return evalScoreId;
	}

	public void setEvalScoreId(Integer evalScoreId) {
		this.evalScoreId = evalScoreId;
	}

	public String getEvalRoleType() {
		return evalRoleType;
	}

	public void setEvalRoleType(String evalRoleType) {
		this.evalRoleType = evalRoleType;
	}

	public Integer getEvalByEmployeeId() {
		return evalByEmployeeId;
	}

	public void setEvalByEmployeeId(Integer evalByEmployeeId) {
		this.evalByEmployeeId = evalByEmployeeId;
	}

	public String getEvalByCusPhone() {
		return evalByCusPhone;
	}

	public void setEvalByCusPhone(String evalByCusPhone) {
		this.evalByCusPhone = evalByCusPhone;
	}

	public Double getGotScore() {
		return gotScore;
	}

	public void setGotScore(Double gotScore) {
		this.gotScore = gotScore;
	}

	public String getEvalFeedback() {
		return evalFeedback;
	}

	public void setEvalFeedback(String evalFeedback) {
		this.evalFeedback = evalFeedback;
	}

	public Date getEvalDatetime() {
		return evalDatetime;
	}

	public void setEvalDatetime(Date evalDatetime) {
		this.evalDatetime = evalDatetime;
	}
}
