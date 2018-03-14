package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;

import java.util.Date;


public class ExportEvalInfo extends DataEntity2<ExportEvalInfo> {
    private static final long serialVersionUID = 1L;

    private String storeId;

    private String storeName;

    private String projectMode;
    private String projectModeName;

    private Integer enginDepartId;

    private String enginDepartName;

    private Date gradeDate;

    private String groupRealName;

    private String customerMessage;

    private String customerName;

    private String packageName;

    private Integer evalRoleType;

    private String evalRoleTypeName;

    private Integer evalIndexId;

    private String indexName;

    private Double gradtotalScore;

    private Double evaltotalScore;

    private String evalTargetType;

    private Date startDate;

    private Date endDate;

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getProjectModeName() {
        return projectModeName;
    }

    public void setProjectModeName(String projectModeName) {
        this.projectModeName = projectModeName;
    }

    public String getEvalRoleTypeName() {
        return evalRoleTypeName;
    }

    public void setEvalRoleTypeName(String evalRoleTypeName) {
        this.evalRoleTypeName = evalRoleTypeName;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getProjectMode() {
        return projectMode;
    }

    public void setProjectMode(String projectMode) {
        this.projectMode = projectMode;
    }

    public Integer getEnginDepartId() {
        return enginDepartId;
    }

    public void setEnginDepartId(Integer enginDepartId) {
        this.enginDepartId = enginDepartId;
    }

    public String getEnginDepartName() {
        return enginDepartName;
    }

    public void setEnginDepartName(String enginDepartName) {
        this.enginDepartName = enginDepartName;
    }

    public Date getGradeDate() {
        return gradeDate;
    }

    public void setGradeDate(Date gradeDate) {
        this.gradeDate = gradeDate;
    }

    public String getGroupRealName() {
        return groupRealName;
    }

    public void setGroupRealName(String groupRealName) {
        this.groupRealName = groupRealName;
    }

    public String getCustomerMessage() {
        return customerMessage;
    }

    public void setCustomerMessage(String customerMessage) {
        this.customerMessage = customerMessage;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Integer getEvalRoleType() {
        return evalRoleType;
    }

    public void setEvalRoleType(Integer evalRoleType) {
        this.evalRoleType = evalRoleType;
    }

    public Integer getEvalIndexId() {
        return evalIndexId;
    }

    public void setEvalIndexId(Integer evalIndexId) {
        this.evalIndexId = evalIndexId;
    }

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public Double getGradtotalScore() {
        return gradtotalScore;
    }

    public void setGradtotalScore(Double gradtotalScore) {
        this.gradtotalScore = gradtotalScore;
    }

    public Double getEvaltotalScore() {
        return evaltotalScore;
    }

    public void setEvaltotalScore(Double evaltotalScore) {
        this.evaltotalScore = evaltotalScore;
    }

    public String getEvalTargetType() {
        return evalTargetType;
    }

    public void setEvalTargetType(String evalTargetType) {
        this.evalTargetType = evalTargetType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
