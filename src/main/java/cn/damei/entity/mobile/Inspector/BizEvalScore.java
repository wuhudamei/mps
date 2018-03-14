package cn.damei.entity.mobile.Inspector;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

/**
 * 评价任务包得分表
 * @author Administrator
 *EvalTaskpackScore
 */
public class BizEvalScore extends DataEntity2<BizEvalScore>{

	
	private static final long serialVersionUID = 1L;
	
	private Integer relatedBusinessId;	//业务关联Id  评价工人：任务包ID  评价项目经理：约检单Id
	private String evalType;  //评价类型   1：评价工人  2：评价项目经理
    private Integer groupLeaderEmployeeId; //被评价人Id
    private Double gotScore; //得分
    private String evalStatus; //评价状态
    private Date statusDatetime; //状态日期时间
    private Date evalStartDatetime; //评价开始日期时间
    private String range; //范围
    
    
    
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
