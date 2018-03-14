package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity;
/**
 * 
 * @author lft
 * 輔料的申請 查詢實體
 * 2017-5-16
 *
 */
public class BizOrderMaterialsStandardQuery extends DataEntity<BizOrderMaterialsStandardQuery> {
	private static final long serialVersionUID = 1L;
	
	private String orderId; //訂單id
	private String storeId; //门店id
	private String storeName; //门店
	private String orderNumber;//订单号
	private String isScrap;//订单状态
	private String orderArea;//订单区域
	private Integer orderAreaId;//订单区域
	private String customerName;//客户姓名
	private String itemManager;//項目經理
	private double sum;//金額
	private double shippingFee;//配送费
	private String materialsLargeType; //1 标化 2 筒灯

	public double getShippingFee() {
		return shippingFee;
	}

	public void setShippingFee(double shippingFee) {
		this.shippingFee = shippingFee;
	}

	public Integer getOrderAreaId() {
		return orderAreaId;
	}

	public void setOrderAreaId(Integer orderAreaId) {
		this.orderAreaId = orderAreaId;
	}

	public String getIsScrap() {
		return isScrap;
	}

	public void setIsScrap(String isScrap) {
		this.isScrap = isScrap;
	}

	public String getOrderArea() {
		return orderArea;
	}

	public void setOrderArea(String orderArea) {
		this.orderArea = orderArea;
	}

	public String getMaterialsLargeType() {
		return materialsLargeType;
	}
	public void setMaterialsLargeType(String materialsLargeType) {
		this.materialsLargeType = materialsLargeType;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getItemManager() {
		return itemManager;
	}
	public void setItemManager(String itemManager) {
		this.itemManager = itemManager;
	}
	public double getSum() {
		return sum;
	}
	public void setSum(double sum) {
		this.sum = sum;
	}
	
	
	
	
}
