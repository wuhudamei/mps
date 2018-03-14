/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;

import cn.damei.common.persistence.DataEntity;

/**
 * 星级承接量Entity
 * @author wyb
 * @version 2016-09-05
 */
public class BizStar extends DataEntity<BizStar> {
	
	private static final long serialVersionUID = 1L;
	private String storeId;		// 门店id
	private String star0;		// 试用
	private String star1;		// 一星
	private String description;		// 描述
	private String star2;		// 二星
	private String star3;		// 三星
	private String star4;		// 四星
	private String star5;		// 五星
	private String projectMode;	//工程模式
	
	public BizStar() {
		super();
	}

	public BizStar(String id){
		super(id);
	}

	@Length(min=1, max=64, message="门店id长度必须介于 1 和 64 之间")
	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	
	@Length(min=1, max=64, message="试用长度必须介于 1 和 64 之间")
	public String getStar0() {
		return star0;
	}

	public void setStar0(String star0) {
		this.star0 = star0;
	}
	
	@Length(min=1, max=64, message="一星长度必须介于 1 和 64 之间")
	public String getStar1() {
		return star1;
	}

	public void setStar1(String star1) {
		this.star1 = star1;
	}
	
	@Length(min=0, max=100, message="描述长度必须介于 0 和 100 之间")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Length(min=1, max=64, message="二星长度必须介于 1 和 64 之间")
	public String getStar2() {
		return star2;
	}

	public void setStar2(String star2) {
		this.star2 = star2;
	}
	
	@Length(min=1, max=64, message="三星长度必须介于 1 和 64 之间")
	public String getStar3() {
		return star3;
	}

	public void setStar3(String star3) {
		this.star3 = star3;
	}
	
	@Length(min=1, max=64, message="四星长度必须介于 1 和 64 之间")
	public String getStar4() {
		return star4;
	}

	public void setStar4(String star4) {
		this.star4 = star4;
	}
	
	@Length(min=1, max=64, message="五星长度必须介于 1 和 64 之间")
	public String getStar5() {
		return star5;
	}

	public void setStar5(String star5) {
		this.star5 = star5;
	}

	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}
	
}