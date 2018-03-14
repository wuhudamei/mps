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


@Controller
@RequestMapping(value = "${adminPath}/app/manager/orderInstallPlan")
public class OrderInstallPlanController {

	@Autowired
	private OrderInstallPlanService orderInstallPlanService;

	@Autowired
	private EnginInstallService enginInstallService;

	@Autowired
	private BizMainMaterialsUnqualifiedReasonService bizMainMaterialsUnqualifiedReasonService;


	@RequestMapping(value = { "enginInstall", "" })
	public String enginInstallListView( HttpServletRequest request, Model model) {


		Manager manager = (Manager) request.getSession().getAttribute("manager");
		if(null != manager && null != manager.getId()){
			model.addAttribute("managerId", manager.getId());
		}
		return "mobile/modules/Manager/progressMain/enginInstall/installCheckList";
	}


	@RequestMapping(value = "ajaxEngineInstallList")
	@ResponseBody
	public List<EnginInstall> ajaxEngineInstallList(@RequestParam(required = false)String text, @RequestParam(required = true)Integer managerId) {
		return orderInstallPlanService.queryInstallAcceptOrderList(managerId, text);
	}



	@RequestMapping(value = "/installAcceptance")
	public String installAcceptance(@RequestParam(required = true)Integer orderId, Model model) {

		EnginInstall enginInstall = enginInstallService.queryOrderDetails(orderId);

		List<OrderInstallPlan> installPlanList = orderInstallPlanService.queryOrderInstallAcceptList(orderId);
		model.addAttribute("order", enginInstall);
		model.addAttribute("installPlanList", installPlanList);
		return "mobile/modules/Manager/progressMain/enginInstall/checkList_2";
	}


	@RequestMapping(value = "/acceptancePM")
	public String acceptancePM(@RequestParam(required = true)Integer id,
							   @RequestParam(required = true)Integer isQualified,Model model) {

		OrderInstallPlan orderInstallPlan = orderInstallPlanService.getById(id);

		orderInstallPlan.setRealAcceptDate(new Date());

		EnginInstall enginInstall = orderInstallPlanService.querySupplierInstallBillMessage(id);

		List<BizMainMaterialsUnqualifiedReason> unReasonList = bizMainMaterialsUnqualifiedReasonService.queryUnqualifiedReasonList(id);

		model.addAttribute("engineInstall",enginInstall);
		model.addAttribute("orderInstallPlan",orderInstallPlan);
		model.addAttribute("isQualified",isQualified);
		model.addAttribute("unReasonList",unReasonList);

		return "mobile/modules/Manager/progressMain/enginInstall/check-accept";
	}



	@RequestMapping(value="/acceptance_submit")
	public @ResponseBody String acceptanceSubmit(OrderInstallPlan orderInstallPlan,String realAcceptDateString,String realIntoDateString,String realCompleteDateString, String[] photo, HttpServletRequest request){
		return orderInstallPlanService.acceptanceSubmit(orderInstallPlan,realAcceptDateString,realIntoDateString,realCompleteDateString,photo,request);
	}


	@RequestMapping(value = "/acceptUnqualifiedLog")
	public String acceptUnqualifiedLog(@RequestParam(required = true)Integer id,@RequestParam(required = true)Integer orderId,Model model) {

		List<OrderInstallPlanAcceptLog> list = orderInstallPlanService.queryAcceptUnqualifiedLog(id);
		model.addAttribute("list",list);
		model.addAttribute("orderId",orderId);

		return "mobile/modules/Manager/progressMain/enginInstall/check-accept-log-unqualified";
	}


	@RequestMapping(value = { "/unqualified_pic", "" })
	public String unqualifiedPic(Integer id, Model model) throws IOException {

		List<ReportCheckDetailsPic> picList = orderInstallPlanService.queryAcceptUnqualifiedPicList(id, PictureTypeContantUtil.PICTURE_TYPE_2076);
		model.addAttribute("picList", picList);
		model.addAttribute("baseUrl", PicRootName.picPrefixName());
		return "mobile/modules/Manager/progressMain/enginInstall/new/photo";
	}



	@RequestMapping(value = "/installAcceptanceDetail")
	public String installAcceptanceDetail(@RequestParam(required = true)Integer orderId, Model model) {

		EnginInstall enginInstall = enginInstallService.queryOrderDetails(orderId);

		List<OrderInstallPlan> installPlanList = orderInstallPlanService.queryOrderInstallAcceptDetailList(orderId);
		model.addAttribute("order", enginInstall);
		model.addAttribute("installPlanList", installPlanList);
		return "mobile/modules/Manager/progressMain/enginInstall/checkdetailsList_5";
	}


	@RequestMapping(value = "/acceptancePMdetail")
	public String acceptancePMdetail(@RequestParam(required = true)Integer id,
									 Model model) throws IOException {


		OrderInstallPlan orderInstallPlan = orderInstallPlanService.getById(id);

		EnginInstall engineInstall = orderInstallPlanService.querySupplierInstallBillMessage(id);

		List<OrderInstallPlanPic> list = orderInstallPlanService.queryAcceptQualifiedPicList(id);

		model.addAttribute("orderInstallPlan", orderInstallPlan);
		model.addAttribute("engineInstall", engineInstall);
		model.addAttribute("list", list);
		model.addAttribute("baseUrl", PicRootName.picPrefixName());
		return "mobile/modules/Manager/progressMain/enginInstall/check-accept-log-qualified";
	}



	@RequestMapping(value = "/construction_pic")
	public String constructionPic(@RequestParam(required = true)Integer constructionId, Model model) throws IOException {

		List<ReportCheckDetailsPic> picList = orderInstallPlanService.queryAcceptUnqualifiedPicList(constructionId, PictureTypeContantUtil.PICTURE_TYPE_2071);
		model.addAttribute("picList", picList);
		model.addAttribute("baseUrl", PicRootName.picPrefixName());
		return "mobile/modules/Manager/progressMain/enginInstall/new/photo";
	}




}
