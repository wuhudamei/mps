
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity;


public class BizAuxiliaryMaterials extends DataEntity<BizAuxiliaryMaterials> {
	
	private static final long serialVersionUID = 1L;
	private String storeId;
	private String auxiliaryMaterialsNo;
	private String auxiliaryMaterialsName;
	private String empWorkType;
	private String categoryId;
	private String specifications;
	private String measurementUnit;
	private String status;
	private String brands;
	private String picUrl;
	private String dropDisp;
	private String unit;
	private String workerType;
	private Double price;
	private String categoryName;
	private String isSandCement;
	private Double supplierPrice;
	private Double wangzhenPrice;
	
	
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