package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;

/**
 * 质保金余额实体类
 * @author hyh
 *
 */
public class BizGuaranteeMoneyBalance extends DataEntity2<BizGuaranteeMoneyBalance>{

	private static final long serialVersionUID = 1L;
	
    private Integer employeeId;//员工Id
    
    private Double guaranteeMoneyBalance; //质保金余额
    
    private Double guaranteeMoneyAmountPaidSettle;//质保金结算上缴总额
    
    private Double guaranteMoneyAmountPaidOffline;//质保金线下上缴总额
    
    private Double guaranteeMoneyAmountPaidUsed;//质保金使用总额
    
    private String storeId;// 门店Id

	private String projectMode;// 工程模式

	private Integer engineDepartId;// 区域
	
	private String empType;//人员类型 1：项目经理 2：工人 
	
    private String empName;//员工姓名
	
	private String empPhone;//员工手机号 
	
	private String workType;//工种
	
	private String star;//星级

	
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
