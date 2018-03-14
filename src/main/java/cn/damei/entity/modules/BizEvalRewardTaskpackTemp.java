
package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;


public class BizEvalRewardTaskpackTemp extends DataEntity2<BizEvalRewardTaskpackTemp> {
	
	private static final long serialVersionUID = 1L;
	private Integer evalRewardCfgId;
	private Integer taskpackTempId;

	private String taskpackTempName;
	
	public BizEvalRewardTaskpackTemp() {
		super();
	}

	public BizEvalRewardTaskpackTemp(Integer id){
		super(id);
	}

	public Integer getEvalRewardCfgId() {
		return evalRewardCfgId;
	}

	public void setEvalRewardCfgId(Integer evalRewardCfgId) {
		this.evalRewardCfgId = evalRewardCfgId;
	}
	
	public Integer getTaskpackTempId() {
		return taskpackTempId;
	}

	public void setTaskpackTempId(Integer taskpackTempId) {
		this.taskpackTempId = taskpackTempId;
	}

	public String getTaskpackTempName() {
		return taskpackTempName;
	}

	public void setTaskpackTempName(String taskpackTempName) {
		this.taskpackTempName = taskpackTempName;
	}
}