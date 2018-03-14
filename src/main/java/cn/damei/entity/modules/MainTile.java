package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;

public class MainTile extends DataEntity2<MainTile>{


	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer purchaseId;
	private String 	mainMateType;
	private String position;
	private String brandCombo;
	private String model;
	private String specification;
	private String unit;
	private double mainMateCount;
	private double includLossCount;
	private double applyCount;
	private String remarks;
	private Double receivedCount;
	private Double owedCount;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public double getMainMateCount() {
		return mainMateCount;
	}
	public void setMainMateCount(double mainMateCount) {
		this.mainMateCount = mainMateCount;
	}
	public double getIncludLossCount() {
		return includLossCount;
	}
	public void setIncludLossCount(double includLossCount) {
		this.includLossCount = includLossCount;
	}
	public double getApplyCount() {
		return applyCount;
	}
	public void setApplyCount(double applyCount) {
		this.applyCount = applyCount;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Double getReceivedCount() {
		return receivedCount;
	}
	public void setReceivedCount(Double receivedCount) {
		this.receivedCount = receivedCount;
	}
	public Double getOwedCount() {
		return owedCount;
	}
	public void setOwedCount(Double owedCount) {
		this.owedCount = owedCount;
	}

}
