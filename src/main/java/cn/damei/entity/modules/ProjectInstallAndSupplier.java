
package cn.damei.entity.modules;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity;


public class ProjectInstallAndSupplier extends DataEntity<ProjectInstallAndSupplier> {

	private static final long serialVersionUID = 1L;
	private String projectInstallItemId;
	private String projectInstallItemName;
	private String InstallItemName;
	private String installItemName1;
	private String supplierId;
	private String supplierName;
	private String storeid;
	private String projectMode;
	private String isOn;
	private List<BizSupplier> bizSupplierList = new ArrayList<BizSupplier>();

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