/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;

/**
 * 付款单付款尾款节点设置Entity
 * @author www
 * @version 2016-11-15
 */
public class BizTaskPackageTemplatCheckNodeRel extends DataEntity2<BizTaskPackageTemplatCheckNodeRel> {
	
	private static final long serialVersionUID = 1L;
	private Integer storeId;		// 门店id
	private Integer projectMode; //工程模式
	private Integer taskPackageId;		// 任务包id
	private String packageName;
	private String qcCheckNodeName;
	private Integer qcCheckNodeId;		// 约检节点id
	private String status;		// 状态 
	
	public BizTaskPackageTemplatCheckNodeRel() {
		super();
	}

	public BizTaskPackageTemplatCheckNodeRel(Integer id){
		super(id);
	}
	

	public Integer getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(Integer projectMode) {
		this.projectMode = projectMode;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	
	public Integer getTaskPackageId() {
		return taskPackageId;
	}

	public void setTaskPackageId(Integer taskPackageId) {
		this.taskPackageId = taskPackageId;
	}
	
	public Integer getQcCheckNodeId() {
		return qcCheckNodeId;
	}

	public void setQcCheckNodeId(Integer qcCheckNodeId) {
		this.qcCheckNodeId = qcCheckNodeId;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	
	public String getQcCheckNodeName() {
		return qcCheckNodeName;
	}

	public void setQcCheckNodeName(String qcCheckNodeName) {
		this.qcCheckNodeName = qcCheckNodeName;
	}
}