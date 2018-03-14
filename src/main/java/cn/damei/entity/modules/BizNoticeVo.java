
package cn.damei.entity.modules;

import com.fasterxml.jackson.annotation.JsonFormat;
import cn.damei.common.persistence.DataEntity2;
import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.List;


public class BizNoticeVo extends DataEntity2<BizNoticeVo> {

	private static final long serialVersionUID = 1L;
	private Integer storeId;
	private String projectMode;
	private String noticeType;
	private String noticeTitle;
	private String noticeStatus;
	private Date publishDatetime;
	private String receiverRoleName;
	private Integer totalReceiverNum;
	private Integer alreadyReceiverNum;
	private List<String> projectModeList;
	private Integer dateDiff;
	private Date nowDate;

	public BizNoticeVo() {
		super();
	}

	public BizNoticeVo(Integer id){
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

	@Length(min=0, max=10, message="&aring;&hellip;&not;&aring;&lsquo;&Scaron;&ccedil;&Scaron;&para;&aelig;&euro; -- '长度必须介于 0 和 10 之间")
	public String getNoticeStatus() {
		return noticeStatus;
	}

	public void setNoticeStatus(String noticeStatus) {
		this.noticeStatus = noticeStatus;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPublishDatetime() {
		return publishDatetime;
	}

	public void setPublishDatetime(Date publishDatetime) {
		this.publishDatetime = publishDatetime;
	}

	public String getReceiverRoleName() {
		return receiverRoleName;
	}

	public void setReceiverRoleName(String receiverRoleName) {
		this.receiverRoleName = receiverRoleName;
	}

	public Integer getTotalReceiverNum() {
		return totalReceiverNum;
	}

	public void setTotalReceiverNum(Integer totalReceiverNum) {
		this.totalReceiverNum = totalReceiverNum;
	}

	public Integer getAlreadyReceiverNum() {
		return alreadyReceiverNum;
	}

	public void setAlreadyReceiverNum(Integer alreadyReceiverNum) {
		this.alreadyReceiverNum = alreadyReceiverNum;
	}

	public List<String> getProjectModeList() {
		return projectModeList;
	}

	public void setProjectModeList(List<String> projectModeList) {
		this.projectModeList = projectModeList;
	}

	public Integer getDateDiff() {
		return dateDiff;
	}

	public void setDateDiff(Integer dateDiff) {
		this.dateDiff = dateDiff;
	}

	public Date getNowDate() {
		return nowDate;
	}

	public void setNowDate(Date nowDate) {
		this.nowDate = nowDate;
	}
}