package cn.damei.entity.modules;
import java.util.Date;
import java.util.List;

import cn.damei.common.persistence.DataEntity2;
public class BizMaterialsSwitchByOrder extends DataEntity2<BizMaterialsSwitchByOrder> {

	/**
	 * 材料类按订单汇总查询（开关面板）
	 * 
	 * @author 王硕
	 * @version 2017-10-27
	 */
	private static final long serialVersionUID = 1L;

	private Integer storeId; // 门店ID
	private Integer orderId; // 订单id
	private String orderNumber; // 订单编号
	private String customerName; // 客户姓名
	private String itemManager; // 项目经理
	private String applyTimes; // 申请次数
	private Integer enginDepartId; // 区域ID
	private Date finalReceiveTime; // 项目经理收货日期
	private Date finalReceiveTimeBegin;//项目经理收货起始日期
	private Date finalReceiveTimeEnd;//项目经理收货结束日期
	private Integer purchaseId;//采购单ID
	private String enginDepartName;//区域名称
	private String customerAddress;//客户地址
	private String orderStatus;//订单状态
	private String projectMode;//工程模式
	private String purchaseCode;//采购单编号
	private Date applyReceiveTime;//期望到货日期
	private Date applyTime;//提交申请日期
	private Date applyTimeBegin;//提交申请开始日期
	private Date applyTimeEnd;//提交申请结束日期
	private String auxiMateCode;//商品编号
	private Double price;//价格
	private Double auxiMateCount;//申请数量
	private Double receivedAuxiMateCount;//已收货数量
	private Double owedAuxiMateCount;//欠货数量
	private String brands;//品牌
	private String 	auxiMaterialName;//商品名称
	private String specifications;//规格
	private Integer categoryId;//材料类别
	private String measurementUnit;//单位
	private String categoryName;//材料类别名称
	private String status;//采购单状态
	private Integer upstairsFloor;//楼层
	private String isElevator;//是否有电梯
	private String realName;//项目经理姓名
	private String phone;//项目经理电话
	private Double laborPrice;//商品工人结算价
	private List<String> purchaseStatusList = null; //采购单状态集合

	public Date getApplyTimeBegin() {
		return applyTimeBegin;
	}

	public void setApplyTimeBegin(Date applyTimeBegin) {
		this.applyTimeBegin = applyTimeBegin;
	}

	public Date getApplyTimeEnd() {
		return applyTimeEnd;
	}

	public void setApplyTimeEnd(Date applyTimeEnd) {
		this.applyTimeEnd = applyTimeEnd;
	}

	public List<String> getPurchaseStatusList() {
		return purchaseStatusList;
	}

	public void setPurchaseStatusList(List<String> purchaseStatusList) {
		this.purchaseStatusList = purchaseStatusList;
	}

	public Double getLaborPrice() {
		return laborPrice;
	}
	public void setLaborPrice(Double laborPrice) {
		this.laborPrice = laborPrice;
	}
	public Integer getUpstairsFloor() {
		return upstairsFloor;
	}
	public void setUpstairsFloor(Integer upstairsFloor) {
		this.upstairsFloor = upstairsFloor;
	}
	public String getIsElevator() {
		return isElevator;
	}
	public void setIsElevator(String isElevator) {
		this.isElevator = isElevator;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getPurchaseCode() {
		return purchaseCode;
	}
	public void setPurchaseCode(String purchaseCode) {
		this.purchaseCode = purchaseCode;
	}
	public Date getApplyReceiveTime() {
		return applyReceiveTime;
	}
	public void setApplyReceiveTime(Date applyReceiveTime) {
		this.applyReceiveTime = applyReceiveTime;
	}
	public Date getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	public String getAuxiMateCode() {
		return auxiMateCode;
	}
	public void setAuxiMateCode(String auxiMateCode) {
		this.auxiMateCode = auxiMateCode;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getAuxiMateCount() {
		return auxiMateCount;
	}
	public void setAuxiMateCount(Double auxiMateCount) {
		this.auxiMateCount = auxiMateCount;
	}
	public Double getReceivedAuxiMateCount() {
		return receivedAuxiMateCount;
	}
	public void setReceivedAuxiMateCount(Double receivedAuxiMateCount) {
		this.receivedAuxiMateCount = receivedAuxiMateCount;
	}
	public Double getOwedAuxiMateCount() {
		return owedAuxiMateCount;
	}
	public void setOwedAuxiMateCount(Double owedAuxiMateCount) {
		this.owedAuxiMateCount = owedAuxiMateCount;
	}
	public String getBrands() {
		return brands;
	}
	public void setBrands(String brands) {
		this.brands = brands;
	}
	public String getAuxiMaterialName() {
		return auxiMaterialName;
	}
	public void setAuxiMaterialName(String auxiMaterialName) {
		this.auxiMaterialName = auxiMaterialName;
	}
	public String getSpecifications() {
		return specifications;
	}
	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getMeasurementUnit() {
		return measurementUnit;
	}
	public void setMeasurementUnit(String measurementUnit) {
		this.measurementUnit = measurementUnit;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getProjectMode() {
		return projectMode;
	}
	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}
	public String getEnginDepartName() {
		return enginDepartName;
	}
	public void setEnginDepartName(String enginDepartName) {
		this.enginDepartName = enginDepartName;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
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
	public String getApplyTimes() {
		return applyTimes;
	}
	public void setApplyTimes(String applyTimes) {
		this.applyTimes = applyTimes;
	}
	public Integer getEnginDepartId() {
		return enginDepartId;
	}
	public void setEnginDepartId(Integer enginDepartId) {
		this.enginDepartId = enginDepartId;
	}
	public Date getFinalReceiveTime() {
		return finalReceiveTime;
	}
	public void setFinalReceiveTime(Date finalReceiveTime) {
		this.finalReceiveTime = finalReceiveTime;
	}
	public Date getFinalReceiveTimeBegin() {
		return finalReceiveTimeBegin;
	}
	public void setFinalReceiveTimeBegin(Date finalReceiveTimeBegin) {
		this.finalReceiveTimeBegin = finalReceiveTimeBegin;
	}
	public Date getFinalReceiveTimeEnd() {
		return finalReceiveTimeEnd;
	}
	public void setFinalReceiveTimeEnd(Date finalReceiveTimeEnd) {
		this.finalReceiveTimeEnd = finalReceiveTimeEnd;
	}
	public Integer getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(Integer purchaseId) {
		this.purchaseId = purchaseId;
	}
	
}