package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;


public class ExchangeOrderDetails extends DataEntity2<ExchangeOrderDetails> {

	private static final long serialVersionUID = 1L;

	private Date exChangeDate;

	private String customer;

	private String packageName;

	private String oldLeaderName;

	private String newLeaderName;

	private String reasonType;

	private String reasonRemarks;

	private String communityName;

	private String buildNumber;

	private String buildUnit;

	private String buildRoom;
	
	private Integer oldEmployeeId;
	
	private Date startExChangeDate;
	private Date endExChangeDate;
	
	
	public Date getStartExChangeDate() {
		return startExChangeDate;
	}

	public void setStartExChangeDate(Date startExChangeDate) {
		this.startExChangeDate = startExChangeDate;
	}

	public Date getEndExChangeDate() {
		return endExChangeDate;
	}

	public void setEndExChangeDate(Date endExChangeDate) {
		this.endExChangeDate = endExChangeDate;
	}


	public String getAdd(){
		
		return getCommunityName()+"-"+getBuildNumber()+"-"+getBuildUnit()+"-"+getBuildRoom()+"-"+getCustomer();
	}
	
	public Date getExChangeDate() {
		return exChangeDate;
	}
	public void setExChangeDate(Date exChangeDate) {
		this.exChangeDate = exChangeDate;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getOldLeaderName() {
		return oldLeaderName;
	}
	public void setOldLeaderName(String oldLeaderName) {
		this.oldLeaderName = oldLeaderName;
	}
	public String getNewLeaderName() {
		return newLeaderName;
	}
	public void setNewLeaderName(String newLeaderName) {
		this.newLeaderName = newLeaderName;
	}
	public String getReasonType() {
		return reasonType;
	}
	public void setReasonType(String reasonType) {
		this.reasonType = reasonType;
	}
	public String getReasonRemarks() {
		return reasonRemarks;
	}
	public void setReasonRemarks(String reasonRemarks) {
		this.reasonRemarks = reasonRemarks;
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

	public Integer getOldEmployeeId() {
		return oldEmployeeId;
	}

	public void setOldEmployeeId(Integer oldEmployeeId) {
		this.oldEmployeeId = oldEmployeeId;
	}
	
	
}
