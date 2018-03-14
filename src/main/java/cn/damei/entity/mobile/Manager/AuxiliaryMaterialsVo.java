package cn.damei.entity.mobile.Manager;

import cn.damei.common.persistence.DataEntity2;

/**
 * @author 梅浩 meihao@zzhyun.cn:
 * @version 创建时间：2016年9月28日 上午11:18:04 辅料Vo
 */

public class AuxiliaryMaterialsVo extends DataEntity2<AuxiliaryMaterialsVo> {

	private static final long serialVersionUID = 1L;
	
	private String auxiliaryMaterialsNo; // 辅料编号
	private String auxiliaryMaterialsName; // 辅料名称
	private String specifications; // 规格
	private Integer totalAuxiMateCount; // 送货数量
	private String measurementUnit; // 商品单位
	private String measurementUnitLabel; // 商品单位名称
	private String laborPrice; // 单价(工人结算价)
	private String supplierPrice;//供应商价格
	private String wangzhenPrice;//网真价格（门店价格）
	private String picUrl; // 图片
	private Double userCountTotal; // 辅料所有实际使用数量
	
	private Integer orderAuxiMateId; // 订单任务包辅料id
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
