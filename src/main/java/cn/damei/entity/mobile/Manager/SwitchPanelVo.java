package cn.damei.entity.mobile.Manager;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

/** 
* @author 梅浩   meihao@zzhyun.cn: 
* @version 创建时间：2016年10月10日 下午2:58:20 
* 开关面板Vo
*/

public class SwitchPanelVo extends DataEntity2<SwitchPanelVo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	

	
	private Integer id; //主材商品表id
	private Integer orderId;//订单id
	private Integer purchaseId;//采购单id
	private Integer receivedCount;
	private Integer owedCount;
	private String isCount;//是否计算 数量限制  1:计算 0:不计算
	
	public String getIsCount() {
		return isCount;
	}
	public void setIsCount(String isCount) {
		this.isCount = isCount;
	}
	public Integer getReceivedCount() {
		return receivedCount;
	}
	public void setReceivedCount(Integer receivedCount) {
		this.receivedCount = receivedCount;
	}
	public Integer getOwedCount() {
		return owedCount;
	}
	public void setOwedCount(Integer owedCount) {
		this.owedCount = owedCount;
	}
	public Integer getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(Integer purchaseId) {
		this.purchaseId = purchaseId;
	}
	private String switchPanelType;//开关面板类型  2 是开关面板
	private String switchPanelCode; //开关面板编号
	private String switchPanelName; //开关面板名称
	private String specification ;// 规格
	private String measurementUnit;//计量单位  都是个
	private String  status;//状态 0 停用 1:启用
	private String  picUrl; // 图片路径
	private Integer storeId;//门店id
	private Integer Count;//数量
	private Double  price;//价格
	private Integer categoryId;//分类id
	private String brands; //品牌
	private Date effectiveDate;//生效日期
	
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getBrands() {
		return brands;
	}
	public void setBrands(String brands) {
		this.brands = brands;
	}
	private Integer totalCount;//总数
	
	private Double totalPrice;//总价
	
	
	private Integer managerId;
	
	
	public Integer getManagerId() {
		return managerId;
	}
	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public Integer getCount() {
		return Count;
	}
	public void setCount(Integer count) {
		Count = count;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
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
	public String getSwitchPanelType() {
		return switchPanelType;
	}
	public void setSwitchPanelType(String switchPanelType) {
		this.switchPanelType = switchPanelType;
	}
	public String getSwitchPanelCode() {
		return switchPanelCode;
	}
	public void setSwitchPanelCode(String switchPanelCode) {
		this.switchPanelCode = switchPanelCode;
	}
	public String getSwitchPanelName() {
		return switchPanelName;
	}
	public void setSwitchPanelName(String switchPanelName) {
		this.switchPanelName = switchPanelName;
	}
	public String getSpecification() {
		return specification;
	}
	public void setSpecification(String specification) {
		this.specification = specification;
	}
	public String getMeasurementUnit() {
		return measurementUnit;
	}
	public void setMeasurementUnit(String measurementUnit) {
		this.measurementUnit = measurementUnit;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	
	
}
