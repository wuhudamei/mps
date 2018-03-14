/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity;

/**
 * 质检报告Entity
 * @author wyb
 * @version 2016-10-31
 */
public class BizQcBill extends DataEntity<BizQcBill> {
	
	private static final long serialVersionUID = 1L;
	private Integer qcBillId;	//质检报告id
	private String qcBillCode;		// 质检单编号 -- '
	private String qcBillType;		// 质检单类型 -- '1：约检单；2.抽检单
	private String isRecheck;		// 是否复检 -- '0.否；1.是；默认0
	private Integer relatedQcBillId;		// 关联质检单id -- '
	private Integer orderId;		// 订单id -- '
	private Integer qcCheckNodeId;		// 检查节点id -- '
	private String applyRemarks;		// 申请备注 -- '
	private String status;		// 状态 -- '复检单状态：1.创建；2.项目经理已申请；3.复检不合格；4.复检合格；约检单抽检单状态：0.暂存；2.项目经理已申请；5.已检查完成；6.已确认验收；
	private Integer applyEmployeeId;		// 质检申请人员工id -- '
	private String  applyEmployeeName;
	private Integer checkEmployeeId;		// 实际质检人员工id -- '
	private Date checkDatetime;		// 质检日期时间 -- '
	private Date expectCheckDatetime;		// 期望质检日期时间 -- '
	private Date acceptCheckDatetime;		// 验收日期时间 -- '
	private Double totalScore;		// 总分 -- '
	private Double gotScore;		// 实际得分 -- '
	private Date beginCheckDatetime;		// 开始 质检日期时间 -- '
	private Date endCheckDatetime;		// 结束 质检日期时间 -- '
	private Integer recheckTimes;	//复检次数
	
	private String orderNumber;	//订单编号
	private String customerAddress; //地址
	private String communityName; //小区
	private String buildNumber; //几号楼
	private String buildUnit; //几单元
	private String buildRoom; //几室
	private String customerName; //顾客
	private String storeId; //门店
	private String managerRealName; //项目经理
	private String inspectorRealName; //质检员
	private Integer orderInspectorId; //质检员id
	private String checkRealName; //检查人
	private String qcCheckNodeName; //节点名称
	private Date signCheckDatetime;	//签到时间
	private String signAddress; //签到地址
	private Date actualStartDate;//实际开工日期
	private String projectMode;		//工程模式   1-产业模式；2-传统模式
	private String isEqual; //总分与实际得分不相等
	
	
	
	
	public String getApplyEmployeeName() {
		return applyEmployeeName;
	}
	public void setApplyEmployeeName(String applyEmployeeName) {
		this.applyEmployeeName = applyEmployeeName;
	}
	public Date getSignCheckDatetime() {
		return signCheckDatetime;
	}
	public void setSignCheckDatetime(Date signCheckDatetime) {
		this.signCheckDatetime = signCheckDatetime;
	}
	public String getIsEqual() {
		return isEqual;
	}
	public void setIsEqual(String isEqual) {
		this.isEqual = isEqual;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public Integer getQcBillId() {
		return qcBillId;
	}
	public void setQcBillId(Integer qcBillId) {
		this.qcBillId = qcBillId;
	}
	public String getQcBillCode() {
		return qcBillCode;
	}
	public void setQcBillCode(String qcBillCode) {
		this.qcBillCode = qcBillCode;
	}
	public String getQcBillType() {
		return qcBillType;
	}
	public void setQcBillType(String qcBillType) {
		this.qcBillType = qcBillType;
	}
	public String getIsRecheck() {
		return isRecheck;
	}
	public void setIsRecheck(String isRecheck) {
		this.isRecheck = isRecheck;
	}
	public Integer getRelatedQcBillId() {
		return relatedQcBillId;
	}
	public void setRelatedQcBillId(Integer relatedQcBillId) {
		this.relatedQcBillId = relatedQcBillId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getQcCheckNodeId() {
		return qcCheckNodeId;
	}
	public void setQcCheckNodeId(Integer qcCheckNodeId) {
		this.qcCheckNodeId = qcCheckNodeId;
	}
	public String getApplyRemarks() {
		return applyRemarks;
	}
	public void setApplyRemarks(String applyRemarks) {
		this.applyRemarks = applyRemarks;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getApplyEmployeeId() {
		return applyEmployeeId;
	}
	public void setApplyEmployeeId(Integer applyEmployeeId) {
		this.applyEmployeeId = applyEmployeeId;
	}
	public Integer getCheckEmployeeId() {
		return checkEmployeeId;
	}
	public void setCheckEmployeeId(Integer checkEmployeeId) {
		this.checkEmployeeId = checkEmployeeId;
	}
	public Date getCheckDatetime() {
		return checkDatetime;
	}
	public void setCheckDatetime(Date checkDatetime) {
		this.checkDatetime = checkDatetime;
	}
	public Date getExpectCheckDatetime() {
		return expectCheckDatetime;
	}
	public void setExpectCheckDatetime(Date expectCheckDatetime) {
		this.expectCheckDatetime = expectCheckDatetime;
	}
	public Date getAcceptCheckDatetime() {
		return acceptCheckDatetime;
	}
	public void setAcceptCheckDatetime(Date acceptCheckDatetime) {
		this.acceptCheckDatetime = acceptCheckDatetime;
	}
	public Double getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(Double totalScore) {
		this.totalScore = totalScore;
	}
	public Double getGotScore() {
		return gotScore;
	}
	public void setGotScore(Double gotScore) {
		this.gotScore = gotScore;
	}
	public Date getBeginCheckDatetime() {
		return beginCheckDatetime;
	}
	public void setBeginCheckDatetime(Date beginCheckDatetime) {
		this.beginCheckDatetime = beginCheckDatetime;
	}
	public Date getEndCheckDatetime() {
		return endCheckDatetime;
	}
	public void setEndCheckDatetime(Date endCheckDatetime) {
		this.endCheckDatetime = endCheckDatetime;
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
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getManagerRealName() {
		return managerRealName;
	}
	public void setManagerRealName(String managerRealName) {
		this.managerRealName = managerRealName;
	}
	public String getInspectorRealName() {
		return inspectorRealName;
	}
	public void setInspectorRealName(String inspectorRealName) {
		this.inspectorRealName = inspectorRealName;
	}
	public String getCheckRealName() {
		return checkRealName;
	}
	public void setCheckRealName(String checkRealName) {
		this.checkRealName = checkRealName;
	}
	public String getQcCheckNodeName() {
		return qcCheckNodeName;
	}
	public void setQcCheckNodeName(String qcCheckNodeName) {
		this.qcCheckNodeName = qcCheckNodeName;
	}
	public String getSignAddress() {
		return signAddress;
	}
	public void setSignAddress(String signAddress) {
		this.signAddress = signAddress;
	}
	public Date getActualStartDate() {
		return actualStartDate;
	}
	public void setActualStartDate(Date actualStartDate) {
		this.actualStartDate = actualStartDate;
	}
	public Integer getOrderInspectorId() {
		return orderInspectorId;
	}
	public void setOrderInspectorId(Integer orderInspectorId) {
		this.orderInspectorId = orderInspectorId;
	}
	public Integer getRecheckTimes() {
		return recheckTimes;
	}
	public void setRecheckTimes(Integer recheckTimes) {
		this.recheckTimes = recheckTimes;
	}
	public String getProjectMode() {
		return projectMode;
	}
	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}
	
	
	
	
		
}