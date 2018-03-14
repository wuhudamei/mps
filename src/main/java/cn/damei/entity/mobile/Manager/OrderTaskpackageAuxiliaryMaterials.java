package cn.damei.entity.mobile.Manager;

import cn.damei.common.persistence.DataEntity2;

/**
 * 任务包结算Entity
 * @author qww
 * @version 2016-10-15
 */

public class OrderTaskpackageAuxiliaryMaterials extends DataEntity2<OrderTaskpackageAuxiliaryMaterials>{

	private static final long serialVersionUID = 1L;

	private Integer id; // id
	private Integer orderId; // 订单id
	private Integer orderTaskpackageId; // 订单任务包id
	private String auxiliaryMaterialsNo; // 辅料编码
	private Double usedCount; // 实际使用数量
	private Double price;//辅料价格 
	private Double supplierPrice;//供应商价格
	private Double wangzhenPrice;//网真价格
	private char isSandCement;//是否是沙子水泥
	
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
