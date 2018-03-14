package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import cn.damei.common.persistence.DataEntity2;


public class WallAndFloorBusinessUrge extends DataEntity2<WallAndFloorBusinessUrge> {
	
	private static final long serialVersionUID = 1L;
	private Integer businessId;
	private Integer businessOnlyMarkInt;
	private String businessOnlyMarkVarchar;
	private String businesType;
	private String businesTypeName;
	private String operateType;
	private String operateTypeName;
	private String operateContent;
	private Integer operatorEmployeeId;
	private String operatorEmployeeName;
	private String operatorType;
	private String operatorTypeName;
	private Date operateDatetime;
	private Date beginOperateDatetime;
	private Date endOperateDatetime;
	
	private Integer purchaseId;
	private String purchaseCode;
	private String purchaseStatus;
	private String purchaseStatusName;
	
	private Integer orderId;
	private String communityName;
	private String buildNumber;
	private String buildUnit;
	private String buildRoom;
	
	private String customerName;
	private String customerPhone;
	
	private Integer itemManagerId;
	private String itemManagerName;
	private String itemManagerPhone;
	
	private Integer storeId;
	private String storeName;
	
	private String projectMode;
	private String projectModeName;
	
	private Integer enginDepartId;
	private String enginDepartName;
	private Integer urgeCount;
	
	

	public WallAndFloorBusinessUrge() {
		super();
	}

	public WallAndFloorBusinessUrge(Integer id){
		super(id);
	}

	public Integer getBusinessOnlyMarkInt() {
		return businessOnlyMarkInt;
	}

	public void setBusinessOnlyMarkInt(Integer businessOnlyMarkInt) {
		this.businessOnlyMarkInt = businessOnlyMarkInt;
	}
	
	@Length(min=0, max=100, message="业务唯一标识字符型长度必须介于 0 和 100 之间")
	public String getBusinessOnlyMarkVarchar() {
		return businessOnlyMarkVarchar;
	}

	public void setBusinessOnlyMarkVarchar(String businessOnlyMarkVarchar) {
		this.businessOnlyMarkVarchar = businessOnlyMarkVarchar;
	}
	
	@Length(min=0, max=10, message="业务类型长度必须介于 0 和 10 之间")
	public String getBusinesType() {
		return businesType;
	}

	public void setBusinesType(String businesType) {
		this.businesType = businesType;
	}
	
	@Length(min=0, max=10, message="操作类型长度必须介于 0 和 10 之间")
	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}
	
	@Length(min=0, max=255, message="操作内容长度必须介于 0 和 255 之间")
	public String getOperateContent() {
		return operateContent;
	}

	public void setOperateContent(String operateContent) {
		this.operateContent = operateContent;
	}
	
	public Integer getOperatorEmployeeId() {
		return operatorEmployeeId;
	}

	public void setOperatorEmployeeId(Integer operatorEmployeeId) {
		this.operatorEmployeeId = operatorEmployeeId;
	}
	
	@Length(min=0, max=10, message="操作人类型长度必须介于 0 和 10 之间")
	public String getOperatorType() {
		return operatorType;
	}

	public void setOperatorType(String operatorType) {
		this.operatorType = operatorType;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getOperateDatetime() {
		return operateDatetime;
	}

	public void setOperateDatetime(Date operateDatetime) {
		this.operateDatetime = operateDatetime;
	}

	public String getBusinesTypeName() {
		return businesTypeName;
	}

	public void setBusinesTypeName(String businesTypeName) {
		this.businesTypeName = businesTypeName;
	}

	public String getOperateTypeName() {
		return operateTypeName;
	}

	public void setOperateTypeName(String operateTypeName) {
		this.operateTypeName = operateTypeName;
	}

	public String getOperatorEmployeeName() {
		return operatorEmployeeName;
	}

	public void setOperatorEmployeeName(String operatorEmployeeName) {
		this.operatorEmployeeName = operatorEmployeeName;
	}

	public String getOperatorTypeName() {
		return operatorTypeName;
	}

	public void setOperatorTypeName(String operatorTypeName) {
		this.operatorTypeName = operatorTypeName;
	}

	public Integer getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Integer businessId) {
		this.businessId = businessId;
	}

	public Date getBeginOperateDatetime() {
		return beginOperateDatetime;
	}

	public void setBeginOperateDatetime(Date beginOperateDatetime) {
		this.beginOperateDatetime = beginOperateDatetime;
	}

	public Date getEndOperateDatetime() {
		return endOperateDatetime;
	}

	public void setEndOperateDatetime(Date endOperateDatetime) {
		this.endOperateDatetime = endOperateDatetime;
	}

	public Integer getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(Integer purchaseId) {
		this.purchaseId = purchaseId;
	}

	public String getPurchaseCode() {
		return purchaseCode;
	}

	public void setPurchaseCode(String purchaseCode) {
		this.purchaseCode = purchaseCode;
	}

	public String getPurchaseStatus() {
		return purchaseStatus;
	}

	public void setPurchaseStatus(String purchaseStatus) {
		this.purchaseStatus = purchaseStatus;
	}

	public String getPurchaseStatusName() {
		return purchaseStatusName;
	}

	public void setPurchaseStatusName(String purchaseStatusName) {
		this.purchaseStatusName = purchaseStatusName;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
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

	public String getItemManagerName() {
		return itemManagerName;
	}

	public void setItemManagerName(String itemManagerName) {
		this.itemManagerName = itemManagerName;
	}

	public String getItemManagerPhone() {
		return itemManagerPhone;
	}

	public void setItemManagerPhone(String itemManagerPhone) {
		this.itemManagerPhone = itemManagerPhone;
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

	public Integer getUrgeCount() {
		return urgeCount;
	}

	public void setUrgeCount(Integer urgeCount) {
		this.urgeCount = urgeCount;
	}
	
}