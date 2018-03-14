/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.mobile.Manager;

import java.util.List;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import cn.damei.common.persistence.DataEntity2;

/**
 * 标化辅材Entity
 * @author 汪文文
 * @version 2016-12-24
 */
public class BizMaterialsStandard extends DataEntity2<BizMaterialsStandard> {
	
	private static final long serialVersionUID = 1L;
	private Integer storeId;		// 门店id -- '
	private String materialsType;		// 物料类别 -- '
	private String materialsName;		// 物料名称 -- '
	private String materialsUnit;		// 物料单位 -- '
	private Double materialsPrice;		// 物料单价 -- '
	private String isEnabled;		// 是否启用 -- '1.启用；0.停用
	private Double receiveNumber = 0.0;
	private Double maxReceiveNumber; // 物料最大可申请上限
	private List<String> ruleCodeNumber;//面积  计算规则
	
	
	
	public List<String> getRuleCodeNumber() {
		return ruleCodeNumber;
	}

	public void setRuleCodeNumber(List<String> ruleCodeNumber) {
		this.ruleCodeNumber = ruleCodeNumber;
	}

	public Double getMaxReceiveNumber() {
		return maxReceiveNumber;
	}

	public void setMaxReceiveNumber(Double maxReceiveNumber) {
		this.maxReceiveNumber = maxReceiveNumber;
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

	public BizMaterialsStandard() {
		super();
	}

	public BizMaterialsStandard(Integer id){
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