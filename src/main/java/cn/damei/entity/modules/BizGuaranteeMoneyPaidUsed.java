package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;


public class BizGuaranteeMoneyPaidUsed extends DataEntity2<BizGuaranteeMoneyPaidUsed> {

	private static final long serialVersionUID = 1L;

	private String orderId;

	private String guaranteeMoneyType;

	private String usedEmployeeType;

	private Integer usedEmployeeId;
	
	private String usedEmpName;
	
	private String usedEmpPhone;

	private String guaranteeMoneyFor;

	private Double guaranteeMoneyAmount;

	private Double guaranteeMoneyTypeAmountTotal;

	private Double guaranteeMoneyBalance;
	
	private Date guaranteeMoneyDateTime;

	private String guaranteeMoneyReason;

	private String storeId;

	private String projectMode;

	private Integer engineDepartId;
	
	private String communityName;
	
	private String buildNumber;
	
	private String buildUnit;
	
	private String buildRoom;
	
	private String worktype;
	
	private Date start;
	
	private Date end;
	
	private String customerName;
	
	private String customerPhone;
	
	private String customerAddress;
	
	private String packageName;
	
	
	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getWorktype() {
		return worktype;
	}

	public void setWorktype(String worktype) {
		this.worktype = worktype;
	}

	public String getUsedEmpName() {
		return usedEmpName;
	}

	public void setUsedEmpName(String usedEmpName) {
		this.usedEmpName = usedEmpName;
	}

	public String getUsedEmpPhone() {
		return usedEmpPhone;
	}

	public void setUsedEmpPhone(String usedEmpPhone) {
		this.usedEmpPhone = usedEmpPhone;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getGuaranteeMoneyType() {
		return guaranteeMoneyType;
	}

	public void setGuaranteeMoneyType(String guaranteeMoneyType) {
		this.guaranteeMoneyType = guaranteeMoneyType;
	}

	

	public String getUsedEmployeeType() {
		return usedEmployeeType;
	}

	public void setUsedEmployeeType(String usedEmployeeType) {
		this.usedEmployeeType = usedEmployeeType;
	}

	public Integer getUsedEmployeeId() {
		return usedEmployeeId;
	}

	public void setUsedEmployeeId(Integer usedEmployeeId) {
		this.usedEmployeeId = usedEmployeeId;
	}

	public String getGuaranteeMoneyFor() {
		return guaranteeMoneyFor;
	}

	public void setGuaranteeMoneyFor(String guaranteeMoneyFor) {
		this.guaranteeMoneyFor = guaranteeMoneyFor;
	}

	public Double getGuaranteeMoneyAmount() {
		return guaranteeMoneyAmount;
	}

	public void setGuaranteeMoneyAmount(Double guaranteeMoneyAmount) {
		this.guaranteeMoneyAmount = guaranteeMoneyAmount;
	}

	public Double getGuaranteeMoneyTypeAmountTotal() {
		return guaranteeMoneyTypeAmountTotal;
	}

	public void setGuaranteeMoneyTypeAmountTotal(Double guaranteeMoneyTypeAmountTotal) {
		this.guaranteeMoneyTypeAmountTotal = guaranteeMoneyTypeAmountTotal;
	}

	public Double getGuaranteeMoneyBalance() {
		return guaranteeMoneyBalance;
	}

	public void setGuaranteeMoneyBalance(Double guaranteeMoneyBalance) {
		this.guaranteeMoneyBalance = guaranteeMoneyBalance;
	}

	public Date getGuaranteeMoneyDateTime() {
		return guaranteeMoneyDateTime;
	}

	public void setGuaranteeMoneyDateTime(Date guaranteeMoneyDateTime) {
		this.guaranteeMoneyDateTime = guaranteeMoneyDateTime;
	}

	public String getGuaranteeMoneyReason() {
		return guaranteeMoneyReason;
	}

	public void setGuaranteeMoneyReason(String guaranteeMoneyReason) {
		this.guaranteeMoneyReason = guaranteeMoneyReason;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public Integer getEngineDepartId() {
		return engineDepartId;
	}

	public void setEngineDepartId(Integer engineDepartId) {
		this.engineDepartId = engineDepartId;
	}

	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}

	public String getCommunityName() {
		return communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}

	public String getBuildNumber() {
		return buildNumber;
	}

	public void setBuildNumber(String buildNumber) {
		this.buildNumber = buildNumber;
	}

	public String getBuildUnit() {
		return buildUnit;
	}

	public void setBuildUnit(String buildUnit) {
		this.buildUnit = buildUnit;
	}

	public String getBuildRoom() {
		return buildRoom;
	}

	public void setBuildRoom(String buildRoom) {
		this.buildRoom = buildRoom;
	}

	
	
}
