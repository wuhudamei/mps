package cn.damei.entity.modules;

import java.io.Serializable;
import java.util.Date;

/**
 * 主材安装台账
 * Created by 巢帅 on 2017/9/14.
 */
public class PrincipalInstall implements Serializable{
    /**安装项名称**/
    private String installItemName;
    /**安装项状态**/
    private String status;
    /**期望进场日期**/
    private Date planIntoDate;
    /**实际进场日期**/
    private Date realIntoDate;
    /**实际完成日期**/
    private Date realCompleteDate;
    /**实际验收日期**/
    private Date realAcceptDate;
    /**是否延期**/
    private String isCompleteDelay;
    /**延期天数**/
    private String completeDelayDays;
    /**安装项状态**/
    private String label;
    /**期望进场时间**/
    private Date applyIntoDate;
    /**项目经理申请时间**/
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
