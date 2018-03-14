package cn.damei.entity.mobile.Manager;

import cn.damei.common.persistence.DataEntity2;

public class PurchaseMainMate extends DataEntity2<PurchaseMainMate> {

	private static final long serialVersionUID = 1L;

	private Integer purchaseId;
	private String mainMateType;
	private String position;
	private String brandCombo;
	private String model;

	private String specification;
	private String unit;
	private Double count;
	private Double includLossCount;
	private Double applyCounta;
	private String remarks;
	private String mainMateTypeName;
	private String attribute;
	private String supplier;
	private Double receivedWallFloorCount;
	private Double owedWallFloorCount;

	private String isCountSquare;
	private Double unitSquare;
	private Double applySquare;

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