
package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;


public class InstallHistoryData extends DataEntity2<InstallHistoryData> {

    private static final long serialVersionUID = 1L;
    
    private String installItemName;
    private String installStatus;
    private String installStatusName;
    private String installItemSequence;
    private String supplierConfirmRemarks;
    
    private Date realAcceptDate;
    private Date realIntoDate;
    private Date realCompleteDate;
    
    private Date applyIntoCreateDatetime;
    
    private Date applyIntoDate;
    
    private Date supplierConfirmIntoDate;
    
    private Integer orderId;
    
    private Integer statusTwoCount;
    private Integer statusThreeCount;
    private Integer statusFourCount;
    
    private Integer itemManagerId;
    private String applyIntoDateString;
    private String supplierConfirmIntoDateString;
    
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