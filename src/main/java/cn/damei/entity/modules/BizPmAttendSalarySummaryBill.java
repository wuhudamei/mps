
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import cn.damei.common.persistence.DataEntity;


public class BizPmAttendSalarySummaryBill extends DataEntity<BizPmAttendSalarySummaryBill> {
	
	private static final long serialVersionUID = 1L;
	private String storeId;
	private String projectMode;
	private String pmAttendSalarySummaryBillCode;
	private String salaryMonth;
	private Date generatedDatetime;
	private String salrayBillCount;
	private String salaryTotalAmount;
	private String status;
	private Date statusDatetime;
	private String statusEmployeeId;
	private String statusDescribe;
	
	public BizPmAttendSalarySummaryBill() {
		super();
	}

	public BizPmAttendSalarySummaryBill(String id){
		super(id);
	}
	
	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	@Length(min=0, max=10, message="工程模式长度必须介于 0 和 10 之间")
	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}
	
	@Length(min=0, max=100, message="工资单汇总编号长度必须介于 0 和 100 之间")
	public String getPmAttendSalarySummaryBillCode() {
		return pmAttendSalarySummaryBillCode;
	}

	public void setPmAttendSalarySummaryBillCode(String pmAttendSalarySummaryBillCode) {
		this.pmAttendSalarySummaryBillCode = pmAttendSalarySummaryBillCode;
	}
	
	@Length(min=0, max=20, message="工资月份长度必须介于 0 和 20 之间")
	public String getSalaryMonth() {
		return salaryMonth;
	}

	public void setSalaryMonth(String salaryMonth) {
		this.salaryMonth = salaryMonth;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getGeneratedDatetime() {
		return generatedDatetime;
	}

	public void setGeneratedDatetime(Date generatedDatetime) {
		this.generatedDatetime = generatedDatetime;
	}
	
	@Length(min=0, max=11, message="工资单数量长度必须介于 0 和 11 之间")
	public String getSalrayBillCount() {
		return salrayBillCount;
	}

	public void setSalrayBillCount(String salrayBillCount) {
		this.salrayBillCount = salrayBillCount;
	}
	
	public String getSalaryTotalAmount() {
		return salaryTotalAmount;
	}

	public void setSalaryTotalAmount(String salaryTotalAmount) {
		this.salaryTotalAmount = salaryTotalAmount;
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
	
	@Length(min=0, max=11, message="状态操作人长度必须介于 0 和 11 之间")
	public String getStatusEmployeeId() {
		return statusEmployeeId;
	}

	public void setStatusEmployeeId(String statusEmployeeId) {
		this.statusEmployeeId = statusEmployeeId;
	}
	
	@Length(min=0, max=1000, message="状态描述长度必须介于 0 和 1000 之间")
	public String getStatusDescribe() {
		return statusDescribe;
	}

	public void setStatusDescribe(String statusDescribe) {
		this.statusDescribe = statusDescribe;
	}
	
}