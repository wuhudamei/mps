
package cn.damei.entity.modules;


import cn.damei.common.persistence.DataEntity2;


public class BizWallFloorTileOrderCount extends DataEntity2<BizWallFloorTileOrderCount> {
	
	private static final long serialVersionUID = 1L;
	private Integer orderId;
	private Double squareBudget;
	private Double squarePurchaseTotal;
	private Double squareReturn;
	private Double squarePurchaseReal;
	private Double squareSettle;
	private Double squareMeasure;
	
	private String projectMode;
	
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