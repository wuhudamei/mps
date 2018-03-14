/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import cn.damei.common.persistence.DataEntity;

/**
 * 检查项工人组和项目经理Entity
 * @author ztw
 * @version 2017-12-24
 */
public class QcItemGroupManger extends DataEntity<QcItemGroupManger> {
	
	private static final long serialVersionUID = 1L;
	private String qcBillId;		// 质检单id
	private String relatedQcBillCheckItemId;		// 关联质检单检查项id
	private String qcCheckItemId;		// 检查项id
	private String isPassed;		// 是否合格
	private String itemScore;		// 项目分数
	private String gotScore;		// 得分
	private String isWarned;		// 警告
	private String isLocaleRepaire;		// 是否现场整改 -- '0.否；1.是
	private String isLimitDateRepaire;		// 是否限期整改
	private Date limitDate;		// 限期整改期限

	private String limitDateRepaireCheckStyle;		// 限期整改检查方式 -- '0：线上检查；1：线下检查
	private String isPunishMoney;		// 是否罚款
	private String punishMoneyAmountDefault;		// 罚款默认金额 -- '
	private String punishMoneyAmountReal;		// 项目经理被罚款金额 -- '
	private String checkStyle;		// 检查方式
	private String workerPunishOrderTaskpackageId;		// 订单问题包ID
	private String workerPunishEmployeegroupId;		// 处罚工人ID
	private String workerPunishAmount;		// 工人组被罚金额
	private String workerPunishScore;		// worker_punish_score
	private String pmPunishEmployeeId;		// 处罚项目经理ID
	private String pmPunishScore;		// 责任项目经理被罚款分数
	private String qcPunishEmployeeId;		// qc_punish_employee_id
	private String qcPunishScore;		// qc_punish_score
	private String qcPunishAmount;		// 工人组被罚金额



	private Date beginCreateDate;		// 开始 创建日期时间
	private Date endCreateDate;		// 结束 创建日期时间


	private Date quCreateDate;		// 提交报告时间
	private String qcCheckKindName;	//检查分类
	private String qcCheckItemName;	//检查项
	private String breakDescribe;	//违规形式
	private String breakTimes;	//出现次数
	private String projectMode;		//工程模式   1-产业模式；2-传统模式(数字)
	private String projectModeName;		//工程模式   1-产业模式；2-传统模式(汉字)
	private String storeId;		//门店ID
	private String storeName;		//门店名称
	private String qcCheckItemBreakId;		//违规问题统计ID
	private String enginDepartId;		//区域ID
	private String enginDepartName;		//区域名称
	private String customerAddr;		//小区
	private String orderInspector;		//质检员
	private String itemManager;		//项目经理
	private String customerName;		//客户姓名
	private String workerGroupId;		//责任工人组长ID
	private String workerGroupName;		//工人组长姓名
	private String workerGroupPhone;		//工人组长手机
	private String workerGroupIllegalCount;		//工人组长违规次数
	private String workerGrouPunishCount;		//工人组长罚款次数
	private String projectManagerId;		// 责任项目经理id
	private String punishMoneyAmountRealSum; //某个项目经理被罚款金额总数
	private String mnagerPerson;		 //责任项目经理姓名
	private String mnagerPersonIllegalCount;		//责任项目经理违规次数
	private String mnagerPersonPunishCount;		//责任项目经理处罚次数
	private String mnagerPersonPhone;		// 责任项目经理手机号
	
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