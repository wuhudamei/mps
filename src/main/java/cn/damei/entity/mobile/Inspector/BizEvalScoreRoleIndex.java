package cn.damei.entity.mobile.Inspector;


import cn.damei.common.persistence.DataEntity2;

/**
 * 评价角色指标得分表
 * @author Administrator
 *EvalTaskpackRoleIndexScore
 */
public class BizEvalScoreRoleIndex extends DataEntity2<BizEvalScoreRoleIndex>{

	
	private static final long serialVersionUID = 1L;
	
	private Integer evalScoreRoleId;	//评价角色得分ID
    private Integer evalActivityIndexId; //评价活动指标ID
    private Double gotScore; //得分
    
    
	
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
