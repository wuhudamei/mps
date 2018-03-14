
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.damei.common.persistence.DataEntity2;


public class BizOrderTaskpackagePaymentSummary extends DataEntity2<BizOrderTaskpackagePaymentSummary> {
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer storeId;
	private String projectMode;
	private String orderTaskpackagePaymentSummaryCode;
	private Integer orderTaskpackagePaymentCount;
	private Date generatedDatetime;
	private Integer applyEmployeeId;
	private String applyEmployeeName;
	private String status;
	private Integer examineEmployeeId;
	private String examineEmployeeName;
	private Date examineDatetime;
	private String examineWords;
	private String cancleReason;
	private Date beginGeneratedDatetime;
	private Date endGeneratedDatetime;

	private Double amount;
	private String realName;
	private String phone;
	private List<String> summaryStatus;
	private String orderTaskpackagePaymentCode;
	private String paymentStatus;
	private Date paymentGeneratedDatetime;
	private Double paymentAmount;
	private Integer employeeId;
	private String bankCardNo;

	private Integer enginDepartId;
	private String departmentName;
	private List<Integer> enginDepartIds = new ArrayList<Integer>();
	public String getBankCardNo() {
		return bankCardNo;
	}

	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}

	public BizOrderTaskpackagePaymentSummary() {
		super();
	}

	public BizOrderTaskpackagePaymentSummary(Integer id){
		super(id);
	}

	
	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	@Length(min=0, max=10, message="订单任务包付款汇总单编号 -- '长度必须介于 0 和 10 之间")
	public String getOrderTaskpackagePaymentSummaryCode() {
		return orderTaskpackagePaymentSummaryCode;
	}

	public void setOrderTaskpackagePaymentSummaryCode(String orderTaskpackagePaymentSummaryCode) {
		this.orderTaskpackagePaymentSummaryCode = orderTaskpackagePaymentSummaryCode;
	}
	
	public Integer getOrderTaskpackagePaymentCount() {
		return orderTaskpackagePaymentCount;
	}

	public void setOrderTaskpackagePaymentCount(Integer orderTaskpackagePaymentCount) {
		this.orderTaskpackagePaymentCount = orderTaskpackagePaymentCount;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getGeneratedDatetime() {
		return generatedDatetime;
	}

	public void setGeneratedDatetime(Date generatedDatetime) {
		this.generatedDatetime = generatedDatetime;
	}
	
	public Integer getApplyEmployeeId() {
		return applyEmployeeId;
	}

	public void setApplyEmployeeId(Integer applyEmployeeId) {
		this.applyEmployeeId = applyEmployeeId;
	}
	
	@Length(min=0, max=10, message="状态 -- '长度必须介于 0 和 10 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public Integer getExamineEmployeeId() {
		return examineEmployeeId;
	}

	public void setExamineEmployeeId(Integer examineEmployeeId) {
		this.examineEmployeeId = examineEmployeeId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getExamineDatetime() {
		return examineDatetime;
	}

	public void setExamineDatetime(Date examineDatetime) {
		this.examineDatetime = examineDatetime;
	}
	
	@Length(min=0, max=255, message="审核意见 -- '长度必须介于 0 和 255 之间")
	public String getExamineWords() {
		return examineWords;
	}

	public void setExamineWords(String examineWords) {
		this.examineWords = examineWords;
	}
	
	@Length(min=0, max=255, message="撤回原因 -- '长度必须介于 0 和 255 之间")
	public String getCancleReason() {
		return cancleReason;
	}

	public void setCancleReason(String cancleReason) {
		this.cancleReason = cancleReason;
	}
	
	public Date getBeginGeneratedDatetime() {
		return beginGeneratedDatetime;
	}

	public void setBeginGeneratedDatetime(Date beginGeneratedDatetime) {
		this.beginGeneratedDatetime = beginGeneratedDatetime;
	}
	
	public Date getEndGeneratedDatetime() {
		return endGeneratedDatetime;
	}

	public void setEndGeneratedDatetime(Date endGeneratedDatetime) {
		this.endGeneratedDatetime = endGeneratedDatetime;
	}

	public String getApplyEmployeeName() {
		return applyEmployeeName;
	}

	public void setApplyEmployeeName(String applyEmployeeName) {
		this.applyEmployeeName = applyEmployeeName;
	}

	public String getExamineEmployeeName() {
		return examineEmployeeName;
	}

	public void setExamineEmployeeName(String examineEmployeeName) {
		this.examineEmployeeName = examineEmployeeName;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<String> getSummaryStatus() {
		return summaryStatus;
	}

	public void setSummaryStatus(List<String> summaryStatus) {
		this.summaryStatus = summaryStatus;
	}

	public String getOrderTaskpackagePaymentCode() {
		return orderTaskpackagePaymentCode;
	}

	public void setOrderTaskpackagePaymentCode(String orderTaskpackagePaymentCode) {
		this.orderTaskpackagePaymentCode = orderTaskpackagePaymentCode;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public Date getPaymentGeneratedDatetime() {
		return paymentGeneratedDatetime;
	}

	public void setPaymentGeneratedDatetime(Date paymentGeneratedDatetime) {
		this.paymentGeneratedDatetime = paymentGeneratedDatetime;
	}

	public Double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(Double paymentAmount) {
		this.paymentAmount = paymentAmount;
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

	public List<Integer> getEnginDepartIds() {
		return enginDepartIds;
	}

	public void setEnginDepartIds(List<Integer> enginDepartIds) {
		this.enginDepartIds = enginDepartIds;
	}
	
}