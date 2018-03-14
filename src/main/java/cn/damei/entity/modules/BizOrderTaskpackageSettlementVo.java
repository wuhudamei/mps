package cn.damei.entity.modules;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.damei.common.persistence.DataEntity2;
public class BizOrderTaskpackageSettlementVo extends DataEntity2<BizOrderTaskpackageSettlementVo>{

	/**
	 * @author wang
	 * @version 2016-10-14
	 */
	private static final long serialVersionUID = -5392178936581100638L;
	
	private Integer id;
	private String settlementNo;		// 结算单编号
	private Integer orderTaskpackageId;		// 订单任务包id
	private Integer storeid;
	private String projectMode;
	private Date checkDate;		// 验收日期
	private String isQualified;		// 是否合格
	private String isDelay;		// 是否延期 
	private Double delayDays;		// 延期天数 
	private Double delayAmerce;		// 延期扣除金额 延期天数*100元
	private String isManagePunish;		// 是否管理处罚 
	private Double punishAmerce;		// 处罚金额 
	private String punishReason;		// 处罚理由
	private Double auxiliaryMaterialsAmount;		// 辅料核算金额 
	private Double sandMaterialsAmount;   //沙子水泥核算金额
	private Double guaranteeMoneyAmount;		// 质保金金额 
	private Double settlementAmount;		// 结算金额
	private String status;		// 状态 
	private String taskPackageNo;  //任务包编号
	private String packName;
	private String packageStateid; //任务包状态
	private String packageStatename; // 任务包状态名称
	private Integer orderId; //订单ID
	private String orderNo;			//订单编号
	private String customerName;		// 客户姓名
	private String customerMessage;		// 客户信息
	private String communityName;		// 小区名称
	private String buildNumber;		// 几号楼
	private String buildUnit;		// 几单元
	private String buildRoom;		// 哪一室
	private String itemManager; 	//项目经理
	private Integer itemManagerId; 
	private String orderTaskpackageName;  //任务包名称
	private String groupRealname;	//组长姓名
	private String designerName; //设计师姓名
	private String houseType; //户型
	private String coveredArea; //建筑面积
	private String refusedReason;
	private Date statusDate;
	private String isNeedRecheck;
	
	private Date beginCheckDate;		// 开始 验收日期 
	private Date endCheckDate;		// 结束 验收日期
	private Date actualStartdate; // 实际开工日期
	private Date actualEnddate; // 实际完工日期
	private Date recheckDatetime; // 质检员复核时间
	private Double qcPunishMoneyAmount; // 质检罚款
	private Double rewardAmount; // 奖励金额
	
	private String isQualityGuarantee; // 是否扣质保金
	private Integer qualityGuaranteeRate; // 质保金扣除比例;1-100
	private Integer taskPackageTemplatId; // 任务包模板id
	private Integer groupId; // 工人组长id
	private String groupName; // 工人组长名称
	private String groupPhone;
	private Double guaranteeMoneyAmountTotal; // 质保金累计金额
	private Integer gualityGuaranteeType; // 扣除质保金分类(自定义字段) 1-不扣质保金 2-扣质保金，但是没满两次 3-扣质保金，但是已满2次

	private String customerPhone; // 客户电话
	private String customerAddress; // 客户地址
	private String itemCustomer; // 项目经理
	private String itemPhone;//项目经理手机号
	private Float advancePaymentRates; // 首付款比例
	private Float restPaymentRates; // 尾付款比例
	private Double advanceAmount; // 首付款金额
	private Double restAmount; // 尾付款金额
	private List<String> settlementStatus;
	private String realName;
	private Date confirmSalaryTime;//工人确认薪酬时间
	private List<Integer> enginDepartIds = new ArrayList<Integer>();
	private Integer enginDepartId;
	private String departmentName;
	
	private Double guaranteeMoneyBalance;//质保金余额
	private Date passBeginDate;//结算员通过开始时间
	private Date passEndDate;//结算员通过结束时间
	private Date ensureBegindatetime;//工人确认薪酬开始时间
	private Date ensureEnddatetime;//工人确认薪酬结束时间
	private Double companyDeductAmount;//公司罚款
	private String companyDeductReason;//公司罚款原因 
	private Double laborAuxiliaryMaterialsBudgetAmount;//工料费预算总金额
	private Double laborBudgetAmount;// 人工费预算总金额
	private Double auxiliaryMaterialsBudgetAmount;//辅料费预算总金额
	private Double laborAuxiliaryMaterialsSettleAmount;//工料费结算总金额
	private Double laborSettleAmount;//人工费结算总金额
	private Double auxiliaryMaterialsSettleAmount;//辅料费结算总金额
	private Double pmMaterialsSettleAmount;//项目经理材料结算总金额
	private Double workerGroupSettleAmount;//工人组结算总金额
	private String settleStyle;//结算方式

	
	
	public Double getLaborAuxiliaryMaterialsBudgetAmount() {
		return laborAuxiliaryMaterialsBudgetAmount;
	}

	public void setLaborAuxiliaryMaterialsBudgetAmount(Double laborAuxiliaryMaterialsBudgetAmount) {
		this.laborAuxiliaryMaterialsBudgetAmount = laborAuxiliaryMaterialsBudgetAmount;
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

	public String getGroupPhone() {
		return groupPhone;
	}

	public void setGroupPhone(String groupPhone) {
		this.groupPhone = groupPhone;
	}

	public String getItemPhone() {
		return itemPhone;
	}

	public void setItemPhone(String itemPhone) {
		this.itemPhone = itemPhone;
	}

	public Double getLaborAuxiliaryMaterialsSettleAmount() {
		if(laborAuxiliaryMaterialsSettleAmount == null){
			laborAuxiliaryMaterialsSettleAmount=0.0;
		}
		return laborAuxiliaryMaterialsSettleAmount;
	}

	public void setLaborAuxiliaryMaterialsSettleAmount(Double laborAuxiliaryMaterialsSettleAmount) {
		this.laborAuxiliaryMaterialsSettleAmount = laborAuxiliaryMaterialsSettleAmount;
	}

	
	
	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}

	public Double getLaborSettleAmount() {
		if(laborSettleAmount == null){
			laborSettleAmount = 0.0;
		}
		return laborSettleAmount;
	}

	public void setLaborSettleAmount(Double laborSettleAmount) {
		this.laborSettleAmount = laborSettleAmount;
	}

	public Double getAuxiliaryMaterialsSettleAmount() {
		if(auxiliaryMaterialsSettleAmount == null){
			auxiliaryMaterialsSettleAmount = 0.0;
		}
		return auxiliaryMaterialsSettleAmount;
	}

	public void setAuxiliaryMaterialsSettleAmount(Double auxiliaryMaterialsSettleAmount) {
		this.auxiliaryMaterialsSettleAmount = auxiliaryMaterialsSettleAmount;
	}

	public Double getPmMaterialsSettleAmount() {
		if(pmMaterialsSettleAmount == null){
			pmMaterialsSettleAmount = 0.0;
		}
		return pmMaterialsSettleAmount;
	}

	public void setPmMaterialsSettleAmount(Double pmMaterialsSettleAmount) {
		this.pmMaterialsSettleAmount = pmMaterialsSettleAmount;
	}

	public Double getWorkerGroupSettleAmount() {
		if(workerGroupSettleAmount == null){
			workerGroupSettleAmount=0.0;
		}
		return workerGroupSettleAmount;
	}

	public void setWorkerGroupSettleAmount(Double workerGroupSettleAmount) {
		this.workerGroupSettleAmount = workerGroupSettleAmount;
	}

	public String getSettleStyle() {
		return settleStyle;
	}

	public void setSettleStyle(String settleStyle) {
		this.settleStyle = settleStyle;
	}

	public Double getCompanyDeductAmount() {
		return companyDeductAmount;
	}

	public void setCompanyDeductAmount(Double companyDeductAmount) {
		this.companyDeductAmount = companyDeductAmount;
	}

	public String getCompanyDeductReason() {
		return companyDeductReason;
	}

	public void setCompanyDeductReason(String companyDeductReason) {
		this.companyDeductReason = companyDeductReason;
	}

	public String getPackName() {
		return packName;
	}

	public void setPackName(String packName) {
		this.packName = packName;
	}

	public Date getPassBeginDate() {
		return passBeginDate;
	}

	public void setPassBeginDate(Date passBeginDate) {
		this.passBeginDate = passBeginDate;
	}

	public Date getPassEndDate() {
		return passEndDate;
	}

	public void setPassEndDate(Date passEndDate) {
		this.passEndDate = passEndDate;
	}

	public Date getEnsureBegindatetime() {
		return ensureBegindatetime;
	}

	public void setEnsureBegindatetime(Date ensureBegindatetime) {
		this.ensureBegindatetime = ensureBegindatetime;
	}

	public Date getEnsureEnddatetime() {
		return ensureEnddatetime;
	}

	public void setEnsureEnddatetime(Date ensureEnddatetime) {
		this.ensureEnddatetime = ensureEnddatetime;
	}

	public Double getGuaranteeMoneyBalance() {
		return guaranteeMoneyBalance;
	}

	public void setGuaranteeMoneyBalance(Double guaranteeMoneyBalance) {
		this.guaranteeMoneyBalance = guaranteeMoneyBalance;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public List<String> getSettlementStatus() {
		return settlementStatus;
	}

	public void setSettlementStatus(List<String> settlementStatus) {
		this.settlementStatus = settlementStatus;
	}

	public Double getAdvanceAmount() {
		return advanceAmount;
	}

	public void setAdvanceAmount(Double advanceAmount) {
		this.advanceAmount = advanceAmount;
	}

	public Double getRestAmount() {
		return restAmount;
	}

	public void setRestAmount(Double restAmount) {
		this.restAmount = restAmount;
	}

	public Float getRestPaymentRates() {
		return restPaymentRates;
	}

	public void setRestPaymentRates(Float restPaymentRates) {
		this.restPaymentRates = restPaymentRates;
	}

	public Float getAdvancePaymentRates() {
		return advancePaymentRates;
	}

	public void setAdvancePaymentRates(Float advancePaymentRates) {
		this.advancePaymentRates = advancePaymentRates;
	}

	public String getItemCustomer() {
		return itemCustomer;
	}

	public void setItemCustomer(String itemCustomer) {
		this.itemCustomer = itemCustomer;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public Date getConfirmSalaryTime() {
		return confirmSalaryTime;
	}
	public void setConfirmSalaryTime(Date confirmSalaryTime) {
		this.confirmSalaryTime = confirmSalaryTime;
	}

	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
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
	public Integer getItemManagerId() {
		return itemManagerId;
	}
	public void setItemManagerId(Integer itemManagerId) {
		this.itemManagerId = itemManagerId;
	}
	public String getOrderTaskpackageName() {
		return orderTaskpackageName;
	}
	public void setOrderTaskpackageName(String orderTaskpackageName) {
		this.orderTaskpackageName = orderTaskpackageName;
	}
	
	public String getCustomerMessage() {
		return customerMessage;
	}
	public void setCustomerMessage(String customerMessage) {
		this.customerMessage = customerMessage;
	}
	public String getGroupRealname() {
		return groupRealname;
	}
	public void setGroupRealname(String groupRealname) {
		this.groupRealname = groupRealname;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSettlementNo() {
		return settlementNo;
	}
	public void setSettlementNo(String settlementNo) {
		this.settlementNo = settlementNo;
	}
	public Integer getOrderTaskpackageId() {
		return orderTaskpackageId;
	}
	public void setOrderTaskpackageId(Integer orderTaskpackageId) {
		this.orderTaskpackageId = orderTaskpackageId;
	}
	public Date getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	public String getIsQualified() {
		return isQualified;
	}
	public void setIsQualified(String isQualified) {
		this.isQualified = isQualified;
	}
	public String getIsDelay() {
		return isDelay;
	}
	public void setIsDelay(String isDelay) {
		this.isDelay = isDelay;
	}
	public Double getDelayDays() {
		return delayDays;
	}
	public void setDelayDays(Double delayDays) {
		this.delayDays = delayDays;
	}
	public Double getDelayAmerce() {
		return delayAmerce;
	}
	public void setDelayAmerce(Double delayAmerce) {
		this.delayAmerce = delayAmerce;
	}
	public String getIsManagePunish() {
		return isManagePunish;
	}
	public void setIsManagePunish(String isManagePunish) {
		this.isManagePunish = isManagePunish;
	}
	public Double getPunishAmerce() {
		return punishAmerce;
	}
	public void setPunishAmerce(Double punishAmerce) {
		this.punishAmerce = punishAmerce;
	}
	public String getPunishReason() {
		return punishReason;
	}
	public void setPunishReason(String punishReason) {
		this.punishReason = punishReason;
	}
	public Double getAuxiliaryMaterialsAmount() {
		return auxiliaryMaterialsAmount;
	}
	public void setAuxiliaryMaterialsAmount(Double auxiliaryMaterialsAmount) {
		this.auxiliaryMaterialsAmount = auxiliaryMaterialsAmount;
	}
	public Double getGuaranteeMoneyAmount() {
		return guaranteeMoneyAmount;
	}
	public void setGuaranteeMoneyAmount(Double guaranteeMoneyAmount) {
		this.guaranteeMoneyAmount = guaranteeMoneyAmount;
	}
	public Double getSettlementAmount() {
		return settlementAmount;
	}
	public void setSettlementAmount(Double settlementAmount) {
		this.settlementAmount = settlementAmount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTaskPackageNo() {
		return taskPackageNo;
	}
	public void setTaskPackageNo(String taskPackageNo) {
		this.taskPackageNo = taskPackageNo;
	}
	public Date getBeginCheckDate() {
		return beginCheckDate;
	}
	public void setBeginCheckDate(Date beginCheckDate) {
		this.beginCheckDate = beginCheckDate;
	}
	public Date getEndCheckDate() {
		return endCheckDate;
	}
	public void setEndCheckDate(Date endCheckDate) {
		this.endCheckDate = endCheckDate;
	}

	public Date getActualEnddate() {
		return actualEnddate;
	}

	public void setActualEnddate(Date actualEnddate) {
		this.actualEnddate = actualEnddate;
	}

	public Date getActualStartdate() {
		return actualStartdate;
	}

	public void setActualStartdate(Date actualStartdate) {
		this.actualStartdate = actualStartdate;
	}

	public Date getRecheckDatetime() {
		return recheckDatetime;
	}

	public void setRecheckDatetime(Date recheckDatetime) {
		this.recheckDatetime = recheckDatetime;
	}

	public Integer getStoreid() {
		return storeid;
	}
	public void setStoreid(Integer storeid) {
		this.storeid = storeid;
	}
	public String getPackageStateid() {
		return packageStateid;
	}
	public void setPackageStateid(String packageStateid) {
		this.packageStateid = packageStateid;
	}

	public String getPackageStatename() {
		return packageStatename;
	}

	public void setPackageStatename(String packageStatename) {
		this.packageStatename = packageStatename;
	}

	public String getDesignerName() {
		return designerName;
	}
	public void setDesignerName(String designerName) {
		this.designerName = designerName;
	}
	public String getHouseType() {
		return houseType;
	}
	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}
	public String getCoveredArea() {
		return coveredArea;
	}
	public void setCoveredArea(String coveredArea) {
		this.coveredArea = coveredArea;
	}
	public String getIsQualityGuarantee() {
		return isQualityGuarantee;
	}
	public void setIsQualityGuarantee(String isQualityGuarantee) {
		this.isQualityGuarantee = isQualityGuarantee;
	}
	public Integer getQualityGuaranteeRate() {
		return qualityGuaranteeRate;
	}
	public void setQualityGuaranteeRate(Integer qualityGuaranteeRate) {
		this.qualityGuaranteeRate = qualityGuaranteeRate;
	}
	public Integer getTaskPackageTemplatId() {
		return taskPackageTemplatId;
	}
	public void setTaskPackageTemplatId(Integer taskPackageTemplatId) {
		this.taskPackageTemplatId = taskPackageTemplatId;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Double getGuaranteeMoneyAmountTotal() {
		return guaranteeMoneyAmountTotal;
	}
	public void setGuaranteeMoneyAmountTotal(Double guaranteeMoneyAmountTotal) {
		this.guaranteeMoneyAmountTotal = guaranteeMoneyAmountTotal;
	}
	public Integer getGualityGuaranteeType() {
		return gualityGuaranteeType;
	}
	public void setGualityGuaranteeType(Integer gualityGuaranteeType) {
		this.gualityGuaranteeType = gualityGuaranteeType;
	}

	public Double getQcPunishMoneyAmount() {
		return qcPunishMoneyAmount;
	}

	public void setQcPunishMoneyAmount(Double qcPunishMoneyAmount) {
		this.qcPunishMoneyAmount = qcPunishMoneyAmount;
	}

	public Double getRewardAmount() {
		return rewardAmount;
	}

	public void setRewardAmount(Double rewardAmount) {
		this.rewardAmount = rewardAmount;
	}

	public String getRefusedReason() {
		return refusedReason;
	}

	public void setRefusedReason(String refusedReason) {
		this.refusedReason = refusedReason;
	}

	public Date getStatusDate() {
		return statusDate;
	}

	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}

	public String getIsNeedRecheck() {
		return isNeedRecheck;
	}

	public void setIsNeedRecheck(String isNeedRecheck) {
		this.isNeedRecheck = isNeedRecheck;
	}

	public List<Integer> getEnginDepartIds() {
		return enginDepartIds;
	}

	public void setEnginDepartIds(List<Integer> enginDepartIds) {
		this.enginDepartIds = enginDepartIds;
	}

	public Integer getEnginDepartId() {
		return enginDepartId;
	}

	public void setEnginDepartId(Integer enginDepartId) {
		this.enginDepartId = enginDepartId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Double getSandMaterialsAmount() {
		return sandMaterialsAmount;
	}

	public void setSandMaterialsAmount(Double sandMaterialsAmount) {
		this.sandMaterialsAmount = sandMaterialsAmount;
	}
	
}
