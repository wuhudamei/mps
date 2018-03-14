
package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity;


public class BizEmgrouprelation extends DataEntity<BizEmgrouprelation> {
	

	private static final long serialVersionUID = 1L;
	private String empId;
	private String groupId;
	private String isLeader;  
	private String salaryRatio;
	private String empName;
	private String managerName;
	private String managerId;
	private String no;
	private String workType;
	private String groupName;
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