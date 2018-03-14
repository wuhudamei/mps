package cn.damei.entity.mobile.Manager;

import cn.damei.common.persistence.DataEntity2;

/**
 *  沙子水泥商品
 */

public class SandGoods extends DataEntity2<SandGoods> {

	private static final long serialVersionUID = 1L;
	
	private Integer auxiliaryMaterialsId;	//辅料ID
	private String auxiliaryMaterialsNo; // 辅料编号
	private String auxiliaryMaterialsName; // 辅料名称
	private String specifications; // 规格
	private Integer totalAuxiMateCount; // 送货数量
	private String measurementUnit; // 商品单位
	private String measurementUnitLabel; // 商品单位名称
	private Integer auxiliaryMaterialsSupplierRelId;//关联供应商ID
	private String laborPrice; // 单价(工人结算价)
	private String supplierPrice; //供应商价格
	private String wangzhenPrice; //网真价格（门店价格）
	private String picUrl; // 图片
	private String brands;	//品牌
	
	private Supplier supplier;	//供应商
	
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
