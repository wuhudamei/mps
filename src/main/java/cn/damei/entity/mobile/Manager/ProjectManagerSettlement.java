package cn.damei.entity.mobile.Manager;

import cn.damei.common.persistence.DataEntity2;

public class ProjectManagerSettlement extends DataEntity2<ProjectManagerSettlement>{


	private static final long serialVersionUID = 1L;
	
	
	private String storeId;
	private String projectMode;
	private String orderNumber;
	private String engineDepartId;
	private int itemManagerId;
	private String customerName;
	private String customerPhone;
	private String orderStatusNumber;
	private String communityName;
	private String buildNumber;
	private String buildUnit;
	private String buildRoom;
	private String contractArea;
	
	private String orderInspectorName;
	private String orderInspectorPhone;
	private String itemManagerName;
	private String itemManagerPhone;
	
	
	private String contractStartSate;
	private String actualStartDate;
	private String contractTime;
	private String orderStatusDescription;
	
	
	private String orderId;
	private String contractAmount                                 ;
	private String midwayQcCheckPunishAmount                   ;
	private String rewardAmount                                   ;
	private String punishAmount                                   ;
	private String orderChangeAddAmount                         ;
	private String orderChangeReduceAmount                      ;
	private String midwayBasicworkAddAmount                     ;
	private String midwayMaterialsStandardAmount                ;
	private String midwayMaterialsAuxiliaryAmount               ;
	private String midwaySandCementAmount                       ;
	private String midwaySwitchPanelAmount                      ;
	private String midwayWorkerSalaryAmount                     ;
	private String midwayMaterialCarryCostAmount               ;
	private String midwayContractSettleRate                     ;
	private String completeGuaranteeMoneyAmount                 ;
	private String completeLongwayCommissionAmount              ;
	private String contractSettleAmount                          ;
	private String realSettleAmount                              ;
	private String status;
	private String statusDatetime;
	private String statusDescribe;
	private String settleBillType;
	private String pmPreIndustrySettleBillCode;
	
	private String countType;

    private String isNewSettleBill;



    public String getIsNewSettleBill() {
        return isNewSettleBill;
    }

    public void setIsNewSettleBill(String isNewSettleBill) {
        this.isNewSettleBill = isNewSettleBill;
    }

    public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getProjectMode() {
		return projectMode;
	}
	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}
	public String getEngineDepartId() {
		return engineDepartId;
	}
	public void setEngineDepartId(String engineDepartId) {
		this.engineDepartId = engineDepartId;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getOrderInspectorName() {
		return orderInspectorName;
	}
	public void setOrderInspectorName(String orderInspectorName) {
		this.orderInspectorName = orderInspectorName;
	}
	public String getOrderInspectorPhone() {
		return orderInspectorPhone;
	}
	public void setOrderInspectorPhone(String orderInspectorPhone) {
		this.orderInspectorPhone = orderInspectorPhone;
	}
	public String getItemManagerName() {
		return itemManagerName;
	}
	public void setItemManagerName(String itemManagerName) {
		this.itemManagerName = itemManagerName;
	}
	public String getItemManagerPhone() {
		return itemManagerPhone;
	}
	public void setItemManagerPhone(String itemManagerPhone) {
		this.itemManagerPhone = itemManagerPhone;
	}
	public String getContractStartSate() {
		return contractStartSate;
	}
	public void setContractStartSate(String contractStartSate) {
		this.contractStartSate = contractStartSate;
	}
	public String getActualStartDate() {
		return actualStartDate;
	}
	public void setActualStartDate(String actualStartDate) {
		this.actualStartDate = actualStartDate;
	}
	public String getContractTime() {
		return contractTime;
	}
	public void setContractTime(String contractTime) {
		this.contractTime = contractTime;
	}
	public String getOrderStatusDescription() {
		return orderStatusDescription;
	}
	public void setOrderStatusDescription(String orderStatusDescription) {
		this.orderStatusDescription = orderStatusDescription;
	}
	public String getCountType() {
		return countType;
	}
	public void setCountType(String countType) {
		this.countType = countType;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public int getItemManagerId() {
		return itemManagerId;
	}
	public void setItemManagerId(int itemManagerId) {
		this.itemManagerId = itemManagerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	public String getOrderStatusNumber() {
		return orderStatusNumber;
	}
	public void setOrderStatusNumber(String orderStatusNumber) {
		this.orderStatusNumber = orderStatusNumber;
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
	public String getContractArea() {
		return contractArea;
	}
	public void setContractArea(String contractArea) {
		this.contractArea = contractArea;
	}
	public String getContractAmount() {
		return contractAmount;
	}
	public void setContractAmount(String contractAmount) {
		this.contractAmount = contractAmount;
	}
	public String getMidwayQcCheckPunishAmount() {
		return midwayQcCheckPunishAmount;
	}
	public void setMidwayQcCheckPunishAmount(String midwayQcCheckPunishAmount) {
		this.midwayQcCheckPunishAmount = midwayQcCheckPunishAmount;
	}
	public String getRewardAmount() {
		return rewardAmount;
	}
	public void setRewardAmount(String rewardAmount) {
		this.rewardAmount = rewardAmount;
	}
	public String getPunishAmount() {
		return punishAmount;
	}
	public void setPunishAmount(String punishAmount) {
		this.punishAmount = punishAmount;
	}
	public String getOrderChangeAddAmount() {
		return orderChangeAddAmount;
	}
	public void setOrderChangeAddAmount(String orderChangeAddAmount) {
		this.orderChangeAddAmount = orderChangeAddAmount;
	}
	public String getOrderChangeReduceAmount() {
		return orderChangeReduceAmount;
	}
	public void setOrderChangeReduceAmount(String orderChangeReduceAmount) {
		this.orderChangeReduceAmount = orderChangeReduceAmount;
	}
	public String getMidwayBasicworkAddAmount() {
		return midwayBasicworkAddAmount;
	}
	public void setMidwayBasicworkAddAmount(String midwayBasicworkAddAmount) {
		this.midwayBasicworkAddAmount = midwayBasicworkAddAmount;
	}
	public String getMidwayMaterialsStandardAmount() {
		return midwayMaterialsStandardAmount;
	}
	public void setMidwayMaterialsStandardAmount(String midwayMaterialsStandardAmount) {
		this.midwayMaterialsStandardAmount = midwayMaterialsStandardAmount;
	}
	public String getMidwayMaterialsAuxiliaryAmount() {
		return midwayMaterialsAuxiliaryAmount;
	}
	public void setMidwayMaterialsAuxiliaryAmount(String midwayMaterialsAuxiliaryAmount) {
		this.midwayMaterialsAuxiliaryAmount = midwayMaterialsAuxiliaryAmount;
	}
	public String getMidwaySandCementAmount() {
		return midwaySandCementAmount;
	}
	public void setMidwaySandCementAmount(String midwaySandCementAmount) {
		this.midwaySandCementAmount = midwaySandCementAmount;
	}
	public String getMidwaySwitchPanelAmount() {
		return midwaySwitchPanelAmount;
	}
	public void setMidwaySwitchPanelAmount(String midwaySwitchPanelAmount) {
		this.midwaySwitchPanelAmount = midwaySwitchPanelAmount;
	}
	public String getMidwayWorkerSalaryAmount() {
		return midwayWorkerSalaryAmount;
	}
	public void setMidwayWorkerSalaryAmount(String midwayWorkerSalaryAmount) {
		this.midwayWorkerSalaryAmount = midwayWorkerSalaryAmount;
	}
	public String getMidwayMaterialCarryCostAmount() {
		return midwayMaterialCarryCostAmount;
	}
	public void setMidwayMaterialCarryCostAmount(String midwayMaterialCarryCostAmount) {
		this.midwayMaterialCarryCostAmount = midwayMaterialCarryCostAmount;
	}
	public String getMidwayContractSettleRate() {
		return midwayContractSettleRate;
	}
	public void setMidwayContractSettleRate(String midwayContractSettleRate) {
		this.midwayContractSettleRate = midwayContractSettleRate;
	}
	public String getCompleteGuaranteeMoneyAmount() {
		return completeGuaranteeMoneyAmount;
	}
	public void setCompleteGuaranteeMoneyAmount(String completeGuaranteeMoneyAmount) {
		this.completeGuaranteeMoneyAmount = completeGuaranteeMoneyAmount;
	}
	public String getCompleteLongwayCommissionAmount() {
		return completeLongwayCommissionAmount;
	}
	public void setCompleteLongwayCommissionAmount(String completeLongwayCommissionAmount) {
		this.completeLongwayCommissionAmount = completeLongwayCommissionAmount;
	}
	public String getContractSettleAmount() {
		return contractSettleAmount;
	}
	public void setContractSettleAmount(String contractSettleAmount) {
		this.contractSettleAmount = contractSettleAmount;
	}
	public String getRealSettleAmount() {
		return realSettleAmount;
	}
	public void setRealSettleAmount(String realSettleAmount) {
		this.realSettleAmount = realSettleAmount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatusDatetime() {
		return statusDatetime;
	}
	public void setStatusDatetime(String statusDatetime) {
		this.statusDatetime = statusDatetime;
	}
	public String getStatusDescribe() {
		return statusDescribe;
	}
	public void setStatusDescribe(String statusDescribe) {
		this.statusDescribe = statusDescribe;
	}
	public String getSettleBillType() {
		return settleBillType;
	}
	public void setSettleBillType(String settleBillType) {
		this.settleBillType = settleBillType;
	}
	public String getPmPreIndustrySettleBillCode() {
		return pmPreIndustrySettleBillCode;
	}
	public void setPmPreIndustrySettleBillCode(String pmPreIndustrySettleBillCode) {
		this.pmPreIndustrySettleBillCode = pmPreIndustrySettleBillCode;
	}
	
	
	
	
	
}
