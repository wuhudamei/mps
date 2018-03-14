package cn.damei.entity.mobile.Manager;

import java.io.Serializable;
import java.util.Date;

/**
 * 复检单VO
 * @author 93173
 *
 */
public class ApplyRecheckEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private  String orderCustomerName;//顾客名字
	private String orderCustomerAddress;//顾客地址
	private Date actualContractStartDate;//实际开工日期
	private Integer contractDays;////工期
	private Integer recheckId;//复检单id
	private String recheckType;//复检单类型(约检, 抽检)
	private String recheckStatus;//复检单当前状态
	private Integer relatedBillId;//关联的(约检)质检单
	private Integer managerId;//订单项目经理id
	private Integer checkItemId;//检查项id
	private String isPassed;//是否合格
	private String checkItemName;//检查项名称
	private String changeWay;//检查方式, 线上还是线下
	private Date hopeApplyDate; //期望验收日期
	
	public Date getHopeApplyDate() {
		return hopeApplyDate;
	}
	public void setHopeApplyDate(Date hopeApplyDate) {
		this.hopeApplyDate = hopeApplyDate;
	}
	
	public Integer getCheckItemId() {
		return checkItemId;
	}
	public void setCheckItemId(Integer checkItemId) {
		this.checkItemId = checkItemId;
	}
	public String getIsPassed() {
		return isPassed;
	}
	public void setIsPassed(String isPassed) {
		this.isPassed = isPassed;
	}
	public String getCheckItemName() {
		return checkItemName;
	}
	public void setCheckItemName(String checkItemName) {
		this.checkItemName = checkItemName;
	}
	public String getChangeWay() {
		return changeWay;
	}
	public void setChangeWay(String changeWay) {
		this.changeWay = changeWay;
	}

	
	public Integer getManagerId() {
		return managerId;
	}
	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}
	public Integer getRelatedBillId() {
		return relatedBillId;
	}
	public void setRelatedBillId(Integer relatedBillId) {
		this.relatedBillId = relatedBillId;
	}
	public String getOrderCustomerName() {
		return orderCustomerName;
	}
	public void setOrderCustomerName(String orderCustomerName) {
		this.orderCustomerName = orderCustomerName;
	}
	public String getOrderCustomerAddress() {
		return orderCustomerAddress;
	}
	public void setOrderCustomerAddress(String orderCustomerAddress) {
		this.orderCustomerAddress = orderCustomerAddress;
	}
	public Date getActualContractStartDate() {
		return actualContractStartDate;
	}
	public void setActualContractStartDate(Date actualContractStartDate) {
		this.actualContractStartDate = actualContractStartDate;
	}
	public Integer getContractDays() {
		return contractDays;
	}
	public void setContractDays(Integer contractDays) {
		this.contractDays = contractDays;
	}
	public Integer getRecheckId() {
		return recheckId;
	}
	public void setRecheckId(Integer recheckId) {
		this.recheckId = recheckId;
	}
	public String getRecheckType() {
		return recheckType;
	}
	public void setRecheckType(String recheckType) {
		this.recheckType = recheckType;
	}
	public String getRecheckStatus() {
		return recheckStatus;
	}
	public void setRecheckStatus(String recheckStatus) {
		this.recheckStatus = recheckStatus;
	}
	

}
