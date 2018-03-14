package cn.damei.entity.mobile.Inspector;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;
import cn.damei.entity.mobile.home.BizOrder;


public class EvaluateWorker extends DataEntity2<EvaluateWorker>{

	
	private static final long serialVersionUID = 1L;
   
	private Integer orderId;
    private String communityName;
    private String buildNumber;
    private String buildUnit;
    private String buildRoom;
    private String customerName;
    
	private String groupRealname;
	private String packageName;
	
	private Integer evalTaskpackScoreId;
	private Date checkDate;
	
	private Integer orderInspectorId;
	private Integer orderTaskpackageId;
	private Integer groupId;
	
	private String text;
	private Date nowDate;
	private String evalRoleType;
	
	private Integer count;
	private Date addEvalStartDatetime;
	private String indexName;
	private String evalFeedback;
	
	private BizOrder order;
	
	
	public BizOrder getOrder() {
		return order;
	}

	public void setOrder(BizOrder order) {
		this.order = order;
	}

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

	public String getGroupRealname() {
		return groupRealname;
	}

	public void setGroupRealname(String groupRealname) {
		this.groupRealname = groupRealname;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public Integer getEvalTaskpackScoreId() {
		return evalTaskpackScoreId;
	}

	public void setEvalTaskpackScoreId(Integer evalTaskpackScoreId) {
		this.evalTaskpackScoreId = evalTaskpackScoreId;
	}

	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public Integer getOrderInspectorId() {
		return orderInspectorId;
	}

	public void setOrderInspectorId(Integer orderInspectorId) {
		this.orderInspectorId = orderInspectorId;
	}

	public Integer getOrderTaskpackageId() {
		return orderTaskpackageId;
	}

	public void setOrderTaskpackageId(Integer orderTaskpackageId) {
		this.orderTaskpackageId = orderTaskpackageId;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getNowDate() {
		return nowDate;
	}

	public void setNowDate(Date nowDate) {
		this.nowDate = nowDate;
	}

	public String getEvalRoleType() {
		return evalRoleType;
	}

	public void setEvalRoleType(String evalRoleType) {
		this.evalRoleType = evalRoleType;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Date getAddEvalStartDatetime() {
		return addEvalStartDatetime;
	}

	public void setAddEvalStartDatetime(Date addEvalStartDatetime) {
		this.addEvalStartDatetime = addEvalStartDatetime;
	}

	public String getIndexName() {
		return indexName;
	}

	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}

	public String getEvalFeedback() {
		return evalFeedback;
	}

	public void setEvalFeedback(String evalFeedback) {
		this.evalFeedback = evalFeedback;
	}
	
	

    
}
