/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.damei.common.persistence.DataEntity;

/**
 * 结算员审核验收单Entity
 * @author wyb
 * @version 2016-10-31
 */
public class InspectorConfirm extends DataEntity<InspectorConfirm> {
	
	private static final long serialVersionUID = 1L;
	
	private Integer qcBillId;	//约检验收单id
	private Integer orderId;	//订单id
	private String storeId;	//门店

	private String communityName;		// 小区名称
	private String buildNumber;		// 几号楼
	private String buildUnit;		// 几单元
	private String buildRoom;		// 哪一室
	private String customerName;		// 客户姓名
	
	private String itemManager;		// 项目经理
	private Integer itemManagerId;		// 项目经理id
	
	private String checkEmployeeName;
	
	
	private String qcBillCode;	//质检单编号
	private Integer qcCheckNodeId;	//约检节点id
	private String qcCheckNodeName;	//约检节点
	private String status;		//状态
	private String isRecheck;	//是否复检
	private String qcBillType;	//质检单类型
	
	
	private Date acceptCheckDatetime; //确认验收日期
	private Date beginAcceptCheckDatetime; // 开始   确认验收日期
	private Date endAcceptCheckDatetime; // 结束   确认验收日期
	
	private String projectMode;		//工程模式   1-产业模式；2-传统模式
	
	private String reviewStatus;	//审核状态
	private String reviewRemark;	//审核意见
	private Date reviewDatetime;	//审核日期时间

	private Integer enginDepartId;
	private String departmentName;
	private List<Integer> enginDepartIds = new ArrayList<Integer>();
	
	
	
	public String getCheckEmployeeName() {
		return checkEmployeeName;
	}

	public void setCheckEmployeeName(String checkEmployeeName) {
		this.checkEmployeeName = checkEmployeeName;
	}

	public Integer getQcBillId() {
		return qcBillId;
	}

	public void setQcBillId(Integer qcBillId) {
		this.qcBillId = qcBillId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
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

	public Integer getItemManagerId() {
		return itemManagerId;
	}

	public void setItemManagerId(Integer itemManagerId) {
		this.itemManagerId = itemManagerId;
	}

	public String getQcBillCode() {
		return qcBillCode;
	}

	public void setQcBillCode(String qcBillCode) {
		this.qcBillCode = qcBillCode;
	}

	public Integer getQcCheckNodeId() {
		return qcCheckNodeId;
	}

	public void setQcCheckNodeId(Integer qcCheckNodeId) {
		this.qcCheckNodeId = qcCheckNodeId;
	}

	public String getQcCheckNodeName() {
		return qcCheckNodeName;
	}

	public void setQcCheckNodeName(String qcCheckNodeName) {
		this.qcCheckNodeName = qcCheckNodeName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIsRecheck() {
		return isRecheck;
	}

	public void setIsRecheck(String isRecheck) {
		this.isRecheck = isRecheck;
	}

	public String getQcBillType() {
		return qcBillType;
	}

	public void setQcBillType(String qcBillType) {
		this.qcBillType = qcBillType;
	}

	public Date getAcceptCheckDatetime() {
		return acceptCheckDatetime;
	}

	public void setAcceptCheckDatetime(Date acceptCheckDatetime) {
		this.acceptCheckDatetime = acceptCheckDatetime;
	}

	public Date getBeginAcceptCheckDatetime() {
		return beginAcceptCheckDatetime;
	}

	public void setBeginAcceptCheckDatetime(Date beginAcceptCheckDatetime) {
		this.beginAcceptCheckDatetime = beginAcceptCheckDatetime;
	}

	public Date getEndAcceptCheckDatetime() {
		return endAcceptCheckDatetime;
	}

	public void setEndAcceptCheckDatetime(Date endAcceptCheckDatetime) {
		this.endAcceptCheckDatetime = endAcceptCheckDatetime;
	}

	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}

	public String getReviewStatus() {
		return reviewStatus;
	}

	public void setReviewStatus(String reviewStatus) {
		this.reviewStatus = reviewStatus;
	}

	public String getReviewRemark() {
		return reviewRemark;
	}

	public void setReviewRemark(String reviewRemark) {
		this.reviewRemark = reviewRemark;
	}

	public Date getReviewDatetime() {
		return reviewDatetime;
	}

	public void setReviewDatetime(Date reviewDatetime) {
		this.reviewDatetime = reviewDatetime;
	}

	public Integer getEnginDepartId() {
		return enginDepartId;
	}

	public void setEnginDepartId(Integer enginDepartId) {
		this.enginDepartId = enginDepartId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public List<Integer> getEnginDepartIds() {
		return enginDepartIds;
	}

	public void setEnginDepartIds(List<Integer> enginDepartIds) {
		this.enginDepartIds = enginDepartIds;
	}
	
}