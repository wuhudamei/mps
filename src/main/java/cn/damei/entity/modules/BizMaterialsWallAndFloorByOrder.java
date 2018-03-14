package cn.damei.entity.modules;
import java.util.Date;
import java.util.List;

import cn.damei.common.persistence.DataEntity2;
public class BizMaterialsWallAndFloorByOrder extends DataEntity2<BizMaterialsWallAndFloorByOrder> {

	/**
	 * 材料类按订单汇总查询(墙地砖）
	 * 
	 * @author 王硕
	 * @version 2017-10-26
	 */
	private static final long serialVersionUID = 1L;

	private Integer storeId; // 门店ID
	private Integer orderId; // 订单id
	private String orderNumber; // 订单编号
	private String customerName; // 客户姓名
	private String itemManager; // 项目经理
	private String applyTimes; // 申请次数
	private Integer enginDepartId; // 区域ID
	private Integer purchaseId;//采购单ID
	private String enginDepartName;//区域名称
	private String customerAddress;//客户地址
	private String orderStatus;//订单状态
	private String projectMode;//工程模式
	private String purchaseCode;//采购单编号
	private Date applyReceiveTime;//期望到货日期
	private Date applyTime;//提交申请日期
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
	private String realName;//项目经理姓名
	private String remarks;//备注
	private String position;//位置
	private Integer mainMateType;//项目名称Id
	private String model;//型号
	private Double mainMateCount;//数量
	private Double includLossCount;//含损耗数量
	private String label;//项目名称
	private List<String> purchaseStatusList = null; //采购单状态集合

	public List<String> getPurchaseStatusList() {
		return purchaseStatusList;
	}
	public void setPurchaseStatusList(List<String> purchaseStatusList) {
		this.purchaseStatusList = purchaseStatusList;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Integer getMainMateType() {
		return mainMateType;
	}
	public void setMainMateType(Integer mainMateType) {
		this.mainMateType = mainMateType;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public Double getMainMateCount() {
		return mainMateCount;
	}
	public void setMainMateCount(Double mainMateCount) {
		this.mainMateCount = mainMateCount;
	}
	public Double getIncludLossCount() {
		return includLossCount;
	}
	public void setIncludLossCount(Double includLossCount) {
		this.includLossCount = includLossCount;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
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
	public Integer getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(Integer purchaseId) {
		this.purchaseId = purchaseId;
	}
	
}