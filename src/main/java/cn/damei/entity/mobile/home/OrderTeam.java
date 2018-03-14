package cn.damei.entity.mobile.home;

import java.util.Date;
import cn.damei.common.persistence.DataEntity2;

public class OrderTeam extends DataEntity2<OrderTeam>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String orderNumber;		// 订单编号 
	private String customerName;		// 客户姓名
	private String customerPhone;		// 客户电话
	private String customerAddress;
	private String communityName;		// 小区名称
	private String buildNumber;		// 几号楼
	private String buildUnit;		// 几单元
	private String buildRoom;		// 哪一室
	private String saleType;		// 套餐类型
	private String buildType;		// 房屋类型
	private String houseType;		// 户型
	private String houseIsNew;		// 新房老房  1为新房  0为老房  默认老房
	private String designerName;		// 设计师姓名
	private String designerPhone;		// 设计师电话
	private String orderReporterName;		// 跟单员姓名
	private String orderReporterPhone;		// 跟单员电话
	private String serviceName;		// 客服姓名
	private String servicePhone;		// 客服电话
	private Date contractStartDate;		// 合同开工日期
	private Date contractEndDate;		// 合同竣工日期
	private Integer contractTime;
	private String coveredArea;		// 建筑面积
	private Date signContractDate;		// 签约日期
	private Integer storeId;		// 门店id
	private Integer itemManagerId;
	private String itemManager;
	private String itemManagerPhone;
	private Integer orderInspectorId;
	private String orderInspector;
	private String inspectorStar;
	private String managerStar;
	private String workerStar;
	private String workerName;
	private Integer empGroupId;
	private Integer empId;
	private String workerPhone;
	private String workerType;
	
	public String getWorkerType() {
		return workerType;
	}
	public void setWorkerType(String workerType) {
		this.workerType = workerType;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
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
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
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
	public String getSaleType() {
		return saleType;
	}
	public void setSaleType(String saleType) {
		this.saleType = saleType;
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
	public Date getContractEndDate() {
		return contractEndDate;
	}
	public void setContractEndDate(Date contractEndDate) {
		this.contractEndDate = contractEndDate;
	}
	public Integer getContractTime() {
		return contractTime;
	}
	public void setContractTime(Integer contractTime) {
		this.contractTime = contractTime;
	}
	public String getCoveredArea() {
		return coveredArea;
	}
	public void setCoveredArea(String coveredArea) {
		this.coveredArea = coveredArea;
	}
	public Date getSignContractDate() {
		return signContractDate;
	}
	public void setSignContractDate(Date signContractDate) {
		this.signContractDate = signContractDate;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
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
	public String getInspectorStar() {
		return inspectorStar;
	}
	public void setInspectorStar(String inspectorStar) {
		this.inspectorStar = inspectorStar;
	}
	public String getManagerStar() {
		return managerStar;
	}
	public void setManagerStar(String managerStar) {
		this.managerStar = managerStar;
	}
	public String getWorkerStar() {
		return workerStar;
	}
	public void setWorkerStar(String workerStar) {
		this.workerStar = workerStar;
	}
	public String getWorkerName() {
		return workerName;
	}
	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}
	public String getWorkerPhone() {
		return workerPhone;
	}
	public void setWorkerPhone(String workerPhone) {
		this.workerPhone = workerPhone;
	}
	public Integer getEmpGroupId() {
		return empGroupId;
	}
	public void setEmpGroupId(Integer empGroupId) {
		this.empGroupId = empGroupId;
	}
	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
}
