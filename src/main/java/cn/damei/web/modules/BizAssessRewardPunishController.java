package cn.damei.web.modules;

import cn.damei.common.config.Global;
import cn.damei.common.persistence.Page;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.BizAssessRewardPunish;
import cn.damei.service.modules.BizAssessRewardPunishService;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping(value = "${adminPath}/bizAssessRewardPunish/bizAssessRewardPunish")
public class BizAssessRewardPunishController extends BaseController {

	@Autowired
	private BizAssessRewardPunishService bizAssessRewardPunishService;

	@Autowired
	private BizEmployeeService2 bizEmployeeService2;


	@RequestMapping(value = "openBizAssessReward")
	public String openBizAssessReward(BizAssessRewardPunish bizAssessRewardPunish, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();

		if (null == bizAssessRewardPunish.getStoreId()) {
			if (null != user.getStoreId()) {
				bizAssessRewardPunish.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if (StringUtils.isBlank(user.getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}


		if (StringUtils.isBlank(bizAssessRewardPunish.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizAssessRewardPunish.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		} else {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizAssessRewardPunish.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}


		if (bizAssessRewardPunish.getEnginDepartId() == null) {
			if (StringUtils.isNoneBlank(UserUtils.getUser().getEmpId())) {
				List<Integer> list = bizEmployeeService2
						.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if (CollectionUtils.isNotEmpty(list)) {
					bizAssessRewardPunish.setEnginDepartIds(list);
				} else {
					bizAssessRewardPunish.setEnginDepartIds(null);
				}
			} else {
				bizAssessRewardPunish.setEnginDepartIds(null);
			}
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(bizAssessRewardPunish.getEnginDepartId());
			bizAssessRewardPunish.setEnginDepartIds(list);
		}
		String result = "";
		if (bizAssessRewardPunish.getIsMonthInspection() == null || bizAssessRewardPunish.getIsMonthInspection().equals("0")) {
			result = "modules/bizAssessRewardPunish/bizAssessRewardList";
		} else if (bizAssessRewardPunish.getIsMonthInspection().equals("1")) {
			result = "modules/bizAssessRewardPunish/bizInspectionRewardList";
		}
		return result;
	}
	


	@RequestMapping(value = "queryBizAssessReward")
	public String queryBizAssessReward(BizAssessRewardPunish bizAssessRewardPunish, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();

		if (null == bizAssessRewardPunish.getStoreId()) {
			if (null != user.getStoreId()) {
				bizAssessRewardPunish.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if (StringUtils.isBlank(user.getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}


		if (StringUtils.isBlank(bizAssessRewardPunish.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizAssessRewardPunish.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		} else {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizAssessRewardPunish.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}


		if (bizAssessRewardPunish.getEnginDepartId() == null) {
			if (StringUtils.isNoneBlank(UserUtils.getUser().getEmpId())) {
				List<Integer> list = bizEmployeeService2
						.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if (CollectionUtils.isNotEmpty(list)) {
					bizAssessRewardPunish.setEnginDepartIds(list);
				} else {
					bizAssessRewardPunish.setEnginDepartIds(null);
				}
			} else {
				bizAssessRewardPunish.setEnginDepartIds(null);
			}
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(bizAssessRewardPunish.getEnginDepartId());
			bizAssessRewardPunish.setEnginDepartIds(list);
		}
		if(bizAssessRewardPunish.getIsRewardOrPunish() == null||bizAssessRewardPunish.getIsRewardOrPunish().equals("")){
			bizAssessRewardPunish.setIsRewardOrPunish("1");
		}
		Page<BizAssessRewardPunish> page = null;
        		String result = "";
		if (bizAssessRewardPunish.getIsMonthInspection() == null || bizAssessRewardPunish.getIsMonthInspection().equals("0")) {
			page = bizAssessRewardPunishService.findPage(new Page<BizAssessRewardPunish>(request, response),
					bizAssessRewardPunish);
			result = "modules/bizAssessRewardPunish/bizAssessRewardList";
		} else if (bizAssessRewardPunish.getIsMonthInspection().equals("1")) {
			page = bizAssessRewardPunishService.findInspectionPage(new Page<BizAssessRewardPunish>(request, response),
					bizAssessRewardPunish);
			result = "modules/bizAssessRewardPunish/bizInspectionRewardList";
		}
		model.addAttribute("page", page);
		return result;
	}
	

	@RequestMapping(value = "openBizAssessPunish")
	public String openBizAssessPunish(BizAssessRewardPunish bizAssessRewardPunish, HttpServletRequest request,
			HttpServletResponse response, Model model){
		User user = UserUtils.getUser();

		if (null == bizAssessRewardPunish.getStoreId()) {
			if (null != user.getStoreId()) {
				bizAssessRewardPunish.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if (StringUtils.isBlank(user.getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}


		if (StringUtils.isBlank(bizAssessRewardPunish.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizAssessRewardPunish.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		} else {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizAssessRewardPunish.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}


		if (bizAssessRewardPunish.getEnginDepartId() == null) {
			if (StringUtils.isNoneBlank(UserUtils.getUser().getEmpId())) {
				List<Integer> list = bizEmployeeService2
						.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if (CollectionUtils.isNotEmpty(list)) {
					bizAssessRewardPunish.setEnginDepartIds(list);
				} else {
					bizAssessRewardPunish.setEnginDepartIds(null);
				}
			} else {
				bizAssessRewardPunish.setEnginDepartIds(null);
			}
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(bizAssessRewardPunish.getEnginDepartId());
			bizAssessRewardPunish.setEnginDepartIds(list);
		}

		String result = "";
		if (bizAssessRewardPunish.getIsMonthInspection() == null || bizAssessRewardPunish.getIsMonthInspection().equals("0")) {
			result = "modules/bizAssessRewardPunish/bizAssessPunishList";
		} else if (bizAssessRewardPunish.getIsMonthInspection().equals("1")) {
			result = "modules/bizAssessRewardPunish/bizInspectionPunishList";
		}
		return result;
	}
	
	

	@RequestMapping(value = "queryBizAssessPunish")
	public String queryBizAssessPunish(BizAssessRewardPunish bizAssessRewardPunish, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();

		if (null == bizAssessRewardPunish.getStoreId()) {
			if (null != user.getStoreId()) {
				bizAssessRewardPunish.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if (StringUtils.isBlank(user.getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}


		if (StringUtils.isBlank(bizAssessRewardPunish.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizAssessRewardPunish.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		} else {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizAssessRewardPunish.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}


		if (bizAssessRewardPunish.getEnginDepartId() == null) {
			if (StringUtils.isNoneBlank(UserUtils.getUser().getEmpId())) {
				List<Integer> list = bizEmployeeService2
						.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if (CollectionUtils.isNotEmpty(list)) {
					bizAssessRewardPunish.setEnginDepartIds(list);
				} else {
					bizAssessRewardPunish.setEnginDepartIds(null);
				}
			} else {
				bizAssessRewardPunish.setEnginDepartIds(null);
			}
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(bizAssessRewardPunish.getEnginDepartId());
			bizAssessRewardPunish.setEnginDepartIds(list);
		}
		
		if(bizAssessRewardPunish.getIsRewardOrPunish() == null||bizAssessRewardPunish.getIsRewardOrPunish().equals("")){
			bizAssessRewardPunish.setIsRewardOrPunish("2");
		}

		Page<BizAssessRewardPunish> page = null;
		String result = "";
		if (bizAssessRewardPunish.getIsMonthInspection() == null || bizAssessRewardPunish.getIsMonthInspection().equals("0")) {
			page = bizAssessRewardPunishService.findPage(new Page<BizAssessRewardPunish>(request, response),
					bizAssessRewardPunish);
			result = "modules/bizAssessRewardPunish/bizAssessPunishList";
		} else if (bizAssessRewardPunish.getIsMonthInspection().equals("1")) {
			page = bizAssessRewardPunishService.findInspectionPage(new Page<BizAssessRewardPunish>(request, response),
					bizAssessRewardPunish);
			result = "modules/bizAssessRewardPunish/bizInspectionPunishList";
		}
		model.addAttribute("page", page);
		return result;
	}


	@RequestMapping(value = "openBizAssessRewardForm")
	public String openBizAssessRewardForm(BizAssessRewardPunish bizAssessRewardPunish, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();

		if (bizAssessRewardPunish.getId() != null) {
			bizAssessRewardPunish = bizAssessRewardPunishService.get(bizAssessRewardPunish.getId());
		}

		if(bizAssessRewardPunish.getIds() != null){
			String ids = bizAssessRewardPunish.getIds();
			List<BizAssessRewardPunish> rewardPunishs = new ArrayList<BizAssessRewardPunish>();
			String[] idsArr = ids.split(",");
			for(int i = 0;i < idsArr.length; i++){
				bizAssessRewardPunish = bizAssessRewardPunishService.get(Integer.valueOf(idsArr[i]));
				rewardPunishs.add(bizAssessRewardPunish);
			}
			bizAssessRewardPunish.setIds(ids);
			model.addAttribute("rewardPunishs",rewardPunishs);
		}


		if (null == bizAssessRewardPunish.getStoreId()) {
			if (null != user.getStoreId()) {
				bizAssessRewardPunish.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if (StringUtils.isBlank(user.getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}


		if (StringUtils.isBlank(bizAssessRewardPunish.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizAssessRewardPunish.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		} else {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizAssessRewardPunish.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}

		}

		if (bizAssessRewardPunish.getRewardPunishDatetime() == null) {
			bizAssessRewardPunish.setRewardPunishDatetime(new Date());
		}
		String result = "";
		if (bizAssessRewardPunish.getIsMonthInspection() == null || bizAssessRewardPunish.getIsMonthInspection().equals("0")) {
			result = "modules/bizAssessRewardPunish/bizAssessRewardForm";
		} else if (bizAssessRewardPunish.getIsMonthInspection().equals("1")) {
			result = "modules/bizAssessRewardPunish/bizInspectionRewardForm";
		}
		model.addAttribute("bizAssessRewardPunish",bizAssessRewardPunish);
		return result;
	}
	
	

	@RequestMapping(value = "openBizAssessPunishForm")
	public String openBizAssessPunishForm(BizAssessRewardPunish bizAssessRewardPunish, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();

		if (bizAssessRewardPunish.getId() != null) {
			bizAssessRewardPunish = bizAssessRewardPunishService.get(bizAssessRewardPunish.getId());
		}

		if(bizAssessRewardPunish.getIds() != null){
			String ids = bizAssessRewardPunish.getIds();
			List<BizAssessRewardPunish> rewardPunishs = new ArrayList<BizAssessRewardPunish>();
			String[] idsArr = ids.split(",");
			for(int i = 0;i < idsArr.length; i++){
				bizAssessRewardPunish = bizAssessRewardPunishService.get(Integer.valueOf(idsArr[i]));
				rewardPunishs.add(bizAssessRewardPunish);
			}
			bizAssessRewardPunish.setIds(ids);
			model.addAttribute("rewardPunishs",rewardPunishs);
		}

		if (null == bizAssessRewardPunish.getStoreId()) {
			if (null != user.getStoreId()) {
				bizAssessRewardPunish.setStoreId(Integer.valueOf(user.getStoreId()));
			}
		}
		if (StringUtils.isBlank(user.getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}


		if (StringUtils.isBlank(bizAssessRewardPunish.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizAssessRewardPunish.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		} else {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizAssessRewardPunish.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}

		}

		if (bizAssessRewardPunish.getRewardPunishDatetime() == null) {
			bizAssessRewardPunish.setRewardPunishDatetime(new Date());
		}

		String result = "";
		if (bizAssessRewardPunish.getIsMonthInspection() == null || bizAssessRewardPunish.getIsMonthInspection().equals("0")) {
			result = "modules/bizAssessRewardPunish/bizAssessPunishForm";
		} else if (bizAssessRewardPunish.getIsMonthInspection().equals("1")) {
			result = "modules/bizAssessRewardPunish/bizInspectionPunishForm";
		}
		model.addAttribute("bizAssessRewardPunish",bizAssessRewardPunish);
		return result;
	}
	
	@RequestMapping(value = "queryTotalAmountByParam")
	public @ResponseBody double queryTotalAmountByParam(BizAssessRewardPunish bizAssessRewardPunish){
		bizAssessRewardPunish.setIsMonthInspection("0");
		return bizAssessRewardPunishService.queryTotalAmountByParam(bizAssessRewardPunish);
	}
	
	@RequestMapping(value = "detail")
	public String detail(BizAssessRewardPunish bizAssessRewardPunish, HttpServletRequest request,
			HttpServletResponse response, Model model){
		if(bizAssessRewardPunish.getId() != null){
			bizAssessRewardPunish = bizAssessRewardPunishService.get(bizAssessRewardPunish.getId());
		}

		if(bizAssessRewardPunish.getIds() != null){
			String ids = bizAssessRewardPunish.getIds();
			List<BizAssessRewardPunish> rewardPunishs = new ArrayList<BizAssessRewardPunish>();
			String[] idsArr = ids.split(",");
			for(int i = 0;i < idsArr.length; i++){
				bizAssessRewardPunish = bizAssessRewardPunishService.get(Integer.valueOf(idsArr[i]));
				rewardPunishs.add(bizAssessRewardPunish);
			}
			bizAssessRewardPunish.setIds(ids);
			model.addAttribute("rewardPunishs",rewardPunishs);
		}

		model.addAttribute("bizAssessRewardPunish",bizAssessRewardPunish);
		String result = null;
		if(bizAssessRewardPunish.getIsRewardOrPunish().equals("1")){
			if(bizAssessRewardPunish.getIsMonthInspection().equals("0")){
				result ="modules/bizAssessRewardPunish/bizAssessRewardDetail";
			}else if(bizAssessRewardPunish.getIsMonthInspection().equals("1")){
				result ="modules/bizAssessRewardPunish/bizInspectionRewardDetail";
			}

		}else if(bizAssessRewardPunish.getIsRewardOrPunish().equals("2")){
			if(bizAssessRewardPunish.getIsMonthInspection().equals("0")){
				result ="modules/bizAssessRewardPunish/bizAssessPunishDetail";
			}else if(bizAssessRewardPunish.getIsMonthInspection().equals("1")){
				result ="modules/bizAssessRewardPunish/bizInspectionPunishDetail";
			}
		}
		return result;
	}


	@RequestMapping(value = "saveBizAssessReward")
	public String saveBizAssessReward(BizAssessRewardPunish bizAssessRewardPunish,
			RedirectAttributes redirectAttributes, Model model) {
		Date date = new Date();
		User user = UserUtils.getUser();
		if (bizAssessRewardPunish.getId() == null) {
			bizAssessRewardPunish.setRelatedBusinessType("1");
			bizAssessRewardPunish.setRewardPunishStatus("1");
			bizAssessRewardPunish.setStatusDatetime(date);
			if (user.getEmpId() != null) {
				bizAssessRewardPunish.setStatusOperator(Integer.valueOf(user.getEmpId()));
			}
			bizAssessRewardPunish.setStatusDescribe("未关联结算");
			bizAssessRewardPunishService.save(bizAssessRewardPunish);
		} else {
			BizAssessRewardPunish assessRewardPunish = bizAssessRewardPunishService.get(bizAssessRewardPunish.getId());
			assessRewardPunish.setAssessRuleId(bizAssessRewardPunish.getAssessRuleId());
			assessRewardPunish.setRelatedBusinessIdInt(bizAssessRewardPunish.getRelatedBusinessIdInt());
			assessRewardPunish.setRewardPunishTargetEmployeeId(bizAssessRewardPunish.getRewardPunishTargetEmployeeId());
			assessRewardPunish.setRewardPunishAmount(bizAssessRewardPunish.getRewardPunishAmount());
			assessRewardPunish.setRewardPunishScore(bizAssessRewardPunish.getRewardPunishScore());
			assessRewardPunish.setRewardPunishDatetime(bizAssessRewardPunish.getRewardPunishDatetime());
			assessRewardPunish.setIsMonthInspection(bizAssessRewardPunish.getIsMonthInspection());
			bizAssessRewardPunishService.save(assessRewardPunish);
		}
		addMessage(redirectAttributes, "保存成功!");
		String result= null;
		if(bizAssessRewardPunish.getIsRewardOrPunish().equals("1")){
			result = "redirect:" + Global.getAdminPath()
			+ "/bizAssessRewardPunish/bizAssessRewardPunish/queryBizAssessReward?repage";
		}else if(bizAssessRewardPunish.getIsRewardOrPunish().equals("2")){
			result = "redirect:" + Global.getAdminPath()
			+ "/bizAssessRewardPunish/bizAssessRewardPunish/queryBizAssessPunish?repage";
		}
		
		return result;
		
	}



	@RequestMapping(value = "saveBizInspectionReward")
	public String saveBizInspectionReward(BizAssessRewardPunish bizAssessRewardPunish, String[] assessRuleTypeIds,
										  String[] assessRuleIds, String[] rewardPunishAmounts,
										  String[] rewardPunishScores , String[] detailRemarks, Model model) {
		bizAssessRewardPunishService.saveBizInspectionReward(bizAssessRewardPunish, assessRuleTypeIds, assessRuleIds, rewardPunishAmounts, rewardPunishScores ,detailRemarks);
		String result = null;
		if (bizAssessRewardPunish.getIsRewardOrPunish().equals("1")) {
			result = "redirect:" + Global.getAdminPath()
					+ "/bizAssessRewardPunish/bizAssessRewardPunish/queryBizAssessReward?isMonthInspection=" + bizAssessRewardPunish.getIsMonthInspection() + "&repage";
		} else if (bizAssessRewardPunish.getIsRewardOrPunish().equals("2")) {
			result = "redirect:" + Global.getAdminPath()
					+ "/bizAssessRewardPunish/bizAssessRewardPunish/queryBizAssessPunish?isMonthInspection=" + bizAssessRewardPunish.getIsMonthInspection() + "&repage";
		}

		return result;
	}

	@RequestMapping(value = "delete")
	public @ResponseBody String delete(BizAssessRewardPunish bizAssessRewardPunish){
		String result="0";
		if (bizAssessRewardPunish.getId() != null && !bizAssessRewardPunish.getId().equals("")){
			bizAssessRewardPunishService.delete(bizAssessRewardPunish);
		}else if (bizAssessRewardPunish.getIds() != null && !bizAssessRewardPunish.getIds().equals("")){
			bizAssessRewardPunishService.delUpdateBatch(bizAssessRewardPunish);
		}
		return result;
	}
	
	

}
