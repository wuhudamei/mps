
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.damei.common.persistence.DataEntity;


public class OrderTaskpackage extends DataEntity<OrderTaskpackage> {

	private Double lng;
	private Double lat;
	private Integer engineDepartId;
	private String engineDepartName;
	private String isScrap;
	private static final long serialVersionUID = 1L;
	private String storeId;
	private String projectMode;
	private String orderId;
	private String orderNumber;
	private String packageCode;
	private String packageName;
	private Date planStartdate;
	private Date planEnddate;
	private Date actualStartdate;
	private Date actualEnddate;
	private String packageStateId;
	private String packageStatename;
	private String empGroupid;
	private String groupId;
	private String groupRealname;
	private String itemCustomer;
	private String taskPackageType;
	private String customerName;
	private String dispatcher;
	private String customerCenter;
	private String  xiaoqu;
	private String lou;
	private String danyuan;
	private String shi;
	private String customerPhone;
	private String isZero;
	private String isSpecialPack;
	private String orderTaskpackageCode;
	
	private String  leaflet;
	private Date leafletDate;
	
	private Date beginleafletdate;
	private Date endleafletdate;
	
	private String customerMessage;
	private Date dispatchTime;
	private Double laborAuxiliaryMaterialsBudgetAmount;
	private Double laborBudgetAmount;
	private Double auxiliaryMaterialsBudgetAmount;
	private String isOvertime;
	private String itemManagerId;
	private String managerPhone;
	private int taskTackageTempId;
	private String orderTaskPackageCode;
	private String time;
	private String settleStyle;

	public String getIsScrap() {
		return isScrap;
	}

	public void setIsScrap(String isScrap) {
		this.isScrap = isScrap;
	}

	public List<WorkgroupVo> getList() {
		return list;
	}

	public void setList(List<WorkgroupVo> list) {
		this.list = list;
	}

	private List<WorkgroupVo> list;
	public String getEngineDepartName() {
		return engineDepartName;
	}


	public void setEngineDepartName(String engineDepartName) {
		this.engineDepartName = engineDepartName;
	}


	public Integer getEngineDepartId() {
		return engineDepartId;
	}


	public void setEngineDepartId(Integer engineDepartId) {
		this.engineDepartId = engineDepartId;
	}


	public Double getLng() {
		return lng;
	}


	public void setLng(Double lng) {
		this.lng = lng;
	}


	public Double getLat() {
		return lat;
	}


	public void setLat(Double lat) {
		this.lat = lat;
	}


	public String getXiaoqu() {
		return xiaoqu;
	}


	public void setXiaoqu(String xiaoqu) {
		this.xiaoqu = xiaoqu;
	}

	public String getLou() {
		return lou;
	}

	public void setLou(String lou) {
		this.lou = lou;
	}

	public String getDanyuan() {
		return danyuan;
	}

	public void setDanyuan(String danyuan) {
		this.danyuan = danyuan;
	}

	public String getShi() {
		return shi;
	}

	public void setShi(String shi) {
		this.shi = shi;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	
	
	

	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}

	public Date getBeginleafletdate() {
		return beginleafletdate;
	}

	public void setBeginleafletdate(Date beginleafletdate) {
		this.beginleafletdate = beginleafletdate;
	}

	public Date getEndleafletdate() {
		return endleafletdate;
	}

	public void setEndleafletdate(Date endleafletdate) {
		this.endleafletdate = endleafletdate;
	}

	public String getOrderTaskpackageCode() {
		return orderTaskpackageCode;
	}

	public void setOrderTaskpackageCode(String orderTaskpackageCode) {
		this.orderTaskpackageCode = orderTaskpackageCode;
	}

	public String getLeaflet() {
		return leaflet;
	}

	public void setLeaflet(String leaflet) {
		this.leaflet = leaflet;
	}

	public Date getLeafletDate() {
		return leafletDate;
	}

	public void setLeafletDate(Date leafletDate) {
		this.leafletDate = leafletDate;
	}

	public String getIsSpecialPack() {
		return isSpecialPack;
	}


	public void setIsSpecialPack(String isSpecialPack) {
		this.isSpecialPack = isSpecialPack;
	}


	public String getCustomerCenter() {
		return customerCenter;
	}

	public void setCustomerCenter(String customerCenter) {
		this.customerCenter = customerCenter;
	}

	public String getManagerPhone() {
		return managerPhone;
	}

	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}
	
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public OrderTaskpackage() {
		super();
	}

	public OrderTaskpackage(String id){
		super(id);
	}

	
	
	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	@Length(min=1, max=64, message="门店Id长度必须介于 1 和 64 之间")
	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	
	@Length(min=1, max=18, message="订单Id长度必须介于 1 和 18 之间")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
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
	public String getPackageStateId() {
		return packageStateId;
	}

	public void setPackageStateId(String packageStateId) {
		this.packageStateId = packageStateId;
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

	@Length(min=0, max=255, message="客户姓名长度必须介于 0 和 255 之间")
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@Length(min=0, max=255, message="调度员长度必须介于 0 和 255 之间")
	public String getDispatcher() {
		return dispatcher;
	}

	public void setDispatcher(String dispatcher) {
		this.dispatcher = dispatcher;
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

	public String getIsOvertime() {
		return isOvertime;
	}

	public void setIsOvertime(String isOvertime) {
		this.isOvertime = isOvertime;
	}

	public Double getLaborAuxiliaryMaterialsBudgetAmount() {
		return laborAuxiliaryMaterialsBudgetAmount;
	}

	public void setLaborAuxiliaryMaterialsBudgetAmount(Double laborAuxiliaryMaterialsBudgetAmount) {
		this.laborAuxiliaryMaterialsBudgetAmount = laborAuxiliaryMaterialsBudgetAmount;
	}

	public String getItemManagerId() {
		return itemManagerId;
	}

	public void setItemManagerId(String itemManagerId) {
		this.itemManagerId = itemManagerId;
	}

	public int getTaskTackageTempId() {
		return taskTackageTempId;
	}

	public void setTaskTackageTempId(int taskTackageTempId) {
		this.taskTackageTempId = taskTackageTempId;
	}

	public String getOrderTaskPackageCode() {
		return orderTaskPackageCode;
	}

	public void setOrderTaskPackageCode(String orderTaskPackageCode) {
		this.orderTaskPackageCode = orderTaskPackageCode;
	}
	public String getIsZero() {
		return isZero;
	}
	public void setIsZero(String isZero) {
		this.isZero = isZero;
	}

	public Double getLaborBudgetAmount() {
		return laborBudgetAmount;
	}

	public void setLaborBudgetAmount(Double laborBudgetAmount) {
		this.laborBudgetAmount = laborBudgetAmount;
	}

	public Double getAuxiliaryMaterialsBudgetAmount() {
		return auxiliaryMaterialsBudgetAmount;
	}

	public void setAuxiliaryMaterialsBudgetAmount(Double auxiliaryMaterialsBudgetAmount) {
		this.auxiliaryMaterialsBudgetAmount = auxiliaryMaterialsBudgetAmount;
	}

	public String getSettleStyle() {
		return settleStyle;
	}

	public void setSettleStyle(String settleStyle) {
		this.settleStyle = settleStyle;
	}
}