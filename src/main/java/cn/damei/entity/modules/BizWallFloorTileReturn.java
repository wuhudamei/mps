/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;

/**
 * 墙地砖退货表Entity
 * @author wyb
 * @version 2017-08-01
 */
public class BizWallFloorTileReturn extends DataEntity2<BizWallFloorTileReturn> {
	
	private static final long serialVersionUID = 1L;
	private Integer orderId;		// 订单id
	private Double squareReturn;		// 退货面积
	private Date returnDatetime;		// 退货日期时间
	
	public BizWallFloorTileReturn() {
		super();
	}

	public BizWallFloorTileReturn(Integer id){
		super(id);
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	public Double getSquareReturn() {
		return squareReturn;
	}

	public void setSquareReturn(Double squareReturn) {
		this.squareReturn = squareReturn;
	}
	
	public Date getReturnDatetime() {
		return returnDatetime;
	}

	public void setReturnDatetime(Date returnDatetime) {
		this.returnDatetime = returnDatetime;
	}
	
}