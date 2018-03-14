/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity;

/**
 * 质检检查项查询Entity
 * @author ztw
 * @version 2017-12-21
 */
public class BizQcBillCheckItem extends DataEntity<BizQcBillCheckItem> {
	
	private static final long serialVersionUID = 1L;
	private String qcBillId;		// 质检单id
	private String relatedQcBillCheckItemId;		// 关联质检单检查项id
	private String qcCheckItemId;		// 检查项id
	private String itemScore;		// 项目分数 -- '
	private String gotScore;		// 得分 -- '
	private String isLimitDateRepaire;		// 是否限期整改 -- '0.否；1.是
	private Date limitDate;		// 限期整改期限 -- '
	private String limitDateRepaireCheckStyle;		// 限期整改检查方式 -- '0：线上检查；1：线下检查
	private String isPunishMoney;		// 是否罚款 -- '0.否；1.是
	private String punishMoneyAmountDefault;		// 罚款默认金额 -- '
	private String punishMoneyAmountReal;		//  项目经理被罚金额
	private String checkStyle;		// 检查方式 -- '0：线上；1：线下；默认为1线下
	private String workerPunishOrderTaskpackageId;		// 订单问题包ID
	private String workerPunishEmployeegroupId;		// 被罚工人组ID
	private String workGroupPerson;		// 被罚工人组名称
	private String workerPunishAmount;		// 工人组被罚金额
	private String workerPunishScore;		// worker_punish_score
	private String pmPunishEmployeeId;		// 处罚项目经理ID
	private String itemManager;		// 被罚项目经理名称
	private String mnagerPerson;		// 责任项目经理名称
	private String pmPunishScore;		// 项目经理被罚分数
	private String qcPunishEmployeeId;		// qc_punish_employee_id
	private String qcPunishScore;		// 工人组长被罚分数
	private String qcPunishAmount;		// qc_punish_amount
	private String projectManagerId;		// 责任项目经理id
	private String workerGroupId;		// 责任工人组id
	private String workerGroupName;		// 责任工人组名称
	private Date quCreateDate;		// 质检员提交报告时间
	private String qcBillCode;		// 质检单编号
	private String storeId;		// 门店
	private String projectMode;		// 工程模式
	private String enginDepartId;		// 区域
	private String customerName;		// 客户姓名
	private String qcCheckKindName;		// 检查项分类名称
	private String qcCheckItemName;		// 检查项名称
	private String breakDescribe;		// 违规形式
	private String isPassed;		// 是否合格 0.不合格；1.合格
	private String isWarned;		// 是否警告 0不警告1警告
	private String isLocaleRepaire; //是否现场整改 , -- 0.否；1.是
	private String orderInspector; //质检名称
	private String orderNumber; //订单编号
	private String employeeId; //员工ID
	private String employeeName; //员工姓名
	private String groupId; // 工人组id

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