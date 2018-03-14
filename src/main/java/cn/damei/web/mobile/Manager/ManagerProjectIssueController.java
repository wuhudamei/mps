package cn.damei.web.mobile.Manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.constantUtils.PictureTypeContantUtil;
import cn.damei.common.constantUtils.projectProblem.ProjectProblemConstantUtil;
import cn.damei.common.utils.DateUtils;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.SessionUtils;
import cn.damei.entity.mobile.Manager.Manager;
import cn.damei.entity.mobile.Manager.ManagerProjectIssueBean;
import cn.damei.service.mobile.Manager.ManagerProjectIssueService;
import cn.damei.common.utils.CusserviceUtils;



@Controller
@RequestMapping(value = "${adminPath}/app/manager/project-issue")
public class ManagerProjectIssueController {

	@Autowired
	private ManagerProjectIssueService service;

	@RequestMapping(value = "list.php")
	public String list(HttpServletRequest request, Model model) {

		Integer managerId = SessionUtils.getManagerSession(request).getId();
		List<Map<String, Object>> mapList = service.findProjectProblemByDealPersonId(managerId);

		model.addAttribute("mapList", mapList);
		return "mobile/modules/Manager/projectIssue/complaint";

	}

	@RequestMapping(value = "checkIssueProblemByOrderId")
	public String checkIssueProblemByOrderId(HttpServletRequest request, @RequestParam(value = "orderId") Integer orderId, Model model) {
		Manager manager = SessionUtils.getManagerSession(request);

		Map<String, Object> map = new HashMap<>(12);

		map.put("orderId", orderId);
		map.put("dealType", ProjectProblemConstantUtil.PROJECT_DEAL_PERSON_TYPE_MANAGER_1);
		map.put("dealPersonId", manager.getId());
		List<Map<String, Object>> maps = service.findProblemByOrderId(map);
		if (maps.size() > 0) {

			model.addAttribute("customerInfo", maps.get(0).get("customerInfo"));
		}
		model.addAttribute("mapList", maps);

		return "mobile/modules/Manager/projectIssue/complaint2";

	}

	@RequestMapping(value = "findWorkerInfoByRelatedId")
	public String findWorkerInfoByRelatedId(HttpServletRequest request, @RequestParam(value = "relatedId") Integer relatedId, Model model) {

		List<Map<String, Object>> mapList = service.findWorkerInfoByRelatedId(relatedId);

		model.addAttribute("mapInfo", mapList.size() > 0 ? mapList.get(0) : new ArrayList<>(2));
		model.addAttribute("workerList", mapList);
		return "mobile/modules/Manager/projectIssue/feedbackDetails";

	}

	@RequestMapping(value = "managerDealByHandleId")
	public String managerDealByHandleId(HttpServletRequest request, Integer handleId, Model model) {
		Date date = new Date();
		Map<String, Object> map = service.findProblemByHandleId(handleId);

		String responseTime = map.get("responseTime").toString();

		Date problemCreateDate = (Date) map.get("createDate");


		Date problemDelayDate = DateUtils.addDate(problemCreateDate, (new Double(responseTime)).intValue());


		if (problemDelayDate.getTime() < date.getTime()) {

			map.put("delayDays", Math.abs(DateUtils.getDistanceOfTwoDate(date, problemDelayDate)) > 0 ? Math.abs(DateUtils.getDistanceOfTwoDate(date, problemDelayDate)) + 1 : 1);

		}

		model.addAttribute("map", map);

		return "mobile/modules/Manager/projectIssue/complaint3";

	}


	@RequestMapping(value = "ajaxupdateScuStatsapp")
	@ResponseBody
	public Map<String, Object> ajaxupdateScuStats(String workOrderCode, String cusServiceid, String status) {



		String sendHttp2 = "";
		String result = "";
		String msg = "";

		sendHttp2 = CusserviceUtils.sendHttp(workOrderCode, "COMPLETED", null);
		result = "1";

		if (StringUtils.isNotBlank(sendHttp2) && sendHttp2.equals("SUCCESS")) {
			ManagerProjectIssueBean bizCusServiceProblem = new ManagerProjectIssueBean();
			bizCusServiceProblem.setId(cusServiceid);
			bizCusServiceProblem.setStatus(ProjectProblemConstantUtil.PROJECT_PROBLEM_COMPLAINT_STATUS_10);
			bizCusServiceProblem.setStatusdatetime(new Date());
			service.updateStatus(bizCusServiceProblem);
			result = "1";
		} else {
			result = "0";
			msg = sendHttp2;
			if (StringUtils.isBlank(sendHttp2)) {
				msg = "请求接口失败";
			}
		}

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("code", result);
		resultMap.put("msg", msg);
		return resultMap;
	}


	@RequestMapping(value = "saveManagerDeal")
	@ResponseBody
	public String saveManagerDeal(String photos[], String dealDescribe, HttpServletRequest request, @RequestParam(value = "handleId") Integer handleId, Integer complaintProblemItemId, Integer orderComplaintProblemId, Integer orderId, @RequestParam(value = "delayDays") Double delayDays, Model model) {








		String result = service.saveManagerDeal(request, dealDescribe, handleId, complaintProblemItemId, orderId, delayDays, orderComplaintProblemId, photos);
		return result;
	}

	@RequestMapping(value = "selectCountNoDealByWorkOrderCode")
	@ResponseBody
	public int selectCountNoDealByWorkOrderCode(String workOrderCode) {

		return service.selectCountNoDealByWorkOrderCode(workOrderCode);
	}

	@RequestMapping(value = "managerCheckDealDetail")
	public String managerCheckDealDetail(HttpServletRequest request, Integer handleId, Model model) {


		Map<String, Object> map = service.findProblemByHandleId(handleId);

		String responseTime = map.get("responseTime").toString();

		Date problemCreateDate = (Date) map.get("createDate");

		Date statusDateTime = (Date) map.get("statusDateTime");


		Date problemDelayDate = DateUtils.addDate(problemCreateDate, (new Double(responseTime)).intValue());


		if (problemDelayDate.getTime() < statusDateTime.getTime()) {

			Double value = Math.abs(DateUtils.getDistanceOfTwoDate(problemDelayDate, statusDateTime)) > 0 ? Math.abs(DateUtils.getDistanceOfTwoDate(problemDelayDate, statusDateTime)) + 1 : 1;
			map.put("delayDays", value);

		}

		model.addAttribute("map", map);
		return "mobile/modules/Manager/projectIssue/complainDetails";

	}

	@RequestMapping(value = "checkPic")
	public String checkPic(Integer handleId, Model model, HttpServletRequest request, Integer relatedId) {
		String businessIdInt;
		String businessType;

		if (null == relatedId && null != handleId) {

			businessIdInt = String.valueOf(handleId);
			businessType = PictureTypeContantUtil.PICTURE_TYPE_109;

		} else {

			businessIdInt = String.valueOf(relatedId);
			businessType = PictureTypeContantUtil.PICTURE_TYPE_200;

		}

		Map<String, String> map = new HashMap<>();

		map.put("businessIdInt", businessIdInt);
		map.put("businessType", businessType);

		List<String> list = service.findPic(map);

		model.addAttribute("picList", list);
		String baseUrl = request.getContextPath();
		model.addAttribute("baseUrl", baseUrl);

		return "mobile/modules/Manager/projectIssue/photo";

	}
}
