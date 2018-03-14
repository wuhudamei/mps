package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

/**
 * @ClassName: SendItemMgrDetail 
 * @Description: 派项目经理明细 VO
 * @author huhanwei 
 * @date 2017年6月21日 下午1:36:28
 */
public class SendItemMgrDetail extends DataEntity2<SendItemMgrDetail>  {
	
	/**
	 * 派项目经理明细
	 */
	private static final long serialVersionUID = 1L;
	private String storeId; //门店ID
	private String storeName; //门店名称
	
	private Date createDate;// 派单时间
	private Date beginCreateDate; // 派单时间-开始
	private Date endCreateDate; // 派单时间-结束
	
	private String operName; // 操作人
	
	private String newItemManager; // 新派项目经理
	private String oldItemManager; // 原项目经理
	
	private String projectMode; // 工程模式   1-产业模式；2-传统模式
	private String projectModeName; // 工程模式名称
	
	private String engineDepartId;//区域编号
	private String engineDepartName; // 区域
	
	private String orderNumber; // 订单编号
	private Date actualStartDate; // 实际开工日期
	
	private String communityName; // 小区名称
	private String buildNumber; // 几号楼
	private String buildUnit; // 几单元
	private String buildRoom; // 几室
	
	private String customerName; // 客户名称
	private String customerPhone; // 客户手机号
	
	private String designerName; // 设计师名称
	private String designerPhone; // 设计师手机号
	
	private String itemManager; // 订单项目经理
	private String itemManagerPhone; // 项目经理手机号
	
	private String orderInspector; // 质检员
	private String orderInspectorPhone; // 质检员手机号
	
	// excel 导出用到
	private String itemManagerId; // 员工编号（项目经理）
	private Integer star; // 星级
	private String orderCount; // 项目经理接单数
	private String orderTotalCount; // 项目经理总接单数
	private String remarks; // 备注
	private String orderId; // 订单号
	private Integer orderStop;// 是否接单，0-否，1-是
	private String dailySendSingular; //日接单数
	private String contractStartDate; //合同开工时间
	private String contractEndDate; //合同竣工时间
	private String customerAddress; // 客户地址
	private String orderAcceptArea;//接单区域
	private String month;//月份
	private String monthDispathCount;//月累计
	private String days;
	private String managerCount;

	
	
	public String getManagerCount() {
		return managerCount;
	}
	public void setManagerCount(String managerCount) {
		this.managerCount = managerCount;
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public String getDispatchDays(){
		return itemManagerId+days;
	}
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getMonthDispathCount() {
		return monthDispathCount;
	}

	public void setMonthDispathCount(String monthDispathCount) {
		this.monthDispathCount = monthDispathCount;
	}

	public String getDispatchMonth(){
		return itemManagerId+month;
	}
	
	public String getOrderAcceptArea() {
		return orderAcceptArea;
	}
	public void setOrderAcceptArea(String orderAcceptArea) {
		this.orderAcceptArea = orderAcceptArea;
	}
	public String getDailySendSingular() {
		return dailySendSingular;
	}
	public void setDailySendSingular(String dailySendSingular) {
		this.dailySendSingular = dailySendSingular;
	}
	public String getContractStartDate() {
		return contractStartDate;
	}
	public void setContractStartDate(String contractStartDate) {
		this.contractStartDate = contractStartDate;
	}
	public String getContractEndDate() {
		return contractEndDate;
	}
	public void setContractEndDate(String contractEndDate) {
		this.contractEndDate = contractEndDate;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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
	public String getOperName() {
		return operName;
	}
	public void setOperName(String operName) {
		this.operName = operName;
	}
	public String getNewItemManager() {
		return newItemManager;
	}
	public void setNewItemManager(String newItemManager) {
		this.newItemManager = newItemManager;
	}
	public String getOldItemManager() {
		return oldItemManager;
	}
	public void setOldItemManager(String oldItemManager) {
		this.oldItemManager = oldItemManager;
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
	public String getEngineDepartId() {
		return engineDepartId;
	}
	public void setEngineDepartId(String engineDepartId) {
		this.engineDepartId = engineDepartId;
	}
	public String getEngineDepartName() {
		return engineDepartName;
	}
	public void setEngineDepartName(String engineDepartName) {
		this.engineDepartName = engineDepartName;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public Date getActualStartDate() {
		return actualStartDate;
	}
	public void setActualStartDate(Date actualStartDate) {
		this.actualStartDate = actualStartDate;
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
	public String getItemManager() {
		return itemManager;
	}
	public void setItemManager(String itemManager) {
		this.itemManager = itemManager;
	}
	public String getItemManagerPhone() {
		return itemManagerPhone;
	}
	public void setItemManagerPhone(String itemManagerPhone) {
		this.itemManagerPhone = itemManagerPhone;
	}
	public String getOrderInspector() {
		return orderInspector;
	}
	public void setOrderInspector(String orderInspector) {
		this.orderInspector = orderInspector;
	}
	public String getOrderInspectorPhone() {
		return orderInspectorPhone;
	}
	public void setOrderInspectorPhone(String orderInspectorPhone) {
		this.orderInspectorPhone = orderInspectorPhone;
	}
	public String getItemManagerId() {
		return itemManagerId;
	}
	public void setItemManagerId(String itemManagerId) {
		this.itemManagerId = itemManagerId;
	}
	public Integer getStar() {
		return star;
	}
	public void setStar(Integer star) {
		this.star = star;
	}
	public String getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(String orderCount) {
		this.orderCount = orderCount;
	}
	public String getOrderTotalCount() {
		return orderTotalCount;
	}
	public void setOrderTotalCount(String orderTotalCount) {
		this.orderTotalCount = orderTotalCount;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Integer getOrderStop() {
		return orderStop;
	}
	public void setOrderStop(Integer orderStop) {
		this.orderStop = orderStop;
	}
}
