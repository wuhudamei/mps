/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity;

/**
 * 工人组关联表Entity
 * @author qhy
 * @version 2016-08-28
 */
public class BizEmgrouprelation extends DataEntity<BizEmgrouprelation> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String empId;//工人ID
	private String groupId;		// 组ID
	private String isLeader;  
	private String salaryRatio;
	private String empName; //姓名
	private String managerName;//项目经理姓名
	private String managerId;//
	private String no;//工人编号
	private String workType;//工种
	private String groupName;//
	public BizEmgrouprelation() {
		super();
	}
	
	public String getEmpId() {
		return empId;
	}
	public String getEmpName() {
		return empName;
	}
	public String getGroupId() {
		return groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public String getIsLeader() {
		return isLeader;
	}
	public String getManagerId() {
		return managerId;
	}
	public String getManagerName() {
		return managerName;
	}
	
	
	public String getNo() {
		return no;
	}
	public String getSalaryRatio() {
		return salaryRatio;
	}
	public String getWorkType() {
		return workType;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public void setIsLeader(String isLeader) {
		this.isLeader = isLeader;}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public void setNo(String no) {
		this.no = no;
	}
	public void setSalaryRatio(String salaryRatio) {
		this.salaryRatio = salaryRatio;
	}
	public void setWorkType(String workType) {
		this.workType = workType;
	}


	
}