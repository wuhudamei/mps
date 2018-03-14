/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 结算单Entity
 * @author qww
 * @version 2016-12-26
 */
public class BizPmSettleBill extends DataEntity2<BizPmSettleBill> {
	
	private static final long serialVersionUID = 1L;
	private String bizPmSettleBillCode;		// 项目经理结算单编号 -- '
	private Integer orderId;		// 订单id -- '
	private Integer pmEmployeeId;		// 项目经理员工id -- '
	private String projectMode;		// 工程模式 -- '
	private String settleMonth; // 月度
	private Date settleDatetime;		// 结算日期时间 -- '
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

	private Double midwayInspectionRewardAmount;//中期巡检奖励金额
	private Double midwayInspectionPunishAmount;//中期巡检罚款金额
	private Double completeInspectionRewardAmount;//竣工巡检奖励金额
	private Double completeInspectionPunishAmount;//竣工巡检罚款金额

	private Double guaranteeMoneyAmount;		// 质保金金额 -- '
	private Double totalAmount;		// 合计金额 -- '
	private String status; // 状态 30,50
	private String settleBillType; // 结算单类型 -- '1.中期结算单；2.竣工结算单
	private String settleRole; // 结算角色 1-项目经理 2-质检员
	private Double qcMidwayCommissionAmount;
	private Double qcCompleteCommissionAmount;
	private Double qcMidwayLongwayAmount;
	private Double qcCompleteLongwayAmount;
	private Integer pmSettleSummaryBillId;

	private Integer storeId; // 门店
	private String customerName; // 客户
	private String itemManager; // 项目经理
	private String itemManagerPhone;//项目经理手机号
	private String orderInspector; // 质检员
	private String orderNumber; // 订单编号
	private String newStatus;
	private Date beginSettleDatetime;
	private Date endSettleDatetime;
	private String customerPhone; // 客户电话
	private String customerMessage; // 客户信息
	private String settleBillId;
	private List<Integer> enginDepartIds = new ArrayList<Integer>();
	private Integer enginDepartId; // 区域
	private String enginDepartName; // 区域名称

	private List<String> list = new ArrayList<String>();

	public BizPmSettleBill() {
		super();
	}

	public BizPmSettleBill(Integer id){
		super(id);
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
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

	public String getItemManagerPhone() {
		return itemManagerPhone;
	}

	public void setItemManagerPhone(String itemManagerPhone) {
		this.itemManagerPhone = itemManagerPhone;
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

	@Length(min=0, max=100, message="项目经理结算单编号 -- '长度必须介于 0 和 100 之间")
	public String getBizPmSettleBillCode() {
		return bizPmSettleBillCode;
	}

	public void setBizPmSettleBillCode(String bizPmSettleBillCode) {
		this.bizPmSettleBillCode = bizPmSettleBillCode;
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
	
	@Length(min=0, max=10, message="工程模式 -- '长度必须介于 0 和 10 之间")
	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}

	public String getSettleMonth() {
		return settleMonth;
	}

	public void setSettleMonth(String settleMonth) {
		this.settleMonth = settleMonth;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSettleDatetime() {
		return settleDatetime;
	}

	public void setSettleDatetime(Date settleDatetime) {
		this.settleDatetime = settleDatetime;
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

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getItemManager() {
		return itemManager;
	}

	public void setItemManager(String itemManager) {
		this.itemManager = itemManager;
	}

	public String getOrderInspector() {
		return orderInspector;
	}

	public void setOrderInspector(String orderInspector) {
		this.orderInspector = orderInspector;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSettleBillType() {
		return settleBillType;
	}

	public void setSettleBillType(String settleBillType) {
		this.settleBillType = settleBillType;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getNewStatus() {
		return newStatus;
	}

	public void setNewStatus(String newStatus) {
		this.newStatus = newStatus;
	}

	public Date getBeginSettleDatetime() {
		return beginSettleDatetime;
	}

	public void setBeginSettleDatetime(Date beginSettleDatetime) {
		this.beginSettleDatetime = beginSettleDatetime;
	}

	public Date getEndSettleDatetime() {
		return endSettleDatetime;
	}

	public void setEndSettleDatetime(Date endSettleDatetime) {
		this.endSettleDatetime = endSettleDatetime;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getCustomerMessage() {
		return customerMessage;
	}

	public void setCustomerMessage(String customerMessage) {
		this.customerMessage = customerMessage;
	}

	public String getSettleBillId() {
		return settleBillId;
	}

	public void setSettleBillId(String settleBillId) {
		this.settleBillId = settleBillId;
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

	public Integer getPmSettleSummaryBillId() {
		return pmSettleSummaryBillId;
	}

	public void setPmSettleSummaryBillId(Integer pmSettleSummaryBillId) {
		this.pmSettleSummaryBillId = pmSettleSummaryBillId;
	}

	public Integer getEnginDepartId() {
		return enginDepartId;
	}

	public void setEnginDepartId(Integer enginDepartId) {
		this.enginDepartId = enginDepartId;
	}

	public String getEnginDepartName() {
		return enginDepartName;
	}

	public void setEnginDepartName(String enginDepartName) {
		this.enginDepartName = enginDepartName;
	}

	public List<Integer> getEnginDepartIds() {
		return enginDepartIds;
	}

	public void setEnginDepartIds(List<Integer> enginDepartIds) {
		this.enginDepartIds = enginDepartIds;
	}
}