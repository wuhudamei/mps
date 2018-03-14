/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import java.util.Date;
import java.util.List;

import cn.damei.common.persistence.DataEntity;

/**
 * 返单关联合同信息Entity
 * @author mh
 * @version 2017-05-08
 */
public class OrderReportRelatedContract extends DataEntity<OrderReportRelatedContract> {

	private static final long serialVersionUID = 1L;

	private Integer storeId;//门店ID

	private String customerName;//客户姓名

	private String customerPhone;//客户手机号

	private String communityName;//小区名称

	private String address;//详细地址

	private String buildNumber;//楼号

	private String buildUnit;//单元门

	private String buildRoom;//门牌号

	private String reportRemarks;//上报备注
	private String isAsked; //是否在大美装饰管理平台咨询过

	private Integer reporterEmployeeId;//返单上报人员工Id

	private String reporterName;//返单上报人姓名

	private String reporterPhone;//返单上报人手机号

	private Date reportDatetime;//返单上报日期

	private String reporterType;//返单上报人类型  1:项目经理 2：质检 3：工人 4：工程部 5：审计部 6：财务部 7：人力部

	private String reportSourceType;//返单上报源类型 1:项目app 2：质检app 3：工人app 4：后台

	private String reportStatus;//返单上报状态

	private List<String> reportStatusList=null;//返单上报状态集合

	private Date instoreDatetime;//进店日期

	private String instoreRemarks;//进店备注

	private Date signBillDatetime;//签单日期

	private String signBillRemarks;//签单备注

	private Date start;

	private Date end;

	private String searchText;


	private String serviceName;//客服姓名
	private String servicePhone;//客服电话


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