package cn.damei.entity.mobile.Manager;

import cn.damei.common.persistence.DataEntity2;

/**
 * 任务包结算Entity
 * @author qww
 * @version 2016-10-15
 */

public class SettlementEvalStore extends DataEntity2<SettlementEvalStore>{

	private static final long serialVersionUID = 1L;
	
	private Double evalTotalScore; // 总分
	private String indexName; // 项目指标名称
	private Double gotScore; // 得分
	private Integer selectCount; // 选中星级个数

	public Double getEvalTotalScore() {
		return evalTotalScore;
	}

	public void setEvalTotalScore(Double evalTotalScore) {
		this.evalTotalScore = evalTotalScore;
	}

	public String getIndexName() {
		return indexName;
	}

	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}

	public Double getGotScore() {
		return gotScore;
	}

	public void setGotScore(Double gotScore) {
		this.gotScore = gotScore;
	}

	public Integer getSelectCount() {
		return selectCount;
	}

	public void setSelectCount(Integer selectCount) {
		this.selectCount = selectCount;
	}
}
