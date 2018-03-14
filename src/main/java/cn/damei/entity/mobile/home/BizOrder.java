package cn.damei.entity.mobile.home;

import java.io.Serializable;
import java.util.List;

import cn.damei.entity.mobile.Inspector.EvaluateWorker;


/**
 * 订单Entity
 * @author wyb
 * @version 2016-11-16
 */
public class BizOrder implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer orderId; //订单id
	private String orderNumber;	//订单编码
	
	private String communityName;	//小区
	private String buildNumber;	//几号楼
	private String buildUnit;	//几单元
	private String buildRoom;	//几室
	
	private String customerName;	//客户姓名
	private String customerPhone; //客户电话
	
	private Integer itemManagerId; //项目经理id
	private String itemManager;	//项目经理
	private String itemManagerPhone; //项目经理电话
	
	private Integer orderInspectorId; //质检员id
	private String orderInspector;	//质检员
	private String orderInspectorPhone; //质检员电话
	private Integer number;//业务表
	
	private List<BizProjectChangeBill> projectChangeList; //质检报告列表
	private List<EvaluateWorker> evaluateWorkerList; //评价列表


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