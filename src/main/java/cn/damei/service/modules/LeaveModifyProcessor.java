package cn.damei.service.modules;

import java.util.Date;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.dao.modules.LeaveDao;
import cn.damei.entity.modules.Leave;


@Service
@Transactional
public class LeaveModifyProcessor implements TaskListener {
	
	private static final long serialVersionUID = 1L;

	@Autowired
	private LeaveDao leaveDao;
	@Autowired
	private RuntimeService runtimeService;

	public void notify(DelegateTask delegateTask) {
		String processInstanceId = delegateTask.getProcessInstanceId();
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		Leave leave = new Leave(processInstance.getBusinessKey());
		leave.setLeaveType((String) delegateTask.getVariable("leaveType"));
		leave.setStartTime((Date) delegateTask.getVariable("startTime"));
		leave.setEndTime((Date) delegateTask.getVariable("endTime"));
		leave.setReason((String) delegateTask.getVariable("reason"));
		leave.preUpdate();
		leaveDao.update(leave);
	}

}
