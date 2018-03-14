package cn.damei.entity.mobile.Inspector;

import cn.damei.common.persistence.DataEntity2;

/**
 * 评价项目经理详情
 * 
 * @author hyh
 *
 */
public class BizEvalManagerDetail extends DataEntity2<BizEvalManagerDetail> {
	private static final long serialVersionUID = 1L;
	private String customerName; // 客户姓名
	private String customerPhone;// 客户手机号
	private String communityName;//小区
	private String buildNumber; // 几号楼
	private String buildUnit; // 几单元
	private String buildRoom; // 哪一室
	private String itemManager; // 项目经理
	private String itemPhone;// 项目经理手机号
	private String checkNodeName;// 约检节点名称
	private Integer relatedBusinessId;
	private Integer orderId;
	
	

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getRelatedBusinessId() {
		return relatedBusinessId;
	}

	public void setRelatedBusinessId(Integer relatedBusinessId) {
		this.relatedBusinessId = relatedBusinessId;
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

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
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

	public String getItemManager() {
		return itemManager;
	}

	public void setItemManager(String itemManager) {
		this.itemManager = itemManager;
	}

	public String getItemPhone() {
		return itemPhone;
	}

	public void setItemPhone(String itemPhone) {
		this.itemPhone = itemPhone;
	}

	public String getCheckNodeName() {
		return checkNodeName;
	}

	public void setCheckNodeName(String checkNodeName) {
		this.checkNodeName = checkNodeName;
	}

}
