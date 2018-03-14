package cn.damei.entity.mobile.Inspector;

import java.util.Date;
import cn.damei.common.persistence.DataEntity2;

/**
 * 任务包结算Entity
 * @author qww
 * @version 2016-10-15
 */

public class PqcOrderTaskpackageSettlement extends DataEntity2<PqcOrderTaskpackageSettlement>{

	private static final long serialVersionUID = 1L;

	private String settlementNo; // 任务包结算编号
	private Integer orderTaskpackageId; // 订单任务包id
	private Date checkDate; // 验收日期
	private String isQualified; // 是否合格 no:不合格 yes:合格
	private String isDelay; // 是否延期  no:延期 yes:按时完成
	private Double delayDays; // 延期天数(延期天数可以为整天，可以为半天，即0.5天)
	private Double delayAmerce; // 延期扣除金额 -- '延期天数*100元
	private String isManagePunish; // 是否管理处罚 0:无 1:有
	private Double punishAmerce; // 处罚金额
	private String punishReason; // 处罚理由
	private Double auxiliaryMaterialsAmount; // 辅料核算金额 
	private Double guaranteeMoneyAmount; // 质保金金额
	private Double settlementAmount; // 结算金额
	private String status; // 状态
	private Date statusDate; // 状态产生时间
	private String refusedReason;
	private String remarks;
	private String isNeedRecheck; // 是否需要复合
	private Integer storeId; // 门店id
	private Date recheckDatetime; // 复核时间
	
	public PqcOrderTaskpackageSettlement() {
		super();
	}

	public PqcOrderTaskpackageSettlement(Integer id){
		super(id);
	}
	
	public String getSettlementNo() {
		return settlementNo;
	}
	public void setSettlementNo(String settlementNo) {
		this.settlementNo = settlementNo;
	}
	public Integer getOrderTaskpackageId() {
		return orderTaskpackageId;
	}
	public void setOrderTaskpackageId(Integer orderTaskpackageId) {
		this.orderTaskpackageId = orderTaskpackageId;
	}
	public Date getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	public String getIsQualified() {
		return isQualified;
	}
	public void setIsQualified(String isQualified) {
		this.isQualified = isQualified;
	}
	public String getIsDelay() {
		return isDelay;
	}
	public void setIsDelay(String isDelay) {
		this.isDelay = isDelay;
	}
	public Double getDelayDays() {
		return delayDays;
	}
	public void setDelayDays(Double delayDays) {
		this.delayDays = delayDays;
	}
	public Double getDelayAmerce() {
		return delayAmerce;
	}
	public void setDelayAmerce(Double delayAmerce) {
		this.delayAmerce = delayAmerce;
	}
	public String getIsManagePunish() {
		return isManagePunish;
	}
	public void setIsManagePunish(String isManagePunish) {
		this.isManagePunish = isManagePunish;
	}
	public Double getPunishAmerce() {
		return punishAmerce;
	}
	public void setPunishAmerce(Double punishAmerce) {
		this.punishAmerce = punishAmerce;
	}
	public String getPunishReason() {
		return punishReason;
	}
	public void setPunishReason(String punishReason) {
		this.punishReason = punishReason;
	}
	public Double getAuxiliaryMaterialsAmount() {
		return auxiliaryMaterialsAmount;
	}
	public void setAuxiliaryMaterialsAmount(Double auxiliaryMaterialsAmount) {
		this.auxiliaryMaterialsAmount = auxiliaryMaterialsAmount;
	}
	public Double getGuaranteeMoneyAmount() {
		return guaranteeMoneyAmount;
	}
	public void setGuaranteeMoneyAmount(Double guaranteeMoneyAmount) {
		this.guaranteeMoneyAmount = guaranteeMoneyAmount;
	}
	public Double getSettlementAmount() {
		return settlementAmount;
	}
	public void setSettlementAmount(Double settlementAmount) {
		this.settlementAmount = settlementAmount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getStatusDate() {
		return statusDate;
	}
	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}
	public String getRefusedReason() {
		return refusedReason;
	}
	public void setRefusedReason(String refusedReason) {
		this.refusedReason = refusedReason;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getIsNeedRecheck() {
		return isNeedRecheck;
	}
	public void setIsNeedRecheck(String isNeedRecheck) {
		this.isNeedRecheck = isNeedRecheck;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Date getRecheckDatetime() {
		return recheckDatetime;
	}

	public void setRecheckDatetime(Date recheckDatetime) {
		this.recheckDatetime = recheckDatetime;
	}
}
