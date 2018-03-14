package cn.damei.entity.modules;

import java.util.Date;


import cn.damei.common.persistence.DataEntity2;
/**
 * 考勤单批次表
 * @author 
 *	cgh
 */
public class BizAttendBatch extends DataEntity2<BizAttendBatch> {

	private static final long serialVersionUID = 1L;
	//考勤批次编号
	private String attendBatchCode;
	//门店id
	private Integer storeId;
	
	//考勤批次月份
	private String attendBatchMonth;
	//考勤单数量
	private Integer attendBillCount;
	//状态 1 待审核 2. 审核通过 3 作废
	private String status;
	//状态日期时间
	private Date statusDatetime;
	//生成批次日期
	private Date batchDatetime;
	//生成批次操作员工id
	private Integer batchOperatorEmployeeId;
	
	//区域id
	private Integer enginDepartId;
	//区域名字
	private String departmentName;
	
	//工程模式
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
