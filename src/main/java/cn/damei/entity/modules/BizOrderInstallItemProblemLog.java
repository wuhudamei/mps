/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity2;



/**
 * 订单安装项问题日志Entity
 * @author 汪文
 * @version 2017-02-20
 */
public class BizOrderInstallItemProblemLog extends DataEntity2<BizOrderInstallItemProblemLog> {
	
	private static final long serialVersionUID = 1L;
	private Integer businessProblemId;		// 问题上报id
	private String status;		// 状态
	private String problemSolveRole;		// 问题处理角色
	private Integer problemSolveEmployeeId;		// 问题处理员工id
	private String problemSolveEmployeeName;		// 问题处理员工 名字
	private String problemSolveEmployeePhone;		// 问题处理员工 电话
	private String problemSolveNotes;		// 问题处理说明
	private String statusName;	//状态名称
	private BizOrderInstallItemProblem bizOrderInstallItemProblem;//问题上报
	
	private String realName;
	
	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	

	public BizOrderInstallItemProblem getBizOrderInstallItemProblem() {
		return bizOrderInstallItemProblem;
	}

	public void setBizOrderInstallItemProblem(BizOrderInstallItemProblem bizOrderInstallItemProblem) {
		this.bizOrderInstallItemProblem = bizOrderInstallItemProblem;
	}

	public BizOrderInstallItemProblemLog() {
		super();
	}

	public BizOrderInstallItemProblemLog(Integer id){
		super(id);
	}

	
	public Integer getBusinessProblemId() {
		return businessProblemId;
	}

	public void setBusinessProblemId(Integer businessProblemId) {
		this.businessProblemId = businessProblemId;
	}

	@Length(min=0, max=10, message="状态长度必须介于 0 和 10 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=10, message="问题处理角色长度必须介于 0 和 10 之间")
	public String getProblemSolveRole() {
		return problemSolveRole;
	}

	public void setProblemSolveRole(String problemSolveRole) {
		this.problemSolveRole = problemSolveRole;
	}
	
	public Integer getProblemSolveEmployeeId() {
		return problemSolveEmployeeId;
	}

	public void setProblemSolveEmployeeId(Integer problemSolveEmployeeId) {
		this.problemSolveEmployeeId = problemSolveEmployeeId;
	}
	
	@Length(min=0, max=500, message="问题处理说明长度必须介于 0 和 500 之间")
	public String getProblemSolveNotes() {
		return problemSolveNotes;
	}

	public void setProblemSolveNotes(String problemSolveNotes) {
		this.problemSolveNotes = problemSolveNotes;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getProblemSolveEmployeeName() {
		return problemSolveEmployeeName;
	}

	public void setProblemSolveEmployeeName(String problemSolveEmployeeName) {
		this.problemSolveEmployeeName = problemSolveEmployeeName;
	}

	public String getProblemSolveEmployeePhone() {
		return problemSolveEmployeePhone;
	}

	public void setProblemSolveEmployeePhone(String problemSolveEmployeePhone) {
		this.problemSolveEmployeePhone = problemSolveEmployeePhone;
	}
	
	
}