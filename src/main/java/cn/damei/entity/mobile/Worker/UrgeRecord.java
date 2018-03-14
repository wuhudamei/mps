package cn.damei.entity.mobile.Worker;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;



public class UrgeRecord   extends DataEntity2<Message>{


	private static final long serialVersionUID = 1L;
	
	
	private Integer packId;
	private Integer urgeMan;
	private Integer beUrgeMan;
	private Date urgeTime;
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
