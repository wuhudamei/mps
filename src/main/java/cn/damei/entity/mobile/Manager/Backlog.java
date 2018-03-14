package cn.damei.entity.mobile.Manager;

import java.util.List;

import cn.damei.common.persistence.DataEntity2;
import cn.damei.entity.modules.ToDoItemEntity;

public class Backlog extends DataEntity2<Backlog>{

	private static final long serialVersionUID = 1L;
	private String taskPackageName; //任务报名
	private String remindTitle;
	private String communityName;//小区名字
	private String buildNumber;//楼号
	private String buildUnit;//几单元
	private String buildRoom;//那一室
	private String settlementId;//结算单ID
	private String taskPackageId;
	private String status;
	private String customerName;//客户姓名
	private String customerPhone;//客户电话
	
	private String designerName;//设计师名称
	private String designerPhone;//设计师电话
	private String managerName ;//项目经理姓名
	private String purchaseType;//采购单类型
	
	private String orderId;
	
	
	public String getRemindTitle() {
		return remindTitle;
	}
	public void setRemindTitle(String remindTitle) {
		this.remindTitle = remindTitle;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	public String getDesignerName() {
		return designerName;
	}
	public void setDesignerName(String designerName) {
		this.designerName = designerName;
	}
	public String getDesignerPhone() {
		return designerPhone;
	}
	public void setDesignerPhone(String designerPhone) {
		this.designerPhone = designerPhone;
	}
	private List<ToDoItemEntity> todayToDoList;//今日待办
	private Integer todayToDoListCount; //今日待办条数
	private List<ToDoItemEntity> otherToDoList; //其他待办
	private Integer otherToDoListCount; //其他待办条数
	
	public List<ToDoItemEntity> getTodayToDoList() {
		return todayToDoList;
	}
	public void setTodayToDoList(List<ToDoItemEntity> todayToDoList) {
		this.todayToDoList = todayToDoList;
	}
	
	public Integer getTodayToDoListCount() {
		return todayToDoListCount;
	}
	public void setTodayToDoListCount(Integer todayToDoListCount) {
		this.todayToDoListCount = todayToDoListCount;
	}
	public Integer getOtherToDoListCount() {
		return otherToDoListCount;
	}
	public void setOtherToDoListCount(Integer otherToDoListCount) {
		this.otherToDoListCount = otherToDoListCount;
	}
	public List<ToDoItemEntity> getOtherToDoList() {
		return otherToDoList;
	}
	public void setOtherToDoList(List<ToDoItemEntity> otherToDoList) {
		this.otherToDoList = otherToDoList;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getPurchaseTypeId() {
		return purchaseType;
	}
	
	public String getPurchaseType() {
		if(purchaseType.equals("1")){
			return "辅料";
		}
		if(purchaseType.equals("5")){
			return "墙地砖";
		}
		
		return null;
	}
	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getSettlementId() {
		return settlementId;
	}
	public void setSettlementId(String settlementId) {
		this.settlementId = settlementId;
	}
	public String getTaskPackageId() {
		return taskPackageId;
	}
	public void setTaskPackageId(String taskPackageId) {
		this.taskPackageId = taskPackageId;
	}
	public String getTaskPackageName() {
		return taskPackageName.substring(0, 2);
	}
	public void setTaskPackageName(String taskPackageName) {
		this.taskPackageName = taskPackageName;
	}
	public String getCommunityName() {
		return communityName;
	}
	public void setCommunityName(String community_name) {
		this.communityName = community_name;
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
	public Backlog() {
		super();
	}
	
	
}
