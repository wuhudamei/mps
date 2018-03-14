/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity;

/**
 * 工程投诉Entity
 * 
 * @author mh
 * @version 2017-07-03
 */
public class BizComplaintProblemType extends DataEntity<BizComplaintProblemType> {

	private static final long serialVersionUID = 1L;
	private Integer storeId; // 门店id
	private String typeName; // 分类名称
	private Integer taskPackageTemplatId; // 对应任务包(模板)id
	private Integer packageId; // 对应任务包id
	private String packName;
	private String dealPersonType; // 处理人类型(1:manager 2:m+w 3:p)
	private String isEnabled; // 是否启用
	private String packagetemplatname; // 任務包(模板)名字
	private String ComplaintProblemTypeId; // 问题分类ID
	private String orderId; // 订单ID

	public String getPackName() {
		return packName;
	}

	public String getPackagetemplatname() {
		return packagetemplatname;
	}

	public String getComplaintProblemTypeId() {
		return ComplaintProblemTypeId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public void setComplaintProblemTypeId(String complaintProblemTypeId) {
		ComplaintProblemTypeId = complaintProblemTypeId;
	}

	public void setPackagetemplatname(String packagetemplatname) {
		this.packagetemplatname = packagetemplatname;
	}

	public void setPackName(String packName) {
		this.packName = packName;
	}

	public Integer getPackageId() {
		return packageId;
	}

	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}

	public BizComplaintProblemType() {
		super();
	}

	public BizComplaintProblemType(String id) {
		super(id);
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	@Length(min = 0, max = 100, message = "分类名称长度必须介于 0 和 100 之间")
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Integer getTaskPackageTemplatId() {
		return taskPackageTemplatId;
	}

	public void setTaskPackageTemplatId(Integer taskPackageTemplatId) {
		this.taskPackageTemplatId = taskPackageTemplatId;
	}

	@Length(min = 0, max = 10, message = "处理人类型(1:manager 2:m+w  3:p)长度必须介于 0 和 10 之间")
	public String getDealPersonType() {
		return dealPersonType;
	}

	public void setDealPersonType(String dealPersonType) {
		this.dealPersonType = dealPersonType;
	}

	@Length(min = 0, max = 1, message = "是否启用长度必须介于 0 和 1 之间")
	public String getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(String isEnabled) {
		this.isEnabled = isEnabled;
	}

}