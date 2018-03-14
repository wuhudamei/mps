package cn.damei.entity.mobile.Manager;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import cn.damei.common.persistence.DataEntity2;

/** 
* @ClassName: OrderDemolitionBuild 
* @Description: 拆改交底实体类
* @author zkj  
* @date 2017年10月19日 下午2:00:16 
* @version V1.0 
*/

public class OrderDemolitionBuild extends DataEntity2<OrderDemolitionBuild>{

	private static final long serialVersionUID = 1L;
	/** 
	*  
	*/ 
	private String orderId;
	
	/** 
	*  提交拆交底时间
	*/ 
	private Date demolitionBuildDate;
	
	/** 
	*  延期天数
	*/ 
	private String delayDays;
	
	/** 
	*  提交拆改交底员工ID
	*/
	private String demolitionBuildEmployeeId;
	
	/** 
	*  照片
	*/ 
	private String photo[];
	
	/** 
	*  签到时间
	*/ 
	private Date signDatetime;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Date getDemolitionBuildDate() {
		return demolitionBuildDate;
	}
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public void setDemolitionBuildDate(Date demolitionBuildDate) {
		this.demolitionBuildDate = demolitionBuildDate;
	}

	public String getDelayDays() {
		return delayDays;
	}

	public void setDelayDays(String delayDays) {
		this.delayDays = delayDays;
	}

	public String getDemolitionBuildEmployeeId() {
		return demolitionBuildEmployeeId;
	}

	public void setDemolitionBuildEmployeeId(String demolitionBuildEmployeeId) {
		this.demolitionBuildEmployeeId = demolitionBuildEmployeeId;
	}

	public String[] getPhoto() {
		return photo;
	}

	public void setPhoto(String[] photo) {
		this.photo = photo;
	}

	public Date getSignDatetime() {
		return signDatetime;
	}

	public void setSignDatetime(Date signDatetime) {
		this.signDatetime = signDatetime;
	}
	
	
}