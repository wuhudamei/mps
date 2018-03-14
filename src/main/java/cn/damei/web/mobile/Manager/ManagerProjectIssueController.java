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

/**
 * Created by joseph on 2017/7/4.
 */

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
		// 问题创建时间
		Date problemCreateDate = (Date) map.get("createDate");

		// 问题的截止日期
		Date problemDelayDate = DateUtils.addDate(problemCreateDate, (new Double(responseTime)).intValue());

		// 如果截止日期小于实际解决日期 就算超时
		if (problemDelayDate.getTime() < date.getTime()) {

			map.put("delayDays", Math.abs(DateUtils.getDistanceOfTwoDate(date, problemDelayDate)) > 0 ? Math.abs(DateUtils.getDistanceOfTwoDate(date, problemDelayDate)) + 1 : 1);

		}

		model.addAttribute("map", map);

		return "mobile/modules/Manager/projectIssue/complaint3";

	}

	/**
	 * 
	 * @Title: ajaxupdateScuStats
	 * @Description:
	 * @param @param workOrderCode 售后系统的唯一标示
	 * @param @param cusServiceid 我们售后表的id
	 * @param @param status 状态
	 * @param @return
	 * @return Map<String,Object>
	 * @author ZhangTongWei
	 * @throws
	 */
	@RequestMapping(value = "ajaxupdateScuStatsapp")
	@ResponseBody
	public Map<String, Object> ajaxupdateScuStats(String workOrderCode, String cusServiceid, String status) {
		// String sendHttp1 = CusserviceUtils.sendHttp(workOrderCode, status,
		// "http://192.168.1.90:8012/service/orderUpdate");
		// 准生产59.110.170.55:60101
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

	/**
	 * 保存项目经理处理投诉问题 数据
	 * 
	 * @Title: saveManagerDeal
	 * @Description: TODO
	 * @param @param photos 图片
	 * @param @param dealDescribe
	 * @param @param request
	 * @param @param handleId 投诉单iD
	 * @param @param complaintProblemItemId 投诉事项ID
	 * @param @param orderId 订单ID
	 * @param @param delayDays 超时时间
	 * @param @param model
	 * @param @return
	 * @return String
	 * @author ZhangTongWei
	 * @throws
	 */
	@RequestMapping(value = "saveManagerDeal")
	@ResponseBody
	public String saveManagerDeal(String photos[], String dealDescribe, HttpServletRequest request, @RequestParam(value = "handleId") Integer handleId, Integer complaintProblemItemId, Integer orderComplaintProblemId, Integer orderId, @RequestParam(value = "delayDays") Double delayDays, Model model) {

		// 去重复数据校验, 查询是否已经处理过该问题
		// 保存答复内容
		// 更新处理表状态-->20
		// 更新投诉问题状态->30 (已处理)
		// 保存图片
		// 保存对应log

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

		// 根据处理id查询处理详情
		Map<String, Object> map = service.findProblemByHandleId(handleId);

		String responseTime = map.get("responseTime").toString();
		// 问题创建时间
		Date problemCreateDate = (Date) map.get("createDate");
		// 问题解决时间
		Date statusDateTime = (Date) map.get("statusDateTime");

		// 问题的截止日期
		Date problemDelayDate = DateUtils.addDate(problemCreateDate, (new Double(responseTime)).intValue());

		// 如果截止日期小于实际解决日期 就算超时
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
			// 处理问题的图片
			businessIdInt = String.valueOf(handleId);
			businessType = PictureTypeContantUtil.PICTURE_TYPE_109;

		} else {
			// 问题的图片
			businessIdInt = String.valueOf(relatedId);
			businessType = PictureTypeContantUtil.PICTURE_TYPE_200;

		}
		// 根据处理id
		Map<String, String> map = new HashMap<>();
		// 查询关联图片列表
		map.put("businessIdInt", businessIdInt);
		map.put("businessType", businessType);

		List<String> list = service.findPic(map);

		model.addAttribute("picList", list);
		String baseUrl = request.getContextPath();
		model.addAttribute("baseUrl", baseUrl);

		return "mobile/modules/Manager/projectIssue/photo";

	}
}
