
package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


public class BizPmSettleCategoryDetail extends DataEntity2<BizPmSettleCategoryDetail> {
	
	private static final long serialVersionUID = 1L;
	private Integer orderId;
	private Integer pmEmployeeId;
	private String settleCategory;
	private Double settleAmount;
	private Integer pmSettleCategorySummaryId;
	private String settleStatus;
	private Date settleStatusDatetime;
	private String settleRemark;
	private Integer relatedBusinessId;
	private String settleRole;
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