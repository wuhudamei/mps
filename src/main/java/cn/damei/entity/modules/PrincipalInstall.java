package cn.damei.entity.modules;

import java.io.Serializable;
import java.util.Date;


public class PrincipalInstall implements Serializable{

    private String installItemName;

    private String status;

    private Date planIntoDate;

    private Date realIntoDate;

    private Date realCompleteDate;

    private Date realAcceptDate;

    private String isCompleteDelay;

    private String completeDelayDays;

    private String label;

    private Date applyIntoDate;

    private Date applyIntoCreateDatetime;

    public String getInstallItemName() {
        return installItemName;
    }

    public void setInstallItemName(String installItemName) {
        this.installItemName = installItemName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getPlanIntoDate() {
        return planIntoDate;
    }

    public void setPlanIntoDate(Date planIntoDate) {
        this.planIntoDate = planIntoDate;
    }

    public Date getRealIntoDate() {
        return realIntoDate;
    }

    public void setRealIntoDate(Date realIntoDate) {
        this.realIntoDate = realIntoDate;
    }

    public Date getRealCompleteDate() {
        return realCompleteDate;
    }

    public void setRealCompleteDate(Date realCompleteDate) {
        this.realCompleteDate = realCompleteDate;
    }

    public Date getRealAcceptDate() {
        return realAcceptDate;
    }

    public void setRealAcceptDate(Date realAcceptDate) {
        this.realAcceptDate = realAcceptDate;
    }

    public String getIsCompleteDelay() {
        return isCompleteDelay;
    }

    public void setIsCompleteDelay(String isCompleteDelay) {
        this.isCompleteDelay = isCompleteDelay;
    }

    public String getCompleteDelayDays() {
        return completeDelayDays;
    }

    public void setCompleteDelayDays(String completeDelayDays) {
        this.completeDelayDays = completeDelayDays;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Date getApplyIntoDate() {
        return applyIntoDate;
    }

    public void setApplyIntoDate(Date applyIntoDate) {
        this.applyIntoDate = applyIntoDate;
    }

    public Date getApplyIntoCreateDatetime() {
        return applyIntoCreateDatetime;
    }

    public void setApplyIntoCreateDatetime(Date applyIntoCreateDatetime) {
        this.applyIntoCreateDatetime = applyIntoCreateDatetime;
    }
}
