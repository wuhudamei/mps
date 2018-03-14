package cn.damei.entity.mobile.Manager;

import cn.damei.common.persistence.DataEntity2;

import java.util.Objects;

/**
 * @author 梅浩 meihao@zzhyun.cn:
 * @version 创建时间：2016年9月28日 上午11:18:04 辅料Vo
 */

/**
 * @author hyh
 *
 */
public class AuxiliaryVo extends DataEntity2<AuxiliaryVo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 爷爷类 辅料id
	private Integer orderId;// 相关订单id -->biz_order
	private Integer purchaseId;// 采购单id
	private String auxiMateCode;// 辅材编号

	private String remarks;// 备注--> 登录的项目经理姓名+手机号
	private Integer managerId;// 相关经理id -->employee item_manager_id
	private String managerName;// 相关经理姓名 -->employee item_customer
	private String managerPhone;// 相关经理手机号---> 手机号和姓名映射到remarks上

	private String pic;// 辅料图片 -->biz_auxiliary_material
	private String name;// 辅料名称-->biz_auxiliary_material
	private String specifications;// 辅料规格-->biz_auxiliary_material
	// 说明: 跟 个 箱 捆 等 -->字典表 表名: sys_dict 字段名:biz_unit
	private String unit;// 单位

	// 说明: 从供应商表 获取 工人价 -->表名: biz_auxiliarys_supplier 字段:labor_price
	private Double price;// 辅料价格
	
	// 说明: 从供应商表 获取 工人价 -->表名: biz_auxiliarys_supplier 字段:supplier_price
	private Double supplierPrice;//供应商价格
	
	// 说明: 从供应商表 获取 工人价 -->表名: biz_auxiliarys_supplier 字段:wangzhen_price
	private Double wangzhenPrice;//网真价格（门店价格）

	// 说明: -->字典表 水工 电工 木工 瓦工 油工 -->表名:sys_dict 字段: emp_work_type
	private String workType;// 工种
	private String workTypeName;// 工种名称

	// 说明: -->分类表 有电料 给水 墙地砖 电工 等 --> 表名:biz_material_category 字段:
	// id+category_name
	// 条件:material_type_id =2 (辅材)
	private Integer categoryId;// 分类id
	private String categoryName;//分类名称

	private String submmitStatus;
	
	private String brand ; //品牌
	private Integer receivedAuxiMateCount;//已收货数量(默认是0,因为暂时没有收货)   
	private Integer owedAuxiMateCount;	// 欠货数量(默认是申请的数量, 逻辑意思也就是申请了多少 供应商还没给货,所以欠货数量)
	private Integer auxiMateCount;	// 辅料数量(申请的数量)
	
	private Integer count;// 数量 -->auxi_mate_count

	private Double totalPrice;// 总价 -->数量*单价 count*price
	

	private String isSandCement;//是否是沙子水泥
	private Integer storeId;
	
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		AuxiliaryVo that = (AuxiliaryVo) o;
		return Objects.equals(orderId, that.orderId) &&
				Objects.equals(purchaseId, that.purchaseId) &&
				Objects.equals(auxiMateCode, that.auxiMateCode) &&
				Objects.equals(remarks, that.remarks) &&
				Objects.equals(storeId, that.storeId) &&
				Objects.equals(managerId, that.managerId) &&
				Objects.equals(managerName, that.managerName) &&
				Objects.equals(managerPhone, that.managerPhone) &&
				Objects.equals(pic, that.pic) &&
				Objects.equals(name, that.name) &&
				Objects.equals(specifications, that.specifications) &&
				Objects.equals(unit, that.unit) &&
				Objects.equals(price, that.price) &&
				Objects.equals(supplierPrice, that.supplierPrice) &&
				Objects.equals(wangzhenPrice, that.wangzhenPrice) &&
				Objects.equals(workType, that.workType) &&
				Objects.equals(categoryId, that.categoryId) &&
				Objects.equals(categoryName, that.categoryName) &&
				Objects.equals(submmitStatus, that.submmitStatus) &&
				Objects.equals(brand, that.brand) &&
				Objects.equals(receivedAuxiMateCount, that.receivedAuxiMateCount) &&
				Objects.equals(owedAuxiMateCount, that.owedAuxiMateCount) &&
				Objects.equals(count, that.count) &&
				Objects.equals(totalPrice, that.totalPrice);
	}

	@Override
	public int hashCode() {
		return Objects.hash(orderId, purchaseId, auxiMateCode, remarks, storeId, managerId, managerName, managerPhone, pic, name, specifications, unit, price, workType, categoryId, categoryName, submmitStatus, brand, receivedAuxiMateCount, owedAuxiMateCount, count, totalPrice);
	}
	public Integer getStoreId() {
		return storeId;
	}
	
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public Integer getReceivedAuxiMateCount() {
		return receivedAuxiMateCount;
	}

	public void setReceivedAuxiMateCount(Integer receivedAuxiMateCount) {
		this.receivedAuxiMateCount = receivedAuxiMateCount;
	}

	public Integer getOwedAuxiMateCount() {
		return owedAuxiMateCount;
	}

	public void setOwedAuxiMateCount(Integer owedAuxiMateCount) {
		this.owedAuxiMateCount = owedAuxiMateCount;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getSubmmitStatus() {
		return submmitStatus;
	}

	public void setSubmmitStatus(String submmitStatus) {
		this.submmitStatus = submmitStatus;
	}

	
	
	public String getIsSandCement() {
		return isSandCement;
	}

	public void setIsSandCement(String isSandCement) {
		this.isSandCement = isSandCement;
	}

	public Integer getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(Integer purchaseId) {
		this.purchaseId = purchaseId;
	}

	public String getAuxiMateCode() {
		return auxiMateCode;
	}

	public void setAuxiMateCode(String auxiMateCode) {
		this.auxiMateCode = auxiMateCode;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getManagerPhone() {
		return managerPhone;
	}

	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String getSpecifications() {
		return specifications;
	}

	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getWorkType() {
		return workType;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getAuxiMateCount() {
		return auxiMateCount;
	}

	public void setAuxiMateCount(Integer auxiMateCount) {
		this.auxiMateCount = auxiMateCount;
	}

	public Double getSupplierPrice() {
		return supplierPrice;
	}

	public void setSupplierPrice(Double supplierPrice) {
		this.supplierPrice = supplierPrice;
	}

	public Double getWangzhenPrice() {
		return wangzhenPrice;
	}

	public void setWangzhenPrice(Double wangzhenPrice) {
		this.wangzhenPrice = wangzhenPrice;
	}

	public String getWorkTypeName() {
		return workTypeName;
	}

	public void setWorkTypeName(String workTypeName) {
		this.workTypeName = workTypeName;
	}
	

}
