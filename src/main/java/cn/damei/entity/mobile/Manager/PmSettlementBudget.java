package cn.damei.entity.mobile.Manager;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

/**
 * 项目经理订单结算金额预览实体类
 * @author hyh
 *
 */
public class PmSettlementBudget extends DataEntity2<PmSettlementBudget>{

	private static final long serialVersionUID = 1L;
	private Integer orderId;//订单Id
	private String customerName;		// 客户姓名
	private String communityName;		// 小区名称
	private String buildNumber;		// 几号楼
	private String buildUnit;		// 几单元
	private String buildRoom;		// 哪一室 
	private String settleBillType;     //结算阶段  10:中期结算  20：竣工结算
    private Double settlementBudgetAmount;//预计金额
    private Date acceptCheckDatetime;//验收时间
    private Double midwayCommissionAmount;		// 中期提成金额 -- '
    private Double ownpayAmount;		// 自主支配项金额 -- '
    private Double materialsStandardAmount;		// 标化辅材金额 -- '
    private Double midwayQcCheckPunishAmount;		// 中期质检罚款金额 -- '
    private Double midwayRewardAmount;//中期奖励金额
    private Double midwayPunishAmount;//中期罚款金额
	private Double midwayInspectionRewardAmount;//中期巡检奖励金额
	private Double midwayInspectionPunishAmount;//中期巡检罚款金额
    private Double midwayAuxiliaryMaterialsSettleAmount; //中期辅料结算金额
    private Double completeCommissionAmount;		// 竣工提成金额 -- '
    private Double completQcCheckPunishAmount;		// 竣工质检罚款金额 -- '
    private Double completeRewardAmount;//竣工奖励金额
    private Double completePunishAmount;//竣工罚款金额
	private Double completeInspectionRewardAmount;//竣工巡检奖励金额
	private Double completeInspectionPunishAmount;//竣工巡检罚款金额
    private Double materialSelfbuyReimburseAmount;//自采材料报销金额
    private Double guaranteeMoneyAmount;		// 质保金金额 -- '
    private Double completeAuxiliaryMaterialsSettleAmount;//竣工辅料结算金额
    private Double totalAmount;//结算总金额
    private Integer pmEmployeeId;//项目经理Id
	private String queryParam; //查询参数

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
