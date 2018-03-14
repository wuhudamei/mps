
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity;


public class BizComplaintProblemType extends DataEntity<BizComplaintProblemType> {

	private static final long serialVersionUID = 1L;
	private Integer storeId;
	private String typeName;
	private Integer taskPackageTemplatId;
	private Integer packageId;
	private String packName;
	private String dealPersonType;
	private String isEnabled;
	private String packagetemplatname;
	private String ComplaintProblemTypeId;
	private String orderId;

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