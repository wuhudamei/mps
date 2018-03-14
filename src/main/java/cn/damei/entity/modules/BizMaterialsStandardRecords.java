
package cn.damei.entity.modules;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import cn.damei.common.persistence.DataEntity2;


public class BizMaterialsStandardRecords extends DataEntity2<BizMaterialsStandardRecords> {
	
	private static final long serialVersionUID = 1L;
	private Integer storeId;
	private String materialsType;
	private String materialsName;
	private String materialsUnit;
	private Double materialsPrice;
	private String isEnabled;
	private Double receiveNumber = 0.0;
	private Double maxReceiveNumberSnap;
	private Double applyNumberTotalSnap;
	private Double receiveNumberTotalSnap;
	private Double applyNumberSuggest;
	
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