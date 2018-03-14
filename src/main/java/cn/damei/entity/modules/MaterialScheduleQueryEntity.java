package cn.damei.entity.modules;

import java.util.Date;
import java.util.List;

import cn.damei.common.persistence.DataEntity2;

public class MaterialScheduleQueryEntity extends DataEntity2<MaterialScheduleQueryEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer storeId;	//门店
	private String orderNumber;	//订单编号
	private String communityName;//小区
	private String buildNumber;//楼
	private String buildUnit;	//单元
	private String buildRoom;	//室
	private String customerName;	//客户姓名
	private String itemManager;	//项目经理
	private String purchaseType;	//材料类型
	private String purchaseCode;	//采购单编号
	private String status;	//采购单状态
	private Date applyTime;		//项目经理申请日期
	private Date beginApplyTime;	//开始   项目经理申请日期
	private Date endAapplyTime;		//结束   项目经理申请日期
	private Date transferDate;		//转单日期
	private Date beginTransferDate;	//开始 转单日期
	private Date endTransferDate;		//结束  转单日期
	private Date completionDate;		//收货完成日期
	private Date beginCompletionDate;	//开始   收货完成日期
	private Date endCompletionDate;		//结束   收货完成日期
	private Integer  acceptMaterialCount;//收货次数 
	private Integer purchaseId;// 采购单id
	private List<String> purchaseStatusList;//状态集合
	private Date recieveGoodsDate;//收货日期
	private String projectMode;//工程模式
	
	public String getProjectMode() {
		return projectMode;
	}
	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}
	public Date getRecieveGoodsDate() {
		return recieveGoodsDate;
	}
	public void setRecieveGoodsDate(Date recieveGoodsDate) {
		this.recieveGoodsDate = recieveGoodsDate;
	}
	public List<String> getPurchaseStatusList() {
		return purchaseStatusList;
	}
	public void setPurchaseStatusList(List<String> purchaseStatusList) {
		this.purchaseStatusList = purchaseStatusList;
	}
	public Integer getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(Integer purchaseId) {
		this.purchaseId = purchaseId;
	}
	public Integer getAcceptMaterialCount() {
		return acceptMaterialCount;
	}
	public void setAcceptMaterialCount(Integer acceptMaterialCount) {
		this.acceptMaterialCount = acceptMaterialCount;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
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
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getItemManager() {
		return itemManager;
	}
	public void setItemManager(String itemManager) {
		this.itemManager = itemManager;
	}
	public String getPurchaseType() {
		return purchaseType;
	}
	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
	}
	public String getPurchaseCode() {
		return purchaseCode;
	}
	public void setPurchaseCode(String purchaseCode) {
		this.purchaseCode = purchaseCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	public Date getBeginApplyTime() {
		return beginApplyTime;
	}
	public void setBeginApplyTime(Date beginApplyTime) {
		this.beginApplyTime = beginApplyTime;
	}
	public Date getEndAapplyTime() {
		return endAapplyTime;
	}
	public void setEndAapplyTime(Date endAapplyTime) {
		this.endAapplyTime = endAapplyTime;
	}
	public Date getTransferDate() {
		return transferDate;
	}
	public void setTransferDate(Date transferDate) {
		this.transferDate = transferDate;
	}
	public Date getBeginTransferDate() {
		return beginTransferDate;
	}
	public void setBeginTransferDate(Date beginTransferDate) {
		this.beginTransferDate = beginTransferDate;
	}
	public Date getEndTransferDate() {
		return endTransferDate;
	}
	public void setEndTransferDate(Date endTransferDate) {
		this.endTransferDate = endTransferDate;
	}
	public Date getCompletionDate() {
		return completionDate;
	}
	public void setCompletionDate(Date completionDate) {
		this.completionDate = completionDate;
	}
	public Date getBeginCompletionDate() {
		return beginCompletionDate;
	}
	public void setBeginCompletionDate(Date beginCompletionDate) {
		this.beginCompletionDate = beginCompletionDate;
	}
	public Date getEndCompletionDate() {
		return endCompletionDate;
	}
	public void setEndCompletionDate(Date endCompletionDate) {
		this.endCompletionDate = endCompletionDate;
	}
	
}
