package cn.damei.entity.mobile.Manager;

import cn.damei.common.persistence.DataEntity2;



public class OrderTaskpackageAuxiliaryMaterials extends DataEntity2<OrderTaskpackageAuxiliaryMaterials>{

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer orderId;
	private Integer orderTaskpackageId;
	private String auxiliaryMaterialsNo;
	private Double usedCount;
	private Double price;
	private Double supplierPrice;
	private Double wangzhenPrice;
	private char isSandCement;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getOrderTaskpackageId() {
		return orderTaskpackageId;
	}
	public void setOrderTaskpackageId(Integer orderTaskpackageId) {
		this.orderTaskpackageId = orderTaskpackageId;
	}
	public String getAuxiliaryMaterialsNo() {
		return auxiliaryMaterialsNo;
	}
	public void setAuxiliaryMaterialsNo(String auxiliaryMaterialsNo) {
		this.auxiliaryMaterialsNo = auxiliaryMaterialsNo;
	}
	public Double getUsedCount() {
		return usedCount;
	}
	public void setUsedCount(Double usedCount) {
		this.usedCount = usedCount;
	}
	public Double getPrice() {
		if(this.price == null){
			this.price = 0.0;
		}
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public char getIsSandCement() {
		return isSandCement;
	}
	public void setIsSandCement(char isSandCement) {
		this.isSandCement = isSandCement;
	}
	public Double getSupplierPrice() {
		if(this.supplierPrice == null){
			this.supplierPrice = 0.0;
		}
		return supplierPrice;
	}
	public void setSupplierPrice(Double supplierPrice) {
		this.supplierPrice = supplierPrice;
	}
	public Double getWangzhenPrice() {
		if(this.wangzhenPrice == null){
			this.wangzhenPrice = 0.0;
		}
		return wangzhenPrice;
	}
	public void setWangzhenPrice(Double wangzhenPrice) {
		this.wangzhenPrice = wangzhenPrice;
	}
	
}
