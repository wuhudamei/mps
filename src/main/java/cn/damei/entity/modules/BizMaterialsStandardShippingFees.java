
package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;
import org.hibernate.validator.constraints.Length;


public class BizMaterialsStandardShippingFees extends DataEntity2<BizMaterialsStandardShippingFees> {
	
	private Integer storeId;
	private Integer bizMaterialsStandardType;
	private Double shippingFees;
	private Integer status;
	private Integer createId;
	private String createName;

	private Boolean flag;

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public BizMaterialsStandardShippingFees() {
		super();
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	
	public Integer getBizMaterialsStandardType() {
		return bizMaterialsStandardType;
	}

	public void setBizMaterialsStandardType(Integer bizMaterialsStandardType) {
		this.bizMaterialsStandardType = bizMaterialsStandardType;
	}
	
	public Double getShippingFees() {
		return shippingFees;
	}

	public void setShippingFees(Double shippingFees) {
		this.shippingFees = shippingFees;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getCreateId() {
		return createId;
	}

	public void setCreateId(Integer createId) {
		this.createId = createId;
	}
	
	@Length(min=0, max=255, message="create_name长度必须介于 0 和 255 之间")
	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}
	
}