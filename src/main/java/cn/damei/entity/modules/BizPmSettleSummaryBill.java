/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity2;

import java.util.ArrayList;
import java.util.List;

/**
 * 结算汇总单Entity
 * @author qww
 * @version 2016-12-26
 */
public class BizPmSettleSummaryBill extends DataEntity2<BizPmSettleSummaryBill> {
	
	private static final long serialVersionUID = 1L;
	private String pmSettleSummaryBillCode;		// 项目经理结算月度汇总单编号 -- '
	private Integer storeId;		// 门店id -- '
	private String settleMonth;		// 月度 -- '
	private Integer pmEmployeeId;		// 项目经理员工id -- '
	private Double midwayCommissionAmount;		// 中期提成金额 -- '
	private Double completeCommissionAmount;		// 竣工提成金额 -- '
	private Double ownpayAmount;		// 自主支配项金额 -- '
	private Double materialsStandardAmount;		// 标化辅材金额 -- '
	private Double midwayQcCheckPunishAmount;		// 中期质检罚款金额 -- '
	private Double completQcCheckPunishAmount;		// 竣工质检罚款金额 -- '
	private Double materialSelfbuyReimburseAmount;//自采材料报销金额
	private Double midwayAuxiliaryMaterialsSettleAmount; //中期辅料结算金额
	private Double completeAuxiliaryMaterialsSettleAmount;//竣工辅料结算金额
	private Double midwayRewardAmount;//中期奖励金额
	private Double midwayPunishAmount;//中期罚款金额
	private Double completeRewardAmount;//竣工奖励金额
	private Double completePunishAmount;//竣工罚款金额
	private Double midwayInspectionRewardAmount;//中期奖励金额
	private Double midwayInspectionPunishAmount;//中期罚款金额
	private Double completeInspectionRewardAmount;//竣工奖励金额
	private Double completeInspectionPunishAmount;//竣工罚款金额
	private Double guaranteeMoneyAmount;		// 质保金金额 -- '
	private Double totalAmount;		// 合计金额 -- '
	private String status; // 状态 -- '
	private String settleRole;
	private Double qcMidwayCommissionAmount;
	private Double qcCompleteCommissionAmount;
	private Double qcMidwayLongwayAmount;
	private Double qcCompleteLongwayAmount;

	private String settleBillIds;
	private String itemManager; // 项目经理
	private String itemManagerPhone; // 项目经理电话
	private String orderInspector; // 质检员
	private String orderInspectorPhone; // 质检员电话
	private Integer enginDepartId;
	private String enginDepartName;
	private List<Integer> enginDepartIds = new ArrayList<Integer>();
	
	public BizPmSettleSummaryBill() {
		super();
	}

	public BizPmSettleSummaryBill(Integer id){
		super(id);
	}

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

	public Double getMidwayAuxiliaryMaterialsSettleAmount() {
		return midwayAuxiliaryMaterialsSettleAmount;
	}

	public void setMidwayAuxiliaryMaterialsSettleAmount(Double midwayAuxiliaryMaterialsSettleAmount) {
		this.midwayAuxiliaryMaterialsSettleAmount = midwayAuxiliaryMaterialsSettleAmount;
	}

	public Double getCompleteAuxiliaryMaterialsSettleAmount() {
		return completeAuxiliaryMaterialsSettleAmount;
	}

	public void setCompleteAuxiliaryMaterialsSettleAmount(Double completeAuxiliaryMaterialsSettleAmount) {
		this.completeAuxiliaryMaterialsSettleAmount = completeAuxiliaryMaterialsSettleAmount;
	}

	public Double getMaterialSelfbuyReimburseAmount() {
		return materialSelfbuyReimburseAmount;
	}

	public void setMaterialSelfbuyReimburseAmount(Double materialSelfbuyReimburseAmount) {
		this.materialSelfbuyReimburseAmount = materialSelfbuyReimburseAmount;
	}

	@Length(min=0, max=100, message="项目经理结算月度汇总单编号 -- '长度必须介于 0 和 100 之间")
	public String getPmSettleSummaryBillCode() {
		return pmSettleSummaryBillCode;
	}

	public void setPmSettleSummaryBillCode(String pmSettleSummaryBillCode) {
		this.pmSettleSummaryBillCode = pmSettleSummaryBillCode;
	}
	
	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getSettleMonth() {
		return settleMonth;
	}

	public void setSettleMonth(String settleMonth) {
		this.settleMonth = settleMonth;
	}

	public Integer getPmEmployeeId() {
		return pmEmployeeId;
	}

	public void setPmEmployeeId(Integer pmEmployeeId) {
		this.pmEmployeeId = pmEmployeeId;
	}
	
	public Double getMidwayCommissionAmount() {
		return midwayCommissionAmount;
	}

	public void setMidwayCommissionAmount(Double midwayCommissionAmount) {
		this.midwayCommissionAmount = midwayCommissionAmount;
	}
	
	public Double getCompleteCommissionAmount() {
		return completeCommissionAmount;
	}

	public void setCompleteCommissionAmount(Double completeCommissionAmount) {
		this.completeCommissionAmount = completeCommissionAmount;
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
	
	public Double getCompletQcCheckPunishAmount() {
		return completQcCheckPunishAmount;
	}

	public void setCompletQcCheckPunishAmount(Double completQcCheckPunishAmount) {
		this.completQcCheckPunishAmount = completQcCheckPunishAmount;
	}
	
	public Double getGuaranteeMoneyAmount() {
		return guaranteeMoneyAmount;
	}

	public void setGuaranteeMoneyAmount(Double guaranteeMoneyAmount) {
		this.guaranteeMoneyAmount = guaranteeMoneyAmount;
	}
	
	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSettleRole() {
		return settleRole;
	}

	public void setSettleRole(String settleRole) {
		this.settleRole = settleRole;
	}

	public Double getQcMidwayCommissionAmount() {
		return qcMidwayCommissionAmount;
	}

	public void setQcMidwayCommissionAmount(Double qcMidwayCommissionAmount) {
		this.qcMidwayCommissionAmount = qcMidwayCommissionAmount;
	}

	public Double getQcCompleteCommissionAmount() {
		return qcCompleteCommissionAmount;
	}

	public void setQcCompleteCommissionAmount(Double qcCompleteCommissionAmount) {
		this.qcCompleteCommissionAmount = qcCompleteCommissionAmount;
	}

	public Double getQcMidwayLongwayAmount() {
		return qcMidwayLongwayAmount;
	}

	public void setQcMidwayLongwayAmount(Double qcMidwayLongwayAmount) {
		this.qcMidwayLongwayAmount = qcMidwayLongwayAmount;
	}

	public Double getQcCompleteLongwayAmount() {
		return qcCompleteLongwayAmount;
	}

	public void setQcCompleteLongwayAmount(Double qcCompleteLongwayAmount) {
		this.qcCompleteLongwayAmount = qcCompleteLongwayAmount;
	}

	public String getSettleBillIds() {
		return settleBillIds;
	}

	public void setSettleBillIds(String settleBillIds) {
		this.settleBillIds = settleBillIds;
	}

	public String getItemManager() {
		return itemManager;
	}

	public void setItemManager(String itemManager) {
		this.itemManager = itemManager;
	}

	public String getItemManagerPhone() {
		return itemManagerPhone;
	}

	public void setItemManagerPhone(String itemManagerPhone) {
		this.itemManagerPhone = itemManagerPhone;
	}

	public String getOrderInspector() {
		return orderInspector;
	}

	public void setOrderInspector(String orderInspector) {
		this.orderInspector = orderInspector;
	}

	public String getOrderInspectorPhone() {
		return orderInspectorPhone;
	}

	public void setOrderInspectorPhone(String orderInspectorPhone) {
		this.orderInspectorPhone = orderInspectorPhone;
	}

	public Integer getEnginDepartId() {
		return enginDepartId;
	}

	public void setEnginDepartId(Integer enginDepartId) {
		this.enginDepartId = enginDepartId;
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
}