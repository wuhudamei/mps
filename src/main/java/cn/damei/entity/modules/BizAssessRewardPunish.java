package cn.damei.entity.modules;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.damei.common.persistence.DataEntity2;

public class BizAssessRewardPunish extends DataEntity2<BizAssessRewardPunish>{

	private String ids;

	private static final long serialVersionUID = 1L;
	
	private String relatedBusinessType;
	
	private Integer relatedBusinessIdInt;
	
	private String relatedBusinessIdVarchar;
	
	private Integer assessRuleTypeId;
	
	private String assessRuleType;
	
	private Integer assessRuleId;
	
	private String assessRuleDescribe;
	
	private String isRewardOrPunish;

	private String isMonthInspection;
	
	private String rewardPunishTargetType;
	
	private String rewardPunishTargetEmployeeType;
	
	private Integer rewardPunishTargetEmployeeId;
	
	private Double rewardPunishAmount;
	
	private Double rewardPunishScore;
	
	private String rewardPunishRemarks;
	
	private Date rewardPunishDatetime;
	
	private String rewardPunishStatus;
	
	private Date statusDatetime;
	
	private Integer statusOperator;
	
	private String operatorName;
	
	private String statusDescribe;
	
	private String settleStage;
	
	private String settleType;
	
	private Integer settleId;

	private Integer storeId;
	
	private String projectMode;
	
	private Integer enginDepartId;
	
	private List<Integer> enginDepartIds = new ArrayList<Integer>(); 
	
	private String enginDepartName;
	
	private String rewardPunishTargetEmployeeName;
	
	private String rewardPunishTargetEmployeePhone;
	
	private Date startDate;
	
	private Date endDate;
	
	private String communityName;
	private String buildNumber;
	private String buildUnit;
	private String buildRoom;
	private String customerName;
	private String customerPhone;
	private String orderNumber;
	private String generalRemarks;
	private String detailRemarks;

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getIsMonthInspection() {
		if(isMonthInspection == null){
			isMonthInspection = "0";
		}
		return isMonthInspection;
	}

	public void setIsMonthInspection(String isMonthInspection) {
		this.isMonthInspection = isMonthInspection;
	}

	public String getAssessRuleType() {
		return assessRuleType;
	}

	public void setAssessRuleType(String assessRuleType) {
		this.assessRuleType = assessRuleType;
	}

	

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getAssessRuleDescribe() {
		return assessRuleDescribe;
	}

	public void setAssessRuleDescribe(String assessRuleDescribe) {
		this.assessRuleDescribe = assessRuleDescribe;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getRewardPunishTargetEmployeePhone() {
		return rewardPunishTargetEmployeePhone;
	}

	public void setRewardPunishTargetEmployeePhone(String rewardPunishTargetEmployeePhone) {
		this.rewardPunishTargetEmployeePhone = rewardPunishTargetEmployeePhone;
	}

	public List<Integer> getEnginDepartIds() {
		return enginDepartIds;
	}

	public void setEnginDepartIds(List<Integer> enginDepartIds) {
		this.enginDepartIds = enginDepartIds;
	}

	public String getEnginDepartName() {
		return enginDepartName;
	}

	public void setEnginDepartName(String enginDepartName) {
		this.enginDepartName = enginDepartName;
	}

	public Integer getAssessRuleTypeId() {
		return assessRuleTypeId;
	}

	public void setAssessRuleTypeId(Integer assessRuleTypeId) {
		this.assessRuleTypeId = assessRuleTypeId;
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

	public String getRewardPunishTargetEmployeeName() {
		return rewardPunishTargetEmployeeName;
	}

	public void setRewardPunishTargetEmployeeName(String rewardPunishTargetEmployeeName) {
		this.rewardPunishTargetEmployeeName = rewardPunishTargetEmployeeName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}

	public Integer getEnginDepartId() {
		return enginDepartId;
	}

	public void setEnginDepartId(Integer enginDepartId) {
		this.enginDepartId = enginDepartId;
	}

	public String getRelatedBusinessType() {
		return relatedBusinessType;
	}

	public void setRelatedBusinessType(String relatedBusinessType) {
		this.relatedBusinessType = relatedBusinessType;
	}

	public Integer getRelatedBusinessIdInt() {
		return relatedBusinessIdInt;
	}

	public void setRelatedBusinessIdInt(Integer relatedBusinessIdInt) {
		this.relatedBusinessIdInt = relatedBusinessIdInt;
	}

	public String getRelatedBusinessIdVarchar() {
		return relatedBusinessIdVarchar;
	}

	public void setRelatedBusinessIdVarchar(String relatedBusinessIdVarchar) {
		this.relatedBusinessIdVarchar = relatedBusinessIdVarchar;
	}

	public Integer getAssessRuleId() {
		return assessRuleId;
	}

	public void setAssessRuleId(Integer assessRuleId) {
		this.assessRuleId = assessRuleId;
	}

	public String getIsRewardOrPunish() {
		return isRewardOrPunish;
	}

	public void setIsRewardOrPunish(String isRewardOrPunish) {
		this.isRewardOrPunish = isRewardOrPunish;
	}

	public String getRewardPunishTargetType() {
		return rewardPunishTargetType;
	}

	public void setRewardPunishTargetType(String rewardPunishTargetType) {
		this.rewardPunishTargetType = rewardPunishTargetType;
	}

	public String getRewardPunishTargetEmployeeType() {
		return rewardPunishTargetEmployeeType;
	}

	public void setRewardPunishTargetEmployeeType(String rewardPunishTargetEmployeeType) {
		this.rewardPunishTargetEmployeeType = rewardPunishTargetEmployeeType;
	}

	public Integer getRewardPunishTargetEmployeeId() {
		return rewardPunishTargetEmployeeId;
	}

	public void setRewardPunishTargetEmployeeId(Integer rewardPunishTargetEmployeeId) {
		this.rewardPunishTargetEmployeeId = rewardPunishTargetEmployeeId;
	}

	public Double getRewardPunishAmount() {
		return rewardPunishAmount;
	}

	public void setRewardPunishAmount(Double rewardPunishAmount) {
		this.rewardPunishAmount = rewardPunishAmount;
	}

	public Double getRewardPunishScore() {
		return rewardPunishScore;
	}

	public void setRewardPunishScore(Double rewardPunishScore) {
		this.rewardPunishScore = rewardPunishScore;
	}

	public String getRewardPunishRemarks() {
		return rewardPunishRemarks;
	}

	public void setRewardPunishRemarks(String rewardPunishRemarks) {
		this.rewardPunishRemarks = rewardPunishRemarks;
	}

	public Date getRewardPunishDatetime() {
		return rewardPunishDatetime;
	}

	public void setRewardPunishDatetime(Date rewardPunishDatetime) {
		this.rewardPunishDatetime = rewardPunishDatetime;
	}

	public String getRewardPunishStatus() {
		return rewardPunishStatus;
	}

	public void setRewardPunishStatus(String rewardPunishStatus) {
		this.rewardPunishStatus = rewardPunishStatus;
	}

	public Date getStatusDatetime() {
		return statusDatetime;
	}

	public void setStatusDatetime(Date statusDatetime) {
		this.statusDatetime = statusDatetime;
	}

	public Integer getStatusOperator() {
		return statusOperator;
	}

	public void setStatusOperator(Integer statusOperator) {
		this.statusOperator = statusOperator;
	}

	public String getStatusDescribe() {
		return statusDescribe;
	}

	public void setStatusDescribe(String statusDescribe) {
		this.statusDescribe = statusDescribe;
	}

	public String getSettleStage() {
		return settleStage;
	}

	public void setSettleStage(String settleStage) {
		this.settleStage = settleStage;
	}

	public String getSettleType() {
		return settleType;
	}

	public void setSettleType(String settleType) {
		this.settleType = settleType;
	}

	public Integer getSettleId() {
		return settleId;
	}

	public void setSettleId(Integer settleId) {
		this.settleId = settleId;
	}


	public String getGeneralRemarks() {
		return generalRemarks;
	}

	public void setGeneralRemarks(String generalRemarks) {
		this.generalRemarks = generalRemarks;
	}

	public String getDetailRemarks() {
		return detailRemarks;
	}

	public void setDetailRemarks(String detailRemarks) {
		this.detailRemarks = detailRemarks;
	}
}
