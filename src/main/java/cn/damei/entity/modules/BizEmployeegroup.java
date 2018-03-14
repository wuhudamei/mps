/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import cn.damei.common.persistence.DataEntity;

/**
 * 工人组管理Entity
 * @author qhy
 * @version 2016-09-01
 */
public class BizEmployeegroup extends DataEntity<BizEmployeegroup> {
	
	private static final long serialVersionUID = 1L;
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
	private String groupid;		// 组长id
	private String storeid;		// 门店Id
	private String taskpackageid;		// 任务包Id
	private String elactricationid;		// 归属工程部Id
	private List<BizEmgrouprelation>empGropRelation;//工人组关联关系表
	
	private String empId;//工人ID，保存关联关系使用。
	private String ratio;//工人工资比例。
	private String isLeader;//是否组长
	private String pointXy1;

	private String projectMode;//工程模式
	private String orderStopReasonType;   //停单原因类型
	private String orderStopReason;       //停单原因
	private String orderStopOperatorEmployeeId;//停单操作人
	private Date orderStopOperateDatetime;  //停单操作时间;

	private Integer reason;    //工人组星级调整原因
	private String changeDescribe;  //工人组星级调整修改说明
	
	private Date startChangeDatetime;
	
	private Date endChangeDatetime;
	
	
	
	public Date getStartChangeDatetime() {
		return startChangeDatetime;
	}

	public void setStartChangeDatetime(Date startChangeDatetime) {
		this.startChangeDatetime = startChangeDatetime;
	}

	public Date getEndChangeDatetime() {
		return endChangeDatetime;
	}

	public void setEndChangeDatetime(Date endChangeDatetime) {
		this.endChangeDatetime = endChangeDatetime;
	}

	public Integer getReason() {
		return reason;
	}

	public void setReason(Integer reason) {
		this.reason = reason;
	}

	public String getChangeDescribe() {
		return changeDescribe;
	}

	public void setChangeDescribe(String changeDescribe) {
		this.changeDescribe = changeDescribe;
	}

	public Date getOrderStopOperateDatetime() {
		return orderStopOperateDatetime;
	}

	public void setOrderStopOperateDatetime(Date orderStopOperateDatetime) {
		this.orderStopOperateDatetime = orderStopOperateDatetime;
	}

	public String getOrderStopReasonType() {
		return orderStopReasonType;
	}

	public void setOrderStopReasonType(String orderStopReasonType) {
		this.orderStopReasonType = orderStopReasonType;
	}

	public String getOrderStopReason() {
		return orderStopReason;
	}

	public void setOrderStopReason(String orderStopReason) {
		this.orderStopReason = orderStopReason;
	}

	public String getOrderStopOperatorEmployeeId() {
		return orderStopOperatorEmployeeId;
	}

	public void setOrderStopOperatorEmployeeId(String orderStopOperatorEmployeeId) {
		this.orderStopOperatorEmployeeId = orderStopOperatorEmployeeId;
	}

	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}

	public String getPointXy1() {
		return pointXy1;
	}

	public void setPointXy1(String pointXy1) {
		this.pointXy1 = pointXy1;
	}

	public BizEmployeegroup() {
		super();
	}

	public BizEmployeegroup(String id){
		super(id);
	}

	@Length(min=0, max=255, message="现住址长度必须介于 0 和 255 之间")
	public String getAddress() {
		return address;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreatetime() {
		return createtime;
	}
	@Length(min=0, max=64, message="创建人长度必须介于 0 和 64 之间")
	public String getCreateuser() {
		return createuser;
	}

	@Length(min=0, max=64, message="归属工程部Id长度必须介于 0 和 64 之间")
	public String getElactricationid() {
		return elactricationid;
	}

	public List<BizEmgrouprelation> getEmpGropRelation() {
		return empGropRelation;
	}

	public String getEmpId() {
		return empId;
	}

	@Length(min=0, max=64, message="组长id长度必须介于 0 和 64 之间")
	public String getGroupid() {
		return groupid;
	}

	public String getIsLeader() {
		return isLeader;
	}

	@Length(min=0, max=50, message="NPS长度必须介于 0 和 50 之间")
	public String getNps() {
		return nps;
	}

	@Length(min=0, max=255, message="接单区域长度必须介于 0 和 255 之间")
	public String getOrdersarea() {
		return ordersarea;
	}
	
	public Integer getOrderstop() {
		return orderstop;
	}

	public String getRatio() {
		return ratio;
	}
	
	public Integer getSort() {
		return sort;
	}

	public Integer getStar() {
		return star;
	}
	
	public Integer getState() {
		return state;
	}

	@Length(min=0, max=64, message="门店Id长度必须介于 0 和 64 之间")
	public String getStoreid() {
		return storeid;
	}
	
	@Length(min=0, max=255, message="任务包Id长度必须介于 0 和 255 之间")
	public String getTaskpackageid() {
		return taskpackageid;
	}

	public Integer getWorktype() {
		return worktype;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

	public void setElactricationid(String elactricationid) {
		this.elactricationid = elactricationid;
	}
	
	public void setEmpGropRelation(List<BizEmgrouprelation> empGropRelation) {
		this.empGropRelation = empGropRelation;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}
	
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	public void setIsLeader(String isLeader) {
		this.isLeader = isLeader;
	}
	
	public void setNps(String nps) {
		this.nps = nps;
	}

	public void setOrdersarea(String ordersarea) {
		this.ordersarea = ordersarea;
	}
	
	public void setOrderstop(Integer orderstop) {
		this.orderstop = orderstop;
	}

	public void setRatio(String ratio) {
		this.ratio = ratio;
	}
	
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public void setStar(Integer star) {
		this.star = star;
	}
	
	public void setState(Integer state) {
		this.state = state;
	}

	public void setStoreid(String storeid) {
		this.storeid = storeid;
	}
	
	public void setTaskpackageid(String taskpackageid) {
		this.taskpackageid = taskpackageid;
	}

	public void setWorktype(Integer worktype) {
		this.worktype = worktype;
	}
	
}