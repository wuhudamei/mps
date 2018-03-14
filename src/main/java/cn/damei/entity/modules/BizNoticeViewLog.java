/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import cn.damei.common.persistence.DataEntity2;

/**
 * 消息公告查看日志Entity
 * @author qww
 * @version 2017-01-14
 */
public class BizNoticeViewLog extends DataEntity2<BizNoticeViewLog> {
	
	private static final long serialVersionUID = 1L;
	private Integer noticeId;		// &aring;&hellip;&not;&aring;&lsquo;&Scaron;id -- '
	private Integer viewEmployeeId;		// &aelig;&Yuml;&yen;&ccedil;&oelig;&lsaquo;&aring;&lsquo;&tilde;&aring;&middot;&yen;id -- '
	private Date viewDatetime;		// &aelig;&Yuml;&yen;&ccedil;&oelig;&lsaquo;&aelig;&mdash;&yen;&aelig;&oelig;&Yuml;&aelig;&mdash;&para;&eacute;&mdash;&acute; -- '

	private String realName;
	
	public BizNoticeViewLog() {
		super();
	}

	public BizNoticeViewLog(Integer id){
		super(id);
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
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getViewDatetime() {
		return viewDatetime;
	}

	public void setViewDatetime(Date viewDatetime) {
		this.viewDatetime = viewDatetime;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}
}