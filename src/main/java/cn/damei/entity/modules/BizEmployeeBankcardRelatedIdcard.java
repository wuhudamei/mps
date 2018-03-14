
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;
import cn.damei.common.persistence.DataEntity2;


public class BizEmployeeBankcardRelatedIdcard extends DataEntity2<BizEmployeeBankcardRelatedIdcard> {
	
	private static final long serialVersionUID = 1L;
	private Integer employeeBankcardId;
	private String relatedIdcardNo;
	private String relatedName;
	
	public BizEmployeeBankcardRelatedIdcard() {
		super();
	}

	public BizEmployeeBankcardRelatedIdcard(Integer id){
		super(id);
	}

	public Integer getEmployeeBankcardId() {
		return employeeBankcardId;
	}

	public void setEmployeeBankcardId(Integer employeeBankcardId) {
		this.employeeBankcardId = employeeBankcardId;
	}
	
	@Length(min=0, max=64, message="关联身份证号 -- '长度必须介于 0 和 64 之间")
	public String getRelatedIdcardNo() {
		return relatedIdcardNo;
	}

	public void setRelatedIdcardNo(String relatedIdcardNo) {
		this.relatedIdcardNo = relatedIdcardNo;
	}
	
	@Length(min=0, max=64, message="关联身份证姓名 -- '长度必须介于 0 和 64 之间")
	public String getRelatedName() {
		return relatedName;
	}

	public void setRelatedName(String relatedName) {
		this.relatedName = relatedName;
	}
	
}