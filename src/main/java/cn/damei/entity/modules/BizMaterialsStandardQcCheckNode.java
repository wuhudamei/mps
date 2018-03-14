/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity2;

/**
 * 标化筒灯约检节点配置Entity
 * @author lft
 * @version 2017-05-25
 */
public class BizMaterialsStandardQcCheckNode extends DataEntity2<BizMaterialsStandardQcCheckNode> {
	
	private static final long serialVersionUID = 1L;
	private Integer storeId;		// 门店
	private String storeName;  //门店名字
	private String projectMode;		// 工程模式
	private String projectModeName;		// 工程模式名字
	private String materialType;		// 材料类型
	private String materialName;		// 材料类型 名字
	private Integer qcCheckNodeId;		// 约检节点
	private String qcCheckNodeIdName;		// 约检节点名字
	
	
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