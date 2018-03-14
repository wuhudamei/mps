/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import cn.damei.common.persistence.DataEntity;

/**
 * 客诉罚款Entity
 * 
 * @author ZTW
 * @version 2017-10-27
 */
public class ProComplaintForfeit extends DataEntity<ProComplaintForfeit> {

	private static final long serialVersionUID = 1L;
	private String sortId; // 序号
	private String orderId; // 订单ID
	private String storeId; // 门店
	private String orderacceptarea; // 区域
	private String complaintProblemItemId; // 事项ID
	private Date getOrderDatetime; // 工程部接单时间
	private String receivePerson; // 接单人
	private String complaintSource; // 投诉部门
	private String complaintPersonName; // 投诉人姓名
	private String orderNmber; // 订单编号
	private String customername; // 客户姓名
	private String customerPhone; // 客户手机
	private String customerAddr; // 客户地址
	private String itemManager; // 项目经理
	private String itemManagerId; // 项目经理
	private String itemManagerPhone; // 项目经理手机号
	private String itemName; // 事项名称

	private Date promiseComDate; // 承诺完成时间
	private Date actualComDate; // 实际完成时间
	private String punishMoney; // 惩罚金额
	private String exaOpinion; // 审批意见
	private String status; // 状态
	private Date beginGetOrderDatetime; // 开始 接单时间
	private Date endGetOrderDatetime; // 结束 接单时间
	private Date beginPromiseComDate; // 开始 计划完成时间
	private Date endPromiseComDate; // 结束 计划完成时间
	private Date beginActualComDate; // 开始 实际完成时间
	private Date endActualComDate; // 结束 实际完成时间

	public ProComplaintForfeit() {
		super();
	}

	public ProComplaintForfeit(String id) {
		super(id);
	}

	public String getSortId() {
		return sortId;
	}

	public void setSortId(String sortId) {
		this.sortId = sortId;
	}

	@Length(min = 0, max = 11, message = "门店长度必须介于 0 和 11 之间")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Length(min = 0, max = 11, message = "区域长度必须介于 0 和 11 之间")
	public String getComplaintProblemItemId() {
		return complaintProblemItemId;
	}

	public void setComplaintProblemItemId(String complaintProblemItemId) {
		this.complaintProblemItemId = complaintProblemItemId;
	}

	public String getItemManagerId() {
		return itemManagerId;
	}

	public void setItemManagerId(String itemManagerId) {
		this.itemManagerId = itemManagerId;
	}

	public void setPromiseComDate(Date promiseComDate) {
		this.promiseComDate = promiseComDate;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPromiseComDate() {
		return promiseComDate;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getActualComDate() {
		return actualComDate;
	}

	public void setActualComDate(Date actualComDate) {
		this.actualComDate = actualComDate;
	}

	public String getPunishMoney() {
		return punishMoney;
	}

	public void setPunishMoney(String punishMoney) {
		this.punishMoney = punishMoney;
	}

	@Length(min = 0, max = 255, message = "审批意见长度必须介于 0 和 255 之间")
	public String getExaOpinion() {
		return exaOpinion;
	}

	public void setExaOpinion(String exaOpinion) {
		this.exaOpinion = exaOpinion;
	}

	@Length(min = 0, max = 2, message = "状态长度必须介于 0 和 2 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getBeginPromiseComDate() {
		return beginPromiseComDate;
	}

	public void setBeginPromiseComDate(Date beginPromiseComDate) {
		this.beginPromiseComDate = beginPromiseComDate;
	}

	public Date getEndPromiseComDate() {
		return endPromiseComDate;
	}

	public void setEndPromiseComDate(Date endPromiseComDate) {
		this.endPromiseComDate = endPromiseComDate;
	}

	public Date getBeginActualComDate() {
		return beginActualComDate;
	}

	public void setBeginActualComDate(Date beginActualComDate) {
		this.beginActualComDate = beginActualComDate;
	}

	public Date getEndActualComDate() {
		return endActualComDate;
	}

	public void setEndActualComDate(Date endActualComDate) {
		this.endActualComDate = endActualComDate;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getOrderacceptarea() {
		return orderacceptarea;
	}

	public void setOrderacceptarea(String orderacceptarea) {
		this.orderacceptarea = orderacceptarea;
	}

	public Date getGetOrderDatetime() {
		return getOrderDatetime;
	}

	public void setGetOrderDatetime(Date getOrderDatetime) {
		this.getOrderDatetime = getOrderDatetime;
	}

	public String getReceivePerson() {
		return receivePerson;
	}

	public void setReceivePerson(String receivePerson) {
		this.receivePerson = receivePerson;
	}

	public String getComplaintSource() {
		return complaintSource;
	}

	public void setComplaintSource(String complaintSource) {
		this.complaintSource = complaintSource;
	}

	public String getComplaintPersonName() {
		return complaintPersonName;
	}

	public void setComplaintPersonName(String complaintPersonName) {
		this.complaintPersonName = complaintPersonName;
	}

	public String getOrderNmber() {
		return orderNmber;
	}

	public void setOrderNmber(String orderNmber) {
		this.orderNmber = orderNmber;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getCustomerAddr() {
		return customerAddr;
	}

	public void setCustomerAddr(String customerAddr) {
		this.customerAddr = customerAddr;
	}

	public String getItemManager() {
		return itemManager;
	}

	public void setItemManager(String itemManager) {
		this.itemManager = itemManager;
	}

	public String getItemManagerPhone() {
		return itemManagerPhone;
	}

	public void setItemManagerPhone(String itemManagerPhone) {
		this.itemManagerPhone = itemManagerPhone;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Date getBeginGetOrderDatetime() {
		return beginGetOrderDatetime;
	}

	public void setBeginGetOrderDatetime(Date beginGetOrderDatetime) {
		this.beginGetOrderDatetime = beginGetOrderDatetime;
	}

	public Date getEndGetOrderDatetime() {
		return endGetOrderDatetime;
	}

	public void setEndGetOrderDatetime(Date endGetOrderDatetime) {
		this.endGetOrderDatetime = endGetOrderDatetime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}