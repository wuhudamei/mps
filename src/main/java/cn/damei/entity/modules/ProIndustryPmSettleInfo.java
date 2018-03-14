package cn.damei.entity.modules;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.damei.common.persistence.DataEntity2;


public class ProIndustryPmSettleInfo extends DataEntity2<ProIndustryPmSettleInfo>{
	private static final long serialVersionUID = 1L;
	private Integer orderId;
	private Integer storeId;
	private Integer projectMode;
	private String orderNum;
	private Integer enginDepartId;
	private String departmentName;
	private List<Integer> enginDepartIds = new ArrayList<Integer>();
	private String communityName;
	private String buildNumber;
	private String buildUnit;
	private String buildRoom;
	
	private String customerName;
	private String customerPhone;
	private String itemCustomer;
	private String itemPhone;
	private String inspector;
	private Date acceptCheckDatetime;
	private Date completeAuditPassTime;
	private Double secondPayment;
	private Date secondPaymentDate;
	private Double finalPayment;
	private Date finalPaymentDate;
	
	public Date getCompleteAuditPassTime() {
		return completeAuditPassTime;
	}
	public void setCompleteAuditPassTime(Date completeAuditPassTime) {
		this.completeAuditPassTime = completeAuditPassTime;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public Integer getProjectMode() {
		return projectMode;
	}
	public void setProjectMode(Integer projectMode) {
		this.projectMode = projectMode;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public Integer getEnginDepartId() {
		return enginDepartId;
	}
	public void setEnginDepartId(Integer enginDepartId) {
		this.enginDepartId = enginDepartId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public List<Integer> getEnginDepartIds() {
		return enginDepartIds;
	}
	public void setEnginDepartIds(List<Integer> enginDepartIds) {
		this.enginDepartIds = enginDepartIds;
	}
	public String getCommunityName() {
		return communityName;
	}
	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}
	public String getBuildNumber() {
		return buildNumber;
	}
	public void setBuildNumber(String buildNumber) {
		this.buildNumber = buildNumber;
	}
	public String getBuildUnit() {
		return buildUnit;
	}
	public void setBuildUnit(String buildUnit) {
		this.buildUnit = buildUnit;
	}
	public String getBuildRoom() {
		return buildRoom;
	}
	public void setBuildRoom(String buildRoom) {
		this.buildRoom = buildRoom;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	public String getItemCustomer() {
		return itemCustomer;
	}
	public void setItemCustomer(String itemCustomer) {
		this.itemCustomer = itemCustomer;
	}
	public String getItemPhone() {
		return itemPhone;
	}
	public void setItemPhone(String itemPhone) {
		this.itemPhone = itemPhone;
	}
	public String getInspector() {
		return inspector;
	}
	public void setInspector(String inspector) {
		this.inspector = inspector;
	}
	public Date getAcceptCheckDatetime() {
		return acceptCheckDatetime;
	}
	public void setAcceptCheckDatetime(Date acceptCheckDatetime) {
		this.acceptCheckDatetime = acceptCheckDatetime;
	}
	public Double getSecondPayment() {
		return secondPayment;
	}
	public void setSecondPayment(Double secondPayment) {
		this.secondPayment = secondPayment;
	}
	public Date getSecondPaymentDate() {
		return secondPaymentDate;
	}
	public void setSecondPaymentDate(Date secondPaymentDate) {
		this.secondPaymentDate = secondPaymentDate;
	}
	public Double getFinalPayment() {
		return finalPayment;
	}
	public void setFinalPayment(Double finalPayment) {
		this.finalPayment = finalPayment;
	}
	public Date getFinalPaymentDate() {
		return finalPaymentDate;
	}
	public void setFinalPaymentDate(Date finalPaymentDate) {
		this.finalPaymentDate = finalPaymentDate;
	}
	
	
}
