package cn.damei.entity.mobile.Manager;

import java.util.Date;
import java.util.List;


public class ManagerTraditionSettleEntity{

    private String communityName;
    private String text;
    private String itemManager;
    private Integer itemManagerId;
    private String managerPhone;

    private String contractNumber;
    private String orderNumber;
    private String customerName;
    private String customerPhone;
    private String orderStatusNumber;
    private String orderStatusDescription;
    private Integer orderId;
    private String buildNumber;
    private String buildUnit;
    private String buildRoom;
    private String buildType;
    private String houseType;
    private String house;
    private String contractArea;
    private Date contractStartDate;
    private Date contractEndDate;
    private String contractTime;
    private Date actualStartDate;
    private Date actualEndDate;
    private String orderBy;
    private String delayType;
    private String designerName;
    private String designerPhone;
    private String orderReporterName;
    private String orderReporterPhone;
    private String serviceName;
    private String servicePhone;
    private String customerAddress;
    private String projectMode;
    private Integer storeId;
    private String orderInspector;
    private String orderInspectorPhone;
    private Integer orderInspectorId;
    private List<ManagerNormalSettle> settleList;
    private String checkNodeName;
    private Integer settleNodeId;

    public String getSettleApplyRemarks() {
        return settleApplyRemarks;
    }

    public void setSettleApplyRemarks(String settleApplyRemarks) {
        this.settleApplyRemarks = settleApplyRemarks;
    }

    private String settleApplyRemarks;


    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }


    public String getManagerPhone() {
        return managerPhone;
    }

    public void setManagerPhone(String managerPhone) {
        this.managerPhone = managerPhone;
    }

    public String getCheckNodeName() {
        return checkNodeName;
    }

    public void setCheckNodeName(String checkNodeName) {
        this.checkNodeName = checkNodeName;
    }
    public Integer getSettleNodeId() {
        return settleNodeId;
    }

    public void setSettleNodeId(Integer settleNodeId) {
        this.settleNodeId = settleNodeId;
    }

    public List<ManagerNormalSettle> getSettleList() {
        return settleList;
    }

    public void setSettleList(List<ManagerNormalSettle> settleList) {
        this.settleList = settleList;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
    public String getItemManager() {
        return itemManager;
    }

    public void setItemManager(String itemManager) {
        this.itemManager = itemManager;
    }

    public Integer getItemManagerId() {
        return itemManagerId;
    }

    public void setItemManagerId(Integer itemManagerId) {
        this.itemManagerId = itemManagerId;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getOrderStatusNumber() {
        return orderStatusNumber;
    }

    public void setOrderStatusNumber(String orderStatusNumber) {
        this.orderStatusNumber = orderStatusNumber;
    }

    public String getOrderStatusDescription() {
        return orderStatusDescription;
    }

    public void setOrderStatusDescription(String orderStatusDescription) {
        this.orderStatusDescription = orderStatusDescription;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public String getBuildNumber() {
        return buildNumber;
    }

    public void setBuildNumber(String buildNumber) {
        this.buildNumber = buildNumber;
    }

    public String getBuildUnit() {
        return buildUnit;
    }

    public void setBuildUnit(String buildUnit) {
        this.buildUnit = buildUnit;
    }

    public String getBuildRoom() {
        return buildRoom;
    }

    public void setBuildRoom(String buildRoom) {
        this.buildRoom = buildRoom;
    }

    public String getBuildType() {
        return buildType;
    }

    public void setBuildType(String buildType) {
        this.buildType = buildType;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getContractArea() {
        return contractArea;
    }

    public void setContractArea(String contractArea) {
        this.contractArea = contractArea;
    }

    public Date getContractStartDate() {
        return contractStartDate;
    }

    public void setContractStartDate(Date contractStartDate) {
        this.contractStartDate = contractStartDate;
    }

    public Date getContractEndDate() {
        return contractEndDate;
    }

    public void setContractEndDate(Date contractEndDate) {
        this.contractEndDate = contractEndDate;
    }

    public String getContractTime() {
        return contractTime;
    }

    public void setContractTime(String contractTime) {
        this.contractTime = contractTime;
    }

    public Date getActualStartDate() {
        return actualStartDate;
    }

    public void setActualStartDate(Date actualStartDate) {
        this.actualStartDate = actualStartDate;
    }

    public Date getActualEndDate() {
        return actualEndDate;
    }

    public void setActualEndDate(Date actualEndDate) {
        this.actualEndDate = actualEndDate;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getDelayType() {
        return delayType;
    }

    public void setDelayType(String delayType) {
        this.delayType = delayType;
    }

    public String getDesignerName() {
        return designerName;
    }

    public void setDesignerName(String designerName) {
        this.designerName = designerName;
    }

    public String getDesignerPhone() {
        return designerPhone;
    }

    public void setDesignerPhone(String designerPhone) {
        this.designerPhone = designerPhone;
    }

    public String getOrderReporterName() {
        return orderReporterName;
    }

    public void setOrderReporterName(String orderReporterName) {
        this.orderReporterName = orderReporterName;
    }

    public String getOrderReporterPhone() {
        return orderReporterPhone;
    }

    public void setOrderReporterPhone(String orderReporterPhone) {
        this.orderReporterPhone = orderReporterPhone;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServicePhone() {
        return servicePhone;
    }

    public void setServicePhone(String servicePhone) {
        this.servicePhone = servicePhone;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getProjectMode() {
        return projectMode;
    }

    public void setProjectMode(String projectMode) {
        this.projectMode = projectMode;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getOrderInspector() {
        return orderInspector;
    }

    public void setOrderInspector(String orderInspector) {
        this.orderInspector = orderInspector;
    }

    public String getOrderInspectorPhone() {
        return orderInspectorPhone;
    }

    public void setOrderInspectorPhone(String orderInspectorPhone) {
        this.orderInspectorPhone = orderInspectorPhone;
    }

    public Integer getOrderInspectorId() {
        return orderInspectorId;
    }

    public void setOrderInspectorId(Integer orderInspectorId) {
        this.orderInspectorId = orderInspectorId;
    }








}
