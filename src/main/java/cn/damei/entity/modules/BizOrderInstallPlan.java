package cn.damei.entity.modules;

import java.util.Date;
import java.util.List;

import cn.damei.common.persistence.DataEntity2;
import cn.damei.common.utils.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;

@SuppressWarnings("serial")
public class BizOrderInstallPlan extends DataEntity2<BizOrderInstallPlan> {

    private Integer id;


    private Integer orderId;


    private Integer orderInstallItemId;


    private String installItemName;


    private Integer installItemSequence;


    private Date planIntoDate;


    private Date applyIntoDate;


    private Date realIntoDate;


    private Date realCompleteDate;


    private Date realAcceptDate;


    private String status;


    private String applyIntoRemarks;


    private String isCompleteDelay;


    private String completeDelayReason;


    private String completeDelayRemarks;


    private String remarks;

    private String gongyingshagnDate;
    private String gongyingshagnperson;
    private String gongyingshagngroup;
    private String gongrendata;
    private String gongrenPerson;
    private String gongrensuccessdate;
    private String gongrensuccessPerson;
    private String gongrensuccessPicUrl;


    private Date createDate;


    private Date updateDate;


    private String delFlag;


    private Date applyIntoCreateDatetime;
    private Date supplierConfirmIntoDate;
    private String supplierConfirmRemarks;


    private String unqualifiedReason;

    private String unqualifiedReasonId;

    private String unqualifiedReasonConfigure;

    private String unqualifiedRemarks;

    private String firstPassRate;

    private Integer unqualifiedTimes;

    private String projectMode;

    private String orderNumber;

    private String storeId;




    private String itemManager;

    private String acceptRemarks;

    private String customerAddressDetaill;

    private String customerName;

    private Date unqualifiedAcceptTime;

    private Date beginUnqualifiedAcceptTime;


    private Date endUnqualifiedAcceptTime;



    private String enginDepartId;

    private String enginDepartName;

    private String orderInstallItemIdStop;

    private String unqualifiedInstallItemCount;

    private String storeName;
    private String projectModeName;
    private String installMode;

    public String getInstallMode() {
        return installMode;
    }

    public void setInstallMode(String installMode) {
        this.installMode = installMode;
    }

    private List<String> projectInstallItemIdList;

    public List<String> getProjectInstallItemIdList() {
        return projectInstallItemIdList;
    }

    public void setProjectInstallItemIdList(List<String> projectInstallItemIdList) {
        this.projectInstallItemIdList = projectInstallItemIdList;
    }

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

    public String getUnqualifiedInstallItemCount() {
        return unqualifiedInstallItemCount;
    }

    public void setUnqualifiedInstallItemCount(String unqualifiedInstallItemCount) {
        this.unqualifiedInstallItemCount = unqualifiedInstallItemCount;
    }

    public String getOrderInstallItemIdStop() {
        return orderInstallItemIdStop;
    }

    public void setOrderInstallItemIdStop(String orderInstallItemIdStop) {
        this.orderInstallItemIdStop = orderInstallItemIdStop;
    }

    public String getEnginDepartId() {
        return enginDepartId;
    }

    public void setEnginDepartId(String enginDepartId) {
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

    public Date getBeginUnqualifiedAcceptTime() {
        return beginUnqualifiedAcceptTime;
    }
    public String getBeginUnqualifiedAcceptTimeString() {
        if(beginUnqualifiedAcceptTime != null){
            return DateUtils.formatDateTime(beginUnqualifiedAcceptTime);
        }
        return "";

    }

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public void setBeginUnqualifiedAcceptTime(Date beginUnqualifiedAcceptTime) {
        this.beginUnqualifiedAcceptTime = beginUnqualifiedAcceptTime;
    }

    public Date getEndUnqualifiedAcceptTime() {
        return endUnqualifiedAcceptTime;
    }
    public String getEndUnqualifiedAcceptTimeString() {
        if(endUnqualifiedAcceptTime != null){
            return DateUtils.formatDateTime(endUnqualifiedAcceptTime);
        }
        return "";

    }

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public void setEndUnqualifiedAcceptTime(Date endUnqualifiedAcceptTime) {
        this.endUnqualifiedAcceptTime = endUnqualifiedAcceptTime;
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

    public String getStoreId() {
        return storeId;
    }



    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getItemManager() {
        return itemManager;
    }

    public void setItemManager(String itemManager) {
        this.itemManager = itemManager;
    }

    public String getAcceptRemarks() {
        return acceptRemarks;
    }

    public void setAcceptRemarks(String acceptRemarks) {
        this.acceptRemarks = acceptRemarks;
    }

    public String getCustomerAddressDetaill() {
        return customerAddressDetaill;
    }

    public void setCustomerAddressDetaill(String customerAddressDetaill) {
        this.customerAddressDetaill = customerAddressDetaill;
    }

    public Date getUnqualifiedAcceptTime() {
        return unqualifiedAcceptTime;
    }

    public void setUnqualifiedAcceptTime(Date unqualifiedAcceptTime) {
        this.unqualifiedAcceptTime = unqualifiedAcceptTime;
    }

    public String getUnqualifiedReason() {
        return unqualifiedReason;
    }

    public void setUnqualifiedReason(String unqualifiedReason) {
        this.unqualifiedReason = unqualifiedReason;
    }

    public String getUnqualifiedRemarks() {
        return unqualifiedRemarks;
    }

    public void setUnqualifiedRemarks(String unqualifiedRemarks) {
        this.unqualifiedRemarks = unqualifiedRemarks;
    }

    public String getFirstPassRate() {
        if(firstPassRate != null){
            Double dou = Double.parseDouble(firstPassRate) * 100;
            return dou+"%";
        }else{
            return firstPassRate;
        }

    }

    public void setFirstPassRate(String firstPassRate) {
        this.firstPassRate = firstPassRate;
    }

    public Integer getUnqualifiedTimes() {
        return unqualifiedTimes;
    }

    public void setUnqualifiedTimes(Integer unqualifiedTimes) {
        this.unqualifiedTimes = unqualifiedTimes;
    }

    public String getSupplierConfirmRemarks() {
        return supplierConfirmRemarks;
    }

    public void setSupplierConfirmRemarks(String supplierConfirmRemarks) {
        this.supplierConfirmRemarks = supplierConfirmRemarks;
    }

    public String getGongyingshagnDate() {
        return gongyingshagnDate;
    }

    public void setGongyingshagnDate(String gongyingshagnDate) {
        this.gongyingshagnDate = gongyingshagnDate;
    }

    public Date getSupplierConfirmIntoDate() {
        return supplierConfirmIntoDate;
    }

    public void setSupplierConfirmIntoDate(Date supplierConfirmIntoDate) {
        this.supplierConfirmIntoDate = supplierConfirmIntoDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGongyingshagnperson() {
        return gongyingshagnperson;
    }

    public void setGongyingshagnperson(String gongyingshagnperson) {
        this.gongyingshagnperson = gongyingshagnperson;
    }

    public String getGongyingshagngroup() {
        return gongyingshagngroup;
    }

    public void setGongyingshagngroup(String gongyingshagngroup) {
        this.gongyingshagngroup = gongyingshagngroup;
    }

    public String getGongrendata() {
        return gongrendata;
    }

    public void setGongrendata(String gongrendata) {
        this.gongrendata = gongrendata;
    }

    public String getGongrenPerson() {
        return gongrenPerson;
    }

    public void setGongrenPerson(String gongrenPerson) {
        this.gongrenPerson = gongrenPerson;
    }

    public String getGongrensuccessdate() {
        return gongrensuccessdate;
    }

    public void setGongrensuccessdate(String gongrensuccessdate) {
        this.gongrensuccessdate = gongrensuccessdate;
    }

    public String getGongrensuccessPerson() {
        return gongrensuccessPerson;
    }

    public void setGongrensuccessPerson(String gongrensuccessPerson) {
        this.gongrensuccessPerson = gongrensuccessPerson;
    }

    public String getGongrensuccessPicUrl() {
        return gongrensuccessPicUrl;
    }

    public void setGongrensuccessPicUrl(String gongrensuccessPicUrl) {
        this.gongrensuccessPicUrl = gongrensuccessPicUrl;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderInstallItemId() {
        return orderInstallItemId;
    }

    public void setOrderInstallItemId(Integer orderInstallItemId) {
        this.orderInstallItemId = orderInstallItemId;
    }

    public String getInstallItemName() {
        return installItemName;
    }

    public void setInstallItemName(String installItemName) {
        this.installItemName = installItemName;
    }

    public Integer getInstallItemSequence() {
        return installItemSequence;
    }

    public void setInstallItemSequence(Integer installItemSequence) {
        this.installItemSequence = installItemSequence;
    }

    public Date getApplyIntoDate() {
        return applyIntoDate;
    }

    public void setApplyIntoDate(Date applyIntoDate) {
        this.applyIntoDate = applyIntoDate;
    }

    public Date getPlanIntoDate() {
        return planIntoDate;
    }

    public void setPlanIntoDate(Date planIntoDate) {
        this.planIntoDate = planIntoDate;
    }

    public Date getRealIntoDate() {
        return realIntoDate;
    }

    public void setRealIntoDate(Date realIntoDate) {
        this.realIntoDate = realIntoDate;
    }

    public Date getRealCompleteDate() {
        return realCompleteDate;
    }

    public void setRealCompleteDate(Date realCompleteDate) {
        this.realCompleteDate = realCompleteDate;
    }

    public Date getRealAcceptDate() {
        return realAcceptDate;
    }

    public void setRealAcceptDate(Date realAcceptDate) {
        this.realAcceptDate = realAcceptDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApplyIntoRemarks() {
        return applyIntoRemarks;
    }

    public void setApplyIntoRemarks(String applyIntoRemarks) {
        this.applyIntoRemarks = applyIntoRemarks;
    }

    public String getIsCompleteDelay() {
        return isCompleteDelay;
    }

    public void setIsCompleteDelay(String isCompleteDelay) {
        this.isCompleteDelay = isCompleteDelay;
    }

    public String getCompleteDelayReason() {
        return completeDelayReason;
    }

    public void setCompleteDelayReason(String completeDelayReason) {
        this.completeDelayReason = completeDelayReason;
    }

    public String getCompleteDelayRemarks() {
        return completeDelayRemarks;
    }

    public void setCompleteDelayRemarks(String completeDelayRemarks) {
        this.completeDelayRemarks = completeDelayRemarks;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public Date getApplyIntoCreateDatetime() {
        return applyIntoCreateDatetime;
    }

    public void setApplyIntoCreateDatetime(Date applyIntoCreateDatetime) {
        this.applyIntoCreateDatetime = applyIntoCreateDatetime;
    }

    public String getUnqualifiedReasonId() {
        return unqualifiedReasonId;
    }

    public void setUnqualifiedReasonId(String unqualifiedReasonId) {
        this.unqualifiedReasonId = unqualifiedReasonId;
    }

    public String getUnqualifiedReasonConfigure() {
        return unqualifiedReasonConfigure;
    }

    public void setUnqualifiedReasonConfigure(String unqualifiedReasonConfigure) {
        this.unqualifiedReasonConfigure = unqualifiedReasonConfigure;
    }
}
