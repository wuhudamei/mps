package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;

import java.util.Date;


public class ExportBudgetCost extends DataEntity2<ExportBudgetCost> {
    private static final long serialVersionUID = 1L;
    private String storeId;

    private String projectMode;

    private Date startDate;

    private Date endDate;


    private String orderNumber;


    private String customerName;


    private Date getOrderDatetime;


    private Date actualEndDate;


    private Double actualMaterialsAmount;


    private Double actualConstructionAmount;


    private Double actualPmAmount;


    private Double paidMaterialsAmount;


    private Double paidConstructionAmount;


    private Double paidPmAmount;

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

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getGetOrderDatetime() {
        return getOrderDatetime;
    }

    public void setGetOrderDatetime(Date getOrderDatetime) {
        this.getOrderDatetime = getOrderDatetime;
    }

    public Date getActualEndDate() {
        return actualEndDate;
    }

    public void setActualEndDate(Date actualEndDate) {
        this.actualEndDate = actualEndDate;
    }

    public Double getActualMaterialsAmount() {
        return actualMaterialsAmount;
    }

    public void setActualMaterialsAmount(Double actualMaterialsAmount) {
        this.actualMaterialsAmount = actualMaterialsAmount;
    }

    public Double getActualConstructionAmount() {
        return actualConstructionAmount;
    }

    public void setActualConstructionAmount(Double actualConstructionAmount) {
        this.actualConstructionAmount = actualConstructionAmount;
    }

    public Double getActualPmAmount() {
        return actualPmAmount;
    }

    public void setActualPmAmount(Double actualPmAmount) {
        this.actualPmAmount = actualPmAmount;
    }

    public Double getPaidMaterialsAmount() {
        return paidMaterialsAmount;
    }

    public void setPaidMaterialsAmount(Double paidMaterialsAmount) {
        this.paidMaterialsAmount = paidMaterialsAmount;
    }

    public Double getPaidConstructionAmount() {
        return paidConstructionAmount;
    }

    public void setPaidConstructionAmount(Double paidConstructionAmount) {
        this.paidConstructionAmount = paidConstructionAmount;
    }

    public Double getPaidPmAmount() {
        return paidPmAmount;
    }

    public void setPaidPmAmount(Double paidPmAmount) {
        this.paidPmAmount = paidPmAmount;
    }
}
