package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;

public class MainPanel extends DataEntity2<MainPanel>{

	
	

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer purchaseId;
	private Integer orderId;
	private String mainMaterialsCode;
	private Integer mainMateCount;
	private String  remarks;
	private String submmitStatus;
	private String mainMaterialsName;
	private String empWorkType;
	private String categoryId;

	private String specifications;
	private String measurementUnit;
	private String brands;
	private String picUrl;
	private Double receivedCount;
	private Double owedCount;
	private Double laborPrice;
	private Double totalPrice;
	
	
	public Double getTotalPrice() {
		totalPrice = laborPrice*mainMateCount;
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Double getLaborPrice() {
		return laborPrice;
	}
	public void setLaborPrice(Double laborPrice) {
		this.laborPrice = laborPrice;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(Integer purchaseId) {
		this.purchaseId = purchaseId;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getMainMaterialsCode() {
		return mainMaterialsCode;
	}
	public void setMainMaterialsCode(String mainMaterialsCode) {
		this.mainMaterialsCode = mainMaterialsCode;
	}
	public Integer getMainMateCount() {
		return mainMateCount;
	}
	public void setMainMateCount(Integer mainMateCount) {
		this.mainMateCount = mainMateCount;
	}
	public String getSubmmitStatus() {
		return submmitStatus;
	}
	public void setSubmmitStatus(String submmitStatus) {
		this.submmitStatus = submmitStatus;
	}
	public String getMainMaterialsName() {
		return mainMaterialsName;
	}
	public void setMainMaterialsName(String mainMaterialsName) {
		this.mainMaterialsName = mainMaterialsName;
	}
	public String getEmpWorkType() {
		return empWorkType;
	}
	public void setEmpWorkType(String empWorkType) {
		this.empWorkType = empWorkType;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getSpecifications() {
		return specifications;
	}
	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}
	public String getMeasurementUnit() {
		return measurementUnit;
	}
	public void setMeasurementUnit(String measurementUnit) {
		this.measurementUnit = measurementUnit;
	}
	public String getBrands() {
		return brands;
	}
	public void setBrands(String brands) {
		this.brands = brands;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public Double getReceivedCount() {
		return receivedCount;
	}
	public void setReceivedCount(Double receivedCount) {
		this.receivedCount = receivedCount;
	}
	public Double getOwedCount() {
		return owedCount;
	}
	public void setOwedCount(Double owedCount) {
		this.owedCount = owedCount;
	}
	
}
