package cn.damei.web.mobile.Manager;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.damei.common.constantUtils.PictureTypeContantUtil;
import cn.damei.entity.mobile.Inspector.ReportCheckDetailsPic;
import cn.damei.entity.mobile.Manager.OrderInstallPlanAcceptLog;

import cn.damei.entity.modules.BizMainMaterialsUnqualifiedReason;
import cn.damei.service.modules.BizMainMaterialsUnqualifiedReasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.utils.PicRootName;
import cn.damei.entity.mobile.Manager.EnginInstall;
import cn.damei.entity.mobile.Manager.OrderInstallPlan;
import cn.damei.entity.mobile.Manager.OrderInstallPlanPic;
import cn.damei.service.mobile.Manager.EnginInstallService;
import cn.damei.service.mobile.Manager.OrderInstallPlanService;
import cn.damei.entity.mobile.Manager.Manager;

/**
 * 工程安装
 *
 * @author wyb
 */
@Controller
@RequestMapping(value = "${adminPath}/app/manager/orderInstallPlan")
public class OrderInstallPlanController {

	@Autowired
	private OrderInstallPlanService orderInstallPlanService;

	@Autowired
	private EnginInstallService enginInstallService;

	@Autowired
	private BizMainMaterialsUnqualifiedReasonService bizMainMaterialsUnqualifiedReasonService;

	/**
	 * 安装验收 订单列表页
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "enginInstall", "" })
	public String enginInstallListView( HttpServletRequest request, Model model) {

		// 获取项目经理session
		Manager manager = (Manager) request.getSession().getAttribute("manager");
		if(null != manager && null != manager.getId()){
			model.addAttribute("managerId", manager.getId());
		}
		return "mobile/modules/Manager/progressMain/enginInstall/installCheckList";
	}

	/**
	 * 动态加载安装验收 订单列表
	 * @param text
	 * @param managerId
	 * @return
	 */
	@RequestMapping(value = "ajaxEngineInstallList")
	@ResponseBody
	public List<EnginInstall> ajaxEngineInstallList(@RequestParam(required = false)String text, @RequestParam(required = true)Integer managerId) {
		return orderInstallPlanService.queryInstallAcceptOrderList(managerId, text);
	}


	/**
	 * 订单 主材安装验收列表页
	 * 安装项状态为：
	 * 	  安装模式-传统【3：已转给供应商】【401：验收不合格】
	 * 	  安装模式-传统【330：工人已申请完工】【401：验收不合格】
	 * @param orderId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/installAcceptance")
	public String installAcceptance(@RequestParam(required = true)Integer orderId, Model model) {
		// 订单
		EnginInstall enginInstall = enginInstallService.queryOrderDetails(orderId);
		// 主材安装验收明细列表页【3：已转给供应商】【330：工人已申请完工】【401：验收不合格】
		List<OrderInstallPlan> installPlanList = orderInstallPlanService.queryOrderInstallAcceptList(orderId);
		model.addAttribute("order", enginInstall);
		model.addAttribute("installPlanList", installPlanList);
		return "mobile/modules/Manager/progressMain/enginInstall/checkList_2";
	}

	/**
	 * 去验收页面
	 * @param id
	 * @param isQualified
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/acceptancePM")
	public String acceptancePM(@RequestParam(required = true)Integer id,
							   @RequestParam(required = true)Integer isQualified,Model model) {
		//1.安装项详情
		OrderInstallPlan orderInstallPlan = orderInstallPlanService.getById(id);
		//1.1.验收日期
		orderInstallPlan.setRealAcceptDate(new Date());
		//2.查询该安装项【订单】【安装单】【施工单】信息
		EnginInstall enginInstall = orderInstallPlanService.querySupplierInstallBillMessage(id);
		//3.查询不合格原因列表
		List<BizMainMaterialsUnqualifiedReason> unReasonList = bizMainMaterialsUnqualifiedReasonService.queryUnqualifiedReasonList(id);

		model.addAttribute("engineInstall",enginInstall);
		model.addAttribute("orderInstallPlan",orderInstallPlan);
		model.addAttribute("isQualified",isQualified);
		model.addAttribute("unReasonList",unReasonList);

		return "mobile/modules/Manager/progressMain/enginInstall/check-accept";
	}


	/**
	 * 确认验收-提交
	 * @param orderInstallPlan
	 * @param photo
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/acceptance_submit")
	public @ResponseBody String acceptanceSubmit(OrderInstallPlan orderInstallPlan,String realAcceptDateString,String realIntoDateString,String realCompleteDateString, String[] photo, HttpServletRequest request){
		return orderInstallPlanService.acceptanceSubmit(orderInstallPlan,realAcceptDateString,realIntoDateString,realCompleteDateString,photo,request);
	}

	/**
	 * 【不合格】验收日志
	 * @param id
	 * @param orderId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/acceptUnqualifiedLog")
	public String acceptUnqualifiedLog(@RequestParam(required = true)Integer id,@RequestParam(required = true)Integer orderId,Model model) {

		List<OrderInstallPlanAcceptLog> list = orderInstallPlanService.queryAcceptUnqualifiedLog(id);
		model.addAttribute("list",list);
		model.addAttribute("orderId",orderId);

		return "mobile/modules/Manager/progressMain/enginInstall/check-accept-log-unqualified";
	}

	/**
	 * 【不合格】验收日志--图片
	 * @param id
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = { "/unqualified_pic", "" })
	public String unqualifiedPic(Integer id, Model model) throws IOException {

		List<ReportCheckDetailsPic> picList = orderInstallPlanService.queryAcceptUnqualifiedPicList(id, PictureTypeContantUtil.PICTURE_TYPE_2076);
		model.addAttribute("picList", picList);
		model.addAttribute("baseUrl", PicRootName.picPrefixName());
		return "mobile/modules/Manager/progressMain/enginInstall/new/photo";
	}


	/**
	 * 订单 安装验收明细列表【合格】
	 * @param orderId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/installAcceptanceDetail")
	public String installAcceptanceDetail(@RequestParam(required = true)Integer orderId, Model model) {
		// 订单
		EnginInstall enginInstall = enginInstallService.queryOrderDetails(orderId);
		// 主材安装验收明细列表页【4：已验收】
		List<OrderInstallPlan> installPlanList = orderInstallPlanService.queryOrderInstallAcceptDetailList(orderId);
		model.addAttribute("order", enginInstall);
		model.addAttribute("installPlanList", installPlanList);
		return "mobile/modules/Manager/progressMain/enginInstall/checkdetailsList_5";
	}

	/**
	 * 【合格】验收日志
	 * @param id
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/acceptancePMdetail")
	public String acceptancePMdetail(@RequestParam(required = true)Integer id,
									 Model model) throws IOException {

		//1.安装项详情
		OrderInstallPlan orderInstallPlan = orderInstallPlanService.getById(id);
		//2.查询该安装项【订单】【安装单】【施工单】信息
		EnginInstall engineInstall = orderInstallPlanService.querySupplierInstallBillMessage(id);
		//3.查询验收【合格】图片
		List<OrderInstallPlanPic> list = orderInstallPlanService.queryAcceptQualifiedPicList(id);

		model.addAttribute("orderInstallPlan", orderInstallPlan);
		model.addAttribute("engineInstall", engineInstall);
		model.addAttribute("list", list);
		model.addAttribute("baseUrl", PicRootName.picPrefixName());
		return "mobile/modules/Manager/progressMain/enginInstall/check-accept-log-qualified";
	}


	/**
	 * 【产业】查看完工图
	 * @param constructionId
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/construction_pic")
	public String constructionPic(@RequestParam(required = true)Integer constructionId, Model model) throws IOException {

		List<ReportCheckDetailsPic> picList = orderInstallPlanService.queryAcceptUnqualifiedPicList(constructionId, PictureTypeContantUtil.PICTURE_TYPE_2071);
		model.addAttribute("picList", picList);
		model.addAttribute("baseUrl", PicRootName.picPrefixName());
		return "mobile/modules/Manager/progressMain/enginInstall/new/photo";
	}




}
