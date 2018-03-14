package cn.damei.entity.mobile.Inspector;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;


public class BizEvalScore extends DataEntity2<BizEvalScore>{

	
	private static final long serialVersionUID = 1L;
	
	private Integer relatedBusinessId;
	private String evalType;
    private Integer groupLeaderEmployeeId;
    private Double gotScore;
    private String evalStatus;
    private Date statusDatetime;
    private Date evalStartDatetime;
    private String range;
    
    
    
	public String getRange() {
		return range;
	}
	public void setRange(String range) {
		this.range = range;
	}
	
	
	
	public Integer getRelatedBusinessId() {
		return relatedBusinessId;
	}
	public void setRelatedBusinessId(Integer relatedBusinessId) {
		this.relatedBusinessId = relatedBusinessId;
	}
	public String getEvalType() {
		return evalType;
	}
	public void setEvalType(String evalType) {
		this.evalType = evalType;
	}
	public Integer getGroupLeaderEmployeeId() {
		return groupLeaderEmployeeId;
	}
	public void setGroupLeaderEmployeeId(Integer groupLeaderEmployeeId) {
		this.groupLeaderEmployeeId = groupLeaderEmployeeId;
	}
	public Double getGotScore() {
		return gotScore;
	}
	public void setGotScore(Double gotScore) {
		this.gotScore = gotScore;
	}
	public String getEvalStatus() {
		return evalStatus;
	}
	public void setEvalStatus(String evalStatus) {
		this.evalStatus = evalStatus;
	}
	public Date getStatusDatetime() {
		return statusDatetime;
	}
	public void setStatusDatetime(Date statusDatetime) {
		this.statusDatetime = statusDatetime;
	}
	public Date getEvalStartDatetime() {
		return evalStartDatetime;
	}
	public void setEvalStartDatetime(Date evalStartDatetime) {
		this.evalStartDatetime = evalStartDatetime;
	}
    
    
    
    

    
}
