package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity;


public class ToDoItemEntity extends  DataEntity<ToDoItemEntity>{

	private static final long serialVersionUID = 1L;
	private Integer toDoItemTypeId;
	private String relatedBusinessType;
	private Integer relatedBusinessId;
	private Integer orderId;
	private Integer toDoItemEmployeeId;
	private String toDoItemRemindTitle;
	private String toDoItemDealUrl;
	private Date toDoItemGeneratedDatetime;
	private Date toDoItemRemindDatetime;
	private Date toDoItemViewDatetime;
	private Date toDoItemDealDatetime;
	private String isViewd;
	private String isSolved;
	private String xiaoqu;
	private String lou;
	private String danyuan;
	private String shi;
	private String customerName;
	private String customerPhone;
	private Integer managerId;
	private String managerName;
	private Integer storeId;
	private String orderNumber;
	private String projectMode;
	private String engineDepartName;
	private Integer engineDepartId;
	
	
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getProjectMode() {
		return projectMode;
	}
	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}
	public String getEngineDepartName() {
		return engineDepartName;
	}
	public void setEngineDepartName(String engineDepartName) {
		this.engineDepartName = engineDepartName;
	}
	public Integer getEngineDepartId() {
		return engineDepartId;
	}
	public void setEngineDepartId(Integer engineDepartId) {
		this.engineDepartId = engineDepartId;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public Date getToDoItemDealDatetime() {
		return toDoItemDealDatetime;
	}
	public void setToDoItemDealDatetime(Date toDoItemDealDatetime) {
		this.toDoItemDealDatetime = toDoItemDealDatetime;
	}
	public Integer getManagerId() {
		return managerId;
	}
	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public Integer getToDoItemTypeId() {
		return toDoItemTypeId;
	}
	public void setToDoItemTypeId(Integer toDoItemTypeId) {
		this.toDoItemTypeId = toDoItemTypeId;
	}
	public String getRelatedBusinessType() {
		return relatedBusinessType;
	}
	public void setRelatedBusinessType(String relatedBusinessType) {
		this.relatedBusinessType = relatedBusinessType;
	}
	public Integer getRelatedBusinessId() {
		return relatedBusinessId;
	}
	public void setRelatedBusinessId(Integer relatedBusinessId) {
		this.relatedBusinessId = relatedBusinessId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getToDoItemEmployeeId() {
		return toDoItemEmployeeId;
	}
	public void setToDoItemEmployeeId(Integer toDoItemEmployeeId) {
		this.toDoItemEmployeeId = toDoItemEmployeeId;
	}
	public String getToDoItemRemindTitle() {
		return toDoItemRemindTitle;
	}
	public void setToDoItemRemindTitle(String toDoItemRemindTitle) {
		this.toDoItemRemindTitle = toDoItemRemindTitle;
	}
	public String getToDoItemDealUrl() {
		return toDoItemDealUrl;
	}
	public void setToDoItemDealUrl(String toDoItemDealUrl) {
		this.toDoItemDealUrl = toDoItemDealUrl;
	}
	public Date getToDoItemGeneratedDatetime() {
		return toDoItemGeneratedDatetime;
	}
	public void setToDoItemGeneratedDatetime(Date toDoItemGeneratedDatetime) {
		this.toDoItemGeneratedDatetime = toDoItemGeneratedDatetime;
	}
	public Date getToDoItemRemindDatetime() {
		return toDoItemRemindDatetime;
	}
	public void setToDoItemRemindDatetime(Date toDoItemRemindDatetime) {
		this.toDoItemRemindDatetime = toDoItemRemindDatetime;
	}
	public Date getToDoItemViewDatetime() {
		return toDoItemViewDatetime;
	}
	public void setToDoItemViewDatetime(Date toDoItemViewDatetime) {
		this.toDoItemViewDatetime = toDoItemViewDatetime;
	}
	public String getIsViewd() {
		return isViewd;
	}
	public void setIsViewd(String isViewd) {
		this.isViewd = isViewd;
	}
	public String getIsSolved() {
		return isSolved;
	}
	public void setIsSolved(String isSolved) {
		this.isSolved = isSolved;
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
	public String getDanyuan() {
		return danyuan;
	}
	public void setDanyuan(String danyuan) {
		this.danyuan = danyuan;
	}
	public String getShi() {
		return shi;
	}
	public void setShi(String shi) {
		this.shi = shi;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	
}
