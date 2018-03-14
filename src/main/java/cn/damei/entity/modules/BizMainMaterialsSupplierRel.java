/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import cn.damei.common.persistence.DataEntity2;

/**
 * 主材供应商管理Entity
 * @author qww
 * @version 2016-10-11
 */
public class BizMainMaterialsSupplierRel extends DataEntity2<BizMainMaterialsSupplierRel> {
	
	private static final long serialVersionUID = 1L;
	private Integer mainMaterialsId;		// 主料id -- '
	private Integer supplierId;		// 供应商Id -- '
	private String supplierNo;		// 供应商编号 -- '
	private Integer supplierCycle;		// 供货周期（天） -- '
	private String supplierPrice;		// 供应商供货价 -- '
	private String laborPrice;		// 工人结算价 -- '
	private Integer version;		// 版本号 -- '
	private Date effectiveDate;		// 生效日期 -- '
	
	public BizMainMaterialsSupplierRel() {
		super();
	}

	public BizMainMaterialsSupplierRel(Integer id){
		super(id);
	}

	public Integer getMainMaterialsId() {
		return mainMaterialsId;
	}

	public void setMainMaterialsId(Integer mainMaterialsId) {
		this.mainMaterialsId = mainMaterialsId;
	}
	
	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}
	
	@Length(min=0, max=18, message="供应商编号 -- '长度必须介于 0 和 18 之间")
	public String getSupplierNo() {
		return supplierNo;
	}

	public void setSupplierNo(String supplierNo) {
		this.supplierNo = supplierNo;
	}
	
	public Integer getSupplierCycle() {
		return supplierCycle;
	}

	public void setSupplierCycle(Integer supplierCycle) {
		this.supplierCycle = supplierCycle;
	}
	
	public String getSupplierPrice() {
		return supplierPrice;
	}

	public void setSupplierPrice(String supplierPrice) {
		this.supplierPrice = supplierPrice;
	}
	
	public String getLaborPrice() {
		return laborPrice;
	}

	public void setLaborPrice(String laborPrice) {
		this.laborPrice = laborPrice;
	}
	
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	
}