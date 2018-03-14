/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import java.util.Date;
import java.util.List;

/**
 * 订单管理Entity
 * @author wyb
 * @version 2016-09-08
 */
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
	private Integer orderId;//订单id
	private String storeId;		// 门店id
	
	private String orderNumber;		// 订单编号
	private String contractNumber;		// 合同编号
	
	private String customerType;		// 客户类型
	private String customerDescription;		// 客户属性描述
	
	private String orderStatusNumber;		// 订单状态码  创建订单成功默认状态码 105
	private String orderStatusDescription;		// 订单状态码详情   默认详情 确认订单   状态码105
	private String orderTaskPackStatus; //任务包状态
	private	List<String> orderStatusList = null; //订单状态集合
	
	private String communityName;		// 小区名称
	private String buildNumber;		// 几号楼
	private String buildUnit;		// 几单元
	private String buildRoom;		// 哪一室
	private String mapCoordinate;		// 地图坐标
	private String lng;
	private String lat; 
	
	private String customerAddress;//客户地址
	private String province;//省
	private String city;//市
	private String county ;//县
	private String detailAddress;//详细地址
	private String  acceptArea;//接单区域
	private Integer acceptAreaId;//接单区域id
	
	private String saleType;		// 套餐类型
	private String area;		// 片区
	private String buildType;		// 房屋类型
	private String houseType;		// 户型
	private String houseIsNew;		// 新房老房  1为新房  0为老房  默认老房
	private String isElevator;		// 是否有电梯  1代表有   0代表没有   默认没有
	private String coveredArea;		// 建筑面积
	private String contractArea;		// 合同面积
	private Integer contractTime;		// 合同工期
	
	private String customerName;		// 客户姓名
	private String customerPhone;		// 客户电话
	private String cusManager;		// 客户经理
	
	private Integer itemManagerId;	//项目经理id
	private String itemManager;		// 项目经理
	private String itemManagerPhone;// 项目经理手机号
	private Integer itemManagerStar;	//项目经理星级
	private Integer itemManagerBuildingCount;	//项目经理承接量
	
	private Integer orderInspectorId;	//订单质检员
	private String orderInspector;		// 订单质检员
	private String orderInspectorPhone;		// 订单质检员手机号
	
	private String designerName;		// 设计师姓名
	private String designerPhone;		// 设计师电话
	
	private String orderReporterName;		// 跟单员姓名
	private String orderReporterPhone;		// 跟单员电话
	
	private String serviceName;		// 客服姓名
	private String servicePhone;		// 客服电话
	
	private Date contractStartDate;			// 合同开工日期
	private Date beginContractStartDate;	// 开始 合同开工日期
	private Date endContractStartDate;		// 结束 合同开工日期
	
	private Date contractEndDate;		// 合同竣工日期
	private Date beginContractEndDate;	// 开始  合同竣工日期
	private Date endContractEndDate;	// 结束  合同竣工日期
	
	private Date actualStartDate;      //实际开工日期
	private Date beginActualStartDate; // 开始   实际开工日期
	private Date endActualStartDate;   // 结束   实际开工日期
	
	private Date actualEndDate;			//实际竣工日期
	private Date beginActualEndDate;    // 开始 实际竣工日期
	private Date endActualEndDate;      // 结束  实际竣工日期
	
	private Date signContractDate;		// 签约日期
	private Date beginSignContractDate;	// 开始 签约日期
	private Date endSignContractDate;	// 结束 签约日期
	
	private Date getOrderDatetime;		// 接单日期
	private Date beginGetOrderDatetime;	// 开始 接单日期
	private Date endGetOrderDatetime;	// 结束 接单日期
	
	private Date orderDistributeLogDate;		// 派项目经理日期
	private Date beginOrderDistributeLogDate;	// 开始 派项目经理日期
	private Date endOrderDistributeLogDate;	// 结束 派项目经理日期
	
	private Date beginCreateDate;	// 开始   订单创建日期
	private Date endCreateDate;		//结束   订单创建日期
	private String projectMode;		//工程模式   1-产业模式；2-传统模式
	
	private Integer engineDepartId;	//区域id
	private String engineDepartName;	//区域名称
	
	private Integer beforeSiteCount;	//原工地数
	private Integer NowSiteCount;	//现工地数
	
	
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