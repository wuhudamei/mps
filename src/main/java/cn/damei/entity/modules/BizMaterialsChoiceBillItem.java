/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity2;

/**
 * 选材单材料表Entity
 * @author wyb
 * @version 2017-06-13
 */
public class BizMaterialsChoiceBillItem extends DataEntity2<BizMaterialsChoiceBillItem> {
	
	private static final long serialVersionUID = 1L;
	private Integer materialsChoiceBillId;		// 选材单id
	private String materialsChoiceType;		// 选材类型
	private String materialsChoiceCategoryCode;		// 选材类目编码
	private String brand;		// 品牌
	private String model;		// 型号
	private String attribute;		// 属性
	private String unit;		// 单位
	private String spec;		// 规格
	private String position;		// 位置
	private String budgetNumber1;		// 预算用量
	private String budgetNumber2;		// 预算用量 --面积
	private Double lossRatio;		// 损耗系数
	private Double includeLossNumber;		// 含损耗用量
	private Double unitPrice;		// 单价
	private Double totalAmount;		// 合价
	private String categoryName;		// 选材类目编码 --名称
	
	private Integer storeId;		// 门店id
	private String customerName;		// 客户姓名
	private String storeName;		// 门店名称
	private String orderNumber;		// 订单编号
	private String firstMaterialsChoiceCategoryCode;		// 选材类目编码(一级)
	private String firstCategoryName;		// 选材类目编码 --名称(一级)
	private String supplierName;	//供应商名称
	private String supplierNo;	//供应商code
	
	
	public BizMaterialsChoiceBillItem() {
		super();
	}

	public BizMaterialsChoiceBillItem(Integer id){
		super(id);
	}

	public Integer getMaterialsChoiceBillId() {
		return materialsChoiceBillId;
	}

	public void setMaterialsChoiceBillId(Integer materialsChoiceBillId) {
		this.materialsChoiceBillId = materialsChoiceBillId;
	}
	
	@Length(min=0, max=10, message="选材类型长度必须介于 0 和 10 之间")
	public String getMaterialsChoiceType() {
		return materialsChoiceType;
	}

	public void setMaterialsChoiceType(String materialsChoiceType) {
		this.materialsChoiceType = materialsChoiceType;
	}
	
	@Length(min=0, max=100, message="选材类目编码长度必须介于 0 和 100 之间")
	public String getMaterialsChoiceCategoryCode() {
		return materialsChoiceCategoryCode;
	}

	public void setMaterialsChoiceCategoryCode(String materialsChoiceCategoryCode) {
		this.materialsChoiceCategoryCode = materialsChoiceCategoryCode;
	}
	
	@Length(min=0, max=100, message="品牌长度必须介于 0 和 100 之间")
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	@Length(min=0, max=100, message="型号长度必须介于 0 和 100 之间")
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	
	@Length(min=0, max=100, message="属性长度必须介于 0 和 100 之间")
	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	
	@Length(min=0, max=100, message="单位长度必须介于 0 和 100 之间")
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	@Length(min=0, max=100, message="规格长度必须介于 0 和 100 之间")
	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}
	
	@Length(min=0, max=100, message="位置长度必须介于 0 和 100 之间")
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
	public String getBudgetNumber1() {
		return budgetNumber1;
	}

	public void setBudgetNumber1(String budgetNumber1) {
		this.budgetNumber1 = budgetNumber1;
	}
	
	public Double getLossRatio() {
		return lossRatio;
	}

	public void setLossRatio(Double lossRatio) {
		this.lossRatio = lossRatio;
	}
	
	public Double getIncludeLossNumber() {
		return includeLossNumber;
	}

	public void setIncludeLossNumber(Double includeLossNumber) {
		this.includeLossNumber = includeLossNumber;
	}
	
	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getFirstMaterialsChoiceCategoryCode() {
		return firstMaterialsChoiceCategoryCode;
	}

	public void setFirstMaterialsChoiceCategoryCode(String firstMaterialsChoiceCategoryCode) {
		this.firstMaterialsChoiceCategoryCode = firstMaterialsChoiceCategoryCode;
	}

	public String getFirstCategoryName() {
		return firstCategoryName;
	}

	public void setFirstCategoryName(String firstCategoryName) {
		this.firstCategoryName = firstCategoryName;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplierNo() {
		return supplierNo;
	}

	public void setSupplierNo(String supplierNo) {
		this.supplierNo = supplierNo;
	}

	public String getBudgetNumber2() {
		return budgetNumber2;
	}

	public void setBudgetNumber2(String budgetNumber2) {
		this.budgetNumber2 = budgetNumber2;
	}

	

	
	
	
}