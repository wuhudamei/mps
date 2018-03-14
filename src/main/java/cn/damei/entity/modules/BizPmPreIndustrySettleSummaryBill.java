package cn.damei.entity.modules;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.damei.common.persistence.DataEntity2;


public class BizPmPreIndustrySettleSummaryBill extends DataEntity2<BizPmPreIndustrySettleSummaryBill> {

	private static final long serialVersionUID = 1L;
	
	private String pmPreIndustrySettleSummaryBill;
	
	private Integer storeId;

	private String storeName;
	
	private Integer projectMode;
	
	private Integer enginDepartId;
	
	private String enginDepartName;
	
	private List<Integer> enginDepartIds = new ArrayList<Integer>();
	
	private String settleMonth;
	
	private Integer pmEmployeeId;
	
	private String pmEmployeeName;
	
	private String pmEmployeePhone;
	
	private Double contractAmount;
	
	private Double midwayQcCheckPunishAmount;
	
	private Double rewardAmount;
	
	private Double punishAmount;
	
	private Double orderChangeAddAmount;
	
	private Double orderChangeReduceAmount;
	
	private Double midwayBasicworkAddAmount;
	
	private Double midwayMaterialsStandardAmount;
	
	private Double midwayMaterialsAuxiliaryAmount;
	
	private Double midwaySandCementAmount;
	
	private Double midwaSwitchPanelAmount;
	
	private Double midwayWorkerSalaryAmount;
	
	private Double midwayMaterialCarryCostAmount;
	
	private Double completeGuaranteeMoneyAmount;
	
	private Double completeLongwayCommissionAmount;
	
	private Double contractSettleAmount;
	
	private Double realSettleAmount;
	
	private Double midwayRealSettleAmount;
	
	private Double completeRealSettleAmount;
	
	private String status; 
	
	private Integer statusOperatorEmployeeId;
	
	private Date statusDatetime;
	
	private String statusDescribe;
	
	private String settleBillId;

	private String customerName;

	private Date createSettleMonthStartDate;

	private Date createSettleMonthEndDate;

	private String orderNumber;

	private Integer orderId;

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Date getCreateSettleMonthStartDate() {
		return createSettleMonthStartDate;
	}

	public void setCreateSettleMonthStartDate(Date createSettleMonthStartDate) {
		this.createSettleMonthStartDate = createSettleMonthStartDate;
	}

	public Date getCreateSettleMonthEndDate() {
		return createSettleMonthEndDate;
	}

	public void setCreateSettleMonthEndDate(Date createSettleMonthEndDate) {
		this.createSettleMonthEndDate = createSettleMonthEndDate;
	}

	public Integer getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(Integer projectMode) {
		this.projectMode = projectMode;
	}

	public List<Integer> getEnginDepartIds() {
		return enginDepartIds;
	}

	public void setEnginDepartIds(List<Integer> enginDepartIds) {
		this.enginDepartIds = enginDepartIds;
	}

	public String getPmEmployeePhone() {
		return pmEmployeePhone;
	}

	public void setPmEmployeePhone(String pmEmployeePhone) {
		this.pmEmployeePhone = pmEmployeePhone;
	}

	public String getPmEmployeeName() {
		return pmEmployeeName;
	}

	public void setPmEmployeeName(String pmEmployeeName) {
		this.pmEmployeeName = pmEmployeeName;
	}

	public String getEnginDepartName() {
		return enginDepartName;
	}

	public void setEnginDepartName(String enginDepartName) {
		this.enginDepartName = enginDepartName;
	}

	public String getSettleBillId() {
		return settleBillId;
	}

	public void setSettleBillId(String settleBillId) {
		this.settleBillId = settleBillId;
	}

	public Double getMidwayRealSettleAmount() {
		return midwayRealSettleAmount;
	}

	public void setMidwayRealSettleAmount(Double midwayRealSettleAmount) {
		this.midwayRealSettleAmount = midwayRealSettleAmount;
	}

	public Double getCompleteRealSettleAmount() {
		return completeRealSettleAmount;
	}

	public void setCompleteRealSettleAmount(Double completeRealSettleAmount) {
		this.completeRealSettleAmount = completeRealSettleAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getStatusOperatorEmployeeId() {
		return statusOperatorEmployeeId;
	}

	public void setStatusOperatorEmployeeId(Integer statusOperatorEmployeeId) {
		this.statusOperatorEmployeeId = statusOperatorEmployeeId;
	}

	public Date getStatusDatetime() {
		return statusDatetime;
	}

	public void setStatusDatetime(Date statusDatetime) {
		this.statusDatetime = statusDatetime;
	}

	public String getStatusDescribe() {
		return statusDescribe;
	}

	public void setStatusDescribe(String statusDescribe) {
		this.statusDescribe = statusDescribe;
	}

	public String getPmPreIndustrySettleSummaryBill() {
		return pmPreIndustrySettleSummaryBill;
	}

	public void setPmPreIndustrySettleSummaryBill(String pmPreIndustrySettleSummaryBill) {
		this.pmPreIndustrySettleSummaryBill = pmPreIndustrySettleSummaryBill;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Integer getEnginDepartId() {
		return enginDepartId;
	}

	public void setEnginDepartId(Integer enginDepartId) {
		this.enginDepartId = enginDepartId;
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

	public Double getContractAmount() {
		return contractAmount;
	}

	public void setContractAmount(Double contractAmount) {
		this.contractAmount = contractAmount;
	}

	public Double getMidwayQcCheckPunishAmount() {
		return midwayQcCheckPunishAmount;
	}

	public void setMidwayQcCheckPunishAmount(Double midwayQcCheckPunishAmount) {
		this.midwayQcCheckPunishAmount = midwayQcCheckPunishAmount;
	}

	public Double getRewardAmount() {
		return rewardAmount;
	}

	public void setRewardAmount(Double rewardAmount) {
		this.rewardAmount = rewardAmount;
	}

	public Double getPunishAmount() {
		return punishAmount;
	}

	public void setPunishAmount(Double punishAmount) {
		this.punishAmount = punishAmount;
	}

	public Double getOrderChangeAddAmount() {
		return orderChangeAddAmount;
	}

	public void setOrderChangeAddAmount(Double orderChangeAddAmount) {
		this.orderChangeAddAmount = orderChangeAddAmount;
	}

	public Double getOrderChangeReduceAmount() {
		return orderChangeReduceAmount;
	}

	public void setOrderChangeReduceAmount(Double orderChangeReduceAmount) {
		this.orderChangeReduceAmount = orderChangeReduceAmount;
	}

	public Double getMidwayBasicworkAddAmount() {
		return midwayBasicworkAddAmount;
	}

	public void setMidwayBasicworkAddAmount(Double midwayBasicworkAddAmount) {
		this.midwayBasicworkAddAmount = midwayBasicworkAddAmount;
	}

	public Double getMidwayMaterialsStandardAmount() {
		return midwayMaterialsStandardAmount;
	}

	public void setMidwayMaterialsStandardAmount(Double midwayMaterialsStandardAmount) {
		this.midwayMaterialsStandardAmount = midwayMaterialsStandardAmount;
	}

	public Double getMidwayMaterialsAuxiliaryAmount() {
		return midwayMaterialsAuxiliaryAmount;
	}

	public void setMidwayMaterialsAuxiliaryAmount(Double midwayMaterialsAuxiliaryAmount) {
		this.midwayMaterialsAuxiliaryAmount = midwayMaterialsAuxiliaryAmount;
	}

	public Double getMidwaySandCementAmount() {
		return midwaySandCementAmount;
	}

	public void setMidwaySandCementAmount(Double midwaySandCementAmount) {
		this.midwaySandCementAmount = midwaySandCementAmount;
	}

	public Double getMidwaSwitchPanelAmount() {
		return midwaSwitchPanelAmount;
	}

	public void setMidwaSwitchPanelAmount(Double midwaSwitchPanelAmount) {
		this.midwaSwitchPanelAmount = midwaSwitchPanelAmount;
	}

	public Double getMidwayWorkerSalaryAmount() {
		return midwayWorkerSalaryAmount;
	}

	public void setMidwayWorkerSalaryAmount(Double midwayWorkerSalaryAmount) {
		this.midwayWorkerSalaryAmount = midwayWorkerSalaryAmount;
	}

	public Double getMidwayMaterialCarryCostAmount() {
		return midwayMaterialCarryCostAmount;
	}

	public void setMidwayMaterialCarryCostAmount(Double midwayMaterialCarryCostAmount) {
		this.midwayMaterialCarryCostAmount = midwayMaterialCarryCostAmount;
	}

	public Double getCompleteGuaranteeMoneyAmount() {
		return completeGuaranteeMoneyAmount;
	}

	public void setCompleteGuaranteeMoneyAmount(Double completeGuaranteeMoneyAmount) {
		this.completeGuaranteeMoneyAmount = completeGuaranteeMoneyAmount;
	}

	public Double getCompleteLongwayCommissionAmount() {
		return completeLongwayCommissionAmount;
	}

	public void setCompleteLongwayCommissionAmount(Double completeLongwayCommissionAmount) {
		this.completeLongwayCommissionAmount = completeLongwayCommissionAmount;
	}

	public Double getContractSettleAmount() {
		return contractSettleAmount;
	}

	public void setContractSettleAmount(Double contractSettleAmount) {
		this.contractSettleAmount = contractSettleAmount;
	}

	public Double getRealSettleAmount() {
		return realSettleAmount;
	}

	public void setRealSettleAmount(Double realSettleAmount) {
		this.realSettleAmount = realSettleAmount;
	}
	
}
