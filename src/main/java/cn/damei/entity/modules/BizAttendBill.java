package cn.damei.entity.modules;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import cn.damei.common.persistence.DataEntity2;

public class BizAttendBill extends DataEntity2<BizAttendBill> {

	private static final long serialVersionUID = 1L;

	private Integer attendBatchId;

	private String attendBillCode;

	private String attendEmployeeRole;

	private Integer attendEmployeeId;

	@DateTimeFormat(pattern = "yyyy-MM")
	private Date attendMonth;
	

	@DateTimeFormat(pattern = "yyyy-MM")
	private Date attendMonth2;
	

	private Integer attendDaysTotal;

	private Integer attendDaysWhole;

	private Integer attendDaysHalf;

	private Double basicSalary;

	private String attendRemarks;

	private Double attendDaysMust;

	private Double leaveDaysThing;

	private Double leaveDaysSick;

	private Double leaveDaysAnnual;

	private Double restDays;

	private String status;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date statusDatetime;

	private String statusRemarks;
	

	private Integer enginDepartId;

	private String departmentName;
	

	private String managerName;

	private String phone;

	private String storeId;

	private String projectMode;

	private String starLevel;

	private Integer sex;
	
	private String no;
	
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}
	
	public String getSex2(){
		return this.sex==1?"男":"女";
	}
	
	public String getRole(){
		boolean b = this.attendEmployeeRole.equals("1");
		if(b){
			return "项目经理";
		}else{
			return "其他";
		}
	}
	
	public Date getAttendMonth2() {
		return attendMonth2;
	}

	public void setAttendMonth2(Date attendMonth2) {
		this.attendMonth2 = attendMonth2;
	}

	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Integer getAttendBatchId() {
		return attendBatchId;
	}
	public void setAttendBatchId(Integer attendBatchId) {
		this.attendBatchId = attendBatchId;
	}
	public String getAttendBillCode() {
		return attendBillCode;
	}
	public void setAttendBillCode(String attendBillCode) {
		this.attendBillCode = attendBillCode;
	}
	public String getAttendEmployeeRole() {
		return attendEmployeeRole;
	}
	public void setAttendEmployeeRole(String attendEmployeeRole) {
		this.attendEmployeeRole = attendEmployeeRole;
	}
	public Integer getAttendEmployeeId() {
		return attendEmployeeId;
	}
	public void setAttendEmployeeId(Integer attendEmployeeId) {
		this.attendEmployeeId = attendEmployeeId;
	}
	public Date getAttendMonth() {
		return attendMonth;
	}
	public void setAttendMonth(Date attendMonth) {
		this.attendMonth = attendMonth;
	}
	public Double getBasicSalary() {
		return basicSalary;
	}
	public void setBasicSalary(Double basicSalary) {
		this.basicSalary = basicSalary;
	}
	public String getAttendRemarks() {
		return attendRemarks;
	}
	public void setAttendRemarks(String attendRemarks) {
		this.attendRemarks = attendRemarks;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getStatusDatetime() {
		return statusDatetime;
	}
	public void setStatusDatetime(Date statusDatetime) {
		this.statusDatetime = statusDatetime;
	}
	public String getStatusRemarks() {
		return statusRemarks;
	}
	public void setStatusRemarks(String statusRemarks) {
		this.statusRemarks = statusRemarks;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
	public String getStarLevel() {
		return starLevel;
	}
	public void setStarLevel(String starLevel) {
		this.starLevel = starLevel;
	}
	public Integer getEnginDepartId() {
		return enginDepartId;
	}
	public void setEnginDepartId(Integer enginDepartId) {
		this.enginDepartId = enginDepartId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	public Integer getAttendDaysTotal() {
		return attendDaysTotal;
	}

	public void setAttendDaysTotal(Integer attendDaysTotal) {
		this.attendDaysTotal = attendDaysTotal;
	}

	public Integer getAttendDaysWhole() {
		return attendDaysWhole;
	}

	public void setAttendDaysWhole(Integer attendDaysWhole) {
		this.attendDaysWhole = attendDaysWhole;
	}

	public Integer getAttendDaysHalf() {
		return attendDaysHalf;
	}

	public void setAttendDaysHalf(Integer attendDaysHalf) {
		this.attendDaysHalf = attendDaysHalf;
	}

	public Double getAttendDaysMust() {
		return attendDaysMust;
	}

	public void setAttendDaysMust(Double attendDaysMust) {
		this.attendDaysMust = attendDaysMust;
	}

	public Double getLeaveDaysThing() {
		return leaveDaysThing;
	}

	public void setLeaveDaysThing(Double leaveDaysThing) {
		this.leaveDaysThing = leaveDaysThing;
	}

	public Double getLeaveDaysSick() {
		return leaveDaysSick;
	}

	public void setLeaveDaysSick(Double leaveDaysSick) {
		this.leaveDaysSick = leaveDaysSick;
	}

	public Double getLeaveDaysAnnual() {
		return leaveDaysAnnual;
	}

	public void setLeaveDaysAnnual(Double leaveDaysAnnual) {
		this.leaveDaysAnnual = leaveDaysAnnual;
	}

	public Double getRestDays() {
		return restDays;
	}

	public void setRestDays(Double restDays) {
		this.restDays = restDays;
	}
	
	
}
