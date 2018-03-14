package cn.damei.entity.modules;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.damei.common.persistence.DataEntity2;


public class BizPmPreIndustrySettleBill extends DataEntity2<BizPmPreIndustrySettleBill>{
	private static final long serialVersionUID = 1L;
	private Integer storeId;
	private Integer projectMode;
	private Integer enginDepartId;
	private String departmentName;
	private String orderNum;
	private List<Integer> enginDepartIds = new ArrayList<Integer>();
	private String communityName;
	private String buildNumber;
	private String buildUnit;
	private String buildRoom;
	private String customerName;
	private String customerPhone;
	private String itemCustomer;
	private String itemPhone;
	
	
	private String pmPreIndustrySettleBillCode;
	private String settleBillType;
	private Integer orderId;
	private Integer pmEmployeeId;
	private String settleMonth;
	private Date settleDatetime;
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
    private Double midwaySwitchPanelAmount;
    private Double midwayWorkerSalaryAmount;
    private Double midwayMaterialCarryCostAmount;
    private Double midwayContractSettleRate;
    private Double completeGuaranteeMoneyAmount;
    private Double completeLongwayCommissionAmount;
    private Double contractSettleAmount;
    private Double realSettleAmount;
    private String status;
    private Integer statusOperatorEmployeeId;
    private Date statusDatetime;
    private String statusDescribe;
    private Integer pmPreIndustrySettleSummaryBillId;

    private Date createSettleMonthStartDate;

    private Date createSettleMonthEndDate;

    private Date createSettleStartDate;

    private Date createSettleEndDate;

    private Date createMonthDate;

    private String isNewSettleBill;

    
    private List<String> statusList = null;

    public String getIsNewSettleBill() {
        return isNewSettleBill;
    }

    public void setIsNewSettleBill(String isNewSettleBill) {
        this.isNewSettleBill = isNewSettleBill;
    }

    public Date getCreateMonthDate() {
		return createMonthDate;
	}

	public void setCreateMonthDate(Date createMonthDate) {
		this.createMonthDate = createMonthDate;
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

	public Date getCreateSettleStartDate() {
		return createSettleStartDate;
	}

	public void setCreateSettleStartDate(Date createSettleStartDate) {
		this.createSettleStartDate = createSettleStartDate;
	}

	public Date getCreateSettleEndDate() {
		return createSettleEndDate;
	}

	public void setCreateSettleEndDate(Date createSettleEndDate) {
		this.createSettleEndDate = createSettleEndDate;
	}

	public Integer getPmPreIndustrySettleSummaryBillId() {
		return pmPreIndustrySettleSummaryBillId;
	}
	public void setPmPreIndustrySettleSummaryBillId(Integer pmPreIndustrySettleSummaryBillId) {
		this.pmPreIndustrySettleSummaryBillId = pmPreIndustrySettleSummaryBillId;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	public List<String> getStatusList() {
		return statusList;
	}
	public void setStatusList(List<String> statusList) {
		this.statusList = statusList;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public Integer getProjectMode() {
		return projectMode;
	}
	public void setProjectMode(Integer projectMode) {
		this.projectMode = projectMode;
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
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public List<Integer> getEnginDepartIds() {
		return enginDepartIds;
	}
	public void setEnginDepartIds(List<Integer> enginDepartIds) {
		this.enginDepartIds = enginDepartIds;
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
	public String getItemCustomer() {
		return itemCustomer;
	}
	public void setItemCustomer(String itemCustomer) {
		this.itemCustomer = itemCustomer;
	}
	public String getItemPhone() {
		return itemPhone;
	}
	public void setItemPhone(String itemPhone) {
		this.itemPhone = itemPhone;
	}
	public String getPmPreIndustrySettleBillCode() {
		return pmPreIndustrySettleBillCode;
	}
	public void setPmPreIndustrySettleBillCode(String pmPreIndustrySettleBillCode) {
		this.pmPreIndustrySettleBillCode = pmPreIndustrySettleBillCode;
	}
	public String getSettleBillType() {
		return settleBillType;
	}
	public void setSettleBillType(String settleBillType) {
		this.settleBillType = settleBillType;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getPmEmployeeId() {
		return pmEmployeeId;
	}
	public void setPmEmployeeId(Integer pmEmployeeId) {
		this.pmEmployeeId = pmEmployeeId;
	}
	public String getSettleMonth() {
		return settleMonth;
	}
	public void setSettleMonth(String settleMonth) {
		this.settleMonth = settleMonth;
	}
	public Date getSettleDatetime() {
		return settleDatetime;
	}
	public void setSettleDatetime(Date settleDatetime) {
		this.settleDatetime = settleDatetime;
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
	public Double getMidwaySwitchPanelAmount() {
		return midwaySwitchPanelAmount;
	}
	public void setMidwaySwitchPanelAmount(Double midwaySwitchPanelAmount) {
		this.midwaySwitchPanelAmount = midwaySwitchPanelAmount;
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
	public Double getMidwayContractSettleRate() {
		return midwayContractSettleRate;
	}
	public void setMidwayContractSettleRate(Double midwayContractSettleRate) {
		this.midwayContractSettleRate = midwayContractSettleRate;
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

    
}
