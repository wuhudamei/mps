/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import java.util.List;


import cn.damei.common.persistence.DataEntity2;

/**
 * 供应商安装单表Entity
 * @author wyb
 * @version 2017-07-13
 */
public class BizSupplierInstallBill extends DataEntity2<BizSupplierInstallBill> {
	
	private static final long serialVersionUID = 1L;
	private String installBillCode;		// 安装单号
	private Integer orderInstallPlanId;		// 订单安装计划id
	private Integer supplierId;		// 供应商id
	private Date supplierConfirmIntoDate;		// 供应商确认进场日期时间
	private Date supplierConfirmCompleteDate;		// 供应商确认完工日期时间
	private String status;		// 状态
	private String statusName;		// 状态名称
	private Date statusDatetime;		// 状态日期时间
	private Date realIntoDate;		// 实际进场日期时间
	private Date realCompleteDate;		// 实际完工日期时间
	private Date realAcceptDate;		// 确认验收日期时间
	
	private Integer installPlanId; //安装项计划id
	private Integer installBillId;		// 安装单id
	private Integer installConstructBillId;		// 施工单id
	
	
	private Date beginCreateDate;//供应商接收时间  开始
	private Date endCreateDate; //供应商接收时间  结束
	
	
	
	private Integer storeId; //门店id
	private String storeName; //门店名称
	private String detailAddress; //客户地址
	private String communityName; //小区
	private String buildNumber; //楼
	private String buildUnit; //单元
	private String buildRoom; //室
	private String customerName; //客户姓名
	private String customerPhone; //客户电话
	private Integer itemManagerId; //项目经理id
	private String itemManagerName; //项目经理名称
	private String itemManagerPhone; //项目经理电话
	private String installItemName; //安装项名称
	private String supplierConfirmRemarks; //材料部说明
	private Date planIntoDate; //计划开始日期
	private Date planCompleteDate; //计划结束日期
	private Integer employeeGroupId; //工人组id
	private String employeeGroupName; //工人组长 名称
	private String employeeGroupPhone; //工人组长 电话
	private Integer projectInstallItemId; //安装项id
	
	private	List<String> installBillStatusList = null; //安装单状态集合
	private List<Integer> supplierIdList = null; //供应商id集合
	
	
	public BizSupplierInstallBill() {
		super();
	}

	public BizSupplierInstallBill(Integer id){
		super(id);
	}

	@Length(min=0, max=100, message="安装单号长度必须介于 0 和 100 之间")
	public String getInstallBillCode() {
		return installBillCode;
	}

	public void setInstallBillCode(String installBillCode) {
		this.installBillCode = installBillCode;
	}
	
	public Integer getOrderInstallPlanId() {
		return orderInstallPlanId;
	}

	public void setOrderInstallPlanId(Integer orderInstallPlanId) {
		this.orderInstallPlanId = orderInstallPlanId;
	}
	
	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}
	
	public Date getSupplierConfirmIntoDate() {
		return supplierConfirmIntoDate;
	}

	public void setSupplierConfirmIntoDate(Date supplierConfirmIntoDate) {
		this.supplierConfirmIntoDate = supplierConfirmIntoDate;
	}
	
	public Date getSupplierConfirmCompleteDate() {
		return supplierConfirmCompleteDate;
	}

	public void setSupplierConfirmCompleteDate(Date supplierConfirmCompleteDate) {
		this.supplierConfirmCompleteDate = supplierConfirmCompleteDate;
	}
	
	@Length(min=0, max=10, message="状态长度必须介于 0 和 10 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public Date getStatusDatetime() {
		return statusDatetime;
	}

	public void setStatusDatetime(Date statusDatetime) {
		this.statusDatetime = statusDatetime;
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

	public Integer getInstallBillId() {
		return installBillId;
	}

	public void setInstallBillId(Integer installBillId) {
		this.installBillId = installBillId;
	}

	public Integer getInstallConstructBillId() {
		return installConstructBillId;
	}

	public void setInstallConstructBillId(Integer installConstructBillId) {
		this.installConstructBillId = installConstructBillId;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public Date getBeginCreateDate() {
		return beginCreateDate;
	}

	public void setBeginCreateDate(Date beginCreateDate) {
		this.beginCreateDate = beginCreateDate;
	}

	public Date getEndCreateDate() {
		return endCreateDate;
	}

	public void setEndCreateDate(Date endCreateDate) {
		this.endCreateDate = endCreateDate;
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

	public Integer getItemManagerId() {
		return itemManagerId;
	}

	public void setItemManagerId(Integer itemManagerId) {
		this.itemManagerId = itemManagerId;
	}

	public String getItemManagerName() {
		return itemManagerName;
	}

	public void setItemManagerName(String itemManagerName) {
		this.itemManagerName = itemManagerName;
	}

	public String getItemManagerPhone() {
		return itemManagerPhone;
	}

	public void setItemManagerPhone(String itemManagerPhone) {
		this.itemManagerPhone = itemManagerPhone;
	}

	public String getInstallItemName() {
		return installItemName;
	}

	public void setInstallItemName(String installItemName) {
		this.installItemName = installItemName;
	}

	public String getSupplierConfirmRemarks() {
		return supplierConfirmRemarks;
	}

	public void setSupplierConfirmRemarks(String supplierConfirmRemarks) {
		this.supplierConfirmRemarks = supplierConfirmRemarks;
	}

	public Date getPlanIntoDate() {
		return planIntoDate;
	}

	public void setPlanIntoDate(Date planIntoDate) {
		this.planIntoDate = planIntoDate;
	}

	public Date getPlanCompleteDate() {
		return planCompleteDate;
	}

	public void setPlanCompleteDate(Date planCompleteDate) {
		this.planCompleteDate = planCompleteDate;
	}

	public Integer getEmployeeGroupId() {
		return employeeGroupId;
	}

	public void setEmployeeGroupId(Integer employeeGroupId) {
		this.employeeGroupId = employeeGroupId;
	}

	public String getEmployeeGroupName() {
		return employeeGroupName;
	}

	public void setEmployeeGroupName(String employeeGroupName) {
		this.employeeGroupName = employeeGroupName;
	}

	public String getEmployeeGroupPhone() {
		return employeeGroupPhone;
	}

	public void setEmployeeGroupPhone(String employeeGroupPhone) {
		this.employeeGroupPhone = employeeGroupPhone;
	}

	public Integer getProjectInstallItemId() {
		return projectInstallItemId;
	}

	public void setProjectInstallItemId(Integer projectInstallItemId) {
		this.projectInstallItemId = projectInstallItemId;
	}

	public List<String> getInstallBillStatusList() {
		return installBillStatusList;
	}

	public void setInstallBillStatusList(List<String> installBillStatusList) {
		this.installBillStatusList = installBillStatusList;
	}

	public List<Integer> getSupplierIdList() {
		return supplierIdList;
	}

	public void setSupplierIdList(List<Integer> supplierIdList) {
		this.supplierIdList = supplierIdList;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public Integer getInstallPlanId() {
		return installPlanId;
	}

	public void setInstallPlanId(Integer installPlanId) {
		this.installPlanId = installPlanId;
	}
	
	
}