package cn.damei.entity.modules;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.damei.common.persistence.DataEntity2;

public class UrgeCheck extends DataEntity2<UrgeCheck>{

	/**
	 * 催促验收的entity
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer storeId;
	private String projectMode;
	private String orderNumber;
	private String customerName;
	private String customerMessage;
	private String packageName;
	private String itemCustomer;
	private String groupRealname;
	private Integer orderTaskpackageId;
	private Integer toUrgeEmployeeId;//催促人id
	private Integer beUrgedEmployeeId;//被催促人id
	private Date urgeTime; //催促时间
	private Date beginUrgeTime;
	private Date endUrgeTime;
	private Integer engineDepartId;
	private String departmentName;
	private List<Integer> enginDepartIds = new ArrayList<Integer>();
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	
	
	public String getProjectMode() {
		return projectMode;
	}
	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerMessage() {
		return customerMessage;
	}
	public void setCustomerMessage(String customerMessage) {
		this.customerMessage = customerMessage;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getItemCustomer() {
		return itemCustomer;
	}
	public void setItemCustomer(String itemCustomer) {
		this.itemCustomer = itemCustomer;
	}
	public String getGroupRealname() {
		return groupRealname;
	}
	public void setGroupRealname(String groupRealname) {
		this.groupRealname = groupRealname;
	}
	public Integer getOrderTaskpackageId() {
		return orderTaskpackageId;
	}
	public void setOrderTaskpackageId(Integer orderTaskpackageId) {
		this.orderTaskpackageId = orderTaskpackageId;
	}
	public Integer getToUrgeEmployeeId() {
		return toUrgeEmployeeId;
	}
	public void setToUrgeEmployeeId(Integer toUrgeEmployeeId) {
		this.toUrgeEmployeeId = toUrgeEmployeeId;
	}
	public Integer getBeUrgedEmployeeId() {
		return beUrgedEmployeeId;
	}
	public void setBeUrgedEmployeeId(Integer beUrgedEmployeeId) {
		this.beUrgedEmployeeId = beUrgedEmployeeId;
	}
	public Date getUrgeTime() {
		return urgeTime;
	}
	public void setUrgeTime(Date urgeTime) {
		this.urgeTime = urgeTime;
	}
	public Date getBeginUrgeTime() {
		return beginUrgeTime;
	}
	public void setBeginUrgeTime(Date beginUrgeTime) {
		this.beginUrgeTime = beginUrgeTime;
	}
	public Date getEndUrgeTime() {
		return endUrgeTime;
	}
	public void setEndUrgeTime(Date endUrgeTime) {
		this.endUrgeTime = endUrgeTime;
	}
	public Integer getEngineDepartId() {
		return engineDepartId;
	}
	public void setEngineDepartId(Integer engineDepartId) {
		this.engineDepartId = engineDepartId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public List<Integer> getEnginDepartIds() {
		return enginDepartIds;
	}
	public void setEnginDepartIds(List<Integer> enginDepartIds) {
		this.enginDepartIds = enginDepartIds;
	}
	
}
