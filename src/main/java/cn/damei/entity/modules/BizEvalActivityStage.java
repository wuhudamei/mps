package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;

public class BizEvalActivityStage extends DataEntity2<BizEvalActivityStage>{

	private static final long serialVersionUID = 1L;
	
	private Integer evalActivityId;  //评价活动Id
	
	private String relatedStage;  //关联阶段类型  1:基础装修验收完成评价  2:竣工验收完成评价
	
	private Integer relatedQcCheckNodeId;  //关联约检节点Id

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
