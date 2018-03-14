
package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;

import java.util.Date;


public class BizCustomerReturnVisitTraditionOrderData extends DataEntity2<BizCustomerReturnVisitTraditionOrderData> {
	private static final long serialVersionUID = 1L;

	private String orderId;

	private String returnVisitNode;

	private Integer returnVisitStatus;

	private Date returnVisitTime;

	private Integer returnVisitTimes;

	private Date actualStartDate;

	private String orderInspector;

	private String itemManager;

	private String designerName;

	private String customerAddress;

	private String customerName;

	private String customerPhone;

	private String orderNumber;

	private String returnVisitNodeName;

	private Date nodeCheckDate;

	private Date nodeCheckDateBegin;

	private Date nodeCheckDateEnd;

	private Integer invalidNum;

	private String storeId;

	private String projectMode;

	private String area;

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