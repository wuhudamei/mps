package cn.damei.entity.mobile.Manager;

import java.io.Serializable;
import java.util.Date;


public class ApplyRecheckEntity implements Serializable{


	private static final long serialVersionUID = 1L;
	
	private  String orderCustomerName;
	private String orderCustomerAddress;
	private Date actualContractStartDate;
	private Integer contractDays;
	private Integer recheckId;
	private String recheckType;
	private String recheckStatus;
	private Integer relatedBillId;
	private Integer managerId;
	private Integer checkItemId;
	private String isPassed;
	private String checkItemName;
	private String changeWay;
	private Date hopeApplyDate;
	
	public Date getHopeApplyDate() {
		return hopeApplyDate;
	}
	public void setHopeApplyDate(Date hopeApplyDate) {
		this.hopeApplyDate = hopeApplyDate;
	}
	
	public Integer getCheckItemId() {
		return checkItemId;
	}
	public void setCheckItemId(Integer checkItemId) {
		this.checkItemId = checkItemId;
	}
	public String getIsPassed() {
		return isPassed;
	}
	public void setIsPassed(String isPassed) {
		this.isPassed = isPassed;
	}
	public String getCheckItemName() {
		return checkItemName;
	}
	public void setCheckItemName(String checkItemName) {
		this.checkItemName = checkItemName;
	}
	public String getChangeWay() {
		return changeWay;
	}
	public void setChangeWay(String changeWay) {
		this.changeWay = changeWay;
	}

	
	public Integer getManagerId() {
		return managerId;
	}
	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}
	public Integer getRelatedBillId() {
		return relatedBillId;
	}
	public void setRelatedBillId(Integer relatedBillId) {
		this.relatedBillId = relatedBillId;
	}
	public String getOrderCustomerName() {
		return orderCustomerName;
	}
	public void setOrderCustomerName(String orderCustomerName) {
		this.orderCustomerName = orderCustomerName;
	}
	public String getOrderCustomerAddress() {
		return orderCustomerAddress;
	}
	public void setOrderCustomerAddress(String orderCustomerAddress) {
		this.orderCustomerAddress = orderCustomerAddress;
	}
	public Date getActualContractStartDate() {
		return actualContractStartDate;
	}
	public void setActualContractStartDate(Date actualContractStartDate) {
		this.actualContractStartDate = actualContractStartDate;
	}
	public Integer getContractDays() {
		return contractDays;
	}
	public void setContractDays(Integer contractDays) {
		this.contractDays = contractDays;
	}
	public Integer getRecheckId() {
		return recheckId;
	}
	public void setRecheckId(Integer recheckId) {
		this.recheckId = recheckId;
	}
	public String getRecheckType() {
		return recheckType;
	}
	public void setRecheckType(String recheckType) {
		this.recheckType = recheckType;
	}
	public String getRecheckStatus() {
		return recheckStatus;
	}
	public void setRecheckStatus(String recheckStatus) {
		this.recheckStatus = recheckStatus;
	}
	

}
