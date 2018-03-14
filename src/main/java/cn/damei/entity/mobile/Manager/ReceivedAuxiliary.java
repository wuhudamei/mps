package cn.damei.entity.mobile.Manager;

import cn.damei.common.persistence.DataEntity2;

public class ReceivedAuxiliary extends DataEntity2<ReceivedAuxiliary>{
	
	private static final long serialVersionUID = 1L;
	//private Integer id; //辅料采购单id
	private Integer purchaseId; //采购单id
	private String mateCode; //辅材编号
	private Double count; //申请数量
	private Double receivedCount; //收货数量
	private Double owedCount; //欠货数量
	private String name; //辅材名称
	private String specifications;
	private String brands; //品牌
	private String picUrl; //图片
	private String unit; //单位
	private Double receivedNumber;//本次收货的数量
	private String model; //型号
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBrands() {
		return brands;
	}
	public void setBrands(String brands) {
		this.brands = brands;
	}
	public String getSpecifications() {
		return specifications;
	}
	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public Double getReceivedNumber() {
		return receivedNumber;
	}
	public void setReceivedNumber(Double receivedNumber) {
		this.receivedNumber = receivedNumber;
	}
}
