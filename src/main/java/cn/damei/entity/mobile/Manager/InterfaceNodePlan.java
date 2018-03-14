package cn.damei.entity.mobile.Manager;

public class InterfaceNodePlan {
	private String ScheduleNodeSequence	;//进度节点顺序	
	private String ScheduleNodeName		;//进度节点名称	
	private String PlanCompleteTime		;//计划完成时间	
	public String getScheduleNodeSequence() {
		return ScheduleNodeSequence;
	}
	public void setScheduleNodeSequence(String scheduleNodeSequence) {
		ScheduleNodeSequence = scheduleNodeSequence;
	}
	public String getScheduleNodeName() {
		return ScheduleNodeName;
	}
	public void setScheduleNodeName(String scheduleNodeName) {
		ScheduleNodeName = scheduleNodeName;
	}
	public String getPlanCompleteTime() {
		return PlanCompleteTime;
	}
	public void setPlanCompleteTime(String planCompleteTime) {
		PlanCompleteTime = planCompleteTime;
	}
    
}
