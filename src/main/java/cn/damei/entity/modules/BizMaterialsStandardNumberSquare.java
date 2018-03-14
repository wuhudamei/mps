
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity2;


public class BizMaterialsStandardNumberSquare extends DataEntity2<BizMaterialsStandardNumberSquare> {
	
	private static final long serialVersionUID = 1L;
	private Integer sId;
	private String materialsStandardId;
	private double squareMin;
	private double squareMax;
	private String numberRuleCode;
	private String numberRuleDescribe;
	
	
	

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