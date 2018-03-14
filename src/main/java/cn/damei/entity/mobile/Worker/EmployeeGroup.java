package cn.damei.entity.mobile.Worker;

import cn.damei.common.persistence.DataEntity2;

public class EmployeeGroup extends DataEntity2<EmployeeGroup>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id; //工人组id
	private Integer star;//星级
	private String nps;
	private Integer sort; //排名
	private String address; //地址
	private Integer orderStop;//是否停单
	private Integer state; //是否删除 1表示删除 0表示未删除
	private String createArea; 
	private Integer workType;
	private Integer groupId;
	private Integer storeId;
	private String taskPackageId;
	private String elactricationId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStar() {
		return star;
	}
	public void setStar(Integer star) {
		this.star = star;
	}
	public String getNps() {
		return nps;
	}
	public void setNps(String nps) {
		this.nps = nps;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getOrderStop() {
		return orderStop;
	}
	public void setOrderStop(Integer orderStop) {
		this.orderStop = orderStop;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getCreateArea() {
		return createArea;
	}
	public void setCreateArea(String createArea) {
		this.createArea = createArea;
	}
	public Integer getWorkType() {
		return workType;
	}
	public void setWorkType(Integer workType) {
		this.workType = workType;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public String getTaskPackageId() {
		return taskPackageId;
	}
	public void setTaskPackageId(String taskPackageId) {
		this.taskPackageId = taskPackageId;
	}
	public String getElactricationId() {
		return elactricationId;
	}
	public void setElactricationId(String elactricationId) {
		this.elactricationId = elactricationId;
	}
	
	
}
