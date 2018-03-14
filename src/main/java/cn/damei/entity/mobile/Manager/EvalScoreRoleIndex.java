package cn.damei.entity.mobile.Manager;

import cn.damei.common.persistence.DataEntity2;

public class EvalScoreRoleIndex extends DataEntity2<EvalScoreRoleIndex>{

	private static final long serialVersionUID = 1L;

	private Integer evalScoreRoleId;
	private Integer evalActivityIndexId;
	private Double gotScore;

	public Integer getEvalScoreRoleId() {
		return evalScoreRoleId;
	}

	public void setEvalScoreRoleId(Integer evalScoreRoleId) {
		this.evalScoreRoleId = evalScoreRoleId;
	}

	public Integer getEvalActivityIndexId() {
		return evalActivityIndexId;
	}

	public void setEvalActivityIndexId(Integer evalActivityIndexId) {
		this.evalActivityIndexId = evalActivityIndexId;
	}

	public Double getGotScore() {
		return gotScore;
	}

	public void setGotScore(Double gotScore) {
		this.gotScore = gotScore;
	}
}
