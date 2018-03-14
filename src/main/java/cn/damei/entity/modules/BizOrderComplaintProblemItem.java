
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity;


public class BizOrderComplaintProblemItem extends DataEntity<BizOrderComplaintProblemItem> {
	
	private static final long serialVersionUID = 1L;
	private String orderComplaintProblemId;
	private String complaintProblemItemId;

	private String  itemName;

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public BizOrderComplaintProblemItem() {
		super();
	}

	public BizOrderComplaintProblemItem(String id){
		super(id);
	}

	@Length(min=0, max=11, message="工程问题长度必须介于 0 和 11 之间")
	public String getOrderComplaintProblemId() {
		return orderComplaintProblemId;
	}

	public void setOrderComplaintProblemId(String orderComplaintProblemId) {
		this.orderComplaintProblemId = orderComplaintProblemId;
	}
	
	@Length(min=0, max=11, message="问题事项ID长度必须介于 0 和 11 之间")
	public String getComplaintProblemItemId() {
		return complaintProblemItemId;
	}

	public void setComplaintProblemItemId(String complaintProblemItemId) {
		this.complaintProblemItemId = complaintProblemItemId;
	}
	
}