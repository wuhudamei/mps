
package cn.damei.entity.modules;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity2;


public class BizOrderMaterialsStandard extends DataEntity2<BizOrderMaterialsStandard> {
	
	private static final long serialVersionUID = 1L;
	private Integer orderId;
	private Integer materialsStandardId;
	private String materialsType;
	private String materialsName;
	private String materialsUnit;
	private Double materialsPrice;
	private Double materialsAmount;
	private Double applyNumberTotal;
	private Double receiveNumberTotal;
	private Double maxReceiveNumber;
	private Double applyNumberSuggest;
	private String materialsStandardReceiveBillCode;
	private String applyEm;
	private Date applyDatetime;
	
	
	public String getApplyEm() {
		return applyEm;
	}

	public void setApplyEm(String applyEm) {
		this.applyEm = applyEm;
	}

	public Date getApplyDatetime() {
		return applyDatetime;
	}

	public void setApplyDatetime(Date applyDatetime) {
		this.applyDatetime = applyDatetime;
	}

	public String getMaterialsStandardReceiveBillCode() {
		return materialsStandardReceiveBillCode;
	}

	public void setMaterialsStandardReceiveBillCode(String materialsStandardReceiveBillCode) {
		this.materialsStandardReceiveBillCode = materialsStandardReceiveBillCode;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	public Integer getMaterialsStandardId() {
		return materialsStandardId;
	}

	public void setMaterialsStandardId(Integer materialsStandardId) {
		this.materialsStandardId = materialsStandardId;
	}
	
	@Length(min=0, max=100, message="类型长度必须介于 0 和 100 之间")
	public String getMaterialsType() {
		return materialsType;
	}

	public void setMaterialsType(String materialsType) {
		this.materialsType = materialsType;
	}
	
	@Length(min=0, max=100, message="名称长度必须介于 0 和 100 之间")
	public String getMaterialsName() {
		return materialsName;
	}

	public void setMaterialsName(String materialsName) {
		this.materialsName = materialsName;
	}
	
	@Length(min=0, max=10, message="单位长度必须介于 0 和 10 之间")
	public String getMaterialsUnit() {
		return materialsUnit;
	}

	public void setMaterialsUnit(String materialsUnit) {
		this.materialsUnit = materialsUnit;
	}
	
	public Double getMaterialsPrice() {
		return materialsPrice;
	}

	public void setMaterialsPrice(Double materialsPrice) {
		this.materialsPrice = materialsPrice;
	}
	
	public Double getMaterialsAmount() {
		return materialsAmount;
	}

	public void setMaterialsAmount(Double materialsAmount) {
		this.materialsAmount = materialsAmount;
	}
	
	public Double getApplyNumberTotal() {
		return applyNumberTotal;
	}

	public void setApplyNumberTotal(Double applyNumberTotal) {
		this.applyNumberTotal = applyNumberTotal;
	}
	
	public Double getReceiveNumberTotal() {
		return receiveNumberTotal;
	}

	public void setReceiveNumberTotal(Double receiveNumberTotal) {
		this.receiveNumberTotal = receiveNumberTotal;
	}
	
	public Double getMaxReceiveNumber() {
		return maxReceiveNumber;
	}

	public void setMaxReceiveNumber(Double maxReceiveNumber) {
		this.maxReceiveNumber = maxReceiveNumber;
	}
	
	public Double getApplyNumberSuggest() {
		return applyNumberSuggest;
	}

	public void setApplyNumberSuggest(Double applyNumberSuggest) {
		this.applyNumberSuggest = applyNumberSuggest;
	}
	
}