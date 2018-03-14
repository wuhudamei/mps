package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class OrderFloor2 extends DataEntity2<OrderFloor2> {
    private static final long serialVersionUID = 1L;

    private Integer orderId;

    private String orderNumber;

    private Double floorSettleArea;

    private Double floorTileBudgetArea;

    private Double floorTileSettleArea;

    private Date payDate;

    private Integer storeId;

    private String projectMode;

    private List<Integer> enginDepartIds = new ArrayList<Integer>();

    private Integer enginDepartId;

    private String enginDepartName;

    private String customerName;

    private String communityName;

    private String buildNumber;

    private String buildUnit;

    private String buildRoom;

    private String customerAddress;

    private String itemManager;

    private Date startDate;

    private Date endDate;

    private Integer orderTaskPackageId;

    public Integer getOrderTaskPackageId() {
        return orderTaskPackageId;
    }

    public void setOrderTaskPackageId(Integer orderTaskPackageId) {
        this.orderTaskPackageId = orderTaskPackageId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Double getFloorSettleArea() {
        return floorSettleArea;
    }

    public void setFloorSettleArea(Double floorSettleArea) {
        this.floorSettleArea = floorSettleArea;
    }

    public Double getFloorTileBudgetArea() {
        return floorTileBudgetArea;
    }

    public void setFloorTileBudgetArea(Double floorTileBudgetArea) {
        this.floorTileBudgetArea = floorTileBudgetArea;
    }

    public Double getFloorTileSettleArea() {
        return floorTileSettleArea;
    }

    public void setFloorTileSettleArea(Double floorTileSettleArea) {
        this.floorTileSettleArea = floorTileSettleArea;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getProjectMode() {
        return projectMode;
    }

    public void setProjectMode(String projectMode) {
        this.projectMode = projectMode;
    }

    public List<Integer> getEnginDepartIds() {
        return enginDepartIds;
    }

    public void setEnginDepartIds(List<Integer> enginDepartIds) {
        this.enginDepartIds = enginDepartIds;
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getItemManager() {
        return itemManager;
    }

    public void setItemManager(String itemManager) {
        this.itemManager = itemManager;
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
