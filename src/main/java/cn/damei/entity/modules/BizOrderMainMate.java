/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity2;
import cn.damei.common.utils.excel.annotation.ExcelField;

/**
 * 主材订单Entity
 * 
 * @author qww
 * @version 2016-10-09
 */
public class BizOrderMainMate extends DataEntity2<BizOrderMainMate> {

	private static final long serialVersionUID = 1L;
	private Integer orderId; // 订单id -- '
	private String mainMateType; // 主材类型 -- '4墙砖；3地砖；开关面板
	private String orderNumber; // 订单编号
	private String position; // 位置 -- '
	private String brandCombo; // 品牌套餐 -- '
	private String model; // 型号 -- '
	private String attribute; // 属性 -- '
	private String supplier; // 供应商 -- '
	private String specification; // 规格 -- '
	private String unit; // 单位 -- '
	private String count; // 数量 -- '
	private Double lossxs; // 损耗系数 -- '
	private String includLossCount; // 含损耗数量 -- '
	private String applyCounta; // 实发数量 -- '
	private String purchaseCount; // 已申请数量'
	private String iscountsquare; // 是否计算面积 1为是 0 为否
	private String unitsquare; // 面积
	private String remarks; // 备注
	private Date createDatez; // 创建时间

	public BizOrderMainMate() {
		super();
	}

	public BizOrderMainMate(Integer id) {
		super(id);
	}

	@Length(min = 0, max = 11, message = "订单id -- '长度必须介于 0 和 11 之间")
	public Integer getOrderId() {
		return orderId;
	}

	public String getIscountsquare() {
		return iscountsquare;
	}

	public void setIscountsquare(String iscountsquare) {
		this.iscountsquare = iscountsquare;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public Date getCreateDatez() {
		return createDatez;
	}

	public void setCreateDatez(Date createDatez) {
		this.createDatez = createDatez;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getUnitsquare() {
		return unitsquare;
	}

	public void setUnitsquare(String unitsquare) {
		this.unitsquare = unitsquare;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getPurchaseCount() {
		return purchaseCount;
	}

	public void setPurchaseCount(String purchaseCount) {
		this.purchaseCount = purchaseCount;
	}

	@Length(min = 0, max = 10, message = "主材类型 -- '墙砖；地砖；开关面板长度必须介于 0 和 10 之间")
	@ExcelField(title = "项目名称", dictType = "main_material_type", align = 2, sort = 10)
	public String getMainMateType() {
		return mainMateType;
	}

	public void setMainMateType(String mainMateType) {
		this.mainMateType = mainMateType;
	}

	@Length(min = 0, max = 50, message = "位置 -- '长度必须介于 0 和 50 之间")
	@ExcelField(title = "位置", align = 2, sort = 15)
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Length(min = 0, max = 100, message = "品牌套餐 -- '长度必须介于 0 和 100 之间")
	@ExcelField(title = "品牌套餐", align = 2, sort = 20)
	public String getBrandCombo() {
		return brandCombo;
	}

	public void setBrandCombo(String brandCombo) {
		this.brandCombo = brandCombo;
	}

	@Length(min = 0, max = 50, message = "型号 -- '长度必须介于 0 和 50 之间")
	@ExcelField(title = "型号", align = 2, sort = 25)
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	@ExcelField(title = "属性", align = 2, sort = 30)
	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	@ExcelField(title = "供应商", align = 2, sort = 35)
	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	@Length(min = 0, max = 50, message = "规格 -- '长度必须介于 0 和 50 之间")
	@ExcelField(title = "规格", align = 2, sort = 40)
	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	@Length(min = 0, max = 50, message = "单位 -- '长度必须介于 0 和 50 之间")
	@ExcelField(title = "单位", align = 2, sort = 50)
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@ExcelField(title = "数量", align = 2, sort = 60)
	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	@ExcelField(title = "损耗系数", align = 2, sort = 70)
	public Double getLossxs() {
		return lossxs;
	}

	public void setLossxs(Double lossxs) {
		this.lossxs = lossxs;
	}

	@ExcelField(title = "含损耗量", align = 2, sort = 80)
	public String getIncludLossCount() {
		return includLossCount;
	}

	public void setIncludLossCount(String includLossCount) {
		this.includLossCount = includLossCount;
	}

	public String getApplyCounta() {
		return applyCounta;
	}

	public void setApplyCounta(String applyCounta) {
		this.applyCounta = applyCounta;
	}

	public String getRemarks() {
		return remarks;
	}

	// @ExcelField(title = "备注", align = 2, sort = 90)
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}