package cn.damei.entity.mobile.Manager;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

public class Checksize extends DataEntity2<Checksize>{

	
	private static final long serialVersionUID = 1L;
	

	private Integer orderId;     //订单id
	private String checksizeType;		// 复尺类型
	private String checksizeTypeName;		// 复尺类型名称
	private Date checksizeDate;		// 复尺日期
	private Integer checksizeEmployeeId;		// 复尺人员工id
	private String remarks;		// 备注
	private String checksizeStatus;	//复尺状态
	private String checksizeStatusName;	//复尺状态名称
	private Date checksizeStatusDatetime;	//复尺状态日期时间
	private Date supplierConfirmDate;	//供应商确认时间
	private Date materialDepartmentDealDatetime;	//材料部处理日期时间
	private Integer materialDepartmentDealEmployeeId;	//材料部处理人员工ID
	private String materialDepartmentDealReply;	//材料部处理回复
	private Integer picCount;	//图片数量
	private String orderInstallItemId; //订单安装项ID
	
	private String checksizeDateString; //期望复尺日期字符串
	private String createDateString; //申请复尺时间 字符串
	private String supplierConfirmDateString; //供应商确认时间 字符串
	
	private Date allowApplyChecksizeDate;//申请复尺计划日期
	
	private String daysPlanChecksizeString; //开工第几天可以复尺
	private String installItemName; //复尺内容
	private String orderActualStartDateString; //订单实际开工日期
	private String canApplyChecksizeDateString; //可以复尺日期
	
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
