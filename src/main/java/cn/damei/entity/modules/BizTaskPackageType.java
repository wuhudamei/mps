
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity;


public class BizTaskPackageType extends DataEntity<BizTaskPackageType> {
	
	private static final long serialVersionUID = 1L;
	private String name;
	private String status;
	
	public BizTaskPackageType() {
		super();
	}

	public BizTaskPackageType(String id){
		super(id);
	}

	@Length(min=0, max=64, message="任务包类型名称长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=1, message="状态;0:停用，1：启用长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}