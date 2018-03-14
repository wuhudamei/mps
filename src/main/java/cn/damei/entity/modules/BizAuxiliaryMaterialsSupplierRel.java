/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.damei.common.persistence.DataEntity;

/**
 * 辅料对应供应商Entity
 * @author chy
 * @version 2016-09-09
 */
public class BizAuxiliaryMaterialsSupplierRel extends DataEntity<BizAuxiliaryMaterialsSupplierRel> {
	
	private static final long serialVersionUID = 1L;
	private String auxiliaryMaterialsId;		// 辅料Id
	private String supplierNo;		// 供应商编号
	private String supplierId;		// 供应商名称
	private String supplierCycle;		// 供货周期（天）
	private String supplierPrice;		// 供应商供货价（元）
	private String laborPrice;		// 工人结算价（元）
	private String wangZhenPrice;		// 网真结算价（元）
	private String version;		// 版本号
	private Date effectiveDate;		// 生效日期
	
	
	public String getWangZhenPrice() {
		return wangZhenPrice;
	}

	public void setWangZhenPrice(String wangZhenPrice) {
		this.wangZhenPrice = wangZhenPrice;
	}

	public BizAuxiliaryMaterialsSupplierRel() {
		super();
	}

	public BizAuxiliaryMaterialsSupplierRel(String id){
		super(id);
	}

	@Length(min=0, max=11, message="辅料Id长度必须介于 0 和 11 之间")
	public String getAuxiliaryMaterialsId() {
		return auxiliaryMaterialsId;
	}

	public void setAuxiliaryMaterialsId(String auxiliaryMaterialsId) {
		this.auxiliaryMaterialsId = auxiliaryMaterialsId;
	}
	
	@Length(min=0, max=18, message="供应商编号长度必须介于 0 和 18 之间")
	public String getSupplierNo() {
		return supplierNo;
	}

	public void setSupplierNo(String supplierNo) {
		this.supplierNo = supplierNo;
	}
	
	@Length(min=0, max=11, message="供应商名称长度必须介于 0 和 11 之间")
	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	
	@Length(min=0, max=3, message="供货周期（天）长度必须介于 0 和 3 之间")
	public String getSupplierCycle() {
		return supplierCycle;
	}

	public void setSupplierCycle(String supplierCycle) {
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
	
	@Length(min=0, max=4, message="版本号长度必须介于 0 和 4 之间")
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	
	public long getEffectiveDateMsec() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String format2 = format.format(date);
		String format3 = format.format(effectiveDate);
		if(format2.equals(format3)){
			long count = effectiveDate.getTime()+date.getTime();
			return count;
		}
		return effectiveDate.getTime();
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	
}