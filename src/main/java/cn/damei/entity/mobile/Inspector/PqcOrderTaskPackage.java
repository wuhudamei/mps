package cn.damei.entity.mobile.Inspector;

import java.util.Date;
import java.util.List;
import cn.damei.common.persistence.DataEntity2;

/**
 * @author qww
 * @version 创建时间：2016年9月19日 下午4:05:22
 */

public class PqcOrderTaskPackage extends DataEntity2<PqcOrderTaskPackage>{

	private static final long serialVersionUID = 1L;
	private Integer packageId; //任务包id
	private Integer orderId;//所属订单id
	private String packageName;//任务包名称
	private String stateId;//状态id
	private String stateName;// 任务包状态名称
	private Date startTime;// 开工日期
	private Date endTime; // 完工日期
	private String customerName; // 顾客信息,  姓名
	private String customerMessage;//顾客信息, 地址
	private String orderBy;//排序
	private String leaderName; //相关的工长姓名
	private String leaderId; //组长id
	private String leaderPhone; //组长手机
	private Double total;//任务包工序总价
	private Integer itemManagerId;//项目经理id
	private String orderTaskPackageCode;//订单任务包编号
	private Date actualStartdate;// 实际开工日期
	private Date actualEnddate; // 实际完工日期
	private String packageStateId; // 任务包状态Id
	private String packageStatename; // 任务包状态名称
	private Integer taskPackageTemplatId; // 任务包模板id
	private Date planStartDate;//计划开始时间
	private Date planEndDate;//计划结束时间
	
	private Double settlementAmount; // 结算金额
	private Date checkDate; // 验收日期
	private Integer settlementId; // 结算单id
	private Integer managerId; // 项目经理id
	private String managerPhone; // 项目经理电话
	private String inspectorName; // 质检员姓名
	private String inspectorPhone; // 质检员电话
	private String customerPhone; // 客户手机号
	private String isScrap ;//订单是否作废
	
	
	public String getIsScrap() {
		return isScrap;
	}

	public void setIsScrap(String isScrap) {
		this.isScrap = isScrap;
	}

	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	private List<PqcOrderTaskpackageProcedure> procedureList;
	
	public PqcOrderTaskPackage() {
		super();
	}

	public PqcOrderTaskPackage(Integer id){
		super(id);
	}
		
	public Date getPlanStartDate() {
		return planStartDate;
	}

	public void setPlanStartDate(Date planStartDate) {
		this.planStartDate = planStartDate;
	}

	public Date getPlanEndDate() {
		return planEndDate;
	}

	public void setPlanEndDate(Date planEndDate) {
		this.planEndDate = planEndDate;
	}

	public Integer getItemManagerId() {
		return itemManagerId;
	}

	public void setItemManagerId(Integer itemManagerId) {
		this.itemManagerId = itemManagerId;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public String getLeaderPhone() {
		return leaderPhone;
	}

	public void setLeaderPhone(String leaderPhone) {
		this.leaderPhone = leaderPhone;
	}

	public String getLeaderId() {
		return leaderId;
	}

	public void setLeaderId(String leaderId) {
		this.leaderId = leaderId;
	}

	public String getLeaderName() {
		return leaderName;
	}

	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerMessage() {
		return customerMessage;
	}

	public void setCustomerMessage(String customerMessage) {
		this.customerMessage = customerMessage;
	}

	

	public Integer getPackageId() {
		return packageId;
	}

	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}


	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getOrderTaskPackageCode() {
		return orderTaskPackageCode;
	}

	public void setOrderTaskPackageCode(String orderTaskPackageCode) {
		this.orderTaskPackageCode = orderTaskPackageCode;
	}

	public Date getActualStartdate() {
		return actualStartdate;
	}

	public void setActualStartdate(Date actualStartdate) {
		this.actualStartdate = actualStartdate;
	}

	public Date getActualEnddate() {
		return actualEnddate;
	}

	public void setActualEnddate(Date actualEnddate) {
		this.actualEnddate = actualEnddate;
	}

	public String getPackageStateId() {
		return packageStateId;
	}

	public void setPackageStateId(String packageStateId) {
		this.packageStateId = packageStateId;
	}

	public Integer getTaskPackageTemplatId() {
		return taskPackageTemplatId;
	}

	public void setTaskPackageTemplatId(Integer taskPackageTemplatId) {
		this.taskPackageTemplatId = taskPackageTemplatId;
	}

	public String getPackageStatename() {
		return packageStatename;
	}

	public void setPackageStatename(String packageStatename) {
		this.packageStatename = packageStatename;
	}

	public Double getSettlementAmount() {
		return settlementAmount;
	}

	public void setSettlementAmount(Double settlementAmount) {
		this.settlementAmount = settlementAmount;
	}

	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public Integer getSettlementId() {
		return settlementId;
	}

	public void setSettlementId(Integer settlementId) {
		this.settlementId = settlementId;
	}

	public List<PqcOrderTaskpackageProcedure> getProcedureList() {
		return procedureList;
	}

	public void setProcedureList(List<PqcOrderTaskpackageProcedure> procedureList) {
		this.procedureList = procedureList;
	}

	public String getManagerPhone() {
		return managerPhone;
	}

	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}

	public String getInspectorName() {
		return inspectorName;
	}

	public void setInspectorName(String inspectorName) {
		this.inspectorName = inspectorName;
	}

	public String getInspectorPhone() {
		return inspectorPhone;
	}

	public void setInspectorPhone(String inspectorPhone) {
		this.inspectorPhone = inspectorPhone;
	}
}
