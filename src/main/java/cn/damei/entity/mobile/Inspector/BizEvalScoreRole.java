package cn.damei.entity.mobile.Inspector;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

/**
 * 评价角色得分表
 * @author Administrator
 *
 */
public class BizEvalScoreRole extends DataEntity2<BizEvalScoreRole>{

	
	private static final long serialVersionUID = 1L;
	
	private Integer evalScoreId;	//评价得分ID
    private String evalRoleType; //评价角色类型
    private Integer evalByEmployeeId; //评价人员工ID
    private String evalByCusPhone; //评价人业主手机号
    private Double gotScore; //得分
    private String evalFeedback; //评价反馈
    private Date evalDatetime; //评价日期时间
    private String evalStatus;//评价状态 0：未评价  1:评价已完成
    
    private String managerType;
    private String  pqcType;
    private String  custemerType;
    
    
	public String getManagerType() {
		return managerType;
	}
	public void setManagerType(String managerType) {
		this.managerType = managerType;
	}
	public String getPqcType() {
		return pqcType;
	}
	public void setPqcType(String pqcType) {
		this.pqcType = pqcType;
	}
	public String getCustemerType() {
		return custemerType;
	}
	public void setCustemerType(String custemerType) {
		this.custemerType = custemerType;
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
