package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;

import java.util.Date;

/**
 * 评价分数导出
 * Created by hyh on 2017/11/29.
 */
public class ExportEvalInfo extends DataEntity2<ExportEvalInfo> {
    private static final long serialVersionUID = 1L;
    // 门店id
    private String storeId;

    private String storeName;
    //工程模式
    private String projectMode;
    private String projectModeName;
    //区域
    private Integer enginDepartId;
    //区域名称
    private String enginDepartName;
    //评分时间
    private Date gradeDate;
    //工人组长
    private String groupRealName;
    //客户信息
    private String customerMessage;
    //客户姓名
    private String customerName;
    //任务包名称
    private String packageName;
    //评价角色类型 1：项目经理  2：质检   3:客户
    private Integer evalRoleType;

    private String evalRoleTypeName;
    // 评价指标id
    private Integer evalIndexId;
    //评价指标内容
    private String indexName;
    //评价分数
    private Double gradtotalScore;
    //评价总分
    private Double evaltotalScore;
    // 评价对象  1:工人   2:项目经理
    private String evalTargetType;
    //开始时间
    private Date startDate;
    //结束时间
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
