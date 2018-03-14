package cn.damei.entity.modules;

import java.io.Serializable;
import java.util.Date;

/**
 * 质检台账
 * Created by 巢帅 on 2017/9/13.
 */
public class QualityCheck implements Serializable{
    /**质检报告编号**/
    private String qcBillCode;
    /**约检节点**/
    private String qcCheckNodeName;
    /**项目经理**/
    private String itemManager;
    /**约检时间**/
    private Date createDate;
    /**质检员**/
    private String realName;
    /**质检时间**/
    private Date checkDatetime;
    /**验收通过时间**/
    private Date acceptCheckDatetime;
    /**实际开工时间**/
    private Date actualStartDate;
    /**工程模式**/
    private String projectMode;
    /**工程区域**/
    private String name;
    /**工程模式**/
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
