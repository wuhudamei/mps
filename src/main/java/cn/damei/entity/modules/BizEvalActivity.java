package cn.damei.entity.modules;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.damei.common.persistence.DataEntity2;


public class BizEvalActivity extends DataEntity2<BizEvalActivity> {
	
	private static final long serialVersionUID = 1L;
	private Integer storeId;
	private String projectMode;
	private String evalTargetType;
	private String evalType;
	private String interval;
	private Date evalStartDatetime;
	private Date evalEndDatetime;
	private String isEnabled;
	private String taskpackTempId; 
	private List<BizEvalActivityTaskpackTemp> evalActivityTaskpackTempList;
	private List<BizEvalActivityIndex> evalActivityIndexList;
	private String evalStageCheckNodeList;
	private String roleCycleId;
	
	

	
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