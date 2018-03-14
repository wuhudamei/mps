package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity;



public class WorkerGroup  extends  DataEntity<WorkerGroup>{


	private static final long serialVersionUID = 1L;

	
	private Integer star;
	private String NPS;
	private Integer sort;
	private  Integer orderStop;
	private String address;
	private String ordersArea;
	private Integer workType;
	private  String groupId;
	private String storeId;
	private String taskPackageId;
	private String  elactriationId;
	private Date allotStartTime;
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
