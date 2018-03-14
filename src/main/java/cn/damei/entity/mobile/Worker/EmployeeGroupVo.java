package cn.damei.entity.mobile.Worker;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

public class EmployeeGroupVo extends DataEntity2<EmployeeGroupVo>{

	/**
	 * @author wang
	 * 2016-10-17
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer star;		// 星级
	private String nps;		// NPS
	private Integer sort;		// 排名
	private Integer orderstop;		// 是否停单
	private String address;		// 现住址
	private String ordersarea;		// 接单区域
	private Date createtime;		// 创建时间
	private String createuser;		// 创建人
	private Integer state;		// 是否删除状态
	private Integer worktype;		// 工种
	private Integer groupid;		// 组长id
	private Integer storeid;		// 门店Id
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
	public Integer getOrderstop() {
		return orderstop;
	}
	public void setOrderstop(Integer orderstop) {
		this.orderstop = orderstop;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getOrdersarea() {
		return ordersarea;
	}
	public void setOrdersarea(String ordersarea) {
		this.ordersarea = ordersarea;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getCreateuser() {
		return createuser;
	}
	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getWorktype() {
		return worktype;
	}
	public void setWorktype(Integer worktype) {
		this.worktype = worktype;
	}
	public Integer getGroupid() {
		return groupid;
	}
	public void setGroupid(Integer groupid) {
		this.groupid = groupid;
	}
	public Integer getStoreid() {
		return storeid;
	}
	public void setStoreid(Integer storeid) {
		this.storeid = storeid;
	}
	
}
