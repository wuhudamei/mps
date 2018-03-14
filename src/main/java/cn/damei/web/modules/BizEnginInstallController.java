package cn.damei.web.modules;

import cn.damei.common.constantUtils.BusinessLogConstantUtil;
import cn.damei.common.constantUtils.BusinessUrgeConstantUtil;
import cn.damei.common.constantUtils.InstallPlanConstantUtil;
import cn.damei.common.persistence.Page;
import cn.damei.common.utils.DataAuthorityConstantUtils;
import cn.damei.common.utils.MessagePushType;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.utils.phoneMessage.PhoneMessageDao;
import cn.damei.common.utils.phoneMessage.PhoneMessageEntity;
import cn.damei.common.web.BaseController;
import cn.damei.web.mobile.home.JobSiteController;
import cn.damei.entity.modules.BizEnginInstall;
import cn.damei.entity.modules.BizOrderInstallPlan;
import cn.damei.service.modules.BizEnginInstallService;
import cn.damei.service.modules.BizOrderInstallPlanService;
import cn.damei.entity.modules.BizBusinessStatusLog;
import cn.damei.service.modules.BizBusinessStatusLogService;
import cn.damei.entity.modules.BizBusinessUrge;
import cn.damei.service.modules.BizBusinessUrgeService;
import cn.damei.entity.modules.BizMsg;
import cn.damei.service.modules.BizProjectChangeBillService;
import cn.damei.service.modules.DataAuthorityService;
import cn.damei.entity.modules.BizProjectInstallItem;
import cn.damei.service.modules.BizProjectInstallItemService;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 *	带货安装申请
 */
@Controller
@RequestMapping(value = "${adminPath}/bizengininstall/bizEnginInstall")
public class BizEnginInstallController extends BaseController {

	@Autowired
	private BizEnginInstallService bizEnginInstallService;

	@Autowired
	private BizProjectInstallItemService bizProjectInstallItemService;

	@Autowired
	private BizOrderInstallPlanService bizOrderInstallPlanService;
	@Autowired
	private BizBusinessUrgeService bizBusinessUrgeService;
	@Autowired
	private BizBusinessStatusLogService bizBusinessStatusLogService;
	@Autowired
	private DataAuthorityService dataAuthorityService;

	@ModelAttribute
	public BizEnginInstall get(@RequestParam(required = false) Integer id) {
		BizEnginInstall entity = null;
		if (StringUtils.isNotBlank(id + "")) {

			entity = bizEnginInstallService.get(id);
		}
		if (entity == null) {
			entity = new BizEnginInstall();
		}
		return entity;
	}

	@RequiresPermissions("bizengininstall:bizEnginInstall:view")
	@RequestMapping(value = { "preList", "" })
	public String preList(BizEnginInstall bizEnginInstall, HttpServletRequest request, HttpServletResponse response, Model model) {
		// bizEnginInstall.setIsUrgeReply(InstallPlanConstantUtil.URGE_REPLY_1);
		bizEnginInstall.setInstallStatus(InstallPlanConstantUtil.INSTALL_PLAN_STATUS_2);
		return "modules/bizEnginInstall/preBizEnginList";
	}

	@RequiresPermissions("bizengininstall:bizEnginInstall:view")
	@RequestMapping(value = { "list", "" })
	public String list(BizEnginInstall bizEnginInstall, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		List<String> orderdPhones = dataAuthorityService.orderdPhones(DataAuthorityConstantUtils.Biz_Business_Type_DD);
		bizEnginInstall.setPhones(orderdPhones);

		String userId = user.getId();
		bizEnginInstall.setUserId(userId);

		Page<BizEnginInstall> page = bizEnginInstallService.findPage(new Page<BizEnginInstall>(request, response), bizEnginInstall);

		if (bizEnginInstall.getInstallItemName() != null) {
			model.addAttribute("installName", bizEnginInstall.getInstallItemName());
		}

		model.addAttribute("page", page);
		return "modules/bizEnginInstall/preBizEnginList";
	}

	/**
	 * 下达供应商
	 * 
	 * @param installId
	 * @param orderId
	 * @param supplierConfirmIntoDate
	 * @param supplierConfirmRemarks
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "updateByStatus", "" })
	public @ResponseBody String updateByStatus(String installId, String orderId, String supplierConfirmIntoDate, String supplierConfirmRemarks, HttpServletRequest request, HttpServletResponse response) {

		String result = "0";
		// 1.安装项ID为空
		if (null == installId || installId.equals("")) {
			result = "1";
			return result;
		}
		// 2.订单ID为空
		if (null == orderId || orderId.equals("")) {
			result = "2";
			return result;
		}
		// 3.供应商确认日期为空
		if (null == supplierConfirmIntoDate || supplierConfirmIntoDate.equals("")) {
			result = "3";
			return result;
		}
		// 4.当前登录人
		User user = UserUtils.getUser();
		Integer empId = null;
		if (null != user && null != user.getEmpId() && !user.getEmpId().equals("")) {
			empId = Integer.valueOf(user.getEmpId());
		}
		// 5.更新状态
		String flag = bizOrderInstallPlanService.updateByIdAndStatus(Integer.valueOf(installId), InstallPlanConstantUtil.INSTALL_PLAN_STATUS_3, supplierConfirmIntoDate, supplierConfirmRemarks);
		if (flag == "1") {
			result = "4";
			return result;
		}

		// 6.保存状态日志
		Integer logId = bizBusinessStatusLogService.saveBusinessStatusLog(empId, Integer.valueOf(installId), BusinessLogConstantUtil.BUSINESS_TYPE_901, InstallPlanConstantUtil.INSTALL_PLAN_STATUS_3, "安装项" + InstallPlanConstantUtil.INSTALL_PLAN_STATUS_NAME_3);
		if (null == logId || logId < 1) {
			result = "5";
			return result;
		}

		// 7.保存短信
		// 订单（小区名称-楼号-单元号-门牌号-客户姓名-带货安装项名称）材料员已转单给供应商，供应商确认进场日期为（2017-02-20），请您知晓。”
		BizEnginInstall enginInstall = new BizEnginInstall();
		enginInstall.setInstallID(Integer.parseInt(installId));

		PhoneMessageEntity entity = new PhoneMessageEntity();
		Page<BizEnginInstall> page = bizEnginInstallService.findPage(new Page<BizEnginInstall>(request, response), enginInstall);
		BizEnginInstall install = page.getList().get(0);

		entity.setMessageGenerateTime(new Date());
		entity.setReceiveEmployeeId(install.getItemManagerId());
		entity.setMessageContent("订单（" + install.getCommunityName() + "-" + install.getBuildNumber() + "-" + install.getBuildUnit() + "-" + install.getBuildRoom() + "-" + install.getCustomerName() + "-" + install.getCustomerName() + ") 材料员已转单给供应商，供应商确认进场日期为(" + install.getInstallItemName() + ")，请您知晓");
		entity.setReceivePhone(install.getEmployeePhone());
		entity.setRelatedBusinessId(Integer.parseInt(installId));
		entity.setRelatedBusinessType("555555");
		entity.setStatus("0");

		messageDao.saveMessageContent(entity);

		// 8.站内消息
		BizMsg bizMsg = new BizMsg();
		bizMsg.setMsgTitle("下达供应商");
		bizMsg.setMsgTime(new Date());
		bizMsg.setMsgContent("订单（" + install.getCommunityName() + "-" + install.getBuildNumber() + "-" + install.getBuildUnit() + "-" + install.getBuildRoom() + "-" + install.getCustomerName() + "-" + install.getCustomerName() + ") 材料员已转单给供应商，供应商确认进场日期为(" + install.getInstallItemName() + ")，请您知晓");
		bizMsg.setMsgType(MessagePushType.MSG_TYPE_1);
		bizMsg.setBusiType("55555");
		bizMsg.setBusiIdInt(Integer.parseInt(installId));
		bizMsg.setEmployeeId(install.getItemManagerId());
		bizProjectChangeBillService.saveBizMsg(bizMsg);
		logger.info("install主键编号：" + installId + "\t 订单编号：" + orderId);

		return result;
	}

	/**
	 * 回复
	 * 
	 * @param installPlanId
	 * @param urgeReply
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "save_install_reply")
	public @ResponseBody String saveInstallReply(String installPlanId, String urgeReply, HttpServletRequest request) {

		String result = "0";

		// 1.安装申请回复 安装项ID为空
		if (null == installPlanId || installPlanId.equals("")) {
			result = "1";
			return result;
		}
		// 2.获取材料部
		User user = UserUtils.getUser();
		Integer managerId = null;
		if (null != user && null != user.getEmpId() && !user.getEmpId().equals("")) {
			managerId = Integer.valueOf(user.getEmpId());
		}
		// 3.安装申请回复--5分钟校验
		Integer count = bizBusinessUrgeService.findCountByfiveTime(Integer.valueOf(installPlanId), managerId, BusinessUrgeConstantUtil.BUSINESS_URGE_BUSINESS_TYPE_1, BusinessUrgeConstantUtil.BUSINESS_URGE_OPERATE_TYPE_2, BusinessUrgeConstantUtil.BUSINESS_URGE_OPERATOR_TYPE_2);
		if ( null == count || count > 0 ) {
			result = "3";
			return result;
		}
		// 4.安装申请回复内容为空
		if (null == urgeReply || urgeReply.equals("")) {
			result = "4";
			return result;
		}
		// 5.保存安装申请回复
		Integer urgeId = bizBusinessUrgeService.saveBusinessUrge(managerId, urgeReply, Integer.valueOf(installPlanId), BusinessUrgeConstantUtil.BUSINESS_URGE_BUSINESS_TYPE_1, BusinessUrgeConstantUtil.BUSINESS_URGE_OPERATE_TYPE_2, BusinessUrgeConstantUtil.BUSINESS_URGE_OPERATOR_TYPE_2);
		if ( null == urgeId ) {
			result = "5";
			return result;
		}
		// 6.保存安装申请回复图片
		// List<ReportCheckDetailsPic> pList = new
		// ArrayList<ReportCheckDetailsPic>();
		// checkConfirmService.savePicAll(pList);
		return result;

	}

	@Autowired
	private PhoneMessageDao messageDao;
	@Autowired
	private BizProjectChangeBillService bizProjectChangeBillService;

	@RequiresPermissions("bizengininstall:bizEnginInstall:view")
	@RequestMapping(value = { "supplierIntoDate", "" })
	public String supplierIntoDate(String orderId, String installId, BizOrderInstallPlan bizOrderInstallPlan, String date, Model model, RedirectAttributes redirectAttributes) throws ParseException {

		Date parse = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		model.addAttribute("Date", parse);
		model.addAttribute("orderId", orderId);
		model.addAttribute("installId", installId);
		return "modules/bizEnginInstall/supplierIntoDate";
	}

	/**
	 * 查看详情
	 * 
	 * @return list
	 */
	@RequiresPermissions("bizengininstall:bizEnginInstall:view")
	@RequestMapping(value = { "selectByInstallID", "" })
	public String selectByInstallID(BizOrderInstallPlan bizOrderInstallPlan, HttpServletRequest request, HttpServletResponse response, Model model, String id, String orderID, String supplierDate) {
		logger.info("install主键编号：" + id + "\t 订单编号：" + orderID);
		BizOrderInstallPlan plan = null;
		BizEnginInstall order = null;
		if (!id.equals("") && !orderID.equals("")) {
			order = bizEnginInstallService.get(Integer.valueOf(orderID));
			plan = bizOrderInstallPlanService.selectByInstallID(Integer.valueOf(id));
		}
		model.addAttribute("order", order);
		model.addAttribute("InstallPlan", plan);
		return "modules/bizEnginInstall/installDetail";
	}

	@RequestMapping(value = { "findInstallItemByStoreIdAndProjectMode", "" })
	public @ResponseBody List<BizProjectInstallItem> findInstallItemByStoreIdAndProjectMode(String storeId, String projectModeId,String isOn) {
		logger.info("根据订单和工程模式查询安装项");
		logger.info("orderId :" + storeId + "\t 工程模式：" + projectModeId);

		if (StringUtils.isNotBlank(storeId) && StringUtils.isNotBlank(projectModeId) && JobSiteController.isNum(storeId) && JobSiteController.isNum(projectModeId)) {

			return bizOrderInstallPlanService.findInstallNameByStoreIdAndProjectModeId(storeId, projectModeId,isOn);

		} else {
			logger.warn("根据订单和工程模式查询安装项 参数有误" + "orderId :" + storeId + "\t 工程模式：" + projectModeId);

			return null;
		}

	}

	/**
	 * 查看日志
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param id
	 * @param orderID
	 * @return
	 */
	@RequiresPermissions("bizengininstall:bizEnginInstall:view")
	@RequestMapping(value = { "urgeLog", "" })
	public String urgeLog(HttpServletRequest request, HttpServletResponse response, Model model, String id, String orderID) {

		// 订单
		BizEnginInstall order = null;
		if (null != orderID && !orderID.equals("")) {
			order = bizEnginInstallService.get(Integer.valueOf(orderID));
		}

		// 安装项
		BizOrderInstallPlan plan = null;
		// 安装项催促回复
		List<BizBusinessUrge> businessUrgeList = null;
		// 安装项催促回复
		List<BizBusinessStatusLog> businessStatusLogList = null;

		if (!id.equals("") && !orderID.equals("")) {

			plan = bizOrderInstallPlanService.selectByInstallID(Integer.valueOf(id));

			businessStatusLogList = bizBusinessStatusLogService.findInstallStatusLog(Integer.valueOf(id), BusinessLogConstantUtil.BUSINESS_TYPE_901, InstallPlanConstantUtil.INSTALL_PLAN_STATUS_1);

			BizBusinessUrge bizBusinessUrge = new BizBusinessUrge();
			// 业务唯一标识整形
			bizBusinessUrge.setBusinessOnlyMarkInt(Integer.valueOf(id));
			// 业务类型
			bizBusinessUrge.setBusinesType(BusinessUrgeConstantUtil.BUSINESS_URGE_BUSINESS_TYPE_1);

			businessUrgeList = bizBusinessUrgeService.findList(bizBusinessUrge);
			//查询项目经里验收日志
            Map<String,String> map = new HashMap<>();
            map.put("id",id);
            /*map.put("accepType",BusinessUrgeConstantUtil.BUSINESS_URGE_ACCEPT_TYPE_2);*/
            List<BizBusinessUrge> list = bizBusinessUrgeService.findUnqualifiedAcceptLog(map);
            model.addAttribute("unqualifiedList", list);
		}

		model.addAttribute("order", order);
		model.addAttribute("InstallPlan", plan);
		model.addAttribute("businessUrgeList", businessUrgeList);
		model.addAttribute("businessStatusLogList", businessStatusLogList);

		return "modules/bizEnginInstall/installLogz";
	}

}
