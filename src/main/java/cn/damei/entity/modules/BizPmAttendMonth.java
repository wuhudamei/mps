/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import com.fasterxml.jackson.annotation.JsonFormat;
import cn.damei.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 考勤月度表Entity
 * @author wl
 * @version 2017-08-02
 */
public class BizPmAttendMonth extends DataEntity<BizPmAttendMonth> {
	
	private static final long serialVersionUID = 1L;
	private String attendMonth;		// 考勤月份
	private String pmEmployeeId;		// 员工id
	private Date attendStartDate;		// 考勤开始日期
	private Date attendEndDate;		// 考勤结束日期
	private String mustSignTimes;		// 应签到次数
	private String realSignTimes;		// 实际签到次数
	private String owedSignTimes;		// 欠缺签到次数
	private Double attendRate;		// 考勤率
	private String status; //考勤状态
	private Date statusDatetime; //状态时间
	private String statusEmployeeId; //状态修改人
	private String statusDescribe;
	private String pmAttendMonthId; //考勤月份ID
	
	private String naturalDay;
	
	private Integer itemManagerStar; //项目经理星级
	
	private String attendMonthCode; //考勤code
	
	private String attendRateStart; //考勤率开始
	private String attendRateEnd; //考勤率结束
	
	private String storeId; //门店ID
	
	private String enginDepartId; //区域ID
	
	private String departmentName; //区域名称
	
	private String itemManager; //项目经理名称
	
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
	
	private Integer signCycleDaysBasicwork; //基装签到周期数
	
	private Integer signCycleDaysComplete;//竣工签到周期数
	
	private Date actualStartDate; //开工时间
	
	private String orderId; //订单ID
	
	private String mustSignTimes2;
	
	private String realSignTimes2;
	//实际取值
	private String actualValue;
	private String actualValue2;
	private String actualValueInput;
	//实际取值总和
	private String actualvalCount;

	private String orderId2;
	private Date acDate;
	
	private String owedSginTime;
	private String owedSginTime2;
	//更换项目经理日期
	private Date changeUpdateDate;
	//更换之前的项目经理ID
	private String oldEmployeeId;
	//欠缺的总签到次数
	private Integer allOwedSignTimes;
	//插入时间
	private Date insertCreateDate;
//    开始时间
    private Date startDate;
//    结束时间
    private Date endDate;
//    1基桩 2考勤
    private String type;

    private double pmStarSalaryAttendss;//实际考勤底薪

	public double getPmStarSalaryAttendss() {
		return pmStarSalaryAttendss;
	}

	public void setPmStarSalaryAttendss(double pmStarSalaryAttendss) {
		this.pmStarSalaryAttendss = pmStarSalaryAttendss;
	}

	public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getActualvalCount() {
        return actualvalCount;
    }

    public void setActualvalCount(String actualvalCount) {
        this.actualvalCount = actualvalCount;
    }

    public String getActualValueInput() {
        return actualValueInput;
    }

    public void setActualValueInput(String actualValueInput) {
        this.actualValueInput = actualValueInput;
    }
    public Date getInsertCreateDate() {
		return insertCreateDate;
	}
	
	public void setInsertCreateDate(Date insertCreateDate) {
		this.insertCreateDate = insertCreateDate;
	}
	
	public Integer getAllOwedSignTimes() {
		return allOwedSignTimes;
	}
	
	public void setAllOwedSignTimes(Integer allOwedSignTimes) {
		this.allOwedSignTimes = allOwedSignTimes;
	}
	
	public String getOldEmployeeId() {
		return oldEmployeeId;
	}
	
	public void setOldEmployeeId(String oldEmployeeId) {
		this.oldEmployeeId = oldEmployeeId;
	}
	
	public Date getChangeUpdateDate() {
		return changeUpdateDate;
	}
	
	public void setChangeUpdateDate(Date changeUpdateDate) {
		this.changeUpdateDate = changeUpdateDate;
	}
	
	public String getActualValue() {
		return actualValue;
	}
	
	public void setActualValue(String actualValue) {
		this.actualValue = actualValue;
	}
	
	public String getActualValue2() {
		return actualValue2;
	}
	
	public void setActualValue2(String actualValue2) {
		this.actualValue2 = actualValue2;
	}
	
	public Date getAcDate() {
		return acDate;
	}
	
	public void setAcDate(Date acDate) {
		this.acDate = acDate;
	}
	
	public BizPmAttendMonth() {
		super();
	}
	
	public BizPmAttendMonth(String id){
		super(id);
	}
	
	@Length(min=0, max=20, message="attend_month长度必须介于 0 和 20 之间")
	public String getAttendMonth() {
		return attendMonth;
	}
	
	public void setAttendMonth(String attendMonth) {
		this.attendMonth = attendMonth;
	}
	
	@Length(min=0, max=11, message="长度必须介于 0 和 11 之间")
	public String getPmEmployeeId() {
		return pmEmployeeId;
	}
	
	public void setPmEmployeeId(String pmEmployeeId) {
		this.pmEmployeeId = pmEmployeeId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAttendStartDate() {
		return attendStartDate;
	}
	
	public void setAttendStartDate(Date attendStartDate) {
		this.attendStartDate = attendStartDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAttendEndDate() {
		return attendEndDate;
	}
	
	public void setAttendEndDate(Date attendEndDate) {
		this.attendEndDate = attendEndDate;
	}
	
	@Length(min=0, max=11, message="长度必须介于 0 和 11 之间")
	public String getMustSignTimes() {
		return mustSignTimes;
	}
	
	public void setMustSignTimes(String mustSignTimes) {
		this.mustSignTimes = mustSignTimes;
	}
	
	@Length(min=0, max=11, message="长度必须介于 0 和 11 之间")
	public String getRealSignTimes() {
		return realSignTimes;
	}
	
	public void setRealSignTimes(String realSignTimes) {
		this.realSignTimes = realSignTimes;
	}
	
	@Length(min=0, max=11, message="长度必须介于 0 和 11 之间")
	public String getOwedSignTimes() {
		return owedSignTimes;
	}
	
	public void setOwedSignTimes(String owedSignTimes) {
		this.owedSignTimes = owedSignTimes;
	}
	
	public Double getAttendRate() {
		return attendRate;
	}
	
	public void setAttendRate(Double attendRate) {
		this.attendRate = attendRate;
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
	
	public String getItemManager() {
		return itemManager;
	}
	
	public void setItemManager(String itemManager) {
		this.itemManager = itemManager;
	}
	
	public String getDepartmentName() {
		return departmentName;
	}
	
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
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
	
	public String getStatusEmployeeId() {
		return statusEmployeeId;
	}
	
	public void setStatusEmployeeId(String statusEmployeeId) {
		this.statusEmployeeId = statusEmployeeId;
	}
	
	public String getStatusDescribe() {
		return statusDescribe;
	}
	
	public void setStatusDescribe(String statusDescribe) {
		this.statusDescribe = statusDescribe;
	}
	
	public String getItemManagerId() {
		return itemManagerId;
	}
	
	public void setItemManagerId(String itemManagerId) {
		this.itemManagerId = itemManagerId;
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
	
	public Integer getItemManagerStar() {
		return itemManagerStar;
	}
	
	public void setItemManagerStar(Integer itemManagerStar) {
		this.itemManagerStar = itemManagerStar;
	}
	
	public String getAttendMonthCode() {
		return attendMonthCode;
	}
	
	public void setAttendMonthCode(String attendMonthCode) {
		this.attendMonthCode = attendMonthCode;
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
	
	public String getPmAttendMonthId() {
		return pmAttendMonthId;
	}
	
	public void setPmAttendMonthId(String pmAttendMonthId) {
		this.pmAttendMonthId = pmAttendMonthId;
	}
	
	public String getNaturalDay() {
		return naturalDay;
	}
	
	public void setNaturalDay(String naturalDay) {
		this.naturalDay = naturalDay;
	}
	
	public Integer getSignCycleDaysBasicwork() {
		return signCycleDaysBasicwork;
	}
	
	public void setSignCycleDaysBasicwork(Integer signCycleDaysBasicwork) {
		this.signCycleDaysBasicwork = signCycleDaysBasicwork;
	}
	
	public Integer getSignCycleDaysComplete() {
		return signCycleDaysComplete;
	}
	
	public void setSignCycleDaysComplete(Integer signCycleDaysComplete) {
		this.signCycleDaysComplete = signCycleDaysComplete;
	}
	
	public Date getActualStartDate() {
		return actualStartDate;
	}
	
	public void setActualStartDate(Date actualStartDate) {
		this.actualStartDate = actualStartDate;
	}
	
	public String getOrderId() {
		return orderId;
	}
	
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	public String getMustSignTimes2() {
		return mustSignTimes2;
	}
	
	public void setMustSignTimes2(String mustSignTimes2) {
		this.mustSignTimes2 = mustSignTimes2;
	}
	
	public String getRealSignTimes2() {
		return realSignTimes2;
	}
	
	public void setRealSignTimes2(String realSignTimes2) {
		this.realSignTimes2 = realSignTimes2;
	}
	
	public String getOwedSginTime() {
		return owedSginTime;
	}
	
	public void setOwedSginTime(String owedSginTime) {
		this.owedSginTime = owedSginTime;
	}
	
	public String getOwedSginTime2() {
		return owedSginTime2;
	}
	
	public void setOwedSginTime2(String owedSginTime2) {
		this.owedSginTime2 = owedSginTime2;
	}
	
	public String getOrderId2() {
		return orderId2;
	}
	
	public void setOrderId2(String orderId2) {
		this.orderId2 = orderId2;
	}
	
}