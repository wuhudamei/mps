
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity;


public class BizMessagegroup extends DataEntity<BizMessagegroup> {
	
	private static final long serialVersionUID = 1L;
	private String storeId;
	private String messageGroupType;
	private String employees;
	private String isEnable;
	private String description;
	private String employeesNo;
	private String empName;
	private String empId;
	
	
	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmployeesNo() {
		return employeesNo;
	}

	public void setEmployeesNo(String employeesNo) {
		this.employeesNo = employeesNo;
	}

	public BizMessagegroup() {
		super();
	}

	public BizMessagegroup(String id){
		super(id);
	}

	@Length(min=1, max=64, message="门店长度必须介于 1 和 64 之间")
	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	
	@Length(min=1, max=11, message="短信组类型长度必须介于 1 和 11 之间")
	public String getMessageGroupType() {
		return messageGroupType;
	}

	public void setMessageGroupType(String messageGroupType) {
		this.messageGroupType = messageGroupType;
	}
	
	@Length(min=0, max=255, message="员工长度必须介于 0 和 255 之间")
	public String getEmployees() {
		return employees;
	}

	public void setEmployees(String employees) {
		this.employees = employees;
	}
	
	@Length(min=0, max=1, message="启用标记长度必须介于 0 和 1 之间")
	public String getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(String isEnable) {
		this.isEnable = isEnable;
	}
	
	@Length(min=0, max=100, message="描述长度必须介于 0 和 100 之间")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}