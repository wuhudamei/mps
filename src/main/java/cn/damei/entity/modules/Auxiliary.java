package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;

public class Auxiliary extends DataEntity2<Auxiliary>{
	
	/**
	 * @author wang
	 * @version 2016-09-28
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;//辅材采购单id
	private Integer purchaseId;
	private String auxiMateCode; //编号
	private Double auxiMateCount; //shuliang
	private Integer categoryId;
	private String  categoryName;
	private String auxiliaryMaterialsName;
	private String measurementUnit;
	private String specifications;
	private Double receivedCount;
	private Double owedCount;
	private String brands;
	private String laborPrice;//工人单价
	
	public String getLaborPrice() {
		return laborPrice;
	}
	public void setLaborPrice(String laborPrice) {
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
	public String getAuxiMateCode() {
		return auxiMateCode;
	}
	public void setAuxiMateCode(String auxiMateCode) {
		this.auxiMateCode = auxiMateCode;
	}
	public Double getAuxiMateCount() {
		return auxiMateCount;
	}
	public void setAuxiMateCount(Double auxiMateCount) {
		this.auxiMateCount = auxiMateCount;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getAuxiliaryMaterialsName() {
		return auxiliaryMaterialsName;
	}
	public void setAuxiliaryMaterialsName(String auxiliaryMaterialsName) {
		this.auxiliaryMaterialsName = auxiliaryMaterialsName;
	}
	public String getMeasurementUnit() {
		return measurementUnit;
	}
	public void setMeasurementUnit(String measurementUnit) {
		this.measurementUnit = measurementUnit;
	}
	public String getSpecifications() {
		return specifications;
	}
	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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
	public String getBrands() {
		return brands;
	}
	public void setBrands(String brands) {
		this.brands = brands;
	}
}
