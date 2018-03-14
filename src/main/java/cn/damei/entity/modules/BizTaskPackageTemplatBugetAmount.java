package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;


public class BizTaskPackageTemplatBugetAmount extends DataEntity2<BizTaskPackageTemplatBugetAmount>{
	
	
	
	private static final long serialVersionUID = 1L;

	private Integer taskpackageTemplatId;
	
	private Double budgetSquareMax;
	
	private Double budgetSquareMin;
	
	private Double laborAuxiliaryMaterialsBudgetAmountMax;
	
	private String isEnabled;
	
	private String storeId;
	
	private String projectMode;
	
	private String templatName;
	
	private String templatNumber;
    

	public String getTemplatNumber() {
		return templatNumber;
	}

	public void setTemplatNumber(String templatNumber) {
		this.templatNumber = templatNumber;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}

	public String getTemplatName() {
		return templatName;
	}

	public void setTemplatName(String templatName) {
		this.templatName = templatName;
	}

	public Integer getTaskpackageTemplatId() {
		return taskpackageTemplatId;
	}

	public void setTaskpackageTemplatId(Integer taskpackageTemplatId) {
		this.taskpackageTemplatId = taskpackageTemplatId;
	}

	public Double getBudgetSquareMax() {
		return budgetSquareMax;
	}

	public void setBudgetSquareMax(Double budgetSquareMax) {
		this.budgetSquareMax = budgetSquareMax;
	}

	public Double getBudgetSquareMin() {
		return budgetSquareMin;
	}

	public void setBudgetSquareMin(Double budgetSquareMin) {
		this.budgetSquareMin = budgetSquareMin;
	}

	public Double getLaborAuxiliaryMaterialsBudgetAmountMax() {
		return laborAuxiliaryMaterialsBudgetAmountMax;
	}

	public void setLaborAuxiliaryMaterialsBudgetAmountMax(Double laborAuxiliaryMaterialsBudgetAmountMax) {
		this.laborAuxiliaryMaterialsBudgetAmountMax = laborAuxiliaryMaterialsBudgetAmountMax;
	}

	public String getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(String isEnabled) {
		this.isEnabled = isEnabled;
	}
	
	
}
