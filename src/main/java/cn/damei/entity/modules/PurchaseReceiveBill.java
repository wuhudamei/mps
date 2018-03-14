/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import java.util.Date;
import cn.damei.common.persistence.DataEntity2;

/**
 * 收货单Entity
 * @author www
 * @version 2016-11-10
 */
public class PurchaseReceiveBill extends DataEntity2<PurchaseReceiveBill> {
	
	private static final long serialVersionUID = 1L;
	private Integer storeId;
	private String purchaseCode;		// 采购单编码 -- '
	private String purchaseType;		// 采购单类型 -- '辅材，主材
	private Integer purchaseId;		// 采购单id
	private String purchaseReceiveBillCode;		// 收货单编号
	private Date receiveDate;		// 收货日期
	private Integer receiveEmployeeId;		// 收货人员工id -- '
	private String receiveName;		// 收货人  
	private Date beginReceiveDate;		// 开始 收货日期
	private Date endReceiveDate;		// 结束 收货日期
	private String customerName;		// 客户姓名
	private String customerAddress;    //地址
	private String communityName;		// 小区名称
	private String buildNumber;		// 几号楼
	private String buildUnit;		// 几单元
	private String buildRoom;		// 哪一室 
	private String projectMode;	//工程模式
	private String orderNumber; //订单编号
	private String purchaseTypeName; //采购单类型名称
	
	
	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}

	public PurchaseReceiveBill() {
		super();
	}

	public PurchaseReceiveBill(Integer id){
		super(id);
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
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

	public Integer getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(Integer purchaseId) {
		this.purchaseId = purchaseId;
	}

	public String getPurchaseReceiveBillCode() {
		return purchaseReceiveBillCode;
	}

	public void setPurchaseReceiveBillCode(String purchaseReceiveBillCode) {
		this.purchaseReceiveBillCode = purchaseReceiveBillCode;
	}

	public Date getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}

	public Integer getReceiveEmployeeId() {
		return receiveEmployeeId;
	}

	public void setReceiveEmployeeId(Integer receiveEmployeeId) {
		this.receiveEmployeeId = receiveEmployeeId;
	}

	public String getReceiveName() {
		return receiveName;
	}

	public void setReceiveName(String receiverName) {
		this.receiveName = receiverName;
	}

	public Date getBeginReceiveDate() {
		return beginReceiveDate;
	}

	public void setBeginReceiveDate(Date beginReceiveDate) {
		this.beginReceiveDate = beginReceiveDate;
	}

	public Date getEndReceiveDate() {
		return endReceiveDate;
	}

	public void setEndReceiveDate(Date endReceiveDate) {
		this.endReceiveDate = endReceiveDate;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
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

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getPurchaseTypeName() {
		return purchaseTypeName;
	}

	public void setPurchaseTypeName(String purchaseTypeName) {
		this.purchaseTypeName = purchaseTypeName;
	}
	
	

}