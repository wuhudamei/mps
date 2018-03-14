/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;


/**
 * 工程部主材工期统计表--主材安装及问题上报
 */
public class BizOrderInstasllPlanAndProblem extends DataEntity2<BizOrderInstasllPlanAndProblem> {
	
	private static final long serialVersionUID = 1L;
	private Integer orderId;		// 订单id
	private Integer orderInstallPlanId;		// 主材安装计划id
	private String installItemSequence;		// 安装项顺序
	private Date applyIntoCreateDatetime;		// 安装申请  提报时间
	private Date applyIntoDate;		// 安装申请  期望进场时间
	private Date realIntoDate;		// 安装申请	实际进场日期
	private Date realCompleteDate;		// 安装申请	实际完工日期
	private Integer problemCount;		// 主材安装 问题上报次数	
	private String problemDescribe;		// 主材安装 最早的  问题上报描述
	private Date problemDateTime;		// 主材安装 最早的  问题上报提交时间
	
	

	public BizOrderInstasllPlanAndProblem() {
		super();
	}

	public BizOrderInstasllPlanAndProblem(Integer id){
		super(id);
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getOrderInstallPlanId() {
		return orderInstallPlanId;
	}

	public void setOrderInstallPlanId(Integer orderInstallPlanId) {
		this.orderInstallPlanId = orderInstallPlanId;
	}

	public String getInstallItemSequence() {
		return installItemSequence;
	}

	public void setInstallItemSequence(String installItemSequence) {
		this.installItemSequence = installItemSequence;
	}

	public Date getApplyIntoCreateDatetime() {
		return applyIntoCreateDatetime;
	}

	public void setApplyIntoCreateDatetime(Date applyIntoCreateDatetime) {
		this.applyIntoCreateDatetime = applyIntoCreateDatetime;
	}

	public Date getApplyIntoDate() {
		return applyIntoDate;
	}

	public void setApplyIntoDate(Date applyIntoDate) {
		this.applyIntoDate = applyIntoDate;
	}

	public Date getRealIntoDate() {
		return realIntoDate;
	}

	public void setRealIntoDate(Date realIntoDate) {
		this.realIntoDate = realIntoDate;
	}

	public Date getRealCompleteDate() {
		return realCompleteDate;
	}

	public void setRealCompleteDate(Date realCompleteDate) {
		this.realCompleteDate = realCompleteDate;
	}

	public Integer getProblemCount() {
		return problemCount;
	}

	public void setProblemCount(Integer problemCount) {
		this.problemCount = problemCount;
	}

	public String getProblemDescribe() {
		return problemDescribe;
	}

	public void setProblemDescribe(String problemDescribe) {
		this.problemDescribe = problemDescribe;
	}

	public Date getProblemDateTime() {
		return problemDateTime;
	}

	public void setProblemDateTime(Date problemDateTime) {
		this.problemDateTime = problemDateTime;
	}
	
}