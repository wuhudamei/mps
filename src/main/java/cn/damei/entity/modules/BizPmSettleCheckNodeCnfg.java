/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity2;

/**
 * 项目经理结算关联约检节点设置Entity
 * @author wyb
 * @version 2016-12-26
 */
public class BizPmSettleCheckNodeCnfg extends DataEntity2<BizPmSettleCheckNodeCnfg> {
	
	private static final long serialVersionUID = 1L;
	private Integer storeId;		// 门店
	private String projectMode;		// 工程模式
	private String settleRole;		//所属结算人员
	private String settleNode;		// 结算节点.中期结算；2.竣工结算
	private Integer qcCheckNodeId;		// 约检节点
	private String qcCheckNodeName;		//约检节点名称
	private String isEnabled;		// 是否启用.启用；2.停用
	
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