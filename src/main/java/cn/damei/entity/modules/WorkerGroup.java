package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity;

/** 
* @author 梅浩   meihao@zzhyun.cn: 
* @version 创建时间：2016年9月12日 下午5:41:44 
* 类说明 
*/

public class WorkerGroup  extends  DataEntity<WorkerGroup>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private Integer star;//工人组星级
	private String NPS; //NPS
	private Integer sort;//排序
	private  Integer orderStop;
	private String address;//地址
	private String ordersArea;//接单区域
	private Integer workType; //工种
	private  String groupId;  //组长id
	private String storeId;//门店id
	private String taskPackageId;//任务包id
	private String  elactriationId;//归属工程部Id
	private Date allotStartTime;//任务包分配时间(计时,超90分钟  更改状态)
	public Integer getStar() {
		return star;
	}
	public void setStar(Integer star) {
		this.star = star;
	}
	public String getNPS() {
		return NPS;
	}
	public void setNPS(String nPS) {
		NPS = nPS;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public Integer getOrderStop() {
		return orderStop;
	}
	public void setOrderStop(Integer orderStop) {
		this.orderStop = orderStop;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getOrdersArea() {
		return ordersArea;
	}
	public void setOrdersArea(String ordersArea) {
		this.ordersArea = ordersArea;
	}
	public Integer getWorkType() {
		return workType;
	}
	public void setWorkType(Integer workType) {
		this.workType = workType;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getTaskPackageId() {
		return taskPackageId;
	}
	public void setTaskPackageId(String taskPackageId) {
		this.taskPackageId = taskPackageId;
	}
	public String getElactriationId() {
		return elactriationId;
	}
	public void setElactriationId(String elactriationId) {
		this.elactriationId = elactriationId;
	}
	public Date getAllotStartTime() {
		return allotStartTime;
	}
	public void setAllotStartTime(Date allotStartTime) {
		this.allotStartTime = allotStartTime;
	}
	
	
	
}
