package cn.damei.entity.modules;

import java.io.Serializable;
import java.util.Date;

/**
 * <dl>
 * <dd>描述:工程台账延期单台账实体</dd>
 * <dd>公司: 智装</dd>
 * <dd>创建时间：2017/9/7</dd>
 * <dd>创建人：Chaos</dd>
 * </dl>
 */
public class DelaySheet implements Serializable{
    /**延期阶段**/
    private String delayBillStageStatus;
    /**延期类别**/
    private String delayTypeName;
    /**延期原因**/
    private String deferredInstruction;
    /**延期开始时间**/
    private Date delayBeginDatetime;
    /**延期结束时间**/
    private Date delayEndDatetime;
    /**延期天数**/
    private String delayDays;
    /**延期阶段值**/
    private String label;
    /**延期单状态**/
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
