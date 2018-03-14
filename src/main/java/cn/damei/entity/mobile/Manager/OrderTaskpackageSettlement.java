package cn.damei.entity.mobile.Manager;

import java.util.Date;
import java.util.List;
import cn.damei.common.persistence.DataEntity2;
import cn.damei.entity.modules.BizEvalActivityIndex;

/**
 * 任务包结算Entity
 * @author qww
 * @version 2016-10-15
 */

public class OrderTaskpackageSettlement extends DataEntity2<OrderTaskpackageSettlement>{

	private static final long serialVersionUID = 1L;

	private Integer id; // 任务包结算id
	private String settlementNo; // 任务包结算编号
	private Integer orderTaskpackageId; // 订单任务包id
	private Date checkDate; // 验收日期
	private String isQualified; // 是否合格 no:不合格 yes:合格
	private String isDelay; // 是否延期  no:延期 yes:按时完成
	private Double delayDays; // 延期天数(延期天数可以为整天，可以为半天，即0.5天)
	private Double delayAmerce; // 延期扣除金额 -- '延期天数*100元
	private String isManagePunish; // 是否管理处罚 0:无 1:有
	private Double punishAmerce; // 处罚金额
	private String punishReason; // 处罚理由
	private Double auxiliaryMaterialsAmount; // 辅料核算金额 
	private Double guaranteeMoneyAmount; // 质保金金额
	private Double settlementAmount; // 结算金额
	private String status; // 状态
	private Date statusDate; // 状态产生时间
	private String refusedReason;
	private String remarks;
	private String isNeedRecheck; // 是否需要复合
	private Integer storeId;
	private Double qcPunishMoneyAmount; // 质检罚款
	private Double rewardAmount; // 评价奖励金额
	private Double sandCementAmount;//沙子水泥金额
	private Double companyDeductAmount;//公司罚款
	private String companyDeductReason;//公司罚款原因
	private Date ensureAmountDatetime;//工人确认薪酬时间
	
	// 额外字段 
	private List<BizOrderTaskpackageProcedure> orderTaskProcedure; // 订单任务包工序表
	private List<BizOrderTaskpackageSettlementDetail> settlementDetail; // 任务结算单人工薪酬
	private List<OrderTaskpackageAuxiliaryMaterials> auxiliaryMaterials; // 订单任务包辅料
	private List<OrderTaskpackageAuxiliaryMaterials> sandMaterials;//订单任务包沙子水泥
	private List<BizEvalActivityIndex> bizEvalActivityIndexList;
	private String evalFeedback; // 评价工人反馈意见
	private Integer orderId; // 订单id
	private Integer groupId; //  员工组长id
	private Integer empGroupid; // 工人组id
	private Integer taskPackageTemplatId; // 任务包模板id
	private Double guaranteeMoneyAmountTotal; // 质保金累计
	private Integer gualityGuaranteeType; // 扣除质保金分类(自定义字段) 1-不扣质保金 2-扣质保金，但是没满两次 3-扣质保金，但是已满2次
	private Double laborAuxiliaryMaterialsSettleAmount;//工料费结算总金额
	private Double laborSettleAmount;//人工费结算总金额
	private Double auxiliaryMaterialsSettleAmount;//辅料费结算总金额
	private Double pmMaterialsSettleAmount;//项目经理材料结算总金额
	private Double workerGroupSettleAmount;//工人组结算总金额
	private String settleStyle;//结算方式
	private Double auxiliaryMaterialsDeductAmountSupplierPrice;//辅料扣除金额供应商价格
	private Double auxiliaryMaterialsDeductAmountWangzhenPrice;//辅料扣除金额网真价格
	private Double sandCementDeductAmountSupplierPrice;//沙子水泥扣除金额供应商价格
	private Double sandCementDeductAmountWangzhenPrice;//沙子水泥扣除金额网真价格
	

	public Double getAuxiliaryMaterialsDeductAmountSupplierPrice() {
		return auxiliaryMaterialsDeductAmountSupplierPrice;
	}

	public void setAuxiliaryMaterialsDeductAmountSupplierPrice(Double auxiliaryMaterialsDeductAmountSupplierPrice) {
		this.auxiliaryMaterialsDeductAmountSupplierPrice = auxiliaryMaterialsDeductAmountSupplierPrice;
	}

	public Double getAuxiliaryMaterialsDeductAmountWangzhenPrice() {
		return auxiliaryMaterialsDeductAmountWangzhenPrice;
	}

	public void setAuxiliaryMaterialsDeductAmountWangzhenPrice(Double auxiliaryMaterialsDeductAmountWangzhenPrice) {
		this.auxiliaryMaterialsDeductAmountWangzhenPrice = auxiliaryMaterialsDeductAmountWangzhenPrice;
	}

	public Double getSandCementDeductAmountSupplierPrice() {
		return sandCementDeductAmountSupplierPrice;
	}

	public void setSandCementDeductAmountSupplierPrice(Double sandCementDeductAmountSupplierPrice) {
		this.sandCementDeductAmountSupplierPrice = sandCementDeductAmountSupplierPrice;
	}

	public Double getSandCementDeductAmountWangzhenPrice() {
		return sandCementDeductAmountWangzhenPrice;
	}

	public void setSandCementDeductAmountWangzhenPrice(Double sandCementDeductAmountWangzhenPrice) {
		this.sandCementDeductAmountWangzhenPrice = sandCementDeductAmountWangzhenPrice;
	}

	public Double getLaborAuxiliaryMaterialsSettleAmount() {
		return laborAuxiliaryMaterialsSettleAmount;
	}

	public void setLaborAuxiliaryMaterialsSettleAmount(Double laborAuxiliaryMaterialsSettleAmount) {
		this.laborAuxiliaryMaterialsSettleAmount = laborAuxiliaryMaterialsSettleAmount;
	}

	public Double getLaborSettleAmount() {
		return laborSettleAmount;
	}

	public void setLaborSettleAmount(Double laborSettleAmount) {
		this.laborSettleAmount = laborSettleAmount;
	}

	public Double getAuxiliaryMaterialsSettleAmount() {
		return auxiliaryMaterialsSettleAmount;
	}

	public void setAuxiliaryMaterialsSettleAmount(Double auxiliaryMaterialsSettleAmount) {
		this.auxiliaryMaterialsSettleAmount = auxiliaryMaterialsSettleAmount;
	}

	public Double getPmMaterialsSettleAmount() {
		return pmMaterialsSettleAmount;
	}

	public void setPmMaterialsSettleAmount(Double pmMaterialsSettleAmount) {
		this.pmMaterialsSettleAmount = pmMaterialsSettleAmount;
	}

	public Double getWorkerGroupSettleAmount() {
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

	public Date getEnsureAmountDatetime() {
		return ensureAmountDatetime;
	}

	public void setEnsureAmountDatetime(Date ensureAmountDatetime) {
		this.ensureAmountDatetime = ensureAmountDatetime;
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

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
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

	public List<BizEvalActivityIndex> getBizEvalActivityIndexList() {
		return bizEvalActivityIndexList;
	}

	public void setBizEvalActivityIndexList(List<BizEvalActivityIndex> bizEvalActivityIndexList) {
		this.bizEvalActivityIndexList = bizEvalActivityIndexList;
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
	public Date getStatusDate() {
		return statusDate;
	}
	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}
	public String getRefusedReason() {
		return refusedReason;
	}
	public void setRefusedReason(String refusedReason) {
		this.refusedReason = refusedReason;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getIsNeedRecheck() {
		return isNeedRecheck;
	}
	public void setIsNeedRecheck(String isNeedRecheck) {
		this.isNeedRecheck = isNeedRecheck;
	}
	public List<BizOrderTaskpackageProcedure> getOrderTaskProcedure() {
		return orderTaskProcedure;
	}
	public void setOrderTaskProcedure(List<BizOrderTaskpackageProcedure> orderTaskProcedure) {
		this.orderTaskProcedure = orderTaskProcedure;
	}
	public List<BizOrderTaskpackageSettlementDetail> getSettlementDetail() {
		return settlementDetail;
	}
	public void setSettlementDetail(List<BizOrderTaskpackageSettlementDetail> settlementDetail) {
		this.settlementDetail = settlementDetail;
	}
	public List<OrderTaskpackageAuxiliaryMaterials> getAuxiliaryMaterials() {
		return auxiliaryMaterials;
	}
	public void setAuxiliaryMaterials(List<OrderTaskpackageAuxiliaryMaterials> auxiliaryMaterials) {
		this.auxiliaryMaterials = auxiliaryMaterials;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public Integer getEmpGroupid() {
		return empGroupid;
	}
	public void setEmpGroupid(Integer empGroupid) {
		this.empGroupid = empGroupid;
	}
	public Integer getTaskPackageTemplatId() {
		return taskPackageTemplatId;
	}
	public void setTaskPackageTemplatId(Integer taskPackageTemplatId) {
		this.taskPackageTemplatId = taskPackageTemplatId;
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

	public String getEvalFeedback() {
		return evalFeedback;
	}

	public void setEvalFeedback(String evalFeedback) {
		this.evalFeedback = evalFeedback;
	}

	public Double getRewardAmount() {
		return rewardAmount;
	}

	public void setRewardAmount(Double rewardAmount) {
		this.rewardAmount = rewardAmount;
	}

	public Double getSandCementAmount() {
		return sandCementAmount;
	}

	public void setSandCementAmount(Double sandCementAmount) {
		this.sandCementAmount = sandCementAmount;
	}

	public List<OrderTaskpackageAuxiliaryMaterials> getSandMaterials() {
		return sandMaterials;
	}

	public void setSandMaterials(List<OrderTaskpackageAuxiliaryMaterials> sandMaterials) {
		this.sandMaterials = sandMaterials;
	}
	
	
}
