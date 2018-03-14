package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;

public class EngineeringDepartmentEmployeePosition extends DataEntity2<EngineeringDepartmentEmployeePosition>{

	private static final long serialVersionUID = 1L;
	
	private Integer enginDepartId;
	private String positionType;
	private Integer employeeId;
	
	public Integer getEnginDepartId() {
		return enginDepartId;
	}
	public void setEnginDepartId(Integer enginDepartId) {
		this.enginDepartId = enginDepartId;
	}
	public String getPositionType() {
		return positionType;
	}
	public void setPositionType(String positionType) {
		this.positionType = positionType;
	}
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
}
