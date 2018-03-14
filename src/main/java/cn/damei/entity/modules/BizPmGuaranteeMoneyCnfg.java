
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity2;


public class BizPmGuaranteeMoneyCnfg extends DataEntity2<BizPmGuaranteeMoneyCnfg> {
	
	private static final long serialVersionUID = 1L;
	private Integer storeId;
	private String projectMode;
	private Double guaranteeMoneyMax;
	private Double guaranteeMoneyPerOrder;
	
	public BizPmGuaranteeMoneyCnfg() {
		super();
	}

	public BizPmGuaranteeMoneyCnfg(Integer id){
		super(id);
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	
	@Length(min=0, max=10, message="工程模式 -- '长度必须介于 0 和 10 之间")
	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}
	
	public Double getGuaranteeMoneyMax() {
		return guaranteeMoneyMax;
	}

	public void setGuaranteeMoneyMax(Double guaranteeMoneyMax) {
		this.guaranteeMoneyMax = guaranteeMoneyMax;
	}
	
	public Double getGuaranteeMoneyPerOrder() {
		return guaranteeMoneyPerOrder;
	}

	public void setGuaranteeMoneyPerOrder(Double guaranteeMoneyPerOrder) {
		this.guaranteeMoneyPerOrder = guaranteeMoneyPerOrder;
	}
	
}