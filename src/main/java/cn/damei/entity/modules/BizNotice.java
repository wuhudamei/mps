/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;
import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

/**
 * 公告Entity
 * @author qww
 * @version 2017-01-14
 */
public class BizNotice extends DataEntity2<BizNotice> {
	
	private static final long serialVersionUID = 1L;
	private Integer storeId;		// &eacute;&mdash;&uml;&aring;&ordm;&mdash;id -- '
	private String projectMode;		// &aring;&middot;&yen;&ccedil;&uml;&lsaquo;&aelig;&uml;&iexcl;&aring;&frac14; -- '
	private String noticeType;		// &aring;&hellip;&not;&aring;&lsquo;&Scaron;&ccedil;&plusmn;&raquo;&aring;ž&lsaquo; -- '
	private String noticeTitle;		// &aring;&hellip;&not;&aring;&lsquo;&Scaron;&aelig;&nbsp;&Dagger;&eacute;&cent;&tilde; -- '
	private String noticeContent;		// &aring;&hellip;&not;&aring;&lsquo;&Scaron;&aring;&dagger;&hellip;&aring;&reg;&sup1; -- '
	private String noticeStatus;		// &aring;&hellip;&not;&aring;&lsquo;&Scaron;&ccedil;&Scaron;&para;&aelig;&euro; -- '
	private Date publishDatetime;		// &aring;&lsquo;&aring;&cedil;&fnof;&aelig;&mdash;&yen;&aelig;&oelig;&Yuml;&aelig;&mdash;&para;&eacute;&mdash;&acute; -- '
	private String receiverRoleId;

	private String receiverRole;
	private Integer[] receiverEmployeeIds;
	private Integer receiverEmployeeId;
	private Integer yesReadNum;
	
	public BizNotice() {
		super();
	}

	public BizNotice(Integer id){
		super(id);
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	
	@Length(min=0, max=10, message="&aring;&middot;&yen;&ccedil;&uml;&lsaquo;&aelig;&uml;&iexcl;&aring;&frac14; -- '长度必须介于 0 和 10 之间")
	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}
	
	@Length(min=0, max=10, message="&aring;&hellip;&not;&aring;&lsquo;&Scaron;&ccedil;&plusmn;&raquo;&aring;ž&lsaquo; -- '长度必须介于 0 和 10 之间")
	public String getNoticeType() {
		return noticeType;
	}

	public void setNoticeType(String noticeType) {
		this.noticeType = noticeType;
	}
	
	@Length(min=0, max=500, message="&aring;&hellip;&not;&aring;&lsquo;&Scaron;&aelig;&nbsp;&Dagger;&eacute;&cent;&tilde; -- '长度必须介于 0 和 500 之间")
	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}
	
	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}
	
	@Length(min=0, max=10, message="&aring;&hellip;&not;&aring;&lsquo;&Scaron;&ccedil;&Scaron;&para;&aelig;&euro; -- '长度必须介于 0 和 10 之间")
	public String getNoticeStatus() {
		return noticeStatus;
	}

	public void setNoticeStatus(String noticeStatus) {
		this.noticeStatus = noticeStatus;
	}
	
	/*@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")*/
	public Date getPublishDatetime() {
		return publishDatetime;
	}

	public void setPublishDatetime(Date publishDatetime) {
		this.publishDatetime = publishDatetime;
	}

	public String getReceiverRole() {
		return receiverRole;
	}

	public void setReceiverRole(String receiverRole) {
		this.receiverRole = receiverRole;
	}

	public Integer[] getReceiverEmployeeIds() {
		return receiverEmployeeIds;
	}

	public void setReceiverEmployeeIds(Integer[] receiverEmployeeIds) {
		this.receiverEmployeeIds = receiverEmployeeIds;
	}

	public String getReceiverRoleId() {
		return receiverRoleId;
	}

	public void setReceiverRoleId(String receiverRoleId) {
		this.receiverRoleId = receiverRoleId;
	}

	public Integer getReceiverEmployeeId() {
		return receiverEmployeeId;
	}

	public void setReceiverEmployeeId(Integer receiverEmployeeId) {
		this.receiverEmployeeId = receiverEmployeeId;
	}

	public Integer getYesReadNum() {
		return yesReadNum;
	}

	public void setYesReadNum(Integer yesReadNum) {
		this.yesReadNum = yesReadNum;
	}
}