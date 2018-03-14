package cn.damei.entity.mobile.Manager;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

public class Checksize extends DataEntity2<Checksize>{

	
	private static final long serialVersionUID = 1L;
	

	private Integer orderId;
	private String checksizeType;
	private String checksizeTypeName;
	private Date checksizeDate;
	private Integer checksizeEmployeeId;
	private String remarks;
	private String checksizeStatus;
	private String checksizeStatusName;
	private Date checksizeStatusDatetime;
	private Date supplierConfirmDate;
	private Date materialDepartmentDealDatetime;
	private Integer materialDepartmentDealEmployeeId;
	private String materialDepartmentDealReply;
	private Integer picCount;
	private String orderInstallItemId;
	
	private String checksizeDateString;
	private String createDateString;
	private String supplierConfirmDateString;
	
	private Date allowApplyChecksizeDate;
	
	private String daysPlanChecksizeString;
	private String installItemName;
	private String orderActualStartDateString;
	private String canApplyChecksizeDateString;
	
	private String text;
	
	
	public String getDaysPlanChecksizeString() {
		return daysPlanChecksizeString;
	}
	public void setDaysPlanChecksizeString(String daysPlanChecksizeString) {
		this.daysPlanChecksizeString = daysPlanChecksizeString;
	}
	public String getInstallItemName() {
		return installItemName;
	}
	public void setInstallItemName(String installItemName) {
		this.installItemName = installItemName;
	}
	public String getOrderActualStartDateString() {
		return orderActualStartDateString;
	}
	public void setOrderActualStartDateString(String orderActualStartDateString) {
		this.orderActualStartDateString = orderActualStartDateString;
	}
	public String getCanApplyChecksizeDateString() {
		return canApplyChecksizeDateString;
	}
	public void setCanApplyChecksizeDateString(String canApplyChecksizeDateString) {
		this.canApplyChecksizeDateString = canApplyChecksizeDateString;
	}
	public String getOrderInstallItemId() {
		return orderInstallItemId;
	}
	public void setOrderInstallItemId(String orderInstallItemId) {
		this.orderInstallItemId = orderInstallItemId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getChecksizeType() {
		return checksizeType;
	}
	public void setChecksizeType(String checksizeType) {
		this.checksizeType = checksizeType;
	}
	public Date getChecksizeDate() {
		return checksizeDate;
	}
	public void setChecksizeDate(Date checksizeDate) {
		this.checksizeDate = checksizeDate;
	}
	public Integer getChecksizeEmployeeId() {
		return checksizeEmployeeId;
	}
	public void setChecksizeEmployeeId(Integer checksizeEmployeeId) {
		this.checksizeEmployeeId = checksizeEmployeeId;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getChecksizeStatus() {
		return checksizeStatus;
	}
	public void setChecksizeStatus(String checksizeStatus) {
		this.checksizeStatus = checksizeStatus;
	}
	public Date getChecksizeStatusDatetime() {
		return checksizeStatusDatetime;
	}
	public void setChecksizeStatusDatetime(Date checksizeStatusDatetime) {
		this.checksizeStatusDatetime = checksizeStatusDatetime;
	}
	public Date getSupplierConfirmDate() {
		return supplierConfirmDate;
	}
	public void setSupplierConfirmDate(Date supplierConfirmDate) {
		this.supplierConfirmDate = supplierConfirmDate;
	}
	public Date getMaterialDepartmentDealDatetime() {
		return materialDepartmentDealDatetime;
	}
	public void setMaterialDepartmentDealDatetime(Date materialDepartmentDealDatetime) {
		this.materialDepartmentDealDatetime = materialDepartmentDealDatetime;
	}
	public Integer getMaterialDepartmentDealEmployeeId() {
		return materialDepartmentDealEmployeeId;
	}
	public void setMaterialDepartmentDealEmployeeId(Integer materialDepartmentDealEmployeeId) {
		this.materialDepartmentDealEmployeeId = materialDepartmentDealEmployeeId;
	}
	public String getMaterialDepartmentDealReply() {
		return materialDepartmentDealReply;
	}
	public void setMaterialDepartmentDealReply(String materialDepartmentDealReply) {
		this.materialDepartmentDealReply = materialDepartmentDealReply;
	}
	public String getChecksizeTypeName() {
		return checksizeTypeName;
	}
	public void setChecksizeTypeName(String checksizeTypeName) {
		this.checksizeTypeName = checksizeTypeName;
	}
	public String getChecksizeStatusName() {
		return checksizeStatusName;
	}
	public void setChecksizeStatusName(String checksizeStatusName) {
		this.checksizeStatusName = checksizeStatusName;
	}
	public Integer getPicCount() {
		return picCount;
	}
	public void setPicCount(Integer picCount) {
		this.picCount = picCount;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getChecksizeDateString() {
		return checksizeDateString;
	}
	public void setChecksizeDateString(String checksizeDateString) {
		this.checksizeDateString = checksizeDateString;
	}
	public String getCreateDateString() {
		return createDateString;
	}
	public void setCreateDateString(String createDateString) {
		this.createDateString = createDateString;
	}
	public String getSupplierConfirmDateString() {
		return supplierConfirmDateString;
	}
	public void setSupplierConfirmDateString(String supplierConfirmDateString) {
		this.supplierConfirmDateString = supplierConfirmDateString;
	}
	public Date getAllowApplyChecksizeDate() {
		return allowApplyChecksizeDate;
	}
	public void setAllowApplyChecksizeDate(Date allowApplyChecksizeDate) {
		this.allowApplyChecksizeDate = allowApplyChecksizeDate;
	}
	
	
}
