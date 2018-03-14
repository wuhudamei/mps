package cn.damei.entity.mobile.Worker;

import cn.damei.common.persistence.DataEntity2;

public class EmpGroup extends DataEntity2<EmpGroup>{

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
	private Integer state; //是否删除 1删除 0未删除
	private String groupId;//组长id
	
	
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
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
}
