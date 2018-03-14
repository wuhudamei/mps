package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity;


public class Seiralnum extends DataEntity<Seiralnum>{

	private static final long serialVersionUID = -4618926430366461231L;
	

	private String seiralNum;
	private String updateDateU;
	


	public String getSeiralNum() {
		return seiralNum;
	}
	public void setSeiralNum(String seiralNum) {
		this.seiralNum = seiralNum;
	}
	public String getUpdateDateU() {
		return updateDateU;
	}
	public void setUpdateDateU(String updateDateU) {
		this.updateDateU = updateDateU;
	}
	
}
