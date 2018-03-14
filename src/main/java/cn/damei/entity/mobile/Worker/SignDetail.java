package cn.damei.entity.mobile.Worker;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;



public class SignDetail extends DataEntity2<SignDetail>{


	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer orderId;
	private Integer packId;
	public Integer getPackId() {
		return packId;
	}
	public void setPackId(Integer packId) {
		this.packId = packId;
	}
	private String signPic;
	private Date signDate;
	private String signAddress;
	private String signXy;
	private String signDistance;
	private String signType;
	private String  signName;
	private Integer managerId;
	private String managerName;
	private String managerPhone;
	
	private String lat;
	private String lon;
	
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}
	
	public String getManagerPhone() {
		return managerPhone;
	}
	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}
	private Integer workerLeaderId;
	private String workerLeaderName;
	private String customerInfo;
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
	public String getSignPic() {
		return signPic;
	}
	public void setSignPic(String signPic) {
		this.signPic = signPic;
	}
	public Date getSignDate() {
		return signDate;
	}
	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}
	public String getSignAddress() {
		return signAddress;
	}
	public void setSignAddress(String signAddress) {
		this.signAddress = signAddress;
	}
	public String getSignXy() {
		return signXy;
	}
	public void setSignXy(String signXy) {
		this.signXy = signXy;
	}
	public String getSignDistance() {
		return signDistance;
	}
	public void setSignDistance(String signDistance) {
		this.signDistance = signDistance;
	}

	public String getSignType() {
		return signType;
	}
	public void setSignType(String signType) {
		this.signType = signType;
	}
	public String getSignName() {
		return signName;
	}
	public void setSignName(String signName) {
		this.signName = signName;
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
	public Integer getWorkerLeaderId() {
		return workerLeaderId;
	}
	public void setWorkerLeaderId(Integer workerLeaderId) {
		this.workerLeaderId = workerLeaderId;
	}
	public String getWorkerLeaderName() {
		return workerLeaderName;
	}
	public void setWorkerLeaderName(String workerLeaderName) {
		this.workerLeaderName = workerLeaderName;
	}
	public String getCustomerInfo() {
		return customerInfo;
	}
	public void setCustomerInfo(String customerInfo) {
		this.customerInfo = customerInfo;
	}
	
	
	
}
