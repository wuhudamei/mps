package cn.damei.entity.modules;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ProjectBulletinVo implements Serializable{
	private Integer id;
	private Integer count;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	
	
}
