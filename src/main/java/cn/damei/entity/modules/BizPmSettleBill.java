
package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;


public class BizPmSettleBill extends DataEntity2<BizPmSettleBill> {
	
	private static final long serialVersionUID = 1L;
	private String bizPmSettleBillCode;
	private Integer orderId;
	private Integer pmEmployeeId;
	private String projectMode;
	private String settleMonth;
	private Date settleDatetime;
	private Double midwayCommissionAmount;
	private Double completeCommissionAmount;
	private Double ownpayAmount;
	private Double materialsStandardAmount;
	private Double midwayQcCheckPunishAmount;
	private Double completQcCheckPunishAmount;
	private Double materialSelfbuyReimburseAmount;
	private Double midwayAuxiliaryMaterialsSettleAmount;
	private Double completeAuxiliaryMaterialsSettleAmount;
	private Double midwayRewardAmount;
	private Double midwayPunishAmount;
	private Double completeRewardAmount;
	private Double completePunishAmount;

	private Double midwayInspectionRewardAmount;
	private Double midwayInspectionPunishAmount;
	private Double completeInspectionRewardAmount;
	private Double completeInspectionPunishAmount;

	private Double guaranteeMoneyAmount;
	private Double totalAmount;
	private String status;
	private String settleBillType;
	private String settleRole;
	private Double qcMidwayCommissionAmount;
	private Double qcCompleteCommissionAmount;
	private Double qcMidwayLongwayAmount;
	private Double qcCompleteLongwayAmount;
	private Integer pmSettleSummaryBillId;

	private Integer storeId;
	private String customerName;
	private String itemManager;
	private String itemManagerPhone;
	private String orderInspector;
	private String orderNumber;
	private String newStatus;
	private Date beginSettleDatetime;
	private Date endSettleDatetime;
	private String customerPhone;
	private String customerMessage;
	private String settleBillId;
	private List<Integer> enginDepartIds = new ArrayList<Integer>();
	private Integer enginDepartId;
	private String enginDepartName;

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