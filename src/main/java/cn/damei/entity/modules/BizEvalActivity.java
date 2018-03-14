package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.damei.common.persistence.DataEntity2;

/**
 * 评价活动设置Entity
 * @author wyb
 * @version 2017-02-25
 */
public class BizEvalActivity extends DataEntity2<BizEvalActivity> {
	
	private static final long serialVersionUID = 1L;
	private Integer storeId;		// 门店
	private String projectMode;		// 工程模式
	private String evalTargetType;		// 评价对象
	private String evalType;	//评价类别
	private String interval;	//间隔时间
	private Date evalStartDatetime;		// 有效时间开始
	private Date evalEndDatetime;		// 有效时间结束
	private String isEnabled;		// 是否启用
	private String taskpackTempId; 
	private List<BizEvalActivityTaskpackTemp> evalActivityTaskpackTempList; //评价活动关联任务包
	private List<BizEvalActivityIndex> evalActivityIndexList; //评价活动关联指标
	private String evalStageCheckNodeList;//评价活动关联的约检节点
	private String roleCycleId;//系统评价时间ID
	
	

	
	public String getRoleCycleId() {
		return roleCycleId;
	}

	public void setRoleCycleId(String roleCycleId) {
		this.roleCycleId = roleCycleId;
	}

	public String getEvalType() {
		return evalType;
	}

	public void setEvalType(String evalType) {
		this.evalType = evalType;
	}

	public String getInterval() {
		return interval;
	}

	public void setInterval(String interval) {
		this.interval = interval;
	}

	public String getEvalStageCheckNodeList() {
		return evalStageCheckNodeList;
	}

	public void setEvalStageCheckNodeList(String evalStageCheckNodeList) {
		this.evalStageCheckNodeList = evalStageCheckNodeList;
	}

	public String getTaskpackTempId() {
		return taskpackTempId;
	}

	public void setTaskpackTempId(String taskpackTempId) {
		this.taskpackTempId = taskpackTempId;
	}

	public BizEvalActivity() {
		super();
	}

	public BizEvalActivity(Integer id){
		super(id);
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	
	@Length(min=0, max=10, message="工程模式长度必须介于 0 和 10 之间")
	public String getProjectMode() {
		return projectMode;
	}

	public void setProjectMode(String projectMode) {
		this.projectMode = projectMode;
	}
	
	@Length(min=0, max=10, message="评价对象长度必须介于 0 和 10 之间")
	public String getEvalTargetType() {
		return evalTargetType;
	}

	public void setEvalTargetType(String evalTargetType) {
		this.evalTargetType = evalTargetType;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEvalStartDatetime() {
		return evalStartDatetime;
	}

	public void setEvalStartDatetime(Date evalStartDatetime) {
		this.evalStartDatetime = evalStartDatetime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEvalEndDatetime() {
		return evalEndDatetime;
	}

	public void setEvalEndDatetime(Date evalEndDatetime) {
		this.evalEndDatetime = evalEndDatetime;
	}
	
	@Length(min=0, max=1, message="是否启用长度必须介于 0 和 1 之间")
	public String getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(String isEnabled) {
		this.isEnabled = isEnabled;
	}

	public List<BizEvalActivityTaskpackTemp> getEvalActivityTaskpackTempList() {
		return evalActivityTaskpackTempList;
	}

	public void setEvalActivityTaskpackTempList(List<BizEvalActivityTaskpackTemp> evalActivityTaskpackTempList) {
		this.evalActivityTaskpackTempList = evalActivityTaskpackTempList;
	}

	public List<BizEvalActivityIndex> getEvalActivityIndexList() {
		return evalActivityIndexList;
	}

	public void setEvalActivityIndexList(List<BizEvalActivityIndex> evalActivityIndexList) {
		this.evalActivityIndexList = evalActivityIndexList;
	}
	
	
}