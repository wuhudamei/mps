
package cn.damei.entity.modules;

import java.util.Date;
import java.util.List;

import cn.damei.common.persistence.DataEntity2;


public class EnginInstallNew extends DataEntity2<EnginInstallNew> {

    private static final long serialVersionUID = 1L;
    
    private String isOn;
    private String installMode;
    private String installModeName;
    private String installItemName;
    private Integer projectInstallItemId;
    private Integer projectInstallItemIdStop;
    private String installStatus;
    private String installStatusName;
    private String installItemSequence;
    private String isUrgeReply;
    private String supplierConfirmRemarks;
    private Integer sendSupplierId;
    
    private String rejectedRemarks;
    private String rejectedId;
    private String rejectedIdName;
     
    private Date realIntoDate;
    private Date realCompleteDate;
    
    private Date applyIntoCreateDatetime;
    private Date beginApplyIntoCreateDatetime;
    private Date endApplyIntoCreateDatetime;
    
    private Date applyIntoDate;
    private Date beginApplyIntoDate;
    private Date endApplyIntoDate;
    
    private Date supplierConfirmIntoDate;
    private Date beginSupplierConfirmIntoDate;
    private Date endSupplierConfirmIntoDate;
    
    private Date supplierConfirmCompleteDate;
    
    private Date supplierOperaterDate;
    private Date beginSupplierOperaterDate;
    private Date endSupplierOperaterDate;
    
    private Date rejectedOperaterDate;
    private Date beginRejectedOperaterDate;
    private Date endRejectedOperaterDate;
    
    private String operator;
    private	List<String> installStatusList = null;
    private	List<Integer> projectInstallItemIdList = null;
    
    private Integer orderId;
    private String orderNumber;
    private Integer storeId;
    private String storeName;
    private String projectMode;
    private String projectModeName;
    
    private String communityName;
    private String buildNumber;
    private String buildUnit;
    private String buildRoom;
    
    private String customerName;
    private String customerPhone;
    private Integer managerId;
    private String managerName;
    private String managerPhone;
    private String designerName;
    private String designerPhone;
   
    private String urgeCount;
    
    public EnginInstallNew() {
        super();
    }

    public EnginInstallNew(Integer id) {
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

	public String getIsUrgeReply() {
		return isUrgeReply;
	}

	public void setIsUrgeReply(String isUrgeReply) {
		this.isUrgeReply = isUrgeReply;
	}

	public Date getApplyIntoCreateDatetime() {
		return applyIntoCreateDatetime;
	}

	public void setApplyIntoCreateDatetime(Date applyIntoCreateDatetime) {
		this.applyIntoCreateDatetime = applyIntoCreateDatetime;
	}

	public Date getBeginApplyIntoCreateDatetime() {
		return beginApplyIntoCreateDatetime;
	}

	public void setBeginApplyIntoCreateDatetime(Date beginApplyIntoCreateDatetime) {
		this.beginApplyIntoCreateDatetime = beginApplyIntoCreateDatetime;
	}

	public Date getEndApplyIntoCreateDatetime() {
		return endApplyIntoCreateDatetime;
	}

	public void setEndApplyIntoCreateDatetime(Date endApplyIntoCreateDatetime) {
		this.endApplyIntoCreateDatetime = endApplyIntoCreateDatetime;
	}

	public Date getApplyIntoDate() {
		return applyIntoDate;
	}

	public void setApplyIntoDate(Date applyIntoDate) {
		this.applyIntoDate = applyIntoDate;
	}

	public Date getBeginApplyIntoDate() {
		return beginApplyIntoDate;
	}

	public void setBeginApplyIntoDate(Date beginApplyIntoDate) {
		this.beginApplyIntoDate = beginApplyIntoDate;
	}

	public Date getEndApplyIntoDate() {
		return endApplyIntoDate;
	}

	public void setEndApplyIntoDate(Date endApplyIntoDate) {
		this.endApplyIntoDate = endApplyIntoDate;
	}

	public List<String> getInstallStatusList() {
		return installStatusList;
	}

	public void setInstallStatusList(List<String> installStatusList) {
		this.installStatusList = installStatusList;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}

	public String getProjectModeName() {
		return projectModeName;
	}

	public void setProjectModeName(String projectModeName) {
		this.projectModeName = projectModeName;
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

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getManagerPhone() {
		return managerPhone;
	}

	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
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

	public String getUrgeCount() {
		return urgeCount;
	}

	public void setUrgeCount(String urgeCount) {
		this.urgeCount = urgeCount;
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

	public Date getSupplierConfirmIntoDate() {
		return supplierConfirmIntoDate;
	}

	public void setSupplierConfirmIntoDate(Date supplierConfirmIntoDate) {
		this.supplierConfirmIntoDate = supplierConfirmIntoDate;
	}

	public Date getBeginSupplierConfirmIntoDate() {
		return beginSupplierConfirmIntoDate;
	}

	public void setBeginSupplierConfirmIntoDate(Date beginSupplierConfirmIntoDate) {
		this.beginSupplierConfirmIntoDate = beginSupplierConfirmIntoDate;
	}

	public Date getEndSupplierConfirmIntoDate() {
		return endSupplierConfirmIntoDate;
	}

	public void setEndSupplierConfirmIntoDate(Date endSupplierConfirmIntoDate) {
		this.endSupplierConfirmIntoDate = endSupplierConfirmIntoDate;
	}

	public String getSupplierConfirmRemarks() {
		return supplierConfirmRemarks;
	}

	public void setSupplierConfirmRemarks(String supplierConfirmRemarks) {
		this.supplierConfirmRemarks = supplierConfirmRemarks;
	}

	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	public Date getSupplierOperaterDate() {
		return supplierOperaterDate;
	}

	public void setSupplierOperaterDate(Date supplierOperaterDate) {
		this.supplierOperaterDate = supplierOperaterDate;
	}

	public Date getBeginSupplierOperaterDate() {
		return beginSupplierOperaterDate;
	}

	public void setBeginSupplierOperaterDate(Date beginSupplierOperaterDate) {
		this.beginSupplierOperaterDate = beginSupplierOperaterDate;
	}

	public Date getEndSupplierOperaterDate() {
		return endSupplierOperaterDate;
	}

	public void setEndSupplierOperaterDate(Date endSupplierOperaterDate) {
		this.endSupplierOperaterDate = endSupplierOperaterDate;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getRejectedRemarks() {
		return rejectedRemarks;
	}

	public void setRejectedRemarks(String rejectedRemarks) {
		this.rejectedRemarks = rejectedRemarks;
	}

	public String getRejectedId() {
		return rejectedId;
	}

	public void setRejectedId(String rejectedId) {
		this.rejectedId = rejectedId;
	}

	public String getRejectedIdName() {
		return rejectedIdName;
	}

	public void setRejectedIdName(String rejectedIdName) {
		this.rejectedIdName = rejectedIdName;
	}

	public Date getRejectedOperaterDate() {
		return rejectedOperaterDate;
	}

	public void setRejectedOperaterDate(Date rejectedOperaterDate) {
		this.rejectedOperaterDate = rejectedOperaterDate;
	}

	public Date getBeginRejectedOperaterDate() {
		return beginRejectedOperaterDate;
	}

	public void setBeginRejectedOperaterDate(Date beginRejectedOperaterDate) {
		this.beginRejectedOperaterDate = beginRejectedOperaterDate;
	}

	public Date getEndRejectedOperaterDate() {
		return endRejectedOperaterDate;
	}

	public void setEndRejectedOperaterDate(Date endRejectedOperaterDate) {
		this.endRejectedOperaterDate = endRejectedOperaterDate;
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

	public String getInstallMode() {
		return installMode;
	}

	public void setInstallMode(String installMode) {
		this.installMode = installMode;
	}

	public String getInstallModeName() {
		return installModeName;
	}

	public void setInstallModeName(String installModeName) {
		this.installModeName = installModeName;
	}

	public Integer getSendSupplierId() {
		return sendSupplierId;
	}

	public void setSendSupplierId(Integer sendSupplierId) {
		this.sendSupplierId = sendSupplierId;
	}

	public Integer getProjectInstallItemId() {
		return projectInstallItemId;
	}

	public void setProjectInstallItemId(Integer projectInstallItemId) {
		this.projectInstallItemId = projectInstallItemId;
	}

	public Date getSupplierConfirmCompleteDate() {
		return supplierConfirmCompleteDate;
	}

	public void setSupplierConfirmCompleteDate(Date supplierConfirmCompleteDate) {
		this.supplierConfirmCompleteDate = supplierConfirmCompleteDate;
	}

	public String getIsOn() {
		return isOn;
	}

	public void setIsOn(String isOn) {
		this.isOn = isOn;
	}

	public Integer getProjectInstallItemIdStop() {
		return projectInstallItemIdStop;
	}

	public void setProjectInstallItemIdStop(Integer projectInstallItemIdStop) {
		this.projectInstallItemIdStop = projectInstallItemIdStop;
	}

	public List<Integer> getProjectInstallItemIdList() {
		return projectInstallItemIdList;
	}

	public void setProjectInstallItemIdList(List<Integer> projectInstallItemIdList) {
		this.projectInstallItemIdList = projectInstallItemIdList;
	}
    
    

   
}