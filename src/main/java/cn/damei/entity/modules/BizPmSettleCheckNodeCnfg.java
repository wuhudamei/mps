
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity2;


public class BizPmSettleCheckNodeCnfg extends DataEntity2<BizPmSettleCheckNodeCnfg> {
	
	private static final long serialVersionUID = 1L;
	private Integer storeId;
	private String projectMode;
	private String settleRole;
	private String settleNode;
	private Integer qcCheckNodeId;
	private String qcCheckNodeName;
	private String isEnabled;
	
	public BizPmSettleCheckNodeCnfg() {
		super();
	}

	public BizPmSettleCheckNodeCnfg(Integer id){
		super(id);
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	
	@Length(min=0, max=10, message="工程模式长度必须介于 0 和 10 之间")
	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}
	
	public String getSettleNode() {
		return settleNode;
	}

	public void setSettleNode(String settleNode) {
		this.settleNode = settleNode;
	}
	
	public Integer getQcCheckNodeId() {
		return qcCheckNodeId;
	}

	public void setQcCheckNodeId(Integer qcCheckNodeId) {
		this.qcCheckNodeId = qcCheckNodeId;
	}
	
	public String getQcCheckNodeName() {
		return qcCheckNodeName;
	}

	public void setQcCheckNodeName(String qcCheckNodeName) {
		this.qcCheckNodeName = qcCheckNodeName;
	}

	public String getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(String isEnabled) {
		this.isEnabled = isEnabled;
	}

	public String getSettleRole() {
		return settleRole;
	}

	public void setSettleRole(String settleRole) {
		this.settleRole = settleRole;
	}
	
	
}