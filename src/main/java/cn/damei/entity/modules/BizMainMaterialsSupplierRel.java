
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import cn.damei.common.persistence.DataEntity2;


public class BizMainMaterialsSupplierRel extends DataEntity2<BizMainMaterialsSupplierRel> {
	
	private static final long serialVersionUID = 1L;
	private Integer mainMaterialsId;
	private Integer supplierId;
	private String supplierNo;
	private Integer supplierCycle;
	private String supplierPrice;
	private String laborPrice;
	private Integer version;
	private Date effectiveDate;
	
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