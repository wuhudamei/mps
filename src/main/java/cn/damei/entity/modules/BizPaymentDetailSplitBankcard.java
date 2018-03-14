/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;

/**
 * 付款单明细拆分Entity
 * @author www
 * @version 2016-10-31
 */
public class BizPaymentDetailSplitBankcard extends DataEntity2<BizPaymentDetailSplitBankcard> {

	private static final long serialVersionUID = 1L;

	private String realName;
	private Double splitAmountTotal;
	private Double relateRemainAmount;
	private Double relateDiffAmount;
	private Integer relateCount;

	public BizPaymentDetailSplitBankcard() {
		super();
	}

	public BizPaymentDetailSplitBankcard(Integer id){
		super(id);
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Double getSplitAmountTotal() {
		return splitAmountTotal;
	}

	public void setSplitAmountTotal(Double splitAmountTotal) {
		this.splitAmountTotal = splitAmountTotal;
	}

	public Double getRelateRemainAmount() {
		return relateRemainAmount;
	}

	public void setRelateRemainAmount(Double relateRemainAmount) {
		this.relateRemainAmount = relateRemainAmount;
	}

	public Double getRelateDiffAmount() {
		return relateDiffAmount;
	}

	public void setRelateDiffAmount(Double relateDiffAmount) {
		this.relateDiffAmount = relateDiffAmount;
	}

	public Integer getRelateCount() {
		return relateCount;
	}

	public void setRelateCount(Integer relateCount) {
		this.relateCount = relateCount;
	}
}