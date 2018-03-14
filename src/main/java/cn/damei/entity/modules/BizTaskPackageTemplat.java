/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity;
import cn.damei.common.utils.StringUtils;

/**
 * 单表生成Entity
 * @author ThinkGem
 * @version 2016-09-03
 */
public class BizTaskPackageTemplat extends DataEntity<BizTaskPackageTemplat> {
	
	private static final long serialVersionUID = 1L;
	private String no;		// 任务包模板编号
	private String templatName;		// 任务包模板名称
	private String taskPackageTypeId;		// 任务包类型id
	private String storeId;		// 门店id
	private String storeOrder;		// 门店顺序
	private String projectMode; //工程模式
	private String advancePaymentRates;		// 首付款比例
	private String restPaymentRates;		// 付尾款比例
	private String status;		// 状态;0:停用，1：启用
	private String procedureSelect;		//给页面中下拉用的，无实际意义
	private String procedureItems;		//包含的工序
	private List<BizTaskPackageTemplatRelProcedure> bizTaskPackageTemplatRefs;//展示用的
	private String isQualityGuarantee;//是否扣质保金；0：否，1：是
	private String qualityGuaranteeRate;//质保金扣除比例;1-100
	private String settleStyle;// 新增结算方式，1-包工包料，2-包工
	private String projectMaxCount; //最大派单数量
	private Integer empWorkType;		// 常用工种
	private Double applyBudgetRatioDouble;		// 申请预算比例 小数类型
	private Integer applyBudgetRatio;		// 申请预算比例
	
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