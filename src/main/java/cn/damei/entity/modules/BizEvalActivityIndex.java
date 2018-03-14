package cn.damei.entity.modules;


import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

/**
 * 评价活动指标设置Entity
 * @author wyb
 * @version 2017-02-25
 */
public class BizEvalActivityIndex extends DataEntity2<BizEvalActivityIndex> {
	
	private static final long serialVersionUID = 1L;
	private Integer evalActivityId;		// 评价活动id
	private String evalRoleType;		// 评价类别id
	private Integer evalIndexId;		// 评价指标id
	private Double evalTotalScore;		// 分值
	private String indexName;	//评价指标内容
	
	private BizEvalActivity evalActivity; //评价活动

	private Integer selectCount; //星级选中个数

	private Double indexScore;//评价分数
	
	private Date evalDate; //评价时间
	
	private String evalCycleHours;//系统评价时间
	
	
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