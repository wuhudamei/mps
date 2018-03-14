/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import cn.damei.common.persistence.DataEntity;

/**
 * 考勤月度工资单Entity
 * @author wl
 * @version 2017-08-07
 */
public class BizPmAttendSalaryBill extends DataEntity<BizPmAttendSalaryBill> {
	
	private static final long serialVersionUID = 1L;
	private String salaryBillId; //工资单Id
	private String pmAttendSalaryBillCode;		// 工资单编号
	private String pmAttendSalarySummaryBillId;		// pm工资汇总ID
	private String pmAttendMonthId;		// 考勤月度ID
	private String pmEmployeeId;		// 员工ID
	private String pmStar;		// 星级
	private String pmStarSalary;		// 星级全勤底薪
	private String pmStarSalaryMin;		// 星级最低薪水
	private String pmStarSalaryAttendFullDefault;		// 考勤周期全勤薪水
	private String pmStarSalaryAttendDefault;		// 考勤薪水
	private String pmStarSalaryRealDefault;		// 实际薪水
	private String pmStarSalaryAttendFull;		// 考勤周期全勤薪水
	private String pmStarSalaryAttend;		// 考勤薪水
	private String pmStarSalaryPunish;		// 薪水扣款
	private String pmStarSalaryReal;		// 实际薪水
	private String status;		// 状态
	private Date statusDatetime;		// 状态日期时间
	private String statusEmployeeId;		// 状态操作人员ID
	private String statusDescribe;		// 状态描述
	private String storeId; //门店ID
	private String enginDepartId; //区域ID
	private String departmentName; //区域名称
	private String attendMonth;		// 考勤月份
	private String attendRateStart; //考勤率开始
	private String attendRateEnd; //考勤率结束
	private String itemManager; //项目经理名称
	private BigDecimal attendRate;	// 考勤率
	
	private String itemManagerId;//项目经理ID
	
	private String phone;//项目经理手机号
	
	private String customerName; //客户姓名
	
	private String customerAddress; //客户地址
	
	private String orderStatusNumber; //订单状态
	
	private String countDay; //应签到天数
	
	private String sjCountDay; //实际签到天数
	
	private String wcDay; //误差天数
	
	private double starSalaryAllAttend;  //星级全勤底薪
	
	private double starSalaryMin;  //星级最低底薪
	
	private double starSalaryReal; //实际薪水

	private String actualValueTotal;//实际取值总和

	private String salaryBillIds;//全选(工资单ID）

	public String getSalaryBillIds() {
		return salaryBillIds;
	}

	public void setSalaryBillIds(String salaryBillIds) {
		this.salaryBillIds = salaryBillIds;
	}

	public String getActualValueTotal() {
		return actualValueTotal;
	}

	public void setActualValueTotal(String actualValueTotal) {
		this.actualValueTotal = actualValueTotal;
	}

	public BizPmAttendSalaryBill() {
		super();
	}

	public BizPmAttendSalaryBill(String id){
		super(id);
	}

	@Length(min=0, max=100, message="工资单编号长度必须介于 0 和 100 之间")
	public String getPmAttendSalaryBillCode() {
		return pmAttendSalaryBillCode;
	}

	public void setPmAttendSalaryBillCode(String pmAttendSalaryBillCode) {
		this.pmAttendSalaryBillCode = pmAttendSalaryBillCode;
	}
	
	@Length(min=0, max=11, message="pm工资汇总ID长度必须介于 0 和 11 之间")
	public String getPmAttendSalarySummaryBillId() {
		return pmAttendSalarySummaryBillId;
	}

	public void setPmAttendSalarySummaryBillId(String pmAttendSalarySummaryBillId) {
		this.pmAttendSalarySummaryBillId = pmAttendSalarySummaryBillId;
	}
	
	@Length(min=0, max=11, message="考勤月度ID长度必须介于 0 和 11 之间")
	public String getPmAttendMonthId() {
		return pmAttendMonthId;
	}

	public void setPmAttendMonthId(String pmAttendMonthId) {
		this.pmAttendMonthId = pmAttendMonthId;
	}
	
	@Length(min=0, max=11, message="员工ID长度必须介于 0 和 11 之间")
	public String getPmEmployeeId() {
		return pmEmployeeId;
	}

	public void setPmEmployeeId(String pmEmployeeId) {
		this.pmEmployeeId = pmEmployeeId;
	}
	
	@Length(min=0, max=11, message="星级长度必须介于 0 和 11 之间")
	public String getPmStar() {
		return pmStar;
	}

	public void setPmStar(String pmStar) {
		this.pmStar = pmStar;
	}
	
	public String getPmStarSalary() {
		return pmStarSalary;
	}

	public void setPmStarSalary(String pmStarSalary) {
		this.pmStarSalary = pmStarSalary;
	}
	
	public String getPmStarSalaryMin() {
		return pmStarSalaryMin;
	}

	public void setPmStarSalaryMin(String pmStarSalaryMin) {
		this.pmStarSalaryMin = pmStarSalaryMin;
	}
	
	public String getPmStarSalaryAttendFullDefault() {
		return pmStarSalaryAttendFullDefault;
	}

	public void setPmStarSalaryAttendFullDefault(String pmStarSalaryAttendFullDefault) {
		this.pmStarSalaryAttendFullDefault = pmStarSalaryAttendFullDefault;
	}
	
	public String getPmStarSalaryAttendDefault() {
		return pmStarSalaryAttendDefault;
	}

	public void setPmStarSalaryAttendDefault(String pmStarSalaryAttendDefault) {
		this.pmStarSalaryAttendDefault = pmStarSalaryAttendDefault;
	}
	
	public String getPmStarSalaryRealDefault() {
		return pmStarSalaryRealDefault;
	}

	public void setPmStarSalaryRealDefault(String pmStarSalaryRealDefault) {
		this.pmStarSalaryRealDefault = pmStarSalaryRealDefault;
	}
	
	public String getPmStarSalaryAttendFull() {
		return pmStarSalaryAttendFull;
	}

	public void setPmStarSalaryAttendFull(String pmStarSalaryAttendFull) {
		this.pmStarSalaryAttendFull = pmStarSalaryAttendFull;
	}
	
	public String getPmStarSalaryAttend() {
		return pmStarSalaryAttend;
	}

	public void setPmStarSalaryAttend(String pmStarSalaryAttend) {
		this.pmStarSalaryAttend = pmStarSalaryAttend;
	}
	
	public String getPmStarSalaryPunish() {
		return pmStarSalaryPunish;
	}

	public void setPmStarSalaryPunish(String pmStarSalaryPunish) {
		this.pmStarSalaryPunish = pmStarSalaryPunish;
	}
	
	public String getPmStarSalaryReal() {
		return pmStarSalaryReal;
	}

	public void setPmStarSalaryReal(String pmStarSalaryReal) {
		this.pmStarSalaryReal = pmStarSalaryReal;
	}
	
	@Length(min=0, max=10, message="状态长度必须介于 0 和 10 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStatusDatetime() {
		return statusDatetime;
	}

	public void setStatusDatetime(Date statusDatetime) {
		this.statusDatetime = statusDatetime;
	}
	
	@Length(min=0, max=11, message="状态操作人员ID长度必须介于 0 和 11 之间")
	public String getStatusEmployeeId() {
		return statusEmployeeId;
	}

	public void setStatusEmployeeId(String statusEmployeeId) {
		this.statusEmployeeId = statusEmployeeId;
	}
	
	@Length(min=0, max=1000, message="状态描述长度必须介于 0 和 1000 之间")
	public String getStatusDescribe() {
		return statusDescribe;
	}

	public void setStatusDescribe(String statusDescribe) {
		this.statusDescribe = statusDescribe;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getEnginDepartId() {
		return enginDepartId;
	}

	public void setEnginDepartId(String enginDepartId) {
		this.enginDepartId = enginDepartId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getAttendMonth() {
		return attendMonth;
	}

	public void setAttendMonth(String attendMonth) {
		this.attendMonth = attendMonth;
	}

	public String getAttendRateStart() {
		return attendRateStart;
	}

	public void setAttendRateStart(String attendRateStart) {
		this.attendRateStart = attendRateStart;
	}

	public String getAttendRateEnd() {
		return attendRateEnd;
	}

	public void setAttendRateEnd(String attendRateEnd) {
		this.attendRateEnd = attendRateEnd;
	}

	public String getItemManager() {
		return itemManager;
	}

	public void setItemManager(String itemManager) {
		this.itemManager = itemManager;
	}

	public String getItemManagerId() {
		return itemManagerId;
	}

	public void setItemManagerId(String itemManagerId) {
		this.itemManagerId = itemManagerId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getOrderStatusNumber() {
		return orderStatusNumber;
	}

	public void setOrderStatusNumber(String orderStatusNumber) {
		this.orderStatusNumber = orderStatusNumber;
	}

	public String getCountDay() {
		return countDay;
	}

	public void setCountDay(String countDay) {
		this.countDay = countDay;
	}

	public String getSjCountDay() {
		return sjCountDay;
	}

	public void setSjCountDay(String sjCountDay) {
		this.sjCountDay = sjCountDay;
	}

	public String getWcDay() {
		return wcDay;
	}

	public void setWcDay(String wcDay) {
		this.wcDay = wcDay;
	}

	public double getStarSalaryAllAttend() {
		return starSalaryAllAttend;
	}

	public void setStarSalaryAllAttend(double starSalaryAllAttend) {
		this.starSalaryAllAttend = starSalaryAllAttend;
	}

	public double getStarSalaryMin() {
		return starSalaryMin;
	}

	public void setStarSalaryMin(double starSalaryMin) {
		this.starSalaryMin = starSalaryMin;
	}

	public double getStarSalaryReal() {
		return starSalaryReal;
	}

	public void setStarSalaryReal(double starSalaryReal) {
		this.starSalaryReal = starSalaryReal;
	}

	public BigDecimal getAttendRate() {
		return attendRate;
	}

	public void setAttendRate(BigDecimal attendRate) {
		this.attendRate = attendRate;
	}

	public String getSalaryBillId() {
		return salaryBillId;
	}

	public void setSalaryBillId(String salaryBillId) {
		this.salaryBillId = salaryBillId;
	}
}