package cn.damei.entity.modules;

import java.io.Serializable;
import java.util.Date;


public class DelaySheet implements Serializable{

    private String delayBillStageStatus;

    private String delayTypeName;

    private String deferredInstruction;

    private Date delayBeginDatetime;

    private Date delayEndDatetime;

    private String delayDays;

    private String label;

    private String status;

    public String getDelayBillStageStatus() {
        return delayBillStageStatus;
    }

    public void setDelayBillStageStatus(String delayBillStageStatus) {
        this.delayBillStageStatus = delayBillStageStatus;
    }

    public String getDelayTypeName() {
        return delayTypeName;
    }

    public void setDelayTypeName(String delayTypeName) {
        this.delayTypeName = delayTypeName;
    }

    public String getDeferredInstruction() {
        return deferredInstruction;
    }

    public void setDeferredInstruction(String deferredInstruction) {
        this.deferredInstruction = deferredInstruction;
    }

    public Date getDelayBeginDatetime() {
        return delayBeginDatetime;
    }

    public void setDelayBeginDatetime(Date delayBeginDatetime) {
        this.delayBeginDatetime = delayBeginDatetime;
    }

    public Date getDelayEndDatetime() {
        return delayEndDatetime;
    }

    public void setDelayEndDatetime(Date delayEndDatetime) {
        this.delayEndDatetime = delayEndDatetime;
    }

    public String getDelayDays() {
        return delayDays;
    }

    public void setDelayDays(String delayDays) {
        this.delayDays = delayDays;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
