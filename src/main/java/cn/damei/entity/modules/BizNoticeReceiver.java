
package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;


public class BizNoticeReceiver extends DataEntity2<BizNoticeReceiver> {
	
	private static final long serialVersionUID = 1L;
	private Integer noticeId;
	private String receiverType;
	private String receiverRole;
	private Integer receiverEmployeeId;

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