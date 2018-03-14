
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity;


public class BizTaskPackageAuxiliaryMaterials extends DataEntity<BizTaskPackageAuxiliaryMaterials> {
	
	private static final long serialVersionUID = 1L;
	private String storeId;
	private String projectMode;
	private String bizTaskPackageTemplatId;
	private String bizAuxiliaryMaterialsNo;
	private String bizAuxiliaryMaterialsName;
	
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