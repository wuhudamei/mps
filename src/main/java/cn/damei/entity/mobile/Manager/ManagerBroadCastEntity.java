package cn.damei.entity.mobile.Manager;

import java.io.Serializable;
import java.util.Date;

public class ManagerBroadCastEntity implements  Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer broadcastId;
	private Integer  orderId;
	private String broadcastName;
	private String communityName;
	private String buildNumber;
	private String buildUnit;
	private String buildName;
	private String status;
	private Date statusDateTime;
	private Integer checkEmployeeId;
	
	public String getStatus() {
		return status;
	}
	public Integer getCheckEmployeeId() {
		return checkEmployeeId;
	}
	public void setCheckEmployeeId(Integer checkEmployeeId) {
		this.checkEmployeeId = checkEmployeeId;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getStatusDateTime() {
		return statusDateTime;
	}
	public void setStatusDateTime(Date statusDateTime) {
		this.statusDateTime = statusDateTime;
	}
	private Date applyBroadCastDate;
	private Integer applyBroadCastId;
	private String applyBroadCastName;
	private String applyBroadCastPhone;
	private String picPath;
	private String customerName;
	private String isShow;//是否展示给顾客看
	private String text;//条件搜索
	private Integer picId;
	private String picType;
	private Integer picCount;
	public Integer getPicCount() {
		return picCount;
	}
	public void setPicCount(Integer picCount) {
		this.picCount = picCount;
	}
	private Date   picDateTime;
	private Integer picRelatedId;
	public Integer getPicRelatedId() {
		return picRelatedId;
	}
	public void setPicRelatedId(Integer picRelatedId) {
		this.picRelatedId = picRelatedId;
	}
	public String getPicType() {
		return picType;
	}
	public void setPicType(String picType) {
		this.picType = picType;
	}
	public Date getPicDateTime() {
		return picDateTime;
	}
	public void setPicDateTime(Date picDateTime) {
		this.picDateTime = picDateTime;
	}
	public Integer getPicId() {
		return picId;
	}
	public void setPicId(Integer picId) {
		this.picId = picId;
	}
	public String getPicPath() {
		return picPath;
	}
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
	public String getApplyBroadCastPhone() {
		return applyBroadCastPhone;
	}
	public void setApplyBroadCastPhone(String applyBroadCastPhone) {
		this.applyBroadCastPhone = applyBroadCastPhone;
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getIsShow() {
		return isShow;
	}
	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}
	public Integer getBroadcastId() {
		return broadcastId;
	}
	public void setBroadcastId(Integer broadcastId) {
		this.broadcastId = broadcastId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getBroadcastName() {
		return broadcastName;
	}
	public void setBroadcastName(String broadcastName) {
		this.broadcastName = broadcastName;
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
	public String getBuildName() {
		return buildName;
	}
	public void setBuildName(String buildName) {
		this.buildName = buildName;
	}
	public Date getApplyBroadCastDate() {
		return applyBroadCastDate;
	}
	public void setApplyBroadCastDate(Date applyBroadCastDate) {
		this.applyBroadCastDate = applyBroadCastDate;
	}
	public Integer getApplyBroadCastId() {
		return applyBroadCastId;
	}
	public void setApplyBroadCastId(Integer applyBroadCastId) {
		this.applyBroadCastId = applyBroadCastId;
	}
	public String getApplyBroadCastName() {
		return applyBroadCastName;
	}
	public void setApplyBroadCastName(String applyBroadCastName) {
		this.applyBroadCastName = applyBroadCastName;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	
	
	
	
	
	
	

}
