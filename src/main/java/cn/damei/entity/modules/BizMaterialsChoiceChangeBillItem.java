
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity2;


public class BizMaterialsChoiceChangeBillItem extends DataEntity2<BizMaterialsChoiceChangeBillItem> {
	
	private static final long serialVersionUID = 1L;
	private Integer materialsChoiceChangeBillId;
	private String changeType;
	private String materialsChoiceType;
	private String materialsChoiceCategoryCode;
	private String brand;
	private String model;
	private String attribute;
	private String unit;
	private String spec;
	private String changeNumber;
	private Double unitPrice;
	private Double totalAmount;
	
	private String categoryName;
	
	
	public BizMaterialsChoiceChangeBillItem() {
		super();
	}

	public BizMaterialsChoiceChangeBillItem(Integer id){
		super(id);
	}

	public Integer getMaterialsChoiceChangeBillId() {
		return materialsChoiceChangeBillId;
	}

	public void setMaterialsChoiceChangeBillId(Integer materialsChoiceChangeBillId) {
		this.materialsChoiceChangeBillId = materialsChoiceChangeBillId;
	}
	
	@Length(min=0, max=10, message="变更类型长度必须介于 0 和 10 之间")
	public String getChangeType() {
		return changeType;
	}

	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}
	
	@Length(min=0, max=10, message="选材类型长度必须介于 0 和 10 之间")
	public String getMaterialsChoiceType() {
		return materialsChoiceType;
	}

	public void setMaterialsChoiceType(String materialsChoiceType) {
		this.materialsChoiceType = materialsChoiceType;
	}
	
	@Length(min=0, max=100, message="材料类目编码长度必须介于 0 和 100 之间")
	public String getMaterialsChoiceCategoryCode() {
		return materialsChoiceCategoryCode;
	}

	public void setMaterialsChoiceCategoryCode(String materialsChoiceCategoryCode) {
		this.materialsChoiceCategoryCode = materialsChoiceCategoryCode;
	}
	
	@Length(min=0, max=100, message="品牌长度必须介于 0 和 100 之间")
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	@Length(min=0, max=100, message="型号长度必须介于 0 和 100 之间")
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	
	@Length(min=0, max=100, message="属性长度必须介于 0 和 100 之间")
	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	
	@Length(min=0, max=100, message="单位长度必须介于 0 和 100 之间")
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	@Length(min=0, max=100, message="规格长度必须介于 0 和 100 之间")
	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}
	
	public String getChangeNumber() {
		return changeNumber;
	}

	public void setChangeNumber(String changeNumber) {
		this.changeNumber = changeNumber;
	}
	
	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
}