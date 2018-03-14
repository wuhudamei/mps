package cn.damei.entity.mobile.Manager;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

import java.util.Date;


@JsonIgnoreType
public class ManagerNormalSettle {



    private Integer applyEmpId;
    private Date applyTime;
    private Date statusDateTime;
    private Integer settleId;
    private Integer settleNodeId;
    private ManagerTraditionSettleEntity orderEntity;
    private String receiveMoneyType;
    private Integer settleIndex;
    private String settleNodeName;
    private String qcCheckNodeId;
    private String isRequired;
    private String isCheckNodeDone;
    private String isMoneyReceive;
    private String settleStatus;
    private String settleStatusName;
    private Date createDate;
    private String createBy;
    private String checkReply;
    private Date checkTime;
    private String checkManName;
    private Double settleAmount;
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
