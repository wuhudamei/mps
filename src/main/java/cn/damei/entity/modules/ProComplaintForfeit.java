
package cn.damei.entity.modules;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import cn.damei.common.persistence.DataEntity;


public class ProComplaintForfeit extends DataEntity<ProComplaintForfeit> {

	private static final long serialVersionUID = 1L;
	private String sortId;
	private String orderId;
	private String storeId;
	private String orderacceptarea;
	private String complaintProblemItemId;
	private Date getOrderDatetime;
	private String receivePerson;
	private String complaintSource;
	private String complaintPersonName;
	private String orderNmber;
	private String customername;
	private String customerPhone;
	private String customerAddr;
	private String itemManager;
	private String itemManagerId;
	private String itemManagerPhone;
	private String itemName;

	private Date promiseComDate;
	private Date actualComDate;
	private String punishMoney;
	private String exaOpinion;
	private String status;
	private Date beginGetOrderDatetime;
	private Date endGetOrderDatetime;
	private Date beginPromiseComDate;
	private Date endPromiseComDate;
	private Date beginActualComDate;
	private Date endActualComDate;

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