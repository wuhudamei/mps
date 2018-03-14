/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import java.util.Date;


import com.fasterxml.jackson.annotation.JsonFormat;
import cn.damei.common.persistence.DataEntity2;

/**
 * 约检节点设置Entity
 * @author 梅浩
 * @version 2016-10-26
 */
public class CheckNode extends DataEntity2<CheckNode> {
	
	private static final long serialVersionUID = 1L;
	private Integer storeId;		// 门店id -- '
	private String qcCheckNodeName;		// 节点名称 -- '
	private Integer qcCheckNodeIndex;		// 节点顺序 -- '
	private Integer daysToCheck;		// 开工后第几天检查 -- '

	private Integer constructionScheduleId;		// 所属进度节点模板id -- '
	private String constructionScheduleName;//进度节点模板名称




	private String isUrgePay;		// 是否缴催 -- '0.否；1.是
	private String status;		// 状态 -- '0.停用；1.启用
	private Date statusDatetime;		// 状态产生日期时间 -- '
	
	
	private String projectMode;		//工程模式   1-产业模式；2-传统模式



	private Integer daysToApply;  			//开工后第几天申请    2017-7-14 加入
	//2017-08-08 加入新字段  是否为基装节点  为其他业务逻辑做甄别
	private String isForBasicWork;

	public String getIsForBasicWork() {
		return isForBasicWork;
	}

	public void setIsForBasicWork(String isForBasicWork) {
		this.isForBasicWork = isForBasicWork;
	}

	public Integer getDaysToApply() {
		return daysToApply;
	}

	public void setDaysToApply(Integer daysToApply) {
		this.daysToApply = daysToApply;
	}

	public CheckNode() {
		super();
	}

	public CheckNode(Integer id){
		super(id);
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	
	public String getQcCheckNodeName() {
		return qcCheckNodeName;
	}

	public void setQcCheckNodeName(String qcCheckNodeName) {
		this.qcCheckNodeName = qcCheckNodeName;
	}
	
	public Integer getQcCheckNodeIndex() {
		return qcCheckNodeIndex;
	}

	public void setQcCheckNodeIndex(Integer qcCheckNodeIndex) {
		this.qcCheckNodeIndex = qcCheckNodeIndex;
	}
	
	public Integer getDaysToCheck() {
		return daysToCheck;
	}

	public void setDaysToCheck(Integer daysToCheck) {
		this.daysToCheck = daysToCheck;
	}
	
	public Integer getConstructionScheduleId() {
		return constructionScheduleId;
	}

	public void setConstructionScheduleId(Integer constructionScheduleId) {
		this.constructionScheduleId = constructionScheduleId;
	}
	
	public String getIsUrgePay() {
		return isUrgePay;
	}

	public void setIsUrgePay(String isUrgePay) {
		this.isUrgePay = isUrgePay;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStatusDatetime() {
		return statusDatetime;
	}

	public void setStatusDatetime(Date statusDatetime) {
		this.statusDatetime = statusDatetime;
	}
	public String getConstructionScheduleName() {
		return constructionScheduleName;
	}

	public void setConstructionScheduleName(String constructionScheduleName) {
		this.constructionScheduleName = constructionScheduleName;
	}

	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}
	
}