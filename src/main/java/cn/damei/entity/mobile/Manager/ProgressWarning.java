package cn.damei.entity.mobile.Manager;

import java.util.Date;
import java.util.List;

import cn.damei.common.persistence.DataEntity2;

public class ProgressWarning extends DataEntity2<ProgressWarning>{

	private static final long serialVersionUID = 1L;
	private String taskPackageName;
	private String communityName;
	private String buildNumber;
	private String buildUnit;
	private String buildRoom;
	private String settlementId;
	private String taskPackageId;
	private String status;
	private String customerName;
	
	private String purchaseType;
	
	private String orderId;
	
	private String count;
	
	private List<String> list;
	
	private Date planDoneDate;
	
	public Date getPlanDoneDate() {
		return planDoneDate;
	}
	public void setPlanDoneDate(Date planDoneDate) {
		this.planDoneDate = planDoneDate;
	}
	public String getExtensionDays() {
		Date date = new Date();
		long time = date.getTime();
		long time2 = planDoneDate.getTime();
		String str = (time - time2)/(24*3600*1000)+"";
		return str;
	}
	public List<String> getList() {
		return list;
	}
	public void setList(List<String> list) {
		this.list = list;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	public String getPurchaseType() {
		
		return purchaseType;
	}
	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getSettlementId() {
		return settlementId;
	}
	public void setSettlementId(String settlementId) {
		this.settlementId = settlementId;
	}
	public String getTaskPackageId() {
		return taskPackageId;
	}
	public void setTaskPackageId(String taskPackageId) {
		this.taskPackageId = taskPackageId;
	}
	public String getTaskPackageName() {
		return taskPackageName.substring(0, 2);
	}
	public void setTaskPackageName(String taskPackageName) {
		this.taskPackageName = taskPackageName;
	}
	public String getCommunityName() {
		return communityName;
	}
	public void setCommunityName(String community_name) {
		this.communityName = community_name;
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
	public ProgressWarning() {
		super();
	}

	
	
}
