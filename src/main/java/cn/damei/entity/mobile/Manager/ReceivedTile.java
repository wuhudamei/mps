package cn.damei.entity.mobile.Manager;

import cn.damei.common.persistence.DataEntity2;

public class ReceivedTile extends DataEntity2<ReceivedTile>{
	
	private static final long serialVersionUID = 1L;

	private Integer purchaseId;
	private String mainMateType;
	private String name;
	private String position;
	private String brands;
	private String model;
	private String specifications;
	private String unit;
	private Double mainMateCount;
	private Double includLossCount;
	private Double count;
	private String remarks;
	private Double receivedCount;
	private Double owedCount;
	private String picUrl;
	private Double receivedNumber;
	private String mateCode;
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(Integer purchaseId) {
		this.purchaseId = purchaseId;
	}
	public String getMainMateType() {
		return mainMateType;
	}
	public void setMainMateType(String mainMateType) {
		this.mainMateType = mainMateType;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getBrands() {
		return brands;
	}
	public void setBrands(String brands) {
		this.brands = brands;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
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
	public String getMateCode() {
		return mateCode;
	}
	public void setMateCode(String mateCode) {
		this.mateCode = mateCode;
	}
}
