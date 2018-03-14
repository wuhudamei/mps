/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity2;

/**
 * 筒灯面积计算类Entity
 * @author lft
 * @version 2017-05-19
 */
public class BizMaterialsStandardNumberSquare extends DataEntity2<BizMaterialsStandardNumberSquare> {
	
	private static final long serialVersionUID = 1L;
	private Integer sId; //主键 为了resultmap
	private String materialsStandardId;		// 筒灯灯带id
	private double squareMin;		// 面积下限
	private double squareMax;		// 面积上限
	private String numberRuleCode;		// 面积计算规则
	private String numberRuleDescribe;		// 面积计算描述
	
	
	

	public Integer getsId() {
		return sId;
	}

	public void setsId(Integer sId) {
		this.sId = sId;
	}

	public BizMaterialsStandardNumberSquare() {
		super();
	}

	public BizMaterialsStandardNumberSquare(Integer id){
		super(id);
	}

	@Length(min=0, max=11, message="筒灯灯带id长度必须介于 0 和 11 之间")
	public String getMaterialsStandardId() {
		return materialsStandardId;
	}

	public void setMaterialsStandardId(String materialsStandardId) {
		this.materialsStandardId = materialsStandardId;
	}
	
	
	public double getSquareMin() {
		return squareMin;
	}

	public void setSquareMin(double squareMin) {
		this.squareMin = squareMin;
	}

	public double getSquareMax() {
		return squareMax;
	}

	public void setSquareMax(double squareMax) {
		this.squareMax = squareMax;
	}

	@Length(min=0, max=10, message="面积计算规则长度必须介于 0 和 10 之间")
	public String getNumberRuleCode() {
		return numberRuleCode;
	}

	public void setNumberRuleCode(String numberRuleCode) {
		this.numberRuleCode = numberRuleCode;
	}

	public String getNumberRuleDescribe() {
		return numberRuleDescribe;
	}

	public void setNumberRuleDescribe(String numberRuleDescribe) {
		this.numberRuleDescribe = numberRuleDescribe;
	}
	
	
	
}