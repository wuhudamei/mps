package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity;

public class unqualifiedItemsStatisticsEntity  extends DataEntity<unqualifiedItemsStatisticsEntity>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date startDate;
	private Date endDate;
	private String projectMode;
	public String getProjectMode() {
		return projectMode;
	}
	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
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
	private Integer orderId;
	private Integer storeId;
	private String orderNumber;
	private String xiaoqu;
	private String lou;
	private String shi;
	private String danyuan;
	private String customerName;
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getXiaoqu() {
		return xiaoqu;
	}
	public void setXiaoqu(String xiaoqu) {
		this.xiaoqu = xiaoqu;
	}
	public String getLou() {
		return lou;
	}
	public void setLou(String lou) {
		this.lou = lou;
	}
	public String getShi() {
		return shi;
	}
	public void setShi(String shi) {
		this.shi = shi;
	}
	public String getDanyuan() {
		return danyuan;
	}
	public void setDanyuan(String danyuan) {
		this.danyuan = danyuan;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getOrderInspectName() {
		return orderInspectName;
	}
	public void setOrderInspectName(String orderInspectName) {
		this.orderInspectName = orderInspectName;
	}
	public Integer getQualifiedItems() {
		return qualifiedItems;
	}
	public void setQualifiedItems(Integer qualifiedItems) {
		this.qualifiedItems = qualifiedItems;
	}
	public Integer getWarnCount() {
		return warnCount;
	}
	public void setWarnCount(Integer warnCount) {
		this.warnCount = warnCount;
	}
	public Integer getLocalChangeCount() {
		return localChangeCount;
	}
	public void setLocalChangeCount(Integer localChangeCount) {
		this.localChangeCount = localChangeCount;
	}
	public Integer getPunishMoneyCount() {
		return punishMoneyCount;
	}
	public void setPunishMoneyCount(Integer punishMoneyCount) {
		this.punishMoneyCount = punishMoneyCount;
	}
	public Double getPunishMoney() {
		return punishMoney;
	}
	public void setPunishMoney(Double punishMoney) {
		this.punishMoney = punishMoney;
	}
	public Integer getLimitDateChangeCount() {
		return limitDateChangeCount;
	}
	public void setLimitDateChangeCount(Integer limitDateChangeCount) {
		this.limitDateChangeCount = limitDateChangeCount;
	}
	public Integer getXianxiaCount() {
		return xianxiaCount;
	}
	public void setXianxiaCount(Integer xianxiaCount) {
		this.xianxiaCount = xianxiaCount;
	}
	public Integer getXianshangCount() {
		return xianshangCount;
	}
	public void setXianshangCount(Integer xianshangCount) {
		this.xianshangCount = xianshangCount;
	}
	private String orderInspectName;
	private Integer  qualifiedItems;
	private Integer warnCount;
	private Integer localChangeCount;

	public Integer getWorkerPunishMoneyCount() {
		return workerPunishMoneyCount;
	}

	public void setWorkerPunishMoneyCount(Integer workerPunishMoneyCount) {
		this.workerPunishMoneyCount = workerPunishMoneyCount;
	}

	public Double getWorkerPunishMoney() {
		return workerPunishMoney;
	}

	public void setWorkerPunishMoney(Double workerPunishMoney) {
		this.workerPunishMoney = workerPunishMoney;
	}

	public Integer getPqcPunishMoneyCount() {
		return pqcPunishMoneyCount;
	}

	public void setPqcPunishMoneyCount(Integer pqcPunishMoneyCount) {
		this.pqcPunishMoneyCount = pqcPunishMoneyCount;
	}

	public Double getPqcPunishMoney() {
		return pqcPunishMoney;
	}

	public void setPqcPunishMoney(Double pqcPunishMoney) {
		this.pqcPunishMoney = pqcPunishMoney;
	}

	private Integer punishMoneyCount;
	private Double punishMoney;
	private Integer workerPunishMoneyCount;
	private Double workerPunishMoney;
	private Integer pqcPunishMoneyCount;
	private Double pqcPunishMoney;
	private  Integer limitDateChangeCount;
	private Integer xianxiaCount;
	private Integer xianshangCount;
	
}
