
package cn.damei.entity.modules;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import cn.damei.common.persistence.DataEntity2;


public class BizAttendDay extends DataEntity2<BizAttendDay> {
	
	private static final long serialVersionUID = 1L;

	private String attendEmployeeRole;

	private Integer attendEmployeeId;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date attendDate;

	private Integer signTimes;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date earlySignDate;

	private Double earlySignReeorDistance;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date lateSignDate;

	private Double lateSignErrorDistance;

	private String attendType;

	private String isGeneratedAttendBill; 
	
	private Date zaoDate;
	private Date wanDate;
	
	public Date getZaoDate() {
		return zaoDate;
	}
	public void setZaoDate(Date zaoDate) {
		this.zaoDate = zaoDate;
	}
	public Date getWanDate() {
		return wanDate;
	}
	public void setWanDate(Date wanDate) {
		this.wanDate = wanDate;
	}

	private String managerName;
	
	public String getAttendEmployeeRole() {
		return attendEmployeeRole;
	}
	public void setAttendEmployeeRole(String attendEmployeeRole) {
		this.attendEmployeeRole = attendEmployeeRole;
	}
	public Integer getAttendEmployeeId() {
		return attendEmployeeId;
	}
	public void setAttendEmployeeId(Integer attendEmployeeId) {
		this.attendEmployeeId = attendEmployeeId;
	}
	public Date getAttendDate() {
		return attendDate;
	}
	public void setAttendDate(Date attendDate) {
		this.attendDate = attendDate;
	}
	public Integer getSignTimes() {
		return signTimes;
	}
	public void setSignTimes(Integer signTimes) {
		this.signTimes = signTimes;
	}
	public Date getEarlySignDate() {
		return earlySignDate;
	}
	public void setEarlySignDate(Date earlySignDate) {
		this.earlySignDate = earlySignDate;
	}
	public Double getEarlySignReeorDistance() {
		return earlySignReeorDistance;
	}
	public void setEarlySignReeorDistance(Double earlySignReeorDistance) {
		this.earlySignReeorDistance = earlySignReeorDistance;
	}
	public Date getLateSignDate() {
		return lateSignDate;
	}
	public void setLateSignDate(Date lateSignDate) {
		this.lateSignDate = lateSignDate;
	}
	public Double getLateSignErrorDistance() {
		return lateSignErrorDistance;
	}
	public void setLateSignErrorDistance(Double lateSignErrorDistance) {
		this.lateSignErrorDistance = lateSignErrorDistance;
	}
	public String getAttendType() {
		return attendType;
	}
	public void setAttendType(String attendType) {
		this.attendType = attendType;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	
	public String getIsGeneratedAttendBill() {
		return isGeneratedAttendBill;
	}
	public void setIsGeneratedAttendBill(String isGeneratedAttendBill) {
		this.isGeneratedAttendBill = isGeneratedAttendBill;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attendDate == null) ? 0 : attendDate.hashCode());
		result = prime * result + ((attendEmployeeId == null) ? 0 : attendEmployeeId.hashCode());
		result = prime * result + ((attendEmployeeRole == null) ? 0 : attendEmployeeRole.hashCode());
		result = prime * result + ((attendType == null) ? 0 : attendType.hashCode());
		result = prime * result + ((earlySignDate == null) ? 0 : earlySignDate.hashCode());
		result = prime * result + ((earlySignReeorDistance == null) ? 0 : earlySignReeorDistance.hashCode());
		result = prime * result + ((isGeneratedAttendBill == null) ? 0 : isGeneratedAttendBill.hashCode());
		result = prime * result + ((lateSignDate == null) ? 0 : lateSignDate.hashCode());
		result = prime * result + ((lateSignErrorDistance == null) ? 0 : lateSignErrorDistance.hashCode());
		result = prime * result + ((managerName == null) ? 0 : managerName.hashCode());
		result = prime * result + ((signTimes == null) ? 0 : signTimes.hashCode());
		result = prime * result + ((wanDate == null) ? 0 : wanDate.hashCode());
		result = prime * result + ((zaoDate == null) ? 0 : zaoDate.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		BizAttendDay other = (BizAttendDay) obj;
		if (attendDate == null) {
			if (other.attendDate != null)
				return false;
		} else if (!attendDate.equals(other.attendDate))
			return false;
		if (attendEmployeeId == null) {
			if (other.attendEmployeeId != null)
				return false;
		} else if (!attendEmployeeId.equals(other.attendEmployeeId))
			return false;
		if (attendEmployeeRole == null) {
			if (other.attendEmployeeRole != null)
				return false;
		} else if (!attendEmployeeRole.equals(other.attendEmployeeRole))
			return false;
		if (attendType == null) {
			if (other.attendType != null)
				return false;
		} else if (!attendType.equals(other.attendType))
			return false;
		if (earlySignDate == null) {
			if (other.earlySignDate != null)
				return false;
		} else if (!earlySignDate.equals(other.earlySignDate))
			return false;
		if (earlySignReeorDistance == null) {
			if (other.earlySignReeorDistance != null)
				return false;
		} else if (!earlySignReeorDistance.equals(other.earlySignReeorDistance))
			return false;
		if (isGeneratedAttendBill == null) {
			if (other.isGeneratedAttendBill != null)
				return false;
		} else if (!isGeneratedAttendBill.equals(other.isGeneratedAttendBill))
			return false;
		if (lateSignDate == null) {
			if (other.lateSignDate != null)
				return false;
		} else if (!lateSignDate.equals(other.lateSignDate))
			return false;
		if (lateSignErrorDistance == null) {
			if (other.lateSignErrorDistance != null)
				return false;
		} else if (!lateSignErrorDistance.equals(other.lateSignErrorDistance))
			return false;
		if (managerName == null) {
			if (other.managerName != null)
				return false;
		} else if (!managerName.equals(other.managerName))
			return false;
		if (signTimes == null) {
			if (other.signTimes != null)
				return false;
		} else if (!signTimes.equals(other.signTimes))
			return false;
		if (wanDate == null) {
			if (other.wanDate != null)
				return false;
		} else if (!wanDate.equals(other.wanDate))
			return false;
		if (zaoDate == null) {
			if (other.zaoDate != null)
				return false;
		} else if (!zaoDate.equals(other.zaoDate))
			return false;
		return true;
	}
	
	
	
}