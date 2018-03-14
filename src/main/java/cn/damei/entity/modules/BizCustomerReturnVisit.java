
package cn.damei.entity.modules;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity;


public class BizCustomerReturnVisit extends DataEntity<BizCustomerReturnVisit> {
	
	private static final long serialVersionUID = 1L;
	private String storeId;
	private String returnVisitNode;
	private String projectNode;
	private String projectNodeCn;
	private String projectMode;
	private List<BizCustomerReturnVisitContent> questions;
	
	public BizCustomerReturnVisit() {
		super();
	}

	public BizCustomerReturnVisit(String id){
		super(id);
	}

	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}

	@Length(min=1, max=11, message="门店长度必须介于 1 和 11 之间")
	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	
	@Length(min=1, max=100, message="回访节点长度必须介于 1 和 100 之间")
	public String getReturnVisitNode() {
		return returnVisitNode;
	}

	public void setReturnVisitNode(String returnVisitNode) {
		this.returnVisitNode = returnVisitNode;
	}
	
	@Length(min=1, max=11, message="工程节点长度必须介于 1 和 11 之间")
	public String getProjectNode() {
		return projectNode;
	}

	public void setProjectNode(String projectNode) {
		this.projectNode = projectNode;
	}
	
	public String getProjectNodeCn() {
		return projectNodeCn;
	}

	public void setProjectNodeCn(String projectNodeCn) {
		this.projectNodeCn = projectNodeCn;
	}

	public List<BizCustomerReturnVisitContent> getQuestions() {
		return questions;
	}

	public void setQuestions(List<BizCustomerReturnVisitContent> questions) {
		this.questions = questions;
	}
	
}