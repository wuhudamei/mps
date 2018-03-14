/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;


import cn.damei.common.persistence.DataEntity;

/**
 * 复尺配置表Entity
 * @author ztw
 * @version 2017-08-02
 */
public class RecheckCnfg extends DataEntity<RecheckCnfg> {
	
	private static final long serialVersionUID = 1L;
	private String price;		// 材料单价
	private String squareOverMaxRate;		// 面积超出上限比例
	
	public RecheckCnfg() {
		super();
	}

	public RecheckCnfg(String id){
		super(id);
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	public String getSquareOverMaxRate() {
		return squareOverMaxRate;
	}

	public void setSquareOverMaxRate(String squareOverMaxRate) {
		this.squareOverMaxRate = squareOverMaxRate;
	}
	
}