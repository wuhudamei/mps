package cn.damei.entity.mobile.Inspector;

import java.io.Serializable;
import java.util.Date;

public class ReCheckCode implements Serializable {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	private Integer serialId;//流水号id

	public Integer getSerialId() {
		return serialId;
	}

	public void setSerialId(Integer serialId) {
		this.serialId = serialId;
	}

	private String bussinessType;// 类型
	private Integer lastSeiralnum;// 数
	private Date generateTime;// 时间

	public String getBussinessType() {
		return bussinessType;
	}

	public void setBussinessType(String bussinessType) {
		this.bussinessType = bussinessType;
	}

	public Integer getLastSeiralnum() {
		return lastSeiralnum;
	}

	public void setLastSeiralnum(Integer lastSeiralnum) {
		this.lastSeiralnum = lastSeiralnum;
	}

	public Date getGenerateTime() {
		return generateTime;
	}

	public void setGenerateTime(Date generateTime) {
		this.generateTime = generateTime;
	}

}
