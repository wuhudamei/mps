package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;

public class BizEvalActivityStage extends DataEntity2<BizEvalActivityStage>{

	private static final long serialVersionUID = 1L;
	
	private Integer evalActivityId;
	
	private String relatedStage;
	
	private Integer relatedQcCheckNodeId;

	public Integer getEvalActivityId() {
		return evalActivityId;
	}

	public void setEvalActivityId(Integer evalActivityId) {
		this.evalActivityId = evalActivityId;
	}

	public String getRelatedStage() {
		return relatedStage;
	}

	public void setRelatedStage(String relatedStage) {
		this.relatedStage = relatedStage;
	}

	public Integer getRelatedQcCheckNodeId() {
		return relatedQcCheckNodeId;
	}

	public void setRelatedQcCheckNodeId(Integer relatedQcCheckNodeId) {
		this.relatedQcCheckNodeId = relatedQcCheckNodeId;
	}
	
	
}
