package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;

/**
 * 对账单辅料信息实体
 * 
 * @author hyh
 *
 */
public class BizAuxiliaryMaterialsVerifyInclude extends DataEntity2<BizAuxiliaryMaterialsVerifyInclude> {

	private static final long serialVersionUID = 1L;

	private Integer auxiliaryMaterialsVerifyId;// 对账单Id
	private String auxiliaryMaterialsNo;// 辅料编号
	private String auxiliaryMaterilasName; //辅料名称
	private String specifications;//规格型号
	private String measurementUnit;		// 单位
	private Integer auxiliaryMaterialsCount;// 辅料数量
	private Double auxiliaryMaterialsSupplierPrice;// 辅料供应商结算价
	private Double auxiliaryMaterialsWangzhenPrice;// 辅料网真结算价价
	private Double auxiliaryMaterialsLaborPrice;// 辅料工人结算价
	private Double auxiliaryMaterialsSupplierPriceAmount;// 辅料供应商总价
	private Double auxiliaryMaterialsWangzhenPriceAmount;// 辅料网真总价
	private Double auxiliaryMaterialsLaborPriceAmount;// 辅料工人总价
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
