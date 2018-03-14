package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;


public class BizGuaranteeMoneyBalance extends DataEntity2<BizGuaranteeMoneyBalance>{

	private static final long serialVersionUID = 1L;
	
    private Integer employeeId;
    
    private Double guaranteeMoneyBalance;
    
    private Double guaranteeMoneyAmountPaidSettle;
    
    private Double guaranteMoneyAmountPaidOffline;
    
    private Double guaranteeMoneyAmountPaidUsed;
    
    private String storeId;

	private String projectMode;

	private Integer engineDepartId;
	
	private String empType;
	
    private String empName;
	
	private String empPhone;
	
	private String workType;
	
	private String star;

	
	public String getWorkType() {
		return workType;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public Double getGuaranteeMoneyBalance() {
		if(guaranteeMoneyBalance == null){
			guaranteeMoneyBalance = 0.0;
		}
		return guaranteeMoneyBalance;
	}

	public void setGuaranteeMoneyBalance(Double guaranteeMoneyBalance) {
		this.guaranteeMoneyBalance = guaranteeMoneyBalance;
	}

	public Double getGuaranteeMoneyAmountPaidSettle() {
		if(guaranteeMoneyAmountPaidSettle == null){
			guaranteeMoneyAmountPaidSettle = 0.0;
		}
		return guaranteeMoneyAmountPaidSettle;
	}

	public void setGuaranteeMoneyAmountPaidSettle(Double guaranteeMoneyAmountPaidSettle) {
		this.guaranteeMoneyAmountPaidSettle = guaranteeMoneyAmountPaidSettle;
	}

	public Double getGuaranteMoneyAmountPaidOffline() {
		if(guaranteMoneyAmountPaidOffline == null){
			guaranteMoneyAmountPaidOffline = 0.0;
		}
		return guaranteMoneyAmountPaidOffline;
	}

	public void setGuaranteMoneyAmountPaidOffline(Double guaranteMoneyAmountPaidOffline) {
		this.guaranteMoneyAmountPaidOffline = guaranteMoneyAmountPaidOffline;
	}

	public Double getGuaranteeMoneyAmountPaidUsed() {
		if(this.guaranteeMoneyAmountPaidUsed == null){
			guaranteeMoneyAmountPaidUsed = 0.0;
		}
		return guaranteeMoneyAmountPaidUsed;
	}

	public void setGuaranteeMoneyAmountPaidUsed(Double guaranteeMoneyAmountPaidUsed) {
		this.guaranteeMoneyAmountPaidUsed = guaranteeMoneyAmountPaidUsed;
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

	public Integer getEngineDepartId() {
		return engineDepartId;
	}

	public void setEngineDepartId(Integer engineDepartId) {
		this.engineDepartId = engineDepartId;
	}

	public String getEmpType() {
		return empType;
	}

	public void setEmpType(String empType) {
		this.empType = empType;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpPhone() {
		return empPhone;
	}

	public void setEmpPhone(String empPhone) {
		this.empPhone = empPhone;
	}

	public String getStar() {
		return star;
	}

	public void setStar(String star) {
		this.star = star;
	}
	
	
	
}
