/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import cn.damei.common.persistence.DataEntity2;

/**
 * 评价奖励任务包模板Entity
 * @author qww
 * @version 2017-02-24
 */
public class BizEvalRewardTaskpackTemp extends DataEntity2<BizEvalRewardTaskpackTemp> {
	
	private static final long serialVersionUID = 1L;
	private Integer evalRewardCfgId;		// 评论奖励设置id
	private Integer taskpackTempId;		// 任务包模板id

	private String taskpackTempName; // 任务包模板名称
	
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