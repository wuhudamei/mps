package cn.damei.entity.mobile.home;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 质检报告Entity
 * @author wyb
 * @version 2016-11-16
 */
public class Report implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer qcBillId; //质检id
	private Integer qcCheckNodeId; //约检节点
	private String qcCheckNodeName; //检查内容
	private Date checkDatetime; //检查时间
	private String isRecheck;	//是否复检  1复检
	private String qcBillType;	//1约检  2抽检
	private String inspector; //质检人
	private Integer viewCount;//日志表

	private Order order; //订单
	
	
	private String communityName;	//小区
	private String buildNumber;	//几号楼
	private String buildUnit;	//几单元
	private String buildRoom;	//几室
	
	private Integer picCount; //图片数量
	private Integer itemCount; //检查项总量
	private Integer passedCount; //合格项数量
	private Integer noPassedCount; //不合格项数量
	
	private List<CheckItem> checkItemList; //检查项列表

	public Integer getQcBillId() {
		return qcBillId;
	}

	public void setQcBillId(Integer qcBillId) {
		this.qcBillId = qcBillId;
	}

	public Integer getQcCheckNodeId() {
		return qcCheckNodeId;
	}

	public void setQcCheckNodeId(Integer qcCheckNodeId) {
		this.qcCheckNodeId = qcCheckNodeId;
	}

	public String getQcCheckNodeName() {
		return qcCheckNodeName;
	}

	public void setQcCheckNodeName(String qcCheckNodeName) {
		this.qcCheckNodeName = qcCheckNodeName;
	}

	public Date getCheckDatetime() {
		return checkDatetime;
	}

	public void setCheckDatetime(Date checkDatetime) {
		this.checkDatetime = checkDatetime;
	}

	public String getIsRecheck() {
		return isRecheck;
	}

	public void setIsRecheck(String isRecheck) {
		this.isRecheck = isRecheck;
	}

	public String getQcBillType() {
		return qcBillType;
	}

	public void setQcBillType(String qcBillType) {
		this.qcBillType = qcBillType;
	}

	public String getInspector() {
		return inspector;
	}

	public void setInspector(String inspector) {
		this.inspector = inspector;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
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

	public Integer getPicCount() {
		return picCount;
	}

	public void setPicCount(Integer picCount) {
		this.picCount = picCount;
	}

	public Integer getItemCount() {
		return itemCount;
	}

	public void setItemCount(Integer itemCount) {
		this.itemCount = itemCount;
	}

	public Integer getPassedCount() {
		return passedCount;
	}

	public void setPassedCount(Integer passedCount) {
		this.passedCount = passedCount;
	}

	public Integer getNoPassedCount() {
		return noPassedCount;
	}

	public void setNoPassedCount(Integer noPassedCount) {
		this.noPassedCount = noPassedCount;
	}

	public List<CheckItem> getCheckItemList() {
		return checkItemList;
	}

	public void setCheckItemList(List<CheckItem> checkItemList) {
		this.checkItemList = checkItemList;
	}

	public Integer getViewCount() {
		return viewCount;
	}

	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}
	
}