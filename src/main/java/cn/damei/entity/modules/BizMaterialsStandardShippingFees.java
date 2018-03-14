/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;
import org.hibernate.validator.constraints.Length;

/**
 * 筒灯灯带标化辅料配送费Entity
 * @author Ryze
 * @version 2017-10-25
 */
public class BizMaterialsStandardShippingFees extends DataEntity2<BizMaterialsStandardShippingFees> {
	
	private Integer storeId;		// store_id
	private Integer bizMaterialsStandardType;		// biz_materials_standard_type
	private Double shippingFees;		// shipping_fees
	private Integer status;		// 0停用 1启用
	private Integer createId;		// create_id
	private String createName;		// create_name
	//是否可以启用
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