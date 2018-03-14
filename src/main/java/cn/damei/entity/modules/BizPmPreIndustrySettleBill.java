package cn.damei.entity.modules;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.damei.common.persistence.DataEntity2;

/**
 * 项目经理准产业结算单实体类
 * @author hyh
 *
 */
public class BizPmPreIndustrySettleBill extends DataEntity2<BizPmPreIndustrySettleBill>{
	private static final long serialVersionUID = 1L;
	private Integer storeId;  //门店
	private Integer projectMode; //工程模式
	private Integer enginDepartId; //区域
	private String departmentName;
	private String orderNum; //订单编号
	private List<Integer> enginDepartIds = new ArrayList<Integer>();
	private String communityName;		// 小区名称
	private String buildNumber;		// 几号楼
	private String buildUnit;		// 几单元
	private String buildRoom;		// 哪一室 
	private String customerName;	// 客户姓名
	private String customerPhone;//客户手机号
	private String itemCustomer;    // 项目经理
	private String itemPhone;       //项目经理手机号
	
	
	private String pmPreIndustrySettleBillCode;//项目经理结算单号
	private String settleBillType;//结算单类型  1：中期结算单  2：竣工结算单
	private Integer orderId;  //订单Id
	private Integer pmEmployeeId; //项目经理员工Id
	private String settleMonth; //结算月份
	private Date settleDatetime; //结算日期时间
	private Double contractAmount; //承包总金额
	private Double midwayQcCheckPunishAmount; //质检罚款金额
	private Double rewardAmount; //奖励金额
	private Double punishAmount; //扣款金额
    private Double orderChangeAddAmount; //变更增项金额
    private Double orderChangeReduceAmount;//变更减项金额
    private Double midwayBasicworkAddAmount;//中期基装增项金额
    private Double midwayMaterialsStandardAmount;//中期标化材料扣款金额
    private Double midwayMaterialsAuxiliaryAmount;//中期辅料用量扣款金额
    private Double midwaySandCementAmount;//中期沙子水泥扣款金额
    private Double midwaySwitchPanelAmount;//中期开关面板扣款金额
    private Double midwayWorkerSalaryAmount;//中期工人人工费扣款金额
    private Double midwayMaterialCarryCostAmount;//中期材料搬运及运输费金额
    private Double midwayContractSettleRate;//承包价结算比例
    private Double completeGuaranteeMoneyAmount;//竣工质保金金额
    private Double completeLongwayCommissionAmount;//竣工远程费金额
    private Double contractSettleAmount;//承包价结算金额
    private Double realSettleAmount;//实际结算金额
    private String status;//状态
    private Integer statusOperatorEmployeeId;//状态操作人员共Id
    private Date statusDatetime;//状态日期时间
    private String statusDescribe;//状态描述
    private Integer pmPreIndustrySettleSummaryBillId;

    private Date createSettleMonthStartDate;

    private Date createSettleMonthEndDate;

    private Date createSettleStartDate;

    private Date createSettleEndDate;

    private Date createMonthDate;

    private String isNewSettleBill; //0 老单子，1新单子

    
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
