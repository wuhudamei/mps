package cn.damei.web.mobile.Worker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.damei.entity.mobile.Manager.GroupLeaderEvalReward;
import cn.damei.service.mobile.Manager.BizEvalRewardTaskpackService;
import cn.damei.entity.mobile.Worker.EmployeeGroupRa;
import cn.damei.entity.mobile.Worker.EmployeeGroupVo;
import cn.damei.service.mobile.Worker.EmployeeGroupRaService;
import cn.damei.service.mobile.Worker.EmployeeGroupVoService;
import cn.damei.entity.mobile.Worker.UrgeEvaluation;
import cn.damei.service.mobile.Worker.UrgeEvaluationService;
import cn.damei.entity.mobile.Worker.EmployeeGroup;
import cn.damei.entity.mobile.Worker.WorkerTaskpackageVo;
import cn.damei.service.mobile.Worker.EmployeeGroupService;
import cn.damei.entity.mobile.Worker.Worker;
import cn.damei.service.mobile.Worker.WorkTaskPackageService;
import cn.damei.entity.modules.EmployeeAgreementPC;
import cn.damei.service.modules.EmployeeAgreementPCService;

@Controller
@RequestMapping(value = "${adminPath}/app/worker")
public class IndexController {

	@Autowired
	private WorkTaskPackageService workTaskPackageService;
	@Autowired
	private EmployeeGroupService employeeGroupService;
	@Autowired
	private EmployeeGroupRaService employeeGroupRaService;
	@Autowired
	private EmployeeGroupVoService employeeGroupVoService;

	@Autowired
	private BizEvalRewardTaskpackService bizEvalRewardTaskpackService;

	@Autowired
	private UrgeEvaluationService urgeEvaluationService;

	@Autowired
	private EmployeeAgreementPCService employeeAgreementPCService;

	@RequestMapping(value = "toindex")
	public String toindex(Model model, HttpServletRequest request) {
		// 已登录的组长
		Worker worker = (Worker) request.getSession().getAttribute("worker");
		WorkerTaskpackageVo workTaskpackageVo = new WorkerTaskpackageVo();
		// 组长id
		Integer groupId = worker.getId();
		// 根据组长id查工人组
		EmployeeGroup employeeGroup = employeeGroupService.selectEmployeeGroupByGroupId(groupId);

		int countCompleted = workTaskPackageService.findCountCompleted(groupId);
		workTaskpackageVo.setCountCompleted(countCompleted);
		int countDiscompleted = workTaskPackageService.findCountDiscompleted(groupId);
		workTaskpackageVo.setCountDiscompleted(countDiscompleted);

		// 查询未结算的任务包数量
		Integer settled = workTaskPackageService.querySettled(groupId);
		Integer settling = workTaskPackageService.querySettling(groupId);
		model.addAttribute("workTaskpackageVo", workTaskpackageVo);
		model.addAttribute("worker", worker);
		model.addAttribute("employeeGroup", employeeGroup);
		model.addAttribute("settled", settled);
		model.addAttribute("settling", settling);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("groupId", groupId);
		List<String> list = new ArrayList<String>();
		list.add("100");
		list.add("105");
		list.add("110");
		list.add("120");
		list.add("130");
		map.put("list", list);
		Integer confirmCount = workTaskPackageService.findTaskPackageForSettlementCount(map);
		model.addAttribute("confirmCount", confirmCount);
		// 查询可签到, 可催促,可接单的数量
		WorkerTaskpackageVo vo = new WorkerTaskpackageVo();
		vo.setWorkerId(groupId);
		// 可接单数量
		vo.setStatus("50");
		Integer orderCount = employeeGroupService.findCount(vo);
		// 可签到数量
		vo.setStatus("60");
		Integer signCount = employeeGroupService.findSignCount(vo);
		// 可申请完工数量
		vo.setStatus("70");
		Integer applyCount = employeeGroupService.findCount(vo);
		// 可催促数量
		vo.setStatus("80");
		Integer urgeCount = employeeGroupService.findCount(vo);
		// 查询可以催促评价数量
		Integer emgrouprelationId = worker.getEmgrouprelationId();
		UrgeEvaluation urgeEvaluation = new UrgeEvaluation();
		urgeEvaluation.setWorkerId(emgrouprelationId + "");
		List<UrgeEvaluation> evaluationList = urgeEvaluationService.findEvaluationTaskpageByGroupId(urgeEvaluation);
		// 查询是否需要签订协议
		Integer id = worker.getId();
		EmployeeAgreementPC employeeAgreementPC = new EmployeeAgreementPC();
		employeeAgreementPC.setEmployeeId(id + "");
		String employeeAgreementPCService2 = employeeAgreementPCService.employeeAgreementPCService(employeeAgreementPC);
		model.addAttribute("isSignAgreement", employeeAgreementPCService2);
		model.addAttribute("evaluationList", evaluationList.size());
		model.addAttribute("orderCount", orderCount);
		model.addAttribute("signCount", signCount);
		model.addAttribute("applyCount", applyCount);
		model.addAttribute("urgeCount", urgeCount);
		return "mobile/modules/Worker/chief_index";
	}

	@RequestMapping(value = "myindex")
	public String myindex(Model model, HttpServletRequest request) {
		// 已登录的工人
		Worker worker = (Worker) request.getSession().getAttribute("worker");
		// 根据员工id去查询工人组id
		EmployeeGroupRa employeeGroupRa = employeeGroupRaService.findByEmployeeId(worker.getId());
		// 根据工人组id查询组长的id
		EmployeeGroupVo employeeGroupVo = employeeGroupVoService.findById(employeeGroupRa.getGroupId());
		// 根据组长id查工人组
		EmployeeGroup employeeGroup = employeeGroupService.selectEmployeeGroupByGroupId(employeeGroupRa.getGroupId());
		// 查询已结算完/未结算完的任务包数量
		Integer settled = workTaskPackageService.querySettled(employeeGroupVo.getGroupid());
		Integer settling = workTaskPackageService.querySettling(employeeGroupVo.getGroupid());
		model.addAttribute("settled", settled);
		model.addAttribute("settling", settling);
		model.addAttribute("employeeGroup", employeeGroup);
		model.addAttribute("worker", worker);
		return "mobile/modules/Worker/chief_index_mine";
	}

	@RequestMapping(value = "toindex1")
	public String toindex1(Model model, HttpServletRequest request) {
		// 已登录的工人
		Worker worker = (Worker) request.getSession().getAttribute("worker");
		// 根据员工id去查询工人组id
		EmployeeGroupRa employeeGroupRa = employeeGroupRaService.findByEmployeeId(worker.getId());
		// 根据工人组id查询组长的id
		EmployeeGroupVo employeeGroupVo = employeeGroupVoService.findById(employeeGroupRa.getGroupId());
		// 查询已完工/未完工的任务包数量
		int countCompleted = workTaskPackageService.findCountCompleted(employeeGroupVo.getGroupid());
		int countDiscompleted = workTaskPackageService.findCountDiscompleted(employeeGroupVo.getGroupid());
		// 查询已结算完/未结算完的任务包数量
		Integer settled = workTaskPackageService.querySettled(employeeGroupVo.getGroupid());
		Integer settling = workTaskPackageService.querySettling(employeeGroupVo.getGroupid());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("groupId", employeeGroupVo.getGroupid());
		List<String> list = new ArrayList<String>();
		list.add("100");
		list.add("105");
		list.add("110");
		list.add("120");
		list.add("130");
		map.put("list", list);
		Integer confirmCount = workTaskPackageService.findTaskPackageForSettlementCount(map);

		// 查询可以催促评价数量
		Integer emgrouprelationId = worker.getEmgrouprelationId();
		UrgeEvaluation urgeEvaluation = new UrgeEvaluation();
		urgeEvaluation.setWorkerId(emgrouprelationId + "");
		List<UrgeEvaluation> evaluationList = urgeEvaluationService.findEvaluationTaskpageByGroupId(urgeEvaluation);
		model.addAttribute("evaluationList", evaluationList.size());
		// 查询是否需要签订协议
		Integer id = worker.getId();
		EmployeeAgreementPC employeeAgreementPC = new EmployeeAgreementPC();
		employeeAgreementPC.setEmployeeId(id + "");
		String employeeAgreementPCService2 = employeeAgreementPCService.employeeAgreementPCService(employeeAgreementPC);
		model.addAttribute("isSignAgreement", employeeAgreementPCService2);
		model.addAttribute("confirmCount", confirmCount);
		model.addAttribute("countCompleted", countCompleted);
		model.addAttribute("countDiscompleted", countDiscompleted);
		model.addAttribute("settled", settled);
		model.addAttribute("settling", settling);
		model.addAttribute("worker", worker);

		return "mobile/modules/Worker/worker_index";
	}

	@RequestMapping(value = "myindex1")
	public String myindex1(Model model, HttpServletRequest request) {
		// 已登录的工人
		Worker worker = (Worker) request.getSession().getAttribute("worker");
		// 根据员工id去查询工人组id
		EmployeeGroupRa employeeGroupRa = employeeGroupRaService.findByEmployeeId(worker.getId());
		// 根据工人组id查询组长的id
		EmployeeGroupVo employeeGroupVo = employeeGroupVoService.findById(employeeGroupRa.getGroupId());
		// 查询已结算完/未结算完的任务包数量
		Integer settled = workTaskPackageService.querySettled(employeeGroupVo.getGroupid());
		Integer settling = workTaskPackageService.querySettling(employeeGroupVo.getGroupid());
		model.addAttribute("settled", settled);
		model.addAttribute("settling", settling);
		model.addAttribute("worker", worker);
		return "mobile/modules/Worker/worker_index_mine";
	}

	@RequestMapping(value = "showRewardAmount")
	public String showRewardAmount(Model model, HttpServletRequest request) {
		// 已登录的工人
		Worker worker = (Worker) request.getSession().getAttribute("worker");
		List<GroupLeaderEvalReward> rewardList = bizEvalRewardTaskpackService.queryRewardAmountByGroupLeaderEmployeeId(worker.getId());
		model.addAttribute("rewardList", rewardList);
		return "mobile/modules/Worker/rewardAmount";
	}

	@RequestMapping(value = "common-issue.php")
	public String commonIssue(Model model, HttpServletRequest request) {

		return "mobile/modules/Worker/common-issue/question-list";
	}

	@RequestMapping(value = "questionPage1.html")
	public String questionPage1(Model model, HttpServletRequest request) {

		return "mobile/modules/Worker/common-issue/questionPage1";
	}

	@RequestMapping(value = "questionPage2.html")
	public String questionPage2(Model model, HttpServletRequest request) {

		return "mobile/modules/Worker/common-issue/questionPage2";
	}
}
