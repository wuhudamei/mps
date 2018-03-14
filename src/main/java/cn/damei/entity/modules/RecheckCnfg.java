
package cn.damei.entity.modules;


import cn.damei.common.persistence.DataEntity;


public class RecheckCnfg extends DataEntity<RecheckCnfg> {
	
	private static final long serialVersionUID = 1L;
	private String price;
	private String squareOverMaxRate;
	
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