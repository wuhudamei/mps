package cn.damei.entity.mobile.Worker;

import cn.damei.common.persistence.DataEntity2;

public class EmployeeGroupRa extends DataEntity2<EmployeeGroupRa>{

	/**
	 * @author wang
	 * 2016-10-17
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer empId;
	private Integer groupId;//工人组id
	private Integer isLead;
	private Integer salaryRatio;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public Integer getIsLead() {
		return isLead;
	}
	public void setIsLead(Integer isLead) {
		this.isLead = isLead;
	}
	public Integer getSalaryRatio() {
		return salaryRatio;
	}
	public void setSalaryRatio(Integer salaryRatio) {
		this.salaryRatio = salaryRatio;
	}
}
