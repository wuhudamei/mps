
package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;


public class BizOrderScrap extends DataEntity2<BizOrderScrap> {

	private static final long serialVersionUID = 1L;

	private String orderId;
	private Integer storeId;
	private String storeName;
	private Integer enginDepartId;
	private String enginDepartName;
	private String projectMode;
	private String projectModeName;

	private String orderNumber;

	private String communityName;
	private String buildNumber;
	private String buildUnit;
	private String buildRoom;

	private String customerName;
	private String customerPhone;

	private Integer itemManagerId;
	private String itemManager;

	private Integer orderInspectorId;
	private String orderInspector;

	private String designerName;
	private String designerPhone;

	private String orderStatusNumber;
	private String orderStatusDescription;

	private Date signContractDate;
	private Date contractStartDate;
	private Date contractEndDate;
	private Date getOrderDatetime;

	private String istorefreshprocessdata;
	private String isScrap;
	private Integer scrapOperatorEmployeeId;
	private String scrapOperatorEmployeeName;
	private Date scrapDatetime;
	private String scrapDescribe;
	private Date qcCompleteAcceptCheckDatetime;
	private String scrapReasonType;
	private String scrapReasonTypeName;

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