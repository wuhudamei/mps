package cn.damei.entity.mobile.Worker;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

/** 
* @author 梅浩   meihao@zzhyun.cn: 
* @version 创建时间：2016年9月22日 下午6:35:08 
* 项目经理(ItemManager)     订单(Order)    签到(Sign)      Vo
*/

public class TaskPackSignVo extends DataEntity2<TaskPackSignVo>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	//任务包id主键在 爷爷类中
	private Integer orderId; //任务包相关订单id	
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	private String workerLeaderName;//组长姓名
	private Integer workerLeaderId;//组长id
	private Integer itemManagerId;//项目经理id
	private String itemManagerName;//项目经理姓名
	private String itemManagerPhone;//项目经理电话
	private String customerName;//客户姓名
	private String customerMessage;//客户信息
	private Date planStartDate;//计划开始日期
	private Date planEndDate;//计划结束日期
	private String  packStateName;//任务包状态名称
	private String packStateId; //任务包状态
	private String signFlag;//是否签到
	private String packageName;//任务包名称
	private Integer count;//签到数量
	private String lat;
	private String lon;
	private String settleStyle;//结算方式
	private String isScrap; //已作废标识
	
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getSignFlag() {
		return signFlag;
	}
	public void setSignFlag(String signFlag) {
		this.signFlag = signFlag;
	}
	public String getWorkerLeaderName() {
		return workerLeaderName;
	}
	public void setWorkerLeaderName(String workerLeaderName) {
		this.workerLeaderName = workerLeaderName;
	}
	public Integer getWorkerLeaderId() {
		return workerLeaderId;
	}
	public void setWorkerLeaderId(Integer workerLeaderId) {
		this.workerLeaderId = workerLeaderId;
	}
	public Integer getItemManagerId() {
		return itemManagerId;
	}
	public void setItemManagerId(Integer itemManagerId) {
		this.itemManagerId = itemManagerId;
	}
	public String getItemManagerName() {
		return itemManagerName;
	}
	public void setItemManagerName(String itemManagerName) {
		this.itemManagerName = itemManagerName;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerMessage() {
		return customerMessage;
	}
	public void setCustomerMessage(String customerMessage) {
		this.customerMessage = customerMessage;
	}
	public Date getPlanStartDate() {
		return planStartDate;
	}
	public void setPlanStartDate(Date planStartDate) {
		this.planStartDate = planStartDate;
	}
	public Date getPlanEndDate() {
		return planEndDate;
	}
	public void setPlanEndDate(Date planEndDate) {
		this.planEndDate = planEndDate;
	}
	public String getPackStateName() {
		return packStateName;
	}
	public void setPackStateName(String packStateName) {
		this.packStateName = packStateName;
	}
	public String getPackStateId() {
		return packStateId;
	}
	public void setPackStateId(String packStateId) {
		this.packStateId = packStateId;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getItemManagerPhone() {
		return itemManagerPhone;
	}
	public void setItemManagerPhone(String itemManagerPhone) {
		this.itemManagerPhone = itemManagerPhone;
	}
	public String getSettleStyle() {
		return settleStyle;
	}
	public void setSettleStyle(String settleStyle) {
		this.settleStyle = settleStyle;
	}
	public String getIsScrap() {
		return isScrap;
	}
	public void setIsScrap(String isScrap) {
		this.isScrap = isScrap;
	}
	
}
