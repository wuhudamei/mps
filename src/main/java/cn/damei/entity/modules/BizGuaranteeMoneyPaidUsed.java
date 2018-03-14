package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

/**
 * 质保金上缴使用实体类
 * 
 * @author hyh
 *
 */
public class BizGuaranteeMoneyPaidUsed extends DataEntity2<BizGuaranteeMoneyPaidUsed> {

	private static final long serialVersionUID = 1L;

	private String orderId;// 订单ID

	private String guaranteeMoneyType;// 质保金类型 1:线下上缴质保金 2：使用质保金

	private String usedEmployeeType;// 使用质保金人员类型 1：项目经理 2：工人

	private Integer usedEmployeeId;// 使用人Id
	
	private String usedEmpName;//使用人姓名
	
	private String usedEmpPhone;//使用人手机号

	private String guaranteeMoneyFor;// 质保金用途 1：开裂 2：漏水

	private Double guaranteeMoneyAmount;// 本次质保金金额

	private Double guaranteeMoneyTypeAmountTotal;// 该类型质保金的总金额

	private Double guaranteeMoneyBalance;// 质保金余额
	
	private Date guaranteeMoneyDateTime;// 质保金日期

	private String guaranteeMoneyReason;// 质保金原因

	private String storeId;// 门店Id

	private String projectMode;// 工程模式

	private Integer engineDepartId;// 区域
	
	private String communityName;//小区
	
	private String buildNumber;//楼号
	
	private String buildUnit;//单元门
	
	private String buildRoom;//门牌号
	
	private String worktype;//工种
	
	private Date start;
	
	private Date end;
	
	private String customerName;//客户姓名
	
	private String customerPhone;//客户手机号
	
	private String customerAddress;//客户地址
	
	private String packageName;//任务包名称
	
	
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
