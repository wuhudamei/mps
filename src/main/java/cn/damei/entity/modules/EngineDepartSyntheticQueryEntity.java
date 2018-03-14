package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity;

import java.util.Date;
import java.util.Objects;

/**
 * Created by joseph on 2017/4/7.
 * 工程部综合查询类
 */
public class EngineDepartSyntheticQueryEntity  extends DataEntity<EngineDepartSyntheticQueryEntity>{

    private Integer storeId; //门店id
    private String storeName;

    private Integer distributeOrderCountNow;//分配项目经理当天
    private Integer actualStartOrderCount;//开工数
    private Integer actualStartOrderCountNow;//开工数当天

    private Integer buildOrderCount;
    private Integer buildOrderCountNow;
    private Integer buildOrderTraditionCount;
    private Integer buildOrderIndustryCount;

    private String orderProjectModeIndustry;
    private String orderProjectModeTradition;
    private String queryDate;
    private Date queryDateForJsp;

    private String engineDepartName;
    private Integer accpetOrderCount;
    private Integer  accpetOrderCountNow;
    private Integer distributeOrderCount;
    private Integer discloseOrderCountNow;
    private Integer discloseOrderCount;




    private String  basicDoneName="基装验收";//基装验收
    private String  completeDoneName="竣工验收";


    private Integer basicDoneCount;//基装验收数
    private Integer basicDaysDiff=40;//基装延期比较天数
    private  Integer basicDelayCount;//基装延期数量
    private String basicDelayPercent;//基装延期占比



    private Integer  mainMaterialDaysDiff=60;//主材延期比较天数
    private Integer mainMaterialStartCount;//主材施工数量
    private Integer mainMaterialDaysDelayCount;//主材延期数量
    private String  mainMaterialDelayPercent;//主材延期占比



    private Integer contractStartDayCount;//合同约定工期-施工中
    private  Integer contractStartDayDelayCount;//合同约定工期-延期数
    private String  contractStartDelayPercent;//合同约定工期-延期占比


    private String  orderDiscloseStatus;//交底状态130
    private String orderConfirmStartStatus;//确认开工状态200
    private String orderDistributeManagerStatus;//分配项目经理120
    private String orderApplyCompleteStatus;//竣工300
    private String  orderInspectionCheckPassStatus;//质检审核通过320


    private Date start;
    private Date end;
private String startDate;
private String endDate;


private Date pqcQueryDate;

private String projectMode;
private String qcCheckNodeName;
private Integer shouldBeDoneCount;
private Integer managerApplyDoneCount;
private Integer alreadyDoneCount;
private Integer unFinishDoneCount;

    public Date getPqcQueryDate() {
        return pqcQueryDate;
    }

    public void setPqcQueryDate(Date pqcQueryDate) {
        this.pqcQueryDate = pqcQueryDate;
    }

    public String getProjectMode() {
        return projectMode;
    }

    public void setProjectMode(String projectMode) {
        this.projectMode = projectMode;
    }

    public String getQcCheckNodeName() {
        return qcCheckNodeName;
    }

    public void setQcCheckNodeName(String qcCheckNodeName) {
        this.qcCheckNodeName = qcCheckNodeName;
    }

    public Integer getShouldBeDoneCount() {
        return shouldBeDoneCount;
    }

    public void setShouldBeDoneCount(Integer shouldBeDoneCount) {
        this.shouldBeDoneCount = shouldBeDoneCount;
    }

    public Integer getManagerApplyDoneCount() {
        return managerApplyDoneCount;
    }

    public void setManagerApplyDoneCount(Integer managerApplyDoneCount) {
        this.managerApplyDoneCount = managerApplyDoneCount;
    }

    public Integer getAlreadyDoneCount() {
        return alreadyDoneCount;
    }

    public void setAlreadyDoneCount(Integer alreadyDoneCount) {
        this.alreadyDoneCount = alreadyDoneCount;
    }

    public Integer getUnFinishDoneCount() {
        return unFinishDoneCount;
    }

    public void setUnFinishDoneCount(Integer unFinishDoneCount) {
        this.unFinishDoneCount = unFinishDoneCount;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getCompleteDoneName() {
        return completeDoneName;
    }

    public void setCompleteDoneName(String completeDoneName) {
        this.completeDoneName = completeDoneName;
    }

    public Integer getMainMaterialDaysDiff() {
        return mainMaterialDaysDiff;
    }

    public void setMainMaterialDaysDiff(Integer mainMaterialDaysDiff) {
        this.mainMaterialDaysDiff = mainMaterialDaysDiff;
    }

    public Integer getMainMaterialStartCount() {
        return mainMaterialStartCount;
    }

    public void setMainMaterialStartCount(Integer mainMaterialStartCount) {
        this.mainMaterialStartCount = mainMaterialStartCount;
    }

    public Integer getMainMaterialDaysDelayCount() {
        return mainMaterialDaysDelayCount;
    }

    public void setMainMaterialDaysDelayCount(Integer mainMaterialDaysDelayCount) {
        this.mainMaterialDaysDelayCount = mainMaterialDaysDelayCount;
    }

    public String getMainMaterialDelayPercent() {
        return mainMaterialDelayPercent;
    }

    public void setMainMaterialDelayPercent(String mainMaterialDelayPercent) {
        this.mainMaterialDelayPercent = mainMaterialDelayPercent;
    }

    public Integer getContractStartDayCount() {
        return contractStartDayCount;
    }

    public void setContractStartDayCount(Integer contractStartDayCount) {
        this.contractStartDayCount = contractStartDayCount;
    }

    public Integer getContractStartDayDelayCount() {
        return contractStartDayDelayCount;
    }

    public void setContractStartDayDelayCount(Integer contractStartDayDelayCount) {
        this.contractStartDayDelayCount = contractStartDayDelayCount;
    }

    public String getContractStartDelayPercent() {
        return contractStartDelayPercent;
    }

    public void setContractStartDelayPercent(String contractStartDelayPercent) {
        this.contractStartDelayPercent = contractStartDelayPercent;
    }



    public String getBasicDoneName() {
        return basicDoneName;
    }

    public void setBasicDoneName(String basicDoneName) {
        this.basicDoneName = basicDoneName;
    }

    public Integer getBasicDoneCount() {
        return basicDoneCount;
    }

    public void setBasicDoneCount(Integer basicDoneCount) {
        this.basicDoneCount = basicDoneCount;
    }

    public Integer getBasicDaysDiff() {
        return basicDaysDiff;
    }

    public void setBasicDaysDiff(Integer basicDaysDiff) {
        this.basicDaysDiff = basicDaysDiff;
    }

    public Integer getBasicDelayCount() {
        return basicDelayCount;
    }

    public void setBasicDelayCount(Integer basicDelayCount) {
        this.basicDelayCount = basicDelayCount;
    }

    public String getBasicDelayPercent() {
        return basicDelayPercent;
    }

    public void setBasicDelayPercent(String basicDelayPercent) {
        this.basicDelayPercent = basicDelayPercent;
    }

    public String getOrderInspectionCheckPassStatus() {
        return orderInspectionCheckPassStatus;
    }

    public void setOrderInspectionCheckPassStatus(String orderInspectionCheckPassStatus) {
        this.orderInspectionCheckPassStatus = orderInspectionCheckPassStatus;
    }

    public Date getQueryDateForJsp() {
        return queryDateForJsp;
    }
    public Integer getBuildOrderTraditionCount() {
        return buildOrderTraditionCount;
    }

    public Integer getBuildOrderIndustryCount() {
        return buildOrderIndustryCount;
    }

    public void setBuildOrderIndustryCount(Integer buildOrderIndustryCount) {
        this.buildOrderIndustryCount = buildOrderIndustryCount;
    }

    public void setBuildOrderTraditionCount(Integer buildOrderTraditionCount) {
        this.buildOrderTraditionCount = buildOrderTraditionCount;
    }

    public void setQueryDateForJsp(Date queryDateForJsp) {
        this.queryDateForJsp = queryDateForJsp;
    }

    public Integer getDiscloseOrderCountNow() {
        return discloseOrderCountNow;
    }

    public void setDiscloseOrderCountNow(Integer discloseOrderCountNow) {
        this.discloseOrderCountNow = discloseOrderCountNow;
    }

    public Integer getDiscloseOrderCount() {
        return discloseOrderCount;
    }

    public void setDiscloseOrderCount(Integer discloseOrderCount) {
        this.discloseOrderCount = discloseOrderCount;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        EngineDepartSyntheticQueryEntity that = (EngineDepartSyntheticQueryEntity) o;
        return Objects.equals(orderDiscloseStatus, that.orderDiscloseStatus) &&
                Objects.equals(orderConfirmStartStatus, that.orderConfirmStartStatus) &&
                Objects.equals(orderDistributeManagerStatus, that.orderDistributeManagerStatus) &&
                Objects.equals(orderApplyCompleteStatus, that.orderApplyCompleteStatus) &&
                Objects.equals(orderProjectModeIndustry, that.orderProjectModeIndustry) &&
                Objects.equals(orderProjectModeTradition, that.orderProjectModeTradition) &&
                Objects.equals(queryDate, that.queryDate) &&
                Objects.equals(engineDepartName, that.engineDepartName) &&
                Objects.equals(accpetOrderCount, that.accpetOrderCount) &&
                Objects.equals(accpetOrderCountNow, that.accpetOrderCountNow) &&
                Objects.equals(distributeOrderCount, that.distributeOrderCount) &&
                Objects.equals(distributeOrderCountNow, that.distributeOrderCountNow) &&
                Objects.equals(actualStartOrderCount, that.actualStartOrderCount) &&
                Objects.equals(actualStartOrderCountNow, that.actualStartOrderCountNow) &&
                Objects.equals(buildOrderCount, that.buildOrderCount) &&
                Objects.equals(buildOrderCountNow, that.buildOrderCountNow) &&
                Objects.equals(storeId, that.storeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderDiscloseStatus, orderConfirmStartStatus, orderDistributeManagerStatus, orderApplyCompleteStatus, orderProjectModeIndustry, orderProjectModeTradition, queryDate, engineDepartName, accpetOrderCount, accpetOrderCountNow, distributeOrderCount, distributeOrderCountNow, actualStartOrderCount, actualStartOrderCountNow, buildOrderCount, buildOrderCountNow, storeId);
    }

    public String getOrderDiscloseStatus() {
        return orderDiscloseStatus;
    }

    public void setOrderDiscloseStatus(String orderDiscloseStatus) {
        this.orderDiscloseStatus = orderDiscloseStatus;
    }

    public String getOrderConfirmStartStatus() {
        return orderConfirmStartStatus;
    }

    public void setOrderConfirmStartStatus(String orderConfirmStartStatus) {
        this.orderConfirmStartStatus = orderConfirmStartStatus;
    }

    public String getOrderDistributeManagerStatus() {
        return orderDistributeManagerStatus;
    }

    public void setOrderDistributeManagerStatus(String orderDistributeManagerStatus) {
        this.orderDistributeManagerStatus = orderDistributeManagerStatus;
    }

    public String getOrderApplyCompleteStatus() {
        return orderApplyCompleteStatus;
    }

    public void setOrderApplyCompleteStatus(String orderApplyCompleteStatus) {
        this.orderApplyCompleteStatus = orderApplyCompleteStatus;
    }

    public String getOrderProjectModeIndustry() {
        return orderProjectModeIndustry;
    }

    public void setOrderProjectModeIndustry(String orderProjectModeIndustry) {
        this.orderProjectModeIndustry = orderProjectModeIndustry;
    }

    public String getOrderProjectModeTradition() {
        return orderProjectModeTradition;
    }

    public void setOrderProjectModeTradition(String orderProjectModeTradition) {
        this.orderProjectModeTradition = orderProjectModeTradition;
    }

    public String getQueryDate() {
        return queryDate;
    }

    public void setQueryDate(String queryDate) {
        this.queryDate = queryDate;
    }

    public String getEngineDepartName() {
        return engineDepartName;
    }

    public void setEngineDepartName(String engineDepartName) {
        this.engineDepartName = engineDepartName;
    }

    public Integer getAccpetOrderCount() {
        return accpetOrderCount;
    }

    public void setAccpetOrderCount(Integer accpetOrderCount) {
        this.accpetOrderCount = accpetOrderCount;
    }

    public Integer getAccpetOrderCountNow() {
        return accpetOrderCountNow;
    }

    public void setAccpetOrderCountNow(Integer accpetOrderCountNow) {
        this.accpetOrderCountNow = accpetOrderCountNow;
    }

    public Integer getDistributeOrderCount() {
        return distributeOrderCount;
    }

    public void setDistributeOrderCount(Integer distributeOrderCount) {
        this.distributeOrderCount = distributeOrderCount;
    }

    public Integer getDistributeOrderCountNow() {
        return distributeOrderCountNow;
    }

    public void setDistributeOrderCountNow(Integer distributeOrderCountNow) {
        this.distributeOrderCountNow = distributeOrderCountNow;
    }

    public Integer getActualStartOrderCount() {
        return actualStartOrderCount;
    }

    public void setActualStartOrderCount(Integer actualStartOrderCount) {
        this.actualStartOrderCount = actualStartOrderCount;
    }

    public Integer getActualStartOrderCountNow() {
        return actualStartOrderCountNow;
    }

    public void setActualStartOrderCountNow(Integer actualStartOrderCountNow) {
        this.actualStartOrderCountNow = actualStartOrderCountNow;
    }

    public Integer getBuildOrderCount() {
        return buildOrderCount;
    }

    public void setBuildOrderCount(Integer buildOrderCount) {
        this.buildOrderCount = buildOrderCount;
    }

    public Integer getBuildOrderCountNow() {
        return buildOrderCountNow;
    }

    public void setBuildOrderCountNow(Integer buildOrderCountNow) {
        this.buildOrderCountNow = buildOrderCountNow;
    }



    public EngineDepartSyntheticQueryEntity() {
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }




}
