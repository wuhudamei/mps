/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

/**
 * 订单作废Entity
 */
public class BizOrderScrap extends DataEntity2<BizOrderScrap> {

	private static final long serialVersionUID = 1L;

	private String orderId; // 订单id
	private Integer storeId; // 门店id
	private String storeName; // 门店名称
	private Integer enginDepartId; // 区域id
	private String enginDepartName; // 区域名称
	private String projectMode; // 工程模式
	private String projectModeName; // 工程模式名称

	private String orderNumber; // 订单编号

	private String communityName; // 小区名称
	private String buildNumber; // 几号楼
	private String buildUnit; // 几单元
	private String buildRoom; // 哪一室

	private String customerName; // 客户姓名
	private String customerPhone; // 客户电话

	private Integer itemManagerId; // 项目经理id
	private String itemManager; // 项目经理

	private Integer orderInspectorId; // 订单质检员
	private String orderInspector; // 订单质检员

	private String designerName; // 设计师姓名
	private String designerPhone; // 设计师电话

	private String orderStatusNumber; // 订单状态码 创建订单成功默认状态码 105
	private String orderStatusDescription; // 订单状态码详情 默认详情 确认订单 状态码105

	private Date signContractDate; // 签约日期
	private Date contractStartDate; // 合同开工日期
	private Date contractEndDate; // 合同竣工日期
	private Date getOrderDatetime; // 接单日期

	private String istorefreshprocessdata; // 大看版
	private String isScrap; // 是否作废
	private Integer scrapOperatorEmployeeId; // 作废操作人员工id
	private String scrapOperatorEmployeeName; // 作废操作人员工/名称
	private Date scrapDatetime; // 作废日期时间
	private String scrapDescribe; // 作废说明
	private Date qcCompleteAcceptCheckDatetime; // 质检竣工验收日期时间
	private String scrapReasonType; // 作废原因类型
	private String scrapReasonTypeName; // 作废原因类型/名称

	public String getFlag() {
		char charAt = orderNumber.charAt(0);
		if(charAt == 'Z'){
			return "0";
		}
		return "1";
	}
	public BizOrderScrap() {
		super();
	}

	public BizOrderScrap(Integer id) {
		super(id);
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
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

	public Integer getEnginDepartId() {
		return enginDepartId;
	}

	public void setEnginDepartId(Integer enginDepartId) {
		this.enginDepartId = enginDepartId;
	}

	public String getEnginDepartName() {
		return enginDepartName;
	}

	public void setEnginDepartName(String enginDepartName) {
		this.enginDepartName = enginDepartName;
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

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
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

	public String getItemManager() {
		return itemManager;
	}

	public void setItemManager(String itemManager) {
		this.itemManager = itemManager;
	}

	public Integer getOrderInspectorId() {
		return orderInspectorId;
	}

	public void setOrderInspectorId(Integer orderInspectorId) {
		this.orderInspectorId = orderInspectorId;
	}

	public String getOrderInspector() {
		return orderInspector;
	}

	public void setOrderInspector(String orderInspector) {
		this.orderInspector = orderInspector;
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

	public String getOrderStatusNumber() {
		return orderStatusNumber;
	}

	public void setOrderStatusNumber(String orderStatusNumber) {
		this.orderStatusNumber = orderStatusNumber;
	}

	public String getOrderStatusDescription() {
		return orderStatusDescription;
	}

	public void setOrderStatusDescription(String orderStatusDescription) {
		this.orderStatusDescription = orderStatusDescription;
	}

	public Date getSignContractDate() {
		return signContractDate;
	}

	public void setSignContractDate(Date signContractDate) {
		this.signContractDate = signContractDate;
	}

	public Date getContractStartDate() {
		return contractStartDate;
	}

	public void setContractStartDate(Date contractStartDate) {
		this.contractStartDate = contractStartDate;
	}

	public Date getContractEndDate() {
		return contractEndDate;
	}

	public void setContractEndDate(Date contractEndDate) {
		this.contractEndDate = contractEndDate;
	}

	public Date getGetOrderDatetime() {
		return getOrderDatetime;
	}

	public void setGetOrderDatetime(Date getOrderDatetime) {
		this.getOrderDatetime = getOrderDatetime;
	}

	public String getIsScrap() {
		return isScrap;
	}

	public void setIsScrap(String isScrap) {
		this.isScrap = isScrap;
	}

	public Integer getScrapOperatorEmployeeId() {
		return scrapOperatorEmployeeId;
	}

	public void setScrapOperatorEmployeeId(Integer scrapOperatorEmployeeId) {
		this.scrapOperatorEmployeeId = scrapOperatorEmployeeId;
	}

	public Date getScrapDatetime() {
		return scrapDatetime;
	}

	public void setScrapDatetime(Date scrapDatetime) {
		this.scrapDatetime = scrapDatetime;
	}

	public String getScrapDescribe() {
		return scrapDescribe;
	}

	public void setScrapDescribe(String scrapDescribe) {
		this.scrapDescribe = scrapDescribe;
	}

	public Date getQcCompleteAcceptCheckDatetime() {
		return qcCompleteAcceptCheckDatetime;
	}

	public void setQcCompleteAcceptCheckDatetime(Date qcCompleteAcceptCheckDatetime) {
		this.qcCompleteAcceptCheckDatetime = qcCompleteAcceptCheckDatetime;
	}

	public String getScrapReasonType() {
		return scrapReasonType;
	}

	public void setScrapReasonType(String scrapReasonType) {
		this.scrapReasonType = scrapReasonType;
	}

	public String getScrapOperatorEmployeeName() {
		return scrapOperatorEmployeeName;
	}

	public void setScrapOperatorEmployeeName(String scrapOperatorEmployeeName) {
		this.scrapOperatorEmployeeName = scrapOperatorEmployeeName;
	}

	public String getScrapReasonTypeName() {
		return scrapReasonTypeName;
	}

	public void setScrapReasonTypeName(String scrapReasonTypeName) {
		this.scrapReasonTypeName = scrapReasonTypeName;
	}

	public String getIstorefreshprocessdata() {
		return istorefreshprocessdata;
	}

	public void setIstorefreshprocessdata(String istorefreshprocessdata) {
		this.istorefreshprocessdata = istorefreshprocessdata;
	}

}