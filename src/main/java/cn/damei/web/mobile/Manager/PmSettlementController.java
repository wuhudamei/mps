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
import cn.damei.entity.modules.BizPmSettleBill;
import cn.damei.entity.modules.InspectorPunish;
import cn.damei.service.modules.BizPmSettleBillService;
import cn.damei.entity.modules.BizPmStarCommissionCnfgSnap;
import cn.damei.service.modules.InspectorConfirmService;


@Controller
@RequestMapping(value = "mobile/modules/manager/projectmanagersettlement/web/pmSettlementController")
public class PmSettlementController {

	@Autowired
	private BizPmSettleBillService bizPmSettleBillService;
	@Autowired
	private InspectorConfirmService inspectorConfirmService;

	@Autowired
	private BizAssessRewardPunishService bizAssessRewardPunishService;
	@Autowired
	private PmMaterialsSettleInfoService pmMaterialsSettleInfoService;
	@Autowired
	private BizPmGuaranteeMoneyCnfgSnapService bizPmGuaranteeMoneyCnfgSnapService;


	@RequestMapping(value = "pmSettleDetail")
	public String pmSettleDetail(int orderId, int settleBillType, Model model, HttpServletRequest request) {
		Manager manager = (Manager) request.getSession().getAttribute("manager");
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("orderId", orderId);
		param.put("settleBillType", settleBillType);
		param.put("pmEmployeeId",manager.getId());
		BizPmSettleBill bizPmSettleBill = bizPmSettleBillService.findPmSettleBillInfoByParam(param);
		if (bizPmSettleBill == null) {
			bizPmSettleBill = new BizPmSettleBill();
		}
		if (bizPmSettleBill.getMidwayCommissionAmount() == null) {
			bizPmSettleBill.setMidwayCommissionAmount(0.0);
		}

		if (bizPmSettleBill.getCompleteCommissionAmount() == null) {
			bizPmSettleBill.setCompleteCommissionAmount(0.0);
		}

		if (bizPmSettleBill.getOwnpayAmount() == null) {
			bizPmSettleBill.setOwnpayAmount(0.0);
		}

		if (bizPmSettleBill.getMaterialsStandardAmount() == null) {
			bizPmSettleBill.setMaterialsStandardAmount(0.0);
		} else if (bizPmSettleBill.getMaterialsStandardAmount() < 0) {
			bizPmSettleBill.setMaterialsStandardAmount(0 - bizPmSettleBill.getMaterialsStandardAmount());
		}

		if (bizPmSettleBill.getMidwayQcCheckPunishAmount() == null) {
			bizPmSettleBill.setMidwayQcCheckPunishAmount(0.0);
		} else if (bizPmSettleBill.getMidwayQcCheckPunishAmount() < 0) {
			bizPmSettleBill.setMidwayQcCheckPunishAmount(0 - bizPmSettleBill.getMidwayQcCheckPunishAmount());
		}

		if (bizPmSettleBill.getCompletQcCheckPunishAmount() == null) {
			bizPmSettleBill.setCompletQcCheckPunishAmount(0.0);
		} else if (bizPmSettleBill.getCompletQcCheckPunishAmount() < 0) {
			bizPmSettleBill.setCompletQcCheckPunishAmount(0 - bizPmSettleBill.getCompletQcCheckPunishAmount());
		}

		if (bizPmSettleBill.getMaterialSelfbuyReimburseAmount() == null) {
			bizPmSettleBill.setMaterialSelfbuyReimburseAmount(0.0);
		}

		if (bizPmSettleBill.getMidwayAuxiliaryMaterialsSettleAmount() == null) {
			bizPmSettleBill.setMidwayAuxiliaryMaterialsSettleAmount(0.0);
		}

		if (bizPmSettleBill.getCompleteAuxiliaryMaterialsSettleAmount() == null) {
			bizPmSettleBill.setCompleteAuxiliaryMaterialsSettleAmount(0.0);
		}

		if (bizPmSettleBill.getMidwayRewardAmount() == null) {
			bizPmSettleBill.setMidwayRewardAmount(0.0);
		}

		if (bizPmSettleBill.getMidwayPunishAmount() == null) {
			bizPmSettleBill.setMidwayPunishAmount(0.0);
		} else if (bizPmSettleBill.getMidwayPunishAmount() < 0) {
			bizPmSettleBill.setMidwayPunishAmount(0 - bizPmSettleBill.getMidwayPunishAmount());
		}

		if (bizPmSettleBill.getCompleteRewardAmount() == null) {
			bizPmSettleBill.setCompleteRewardAmount(0.0);
		}

		if (bizPmSettleBill.getCompletePunishAmount() == null) {
			bizPmSettleBill.setCompletePunishAmount(0.0);
		} else if (bizPmSettleBill.getCompletePunishAmount() < 0) {
			bizPmSettleBill.setCompletePunishAmount(0 - bizPmSettleBill.getCompletePunishAmount());
		}

		if (bizPmSettleBill.getMidwayInspectionRewardAmount() == null) {
			bizPmSettleBill.setMidwayInspectionRewardAmount(0.0);
		}

		if (bizPmSettleBill.getMidwayInspectionPunishAmount() == null) {
			bizPmSettleBill.setMidwayInspectionPunishAmount(0.0);
		} else if (bizPmSettleBill.getMidwayInspectionPunishAmount() < 0) {
			bizPmSettleBill.setMidwayInspectionPunishAmount(0 - bizPmSettleBill.getMidwayInspectionPunishAmount());
		}

		if (bizPmSettleBill.getCompleteInspectionRewardAmount() == null) {
			bizPmSettleBill.setCompleteInspectionRewardAmount(0.0);
		}

		if (bizPmSettleBill.getCompleteInspectionPunishAmount() == null) {
			bizPmSettleBill.setCompleteInspectionPunishAmount(0.0);
		} else if (bizPmSettleBill.getCompleteInspectionPunishAmount() < 0) {
			bizPmSettleBill.setCompleteInspectionPunishAmount(0 - bizPmSettleBill.getCompleteInspectionPunishAmount());
		}

		if (bizPmSettleBill.getGuaranteeMoneyAmount() == null) {
			bizPmSettleBill.setGuaranteeMoneyAmount(0.0);
		} else if (bizPmSettleBill.getGuaranteeMoneyAmount() < 0) {
			bizPmSettleBill.setGuaranteeMoneyAmount(0 - bizPmSettleBill.getGuaranteeMoneyAmount());
		}

		if (bizPmSettleBill.getTotalAmount() == null) {
			bizPmSettleBill.setTotalAmount(0.0);
		}

		model.addAttribute("bizPmSettleBill", bizPmSettleBill);
		String result = "";
		if (settleBillType == 1) {
			result = "mobile/modules/Manager/projectmanagersettlement/pmsettlementDetail/midwayPmSettleDetail";
		} else if (settleBillType == 2) {
			result = "mobile/modules/Manager/projectmanagersettlement/pmsettlementDetail/completePmSettleDetail";
		}
		return result;
	}


	@RequestMapping(value = "pmCommissionDetail")
	public String pmCommissionDetail(int orderId, Model model, String type,HttpServletRequest request) {
		Manager manager = (Manager) request.getSession().getAttribute("manager");
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("orderId",orderId);
		param.put("pmEmployeeId",manager.getId());
		BizPmStarCommissionCnfgSnap bizPmStarCommissionCnfgSnap = inspectorConfirmService
				.queryManagerCommissionByParam(param);
		Double commissionAmount = 0.0;
		String result = "";
		if (type.equals("1")) {
			commissionAmount = bizPmStarCommissionCnfgSnap.getCommissionAmount()
					* bizPmStarCommissionCnfgSnap.getCommissionRateMidway();
			result = "mobile/modules/Manager/projectmanagersettlement/pmsettlementDetail/midwayPmCommissionDetail";
		} else if (type.equals("2")) {
			commissionAmount = bizPmStarCommissionCnfgSnap.getCommissionAmount()
					* bizPmStarCommissionCnfgSnap.getCommissionRateComplete();
			result = "mobile/modules/Manager/projectmanagersettlement/pmsettlementDetail/completePmCommissionDetail";
		}
		model.addAttribute("bizPmStarCommissionCnfgSnap", bizPmStarCommissionCnfgSnap);
		model.addAttribute("commissionAmount", commissionAmount);
		return result;
	}


	@RequestMapping(value = "pmOwnpayDetail")
	public String pmOwnpayDetail(int orderId, Model model) {
		Double managerOwnpay = inspectorConfirmService.queryManagerOwnpay(orderId);
		List<BizPmOwnpayCnfgSnap> pmOwnpayCnfgSnapList = inspectorConfirmService
				.queryPmOwnpayCnfgSnapByOrderId(orderId);
		model.addAttribute("managerOwnpay", managerOwnpay);
		model.addAttribute("pmOwnpayCnfgSnapList", pmOwnpayCnfgSnapList);
		return "mobile/modules/Manager/projectmanagersettlement/pmsettlementDetail/midwayOwnpayDetail";
	}


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
		if (details3.getReceiveBillAmount() > 0) {
			details3.setReceiveBillAmount(0 - details3.getReceiveBillAmount());
		}
		model.addAttribute("details3", details3);
		model.addAttribute("codeList", codeList);
		model.addAttribute("materialsStandardList", materialsStandardList);
		return "mobile/modules/Manager/projectmanagersettlement/pmsettlementDetail/midwayMaterialsStandardDetail";
	}


	@RequestMapping(value = "pmQcCheckPunishDetail")
	public String pmQcCheckPunishDetail(Integer orderId, String type, Model model, HttpServletRequest request) {
		Manager manager = (Manager) request.getSession().getAttribute("manager");
		InspectorPunish inspectorPunish = new InspectorPunish();
		inspectorPunish.setOrderId(orderId);
		inspectorPunish.setPmEmployeeId(manager.getId());
		String result="";
		if (type.equals("1")) {
			inspectorPunish.setType(ConstantUtils.PM_SETTLE_CATEGORY_401);
			result="mobile/modules/Manager/projectmanagersettlement/pmsettlementDetail/midwayQcCheckPunishDetail";
		} else if (type.equals("2")) {
			inspectorPunish.setType(ConstantUtils.PM_SETTLE_CATEGORY_402);
			result="mobile/modules/Manager/projectmanagersettlement/pmsettlementDetail/completeQcCheckPunishDetail";
		}
		Double managerPenalty=0.0;
		List<InspectorPunish> pmPunishList = bizPmSettleBillService.findInspector(inspectorPunish);
		for(InspectorPunish inspector : pmPunishList){
			managerPenalty= managerPenalty+inspector.getMoney();
		}
		model.addAttribute("pmPunishList", pmPunishList);
		model.addAttribute("managerPenalty", managerPenalty);
		return result;
	}


	@RequestMapping(value = "pmRewardDetail")
	public String pmRewardDetail(int orderId, Model model, HttpServletRequest request, String type) {
		Manager manager = (Manager) request.getSession().getAttribute("manager");
		Map<String, Object> rewarPunishParam = new HashMap<String, Object>();
		rewarPunishParam.put("orderId", orderId);
		rewarPunishParam.put("pmEmployeeId", manager.getId());
		String result = "";
		if (type.equals("1")) {
			rewarPunishParam.put("settleCategory", ConstantUtils.PM_SETTLE_CATEGORY_907);
			result = "mobile/modules/Manager/projectmanagersettlement/pmsettlementDetail/midwayRewardDetail";
		} else if (type.equals("2")) {
			rewarPunishParam.put("settleCategory", ConstantUtils.PM_SETTLE_CATEGORY_1002);
			result = "mobile/modules/Manager/projectmanagersettlement/pmsettlementDetail/completeRewardDetail";
		} else if(type.equals("3")){
			rewarPunishParam.put("settleCategory", ConstantUtils.PM_SETTLE_CATEGORY_912);
			result = "mobile/modules/Manager/projectmanagersettlement/pmsettlementDetail/midwayInspectionRewardDetail";
		}else if(type.equals("4")){
			rewarPunishParam.put("settleCategory", ConstantUtils.PM_SETTLE_CATEGORY_1012);
			result = "mobile/modules/Manager/projectmanagersettlement/pmsettlementDetail/completeInspectionRewardDetail";
		}
		List<BizAssessRewardPunish> list = bizAssessRewardPunishService
				.queryPmRewardPunishBySettleParam(rewarPunishParam);
		Double pmRewardAmount = 0.0;
		for(BizAssessRewardPunish reward: list){
			pmRewardAmount = pmRewardAmount+reward.getRewardPunishAmount();
		}
		model.addAttribute("list",list);
		model.addAttribute("pmRewardAmount",pmRewardAmount);
		return result;
	}


	@RequestMapping(value = "pmPunishDetail")
	public String pmPunishDetail(int orderId, Model model, HttpServletRequest request, String type) {
		Manager manager = (Manager) request.getSession().getAttribute("manager");
		Map<String, Object> rewarPunishParam = new HashMap<String, Object>();
		rewarPunishParam.put("orderId", orderId);
		rewarPunishParam.put("pmEmployeeId", manager.getId());
		String result = "";
		if (type.equals("1")) {
			rewarPunishParam.put("settleCategory", ConstantUtils.PM_SETTLE_CATEGORY_908);
			result = "mobile/modules/Manager/projectmanagersettlement/pmsettlementDetail/midwayPunishDetail";
		} else if (type.equals("2")) {
			rewarPunishParam.put("settleCategory", ConstantUtils.PM_SETTLE_CATEGORY_1003);
			result = "mobile/modules/Manager/projectmanagersettlement/pmsettlementDetail/completePunishDetail";
		} else if (type.equals("3")) {
			rewarPunishParam.put("settleCategory", ConstantUtils.PM_SETTLE_CATEGORY_913);
			result = "mobile/modules/Manager/projectmanagersettlement/pmsettlementDetail/midwayInspectionPunishDetail";
		}else if (type.equals("4")) {
			rewarPunishParam.put("settleCategory", ConstantUtils.PM_SETTLE_CATEGORY_1013);
			result = "mobile/modules/Manager/projectmanagersettlement/pmsettlementDetail/completeInspectionPunishDetail";
		}
		List<BizAssessRewardPunish> list = bizAssessRewardPunishService
				.queryPmRewardPunishBySettleParam(rewarPunishParam);
		Double pmPunishAmount = 0.0;
		for(BizAssessRewardPunish reward: list){
			pmPunishAmount = pmPunishAmount+reward.getRewardPunishAmount();
		}
		model.addAttribute("list",list);
		model.addAttribute("pmPunishAmount",pmPunishAmount);
		return result;
	}


	@RequestMapping(value = "queryPmMaterials")
	public String queryPmMaterials(Integer orderId, String type, String settleStatus, Model model,
			HttpServletRequest request) {
		Manager manager = (Manager) request.getSession().getAttribute("manager");
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("orderId", orderId);
		String result= "";
		if (type.equals("1")) {
			result = "mobile/modules/Manager/projectmanagersettlement/pmsettlementDetail/midwayMaterials";
			param.put("settleCategory", ConstantUtils.PM_SETTLE_CATEGORY_121);
		} else if (type.equals("2")) {
			result = "mobile/modules/Manager/projectmanagersettlement/pmsettlementDetail/completeMaterials";
			param.put("settleCategory", ConstantUtils.PM_SETTLE_CATEGORY_122);
		}
		param.put("pmEmployeeId", manager.getId());
		param.put("settleStatus", settleStatus);
		List<PmMaterialsSettleInfo> pmMaterials = pmMaterialsSettleInfoService.queryPmMaterialsByParam(param);
		double pmMaterialsSettleAmount = 0.0;
		if (pmMaterials != null && pmMaterials.size() > 0) {
			for (PmMaterialsSettleInfo info : pmMaterials) {
				pmMaterialsSettleAmount = pmMaterialsSettleAmount + info.getPmMaterialsSettleAmount();
			}
		}
		model.addAttribute("pmMaterialsSettleAmount", pmMaterialsSettleAmount);
		model.addAttribute("pmMaterials", pmMaterials);
		return result;
	}


	@RequestMapping(value = "querySelfbuyMaterial")
	public String querySelfbuyMaterial(Integer orderId, Model model, HttpServletRequest request) {
		Manager manager = (Manager) request.getSession().getAttribute("manager");
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("orderId", orderId);
		param.put("pmEmployeeId", manager.getId());
		List<BizMaterialSelfbuyVo> list = bizPmSettleBillService.querySelfbuyMaterial(param);
		Double totalAmount = 0.0;
		if (list != null && list.size() > 0) {
			for (BizMaterialSelfbuyVo vo : list) {
				totalAmount = totalAmount + vo.getSettleAmount();
			}
		}
		model.addAttribute("list", list);
		model.addAttribute("totalAmount", totalAmount);
		return "mobile/modules/Manager/projectmanagersettlement/pmsettlementDetail/completeSelfbuyMaterial";
	}


	@RequestMapping(value = "queryGuaranteeMoney")
	public String queryGuaranteeMoney(Integer orderId, Double pmGuaranteeMoney, Model model) {
		BizPmStarCommissionCnfgSnap bizPmStarCommissionCnfgSnap = inspectorConfirmService
				.queryManagerCommissionByOrderId(orderId);
		BizPmGuaranteeMoneyCnfgSnap gmcs = bizPmGuaranteeMoneyCnfgSnapService.findGmc(orderId);
		model.addAttribute("gmcs", gmcs);
		model.addAttribute("pmGuaranteeMoney", pmGuaranteeMoney);
		model.addAttribute("bizPmStarCommissionCnfgSnap", bizPmStarCommissionCnfgSnap);
		return "mobile/modules/Manager/projectmanagersettlement/pmsettlementDetail/completeGuaranteeMoney";
	}
}
