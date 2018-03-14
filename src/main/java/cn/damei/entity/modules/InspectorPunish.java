package cn.damei.entity.modules;

import java.util.Date;
import java.util.List;

import cn.damei.common.persistence.DataEntity2;

/**
 * 质检罚款Entity
 */
public class InspectorPunish extends DataEntity2<InspectorPunish> {
	
	private static final long serialVersionUID = 1L;
	private Integer orderId;
	private Integer pmEmployeeId;
	private Integer pmSettleBillId;		// 结算单id
	private String type; //类型
	private String orderNumber;	//订单编号
	private String customerAddress; //地址
	private String communityName; //小区
	private String buildNumber; //几号楼
	private String buildUnit; //几单元
	private String buildRoom; //几室
	private String customerName; //顾客
	
	private String qcBillCode; //质检单编号
	private String checkEmployee;//提交人
	private Date actualCheckDate;//提交时间
	
	private String qcBillCheckKindName; //检查分类
	private String qcBillCheckItemName; //检查项
	
	
	private Double money;//罚款金额
	
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