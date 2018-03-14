package cn.damei.entity.modules;

import java.util.Date;
import java.util.List;

import cn.damei.common.persistence.DataEntity;

/**
 * @ClassName: BudgetDailyTable 
 * @Description: 预算日报表实体类
 * @author huhanwei 
 * @date 2017年7月18日 下午6:10:10
 */
public class BudgetDailyTable extends DataEntity<BudgetDailyTable> {
	
	private String storeId;// 门店
	private String storeName;// 门店名称
	private String enginDepartId;//区域编号
	private String enginDepartName;// 区域
	private String storeStayCreatePkgOrdCount;//门店当前待生成任务包订单数
	private String storeStayAuditPkgCount;// 门店当前待审核任务包
	private String currNotCreatePkgOrdCount;// 当前未生成任务包订单数量
	private String currNotAuditPkgCount;// 当前未审核任务包数量
	private String optDateCreatePkgOrdCount;// 选择日期已生成任务包订单数量
	private String optDateAuditPkgCount;// 选择日期已审核任务包数量
	private Date startDate;// 开始日期
	private Date endDate;// 结束日期
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
