/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

/**
 * 主材安装申请Entity
 * 
 * @author haven
 * @version 2016-09-05
 */
public class InstallHistoryData extends DataEntity2<InstallHistoryData> {

    private static final long serialVersionUID = 1L;
    
    private String installItemName; // 安装项名称
    private String installStatus; // 安装项状态
    private String installStatusName; // 安装项状态名称
    private String installItemSequence; // 安装项顺序
    private String supplierConfirmRemarks; // 下达供应商 说明
    
    private Date realAcceptDate; // 实际验收时间
    private Date realIntoDate; // 实际进场日期
    private Date realCompleteDate; // 实际完工日期
    
    private Date applyIntoCreateDatetime; // 申请时间
    
    private Date applyIntoDate; // 期望进场日期
    
    private Date supplierConfirmIntoDate; // 供应商确认日期
    
    private Integer orderId; // 订单id
    
    private Integer statusTwoCount; // 申请 的数量
    private Integer statusThreeCount; // 下达供应商  的数量
    private Integer statusFourCount; // 验收  的数量
    
    private Integer itemManagerId;//项目经理id
    private String applyIntoDateString; // 期望进场日期 字符串
    private String supplierConfirmIntoDateString; // 供应商确认日期 字符串
    
    public InstallHistoryData() {
        super();
    }

    public InstallHistoryData(Integer id) {
        super(id);
    }

	public String getInstallItemName() {
		return installItemName;
	}

	public void setInstallItemName(String installItemName) {
		this.installItemName = installItemName;
	}

	public String getInstallStatus() {
		return installStatus;
	}

	public void setInstallStatus(String installStatus) {
		this.installStatus = installStatus;
	}

	public String getInstallStatusName() {
		return installStatusName;
	}

	public void setInstallStatusName(String installStatusName) {
		this.installStatusName = installStatusName;
	}

	public String getInstallItemSequence() {
		return installItemSequence;
	}

	public void setInstallItemSequence(String installItemSequence) {
		this.installItemSequence = installItemSequence;
	}

	public String getSupplierConfirmRemarks() {
		return supplierConfirmRemarks;
	}

	public void setSupplierConfirmRemarks(String supplierConfirmRemarks) {
		this.supplierConfirmRemarks = supplierConfirmRemarks;
	}

	public Date getRealAcceptDate() {
		return realAcceptDate;
	}

	public void setRealAcceptDate(Date realAcceptDate) {
		this.realAcceptDate = realAcceptDate;
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

	public Date getApplyIntoCreateDatetime() {
		return applyIntoCreateDatetime;
	}

	public void setApplyIntoCreateDatetime(Date applyIntoCreateDatetime) {
		this.applyIntoCreateDatetime = applyIntoCreateDatetime;
	}

	public Date getApplyIntoDate() {
		return applyIntoDate;
	}

	public void setApplyIntoDate(Date applyIntoDate) {
		this.applyIntoDate = applyIntoDate;
	}

	public Date getSupplierConfirmIntoDate() {
		return supplierConfirmIntoDate;
	}

	public void setSupplierConfirmIntoDate(Date supplierConfirmIntoDate) {
		this.supplierConfirmIntoDate = supplierConfirmIntoDate;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getStatusTwoCount() {
		return statusTwoCount;
	}

	public void setStatusTwoCount(Integer statusTwoCount) {
		this.statusTwoCount = statusTwoCount;
	}

	public Integer getStatusThreeCount() {
		return statusThreeCount;
	}

	public void setStatusThreeCount(Integer statusThreeCount) {
		this.statusThreeCount = statusThreeCount;
	}

	public Integer getStatusFourCount() {
		return statusFourCount;
	}

	public void setStatusFourCount(Integer statusFourCount) {
		this.statusFourCount = statusFourCount;
	}

	public Integer getItemManagerId() {
		return itemManagerId;
	}

	public void setItemManagerId(Integer itemManagerId) {
		this.itemManagerId = itemManagerId;
	}

	public String getApplyIntoDateString() {
		return applyIntoDateString;
	}

	public void setApplyIntoDateString(String applyIntoDateString) {
		this.applyIntoDateString = applyIntoDateString;
	}

	public String getSupplierConfirmIntoDateString() {
		return supplierConfirmIntoDateString;
	}

	public void setSupplierConfirmIntoDateString(String supplierConfirmIntoDateString) {
		this.supplierConfirmIntoDateString = supplierConfirmIntoDateString;
	}
    
    


   
}