package cn.damei.entity.modules;

import java.util.Date;
import java.util.List;

import cn.damei.common.persistence.DataEntity2;

/**
 * 项目经理材料结算信息实体类
 * 
 * @author hyh
 *
 */
public class PmMaterialsSettleInfo extends DataEntity2<PmMaterialsSettleInfo> {

	private static final long serialVersionUID = 1L;
	private Integer storeId; // 门店id
	private Integer enginDepartId;
	private String engineDepartName; // 区域
	private Integer orderId; // 订单Id
	private String orderNumber; // 订单编号
	private Date auditDate; // 结算员审核通过时间
	private String settleStatus; // 任务包材料结算单状态
	private String communityName; // 小区名称
	private String buildNumber; // 几号楼
	private String buildUnit; // 几单元
	private String buildRoom; // 哪一室
	private String customerName; // 客户姓名
	private String itemManager; // 项目经理
	private String itemPhone; // 项目经理手机号
	private Integer taskPackageId;// 任务包Id
	private String taskPackageNo;// 任务包编号
	private String taskPackageName;// 任务包名称
	private Integer settleId;//结算单Id
	private Double auxiliaryMaterialsSettleAmount;// 辅料结算金额
	private Double auxiliaryMaterialsAmount;// 辅料扣款金额
	private Double sandCementAmount;// 沙子水泥扣款金额
	private Double pmMaterialsSettleAmount;// 项目经理结算金额
	private List<String> status; 
	
	
	public Integer getSettleId() {
		return settleId;
	}

	public void setSettleId(Integer settleId) {
		this.settleId = settleId;
	}

	public List<String> getStatus() {
		return status;
	}

	public void setStatus(List<String> status) {
		this.status = status;
	}

	public Integer getEnginDepartId() {
		return enginDepartId;
	}

	public void setEnginDepartId(Integer enginDepartId) {
		this.enginDepartId = enginDepartId;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getEngineDepartName() {
		return engineDepartName;
	}

	public void setEngineDepartName(String engineDepartName) {
		this.engineDepartName = engineDepartName;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Date getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}

	public String getSettleStatus() {
		return settleStatus;
	}

	public void setSettleStatus(String settleStatus) {
		this.settleStatus = settleStatus;
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

	public String getItemManager() {
		return itemManager;
	}

	public void setItemManager(String itemManager) {
		this.itemManager = itemManager;
	}

	public String getItemPhone() {
		return itemPhone;
	}

	public void setItemPhone(String itemPhone) {
		this.itemPhone = itemPhone;
	}

	public Integer getTaskPackageId() {
		return taskPackageId;
	}

	public void setTaskPackageId(Integer taskPackageId) {
		this.taskPackageId = taskPackageId;
	}

	public String getTaskPackageNo() {
		return taskPackageNo;
	}

	public void setTaskPackageNo(String taskPackageNo) {
		this.taskPackageNo = taskPackageNo;
	}

	public String getTaskPackageName() {
		return taskPackageName;
	}

	public void setTaskPackageName(String taskPackageName) {
		this.taskPackageName = taskPackageName;
	}

	public Double getAuxiliaryMaterialsSettleAmount() {
		return auxiliaryMaterialsSettleAmount;
	}

	public void setAuxiliaryMaterialsSettleAmount(Double auxiliaryMaterialsSettleAmount) {
		this.auxiliaryMaterialsSettleAmount = auxiliaryMaterialsSettleAmount;
	}

	public Double getAuxiliaryMaterialsAmount() {
		return auxiliaryMaterialsAmount;
	}

	public void setAuxiliaryMaterialsAmount(Double auxiliaryMaterialsAmount) {
		this.auxiliaryMaterialsAmount = auxiliaryMaterialsAmount;
	}

	public Double getSandCementAmount() {
		return sandCementAmount;
	}

	public void setSandCementAmount(Double sandCementAmount) {
		this.sandCementAmount = sandCementAmount;
	}

	public Double getPmMaterialsSettleAmount() {
		return pmMaterialsSettleAmount;
	}

	public void setPmMaterialsSettleAmount(Double pmMaterialsSettleAmount) {
		this.pmMaterialsSettleAmount = pmMaterialsSettleAmount;
	}

}
