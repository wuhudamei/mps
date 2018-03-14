package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;
import cn.damei.entity.modules.BizNodePlan;

import java.util.Date;


public class BizNodePlanExtend extends DataEntity2<BizNodePlan> {

    private String nodeName;
    private Date planDoneDate;
    private Date realDoneDate;

    private Date disclosure;

    private Date confirmation;
    private String designerName;
    private String serviceName;
    private String itemManager;
    private Date planCheckTime;

    public Date getPlanCheckTime() {
        return planCheckTime;
    }

    public void setPlanCheckTime(Date planCheckTime) {
        this.planCheckTime = planCheckTime;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public Date getPlanDoneDate() {
        return planDoneDate;
    }

    public void setPlanDoneDate(Date planDoneDate) {
        this.planDoneDate = planDoneDate;
    }

    public Date getRealDoneDate() {
        return realDoneDate;
    }

    public void setRealDoneDate(Date realDoneDate) {
        this.realDoneDate = realDoneDate;
    }

    public String getDesignerName() {
        return designerName;
    }

    public void setDesignerName(String designerName) {
        this.designerName = designerName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getItemManager() {
        return itemManager;
    }

    public void setItemManager(String itemManager) {
        this.itemManager = itemManager;
    }

    public Date getDisclosure() {
        return disclosure;
    }

    public void setDisclosure(Date disclosure) {
        this.disclosure = disclosure;
    }

    public Date getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(Date confirmation) {
        this.confirmation = confirmation;
    }
}
