
package cn.damei.entity.modules;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import cn.damei.common.persistence.DataEntity2;


public class BizNoticeViewLog extends DataEntity2<BizNoticeViewLog> {
	
	private static final long serialVersionUID = 1L;
	private Integer noticeId;
	private Integer viewEmployeeId;
	private Date viewDatetime;

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