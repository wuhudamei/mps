package cn.damei.entity.mobile.Manager;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class balanceDetail implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	
	
	private Integer  balanceId;
	private String balanceCode;
	public String getBalanceCode() {
		return balanceCode;
	}
	public void setBalanceCode(String balanceCode) {
		this.balanceCode = balanceCode;
	}
	private Integer orderId;
	private Integer managerId;
	private String projectMode;
	private Date balanceDate;
	private Double midBalanceMoney;
	private Double completeBalanceMoney;
	private Double freePayMoney;
	private Double materialsStandardAmount;
	private Double midFineMoney;
	private Double comleteFineMoney;
	private Double guaranteMoney;
	private Double materialSelfbuyReimburseAmount;
	private Double totalMoney;
	private String remarks;
	private Integer createBy;
	private Integer updateBy;
	private Date createDate;
	private Date updateDate;
	private String delFlag;
	private Double midwayAuxiliaryMaterialsDeductAmount;// 中期辅料扣除金额
	private Double completeAuxiliaryMaterialsDeductAmount;// 竣工辅料扣除金额
	private Double midwayRewardAmount;
	private Double midwayPunishAmount;
	private Double completeRewardAmount;
	private Double completePunishAmount;
	private String settleMonth;
	private Integer settleBillId;  //项目经理结算单Id
	private String customerName;		// 客户姓名
	private String communityName;		// 小区名称
	private String buildNumber;		// 几号楼
	private String buildUnit;		// 几单元
	private String buildRoom;		// 哪一室  
	private String settleBillType;// 结算单类型
	private Date settleMonthDate;
	private String summaryBillIds;
	
	
	public String getSummaryBillIds() {
		return summaryBillIds;
	}
	public void setSummaryBillIds(String summaryBillIds) {
		this.summaryBillIds = summaryBillIds;
	}
	public Date getSettleMonthDate() throws ParseException {
		if(settleMonth != null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
			settleMonthDate = sdf.parse(settleMonth);
		}
		return settleMonthDate;
	}
	public void setSettleMonthDate(Date settleMonthDate) {
		this.settleMonthDate = settleMonthDate;
	}
	public Integer getSettleBillId() {
		return settleBillId;
	}
	public void setSettleBillId(Integer settleBillId) {
		this.settleBillId = settleBillId;
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
	public String getSettleMonth() {
		return settleMonth;
	}
	public void setSettleMonth(String settleMonth) {
		this.settleMonth = settleMonth;
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
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	public Integer getBalanceId() {
		return balanceId;
	}
	public void setBalanceId(Integer balanceId) {
		this.balanceId = balanceId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getManagerId() {
		return managerId;
	}
	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}
	public String getProjectMode() {
		return projectMode;
	}
	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}
	public Date getBalanceDate() {
		return balanceDate;
	}
	public void setBalanceDate(Date balanceDate) {
		this.balanceDate = balanceDate;
	}
	public Double getMidBalanceMoney() {
		return midBalanceMoney;
	}
	public void setMidBalanceMoney(Double midBalanceMoney) {
		this.midBalanceMoney = midBalanceMoney;
	}
	public Double getCompleteBalanceMoney() {
		return completeBalanceMoney;
	}
	public void setCompleteBalanceMoney(Double completeBalanceMoney) {
		this.completeBalanceMoney = completeBalanceMoney;
	}
	public Double getFreePayMoney() {
		return freePayMoney;
	}
	public void setFreePayMoney(Double freePayMoney) {
		this.freePayMoney = freePayMoney;
	}
	public Double getMaterialsStandardAmount() {
		return materialsStandardAmount;
	}
	public void setMaterialsStandardAmount(Double materialsStandardAmount) {
		this.materialsStandardAmount = materialsStandardAmount;
	}
	public Double getMidFineMoney() {
		return midFineMoney;
	}
	public void setMidFineMoney(Double midFineMoney) {
		this.midFineMoney = midFineMoney;
	}
	public Double getComleteFineMoney() {
		return comleteFineMoney;
	}
	public void setComleteFineMoney(Double comleteFineMoney) {
		this.comleteFineMoney = comleteFineMoney;
	}
	public Double getGuaranteMoney() {
		return guaranteMoney;
	}
	public void setGuaranteMoney(Double guaranteMoney) {
		this.guaranteMoney = guaranteMoney;
	}
	public Double getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Integer getCreateBy() {
		return createBy;
	}
	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}
	public Integer getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Double getMidwayAuxiliaryMaterialsDeductAmount() {
		return midwayAuxiliaryMaterialsDeductAmount;
	}
	public void setMidwayAuxiliaryMaterialsDeductAmount(Double midwayAuxiliaryMaterialsDeductAmount) {
		this.midwayAuxiliaryMaterialsDeductAmount = midwayAuxiliaryMaterialsDeductAmount;
	}
	public Double getCompleteAuxiliaryMaterialsDeductAmount() {
		return completeAuxiliaryMaterialsDeductAmount;
	}
	public void setCompleteAuxiliaryMaterialsDeductAmount(Double completeAuxiliaryMaterialsDeductAmount) {
		this.completeAuxiliaryMaterialsDeductAmount = completeAuxiliaryMaterialsDeductAmount;
	}
}
