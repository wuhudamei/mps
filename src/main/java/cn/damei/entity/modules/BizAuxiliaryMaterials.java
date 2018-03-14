/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity;

/**
 * 辅料管理Entity
 * @author chy
 * @version 2016-09-09
 */
public class BizAuxiliaryMaterials extends DataEntity<BizAuxiliaryMaterials> {
	
	private static final long serialVersionUID = 1L;
	private String storeId;		// 门店
	private String auxiliaryMaterialsNo;		// 材料编号
	private String auxiliaryMaterialsName;		// 材料名称
	private String empWorkType;		// 常用工种
	private String categoryId;		// 材料类别
	private String specifications;		// 规格
	private String measurementUnit;		// 单位
	private String status;		// 状态
	private String brands;		// 品牌
	private String picUrl;		// 辅料图片路径
	private String dropDisp;		// 下拉列表显示用
	private String unit;		//单位名称
	private String workerType;	//常用工种
	private Double price;	//价格
	private String categoryName;	//材料类别名称
	private String isSandCement;	//是否沙子水泥 1.是 0.否
	private Double supplierPrice;	//供应商价格
	private Double wangzhenPrice;	//网真价格
	
	
	public String getIsSandCement() {
		return isSandCement;
	}

	public void setIsSandCement(String isSandCement) {
		this.isSandCement = isSandCement;
	}

	public BizAuxiliaryMaterials() {
		super();
	}

	public BizAuxiliaryMaterials(String id){
		super(id);
	}

	@Length(min=0, max=64, message="门店长度必须介于 0 和 64 之间")
	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	
	@Length(min=0, max=100, message="材料编号长度必须介于 0 和 100 之间")
	public String getAuxiliaryMaterialsNo() {
		return auxiliaryMaterialsNo;
	}

	public void setAuxiliaryMaterialsNo(String auxiliaryMaterialsNo) {
		this.auxiliaryMaterialsNo = auxiliaryMaterialsNo;
	}
	
	@Length(min=0, max=100, message="材料名称长度必须介于 0 和 100 之间")
	public String getAuxiliaryMaterialsName() {
		return auxiliaryMaterialsName;
	}

	public void setAuxiliaryMaterialsName(String auxiliaryMaterialsName) {
		this.auxiliaryMaterialsName = auxiliaryMaterialsName;
	}
	
	@Length(min=0, max=3, message="常用工种长度必须介于 0 和 3 之间")
	public String getEmpWorkType() {
		return empWorkType;
	}

	public void setEmpWorkType(String empWorkType) {
		this.empWorkType = empWorkType;
	}
	
	@Length(min=0, max=11, message="材料类别长度必须介于 0 和 11 之间")
	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	
	@Length(min=1, max=255, message="规格长度必须介于 1 和 255 之间")
	public String getSpecifications() {
		return specifications;
	}

	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}
	
	@Length(min=1, max=25, message="单位长度必须介于 1 和 25 之间")
	public String getMeasurementUnit() {
		return measurementUnit;
	}

	public void setMeasurementUnit(String measurementUnit) {
		this.measurementUnit = measurementUnit;
	}
	
	@Length(min=0, max=1, message="状态长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=255, message="品牌长度必须介于 0 和 255 之间")
	public String getBrands() {
		return brands;
	}

	public void setBrands(String brands) {
		this.brands = brands;
	}
	
	@Length(min=0, max=255, message="辅料图片路径长度必须介于 0 和 255 之间")
	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getDropDisp() {
		return dropDisp;
	}

	public void setDropDisp(String dropDisp) {
		this.dropDisp = dropDisp;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getWorkerType() {
		return workerType;
	}

	public void setWorkerType(String workerType) {
		this.workerType = workerType;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Double getSupplierPrice() {
		return supplierPrice;
	}

	public void setSupplierPrice(Double supplierPrice) {
		this.supplierPrice = supplierPrice;
	}

	public Double getWangzhenPrice() {
		return wangzhenPrice;
	}

	public void setWangzhenPrice(Double wangzhenPrice) {
		this.wangzhenPrice = wangzhenPrice;
	}
	
	
}