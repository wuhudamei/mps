/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;


import cn.damei.common.persistence.DataEntity2;

/**
 * 主材安装供应商列表Entity
 * 
 * @author haven
 * @version 2016-09-05
 */
public class EnginInstallSupplier extends DataEntity2<EnginInstallSupplier> {

    private static final long serialVersionUID = 1L;
    
    private Integer supplierId; // 供应商id
    private String supplierName; // 供应商 名称
    private String supplierContactsPhone;	//供应商联系电话
	private String supplierContacts;	//供应商联系人
	
    public EnginInstallSupplier() {
        super();
    }

    public EnginInstallSupplier(Integer id) {
        super(id);
    }

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplierContactsPhone() {
		return supplierContactsPhone;
	}

	public void setSupplierContactsPhone(String supplierContactsPhone) {
		this.supplierContactsPhone = supplierContactsPhone;
	}

	public String getSupplierContacts() {
		return supplierContacts;
	}

	public void setSupplierContacts(String supplierContacts) {
		this.supplierContacts = supplierContacts;
	}
    
    


   
}