package cn.damei.entity.mobile.Manager;

import cn.damei.common.persistence.DataEntity2;



public class AuxiliaryMaterialsVo extends DataEntity2<AuxiliaryMaterialsVo> {

	private static final long serialVersionUID = 1L;
	
	private String auxiliaryMaterialsNo;
	private String auxiliaryMaterialsName;
	private String specifications;
	private Integer totalAuxiMateCount;
	private String measurementUnit;
	private String measurementUnitLabel;
	private String laborPrice;
	private String supplierPrice;
	private String wangzhenPrice;
	private String picUrl;
	private Double userCountTotal;
	
	private Integer orderAuxiMateId;
	private Double usedCount;
	
	public String getAuxiliaryMaterialsNo() {
		return auxiliaryMaterialsNo;
	}
	public void setAuxiliaryMaterialsNo(String auxiliaryMaterialsNo) {
		this.auxiliaryMaterialsNo = auxiliaryMaterialsNo;
	}
	public String getAuxiliaryMaterialsName() {
		return auxiliaryMaterialsName;
	}
	public void setAuxiliaryMaterialsName(String auxiliaryMaterialsName) {
		this.auxiliaryMaterialsName = auxiliaryMaterialsName;
	}
	public String getSpecifications() {
		return specifications;
	}
	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}
	public Integer getTotalAuxiMateCount() {
		return totalAuxiMateCount;
	}
	public void setTotalAuxiMateCount(Integer totalAuxiMateCount) {
		this.totalAuxiMateCount = totalAuxiMateCount;
	}
	public String getMeasurementUnit() {
		return measurementUnit;
	}
	public void setMeasurementUnit(String measurementUnit) {
		this.measurementUnit = measurementUnit;
	}
	public String getMeasurementUnitLabel() {
		return measurementUnitLabel;
	}
	public void setMeasurementUnitLabel(String measurementUnitLabel) {
		this.measurementUnitLabel = measurementUnitLabel;
	}
	public String getLaborPrice() {
		return laborPrice;
	}
	public void setLaborPrice(String laborPrice) {
		this.laborPrice = laborPrice;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public Double getUserCountTotal() {
		return userCountTotal;
	}
	public void setUserCountTotal(Double userCountTotal) {
		this.userCountTotal = userCountTotal;
	}
	public Integer getOrderAuxiMateId() {
		return orderAuxiMateId;
	}
	public void setOrderAuxiMateId(Integer orderAuxiMateId) {
		this.orderAuxiMateId = orderAuxiMateId;
	}
	public Double getUsedCount() {
		return usedCount;
	}
	public void setUsedCount(Double usedCount) {
		this.usedCount = usedCount;
	}
	public String getSupplierPrice() {
		return supplierPrice;
	}
	public void setSupplierPrice(String supplierPrice) {
		this.supplierPrice = supplierPrice;
	}
	public String getWangzhenPrice() {
		return wangzhenPrice;
	}
	public void setWangzhenPrice(String wangzhenPrice) {
		this.wangzhenPrice = wangzhenPrice;
	}
	
}
