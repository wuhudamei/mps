
package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity;


public class BizQcBillCheckItem extends DataEntity<BizQcBillCheckItem> {
	
	private static final long serialVersionUID = 1L;
	private String qcBillId;
	private String relatedQcBillCheckItemId;
	private String qcCheckItemId;
	private String itemScore;
	private String gotScore;
	private String isLimitDateRepaire;
	private Date limitDate;
	private String limitDateRepaireCheckStyle;
	private String isPunishMoney;
	private String punishMoneyAmountDefault;
	private String punishMoneyAmountReal;
	private String checkStyle;
	private String workerPunishOrderTaskpackageId;
	private String workerPunishEmployeegroupId;
	private String workGroupPerson;
	private String workerPunishAmount;
	private String workerPunishScore;
	private String pmPunishEmployeeId;
	private String itemManager;
	private String mnagerPerson;
	private String pmPunishScore;
	private String qcPunishEmployeeId;
	private String qcPunishScore;
	private String qcPunishAmount;
	private String projectManagerId;
	private String workerGroupId;
	private String workerGroupName;
	private Date quCreateDate;
	private String qcBillCode;
	private String storeId;
	private String projectMode;
	private String enginDepartId;
	private String customerName;
	private String qcCheckKindName;
	private String qcCheckItemName;
	private String breakDescribe;
	private String isPassed;
	private String isWarned;
	private String isLocaleRepaire;
	private String orderInspector;
	private String orderNumber;
	private String employeeId;
	private String employeeName;
	private String groupId;

	public BizQcBillCheckItem() {
		super();
	}

	public BizQcBillCheckItem(String id){
		super(id);
	}


	public String getQcBillId() {
		return qcBillId;
	}

	public void setQcBillId(String qcBillId) {
		this.qcBillId = qcBillId;
	}

	public String getRelatedQcBillCheckItemId() {
		return relatedQcBillCheckItemId;
	}

	public void setRelatedQcBillCheckItemId(String relatedQcBillCheckItemId) {
		this.relatedQcBillCheckItemId = relatedQcBillCheckItemId;
	}

	public String getQcCheckItemId() {
		return qcCheckItemId;
	}

	public void setQcCheckItemId(String qcCheckItemId) {
		this.qcCheckItemId = qcCheckItemId;
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

	public String getWorkerGroupName() {
		return workerGroupName;
	}

	public void setWorkerGroupName(String workerGroupName) {
		this.workerGroupName = workerGroupName;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getIsLimitDateRepaire() {
		return isLimitDateRepaire;
	}

	public void setIsLimitDateRepaire(String isLimitDateRepaire) {
		this.isLimitDateRepaire = isLimitDateRepaire;
	}

	public Date getLimitDate() {
		return limitDate;
	}

	public void setLimitDate(Date limitDate) {
		this.limitDate = limitDate;
	}

	public String getLimitDateRepaireCheckStyle() {
		return limitDateRepaireCheckStyle;
	}

	public void setLimitDateRepaireCheckStyle(String limitDateRepaireCheckStyle) {
		this.limitDateRepaireCheckStyle = limitDateRepaireCheckStyle;
	}

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

	public String getPunishMoneyAmountReal() {
		return punishMoneyAmountReal;
	}

	public void setPunishMoneyAmountReal(String punishMoneyAmountReal) {
		this.punishMoneyAmountReal = punishMoneyAmountReal;
	}

	public String getCheckStyle() {
		return checkStyle;
	}

	public void setCheckStyle(String checkStyle) {
		this.checkStyle = checkStyle;
	}

	public String getWorkerPunishOrderTaskpackageId() {
		return workerPunishOrderTaskpackageId;
	}

	public void setWorkerPunishOrderTaskpackageId(String workerPunishOrderTaskpackageId) {
		this.workerPunishOrderTaskpackageId = workerPunishOrderTaskpackageId;
	}

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

	public String getWorkerPunishScore() {
		return workerPunishScore;
	}

	public void setWorkerPunishScore(String workerPunishScore) {
		this.workerPunishScore = workerPunishScore;
	}

	public String getPmPunishEmployeeId() {
		return pmPunishEmployeeId;
	}

	public void setPmPunishEmployeeId(String pmPunishEmployeeId) {
		this.pmPunishEmployeeId = pmPunishEmployeeId;
	}

	public String getItemManager() {
		return itemManager;
	}

	public void setItemManager(String itemManager) {
		itemManager=itemManager.replaceAll(",","");
		this.itemManager = itemManager;
	}

	public String getPmPunishScore() {
		return pmPunishScore;
	}

	public void setPmPunishScore(String pmPunishScore) {
		this.pmPunishScore = pmPunishScore;
	}

	public String getQcPunishEmployeeId() {
		return qcPunishEmployeeId;
	}

	public void setQcPunishEmployeeId(String qcPunishEmployeeId) {
		this.qcPunishEmployeeId = qcPunishEmployeeId;
	}

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

	public String getProjectManagerId() {
		return projectManagerId;
	}

	public void setProjectManagerId(String projectManagerId) {
		this.projectManagerId = projectManagerId;
	}

	public String getWorkerGroupId() {
		return workerGroupId;
	}

	public void setWorkerGroupId(String workerGroupId) {
		this.workerGroupId = workerGroupId;
	}

	public Date getQuCreateDate() {
		return quCreateDate;
	}

	public void setQuCreateDate(Date quCreateDate) {
		this.quCreateDate = quCreateDate;
	}

	public String getQcBillCode() {
		return qcBillCode;
	}

	public void setQcBillCode(String qcBillCode) {
		this.qcBillCode = qcBillCode;
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

	public String getEnginDepartId() {
		return enginDepartId;
	}

	public void setEnginDepartId(String enginDepartId) {
		this.enginDepartId = enginDepartId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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

	public String getIsPassed() {
		return isPassed;
	}

	public void setIsPassed(String isPassed) {
		this.isPassed = isPassed;
	}

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

	public String getOrderInspector() {
		return orderInspector;
	}

	public void setOrderInspector(String orderInspector) {
		this.orderInspector = orderInspector;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getMnagerPerson() {
		return mnagerPerson;
	}
	public void setMnagerPerson(String mnagerPerson) {
		this.mnagerPerson = mnagerPerson;
	}

	public String getWorkGroupPerson() {
		return workGroupPerson;
	}

	public void setWorkGroupPerson(String workGroupPerson) {
		this.workGroupPerson = workGroupPerson;
	}
}