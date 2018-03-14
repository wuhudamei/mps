/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import java.util.Date;
import java.util.List;

import cn.damei.common.persistence.DataEntity2;

/**
 * 主材安装申请Entity
 * 
 * @author haven
 * @version 2016-09-05
 */
public class EnginInstallNew extends DataEntity2<EnginInstallNew> {

    private static final long serialVersionUID = 1L;
    
    private String isOn; //是否启用（安装项名称）
    private String installMode; // 安装模式
    private String installModeName; // 安装模式 名称
    private String installItemName; // 安装项名称
    private Integer projectInstallItemId; // 安装项模板id
    private Integer projectInstallItemIdStop; // 安装项模板id(停用的)
    private String installStatus; // 安装项状态
    private String installStatusName; // 安装项状态名称
    private String installItemSequence; // 安装项顺序
    private String isUrgeReply; // 是否回复
    private String supplierConfirmRemarks; // 下达供应商 说明
    private Integer sendSupplierId; //供应商id
    
    private String rejectedRemarks; // 驳回 备注
    private String rejectedId; // 驳回 类型
    private String rejectedIdName; // 驳回 类型 名称
     
    private Date realIntoDate; // 实际进场日期
    private Date realCompleteDate; // 实际完工日期
    
    private Date applyIntoCreateDatetime; // 申请时间
    private Date beginApplyIntoCreateDatetime; // 申请时间 开始
    private Date endApplyIntoCreateDatetime; // 申请时间 结束
    
    private Date applyIntoDate; // 期望进场日期
    private Date beginApplyIntoDate; // 期望进场日期 开始
    private Date endApplyIntoDate; // 期望进场日期 结束
    
    private Date supplierConfirmIntoDate; // 供应商确认日期
    private Date beginSupplierConfirmIntoDate; // 供应商确认日期 开始
    private Date endSupplierConfirmIntoDate; // 供应商确认日期 结束
    
    private Date supplierConfirmCompleteDate; // 供应商确认完工日期
    
    private Date supplierOperaterDate; // 转供应商时间
    private Date beginSupplierOperaterDate; // 转供应商时间  开始
    private Date endSupplierOperaterDate; // 转供应商时间 结束
    
    private Date rejectedOperaterDate; // 驳回时间
    private Date beginRejectedOperaterDate; // 驳回时间  开始
    private Date endRejectedOperaterDate; // 驳回时间 结束
    
    private String operator; //操作人
    private	List<String> installStatusList = null; //安装项状态集合
    private	List<Integer> projectInstallItemIdList = null; //安装项id集合
    
    private Integer orderId; // 订单id
    private String orderNumber; // 订单编号
    private Integer storeId; // 门店
    private String storeName; // 门店名称
    private String projectMode; // 工程模式 -- '1-产业模式；2-传统模式；3-全部
    private String projectModeName; //工程模式名称
    
    private String communityName; //小区
    private String buildNumber; //楼
    private String buildUnit; //单元
    private String buildRoom; //室
    
    private String customerName; // 客户
    private String customerPhone; // 客户手机号
    private Integer managerId; // 项目经理id
    private String managerName; // 项目经理
    private String managerPhone; // 项目经理手机号
    private String designerName;  //设计师
    private String designerPhone; //设计师电话
   
    private String urgeCount;         //接单员
    
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