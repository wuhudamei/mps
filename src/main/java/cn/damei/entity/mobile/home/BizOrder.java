package cn.damei.entity.mobile.home;

import java.io.Serializable;
import java.util.List;

import cn.damei.entity.mobile.Inspector.EvaluateWorker;



public class BizOrder implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer orderId;
	private String orderNumber;
	
	private String communityName;
	private String buildNumber;
	private String buildUnit;
	private String buildRoom;
	
	private String customerName;
	private String customerPhone;
	
	private Integer itemManagerId;
	private String itemManager;
	private String itemManagerPhone;
	
	private Integer orderInspectorId;
	private String orderInspector;
	private String orderInspectorPhone;
	private Integer number;
	
	private List<BizProjectChangeBill> projectChangeList;
	private List<EvaluateWorker> evaluateWorkerList;


	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
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

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public Integer getItemManagerId() {
		return itemManagerId;
	}

	public void setItemManagerId(Integer itemManagerId) {
		this.itemManagerId = itemManagerId;
	}

	public String getItemManager() {
		return itemManager;
	}

	public void setItemManager(String itemManager) {
		this.itemManager = itemManager;
	}

	public String getItemManagerPhone() {
		return itemManagerPhone;
	}

	public void setItemManagerPhone(String itemManagerPhone) {
		this.itemManagerPhone = itemManagerPhone;
	}

	public Integer getOrderInspectorId() {
		return orderInspectorId;
	}

	public void setOrderInspectorId(Integer orderInspectorId) {
		this.orderInspectorId = orderInspectorId;
	}

	public String getOrderInspector() {
		return orderInspector;
	}

	public void setOrderInspector(String orderInspector) {
		this.orderInspector = orderInspector;
	}

	public String getOrderInspectorPhone() {
		return orderInspectorPhone;
	}

	public void setOrderInspectorPhone(String orderInspectorPhone) {
		this.orderInspectorPhone = orderInspectorPhone;
	}

	public List<BizProjectChangeBill> getProjectChangeList() {
		return projectChangeList;
	}

	public void setProjectChangeList(List<BizProjectChangeBill> projectChangeList) {
		this.projectChangeList = projectChangeList;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public List<EvaluateWorker> getEvaluateWorkerList() {
		return evaluateWorkerList;
	}

	public void setEvaluateWorkerList(List<EvaluateWorker> evaluateWorkerList) {
		this.evaluateWorkerList = evaluateWorkerList;
	}

	
	
		
}