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
 * 评价指标设置Entity
 * @author ws
 * @version 2017-09-14
 */
public class BizWorkerScore extends DataEntity<BizWorkerScore> implements Comparable<BizWorkerScore> {
	
	private static final long serialVersionUID = 1L;
	
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
	private List<BizEmgrouprelation>empGropRelation;//工人组关联关系表
	private Date planStartDate;// 计划开工
	private Date planEndDate;// 计划结束

	private Integer workerGroupId;

	private String recommendManagerName; // 推荐项目经的姓名
	
	private String orderStopReason;       //停单原因
	private String orderStopReasonType;       //停单原因类型
	private String orderStopOperatorEmployeeId;//停单操作人
	private Date orderStopOperateDatetime;  //停单操作时间;
	
	private String projectMode;//工程模式

	
	private String starScore;    //平均分
	
	
	private Integer sort;//排名
	
	
	private Date gradeDate;//评分时间
	
	private String groupRealName;//工人组长
	
	private String customerMessage;//客户地址信息
	
	private String customerName;//客户姓名
	
	private Integer packageId;
	
	private String packageName;//任务包名称
	
	private Integer evalRoleType;//评价角色类型 1：项目经理  2：质检   3:客户
	
	private Double evaltotalScore;//总分
	
	private Double gradtotalScore;//实际得分
	
	private String starScore1;    //平均分比较
	
	private String starScore2;    //平均分比较
	
	private Integer sort1;//排名比较
	
	private Integer sort2;//排名比较
	
	private List<BizEvalActivityIndex> bizEvalActivityIndexList;
	
	
	
	

	public List<BizEmgrouprelation> getEmpGropRelation() {
		return empGropRelation;
	}

	public void setEmpGropRelation(List<BizEmgrouprelation> empGropRelation) {
		this.empGropRelation = empGropRelation;
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

	public String getRecommendManagerName() {
		return recommendManagerName;
	}

	public void setRecommendManagerName(String recommendManagerName) {
		this.recommendManagerName = recommendManagerName;
	}

	public String getOrderStopReason() {
		return orderStopReason;
	}

	public void setOrderStopReason(String orderStopReason) {
		this.orderStopReason = orderStopReason;
	}

	public String getOrderStopReasonType() {
		return orderStopReasonType;
	}

	public void setOrderStopReasonType(String orderStopReasonType) {
		this.orderStopReasonType = orderStopReasonType;
	}

	public String getOrderStopOperatorEmployeeId() {
		return orderStopOperatorEmployeeId;
	}

	public void setOrderStopOperatorEmployeeId(String orderStopOperatorEmployeeId) {
		this.orderStopOperatorEmployeeId = orderStopOperatorEmployeeId;
	}

	public Date getOrderStopOperateDatetime() {
		return orderStopOperateDatetime;
	}

	public void setOrderStopOperateDatetime(Date orderStopOperateDatetime) {
		this.orderStopOperateDatetime = orderStopOperateDatetime;
	}

	public String getLeaderRealNamezhen() {
		return leaderRealNamezhen;
	}

	public void setLeaderRealNamezhen(String leaderRealNamezhen) {
		this.leaderRealNamezhen = leaderRealNamezhen;
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

	public Integer getNoInDepartment() {
		return noInDepartment;
	}

	public void setNoInDepartment(Integer noInDepartment) {
		this.noInDepartment = noInDepartment;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getWorkerGroupId() {
		return workerGroupId;
	}

	public void setWorkerGroupId(Integer workerGroupId) {
		this.workerGroupId = workerGroupId;
	}

	public String getStarScore1() {
		return starScore1;
	}

	public void setStarScore1(String starScore1) {
		this.starScore1 = starScore1;
	}

	public String getStarScore2() {
		return starScore2;
	}

	public void setStarScore2(String starScore2) {
		this.starScore2 = starScore2;
	}

	public Integer getSort1() {
		return sort1;
	}

	public void setSort1(Integer sort1) {
		this.sort1 = sort1;
	}

	public Integer getSort2() {
		return sort2;
	}

	public void setSort2(Integer sort2) {
		this.sort2 = sort2;
	}

	public String getGroupRealName() {
		return groupRealName;
	}

	public void setGroupRealName(String groupRealName) {
		this.groupRealName = groupRealName;
	}

	public Date getGradeDate() {
		return gradeDate;
	}

	public void setGradeDate(Date gradeDate) {
		this.gradeDate = gradeDate;
	}

	public String getCustomerMessage() {
		return customerMessage;
	}

	public void setCustomerMessage(String customerMessage) {
		this.customerMessage = customerMessage;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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

	public Integer getEvalRoleType() {
		return evalRoleType;
	}

	private Integer taskPackageTemplatId;
	
	
	public Integer getTaskPackageTemplatId() {
		return taskPackageTemplatId;
	}

	public void setTaskPackageTemplatId(Integer taskPackageTemplatId) {
		this.taskPackageTemplatId = taskPackageTemplatId;
	}

	public void setEvalRoleType(Integer evalRoleType) {
		this.evalRoleType = evalRoleType;
	}

	public Double getEvaltotalScore() {
		return evaltotalScore;
	}

	public void setEvaltotalScore(Double evaltotalScore) {
		this.evaltotalScore = evaltotalScore;
	}

	public Double getGradtotalScore() {
		return gradtotalScore;
	}

	public void setGradtotalScore(Double gradtotalScore) {
		this.gradtotalScore = gradtotalScore;
	}

	public List<BizEvalActivityIndex> getBizEvalActivityIndexList() {
		return bizEvalActivityIndexList;
	}

	public void setBizEvalActivityIndexList(
			List<BizEvalActivityIndex> bizEvalActivityIndexList) {
		this.bizEvalActivityIndexList = bizEvalActivityIndexList;
	}


	public List<Integer> getEnginDepartIds() {
		return enginDepartIds;
	}

	public void setEnginDepartIds(List<Integer> enginDepartIds) {
		this.enginDepartIds = enginDepartIds;
	}

	public BizWorkerScore() {
		super();
	}



	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
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


	public Integer getWorkType() {
		return workType;
	}

	public void setWorkType(Integer workType) {
		this.workType = workType;
	}

	public String getStarScore() {
		return starScore;
	}

	public void setStarScore(String starScore) {
		this.starScore = starScore;
	}

	public String getLeaderPhone() {
		return leaderPhone;
	}

	public void setLeaderPhone(String leaderPhone) {
		this.leaderPhone = leaderPhone;
	}

	public Integer getStar() {
		return star;
	}

	public void setStar(Integer star) {
		this.star = star;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Override
	public int compareTo(BizWorkerScore o) {
		if(Double.valueOf(this.getStarScore())>Double.valueOf(o.getStarScore())){
			return -1;
		}else if(Double.valueOf(this.getStarScore())<Double.valueOf(o.getStarScore())){
			return 1;
		}
		return 0;
	}
	List<BizEmgrouprelation> bizEmgrouprelationList;
	
	public List<BizEmgrouprelation> getBizEmgrouprelationList() {
		return bizEmgrouprelationList;
	}

	public void setBizEmgrouprelationList(
			List<BizEmgrouprelation> bizEmgrouprelationList) {
		this.bizEmgrouprelationList = bizEmgrouprelationList;
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
		BizWorkerScore other = (BizWorkerScore) obj;
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