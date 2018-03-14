package cn.damei.entity.mobile.Manager;

import java.io.Serializable;
import java.util.Date;



public class PurchaseDetailsVo  implements Serializable{

	

	private static final long serialVersionUID = 1L;
	
	private String purchaseCode;
	private Date hopeForTime;
	private String status;
	private String statusId;
	private Integer count;
	private String name;
	private String specification;
	private String url;
	private Double price;
	private Double auxiliaryMoney;
	private Double totalMoney;
	private String brand;
	private  Integer totalCount;
	private  Double  contractAreaForApplySwitchPane;
	private String projectMode;
	private String statusDescribe;
	private String unit;
	private String empWorkType;
	private String empWorkTypeName;
	
	
	public String getStatusId() {
		return statusId;
	}
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
	public String getStatusDescribe() {
		return statusDescribe;
	}
	public void setStatusDescribe(String statusDescribe) {
		this.statusDescribe = statusDescribe;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	private Integer orderId;
	
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	private String customerName;
	private String communityName;
	private String buildNumber;
	private String buildUnit;
	private String buildRoom;
	
	
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
	public String getProjectMode() {
		return projectMode;
	}
	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Double getContractAreaForApplySwitchPane() {
		return contractAreaForApplySwitchPane;
	}
	public void setContractAreaForApplySwitchPane(Double contractAreaForApplySwitchPane) {
		this.contractAreaForApplySwitchPane = contractAreaForApplySwitchPane;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public Double getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}
	public String getPurchaseCode() {
		return purchaseCode;
	}
	public void setPurchaseCode(String purchaseCode) {
		this.purchaseCode = purchaseCode;
	}
	public Date getHopeForTime() {
		return hopeForTime;
	}
	public void setHopeForTime(Date hopeForTime) {
		this.hopeForTime = hopeForTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSpecification() {
		return specification;
	}
	public void setSpecification(String specification) {
		this.specification = specification;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getAuxiliaryMoney() {
		return auxiliaryMoney;
	}
	public void setAuxiliaryMoney(Double auxiliaryMoney) {
		this.auxiliaryMoney = auxiliaryMoney;
	}
	public String getEmpWorkType() {
		return empWorkType;
	}
	public void setEmpWorkType(String empWorkType) {
		this.empWorkType = empWorkType;
	}
	public String getEmpWorkTypeName() {
		return empWorkTypeName;
	}
	public void setEmpWorkTypeName(String empWorkTypeName) {
		this.empWorkTypeName = empWorkTypeName;
	}
	
	
	
}
