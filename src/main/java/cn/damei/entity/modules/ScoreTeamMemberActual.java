/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity;

/**
 * 团队成员评分实际评分结果Entity
 * @author lwc
 * @version 2017-04-12
 */
public class ScoreTeamMemberActual extends DataEntity<ScoreTeamMemberActual> {
	
	private static final long serialVersionUID = 1L;

	private Integer orderId;		// 订单id
	private Integer employeeId;		// 员工id
	private String employeeName;	// 员工姓名
	private String employeePhone;	// 员工电话
	private Integer belongOffice;	// 员工所属门店
	private String employeePost;	// 员工岗位
	private Integer currentScore;	// 当前评分值
	private Integer currentGoodNum;	// 当前好评数
	private Integer currentBadNum;	// 当前差评数
	private String initFlag;		// 初始标记

	public ScoreTeamMemberActual() {
		super();
	}

	public ScoreTeamMemberActual(String id){
		super(id);
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeePhone() {
		return employeePhone;
	}

	public void setEmployeePhone(String employeePhone) {
		this.employeePhone = employeePhone;
	}

	public Integer getCurrentScore() {
		return currentScore;
	}

	public void setCurrentScore(Integer currentScore) {
		this.currentScore = currentScore;
	}

	public Integer getCurrentGoodNum() {
		return currentGoodNum;
	}

	public void setCurrentGoodNum(Integer currentGoodNum) {
		this.currentGoodNum = currentGoodNum;
	}

	public Integer getCurrentBadNum() {
		return currentBadNum;
	}

	public void setCurrentBadNum(Integer currentBadNum) {
		this.currentBadNum = currentBadNum;
	}

	public String getInitFlag() {
		return initFlag;
	}

	public void setInitFlag(String initFlag) {
		this.initFlag = initFlag;
	}

	public Integer getBelongOffice() {
		return belongOffice;
	}

	public void setBelongOffice(Integer belongOffice) {
		this.belongOffice = belongOffice;
	}

	public String getEmployeePost() {
		return employeePost;
	}

	public void setEmployeePost(String employeePost) {
		this.employeePost = employeePost;
	}
	
	
}