
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import cn.damei.common.persistence.DataEntity2;


public class BizPrepareOrder extends DataEntity2<BizPrepareOrder> {
	
	private static final long serialVersionUID = 1L;
	private Integer synDataId;
	private Integer storeId;
	private String projectMode;
	private String orderNumber;
	private Integer enginDepartId;
	private String enginDepartName;
	private String contractNumber;
	private String customerType;
	private String customerDescription;
	private String customerName;
	private String customerPhone;
	private String customerAddress;
	private String isLongwayCommission;
	private Double longwayAmount;
	private String communityName;
	private String detailAddress;
	private String buildNumber;
	private String buildUnit;
	private String buildRoom;
	private String mapCoordinate;
	private String province;
	private String city;
	private String county;
	private String saleType;
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
	private Date beginContractStartDate;
	private Date endContractStartDate;
	private Date contractEndDate;
	private String coveredArea;
	private String contractArea;
	private Integer contractTime;
	private Date signContractDate;
	private Date beginSignContractDate;
	private Date endSignContractDate;
	private String bizOrderAcceptArea;
	private String bizOrderAcceptAreaName;
	private Date beginCreateDate;
	private Date endCreateDate;
	private String status;
	private Date getOrderDatetime;
	private Integer floor;
	private Double  contractAmount;
	private String orderDesignerName;
	private String orderDesignerPhone;
	private String itemManager;
	
	private Integer auditorEmployeeId;
	private String auditorName;
	private String auditorPhone;
	
	private String refuseReasonType;
	private String refuseReasonTypeName;
	private String refuseReason;
	
	private String auditorTagname;

	public String getAuditorTagname() {
		return auditorTagname;
	}

	public void setAuditorTagname(String auditorTagname) {
		this.auditorTagname = auditorTagname;
	}


	public String getItemManager() {
		return itemManager;
	}

	public void setItemManager(String itemManager) {
		this.itemManager = itemManager;
	}

	public String getOrderDesignerPhone() {
		return orderDesignerPhone;
	}

	public void setOrderDesignerPhone(String orderDesignerPhone) {
		this.orderDesignerPhone = orderDesignerPhone;
	}

	public String getOrderDesignerName() {
		return orderDesignerName;
	}

	public void setOrderDesignerName(String orderDesignerName) {
		this.orderDesignerName = orderDesignerName;
	}

	public Integer getFloor() {
		return floor;
	}

	public void setFloor(Integer floor) {
		this.floor = floor;
	}
	public BizPrepareOrder() {
		super();
	}

	public BizPrepareOrder(Integer id){
		super(id);
	}

	public Integer getSynDataId() {
		return synDataId;
	}

	public void setSynDataId(Integer synDataId) {
		this.synDataId = synDataId;
	}
	
	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	
	@Length(min=0, max=10, message="工程模式长度必须介于 0 和 10 之间")
	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}
	
	@Length(min=0, max=100, message="订单编号长度必须介于 0 和 100 之间")
	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	public Integer getEnginDepartId() {
		return enginDepartId;
	}

	public void setEnginDepartId(Integer enginDepartId) {
		this.enginDepartId = enginDepartId;
	}
	
	@Length(min=0, max=100, message="合同编号长度必须介于 0 和 100 之间")
	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}
	
	@Length(min=0, max=10, message="客户类型长度必须介于 0 和 10 之间")
	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	
	@Length(min=0, max=500, message="客户属性描述长度必须介于 0 和 500 之间")
	public String getCustomerDescription() {
		return customerDescription;
	}

	public void setCustomerDescription(String customerDescription) {
		this.customerDescription = customerDescription;
	}
	
	@Length(min=0, max=16, message="客户姓名长度必须介于 0 和 16 之间")
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	@Length(min=0, max=11, message="客户手机长度必须介于 0 和 11 之间")
	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	
	@Length(min=0, max=500, message="客户地址长度必须介于 0 和 500 之间")
	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	
	@Length(min=0, max=10, message="是否有远程费长度必须介于 0 和 10 之间")
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
	
	@Length(min=0, max=100, message="小区名称长度必须介于 0 和 100 之间")
	public String getCommunityName() {
		return communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}
	
	@Length(min=0, max=500, message="详细地址长度必须介于 0 和 500 之间")
	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}
	
	@Length(min=0, max=16, message="楼长度必须介于 0 和 16 之间")
	public String getBuildNumber() {
		return buildNumber;
	}

	public void setBuildNumber(String buildNumber) {
		this.buildNumber = buildNumber;
	}
	
	@Length(min=0, max=16, message="单元长度必须介于 0 和 16 之间")
	public String getBuildUnit() {
		return buildUnit;
	}

	public void setBuildUnit(String buildUnit) {
		this.buildUnit = buildUnit;
	}
	
	@Length(min=0, max=16, message="室长度必须介于 0 和 16 之间")
	public String getBuildRoom() {
		return buildRoom;
	}

	public void setBuildRoom(String buildRoom) {
		this.buildRoom = buildRoom;
	}
	
	@Length(min=0, max=255, message="坐标长度必须介于 0 和 255 之间")
	public String getMapCoordinate() {
		return mapCoordinate;
	}

	public void setMapCoordinate(String mapCoordinate) {
		this.mapCoordinate = mapCoordinate;
	}
	
	@Length(min=0, max=50, message="省长度必须介于 0 和 50 之间")
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	
	@Length(min=0, max=50, message="市长度必须介于 0 和 50 之间")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	@Length(min=0, max=50, message="县长度必须介于 0 和 50 之间")
	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}
	
	@Length(min=0, max=32, message="套餐类型长度必须介于 0 和 32 之间")
	public String getSaleType() {
		return saleType;
	}

	public void setSaleType(String saleType) {
		this.saleType = saleType;
	}
	
	@Length(min=0, max=16, message="房屋类型长度必须介于 0 和 16 之间")
	public String getBuildType() {
		return buildType;
	}

	public void setBuildType(String buildType) {
		this.buildType = buildType;
	}
	
	@Length(min=0, max=16, message="户型长度必须介于 0 和 16 之间")
	public String getHouseType() {
		return houseType;
	}

	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}
	
	@Length(min=0, max=10, message="是否新房长度必须介于 0 和 10 之间")
	public String getHouseIsNew() {
		return houseIsNew;
	}

	public void setHouseIsNew(String houseIsNew) {
		this.houseIsNew = houseIsNew;
	}
	
	@Length(min=0, max=10, message="有无电梯长度必须介于 0 和 10 之间")
	public String getIsElevator() {
		return isElevator;
	}

	public void setIsElevator(String isElevator) {
		this.isElevator = isElevator;
	}
	
	@Length(min=0, max=50, message="设计师姓名长度必须介于 0 和 50 之间")
	public String getDesignerName() {
		return designerName;
	}

	public void setDesignerName(String designerName) {
		this.designerName = designerName;
	}
	
	@Length(min=0, max=11, message="设计师电话长度必须介于 0 和 11 之间")
	public String getDesignerPhone() {
		return designerPhone;
	}

	public void setDesignerPhone(String designerPhone) {
		this.designerPhone = designerPhone;
	}
	
	@Length(min=0, max=50, message="跟单员姓名长度必须介于 0 和 50 之间")
	public String getOrderReporterName() {
		return orderReporterName;
	}

	public void setOrderReporterName(String orderReporterName) {
		this.orderReporterName = orderReporterName;
	}
	
	@Length(min=0, max=11, message="跟单员电话长度必须介于 0 和 11 之间")
	public String getOrderReporterPhone() {
		return orderReporterPhone;
	}

	public void setOrderReporterPhone(String orderReporterPhone) {
		this.orderReporterPhone = orderReporterPhone;
	}
	
	@Length(min=0, max=50, message="客服姓名长度必须介于 0 和 50 之间")
	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	
	@Length(min=0, max=11, message="客服电话长度必须介于 0 和 11 之间")
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
	
	@Length(min=0, max=20, message="建筑面积长度必须介于 0 和 20 之间")
	public String getCoveredArea() {
		return coveredArea;
	}

	public void setCoveredArea(String coveredArea) {
		this.coveredArea = coveredArea;
	}
	
	@Length(min=0, max=20, message="合同面积长度必须介于 0 和 20 之间")
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
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSignContractDate() {
		return signContractDate;
	}

	public void setSignContractDate(Date signContractDate) {
		this.signContractDate = signContractDate;
	}
	
	@Length(min=0, max=100, message="接单区域长度必须介于 0 和 100 之间")
	public String getBizOrderAcceptArea() {
		return bizOrderAcceptArea;
	}

	public void setBizOrderAcceptArea(String bizOrderAcceptArea) {
		this.bizOrderAcceptArea = bizOrderAcceptArea;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getGetOrderDatetime() {
		return getOrderDatetime;
	}

	public void setGetOrderDatetime(Date getOrderDatetime) {
		this.getOrderDatetime = getOrderDatetime;
	}

	public String getBizOrderAcceptAreaName() {
		return bizOrderAcceptAreaName;
	}

	public void setBizOrderAcceptAreaName(String bizOrderAcceptAreaName) {
		this.bizOrderAcceptAreaName = bizOrderAcceptAreaName;
	}

	public String getEnginDepartName() {
		return enginDepartName;
	}

	public void setEnginDepartName(String enginDepartName) {
		this.enginDepartName = enginDepartName;
	}

	public Double getContractAmount() {
		return contractAmount;
	}

	public void setContractAmount(Double contractAmount) {
		this.contractAmount = contractAmount;
	}

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

	public String getRefuseReasonType() {
		return refuseReasonType;
	}

	public void setRefuseReasonType(String refuseReasonType) {
		this.refuseReasonType = refuseReasonType;
	}

	public String getRefuseReasonTypeName() {
		return refuseReasonTypeName;
	}

	public void setRefuseReasonTypeName(String refuseReasonTypeName) {
		this.refuseReasonTypeName = refuseReasonTypeName;
	}

	public String getRefuseReason() {
		return refuseReason;
	}

	public void setRefuseReason(String refuseReason) {
		this.refuseReason = refuseReason;
	}
	
	
}