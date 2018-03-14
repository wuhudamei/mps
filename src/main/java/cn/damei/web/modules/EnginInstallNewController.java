package cn.damei.web.modules;

import cn.damei.common.constantUtils.BusinessLogConstantUtil;
import cn.damei.common.constantUtils.BusinessUrgeConstantUtil;
import cn.damei.common.constantUtils.InstallPlanConstantUtil;
import cn.damei.common.persistence.Page;
import cn.damei.common.utils.PicRootName;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizBusinessStatusLog;
import cn.damei.entity.modules.BizBusinessUrge;
import cn.damei.service.modules.BizBusinessUrgeService;
import cn.damei.entity.modules.ReportCheckDetailsPic;
import cn.damei.entity.modules.EnginInstallNew;
import cn.damei.service.modules.EnginInstallNewService;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Controller
@RequestMapping(value = "${adminPath}/enginInstallNew/enginInstallNew")
public class EnginInstallNewController extends BaseController {

	@Autowired
	private EnginInstallNewService enginInstallNewService;
	@Autowired
	private BizBusinessUrgeService bizBusinessUrgeService;

	@ModelAttribute
	public EnginInstallNew get(@RequestParam(required = false) Integer id) {
		EnginInstallNew entity = null;
		if (StringUtils.isNotBlank(id + "")) {
			entity = enginInstallNewService.get(id);
		}
		if (entity == null) {
			entity = new EnginInstallNew();
		}
		return entity;
	}


	@RequiresPermissions("enginInstallNew:enginInstallNew:view")
	@RequestMapping(value = { "preList", "" })
	public String preList(EnginInstallNew enginInstallNew, HttpServletRequest request, HttpServletResponse response, Model model) {


		User user = UserUtils.getUser();
		if (StringUtils.isBlank(enginInstallNew.getProjectMode())) {
			if (StringUtils.isBlank(user.getProjectMode()) || user.getProjectMode().equals("3")) {
				model.addAttribute("gongcheng", true);
			} else {
				enginInstallNew.setProjectMode(user.getProjectMode());
			}
		} else {
			if (StringUtils.isBlank(user.getProjectMode()) || user.getProjectMode().equals("3")) {
				model.addAttribute("gongcheng", true);
			} else {
				enginInstallNew.setProjectMode(user.getProjectMode());
			}
		}

		Integer applyCount = enginInstallNewService.findInstallCountUnderStatus(InstallPlanConstantUtil.INSTALL_PLAN_STATUS_2);


		Integer reApplyCount = enginInstallNewService.findInstallCountUnderStatus(InstallPlanConstantUtil.INSTALL_PLAN_STATUS_6);

		model.addAttribute("applyCount", applyCount);
		model.addAttribute("reApplyCount", reApplyCount);

		return "modules/enginInstallNew/EnginInstallWaitToBeDoneList";
	}


	@RequiresPermissions("enginInstallNew:enginInstallNew:view")
	@RequestMapping(value = { "list", "" })
	public String list(EnginInstallNew enginInstallNew, HttpServletRequest request, HttpServletResponse response, Model model) {


		if (enginInstallNew.getInstallStatus() != null) {
			String[] status = enginInstallNew.getInstallStatus().split(",");
			if (null != status && status.length > 0) {
				enginInstallNew.setInstallStatusList(Arrays.asList(status));
			}
		}

		List<Integer> projectInstallItemIdList = new ArrayList<Integer>();
		if(null != enginInstallNew.getProjectInstallItemId()){
			projectInstallItemIdList.add(enginInstallNew.getProjectInstallItemId());
		}
		if(null != enginInstallNew.getProjectInstallItemIdStop()){
			projectInstallItemIdList.add(enginInstallNew.getProjectInstallItemIdStop());
		}
		if(CollectionUtils.isNotEmpty(projectInstallItemIdList)){
			enginInstallNew.setProjectInstallItemIdList(projectInstallItemIdList);
		}
		

		User user = UserUtils.getUser();
		if (StringUtils.isBlank(enginInstallNew.getProjectMode())) {
			if (StringUtils.isBlank(user.getProjectMode()) || user.getProjectMode().equals("3")) {
				model.addAttribute("gongcheng", true);
			} else {
				enginInstallNew.setProjectMode(user.getProjectMode());
			}
		} else {
			if (StringUtils.isBlank(user.getProjectMode()) || user.getProjectMode().equals("3")) {
				model.addAttribute("gongcheng", true);
			} else {
				enginInstallNew.setProjectMode(user.getProjectMode());
			}
		}

		Page<EnginInstallNew> page = enginInstallNewService.findPage(new Page<EnginInstallNew>(request, response), enginInstallNew);
		model.addAttribute("page", page);


		Integer applyCount = enginInstallNewService.findInstallCountUnderStatus(InstallPlanConstantUtil.INSTALL_PLAN_STATUS_2);


		Integer reApplyCount = enginInstallNewService.findInstallCountUnderStatus(InstallPlanConstantUtil.INSTALL_PLAN_STATUS_6);

		model.addAttribute("applyCount", applyCount);
		model.addAttribute("reApplyCount", reApplyCount);

		return "modules/enginInstallNew/EnginInstallWaitToBeDoneList";
	}


	@RequiresPermissions("enginInstallNew:enginInstallNew:view")
	@RequestMapping(value = { "preDealWithList", "" })
	public String preDealWithList(EnginInstallNew enginInstallNew, HttpServletRequest request, HttpServletResponse response, Model model) {


		User user = UserUtils.getUser();
		if (StringUtils.isBlank(enginInstallNew.getProjectMode())) {
			if (StringUtils.isBlank(user.getProjectMode()) || user.getProjectMode().equals("3")) {
				model.addAttribute("gongcheng", true);
			} else {
				enginInstallNew.setProjectMode(user.getProjectMode());
			}
		} else {
			if (StringUtils.isBlank(user.getProjectMode()) || user.getProjectMode().equals("3")) {
				model.addAttribute("gongcheng", true);
			} else {
				enginInstallNew.setProjectMode(user.getProjectMode());
			}
		}

		Integer supplierCount = enginInstallNewService.findInstallCountUnderStatus(InstallPlanConstantUtil.INSTALL_PLAN_STATUS_3);
		model.addAttribute("supplierCount", supplierCount);

		return "modules/enginInstallNew/EnginInstallDealWithList";
	}


	@RequiresPermissions("enginInstallNew:enginInstallNew:view")
	@RequestMapping(value = { "dealWithList", "" })
	public String dealWithList(EnginInstallNew enginInstallNew, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		

		List<Integer> projectInstallItemIdList = new ArrayList<Integer>();
		if(null != enginInstallNew.getProjectInstallItemId()){
			projectInstallItemIdList.add(enginInstallNew.getProjectInstallItemId());
		}
		if(null != enginInstallNew.getProjectInstallItemIdStop()){
			projectInstallItemIdList.add(enginInstallNew.getProjectInstallItemIdStop());
		}
		if(CollectionUtils.isNotEmpty(projectInstallItemIdList)){
			enginInstallNew.setProjectInstallItemIdList(projectInstallItemIdList);
		}
		


		User user = UserUtils.getUser();
		if (StringUtils.isBlank(enginInstallNew.getProjectMode())) {
			if (StringUtils.isBlank(user.getProjectMode()) || user.getProjectMode().equals("3")) {
				model.addAttribute("gongcheng", true);
			} else {
				enginInstallNew.setProjectMode(user.getProjectMode());
			}
		} else {
			if (StringUtils.isBlank(user.getProjectMode()) || user.getProjectMode().equals("3")) {
				model.addAttribute("gongcheng", true);
			} else {
				enginInstallNew.setProjectMode(user.getProjectMode());
			}
		}
				
		Page<EnginInstallNew> page = enginInstallNewService.findDealWithPage(new Page<EnginInstallNew>(request, response), enginInstallNew);
		model.addAttribute("page", page);


		Integer supplierCount = enginInstallNewService.findInstallCountUnderStatus(InstallPlanConstantUtil.INSTALL_PLAN_STATUS_3);
		model.addAttribute("supplierCount", supplierCount);

		return "modules/enginInstallNew/EnginInstallDealWithList";
	}


	@RequiresPermissions("enginInstallNew:enginInstallNew:view")
	@RequestMapping(value = { "preRejectedList", "" })
	public String preRejectedList(EnginInstallNew enginInstallNew, HttpServletRequest request, HttpServletResponse response, Model model) {


		User user = UserUtils.getUser();
		if (StringUtils.isBlank(enginInstallNew.getProjectMode())) {
			if (StringUtils.isBlank(user.getProjectMode()) || user.getProjectMode().equals("3")) {
				model.addAttribute("gongcheng", true);
			} else {
				enginInstallNew.setProjectMode(user.getProjectMode());
			}
		} else {
			if (StringUtils.isBlank(user.getProjectMode()) || user.getProjectMode().equals("3")) {
				model.addAttribute("gongcheng", true);
			} else {
				enginInstallNew.setProjectMode(user.getProjectMode());
			}
		}


		Integer rejectedCount = enginInstallNewService.findInstallCountUnderStatus(InstallPlanConstantUtil.INSTALL_PLAN_STATUS_5);
		model.addAttribute("rejectedCount", rejectedCount);

		return "modules/enginInstallNew/EnginInstallRejectedList";
	}


	@RequiresPermissions("enginInstallNew:enginInstallNew:view")
	@RequestMapping(value = { "rejectedList", "" })
	public String rejectedList(EnginInstallNew enginInstallNew, HttpServletRequest request, HttpServletResponse response, Model model) {


		List<Integer> projectInstallItemIdList = new ArrayList<Integer>();
		if(null != enginInstallNew.getProjectInstallItemId()){
			projectInstallItemIdList.add(enginInstallNew.getProjectInstallItemId());
		}
		if(null != enginInstallNew.getProjectInstallItemIdStop()){
			projectInstallItemIdList.add(enginInstallNew.getProjectInstallItemIdStop());
		}
		if(CollectionUtils.isNotEmpty(projectInstallItemIdList)){
			enginInstallNew.setProjectInstallItemIdList(projectInstallItemIdList);
		}
		

		User user = UserUtils.getUser();
		if (StringUtils.isBlank(enginInstallNew.getProjectMode())) {
			if (StringUtils.isBlank(user.getProjectMode()) || user.getProjectMode().equals("3")) {
				model.addAttribute("gongcheng", true);
			} else {
				enginInstallNew.setProjectMode(user.getProjectMode());
			}
		} else {
			if (StringUtils.isBlank(user.getProjectMode()) || user.getProjectMode().equals("3")) {
				model.addAttribute("gongcheng", true);
			} else {
				enginInstallNew.setProjectMode(user.getProjectMode());
			}
		}

				
		Page<EnginInstallNew> page = enginInstallNewService.findRejectedPage(new Page<EnginInstallNew>(request, response), enginInstallNew);
		model.addAttribute("page", page);


		Integer rejectedCount = enginInstallNewService.findInstallCountUnderStatus(InstallPlanConstantUtil.INSTALL_PLAN_STATUS_5);
		model.addAttribute("rejectedCount", rejectedCount);

		return "modules/enginInstallNew/EnginInstallRejectedList";
	}


	@RequiresPermissions("enginInstallNew:enginInstallNew:view")
	@RequestMapping(value = { "preSpecialDealWithList", "" })
	public String preSpecialDealWithList(EnginInstallNew enginInstallNew, HttpServletRequest request, HttpServletResponse response, Model model) {


		User user = UserUtils.getUser();
		if (StringUtils.isBlank(enginInstallNew.getProjectMode())) {
			if (StringUtils.isBlank(user.getProjectMode()) || user.getProjectMode().equals("3")) {
				model.addAttribute("gongcheng", true);
			} else {
				enginInstallNew.setProjectMode(user.getProjectMode());
			}
		} else {
			if (StringUtils.isBlank(user.getProjectMode()) || user.getProjectMode().equals("3")) {
				model.addAttribute("gongcheng", true);
			} else {
				enginInstallNew.setProjectMode(user.getProjectMode());
			}
		}

		enginInstallNew.setInstallMode(InstallPlanConstantUtil.INSTALL_MODE_1);

		return "modules/enginInstallNew/EnginInstallSpecialDealWithList";
	}


	@RequiresPermissions("enginInstallNew:enginInstallNew:view")
	@RequestMapping(value = { "specialDealWithList", "" })
	public String specialDealWithList(EnginInstallNew enginInstallNew, HttpServletRequest request, HttpServletResponse response, Model model) {


		if (enginInstallNew.getInstallStatus() != null) {
			String[] status = enginInstallNew.getInstallStatus().split(",");
			if (null != status && status.length > 0) {
				enginInstallNew.setInstallStatusList(Arrays.asList(status));
			}
		}


		List<Integer> projectInstallItemIdList = new ArrayList<Integer>();
		if(null != enginInstallNew.getProjectInstallItemId()){
			projectInstallItemIdList.add(enginInstallNew.getProjectInstallItemId());
		}
		if(null != enginInstallNew.getProjectInstallItemIdStop()){
			projectInstallItemIdList.add(enginInstallNew.getProjectInstallItemIdStop());
		}
		if(CollectionUtils.isNotEmpty(projectInstallItemIdList)){
			enginInstallNew.setProjectInstallItemIdList(projectInstallItemIdList);
		}
				

		User user = UserUtils.getUser();
		if (StringUtils.isBlank(enginInstallNew.getProjectMode())) {
			if (StringUtils.isBlank(user.getProjectMode()) || user.getProjectMode().equals("3")) {
				model.addAttribute("gongcheng", true);
			} else {
				enginInstallNew.setProjectMode(user.getProjectMode());
			}
		} else {
			if (StringUtils.isBlank(user.getProjectMode()) || user.getProjectMode().equals("3")) {
				model.addAttribute("gongcheng", true);
			} else {
				enginInstallNew.setProjectMode(user.getProjectMode());
			}
		}


		enginInstallNew.setInstallMode(InstallPlanConstantUtil.INSTALL_MODE_1);

		Page<EnginInstallNew> page = enginInstallNewService.findSpecialDealWithPage(new Page<EnginInstallNew>(request, response), enginInstallNew);
		model.addAttribute("page", page);

		return "modules/enginInstallNew/EnginInstallSpecialDealWithList";
	}


	@RequestMapping(value = { "installDetails", "" })
	public String installDetails(String orderId, String installId, HttpServletRequest request, Model model) {

		EnginInstallNew enginInstallNew = null;

		if (StringUtils.isNotBlank(installId)) {

			enginInstallNew = enginInstallNewService.get(Integer.valueOf(installId));
		}

		model.addAttribute("enginInstallNew", enginInstallNew);

		return "modules/enginInstallNew/installDetails";
	}


	@RequestMapping(value = { "installLogOperation", "" })
	public String installLogOperation(String orderId, String installId, String installMode, HttpServletRequest request, Model model) {

		EnginInstallNew enginInstallNew = null;
		List<BizBusinessStatusLog> statusLogList = null;
		List<BizBusinessStatusLog> statusLogTwoList = null;
        List<BizBusinessStatusLog> bizBusinessStatusLogyanshou = null;
		if (null != installMode && installMode.equals("1")) {
			if (StringUtils.isNotBlank(installId)) {


				enginInstallNew = enginInstallNewService.get(Integer.valueOf(installId));

				BizBusinessStatusLog bizBusinessStatusLogxiacailiao = enginInstallNewService.getcailiaozhuang(Integer.valueOf(installId), BusinessLogConstantUtil.BUSINESS_TYPE_901);
				if (bizBusinessStatusLogxiacailiao != null) {
					bizBusinessStatusLogxiacailiao.setBusinessStatus("3");
				}

				BizBusinessStatusLog bizBusinessStatusLogfenpeigonrenzu = enginInstallNewService.getfenpeigonrenzu(Integer.valueOf(installId), BusinessLogConstantUtil.BUSINESS_TYPE_901);
				if (bizBusinessStatusLogfenpeigonrenzu != null) {
					bizBusinessStatusLogfenpeigonrenzu.setBusinessStatus("310");
				}

				BizBusinessStatusLog bizBusinessStatusLogqiandao320 = enginInstallNewService.getgonrenrenqiandao(Integer.valueOf(installId), BusinessLogConstantUtil.BUSINESS_TYPE_901);
				if (bizBusinessStatusLogqiandao320 != null) {
					bizBusinessStatusLogqiandao320.setBusinessStatus("320");
				}

				BizBusinessStatusLog bizBusinessStatusLogqiandao330 = enginInstallNewService.bizBusinessscuesss(Integer.valueOf(installId), BusinessLogConstantUtil.BUSINESS_TYPE_901);
				if (bizBusinessStatusLogqiandao330 != null) {
					bizBusinessStatusLogqiandao330.setBusinessStatus("330");
				}






				statusLogList = enginInstallNewService.findInstallStatusLogC(Integer.valueOf(installId), BusinessLogConstantUtil.BUSINESS_TYPE_901);
				statusLogList.add(bizBusinessStatusLogxiacailiao);
				statusLogList.add(bizBusinessStatusLogfenpeigonrenzu);
				statusLogList.add(bizBusinessStatusLogqiandao320);
				statusLogList.add(bizBusinessStatusLogqiandao330);


				statusLogTwoList = enginInstallNewService.findInstallStatusLogTwo(Integer.valueOf(installId), BusinessLogConstantUtil.BUSINESS_TYPE_901);

			}
            bizBusinessStatusLogyanshou = enginInstallNewService.getAcceptLog(installId);
			model.addAttribute("enginInstallNew", enginInstallNew);
			model.addAttribute("statusLogList", statusLogList);
			model.addAttribute("statusLogTwoList", statusLogTwoList);
            model.addAttribute("list", bizBusinessStatusLogyanshou);

			return "modules/enginInstallNew/installLogOperationC";
		} else {
			if (StringUtils.isNotBlank(installId)) {

				enginInstallNew = enginInstallNewService.get(Integer.valueOf(installId));


				statusLogList = enginInstallNewService.findInstallStatusLog(Integer.valueOf(installId), BusinessLogConstantUtil.BUSINESS_TYPE_901);

                bizBusinessStatusLogyanshou = enginInstallNewService.getAcceptLog(installId);

				statusLogTwoList = enginInstallNewService.findInstallStatusLogTwo(Integer.valueOf(installId), BusinessLogConstantUtil.BUSINESS_TYPE_901);

			}
			model.addAttribute("enginInstallNew", enginInstallNew);
			model.addAttribute("statusLogList", statusLogList);
			model.addAttribute("statusLogTwoList", statusLogTwoList);

			return "modules/enginInstallNew/installLogOperation";
		}

	}


	@RequestMapping(value = "installLogOperationBackup")
	public String installLogOperationBackup(String orderId, String installId, HttpServletRequest request, Model model) {

		EnginInstallNew enginInstallNew = null;
		List<BizBusinessStatusLog> statusLogList = null;
		List<BizBusinessStatusLog> statusLogTwoList = null;
        List<BizBusinessStatusLog> bizBusinessStatusLogyanshou = null;
		if (StringUtils.isNotBlank(installId)) {


			enginInstallNew = enginInstallNewService.get(Integer.valueOf(installId));


			statusLogList = enginInstallNewService.findInstallStatusLog(Integer.valueOf(installId), BusinessLogConstantUtil.BUSINESS_TYPE_901);

            bizBusinessStatusLogyanshou = enginInstallNewService.getAcceptLog(installId);

			statusLogTwoList = enginInstallNewService.findInstallStatusLogTwo(Integer.valueOf(installId), BusinessLogConstantUtil.BUSINESS_TYPE_901);
            model.addAttribute("enginInstallNew", enginInstallNew);
            model.addAttribute("statusLogList", statusLogList);
            model.addAttribute("statusLogTwoList", statusLogTwoList);
            model.addAttribute("list", bizBusinessStatusLogyanshou);
		}
		return "modules/enginInstallNew/installLogOperation";
	}


	@RequestMapping(value = { "installLogUrge", "" })
	public String installLogUrge(String orderId, String installId, HttpServletRequest request, Model model) {

		EnginInstallNew enginInstallNew = null;
		List<BizBusinessUrge> businessUrgeList = null;

		if (StringUtils.isNotBlank(installId)) {


			enginInstallNew = enginInstallNewService.get(Integer.valueOf(installId));


			BizBusinessUrge bizBusinessUrge = new BizBusinessUrge();

			bizBusinessUrge.setBusinessOnlyMarkInt(Integer.valueOf(installId));

			bizBusinessUrge.setBusinesType(BusinessUrgeConstantUtil.BUSINESS_URGE_BUSINESS_TYPE_1);

			businessUrgeList = bizBusinessUrgeService.findList(bizBusinessUrge);

		}

		model.addAttribute("enginInstallNew", enginInstallNew);
		model.addAttribute("businessUrgeList", businessUrgeList);

		return "modules/enginInstallNew/installLogUrge";
	}


	@RequestMapping(value = { "pic", "" })
	public String pic(String id, String type, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

		List<ReportCheckDetailsPic> picList = null;
		if (StringUtils.isNotBlank(id)) {

			picList = enginInstallNewService.findPic(Integer.valueOf(id), type);
		}
		String baseUrl = PicRootName.picPrefixName();
		model.addAttribute("picList", picList);
		model.addAttribute("baseUrl", baseUrl);
		return "modules/enginInstallNew/installLogPic";
	}


	@RequestMapping(value = { "acceptPic", "" })
	public String acceptPic(String id, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

		List<ReportCheckDetailsPic> picList = null;
		if (StringUtils.isNotBlank(id)) {

			picList = enginInstallNewService.findAcceptPic(Integer.valueOf(id));
		}
		String baseUrl = PicRootName.picPrefixName();
		model.addAttribute("picList", picList);
		model.addAttribute("baseUrl", baseUrl);

		return "modules/enginInstallNew/installLogPic";
	}
}