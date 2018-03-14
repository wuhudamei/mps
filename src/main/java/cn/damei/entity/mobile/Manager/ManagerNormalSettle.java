package cn.damei.entity.mobile.Manager;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

import java.util.Date;

/**
 * Created by joseph on 2017/4/14.
 *  传统经理结算-结算单对象  (一个订单对应该订单对应门店下的结算节点和结算单数据)
 */
@JsonIgnoreType
public class ManagerNormalSettle {



    private Integer applyEmpId;
    private Date applyTime;
    private Date statusDateTime;//状态生成时间
    private Integer settleId;//结算单id
    private Integer settleNodeId;//结算节点外键
    private ManagerTraditionSettleEntity orderEntity;//订单信息类
    private String receiveMoneyType;//二期款1 尾款2
    private Integer settleIndex;//结算节点顺序
    private String settleNodeName;//结算节点名称
    private String qcCheckNodeId;//结算节点对应验收节点id
    private String isRequired;//是否必选  1:必选 0:非必选
    private String isCheckNodeDone;//节点是否验收  1:验收 0:没有验收
    private String isMoneyReceive;//对应的款项是否已经收到 (二期款 尾款是否已确认收款)
    private String settleStatus;//目前的结算单状态id
    private String settleStatusName;//目前的结算单状态名称
    private Date createDate;//创建时间
    private String createBy;//创建人
    private String checkReply;//检查人回复
    private Date checkTime;//检查时间
    private String checkManName;//检查人名称
    private Double settleAmount;//结算金额
    private String picUrl;
    private Integer nodeIndex;

    public Integer getNodeIndex() {
        return nodeIndex;
    }

    public void setNodeIndex(Integer nodeIndex) {
        this.nodeIndex = nodeIndex;
    }

    public String getCheckReply() {
        return checkReply;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public void setCheckReply(String checkReply) {
        this.checkReply = checkReply;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public String getCheckManName() {
        return checkManName;
    }

    public void setCheckManName(String checkManName) {
        this.checkManName = checkManName;
    }

    public Double getSettleAmount() {
        return settleAmount;
    }

    public void setSettleAmount(Double settleAmount) {
        this.settleAmount = settleAmount;
    }

    public Integer getApplyEmpId() {
        return applyEmpId;
    }


    public Date getStatusDateTime() {
        return statusDateTime;
    }


    public void setStatusDateTime(Date statusDateTime) {
        this.statusDateTime = statusDateTime;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
    public void setApplyEmpId(Integer applyEmpId) {
        this.applyEmpId = applyEmpId;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }
    public Integer getSettleId() {
        return settleId;
    }

    public void setSettleId(Integer settleId) {
        this.settleId = settleId;
    }

    public Integer getSettleNodeId() {
        return settleNodeId;
    }

    public void setSettleNodeId(Integer settleNodeId) {
        this.settleNodeId = settleNodeId;
    }

    public ManagerTraditionSettleEntity getOrderEntity() {
        return orderEntity;
    }

    public void setOrderEntity(ManagerTraditionSettleEntity orderEntity) {
        this.orderEntity = orderEntity;
    }

    public String getReceiveMoneyType() {
        return receiveMoneyType;
    }

    public void setReceiveMoneyType(String receiveMoneyType) {
        this.receiveMoneyType = receiveMoneyType;
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

    public String getQcCheckNodeId() {
        return qcCheckNodeId;
    }

    public void setQcCheckNodeId(String qcCheckNodeId) {
        this.qcCheckNodeId = qcCheckNodeId;
    }

    public String getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(String isRequired) {
        this.isRequired = isRequired;
    }

    public String getIsCheckNodeDone() {
        return isCheckNodeDone;
    }

    public void setIsCheckNodeDone(String isCheckNodeDone) {
        this.isCheckNodeDone = isCheckNodeDone;
    }

    public String getIsMoneyReceive() {
        return isMoneyReceive;
    }

    public void setIsMoneyReceive(String isMoneyReceive) {
        this.isMoneyReceive = isMoneyReceive;
    }

    public String getSettleStatus() {
        return settleStatus;
    }

    public void setSettleStatus(String settleStatus) {
        this.settleStatus = settleStatus;
    }

    public String getSettleStatusName() {
        return settleStatusName;
    }

    public void setSettleStatusName(String settleStatusName) {
        this.settleStatusName = settleStatusName;
    }
}
