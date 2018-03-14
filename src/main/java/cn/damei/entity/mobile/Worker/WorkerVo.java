package cn.damei.entity.mobile.Worker;

import cn.damei.common.persistence.DataEntity2;

public class WorkerVo extends DataEntity2<WorkerVo>{

	/**
	 * @author 汪文文
	 * @version 2016-09-22
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id; //工人组id
	private Integer star;//星级
	private String nps;
	private Integer sort; //排名
	private String address; //地址
	private Integer orderStop;//是否停单
	private Integer state; //是否删除
	private Worker worker; //组长id
	private String realname;//真实姓名
	private String phone; //手机号
	private Integer groupId; //组长id
	
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
	public Worker getWorker() {
		return worker;
	}
	public void setWorker(Worker worker) {
		this.worker = worker;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	
	
	
}
