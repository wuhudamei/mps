
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity;


public class BizQcMaxCount extends DataEntity<BizQcMaxCount> {
	
	private static final long serialVersionUID = 1L;
	private String storeId;
	private String qcMaxCount;
	
	public BizQcMaxCount() {
		super();
	}

	public BizQcMaxCount(String id){
		super(id);
	}

	@Length(min=0, max=11, message="门店长度必须介于 0 和 11 之间")
	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	
	@Length(min=0, max=11, message="最大约检数量长度必须介于 0 和 11 之间")
	public String getQcMaxCount() {
		return qcMaxCount;
	}

	public void setQcMaxCount(String qcMaxCount) {
		this.qcMaxCount = qcMaxCount;
	}
	
}