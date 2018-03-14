/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;

import java.util.Date;

/**
 * 传统订单待回访
 * @author 王硕
 * @version 2017-12-7
 */
public class BizCustomerReturnVisitTraditionOrderData extends DataEntity2<BizCustomerReturnVisitTraditionOrderData> {
	private static final long serialVersionUID = 1L;

	private String orderId;//订单Id

	private String returnVisitNode;//回访节点

	private Integer returnVisitStatus;//回访状态（0过期，1待回访，2已回访）

	private Date returnVisitTime;//回访时间

	private Integer returnVisitTimes;//回访次数

	private Date actualStartDate;//开工时间

	private String orderInspector;//质检员姓名

	private String itemManager;//项目经理

	private String designerName;//设计师姓名

	private String customerAddress;//客户地址

	private String customerName;//客户姓名

	private String customerPhone;//客户电话

	private String orderNumber;//订单编号

	private String returnVisitNodeName;//回访节点名称

	private Date nodeCheckDate;//节点验收时间

	private Date nodeCheckDateBegin;//节点验收时间开始

	private Date nodeCheckDateEnd;//节点验收时间结束

	private Integer invalidNum;//回访次数

	private String storeId;//门店ID

	private String projectMode;//工程模式

	private String area;//区域

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
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

	public Date getActualStartDate() {
		return actualStartDate;
	}

	public void setActualStartDate(Date actualStartDate) {
		this.actualStartDate = actualStartDate;
	}

	public String getOrderInspector() {
		return orderInspector;
	}

	public void setOrderInspector(String orderInspector) {
		this.orderInspector = orderInspector;
	}

	public String getItemManager() {
		return itemManager;
	}

	public void setItemManager(String itemManager) {
		this.itemManager = itemManager;
	}

	public String getDesignerName() {
		return designerName;
	}

	public void setDesignerName(String designerName) {
		this.designerName = designerName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
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

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getReturnVisitNodeName() {
		return returnVisitNodeName;
	}

	public void setReturnVisitNodeName(String returnVisitNodeName) {
		this.returnVisitNodeName = returnVisitNodeName;
	}

	public Date getNodeCheckDate() {
		return nodeCheckDate;
	}

	public void setNodeCheckDate(Date nodeCheckDate) {
		this.nodeCheckDate = nodeCheckDate;
	}

	public Date getNodeCheckDateBegin() {
		return nodeCheckDateBegin;
	}

	public void setNodeCheckDateBegin(Date nodeCheckDateBegin) {
		this.nodeCheckDateBegin = nodeCheckDateBegin;
	}

	public Date getNodeCheckDateEnd() {
		return nodeCheckDateEnd;
	}

	public void setNodeCheckDateEnd(Date nodeCheckDateEnd) {
		this.nodeCheckDateEnd = nodeCheckDateEnd;
	}

	public Integer getInvalidNum() {
		return invalidNum;
	}

	public void setInvalidNum(Integer invalidNum) {
		this.invalidNum = invalidNum;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getReturnVisitNode() {
		return returnVisitNode;
	}

	public void setReturnVisitNode(String returnVisitNode) {
		this.returnVisitNode = returnVisitNode;
	}

	public Integer getReturnVisitStatus() {
		return returnVisitStatus;
	}

	public void setReturnVisitStatus(Integer returnVisitStatus) {
		this.returnVisitStatus = returnVisitStatus;
	}

	public Date getReturnVisitTime() {
		return returnVisitTime;
	}

	public void setReturnVisitTime(Date returnVisitTime) {
		this.returnVisitTime = returnVisitTime;
	}

	public Integer getReturnVisitTimes() {
		return returnVisitTimes;
	}

	public void setReturnVisitTimes(Integer returnVisitTimes) {
		this.returnVisitTimes = returnVisitTimes;
	}
}