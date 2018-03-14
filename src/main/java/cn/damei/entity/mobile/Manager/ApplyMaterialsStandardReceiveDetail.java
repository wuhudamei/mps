/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.mobile.Manager;

import org.hibernate.validator.constraints.Length;
import cn.damei.common.persistence.DataEntity2;

/**
 * 标化辅材领取详情Entity
 * @author 汪文文
 * @version 2016-12-26
 */
public class ApplyMaterialsStandardReceiveDetail extends DataEntity2<ApplyMaterialsStandardReceiveDetail> {
	
	private static final long serialVersionUID = 1L;
	private Integer materialsStandardReceiveBillId;		// 标化辅材领取单id -- '
	private Integer materialsId;
	private String materialsType;		// 物料类别
	private String materialsName;		// 物料名称
	private String materialsUnit;		// 物料单位
	private Double materialsPrice;		// 物料单价
	private Double receiveNumber;		// 领取数量
	private Double materialsAmount;		// 物料金额
	private Double maxReceiveNumberSnap;//领取上限快照
	private Double applyNumberTotalSnap;//已申请总数快照
	private Double receiveNumberTotalSnap;//已领取总数快照
	

	public Double getMaxReceiveNumberSnap() {
		return maxReceiveNumberSnap;
	}

	public void setMaxReceiveNumberSnap(Double maxReceiveNumberSnap) {
		this.maxReceiveNumberSnap = maxReceiveNumberSnap;
	}

	public Double getApplyNumberTotalSnap() {
		return applyNumberTotalSnap;
	}

	public void setApplyNumberTotalSnap(Double applyNumberTotalSnap) {
		this.applyNumberTotalSnap = applyNumberTotalSnap;
	}

	public Double getReceiveNumberTotalSnap() {
		return receiveNumberTotalSnap;
	}

	public void setReceiveNumberTotalSnap(Double receiveNumberTotalSnap) {
		this.receiveNumberTotalSnap = receiveNumberTotalSnap;
	}

	public ApplyMaterialsStandardReceiveDetail() {
		super();
	}

	public ApplyMaterialsStandardReceiveDetail(Integer id){
		super(id);
	}

	public Integer getMaterialsId() {
		return materialsId;
	}

	public void setMaterialsId(Integer materialsId) {
		this.materialsId = materialsId;
	}

	public Integer getMaterialsStandardReceiveBillId() {
		return materialsStandardReceiveBillId;
	}

	public void setMaterialsStandardReceiveBillId(Integer materialsStandardReceiveBillId) {
		this.materialsStandardReceiveBillId = materialsStandardReceiveBillId;
	}
	
	@Length(min=0, max=100, message="物料类别长度必须介于 0 和 100 之间")
	public String getMaterialsType() {
		return materialsType;
	}

	public void setMaterialsType(String materialsType) {
		this.materialsType = materialsType;
	}
	
	@Length(min=0, max=100, message="物料名称长度必须介于 0 和 100 之间")
	public String getMaterialsName() {
		return materialsName;
	}

	public void setMaterialsName(String materialsName) {
		this.materialsName = materialsName;
	}
	
	@Length(min=0, max=10, message="物料单位长度必须介于 0 和 10 之间")
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
	
	public Double getReceiveNumber() {
		return receiveNumber;
	}

	public void setReceiveNumber(Double receiveNumber) {
		this.receiveNumber = receiveNumber;
	}
	
	public Double getMaterialsAmount() {
		return materialsAmount;
	}

	public void setMaterialsAmount(Double materialsAmount) {
		this.materialsAmount = materialsAmount;
	}
	
}