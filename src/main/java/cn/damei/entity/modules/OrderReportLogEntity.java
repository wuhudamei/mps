package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity;

import java.util.Date;

/**
 * Created by joseph on 2017/5/15.
 * 返单状态log实体类,通用保存log日志
 */
public class OrderReportLogEntity extends DataEntity<OrderReportLogEntity> {


    private static final long serialVersionUID = 1L;


    private Integer orderReportId; //关联返单id
    private String logType;//日志的type   从 BizOrderReportConstantUtil 中取
    private String operateSource;//返单来源
    private String operateEmployeeId;//操作人Id
    private String operateEmployeeName;//操作人
    private String operateEmployeePhone;//操作人手机(一般无用)
    private Date operateDateTime;//操作时间
    private Date logDateTime;//log的生成时间
    private String logRemarks;//log保存备注
    private String logStatusRemarks;
    private String serviceId;//客服id
    private String serviceName;//客服名称
    private String servicePhone;//客服手机
    private String messageContent;//log查看短信内容
    private String relatedOrderNumber;//该log保存时,关联的订单编号(已签单和已签合同)
    private Date inStoreDateTime;

    private Date  signBillDateTime;
    private String signBillRemarks;

    public Date getInStoreDateTime() {
        return inStoreDateTime;
    }

    public void setInStoreDateTime(Date inStoreDateTime) {
        this.inStoreDateTime = inStoreDateTime;
    }



    public Date getSignBillDateTime() {
        return signBillDateTime;
    }

    public void setSignBillDateTime(Date signBillDateTime) {
        this.signBillDateTime = signBillDateTime;
    }

    public String getSignBillRemarks() {
        return signBillRemarks;
    }

    public void setSignBillRemarks(String signBillRemarks) {
        this.signBillRemarks = signBillRemarks;
    }

    public String getLogStatusRemarks() {
        return logStatusRemarks;
    }

    public void setLogStatusRemarks(String logStatusRemarks) {
        this.logStatusRemarks = logStatusRemarks;
    }

    public Integer getOrderReportId() {
        return orderReportId;
    }

    public void setOrderReportId(Integer orderReportId) {
        this.orderReportId = orderReportId;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public String getOperateSource() {
        return operateSource;
    }

    public void setOperateSource(String operateSource) {
        this.operateSource = operateSource;
    }

    public String getOperateEmployeeId() {
        return operateEmployeeId;
    }

    public void setOperateEmployeeId(String operateEmployeeId) {
        this.operateEmployeeId = operateEmployeeId;
    }

    public String getOperateEmployeeName() {
        return operateEmployeeName;
    }

    public void setOperateEmployeeName(String operateEmployeeName) {
        this.operateEmployeeName = operateEmployeeName;
    }

    public String getOperateEmployeePhone() {
        return operateEmployeePhone;
    }

    public void setOperateEmployeePhone(String operateEmployeePhone) {
        this.operateEmployeePhone = operateEmployeePhone;
    }

    public Date getOperateDateTime() {
        return operateDateTime;
    }

    public void setOperateDateTime(Date operateDateTime) {
        this.operateDateTime = operateDateTime;
    }

    public Date getLogDateTime() {
        return logDateTime;
    }

    public void setLogDateTime(Date logDateTime) {
        this.logDateTime = logDateTime;
    }

    public String getLogRemarks() {
        return logRemarks;
    }

    public void setLogRemarks(String logRemarks) {
        this.logRemarks = logRemarks;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
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

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getRelatedOrderNumber() {
        return relatedOrderNumber;
    }

    public void setRelatedOrderNumber(String relatedOrderNumber) {
        this.relatedOrderNumber = relatedOrderNumber;
    }



    private Date invidReportDate;

    private String distributeServiceOperateName;
    private Date distributeServiceOperateDate;
    private String distributeServiceName;
    private String distributeServicePhone;


    private String transferServiceOperateName;
    private Date transferServiceOperateDate;
    private String transferServiceName;
    private String transferServicePhone;


    private String inStoreRemarks;
    private Date inStoreDate;
    private Date inStoreOperateDate;
    private String inStoreOperateName;
    private  String inStorePhoneMessageContent;


    private Date signDateTime;
    private String signRemarks;
    private Date signOperateDate;
    private String signOperateName;
    private String signRelatedOrderNumbers;
    private String signPhoneMessageContent;





    private Date signContractDateTime;
    private String signContractRemarks;
    private Date signContractOperateDate;
    private String signContractOperateName;
    private String signContractRelatedOrderNumbers;
    private String signContractPhoneMessageContent;







    private String replenishSignContractRemarks;
    private Date replenishSignContractOperateDate;
    private String replenishSignContractOperateName;
    private String replenishSignContractRelatedOrderNumbers;
    private String replenishSignContractPhoneMessageContent;



    private Date outOfDateDate;

    public String getInStoreRemarks() {
        return inStoreRemarks;
    }

    public void setInStoreRemarks(String inStoreRemarks) {
        this.inStoreRemarks = inStoreRemarks;
    }

    public Date getInvidReportDate() {
        return invidReportDate;
    }

    public void setInvidReportDate(Date invidReportDate) {
        this.invidReportDate = invidReportDate;
    }

    public String getDistributeServiceOperateName() {
        return distributeServiceOperateName;
    }

    public void setDistributeServiceOperateName(String distributeServiceOperateName) {
        this.distributeServiceOperateName = distributeServiceOperateName;
    }

    public Date getDistributeServiceOperateDate() {
        return distributeServiceOperateDate;
    }

    public void setDistributeServiceOperateDate(Date distributeServiceOperateDate) {
        this.distributeServiceOperateDate = distributeServiceOperateDate;
    }

    public String getDistributeServiceName() {
        return distributeServiceName;
    }

    public void setDistributeServiceName(String distributeServiceName) {
        this.distributeServiceName = distributeServiceName;
    }

    public String getDistributeServicePhone() {
        return distributeServicePhone;
    }

    public void setDistributeServicePhone(String distributeServicePhone) {
        this.distributeServicePhone = distributeServicePhone;
    }

    public String getTransferServiceOperateName() {
        return transferServiceOperateName;
    }

    public void setTransferServiceOperateName(String transferServiceOperateName) {
        this.transferServiceOperateName = transferServiceOperateName;
    }

    public Date getTransferServiceOperateDate() {
        return transferServiceOperateDate;
    }

    public void setTransferServiceOperateDate(Date transferServiceOperateDate) {
        this.transferServiceOperateDate = transferServiceOperateDate;
    }

    public String getTransferServiceName() {
        return transferServiceName;
    }

    public void setTransferServiceName(String transferServiceName) {
        this.transferServiceName = transferServiceName;
    }

    public String getTransferServicePhone() {
        return transferServicePhone;
    }

    public void setTransferServicePhone(String transferServicePhone) {
        this.transferServicePhone = transferServicePhone;
    }

    public Date getInStoreDate() {
        return inStoreDate;
    }

    public void setInStoreDate(Date inStoreDate) {
        this.inStoreDate = inStoreDate;
    }

    public Date getInStoreOperateDate() {
        return inStoreOperateDate;
    }

    public void setInStoreOperateDate(Date inStoreOperateDate) {
        this.inStoreOperateDate = inStoreOperateDate;
    }

    public String getInStoreOperateName() {
        return inStoreOperateName;
    }

    public void setInStoreOperateName(String inStoreOperateName) {
        this.inStoreOperateName = inStoreOperateName;
    }

    public String getInStorePhoneMessageContent() {
        return inStorePhoneMessageContent;
    }

    public void setInStorePhoneMessageContent(String inStorePhoneMessageContent) {
        this.inStorePhoneMessageContent = inStorePhoneMessageContent;
    }

    public Date getSignDateTime() {
        return signDateTime;
    }

    public void setSignDateTime(Date signDateTime) {
        this.signDateTime = signDateTime;
    }

    public String getSignRemarks() {
        return signRemarks;
    }

    public void setSignRemarks(String signRemarks) {
        this.signRemarks = signRemarks;
    }

    public Date getSignOperateDate() {
        return signOperateDate;
    }

    public void setSignOperateDate(Date signOperateDate) {
        this.signOperateDate = signOperateDate;
    }

    public String getSignOperateName() {
        return signOperateName;
    }

    public void setSignOperateName(String signOperateName) {
        this.signOperateName = signOperateName;
    }

    public String getSignRelatedOrderNumbers() {
        return signRelatedOrderNumbers;
    }

    public void setSignRelatedOrderNumbers(String signRelatedOrderNumbers) {
        this.signRelatedOrderNumbers = signRelatedOrderNumbers;
    }

    public String getSignPhoneMessageContent() {
        return signPhoneMessageContent;
    }

    public void setSignPhoneMessageContent(String signPhoneMessageContent) {
        this.signPhoneMessageContent = signPhoneMessageContent;
    }

    public Date getSignContractDateTime() {
        return signContractDateTime;
    }

    public void setSignContractDateTime(Date signContractDateTime) {
        this.signContractDateTime = signContractDateTime;
    }

    public String getSignContractRemarks() {
        return signContractRemarks;
    }

    public void setSignContractRemarks(String signContractRemarks) {
        this.signContractRemarks = signContractRemarks;
    }

    public Date getSignContractOperateDate() {
        return signContractOperateDate;
    }

    public void setSignContractOperateDate(Date signContractOperateDate) {
        this.signContractOperateDate = signContractOperateDate;
    }

    public String getSignContractOperateName() {
        return signContractOperateName;
    }

    public void setSignContractOperateName(String signContractOperateName) {
        this.signContractOperateName = signContractOperateName;
    }

    public String getSignContractRelatedOrderNumbers() {
        return signContractRelatedOrderNumbers;
    }

    public void setSignContractRelatedOrderNumbers(String signContractRelatedOrderNumbers) {
        this.signContractRelatedOrderNumbers = signContractRelatedOrderNumbers;
    }

    public String getSignContractPhoneMessageContent() {
        return signContractPhoneMessageContent;
    }

    public void setSignContractPhoneMessageContent(String signContractPhoneMessageContent) {
        this.signContractPhoneMessageContent = signContractPhoneMessageContent;
    }

    public String getReplenishSignContractRemarks() {
        return replenishSignContractRemarks;
    }

    public void setReplenishSignContractRemarks(String replenishSignContractRemarks) {
        this.replenishSignContractRemarks = replenishSignContractRemarks;
    }

    public Date getReplenishSignContractOperateDate() {
        return replenishSignContractOperateDate;
    }

    public void setReplenishSignContractOperateDate(Date replenishSignContractOperateDate) {
        this.replenishSignContractOperateDate = replenishSignContractOperateDate;
    }

    public String getReplenishSignContractOperateName() {
        return replenishSignContractOperateName;
    }

    public void setReplenishSignContractOperateName(String replenishSignContractOperateName) {
        this.replenishSignContractOperateName = replenishSignContractOperateName;
    }

    public String getReplenishSignContractRelatedOrderNumbers() {
        return replenishSignContractRelatedOrderNumbers;
    }

    public void setReplenishSignContractRelatedOrderNumbers(String replenishSignContractRelatedOrderNumbers) {
        this.replenishSignContractRelatedOrderNumbers = replenishSignContractRelatedOrderNumbers;
    }

    public String getReplenishSignContractPhoneMessageContent() {
        return replenishSignContractPhoneMessageContent;
    }

    public void setReplenishSignContractPhoneMessageContent(String replenishSignContractPhoneMessageContent) {
        this.replenishSignContractPhoneMessageContent = replenishSignContractPhoneMessageContent;
    }

    public Date getOutOfDateDate() {
        return outOfDateDate;
    }

    public void setOutOfDateDate(Date outOfDateDate) {
        this.outOfDateDate = outOfDateDate;
    }
}
