
package cn.damei.entity.modules;

import java.util.Date;
import java.util.List;

import cn.damei.common.persistence.DataEntity;


public class OrderReportRelatedContract extends DataEntity<OrderReportRelatedContract> {

	private static final long serialVersionUID = 1L;

	private Integer storeId;

	private String customerName;

	private String customerPhone;

	private String communityName;

	private String address;

	private String buildNumber;

	private String buildUnit;

	private String buildRoom;

	private String reportRemarks;
	private String isAsked;

	private Integer reporterEmployeeId;

	private String reporterName;

	private String reporterPhone;

	private Date reportDatetime;

	private String reporterType;

	private String reportSourceType;

	private String reportStatus;

	private List<String> reportStatusList=null;

	private Date instoreDatetime;

	private String instoreRemarks;

	private Date signBillDatetime;

	private String signBillRemarks;

	private Date start;

	private Date end;

	private String searchText;


	private String serviceName;
	private String servicePhone;


	private String orderNumber;
	private String orderCustomerName;
	private String orderCommunityName;
	private Date   orderSignContractDate;
	private String orderDetailAddress;
private Integer orderId;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getOrderCustomerName() {
		return orderCustomerName;
	}

	public void setOrderCustomerName(String orderCustomerName) {
		this.orderCustomerName = orderCustomerName;
	}

	public String getOrderCommunityName() {
		return orderCommunityName;
	}

	public void setOrderCommunityName(String orderCommunityName) {
		this.orderCommunityName = orderCommunityName;
	}

	public Date getOrderSignContractDate() {
		return orderSignContractDate;
	}

	public void setOrderSignContractDate(Date orderSignContractDate) {
		this.orderSignContractDate = orderSignContractDate;
	}

	public String getOrderDetailAddress() {
		return orderDetailAddress;
	}

	public void setOrderDetailAddress(String orderDetailAddress) {
		this.orderDetailAddress = orderDetailAddress;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServicePhone() {
		return servicePhone;
	}

	public void setServicePhone(String servicePhone) {
		this.servicePhone = servicePhone;
	}

	public String getIsAsked() {
		return isAsked;
	}

	public void setIsAsked(String isAsked) {
		this.isAsked = isAsked;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
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

	public String getCommunityName() {
		return communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getReportRemarks() {
		return reportRemarks;
	}

	public void setReportRemarks(String reportRemarks) {
		this.reportRemarks = reportRemarks;
	}

	public Integer getReporterEmployeeId() {
		return reporterEmployeeId;
	}

	public void setReporterEmployeeId(Integer reporterEmployeeId) {
		this.reporterEmployeeId = reporterEmployeeId;
	}

	public String getReporterName() {
		return reporterName;
	}

	public void setReporterName(String reporterName) {
		this.reporterName = reporterName;
	}

	public String getReporterPhone() {
		return reporterPhone;
	}

	public void setReporterPhone(String reporterPhone) {
		this.reporterPhone = reporterPhone;
	}

	public Date getReportDatetime() {
		return reportDatetime;
	}

	public void setReportDatetime(Date reportDatetime) {
		this.reportDatetime = reportDatetime;
	}

	public String getReporterType() {
		return reporterType;
	}

	public void setReporterType(String reporterType) {
		this.reporterType = reporterType;
	}

	public String getReportSourceType() {
		return reportSourceType;
	}

	public void setReportSourceType(String reportSourceType) {
		this.reportSourceType = reportSourceType;
	}

	public String getReportStatus() {
		return reportStatus;
	}

	public void setReportStatus(String reportStatus) {
		this.reportStatus = reportStatus;
	}

	public Date getInstoreDatetime() {
		return instoreDatetime;
	}

	public void setInstoreDatetime(Date instoreDatetime) {
		this.instoreDatetime = instoreDatetime;
	}

	public String getInstoreRemarks() {
		return instoreRemarks;
	}

	public void setInstoreRemarks(String instoreRemarks) {
		this.instoreRemarks = instoreRemarks;
	}

	public Date getSignBillDatetime() {
		return signBillDatetime;
	}

	public void setSignBillDatetime(Date signBillDatetime) {
		this.signBillDatetime = signBillDatetime;
	}

	public String getSignBillRemarks() {
		return signBillRemarks;
	}

	public void setSignBillRemarks(String signBillRemarks) {
		this.signBillRemarks = signBillRemarks;
	}


	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public List<String> getReportStatusList() {
		return reportStatusList;
	}

	public void setReportStatusList(List<String> reportStatusList) {
		this.reportStatusList = reportStatusList;
	}



}