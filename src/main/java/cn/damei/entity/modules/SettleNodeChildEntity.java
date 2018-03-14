package cn.damei.entity.modules;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

import java.io.Serializable;

/**
 * Created by joseph on 2017/4/17.
 */
@JsonIgnoreType
public class SettleNodeChildEntity  implements Serializable{
	private static final long serialVersionUID = 1L;
    private BizNormalPmSettleNode  settleNode;

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

	public BizNormalPmSettleNode getSettleNode() {
        return settleNode;
    }

    public void setSettleNode(BizNormalPmSettleNode settleNode) {
        this.settleNode = settleNode;
    }

   

    public Integer getSettleIndex() {
        return settleIndex;
    }

    public void setSettleIndex(Integer settleIndex) {
        this.settleIndex = settleIndex;
    }

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

    public String getSettleType() {
        return settleType;
    }

    public void setSettleType(String settleType) {
        this.settleType = settleType;
    }

    public String getSettleRule() {
        return settleRule;
    }

    public void setSettleRule(String settleRule) {
        this.settleRule = settleRule;
    }

    public String getReceiveMoneyType() {
        return receiveMoneyType;
    }

    public void setReceiveMoneyType(String receiveMoneyType) {
        this.receiveMoneyType = receiveMoneyType;
    }

    public String getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(String isRequired) {
        this.isRequired = isRequired;
    }

    public String getCreateMan() {
        return createMan;
    }

    public void setCreateMan(String createMan) {
        this.createMan = createMan;
    }

   

    public String getQcCheckNodeName() {
        return qcCheckNodeName;
    }

    public void setQcCheckNodeName(String qcCheckNodeName) {
        this.qcCheckNodeName = qcCheckNodeName;
    }

}
