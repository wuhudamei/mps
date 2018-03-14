/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import cn.damei.common.persistence.DataEntity;

/**
 * 团队成员评价Entity
 * @author lwc
 * @version 2017-04-12
 */
public class ScoreTeamMemberEvaluate extends DataEntity<ScoreTeamMemberEvaluate> {
	
	private static final long serialVersionUID = 1L;

	private Integer orderId;		// 订单id
	private Integer employeeId;		// 员工id
	private String employeeName;	// 员工姓名
	private String employeePhone;	// 员工电话
	private String evaluateType;	// 评价类型
	private Date evaluateTime;		// 评价时间

	public ScoreTeamMemberEvaluate() {
		super();
	}

	public ScoreTeamMemberEvaluate(String id){
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

	public String getEvaluateType() {
		return evaluateType;
	}

	public void setEvaluateType(String evaluateType) {
		this.evaluateType = evaluateType;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEvaluateTime() {
		return evaluateTime;
	}

	public void setEvaluateTime(Date evaluateTime) {
		this.evaluateTime = evaluateTime;
	}
	
}