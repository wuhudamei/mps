/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;



public class BizNoticeViewLog2  {
	
	private Integer noticeId;		
	private Integer viewEmployeeId;		
	private String viewDatetime;		
	private String realName;
	
	public BizNoticeViewLog2() {
		super();
	}

	public Integer getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(Integer noticeId) {
		this.noticeId = noticeId;
	}
	
	public Integer getViewEmployeeId() {
		return viewEmployeeId;
	}

	public void setViewEmployeeId(Integer viewEmployeeId) {
		this.viewEmployeeId = viewEmployeeId;
	}
	
	public String getViewDatetime() {
		return viewDatetime;
	}
	

	public void setViewDatetime(String viewDatetime) {
		this.viewDatetime = viewDatetime;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}
}