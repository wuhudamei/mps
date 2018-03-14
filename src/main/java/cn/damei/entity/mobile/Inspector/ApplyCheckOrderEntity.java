package cn.damei.entity.mobile.Inspector;

import cn.damei.common.constantUtils.QcBillConstantUtil;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by joseph on 2017/4/22.
 */
public class ApplyCheckOrderEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private String customerInfo;
    private String customerPhone;
    private String managerInfo;//name-phone
    private Date actualStartDate;
    private Integer orderId;
    private Integer pqcId;
  private String text;
    private List<ApplyCheckDetailEntity> checkDetailList;
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }



    public Integer getPqcId() {
        return pqcId;
    }

    public void setPqcId(Integer pqcId) {
        this.pqcId = pqcId;
    }

    private String pqcStatus= QcBillConstantUtil.QC_BILL_CHECK_STATUS_30;
    private String isRecheck="0";
    private String qcBillType="1";



    public String getPqcStatus() {
        return pqcStatus;
    }

    public void setPqcStatus(String pqcStatus) {
        this.pqcStatus = pqcStatus;
    }

    public String getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(String customerInfo) {
        this.customerInfo = customerInfo;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getManagerInfo() {
        return managerInfo;
    }

    public void setManagerInfo(String managerInfo) {
        this.managerInfo = managerInfo;
    }

    public Date getActualStartDate() {
        return actualStartDate;
    }

    public void setActualStartDate(Date actualStartDate) {
        this.actualStartDate = actualStartDate;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public List<ApplyCheckDetailEntity> getCheckDetailList() {
        return checkDetailList;
    }

    public void setCheckDetailList(List<ApplyCheckDetailEntity> checkDetailList) {
        this.checkDetailList = checkDetailList;
    }
}
