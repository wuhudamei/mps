/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity;

/**
 * 结算二期款Entity
 * @author 梅浩
 * @version 2016-12-28
 */
public class ErqiEntity extends DataEntity<ErqiEntity> {
	
	private static final long serialVersionUID = 1L;
	private Integer storeId;
	private String itemManagerName;
	private Date start;
	private Date end;
	private  String orderNumber;
	private String customerName;	// 客户姓名
	private String communityName;		// 小区名称
	private String buildNumber;		// 几号楼
	private String buildUnit;		// 几单元
	private String buildRoom;		// 哪一室
	private Date applyCheckDate;
	private String inspectName;
	private Date yanshouDate;
	private Integer orderId;
	private Integer engineDepartId;
	private String engineDepartName;
	private Date paymentDate;    //交款时间 
	private Double paymentAmount;  //交款金额
	private Date reviewDateTime;//审核日期时间
	
	
	public Date getReviewDateTime() {
		return reviewDateTime;
	}


	public void setReviewDateTime(Date reviewDateTime) {
		this.reviewDateTime = reviewDateTime;
	}



	public Integer getEngineDepartId() {
		return engineDepartId;
	}



	public void setEngineDepartId(Integer engineDepartId) {
		this.engineDepartId = engineDepartId;
	}



	public String getEngineDepartName() {
		return engineDepartName;
	}



	public void setEngineDepartName(String engineDepartName) {
		this.engineDepartName = engineDepartName;
	}



	public Integer getStoreId() {
		return storeId;
	}



	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}



	public String getItemManagerName() {
		return itemManagerName;
	}



	public void setItemManagerName(String itemManagerName) {
		this.itemManagerName = itemManagerName;
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



	public String getOrderNumber() {
		return orderNumber;
	}



	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
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



	public Date getApplyCheckDate() {
		return applyCheckDate;
	}



	public void setApplyCheckDate(Date applyCheckDate) {
		this.applyCheckDate = applyCheckDate;
	}



	public String getInspectName() {
		return inspectName;
	}



	public void setInspectName(String inspectName) {
		this.inspectName = inspectName;
	}



	public Date getYanshouDate() {
		return yanshouDate;
	}



	public void setYanshouDate(Date yanshouDate) {
		this.yanshouDate = yanshouDate;
	}



	public ErqiEntity(){
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}



	public Date getPaymentDate() {
		return paymentDate;
	}



	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}



	public Double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(Double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	
}