package cn.damei.entity.mobile.Manager;


import cn.damei.common.persistence.DataEntity2;

public class ChecksizeType extends DataEntity2<ChecksizeType>{

	
	private static final long serialVersionUID = 1L;
	

	private String type;
	private String name;
	private String daysPlanChecksize;
	private String orderInstallItemId;
	
	public String getDaysPlanChecksize() {
		return daysPlanChecksize;
	}
	public void setDaysPlanChecksize(String daysPlanChecksize) {
		this.daysPlanChecksize = daysPlanChecksize;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
	
	
	
}
