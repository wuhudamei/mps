package cn.damei.entity.mobile.Inspector;

import java.util.Date;
import cn.damei.common.persistence.DataEntity2;



public class PqcOrderTaskpackageSettlement extends DataEntity2<PqcOrderTaskpackageSettlement>{

	private static final long serialVersionUID = 1L;

	private String settlementNo;
	private Integer orderTaskpackageId;
	private Date checkDate;
	private String isQualified;
	private String isDelay;
	private Double delayDays;
	private Double delayAmerce;
	private String isManagePunish;
	private Double punishAmerce;
	private String punishReason;
	private Double auxiliaryMaterialsAmount;
	private Double guaranteeMoneyAmount;
	private Double settlementAmount;
	private String status;
	private Date statusDate;
	private String refusedReason;
	private String remarks;
	private String isNeedRecheck;
	private Integer storeId;
	private Date recheckDatetime;
	
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
