package cn.damei.entity.modules;

import java.util.Date;


import cn.damei.common.persistence.DataEntity2;

public class BizAttendBatch extends DataEntity2<BizAttendBatch> {

	private static final long serialVersionUID = 1L;

	private String attendBatchCode;

	private Integer storeId;
	

	private String attendBatchMonth;

	private Integer attendBillCount;

	private String status;

	private Date statusDatetime;

	private Date batchDatetime;

	private Integer batchOperatorEmployeeId;
	

	private Integer enginDepartId;

	private String departmentName;
	

	private String projectMode;
	
	private String createName;
	
	
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	public String getAttendBatchCode() {
		return attendBatchCode;
	}
	public void setAttendBatchCode(String attendBatchCode) {
		this.attendBatchCode = attendBatchCode;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	
	public Integer getEnginDepartId() {
		return enginDepartId;
	}
	public void setEnginDepartId(Integer enginDepartId) {
		this.enginDepartId = enginDepartId;
	}
	
	public String getAttendBatchMonth() {
		return attendBatchMonth;
	}
	public void setAttendBatchMonth(String attendBatchMonth) {
		this.attendBatchMonth = attendBatchMonth;
	}
	public Integer getAttendBillCount() {
		return attendBillCount;
	}
	public void setAttendBillCount(Integer attendBillCount) {
		this.attendBillCount = attendBillCount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getStatusDatetime() {
		return statusDatetime;
	}
	public void setStatusDatetime(Date statusDatetime) {
		this.statusDatetime = statusDatetime;
	}
	public Date getBatchDatetime() {
		return batchDatetime;
	}
	public void setBatchDatetime(Date batchDatetime) {
		this.batchDatetime = batchDatetime;
	}
	public Integer getBatchOperatorEmployeeId() {
		return batchOperatorEmployeeId;
	}
	public void setBatchOperatorEmployeeId(Integer batchOperatorEmployeeId) {
		this.batchOperatorEmployeeId = batchOperatorEmployeeId;
	}
	public String getProjectMode() {
		return projectMode;
	}
	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
}
