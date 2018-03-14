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


/**
 * 工程安装
 * 
 * @author wyb
 */
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


	/**
	 * 安装申请
	 * @param apply
	 * @param index
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "installListNew", "" })
	public String installListNew(String apply, String index,HttpServletRequest request, Model model) {

		if ("0".equals(index) || "1".equals(index)) {
			request.getSession().setAttribute("index", index);
		} else {
			logger.warn("工程安装访问参数有误 ,无法识别路径参数: index :" + index);
		}

		// 获取项目经理sesseion
		Manager manager = (Manager) request.getSession().getAttribute("manager");
		if (null != manager) {
			model.addAttribute("managerId", manager.getId());
		}
		if (null != apply && apply.equals("1")) {
			model.addAttribute("apply", apply);
		}

		return "mobile/modules/Manager/progressMain/enginInstall/new/installList";
	}


	/**
	 * 动态加载申请安装列表
	 * 
	 * @param managerId
	 * @param text
	 * @return
	 */
	@RequestMapping(value = "installListNew_ajax_list")
	public @ResponseBody List<EnginInstall> installListNewAjaxList(@RequestParam(required = true)Integer managerId, @RequestParam(required = false)String text) {
		return newEnginInstallService.queryInstallOrderList(managerId, BusinessLogConstantUtil.SETTLE_MONEY_TYPE_MID_1, text);
	}
	
	/**
	 * 校验给订单是否有可申请的安装项
	 * 
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value = "check_install_plan_count_ajax")
	@ResponseBody
	public String checkInstallPlanCountAjax(String orderId) {
		
		return newEnginInstallService.queryInstallPlanCount(orderId);
	}
	
	/**
	 * 工程安装List页面 订单状态等于【200-施工中】。 安装申请 计划功能
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "installApplication", "" })
	public String installApplication(String id, Model model) {

		EnginInstall enginInstall = null;// 订单
		if (!id.equals("")) {
			enginInstall = enginInstallService.queryOrderDetails(Integer.valueOf(id));
		}
		model.addAttribute("order", enginInstall);

		return "mobile/modules/Manager/progressMain/enginInstall/new/installApplyList";
	}

	/**
	 * 动态加载 安装项
	 * 
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value = "installApplyList_ajax_list")
	public @ResponseBody List<OrderInstallPlan> installApplyListAjaxList(String orderId) {

		List<OrderInstallPlan> installPlanList = null;// 订单安装项
		if (null != orderId && !("").equals(orderId)) {
			installPlanList = newEnginInstallService.queryInstallPlanApplyList(Integer.valueOf(orderId));
		}

		return installPlanList;

	}
	
	/**
	 * 安装申请
	 * 校验
	 *  1.该主材只有复尺后才能申请安装!
	 *  2.家具申请时，订单包含的橱柜、木门、木地板安装项未验收完毕，请及时处理。
	 *  3.该工地2017-08-22开工，按照工程部规定主材（家电安装）开工58天后（2017-10-19）才可以申请安装，如果需提前申请，请点击【提前申请】，如有其它问题请联系大区经理或助理。
	 *  4.每个安装项【提前申请】只能申请一次
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "checksizeAjax")
	@ResponseBody
	public String checksizeAjax(String id) {
		return newEnginInstallService.installPlanChecksizeAjax(id);
	}
	

	/**
	 * ajax 催促安装，一天最多允许催促2次 申请安装后24小时内不能进行催促安装
	 * 
	 * @param id
	 *            安装项ID
	 * @return
	 */
	@RequestMapping(value = "push_installation_ajax")
	public @ResponseBody String pushInstallationAjax(String id, HttpServletRequest request) {

		Manager manager = (Manager) request.getSession().getAttribute("manager");
		return newEnginInstallService.queryPushInstallation(id,manager);

	}

	/**
	 * 催促安装页面
	 * 
	 * @param id
	 * @param orderId
	 * @param pushinstall
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "push_installation", "" })
	public String pushInstallation(String id, String orderId, String pushinstall, Model model) {

		model.addAttribute("orderId", orderId);
		model.addAttribute("id", id);
		model.addAttribute("pushinstall", pushinstall);

		return "mobile/modules/Manager/progressMain/enginInstall/new/installApply";
	}

	/**
	 * ajax 催促安装
	 * 
	 * @param id
	 *            安装项ID
	 * @param orderId
	 * @param operateContent
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "save_push_installation_ajax")
	public @ResponseBody String savePushInstallationAjax(String id, String orderId, String operateContent, HttpServletRequest request) {

		Manager manager = (Manager) request.getSession().getAttribute("manager");
		return newEnginInstallService.savePushInstallation(id,orderId,operateContent,manager);

	}

	/**
	 * 进度日志页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "progress_log", "" })
	public String progressLog(String id, Model model) {

		OrderInstallPlan orderInstallPlan = null;// 安装项
		if (null != id && !id.equals("")) {
			orderInstallPlan = newEnginInstallService.queryInstallPlanDetails(Integer.valueOf(id));
		}
		model.addAttribute("order", orderInstallPlan);

		return "mobile/modules/Manager/progressMain/enginInstall/new/installProgressRecord";
	}

	/**
	 * 动态加载 进度日志
	 * 
	 * @param id
	 *            安装项ID
	 * @return
	 */
	@RequestMapping(value = "urgeList_ajax_list")
	public @ResponseBody List<BizBusinessUrge> urgeListAjaxList(String id) {

		List<BizBusinessUrge> businessUrgeList = null;
		if (null != id && !("").equals(id)) {

			BizBusinessUrge bizBusinessUrge = new BizBusinessUrge();
			// 业务唯一标识整形
			bizBusinessUrge.setBusinessOnlyMarkInt(Integer.valueOf(id));
			// 业务类型
			bizBusinessUrge.setBusinesType(BusinessUrgeConstantUtil.BUSINESS_URGE_BUSINESS_TYPE_1);

			businessUrgeList = bizBusinessUrgeService.findList(bizBusinessUrge);
		}

		return businessUrgeList;

	}

	/**
	 * 动态加载 操作日志
	 * 
	 * @param id
	 *            安装项ID
	 * @return
	 */
	@RequestMapping(value = "operationList_ajax_list")
	public @ResponseBody List<BizBusinessStatusLog> operationListAjaxList(String id) {

		List<BizBusinessStatusLog> operationList = null;
		if (StringUtils.isNotBlank(id)) {
			// 查询操作日志
			operationList = newEnginInstallService.findOperationList(Integer.valueOf(id));
		}
		return operationList;

	}

	/**
	 * 安装申请回复页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "install_reply_page", "" })
	public String installReplyPage(String id, Model model) {

		model.addAttribute("id", id);

		return "mobile/modules/Manager/progressMain/enginInstall/new/installReply";
	}

	/**
	 * ajax 安装申请回复
	 * 
	 * @param id
	 *            安装项ID
	 * @param operateContent
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "save_install_reply_ajax")
	public @ResponseBody String saveInstallReplyAjax(String id, String operateContent, HttpServletRequest request) {

		Manager manager = (Manager) request.getSession().getAttribute("manager");
		
		return newEnginInstallService.saveInstallReply(id, operateContent, manager);

	}

	/**
	 * 进度日志--详情--图片
	 * 
	 * @param id
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = { "urge_pic", "" })
	public String urgePic(Integer id, Model model) throws IOException {

		String text = "";
		List<ReportCheckDetailsPic> picList = problemService.findPic(id, text);
		String baseUrl = PicRootName.picPrefixName();
		model.addAttribute("picList", picList);
		model.addAttribute("baseUrl", baseUrl);
		return "mobile/modules/Manager/progressMain/enginInstall/new/photo";
	}

	/**
	 * 申请安装页面
	 * 
	 * @param id
	 * @param orderId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "appInstall", "" })
	public String appInstall(Integer id, Integer orderId, Model model) {

		PlanAdvanceApplyDTO dto = newEnginInstallService.queryInstallPlan(orderId,id);
		model.addAttribute("order", dto.getEnginInstall());
		model.addAttribute("orderInstallPlan", dto.getOrderInstallPlan());
		
		return "mobile/modules/Manager/progressMain/enginInstall/new/apply";
	}

	/**
	 * 重新 申请安装页面
	 * 
	 * @param id
	 * @param orderId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "reAppInstall", "" })
	public String reAppInstall(Integer id, Integer orderId, Model model) {

		PlanAdvanceApplyDTO dto = newEnginInstallService.queryInstallPlan(orderId,id);
		model.addAttribute("order", dto.getEnginInstall());
		model.addAttribute("orderInstallPlan", dto.getOrderInstallPlan());
		
		return "mobile/modules/Manager/progressMain/enginInstall/new/applyList";
	}

	/**
	 * ajax 安装申请
	 * @param orderId
	 * @param orderInstallItemId
	 * @param dataday2
	 * @param remarks
	 * @param installRemarks
	 * @param isShowInstallDescription
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "save_new_install_apply_ajax")
	public @ResponseBody String saveNewInstallApplyAjax(String orderId, String orderInstallItemId, String dataday2, String remarks, String installRemarks, String isShowInstallDescription, HttpServletRequest request) {

		Manager manager = (Manager) request.getSession().getAttribute("manager");
		return 	newEnginInstallService.saveInstallPlanApply(orderId,orderInstallItemId,dataday2,remarks,installRemarks,isShowInstallDescription,manager,request);
	}
	
	/**
	 * 申请主材安装--快捷申请【提前申请】
	 * 
	 * @param id
	 * @param orderId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "installPlanAdvanceApply", "" })
	public String installPlanAdvanceApply(Integer id, Integer orderId, Model model) {
		PlanAdvanceApplyDTO dto = newEnginInstallService.queryPlanAdvanceApply(orderId, id);
		model.addAttribute("order", dto.getEnginInstall());
		model.addAttribute("orderInstallPlan", dto.getOrderInstallPlan());
		return "mobile/modules/Manager/progressMain/enginInstall/new/InstallApply-earlyApply";
	}
	
	
	/**
	 * ajax 主材安装项提前申请记录保存【提前申请】
	 * @param orderInstallItemId 安装项ID
	 * @param photo 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "save_install_apply_advance_apply_ajax")
	public @ResponseBody String saveInstallApplyAdvanceApplyAjax(String orderInstallItemId,String[] photo, HttpServletRequest request) {
		Manager manager = (Manager) request.getSession().getAttribute("manager");
		return 	newEnginInstallService.saveInstallApplyAdvanceApply(orderInstallItemId,photo,manager,request);
	}


	/**
	 * 该订单未交二期款,赶紧催客户交钱-客户未缴二期款发送一次短信 (设计师+客户)
	 */
	@RequestMapping(value = "sendUrgeMessage.php")
	public @ResponseBody String sendUrgeMessage(String orderId, HttpServletRequest request) {
		return newEnginInstallService.sendUrgeMessage(orderId,request);
	}
	
}
