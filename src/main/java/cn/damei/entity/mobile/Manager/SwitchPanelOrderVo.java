package cn.damei.entity.mobile.Manager;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

/**
 * @author 梅浩 meihao@zzhyun.cn:
 * @version 创建时间：2016年9月28日 上午10:10:41 类说明
 */

public class SwitchPanelOrderVo extends DataEntity2<SwitchPanelOrderVo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 订单id主键在 爷爷类中

	private Integer itemManagerId; // 关键的项目经理id
	private String customerName;// 客户姓名
	private String communityName;// 小区名称
	private String buildNumber;// 楼号
	private String buildUnit;// 单元号
	private String buildRoom;// 门牌号 哪一室
	private Date contractStartDate;// 合同开工日期
	private Date actualStartDate;// 实际开工日期
	private Integer contractTime;// 合同工期
	private String orderStatus;// 订单状态
	private String isScrap; // 订单是否作废 1为是 0为否

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getIsScrap() {
		return isScrap;
	}

	public void setIsScrap(String isScrap) {
		this.isScrap = isScrap;
	}

	public Integer getContractTime() {
		return contractTime;
	}

	public void setContractTime(Integer contractTime) {

		this.contractTime = contractTime;
	}

	public Integer getItemManagerId() {
		return itemManagerId;
	}

	public void setItemManagerId(Integer itemManagerId) {
		this.itemManagerId = itemManagerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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

	public Date getContractStartDate() {
		return contractStartDate;
	}

	public void setContractStartDate(Date contractStartDate) {
		this.contractStartDate = contractStartDate;
	}

	public Date getActualStartDate() {
		return actualStartDate;
	}

	public void setActualStartDate(Date actualStartDate) {
		this.actualStartDate = actualStartDate;
	} // 实际开工日期

}
