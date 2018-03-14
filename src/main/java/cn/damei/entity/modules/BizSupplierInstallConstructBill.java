/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import cn.damei.common.persistence.DataEntity2;

/**
 * 供应商安装施工单表Entity
 * @author wyb
 * @version 2017-07-14
 */
public class BizSupplierInstallConstructBill extends DataEntity2<BizSupplierInstallConstructBill> {
	
	private static final long serialVersionUID = 1L;
	private String constructBillCode;		// 施工单号
	private Integer supplierInstallBillId;		// 供应商安装单id
	private Integer employeeGroupId;		// 工人组id
	private String status;		// 状态
	private Date statusDatetime;		// 状态日期时间
	private Date realIntoDate;		// 实际进场日期时间
	private Date realCompleteDate;		// 实际完工日期时间
	private Date realAcceptDate;		// 确认验收日期时间
	private Integer workerId;  // 组长id
	private String workerName;		// 组长姓名
	private String workerPhone;		// 组长电话
	
	private String realIntoDateString;		// 实际进场日期时间 字符串类型
	private String realCompleteDateString;		// 实际完工日期时间 字符串类型
	private String realAcceptDateString;		// 确认验收日期时间 字符串类型
	
	public BizSupplierInstallConstructBill() {
		super();
	}

	public BizSupplierInstallConstructBill(Integer id){
		super(id);
	}

	@Length(min=0, max=100, message="施工单号长度必须介于 0 和 100 之间")
	public String getConstructBillCode() {
		return constructBillCode;
	}

	public void setConstructBillCode(String constructBillCode) {
		this.constructBillCode = constructBillCode;
	}
	
	public Integer getSupplierInstallBillId() {
		return supplierInstallBillId;
	}

	public void setSupplierInstallBillId(Integer supplierInstallBillId) {
		this.supplierInstallBillId = supplierInstallBillId;
	}
	
	public Integer getEmployeeGroupId() {
		return employeeGroupId;
	}

	public void setEmployeeGroupId(Integer employeeGroupId) {
		this.employeeGroupId = employeeGroupId;
	}
	
	@Length(min=0, max=10, message="状态长度必须介于 0 和 10 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStatusDatetime() {
		return statusDatetime;
	}

	public void setStatusDatetime(Date statusDatetime) {
		this.statusDatetime = statusDatetime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRealIntoDate() {
		return realIntoDate;
	}

	public void setRealIntoDate(Date realIntoDate) {
		this.realIntoDate = realIntoDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRealCompleteDate() {
		return realCompleteDate;
	}

	public void setRealCompleteDate(Date realCompleteDate) {
		this.realCompleteDate = realCompleteDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRealAcceptDate() {
		return realAcceptDate;
	}

	public void setRealAcceptDate(Date realAcceptDate) {
		this.realAcceptDate = realAcceptDate;
	}

	public Integer getWorkerId() {
		return workerId;
	}

	public void setWorkerId(Integer workerId) {
		this.workerId = workerId;
	}

	public String getWorkerName() {
		return workerName;
	}

	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}

	public String getWorkerPhone() {
		return workerPhone;
	}

	public void setWorkerPhone(String workerPhone) {
		this.workerPhone = workerPhone;
	}

	public String getRealIntoDateString() {
		return realIntoDateString;
	}

	public void setRealIntoDateString(String realIntoDateString) {
		this.realIntoDateString = realIntoDateString;
	}

	public String getRealCompleteDateString() {
		return realCompleteDateString;
	}

	public void setRealCompleteDateString(String realCompleteDateString) {
		this.realCompleteDateString = realCompleteDateString;
	}

	public String getRealAcceptDateString() {
		return realAcceptDateString;
	}

	public void setRealAcceptDateString(String realAcceptDateString) {
		this.realAcceptDateString = realAcceptDateString;
	}
	
}