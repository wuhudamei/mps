/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import cn.damei.common.persistence.DataEntity2;

/**
 * 标化辅材Entity
 * @author 汪文文
 * @version 2016-12-24
 */
public class BizMaterialsStandardRecords extends DataEntity2<BizMaterialsStandardRecords> {
	
	private static final long serialVersionUID = 1L;
	private Integer storeId;		// 门店id -- '
	private String materialsType;		// 物料类别 -- '
	private String materialsName;		// 物料名称 -- '
	private String materialsUnit;		// 物料单位 -- '
	private Double materialsPrice;		// 物料单价 -- '
	private String isEnabled;		// 是否启用 -- '1.启用；0.停用
	private Double receiveNumber = 0.0;
	private Double maxReceiveNumberSnap;//领取上限快照
	private Double applyNumberTotalSnap;//已申请总数快照
	private Double receiveNumberTotalSnap;//已领取总数快照
	private Double applyNumberSuggest; //建议申请数量
	
	public Double getApplyNumberSuggest() {
		return applyNumberSuggest;
	}

	public void setApplyNumberSuggest(Double applyNumberSuggest) {
		this.applyNumberSuggest = applyNumberSuggest;
	}

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

	public Double getMaterialsAmount() {
		return (double)Math.round(receiveNumber * materialsPrice*100)/100;
		
	}

	public Double getReceiveNumber() {
		return receiveNumber;
	}

	public void setReceiveNumber(Double receiveNumber) {
		this.receiveNumber = receiveNumber;
	}

	public BizMaterialsStandardRecords() {
		super();
	}

	public BizMaterialsStandardRecords(Integer id){
		super(id);
	}

	@NotNull(message="门店id -- '不能为空")
	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	
	@Length(min=1, max=100, message="物料类别 -- '长度必须介于 1 和 100 之间")
	public String getMaterialsType() {
		return materialsType;
	}

	public void setMaterialsType(String materialsType) {
		this.materialsType = materialsType;
	}
	
	@Length(min=1, max=100, message="物料名称 -- '长度必须介于 1 和 100 之间")
	public String getMaterialsName() {
		return materialsName;
	}

	public void setMaterialsName(String materialsName) {
		this.materialsName = materialsName;
	}
	
	@Length(min=1, max=10, message="物料单位 -- '长度必须介于 1 和 10 之间")
	public String getMaterialsUnit() {
		return materialsUnit;
	}

	public void setMaterialsUnit(String materialsUnit) {
		this.materialsUnit = materialsUnit;
	}
	
	public Double getMaterialsPrice() {
		//保留两位小数
		return (double)Math.round(materialsPrice*100)/100;
		 
	}

	public void setMaterialsPrice(Double materialsPrice) {
		this.materialsPrice = materialsPrice;
	}

	public String getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(String isEnabled) {
		this.isEnabled = isEnabled;
	}
	
}