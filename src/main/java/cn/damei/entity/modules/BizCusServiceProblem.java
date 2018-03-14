/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import cn.damei.common.persistence.DataEntity;

/**
 * 售后问题反馈详情Entity
 *
 * @author joseph
 * @version 2017-06-23
 */
public class BizCusServiceProblem extends DataEntity<BizCusServiceProblem> {

	private static final long serialVersionUID = 1L;
	private String storeId; // store_id
	private String storeCode; // store_code
	private String orderId; // order_id
	private String orderCode; // order_code
	private String workOrderCode; // work_order_code
	private String customerAddress; // customer_address
	private String customerName; // customer_name
	private String customerMobile; // customer_mobile
	private String contractorName; // contractor_name
	private String contractorMobile; // contractor_mobile
	private String complaintSource; // 售後投訴來源
	private String supervisorName; // supervisor_name
	private String supervisorMobile; // supervisor_mobile
	private Date problemCreateDatetime; // problem_create_datetime
	private String problemDescribe; // problem_describe
	private String liableDepartmentCode; // liable_department_code
	private String liableTypeCode1; // liable_type_code_1
	private String liableTypeCode2; // liable_type_code_2
	private String importantDegreeCode1; // important_degree_code_1
	private String importantDegreeCode2; // important_degree_code_2
	private String photoUrl; // photo_url
	private String status; // 状态 10已接收;20已处理 ;30已驳回
	private Date statusdatetime; // 状态日期(添加)
	private String statusdescribe; // 状态描述(处理/驳回)
	private Integer statusoperatoremployeeid; // 状态 操作人员ID(添加)
	private String complaintTypeName;
	private String cusServiceId; // 售后ID
	private String pqcName;
	private String managerName;
	private Date start;
	private Date end;
	private String xuhao;// 页面上传图片使用的ID
	private String xuhaourl; // 页面上传图片使用的url
	private String cusServicefla; // 是否为售后字段 1为是 0 为否
	private String complaintProblemNr;

	// 2017-07-26 接口调整 新增字段
	private String questionType1;
	private String questionType2;
	private String workSource;

	// 接口对接使用的字段

	private String beforehandDatehou; // 执行时限
	private String disposefaHou; // 执行方案
	private String key; // 密钥
	private String operationUser; // 操作人
	private String workorderId; // 工单id/售后id
	private String operationTime; // 操作时间
	private String treamentTime; // 预计完成时间
	private String remarks; // 备注（驳回为驳回意见 处理为处理方案）

	public String getComplaintTypeName() {
		return complaintTypeName;
	}

	public void setComplaintTypeName(String complaintTypeName) {
		this.complaintTypeName = complaintTypeName;
	}

	public String getQuestionType1() {
		return questionType1;
	}

	public void setQuestionType1(String questionType1) {
		this.questionType1 = questionType1;
	}

	public String getDisposefaHou() {
		return disposefaHou;
	}

	public void setDisposefaHou(String disposefaHou) {
		this.disposefaHou = disposefaHou;
	}

	public String getCusServicefla() {
		return cusServicefla;
	}

	public String getKey() {
		return key;
	}

	public String getBeforehandDatehou() {
		return beforehandDatehou;
	}

	public void setBeforehandDatehou(String beforehandDatehou) {
		this.beforehandDatehou = beforehandDatehou;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getOperationUser() {
		return operationUser;
	}

	public void setOperationUser(String operationUser) {
		this.operationUser = operationUser;
	}

	public String getWorkorderId() {
		return workorderId;
	}

	public void setWorkorderId(String workorderId) {
		this.workorderId = workorderId;
	}

	public String getOperationTime() {
		return operationTime;
	}

	public void setOperationTime(String operationTime) {
		this.operationTime = operationTime;
	}

	public String getTreamentTime() {
		return treamentTime;
	}

	public void setTreamentTime(String treamentTime) {
		this.treamentTime = treamentTime;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void setCusServicefla(String cusServicefla) {
		this.cusServicefla = cusServicefla;
	}

	public String getQuestionType2() {
		return questionType2;
	}

	public void setQuestionType2(String questionType2) {
		this.questionType2 = questionType2;
	}

	public String getWorkSource() {
		return workSource;
	}

	public String getXuhao() {
		return xuhao;
	}

	public void setXuhao(String xuhao) {
		this.xuhao = xuhao;
	}

	public String getXuhaourl() {
		return xuhaourl;
	}

	public void setXuhaourl(String xuhaourl) {
		this.xuhaourl = xuhaourl;
	}

	public void setWorkSource(String workSource) {
		this.workSource = workSource;
	}

	public String getPqcName() {
		return pqcName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getComplaintSource() {
		return complaintSource;
	}

	public String getCusServiceId() {
		return cusServiceId;
	}

	public void setCusServiceId(String cusServiceId) {
		this.cusServiceId = cusServiceId;
	}

	public void setComplaintSource(String complaintSource) {
		this.complaintSource = complaintSource;
	}

	public Date getStatusdatetime() {
		return statusdatetime;
	}

	public void setStatusdatetime(Date statusdatetime) {
		this.statusdatetime = statusdatetime;
	}

	public String getStatusdescribe() {
		return statusdescribe;
	}

	public void setStatusdescribe(String statusdescribe) {
		this.statusdescribe = statusdescribe;
	}

	public Integer getStatusoperatoremployeeid() {
		return statusoperatoremployeeid;
	}

	public String getComplaintProblemNr() {
		return complaintProblemNr;
	}

	public void setComplaintProblemNr(String complaintProblemNr) {
		this.complaintProblemNr = complaintProblemNr;
	}

	public void setStatusoperatoremployeeid(Integer statusoperatoremployeeid) {
		this.statusoperatoremployeeid = statusoperatoremployeeid;
	}

	public void setPqcName(String pqcName) {
		this.pqcName = pqcName;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public BizCusServiceProblem() {
		super();
	}

	public BizCusServiceProblem(String id) {
		super(id);
	}

	@Length(min = 0, max = 11, message = "store_id长度必须介于 0 和 11 之间")
	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	@Length(min = 0, max = 50, message = "store_code长度必须介于 0 和 50 之间")
	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	@Length(min = 0, max = 11, message = "order_id长度必须介于 0 和 11 之间")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Length(min = 0, max = 100, message = "order_code长度必须介于 0 和 100 之间")
	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	@Length(min = 0, max = 100, message = "work_order_code长度必须介于 0 和 100 之间")
	public String getWorkOrderCode() {
		return workOrderCode;
	}

	public void setWorkOrderCode(String workOrderCode) {
		this.workOrderCode = workOrderCode;
	}

	@Length(min = 0, max = 255, message = "customer_address长度必须介于 0 和 255 之间")
	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	@Length(min = 0, max = 20, message = "customer_name长度必须介于 0 和 20 之间")
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@Length(min = 0, max = 11, message = "customer_mobile长度必须介于 0 和 11 之间")
	public String getCustomerMobile() {
		return customerMobile;
	}

	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}

	@Length(min = 0, max = 20, message = "contractor_name长度必须介于 0 和 20 之间")
	public String getContractorName() {
		return contractorName;
	}

	public void setContractorName(String contractorName) {
		this.contractorName = contractorName;
	}

	@Length(min = 0, max = 11, message = "contractor_mobile长度必须介于 0 和 11 之间")
	public String getContractorMobile() {
		return contractorMobile;
	}

	public void setContractorMobile(String contractorMobile) {
		this.contractorMobile = contractorMobile;
	}

	@Length(min = 0, max = 20, message = "supervisor_name长度必须介于 0 和 20 之间")
	public String getSupervisorName() {
		return supervisorName;
	}

	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}

	@Length(min = 0, max = 11, message = "supervisor_mobile长度必须介于 0 和 11 之间")
	public String getSupervisorMobile() {
		return supervisorMobile;
	}

	public void setSupervisorMobile(String supervisorMobile) {
		this.supervisorMobile = supervisorMobile;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getProblemCreateDatetime() {
		return problemCreateDatetime;
	}

	public void setProblemCreateDatetime(Date problemCreateDatetime) {
		this.problemCreateDatetime = problemCreateDatetime;
	}

	@Length(min = 0, max = 1000, message = "problem_describe长度必须介于 0 和 1000 之间")
	public String getProblemDescribe() {
		return problemDescribe;
	}

	public void setProblemDescribe(String problemDescribe) {
		this.problemDescribe = problemDescribe;
	}

	@Length(min = 0, max = 20, message = "liable_department_code长度必须介于 0 和 20 之间")
	public String getLiableDepartmentCode() {
		return liableDepartmentCode;
	}

	public void setLiableDepartmentCode(String liableDepartmentCode) {
		this.liableDepartmentCode = liableDepartmentCode;
	}

	@Length(min = 0, max = 20, message = "liable_type_code_1长度必须介于 0 和 20 之间")
	public String getLiableTypeCode1() {
		return liableTypeCode1;
	}

	public void setLiableTypeCode1(String liableTypeCode1) {
		this.liableTypeCode1 = liableTypeCode1;
	}

	@Length(min = 0, max = 20, message = "liable_type_code_2长度必须介于 0 和 20 之间")
	public String getLiableTypeCode2() {
		return liableTypeCode2;
	}

	public void setLiableTypeCode2(String liableTypeCode2) {
		this.liableTypeCode2 = liableTypeCode2;
	}

	@Length(min = 0, max = 20, message = "important_degree_code_1长度必须介于 0 和 20 之间")
	public String getImportantDegreeCode1() {
		return importantDegreeCode1;
	}

	public void setImportantDegreeCode1(String importantDegreeCode1) {
		this.importantDegreeCode1 = importantDegreeCode1;
	}

	@Length(min = 0, max = 20, message = "important_degree_code_2长度必须介于 0 和 20 之间")
	public String getImportantDegreeCode2() {
		return importantDegreeCode2;
	}

	public void setImportantDegreeCode2(String importantDegreeCode2) {
		this.importantDegreeCode2 = importantDegreeCode2;
	}

	@Length(min = 0, max = 1000, message = "photo_url长度必须介于 0 和 1000 之间")
	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

}