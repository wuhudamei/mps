
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import cn.damei.common.persistence.DataEntity2;


public class BizOrderTaskpackageSettlement extends DataEntity2<BizOrderTaskpackageSettlement> {
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer storeid;
	private String settlementNo;
	private Integer orderTaskpackageId;
	private Date checkDate;
	private String isQualified;
	private String isDelay;
	private Integer delayDays;
	private Double delayAmerce;
	private String isManagePunish;
	private Double punishAmerce;
	private String punishReason;
	private Double auxiliaryMaterialsAmount;
	private Double guaranteeMoneyAmount;
	private Double settlementAmount;
	private String status;
	private Date beginCheckDate;
	private Date endCheckDate;
	private Double qcPunishMoneyAmount;
	private Double rewardAmount;
	private Double sandCementAmount;
	private Double companyDeductAmount;
	private String companyDeductReason;
	private Double laborAuxiliaryMaterialsSettleAmount;
	private Double laborSettleAmount;
	private Double auxiliaryMaterialsSettleAmount;
	private Double pmMaterialsSettleAmount;
	private Double workerGroupSettleAmount;
	private String settleStyle;
	
	public Double getLaborAuxiliaryMaterialsSettleAmount() {
		return laborAuxiliaryMaterialsSettleAmount;
	}

	public void setLaborAuxiliaryMaterialsSettleAmount(Double laborAuxiliaryMaterialsSettleAmount) {
		this.laborAuxiliaryMaterialsSettleAmount = laborAuxiliaryMaterialsSettleAmount;
	}

	public Double getLaborSettleAmount() {
		return laborSettleAmount;
	}

	public void setLaborSettleAmount(Double laborSettleAmount) {
		this.laborSettleAmount = laborSettleAmount;
	}

	public Double getAuxiliaryMaterialsSettleAmount() {
		return auxiliaryMaterialsSettleAmount;
	}

	public void setAuxiliaryMaterialsSettleAmount(Double auxiliaryMaterialsSettleAmount) {
		this.auxiliaryMaterialsSettleAmount = auxiliaryMaterialsSettleAmount;
	}

	public Double getPmMaterialsSettleAmount() {
		return pmMaterialsSettleAmount;
	}

	public void setPmMaterialsSettleAmount(Double pmMaterialsSettleAmount) {
		this.pmMaterialsSettleAmount = pmMaterialsSettleAmount;
	}

	public Double getWorkerGroupSettleAmount() {
		return workerGroupSettleAmount;
	}

	public void setWorkerGroupSettleAmount(Double workerGroupSettleAmount) {
		this.workerGroupSettleAmount = workerGroupSettleAmount;
	}

	public String getSettleStyle() {
		return settleStyle;
	}

	public void setSettleStyle(String settleStyle) {
		this.settleStyle = settleStyle;
	}

	public BizOrderTaskpackageSettlement() {
		super();
	}

	public BizOrderTaskpackageSettlement(Integer id){
		super(id);
	}
	
	public Double getCompanyDeductAmount() {
		return companyDeductAmount;
	}

	public void setCompanyDeductAmount(Double companyDeductAmount) {
		this.companyDeductAmount = companyDeductAmount;
	}

	public String getCompanyDeductReason() {
		return companyDeductReason;
	}

	public void setCompanyDeductReason(String companyDeductReason) {
		this.companyDeductReason = companyDeductReason;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Length(min=0, max=64, message="settlement_no长度必须介于 0 和 64 之间")
	public String getSettlementNo() {
		return settlementNo;
	}

	public void setSettlementNo(String settlementNo) {
		this.settlementNo = settlementNo;
	}
	
	public Integer getStoreid() {
		return storeid;
	}

	public void setStoreid(Integer storeid) {
		this.storeid = storeid;
	}

	public Integer getOrderTaskpackageId() {
		return orderTaskpackageId;
	}

	public void setOrderTaskpackageId(Integer orderTaskpackageId) {
		this.orderTaskpackageId = orderTaskpackageId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	
	@Length(min=0, max=10, message="是否合格 -- '长度必须介于 0 和 10 之间")
	public String getIsQualified() {
		return isQualified;
	}

	public void setIsQualified(String isQualified) {
		this.isQualified = isQualified;
	}
	
	@Length(min=0, max=10, message="是否延期 -- '长度必须介于 0 和 10 之间")
	public String getIsDelay() {
		return isDelay;
	}

	public void setIsDelay(String isDelay) {
		this.isDelay = isDelay;
	}
	
	public Integer getDelayDays() {
		return delayDays;
	}

	public void setDelayDays(Integer delayDays) {
		this.delayDays = delayDays;
	}
	
	public Double getDelayAmerce() {
		return delayAmerce;
	}

	public void setDelayAmerce(Double delayAmerce) {
		this.delayAmerce = delayAmerce;
	}
	
	@Length(min=0, max=10, message="是否管理处罚 -- '长度必须介于 0 和 10 之间")
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
	
	@Length(min=0, max=255, message="处罚理由 -- '长度必须介于 0 和 255 之间")
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
	
	@Length(min=0, max=10, message="状态 -- '长度必须介于 0 和 10 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public Date getBeginCheckDate() {
		return beginCheckDate;
	}

	public void setBeginCheckDate(Date beginCheckDate) {
		this.beginCheckDate = beginCheckDate;
	}
	
	public Date getEndCheckDate() {
		return endCheckDate;
	}

	public void setEndCheckDate(Date endCheckDate) {
		this.endCheckDate = endCheckDate;
	}

	public Double getQcPunishMoneyAmount() {
		return qcPunishMoneyAmount;
	}

	public void setQcPunishMoneyAmount(Double qcPunishMoneyAmount) {
		this.qcPunishMoneyAmount = qcPunishMoneyAmount;
	}

	public Double getRewardAmount() {
		return rewardAmount;
	}

	public void setRewardAmount(Double rewardAmount) {
		this.rewardAmount = rewardAmount;
	}

	public Double getSandCementAmount() {
		return sandCementAmount;
	}

	public void setSandCementAmount(Double sandCementAmount) {
		this.sandCementAmount = sandCementAmount;
	}
	
	
}