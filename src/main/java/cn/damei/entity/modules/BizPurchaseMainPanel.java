package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;

import java.util.Date;
import java.util.List;

public class BizPurchaseMainPanel extends DataEntity2<BizPurchaseMainPanel> {

	/**
	 * 主材采购单 --开关面板
	 * 
	 * @author wang
	 * @version 2016-10-10
	 */
	private static final long serialVersionUID = 1L;

	private Integer id; // 采购单id
	private Integer orderId; // 订单id
	private Integer storeId; // 门店id
	private Date applyTime; // 申请时间
	private Date applyReceiveTime; // 期望送货时间
	private String orderNumber; // 订单
	private String purchaseCode; // 采购单编号
	private String purchaseType; // 采购单类型 辅材，地墙砖 ，开关面板
	private String receiverName; // 收货人
	private String receiverPhone; // 收货电话
	private Integer applyEmployee; // 申请人 id
	private String applyName; // 申请人姓名
	private String applyEmployeePhone; // 申请人电话
	// private String pictureUrl; //图片路径
	private String status; // 状态
	private String remarks; // 备注
	private String customerName; // 客户姓名
	private String customerPhone; // 客户电话
	private String customerAddress; // 地址
	private String communityName; // 小区名称
	private String buildNumber; // 几号楼
	private String buildUnit; // 几单元
	private String buildRoom; // 哪一室
	private String itemManager; // 项目经理
	private Integer itemManagerId; // 项目经理id
	private String itemManagerPhone;
	private Date beginApplyReceiveTime; // 开始 期望送货日期
	private Date endApplyReceiveTime; // 结束 期望送货日期
	private Date beginApplyTime; // 开始 申请时间
	private Date endApplyTime; // 结束 申请时间
	// private String managerPhone;
	private String projectMode;
	private Double overNumber; // 超额数量
	private String overReasonType; // 超额类型
	private String overReasonWords; // 超额描述
	private String contractArea; // 合同面积
	private Double purchaseCountTotal; // 采购商品总数
	private Double standardCountTotal; // 标配数量
	private String statusDescribe; // 废弃原因
	private String isScrap; // 订单是否作废 1为是 0为否

	private String purchaseApplyIndex; //第几次申请

	private List<Integer> purchaseIds = null; //采购单id集合

	public List<Integer> getPurchaseIds() {
		return purchaseIds;
	}

	public void setPurchaseIds(List<Integer> purchaseIds) {
		this.purchaseIds = purchaseIds;
	}

	public String getPurchaseApplyIndex() {
		return purchaseApplyIndex;
	}

	public void setPurchaseApplyIndex(String purchaseApplyIndex) {
		this.purchaseApplyIndex = purchaseApplyIndex;
	}

	public String getApplyEmployeePhone() {
		return applyEmployeePhone;
	}

	public void setApplyEmployeePhone(String applyEmployeePhone) {
		this.applyEmployeePhone = applyEmployeePhone;
	}

	public String getStatusDescribe() {
		return statusDescribe;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public void setStatusDescribe(String statusDescribe) {
		this.statusDescribe = statusDescribe;
	}

	public String getContractArea() {
		return contractArea;
	}

	public String getIsScrap() {
		return isScrap;
	}

	public void setIsScrap(String isScrap) {
		this.isScrap = isScrap;
	}

	public void setContractArea(String contractArea) {
		this.contractArea = contractArea;
	}

	public Double getPurchaseCountTotal() {
		return purchaseCountTotal;
	}

	public void setPurchaseCountTotal(Double purchaseCountTotal) {
		this.purchaseCountTotal = purchaseCountTotal;
	}

	public Double getStandardCountTotal() {
		return standardCountTotal;
	}

	public void setStandardCountTotal(Double standardCountTotal) {
		this.standardCountTotal = standardCountTotal;
	}

	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public Date getApplyReceiveTime() {
		return applyReceiveTime;
	}

	public void setApplyReceiveTime(Date applyReceiveTime) {
		this.applyReceiveTime = applyReceiveTime;
	}

	public String getPurchaseCode() {
		return purchaseCode;
	}

	public void setPurchaseCode(String purchaseCode) {
		this.purchaseCode = purchaseCode;
	}

	public String getPurchaseType() {
		return purchaseType;
	}

	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverPhone() {
		return receiverPhone;
	}

	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}

	public Integer getApplyEmployee() {
		return applyEmployee;
	}

	public void setApplyEmployee(Integer applyEmployee) {
		this.applyEmployee = applyEmployee;
	}

	public String getApplyName() {
		return applyName;
	}

	public void setApplyName(String applyName) {
		this.applyName = applyName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getItemManager() {
		return itemManager;
	}

	public void setItemManager(String itemManager) {
		this.itemManager = itemManager;
	}

	public Integer getItemManagerId() {
		return itemManagerId;
	}

	public void setItemManagerId(Integer itemManagerId) {
		this.itemManagerId = itemManagerId;
	}

	public Date getBeginApplyReceiveTime() {
		return beginApplyReceiveTime;
	}

	public void setBeginApplyReceiveTime(Date beginApplyReceiveTime) {
		this.beginApplyReceiveTime = beginApplyReceiveTime;
	}

	public Date getEndApplyReceiveTime() {
		return endApplyReceiveTime;
	}

	public void setEndApplyReceiveTime(Date endApplyReceiveTime) {
		this.endApplyReceiveTime = endApplyReceiveTime;
	}

	public Date getBeginApplyTime() {
		return beginApplyTime;
	}

	public void setBeginApplyTime(Date beginApplyTime) {
		this.beginApplyTime = beginApplyTime;
	}

	public Date getEndApplyTime() {
		return endApplyTime;
	}

	public void setEndApplyTime(Date endApplyTime) {
		this.endApplyTime = endApplyTime;
	}

	public Double getOverNumber() {
		return overNumber;
	}

	public void setOverNumber(Double overNumber) {
		this.overNumber = overNumber;
	}

	public String getOverReasonType() {
		return overReasonType;
	}

	public void setOverReasonType(String overReasonType) {
		this.overReasonType = overReasonType;
	}

	public String getOverReasonWords() {
		return overReasonWords;
	}

	public void setOverReasonWords(String overReasonWords) {
		this.overReasonWords = overReasonWords;
	}

	public String getItemManagerPhone() {
		return itemManagerPhone;
	}

	public void setItemManagerPhone(String itemManagerPhone) {
		this.itemManagerPhone = itemManagerPhone;
	}
}
