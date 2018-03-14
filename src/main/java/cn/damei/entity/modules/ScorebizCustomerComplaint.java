/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import cn.damei.common.persistence.DataEntity;

/**
 * 顾客投诉Entity
 * @author lwc
 * @version 2017-04-12
 */
public class ScorebizCustomerComplaint extends DataEntity<ScorebizCustomerComplaint> {
	
	private static final long serialVersionUID = 1L;

	private Integer orderId;		// 订单id
	private String complainCode;	// 投诉编号
	private String complainType;	// 投诉类型
	private String complainContent;	// 投诉内容
	private Date complainTime;		// 投诉时间
	private Integer employeeId;		// 员工id
	private String employeeName;	// 员工姓名
	private String employeePhone;	// 员工电话
	
	public ScorebizCustomerComplaint() {
		super();
	}

	public ScorebizCustomerComplaint(String id){
		super(id);
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getComplainCode() {
		return complainCode;
	}

	public void setComplainCode(String complainCode) {
		this.complainCode = complainCode;
	}

	public String getComplainType() {
		return complainType;
	}

	public void setComplainType(String complainType) {
		this.complainType = complainType;
	}

	public String getComplainContent() {
		return complainContent;
	}

	public void setComplainContent(String complainContent) {
		this.complainContent = complainContent;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getComplainTime() {
		return complainTime;
	}

	public void setComplainTime(Date complainTime) {
		this.complainTime = complainTime;
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
	
}