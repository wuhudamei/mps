package cn.damei.entity.mobile.Manager;

import cn.damei.common.persistence.DataEntity2;



public class SandGoods extends DataEntity2<SandGoods> {

	private static final long serialVersionUID = 1L;
	
	private Integer auxiliaryMaterialsId;
	private String auxiliaryMaterialsNo;
	private String auxiliaryMaterialsName;
	private String specifications;
	private Integer totalAuxiMateCount;
	private String measurementUnit;
	private String measurementUnitLabel;
	private Integer auxiliaryMaterialsSupplierRelId;
	private String laborPrice;
	private String supplierPrice;
	private String wangzhenPrice;
	private String picUrl;
	private String brands;
	
	private Supplier supplier;
	
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
	public Integer getAuxiliaryMaterialsId() {
		return auxiliaryMaterialsId;
	}
	public void setAuxiliaryMaterialsId(Integer auxiliaryMaterialsId) {
		this.auxiliaryMaterialsId = auxiliaryMaterialsId;
	}
	public Integer getAuxiliaryMaterialsSupplierRelId() {
		return auxiliaryMaterialsSupplierRelId;
	}
	public void setAuxiliaryMaterialsSupplierRelId(Integer auxiliaryMaterialsSupplierRelId) {
		this.auxiliaryMaterialsSupplierRelId = auxiliaryMaterialsSupplierRelId;
	}
	public String getBrands() {
		return brands;
	}
	public void setBrands(String brands) {
		this.brands = brands;
	}
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
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
