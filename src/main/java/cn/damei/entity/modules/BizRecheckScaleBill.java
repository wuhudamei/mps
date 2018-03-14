package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

/**
 * 上报复尺新版(20161107-20161113)
 * 
 * @author llp 2016-11-15
 */
@SuppressWarnings("serial")
public class BizRecheckScaleBill extends DataEntity2<BizRecheckScaleBill> {
	private Integer id;

	/**
	 * 订单编号
	 */
	private Integer orderId;

	/**
	 * 复尺单编号
	 */
	private String recheckScaleBillCode;

	/**
	 * 预计安装日期
	 */
	private Date planInstallDate;
	
	/**
	 * 复尺类型
	 */
	private String type;

	/**
	 * 状态
	 */
	private String status;

	/**
	 * 备注
	 */
	private String remarks;

	/**
	 * 创建日期时间
	 */
	private Date createDate;

	/**
	 * 更新日期时间
	 */
	private Date updateDate;

	/**
	 * 是否删除
	 */
	private String delFlag;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getRecheckScaleBillCode() {
		return recheckScaleBillCode;
	}

	public void setRecheckScaleBillCode(String recheckScaleBillCode) {
		this.recheckScaleBillCode = recheckScaleBillCode;
	}

	public Date getPlanInstallDate() {
		return planInstallDate;
	}

	public void setPlanInstallDate(Date planInstallDate) {
		this.planInstallDate = planInstallDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
