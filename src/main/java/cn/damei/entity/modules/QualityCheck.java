package cn.damei.entity.modules;

import java.io.Serializable;
import java.util.Date;


public class QualityCheck implements Serializable{

    private String qcBillCode;

    private String qcCheckNodeName;

    private String itemManager;

    private Date createDate;

    private String realName;

    private Date checkDatetime;

    private Date acceptCheckDatetime;

    private Date actualStartDate;

    private String projectMode;

    private String name;

    private String label;

    public String getQcBillCode() {
        return qcBillCode;
    }

    public void setQcBillCode(String qcBillCode) {
        this.qcBillCode = qcBillCode;
    }

    public String getQcCheckNodeName() {
        return qcCheckNodeName;
    }

    public void setQcCheckNodeName(String qcCheckNodeName) {
        this.qcCheckNodeName = qcCheckNodeName;
    }

    public String getItemManager() {
        return itemManager;
    }

    public void setItemManager(String itemManager) {
        this.itemManager = itemManager;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Date getAcceptCheckDatetime() {
        return acceptCheckDatetime;
    }

    public void setAcceptCheckDatetime(Date acceptCheckDatetime) {
        this.acceptCheckDatetime = acceptCheckDatetime;
    }

    public Date getActualStartDate() {
        return actualStartDate;
    }

    public void setActualStartDate(Date actualStartDate) {
        this.actualStartDate = actualStartDate;
    }

    public String getProjectMode() {
        return projectMode;
    }

    public void setProjectMode(String projectMode) {
        this.projectMode = projectMode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Date getCheckDatetime() {
        return checkDatetime;
    }

    public void setCheckDatetime(Date checkDatetime) {
        this.checkDatetime = checkDatetime;
    }
}
