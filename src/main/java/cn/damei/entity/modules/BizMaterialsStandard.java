
package cn.damei.entity.modules;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity2;


public class BizMaterialsStandard extends DataEntity2<BizMaterialsStandard> {
	
	private static final long serialVersionUID = 1L;
	private Integer storeId;
	private String materialsType;
	private String materialsName;
	private String materialsUnit;
	private Double materialsPrice;
	private String isEnabled;
	private Double receiveNumber = 0.0;
	private Double maxReceiveNumber;
	private String materialsLargeType;
	private String isLimitMaxNumber;
	private String [] numberRuleCode;
	private String [] limtArea;
	private String [] sId;
	private List< BizMaterialsStandardNumberSquare> bizMaterialsStandardNumberSquareList;
	
	
	
	public String[] getsId() {
		return sId;
	}

	public void setsId(String[] sId) {
		this.sId = sId;
	}

	public List<BizMaterialsStandardNumberSquare> getBizMaterialsStandardNumberSquareList() {
		return bizMaterialsStandardNumberSquareList;
	}
	
	public void setBizMaterialsStandardNumberSquareList(
			List<BizMaterialsStandardNumberSquare> bizMaterialsStandardNumberSquareList) {
		this.bizMaterialsStandardNumberSquareList = bizMaterialsStandardNumberSquareList;
	}
	public String[] getNumberRuleCode() {
		return numberRuleCode;
	}
	public void setNumberRuleCode(String[] numberRuleCode) {
		this.numberRuleCode = numberRuleCode;
	}
	public String[] getLimtArea() {
		return limtArea;
	}
	public void setLimtArea(String[] limtArea) {
		this.limtArea = limtArea;
	}
	public String getIsLimitMaxNumber() {
		return isLimitMaxNumber;
	}
	public void setIsLimitMaxNumber(String isLimitMaxNumber) {
		this.isLimitMaxNumber = isLimitMaxNumber;
	}
	public String getMaterialsLargeType() {
		return materialsLargeType;
	}
	public void setMaterialsLargeType(String materialsLargeType) {
		this.materialsLargeType = materialsLargeType;
	}
	public Double getMaxReceiveNumber() {
		return maxReceiveNumber;
	}
	public void setMaxReceiveNumber(Double maxReceiveNumber) {
		this.maxReceiveNumber = maxReceiveNumber;
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
		return materialsPrice;
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