package cn.damei.entity.mobile.Manager;

import cn.damei.common.persistence.DataEntity2;



public class SettlementEvalStore extends DataEntity2<SettlementEvalStore>{

	private static final long serialVersionUID = 1L;
	
	private Double evalTotalScore;
	private String indexName;
	private Double gotScore;
	private Integer selectCount;

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
