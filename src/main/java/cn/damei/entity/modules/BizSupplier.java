/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity;

/**
 * 供应商管理Entity
 * 
 * @author lc
 * @version 2016-09-08
 */
public class BizSupplier extends DataEntity<BizSupplier> {

	private static final long serialVersionUID = 1L;
	private String installitemtype; // 安装项类别
	private String projectInstallItemName; // 供应商业务分类
	private String supplierNo; // 供应商编号
	private String supplierName; // 供应商名称
	private String contacts; // 联系人
	private String contactsPhone; // 联系电话
	private String email; // 邮箱
	private String status; // 状态

	public BizSupplier() {
		super();
	}

	public BizSupplier(String id) {
		super(id);
	}

	public String getInstallitemtype() {
		return installitemtype;
	}

	public void setInstallitemtype(String installitemtype) {
		this.installitemtype = installitemtype;
	}

	@Length(min = 1, max = 18, message = "供应商编号长度必须介于 1 和 18 之间")
	public String getSupplierNo() {
		return supplierNo;
	}

	public void setSupplierNo(String supplierNo) {
		this.supplierNo = supplierNo;
	}

	@Length(min = 1, max = 255, message = "供应商名称长度必须介于 1 和 255 之间")
	public String getSupplierName() {
		return supplierName;
	}

	public String getProjectInstallItemName() {
		return projectInstallItemName;
	}

	public void setProjectInstallItemName(String projectInstallItemName) {
		this.projectInstallItemName = projectInstallItemName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	@Length(min = 0, max = 100, message = "联系人长度必须介于 0 和 100 之间")
	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	@Length(min = 0, max = 20, message = "联系电话长度必须介于 0 和 20 之间")
	public String getContactsPhone() {
		return contactsPhone;
	}

	public void setContactsPhone(String contactsPhone) {
		this.contactsPhone = contactsPhone;
	}

	@Length(min = 0, max = 100, message = "邮箱长度必须介于 0 和 100 之间")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Length(min = 1, max = 1, message = "状态长度必须介于 1 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}