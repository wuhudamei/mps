/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity2;

/**
 * 标化辅料（筒灯灯带）订单Entity
 * @author lft
 * @version 2017-05-12
 */
public class BizOrderMaterialsStandard extends DataEntity2<BizOrderMaterialsStandard> {
	
	private static final long serialVersionUID = 1L;
	private Integer orderId;		// 订单主键id
	private Integer materialsStandardId;		// 辅料表主键id
	private String materialsType;		// 类型
	private String materialsName;		// 名称
	private String materialsUnit;		// 单位
	private Double materialsPrice;		// 单价
	private Double materialsAmount;		// 金额
	private Double applyNumberTotal;		// 申请总数
	private Double receiveNumberTotal;		// 领取总数
	private Double maxReceiveNumber;		// 上限
	private Double applyNumberSuggest;		// 建议申请
	private String materialsStandardReceiveBillCode;//申请单号
	private String applyEm;  //申请人
	private Date applyDatetime;  //申请时间
	
	
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