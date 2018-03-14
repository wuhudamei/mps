package cn.damei.web.mobile.Worker;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.entity.mobile.Worker.UrgeEvaluation;
import cn.damei.service.mobile.Worker.UrgeEvaluationService;
import cn.damei.entity.mobile.Worker.EmployeeGroup;
import cn.damei.service.mobile.Worker.EmployeeGroupService;
import cn.damei.entity.mobile.Worker.Worker;
import cn.damei.service.modules.BizBusinessUrgeService;

@Controller
@RequestMapping(value="${adminPath}/app/worker/urgeevaluation")
public class UrgeEvaluationController {
	@Autowired
	private UrgeEvaluationService urgeEvaluationService;
	@Autowired
	private BizBusinessUrgeService  bizBusinessUrgeService;
	@Autowired
	private EmployeeGroupService employeeGroupService;
	
	@RequestMapping(value="list")
	public String list(HttpServletRequest request,HttpServletResponse response,Model model){
		
		Worker worker = (Worker)request.getSession().getAttribute("worker");
		Integer emgrouprelationId = worker.getEmgrouprelationId();
		UrgeEvaluation urgeEvaluation = new UrgeEvaluation();
		urgeEvaluation.setWorkerId(emgrouprelationId+"");
		List<UrgeEvaluation> list = urgeEvaluationService.findEvaluationTaskpageByGroupId(urgeEvaluation);
		EmployeeGroup employeeGroup = employeeGroupService.selectEmployeeGroupByGroupId(worker.getId());
		String isLeader="0";
		if(employeeGroup != null){
			isLeader="0";
		}else{
			isLeader="1";
		}
		model.addAttribute("isLeader",isLeader);
		model.addAttribute("list", list);
		model.addAttribute("workerId", worker.getId());
		model.addAttribute("listSize", list.size());
		
		return "mobile/modules/Worker/urgeevaluation/pushComment";
	}
	

	@RequestMapping(value="urgeevaluation")
	@ResponseBody
	public Integer urgeevaluation(HttpServletRequest request,UrgeEvaluation urgeEvaluation){
		List<UrgeEvaluation> findEvaluationTaskpageByGroupId = urgeEvaluationService.findEvaluationTaskpageByGroupId(urgeEvaluation);
		if(findEvaluationTaskpageByGroupId!=null && findEvaluationTaskpageByGroupId.size()>0){
			urgeEvaluation = findEvaluationTaskpageByGroupId.get(0);
			Worker worker = (Worker)request.getSession().getAttribute("worker");
			urgeEvaluation.setWorkerId(worker.getId()+"");
			Integer count = urgeEvaluationService.findCount(urgeEvaluation);
			return count;
		}
		
		return null;
	}
}
