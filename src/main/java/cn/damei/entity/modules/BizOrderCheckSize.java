
package cn.damei.entity.modules;

import java.util.Date;

import cn.damei.common.persistence.DataEntity2;



public class BizOrderCheckSize extends DataEntity2<BizOrderCheckSize> {
	
	private static final long serialVersionUID = 1L;
	private Integer orderId;
	private String checksizeType;
	private Date checksizeDate;
	
	

	public BizOrderCheckSize() {
		super();
	}

	public BizOrderCheckSize(Integer id){
		super(id);
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getChecksizeType() {
		return checksizeType;
	}

	public void setChecksizeType(String checksizeType) {
		this.checksizeType = checksizeType;
	}

	public Date getChecksizeDate() {
		return checksizeDate;
	}

	public void setChecksizeDate(Date checksizeDate) {
		this.checksizeDate = checksizeDate;
	}

	

	



	
	
}