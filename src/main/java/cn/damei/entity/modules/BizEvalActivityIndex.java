package cn.damei.entity.modules;


import java.util.Date;

import cn.damei.common.persistence.DataEntity2;


public class BizEvalActivityIndex extends DataEntity2<BizEvalActivityIndex> {
	
	private static final long serialVersionUID = 1L;
	private Integer evalActivityId;
	private String evalRoleType;
	private Integer evalIndexId;
	private Double evalTotalScore;
	private String indexName;
	
	private BizEvalActivity evalActivity;

	private Integer selectCount;

	private Double indexScore;
	
	private Date evalDate;
	
	private String evalCycleHours;
	
	
	public String getEvalCycleHours() {
		return evalCycleHours;
	}

	public void setEvalCycleHours(String evalCycleHours) {
		this.evalCycleHours = evalCycleHours;
	}

	public Date getEvalDate() {
		return evalDate;
	}

	public void setEvalDate(Date evalDate) {
		this.evalDate = evalDate;
	}

	public Double getIndexScore() {
		return indexScore;
	}

	public void setIndexScore(Double indexScore) {
		this.indexScore = indexScore;
	}

	public BizEvalActivityIndex() {
		super();
	}

	public BizEvalActivityIndex(Integer id){
		super(id);
	}

	public Integer getEvalActivityId() {
		return evalActivityId;
	}

	public void setEvalActivityId(Integer evalActivityId) {
		this.evalActivityId = evalActivityId;
	}

	public String getEvalRoleType() {
		return evalRoleType;
	}

	public void setEvalRoleType(String evalRoleType) {
		this.evalRoleType = evalRoleType;
	}

	public Integer getEvalIndexId() {
		return evalIndexId;
	}

	public void setEvalIndexId(Integer evalIndexId) {
		this.evalIndexId = evalIndexId;
	}

	public Double getEvalTotalScore() {
		return evalTotalScore;
	}

	public void setEvalTotalScore(Double evalTotalScore) {
		this.evalTotalScore = evalTotalScore;
	}

	public BizEvalActivity getEvalActivity() {
		return evalActivity;
	}

	public void setEvalActivity(BizEvalActivity evalActivity) {
		this.evalActivity = evalActivity;
	}

	public String getIndexName() {
		return indexName;
	}

	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}

	public Integer getSelectCount() {
		return selectCount;
	}

	public void setSelectCount(Integer selectCount) {
		this.selectCount = selectCount;
	}
}