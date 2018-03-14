package cn.damei.entity.mobile.Inspector;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;


public class CheckConfirm extends DataEntity2<CheckConfirm>{

	
	private static final long serialVersionUID = 1L;
    private Integer id ;
   
    private String communityName;
    private String buildNumber;
    private String buildUnit;
    private String buildRoom;
    private String customerName;
    private String projectMode;
	
    private String customerPhone;
    private String designerPhone;
    private String managerRealName;
    private Integer qcCheckNodeId;
    private String qcCheckNodeName;
    private Date expectCheckDatetime;
    private Date acceptCheckDatetime;
    private String status;
    private String isUrgePay;
    private String checkWords;
    
    private Integer workerCount;
    private Integer managerCount;
    private Integer inspectorCount;
	private String delayReasonQc;
	private String delayReasonPm;
	private Date planCheckDate;
	private Integer orderId;
	private String orderNumber;
	

	private Integer qcCheckNodeIndex;
		
	public Integer getQcCheckNodeIndex() {
		return qcCheckNodeIndex;
	}

	public void setQcCheckNodeIndex(Integer qcCheckNodeIndex) {
		this.qcCheckNodeIndex = qcCheckNodeIndex;
	}
	
	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getDelayReasonPm() {
		return delayReasonPm;
	}

	public void setDelayReasonPm(String delayReasonPm) {
		this.delayReasonPm = delayReasonPm;
	}

	public Date getPlanCheckDate() {
		return planCheckDate;
	}

	public void setPlanCheckDate(Date planCheckDate) {
		this.planCheckDate = planCheckDate;
	}

	public String getDelayReasonQc() {
		return delayReasonQc;
	}

	public void setDelayReasonQc(String delayReasonQc) {
		this.delayReasonQc = delayReasonQc;
	}

	public Integer getQcCheckNodeId() {
		return qcCheckNodeId;
	}
	public void setQcCheckNodeId(Integer qcCheckNodeId) {
		this.qcCheckNodeId = qcCheckNodeId;
	}
	public String getProjectMode() {
		return projectMode;
	}
	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}
    public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getManagerRealName() {
		return managerRealName;
	}
	public void setManagerRealName(String managerRealName) {
		this.managerRealName = managerRealName;
	}
	public String getQcCheckNodeName() {
		return qcCheckNodeName;
	}
	public void setQcCheckNodeName(String qcCheckNodeName) {
		this.qcCheckNodeName = qcCheckNodeName;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDesignerPhone() {
		return designerPhone;
	}
	public void setDesignerPhone(String designerPhone) {
		this.designerPhone = designerPhone;
	}
	public String getIsUrgePay() {
		return isUrgePay;
	}
	public void setIsUrgePay(String isUrgePay) {
		this.isUrgePay = isUrgePay;
	}
	public String getCheckWords() {
		return checkWords;
	}
	public void setCheckWords(String checkWords) {
		this.checkWords = checkWords;
	}
	public Integer getWorkerCount() {
		return workerCount;
	}
	public void setWorkerCount(Integer workerCount) {
		this.workerCount = workerCount;
	}
	public Integer getManagerCount() {
		return managerCount;
	}
	public void setManagerCount(Integer managerCount) {
		this.managerCount = managerCount;
	}
	public Integer getInspectorCount() {
		return inspectorCount;
	}
	public void setInspectorCount(Integer inspectorCount) {
		this.inspectorCount = inspectorCount;
	}
	
	

    
}
