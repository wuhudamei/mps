package cn.damei.entity.mobile.Inspector;

import java.util.Objects;


public class BasePqcEntity extends PqcOrderEntity {

    private Integer qcBillId;
    private Integer qcNodeId;
    private String qcNodeName;
    private String qcType;
    private String isRecheck;
    private String qcStatus;
    private String planCheckDate;
    private Integer checkNodeIndex;

    public Integer getCheckNodeIndex() {
        return checkNodeIndex;
    }

    public void setCheckNodeIndex(Integer checkNodeIndex) {
        this.checkNodeIndex = checkNodeIndex;
    }

    public String getQcStatus() {
        return qcStatus;
    }

    public void setQcStatus(String qcStatus) {
        this.qcStatus = qcStatus;
    }

    public String getPlanCheckDate() {
        return planCheckDate;
    }

    public void setPlanCheckDate(String planCheckDate) {
        this.planCheckDate = planCheckDate;
    }

    public Integer getQcBillId() {
        return qcBillId;
    }

    public void setQcBillId(Integer qcBillId) {
        this.qcBillId = qcBillId;
    }

    public Integer getQcNodeId() {
        return qcNodeId;
    }

    public void setQcNodeId(Integer qcNodeId) {
        this.qcNodeId = qcNodeId;
    }

    public String getQcNodeName() {
        return qcNodeName;
    }

    public void setQcNodeName(String qcNodeName) {
        this.qcNodeName = qcNodeName;
    }

    public String getQcType() {
        return qcType;
    }

    public void setQcType(String qcType) {
        this.qcType = qcType;
    }

    public String getIsRecheck() {
        return isRecheck;
    }

    public void setIsRecheck(String isRecheck) {
        this.isRecheck = isRecheck;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BasePqcEntity that = (BasePqcEntity) o;
        return Objects.equals(qcBillId, that.qcBillId) &&
                Objects.equals(qcNodeId, that.qcNodeId) &&
                Objects.equals(qcNodeName, that.qcNodeName) &&
                Objects.equals(qcType, that.qcType) &&
                Objects.equals(isRecheck, that.isRecheck) &&
                Objects.equals(qcStatus, that.qcStatus) &&
                Objects.equals(planCheckDate, that.planCheckDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), qcBillId, qcNodeId, qcNodeName, qcType, isRecheck, qcStatus, planCheckDate);
    }
}
