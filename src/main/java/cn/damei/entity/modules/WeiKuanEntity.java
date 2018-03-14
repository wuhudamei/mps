
package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity;


public class WeiKuanEntity extends DataEntity<WeiKuanEntity> {
	
	private static final long serialVersionUID = 1L;
private  Integer storeId;
private String customerName;
private String communityName;
private String buildNumber;
private String buildUnit;
private String buildRoom;
	private Integer orderId;
	private Integer engineDepartId;
	private String engineDepartName;
	private Date paymentDate;
	private Double paymentAmount;
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

private String managerName;
private Date start;
private Date end;
private String orderNumber;
private Date applyDoneDate;
private String jiesuanName;
private Date passDate;
	
	public Integer getStoreId() {
	return storeId;
}

public void setStoreId(Integer storeId) {
	this.storeId = storeId;
}

public String getCustomerName() {
	return customerName;
}

public void setCustomerName(String customerName) {
	this.customerName = customerName;
}

public String getManagerName() {
	return managerName;
}

public void setManagerName(String managerName) {
	this.managerName = managerName;
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

public Date getApplyDoneDate() {
	return applyDoneDate;
}

public void setApplyDoneDate(Date applyDoneDate) {
	this.applyDoneDate = applyDoneDate;
}

public String getJiesuanName() {
	return jiesuanName;
}

public void setJiesuanName(String jiesuanName) {
	this.jiesuanName = jiesuanName;
}

public Date getPassDate() {
	return passDate;
}

public void setPassDate(Date passDate) {
	this.passDate = passDate;
}

	public WeiKuanEntity() {
		super();
	}

	public WeiKuanEntity(String id){
		super(id);
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