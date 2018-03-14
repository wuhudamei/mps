
package cn.damei.entity.modules;

import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import cn.damei.common.persistence.DataEntity;


public class Order extends DataEntity<Order>
{

	private static final long serialVersionUID = 1L;
	private Integer synId;

	private Integer orderId;
	private Integer engineDepartId;
	private String engineDepartName;
	private String empId;
	private List<Integer> empEngineDepartIds;
	private String lng;
	private String lat;
	private String lon;
	private Integer star;
	private Double distance;

	private Date acceptOrderDate;

	private String projectMode;
	private String orderNumber;
	private String contractNumber;
	private String customerType;
	private String customerDescription;
	private String customerName;
	private String customerPhone;
	private String communityName;
	private String buildNumber;
	private String buildUnit;
	private String buildRoom;
	private Integer floor;
	private String designerEmployeeId;
	
	
	
	private Date actualStartDate;
	private Date actualEndDate;
	private Date startActualStartDate;
	private Date endActualEndDate;
	private Integer enabled;

	
	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}


	private String flag;
	
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Integer getFloor()
	{
		return floor;
	}

	public void setFloor(Integer floor)
	{
		this.floor = floor;
	}

	private String mapCoordinate;
	private String saleType;
	private String area;
	private String buildType;
	private String houseType;
	private String houseIsNew;
	private String isElevator;
	private String designerName;
	private String designerPhone;
	private String orderReporterName;
	private String orderReporterPhone;
	private String serviceName;
	private String servicePhone;
	private Date contractStartDate;
	private Date contractEndDate;
	private String coveredArea;
	private String contractArea;
	private Integer contractTime;
	private Date signContractDate;
	private String orderStatusNumber;
	private String orderStatusDescription;
	private String orderInspector;
	private String itemManager;
	private Integer itemManagerId;
	private String storeId;
	private String cusManager;
	private Date beginContractStartDate;
	private Date endContractStartDate;
	private Date beginSignContractDate;
	private Date endSignContractDate;
	private Date beginCreateDate;
	private Date endCreateDate;
	private String orderTaskPackStatus;
	private String customerAddress;
	private String province;
	private String city;
	private String county;
	private String detailAddress;
	private String acceptArea;
	private Integer acceptAreaId;

	private Double distanceFee;
	private Integer prepareOrderId;
	private Integer orderReportId;
	private Double contractAmount;
	private String projectName;

	private String isScrap;
	private Integer auditorEmployeeId;
	private String auditorName;
	private String auditorPhone;
	
	private String orderSource;
	
	public Integer getAuditorEmployeeId() {
		return auditorEmployeeId;
	}

	public void setAuditorEmployeeId(Integer auditorEmployeeId) {
		this.auditorEmployeeId = auditorEmployeeId;
	}

	public String getAuditorName() {
		return auditorName;
	}

	public void setAuditorName(String auditorName) {
		this.auditorName = auditorName;
	}

	public String getAuditorPhone() {
		return auditorPhone;
	}

	public void setAuditorPhone(String auditorPhone) {
		this.auditorPhone = auditorPhone;
	}

	public String getIsScrap() {
		return isScrap;
	}

	public void setIsScrap(String isScrap) {
		this.isScrap = isScrap;
	}

	public Integer getOrderReportId()
	{
		return orderReportId;
	}

	public void setOrderReportId(Integer orderReportId)
	{
		this.orderReportId = orderReportId;
	}

	public Integer getStar()
	{
		return star;
	}

	public void setStar(Integer star)
	{
		this.star = star;
	}

	public Double getDistance()
	{
		return distance;
	}

	public String getProjectName()
	{
		return projectName;
	}

	public void setProjectName(String projectName)
	{
		this.projectName = projectName;
	}

	public void setDistance(Double distance)
	{
		this.distance = distance;
	}

	public Integer getSynId()
	{
		return synId;
	}

	public void setSynId(Integer synId)
	{
		this.synId = synId;
	}

	public String getLng()
	{
		return lng;
	}

	public void setLng(String lng)
	{
		this.lng = lng;
	}

	public String getLat()
	{
		return lat;
	}

	public void setLat(String lat)
	{
		this.lat = lat;
	}

	public Date getAcceptOrderDate()
	{
		return acceptOrderDate;
	}

	public void setAcceptOrderDate(Date acceptOrderDate)
	{
		this.acceptOrderDate = acceptOrderDate;
	}

	public List<Integer> getEmpEngineDepartIds()
	{
		return empEngineDepartIds;
	}

	public void setEmpEngineDepartIds(List<Integer> empEngineDepartIds)
	{
		this.empEngineDepartIds = empEngineDepartIds;
	}

	public String getEngineDepartName()
	{
		return engineDepartName;
	}

	public void setEngineDepartName(String engineDepartName)
	{
		this.engineDepartName = engineDepartName;
	}

	public String getEmpId()
	{
		return empId;
	}

	public void setEmpId(String empId)
	{
		this.empId = empId;
	}

	public Integer getOrderId()
	{
		return orderId;
	}

	public Integer getEngineDepartId()
	{
		return engineDepartId;
	}

	public void setEngineDepartId(Integer engineDepartId)
	{
		this.engineDepartId = engineDepartId;
	}

	public void setOrderId(Integer orderId)
	{
		this.orderId = orderId;
	}

	public String getProjectMode()
	{
		return projectMode;
	}

	public void setProjectMode(String projectMode)
	{
		this.projectMode = projectMode;
	}

	public Double getDistanceFee()
	{
		return distanceFee;
	}

	public void setDistanceFee(Double distanceFee)
	{
		this.distanceFee = distanceFee;
	}

	public Integer getAcceptAreaId()
	{
		return acceptAreaId;
	}

	public void setAcceptAreaId(Integer acceptAreaId)
	{
		this.acceptAreaId = acceptAreaId;
	}

	public String getProvince()
	{
		return province;
	}

	public void setProvince(String province)
	{
		this.province = province;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getCounty()
	{
		return county;
	}

	public void setCounty(String county)
	{
		this.county = county;
	}

	public String getDetailAddress()
	{
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress)
	{
		this.detailAddress = detailAddress;
	}

	public String getAcceptArea()
	{
		return acceptArea;
	}

	public void setAcceptArea(String acceptArea)
	{
		this.acceptArea = acceptArea;
	}

	public String getCustomerAddress()
	{
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress)
	{
		this.customerAddress = customerAddress;
	}

	public Order()
	{
		super();
	}

	public Order(String id)
	{
		super(id);
	}

	@Length(min = 1, max = 100, message = "订单编号长度必须介于 1 和 100 之间")
	public String getOrderNumber()
	{
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber)
	{
		this.orderNumber = orderNumber;
	}

	public String getContractNumber()
	{
		return contractNumber;
	}

	public void setContractNumber(String contractNumber)
	{
		this.contractNumber = contractNumber;
	}

	public String getCustomerType()
	{
		return customerType;
	}

	public void setCustomerType(String customerType)
	{
		this.customerType = customerType;
	}

	@Length(min = 0, max = 64, message = "客户属性描述长度必须介于 0 和 64 之间")
	public String getCustomerDescription()
	{
		return customerDescription;
	}

	public void setCustomerDescription(String customerDescription)
	{
		this.customerDescription = customerDescription;
	}

	@Length(min = 1, max = 16, message = "客户姓名长度必须介于 1 和 16 之间")
	public String getCustomerName()
	{
		return customerName;
	}

	public void setCustomerName(String customerName)
	{
		this.customerName = customerName;
	}

	@Length(min = 1, max = 11, message = "客户电话长度必须介于 1 和 11 之间")
	public String getCustomerPhone()
	{
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone)
	{
		this.customerPhone = customerPhone;
	}

	@Length(min = 1, max = 32, message = "小区名称长度必须介于 1 和 32 之间")
	public String getCommunityName()
	{
		return communityName;
	}

	public void setCommunityName(String communityName)
	{
		this.communityName = communityName;
	}

	@Length(min = 1, max = 16, message = "几号楼长度必须介于 1 和 16 之间")
	public String getBuildNumber()
	{
		return buildNumber;
	}

	public void setBuildNumber(String buildNumber)
	{
		this.buildNumber = buildNumber;
	}

	@Length(min = 1, max = 16, message = "几单元长度必须介于 1 和 16 之间")
	public String getBuildUnit()
	{
		return buildUnit;
	}

	public void setBuildUnit(String buildUnit)
	{
		this.buildUnit = buildUnit;
	}

	@Length(min = 1, max = 16, message = "哪一室长度必须介于 1 和 16 之间")
	public String getBuildRoom()
	{
		return buildRoom;
	}

	public void setBuildRoom(String buildRoom)
	{
		this.buildRoom = buildRoom;
	}

	@Length(min = 0, max = 255, message = "地图坐标长度必须介于 0 和 255 之间")
	public String getMapCoordinate()
	{
		return mapCoordinate;
	}

	public void setMapCoordinate(String mapCoordinate)
	{
		this.mapCoordinate = mapCoordinate;
	}

	@Length(min = 0, max = 32, message = "套餐类型长度必须介于 0 和 32 之间")
	public String getSaleType()
	{
		return saleType;
	}

	public void setSaleType(String saleType)
	{
		this.saleType = saleType;
	}

	@Length(min = 0, max = 32, message = "片区长度必须介于 0 和 32 之间")
	public String getArea()
	{
		return area;
	}

	public void setArea(String area)
	{
		this.area = area;
	}

	public String getBuildType()
	{
		return buildType;
	}

	public void setBuildType(String buildType)
	{
		this.buildType = buildType;
	}

	public String getHouseType()
	{
		return houseType;
	}

	public void setHouseType(String houseType)
	{
		this.houseType = houseType;
	}

	@Length(min = 1, max = 2, message = "新房老房  1为新房  0为老房  默认老房长度必须介于 1 和 2 之间")
	public String getHouseIsNew()
	{
		return houseIsNew;
	}

	public void setHouseIsNew(String houseIsNew)
	{
		this.houseIsNew = houseIsNew;
	}

	@Length(min = 1, max = 2, message = "是否有电梯  1代表有   0代表没有   默认没有长度必须介于 1 和 2 之间")
	public String getIsElevator()
	{
		return isElevator;
	}

	public void setIsElevator(String isElevator)
	{
		this.isElevator = isElevator;
	}

	@Length(min = 0, max = 12, message = "设计师姓名长度必须介于 0 和 12 之间")
	public String getDesignerName()
	{
		return designerName;
	}

	public void setDesignerName(String designerName)
	{
		this.designerName = designerName;
	}

	@Length(min = 0, max = 12, message = "设计师电话长度必须介于 0 和 12 之间")
	public String getDesignerPhone()
	{
		return designerPhone;
	}

	public void setDesignerPhone(String designerPhone)
	{
		this.designerPhone = designerPhone;
	}

	@Length(min = 0, max = 12, message = "跟单员姓名长度必须介于 0 和 12 之间")
	public String getOrderReporterName()
	{
		return orderReporterName;
	}

	public void setOrderReporterName(String orderReporterName)
	{
		this.orderReporterName = orderReporterName;
	}

	@Length(min = 0, max = 12, message = "跟单员电话长度必须介于 0 和 12 之间")
	public String getOrderReporterPhone()
	{
		return orderReporterPhone;
	}

	public void setOrderReporterPhone(String orderReporterPhone)
	{
		this.orderReporterPhone = orderReporterPhone;
	}

	@Length(min = 0, max = 12, message = "客服姓名长度必须介于 0 和 12 之间")
	public String getServiceName()
	{
		return serviceName;
	}

	public void setServiceName(String serviceName)
	{
		this.serviceName = serviceName;
	}

	@Length(min = 0, max = 12, message = "客服电话长度必须介于 0 和 12 之间")
	public String getServicePhone()
	{
		return servicePhone;
	}

	public void setServicePhone(String servicePhone)
	{
		this.servicePhone = servicePhone;
	}


	public Date getContractStartDate()
	{
		return contractStartDate;
	}

	public void setContractStartDate(Date contractStartDate)
	{
		this.contractStartDate = contractStartDate;
	}


	public Date getContractEndDate()
	{
		return contractEndDate;
	}

	public void setContractEndDate(Date contractEndDate)
	{
		this.contractEndDate = contractEndDate;
	}

	@Length(min = 0, max = 12, message = "建筑面积长度必须介于 0 和 12 之间")
	public String getCoveredArea()
	{
		return coveredArea;
	}

	public void setCoveredArea(String coveredArea)
	{
		this.coveredArea = coveredArea;
	}

	@Length(min = 0, max = 12, message = "合同面积长度必须介于 0 和 12 之间")
	public String getContractArea()
	{
		return contractArea;
	}

	public void setContractArea(String contractArea)
	{
		this.contractArea = contractArea;
	}

	@Length(min = 0, max = 12, message = "合同工期长度必须介于 0 和 12 之间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSignContractDate()
	{
		return signContractDate;
	}

	public Integer getContractTime()
	{
		return contractTime;
	}

	public void setContractTime(Integer contractTime)
	{
		this.contractTime = contractTime;
	}

	public void setSignContractDate(Date signContractDate)
	{
		this.signContractDate = signContractDate;
	}

	@Length(min = 1, max = 12, message = "订单状态码  创建订单成功默认状态码 105长度必须介于 1 和 12 之间")
	public String getOrderStatusNumber()
	{
		return orderStatusNumber;
	}

	public void setOrderStatusNumber(String orderStatusNumber)
	{
		this.orderStatusNumber = orderStatusNumber;
	}

	@Length(min = 1, max = 64, message = "订单状态码详情   默认详情 确认订单   状态码105长度必须介于 1 和 64 之间")
	public String getOrderStatusDescription()
	{
		return orderStatusDescription;
	}

	public void setOrderStatusDescription(String orderStatusDescription)
	{
		this.orderStatusDescription = orderStatusDescription;
	}

	@Length(min = 0, max = 64, message = "订单质检员长度必须介于 0 和 64 之间")
	public String getOrderInspector()
	{
		return orderInspector;
	}

	public void setOrderInspector(String orderInspector)
	{
		this.orderInspector = orderInspector;
	}

	@Length(min = 0, max = 64, message = "项目经理长度必须介于 0 和 64 之间")
	public String getItemManager()
	{
		return itemManager;
	}

	public void setItemManager(String itemManager)
	{
		this.itemManager = itemManager;
	}

	@Length(min = 1, max = 64, message = "门店id长度必须介于 1 和 64 之间")
	public String getStoreId()
	{
		return storeId;
	}

	public void setStoreId(String storeId)
	{
		this.storeId = storeId;
	}

	@Length(min = 0, max = 64, message = "客户经理长度必须介于 0 和 64 之间")
	public String getCusManager()
	{
		return cusManager;
	}

	public void setCusManager(String cusManager)
	{
		this.cusManager = cusManager;
	}

	public Date getBeginContractStartDate()
	{
		return beginContractStartDate;
	}

	public void setBeginContractStartDate(Date beginContractStartDate)
	{
		this.beginContractStartDate = beginContractStartDate;
	}

	public Date getEndContractStartDate()
	{
		return endContractStartDate;
	}

	public void setEndContractStartDate(Date endContractStartDate)
	{
		this.endContractStartDate = endContractStartDate;
	}

	public Date getBeginSignContractDate()
	{
		return beginSignContractDate;
	}

	public void setBeginSignContractDate(Date beginSignContractDate)
	{
		this.beginSignContractDate = beginSignContractDate;
	}

	public Date getEndSignContractDate()
	{
		return endSignContractDate;
	}

	public void setEndSignContractDate(Date endSignContractDate)
	{
		this.endSignContractDate = endSignContractDate;
	}

	public Date getBeginCreateDate()
	{
		return beginCreateDate;
	}

	public void setBeginCreateDate(Date beginCreateDate)
	{
		this.beginCreateDate = beginCreateDate;
	}

	public Date getEndCreateDate()
	{
		return endCreateDate;
	}

	public void setEndCreateDate(Date endCreateDate)
	{
		this.endCreateDate = endCreateDate;
	}

	public String getOrderTaskPackStatus()
	{
		return orderTaskPackStatus;
	}

	public void setOrderTaskPackStatus(String orderTaskPackStatus)
	{
		this.orderTaskPackStatus = orderTaskPackStatus;
	}

	public Integer getItemManagerId()
	{
		return itemManagerId;
	}

	public void setItemManagerId(Integer itemManagerId)
	{
		this.itemManagerId = itemManagerId;
	}

	public Integer getPrepareOrderId()
	{
		return prepareOrderId;
	}

	public void setPrepareOrderId(Integer prepareOrderId)
	{
		this.prepareOrderId = prepareOrderId;
	}

	public Double getContractAmount()
	{
		return contractAmount;
	}

	public void setContractAmount(Double contractAmount)
	{
		this.contractAmount = contractAmount;
	}

	public String getDesignerEmployeeId() {
		return designerEmployeeId;
	}

	public void setDesignerEmployeeId(String designerEmployeeId) {
		this.designerEmployeeId = designerEmployeeId;
	}

	public Date getActualStartDate() {
		return actualStartDate;
	}

	public void setActualStartDate(Date actualStartDate) {
		this.actualStartDate = actualStartDate;
	}

	public Date getActualEndDate() {
		return actualEndDate;
	}

	public void setActualEndDate(Date actualEndDate) {
		this.actualEndDate = actualEndDate;
	}

	public Date getStartActualStartDate() {
		return startActualStartDate;
	}

	public void setStartActualStartDate(Date startActualStartDate) {
		this.startActualStartDate = startActualStartDate;
	}

	public Date getEndActualEndDate() {
		return endActualEndDate;
	}

	public void setEndActualEndDate(Date endActualEndDate) {
		this.endActualEndDate = endActualEndDate;
	}

	public String getOrderSource() {
		return orderSource;
	}

	public void setOrderSource(String orderSource) {
		this.orderSource = orderSource;
	}

	
}