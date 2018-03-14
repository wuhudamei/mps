package cn.damei.web.mobile.Worker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.constantUtils.PictureTypeContantUtil;
import cn.damei.common.constantUtils.projectProblem.ProjectProblemConstantUtil;
import cn.damei.common.SessionUtils;
import cn.damei.common.ProjectIssueUtil.ProjectUtil;
import cn.damei.service.mobile.Worker.ProjectIssueService;
import cn.damei.entity.mobile.Worker.EmployeeGroup;
import cn.damei.service.mobile.Worker.EmployeeGroupService;
import cn.damei.entity.mobile.Worker.Worker;


@Controller
@RequestMapping(value = "{adminPath}/app/worker/project-issue")
public class ProjectIssueController {

	@Autowired
	private ProjectIssueService service;
	@Autowired
	private ProjectUtil util;
	@Autowired
	private EmployeeGroupService employeeGroupService;

	private static final String SUCCESS_1 = "1";

	@RequestMapping(value = "list.php")
	public String workerReport(HttpServletRequest request, Model model) {
		Worker worker = SessionUtils.getWorkerSession(request);

		List<Map<String, Object>> mapList = util.findProjectProblemByDealPersonId(worker.getId());

		EmployeeGroup employeeGroup = employeeGroupService.selectEmployeeGroupByGroupId(worker.getId());
		String isLeader = "0";
		if (employeeGroup != null) {
			isLeader = "0";
		} else {
			isLeader = "1";
		}
		model.addAttribute("isLeader", isLeader);

		model.addAttribute("mapList", mapList);

		return "mobile/modules/Worker/complain/complaint";
	}

	@RequestMapping(value = "checkIssueProblemByOrderId")
	public String checkIssueProblemByOrderId(HttpServletRequest request, Integer orderId, Model model) {

		Worker worker = SessionUtils.getWorkerSession(request);
		Map<String, Object> map = new HashMap<>(12);
		map.put("orderId", orderId);
		map.put("dealType", ProjectProblemConstantUtil.PROJECT_DEAL_PERSON_TYPE_WORKER_2);
		map.put("dealPersonId", worker.getId());

		List<Map<String, Object>> mapList = service.findProblemByOrderId(map);
		if (mapList.size() > 0) {
			model.addAttribute("customerInfo", mapList.get(0).get("customerInfo"));
		}
		model.addAttribute("mapList", mapList);
		return "mobile/modules/Worker/complain/complaint2";
	}

	@RequestMapping(value = "saveWorkerHandle")
	@ResponseBody
	public String saveWorkerHandle(Integer handleId, HttpServletRequest request) {

		service.updateHandleStatusDataById(handleId, SessionUtils.getWorkerSession(request).getId());

		return SUCCESS_1;
	}

	@RequestMapping(value = "checkPic")
	public String checkPic(Model model, HttpServletRequest request, Integer relatedId) {


		Map<String, String> map = new HashMap<>();

		map.put("businessIdInt", String.valueOf(relatedId));
		map.put("businessType", PictureTypeContantUtil.PICTURE_TYPE_200);

		List<String> list = service.findPic(map);

		model.addAttribute("picList", list);
		String baseUrl = request.getContextPath();
		model.addAttribute("baseUrl", baseUrl);

		return "mobile/modules/Worker/complain/photo";

	}
}
