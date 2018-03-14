
package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

public class OrderFullAction extends DataEntity2<OrderFullAction> {

	private static final long serialVersionUID = 1L;

	private Date enteringDate;

	private String keyboarder;

	private Date receivingDate;

	private Date  packageDate;

	private String packageBy;

	private Date assignedManagerDate;

	private String assignedManagerBy; 

	private Date assignedInspectorDate;

	private String assignedInspectorBy;
	

	private Date assignedDate;

	private String assignedBy;

	private String distributeType;
	

	private Integer storeId;

	private String orderNumber;
	
	
	public String getDistributeType() {
		return distributeType;
	}
	public void setDistributeType(String distributeType) {
		this.distributeType = distributeType;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public Date getEnteringDate() {
		return enteringDate;
	}
	public void setEnteringDate(Date enteringDate) {
		this.enteringDate = enteringDate;
	}
	public String getKeyboarder() {
		return keyboarder;
	}
	public void setKeyboarder(String keyboarder) {
		this.keyboarder = keyboarder;
	}
	public Date getReceivingDate() {
		return receivingDate;
	}
	public void setReceivingDate(Date receivingDate) {
		this.receivingDate = receivingDate;
	}
	public Date getPackageDate() {
		return packageDate;
	}
	public void setPackageDate(Date packageDate) {
		this.packageDate = packageDate;
	}
	public String getPackageBy() {
		return packageBy;
	}
	public void setPackageBy(String packageBy) {
		this.packageBy = packageBy;
	}
	public Date getAssignedManagerDate() {
		return assignedManagerDate;
	}
	public void setAssignedManagerDate(Date assignedManagerDate) {
		this.assignedManagerDate = assignedManagerDate;
	}
	public String getAssignedManagerBy() {
		return assignedManagerBy;
	}
	public void setAssignedManagerBy(String assignedManagerBy) {
		this.assignedManagerBy = assignedManagerBy;
	}

	public Date getAssignedInspectorDate() {
		return assignedInspectorDate;
	}
	public void setAssignedInspectorDate(Date assignedInspectorDate) {
		this.assignedInspectorDate = assignedInspectorDate;
	}
	public String getAssignedInspectorBy() {
		return assignedInspectorBy;
	}
	public void setAssignedInspectorBy(String assignedInspectorBy) {
		this.assignedInspectorBy = assignedInspectorBy;
	}
	public Date getAssignedDate() {
		return assignedDate;
	}
	public void setAssignedDate(Date assignedDate) {
		this.assignedDate = assignedDate;
	}
	public String getAssignedBy() {
		return assignedBy;
	}
	public void setAssignedBy(String assignedBy) {
		this.assignedBy = assignedBy;
	}
	
}