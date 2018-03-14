/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import cn.damei.common.persistence.DataEntity;

/**
 * 订单管理Entity
 * 
 * @author wyb
 * @version 2016-09-08
 */
public class Order extends DataEntity<Order>
{

	private static final long serialVersionUID = 1L;
	private Integer synId;

	private Integer orderId;// 订单id
	private Integer engineDepartId;// 工程部id
	private String engineDepartName;
	private String empId;
	private List<Integer> empEngineDepartIds;
	private String lng;
	private String lat;
	private String lon;
	private Integer star; // 星级
	private Double distance; // 住址和工地距离
	// 2017-3-8 加入 接单时间
	private Date acceptOrderDate; // 接单时间

	private String projectMode;// 工程模式
	private String orderNumber; // 订单编号
	private String contractNumber; // 合同编号
	private String customerType; // 客户类型
	private String customerDescription; // 客户属性描述
	private String customerName; // 客户姓名
	private String customerPhone; // 客户电话
	private String communityName; // 小区名称
	private String buildNumber; // 几号楼
	private String buildUnit; // 几单元
	private String buildRoom; // 哪一室
	private Integer floor; // 楼层
	private String designerEmployeeId; //设计师ID
	
	
	
	private Date actualStartDate; //实际开工时间
	private Date actualEndDate; //实际竣工日期
	private Date startActualStartDate; //实际开工日期开始
	private Date endActualEndDate; //实际竣工日期结束
	private Integer enabled;  //是否启用

	
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

	//返回标识
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

	private String mapCoordinate; // 地图坐标
	private String saleType; // 套餐类型
	private String area; // 片区
	private String buildType; // 房屋类型
	private String houseType; // 户型
	private String houseIsNew; // 新房老房 1为新房 0为老房 默认老房
	private String isElevator; // 是否有电梯 1代表有 0代表没有 默认没有
	private String designerName; // 设计师姓名
	private String designerPhone; // 设计师电话
	private String orderReporterName; // 跟单员姓名
	private String orderReporterPhone; // 跟单员电话
	private String serviceName; // 客服姓名
	private String servicePhone; // 客服电话
	private Date contractStartDate; // 合同开工日期
	private Date contractEndDate; // 合同竣工日期
	private String coveredArea; // 建筑面积
	private String contractArea; // 合同面积
	private Integer contractTime; // 合同工期
	private Date signContractDate; // 签约日期
	private String orderStatusNumber; // 订单状态码 创建订单成功默认状态码 105
	private String orderStatusDescription; // 订单状态码详情 默认详情 确认订单 状态码105
	private String orderInspector; // 订单质检员
	private String itemManager; // 项目经理
	private Integer itemManagerId; // 项目经理id
	private String storeId; // 门店id
	private String cusManager; // 客户经理
	private Date beginContractStartDate; // 开始 合同开工日期
	private Date endContractStartDate; // 结束 合同开工日期
	private Date beginSignContractDate; // 开始 签约日期
	private Date endSignContractDate; // 结束 签约日期
	private Date beginCreateDate; // 开始 创建时间
	private Date endCreateDate; // 结束 创建时间
	private String orderTaskPackStatus; // 任务包状态
	private String customerAddress;// 客户地址
	private String province;// 省
	private String city;// 市
	private String county;// 县
	private String detailAddress;// 详细地址
	private String acceptArea;// 接单区域
	private Integer acceptAreaId;// 接单区域id
	// 开发:17-2-13 加入 远程费
	private Double distanceFee; // 远程费
	private Integer prepareOrderId; // 预备订单ID
	private Integer orderReportId;// 返单id
	private Double contractAmount; // 合同金额
	private String projectName; // 工程名稱

	private String isScrap; // 是否作废
	private Integer auditorEmployeeId;//审计员id
	private String auditorName;//审计员姓名
	private String auditorPhone;//审计员电话
	
	private String orderSource; //订单来源
	
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

	// @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getContractStartDate()
	{
		return contractStartDate;
	}

	public void setContractStartDate(Date contractStartDate)
	{
		this.contractStartDate = contractStartDate;
	}

	// @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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