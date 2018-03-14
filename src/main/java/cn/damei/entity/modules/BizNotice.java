
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;
import java.util.Date;

import cn.damei.common.persistence.DataEntity2;


public class BizNotice extends DataEntity2<BizNotice> {
	
	private static final long serialVersionUID = 1L;
	private Integer storeId;
	private String projectMode;
	private String noticeType;
	private String noticeTitle;
	private String noticeContent;
	private String noticeStatus;
	private Date publishDatetime;
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