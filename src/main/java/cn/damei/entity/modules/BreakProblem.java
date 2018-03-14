/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity;
import cn.damei.common.utils.DateUtils;

/**
 * 违规问题统计Entity
 * @author wyb
 * @version 2016-10-31
 */
public class BreakProblem extends DataEntity<BreakProblem> {
	
	private static final long serialVersionUID = 1L;
	
	private String storeId; //门店
	private Date quCreateDate; //质检提交报告时间
	private String quCreateDateString; //质检提交报告时间
	private String storeName; //门店名称
	private String projectModeName; //工程模式

	private Date checkDatetime;		// 质检日期时间 -- '
	private Date beginCheckDatetime;		// 开始 质检日期时间 -- '
	private Date endCheckDatetime;		// 结束 质检日期时间 -- '
	
	private String qcCheckKindName;	//检查分类
	private String qcCheckItemName;	//检查项
	private String breakDescribe;	//违规形式
	private String breakTimes;	//出现次数
	private String projectMode;		//工程模式   1-产业模式；2-传统模式
	private String qcCheckItemBreakId;		//违规问题统计ID
	private String enginDepartId;		//区域ID
	private String enginDepartName;		//区域名称
	private String customerAddr;		//小区
	private String orderInspector;		//质检员
	private String itemManager;		//项目经理
	private String customerName;		//客户姓名
	private String workerGroupName;		//工人组长姓名
	private String mnagerPerson;		//责任项目经理姓名

	
	public String getProjectMode() {
		return projectMode;
	}
	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}

	public String getEnginDepartName() {
		return enginDepartName;
	}

	public void setEnginDepartName(String enginDepartName) {
		this.enginDepartName = enginDepartName;
	}

	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public Date getCheckDatetime() {
		return checkDatetime;
	}
	public void setCheckDatetime(Date checkDatetime) {
		this.checkDatetime = checkDatetime;
	}
	public Date getBeginCheckDatetime() {
		return beginCheckDatetime;
	}
	public void setBeginCheckDatetime(Date beginCheckDatetime) {
		this.beginCheckDatetime = beginCheckDatetime;
	}
	public Date getEndCheckDatetime() {
		return endCheckDatetime;
	}
	public void setEndCheckDatetime(Date endCheckDatetime) {
		this.endCheckDatetime = endCheckDatetime;
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

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getProjectModeName() {
		return projectModeName;
	}

	public void setProjectModeName(String projectModeName) {
		this.projectModeName = projectModeName;
	}


	public Date getQuCreateDate() {
		return quCreateDate;
	}

	public void setQuCreateDate(Date quCreateDate) {

		this.quCreateDateString=DateUtils.formatDateTime(quCreateDate);
		this.quCreateDate = quCreateDate;
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

	public String getMnagerPerson() {
		return mnagerPerson;
	}

	public void setMnagerPerson(String mnagerPerson) {
		this.mnagerPerson = mnagerPerson;
	}

	public String getQuCreateDateString() {
		return quCreateDateString;
	}

	public void setQuCreateDateString(String quCreateDateString) {
		this.quCreateDateString = quCreateDateString;
	}
}