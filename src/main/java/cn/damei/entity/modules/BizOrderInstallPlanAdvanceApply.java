
package cn.damei.entity.modules;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import cn.damei.common.persistence.DataEntity2;


public class BizOrderInstallPlanAdvanceApply extends DataEntity2<BizOrderInstallPlanAdvanceApply> {
	
	private static final long serialVersionUID = 1L;
	private Integer orderId;
	private String applyType;
	private Integer orderInstallPlanId;
	private String installItemName;
	private Date oldPlanApplyDate;
	private String dealStatus;
	private Integer dealEmployeeId;
	private Date delaTime;
	
	
	private String dealStatusName;
	private String dealEmployeeName;
	
	private Integer storeId;
	private String storeName;
	private String projectMode;
	private String projectModeName;
	private Integer engineDepartId;
	private String engineDepartName;
	private String orderNumber;
	private String customerName;
	private String customerPhone;
	private Integer itemManagerId;
	private String itemManager;
	private String itemManagerPhone;
	private Date actualStartDate;
	
	private String communityName;
	private String buildNumber;
	private String buildUnit;
	private String buildRoom;
	
	public BizOrderInstallPlanAdvanceApply() {
		super();
	}

	public BizOrderInstallPlanAdvanceApply(Integer id){
		super(id);
	}

	@NotNull(message="订单id不能为空")
	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	@Length(min=1, max=10, message="申请类型（安装/复尺）长度必须介于 1 和 10 之间")
	public String getApplyType() {
		return applyType;
	}

	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}
	
	@NotNull(message="安装项计划id不能为空")
	public Integer getOrderInstallPlanId() {
		return orderInstallPlanId;
	}

	public void setOrderInstallPlanId(Integer orderInstallPlanId) {
		this.orderInstallPlanId = orderInstallPlanId;
	}
	
	@Length(min=1, max=100, message="安装项计划名称长度必须介于 1 和 100 之间")
	public String getInstallItemName() {
		return installItemName;
	}

	public void setInstallItemName(String installItemName) {
		this.installItemName = installItemName;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="原计划可申请日期不能为空")
	public Date getOldPlanApplyDate() {
		return oldPlanApplyDate;
	}

	public void setOldPlanApplyDate(Date oldPlanApplyDate) {
		this.oldPlanApplyDate = oldPlanApplyDate;
	}
	
	@Length(min=1, max=10, message="处理状态长度必须介于 1 和 10 之间")
	public String getDealStatus() {
		return dealStatus;
	}

	public void setDealStatus(String dealStatus) {
		this.dealStatus = dealStatus;
	}
	
	public Integer getDealEmployeeId() {
		return dealEmployeeId;
	}

	public void setDealEmployeeId(Integer dealEmployeeId) {
		this.dealEmployeeId = dealEmployeeId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDelaTime() {
		return delaTime;
	}

	public void setDelaTime(Date delaTime) {
		this.delaTime = delaTime;
	}

	public String getDealStatusName() {
		return dealStatusName;
	}

	public void setDealStatusName(String dealStatusName) {
		this.dealStatusName = dealStatusName;
	}

	public String getDealEmployeeName() {
		return dealEmployeeName;
	}

	public void setDealEmployeeName(String dealEmployeeName) {
		this.dealEmployeeName = dealEmployeeName;
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

	public String getItemManager() {
		return itemManager;
	}

	public void setItemManager(String itemManager) {
		this.itemManager = itemManager;
	}

	public Date getActualStartDate() {
		return actualStartDate;
	}

	public void setActualStartDate(Date actualStartDate) {
		this.actualStartDate = actualStartDate;
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

	public Integer getItemManagerId() {
		return itemManagerId;
	}

	public void setItemManagerId(Integer itemManagerId) {
		this.itemManagerId = itemManagerId;
	}

	public String getItemManagerPhone() {
		return itemManagerPhone;
	}

	public void setItemManagerPhone(String itemManagerPhone) {
		this.itemManagerPhone = itemManagerPhone;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	
	
}