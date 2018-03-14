package cn.damei.entity.mobile.Inspector;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

/**
 * 质检报告vo
 * @author Administrator
 *
 */
public class ReportCheck extends DataEntity2<ReportCheck>{

	private static final long serialVersionUID = 1L;
	private String communityName; //小区名称
	private String buildNumber; //几号楼
	private String buildUnit; //几单元
	private String buildRoom; //几室
	private String customerName; //客户姓名
	private String customerPhone; //客户电话
	private Integer itemManagerId; //项目经理id
	private String managerRealName; //项目经理
	private Integer orderId; //订单id
	private String actualStartDateString; //实际开工日期字符串
	private String orderStatusNumber; //订单状态
	private String orderStatusDescription; //订单状态描述
	private String qcCheckNodeName; //检查内容
	private Date checkDatetime; //检查时间
	private Integer checkEmployeeId; //质检员id
	private String inspectorRealName;//质检员
	private Double totalScore; //总分
	private Double gotScore;//实际得分
	private String isRecheck;	//是否复检  1复检
	private String qcBillType;	//1约检  2抽检
	private String text; //搜索框


	 
	public Integer getItemManagerId() {
		return itemManagerId;
	}
	public void setItemManagerId(Integer itemManagerId) {
		this.itemManagerId = itemManagerId;
	}
	public String getIsRecheck() {
		return isRecheck;
	}
	public void setIsRecheck(String isRecheck) {
		this.isRecheck = isRecheck;
	}
	public String getQcBillType() {
		return qcBillType;
	}
	public void setQcBillType(String qcBillType) {
		this.qcBillType = qcBillType;
	}
	public String getInspectorRealName() {
		return inspectorRealName;
	}
	public void setInspectorRealName(String inspectorRealName) {
		this.inspectorRealName = inspectorRealName;
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
	public Integer getCheckEmployeeId() {
		return checkEmployeeId;
	}
	public void setCheckEmployeeId(Integer checkEmployeeId) {
		this.checkEmployeeId = checkEmployeeId;
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
	public Date getCheckDatetime() {
		return checkDatetime;
	}
	public void setCheckDatetime(Date checkDatetime) {
		this.checkDatetime = checkDatetime;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getOrderStatusNumber() {
		return orderStatusNumber;
	}

	public void setOrderStatusNumber(String orderStatusNumber) {
		this.orderStatusNumber = orderStatusNumber;
	}

	public String getOrderStatusDescription() {
		return orderStatusDescription;
	}

	public void setOrderStatusDescription(String orderStatusDescription) {
		this.orderStatusDescription = orderStatusDescription;
	}

	public String getActualStartDateString() {
		return actualStartDateString;
	}

	public void setActualStartDateString(String actualStartDateString) {
		this.actualStartDateString = actualStartDateString;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
}
