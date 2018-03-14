/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity;

/**
 * 工程事项和工程问题对照表Entity
 * @author ztw
 * @version 2017-07-06
 */
public class BizOrderComplaintProblemItem extends DataEntity<BizOrderComplaintProblemItem> {
	
	private static final long serialVersionUID = 1L;
	private String orderComplaintProblemId;		// 工程问题
	private String complaintProblemItemId;		// 问题事项ID

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