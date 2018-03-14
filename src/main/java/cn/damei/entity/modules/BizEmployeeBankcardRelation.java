package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;

public class BizEmployeeBankcardRelation extends DataEntity2<BizEmployeeBankcardRelation>{


	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer employeeBankcardId;
	private Integer employeeId;
	private String employeeName;
	private String idCardNo;
	private String bankCardNo;
	private String relationName;
	private String relationIdCardNo;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getEmployeeBankcardId() {
		return employeeBankcardId;
	}
	public void setEmployeeBankcardId(Integer employeeBankcardId) {
		this.employeeBankcardId = employeeBankcardId;
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
	public String getIdCardNo() {
		return idCardNo;
	}
	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}
	public String getBankCardNo() {
		return bankCardNo;
	}
	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}
	public String getRelationName() {
		return relationName;
	}
	public void setRelationName(String relationName) {
		this.relationName = relationName;
	}
	public String getRelationIdCardNo() {
		return relationIdCardNo;
	}
	public void setRelationIdCardNo(String relationIdCardNo) {
		this.relationIdCardNo = relationIdCardNo;
	}
	
}
