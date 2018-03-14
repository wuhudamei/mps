package cn.damei.entity.modules;


import cn.damei.common.persistence.DataEntity2;

/**
 * 评价活动设置Entity
 * @author wyb
 * @version 2017-02-25
 */
public class BizEvalActivityTaskpackTemp extends DataEntity2<BizEvalActivityTaskpackTemp> {
	
	private static final long serialVersionUID = 1L;
	private Integer evalActivityId;		// 评价活动id
	private Integer taskpackTempId;		// 任务包模板id
	
	private BizEvalActivity evalActivity; //评价活动
	
	public BizEvalActivityTaskpackTemp() {
		super();
	}

	public BizEvalActivityTaskpackTemp(Integer id){
		super(id);
	}

	public Integer getEvalActivityId() {
		return evalActivityId;
	}

	public void setEvalActivityId(Integer evalActivityId) {
		this.evalActivityId = evalActivityId;
	}

	public Integer getTaskpackTempId() {
		return taskpackTempId;
	}

	public void setTaskpackTempId(Integer taskpackTempId) {
		this.taskpackTempId = taskpackTempId;
	}

	public BizEvalActivity getEvalActivity() {
		return evalActivity;
	}

	public void setEvalActivity(BizEvalActivity evalActivity) {
		this.evalActivity = evalActivity;
	}
	
	


	
}