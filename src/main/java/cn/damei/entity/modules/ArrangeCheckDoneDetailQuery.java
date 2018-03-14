
package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity;


public class ArrangeCheckDoneDetailQuery extends DataEntity<ArrangeCheckDoneDetailQuery> {
	

	private String storeId;
	private String projectMode;
	private String engineDepartId;
	private String engineDepartName;
	private String orderNumber;
	private String customerName;
	private String managerName;
	private String pqcName;
	private String qcStatus;
	private String qcNodeName;
	private Date applyCheckDoneStartDate;
	private Date applyCheckDoneEndDate;
	private String customerInfo;
	private Date applyCheckDoneDate;
	private Date checkDoneDate;


	public String getEngineDepartName() {
		return engineDepartName;
	}

	public void setEngineDepartName(String engineDepartName) {
		this.engineDepartName = engineDepartName;
	}

	private Integer qcBillId;
	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}

	public String getEngineDepartId() {
		return engineDepartId;
	}

	public void setEngineDepartId(String engineDepartId) {
		this.engineDepartId = engineDepartId;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getPqcName() {
		return pqcName;
	}

	public void setPqcName(String pqcName) {
		this.pqcName = pqcName;
	}

	public String getQcStatus() {
		return qcStatus;
	}

	public void setQcStatus(String qcStatus) {
		this.qcStatus = qcStatus;
	}

	public String getQcNodeName() {
		return qcNodeName;
	}

	public void setQcNodeName(String qcNodeName) {
		this.qcNodeName = qcNodeName;
	}

	public Date getApplyCheckDoneStartDate() {
		return applyCheckDoneStartDate;
	}

	public void setApplyCheckDoneStartDate(Date applyCheckDoneStartDate) {
		this.applyCheckDoneStartDate = applyCheckDoneStartDate;
	}

	public Date getApplyCheckDoneEndDate() {
		return applyCheckDoneEndDate;
	}

	public void setApplyCheckDoneEndDate(Date applyCheckDoneEndDate) {
		this.applyCheckDoneEndDate = applyCheckDoneEndDate;
	}

	public Date getApplyCheckDoneDate() {
		return applyCheckDoneDate;
	}

	public void setApplyCheckDoneDate(Date applyCheckDoneDate) {
		this.applyCheckDoneDate = applyCheckDoneDate;
	}

	public Date getCheckDoneDate() {
		return checkDoneDate;
	}

	public void setCheckDoneDate(Date checkDoneDate) {
		this.checkDoneDate = checkDoneDate;
	}

	public Integer getQcBillId() {
		return qcBillId;
	}

	public void setQcBillId(Integer qcBillId) {
		this.qcBillId = qcBillId;
	}

	public String getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(String customerInfo) {
		this.customerInfo = customerInfo;
	}
}