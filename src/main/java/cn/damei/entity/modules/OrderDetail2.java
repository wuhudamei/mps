
package cn.damei.entity.modules;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import cn.damei.common.persistence.DataEntity2;


public class OrderDetail2 extends DataEntity2<OrderDetail2> {

	private static final long serialVersionUID = 1L;
	
	

	private String orderNumber;
	private String contractNumber;
	private String customerType;
	private String customerDescription;
	private String customerName;
	private String customerPhone;
	private String customerAddress;
	private String communityName;
	private String buildNumber;
	private String buildUnit;
	private String buildRoom;
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
	private String storeId;
	private String cusManager;
	private String isLongwayCommission;
	private Double longwayAmount;
	
	private Date beginContractStartDate;
	private Date endContractStartDate;
	private Date beginSignContractDate;
	private Date endSignContractDate;
	private Date beginCreateDate;
	private Date endCreateDate;
	private String orderTaskPackStatus;
	private Integer itemManagerId;
	private Integer orderInspectorId;
	private String workeMaprName;
	private String workerMapAddress;
	private String workerMapPhone;
	private Integer workerMapType;
	
	public String getWorkeMaprName() {
		return workeMaprName;
	}

	public void setWorkeMaprName(String workeMaprName) {
		this.workeMaprName = workeMaprName;
	}

	public String getWorkerMapAddress() {
		return workerMapAddress;
	}

	public void setWorkerMapAddress(String workerMapAddress) {
		this.workerMapAddress = workerMapAddress;
	}

	public String getWorkerMapPhone() {
		return workerMapPhone;
	}

	public void setWorkerMapPhone(String workerMapPhone) {
		this.workerMapPhone = workerMapPhone;
	}

	public Integer getWorkerMapType() {
		return workerMapType;
	}

	public void setWorkerMapType(Integer workerMapType) {
		this.workerMapType = workerMapType;
	}

	private String projectMode;
	private List<String> orderStatus;
	private List<String> paymentStatusList;
	private String paymentStatus;
	private Double amount1;
	private Double amount2;
	private Double amount3;
	private Double amount4;
	private Double amount5;
	private Double amount6;
	private Double amount7;
	private Double amount8;
	private Double amount9;
	private Double amount10;

	private Date settleDatetime;
	private Double guaranteeMoneyAmount;
	private Date beginGeneratedDatetime;
	private Date endGeneratedDatetime;

	private String isAllotManager;
	private String isAllotInspector;
	private Integer enginDepartId;
	private String departmentName;

	private String managerName;
	private String managerPhone;
	private String starLevel;
	private  String managerAddress;
	private Double pointX;
	private Double pointY;
	private Integer engineDepartId;
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

	public String getStarLevel() {
		return starLevel;
	}

	public void setStarLevel(String starLevel) {
		this.starLevel = starLevel;
	}

	public String getManagerAddress() {
		return managerAddress;
	}

	public void setManagerAddress(String managerAddress) {
		this.managerAddress = managerAddress;
	}

	public Double getPointX() {
		return pointX;
	}

	public void setPointX(Double pointX) {
		this.pointX = pointX;
	}

	public Double getPointY() {
		return pointY;
	}

	public void setPointY(Double pointY) {
		this.pointY = pointY;
	}

	public Integer getEngineDepartId() {
		return engineDepartId;
	}

	public void setEngineDepartId(Integer engineDepartId) {
		this.engineDepartId = engineDepartId;
	}

	private List<Integer> enginDepartIds = new ArrayList<Integer>();
	public Date getBeginGeneratedDatetime() {
		return beginGeneratedDatetime;
	}

	public void setBeginGeneratedDatetime(Date beginGeneratedDatetime) {
		this.beginGeneratedDatetime = beginGeneratedDatetime;
	}

	public Date getEndGeneratedDatetime() {
		return endGeneratedDatetime;
	}

	public void setEndGeneratedDatetime(Date endGeneratedDatetime) {
		this.endGeneratedDatetime = endGeneratedDatetime;
	}

	public Date getSettleDatetime() {
		return settleDatetime;
	}

	public void setSettleDatetime(Date settleDatetime) {
		this.settleDatetime = settleDatetime;
	}

	public Double getGuaranteeMoneyAmount() {
		return guaranteeMoneyAmount;
	}

	public void setGuaranteeMoneyAmount(Double guaranteeMoneyAmount) {
		this.guaranteeMoneyAmount = guaranteeMoneyAmount;
	}

	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}

	public List<String> getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(List<String> orderStatus) {
		this.orderStatus = orderStatus;
	}

	public List<String> getPaymentStatusList() {
		return paymentStatusList;
	}

	public void setPaymentStatusList(List<String> paymentStatusList) {
		this.paymentStatusList = paymentStatusList;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public Double getAmount1() {
		return amount1;
	}

	public void setAmount1(Double amount1) {
		this.amount1 = amount1;
	}

	public Double getAmount2() {
		return amount2;
	}

	public void setAmount2(Double amount2) {
		this.amount2 = amount2;
	}

	public Double getAmount3() {
		return amount3;
	}

	public void setAmount3(Double amount3) {
		this.amount3 = amount3;
	}

	public Double getAmount4() {
		return amount4;
	}

	public void setAmount4(Double amount4) {
		this.amount4 = amount4;
	}

	public Double getAmount5() {
		return amount5;
	}

	public void setAmount5(Double amount5) {
		this.amount5 = amount5;
	}

	public Double getAmount6() {
		return amount6;
	}

	public void setAmount6(Double amount6) {
		this.amount6 = amount6;
	}

	public Double getAmount7() {
		return amount7;
	}

	public void setAmount7(Double amount7) {
		this.amount7 = amount7;
	}

	public Double getAmount8() {
		return amount8;
	}

	public void setAmount8(Double amount8) {
		this.amount8 = amount8;
	}

	public Double getAmount9() {
		return amount9;
	}

	public void setAmount9(Double amount9) {
		this.amount9 = amount9;
	}

	public Double getAmount10() {
		return amount10;
	}

	public void setAmount10(Double amount10) {
		this.amount10 = amount10;
	}

	public OrderDetail2() {
		super();
	}

	public OrderDetail2(Integer id){
		super(id);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Length(min=1, max=100, message="订单编号长度必须介于 1 和 100 之间")
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
	
	@Length(min=0, max=64, message="客户属性描述长度必须介于 0 和 64 之间")
	public String getCustomerDescription() {
		return customerDescription;
	}

	public void setCustomerDescription(String customerDescription) {
		this.customerDescription = customerDescription;
	}
	
	@Length(min=1, max=16, message="客户姓名长度必须介于 1 和 16 之间")
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	@Length(min=1, max=11, message="客户电话长度必须介于 1 和 11 之间")
	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	
	@Length(min=1, max=32, message="小区名称长度必须介于 1 和 32 之间")
	public String getCommunityName() {
		return communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}
	
	@Length(min=1, max=16, message="几号楼长度必须介于 1 和 16 之间")
	public String getBuildNumber() {
		return buildNumber;
	}

	public void setBuildNumber(String buildNumber) {
		this.buildNumber = buildNumber;
	}
	
	@Length(min=1, max=16, message="几单元长度必须介于 1 和 16 之间")
	public String getBuildUnit() {
		return buildUnit;
	}

	public void setBuildUnit(String buildUnit) {
		this.buildUnit = buildUnit;
	}
	
	@Length(min=1, max=16, message="哪一室长度必须介于 1 和 16 之间")
	public String getBuildRoom() {
		return buildRoom;
	}

	public void setBuildRoom(String buildRoom) {
		this.buildRoom = buildRoom;
	}
	
	@Length(min=0, max=255, message="地图坐标长度必须介于 0 和 255 之间")
	public String getMapCoordinate() {
		return mapCoordinate;
	}

	public void setMapCoordinate(String mapCoordinate) {
		this.mapCoordinate = mapCoordinate;
	}
	
	@Length(min=0, max=32, message="套餐类型长度必须介于 0 和 32 之间")
	public String getSaleType() {
		return saleType;
	}

	public void setSaleType(String saleType) {
		this.saleType = saleType;
	}
	
	@Length(min=0, max=32, message="片区长度必须介于 0 和 32 之间")
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
	
	@Length(min=1, max=2, message="新房老房  1为新房  0为老房  默认老房长度必须介于 1 和 2 之间")
	public String getHouseIsNew() {
		return houseIsNew;
	}

	public void setHouseIsNew(String houseIsNew) {
		this.houseIsNew = houseIsNew;
	}
	
	@Length(min=1, max=2, message="是否有电梯  1代表有   0代表没有   默认没有长度必须介于 1 和 2 之间")
	public String getIsElevator() {
		return isElevator;
	}

	public void setIsElevator(String isElevator) {
		this.isElevator = isElevator;
	}
	
	@Length(min=0, max=12, message="设计师姓名长度必须介于 0 和 12 之间")
	public String getDesignerName() {
		return designerName;
	}

	public void setDesignerName(String designerName) {
		this.designerName = designerName;
	}
	
	@Length(min=0, max=12, message="设计师电话长度必须介于 0 和 12 之间")
	public String getDesignerPhone() {
		return designerPhone;
	}

	public void setDesignerPhone(String designerPhone) {
		this.designerPhone = designerPhone;
	}
	
	@Length(min=0, max=12, message="跟单员姓名长度必须介于 0 和 12 之间")
	public String getOrderReporterName() {
		return orderReporterName;
	}

	public void setOrderReporterName(String orderReporterName) {
		this.orderReporterName = orderReporterName;
	}
	
	@Length(min=0, max=12, message="跟单员电话长度必须介于 0 和 12 之间")
	public String getOrderReporterPhone() {
		return orderReporterPhone;
	}

	public void setOrderReporterPhone(String orderReporterPhone) {
		this.orderReporterPhone = orderReporterPhone;
	}
	
	@Length(min=0, max=12, message="客服姓名长度必须介于 0 和 12 之间")
	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	
	@Length(min=0, max=12, message="客服电话长度必须介于 0 和 12 之间")
	public String getServicePhone() {
		return servicePhone;
	}

	public void setServicePhone(String servicePhone) {
		this.servicePhone = servicePhone;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getContractStartDate() {
		return contractStartDate;
	}

	public void setContractStartDate(Date contractStartDate) {
		this.contractStartDate = contractStartDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getContractEndDate() {
		return contractEndDate;
	}

	public void setContractEndDate(Date contractEndDate) {
		this.contractEndDate = contractEndDate;
	}
	
	@Length(min=0, max=12, message="建筑面积长度必须介于 0 和 12 之间")
	public String getCoveredArea() {
		return coveredArea;
	}

	public void setCoveredArea(String coveredArea) {
		this.coveredArea = coveredArea;
	}
	
	@Length(min=0, max=12, message="合同面积长度必须介于 0 和 12 之间")
	public String getContractArea() {
		return contractArea;
	}

	public void setContractArea(String contractArea) {
		this.contractArea = contractArea;
	}
	
	@Length(min=0, max=12, message="合同工期长度必须介于 0 和 12 之间")
	
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSignContractDate() {
		return signContractDate;
	}

	public Integer getContractTime() {
		return contractTime;
	}

	public void setContractTime(Integer contractTime) {
		this.contractTime = contractTime;
	}

	public void setSignContractDate(Date signContractDate) {
		this.signContractDate = signContractDate;
	}
	
	@Length(min=1, max=12, message="订单状态码  创建订单成功默认状态码 105长度必须介于 1 和 12 之间")
	public String getOrderStatusNumber() {
		return orderStatusNumber;
	}

	public void setOrderStatusNumber(String orderStatusNumber) {
		this.orderStatusNumber = orderStatusNumber;
	}
	
	@Length(min=1, max=64, message="订单状态码详情   默认详情 确认订单   状态码105长度必须介于 1 和 64 之间")
	public String getOrderStatusDescription() {
		return orderStatusDescription;
	}

	public void setOrderStatusDescription(String orderStatusDescription) {
		this.orderStatusDescription = orderStatusDescription;
	}
	
	@Length(min=0, max=64, message="订单质检员长度必须介于 0 和 64 之间")
	public String getOrderInspector() {
		return orderInspector;
	}

	public void setOrderInspector(String orderInspector) {
		this.orderInspector = orderInspector;
	}
	
	@Length(min=0, max=64, message="项目经理长度必须介于 0 和 64 之间")
	public String getItemManager() {
		return itemManager;
	}

	public void setItemManager(String itemManager) {
		this.itemManager = itemManager;
	}
	
	@Length(min=1, max=64, message="门店id长度必须介于 1 和 64 之间")
	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	
	@Length(min=0, max=64, message="客户经理长度必须介于 0 和 64 之间")
	public String getCusManager() {
		return cusManager;
	}

	public void setCusManager(String cusManager) {
		this.cusManager = cusManager;
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

	public String getOrderTaskPackStatus() {
		return orderTaskPackStatus;
	}

	public void setOrderTaskPackStatus(String orderTaskPackStatus) {
		this.orderTaskPackStatus = orderTaskPackStatus;
	}
	
	public Integer getItemManagerId() {
		return itemManagerId;
	}

	public void setItemManagerId(Integer itemManagerId) {
		this.itemManagerId = itemManagerId;
	}
	
	public Integer getOrderInspectorId() {
		return orderInspectorId;
	}

	public void setOrderInspectorId(Integer orderInspectorId) {
		this.orderInspectorId = orderInspectorId;
	}

	public String getIsLongwayCommission() {
		return isLongwayCommission;
	}

	public void setIsLongwayCommission(String isLongwayCommission) {
		this.isLongwayCommission = isLongwayCommission;
	}

	public Double getLongwayAmount() {
		return longwayAmount;
	}

	public void setLongwayAmount(Double longwayAmount) {
		this.longwayAmount = longwayAmount;
	}

	public String getIsAllotManager() {
		return isAllotManager;
	}

	public void setIsAllotManager(String isAllotManager) {
		this.isAllotManager = isAllotManager;
	}

	public String getIsAllotInspector() {
		return isAllotInspector;
	}

	public void setIsAllotInspector(String isAllotInspector) {
		this.isAllotInspector = isAllotInspector;
	}

	public List<Integer> getEnginDepartIds() {
		return enginDepartIds;
	}

	public void setEnginDepartIds(List<Integer> enginDepartIds) {
		this.enginDepartIds = enginDepartIds;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	public Integer getEnginDepartId() {
		return enginDepartId;
	}
	
	public void setEnginDepartId(Integer enginDepartId) {
		this.enginDepartId = enginDepartId;
	}
}