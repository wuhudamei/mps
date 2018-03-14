package cn.damei.entity.mobile.Inspector;

import java.io.Serializable;
import java.util.Date;

public class InspectorBalanceEntity  implements Serializable{


	private String  balanceRole;
	
	public String getBalanceRole() {
		return balanceRole;
	}
	public void setBalanceRole(String balanceRole) {
		this.balanceRole = balanceRole;
	}
	private static final long serialVersionUID = 1L;
	public Integer getBalanceId() {
		return balanceId;
	}
	public void setBalanceId(Integer balanceId) {
		this.balanceId = balanceId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getProjectMode() {
		return projectMode;
	}
	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}
	public Date getBalanceDate() {
		return balanceDate;
	}
	public void setBalanceDate(Date balanceDate) {
		this.balanceDate = balanceDate;
	}
	public Double getMidBalanceMoney() {
		return midBalanceMoney;
	}
	public void setMidBalanceMoney(Double midBalanceMoney) {
		this.midBalanceMoney = midBalanceMoney;
	}
	public Double getCompleteBalanceMoney() {
		return completeBalanceMoney;
	}
	public void setCompleteBalanceMoney(Double completeBalanceMoney) {
		this.completeBalanceMoney = completeBalanceMoney;
	}
	public Double getFreePayMoney() {
		return freePayMoney;
	}
	public void setFreePayMoney(Double freePayMoney) {
		this.freePayMoney = freePayMoney;
	}
	public Double getMaterialsStandardAmount() {
		return materialsStandardAmount;
	}
	public void setMaterialsStandardAmount(Double materialsStandardAmount) {
		this.materialsStandardAmount = materialsStandardAmount;
	}
	public Double getMidFineMoney() {
		return midFineMoney;
	}
	public void setMidFineMoney(Double midFineMoney) {
		this.midFineMoney = midFineMoney;
	}
	public Double getComleteFineMoney() {
		return comleteFineMoney;
	}
	public void setComleteFineMoney(Double comleteFineMoney) {
		this.comleteFineMoney = comleteFineMoney;
	}
	public Double getGuaranteMoney() {
		return guaranteMoney;
	}
	public void setGuaranteMoney(Double guaranteMoney) {
		this.guaranteMoney = guaranteMoney;
	}
	public Double getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Integer getCreateBy() {
		return createBy;
	}
	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}
	public Integer getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	private Integer  balanceId;
	private String balanceCode;
	public String getBalanceCode() {
		return balanceCode;
	}
	public void setBalanceCode(String balanceCode) {
		this.balanceCode = balanceCode;
	}
	private Integer orderId;
	private Integer inspectId;
	public Integer getInspectId() {
		return inspectId;
	}
	public void setInspectId(Integer inspectId) {
		this.inspectId = inspectId;
	}
	private String projectMode;
	private Date balanceDate;
	private Double midBalanceMoney;
	private Double completeBalanceMoney;
	private Double midDistanceFee;
	private Double completeDistanceFee;
	
	
	
	public Double getMidDistanceFee() {
		return midDistanceFee;
	}
	public void setMidDistanceFee(Double midDistanceFee) {
		this.midDistanceFee = midDistanceFee;
	}
	public Double getCompleteDistanceFee() {
		return completeDistanceFee;
	}
	public void setCompleteDistanceFee(Double completeDistanceFee) {
		this.completeDistanceFee = completeDistanceFee;
	}
	private Double freePayMoney;
	private Double materialsStandardAmount;
	private Double midFineMoney;
	private Double comleteFineMoney;
	private Double guaranteMoney;
	private Double totalMoney;
	private String remarks;
	private Integer createBy;
	private Integer updateBy;
	private Date createDate;
	private Date updateDate;
	private String delFlag;
	
	
	
}
