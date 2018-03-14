/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity;

/**
 * 主材安装供应商设置Entity
 * 
 * @author ztw
 * @version 2017-07-14
 */
public class ProjectInstallAndSupplier extends DataEntity<ProjectInstallAndSupplier> {

	private static final long serialVersionUID = 1L;
	private String projectInstallItemId; // 安装项ID
	private String projectInstallItemName; // 供应商业务分类
	private String InstallItemName; // 安装项名称
	private String installItemName1; // 安装项名称1
	private String supplierId; // 供应商ID
	private String supplierName; // 供应商名称
	private String storeid; // 门店
	private String projectMode;// 工程模式
	private String isOn;// 是否啟用
	private List<BizSupplier> bizSupplierList = new ArrayList<BizSupplier>();// 供應商集合

	public ProjectInstallAndSupplier() {
		super();
	}

	public ProjectInstallAndSupplier(String id) {
		super(id);
	}

	public String getProjectInstallItemName() {
		return projectInstallItemName;
	}

	public String getInstallItemName1() {
		return installItemName1;
	}

	public List<BizSupplier> getBizSupplierList() {
		return bizSupplierList;
	}

	public void setBizSupplierList(List<BizSupplier> bizSupplierList) {
		this.bizSupplierList = bizSupplierList;
	}

	public void setInstallItemName1(String installItemName1) {
		this.installItemName1 = installItemName1;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public String getIsOn() {
		return isOn;
	}

	public void setIsOn(String isOn) {
		this.isOn = isOn;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public void setProjectInstallItemName(String projectInstallItemName) {
		this.projectInstallItemName = projectInstallItemName;
	}

	public String getInstallItemName() {
		return InstallItemName;
	}

	public void setInstallItemName(String installItemName) {
		InstallItemName = installItemName;
	}

	public String getStoreid() {
		return storeid;
	}

	public void setStoreid(String storeid) {
		this.storeid = storeid;
	}

	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}

	@Length(min = 0, max = 11, message = "安装项ID长度必须介于 0 和 11 之间")
	public String getProjectInstallItemId() {
		return projectInstallItemId;
	}

	public void setProjectInstallItemId(String projectInstallItemId) {
		this.projectInstallItemId = projectInstallItemId;
	}

	@Length(min = 0, max = 11, message = "供应商ID长度必须介于 0 和 11 之间")
	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

}