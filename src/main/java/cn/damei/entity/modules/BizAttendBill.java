package cn.damei.entity.modules;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import cn.damei.common.persistence.DataEntity2;
/**
 * 考勤单
 * @author 
 *
 */
public class BizAttendBill extends DataEntity2<BizAttendBill> {

	private static final long serialVersionUID = 1L;
	//考勤单批次id
	private Integer attendBatchId;
	//考勤单编号
	private String attendBillCode;
	//考勤人角色 1 经理
	private String attendEmployeeRole;
	//考勤人id
	private Integer attendEmployeeId;
	//考勤月份
	@DateTimeFormat(pattern = "yyyy-MM")
	private Date attendMonth;
	
	//考勤月份
	@DateTimeFormat(pattern = "yyyy-MM")
	private Date attendMonth2;
	
	//实际出勤天数
	private Integer attendDaysTotal;
	//全勤天数
	private Integer attendDaysWhole;
	//半勤天数
	private Integer attendDaysHalf;
	//底薪
	private Double basicSalary;
	//考勤备注
	private String attendRemarks;
	//应出勤天数
	private Double attendDaysMust;
	//事假天数
	private Double leaveDaysThing;
	//病假天数
	private Double leaveDaysSick;
	//年假天数
	private Double leaveDaysAnnual;
	//补休天数
	private Double restDays;
	//状态 20 以生成考勤单，30 以生成考勤批次
	private String status;
	//状态日期时间
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date statusDatetime;
	//状态备注
	private String statusRemarks;
	
	//区域id
	private Integer enginDepartId;
	//区域名字
	private String departmentName;
	
	//项目经理名字
	private String managerName;
	//手机号
	private String phone;
	//门店id
	private String storeId;
	//工程模式
	private String projectMode;
	//星级
	private String starLevel;
	//性别
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
