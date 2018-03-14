
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity2;


public class BizPmOwnpayCnfg extends DataEntity2<BizPmOwnpayCnfg> {
	
	private static final long serialVersionUID = 1L;
	private Integer storeId;
	private String isOldNew;
	private String projectMode;
	private String ownpayName;
	private String unit;
	private Double amount;
	private String remarks;
	private String isEnabled;
	
	public BizPmOwnpayCnfg() {
		super();
	}

	public BizPmOwnpayCnfg(Integer id){
		super(id);
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	
	public String getIsOldNew() {
		return isOldNew;
	}

	public void setIsOldNew(String isOldNew) {
		this.isOldNew = isOldNew;
	}
	
	@Length(min=0, max=10, message="工程模式 -- '长度必须介于 0 和 10 之间")
	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}
	
	@Length(min=0, max=100, message="支配项名称 -- '长度必须介于 0 和 100 之间")
	public String getOwnpayName() {
		return ownpayName;
	}

	public void setOwnpayName(String ownpayName) {
		this.ownpayName = ownpayName;
	}
	
	@Length(min=0, max=10, message="单位 -- '长度必须介于 0 和 10 之间")
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	@Length(min=0, max=500, message="备注 -- '长度必须介于 0 和 500 之间")
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public String getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(String isEnabled) {
		this.isEnabled = isEnabled;
	}
	
}