package cn.damei.entity.mobile.Manager;

public class InterfaceOrder {
	
	private String StoreId                ;
	private String ProjectMode            ;
	private String OrderNo                ;
	private String ProjectArea            ;
	private String IsNew                  ;
	private String ProjectManager         ;
	private String ContractStartTime      ;
	private String ContractCompleteTime   ;
	private String RealStartTime   ;
	private String scheduleNodeInfo;
	private String key                    ;
	
	
	
	public String getScheduleNodeInfo() {
		return scheduleNodeInfo;
	}
	public void setScheduleNodeInfo(String scheduleNodeInfo) {
		this.scheduleNodeInfo = scheduleNodeInfo;
	}
	public String getRealStartTime() {
		return RealStartTime;
	}
	public void setRealStartTime(String realStartTime) {
		RealStartTime = realStartTime;
	}
	
	public String getStoreId() {
		return StoreId;
	}
	public void setStoreId(String storeId) {
		StoreId = storeId;
	}
	public String getProjectMode() {
		return ProjectMode;
	}
	public void setProjectMode(String projectMode) {
		ProjectMode = projectMode;
	}
	public String getOrderNo() {
		return OrderNo;
	}
	public void setOrderNo(String orderNo) {
		OrderNo = orderNo;
	}
	public String getProjectArea() {
		return ProjectArea;
	}
	public void setProjectArea(String projectArea) {
		ProjectArea = projectArea;
	}
	public String getIsNew() {
		return IsNew;
	}
	public void setIsNew(String isNew) {
		IsNew = isNew;
	}
	public String getProjectManager() {
		return ProjectManager;
	}
	public void setProjectManager(String projectManager) {
		ProjectManager = projectManager;
	}
	public String getContractStartTime() {
		return ContractStartTime;
	}
	public void setContractStartTime(String contractStartTime) {
		ContractStartTime = contractStartTime;
	}
	public String getContractCompleteTime() {
		return ContractCompleteTime;
	}
	public void setContractCompleteTime(String contractCompleteTime) {
		ContractCompleteTime = contractCompleteTime;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
	

}
