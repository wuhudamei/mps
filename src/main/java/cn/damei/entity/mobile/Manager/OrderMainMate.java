package cn.damei.entity.mobile.Manager;

import cn.damei.common.persistence.DataEntity2;

public class OrderMainMate extends DataEntity2<OrderMainMate> {

	private static final long serialVersionUID = 1L;

	private Integer orderId; // 订单id
	private String mainMateType; // 主材类型 4墙砖，3地砖
	private String mainMateTypeName; // 主材类型名称
	private String position; // 位置 厨房--
	private String brandCombo; // 品牌套餐
	private String model; // 类型

	private String specification; // 规格
	private String attribute; // 属性
	private String supplier; // 供应商
	private String unit; // 单位
	private Double count; // 数量
	private Double includLossCount; // 含耗损数量
	private Double applyCounta; // 实发数量
	private String remarks; // 备注
	private String isCountSquare; // 是否计算面积 1为是 0 为否
	private String unitSquare; // 面积
	private Double purchaseCount; // 已申请数量

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getMainMateType() {
		return mainMateType;
	}

	public void setMainMateType(String mainMateType) {
		this.mainMateType = mainMateType;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getBrandCombo() {
		return brandCombo;
	}

	public void setBrandCombo(String brandCombo) {
		this.brandCombo = brandCombo;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Double getCount() {
		return count;
	}

	public void setCount(Double count) {
		this.count = count;
	}

	public Double getIncludLossCount() {
		return includLossCount;
	}

	public void setIncludLossCount(Double includLossCount) {
		this.includLossCount = includLossCount;
	}

	public Double getApplyCounta() {
		return applyCounta;
	}

	public void setApplyCounta(Double applyCounta) {
		this.applyCounta = applyCounta;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getIsCountSquare() {
		return isCountSquare;
	}

	public void setIsCountSquare(String isCountSquare) {
		this.isCountSquare = isCountSquare;
	}

	public String getUnitSquare() {
		return unitSquare;
	}

	public void setUnitSquare(String unitSquare) {
		this.unitSquare = unitSquare;
	}

	public Double getPurchaseCount() {
		return purchaseCount;
	}

	public void setPurchaseCount(Double purchaseCount) {
		this.purchaseCount = purchaseCount;
	}

	public String getMainMateTypeName() {
		return mainMateTypeName;
	}

	public void setMainMateTypeName(String mainMateTypeName) {
		this.mainMateTypeName = mainMateTypeName;
	}

}