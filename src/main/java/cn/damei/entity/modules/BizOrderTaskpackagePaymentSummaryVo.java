/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;

import java.util.Date;
import java.util.List;

/**
 * 付款单VoEntity
 * @author qww
 * @version 2016-10-26
 */
public class BizOrderTaskpackagePaymentSummaryVo extends DataEntity2<BizOrderTaskpackagePaymentSummaryVo> {

	private static final long serialVersionUID = 1L;

	private Integer storeId; // 付款单门店
	private String orderTaskpackagePaymentSummaryCode; // 批次编号
	private String summaryStatus; // 批次状态
	private Date summaryGeneratedDatetime; // 批次生成日期
	private String orderTaskpackagePaymentCode; // 付款单编号
	private String paymentStatus; // 付款单状态
	private Date paymentGeneratedDatetime; // 付款单生成日期
	private Double amount; // 付款单金额
	private String orderTaskpackagePaymentType; // 付款单类型
	private String packageName; // 对应任务包名
	private String realName; // 对应工人

	private List<String> summaryStatusList;
	private List<String> paymentStatusList;
	private Date beginSummaryGeneratedDatetime;
	private Date endSummaryGeneratedDatetime;
	private Date beginPaymentGeneratedDatetime;
	private Date endPaymentGeneratedDatetime;

	public BizOrderTaskpackagePaymentSummaryVo() {
		super();
	}

	public BizOrderTaskpackagePaymentSummaryVo(Integer id){
		super(id);
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getOrderTaskpackagePaymentSummaryCode() {
		return orderTaskpackagePaymentSummaryCode;
	}

	public void setOrderTaskpackagePaymentSummaryCode(String orderTaskpackagePaymentSummaryCode) {
		this.orderTaskpackagePaymentSummaryCode = orderTaskpackagePaymentSummaryCode;
	}

	public String getSummaryStatus() {
		return summaryStatus;
	}

	public void setSummaryStatus(String summaryStatus) {
		this.summaryStatus = summaryStatus;
	}

	public Date getSummaryGeneratedDatetime() {
		return summaryGeneratedDatetime;
	}

	public void setSummaryGeneratedDatetime(Date summaryGeneratedDatetime) {
		this.summaryGeneratedDatetime = summaryGeneratedDatetime;
	}

	public String getOrderTaskpackagePaymentCode() {
		return orderTaskpackagePaymentCode;
	}

	public void setOrderTaskpackagePaymentCode(String orderTaskpackagePaymentCode) {
		this.orderTaskpackagePaymentCode = orderTaskpackagePaymentCode;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public Date getPaymentGeneratedDatetime() {
		return paymentGeneratedDatetime;
	}

	public void setPaymentGeneratedDatetime(Date paymentGeneratedDatetime) {
		this.paymentGeneratedDatetime = paymentGeneratedDatetime;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getOrderTaskpackagePaymentType() {
		return orderTaskpackagePaymentType;
	}

	public void setOrderTaskpackagePaymentType(String orderTaskpackagePaymentType) {
		this.orderTaskpackagePaymentType = orderTaskpackagePaymentType;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public List<String> getSummaryStatusList() {
		return summaryStatusList;
	}

	public void setSummaryStatusList(List<String> summaryStatusList) {
		this.summaryStatusList = summaryStatusList;
	}

	public Date getBeginSummaryGeneratedDatetime() {
		return beginSummaryGeneratedDatetime;
	}

	public void setBeginSummaryGeneratedDatetime(Date beginSummaryGeneratedDatetime) {
		this.beginSummaryGeneratedDatetime = beginSummaryGeneratedDatetime;
	}

	public Date getEndSummaryGeneratedDatetime() {
		return endSummaryGeneratedDatetime;
	}

	public void setEndSummaryGeneratedDatetime(Date endSummaryGeneratedDatetime) {
		this.endSummaryGeneratedDatetime = endSummaryGeneratedDatetime;
	}

	public Date getBeginPaymentGeneratedDatetime() {
		return beginPaymentGeneratedDatetime;
	}

	public void setBeginPaymentGeneratedDatetime(Date beginPaymentGeneratedDatetime) {
		this.beginPaymentGeneratedDatetime = beginPaymentGeneratedDatetime;
	}

	public Date getEndPaymentGeneratedDatetime() {
		return endPaymentGeneratedDatetime;
	}

	public void setEndPaymentGeneratedDatetime(Date endPaymentGeneratedDatetime) {
		this.endPaymentGeneratedDatetime = endPaymentGeneratedDatetime;
	}

	public List<String> getPaymentStatusList() {
		return paymentStatusList;
	}

	public void setPaymentStatusList(List<String> paymentStatusList) {
		this.paymentStatusList = paymentStatusList;
	}
}