package cn.damei.entity.mobile.Manager;

import cn.damei.common.persistence.DataEntity2;

import java.util.Date;


public class EvalScoreRole extends DataEntity2<EvalScoreRole>{

	private static final long serialVersionUID = 1L;

	private Integer evalScoreId;
	private String evalRoleType;
	private Integer evalByEmployeeId;
	private String evalByCusPhone;
	private Double gotScore;
	private String evalFeedback;
	private Date evalDatetime;
	private String evalStatus;
	
	private String evalCycleHours;
	
	
	
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
