package cn.damei.entity.mobile.Worker;

import cn.damei.common.persistence.DataEntity2;

public class SettlementAuxiliary extends DataEntity2<SettlementAuxiliary>{

	/**
	 * @author wang
	 * 2016-10-19
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String auxiliaryMaterialsNo; // 辅料编号
	private String auxiliaryMaterialsName; // 辅料名称
	private String specifications; // 规格
	private String measurementUnit; // 商品单位
	private double laborPrice; // 单价
	private double usedCount;//使用数量
	private double price;
	private Integer orderTaskpackageId;//任务包id
	private String brands;// 品牌
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	public String getMeasurementUnit() {
		return measurementUnit;
	}
	public void setMeasurementUnit(String measurementUnit) {
		this.measurementUnit = measurementUnit;
	}
	public double getLaborPrice() {
		return laborPrice;
	}
	public void setLaborPrice(double laborPrice) {
		this.laborPrice = laborPrice;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getUsedCount() {
		return usedCount;
	}
	public void setUsedCount(double usedCount) {
		this.usedCount = usedCount;
	}
	public Integer getOrderTaskpackageId() {
		return orderTaskpackageId;
	}
	public void setOrderTaskpackageId(Integer orderTaskpackageId) {
		this.orderTaskpackageId = orderTaskpackageId;
	}
	public String getBrands() {
		return brands;
	}
	public void setBrands(String brands) {
		this.brands = brands;
	}
}
