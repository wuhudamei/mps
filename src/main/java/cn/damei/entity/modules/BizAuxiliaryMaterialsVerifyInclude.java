package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;


public class BizAuxiliaryMaterialsVerifyInclude extends DataEntity2<BizAuxiliaryMaterialsVerifyInclude> {

	private static final long serialVersionUID = 1L;

	private Integer auxiliaryMaterialsVerifyId;
	private String auxiliaryMaterialsNo;
	private String auxiliaryMaterilasName;
	private String specifications;
	private String measurementUnit;
	private Integer auxiliaryMaterialsCount;
	private Double auxiliaryMaterialsSupplierPrice;
	private Double auxiliaryMaterialsWangzhenPrice;
	private Double auxiliaryMaterialsLaborPrice;
	private Double auxiliaryMaterialsSupplierPriceAmount;
	private Double auxiliaryMaterialsWangzhenPriceAmount;
	private Double auxiliaryMaterialsLaborPriceAmount;
	private String supplierId;
	private String groupType;

	public Integer getAuxiliaryMaterialsVerifyId() {
		return auxiliaryMaterialsVerifyId;
	}

	public void setAuxiliaryMaterialsVerifyId(Integer auxiliaryMaterialsVerifyId) {
		this.auxiliaryMaterialsVerifyId = auxiliaryMaterialsVerifyId;
	}

	public String getAuxiliaryMaterialsNo() {
		return auxiliaryMaterialsNo;
	}

	public void setAuxiliaryMaterialsNo(String auxiliaryMaterialsNo) {
		this.auxiliaryMaterialsNo = auxiliaryMaterialsNo;
	}

	public Integer getAuxiliaryMaterialsCount() {
		return auxiliaryMaterialsCount;
	}

	public void setAuxiliaryMaterialsCount(Integer auxiliaryMaterialsCount) {
		this.auxiliaryMaterialsCount = auxiliaryMaterialsCount;
	}

	public Double getAuxiliaryMaterialsSupplierPrice() {
		return auxiliaryMaterialsSupplierPrice;
	}

	public void setAuxiliaryMaterialsSupplierPrice(Double auxiliaryMaterialsSupplierPrice) {
		this.auxiliaryMaterialsSupplierPrice = auxiliaryMaterialsSupplierPrice;
	}

	public Double getAuxiliaryMaterialsWangzhenPrice() {
		return auxiliaryMaterialsWangzhenPrice;
	}

	public void setAuxiliaryMaterialsWangzhenPrice(Double auxiliaryMaterialsWangzhenPrice) {
		this.auxiliaryMaterialsWangzhenPrice = auxiliaryMaterialsWangzhenPrice;
	}

	public Double getAuxiliaryMaterialsLaborPrice() {
		return auxiliaryMaterialsLaborPrice;
	}

	public void setAuxiliaryMaterialsLaborPrice(Double auxiliaryMaterialsLaborPrice) {
		this.auxiliaryMaterialsLaborPrice = auxiliaryMaterialsLaborPrice;
	}

	public Double getAuxiliaryMaterialsSupplierPriceAmount() {
		return auxiliaryMaterialsSupplierPriceAmount;
	}

	public void setAuxiliaryMaterialsSupplierPriceAmount(Double auxiliaryMaterialsSupplierPriceAmount) {
		this.auxiliaryMaterialsSupplierPriceAmount = auxiliaryMaterialsSupplierPriceAmount;
	}

	public Double getAuxiliaryMaterialsWangzhenPriceAmount() {
		return auxiliaryMaterialsWangzhenPriceAmount;
	}

	public void setAuxiliaryMaterialsWangzhenPriceAmount(Double auxiliaryMaterialsWangzhenPriceAmount) {
		this.auxiliaryMaterialsWangzhenPriceAmount = auxiliaryMaterialsWangzhenPriceAmount;
	}

	public Double getAuxiliaryMaterialsLaborPriceAmount() {
		return auxiliaryMaterialsLaborPriceAmount;
	}

	public void setAuxiliaryMaterialsLaborPriceAmount(Double auxiliaryMaterialsLaborPriceAmount) {
		this.auxiliaryMaterialsLaborPriceAmount = auxiliaryMaterialsLaborPriceAmount;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getAuxiliaryMaterilasName() {
		return auxiliaryMaterilasName;
	}

	public void setAuxiliaryMaterilasName(String auxiliaryMaterilasName) {
		this.auxiliaryMaterilasName = auxiliaryMaterilasName;
	}

	public String getMeasurementUnit() {
		return measurementUnit;
	}

	public void setMeasurementUnit(String measurementUnit) {
		this.measurementUnit = measurementUnit;
	}

	public String getGroupType() {
		return groupType;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}

	public String getSpecifications() {
		return specifications;
	}

	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}
	

	
}
