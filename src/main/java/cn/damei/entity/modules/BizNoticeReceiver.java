/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;

/**
 * 消息公告接收人Entity
 * @author qww
 * @version 2017-01-14
 */
public class BizNoticeReceiver extends DataEntity2<BizNoticeReceiver> {
	
	private static final long serialVersionUID = 1L;
	private Integer noticeId;		// &aring;&hellip;&not;&aring;&lsquo;&Scaron;id -- '
	private String receiverType;		// &aelig;Ž&yen;&aelig;&rdquo;&para;&ccedil;&plusmn;&raquo;&aring;ž&lsaquo; -- '1.&egrave;&sect;&rsquo;&egrave;&permil;&sup2;&iuml;&frac14;&rsaquo;2.&aelig;&OElig;&Dagger;&aring;&reg;&scaron;&aring;&lsquo;&tilde;&aring;&middot;&yen;
	private String receiverRole;		// &aelig;Ž&yen;&aelig;&rdquo;&para;&egrave;&sect;&rsquo;&egrave;&permil;&sup2; -- '1.&eacute;&iexcl;&sup1;&ccedil;&rsaquo;&reg;&ccedil;&raquo;&ccedil;&dagger;&iuml;&frac14;&rsaquo;2.&egrave;&acute;&uml;&aelig;&pound;&euro;&aring;&lsquo;&tilde;&iuml;&frac14;&rsaquo;3.&aring;&middot;&yen;&auml;&ordm;&ordm;
	private Integer receiverEmployeeId;		// &aelig;Ž&yen;&aelig;&rdquo;&para;&aring;&lsquo;&tilde;&aring;&middot;&yen;id -- '

	public BizNoticeReceiver() {
		super();
	}

	public BizNoticeReceiver(Integer id){
		super(id);
	}

	public Integer getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(Integer noticeId) {
		this.noticeId = noticeId;
	}

	public String getReceiverType() {
		return receiverType;
	}

	public void setReceiverType(String receiverType) {
		this.receiverType = receiverType;
	}

	public String getReceiverRole() {
		return receiverRole;
	}

	public void setReceiverRole(String receiverRole) {
		this.receiverRole = receiverRole;
	}
	
	public Integer getReceiverEmployeeId() {
		return receiverEmployeeId;
	}

	public void setReceiverEmployeeId(Integer receiverEmployeeId) {
		this.receiverEmployeeId = receiverEmployeeId;
	}
}