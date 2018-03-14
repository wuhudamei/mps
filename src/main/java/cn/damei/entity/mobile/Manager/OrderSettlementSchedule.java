package cn.damei.entity.mobile.Manager;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;


public class OrderSettlementSchedule extends DataEntity2<OrderSettlementSchedule> {
	
	private static final long serialVersionUID = 1L;
	
	private Integer orderId;
	private String customerName;
	private String communityName;
	private String buildNumber;
	private String buildUnit;
	private String buildRoom;
	private Date getOrderDatetime;
	private Date pqcCheckedDatetime;
	private Date settlementClerkPassDatetime;
	private Date financeAffirmDatetime;
	private Date pmApplyCompleteDatetime;
	private Integer pmEmployeeId;
	private String queryParam;
	private Date createMonthSettle;
	
	
	public Date getCreateMonthSettle() {
		return createMonthSettle;
	}
	public void setCreateMonthSettle(Date createMonthSettle) {
		this.createMonthSettle = createMonthSettle;
	}
	public Integer getPmEmployeeId() {
		return pmEmployeeId;
	}
	public void setPmEmployeeId(Integer pmEmployeeId) {
		this.pmEmployeeId = pmEmployeeId;
	}
	public String getQueryParam() {
		return queryParam;
	}
	public void setQueryParam(String queryParam) {
		this.queryParam = queryParam;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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
	public Date getGetOrderDatetime() {
		return getOrderDatetime;
	}
	public void setGetOrderDatetime(Date getOrderDatetime) {
		this.getOrderDatetime = getOrderDatetime;
	}
	public Date getPqcCheckedDatetime() {
		return pqcCheckedDatetime;
	}
	public void setPqcCheckedDatetime(Date pqcCheckedDatetime) {
		this.pqcCheckedDatetime = pqcCheckedDatetime;
	}
	public Date getSettlementClerkPassDatetime() {
		return settlementClerkPassDatetime;
	}
	public void setSettlementClerkPassDatetime(Date settlementClerkPassDatetime) {
		this.settlementClerkPassDatetime = settlementClerkPassDatetime;
	}
	public Date getFinanceAffirmDatetime() {
		return financeAffirmDatetime;
	}
	public void setFinanceAffirmDatetime(Date financeAffirmDatetime) {
		this.financeAffirmDatetime = financeAffirmDatetime;
	}
	public Date getPmApplyCompleteDatetime() {
		return pmApplyCompleteDatetime;
	}
	public void setPmApplyCompleteDatetime(Date pmApplyCompleteDatetime) {
		this.pmApplyCompleteDatetime = pmApplyCompleteDatetime;
	}

	
}
