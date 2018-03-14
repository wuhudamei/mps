package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;
/**
 * 项目经理薪级
 * @author cgh
 *
 */
public class BizManagerStar extends DataEntity2<BizManagerStar>{

	private static final long serialVersionUID = 8178448147379987199L;
	//门店id
	private Integer storeId;
	//工程模式
	private String projectMode;
	//星级类型 1.人员
	private String starType;
	//星级级别
	private Integer starLevel;
	//状态 停用 启用
	private String status;
	
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public String getProjectMode() {
		return projectMode;
	}
	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}
	public String getStarType() {
		return starType;
	}
	public void setStarType(String starType) {
		this.starType = starType;
	}
	public Integer getStarLevel() {
		return starLevel;
	}
	public void setStarLevel(Integer starLevel) {
		this.starLevel = starLevel;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
