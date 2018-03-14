/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity;

/**
 * 员工星级变更记录表Entity
 * @author ws
 * @version 2017-09-08
 */
public class BizEmployeeStarLog extends DataEntity<BizEmployeeStarLog> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer empId;//员工ID
	private String starChangeSource;		// 星级变更来源
	private Integer relatedBusinessId;       //评价分数ID
	private String starScoreBefore;			//变更前星级得分
	private String starScoreAfter;         //变更后星级得分
	private Integer starBefore;            //变更前星级
	private Integer starAfter;              //变更后星级
	private String reasonType;                 //变更原因分类
	private String changeDescribe;			//变更说明
	private Integer changeEmpId;            //变更操作人ID
	private String starChangeDatetime;		//变更日期时间
	public BizEmployeeStarLog() {
		super();
	}
	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	public String getStarChangeSource() {
		return starChangeSource;
	}
	public void setStarChangeSource(String starChangeSource) {
		this.starChangeSource = starChangeSource;
	}
	public Integer getRelatedBusinessId() {
		return relatedBusinessId;
	}
	public void setRelatedBusinessId(Integer relatedBusinessId) {
		this.relatedBusinessId = relatedBusinessId;
	}
	public String getStarScoreBefore() {
		return starScoreBefore;
	}
	public void setStarScoreBefore(String starScoreBefore) {
		this.starScoreBefore = starScoreBefore;
	}
	public String getStarScoreAfter() {
		return starScoreAfter;
	}
	public void setStarScoreAfter(String starScoreAfter) {
		this.starScoreAfter = starScoreAfter;
	}
	public Integer getStarBefore() {
		return starBefore;
	}
	public void setStarBefore(Integer starBefore) {
		this.starBefore = starBefore;
	}
	public Integer getStarAfter() {
		return starAfter;
	}
	public void setStarAfter(Integer starAfter) {
		this.starAfter = starAfter;
	}
	public String getReasonType() {
		return reasonType;
	}
	public void setReasonType(String reasonType) {
		this.reasonType = reasonType;
	}
	public String getChangeDescribe() {
		return changeDescribe;
	}
	public void setChangeDescribe(String changeDescribe) {
		this.changeDescribe = changeDescribe;
	}
	public Integer getChangeEmpId() {
		return changeEmpId;
	}
	public void setChangeEmpId(Integer changeEmpId) {
		this.changeEmpId = changeEmpId;
	}
	public String getStarChangeDatetime() {
		return starChangeDatetime;
	}
	public void setStarChangeDatetime(String starChangeDatetime) {
		this.starChangeDatetime = starChangeDatetime;
	}

}