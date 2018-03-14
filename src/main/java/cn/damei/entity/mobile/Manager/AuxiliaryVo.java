package cn.damei.entity.mobile.Manager;

import cn.damei.common.persistence.DataEntity2;

import java.util.Objects;




public class AuxiliaryVo extends DataEntity2<AuxiliaryVo> {


	private static final long serialVersionUID = 1L;


	private Integer orderId;
	private Integer purchaseId;
	private String auxiMateCode;

	private String remarks;
	private Integer managerId;
	private String managerName;
	private String managerPhone;

	private String pic;
	private String name;
	private String specifications;

	private String unit;


	private Double price;
	

	private Double supplierPrice;
	

	private Double wangzhenPrice;


	private String workType;
	private String workTypeName;




	private Integer categoryId;
	private String categoryName;

	private String submmitStatus;
	
	private String brand ;
	private Integer receivedAuxiMateCount;
	private Integer owedAuxiMateCount;
	private Integer auxiMateCount;
	
	private Integer count;

	private Double totalPrice;
	

	private String isSandCement;
	private Integer storeId;
	
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		AuxiliaryVo that = (AuxiliaryVo) o;
		return Objects.equals(orderId, that.orderId) &&
				Objects.equals(purchaseId, that.purchaseId) &&
				Objects.equals(auxiMateCode, that.auxiMateCode) &&
				Objects.equals(remarks, that.remarks) &&
				Objects.equals(storeId, that.storeId) &&
				Objects.equals(managerId, that.managerId) &&
				Objects.equals(managerName, that.managerName) &&
				Objects.equals(managerPhone, that.managerPhone) &&
				Objects.equals(pic, that.pic) &&
				Objects.equals(name, that.name) &&
				Objects.equals(specifications, that.specifications) &&
				Objects.equals(unit, that.unit) &&
				Objects.equals(price, that.price) &&
				Objects.equals(supplierPrice, that.supplierPrice) &&
				Objects.equals(wangzhenPrice, that.wangzhenPrice) &&
				Objects.equals(workType, that.workType) &&
				Objects.equals(categoryId, that.categoryId) &&
				Objects.equals(categoryName, that.categoryName) &&
				Objects.equals(submmitStatus, that.submmitStatus) &&
				Objects.equals(brand, that.brand) &&
				Objects.equals(receivedAuxiMateCount, that.receivedAuxiMateCount) &&
				Objects.equals(owedAuxiMateCount, that.owedAuxiMateCount) &&
				Objects.equals(count, that.count) &&
				Objects.equals(totalPrice, that.totalPrice);
	}

	@Override
	public int hashCode() {
		return Objects.hash(orderId, purchaseId, auxiMateCode, remarks, storeId, managerId, managerName, managerPhone, pic, name, specifications, unit, price, workType, categoryId, categoryName, submmitStatus, brand, receivedAuxiMateCount, owedAuxiMateCount, count, totalPrice);
	}
	public Integer getStoreId() {
		return storeId;
	}
	
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public Integer getReceivedAuxiMateCount() {
		return receivedAuxiMateCount;
	}

	public void setReceivedAuxiMateCount(Integer receivedAuxiMateCount) {
		this.receivedAuxiMateCount = receivedAuxiMateCount;
	}

	public Integer getOwedAuxiMateCount() {
		return owedAuxiMateCount;
	}

	public void setOwedAuxiMateCount(Integer owedAuxiMateCount) {
		this.owedAuxiMateCount = owedAuxiMateCount;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getSubmmitStatus() {
		return submmitStatus;
	}

	public void setSubmmitStatus(String submmitStatus) {
		this.submmitStatus = submmitStatus;
	}

	
	
	public String getIsSandCement() {
		return isSandCement;
	}

	public void setIsSandCement(String isSandCement) {
		this.isSandCement = isSandCement;
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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getManagerPhone() {
		return managerPhone;
	}

	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String getSpecifications() {
		return specifications;
	}

	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getWorkType() {
		return workType;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getAuxiMateCount() {
		return auxiMateCount;
	}

	public void setAuxiMateCount(Integer auxiMateCount) {
		this.auxiMateCount = auxiMateCount;
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

	public String getWorkTypeName() {
		return workTypeName;
	}

	public void setWorkTypeName(String workTypeName) {
		this.workTypeName = workTypeName;
	}
	

}
