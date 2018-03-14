/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import java.util.List;
import java.util.Map;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity;

/**
 * 客户回访节点内容Entity
 * @author LiWancai
 * @version 2017-05-23
 */
public class BizCustomerReturnVisitContent extends DataEntity<BizCustomerReturnVisitContent> {
	
	private static final long serialVersionUID = 1L;
	private String returnVisitId;		// 回访节点ID
	private String questionContent;		// 问题描述
	private Integer statisticsDepartment;	//统计部门
	private String replyMode;			// 答复方式(dict:3：填空；1：一级选项；2：二级选项)
	private String itemContent;			// 选项内容
	private List<Map<String,String>> itemContentList;
	
	public BizCustomerReturnVisitContent() {
		super();
	}

	public BizCustomerReturnVisitContent(String id){
		super(id);
	}

	@Length(min=1, max=11, message="回访节点ID长度必须介于 1 和 11 之间")
	public String getReturnVisitId() {
		return returnVisitId;
	}

	public void setReturnVisitId(String returnVisitId) {
		this.returnVisitId = returnVisitId;
	}
	
	public String getQuestionContent() {
		return questionContent;
	}

	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}
	
	public String getReplyMode() {
		return replyMode;
	}

	public void setReplyMode(String replyMode) {
		this.replyMode = replyMode;
	}
	
	public String getItemContent() {
		return itemContent;
	}

	public void setItemContent(String itemContent) {
		this.itemContent = itemContent;
	}

	public List<Map<String, String>> getItemContentList() {
		return itemContentList;
	}

	public void setItemContentList(List<Map<String, String>> itemContentList) {
		this.itemContentList = itemContentList;
	}

	public Integer getStatisticsDepartment() {
		return statisticsDepartment;
	}

	public void setStatisticsDepartment(Integer statisticsDepartment) {
		this.statisticsDepartment = statisticsDepartment;
	}
	
}