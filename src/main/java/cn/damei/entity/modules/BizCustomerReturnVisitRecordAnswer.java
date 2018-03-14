/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity2;

/**
 * 客户回访答案Entity
 * @author lft
 * @version 2017-05-26
 */
public class BizCustomerReturnVisitRecordAnswer extends DataEntity2<BizCustomerReturnVisitRecordAnswer> {
	
	private static final long serialVersionUID = 1L;
	private Integer returnVisitRecordId;	// 回访记录ID
	private String returnVisitQuestion;		// 回访问题
	private Integer statisticsDepartment;	//统计部门
	private String statisticsResult;		//统计结果
	private String questionAnswer;			// 问题答案
	
	public BizCustomerReturnVisitRecordAnswer() {
		super();
	}

	public BizCustomerReturnVisitRecordAnswer(Integer id){
		super(id);
	}

	@NotNull(message="回访记录ID不能为空")
	public Integer getReturnVisitRecordId() {
		return returnVisitRecordId;
	}

	public void setReturnVisitRecordId(Integer returnVisitRecordId) {
		this.returnVisitRecordId = returnVisitRecordId;
	}
	

	public String getReturnVisitQuestion() {
		return returnVisitQuestion;
	}

	public void setReturnVisitQuestion(String returnVisitQuestion) {
		this.returnVisitQuestion = returnVisitQuestion;
	}

	@Length(min=1, max=100, message="问题答案长度必须介于 1 和 100 之间")
	public String getQuestionAnswer() {
		return questionAnswer;
	}

	public void setQuestionAnswer(String questionAnswer) {
		this.questionAnswer = questionAnswer;
	}

	public Integer getStatisticsDepartment() {
		return statisticsDepartment;
	}

	public void setStatisticsDepartment(Integer statisticsDepartment) {
		this.statisticsDepartment = statisticsDepartment;
	}

	public String getStatisticsResult() {
		return statisticsResult;
	}

	public void setStatisticsResult(String statisticsResult) {
		this.statisticsResult = statisticsResult;
	}
	
}