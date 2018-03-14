
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity2;


public class BizMaterialsStandardQcCheckNode extends DataEntity2<BizMaterialsStandardQcCheckNode> {
	
	private static final long serialVersionUID = 1L;
	private Integer storeId;
	private String storeName;
	private String projectMode;
	private String projectModeName;
	private String materialType;
	private String materialName;
	private Integer qcCheckNodeId;
	private String qcCheckNodeIdName;
	
	
	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getProjectModeName() {
		return projectModeName;
	}

	public void setProjectModeName(String projectModeName) {
		this.projectModeName = projectModeName;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	
	public String getQcCheckNodeIdName() {
		return qcCheckNodeIdName;
	}

	public void setQcCheckNodeIdName(String qcCheckNodeIdName) {
		this.qcCheckNodeIdName = qcCheckNodeIdName;
	}

	public BizMaterialsStandardQcCheckNode() {
		super();
	}

	public BizMaterialsStandardQcCheckNode(Integer id){
		super(id);
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	
	@Length(min=0, max=10, message="工程模式长度必须介于 0 和 10 之间")
	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}
	
	@Length(min=0, max=10, message="材料类型长度必须介于 0 和 10 之间")
	public String getMaterialType() {
		return materialType;
	}

	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}
	
	public Integer getQcCheckNodeId() {
		return qcCheckNodeId;
	}

	public void setQcCheckNodeId(Integer qcCheckNodeId) {
		this.qcCheckNodeId = qcCheckNodeId;
	}
	
}