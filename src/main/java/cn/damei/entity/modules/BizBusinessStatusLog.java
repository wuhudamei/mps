/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity2;

/**
 * 业务状态记录表Entity
 * 
 * @author wyb
 * @version 2017-03-16
 */
public class BizBusinessStatusLog extends DataEntity2<BizBusinessStatusLog> {

	private static final long serialVersionUID = 1L;
	private String businessType; // 业务类型
	private Integer businessOnlyMarkInt; // 业务唯一标识整形
	private String businessOnlyMarkVarchar; // 业务唯一标识字符型
	private String businessStatus; // 业务状态
	private String businessStatusName; // 业务状态 名称
	private Date statusDatetime; // 状态时间
	private String businessRemarks; // 业务备注
	private Integer businessEmployeeId; // 业务人员员工id
	private String businessEmployeeName;
	private String rejectedIdName;
	private String tianshu; // 延期天数
	private String yuany; // 延期原因
	private String shuom; // 延期说明
	private String planId; // 计划ID

	private String businessName;//节点
	
	private String businessDate;//期望验收日期、
	
	
	
	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getBusinessDate() {
		return businessDate;
	}

	public void setBusinessDate(String businessDate) {
		this.businessDate = businessDate;
	}

	public BizBusinessStatusLog() {
		super();
	}

	public BizBusinessStatusLog(Integer id) {
		super(id);
	}

	@Length(min = 0, max = 10, message = "业务类型长度必须介于 0 和 10 之间")
	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public Integer getBusinessOnlyMarkInt() {
		return businessOnlyMarkInt;
	}

	public String getTianshu() {
		return tianshu;
	}

	public void setTianshu(String tianshu) {
		this.tianshu = tianshu;
	}

	public String getYuany() {
		return yuany;
	}

	public void setYuany(String yuany) {
		this.yuany = yuany;
	}

	public String getShuom() {
		return shuom;
	}

	public void setShuom(String shuom) {
		this.shuom = shuom;
	}

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public void setBusinessOnlyMarkInt(Integer businessOnlyMarkInt) {
		this.businessOnlyMarkInt = businessOnlyMarkInt;
	}

	@Length(min = 0, max = 200, message = "业务唯一标识字符型长度必须介于 0 和 200 之间")
	public String getBusinessOnlyMarkVarchar() {
		return businessOnlyMarkVarchar;
	}

	public void setBusinessOnlyMarkVarchar(String businessOnlyMarkVarchar) {
		this.businessOnlyMarkVarchar = businessOnlyMarkVarchar;
	}

	@Length(min = 0, max = 10, message = "业务状态长度必须介于 0 和 10 之间")
	public String getBusinessStatus() {
		return businessStatus;
	}

	public void setBusinessStatus(String businessStatus) {
		this.businessStatus = businessStatus;
	}

	public Date getStatusDatetime() {
		return statusDatetime;
	}

	public void setStatusDatetime(Date statusDatetime) {
		this.statusDatetime = statusDatetime;
	}

	@Length(min = 0, max = 500, message = "业务备注长度必须介于 0 和 500 之间")
	public String getBusinessRemarks() {
		return businessRemarks;
	}

	public void setBusinessRemarks(String businessRemarks) {
		this.businessRemarks = businessRemarks;
	}

	public Integer getBusinessEmployeeId() {
		return businessEmployeeId;
	}

	public void setBusinessEmployeeId(Integer businessEmployeeId) {
		this.businessEmployeeId = businessEmployeeId;
	}

	public String getBusinessEmployeeName() {
		return businessEmployeeName;
	}

	public void setBusinessEmployeeName(String businessEmployeeName) {
		this.businessEmployeeName = businessEmployeeName;
	}

	public String getBusinessStatusName() {
		return businessStatusName;
	}

	public void setBusinessStatusName(String businessStatusName) {
		this.businessStatusName = businessStatusName;
	}

	public String getRejectedIdName() {
		return rejectedIdName;
	}

	public void setRejectedIdName(String rejectedIdName) {
		this.rejectedIdName = rejectedIdName;
	}

}