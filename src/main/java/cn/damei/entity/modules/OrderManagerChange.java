
package cn.damei.entity.modules;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import cn.damei.common.persistence.DataEntity;

public class OrderManagerChange extends DataEntity<OrderManagerChange> {
	
	private static final long serialVersionUID = 1L;

	private Integer storeId;

	private String orderNumber;

	private String customerName;

	private String customerPhone;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date changeDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endChangeDate;

	private String oldManagerName;

	private String oldManagerPhone;

	private String newManagerName;

	private String newManagerPhone;

	private String createName;
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

	public Date getChangeDate() {
		return changeDate;
	}
	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}
	public Date getEndChangeDate() {
		return endChangeDate;
	}
	public void setEndChangeDate(Date endChangeDate) {
		this.endChangeDate = endChangeDate;
	}
	public String getOldManagerName() {
		return oldManagerName;
	}
	public void setOldManagerName(String oldManagerName) {
		this.oldManagerName = oldManagerName;
	}
	public String getOldManagerPhone() {
		return oldManagerPhone;
	}
	public void setOldManagerPhone(String oldManagerPhone) {
		this.oldManagerPhone = oldManagerPhone;
	}
	public String getNewManagerName() {
		return newManagerName;
	}
	public void setNewManagerName(String newManagerName) {
		this.newManagerName = newManagerName;
	}
	public String getNewManagerPhone() {
		return newManagerPhone;
	}
	public void setNewManagerPhone(String newManagerPhone) {
		this.newManagerPhone = newManagerPhone;
	}
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	
	
}