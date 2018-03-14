package cn.damei.entity.mobile.Manager;

import java.util.Date;
import java.util.List;
import cn.damei.common.persistence.DataEntity2;
import cn.damei.entity.modules.BizEvalActivityIndex;



public class OrderTaskpackageSettlement extends DataEntity2<OrderTaskpackageSettlement>{

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String settlementNo;
	private Integer orderTaskpackageId;
	private Date checkDate;
	private String isQualified;
	private String isDelay;
	private Double delayDays;
	private Double delayAmerce;
	private String isManagePunish;
	private Double punishAmerce;
	private String punishReason;
	private Double auxiliaryMaterialsAmount;
	private Double guaranteeMoneyAmount;
	private Double settlementAmount;
	private String status;
	private Date statusDate;
	private String refusedReason;
	private String remarks;
	private String isNeedRecheck;
	private Integer storeId;
	private Double qcPunishMoneyAmount;
	private Double rewardAmount;
	private Double sandCementAmount;
	private Double companyDeductAmount;
	private String companyDeductReason;
	private Date ensureAmountDatetime;
	

	private List<BizOrderTaskpackageProcedure> orderTaskProcedure;
	private List<BizOrderTaskpackageSettlementDetail> settlementDetail;
	private List<OrderTaskpackageAuxiliaryMaterials> auxiliaryMaterials;
	private List<OrderTaskpackageAuxiliaryMaterials> sandMaterials;
	private List<BizEvalActivityIndex> bizEvalActivityIndexList;
	private String evalFeedback;
	private Integer orderId;
	private Integer groupId;
	private Integer empGroupid;
	private Integer taskPackageTemplatId;
	private Double guaranteeMoneyAmountTotal;
	private Integer gualityGuaranteeType;
	private Double laborAuxiliaryMaterialsSettleAmount;
	private Double laborSettleAmount;
	private Double auxiliaryMaterialsSettleAmount;
	private Double pmMaterialsSettleAmount;
	private Double workerGroupSettleAmount;
	private String settleStyle;
	private Double auxiliaryMaterialsDeductAmountSupplierPrice;
	private Double auxiliaryMaterialsDeductAmountWangzhenPrice;
	private Double sandCementDeductAmountSupplierPrice;
	private Double sandCementDeductAmountWangzhenPrice;
	

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
