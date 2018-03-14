/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.damei.common.persistence.DataEntity;

/**
 * 订单任务包审核Entity
 * @author llp
 * @version 2016-09-24
 */
public class OrderTaskpackageAudit extends DataEntity<OrderTaskpackageAudit> {
	
	private static final long serialVersionUID = 1L;
	private String storeId;		// 门店Id
	private String orderId;		// 订单Id
	private String isScrap;//是否作废

	private String picId;		// 任务包图片id
	private String packageCode;		// 任务包编号
	private String packageName;		// 任务包名称
	private Date planStartdate;		// 计划开工日期
	private Date planEnddate;		// 计划完工日期
	private Date actualStartdate;		// 实际开工日期
	private Date actualEnddate;		// 实际完工日期
	private String packageStateid;		// 任务包状态Id
	private String packageStatename;		// 任务包状态名称
	private String empGroupid;		// 工人组ID
	private String groupId;		// 组长ID
	private String groupRealname;		// 组长真实姓名
	private String itemCustomer;		// 项目经理
	private String taskPackageType;		// 任务包类型
	private String dispatcher;		// 调度员
	private String customerName;		// 客户姓名
	private String customerPhone;		// 客户姓名
	private String customerMessage;		// 客户信息
	private Date dispatchTime;		// 派工时间
	private String isOvertime;		// 是否超时 0未超时 1超时
	private String total;		// 任务包下工序的总价
	private String itemManagerId;		// 项目经理id
	
	private String orderTaskPackageCode;//
	
	private String orderNumber;//订单编号
	private String communityName;		// 小区名称
	private String buildNumber;		// 几号楼
	private String buildUnit;		// 几单元
	private String buildRoom;		// 哪一室 
	private String projectMode;//工程模式 -- '1-产业模式；4-准产业
	
	private String empRealName;//员工姓名
	private String empPhone;//员工姓名
	private Integer empId;//员工编号
	private Integer engineDepartId;
	private String departmentName;
	private List<Integer> enginDepartIds = new ArrayList<Integer>();
	
	private String customerAddress;// 客户地址
	private List<String> packageStateids = null;
	private String startPackageStateId;
	private String endPackageStateId;
	private Date packCreateTime;//生成任务包时间
	private String packCreateBy;//生成任务包操作人
	private Date packAuditTime;// 任务包审核通过时间
	private String packAuditName;// 任务包审核通过操作人

	private Date createStartDate;

	private Date createEndDate;

	public Date getCreateStartDate() {
		return createStartDate;
	}

	public void setCreateStartDate(Date createStartDate) {
		this.createStartDate = createStartDate;
	}

	public Date getCreateEndDate() {
		return createEndDate;
	}

	public void setCreateEndDate(Date createEndDate) {
		this.createEndDate = createEndDate;
	}

	public String getIsScrap() {
		return isScrap;
	}

	public void setIsScrap(String isScrap) {
		this.isScrap = isScrap;
	}

	public OrderTaskpackageAudit() {
		super();
	}

	public OrderTaskpackageAudit(String id){
		super(id);
	}

	@Length(min=1, max=64, message="门店Id长度必须介于 1 和 64 之间")
	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	
	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	@Length(min=1, max=18, message="订单Id长度必须介于 1 和 18 之间")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	@Length(min=0, max=32, message="任务包图片id长度必须介于 0 和 32 之间")
	public String getPicId() {
		return picId;
	}

	public String getEmpRealName() {
		return empRealName;
	}

	public void setEmpRealName(String empRealName) {
		this.empRealName = empRealName;
	}

	public String getEmpPhone() {
		return empPhone;
	}

	public void setEmpPhone(String empPhone) {
		this.empPhone = empPhone;
	}

	public void setPicId(String picId) {
		this.picId = picId;
	}
	
	@Length(min=1, max=18, message="任务包编号长度必须介于 1 和 18 之间")
	public String getPackageCode() {
		return packageCode;
	}

	public void setPackageCode(String packageCode) {
		this.packageCode = packageCode;
	}
	
	@Length(min=1, max=100, message="任务包名称长度必须介于 1 和 100 之间")
	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPlanStartdate() {
		return planStartdate;
	}

	public void setPlanStartdate(Date planStartdate) {
		this.planStartdate = planStartdate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPlanEnddate() {
		return planEnddate;
	}

	public void setPlanEnddate(Date planEnddate) {
		this.planEnddate = planEnddate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getActualStartdate() {
		return actualStartdate;
	}

	public void setActualStartdate(Date actualStartdate) {
		this.actualStartdate = actualStartdate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getActualEnddate() {
		return actualEnddate;
	}

	public void setActualEnddate(Date actualEnddate) {
		this.actualEnddate = actualEnddate;
	}
	
	@Length(min=1, max=5, message="任务包状态Id长度必须介于 1 和 5 之间")
	public String getPackageStateid() {
		return packageStateid;
	}

	public void setPackageStateid(String packageStateid) {
		this.packageStateid = packageStateid;
	}
	
	@Length(min=1, max=64, message="任务包状态名称长度必须介于 1 和 64 之间")
	public String getPackageStatename() {
		return packageStatename;
	}

	public void setPackageStatename(String packageStatename) {
		this.packageStatename = packageStatename;
	}
	
	@Length(min=0, max=255, message="工人组ID长度必须介于 0 和 255 之间")
	public String getEmpGroupid() {
		return empGroupid;
	}

	public void setEmpGroupid(String empGroupid) {
		this.empGroupid = empGroupid;
	}
	
	@Length(min=0, max=64, message="组长ID长度必须介于 0 和 64 之间")
	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
	@Length(min=0, max=255, message="组长真实姓名长度必须介于 0 和 255 之间")
	public String getGroupRealname() {
		return groupRealname;
	}

	public void setGroupRealname(String groupRealname) {
		this.groupRealname = groupRealname;
	}
	
	@Length(min=0, max=255, message="项目经理长度必须介于 0 和 255 之间")
	public String getItemCustomer() {
		return itemCustomer;
	}

	public void setItemCustomer(String itemCustomer) {
		this.itemCustomer = itemCustomer;
	}
	
	@Length(min=0, max=32, message="任务包类型长度必须介于 0 和 32 之间")
	public String getTaskPackageType() {
		return taskPackageType;
	}

	public void setTaskPackageType(String taskPackageType) {
		this.taskPackageType = taskPackageType;
	}
	
	@Length(min=0, max=255, message="调度员长度必须介于 0 和 255 之间")
	public String getDispatcher() {
		return dispatcher;
	}

	public void setDispatcher(String dispatcher) {
		this.dispatcher = dispatcher;
	}
	
	@Length(min=0, max=255, message="客户姓名长度必须介于 0 和 255 之间")
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	@Length(min=0, max=255, message="客户信息长度必须介于 0 和 255 之间")
	public String getCustomerMessage() {
		return customerMessage;
	}

	public void setCustomerMessage(String customerMessage) {
		this.customerMessage = customerMessage;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDispatchTime() {
		return dispatchTime;
	}

	public void setDispatchTime(Date dispatchTime) {
		this.dispatchTime = dispatchTime;
	}
	
	@Length(min=0, max=1, message="是否超时 0未超时 1超时长度必须介于 0 和 1 之间")
	public String getIsOvertime() {
		return isOvertime;
	}

	public void setIsOvertime(String isOvertime) {
		this.isOvertime = isOvertime;
	}
	
	@Length(min=0, max=50, message="任务包下工序的总价长度必须介于 0 和 50 之间")
	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}
	
	@Length(min=0, max=100, message="项目经理id长度必须介于 0 和 100 之间")
	public String getItemManagerId() {
		return itemManagerId;
	}

	public void setItemManagerId(String itemManagerId) {
		this.itemManagerId = itemManagerId;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getOrderTaskPackageCode() {
		return orderTaskPackageCode;
	}

	public void setOrderTaskPackageCode(String orderTaskPackageCode) {
		this.orderTaskPackageCode = orderTaskPackageCode;
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

	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}

	public Integer getEngineDepartId() {
		return engineDepartId;
	}

	public void setEngineDepartId(Integer enginDepartId) {
		this.engineDepartId = enginDepartId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public List<Integer> getEnginDepartIds() {
		return enginDepartIds;
	}

	public void setEnginDepartIds(List<Integer> enginDepartIds) {
		this.enginDepartIds = enginDepartIds;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public List<String> getPackageStateids() {
		return packageStateids;
	}

	public void setPackageStateids(List<String> packageStateids) {
		this.packageStateids = packageStateids;
	}

	public String getStartPackageStateId() {
		return startPackageStateId;
	}

	public void setStartPackageStateId(String startPackageStateId) {
		this.startPackageStateId = startPackageStateId;
	}

	public String getEndPackageStateId() {
		return endPackageStateId;
	}

	public void setEndPackageStateId(String endPackageStateId) {
		this.endPackageStateId = endPackageStateId;
	}

	public Date getPackCreateTime() {
		return packCreateTime;
	}

	public void setPackCreateTime(Date packCreateTime) {
		this.packCreateTime = packCreateTime;
	}

	public String getPackCreateBy() {
		return packCreateBy;
	}

	public void setPackCreateBy(String packCreateBy) {
		this.packCreateBy = packCreateBy;
	}

	public Date getPackAuditTime() {
		return packAuditTime;
	}

	public void setPackAuditTime(Date packAuditTime) {
		this.packAuditTime = packAuditTime;
	}

	public String getPackAuditName() {
		return packAuditName;
	}

	public void setPackAuditName(String packAuditName) {
		this.packAuditName = packAuditName;
	}
}