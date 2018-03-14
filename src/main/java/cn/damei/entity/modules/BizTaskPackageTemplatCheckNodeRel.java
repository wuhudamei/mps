
package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;


public class BizTaskPackageTemplatCheckNodeRel extends DataEntity2<BizTaskPackageTemplatCheckNodeRel> {
	
	private static final long serialVersionUID = 1L;
	private Integer storeId;
	private Integer projectMode;
	private Integer taskPackageId;
	private String packageName;
	private String qcCheckNodeName;
	private Integer qcCheckNodeId;
	private String status;
	
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