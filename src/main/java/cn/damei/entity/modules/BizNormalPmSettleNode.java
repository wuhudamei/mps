/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity;

import java.util.List;

/**
 * 结算节点配置Entity
 * @author 梅浩
 * @version 2017-04-15
 */
public class BizNormalPmSettleNode extends DataEntity<BizNormalPmSettleNode> {
	
	private static final long serialVersionUID = 1L;
	private Integer storeSettleNodeCount;
	private Integer storeId;		// 门店
	private Integer settleIndex;		// 结算顺序
	private String settleNodeName;		// 节点名称
	private Integer qcCheckNodeId;		// 验收节点id
	private String  qcCheckNodeName; //验收节点名称
	private String settleType;		// 结算类型
	private String settleRule;		// 具体结算占比
	private String receiveMoneyType;		// 收款类型
	private String isRequired;		// 是否必选
	private String createMan;//创建人名称	
	private String projectMode;
	private String settleStage;//结算阶段
	private String settlePrice;//结算单价
	
	
	
	public String getProjectMode() {
		return projectMode;
	}
	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}
	public String getSettleStage() {
		return settleStage;
	}
	public void setSettleStage(String settleStage) {
		this.settleStage = settleStage;
	}
	public String getSettlePrice() {
		return settlePrice;
	}
	public void setSettlePrice(String settlePrice) {
		this.settlePrice = settlePrice;
	}

	public String getQcCheckNodeName() {
		return qcCheckNodeName;
	}
	public void setQcCheckNodeName(String qcCheckNodeName) {
		this.qcCheckNodeName = qcCheckNodeName;
	}
	

	public Integer getStoreSettleNodeCount() {
		return storeSettleNodeCount;
	}

	public void setStoreSettleNodeCount(Integer storeSettleNodeCount) {
		this.storeSettleNodeCount = storeSettleNodeCount;
	}
	

	public List<SettleNodeChildEntity> getChildEntity() {
		return childEntity;
	}

	public void setChildEntity(List<SettleNodeChildEntity> childEntity) {
		this.childEntity = childEntity;
	}
	

	private List<SettleNodeChildEntity> childEntity;


	public String getCreateMan() {
		return createMan;
	}

	public void setCreateMan(String createMan) {
		this.createMan = createMan;
	}

	public BizNormalPmSettleNode() {
		super();
	}

	public BizNormalPmSettleNode(String id){
		super(id);
	}

	@NotNull(message="门店不能为空")
	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	
	@NotNull(message="结算顺序不能为空")
	public Integer getSettleIndex() {
		return settleIndex;
	}

	public void setSettleIndex(Integer settleIndex) {
		this.settleIndex = settleIndex;
	}
	
	@Length(min=0, max=100, message="节点名称长度必须介于 0 和 100 之间")
	public String getSettleNodeName() {
		return settleNodeName;
	}

	public void setSettleNodeName(String settleNodeName) {
		this.settleNodeName = settleNodeName;
	}
	
	public Integer getQcCheckNodeId() {
		return qcCheckNodeId;
	}

	public void setQcCheckNodeId(Integer qcCheckNodeId) {
		this.qcCheckNodeId = qcCheckNodeId;
	}
	
	@Length(min=0, max=10, message="结算类型长度必须介于 0 和 10 之间")
	public String getSettleType() {
		return settleType;
	}

	public void setSettleType(String settleType) {
		this.settleType = settleType;
	}
	
	@Length(min=0, max=100, message="具体结算占比长度必须介于 0 和 100 之间")
	public String getSettleRule() {
		return settleRule;
	}

	public void setSettleRule(String settleRule) {
		this.settleRule = settleRule;
	}
	
	@Length(min=0, max=10, message="收款类型长度必须介于 0 和 10 之间")
	public String getReceiveMoneyType() {
		return receiveMoneyType;
	}

	public void setReceiveMoneyType(String receiveMoneyType) {
		this.receiveMoneyType = receiveMoneyType;
	}
	
	@Length(min=1, max=1, message="是否必选长度必须介于 1 和 1 之间")
	public String getIsRequired() {
		return isRequired;
	}

	public void setIsRequired(String isRequired) {
		this.isRequired = isRequired;
	}
	
}