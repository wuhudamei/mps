
package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity;
import cn.damei.common.utils.DateUtils;


public class BreakProblem extends DataEntity<BreakProblem> {
	
	private static final long serialVersionUID = 1L;
	
	private String storeId;
	private Date quCreateDate;
	private String quCreateDateString;
	private String storeName;
	private String projectModeName;

	private Date checkDatetime;
	private Date beginCheckDatetime;
	private Date endCheckDatetime;
	
	private String qcCheckKindName;
	private String qcCheckItemName;
	private String breakDescribe;
	private String breakTimes;
	private String projectMode;
	private String qcCheckItemBreakId;
	private String enginDepartId;
	private String enginDepartName;
	private String customerAddr;
	private String orderInspector;
	private String itemManager;
	private String customerName;
	private String workerGroupName;
	private String mnagerPerson;

	
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