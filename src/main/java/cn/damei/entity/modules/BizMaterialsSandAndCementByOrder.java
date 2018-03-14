package cn.damei.entity.modules;
import java.util.Date;
import java.util.List;

import cn.damei.common.persistence.DataEntity2;
public class BizMaterialsSandAndCementByOrder extends DataEntity2<BizMaterialsSandAndCementByOrder> {


	private static final long serialVersionUID = 1L;

	private Integer storeId;
	private Integer orderId;
	private String orderNumber;
	private String customerName;
	private String itemManager;
	private String applyTimes;
	private Integer enginDepartId;
	private Date finalReceiveTime;
	private Date finalReceiveTimeBegin;
	private Date finalReceiveTimeEnd;
	private Integer purchaseId;
	private String enginDepartName;
	private String customerAddress;
	private String orderStatus;
	private String projectMode;
	private String purchaseCode;
	private Date applyReceiveTime;
	private Date applyTime;
	private Date applyTimeBegin;
	private Date applyTimeEnd;
	private String auxiMateCode;
	private Double price;
	private Double auxiMateCount;
	private Double receivedAuxiMateCount;
	private Double owedAuxiMateCount;
	private String brands;
	private String 	auxiMaterialName;
	private String specifications;
	private Integer categoryId;
	private String measurementUnit;
	private String categoryName;
	private String status;
	private Integer upstairsFloor;
	private String isElevator;
	private String realName;
	private String phone;
	private List<String> purchaseStatusList = null;


	public Date getApplyTimeBegin() {
		return applyTimeBegin;
	}

	public void setApplyTimeBegin(Date applyTimeBegin) {
		this.applyTimeBegin = applyTimeBegin;
	}

	public Date getApplyTimeEnd() {
		return applyTimeEnd;
	}

	public void setApplyTimeEnd(Date applyTimeEnd) {
		this.applyTimeEnd = applyTimeEnd;
	}

	public List<String> getPurchaseStatusList() {
		return purchaseStatusList;
	}

	public void setPurchaseStatusList(List<String> purchaseStatusList) {
		this.purchaseStatusList = purchaseStatusList;
	}

	public Integer getUpstairsFloor() {
		return upstairsFloor;
	}
	public void setUpstairsFloor(Integer upstairsFloor) {
		this.upstairsFloor = upstairsFloor;
	}
	public String getIsElevator() {
		return isElevator;
	}
	public void setIsElevator(String isElevator) {
		this.isElevator = isElevator;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getPurchaseCode() {
		return purchaseCode;
	}
	public void setPurchaseCode(String purchaseCode) {
		this.purchaseCode = purchaseCode;
	}
	public Date getApplyReceiveTime() {
		return applyReceiveTime;
	}
	public void setApplyReceiveTime(Date applyReceiveTime) {
		this.applyReceiveTime = applyReceiveTime;
	}
	public Date getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	public String getAuxiMateCode() {
		return auxiMateCode;
	}
	public void setAuxiMateCode(String auxiMateCode) {
		this.auxiMateCode = auxiMateCode;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getAuxiMateCount() {
		return auxiMateCount;
	}
	public void setAuxiMateCount(Double auxiMateCount) {
		this.auxiMateCount = auxiMateCount;
	}
	public Double getReceivedAuxiMateCount() {
		return receivedAuxiMateCount;
	}
	public void setReceivedAuxiMateCount(Double receivedAuxiMateCount) {
		this.receivedAuxiMateCount = receivedAuxiMateCount;
	}
	public Double getOwedAuxiMateCount() {
		return owedAuxiMateCount;
	}
	public void setOwedAuxiMateCount(Double owedAuxiMateCount) {
		this.owedAuxiMateCount = owedAuxiMateCount;
	}
	public String getBrands() {
		return brands;
	}
	public void setBrands(String brands) {
		this.brands = brands;
	}
	public String getAuxiMaterialName() {
		return auxiMaterialName;
	}
	public void setAuxiMaterialName(String auxiMaterialName) {
		this.auxiMaterialName = auxiMaterialName;
	}
	public String getSpecifications() {
		return specifications;
	}
	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getMeasurementUnit() {
		return measurementUnit;
	}
	public void setMeasurementUnit(String measurementUnit) {
		this.measurementUnit = measurementUnit;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getProjectMode() {
		return projectMode;
	}
	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}
	public String getEnginDepartName() {
		return enginDepartName;
	}
	public void setEnginDepartName(String enginDepartName) {
		this.enginDepartName = enginDepartName;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
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
	public String getItemManager() {
		return itemManager;
	}
	public void setItemManager(String itemManager) {
		this.itemManager = itemManager;
	}
	public String getApplyTimes() {
		return applyTimes;
	}
	public void setApplyTimes(String applyTimes) {
		this.applyTimes = applyTimes;
	}
	public Integer getEnginDepartId() {
		return enginDepartId;
	}
	public void setEnginDepartId(Integer enginDepartId) {
		this.enginDepartId = enginDepartId;
	}
	public Date getFinalReceiveTime() {
		return finalReceiveTime;
	}
	public void setFinalReceiveTime(Date finalReceiveTime) {
		this.finalReceiveTime = finalReceiveTime;
	}
	public Date getFinalReceiveTimeBegin() {
		return finalReceiveTimeBegin;
	}
	public void setFinalReceiveTimeBegin(Date finalReceiveTimeBegin) {
		this.finalReceiveTimeBegin = finalReceiveTimeBegin;
	}
	public Date getFinalReceiveTimeEnd() {
		return finalReceiveTimeEnd;
	}
	public void setFinalReceiveTimeEnd(Date finalReceiveTimeEnd) {
		this.finalReceiveTimeEnd = finalReceiveTimeEnd;
	}
	public Integer getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(Integer purchaseId) {
		this.purchaseId = purchaseId;
	}
	
}