
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import cn.damei.common.persistence.DataEntity;


public class QcItemGroupManger extends DataEntity<QcItemGroupManger> {
	
	private static final long serialVersionUID = 1L;
	private String qcBillId;
	private String relatedQcBillCheckItemId;
	private String qcCheckItemId;
	private String isPassed;
	private String itemScore;
	private String gotScore;
	private String isWarned;
	private String isLocaleRepaire;
	private String isLimitDateRepaire;
	private Date limitDate;

	private String limitDateRepaireCheckStyle;
	private String isPunishMoney;
	private String punishMoneyAmountDefault;
	private String punishMoneyAmountReal;
	private String checkStyle;
	private String workerPunishOrderTaskpackageId;
	private String workerPunishEmployeegroupId;
	private String workerPunishAmount;
	private String workerPunishScore;
	private String pmPunishEmployeeId;
	private String pmPunishScore;
	private String qcPunishEmployeeId;
	private String qcPunishScore;
	private String qcPunishAmount;



	private Date beginCreateDate;
	private Date endCreateDate;


	private Date quCreateDate;
	private String qcCheckKindName;
	private String qcCheckItemName;
	private String breakDescribe;
	private String breakTimes;
	private String projectMode;
	private String projectModeName;
	private String storeId;
	private String storeName;
	private String qcCheckItemBreakId;
	private String enginDepartId;
	private String enginDepartName;
	private String customerAddr;
	private String orderInspector;
	private String itemManager;
	private String customerName;
	private String workerGroupId;
	private String workerGroupName;
	private String workerGroupPhone;
	private String workerGroupIllegalCount;
	private String workerGrouPunishCount;
	private String projectManagerId;
	private String punishMoneyAmountRealSum;
	private String mnagerPerson;
	private String mnagerPersonIllegalCount;
	private String mnagerPersonPunishCount;
	private String mnagerPersonPhone;
	
	public QcItemGroupManger() {
		super();
	}

	public QcItemGroupManger(String id){
		super(id);
	}

	@Length(min=0, max=11, message="质检单id长度必须介于 0 和 11 之间")
	public String getQcBillId() {
		return qcBillId;
	}

	public void setQcBillId(String qcBillId) {
		this.qcBillId = qcBillId;
	}
	
	@Length(min=0, max=11, message="关联质检单检查项id长度必须介于 0 和 11 之间")
	public String getRelatedQcBillCheckItemId() {
		return relatedQcBillCheckItemId;
	}

	public void setRelatedQcBillCheckItemId(String relatedQcBillCheckItemId) {
		this.relatedQcBillCheckItemId = relatedQcBillCheckItemId;
	}
	
	@Length(min=0, max=11, message="检查项id长度必须介于 0 和 11 之间")
	public String getQcCheckItemId() {
		return qcCheckItemId;
	}

	public void setQcCheckItemId(String qcCheckItemId) {
		this.qcCheckItemId = qcCheckItemId;
	}
	
	@Length(min=0, max=1, message="是否合格长度必须介于 0 和 1 之间")
	public String getIsPassed() {
		return isPassed;
	}

	public void setIsPassed(String isPassed) {
		this.isPassed = isPassed;
	}
	
	public String getItemScore() {
		return itemScore;
	}

	public void setItemScore(String itemScore) {
		this.itemScore = itemScore;
	}
	
	public String getGotScore() {
		return gotScore;
	}

	public void setGotScore(String gotScore) {
		this.gotScore = gotScore;
	}
	
	@Length(min=0, max=1, message="警告长度必须介于 0 和 1 之间")
	public String getIsWarned() {
		return isWarned;
	}

	public void setIsWarned(String isWarned) {
		this.isWarned = isWarned;
	}
	
	public String getIsLocaleRepaire() {
		return isLocaleRepaire;
	}

	public void setIsLocaleRepaire(String isLocaleRepaire) {
		this.isLocaleRepaire = isLocaleRepaire;
	}
	
	@Length(min=0, max=1, message="是否限期整改长度必须介于 0 和 1 之间")
	public String getIsLimitDateRepaire() {
		return isLimitDateRepaire;
	}

	public void setIsLimitDateRepaire(String isLimitDateRepaire) {
		this.isLimitDateRepaire = isLimitDateRepaire;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLimitDate() {
		return limitDate;
	}

	public void setLimitDate(Date limitDate) {
		this.limitDate = limitDate;
	}
	
	@Length(min=0, max=1, message="限期整改检查方式 -- '0：线上检查；1：线下检查长度必须介于 0 和 1 之间")
	public String getLimitDateRepaireCheckStyle() {
		return limitDateRepaireCheckStyle;
	}

	public void setLimitDateRepaireCheckStyle(String limitDateRepaireCheckStyle) {
		this.limitDateRepaireCheckStyle = limitDateRepaireCheckStyle;
	}
	
	@Length(min=0, max=1, message="是否罚款长度必须介于 0 和 1 之间")
	public String getIsPunishMoney() {
		return isPunishMoney;
	}

	public void setIsPunishMoney(String isPunishMoney) {
		this.isPunishMoney = isPunishMoney;
	}
	
	public String getPunishMoneyAmountDefault() {
		return punishMoneyAmountDefault;
	}

	public void setPunishMoneyAmountDefault(String punishMoneyAmountDefault) {
		this.punishMoneyAmountDefault = punishMoneyAmountDefault;
	}

	public String getProjectModeName() {
		return projectModeName;
	}

	public void setProjectModeName(String projectModeName) {
		this.projectModeName = projectModeName;
	}

	public String getPunishMoneyAmountReal() {
		return punishMoneyAmountReal;
	}

	public void setPunishMoneyAmountReal(String punishMoneyAmountReal) {
		this.punishMoneyAmountReal = punishMoneyAmountReal;
	}
	
	@Length(min=0, max=1, message="检查方式长度必须介于 0 和 1 之间")
	public String getCheckStyle() {
		return checkStyle;
	}

	public void setCheckStyle(String checkStyle) {
		this.checkStyle = checkStyle;
	}
	
	@Length(min=0, max=11, message="订单问题包ID长度必须介于 0 和 11 之间")
	public String getWorkerPunishOrderTaskpackageId() {
		return workerPunishOrderTaskpackageId;
	}

	public void setWorkerPunishOrderTaskpackageId(String workerPunishOrderTaskpackageId) {
		this.workerPunishOrderTaskpackageId = workerPunishOrderTaskpackageId;
	}
	
	@Length(min=0, max=11, message="处罚工人ID长度必须介于 0 和 11 之间")
	public String getWorkerPunishEmployeegroupId() {
		return workerPunishEmployeegroupId;
	}

	public void setWorkerPunishEmployeegroupId(String workerPunishEmployeegroupId) {
		this.workerPunishEmployeegroupId = workerPunishEmployeegroupId;
	}
	
	public String getWorkerPunishAmount() {
		return workerPunishAmount;
	}

	public void setWorkerPunishAmount(String workerPunishAmount) {
		this.workerPunishAmount = workerPunishAmount;
	}
	
	@Length(min=0, max=11, message="worker_punish_score长度必须介于 0 和 11 之间")
	public String getWorkerPunishScore() {
		return workerPunishScore;
	}

	public void setWorkerPunishScore(String workerPunishScore) {
		this.workerPunishScore = workerPunishScore;
	}
	
	@Length(min=0, max=11, message="处罚项目经理ID长度必须介于 0 和 11 之间")
	public String getPmPunishEmployeeId() {
		return pmPunishEmployeeId;
	}

	public void setPmPunishEmployeeId(String pmPunishEmployeeId) {
		this.pmPunishEmployeeId = pmPunishEmployeeId;
	}
	
	@Length(min=0, max=11, message="pm_punish_score长度必须介于 0 和 11 之间")
	public String getPmPunishScore() {
		return pmPunishScore;
	}

	public void setPmPunishScore(String pmPunishScore) {
		this.pmPunishScore = pmPunishScore;
	}
	
	@Length(min=0, max=11, message="qc_punish_employee_id长度必须介于 0 和 11 之间")
	public String getQcPunishEmployeeId() {
		return qcPunishEmployeeId;
	}

	public void setQcPunishEmployeeId(String qcPunishEmployeeId) {
		this.qcPunishEmployeeId = qcPunishEmployeeId;
	}
	
	@Length(min=0, max=11, message="qc_punish_score长度必须介于 0 和 11 之间")
	public String getQcPunishScore() {
		return qcPunishScore;
	}

	public void setQcPunishScore(String qcPunishScore) {
		this.qcPunishScore = qcPunishScore;
	}
	
	public String getQcPunishAmount() {
		return qcPunishAmount;
	}

	public void setQcPunishAmount(String qcPunishAmount) {
		this.qcPunishAmount = qcPunishAmount;
	}
	
	@Length(min=0, max=11, message="责任项目经理id长度必须介于 0 和 11 之间")
	public String getProjectManagerId() {
		return projectManagerId;
	}

	public void setProjectManagerId(String projectManagerId) {
		this.projectManagerId = projectManagerId;
	}
	
	@Length(min=0, max=11, message="责任工人组id长度必须介于 0 和 11 之间")
	public String getWorkerGroupId() {
		return workerGroupId;
	}

	public void setWorkerGroupId(String workerGroupId) {
		this.workerGroupId = workerGroupId;
	}
	
	public Date getBeginCreateDate() {
		return beginCreateDate;
	}

	public void setBeginCreateDate(Date beginCreateDate) {
		this.beginCreateDate = beginCreateDate;
	}
	
	public Date getEndCreateDate() {
		return endCreateDate;
	}

	public void setEndCreateDate(Date endCreateDate) {
		this.endCreateDate = endCreateDate;
	}


	public Date getQuCreateDate() {
		return quCreateDate;
	}

	public void setQuCreateDate(Date quCreateDate) {
		this.quCreateDate = quCreateDate;
	}

	public String getQcCheckKindName() {
		return qcCheckKindName;
	}

	public void setQcCheckKindName(String qcCheckKindName) {
		this.qcCheckKindName = qcCheckKindName;
	}

	public String getQcCheckItemName() {
		return qcCheckItemName;
	}

	public void setQcCheckItemName(String qcCheckItemName) {
		this.qcCheckItemName = qcCheckItemName;
	}

	public String getBreakDescribe() {
		return breakDescribe;
	}

	public void setBreakDescribe(String breakDescribe) {
		this.breakDescribe = breakDescribe;
	}

	public String getBreakTimes() {
		return breakTimes;
	}

	public void setBreakTimes(String breakTimes) {
		this.breakTimes = breakTimes;
	}

	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}

	public String getQcCheckItemBreakId() {
		return qcCheckItemBreakId;
	}

	public void setQcCheckItemBreakId(String qcCheckItemBreakId) {
		this.qcCheckItemBreakId = qcCheckItemBreakId;
	}

	public String getEnginDepartId() {
		return enginDepartId;
	}

	public void setEnginDepartId(String enginDepartId) {
		this.enginDepartId = enginDepartId;
	}

	public String getEnginDepartName() {
		return enginDepartName;
	}

	public void setEnginDepartName(String enginDepartName) {
		this.enginDepartName = enginDepartName;
	}

	public String getCustomerAddr() {
		return customerAddr;
	}

	public void setCustomerAddr(String customerAddr) {
		this.customerAddr = customerAddr;
	}

	public String getOrderInspector() {
		return orderInspector;
	}

	public void setOrderInspector(String orderInspector) {
		this.orderInspector = orderInspector;
	}

	public String getItemManager() {
		return itemManager;
	}

	public void setItemManager(String itemManager) {
		this.itemManager = itemManager;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getWorkerGroupName() {
		return workerGroupName;
	}

	public void setWorkerGroupName(String workerGroupName) {
		this.workerGroupName = workerGroupName;
	}

	public String getWorkerGroupPhone() {
		return workerGroupPhone;
	}

	public void setWorkerGroupPhone(String workerGroupPhone) {
		this.workerGroupPhone = workerGroupPhone;
	}

	public String getWorkerGroupIllegalCount() {
		return workerGroupIllegalCount;
	}

	public void setWorkerGroupIllegalCount(String workerGroupIllegalCount) {
		this.workerGroupIllegalCount = workerGroupIllegalCount;
	}

	public String getWorkerGrouPunishCount() {
		return workerGrouPunishCount;
	}

	public void setWorkerGrouPunishCount(String workerGrouPunishCount) {
		this.workerGrouPunishCount = workerGrouPunishCount;
	}

	public String getPunishMoneyAmountRealSum() {
		return punishMoneyAmountRealSum;
	}

	public void setPunishMoneyAmountRealSum(String punishMoneyAmountRealSum) {
		this.punishMoneyAmountRealSum = punishMoneyAmountRealSum;
	}

	public String getMnagerPerson() {
		return mnagerPerson;
	}

	public void setMnagerPerson(String mnagerPerson) {
		this.mnagerPerson = mnagerPerson;
	}

	public String getMnagerPersonIllegalCount() {
		return mnagerPersonIllegalCount;
	}

	public void setMnagerPersonIllegalCount(String mnagerPersonIllegalCount) {
		this.mnagerPersonIllegalCount = mnagerPersonIllegalCount;
	}

	public String getMnagerPersonPunishCount() {
		return mnagerPersonPunishCount;
	}

	public void setMnagerPersonPunishCount(String mnagerPersonPunishCount) {
		this.mnagerPersonPunishCount = mnagerPersonPunishCount;
	}

	public String getMnagerPersonPhone() {
		return mnagerPersonPhone;
	}

	public void setMnagerPersonPhone(String mnagerPersonPhone) {
		this.mnagerPersonPhone = mnagerPersonPhone;
	}


	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
}