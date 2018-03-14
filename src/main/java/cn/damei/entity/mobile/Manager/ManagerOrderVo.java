package cn.damei.entity.mobile.Manager;

import cn.damei.common.persistence.DataEntity;
import cn.damei.entity.modules.WorkgroupVo;

public class ManagerOrderVo extends DataEntity<WorkgroupVo>{
	

	private static final long serialVersionUID = 1L;
	
	private int buildingCount;
	private int totalCount;
	
	public int getBuildingCount() {
		return buildingCount;
	}
	public void setBuildingCount(int buildingCount) {
		this.buildingCount = buildingCount;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
}
