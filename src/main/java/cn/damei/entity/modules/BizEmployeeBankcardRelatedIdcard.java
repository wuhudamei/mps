/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;
import cn.damei.common.persistence.DataEntity2;

/**
 * 员工身份证关联Entity
 * @author qww
 * @version 2016-10-31
 */
public class BizEmployeeBankcardRelatedIdcard extends DataEntity2<BizEmployeeBankcardRelatedIdcard> {
	
	private static final long serialVersionUID = 1L;
	private Integer employeeBankcardId;		// 员工银行卡id -- '
	private String relatedIdcardNo;		// 关联身份证号 -- '
	private String relatedName;		// 关联身份证姓名 -- '
	
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