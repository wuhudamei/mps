package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity;



public class TeamLeaderInfo extends DataEntity<OrderTaskpackage> {


	private static final long serialVersionUID = 1L;

	private String realName;
	private String phone;
	private String headPic;
	
	private Integer teamNumber;
	public Integer getTeamNumber() {
		return teamNumber;
	}

	public void setTeamNumber(Integer teamNumber) {
		this.teamNumber = teamNumber;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getHeadPic() {
		return headPic;
	}


	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}

}
