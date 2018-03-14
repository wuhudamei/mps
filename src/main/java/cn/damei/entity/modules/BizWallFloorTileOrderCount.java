/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;


import cn.damei.common.persistence.DataEntity2;

/**
 * 墙地砖订单统计表Entity
 * @author wyb
 * @version 2017-08-01
 */
public class BizWallFloorTileOrderCount extends DataEntity2<BizWallFloorTileOrderCount> {
	
	private static final long serialVersionUID = 1L;
	private Integer orderId;		// 订单id
	private Double squareBudget;		// 预算面积
	private Double squarePurchaseTotal;		// 采购合计面积
	private Double squareReturn;		// 退货面积
	private Double squarePurchaseReal;		// 采购实际面积
	private Double squareSettle;		// 结算面积
	private Double squareMeasure;		// 实测面积
	
	private String projectMode; //订单工程模式
	
	public BizWallFloorTileOrderCount() {
		super();
	}

	public BizWallFloorTileOrderCount(Integer id){
		super(id);
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	public Double getSquareBudget() {
		return squareBudget;
	}

	public void setSquareBudget(Double squareBudget) {
		this.squareBudget = squareBudget;
	}
	
	public Double getSquarePurchaseTotal() {
		return squarePurchaseTotal;
	}

	public void setSquarePurchaseTotal(Double squarePurchaseTotal) {
		this.squarePurchaseTotal = squarePurchaseTotal;
	}
	
	public Double getSquareReturn() {
		return squareReturn;
	}

	public void setSquareReturn(Double squareReturn) {
		this.squareReturn = squareReturn;
	}
	
	public Double getSquarePurchaseReal() {
		return squarePurchaseReal;
	}

	public void setSquarePurchaseReal(Double squarePurchaseReal) {
		this.squarePurchaseReal = squarePurchaseReal;
	}
	
	public Double getSquareSettle() {
		return squareSettle;
	}

	public void setSquareSettle(Double squareSettle) {
		this.squareSettle = squareSettle;
	}
	
	public Double getSquareMeasure() {
		return squareMeasure;
	}

	public void setSquareMeasure(Double squareMeasure) {
		this.squareMeasure = squareMeasure;
	}

	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}
	
	
}