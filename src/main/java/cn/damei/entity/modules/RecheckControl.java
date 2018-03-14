/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity;

/**
 * 复检单监控表Entity
 * @author wyb
 * @version 2016-10-31
 */
public class RecheckControl extends DataEntity<RecheckControl> {
	
	private static final long serialVersionUID = 1L;
	
	private String storeId ; //门店
	private String orderNumber;		// 订单编号

	private String communityName;		// 小区名称
	private String buildNumber;		// 几号楼
	private String buildUnit;		// 几单元
	private String buildRoom;		// 哪一室
	private String customerName;		// 客户姓名
	private String itemManager;		// 项目经理
	
	private String qcBillCode;		// 质检报告编号 -- '
	private Integer qcBillId; //质检报告id
	private Date createDate; //复检单生成日期
	private Date beginCreateDate; // 开始   复检单生成日期
	private Date endCreateDate; // 结束   复检单生成日期
	
	private Date expectCheckDatetime;		//项目经理申请日期
	private Date checkDatetime;		// 质检员检查日期'
	private Integer recheckTimes;	//复检次数
	private String status;		// 状态 -- '复检单状态：1.创建；2.项目经理已申请；3.复检不合格；4.复检合格
	private String projectMode;		//工程模式   1-产业模式；2-传统模式
	
	
	
	public Integer getQcBillId() {
		return qcBillId;
	}
	public void setQcBillId(Integer qcBillId) {
		this.qcBillId = qcBillId;
	}
	public String getProjectMode() {
		return projectMode;
	}
	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}
	
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
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
	public String getQcBillCode() {
		return qcBillCode;
	}
	public void setQcBillCode(String qcBillCode) {
		this.qcBillCode = qcBillCode;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getBeginCreateDate() {
		return beginCreateDate;
	}
	public void setBeginCreateDate(Date beginCreateDate) {
		this.beginCreateDate = beginCreateDate;
	}
	public Date getEndCreateDate() {
		return endCreateDate;
	}
	public void setEndCreateDate(Date endCreateDate) {
		this.endCreateDate = endCreateDate;
	}
	public Date getExpectCheckDatetime() {
		return expectCheckDatetime;
	}
	public void setExpectCheckDatetime(Date expectCheckDatetime) {
		this.expectCheckDatetime = expectCheckDatetime;
	}
	public Date getCheckDatetime() {
		return checkDatetime;
	}
	public void setCheckDatetime(Date checkDatetime) {
		this.checkDatetime = checkDatetime;
	}
	public Integer getRecheckTimes() {
		return recheckTimes;
	}
	public void setRecheckTimes(Integer recheckTimes) {
		this.recheckTimes = recheckTimes;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
	
}