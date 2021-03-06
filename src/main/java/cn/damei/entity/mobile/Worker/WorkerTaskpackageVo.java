package cn.damei.entity.mobile.Worker;

import cn.damei.common.persistence.DataEntity;

public class WorkerTaskpackageVo extends DataEntity<WorkerTaskpackageVo>{
	

	private static final long serialVersionUID = 1L;
	
	private Integer countCompleted;
	private Integer countDiscompleted;
	private String status;
	private Integer workerId;
	
	public Integer getWorkerId() {
		return workerId;
	}
	public void setWorkerId(Integer workerId) {
		this.workerId = workerId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getCountCompleted() {
		return countCompleted;
	}
	public void setCountCompleted(Integer countCompleted) {
		this.countCompleted = countCompleted;
	}
	public Integer getCountDiscompleted() {
		return countDiscompleted;
	}
	public void setCountDiscompleted(Integer countDiscompleted) {
		this.countDiscompleted = countDiscompleted;
	}
	
}
