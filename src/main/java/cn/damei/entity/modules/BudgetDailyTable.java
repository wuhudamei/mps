package cn.damei.entity.modules;

import java.util.Date;
import java.util.List;

import cn.damei.common.persistence.DataEntity;


public class BudgetDailyTable extends DataEntity<BudgetDailyTable> {
	
	private String storeId;
	private String storeName;
	private String enginDepartId;
	private String enginDepartName;
	private String storeStayCreatePkgOrdCount;
	private String storeStayAuditPkgCount;
	private String currNotCreatePkgOrdCount;
	private String currNotAuditPkgCount;
	private String optDateCreatePkgOrdCount;
	private String optDateAuditPkgCount;
	private Date startDate;
	private Date endDate;
	private List<Integer> ids;
	
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
	public String getEnginDepartName() {
		return enginDepartName;
	}
	public void setEnginDepartName(String enginDepartName) {
		this.enginDepartName = enginDepartName;
	}
	public String getStoreStayAuditPkgCount() {
		return storeStayAuditPkgCount;
	}
	public void setStoreStayAuditPkgCount(String storeStayAuditPkgCount) {
		this.storeStayAuditPkgCount = storeStayAuditPkgCount;
	}
	public String getCurrNotCreatePkgOrdCount() {
		return currNotCreatePkgOrdCount;
	}
	public void setCurrNotCreatePkgOrdCount(String currNotCreatePkgOrdCount) {
		this.currNotCreatePkgOrdCount = currNotCreatePkgOrdCount;
	}
	public String getCurrNotAuditPkgCount() {
		return currNotAuditPkgCount;
	}
	public void setCurrNotAuditPkgCount(String currNotAuditPkgCount) {
		this.currNotAuditPkgCount = currNotAuditPkgCount;
	}
	public String getOptDateCreatePkgOrdCount() {
		return optDateCreatePkgOrdCount;
	}
	public void setOptDateCreatePkgOrdCount(String optDateCreatePkgOrdCount) {
		this.optDateCreatePkgOrdCount = optDateCreatePkgOrdCount;
	}
	public String getOptDateAuditPkgCount() {
		return optDateAuditPkgCount;
	}
	public void setOptDateAuditPkgCount(String optDateAuditPkgCount) {
		this.optDateAuditPkgCount = optDateAuditPkgCount;
	}
	public String getEnginDepartId() {
		return enginDepartId;
	}
	public void setEnginDepartId(String enginDepartId) {
		this.enginDepartId = enginDepartId;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public List<Integer> getIds() {
		return ids;
	}
	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}
	public String getStoreStayCreatePkgOrdCount() {
		return storeStayCreatePkgOrdCount;
	}
	public void setStoreStayCreatePkgOrdCount(String storeStayCreatePkgOrdCount) {
		this.storeStayCreatePkgOrdCount = storeStayCreatePkgOrdCount;
	}
}
