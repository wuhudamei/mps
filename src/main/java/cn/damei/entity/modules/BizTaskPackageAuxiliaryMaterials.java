/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity;

/**
 * 任务包辅料对照表管理Entity
 * @author wangchao
 * @version 2016-09-09
 */
public class BizTaskPackageAuxiliaryMaterials extends DataEntity<BizTaskPackageAuxiliaryMaterials> {
	
	private static final long serialVersionUID = 1L;
	private String storeId;		// 门店
	private String projectMode; //工程模式
	private String bizTaskPackageTemplatId;		// 任务包模板id
	private String bizAuxiliaryMaterialsNo;		// 辅料code
	private String bizAuxiliaryMaterialsName;// 辅料名称
	
	public BizTaskPackageAuxiliaryMaterials() {
		super();
	}

	public BizTaskPackageAuxiliaryMaterials(String id){
		super(id);
	}

	
	public String getBizAuxiliaryMaterialsName() {
		return bizAuxiliaryMaterialsName;
	}

	public void setBizAuxiliaryMaterialsName(String bizAuxiliaryMaterialsName) {
		this.bizAuxiliaryMaterialsName = bizAuxiliaryMaterialsName;
	}

	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}

	@Length(min=0, max=11, message="门店长度必须介于 0 和 11 之间")
	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	
	@Length(min=0, max=11, message="任务包模板id长度必须介于 0 和 11 之间")
	public String getBizTaskPackageTemplatId() {
		return bizTaskPackageTemplatId;
	}

	public void setBizTaskPackageTemplatId(String bizTaskPackageTemplatId) {
		this.bizTaskPackageTemplatId = bizTaskPackageTemplatId;
	}
	
	@Length(min=0, max=11, message="辅料code长度必须介于 0 和 11 之间")
	public String getBizAuxiliaryMaterialsNo() {
		return bizAuxiliaryMaterialsNo;
	}

	public void setBizAuxiliaryMaterialsNo(String bizAuxiliaryMaterialsNo) {
		this.bizAuxiliaryMaterialsNo = bizAuxiliaryMaterialsNo;
	}
	
}