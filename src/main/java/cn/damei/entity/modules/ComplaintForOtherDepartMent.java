/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import cn.damei.common.utils.UserUtils;
import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity;

import java.util.Date;

/**
 * 其他部门投诉Entity
 * @author mh
 * @version 2017-07-24
 */
public class ComplaintForOtherDepartMent extends DataEntity<ComplaintForOtherDepartMent> {
	
	private static final long serialVersionUID = 1L;
	private String orderId;
	private String complaintSource;
	private String dataInputChannel;
	private String cusServiceProblemId;
	private String complaintPersonName;
	private String complaintPersonPhone;
	private String status;

	private String storeId;
	private String orderNumber;
	private String projectMode;
	private String departId;
	private String departName;
	private String customerAddress;
	private String customerName;
	private String customerPhone;
	private String managerName;
	private String managerPhone;

	//权限使用
	private String complaintEmployeeId= UserUtils.getUser().getEmpId();

	private Date startDate;
	private Date endDate;
	private String complaintText;

	public String getComplaintEmployeeId() {
		return complaintEmployeeId;
	}

	public void setComplaintEmployeeId(String complaintEmployeeId) {
		this.complaintEmployeeId = complaintEmployeeId;
	}

	public String getComplaintText() {
		return complaintText;
	}

	public void setComplaintText(String complaintText) {
		this.complaintText = complaintText;
	}

	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
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

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

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

	public String getDepartId() {
		return departId;
	}

	public void setDepartId(String departId) {
		this.departId = departId;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
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

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getManagerPhone() {
		return managerPhone;
	}

	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}

	public ComplaintForOtherDepartMent() {
		super();
	}

	public ComplaintForOtherDepartMent(String id){
		super(id);
	}

	@Length(min=0, max=11, message="????id -- '长度必须介于 0 和 11 之间")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	

	public String getComplaintSource() {
		return complaintSource;
	}

	public void setComplaintSource(String complaintSource) {
		this.complaintSource = complaintSource;
	}
	

	public String getDataInputChannel() {
		return dataInputChannel;
	}

	public void setDataInputChannel(String dataInputChannel) {
		this.dataInputChannel = dataInputChannel;
	}
	
	@Length(min=0, max=11, message="?ۺ?????id -- '长度必须介于 0 和 11 之间")
	public String getCusServiceProblemId() {
		return cusServiceProblemId;
	}

	public void setCusServiceProblemId(String cusServiceProblemId) {
		this.cusServiceProblemId = cusServiceProblemId;
	}
	
	@Length(min=0, max=20, message="Ͷ???????? -- '长度必须介于 0 和 20 之间")
	public String getComplaintPersonName() {
		return complaintPersonName;
	}

	public void setComplaintPersonName(String complaintPersonName) {
		this.complaintPersonName = complaintPersonName;
	}
	
	@Length(min=0, max=11, message="Ͷ?????ֻ??? -- '长度必须介于 0 和 11 之间")
	public String getComplaintPersonPhone() {
		return complaintPersonPhone;
	}

	public void setComplaintPersonPhone(String complaintPersonPhone) {
		this.complaintPersonPhone = complaintPersonPhone;
	}
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}