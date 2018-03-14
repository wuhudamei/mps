package cn.damei.web.mobile.Manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.damei.common.utils.ConstantUtils;
import cn.damei.entity.mobile.Manager.Manager;
import cn.damei.entity.mobile.Manager.PmSettlementBudget;
import cn.damei.service.mobile.Manager.PmSettlementBudgetService;
import cn.damei.entity.modules.BizAssessRewardPunish;
import cn.damei.service.modules.BizAssessRewardPunishService;
import cn.damei.entity.modules.BizOrderMaterialsStandard;
import cn.damei.entity.modules.BizMaterialsStandardReceiveBill;
import cn.damei.entity.modules.PmMaterialsSettleInfo;
import cn.damei.service.modules.PmMaterialsSettleInfoService;
import cn.damei.entity.modules.BizPmGuaranteeMoneyCnfgSnap;
import cn.damei.service.modules.BizPmGuaranteeMoneyCnfgSnapService;
import cn.damei.entity.modules.BizPmOwnpayCnfgSnap;
import cn.damei.entity.modules.BizMaterialSelfbuyVo;
import cn.damei.service.modules.BizPmSettleBillService;
import cn.damei.entity.modules.BizPmStarCommissionCnfgSnap;
import cn.damei.entity.modules.BizGuaranteeMoneyBalance;
import cn.damei.service.modules.BizGuaranteeMoneyBalanceService;
import cn.damei.entity.modules.QcBillCheckItemInfo;
import cn.damei.service.modules.InspectorConfirmService;

/**
 * 项目经理订单结算金额预览Controller
 * 
 * @author hyh
 *
 */
@Controller
@RequestMapping(value = "mobile/modules/manager/projectmanagersettlement/web/pmSettlementBudget")
public class PmSettlementBudgetController {

	@Autowired
	private PmSettlementBudgetService pmSettlementBudgetService;
	@Autowired
	private InspectorConfirmService inspectorConfirmService;
	@Autowired
	private BizAssessRewardPunishService bizAssessRewardPunishService;
	@Autowired
	private PmMaterialsSettleInfoService pmMaterialsSettleInfoService;
	@Autowired
	private BizPmSettleBillService bizPmSettleBillService;
	@Autowired
	private BizPmGuaranteeMoneyCnfgSnapService bizPmGuaranteeMoneyCnfgSnapService;
	@Autowired
	private BizGuaranteeMoneyBalanceService bizGuaranteeMoneyBalanceService;

	/**
	 * 订单结算金额预览列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "pmSettlementBudgetList")
	public String pmSettlementBudgetList(Model model, HttpServletRequest request) {
		Manager manager = (Manager) request.getSession().getAttribute("manager");
		String result="";
		List<PmSettlementBudget> list = pmSettlementBudgetService.queryPmSettlementBudgetByManagerId(manager.getId());
		if(list != null && list.size() >0){
			model.addAttribute("list",list);
			result="/mobile/modules/Manager/projectmanagersettlement/pmSettlementBudgetList";
		}else{
			result="/mobile/modules/Manager/projectmanagersettlement/noPmSettlementBudgetList";
		}
		return result;
	}

	/**
	 * 订单结算金额预览详情
	 * 
	 * @return
	 */
	@RequestMapping(value = "pmSettlementBudgetDetail")
	public String pmSettlementBudgetDetail(PmSettlementBudget pmSettlementBudget,Model model,HttpServletRequest request) {
		String result = "";
		Manager manager = (Manager) request.getSession().getAttribute("manager");
		pmSettlementBudget.setPmEmployeeId(manager.getId());
		if (pmSettlementBudget.getSettleBillType().equals("10")) {// 中期结算单
			pmSettlementBudgetService.getMidwaySettlementBudgetAmount(pmSettlementBudget);
			result = "/mobile/modules/Manager/projectmanagersettlement/midwaySettlementBudgetDetail";
		} else if (pmSettlementBudget.getSettleBillType().equals("20")) {// 竣工结算单
			pmSettlementBudgetService.getCompleteSettlementBudgetAmount(pmSettlementBudget);
			result = "/mobile/modules/Manager/projectmanagersettlement/completeSettlementBudgetDetail";
		}
		model.addAttribute("pmSettlementBudget",pmSettlementBudget);
		return result;
	}

	/**
	 * 提成信息
	 * 
	 * @param orderId
	 * @param model
	 * @param type
	 *            1: 中期提成 2：竣工提成
	 * @return
	 */
	@RequestMapping(value = "pmCommissionDetail")
	public String pmCommissionDetail(int orderId, Model model, String type) {
		BizPmStarCommissionCnfgSnap bizPmStarCommissionCnfgSnap = inspectorConfirmService
				.queryManagerCommissionByOrderId(orderId);
		Double commissionAmount = 0.0;
		String result = "";
		if (type.equals("1")) {
			commissionAmount = bizPmStarCommissionCnfgSnap.getCommissionAmount()
					* bizPmStarCommissionCnfgSnap.getCommissionRateMidway();
			result = "/mobile/modules/Manager/projectmanagersettlement/midwayCommissionDetail";
		} else if (type.equals("2")) {
			commissionAmount = bizPmStarCommissionCnfgSnap.getCommissionAmount()
					* bizPmStarCommissionCnfgSnap.getCommissionRateComplete();
			result = "/mobile/modules/Manager/projectmanagersettlement/completeCommissionDetail";
		}
		model.addAttribute("bizPmStarCommissionCnfgSnap", bizPmStarCommissionCnfgSnap);
		model.addAttribute("commissionAmount", commissionAmount);
		return result;
	}

	/**
	 * 自主支配信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "pmOwnpayDetail")
	public String pmOwnpayDetail(int orderId, Model model) {
		Double managerOwnpay = inspectorConfirmService.queryManagerOwnpay(orderId);
		List<BizPmOwnpayCnfgSnap> pmOwnpayCnfgSnapList = inspectorConfirmService
				.queryPmOwnpayCnfgSnapByOrderId(orderId);
		model.addAttribute("managerOwnpay", managerOwnpay);
		model.addAttribute("pmOwnpayCnfgSnapList", pmOwnpayCnfgSnapList);
		return "/mobile/modules/Manager/projectmanagersettlement/ownpayDetail";
	}

	/**
	 * 标化辅料信息
	 * 
	 * @param orderId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "pmMaterialsStandardDetail")
	public String pmMaterialsStandardDetail(int orderId, Model model) {
		BizMaterialsStandardReceiveBill details3 = new BizMaterialsStandardReceiveBill();
		details3.setReceiveBillAmount(0.0);
		List<BizOrderMaterialsStandard> materialsStandardList = inspectorConfirmService
				.queryMaterialsStandardByOrderId(orderId);
		List<String> codeList = new ArrayList<String>();
		if (materialsStandardList != null && materialsStandardList.size() > 0) {
			for (BizOrderMaterialsStandard standard : materialsStandardList) {
				if (!codeList.contains(standard.getMaterialsStandardReceiveBillCode())) {
					codeList.add(standard.getMaterialsStandardReceiveBillCode());
				}
			}
			for (BizOrderMaterialsStandard materialsStandard : materialsStandardList) {
				details3.setReceiveBillAmount(details3.getReceiveBillAmount() + materialsStandard.getMaterialsAmount());
			}
		}
		if(details3.getReceiveBillAmount()<0){
			details3.setReceiveBillAmount(0-details3.getReceiveBillAmount());
		}
		model.addAttribute("details3", details3);
		model.addAttribute("codeList", codeList);
		model.addAttribute("materialsStandardList", materialsStandardList);
		return "/mobile/modules/Manager/projectmanagersettlement/materialsStandard";
	}

	/**
	 * 质检罚款信息
	 * 
	 * @param orderId
	 * @param model
	 * @param type
	 *            1:中期质检罚款 2：竣工质检罚款
	 * @return
	 */
	@RequestMapping(value = "pmQcCheckPunishDetail")
	public String pmQcCheckPunishDetail(int orderId,String type,Model model, HttpServletRequest request) {
		Manager manager = (Manager) request.getSession().getAttribute("manager");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderId", orderId);
		map.put("settleCategory", ConstantUtils.PM_SETTLE_CATEGORY_4);
		map.put("settleStatus", 10);
		map.put("settleRole", ConstantUtils.SETTLE_ROLE_1);
		map.put("pmEmployeeId", manager.getId());
		Double managerPenalty = inspectorConfirmService.queryManangerPenalty(map);
		List<QcBillCheckItemInfo> pmPunishList = inspectorConfirmService.queryPmPunishByParam(map);
		model.addAttribute("managerPenalty", managerPenalty);
		model.addAttribute("pmPunishList", pmPunishList);
		String result = "";
		if (type.equals("1")) {
			result = "/mobile/modules/Manager/projectmanagersettlement/midwayQcCheckPunishDetail";
		} else if (type.equals("2")) {
			result = "/mobile/modules/Manager/projectmanagersettlement/completeQcCheckPunishDetail";
		}
		return result;
	}

	/**
	 * 奖励信息
	 * 
	 * @param orderId
	 * @param model
	 * @param request
	 * @param type
	 *            1:中期奖励信息 2：竣工奖励信息  3:中期巡检奖励信息  4:竣工巡检奖励信息
	 * @return
	 */
	@RequestMapping(value = "pmRewardDetail")
	public String pmRewardDetail(int orderId, Model model, HttpServletRequest request, String type) {
		Manager manager = (Manager) request.getSession().getAttribute("manager");
		Map<String, Object> rewarPunishParam = new HashMap<String, Object>();
		rewarPunishParam.put("relatedBusinessIdInt", orderId);
		rewarPunishParam.put("rewardPunishTargetEmployeeType", 1);
		rewarPunishParam.put("rewardPunishTargetType", 10);
		rewarPunishParam.put("isRewardOrPunish", 1);
		if(type.equals("1") || type.equals("2")){
			rewarPunishParam.put("isMonthInspection", 0);
		}else if(type.equals("3") || type.equals("4")){
			rewarPunishParam.put("isMonthInspection", 1);
		}
		rewarPunishParam.put("rewardPunishStatus", 1);
		rewarPunishParam.put("rewardPunishTargetEmployeeId", manager.getId());
		List<BizAssessRewardPunish> pmRewardList = bizAssessRewardPunishService
				.queryAssessRewardPunishByParam(rewarPunishParam);
		double pmRewardAmount = 0.0;
		BizAssessRewardPunish rewardPunish = new BizAssessRewardPunish();
		rewardPunish.setRelatedBusinessIdInt(orderId);
		rewardPunish.setRewardPunishTargetEmployeeId(manager.getId());
		rewardPunish.setRewardPunishTargetEmployeeType("1");
		rewardPunish.setRewardPunishTargetType("10");
		rewardPunish.setIsRewardOrPunish("1");
		if(type.equals("1") || type.equals("2")){
			rewardPunish.setIsMonthInspection("0");
		}else if(type.equals("3") || type.equals("4")){
			rewardPunish.setIsMonthInspection("1");
		}
		rewardPunish.setRewardPunishStatus("1");
		pmRewardAmount = bizAssessRewardPunishService.queryTotalAmountByParam(rewardPunish);
		model.addAttribute("pmRewardList", pmRewardList);
		model.addAttribute("pmRewardAmount", pmRewardAmount);
		String result = "";
		if (type.equals("1")) {
			result = "/mobile/modules/Manager/projectmanagersettlement/midwayReward";
		} else if (type.equals("2")) {
			result = "/mobile/modules/Manager/projectmanagersettlement/completeReward";
		} else if (type.equals("3")){
			result = "/mobile/modules/Manager/projectmanagersettlement/midwayInspectionReward";
		} else if(type.equals("4")){
			result = "/mobile/modules/Manager/projectmanagersettlement/completeInspectionReward";
		}
		return result;
	}

	/**
	 * 扣款信息
	 * 
	 * @param orderId
	 * @param model
	 * @param request
	 * @param type 1:中期扣款信息 2：竣工扣款信息  3:中期巡检扣款信息  4:竣工巡检扣款信息
	 * @return
	 */
	@RequestMapping(value = "pmPunishDetail")
	public String pmPunishDetail(int orderId, Model model, HttpServletRequest request, String type) {
		Manager manager = (Manager) request.getSession().getAttribute("manager");
		double pmPunishAmount = 0.0;
		BizAssessRewardPunish rewardPunish = new BizAssessRewardPunish();
		rewardPunish.setRelatedBusinessIdInt(orderId);
		rewardPunish.setRewardPunishTargetEmployeeId(manager.getId());
		rewardPunish.setRewardPunishTargetEmployeeType("1");
		rewardPunish.setRewardPunishTargetType("10");
		rewardPunish.setIsRewardOrPunish("2");
		if(type.equals("1") || type.equals("2")){
			rewardPunish.setIsMonthInspection("0");
		}else if(type.equals("3") || type.equals("4")){
			rewardPunish.setIsMonthInspection("1");
		}
		rewardPunish.setRewardPunishStatus("1");
		pmPunishAmount = bizAssessRewardPunishService.queryTotalAmountByParam(rewardPunish);

		Map<String, Object> rewarPunishParam = new HashMap<String, Object>();
		rewarPunishParam.put("relatedBusinessIdInt", orderId);
		rewarPunishParam.put("rewardPunishTargetEmployeeType", 1);
		rewarPunishParam.put("rewardPunishTargetType", 10);
		rewarPunishParam.put("isRewardOrPunish", 2);
		rewarPunishParam.put("rewardPunishStatus", 1);
		if(type.equals("1") || type.equals("2")){
			rewarPunishParam.put("isMonthInspection", 0);
		}else if(type.equals("3") || type.equals("4")){
			rewarPunishParam.put("isMonthInspection", 1);
		}
		rewarPunishParam.put("rewardPunishTargetEmployeeId", manager.getId());
		List<BizAssessRewardPunish> pmPunishList = bizAssessRewardPunishService
				.queryAssessRewardPunishByParam(rewarPunishParam);
		model.addAttribute("pmPunishAmount", pmPunishAmount);
		model.addAttribute("pmPunishList", pmPunishList);
		String result = "";
		if (type.equals("1")) {
			result = "/mobile/modules/Manager/projectmanagersettlement/midwayPunish";
		} else if (type.equals("2")) {
			result = "/mobile/modules/Manager/projectmanagersettlement/completePunish";
		} else if (type.equals("3")){
			result = "/mobile/modules/Manager/projectmanagersettlement/midwayInspectionPunish";
		} else if(type.equals("4")){
			result = "/mobile/modules/Manager/projectmanagersettlement/completeInspectionPunish";
		}
		return result;
	}

	/**
	 * 任务包材料结算信息
	 * 
	 * @param orderId
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "pmAuxiliaryMaterialsDetail")
	public String pmAuxiliaryMaterialsDetail(int orderId, Model model, HttpServletRequest request, String type) {
		Manager manager = (Manager) request.getSession().getAttribute("manager");
		double pmMaterialsSettleAmount = 0.00;
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("orderId", orderId);
		param.put("pmEmployeeId", manager.getId());
		List<PmMaterialsSettleInfo> pmMaterials = pmMaterialsSettleInfoService.queryPmMaterialsInfoByParam(param);
		if (pmMaterials == null) {
			pmMaterials = new ArrayList<PmMaterialsSettleInfo>();
		}
		if (pmMaterials != null && pmMaterials.size() > 0) {
			for (PmMaterialsSettleInfo info : pmMaterials) {
				pmMaterialsSettleAmount = pmMaterialsSettleAmount + info.getPmMaterialsSettleAmount();
			}
		}
		model.addAttribute("pmMaterialsSettleAmount", pmMaterialsSettleAmount);
		model.addAttribute("pmMaterials", pmMaterials);
		String result = "";
		if (type.equals("1")) {
			result = "/mobile/modules/Manager/projectmanagersettlement/midwayAuxiliaryMaterialsDetail";
		} else if (type.equals("2")) {
			result = "/mobile/modules/Manager/projectmanagersettlement/completeAuxiliaryMaterialsDetail";
		}
		return result;
	}

	/**
	 * 自采材料信息
	 * 
	 * @param orderId
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "pmMaterialSelfbuyReimburseDetail")
	public String pmMaterialSelfbuyReimburseDetail(int orderId, Model model, HttpServletRequest request) {
		Manager manager = (Manager) request.getSession().getAttribute("manager");
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("orderId", orderId);
		map1.put("settleCategory", ConstantUtils.PM_SETTLE_CATEGORY_11);
		map1.put("pmEmployeeId", manager.getId());
		Double sinceMaterials = inspectorConfirmService.queryManangerPenalty(map1);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("orderId", orderId);
		param.put("pmEmployeeId", manager.getId());
		List<BizMaterialSelfbuyVo> materialSelfbuyList = bizPmSettleBillService.querySelfbuyMaterial(param);
		model.addAttribute("sinceMaterials", sinceMaterials);
		model.addAttribute("materialSelfbuyList", materialSelfbuyList);
		return "/mobile/modules/Manager/projectmanagersettlement/completeMaterialSelfbuyReimburseDetail";
	}

	/**
	 * 质保金信息
	 * @param orderId
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "pmGuaranteeMoneyDetail")
	public String pmGuaranteeMoneyDetail(int orderId, Model model, HttpServletRequest request) {
		// 质保金
		Double pmGuaranteeMoney = 0.0;
		BizPmGuaranteeMoneyCnfgSnap gmcs = bizPmGuaranteeMoneyCnfgSnapService.findGmc(orderId);
		if (gmcs != null) {
			BizGuaranteeMoneyBalance bizGuaranteeMoneyBalance = bizGuaranteeMoneyBalanceService
					.findGuaranteeMoneyBalanceByEmployeeId(gmcs.getPmEmployeeId());
			if (bizGuaranteeMoneyBalance == null) {
				bizGuaranteeMoneyBalance = new BizGuaranteeMoneyBalance();
			}
			Double total = bizGuaranteeMoneyBalance.getGuaranteeMoneyBalance() + gmcs.getGuaranteeMoneyPerOrder();
			if (total >= gmcs.getGuaranteeMoneyMax()) {
				pmGuaranteeMoney = gmcs.getGuaranteeMoneyMax() - bizGuaranteeMoneyBalance.getGuaranteeMoneyBalance();
				if (pmGuaranteeMoney < 0) {
					pmGuaranteeMoney = 0.00;
				}
			} else {
				pmGuaranteeMoney = gmcs.getGuaranteeMoneyPerOrder();
			}
		}
		model.addAttribute("pmGuaranteeMoney", pmGuaranteeMoney);
		model.addAttribute("gmcs", gmcs);
		return "/mobile/modules/Manager/projectmanagersettlement/completeGuaranteeMoneyDetail";
	}

}
