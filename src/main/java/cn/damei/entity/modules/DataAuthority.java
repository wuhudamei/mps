package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity;

public class DataAuthority extends DataEntity<DataAuthority>{
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String transactionData;
	private String dataAuthOptionId;
	private String roleId;
	
	
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getDataAuthOptionId() {
		return dataAuthOptionId;
	}

	public void setDataAuthOptionId(String dataAuthOptionId) {
		this.dataAuthOptionId = dataAuthOptionId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTransactionData() {
		return transactionData;
	}

	public void setTransactionData(String transactionData) {
		this.transactionData = transactionData;
	}

	public DataAuthority() {
		super();
	}
	
	
	

}
