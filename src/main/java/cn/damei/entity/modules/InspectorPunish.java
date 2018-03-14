package cn.damei.entity.modules;

import java.util.Date;
import java.util.List;

import cn.damei.common.persistence.DataEntity2;


public class InspectorPunish extends DataEntity2<InspectorPunish> {
	
	private static final long serialVersionUID = 1L;
	private Integer orderId;
	private Integer pmEmployeeId;
	private Integer pmSettleBillId;
	private String type;
	private String orderNumber;
	private String customerAddress;
	private String communityName;
	private String buildNumber;
	private String buildUnit;
	private String buildRoom;
	private String customerName;
	
	private String qcBillCode;
	private String checkEmployee;
	private Date actualCheckDate;
	
	private String qcBillCheckKindName;
	private String qcBillCheckItemName;
	
	
	private Double money;
	
	private List<ReportCheckBreakSettleBill> reportCheckBreakSettleBillList;
	
	private String breakDescribe;
	

	public Integer getPmEmployeeId() {
		return pmEmployeeId;
	}

	public void setPmEmployeeId(Integer pmEmployeeId) {
		this.pmEmployeeId = pmEmployeeId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getBreakDescribe() {
		return breakDescribe;
	}

	public void setBreakDescribe(String breakDescribe) {
		this.breakDescribe = breakDescribe;
	}

	public Integer getPmSettleBillId() {
		return pmSettleBillId;
	}

	public void setPmSettleBillId(Integer pmSettleBillId) {
		this.pmSettleBillId = pmSettleBillId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
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

	public String getCheckEmployee() {
		return checkEmployee;
	}

	public void setCheckEmployee(String checkEmployee) {
		this.checkEmployee = checkEmployee;
	}

	public Date getActualCheckDate() {
		return actualCheckDate;
	}

	public void setActualCheckDate(Date actualCheckDate) {
		this.actualCheckDate = actualCheckDate;
	}

	public String getQcBillCheckKindName() {
		return qcBillCheckKindName;
	}

	public void setQcBillCheckKindName(String qcBillCheckKindName) {
		this.qcBillCheckKindName = qcBillCheckKindName;
	}

	public String getQcBillCheckItemName() {
		return qcBillCheckItemName;
	}

	public void setQcBillCheckItemName(String qcBillCheckItemName) {
		this.qcBillCheckItemName = qcBillCheckItemName;
	}


	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public List<ReportCheckBreakSettleBill> getReportCheckBreakSettleBillList() {
		return reportCheckBreakSettleBillList;
	}

	public void setReportCheckBreakSettleBillList(List<ReportCheckBreakSettleBill> reportCheckBreakSettleBillList) {
		this.reportCheckBreakSettleBillList = reportCheckBreakSettleBillList;
	}

	public String getQcBillCode() {
		return qcBillCode;
	}

	public void setQcBillCode(String qcBillCode) {
		this.qcBillCode = qcBillCode;
	}
	

	
	
}