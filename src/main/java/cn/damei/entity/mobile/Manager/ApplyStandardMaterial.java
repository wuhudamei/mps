package cn.damei.entity.mobile.Manager;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import cn.damei.common.persistence.DataEntity2;

public class ApplyStandardMaterial extends DataEntity2<ApplyStandardMaterial> {
	private static final long serialVersionUID = 1L;

	private String communityName;// 小区的名字
	private String customerName;// 客户的名字
	private String buildNumber;// 几号楼
	private String buildUnit;// 几单元
	private String buildRoom;// 那一室
	private Date contractStartDate;// 合同开工日期
	private Date actualStartDate;// 实际开工日期
	private Integer contractTime;// 合同工期
	private String orderStatusNumber;// 订单状态码
	private String orderStatusDescription;// 订单状态码描述
	private String isBasicworkCompleted;// 是否基礎建設完成
	private String isScrap; // 订单是否作废 1为是 0为否

	public String getIsBasicworkCompleted() {
		return isBasicworkCompleted;
	}

	public void setIsBasicworkCompleted(String isBasicworkCompleted) {
		this.isBasicworkCompleted = isBasicworkCompleted;
	}

	public ApplyStandardMaterial() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getIsScrap() {
		return isScrap;
	}

	public void setIsScrap(String isScrap) {
		this.isScrap = isScrap;
	}

	public ApplyStandardMaterial(Integer id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	public String getCommunityName() {
		return communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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

	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getContractStartDate() {
		return contractStartDate;
	}

	public void setContractStartDate(Date contractStartDate) {
		this.contractStartDate = contractStartDate;
	}

	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getActualStartDate() {
		return actualStartDate;
	}

	public void setActualStartDate(Date actualStartDate) {
		this.actualStartDate = actualStartDate;
	}

	public Integer getContractTime() {
		return contractTime;
	}

	public void setContractTime(Integer contractTime) {
		this.contractTime = contractTime;
	}

	public String getOrderStatusNumber() {
		return orderStatusNumber;
	}

	public void setOrderStatusNumber(String orderStatusNumber) {
		this.orderStatusNumber = orderStatusNumber;
	}

	public String getOrderStatusDescription() {
		return orderStatusDescription;
	}

	public void setOrderStatusDescription(String orderStatusDescription) {
		this.orderStatusDescription = orderStatusDescription;
	}

}
