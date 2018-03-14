package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity;

/**
 * @author 梅浩 meihao@zzhyun.cn:
 * @version 创建时间：2016年9月17日 下午7:45:02 类说明
 */

public class TeamLeaderInfo extends DataEntity<OrderTaskpackage> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String realName;// 真实姓名
	private String phone;// 手机号
	private String headPic;// 图片
	
	private Integer teamNumber;//组内人数
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
