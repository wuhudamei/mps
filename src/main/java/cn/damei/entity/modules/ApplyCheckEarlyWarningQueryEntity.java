package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity;

import java.util.Date;
import java.util.List;


public class ApplyCheckEarlyWarningQueryEntity  extends DataEntity<ApplyCheckEarlyWarningQueryEntity>{


    private Integer storeId;
    private Integer projectMode;
private Integer engineDepartId;
    private String constructionScheduleName;
    private String nodeId;
    private Integer orderId;
    private Integer delayOrderCount;
    private String checkNodeName;
    private Date  planEndDate;
    private Integer delayDaysCount;
    private String engineDepartName;
    private String addressInfo;
    private String customerInfo;
    private String managerInfo;
    private String pqcInfo;
    private Date actualStartDate;
    private Date expectCheckDate;
    private Date pqcSignDate;
    private Date pqcCheckDate;
    private Date pqcDoneDate;
private String customerName;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getEngineDepartId() {
        return engineDepartId;
    }

    public void setEngineDepartId(Integer engineDepartId) {
        this.engineDepartId = engineDepartId;
    }

    public String getCheckNodeName() {
        return checkNodeName;
    }

    public void setCheckNodeName(String checkNodeName) {
        this.checkNodeName = checkNodeName;
    }

    public Date getPlanEndDate() {
        return planEndDate;
    }

    public void setPlanEndDate(Date planEndDate) {
        this.planEndDate = planEndDate;
    }

    public Integer getDelayDaysCount() {
        return delayDaysCount;
    }

    public void setDelayDaysCount(Integer delayDaysCount) {
        this.delayDaysCount = delayDaysCount;
    }

    public String getEngineDepartName() {
        return engineDepartName;
    }

    public void setEngineDepartName(String engineDepartName) {
        this.engineDepartName = engineDepartName;
    }

    public String getAddressInfo() {
        return addressInfo;
    }

    public void setAddressInfo(String addressInfo) {
        this.addressInfo = addressInfo;
    }

    public String getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(String customerInfo) {
        this.customerInfo = customerInfo;
    }

    public String getManagerInfo() {
        return managerInfo;
    }

    public void setManagerInfo(String managerInfo) {
        this.managerInfo = managerInfo;
    }

    public String getPqcInfo() {
        return pqcInfo;
    }

    public void setPqcInfo(String pqcInfo) {
        this.pqcInfo = pqcInfo;
    }

    public Date getActualStartDate() {
        return actualStartDate;
    }

    public void setActualStartDate(Date actualStartDate) {
        this.actualStartDate = actualStartDate;
    }

    public Date getExpectCheckDate() {
        return expectCheckDate;
    }

    public void setExpectCheckDate(Date expectCheckDate) {
        this.expectCheckDate = expectCheckDate;
    }

    public Date getPqcSignDate() {
        return pqcSignDate;
    }

    public void setPqcSignDate(Date pqcSignDate) {
        this.pqcSignDate = pqcSignDate;
    }

    public Date getPqcCheckDate() {
        return pqcCheckDate;
    }

    public void setPqcCheckDate(Date pqcCheckDate) {
        this.pqcCheckDate = pqcCheckDate;
    }

    public Date getPqcDoneDate() {
        return pqcDoneDate;
    }

    public void setPqcDoneDate(Date pqcDoneDate) {
        this.pqcDoneDate = pqcDoneDate;
    }

    public Integer getDelayOrderCount() {
        return delayOrderCount;
    }

    public void setDelayOrderCount(Integer delayOrderCount) {
        this.delayOrderCount = delayOrderCount;
    }

    private List<Integer> orderIds;

    public List<Integer> getOrderIds() {
        return orderIds;
    }

    public void setOrderIds(List<Integer> orderIds) {
        this.orderIds = orderIds;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getProjectMode() {
        return projectMode;
    }

    public void setProjectMode(Integer projectMode) {
        this.projectMode = projectMode;
    }

    public String getConstructionScheduleName() {
        return constructionScheduleName;
    }

    public void setConstructionScheduleName(String constructionScheduleName) {
        this.constructionScheduleName = constructionScheduleName;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}
