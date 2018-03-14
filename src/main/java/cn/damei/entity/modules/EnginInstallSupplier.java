
package cn.damei.entity.modules;


import cn.damei.common.persistence.DataEntity2;


public class EnginInstallSupplier extends DataEntity2<EnginInstallSupplier> {

    private static final long serialVersionUID = 1L;
    
    private Integer supplierId;
    private String supplierName;
    private String supplierContactsPhone;
	private String supplierContacts;
	
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