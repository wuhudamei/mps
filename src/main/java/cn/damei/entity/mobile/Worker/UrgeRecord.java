package cn.damei.entity.mobile.Worker;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

/** 
* @author 梅浩   meihao@zzhyun.cn: 
* @version 创建时间：2016年10月12日 下午5:44:49 
*  app工人组长催促验收
*/

public class UrgeRecord   extends DataEntity2<Message>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Integer packId;//   任务包id
	private Integer urgeMan;//催促人  ->工人组长
	private Integer beUrgeMan;//被催促人  ->项目经理
	private Date urgeTime;//催促时间
	public Date getUrgeTime() {
		return urgeTime;
	}
	public void setUrgeTime(Date urgeTime) {
		this.urgeTime = urgeTime;
	}
	public Integer getPackId() {
		return packId;
	}
	public void setPackId(Integer packId) {
		this.packId = packId;
	}
	public Integer getUrgeMan() {
		return urgeMan;
	}
	public void setUrgeMan(Integer urgeMan) {
		this.urgeMan = urgeMan;
	}
	public Integer getBeUrgeMan() {
		return beUrgeMan;
	}
	public void setBeUrgeMan(Integer beUrgeMan) {
		this.beUrgeMan = beUrgeMan;
	}
	
	

}
