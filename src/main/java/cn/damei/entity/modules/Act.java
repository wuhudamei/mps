
package cn.damei.entity.modules;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import cn.damei.common.persistence.BaseEntity;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.utils.TimeUtils;
import cn.damei.common.utils.Variable;


public class Act extends BaseEntity<Act> {
	
	private static final long serialVersionUID = 1L;

	private String taskId;
	private String taskName;
	private String taskDefKey;

	private String procInsId;
	private String procDefId;
	private String procDefKey;

	private String businessTable;
	private String businessId;
	
	private String title;

	private String status;


	private String comment;
	private String flag;
	
	private Task task;
	private ProcessDefinition procDef;
	private ProcessInstance procIns;
	private HistoricTaskInstance histTask;
	private HistoricActivityInstance histIns;

	private String assignee;
	private String assigneeName;

	private Variable vars;

	
	private Date beginDate;
	private Date endDate;

	private List<Act> list;

	public Act() {
		super();
	}

	public String getTaskId() {
		if (taskId == null && task != null){
			taskId = task.getId();
		}
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		if (taskName == null && task != null){
			taskName = task.getName();
		}
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskDefKey() {
		if (taskDefKey == null && task != null){
			taskDefKey = task.getTaskDefinitionKey();
		}
		return taskDefKey;
	}

	public void setTaskDefKey(String taskDefKey) {
		this.taskDefKey = taskDefKey;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTaskCreateDate() {
		if (task != null){
			return task.getCreateTime();
		}
		return null;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTaskEndDate() {
		if (histTask != null){
			return histTask.getEndTime();
		}
		return null;
	}
	
	@JsonIgnore
	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	@JsonIgnore
	public ProcessDefinition getProcDef() {
		return procDef;
	}

	public void setProcDef(ProcessDefinition procDef) {
		this.procDef = procDef;
	}
	
	public String getProcDefName() {
		return procDef.getName();
	}

	@JsonIgnore
	public ProcessInstance getProcIns() {
		return procIns;
	}

	public void setProcIns(ProcessInstance procIns) {
		this.procIns = procIns;
		if (procIns != null && procIns.getBusinessKey() != null){
			String[] ss = procIns.getBusinessKey().split(":");
			setBusinessTable(ss[0]);
			setBusinessId(ss[1]);
		}
	}









	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@JsonIgnore
	public HistoricTaskInstance getHistTask() {
		return histTask;
	}

	public void setHistTask(HistoricTaskInstance histTask) {
		this.histTask = histTask;
	}

	@JsonIgnore
	public HistoricActivityInstance getHistIns() {
		return histIns;
	}

	public void setHistIns(HistoricActivityInstance histIns) {
		this.histIns = histIns;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getProcDefId() {
		if (procDefId == null && task != null){
			procDefId = task.getProcessDefinitionId();
		}
		return procDefId;
	}

	public void setProcDefId(String procDefId) {
		this.procDefId = procDefId;
	}

	public String getProcInsId() {
		if (procInsId == null && task != null){
			procInsId = task.getProcessInstanceId();
		}
		return procInsId;
	}

	public void setProcInsId(String procInsId) {
		this.procInsId = procInsId;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getBusinessTable() {
		return businessTable;
	}

	public void setBusinessTable(String businessTable) {
		this.businessTable = businessTable;
	}

	public String getAssigneeName() {
		return assigneeName;
	}

	public void setAssigneeName(String assigneeName) {
		this.assigneeName = assigneeName;
	}

	public String getAssignee() {
		if (assignee == null && task != null){
			assignee = task.getAssignee();
		}
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public List<Act> getList() {
		return list;
	}

	public void setList(List<Act> list) {
		this.list = list;
	}

	public Variable getVars() {
		return vars;
	}

	public void setVars(Variable vars) {
		this.vars = vars;
	}
	

	public void setVars(Map<String, Object> map) {
		this.vars = new Variable(map);
	}


















	public String getProcDefKey() {
		if (StringUtils.isBlank(procDefKey) && StringUtils.isNotBlank(procDefId)){
			procDefKey = StringUtils.split(procDefId, ":")[0];
		}
		return procDefKey;
	}

	public void setProcDefKey(String procDefKey) {
		this.procDefKey = procDefKey;
	}
	

	public String getDurationTime(){
		if (histIns!=null && histIns.getDurationInMillis() != null){
			return TimeUtils.toTimeString(histIns.getDurationInMillis());
		}
		return "";
	}
	

	public boolean isTodoTask(){
		return "todo".equals(status) || "claim".equals(status);
	}
	

	public boolean isFinishTask(){
		return "finish".equals(status) || StringUtils.isBlank(taskId);
	}

	@Override
	public void preInsert() {
		
	}

	@Override
	public void preUpdate() {
		
	}

}


