
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import cn.damei.common.persistence.DataEntity;


public class BizPmAttendSalaryBill extends DataEntity<BizPmAttendSalaryBill> {
	
	private static final long serialVersionUID = 1L;
	private String salaryBillId;
	private String pmAttendSalaryBillCode;
	private String pmAttendSalarySummaryBillId;
	private String pmAttendMonthId;
	private String pmEmployeeId;
	private String pmStar;
	private String pmStarSalary;
	private String pmStarSalaryMin;
	private String pmStarSalaryAttendFullDefault;
	private String pmStarSalaryAttendDefault;
	private String pmStarSalaryRealDefault;
	private String pmStarSalaryAttendFull;
	private String pmStarSalaryAttend;
	private String pmStarSalaryPunish;
	private String pmStarSalaryReal;
	private String status;
	private Date statusDatetime;
	private String statusEmployeeId;
	private String statusDescribe;
	private String storeId;
	private String enginDepartId;
	private String departmentName;
	private String attendMonth;
	private String attendRateStart;
	private String attendRateEnd;
	private String itemManager;
	private BigDecimal attendRate;
	
	private String itemManagerId;
	
	private String phone;
	
	private String customerName;
	
	private String customerAddress;
	
	private String orderStatusNumber;
	
	private String countDay;
	
	private String sjCountDay;
	
	private String wcDay;
	
	private double starSalaryAllAttend;
	
	private double starSalaryMin;
	
	private double starSalaryReal;

	private String actualValueTotal;

	private String salaryBillIds;

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