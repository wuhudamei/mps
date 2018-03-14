package cn.damei.entity.mobile.Inspector;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

public class Order  extends DataEntity2<Order>{

	/**
	 * 质检端,质检单Vo   @author  梅浩
	 */
	private static final long serialVersionUID = 1L;

	private Integer orderId; // 订单id
	private String projectMode; // 订单id
	private String communityName;//小区
	private String buildNumber;//几号楼
	private String buildUnit;//几单元
	private String buildRoom;//几室
	private String customerName;//客户姓名
	private String customerPhone;//客户电话
	private String itemManager;//项目经理
	private Date actualStartDate;//实际开工日期
	private Integer orderInspectorId;//质检员id
	private String managerPhone;
	public String getManagerPhone() {
		return managerPhone;
	}
	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}
	private Integer qcBillId; //抽检单id
	private String qcBillStatus; //抽检单状态
	private String text;//2017-1-5 app端加入搜索功能
	private String lon;
	private String lat;
	private String isScrap; // 是否作废
	
	
	public String getIsScrap() {
		return isScrap;
	}
	public void setIsScrap(String isScrap) {
		this.isScrap = isScrap;
	}
	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
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
	public String getItemManager() {
		return itemManager;
	}
	public void setItemManager(String itemManager) {
		this.itemManager = itemManager;
	}
	public Date getActualStartDate() {
		return actualStartDate;
	}
	public void setActualStartDate(Date actualStartDate) {
		this.actualStartDate = actualStartDate;
	}
	public Integer getOrderInspectorId() {
		return orderInspectorId;
	}
	public void setOrderInspectorId(Integer orderInspectorId) {
		this.orderInspectorId = orderInspectorId;
	}
	public Integer getQcBillId() {
		return qcBillId;
	}
	public void setQcBillId(Integer qcBillId) {
		this.qcBillId = qcBillId;
	}
	public String getQcBillStatus() {
		return qcBillStatus;
	}
	public void setQcBillStatus(String qcBillStatus) {
		this.qcBillStatus = qcBillStatus;
	}

	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}
}
