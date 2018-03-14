
package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;


public class BizWallFloorTileReturn extends DataEntity2<BizWallFloorTileReturn> {
	
	private static final long serialVersionUID = 1L;
	private Integer orderId;
	private Double squareReturn;
	private Date returnDatetime;
	
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