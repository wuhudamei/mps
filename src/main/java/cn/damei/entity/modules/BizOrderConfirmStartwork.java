package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;

@SuppressWarnings("serial")
public class BizOrderConfirmStartwork extends DataEntity2<BizOrderConfirmStartwork> {
//	private Integer id;

	/**
	 * 订单id
	 */
	private Integer orderId;

	/**
	 * 开工是否需要客户签字 0.否；1.是
	 */
	private String isNeedSign;

	/**
	 * 自装延期天数
	 */
	private Integer selfDecorateDelayDays;

	/**
	 * 自装是否需要客户签字 0.否；1.是
	 */
	private String isSelfDecorateNeedSign;

	/**
	 * 备注
	 */
	//private String remarks;

	/**
	 * 创建日期时间
	 */
	//private Date createDate;


	/**
	 * 更新日期时间
	 */
	//private Date updateDate;

	/**
	 * 删除标识
	 */
	private String delFlag;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getIsNeedSign() {
		return isNeedSign;
	}

	public void setIsNeedSign(String isNeedSign) {
		this.isNeedSign = isNeedSign;
	}

	public Integer getSelfDecorateDelayDays() {
		return selfDecorateDelayDays;
	}

	public void setSelfDecorateDelayDays(Integer selfDecorateDelayDays) {
		this.selfDecorateDelayDays = selfDecorateDelayDays;
	}

	public String getIsSelfDecorateNeedSign() {
		return isSelfDecorateNeedSign;
	}

	public void setIsSelfDecorateNeedSign(String isSelfDecorateNeedSign) {
		this.isSelfDecorateNeedSign = isSelfDecorateNeedSign;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

}
