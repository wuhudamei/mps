/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity;

/**
 * 项目约检情况查询Entity
 * @author wyb
 * @version 2016-10-31
 */
public class CheckSelect extends DataEntity<CheckSelect> {
	
	private static final long serialVersionUID = 1L;
	
	private Integer orderId;//订单id
	private Integer storeId; //门店
	private String storeName; //门店名称
	private String projectMode;		//工程模式   1-产业模式；2-传统模式
	private String projectModeName;		//工程模式名称
	private Integer engineDepartId; // 区域id
	private String engineDepartName; // 区域名称
	private String orderNumber;		// 订单编号

	private String detailAddress;	//详细地址
	private String communityName;		// 小区名称
	private String buildNumber;		// 几号楼
	private String buildUnit;		// 几单元
	private String buildRoom;		// 哪一室
	private String customerName;		// 客户姓名
	
	private String itemManager;		// 项目经理
	private String orderInspector;	//质检
	private String signType;	//签到类型
	
	private Date actualStartDate; //实际开工日期
	private Date beginActualStartDate; // 开始   实际开工日期
	private Date endActualStartDate; // 结束   实际开工日期
	
	private Integer qcBillId;	//质检单id
	private String qcBillType; // 质检单类型 1约检单  2抽检单
	
	private String isRecheck; // 是否复检
	
	private Integer qcCheckNodeId; // 检查节点id
	private String qcCheckNodeName; // 检查节点名称
	
	private String status; // 状态 -- '抽检单状态：-1.未选择检查项；0.暂存；5.提交报告；
	
	private Date expectCheckDatetime; // 期望质检日期时间
	private Date signCheckDatetime; // 签到日期时间
	
	private Date checkDatetime; // 质检日期时间
	private Date beginCheckDatetime; // 开始   质检日期时间
	private Date endCheckDatetime; // 结束   质检日期时间
	
	private Date acceptCheckDatetime; // 验收日期时间
	private Date beginAcceptCheckDatetime; // 开始   验收日期时间
	private Date endAcceptCheckDatetime; // 结束   验收日期时间

	private Date assertNodeConfirmDoneStartDate;
	private Date assertNodeConfirmDoneEndDate;

	private Double totalScore; // 总分
	private Double gotScore; // 实际得分
	
	private Date beginCreateDate; // 开始   申请日期时间
	private Date endCreateDate; // 结束  申请日期时间
	
	private String totalAndGotIsEqual;//总分与实际得分是否相等
	private String delayReasonPm;
	private String delayReasonQc;

	public Date getAssertNodeConfirmDoneStartDate() {
		return assertNodeConfirmDoneStartDate;
	}

	public void setAssertNodeConfirmDoneStartDate(Date assertNodeConfirmDoneStartDate) {
		this.assertNodeConfirmDoneStartDate = assertNodeConfirmDoneStartDate;
	}

	public Date getAssertNodeConfirmDoneEndDate() {
		return assertNodeConfirmDoneEndDate;
	}

	public void setAssertNodeConfirmDoneEndDate(Date assertNodeConfirmDoneEndDate) {
		this.assertNodeConfirmDoneEndDate = assertNodeConfirmDoneEndDate;
	}

	public String getDelayReasonPm() {
		return delayReasonPm;
	}

	public void setDelayReasonPm(String delayReasonPm) {
		this.delayReasonPm = delayReasonPm;
	}

	public String getDelayReasonQc() {
		return delayReasonQc;
	}

	public void setDelayReasonQc(String delayReasonQc) {
		this.delayReasonQc = delayReasonQc;
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
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getProjectMode() {
		return projectMode;
	}
	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}
	public String getProjectModeName() {
		return projectModeName;
	}
	public void setProjectModeName(String projectModeName) {
		this.projectModeName = projectModeName;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getDetailAddress() {
		return detailAddress;
	}
	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
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
	public String getOrderInspector() {
		return orderInspector;
	}
	public void setOrderInspector(String orderInspector) {
		this.orderInspector = orderInspector;
	}
	public Date getActualStartDate() {
		return actualStartDate;
	}
	public void setActualStartDate(Date actualStartDate) {
		this.actualStartDate = actualStartDate;
	}
	public Date getBeginActualStartDate() {
		return beginActualStartDate;
	}
	public void setBeginActualStartDate(Date beginActualStartDate) {
		this.beginActualStartDate = beginActualStartDate;
	}
	public Date getEndActualStartDate() {
		return endActualStartDate;
	}
	public void setEndActualStartDate(Date endActualStartDate) {
		this.endActualStartDate = endActualStartDate;
	}
	public Integer getQcBillId() {
		return qcBillId;
	}
	public void setQcBillId(Integer qcBillId) {
		this.qcBillId = qcBillId;
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
	public Date getCheckDatetime() {
		return checkDatetime;
	}
	public void setCheckDatetime(Date checkDatetime) {
		this.checkDatetime = checkDatetime;
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
	public String getTotalAndGotIsEqual() {
		return totalAndGotIsEqual;
	}
	public void setTotalAndGotIsEqual(String totalAndGotIsEqual) {
		this.totalAndGotIsEqual = totalAndGotIsEqual;
	}
	public Integer getEngineDepartId() {
		return engineDepartId;
	}
	public void setEngineDepartId(Integer engineDepartId) {
		this.engineDepartId = engineDepartId;
	}
	public String getEngineDepartName() {
		return engineDepartName;
	}
	public void setEngineDepartName(String engineDepartName) {
		this.engineDepartName = engineDepartName;
	}
	public Date getExpectCheckDatetime() {
		return expectCheckDatetime;
	}
	public void setExpectCheckDatetime(Date expectCheckDatetime) {
		this.expectCheckDatetime = expectCheckDatetime;
	}
	public Date getSignCheckDatetime() {
		return signCheckDatetime;
	}
	public void setSignCheckDatetime(Date signCheckDatetime) {
		this.signCheckDatetime = signCheckDatetime;
	}
	public String getSignType() {
		return signType;
	}
	public void setSignType(String signType) {
		this.signType = signType;
	}
	
	
	
	
	
}