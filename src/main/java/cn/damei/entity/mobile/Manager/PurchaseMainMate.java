package cn.damei.entity.mobile.Manager;

import cn.damei.common.persistence.DataEntity2;

public class PurchaseMainMate extends DataEntity2<PurchaseMainMate> {

	private static final long serialVersionUID = 1L;

	private Integer purchaseId; // 采购单id
	private String mainMateType; // 主材类型 墙砖，地砖，开关面板
	private String position; // 位置 厨房--
	private String brandCombo; // 品牌套餐
	private String model; // 类型

	private String specification; // 规格
	private String unit; // 单位
	private Double count; // 数量
	private Double includLossCount; // 含耗损数量
	private Double applyCounta; // 实发数量
	private String remarks; // 备注
	private String mainMateTypeName; // 主材类型名称
	private String attribute; // 属性
	private String supplier; // 供应商
	private Double receivedWallFloorCount; // 已收货数量
	private Double owedWallFloorCount; // 欠货数量

	private String isCountSquare; // 是否计算面积
	private Double unitSquare; // 单位面积
	private Double applySquare; // 申请面积

	public String getIsCountSquare() {
		return isCountSquare;
	}

	public void setIsCountSquare(String isCountSquare) {
		this.isCountSquare = isCountSquare;
	}

	public Double getUnitSquare() {
		return unitSquare;
	}

	public void setUnitSquare(Double unitSquare) {
		this.unitSquare = unitSquare;
	}

	public Double getApplySquare() {
		return applySquare;
	}

	public void setApplySquare(Double applySquare) {
		this.applySquare = applySquare;
	}

	public Integer getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(Integer purchaseId) {
		this.purchaseId = purchaseId;
	}

	public String getMainMateType() {
		return mainMateType;
	}

	public String getMainMateTypeName() {
		return mainMateTypeName;
	}

	public void setMainMateTypeName(String mainMateTypeName) {
		this.mainMateTypeName = mainMateTypeName;
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

	public Double getReceivedWallFloorCount() {
		return receivedWallFloorCount;
	}

	public void setReceivedWallFloorCount(Double receivedWallFloorCount) {
		this.receivedWallFloorCount = receivedWallFloorCount;
	}

	public Double getOwedWallFloorCount() {
		return owedWallFloorCount;
	}

	public void setOwedWallFloorCount(Double owedWallFloorCount) {
		this.owedWallFloorCount = owedWallFloorCount;
	}

}