package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity;

public class SysRoleStoreRel extends DataEntity<Role>{
	
	

	private static final long serialVersionUID = 1L;
	private String id;
	private String roleId;
	private String storeId;
	private String remarks;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public SysRoleStoreRel() {
		super();
	}
	
	

}
