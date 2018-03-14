/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import cn.damei.common.persistence.DataEntity;

/**
 * 工人组管理Entity
 * 
 * @author qhy
 * @version 2016-09-01
 */
public class BizEmployeegroupVO extends DataEntity<BizEmployeegroupVO> {

	private static final long serialVersionUID = 1L;

	String id;
	String storeId; // 门店Id
	String storeName;// 门店名称
	String elactricationId;// 工程部ID
	String elactricationName;// 工程部名称
	String empId;// 组长ID
	String leaderRealName;// 组长姓名
	String leaderRealNamezhen;// 组长姓名 供应商使用
	String empCount;// 组员数量
	String empIds;// 组员id逗号分隔
	String empRealNames;// 组员名称逗号分隔
	String taskPackageId;// 任务包
	String leaderPhone;// 组长手机号
	Integer orderstop;
	Integer state;
	Integer star; // 星级
	private Integer workType; // 工种
	private List<Integer> enginDepartIds = new ArrayList<Integer>();
	private Integer noInDepartment;
	private String address;// 地址
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date freeBeginDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date freeEndDate;

	private Date planStartDate;// 计划开工
	private Date planEndDate;// 计划结束

	private Integer workerGroupId;

	private String recommendManagerName; // 推荐项目经的姓名
	
	private String orderStopReason;       //停单原因
	private String orderStopReasonType;       //停单原因类型
	private String orderStopOperatorEmployeeId;//停单操作人
	private Date orderStopOperateDatetime;  //停单操作时间;
	
	private Double gotScore;  //获取总分
	private Integer ciShu;		//获取次数
	private Integer groupLeaderEmployeeId;  //工人组ID
	
	private Integer reason;    //工人组星级调整原因
	private String changeDescribe;  //工人组星级调整修改说明
	
	private Integer starBefore;    //变更前星级
	private Integer starAfter;     		//变更后星级
	private Date starChangeDatetime;	//变更时间
	private Date startChangeDatetime;
	private Date endChangeDatetime;
	
	

	public Date getStartChangeDatetime() {
		return startChangeDatetime;
	}

	public void setStartChangeDatetime(Date startChangeDatetime) {
		this.startChangeDatetime = startChangeDatetime;
	}

	public Date getEndChangeDatetime() {
		return endChangeDatetime;
	}

	public void setEndChangeDatetime(Date endChangeDatetime) {
		this.endChangeDatetime = endChangeDatetime;
	}

	public Integer getStarBefore() {
		return starBefore;
	}

	public void setStarBefore(Integer starBefore) {
		this.starBefore = starBefore;
	}

	public Integer getStarAfter() {
		return starAfter;
	}

	public void setStarAfter(Integer starAfter) {
		this.starAfter = starAfter;
	}

	public Date getStarChangeDatetime() {
		return starChangeDatetime;
	}

	public void setStarChangeDatetime(Date starChangeDatetime) {
		this.starChangeDatetime = starChangeDatetime;
	}

	public Integer getReason() {
		return reason;
	}

	public void setReason(Integer reason) {
		this.reason = reason;
	}

	public String getChangeDescribe() {
		return changeDescribe;
	}

	public void setChangeDescribe(String changeDescribe) {
		this.changeDescribe = changeDescribe;
	}

	public Double getGotScore() {
		return gotScore;
	}

	public void setGotScore(Double gotScore) {
		this.gotScore = gotScore;
	}

	public Integer getCiShu() {
		return ciShu;
	}

	public void setCiShu(Integer ciShu) {
		this.ciShu = ciShu;
	}


	public Integer getGroupLeaderEmployeeId() {
		return groupLeaderEmployeeId;
	}

	public void setGroupLeaderEmployeeId(Integer groupLeaderEmployeeId) {
		this.groupLeaderEmployeeId = groupLeaderEmployeeId;
	}

	public Date getOrderStopOperateDatetime() {
		return orderStopOperateDatetime;
	}

	public void setOrderStopOperateDatetime(Date orderStopOperateDatetime) {
		this.orderStopOperateDatetime = orderStopOperateDatetime;
	}

	public String getOrderStopReasonType() {
		return orderStopReasonType;
	}

	public void setOrderStopReasonType(String orderStopReasonType) {
		this.orderStopReasonType = orderStopReasonType;
	}

	public String getOrderStopReason() {
		return orderStopReason;
	}

	public void setOrderStopReason(String orderStopReason) {
		this.orderStopReason = orderStopReason;
	}

	public String getOrderStopOperatorEmployeeId() {
		return orderStopOperatorEmployeeId;
	}

	public void setOrderStopOperatorEmployeeId(String orderStopOperatorEmployeeId) {
		this.orderStopOperatorEmployeeId = orderStopOperatorEmployeeId;
	}

	public String getRecommendManagerName() {
		return recommendManagerName;
	}

	public void setRecommendManagerName(String recommendManagerName) {
		this.recommendManagerName = recommendManagerName;
	}

	public String getLeaderRealNamezhen() {
		return leaderRealNamezhen;
	}

	public void setLeaderRealNamezhen(String leaderRealNamezhen) {
		this.leaderRealNamezhen = leaderRealNamezhen;
	}

	public Integer getWorkerGroupId() {
		return workerGroupId;
	}

	public void setWorkerGroupId(Integer workerGroupId) {
		this.workerGroupId = workerGroupId;
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

	private String projectMode;

	private String packageName;

	private Integer taskPackageTemplatId;

	public Integer getTaskPackageTemplatId() {
		return taskPackageTemplatId;
	}

	public void setTaskPackageTemplatId(Integer taskPackageTemplatId) {
		this.taskPackageTemplatId = taskPackageTemplatId;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}

	public Date getFreeBeginDate() {
		return freeBeginDate;
	}

	public void setFreeBeginDate(Date freeBeginDate) {
		this.freeBeginDate = freeBeginDate;
	}

	public Date getFreeEndDate() {
		return freeEndDate;
	}

	public void setFreeEndDate(Date freeEndDate) {
		this.freeEndDate = freeEndDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getOrderstop() {
		return orderstop;
	}

	public void setOrderstop(Integer orderstop) {
		this.orderstop = orderstop;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	List<BizEmgrouprelation> bizEmgrouprelationList;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getElactricationId() {
		return elactricationId;
	}

	public void setElactricationId(String elactricationId) {
		this.elactricationId = elactricationId;
	}

	public String getElactricationName() {
		return elactricationName;
	}

	public void setElactricationName(String elactricationName) {
		this.elactricationName = elactricationName;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getLeaderRealName() {
		return leaderRealName;
	}

	public void setLeaderRealName(String leaderRealName) {
		this.leaderRealName = leaderRealName;
	}

	public String getEmpCount() {
		return empCount;
	}

	public void setEmpCount(String empCount) {
		this.empCount = empCount;
	}

	public String getEmpIds() {
		return empIds;
	}

	public void setEmpIds(String empIds) {
		this.empIds = empIds;
	}

	public String getEmpRealNames() {
		return empRealNames;
	}

	public void setEmpRealNames(String empRealNames) {
		this.empRealNames = empRealNames;
	}

	public String getTaskPackageId() {
		return taskPackageId;
	}

	public void setTaskPackageId(String taskPackageId) {
		this.taskPackageId = taskPackageId;
	}

	public String getLeaderPhone() {
		return leaderPhone;
	}

	public void setLeaderPhone(String leaderPhone) {
		this.leaderPhone = leaderPhone;
	}

	public List<BizEmgrouprelation> getBizEmgrouprelationList() {
		return bizEmgrouprelationList;
	}

	public void setBizEmgrouprelationList(List<BizEmgrouprelation> bizEmgrouprelationList) {
		this.bizEmgrouprelationList = bizEmgrouprelationList;
	}

	public Integer getStar() {
		return star;
	}

	public void setStar(Integer star) {
		this.star = star;
	}

	public Integer getWorkType() {
		return workType;
	}

	public void setWorkType(Integer workType) {
		this.workType = workType;
	}

	public List<Integer> getEnginDepartIds() {
		return enginDepartIds;
	}

	public void setEnginDepartIds(List<Integer> enginDepartIds) {
		this.enginDepartIds = enginDepartIds;
	}

	public Integer getNoInDepartment() {
		return noInDepartment;
	}

	public void setNoInDepartment(Integer noInDepartment) {
		this.noInDepartment = noInDepartment;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((bizEmgrouprelationList == null) ? 0 : bizEmgrouprelationList.hashCode());
		result = prime * result + ((elactricationId == null) ? 0 : elactricationId.hashCode());
		result = prime * result + ((elactricationName == null) ? 0 : elactricationName.hashCode());
		result = prime * result + ((empCount == null) ? 0 : empCount.hashCode());
		result = prime * result + ((empId == null) ? 0 : empId.hashCode());
		result = prime * result + ((empIds == null) ? 0 : empIds.hashCode());
		result = prime * result + ((empRealNames == null) ? 0 : empRealNames.hashCode());
		result = prime * result + ((enginDepartIds == null) ? 0 : enginDepartIds.hashCode());
		result = prime * result + ((freeBeginDate == null) ? 0 : freeBeginDate.hashCode());
		result = prime * result + ((freeEndDate == null) ? 0 : freeEndDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((leaderPhone == null) ? 0 : leaderPhone.hashCode());
		result = prime * result + ((leaderRealName == null) ? 0 : leaderRealName.hashCode());
		result = prime * result + ((noInDepartment == null) ? 0 : noInDepartment.hashCode());
		result = prime * result + ((orderstop == null) ? 0 : orderstop.hashCode());
		result = prime * result + ((packageName == null) ? 0 : packageName.hashCode());
		result = prime * result + ((planEndDate == null) ? 0 : planEndDate.hashCode());
		result = prime * result + ((planStartDate == null) ? 0 : planStartDate.hashCode());
		result = prime * result + ((projectMode == null) ? 0 : projectMode.hashCode());
		result = prime * result + ((star == null) ? 0 : star.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((storeId == null) ? 0 : storeId.hashCode());
		result = prime * result + ((storeName == null) ? 0 : storeName.hashCode());
		result = prime * result + ((taskPackageId == null) ? 0 : taskPackageId.hashCode());
		result = prime * result + ((taskPackageTemplatId == null) ? 0 : taskPackageTemplatId.hashCode());
		result = prime * result + ((workType == null) ? 0 : workType.hashCode());
		result = prime * result + ((workerGroupId == null) ? 0 : workerGroupId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		BizEmployeegroupVO other = (BizEmployeegroupVO) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (bizEmgrouprelationList == null) {
			if (other.bizEmgrouprelationList != null)
				return false;
		} else if (!bizEmgrouprelationList.equals(other.bizEmgrouprelationList))
			return false;
		if (elactricationId == null) {
			if (other.elactricationId != null)
				return false;
		} else if (!elactricationId.equals(other.elactricationId))
			return false;
		if (elactricationName == null) {
			if (other.elactricationName != null)
				return false;
		} else if (!elactricationName.equals(other.elactricationName))
			return false;
		if (empCount == null) {
			if (other.empCount != null)
				return false;
		} else if (!empCount.equals(other.empCount))
			return false;
		if (empId == null) {
			if (other.empId != null)
				return false;
		} else if (!empId.equals(other.empId))
			return false;
		if (empIds == null) {
			if (other.empIds != null)
				return false;
		} else if (!empIds.equals(other.empIds))
			return false;
		if (empRealNames == null) {
			if (other.empRealNames != null)
				return false;
		} else if (!empRealNames.equals(other.empRealNames))
			return false;
		if (enginDepartIds == null) {
			if (other.enginDepartIds != null)
				return false;
		} else if (!enginDepartIds.equals(other.enginDepartIds))
			return false;
		if (freeBeginDate == null) {
			if (other.freeBeginDate != null)
				return false;
		} else if (!freeBeginDate.equals(other.freeBeginDate))
			return false;
		if (freeEndDate == null) {
			if (other.freeEndDate != null)
				return false;
		} else if (!freeEndDate.equals(other.freeEndDate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (leaderPhone == null) {
			if (other.leaderPhone != null)
				return false;
		} else if (!leaderPhone.equals(other.leaderPhone))
			return false;
		if (leaderRealName == null) {
			if (other.leaderRealName != null)
				return false;
		} else if (!leaderRealName.equals(other.leaderRealName))
			return false;
		if (noInDepartment == null) {
			if (other.noInDepartment != null)
				return false;
		} else if (!noInDepartment.equals(other.noInDepartment))
			return false;
		if (orderstop == null) {
			if (other.orderstop != null)
				return false;
		} else if (!orderstop.equals(other.orderstop))
			return false;
		if (packageName == null) {
			if (other.packageName != null)
				return false;
		} else if (!packageName.equals(other.packageName))
			return false;
		if (planEndDate == null) {
			if (other.planEndDate != null)
				return false;
		} else if (!planEndDate.equals(other.planEndDate))
			return false;
		if (planStartDate == null) {
			if (other.planStartDate != null)
				return false;
		} else if (!planStartDate.equals(other.planStartDate))
			return false;
		if (projectMode == null) {
			if (other.projectMode != null)
				return false;
		} else if (!projectMode.equals(other.projectMode))
			return false;
		if (star == null) {
			if (other.star != null)
				return false;
		} else if (!star.equals(other.star))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (storeId == null) {
			if (other.storeId != null)
				return false;
		} else if (!storeId.equals(other.storeId))
			return false;
		if (storeName == null) {
			if (other.storeName != null)
				return false;
		} else if (!storeName.equals(other.storeName))
			return false;
		if (taskPackageId == null) {
			if (other.taskPackageId != null)
				return false;
		} else if (!taskPackageId.equals(other.taskPackageId))
			return false;
		if (taskPackageTemplatId == null) {
			if (other.taskPackageTemplatId != null)
				return false;
		} else if (!taskPackageTemplatId.equals(other.taskPackageTemplatId))
			return false;
		if (workType == null) {
			if (other.workType != null)
				return false;
		} else if (!workType.equals(other.workType))
			return false;
		if (workerGroupId == null) {
			if (other.workerGroupId != null)
				return false;
		} else if (!workerGroupId.equals(other.workerGroupId))
			return false;
		return true;
	}

}