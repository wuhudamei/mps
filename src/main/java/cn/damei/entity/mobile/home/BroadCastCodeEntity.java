package cn.damei.entity.mobile.home;

import java.io.Serializable;
import java.util.Date;

public class BroadCastCodeEntity  implements Serializable{

private static final long serialVersionUID = 1L;
private Integer serialId;

public Integer getSerialId() {
	return serialId;
}

public void setSerialId(Integer serialId) {
	this.serialId = serialId;
}

private String bussinessType;
private Integer lastSeiralnum;
private Date generateTime;

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
