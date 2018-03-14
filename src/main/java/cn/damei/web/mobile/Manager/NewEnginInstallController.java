package cn.damei.web.mobile.Manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.constantUtils.BusinessLogConstantUtil;
import cn.damei.common.constantUtils.BusinessUrgeConstantUtil;
import cn.damei.common.utils.PicRootName;
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.mobile.Inspector.ReportCheckDetailsPic;
import cn.damei.entity.mobile.Manager.EnginInstall;
import cn.damei.entity.mobile.Manager.OrderInstallPlan;
import cn.damei.entity.mobile.Manager.PlanAdvanceApplyDTO;
import cn.damei.service.mobile.Manager.EnginInstallService;
import cn.damei.service.mobile.Manager.NewEnginInstallService;
import cn.damei.entity.mobile.Manager.Manager;
import cn.damei.service.mobile.Manager.ProblemService;
import cn.damei.entity.modules.BizBusinessStatusLog;
import cn.damei.entity.modules.BizBusinessUrge;
import cn.damei.service.modules.BizBusinessUrgeService;



@Controller
@RequestMapping(value = "${adminPath}/app/manager/newEnginInstallController")
public class NewEnginInstallController {
	private static Logger logger = LoggerFactory.getLogger(NewEnginInstallController.class);

	@Autowired
	private NewEnginInstallService newEnginInstallService;
	@Autowired
	private EnginInstallService enginInstallService;
	@Autowired
	private BizBusinessUrgeService bizBusinessUrgeService;
	@Autowired
	private ProblemService problemService;



	@RequestMapping(value = { "installListNew", "" })
	public String installListNew(String apply, String index,HttpServletRequest request, Model model) {

		if ("0".equals(index) || "1".equals(index)) {
			request.getSession().setAttribute("index", index);
		} else {
			logger.warn("工程安装访问参数有误 ,无法识别路径参数: index :" + index);
		}


		Manager manager = (Manager) request.getSession().getAttribute("manager");
		if (null != manager) {
			model.addAttribute("managerId", manager.getId());
		}
		if (null != apply && apply.equals("1")) {
			model.addAttribute("apply", apply);
		}

		return "mobile/modules/Manager/progressMain/enginInstall/new/installList";
	}



	@RequestMapping(value = "installListNew_ajax_list")
	public @ResponseBody List<EnginInstall> installListNewAjaxList(@RequestParam(required = true)Integer managerId, @RequestParam(required = false)String text) {
		return newEnginInstallService.queryInstallOrderList(managerId, BusinessLogConstantUtil.SETTLE_MONEY_TYPE_MID_1, text);
	}
	

	@RequestMapping(value = "check_install_plan_count_ajax")
	@ResponseBody
	public String checkInstallPlanCountAjax(String orderId) {
		
		return newEnginInstallService.queryInstallPlanCount(orderId);
	}
	

	@RequestMapping(value = { "installApplication", "" })
	public String installApplication(String id, Model model) {

		EnginInstall enginInstall = null;
		if (!id.equals("")) {
			enginInstall = enginInstallService.queryOrderDetails(Integer.valueOf(id));
		}
		model.addAttribute("order", enginInstall);

		return "mobile/modules/Manager/progressMain/enginInstall/new/installApplyList";
	}


	@RequestMapping(value = "installApplyList_ajax_list")
	public @ResponseBody List<OrderInstallPlan> installApplyListAjaxList(String orderId) {

		List<OrderInstallPlan> installPlanList = null;
		if (null != orderId && !("").equals(orderId)) {
			installPlanList = newEnginInstallService.queryInstallPlanApplyList(Integer.valueOf(orderId));
		}

		return installPlanList;

	}
	

	@RequestMapping(value = "checksizeAjax")
	@ResponseBody
	public String checksizeAjax(String id) {
		return newEnginInstallService.installPlanChecksizeAjax(id);
	}
	


	@RequestMapping(value = "push_installation_ajax")
	public @ResponseBody String pushInstallationAjax(String id, HttpServletRequest request) {

		Manager manager = (Manager) request.getSession().getAttribute("manager");
		return newEnginInstallService.queryPushInstallation(id,manager);

	}


	@RequestMapping(value = { "push_installation", "" })
	public String pushInstallation(String id, String orderId, String pushinstall, Model model) {

		model.addAttribute("orderId", orderId);
		model.addAttribute("id", id);
		model.addAttribute("pushinstall", pushinstall);

		return "mobile/modules/Manager/progressMain/enginInstall/new/installApply";
	}


	@RequestMapping(value = "save_push_installation_ajax")
	public @ResponseBody String savePushInstallationAjax(String id, String orderId, String operateContent, HttpServletRequest request) {

		Manager manager = (Manager) request.getSession().getAttribute("manager");
		return newEnginInstallService.savePushInstallation(id,orderId,operateContent,manager);

	}


	@RequestMapping(value = { "progress_log", "" })
	public String progressLog(String id, Model model) {

		OrderInstallPlan orderInstallPlan = null;
		if (null != id && !id.equals("")) {
			orderInstallPlan = newEnginInstallService.queryInstallPlanDetails(Integer.valueOf(id));
		}
		model.addAttribute("order", orderInstallPlan);

		return "mobile/modules/Manager/progressMain/enginInstall/new/installProgressRecord";
	}


	@RequestMapping(value = "urgeList_ajax_list")
	public @ResponseBody List<BizBusinessUrge> urgeListAjaxList(String id) {

		List<BizBusinessUrge> businessUrgeList = null;
		if (null != id && !("").equals(id)) {

			BizBusinessUrge bizBusinessUrge = new BizBusinessUrge();

			bizBusinessUrge.setBusinessOnlyMarkInt(Integer.valueOf(id));

			bizBusinessUrge.setBusinesType(BusinessUrgeConstantUtil.BUSINESS_URGE_BUSINESS_TYPE_1);

			businessUrgeList = bizBusinessUrgeService.findList(bizBusinessUrge);
		}

		return businessUrgeList;

	}


	@RequestMapping(value = "operationList_ajax_list")
	public @ResponseBody List<BizBusinessStatusLog> operationListAjaxList(String id) {

		List<BizBusinessStatusLog> operationList = null;
		if (StringUtils.isNotBlank(id)) {

			operationList = newEnginInstallService.findOperationList(Integer.valueOf(id));
		}
		return operationList;

	}


	@RequestMapping(value = { "install_reply_page", "" })
	public String installReplyPage(String id, Model model) {

		model.addAttribute("id", id);

		return "mobile/modules/Manager/progressMain/enginInstall/new/installReply";
	}


	@RequestMapping(value = "save_install_reply_ajax")
	public @ResponseBody String saveInstallReplyAjax(String id, String operateContent, HttpServletRequest request) {

		Manager manager = (Manager) request.getSession().getAttribute("manager");
		
		return newEnginInstallService.saveInstallReply(id, operateContent, manager);

	}


	@RequestMapping(value = { "urge_pic", "" })
	public String urgePic(Integer id, Model model) throws IOException {

		String text = "";
		List<ReportCheckDetailsPic> picList = problemService.findPic(id, text);
		String baseUrl = PicRootName.picPrefixName();
		model.addAttribute("picList", picList);
		model.addAttribute("baseUrl", baseUrl);
		return "mobile/modules/Manager/progressMain/enginInstall/new/photo";
	}


	@RequestMapping(value = { "appInstall", "" })
	public String appInstall(Integer id, Integer orderId, Model model) {

		PlanAdvanceApplyDTO dto = newEnginInstallService.queryInstallPlan(orderId,id);
		model.addAttribute("order", dto.getEnginInstall());
		model.addAttribute("orderInstallPlan", dto.getOrderInstallPlan());
		
		return "mobile/modules/Manager/progressMain/enginInstall/new/apply";
	}


	@RequestMapping(value = { "reAppInstall", "" })
	public String reAppInstall(Integer id, Integer orderId, Model model) {

		PlanAdvanceApplyDTO dto = newEnginInstallService.queryInstallPlan(orderId,id);
		model.addAttribute("order", dto.getEnginInstall());
		model.addAttribute("orderInstallPlan", dto.getOrderInstallPlan());
		
		return "mobile/modules/Manager/progressMain/enginInstall/new/applyList";
	}


	@RequestMapping(value = "save_new_install_apply_ajax")
	public @ResponseBody String saveNewInstallApplyAjax(String orderId, String orderInstallItemId, String dataday2, String remarks, String installRemarks, String isShowInstallDescription, HttpServletRequest request) {

		Manager manager = (Manager) request.getSession().getAttribute("manager");
		return 	newEnginInstallService.saveInstallPlanApply(orderId,orderInstallItemId,dataday2,remarks,installRemarks,isShowInstallDescription,manager,request);
	}
	

	@RequestMapping(value = { "installPlanAdvanceApply", "" })
	public String installPlanAdvanceApply(Integer id, Integer orderId, Model model) {
		PlanAdvanceApplyDTO dto = newEnginInstallService.queryPlanAdvanceApply(orderId, id);
		model.addAttribute("order", dto.getEnginInstall());
		model.addAttribute("orderInstallPlan", dto.getOrderInstallPlan());
		return "mobile/modules/Manager/progressMain/enginInstall/new/InstallApply-earlyApply";
	}
	
	

	@RequestMapping(value = "save_install_apply_advance_apply_ajax")
	public @ResponseBody String saveInstallApplyAdvanceApplyAjax(String orderInstallItemId,String[] photo, HttpServletRequest request) {
		Manager manager = (Manager) request.getSession().getAttribute("manager");
		return 	newEnginInstallService.saveInstallApplyAdvanceApply(orderInstallItemId,photo,manager,request);
	}



	@RequestMapping(value = "sendUrgeMessage.php")
	public @ResponseBody String sendUrgeMessage(String orderId, HttpServletRequest request) {
		return newEnginInstallService.sendUrgeMessage(orderId,request);
	}
	
}
