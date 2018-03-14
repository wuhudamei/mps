package cn.damei.entity.mobile.Manager;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

/**
 * 订单结算进度实体类
 * @author hyh
 *
 */
public class OrderSettlementSchedule extends DataEntity2<OrderSettlementSchedule> {
	
	private static final long serialVersionUID = 1L;
	
	private Integer orderId;  //订单Id
	private String customerName;		// 客户姓名
	private String communityName;		// 小区名称
	private String buildNumber;		// 几号楼
	private String buildUnit;		// 几单元
	private String buildRoom;		// 哪一室  
	private Date getOrderDatetime; //接单时间
	private Date pqcCheckedDatetime;//质检员确认验收约检节点时间
	private Date settlementClerkPassDatetime;//结算员通过约检节点时间
	private Date financeAffirmDatetime;//财务确认时间
	private Date pmApplyCompleteDatetime;//项目经理申请竣工
	private Integer pmEmployeeId;//项目经理Id
	private String queryParam; //查询参数
	private Date createMonthSettle;//劳资员生成月度结算单
	
	
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
