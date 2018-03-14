package cn.damei.entity.mobile.Manager;

import cn.damei.common.persistence.DataEntity2;

public class ReceivedPanel extends DataEntity2<ReceivedPanel>{
	
	private static final long serialVersionUID = 1L;

	private Integer purchaseId;
	private Integer orderId;
	private String mateCode;
	private Double count;
	private String  remarks;
	private String submmitStatus;
	private String name;
	private String empWorkType;
	private String categoryId;
	private String specifications;
	private String unit;
	private String brands;
	private String picUrl;
	private Double receivedCount;
	private Double owedCount;
	private Double receivedNumber;
	private String model;
	
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	
	public Integer getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(Integer purchaseId) {
		this.purchaseId = purchaseId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getMateCode() {
		return mateCode;
	}
	public void setMateCode(String mateCode) {
		this.mateCode = mateCode;
	}
	public Double getCount() {
		return count;
	}
	public void setCount(Double count) {
		this.count = count;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getSubmmitStatus() {
		return submmitStatus;
	}
	public void setSubmmitStatus(String submmitStatus) {
		this.submmitStatus = submmitStatus;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
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
	public Double getReceivedNumber() {
		return receivedNumber;
	}
	public void setReceivedNumber(Double receivedNumber) {
		this.receivedNumber = receivedNumber;
	}
	
}
