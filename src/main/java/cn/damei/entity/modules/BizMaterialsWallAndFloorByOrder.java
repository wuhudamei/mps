package cn.damei.entity.modules;
import java.util.Date;
import java.util.List;

import cn.damei.common.persistence.DataEntity2;
public class BizMaterialsWallAndFloorByOrder extends DataEntity2<BizMaterialsWallAndFloorByOrder> {


	private static final long serialVersionUID = 1L;

	private Integer storeId;
	private Integer orderId;
	private String orderNumber;
	private String customerName;
	private String itemManager;
	private String applyTimes;
	private Integer enginDepartId;
	private Integer purchaseId;
	private String enginDepartName;
	private String customerAddress;
	private String orderStatus;
	private String projectMode;
	private String purchaseCode;
	private Date applyReceiveTime;
	private Date applyTime;
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
	private String realName;
	private String remarks;
	private String position;
	private Integer mainMateType;
	private String model;
	private Double mainMateCount;
	private Double includLossCount;
	private String label;
	private List<String> purchaseStatusList = null;

	public List<String> getPurchaseStatusList() {
		return purchaseStatusList;
	}
	public void setPurchaseStatusList(List<String> purchaseStatusList) {
		this.purchaseStatusList = purchaseStatusList;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Integer getMainMateType() {
		return mainMateType;
	}
	public void setMainMateType(Integer mainMateType) {
		this.mainMateType = mainMateType;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public Double getMainMateCount() {
		return mainMateCount;
	}
	public void setMainMateCount(Double mainMateCount) {
		this.mainMateCount = mainMateCount;
	}
	public Double getIncludLossCount() {
		return includLossCount;
	}
	public void setIncludLossCount(Double includLossCount) {
		this.includLossCount = includLossCount;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
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
	public Integer getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(Integer purchaseId) {
		this.purchaseId = purchaseId;
	}
	
}