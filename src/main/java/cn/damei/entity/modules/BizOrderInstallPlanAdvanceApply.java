/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import cn.damei.common.persistence.DataEntity2;

/**
 * 主材安装项提前申请记录Entity
 * @author wyb
 */
public class BizOrderInstallPlanAdvanceApply extends DataEntity2<BizOrderInstallPlanAdvanceApply> {
	
	private static final long serialVersionUID = 1L;
	private Integer orderId;		// 订单id
	private String applyType;		// 申请类型（安装/复尺）
	private Integer orderInstallPlanId;		// 安装项计划id
	private String installItemName;		// 安装项计划名称
	private Date oldPlanApplyDate;		// 原计划可申请日期
	private String dealStatus;		// 处理状态
	private Integer dealEmployeeId;		// 处理人id
	private Date delaTime;		// 处理时间
	
	
	private String dealStatusName;		// 处理状态 名称
	private String dealEmployeeName;		// 处理人 名称
	
	private Integer storeId;	//门店
	private String storeName;  //门店名称
	private String projectMode;		//工程模式
	private String projectModeName;		//工程模式名称
	private Integer engineDepartId;  //区域id
	private String engineDepartName;  //区域名称
	private String orderNumber;  //订单编号
	private String customerName; //客户姓名
	private String customerPhone; //客户电话
	private Integer itemManagerId;	 //项目经理id
	private String itemManager;	 //项目经理名称
	private String itemManagerPhone;	 //项目经理名称电话
	private Date actualStartDate;  //实际开工日期
	
	private String communityName;	 //小区
	private String buildNumber;	 //楼
	private String buildUnit;	 //单元
	private String buildRoom;	 //室
	
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