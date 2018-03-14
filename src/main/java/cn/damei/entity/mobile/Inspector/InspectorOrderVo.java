package cn.damei.entity.mobile.Inspector;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

public class InspectorOrderVo  extends DataEntity2<InspectorOrderVo>{

	/**
	 * 质检端,质检单Vo   @author  梅浩
	 */
	private static final long serialVersionUID = 1L;

	private Integer id; // 质检单主键
	private Integer orderId;//相关订单id
	private  String communityAddress;//顾客小区地址
	private String buildNumber;//楼
	private String buildUnit;//单元
	private String buildRoom;//室
	private String text;//条件查询的文本
	private String managerPhone;
	public String getManagerPhone() {
		return managerPhone;
	}
	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
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
	private String customerName;//顾客姓名
	private String customerPhone;//顾客手机号
	private Integer managerId;//发起约检的经理id
	private String managerName;//发起约检的经理姓名
	private Date actualStartDate;//订单的实际开工日期
	private String checkNodeName;//约检节点名称
	private Integer checkNodeId;//约检节点id
	private Date checkTime;//约检日期
	private String status; //状态
	private String lon;
	private String lat;

	private String delayDaysPm;
	private String delayDaysQc;
	private String isScrap;  //订单作废标识
	private Integer noScoreCount;//质检员未对工人评价的数量
	
	
	public Integer getNoScoreCount() {
		return noScoreCount;
	}
	public void setNoScoreCount(Integer noScoreCount) {
		this.noScoreCount = noScoreCount;
	}
	public String getIsScrap() {
		return isScrap;
	}
	public void setIsScrap(String isScrap) {
		this.isScrap = isScrap;
	}
	public String getDelayDaysPm() {
		return delayDaysPm;
	}

	public void setDelayDaysPm(String delayDaysPm) {
		this.delayDaysPm = delayDaysPm;
	}

	public String getDelayDaysQc() {
		return delayDaysQc;
	}

	public void setDelayDaysQc(String delayDaysQc) {
		this.delayDaysQc = delayDaysQc;
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getCommunityAddress() {
		return communityAddress;
	}
	public void setCommunityAddress(String communityAddress) {
		this.communityAddress = communityAddress;
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
	public Integer getManagerId() {
		return managerId;
	}
	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public Date getActualStartDate() {
		return actualStartDate;
	}
	public void setActualStartDate(Date actualStartDate) {
		this.actualStartDate = actualStartDate;
	}
	public String getCheckNodeName() {
		return checkNodeName;
	}
	public void setCheckNodeName(String checkNodeName) {
		this.checkNodeName = checkNodeName;
	}
	public Integer getCheckNodeId() {
		return checkNodeId;
	}
	public void setCheckNodeId(Integer checkNodeId) {
		this.checkNodeId = checkNodeId;
	}
	public Date getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}