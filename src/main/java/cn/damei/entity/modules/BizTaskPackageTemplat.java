
package cn.damei.entity.modules;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity;
import cn.damei.common.utils.StringUtils;


public class BizTaskPackageTemplat extends DataEntity<BizTaskPackageTemplat> {
	
	private static final long serialVersionUID = 1L;
	private String no;
	private String templatName;
	private String taskPackageTypeId;
	private String storeId;
	private String storeOrder;
	private String projectMode;
	private String advancePaymentRates;
	private String restPaymentRates;
	private String status;
	private String procedureSelect;
	private String procedureItems;
	private List<BizTaskPackageTemplatRelProcedure> bizTaskPackageTemplatRefs;
	private String isQualityGuarantee;
	private String qualityGuaranteeRate;
	private String settleStyle;
	private String projectMaxCount;
	private Integer empWorkType;
	private Double applyBudgetRatioDouble;
	private Integer applyBudgetRatio;
	
	public BizTaskPackageTemplat() {
		super();
	}

	public BizTaskPackageTemplat(String id){
		super(id);
	}

	
	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}

	@Length(min=0, max=18, message="任务包模板编号长度必须介于 0 和 18 之间")
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}
	
	@Length(min=0, max=100, message="任务包模板名称长度必须介于 0 和 100 之间")
	public String getTemplatName() {
		return templatName;
	}

	public void setTemplatName(String templatName) {
		this.templatName = templatName;
	}
	
	@Length(min=0, max=11, message="任务包类型id长度必须介于 0 和 11 之间")
	public String getTaskPackageTypeId() {
		return taskPackageTypeId;
	}

	public void setTaskPackageTypeId(String taskPackageTypeId) {
		this.taskPackageTypeId = taskPackageTypeId;
	}
	
	@Length(min=0, max=11, message="门店id长度必须介于 0 和 11 之间")
	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	
	@Length(min=0, max=11, message="门店顺序长度必须介于 0 和 11 之间")
	public String getStoreOrder() {
		return storeOrder;
	}

	public void setStoreOrder(String storeOrder) {
		this.storeOrder = storeOrder;
	}
	
	public String getAdvancePaymentRates() {
		if(StringUtils.isNoneBlank(advancePaymentRates)){
			return (int)Double.parseDouble(advancePaymentRates)+"";
		}
		return advancePaymentRates;
	}

	public void setAdvancePaymentRates(String advancePaymentRates) {
		this.advancePaymentRates = advancePaymentRates;
	}
	
	public String getRestPaymentRates() {
		if(StringUtils.isNoneBlank(restPaymentRates)){
			return (int)Double.parseDouble(restPaymentRates)+"";
		}
		return restPaymentRates;
	}

	public void setRestPaymentRates(String restPaymentRates) {
		this.restPaymentRates = restPaymentRates;
	}
	
	@Length(min=0, max=1, message="状态;0:停用，1：启用长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProcedureSelect() {
		return procedureSelect;
	}

	public void setProcedureSelect(String procedureSelect) {
		this.procedureSelect = procedureSelect;
	}

	public String getProcedureItems() {
		return procedureItems;
	}

	public void setProcedureItems(String procedureItems) {
		this.procedureItems = procedureItems;
	}

	public List<BizTaskPackageTemplatRelProcedure> getBizTaskPackageTemplatRefs() {
		return bizTaskPackageTemplatRefs;
	}

	public void setBizTaskPackageTemplatRefs(
			List<BizTaskPackageTemplatRelProcedure> bizTaskPackageTemplatRefs) {
		this.bizTaskPackageTemplatRefs = bizTaskPackageTemplatRefs;
	}

	public String getIsQualityGuarantee() {
		return isQualityGuarantee;
	}

	public void setIsQualityGuarantee(String isQualityGuarantee) {
		this.isQualityGuarantee = isQualityGuarantee;
	}

	public String getQualityGuaranteeRate() {
		return qualityGuaranteeRate;
	}

	public void setQualityGuaranteeRate(String qualityGuaranteeRate) {
		this.qualityGuaranteeRate = qualityGuaranteeRate;
	}

	public String getSettleStyle() {
		return settleStyle;
	}

	public void setSettleStyle(String settleStyle) {
		this.settleStyle = settleStyle;
	}

	public String getProjectMaxCount() {
		return projectMaxCount;
	}

	public void setProjectMaxCount(String projectMaxCount) {
		this.projectMaxCount = projectMaxCount;
	}

	public Integer getEmpWorkType() {
		return empWorkType;
	}

	public void setEmpWorkType(Integer empWorkType) {
		this.empWorkType = empWorkType;
	}

	public Double getApplyBudgetRatioDouble() {
		return applyBudgetRatioDouble;
	}

	public void setApplyBudgetRatioDouble(Double applyBudgetRatioDouble) {
		this.applyBudgetRatioDouble = applyBudgetRatioDouble;
	}

	public Integer getApplyBudgetRatio() {
		return applyBudgetRatio;
	}

	public void setApplyBudgetRatio(Integer applyBudgetRatio) {
		this.applyBudgetRatio = applyBudgetRatio;
	}
	
}