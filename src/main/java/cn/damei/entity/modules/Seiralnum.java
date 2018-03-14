package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity;

/**
 * biz_order_taskPackage中order_task_package_code该字段
 * 任务包编号：RW2061609090001
 * 【时间戳八位数字+顺序码四位数字，按顺序生成；默认显示最大编号+1】系统生成不可重复。
 * @author llp
 */
public class Seiralnum extends DataEntity<Seiralnum>{

	private static final long serialVersionUID = -4618926430366461231L;
	
	//private String id;
	private String seiralNum;
	private String updateDateU;
	
	/*public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}*/

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
