package cn.damei.entity.modules;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.damei.common.persistence.DataEntity2;

/**
 * 准产业项目经理结算单汇总实体类
 * @author hyh
 *
 */
public class BizPmPreIndustrySettleSummaryBill extends DataEntity2<BizPmPreIndustrySettleSummaryBill> {

	private static final long serialVersionUID = 1L;
	
	private String pmPreIndustrySettleSummaryBill;//汇总单号
	
	private Integer storeId;//门店

	private String storeName;
	
	private Integer projectMode;//工程模式
	
	private Integer enginDepartId;//区域
	
	private String enginDepartName;//
	
	private List<Integer> enginDepartIds = new ArrayList<Integer>();
	
	private String settleMonth;//结算月份
	
	private Integer pmEmployeeId;//项目经理员工ID
	
	private String pmEmployeeName;//项目经理员工
	
	private String pmEmployeePhone;//项目经理手机号
	
	private Double contractAmount;//承包总金额
	
	private Double midwayQcCheckPunishAmount;//质检罚款总金额
	
	private Double rewardAmount;//奖励金额
	
	private Double punishAmount;//扣款金额
	
	private Double orderChangeAddAmount;//变更增项金额
	
	private Double orderChangeReduceAmount;//变更减项金额
	
	private Double midwayBasicworkAddAmount;//中期基装增项金额
	
	private Double midwayMaterialsStandardAmount;//中期标化辅料扣款金额
	
	private Double midwayMaterialsAuxiliaryAmount;//中期辅料用量扣款金额
	
	private Double midwaySandCementAmount;//中期水泥沙子扣款金额
	
	private Double midwaSwitchPanelAmount;//中期开关面板扣款金额
	
	private Double midwayWorkerSalaryAmount;//中期工人人工费扣款金额
	
	private Double midwayMaterialCarryCostAmount;//中期材料搬运及运输费金额
	
	private Double completeGuaranteeMoneyAmount;//竣工质保金金额
	
	private Double completeLongwayCommissionAmount;//竣工远程费金额
	
	private Double contractSettleAmount;//承包价结算金额
	
	private Double realSettleAmount;//实际结算金额
	
	private Double midwayRealSettleAmount;//中期结算金额
	
	private Double completeRealSettleAmount;//竣工结算金额
	
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
