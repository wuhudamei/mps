package cn.damei.entity.mobile.Manager;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;


public class PmSettlementBudget extends DataEntity2<PmSettlementBudget>{

	private static final long serialVersionUID = 1L;
	private Integer orderId;
	private String customerName;
	private String communityName;
	private String buildNumber;
	private String buildUnit;
	private String buildRoom;
	private String settleBillType;
    private Double settlementBudgetAmount;
    private Date acceptCheckDatetime;
    private Double midwayCommissionAmount;
    private Double ownpayAmount;
    private Double materialsStandardAmount;
    private Double midwayQcCheckPunishAmount;
    private Double midwayRewardAmount;
    private Double midwayPunishAmount;
	private Double midwayInspectionRewardAmount;
	private Double midwayInspectionPunishAmount;
    private Double midwayAuxiliaryMaterialsSettleAmount;
    private Double completeCommissionAmount;
    private Double completQcCheckPunishAmount;
    private Double completeRewardAmount;
    private Double completePunishAmount;
	private Double completeInspectionRewardAmount;
	private Double completeInspectionPunishAmount;
    private Double materialSelfbuyReimburseAmount;
    private Double guaranteeMoneyAmount;
    private Double completeAuxiliaryMaterialsSettleAmount;
    private Double totalAmount;
    private Integer pmEmployeeId;
	private String queryParam;

	public Double getMidwayInspectionRewardAmount() {
		return midwayInspectionRewardAmount;
	}

	public void setMidwayInspectionRewardAmount(Double midwayInspectionRewardAmount) {
		this.midwayInspectionRewardAmount = midwayInspectionRewardAmount;
	}

	public Double getMidwayInspectionPunishAmount() {
		return midwayInspectionPunishAmount;
	}

	public void setMidwayInspectionPunishAmount(Double midwayInspectionPunishAmount) {
		this.midwayInspectionPunishAmount = midwayInspectionPunishAmount;
	}

	public Double getCompleteInspectionRewardAmount() {
		return completeInspectionRewardAmount;
	}

	public void setCompleteInspectionRewardAmount(Double completeInspectionRewardAmount) {
		this.completeInspectionRewardAmount = completeInspectionRewardAmount;
	}

	public Double getCompleteInspectionPunishAmount() {
		return completeInspectionPunishAmount;
	}

	public void setCompleteInspectionPunishAmount(Double completeInspectionPunishAmount) {
		this.completeInspectionPunishAmount = completeInspectionPunishAmount;
	}

	public Integer getPmEmployeeId() {
		return pmEmployeeId;
	}
	public void setPmEmployeeId(Integer pmEmployeeId) {
		this.pmEmployeeId = pmEmployeeId;
	}
	public String getQueryParam() {
		return queryParam;
	}
	public void setQueryParam(String queryParam) {
		this.queryParam = queryParam;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Double getMidwayCommissionAmount() {
		return midwayCommissionAmount;
	}
	public void setMidwayCommissionAmount(Double midwayCommissionAmount) {
		this.midwayCommissionAmount = midwayCommissionAmount;
	}
	public Double getOwnpayAmount() {
		return ownpayAmount;
	}
	public void setOwnpayAmount(Double ownpayAmount) {
		this.ownpayAmount = ownpayAmount;
	}
	public Double getMaterialsStandardAmount() {
		return materialsStandardAmount;
	}
	public void setMaterialsStandardAmount(Double materialsStandardAmount) {
		this.materialsStandardAmount = materialsStandardAmount;
	}
	public Double getMidwayQcCheckPunishAmount() {
		return midwayQcCheckPunishAmount;
	}
	public void setMidwayQcCheckPunishAmount(Double midwayQcCheckPunishAmount) {
		this.midwayQcCheckPunishAmount = midwayQcCheckPunishAmount;
	}
	public Double getMidwayRewardAmount() {
		return midwayRewardAmount;
	}
	public void setMidwayRewardAmount(Double midwayRewardAmount) {
		this.midwayRewardAmount = midwayRewardAmount;
	}
	public Double getMidwayPunishAmount() {
		return midwayPunishAmount;
	}
	public void setMidwayPunishAmount(Double midwayPunishAmount) {
		this.midwayPunishAmount = midwayPunishAmount;
	}
	public Double getMidwayAuxiliaryMaterialsSettleAmount() {
		return midwayAuxiliaryMaterialsSettleAmount;
	}
	public void setMidwayAuxiliaryMaterialsSettleAmount(Double midwayAuxiliaryMaterialsSettleAmount) {
		this.midwayAuxiliaryMaterialsSettleAmount = midwayAuxiliaryMaterialsSettleAmount;
	}
	public Double getCompleteCommissionAmount() {
		return completeCommissionAmount;
	}
	public void setCompleteCommissionAmount(Double completeCommissionAmount) {
		this.completeCommissionAmount = completeCommissionAmount;
	}
	public Double getCompletQcCheckPunishAmount() {
		return completQcCheckPunishAmount;
	}
	public void setCompletQcCheckPunishAmount(Double completQcCheckPunishAmount) {
		this.completQcCheckPunishAmount = completQcCheckPunishAmount;
	}
	public Double getCompleteRewardAmount() {
		return completeRewardAmount;
	}
	public void setCompleteRewardAmount(Double completeRewardAmount) {
		this.completeRewardAmount = completeRewardAmount;
	}
	public Double getCompletePunishAmount() {
		return completePunishAmount;
	}
	public void setCompletePunishAmount(Double completePunishAmount) {
		this.completePunishAmount = completePunishAmount;
	}
	public Double getMaterialSelfbuyReimburseAmount() {
		return materialSelfbuyReimburseAmount;
	}
	public void setMaterialSelfbuyReimburseAmount(Double materialSelfbuyReimburseAmount) {
		this.materialSelfbuyReimburseAmount = materialSelfbuyReimburseAmount;
	}
	public Double getGuaranteeMoneyAmount() {
		return guaranteeMoneyAmount;
	}
	public void setGuaranteeMoneyAmount(Double guaranteeMoneyAmount) {
		this.guaranteeMoneyAmount = guaranteeMoneyAmount;
	}
	public Double getCompleteAuxiliaryMaterialsSettleAmount() {
		return completeAuxiliaryMaterialsSettleAmount;
	}
	public void setCompleteAuxiliaryMaterialsSettleAmount(Double completeAuxiliaryMaterialsSettleAmount) {
		this.completeAuxiliaryMaterialsSettleAmount = completeAuxiliaryMaterialsSettleAmount;
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
	public String getSettleBillType() {
		return settleBillType;
	}
	public void setSettleBillType(String settleBillType) {
		this.settleBillType = settleBillType;
	}
	public Double getSettlementBudgetAmount() {
		return settlementBudgetAmount;
	}
	public void setSettlementBudgetAmount(Double settlementBudgetAmount) {
		this.settlementBudgetAmount = settlementBudgetAmount;
	}
	public Date getAcceptCheckDatetime() {
		return acceptCheckDatetime;
	}
	public void setAcceptCheckDatetime(Date acceptCheckDatetime) {
		this.acceptCheckDatetime = acceptCheckDatetime;
	}
    
}
