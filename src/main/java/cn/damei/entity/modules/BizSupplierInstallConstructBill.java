
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import cn.damei.common.persistence.DataEntity2;


public class BizSupplierInstallConstructBill extends DataEntity2<BizSupplierInstallConstructBill> {
	
	private static final long serialVersionUID = 1L;
	private String constructBillCode;
	private Integer supplierInstallBillId;
	private Integer employeeGroupId;
	private String status;
	private Date statusDatetime;
	private Date realIntoDate;
	private Date realCompleteDate;
	private Date realAcceptDate;
	private Integer workerId;
	private String workerName;
	private String workerPhone;
	
	private String realIntoDateString;
	private String realCompleteDateString;
	private String realAcceptDateString;
	
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