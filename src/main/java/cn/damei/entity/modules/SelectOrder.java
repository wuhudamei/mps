
package cn.damei.entity.modules;

import java.util.Date;
import java.util.List;


public class SelectOrder extends DataAuthorityEntitySelect {
	
	private static final long serialVersionUID = 1L;
	private Integer alreadyDistributeCount;
	public Integer getAlreadyDistributeCount() {
		return alreadyDistributeCount;
	}
	public void setAlreadyDistributeCount(Integer alreadyDistributeCount) {
		this.alreadyDistributeCount = alreadyDistributeCount;
	}
	private Integer doNow;
	public Integer getDoNow() {
		return doNow;
	}
	public void setDoNow(Integer doNow) {
		this.doNow = doNow;
	}
	private Integer totalCount;
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	private Integer orderId;
	private String storeId;
	
	private String orderNumber;
	private String contractNumber;
	
	private String customerType;
	private String customerDescription;
	
	private String orderStatusNumber;
	private String orderStatusDescription;
	private String orderTaskPackStatus;
	private	List<String> orderStatusList = null;
	
	private String communityName;
	private String buildNumber;
	private String buildUnit;
	private String buildRoom;
	private String mapCoordinate;
	private String lng;
	private String lat; 
	
	private String customerAddress;
	private String province;
	private String city;
	private String county ;
	private String detailAddress;
	private String  acceptArea;
	private Integer acceptAreaId;
	
	private String saleType;
	private String area;
	private String buildType;
	private String houseType;
	private String houseIsNew;
	private String isElevator;
	private String coveredArea;
	private String contractArea;
	private Integer contractTime;
	
	private String customerName;
	private String customerPhone;
	private String cusManager;
	
	private Integer itemManagerId;
	private String itemManager;
	private String itemManagerPhone;
	private Integer itemManagerStar;
	private Integer itemManagerBuildingCount;
	
	private Integer orderInspectorId;
	private String orderInspector;
	private String orderInspectorPhone;
	
	private String designerName;
	private String designerPhone;
	
	private String orderReporterName;
	private String orderReporterPhone;
	
	private String serviceName;
	private String servicePhone;
	
	private Date contractStartDate;
	private Date beginContractStartDate;
	private Date endContractStartDate;
	
	private Date contractEndDate;
	private Date beginContractEndDate;
	private Date endContractEndDate;
	
	private Date actualStartDate;
	private Date beginActualStartDate;
	private Date endActualStartDate;
	
	private Date actualEndDate;
	private Date beginActualEndDate;
	private Date endActualEndDate;
	
	private Date signContractDate;
	private Date beginSignContractDate;
	private Date endSignContractDate;
	
	private Date getOrderDatetime;
	private Date beginGetOrderDatetime;
	private Date endGetOrderDatetime;
	
	private Date orderDistributeLogDate;
	private Date beginOrderDistributeLogDate;
	private Date endOrderDistributeLogDate;
	
	private Date beginCreateDate;
	private Date endCreateDate;
	private String projectMode;
	
	private Integer engineDepartId;
	private String engineDepartName;
	
	private Integer beforeSiteCount;
	private Integer NowSiteCount;
	
	
	private List<String> phones;
	private String flag;
	
	
	
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public List<String> getPhones() {
		return phones;
	}
	public void setPhones(List<String> phones) {
		this.phones = phones;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getProjectMode() {
		return projectMode;
	}
	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getContractNumber() {
		return contractNumber;
	}
	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	public String getCustomerDescription() {
		return customerDescription;
	}
	public void setCustomerDescription(String customerDescription) {
		this.customerDescription = customerDescription;
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
	public String getOrderTaskPackStatus() {
		return orderTaskPackStatus;
	}
	public void setOrderTaskPackStatus(String orderTaskPackStatus) {
		this.orderTaskPackStatus = orderTaskPackStatus;
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
	public String getMapCoordinate() {
		return mapCoordinate;
	}
	public void setMapCoordinate(String mapCoordinate) {
		this.mapCoordinate = mapCoordinate;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getDetailAddress() {
		return detailAddress;
	}
	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}
	public String getAcceptArea() {
		return acceptArea;
	}
	public void setAcceptArea(String acceptArea) {
		this.acceptArea = acceptArea;
	}
	public Integer getAcceptAreaId() {
		return acceptAreaId;
	}
	public void setAcceptAreaId(Integer acceptAreaId) {
		this.acceptAreaId = acceptAreaId;
	}
	public String getSaleType() {
		return saleType;
	}
	public void setSaleType(String saleType) {
		this.saleType = saleType;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getBuildType() {
		return buildType;
	}
	public void setBuildType(String buildType) {
		this.buildType = buildType;
	}
	public String getHouseType() {
		return houseType;
	}
	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}
	public String getHouseIsNew() {
		return houseIsNew;
	}
	public void setHouseIsNew(String houseIsNew) {
		this.houseIsNew = houseIsNew;
	}
	public String getIsElevator() {
		return isElevator;
	}
	public void setIsElevator(String isElevator) {
		this.isElevator = isElevator;
	}
	public String getCoveredArea() {
		return coveredArea;
	}
	public void setCoveredArea(String coveredArea) {
		this.coveredArea = coveredArea;
	}
	public String getContractArea() {
		return contractArea;
	}
	public void setContractArea(String contractArea) {
		this.contractArea = contractArea;
	}
	public Integer getContractTime() {
		return contractTime;
	}
	public void setContractTime(Integer contractTime) {
		this.contractTime = contractTime;
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
	public String getCusManager() {
		return cusManager;
	}
	public void setCusManager(String cusManager) {
		this.cusManager = cusManager;
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
	public String getItemManagerPhone() {
		return itemManagerPhone;
	}
	public void setItemManagerPhone(String itemManagerPhone) {
		this.itemManagerPhone = itemManagerPhone;
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
	public String getOrderInspectorPhone() {
		return orderInspectorPhone;
	}
	public void setOrderInspectorPhone(String orderInspectorPhone) {
		this.orderInspectorPhone = orderInspectorPhone;
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
	public String getOrderReporterName() {
		return orderReporterName;
	}
	public void setOrderReporterName(String orderReporterName) {
		this.orderReporterName = orderReporterName;
	}
	public String getOrderReporterPhone() {
		return orderReporterPhone;
	}
	public void setOrderReporterPhone(String orderReporterPhone) {
		this.orderReporterPhone = orderReporterPhone;
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
	public Date getContractStartDate() {
		return contractStartDate;
	}
	public void setContractStartDate(Date contractStartDate) {
		this.contractStartDate = contractStartDate;
	}
	public Date getBeginContractStartDate() {
		return beginContractStartDate;
	}
	public void setBeginContractStartDate(Date beginContractStartDate) {
		this.beginContractStartDate = beginContractStartDate;
	}
	public Date getEndContractStartDate() {
		return endContractStartDate;
	}
	public void setEndContractStartDate(Date endContractStartDate) {
		this.endContractStartDate = endContractStartDate;
	}
	public Date getContractEndDate() {
		return contractEndDate;
	}
	public void setContractEndDate(Date contractEndDate) {
		this.contractEndDate = contractEndDate;
	}
	public Date getBeginContractEndDate() {
		return beginContractEndDate;
	}
	public void setBeginContractEndDate(Date beginContractEndDate) {
		this.beginContractEndDate = beginContractEndDate;
	}
	public Date getEndContractEndDate() {
		return endContractEndDate;
	}
	public void setEndContractEndDate(Date endContractEndDate) {
		this.endContractEndDate = endContractEndDate;
	}
	public Date getActualStartDate() {
		return actualStartDate;
	}
	public void setActualStartDate(Date actualStartDate) {
		this.actualStartDate = actualStartDate;
	}
	public Date getBeginActualStartDate() {
		return beginActualStartDate;
	}
	public void setBeginActualStartDate(Date beginActualStartDate) {
		this.beginActualStartDate = beginActualStartDate;
	}
	public Date getEndActualStartDate() {
		return endActualStartDate;
	}
	public void setEndActualStartDate(Date endActualStartDate) {
		this.endActualStartDate = endActualStartDate;
	}
	public Date getActualEndDate() {
		return actualEndDate;
	}
	public void setActualEndDate(Date actualEndDate) {
		this.actualEndDate = actualEndDate;
	}
	public Date getBeginActualEndDate() {
		return beginActualEndDate;
	}
	public void setBeginActualEndDate(Date beginActualEndDate) {
		this.beginActualEndDate = beginActualEndDate;
	}
	public Date getEndActualEndDate() {
		return endActualEndDate;
	}
	public void setEndActualEndDate(Date endActualEndDate) {
		this.endActualEndDate = endActualEndDate;
	}
	public Date getSignContractDate() {
		return signContractDate;
	}
	public void setSignContractDate(Date signContractDate) {
		this.signContractDate = signContractDate;
	}
	public Date getBeginSignContractDate() {
		return beginSignContractDate;
	}
	public void setBeginSignContractDate(Date beginSignContractDate) {
		this.beginSignContractDate = beginSignContractDate;
	}
	public Date getEndSignContractDate() {
		return endSignContractDate;
	}
	public void setEndSignContractDate(Date endSignContractDate) {
		this.endSignContractDate = endSignContractDate;
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
	public List<String> getOrderStatusList() {
		return orderStatusList;
	}
	public void setOrderStatusList(List<String> orderStatusList) {
		this.orderStatusList = orderStatusList;
	}
	public Integer getItemManagerStar() {
		return itemManagerStar;
	}
	public void setItemManagerStar(Integer itemManagerStar) {
		this.itemManagerStar = itemManagerStar;
	}
	public Integer getEngineDepartId() {
		return engineDepartId;
	}
	public void setEngineDepartId(Integer engineDepartId) {
		this.engineDepartId = engineDepartId;
	}
	public String getEngineDepartName() {
		return engineDepartName;
	}
	public void setEngineDepartName(String engineDepartName) {
		this.engineDepartName = engineDepartName;
	}
	public Integer getBeforeSiteCount() {
		return beforeSiteCount;
	}
	public void setBeforeSiteCount(Integer beforeSiteCount) {
		this.beforeSiteCount = beforeSiteCount;
	}
	public Integer getNowSiteCount() {
		return NowSiteCount;
	}
	public void setNowSiteCount(Integer nowSiteCount) {
		NowSiteCount = nowSiteCount;
	}
	public Date getGetOrderDatetime() {
		return getOrderDatetime;
	}
	public void setGetOrderDatetime(Date getOrderDatetime) {
		this.getOrderDatetime = getOrderDatetime;
	}
	public Date getBeginGetOrderDatetime() {
		return beginGetOrderDatetime;
	}
	public void setBeginGetOrderDatetime(Date beginGetOrderDatetime) {
		this.beginGetOrderDatetime = beginGetOrderDatetime;
	}
	public Date getEndGetOrderDatetime() {
		return endGetOrderDatetime;
	}
	public void setEndGetOrderDatetime(Date endGetOrderDatetime) {
		this.endGetOrderDatetime = endGetOrderDatetime;
	}
	public Date getOrderDistributeLogDate() {
		return orderDistributeLogDate;
	}
	public void setOrderDistributeLogDate(Date orderDistributeLogDate) {
		this.orderDistributeLogDate = orderDistributeLogDate;
	}
	public Date getBeginOrderDistributeLogDate() {
		return beginOrderDistributeLogDate;
	}
	public void setBeginOrderDistributeLogDate(Date beginOrderDistributeLogDate) {
		this.beginOrderDistributeLogDate = beginOrderDistributeLogDate;
	}
	public Date getEndOrderDistributeLogDate() {
		return endOrderDistributeLogDate;
	}
	public void setEndOrderDistributeLogDate(Date endOrderDistributeLogDate) {
		this.endOrderDistributeLogDate = endOrderDistributeLogDate;
	}
	public Integer getItemManagerBuildingCount() {
		return itemManagerBuildingCount;
	}
	public void setItemManagerBuildingCount(Integer itemManagerBuildingCount) {
		this.itemManagerBuildingCount = itemManagerBuildingCount;
	}
	
	
	

}