package cn.damei.entity.mobile.Manager;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

/** 
* @author 梅浩   meihao@zzhyun.cn: 
* @version 创建时间：2016年9月29日 下午8:42:43 
* 采购单表,  有 主材和辅材     我是开关面板 和辅材
*/

public class PurchaseVo    extends DataEntity2<PurchaseVo> {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Integer id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	private Integer orderId;//订单id
	private String purchaseCode;//采购单编码
	private String remarks; //收货人姓名加上手机号
	private Date applyTime; //申请时间   当前时间
	private Integer applyPerson;//申请人
	
	private Double AuxiliaryAllMoney; //辅料总价
	private Date hopeForTime; //期望送货日期
	private String  status;  //采购单状态    10-项目经理已提交辅料申请
	private String purchaseType; //采购单类别   开关面板 :2
	private Integer  totalCount; //总数
	//12-28加入超定额
	
	private Integer overCount;//超出多少
	private String  overReasonType;//超出类型
	private String overWords;//超出原因说明
	private Double purchaseCountTotal; //采购商品总数
	private String statusDescribe;//废弃原因
	private String statusId; //状态id
	
	
	
	public String getStatusId() {
		return statusId;
	}
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
	public String getStatusDescribe() {
		return statusDescribe;
	}
	public void setStatusDescribe(String statusDescribe) {
		this.statusDescribe = statusDescribe;
	}
	public Double getPurchaseCountTotal() {
		return purchaseCountTotal;
	}
	public void setPurchaseCountTotal(Double purchaseCountTotal) {
		this.purchaseCountTotal = purchaseCountTotal;
	}
	public Integer getOverCount() {
		return overCount;
	}
	public void setOverCount(Integer overCount) {
		this.overCount = overCount;
	}
	public String getOverReasonType() {
		return overReasonType;
	}
	public void setOverReasonType(String overReasonType) {
		this.overReasonType = overReasonType;
	}
	public String getOverWords() {
		return overWords;
	}
	public void setOverWords(String overWords) {
		this.overWords = overWords;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	private Date createDate;
	private String delFlag;
	
	
	
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	public String getPurchaseType() {
		return purchaseType;
	}
	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
	}
	private String customerName;
	private String communityName;
	private String buildNumber;
	private String buildUnit;
	private String buildRoom;
	
	
	
	
	
	
	
	
	
	
	
	
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
	
	public Integer getApplyPerson() {
		return applyPerson;
	}
	public void setApplyPerson(Integer applyPerson) {
		this.applyPerson = applyPerson;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getPurchaseCode() {
		return purchaseCode;
	}
	public void setPurchaseCode(String purchaseCode) {
		this.purchaseCode = purchaseCode;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Date getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	public Double getAuxiliaryAllMoney() {
		return AuxiliaryAllMoney;
	}
	public void setAuxiliaryAllMoney(Double auxiliaryAllMoney) {
		AuxiliaryAllMoney = auxiliaryAllMoney;
	}
	public Date getHopeForTime() {
		return hopeForTime;
	}
	public void setHopeForTime(Date hopeForTime) {
		this.hopeForTime = hopeForTime;
	}
	

}
