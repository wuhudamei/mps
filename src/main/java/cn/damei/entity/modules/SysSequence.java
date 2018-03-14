
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity;


public class SysSequence extends DataEntity<SysSequence> {
	
	private static final long serialVersionUID = 1L;
	private String name;
	private String prefix;
	private String currentValue;
	private String increment;
	private String maxlength;
	
	public SysSequence() {
		super();
	}

	public SysSequence(String id){
		super(id);
	}

	@Length(min=1, max=50, message="name长度必须介于 1 和 50 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=1, max=50, message="prefix长度必须介于 1 和 50 之间")
	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	@Length(min=1, max=11, message="current_value长度必须介于 1 和 11 之间")
	public String getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(String currentValue) {
		this.currentValue = currentValue;
	}
	
	@Length(min=1, max=11, message="increment长度必须介于 1 和 11 之间")
	public String getIncrement() {
		return increment;
	}

	public void setIncrement(String increment) {
		this.increment = increment;
	}
	
	@Length(min=1, max=2, message="maxlength长度必须介于 1 和 2 之间")
	public String getMaxlength() {
		return maxlength;
	}

	public void setMaxlength(String maxlength) {
		this.maxlength = maxlength;
	}
	
}