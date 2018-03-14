package cn.damei.entity.modules;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.damei.common.persistence.DataEntity2;

public class BizAssessRewardPunish extends DataEntity2<BizAssessRewardPunish>{

	private String ids;

	private static final long serialVersionUID = 1L;
	
	private String relatedBusinessType; //关联业务类型  1:产业项目经理奖惩
	
	private Integer relatedBusinessIdInt; //关联业务id整型  订单Id
	
	private String relatedBusinessIdVarchar;//关联业务id字符型
	
	private Integer assessRuleTypeId;//考核条例分类Id
	
	private String assessRuleType;//考核条例分类
	
	private Integer assessRuleId;//考核条例Id
	
	private String assessRuleDescribe;//考核条例细则说明
	
	private String isRewardOrPunish;//奖励或惩罚 //1:奖励  2：惩罚

	private String isMonthInspection;//是否月度巡检  0：否 1：是
	
	private String rewardPunishTargetType;//奖惩对象 10：订单 20：员工
	
	private String rewardPunishTargetEmployeeType;//奖惩对象员工类型  1：项目经理 2：工人 3：质检员
	
	private Integer rewardPunishTargetEmployeeId;//奖惩对象员工Id
	
	private Double rewardPunishAmount;//奖惩金额
	
	private Double rewardPunishScore;//奖惩分数
	
	private String rewardPunishRemarks;//奖惩说明
	
	private Date rewardPunishDatetime;//奖惩日期时间
	
	private String rewardPunishStatus;//奖惩状态 1：新建 (未关联结算) 2：已关联结算
	
	private Date statusDatetime;//状态日期时间
	
	private Integer statusOperator;//状态操作人
	
	private String operatorName;//操作人姓名
	
	private String statusDescribe;//状态说明
	
	private String settleStage;//所属结算阶段  1:中期结算单  2：竣工结算单 
	
	private String settleType;// 结算单类型 1：工人结算单  2：项目经理结算单
	
	private Integer settleId;//结算单id

	private Integer storeId;
	
	private String projectMode;
	
	private Integer enginDepartId;
	
	private List<Integer> enginDepartIds = new ArrayList<Integer>(); 
	
	private String enginDepartName;
	
	private String rewardPunishTargetEmployeeName;
	
	private String rewardPunishTargetEmployeePhone;
	
	private Date startDate;
	
	private Date endDate;
	
	private String communityName;		// 小区名称
	private String buildNumber;		// 几号楼
	private String buildUnit;		// 几单元
	private String buildRoom;		// 哪一室 
	private String customerName;	// 客户姓名 
	private String customerPhone;//客户手机号
	private String orderNumber;//订单编号
	private String generalRemarks;//主题备注
	private String detailRemarks;//明细备注

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
