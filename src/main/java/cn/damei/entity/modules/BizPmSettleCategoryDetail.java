/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 结算类目明细Entity
 * @author qww
 * @version 2016-12-26
 */
public class BizPmSettleCategoryDetail extends DataEntity2<BizPmSettleCategoryDetail> {
	
	private static final long serialVersionUID = 1L;
	private Integer orderId;		// 订单id -- '
	private Integer pmEmployeeId;		// 项目经理员工id -- '
	private String settleCategory;		// 结算类目 -- '1.标化辅材;2.自主支配;3.中期提成;4.质检罚款;5.竣工提成;6.质保金; 11-自采材料报销;12-项目经理材料结算类目明细;
	private Double settleAmount;		// 结算金额 -- '
	private Integer pmSettleCategorySummaryId;		// 项目经理结算类目汇总id -- '
	private String settleStatus;		// 结算状态 -- '
	private Date settleStatusDatetime;		// 结算状态日期时间 -- '
	private String settleRemark;		// 结算备注 -- '
	private Integer relatedBusinessId;		// 关联业务id -- '
	private String settleRole;         //结算角色 1项目经理 2质检员
	private String relatedBusinessIdVarchar; 
	
	
	
	public String getRelatedBusinessIdVarchar() {
		return relatedBusinessIdVarchar;
	}

	public void setRelatedBusinessIdVarchar(String relatedBusinessIdVarchar) {
		this.relatedBusinessIdVarchar = relatedBusinessIdVarchar;
	}

	public BizPmSettleCategoryDetail() {
		super();
	}

	public BizPmSettleCategoryDetail(Integer id){
		super(id);
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	public Integer getPmEmployeeId() {
		return pmEmployeeId;
	}

	public void setPmEmployeeId(Integer pmEmployeeId) {
		this.pmEmployeeId = pmEmployeeId;
	}

	public String getSettleCategory() {
		return settleCategory;
	}

	public void setSettleCategory(String settleCategory) {
		this.settleCategory = settleCategory;
	}
	
	public Double getSettleAmount() {
		return settleAmount;
	}

	public void setSettleAmount(Double settleAmount) {
		this.settleAmount = settleAmount;
	}
	
	public Integer getPmSettleCategorySummaryId() {
		return pmSettleCategorySummaryId;
	}

	public void setPmSettleCategorySummaryId(Integer pmSettleCategorySummaryId) {
		this.pmSettleCategorySummaryId = pmSettleCategorySummaryId;
	}
	
	@Length(min=0, max=10, message="结算状态 -- '长度必须介于 0 和 10 之间")
	public String getSettleStatus() {
		return settleStatus;
	}

	public void setSettleStatus(String settleStatus) {
		this.settleStatus = settleStatus;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSettleStatusDatetime() {
		return settleStatusDatetime;
	}

	public void setSettleStatusDatetime(Date settleStatusDatetime) {
		this.settleStatusDatetime = settleStatusDatetime;
	}
	
	@Length(min=0, max=500, message="结算备注 -- '长度必须介于 0 和 500 之间")
	public String getSettleRemark() {
		return settleRemark;
	}

	public void setSettleRemark(String settleRemark) {
		this.settleRemark = settleRemark;
	}
	
	public Integer getRelatedBusinessId() {
		return relatedBusinessId;
	}

	public void setRelatedBusinessId(Integer relatedBusinessId) {
		this.relatedBusinessId = relatedBusinessId;
	}

	public String getSettleRole() {
		return settleRole;
	}

	public void setSettleRole(String settleRole) {
		this.settleRole = settleRole;
	}
}