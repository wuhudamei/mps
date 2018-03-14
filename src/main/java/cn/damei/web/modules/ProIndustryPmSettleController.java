package cn.damei.web.modules;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.damei.entity.modules.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.damei.common.config.Global;
import cn.damei.common.constantUtils.BusinessLogConstantUtil;
import cn.damei.common.persistence.Page;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.mobile.Worker.SettlementAuxiliary;
import cn.damei.service.modules.BizBusinessRewardPunishService;
import cn.damei.service.modules.BizOrderChangeService;
import cn.damei.service.modules.BizOrderContractSettleService;
import cn.damei.service.modules.BizOrderMaterialCarryCostService;
import cn.damei.entity.modules.BizPmPreIndustrySettleBill;
import cn.damei.service.modules.BizPmPreIndustrySettleBillService;
import cn.damei.service.modules.BizBusinessStatusLogService;
import cn.damei.entity.modules.BizQcLongwayCommissionLog;
import cn.damei.service.modules.BizQcLongwayCommissionLogService;
import cn.damei.entity.modules.BizNormalPmSettleNode;
import cn.damei.service.modules.BizNormalPmSettleNodeService;
import cn.damei.entity.modules.BizOrderMaterialsStandard;
import cn.damei.entity.modules.BizMaterialsStandardReceiveBill;
import cn.damei.entity.modules.Order2;
import cn.damei.service.modules.OrderService2;
import cn.damei.entity.modules.BizOrderTaskpackagePaymentVo;
import cn.damei.service.modules.BizOrderTaskpackagePaymentService;
import cn.damei.entity.modules.BizPmGuaranteeMoneyLog;
import cn.damei.service.modules.BizPmGuaranteeMoneyLogService;
import cn.damei.entity.modules.BizPmGuaranteeMoneyCnfgSnap;
import cn.damei.service.modules.BizPmGuaranteeMoneyCnfgSnapService;
import cn.damei.entity.modules.InspectorPunish;
import cn.damei.entity.modules.BizPmSettleCategoryDetail;
import cn.damei.service.modules.BizPmSettleCategoryDetailService;
import cn.damei.entity.modules.BizPmSettleCategorySummary;
import cn.damei.service.modules.BizPmSettleCategorySummaryService;
import cn.damei.entity.modules.ProIndustryPmSettleInfo;
import cn.damei.service.modules.ProIndustryPmSettleService;
import cn.damei.entity.modules.MainPanel;
import cn.damei.entity.modules.BizGuaranteeMoneyBalance;
import cn.damei.service.modules.BizGuaranteeMoneyBalanceService;
import cn.damei.service.modules.InspectorConfirmService;
import cn.damei.common.utils.UserUtils;


@Controller
@RequestMapping(value = "${adminPath}/proIndustryPmSettle/proIndustryPmSettle")
public class ProIndustryPmSettleController extends BaseController {

	@Autowired
	private ProIndustryPmSettleService proIndustryPmSettleService;
	@Autowired
	private BizOrderTaskpackagePaymentService bizOrderTaskpackagePaymentService;
	@Autowired
	private OrderService2 orderService2;
	@Autowired
	private BizNormalPmSettleNodeService bizNormalPmSettleNodeService;
	@Autowired
	private BizOrderContractSettleService bizOrderContractSettleService;
	@Autowired
	private BizOrderChangeService bizOrderChangeService;
	@Autowired
	private InspectorConfirmService inspectorConfirmService;
	@Autowired
	private BizOrderMaterialCarryCostService bizOrderMaterialCarryCostService;
	@Autowired
	private BizBusinessRewardPunishService bizBusinessRewardPunishService;
	@Autowired
	private BizPmPreIndustrySettleBillService bizPmPreIndustrySettleBillService;
	@Autowired
	private BizPmGuaranteeMoneyCnfgSnapService bizPmGuaranteeMoneyCnfgSnapService;
	@Autowired
	private BizPmGuaranteeMoneyLogService bizPmGuaranteeMoneyLogService;
	@Autowired
	private BizGuaranteeMoneyBalanceService bizGuaranteeMoneyBalanceService;
	@Autowired
	private BizQcLongwayCommissionLogService bizQcLongwayCommissionLogService;

	@Autowired
	private BizPmSettleCategorySummaryService bizPmSettleCategorySummaryService;
	@Autowired
	private BizPmSettleCategoryDetailService bizPmSettleCategoryDetailService;
	@Autowired
	private BizBusinessStatusLogService bizBusinessStatusLogService;


	@RequestMapping(value = "queryProIndustryPmMidwaySettle")
	public String queryProIndustryPmMidwaySettle(ProIndustryPmSettleInfo proIndustryPmSettleInfo, Model model,
												 HttpServletRequest request, HttpServletResponse response) {

		if (proIndustryPmSettleInfo.getStoreId() == null) {
			String storeId = UserUtils.getUser().getStoreId();
			if (StringUtils.isBlank(storeId)) {
				proIndustryPmSettleInfo.setStoreId(null);
			} else {
				proIndustryPmSettleInfo.setStoreId(Integer.parseInt(storeId));
			}
		}
		if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		} else {
			proIndustryPmSettleInfo.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
		}
		return "modules/proIndustryPmSettle/proIndustryPmMidwaySettle";
	}


	@RequestMapping(value = "queryProIndustryPmMidwaySettleList")
	public String queryProIndustryPmMidwaySettleList(ProIndustryPmSettleInfo proIndustryPmSettleInfo, Model model,
													 HttpServletRequest request, HttpServletResponse response) {

		if (proIndustryPmSettleInfo.getStoreId() == null) {
			String storeId = UserUtils.getUser().getStoreId();
			if (StringUtils.isBlank(storeId)) {
				proIndustryPmSettleInfo.setStoreId(null);
			} else {
				proIndustryPmSettleInfo.setStoreId(Integer.parseInt(storeId));
			}
		}

		if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		} else {
			proIndustryPmSettleInfo.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
		}
		if (proIndustryPmSettleInfo.getEnginDepartId() != null) {
			List<Integer> list = new ArrayList<>();
			list.add(proIndustryPmSettleInfo.getEnginDepartId());
			proIndustryPmSettleInfo.setEnginDepartIds(list);
		}
		proIndustryPmSettleInfo.setProjectMode(4);
		Page<ProIndustryPmSettleInfo> page = proIndustryPmSettleService.queryProIndustryPmMidwaySettle(
				new Page<ProIndustryPmSettleInfo>(request, response), proIndustryPmSettleInfo);
		model.addAttribute("page", page);
		return "modules/proIndustryPmSettle/proIndustryPmMidwaySettle";
	}


	@RequestMapping(value = "queryProIndustryPmCompleteSettle")
	public String queryProIndustryPmCompleteSettle(ProIndustryPmSettleInfo proIndustryPmSettleInfo, Model model,
												   HttpServletRequest request, HttpServletResponse response) {

		if (proIndustryPmSettleInfo.getStoreId() == null) {
			String storeId = UserUtils.getUser().getStoreId();
			if (StringUtils.isBlank(storeId)) {
				proIndustryPmSettleInfo.setStoreId(null);
			} else {
				proIndustryPmSettleInfo.setStoreId(Integer.parseInt(storeId));
			}
		}
		if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		} else {
			proIndustryPmSettleInfo.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
		}
		return "modules/proIndustryPmSettle/proIndustryPmCompleteSettle";
	}


	@RequestMapping(value = "queryProIndustryPmCompleteSettleList")
	public String queryProIndustryPmCompleteSettleList(ProIndustryPmSettleInfo proIndustryPmSettleInfo, Model model,
													   HttpServletRequest request, HttpServletResponse response) {

		if (proIndustryPmSettleInfo.getStoreId() == null) {
			String storeId = UserUtils.getUser().getStoreId();
			if (StringUtils.isBlank(storeId)) {
				proIndustryPmSettleInfo.setStoreId(null);
			} else {
				proIndustryPmSettleInfo.setStoreId(Integer.parseInt(storeId));
			}
		}

		if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		} else {
			proIndustryPmSettleInfo.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
		}
		if (proIndustryPmSettleInfo.getEnginDepartId() != null) {
			List<Integer> list = new ArrayList<>();
			list.add(proIndustryPmSettleInfo.getEnginDepartId());
			proIndustryPmSettleInfo.setEnginDepartIds(list);
		}
		proIndustryPmSettleInfo.setProjectMode(4);
		Page<ProIndustryPmSettleInfo> page = proIndustryPmSettleService.queryProIndustryPmCompleteSettle(
				new Page<ProIndustryPmSettleInfo>(request, response), proIndustryPmSettleInfo);
		model.addAttribute("page", page);
		return "modules/proIndustryPmSettle/proIndustryPmCompleteSettle";
	}

	@RequestMapping(value = "queryTaskpackPaymentInfo")
	public String queryTaskpackPaymentInfo(Integer orderId, Model model, HttpServletRequest request,
										   HttpServletResponse response) {
		Order2 order2 = orderService2.get(orderId);
		List<BizOrderTaskpackagePaymentVo> paymentList = bizOrderTaskpackagePaymentService
				.findPaymentVoByOrderId(orderId);
		model.addAttribute("paymentList", paymentList);
		model.addAttribute("order2", order2);
		return "modules/proIndustryPmSettle/orderPaymentInfo";
	}


	@RequestMapping(value = "openMidwaySettleInfoPage")
	public String openMidwaySettleInfoPage(Integer orderId, Model model, HttpServletRequest request,
										   HttpServletResponse response) {
		Order2 order2 = orderService2.get(orderId);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("storeId", order2.getStoreId());
		param.put("projectMode", order2.getProjectMode());
		param.put("settleStage", 10);
		BizNormalPmSettleNode bizNormalPmSettleNode = bizNormalPmSettleNodeService.querySettleNodeByParam(param);

		Double contractAmount = 0.0;
		Map<String, Object> settParam = new HashMap<String, Object>();
		settParam.put("orderId", orderId);
		settParam.put("settleStage", 10);
		BizOrderContractSettle contractSettle = bizOrderContractSettleService.findOrderContractSettleByParam(settParam);
		if (contractSettle == null) {
			contractSettle = new BizOrderContractSettle();
			Double subsidySquare = Double.valueOf(order2.getContractArea());
			Double subsidyPrice = 0.0;
			if (subsidySquare > 0 && subsidySquare < 71) {
				Map<String, Object> subsidyPriceParam = new HashMap<String, Object>();
				subsidyPriceParam.put("storeId", order2.getStoreId());
				subsidyPriceParam.put("projectMode", order2.getProjectMode());
				subsidyPriceParam.put("contractArea", subsidySquare);
				subsidyPrice = proIndustryPmSettleService.querySubsidyPriceByParam(subsidyPriceParam);
				if (subsidyPrice == null) {
					subsidyPrice = 0.0;
				}
			}
			Double subsidyAmount = subsidySquare * subsidyPrice;

			if(bizNormalPmSettleNode ==null){
				bizNormalPmSettleNode = new BizNormalPmSettleNode();
			}
			if(bizNormalPmSettleNode.getSettlePrice() == null){
				bizNormalPmSettleNode.setSettlePrice("0");
			}
			contractAmount = Double.valueOf(bizNormalPmSettleNode.getSettlePrice())
					* Double.valueOf(order2.getContractArea()) + subsidyAmount;

			contractSettle.setOrderId(orderId);
			contractSettle.setSettleStage("10");
			contractSettle.setPackagedSquare( Double.valueOf(order2.getContractArea()));
			contractSettle.setPackagedPrice(Double.valueOf(bizNormalPmSettleNode.getSettlePrice()));
			contractSettle.setPackagedAmount(Double.valueOf(order2.getContractArea()) * Double.valueOf(bizNormalPmSettleNode.getSettlePrice()));
			contractSettle.setContractSubsidySquare(subsidySquare);
			contractSettle.setContractSubsidyPrice(subsidyPrice);
			contractSettle.setContractSubsidyAmount(subsidyAmount);
			contractSettle.setContractAmount(contractAmount);
			bizOrderContractSettleService.save(contractSettle);
		} else {
			contractAmount = contractSettle.getContractAmount();
		}

		Map<String, Object> auMaterParam = new HashMap<String, Object>();
		auMaterParam.put("orderId", orderId);
		auMaterParam.put("isSandCement", 0);
		Double auMaterAmount = proIndustryPmSettleService.queryAuxiliaryMaterialsAmountByOrderId(auMaterParam);
		if (auMaterAmount == null) {
			auMaterAmount = 0.00;
		}
		if(auMaterAmount>0){
			auMaterAmount = 0-auMaterAmount;
		}

		Map<String, Object> sandParam = new HashMap<String, Object>();
		sandParam.put("orderId", orderId);
		sandParam.put("isSandCement", 1);
		Double sandAmount = proIndustryPmSettleService.queryAuxiliaryMaterialsAmountByOrderId(sandParam);
		if (sandAmount == null) {
			sandAmount = 0.00;
		}
		if(sandAmount>0){
			sandAmount = 0-sandAmount;
		}

		Double standardMaAmount = 0.0;
		List<BizOrderMaterialsStandard> materialsStandardList = inspectorConfirmService
				.queryMaterialsStandardByOrderId(orderId);

		if (materialsStandardList != null && materialsStandardList.size() > 0) {
			for (BizOrderMaterialsStandard materialsStandard : materialsStandardList) {
				standardMaAmount = standardMaAmount + materialsStandard.getMaterialsAmount();
			}
		}
		if(standardMaAmount>0){
			standardMaAmount = 0-standardMaAmount;
		}

		Double mainPanelAmount = proIndustryPmSettleService.queryMainPanelAmountByOrderId(orderId);
		if (mainPanelAmount == null) {
			mainPanelAmount = 0.00;
		}
		if(mainPanelAmount>0){
			mainPanelAmount = 0-mainPanelAmount;
		}

		Double workerAmount = proIndustryPmSettleService.queryWorkerAmountByOrderId(orderId);
		if(workerAmount == null){
			workerAmount = 0.00;
		}
		if(workerAmount>0){
			workerAmount = 0-workerAmount;
		}

		Map<String, Object> pmQcParam = new HashMap<String, Object>();
		pmQcParam.put("orderId", orderId);
		pmQcParam.put("pmEmployeeId", order2.getItemManagerId());
		Double pmQcFine = proIndustryPmSettleService.queryPmQcFineByParam(pmQcParam);
		if (pmQcFine == null) {
			pmQcFine = 0.00;
		}

		Map<String, Object> baseInstalledParam = new HashMap<String, Object>();
		baseInstalledParam.put("orderId", orderId);
		baseInstalledParam.put("changeType", 10);
		Double baseInstalledAmount = proIndustryPmSettleService.queryBaseInstalledAmount(baseInstalledParam);
		if (baseInstalledAmount == null) {
			baseInstalledAmount = 0.00;
		}


		baseInstalledParam.put("changeType", 20);
		Double midwayChangeAddAmount = proIndustryPmSettleService.queryBaseInstalledAmount(baseInstalledParam);
		if (midwayChangeAddAmount == null) {
			midwayChangeAddAmount = 0.00;
		}


		baseInstalledParam.put("changeType", 30);
		Double midwayChangeReductAmount = proIndustryPmSettleService.queryBaseInstalledAmount(baseInstalledParam);
		if (midwayChangeReductAmount == null) {
			midwayChangeReductAmount = 0.00;
		}

		if(midwayChangeReductAmount>0){
			midwayChangeReductAmount = 0-midwayChangeReductAmount;
		}


		BizOrderMaterialCarryCost bizOrderMaterialCarryCost = bizOrderMaterialCarryCostService
				.queryOrderMaterialCarryCostByOrderId(orderId);
		if(bizOrderMaterialCarryCost == null){
			bizOrderMaterialCarryCost = new BizOrderMaterialCarryCost();
		}
		if(bizOrderMaterialCarryCost.getAccountAmount() == null){
			bizOrderMaterialCarryCost.setAccountAmount(0.00);
		}

		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("orderId", orderId);
		map1.put("employeeType", 2);
		map1.put("relatedBusinessType", "1");
		map1.put("rewardPunishType", "1");
		BizBusinessRewardPunish midwayReward = bizBusinessRewardPunishService.queryBusinessRewardPunishByParam(map1);
		if (midwayReward == null) {
			midwayReward = new BizBusinessRewardPunish();
			midwayReward.setRewardPunishAmount(0.00);
		}


		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("orderId", orderId);
		map2.put("employeeType", 2);
		map2.put("relatedBusinessType", "1");
		map2.put("rewardPunishType", "2");
		BizBusinessRewardPunish midwayPunish = bizBusinessRewardPunishService.queryBusinessRewardPunishByParam(map2);
		if (midwayPunish == null) {
			midwayPunish = new BizBusinessRewardPunish();
			midwayPunish.setRewardPunishAmount(0.00);
		}

		if(midwayPunish.getRewardPunishAmount()>0){
			midwayPunish.setRewardPunishAmount(0-midwayPunish.getRewardPunishAmount());
		}

		model.addAttribute("order2", order2);
		model.addAttribute("contractAmount", contractAmount);
		model.addAttribute("auMaterAmount", auMaterAmount);
		model.addAttribute("sandAmount", sandAmount);
		model.addAttribute("standardMaAmount", standardMaAmount);
		model.addAttribute("mainPanelAmount", mainPanelAmount);
		model.addAttribute("workerAmount", workerAmount);
		model.addAttribute("pmQcFine", pmQcFine);
		model.addAttribute("baseInstalledAmount", baseInstalledAmount);
		model.addAttribute("midwayChangeAddAmount", midwayChangeAddAmount);
		model.addAttribute("midwayChangeReductAmount", midwayChangeReductAmount);
		model.addAttribute("bizOrderMaterialCarryCost", bizOrderMaterialCarryCost);
		model.addAttribute("midwayReward", midwayReward);
		model.addAttribute("midwayPunish", midwayPunish);
		model.addAttribute("bizNormalPmSettleNode",bizNormalPmSettleNode);
		return "modules/proIndustryPmSettle/settleInfo";
	}


	@RequestMapping(value = "openCompleteSettleInfoPage")
	public String openCompleteSettleInfoPage(Integer orderId, Model model, HttpServletRequest request,
											 HttpServletResponse response) {
		Order2 order2 = orderService2.get(orderId);

		Double contractAmount = 0.0;
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("storeId", order2.getStoreId());
		param.put("projectMode", order2.getProjectMode());
		param.put("settleStage", 20);
		BizNormalPmSettleNode bizNormalPmSettleNode = bizNormalPmSettleNodeService.querySettleNodeByParam(param);
		Map<String, Object> settParam = new HashMap<String, Object>();
		settParam.put("orderId", orderId);
		settParam.put("settleStage", 20);
		BizOrderContractSettle contractSettle = bizOrderContractSettleService.findOrderContractSettleByParam(settParam);
		if (contractSettle == null) {
			contractSettle = new BizOrderContractSettle();
			Double subsidySquare = Double.valueOf(order2.getContractArea());
			Double subsidyPrice = 0.0;
			if (subsidySquare > 0 && subsidySquare <= 70) {
				Map<String, Object> subsidyPriceParam = new HashMap<String, Object>();
				subsidyPriceParam.put("storeId", order2.getStoreId());
				subsidyPriceParam.put("projectMode", order2.getProjectMode());
				subsidyPriceParam.put("contractArea", subsidySquare);
				subsidyPrice = proIndustryPmSettleService.querySubsidyPriceByParam(subsidyPriceParam);
				if (subsidyPrice == null) {
					subsidyPrice = 0.0;
				}
			}
			Double subsidyAmount = subsidySquare * subsidyPrice;

			if(bizNormalPmSettleNode ==null){
				bizNormalPmSettleNode = new BizNormalPmSettleNode();
			}
			if(bizNormalPmSettleNode.getSettlePrice() == null){
				bizNormalPmSettleNode.setSettlePrice("0");
			}
			contractAmount = Double.valueOf(bizNormalPmSettleNode.getSettlePrice())
					* Double.valueOf(order2.getContractArea()) + subsidyAmount;

			contractSettle.setOrderId(orderId);
			contractSettle.setSettleStage("20");
			contractSettle.setPackagedSquare( Double.valueOf(order2.getContractArea()));
			contractSettle.setPackagedPrice(Double.valueOf(bizNormalPmSettleNode.getSettlePrice()));
			contractSettle.setPackagedAmount(Double.valueOf(order2.getContractArea()) * Double.valueOf(bizNormalPmSettleNode.getSettlePrice()));
			contractSettle.setContractSubsidySquare(subsidySquare);
			contractSettle.setContractSubsidyPrice(subsidyPrice);
			contractSettle.setContractSubsidyAmount(subsidyAmount);
			contractSettle.setContractAmount(contractAmount);
			bizOrderContractSettleService.save(contractSettle);
		} else {
			contractAmount = contractSettle.getContractAmount();
		}

		Map<String, Object> pmQcParam = new HashMap<String, Object>();
		pmQcParam.put("orderId", orderId);
		pmQcParam.put("pmEmployeeId", order2.getItemManagerId());
		Double pmQcFine = proIndustryPmSettleService.queryPmQcFineByParam(pmQcParam);
		if (pmQcFine == null) {
			pmQcFine = 0.00;
		}

		Double completeGuaranteeMoneyAmount = 0.00;
		BizPmGuaranteeMoneyCnfgSnap gmcs = bizPmGuaranteeMoneyCnfgSnapService.findGmc(orderId);
		if (gmcs != null) {
			Map<String,Object> logParam = new HashMap<String,Object>();
			logParam.put("orderId", orderId);
			logParam.put("pmEmPloyeeId", gmcs.getPmEmployeeId());
			BizPmGuaranteeMoneyLog moneyLog = bizPmGuaranteeMoneyLogService.findByParam(logParam);
			if(moneyLog == null){
				Double money = 0.0;
				BizPmGuaranteeMoneyLog log = bizPmGuaranteeMoneyLogService.findByEmployeeId(gmcs.getPmEmployeeId());
				BizGuaranteeMoneyBalance bizGuaranteeMoneyBalance = bizGuaranteeMoneyBalanceService
						.findGuaranteeMoneyBalanceByEmployeeId(gmcs.getPmEmployeeId());
				if (bizGuaranteeMoneyBalance == null || bizGuaranteeMoneyBalance.getId() == null) {
					bizGuaranteeMoneyBalance = new BizGuaranteeMoneyBalance();
				}
				if (log != null) {
					money = bizGuaranteeMoneyBalance.getGuaranteeMoneyBalance();
				}else{
					money = bizGuaranteeMoneyBalance.getGuaranteeMoneyBalance();
				}
				if(money == null){
					money = 0.0;
				}
				BizPmGuaranteeMoneyLog gml = new BizPmGuaranteeMoneyLog();
				gml.setPmEmployeeId(gmcs.getPmEmployeeId());
				gml.setOrderId(gmcs.getOrderId());
				gml.setTakeoffDatetime(new Date());
				Double total = money + gmcs.getGuaranteeMoneyPerOrder();
				Double takeoffAmount = 0.00;
				if (total >= gmcs.getGuaranteeMoneyMax()) {
					takeoffAmount = gmcs.getGuaranteeMoneyMax() - money;
					if(takeoffAmount <0){
						takeoffAmount = 0.00;
					}
				} else {
					takeoffAmount = gmcs.getGuaranteeMoneyPerOrder();
				}
				gml.setTakeoffAmount(takeoffAmount);
				gml.setTakeoffAmountTotal(
						bizGuaranteeMoneyBalance.getGuaranteeMoneyAmountPaidSettle() + gml.getTakeoffAmount());
				gml.setGuaranteeMoneyBalance(
						bizGuaranteeMoneyBalance.getGuaranteeMoneyBalance() + gml.getTakeoffAmount());
				gml.preInsert();

				Integer id = bizPmGuaranteeMoneyLogService.insert1(gml);

				if (bizGuaranteeMoneyBalance == null || bizGuaranteeMoneyBalance.getId() == null) {
					bizGuaranteeMoneyBalance = new BizGuaranteeMoneyBalance();
					bizGuaranteeMoneyBalance.setEmployeeId(gml.getPmEmployeeId());
					bizGuaranteeMoneyBalance.setGuaranteeMoneyAmountPaidSettle(gml.getTakeoffAmountTotal());
				} else {
					bizGuaranteeMoneyBalance.setGuaranteeMoneyAmountPaidSettle(
							bizGuaranteeMoneyBalance.getGuaranteeMoneyAmountPaidSettle() + gml.getTakeoffAmount());
				}
				bizGuaranteeMoneyBalance.setGuaranteeMoneyBalance(gml.getGuaranteeMoneyBalance());
				bizGuaranteeMoneyBalanceService.save(bizGuaranteeMoneyBalance);
				completeGuaranteeMoneyAmount = gml.getTakeoffAmount();
			}else{
				completeGuaranteeMoneyAmount = moneyLog.getTakeoffAmount();
			}

		}
		if(completeGuaranteeMoneyAmount>0){
			completeGuaranteeMoneyAmount = 0-completeGuaranteeMoneyAmount;
		}

		Double completeLongwayCommissionAmount = 0.00;
		BizQcLongwayCommissionLog bizQcLongwayCommissionLog = new BizQcLongwayCommissionLog();
		bizQcLongwayCommissionLog.setOrderId(orderId);
		bizQcLongwayCommissionLog.setLongwayCommissionType("10");
		bizQcLongwayCommissionLog.setLongwayCommissionEmployeeId(order2.getItemManagerId());
		BizQcLongwayCommissionLog commissionLog = bizQcLongwayCommissionLogService.queryCommissionLogByParam(bizQcLongwayCommissionLog);
		if(commissionLog != null){
			completeLongwayCommissionAmount = 	commissionLog.getCommissionAmount();
		}

		Map<String, Object> baseInstalledParam = new HashMap<String, Object>();
		baseInstalledParam.put("orderId", orderId);
		baseInstalledParam.put("changeType", 40);
		Double completeChangeAddAmount = proIndustryPmSettleService.queryBaseInstalledAmount(baseInstalledParam);
		if (completeChangeAddAmount == null) {
			completeChangeAddAmount = 0.00;
		}

		baseInstalledParam.put("changeType", 50);
		Double completeChangeReductAmount = proIndustryPmSettleService.queryBaseInstalledAmount(baseInstalledParam);
		if (completeChangeReductAmount == null) {
			completeChangeReductAmount = 0.00;
		}
		if(completeChangeReductAmount>0){
			completeChangeReductAmount = 0-completeChangeReductAmount;
		}

		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("orderId", orderId);
		map1.put("employeeType", 2);
		map1.put("relatedBusinessType", "2");
		map1.put("rewardPunishType", "1");
		BizBusinessRewardPunish completeReward = bizBusinessRewardPunishService.queryBusinessRewardPunishByParam(map1);
		if (completeReward == null) {
			completeReward = new BizBusinessRewardPunish();
			completeReward.setRewardPunishAmount(0.00);
		}

		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("orderId", orderId);
		map2.put("employeeType", 2);
		map2.put("relatedBusinessType", "2");
		map2.put("rewardPunishType", "2");
		BizBusinessRewardPunish completePunish = bizBusinessRewardPunishService.queryBusinessRewardPunishByParam(map2);
		if (completePunish == null) {
			completePunish = new BizBusinessRewardPunish();
			completePunish.setRewardPunishAmount(0.00);
		}
		if(completePunish.getRewardPunishAmount()>0){
			completePunish.setRewardPunishAmount(0-completePunish.getRewardPunishAmount());
		}

		Map<String,Object> midwaySettleBillParam = new HashMap<String,Object>();
		midwaySettleBillParam.put("orderId", orderId);
		midwaySettleBillParam.put("settleBillType", 1);
		BizPmPreIndustrySettleBill midwaySettleBill = bizPmPreIndustrySettleBillService.queryPmPreIndustrySettleBillByParam(midwaySettleBillParam);
		model.addAttribute("order2", order2);
		model.addAttribute("contractAmount",contractAmount);
		model.addAttribute("pmQcFine",pmQcFine);
		model.addAttribute("completeGuaranteeMoneyAmount",completeGuaranteeMoneyAmount);
		model.addAttribute("completeLongwayCommissionAmount",completeLongwayCommissionAmount);
		model.addAttribute("completeChangeAddAmount",completeChangeAddAmount);
		model.addAttribute("completeChangeReductAmount",completeChangeReductAmount);
		model.addAttribute("completeReward",completeReward);
		model.addAttribute("completePunish",completePunish);
		model.addAttribute("midwaySettleBill",midwaySettleBill);
		model.addAttribute("bizNormalPmSettleNode",bizNormalPmSettleNode);
		return "modules/proIndustryPmSettle/completeSettleInfo";
	}

	@RequestMapping(value = "openContractTotal")
	public String openContractTotal(Integer orderId,Integer settleStage,Integer isEditSettleBill, Model model, HttpServletRequest request,
									HttpServletResponse response,String isNewSettleBill) {
		Order2 order2 = orderService2.get(orderId);
		Map<String, Object> settParam = new HashMap<String, Object>();
		settParam.put("orderId", orderId);
		settParam.put("settleStage", settleStage);
		BizOrderContractSettle contractSettle = bizOrderContractSettleService.findOrderContractSettleByParam(settParam);
		model.addAttribute("contractSettle", contractSettle);
		model.addAttribute("order2", order2);
		model.addAttribute("isEditSettleBill",isEditSettleBill);
        model.addAttribute("isNewSettleBill",isNewSettleBill);
		return "modules/proIndustryPmSettle/contractTotal";
	}


	@RequestMapping(value = "saveContractTotal")
	public String saveContractTotal(BizOrderContractSettle contractSettle,Integer isEditSettleBill, RedirectAttributes redirectAttributes,
									Model model, HttpServletRequest request, HttpServletResponse response,String isNewSettleBill) {
		if (contractSettle.getPackagedSquare() == null) {
			contractSettle.setPackagedSquare(0.0);
		}
		if (contractSettle.getContractSubsidySquare() == null) {
			contractSettle.setContractSubsidySquare(0.0);
		}
		BizOrderContractSettle conSett = bizOrderContractSettleService.get(contractSettle.getId());
		conSett.setPackagedSquare(contractSettle.getPackagedSquare());
		conSett.setPackagedPrice(contractSettle.getPackagedPrice());
		conSett.setPackagedAmount(contractSettle.getPackagedAmount());
		conSett.setContractSubsidySquare(contractSettle.getContractSubsidySquare());
		conSett.setContractSubsidyPrice(contractSettle.getContractSubsidyPrice());
		conSett.setContractSubsidyAmount(contractSettle.getContractSubsidyAmount());
		conSett.setContractAmount(contractSettle.getContractAmount());
		bizOrderContractSettleService.save(conSett);
		BizPmPreIndustrySettleBill settleBill = new BizPmPreIndustrySettleBill();
		if(isEditSettleBill == 1){

			Map<String,Object> settleBillParam = new HashMap<String,Object>();
			settleBillParam.put("orderId", conSett.getOrderId());
			if(conSett.getSettleStage().equals("10")){
				settleBillParam.put("settleBillType", 1);
			}else if(conSett.getSettleStage().equals("20")){
				settleBillParam.put("settleBillType", 2);
			}
			settleBill = bizPmPreIndustrySettleBillService.queryPmPreIndustrySettleBillByParam(settleBillParam);
			settleBill.setContractAmount(conSett.getContractAmount());
			if(conSett.getSettleStage().equals("10")){
                settleBill = bizPmPreIndustrySettleBillService.midDataEncapsulation(settleBill);
			}else if(conSett.getSettleStage().equals("20")){
                settleBill = bizPmPreIndustrySettleBillService.completedDataEncapsulation(settleBill,conSett.getOrderId());
			}
			bizPmPreIndustrySettleBillService.save(settleBill);

			Map<String,Object> cateGorySummeryParam = new HashMap<String,Object>();
			cateGorySummeryParam.put("orderId", settleBill.getOrderId());
			cateGorySummeryParam.put("pmEmployeeId", settleBill.getPmEmployeeId());
			cateGorySummeryParam.put("pmSettleBillId", settleBill.getId());
			if(conSett.getSettleStage().equals("10")){
				cateGorySummeryParam.put("settleCategory", ConstantUtils.PM_SETTLE_CATEGORY_900);
			}else if(conSett.getSettleStage().equals("20")){
				cateGorySummeryParam.put("settleCategory", ConstantUtils.PM_SETTLE_CATEGORY_1000);
			}

			BizPmSettleCategorySummary bizPmSettleCategorySummary = bizPmSettleCategorySummaryService.queryCateGrrySummaryByParam(cateGorySummeryParam);
			if(bizPmSettleCategorySummary == null || bizPmSettleCategorySummary.getId() == null){
				bizPmSettleCategorySummary = new BizPmSettleCategorySummary();
				bizPmSettleCategorySummary.setOrderId(settleBill.getOrderId());
				bizPmSettleCategorySummary.setPmEmployeeId(settleBill.getPmEmployeeId());
				bizPmSettleCategorySummary.setSettleCategory(cateGorySummeryParam.get("settleCategory").toString());
				bizPmSettleCategorySummary.setSettleStatus(ConstantUtils.PM_SETTLE_STATUS_10);
				bizPmSettleCategorySummary.setSettleStatusDatetime(new Date());
				bizPmSettleCategorySummary.setSettleRole(ConstantUtils.SETTLE_ROLE_1);
				bizPmSettleCategorySummary.setPmSettleBillId(settleBill.getId());
			}
			bizPmSettleCategorySummary.setSettleAmount(conSett.getContractAmount());
			bizPmSettleCategorySummaryService.save(bizPmSettleCategorySummary);

			Map<String,Object> cateGoryDetailParam = new HashMap<String,Object>();
			cateGoryDetailParam.put("orderId", settleBill.getOrderId());
			cateGoryDetailParam.put("pmEmployeeId", settleBill.getPmEmployeeId());
			if(conSett.getSettleStage().equals("10")){
				cateGoryDetailParam.put("settleCategory", ConstantUtils.PM_SETTLE_CATEGORY_900);
			}else if(conSett.getSettleStage().equals("20")){
				cateGoryDetailParam.put("settleCategory", ConstantUtils.PM_SETTLE_CATEGORY_1000);
			}
			BizPmSettleCategoryDetail bizPmSettleCategoryDetail = bizPmSettleCategoryDetailService.querySettleCategoryDetailByParam(cateGoryDetailParam);
			if(bizPmSettleCategoryDetail ==null || bizPmSettleCategoryDetail.getId() == null){
				bizPmSettleCategoryDetail.setOrderId(settleBill.getOrderId());
				bizPmSettleCategoryDetail.setPmEmployeeId(settleBill.getPmEmployeeId());
				bizPmSettleCategoryDetail.setSettleStatus(ConstantUtils.PM_SETTLE_STATUS_10);
				bizPmSettleCategoryDetail.setSettleCategory(cateGoryDetailParam.get("settleCategory").toString());
				bizPmSettleCategoryDetail.setSettleStatusDatetime(new Date());
				bizPmSettleCategoryDetail.setRelatedBusinessId(conSett.getId());
				bizPmSettleCategoryDetail.setSettleRole(ConstantUtils.SETTLE_ROLE_1);
				bizPmSettleCategoryDetail.setPmSettleCategorySummaryId(bizPmSettleCategorySummary.getId());
			}
			bizPmSettleCategoryDetail.setSettleAmount(conSett.getContractAmount());
			bizPmSettleCategoryDetailService.save(bizPmSettleCategoryDetail);
		}
        String result= null;
		if(isEditSettleBill == 0){
			if(conSett.getSettleStage().equals("10")){

                if("0".equals(isNewSettleBill)){
                    result="redirect:" + Global.getAdminPath()
                            + "/proIndustryPmSettle/proIndustryPmSettle/openMidwaySettleInfoPage?orderId="
                            + contractSettle.getOrderId();
                }else {
                    result="redirect:" + Global.getAdminPath()
                            + "/newProIndustryPmSettle/openMidwaySettleInfoPage?orderId="
                            + contractSettle.getOrderId();
                }
			}else if(conSett.getSettleStage().equals("20")){
                if("0".equals(isNewSettleBill)){
                    result="redirect:" + Global.getAdminPath()
                            + "/proIndustryPmSettle/proIndustryPmSettle/openCompleteSettleInfoPage?orderId="
                            + contractSettle.getOrderId();
                }else {
                    result="redirect:" + Global.getAdminPath()
                            + "/newProIndustryPmSettle/openCompleteSettleInfoPage?orderId="
                            + contractSettle.getOrderId();
                }
			}
		}else if(isEditSettleBill == 1){
			BizBusinessStatusLog statusLog = new BizBusinessStatusLog();
			statusLog.setBusinessType(BusinessLogConstantUtil.PRE_MANAGER_SETTLE_TYPE_3600);
			statusLog.setBusinessOnlyMarkInt(settleBill.getId());
			statusLog.setBusinessStatus(settleBill.getStatus());
			statusLog.setStatusDatetime(new Date());
			String empId = UserUtils.getUser1().getEmpId();
			if (null != empId) {
				statusLog.setBusinessEmployeeId(Integer.parseInt(empId));
			}
			statusLog.setBusinessRemarks("修改结算单");
			bizBusinessStatusLogService.save(statusLog);

			result="redirect:" + Global.getAdminPath()
					+ "/bizPmPreIndustrySettleBill/bizPmPreIndustrySettleBill/editSettleBill?id="
					+ settleBill.getId();
		}

		return result;
	}

	@RequestMapping(value = "openBaseInstalled")
	public String openBaseInstalled(Integer orderId, String changeType,Integer isEditSettleBill, Model model, HttpServletRequest request,
									HttpServletResponse response,String isNewSettleBill) {
		Order2 order2 = orderService2.get(orderId);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("orderId", orderId);
		param.put("changeType", changeType);
		BizOrderChange bizOrderChange = bizOrderChangeService.queryOrderChangeByParam(param);
		if (bizOrderChange == null) {
			bizOrderChange = new BizOrderChange();
			bizOrderChange.setOrderId(orderId);
			bizOrderChange.setChangeType(changeType);
		} else if (bizOrderChange.getId() == null) {
			bizOrderChange.setOrderId(orderId);
			bizOrderChange.setChangeType(changeType);
		}
		model.addAttribute("bizOrderChange", bizOrderChange);
		model.addAttribute("order2", order2);
		model.addAttribute("isEditSettleBill",isEditSettleBill);
        model.addAttribute("isNewSettleBill",isNewSettleBill);
		String result = null;
		if (changeType.equals("10")) {
			result = "modules/proIndustryPmSettle/baseInstalled";
		} else if (changeType.equals("20")) {
			result = "modules/proIndustryPmSettle/midwayChangeAdd";
		} else if (changeType.equals("30")) {
			result = "modules/proIndustryPmSettle/midwayChangeReduce";
		} else if (changeType.equals("40")) {
			result = "modules/proIndustryPmSettle/completeChangeAdd";
		} else if (changeType.equals("50")) {
			result = "modules/proIndustryPmSettle/completeChangeReduce";
		}
		return result;
	}

	@RequestMapping(value = "saveBaseInstalled")
	public String saveBaseInstalled(BizOrderChange bizOrderChange,Integer isEditSettleBill, RedirectAttributes redirectAttributes, Model model,
									HttpServletRequest request, HttpServletResponse response,String isNewSettleBill) {
		BizOrderChange orderChange = null;

		Map<String,Object> param = new HashMap<String,Object>();
		param.put("orderId", bizOrderChange.getOrderId());
		param.put("changeType", bizOrderChange.getChangeType());
		orderChange = bizOrderChangeService.queryOrderChangeByParam(param);
		if (orderChange == null || orderChange.getId() == null) {
			orderChange = new BizOrderChange();
		}
		orderChange.setOrderId(bizOrderChange.getOrderId());
		orderChange.setChangeType(bizOrderChange.getChangeType());
		orderChange.setChangeAmount(bizOrderChange.getChangeAmount());
		orderChange.setChangeAccountRate(bizOrderChange.getChangeAccountRate());
		orderChange.setChangeAccountAmount(bizOrderChange.getChangeAccountAmount());
		orderChange.setChangeRemarks(bizOrderChange.getChangeRemarks());
		bizOrderChangeService.save(orderChange);
		BizPmPreIndustrySettleBill settleBill = new BizPmPreIndustrySettleBill();
		if(isEditSettleBill == 1){

			Map<String,Object> midwaySettleBillParam = new HashMap<String,Object>();
			midwaySettleBillParam.put("orderId", orderChange.getOrderId());
			if(orderChange.getChangeType().equals("10") || orderChange.getChangeType().equals("20") || orderChange.getChangeType().equals("30")){
				midwaySettleBillParam.put("settleBillType", 1);
			}else if(orderChange.getChangeType().equals("40") || orderChange.getChangeType().equals("50")){
				midwaySettleBillParam.put("settleBillType", 2);
			}
			settleBill = bizPmPreIndustrySettleBillService.queryPmPreIndustrySettleBillByParam(midwaySettleBillParam);
			if(orderChange.getChangeType().equals("10")){
				settleBill.setMidwayBasicworkAddAmount(bizOrderChange.getChangeAccountAmount());
			}else if(orderChange.getChangeType().equals("20") || orderChange.getChangeType().equals("40")){
				settleBill.setOrderChangeAddAmount(bizOrderChange.getChangeAccountAmount());
			}else if(orderChange.getChangeType().equals("30") || orderChange.getChangeType().equals("50")){
				if(bizOrderChange.getChangeAccountAmount() > 0){
					settleBill.setOrderChangeReduceAmount(0-bizOrderChange.getChangeAccountAmount());
				}else{
					settleBill.setOrderChangeReduceAmount(bizOrderChange.getChangeAccountAmount());
				}

			}
			if(settleBill.getSettleBillType().equals("1")){
                settleBill = bizPmPreIndustrySettleBillService.midDataEncapsulation(settleBill);
			}else if(settleBill.getSettleBillType().equals("2")){
                settleBill = bizPmPreIndustrySettleBillService.completedDataEncapsulation(settleBill,bizOrderChange.getOrderId());
			}


			bizPmPreIndustrySettleBillService.save(settleBill);

			Map<String,Object> cateGorySummeryParam = new HashMap<String,Object>();
			cateGorySummeryParam.put("orderId", settleBill.getOrderId());
			cateGorySummeryParam.put("pmEmployeeId", settleBill.getPmEmployeeId());
			cateGorySummeryParam.put("pmSettleBillId", settleBill.getId());
			if(orderChange.getChangeType().equals("10")){
				cateGorySummeryParam.put("settleCategory", ConstantUtils.PM_SETTLE_CATEGORY_901);
			}else if(orderChange.getChangeType().equals("20")){
				cateGorySummeryParam.put("settleCategory", ConstantUtils.PM_SETTLE_CATEGORY_910);
			}else if(orderChange.getChangeType().equals("30")){
				cateGorySummeryParam.put("settleCategory", ConstantUtils.PM_SETTLE_CATEGORY_911);
			}else if(orderChange.getChangeType().equals("40")){
				cateGorySummeryParam.put("settleCategory", ConstantUtils.PM_SETTLE_CATEGORY_1004);
			}else if(orderChange.getChangeType().equals("50")){
				cateGorySummeryParam.put("settleCategory", ConstantUtils.PM_SETTLE_CATEGORY_1005);
			}

			BizPmSettleCategorySummary bizPmSettleCategorySummary = bizPmSettleCategorySummaryService.queryCateGrrySummaryByParam(cateGorySummeryParam);
			if(bizPmSettleCategorySummary == null || bizPmSettleCategorySummary.getId() == null){
				bizPmSettleCategorySummary = new BizPmSettleCategorySummary();
				bizPmSettleCategorySummary.setOrderId(settleBill.getOrderId());
				bizPmSettleCategorySummary.setPmEmployeeId(settleBill.getPmEmployeeId());
				bizPmSettleCategorySummary.setSettleCategory(cateGorySummeryParam.get("settleCategory").toString());
				bizPmSettleCategorySummary.setSettleStatus(ConstantUtils.PM_SETTLE_STATUS_10);
				bizPmSettleCategorySummary.setSettleStatusDatetime(new Date());
				bizPmSettleCategorySummary.setSettleRole(ConstantUtils.SETTLE_ROLE_1);
				bizPmSettleCategorySummary.setPmSettleBillId(settleBill.getId());
			}
			if(orderChange.getChangeType().equals("10") || orderChange.getChangeType().equals("20") || orderChange.getChangeType().equals("40")){
				bizPmSettleCategorySummary.setSettleAmount(bizOrderChange.getChangeAccountAmount());
			}else if(orderChange.getChangeType().equals("30") || orderChange.getChangeType().equals("50")){
				if(bizOrderChange.getChangeAccountAmount() > 0){
					bizPmSettleCategorySummary.setSettleAmount(0-bizOrderChange.getChangeAccountAmount());
				}else{
					bizPmSettleCategorySummary.setSettleAmount(bizOrderChange.getChangeAccountAmount());
				}

			}

			bizPmSettleCategorySummaryService.save(bizPmSettleCategorySummary);

			Map<String,Object> cateGoryDetailParam = new HashMap<String,Object>();
			cateGoryDetailParam.put("orderId", settleBill.getOrderId());
			cateGoryDetailParam.put("pmEmployeeId", settleBill.getPmEmployeeId());
			if(orderChange.getChangeType().equals("10")){
				cateGoryDetailParam.put("settleCategory", ConstantUtils.PM_SETTLE_CATEGORY_901);
			}else if(orderChange.getChangeType().equals("20")){
				cateGoryDetailParam.put("settleCategory", ConstantUtils.PM_SETTLE_CATEGORY_910);
			}else if(orderChange.getChangeType().equals("30")){
				cateGoryDetailParam.put("settleCategory", ConstantUtils.PM_SETTLE_CATEGORY_911);
			}else if(orderChange.getChangeType().equals("40")){
				cateGoryDetailParam.put("settleCategory", ConstantUtils.PM_SETTLE_CATEGORY_1004);
			}else if(orderChange.getChangeType().equals("50")){
				cateGoryDetailParam.put("settleCategory", ConstantUtils.PM_SETTLE_CATEGORY_1005);
			}
			BizPmSettleCategoryDetail bizPmSettleCategoryDetail = bizPmSettleCategoryDetailService.querySettleCategoryDetailByParam(cateGoryDetailParam);
			if(bizPmSettleCategoryDetail ==null || bizPmSettleCategoryDetail.getId() == null){
				bizPmSettleCategoryDetail = new BizPmSettleCategoryDetail();
				bizPmSettleCategoryDetail.setOrderId(settleBill.getOrderId());
				bizPmSettleCategoryDetail.setPmEmployeeId(settleBill.getPmEmployeeId());
				bizPmSettleCategoryDetail.setSettleStatus(ConstantUtils.PM_SETTLE_STATUS_10);
				bizPmSettleCategoryDetail.setSettleCategory(cateGoryDetailParam.get("settleCategory").toString());
				bizPmSettleCategoryDetail.setSettleStatusDatetime(new Date());
				bizPmSettleCategoryDetail.setRelatedBusinessId(orderChange.getId());
				bizPmSettleCategoryDetail.setSettleRole(ConstantUtils.SETTLE_ROLE_1);
				bizPmSettleCategoryDetail.setPmSettleCategorySummaryId(bizPmSettleCategorySummary.getId());
			}
			if(orderChange.getChangeType().equals("10") || orderChange.getChangeType().equals("20") || orderChange.getChangeType().equals("40")){
				bizPmSettleCategoryDetail.setSettleAmount(bizOrderChange.getChangeAccountAmount());
			}else if(orderChange.getChangeType().equals("30") || orderChange.getChangeType().equals("50")){
				if(bizOrderChange.getChangeAccountAmount() > 0){
					bizPmSettleCategoryDetail.setSettleAmount(0-bizOrderChange.getChangeAccountAmount());
				}else{
					bizPmSettleCategoryDetail.setSettleAmount(bizOrderChange.getChangeAccountAmount());
				}

			}
			bizPmSettleCategoryDetailService.save(bizPmSettleCategoryDetail);
		}
		String result = null;
		if(isEditSettleBill == 0){
			if(orderChange.getChangeType().equals("10") || orderChange.getChangeType().equals("20") || orderChange.getChangeType().equals("30")){

				if("0".equals(isNewSettleBill)){
                    result="redirect:" + Global.getAdminPath()
                            + "/proIndustryPmSettle/proIndustryPmSettle/openMidwaySettleInfoPage?orderId="
                            + bizOrderChange.getOrderId();
                }else{
                    result="redirect:" + Global.getAdminPath()
                            + "/newProIndustryPmSettle/openMidwaySettleInfoPage?orderId="
                            + bizOrderChange.getOrderId();
                }

			}else if(orderChange.getChangeType().equals("40") || orderChange.getChangeType().equals("50") ){

                if("0".equals(isNewSettleBill)){
                    result="redirect:" + Global.getAdminPath()
                            + "/proIndustryPmSettle/proIndustryPmSettle/openCompleteSettleInfoPage?orderId="
                            + bizOrderChange.getOrderId();
                }else {
                    result="redirect:" + Global.getAdminPath()
                            + "/newProIndustryPmSettle/openCompleteSettleInfoPage?orderId="
                            + bizOrderChange.getOrderId();
                }
			}
		}else if(isEditSettleBill == 1){
			BizBusinessStatusLog statusLog = new BizBusinessStatusLog();
			statusLog.setBusinessType(BusinessLogConstantUtil.PRE_MANAGER_SETTLE_TYPE_3600);
			statusLog.setBusinessOnlyMarkInt(settleBill.getId());
			statusLog.setBusinessStatus(settleBill.getStatus());
			statusLog.setStatusDatetime(new Date());
			String empId = UserUtils.getUser1().getEmpId();
			if (null != empId) {
				statusLog.setBusinessEmployeeId(Integer.parseInt(empId));
			}
			statusLog.setBusinessRemarks("修改结算单");
			bizBusinessStatusLogService.save(statusLog);

			result="redirect:" + Global.getAdminPath()
					+ "/bizPmPreIndustrySettleBill/bizPmPreIndustrySettleBill/editSettleBill?id="
					+ settleBill.getId();
			result="redirect:" + Global.getAdminPath()
					+ "/bizPmPreIndustrySettleBill/bizPmPreIndustrySettleBill/editSettleBill?id="
					+ settleBill.getId();
		}

		return result;
	}

	@RequestMapping(value = "openAuxiliaryMaInfo")
	public String openAuxiliaryMaInfo(Integer orderId, Integer isSandCement, Model model, HttpServletRequest request,
									  HttpServletResponse response) {
		Map<String, Object> auMaterParam = new HashMap<String, Object>();
		auMaterParam.put("orderId", orderId);
		auMaterParam.put("isSandCement", isSandCement);
		Double auMaterAmount = proIndustryPmSettleService.queryAuxiliaryMaterialsAmountByOrderId(auMaterParam);
		if (auMaterAmount == null) {
			auMaterAmount = 0.00;
		}
		List<SettlementAuxiliary> auxiliaryList = proIndustryPmSettleService.queryAuxiliaryInfoByParam(auMaterParam);
		Order2 order2 = orderService2.get(orderId);
		model.addAttribute("auxiliaryList", auxiliaryList);
		model.addAttribute("auMaterAmount", auMaterAmount);
		model.addAttribute("order2", order2);
		return "modules/proIndustryPmSettle/auxiliaryMaInfo";
	}

	@RequestMapping(value = "openMaterialsStandardInfo")
	public String openMaterialsStandardInfo(Integer orderId, Model model, HttpServletRequest request,
											HttpServletResponse response) {
		List<BizOrderMaterialsStandard> materialsStandardList = inspectorConfirmService
				.queryMaterialsStandardByOrderId(orderId);
		BizMaterialsStandardReceiveBill details3 = new BizMaterialsStandardReceiveBill();
		details3.setReceiveBillAmount(0.0);
		if (materialsStandardList != null && materialsStandardList.size() > 0) {
			for (BizOrderMaterialsStandard materialsStandard : materialsStandardList) {
				details3.setReceiveBillAmount(details3.getReceiveBillAmount() + materialsStandard.getMaterialsAmount());
			}
		}
		Order2 order2 = orderService2.get(orderId);
		model.addAttribute("materialsStandardList", materialsStandardList);
		model.addAttribute("details3", details3);
		model.addAttribute("order2", order2);
		return "modules/proIndustryPmSettle/materialsStandardInfo";
	}

	@RequestMapping(value = "openMainPanelInfo")
	public String openMainPanelInfo(Integer orderId, Model model, HttpServletRequest request,
									HttpServletResponse response) {
		Double mainPanelAmount = proIndustryPmSettleService.queryMainPanelAmountByOrderId(orderId);
		if (mainPanelAmount == null) {
			mainPanelAmount = 0.00;
		}
		List<MainPanel> mainPanelList = proIndustryPmSettleService.queryMainPanelListByOrderId(orderId);
		Order2 order2 = orderService2.get(orderId);
		model.addAttribute("mainPanelList", mainPanelList);
		model.addAttribute("mainPanelAmount", mainPanelAmount);
		model.addAttribute("order2", order2);
		return "modules/proIndustryPmSettle/mainPanelInfo";
	}

	@RequestMapping(value = "openPmQcFineInfo")
	public String openPmQcFineInfo(Integer orderId,Integer settleBillId, Model model, HttpServletRequest request,
								   HttpServletResponse response) {
		Order2 order2 = orderService2.get(orderId);
		Map<String, Object> pmQcParam = new HashMap<String, Object>();
		pmQcParam.put("orderId", orderId);
		pmQcParam.put("pmEmployeeId", order2.getItemManagerId());
		List<InspectorPunish> inspectorPunishList=null;
		if(settleBillId != null){
			pmQcParam.put("pmSettleBillId", settleBillId);
			pmQcParam.put("settleCategory", 4);
			BizPmSettleCategorySummary summary=bizPmSettleCategorySummaryService.queryCateGrrySummaryByParam(pmQcParam);
			if(summary != null){
				pmQcParam.put("summaryId", summary.getId());
				pmQcParam.put("settleStatus",summary.getSettleStatus());
			}

		}else{
			pmQcParam.put("settleStatus",10);
		}

		Double pmQcFine = proIndustryPmSettleService.queryPmQcFineByParam(pmQcParam);
		if (pmQcFine == null) {
			pmQcFine = 0.00;
		}
		inspectorPunishList = proIndustryPmSettleService.queryPmQcFineInfoByParam(pmQcParam);
		model.addAttribute("pmQcFine", pmQcFine);
		model.addAttribute("inspectorPunishList", inspectorPunishList);
		model.addAttribute("order2", order2);
		return "modules/proIndustryPmSettle/pmQcFineInfo";
	}

	@RequestMapping(value = "openOrderMaterialCarryCost")
	public String openOrderMaterialCarryCost(Integer orderId,Integer isEditSettleBill, Model model, HttpServletRequest request,
											 HttpServletResponse response,String isNewSettleBill) {
		BizOrderMaterialCarryCost bizOrderMaterialCarryCost = bizOrderMaterialCarryCostService
				.queryOrderMaterialCarryCostByOrderId(orderId);
		if (bizOrderMaterialCarryCost == null) {
			bizOrderMaterialCarryCost = new BizOrderMaterialCarryCost();
			bizOrderMaterialCarryCost.setOrderId(orderId);
		} else if (bizOrderMaterialCarryCost.getId() == null) {
			bizOrderMaterialCarryCost.setOrderId(orderId);
		}
		Order2 order2 = orderService2.get(orderId);
		model.addAttribute("bizOrderMaterialCarryCost", bizOrderMaterialCarryCost);
		model.addAttribute("order2", order2);
		model.addAttribute("isEditSettleBill", isEditSettleBill);
		model.addAttribute("isNewSettleBill",isNewSettleBill);
		return "modules/proIndustryPmSettle/orderMaterialCarryCostInfo";
	}

	@RequestMapping(value = "saveOrderMaterialCarryCost")
	public String saveOrderMaterialCarryCost(BizOrderMaterialCarryCost bizOrderMaterialCarryCost,Integer isEditSettleBill, Model model,
											 HttpServletRequest request, HttpServletResponse response,String isNewSettleBill) {
		BizOrderMaterialCarryCost orderMaterialCarryCost =null;

		orderMaterialCarryCost = bizOrderMaterialCarryCostService
				.queryOrderMaterialCarryCostByOrderId(bizOrderMaterialCarryCost.getOrderId());
		if (orderMaterialCarryCost == null) {
			orderMaterialCarryCost = new BizOrderMaterialCarryCost();
		}
		orderMaterialCarryCost.setOrderId(bizOrderMaterialCarryCost.getOrderId());
		orderMaterialCarryCost.setCarryCostAmount(bizOrderMaterialCarryCost.getCarryCostAmount());
		orderMaterialCarryCost.setAccountAmount(bizOrderMaterialCarryCost.getAccountAmount());
		orderMaterialCarryCost.setAccountRate(bizOrderMaterialCarryCost.getAccountRate());
		orderMaterialCarryCost.setCarryCostRemarks(bizOrderMaterialCarryCost.getCarryCostRemarks());
		bizOrderMaterialCarryCostService.save(orderMaterialCarryCost);
		BizPmPreIndustrySettleBill settleBill = new BizPmPreIndustrySettleBill();
		if(isEditSettleBill == 1){

			Map<String,Object> midwaySettleBillParam = new HashMap<String,Object>();
			midwaySettleBillParam.put("orderId", orderMaterialCarryCost.getOrderId());
			midwaySettleBillParam.put("settleBillType", 1);
			settleBill = bizPmPreIndustrySettleBillService.queryPmPreIndustrySettleBillByParam(midwaySettleBillParam);
			settleBill.setMidwayMaterialCarryCostAmount(bizOrderMaterialCarryCost.getAccountAmount());

            settleBill = bizPmPreIndustrySettleBillService.midDataEncapsulation(settleBill);

			bizPmPreIndustrySettleBillService.save(settleBill);

			Map<String,Object> cateGorySummeryParam = new HashMap<String,Object>();
			cateGorySummeryParam.put("orderId", settleBill.getOrderId());
			cateGorySummeryParam.put("pmEmployeeId", settleBill.getPmEmployeeId());
			cateGorySummeryParam.put("pmSettleBillId", settleBill.getId());
			cateGorySummeryParam.put("settleCategory", ConstantUtils.PM_SETTLE_CATEGORY_909);
			BizPmSettleCategorySummary bizPmSettleCategorySummary = bizPmSettleCategorySummaryService.queryCateGrrySummaryByParam(cateGorySummeryParam);
			if(bizPmSettleCategorySummary == null || bizPmSettleCategorySummary.getId() == null){
				bizPmSettleCategorySummary = new BizPmSettleCategorySummary();
				bizPmSettleCategorySummary.setOrderId(settleBill.getOrderId());
				bizPmSettleCategorySummary.setPmEmployeeId(settleBill.getPmEmployeeId());
				bizPmSettleCategorySummary.setSettleCategory(cateGorySummeryParam.get("settleCategory").toString());
				bizPmSettleCategorySummary.setSettleStatus(ConstantUtils.PM_SETTLE_STATUS_10);
				bizPmSettleCategorySummary.setSettleStatusDatetime(new Date());
				bizPmSettleCategorySummary.setSettleRole(ConstantUtils.SETTLE_ROLE_1);
				bizPmSettleCategorySummary.setPmSettleBillId(settleBill.getId());
			}
			bizPmSettleCategorySummary.setSettleAmount(bizOrderMaterialCarryCost.getAccountAmount());
			bizPmSettleCategorySummaryService.save(bizPmSettleCategorySummary);

			Map<String,Object> cateGoryDetailParam = new HashMap<String,Object>();
			cateGoryDetailParam.put("orderId", settleBill.getOrderId());
			cateGoryDetailParam.put("pmEmployeeId", settleBill.getPmEmployeeId());
			cateGoryDetailParam.put("settleCategory", ConstantUtils.PM_SETTLE_CATEGORY_909);
			BizPmSettleCategoryDetail bizPmSettleCategoryDetail = bizPmSettleCategoryDetailService.querySettleCategoryDetailByParam(cateGoryDetailParam);
			if(bizPmSettleCategoryDetail ==null || bizPmSettleCategoryDetail.getId() == null){
				bizPmSettleCategoryDetail = new BizPmSettleCategoryDetail();
				bizPmSettleCategoryDetail.setOrderId(settleBill.getOrderId());
				bizPmSettleCategoryDetail.setPmEmployeeId(settleBill.getPmEmployeeId());
				bizPmSettleCategoryDetail.setSettleStatus(ConstantUtils.PM_SETTLE_STATUS_10);
				bizPmSettleCategoryDetail.setSettleCategory(cateGoryDetailParam.get("settleCategory").toString());
				bizPmSettleCategoryDetail.setSettleStatusDatetime(new Date());
				bizPmSettleCategoryDetail.setRelatedBusinessId(orderMaterialCarryCost.getId());
				bizPmSettleCategoryDetail.setSettleRole(ConstantUtils.SETTLE_ROLE_1);
				bizPmSettleCategoryDetail.setPmSettleCategorySummaryId(bizPmSettleCategorySummary.getId());
			}
			bizPmSettleCategoryDetail.setSettleAmount(bizOrderMaterialCarryCost.getAccountAmount());
			bizPmSettleCategoryDetailService.save(bizPmSettleCategoryDetail);
		}
		String result= null;
		if(isEditSettleBill == 0){
		    if("0".equals(isNewSettleBill)){
                result = "redirect:" + Global.getAdminPath()
                        + "/proIndustryPmSettle/proIndustryPmSettle/openMidwaySettleInfoPage?orderId="
                        + bizOrderMaterialCarryCost.getOrderId();
            }else {
                result = "redirect:" + Global.getAdminPath()
                        + "/newProIndustryPmSettle/openMidwaySettleInfoPage?orderId="
                        + bizOrderMaterialCarryCost.getOrderId();
            }

		}else if(isEditSettleBill == 1){
			BizBusinessStatusLog statusLog = new BizBusinessStatusLog();
			statusLog.setBusinessType(BusinessLogConstantUtil.PRE_MANAGER_SETTLE_TYPE_3600);
			statusLog.setBusinessOnlyMarkInt(settleBill.getId());
			statusLog.setBusinessStatus(settleBill.getStatus());
			statusLog.setStatusDatetime(new Date());
			String empId = UserUtils.getUser1().getEmpId();
			if (null != empId) {
				statusLog.setBusinessEmployeeId(Integer.parseInt(empId));
			}
			statusLog.setBusinessRemarks("修改结算单");
			bizBusinessStatusLogService.save(statusLog);

			result="redirect:" + Global.getAdminPath()
					+ "/bizPmPreIndustrySettleBill/bizPmPreIndustrySettleBill/editSettleBill?id="
					+ settleBill.getId();

		}
		return result;
	}


	@RequestMapping(value = "openPmRewardPunish")
	public String openPmRewardPunish(Integer orderId, String rewardPunishType,Integer isEditSettleBill, String relatedBusinessType, Model model,
									 HttpServletRequest request, HttpServletResponse response,String isNewSettleBill) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("orderId", orderId);
		param.put("employeeType", 2);
		param.put("relatedBusinessType", relatedBusinessType);
		param.put("rewardPunishType", rewardPunishType);
		BizBusinessRewardPunish bizBusinessRewardPunish = bizBusinessRewardPunishService
				.queryBusinessRewardPunishByParam(param);
		if (bizBusinessRewardPunish == null || bizBusinessRewardPunish.getId() == null) {
			bizBusinessRewardPunish = new BizBusinessRewardPunish();
			bizBusinessRewardPunish.setRelatedBusinessIdInt(orderId);
			bizBusinessRewardPunish.setRelatedBusinessType(relatedBusinessType);
			bizBusinessRewardPunish.setRewardPunishType(rewardPunishType);
		}
		Order2 order2 = orderService2.get(orderId);
		model.addAttribute("bizBusinessRewardPunish", bizBusinessRewardPunish);
		model.addAttribute("order2", order2);
		model.addAttribute("isEditSettleBill",isEditSettleBill);
        model.addAttribute("isNewSettleBill",isNewSettleBill);
		String result = null;
		if (rewardPunishType.equals("1")) {
			if (relatedBusinessType.equals("1")) {
				result = "modules/proIndustryPmSettle/midwayRewardAmount";
			} else if (relatedBusinessType.equals("2")) {
				result = "modules/proIndustryPmSettle/completeRewardAmount";
			}
		} else if (rewardPunishType.equals("2")) {
			if (relatedBusinessType.equals("1")) {
				result = "modules/proIndustryPmSettle/midwayPunishAmount";
			} else if (relatedBusinessType.equals("2")) {
				result = "modules/proIndustryPmSettle/completePunishAmount";
			}
		}
		return result;
	}


	@RequestMapping(value = "savePmRewardPunish")
	public String savePmRewardPunish(BizBusinessRewardPunish bizBusinessRewardPunish,Integer isEditSettleBill, Model model,
									 HttpServletRequest request, HttpServletResponse response,String isNewSettleBill) {
		BizBusinessRewardPunish businessRewardPunish = null;

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("orderId", bizBusinessRewardPunish.getRelatedBusinessIdInt());
		param.put("employeeType", 2);
		param.put("relatedBusinessType", bizBusinessRewardPunish.getRelatedBusinessType());
		param.put("rewardPunishType", bizBusinessRewardPunish.getRewardPunishType());
		businessRewardPunish = bizBusinessRewardPunishService
				.queryBusinessRewardPunishByParam(param);
		if (businessRewardPunish == null) {
			businessRewardPunish = new BizBusinessRewardPunish();
			businessRewardPunish.setRewardPunishStatus("1");
			businessRewardPunish.setStatusDatetime(new Date());
		}
		businessRewardPunish.setRelatedBusinessIdInt(bizBusinessRewardPunish.getRelatedBusinessIdInt());
		businessRewardPunish.setRewardPunishType(bizBusinessRewardPunish.getRewardPunishType());
		businessRewardPunish.setEmployeeId(bizBusinessRewardPunish.getEmployeeId());
		businessRewardPunish.setEmployeeType("2");
		businessRewardPunish.setRelatedBusinessType(bizBusinessRewardPunish.getRelatedBusinessType());
		businessRewardPunish.setRewardPunishAmount(bizBusinessRewardPunish.getRewardPunishAmount());
		businessRewardPunish.setRewardPunishRemarks(bizBusinessRewardPunish.getRewardPunishRemarks());
		businessRewardPunish.setRewardPunishDatetime(new Date());
		bizBusinessRewardPunishService.save(businessRewardPunish);
		BizPmPreIndustrySettleBill settleBill = new BizPmPreIndustrySettleBill();
        Map<String,Object> midwaySettleBillParam = new HashMap<String,Object>();
        midwaySettleBillParam.put("orderId", businessRewardPunish.getRelatedBusinessIdInt());
        midwaySettleBillParam.put("settleBillType", businessRewardPunish.getRelatedBusinessType());
        settleBill = bizPmPreIndustrySettleBillService.queryPmPreIndustrySettleBillByParam(midwaySettleBillParam);
		if(isEditSettleBill == 1){


			if(businessRewardPunish.getRewardPunishType().equals("1")){
				settleBill.setRewardAmount(bizBusinessRewardPunish.getRewardPunishAmount());
			}else if(businessRewardPunish.getRewardPunishType().equals("2")){
				if(bizBusinessRewardPunish.getRewardPunishAmount()>0){
					settleBill.setPunishAmount(0-bizBusinessRewardPunish.getRewardPunishAmount());
				}else{
					settleBill.setPunishAmount(bizBusinessRewardPunish.getRewardPunishAmount());
				}
			}
			if(settleBill.getSettleBillType().equals("1")){
                settleBill = bizPmPreIndustrySettleBillService.midDataEncapsulation(settleBill);
			}else if(settleBill.getSettleBillType().equals("2")){
                settleBill = bizPmPreIndustrySettleBillService.completedDataEncapsulation(settleBill,bizBusinessRewardPunish.getRelatedBusinessIdInt());
			}

			bizPmPreIndustrySettleBillService.save(settleBill);

			Map<String,Object> cateGorySummeryParam = new HashMap<String,Object>();
			cateGorySummeryParam.put("orderId", settleBill.getOrderId());
			cateGorySummeryParam.put("pmEmployeeId", settleBill.getPmEmployeeId());
			cateGorySummeryParam.put("pmSettleBillId", settleBill.getId());
			if(businessRewardPunish.getRewardPunishType().equals("1")){
				if(businessRewardPunish.getRelatedBusinessType().equals("1")){
					cateGorySummeryParam.put("settleCategory", ConstantUtils.PM_SETTLE_CATEGORY_907);
				}else if(businessRewardPunish.getRelatedBusinessType().equals("2")){
					cateGorySummeryParam.put("settleCategory", ConstantUtils.PM_SETTLE_CATEGORY_1002);
				}
			}else if(businessRewardPunish.getRewardPunishType().equals("2")){
				if(businessRewardPunish.getRelatedBusinessType().equals("1")){
					cateGorySummeryParam.put("settleCategory", ConstantUtils.PM_SETTLE_CATEGORY_908);
				}else if(businessRewardPunish.getRelatedBusinessType().equals("2")){
					cateGorySummeryParam.put("settleCategory", ConstantUtils.PM_SETTLE_CATEGORY_1003);
				}
			}

			BizPmSettleCategorySummary bizPmSettleCategorySummary = bizPmSettleCategorySummaryService.queryCateGrrySummaryByParam(cateGorySummeryParam);
			if(bizPmSettleCategorySummary == null || bizPmSettleCategorySummary.getId() == null){
				bizPmSettleCategorySummary = new BizPmSettleCategorySummary();
				bizPmSettleCategorySummary.setOrderId(settleBill.getOrderId());
				bizPmSettleCategorySummary.setPmEmployeeId(settleBill.getPmEmployeeId());
				bizPmSettleCategorySummary.setSettleCategory(cateGorySummeryParam.get("settleCategory").toString());
				bizPmSettleCategorySummary.setSettleStatus(ConstantUtils.PM_SETTLE_STATUS_10);
				bizPmSettleCategorySummary.setSettleStatusDatetime(new Date());
				bizPmSettleCategorySummary.setSettleRole(ConstantUtils.SETTLE_ROLE_1);
				bizPmSettleCategorySummary.setPmSettleBillId(settleBill.getId());
			}
			if(businessRewardPunish.getRewardPunishType().equals("1")){
				bizPmSettleCategorySummary.setSettleAmount(bizBusinessRewardPunish.getRewardPunishAmount());
			}else if(businessRewardPunish.getRewardPunishType().equals("2")){
				if(bizBusinessRewardPunish.getRewardPunishAmount()>0){
					bizPmSettleCategorySummary.setSettleAmount(0-bizBusinessRewardPunish.getRewardPunishAmount());
				}else{
					bizPmSettleCategorySummary.setSettleAmount(bizBusinessRewardPunish.getRewardPunishAmount());
				}

			}

			bizPmSettleCategorySummaryService.save(bizPmSettleCategorySummary);

			Map<String,Object> cateGoryDetailParam = new HashMap<String,Object>();
			cateGoryDetailParam.put("orderId", settleBill.getOrderId());
			cateGoryDetailParam.put("pmEmployeeId", settleBill.getPmEmployeeId());
			if(businessRewardPunish.getRewardPunishType().equals("1")){
				if(businessRewardPunish.getRelatedBusinessType().equals("1")){
					cateGoryDetailParam.put("settleCategory", ConstantUtils.PM_SETTLE_CATEGORY_907);
				}else if(businessRewardPunish.getRelatedBusinessType().equals("2")){
					cateGoryDetailParam.put("settleCategory", ConstantUtils.PM_SETTLE_CATEGORY_1002);
				}
			}else if(businessRewardPunish.getRewardPunishType().equals("2")){
				if(businessRewardPunish.getRelatedBusinessType().equals("1")){
					cateGoryDetailParam.put("settleCategory", ConstantUtils.PM_SETTLE_CATEGORY_908);
				}else if(businessRewardPunish.getRelatedBusinessType().equals("2")){
					cateGoryDetailParam.put("settleCategory", ConstantUtils.PM_SETTLE_CATEGORY_1003);
				}
			}

			BizPmSettleCategoryDetail bizPmSettleCategoryDetail = bizPmSettleCategoryDetailService.querySettleCategoryDetailByParam(cateGoryDetailParam);
			if(bizPmSettleCategoryDetail ==null || bizPmSettleCategoryDetail.getId() == null){
				bizPmSettleCategoryDetail = new BizPmSettleCategoryDetail();
				bizPmSettleCategoryDetail.setOrderId(settleBill.getOrderId());
				bizPmSettleCategoryDetail.setPmEmployeeId(settleBill.getPmEmployeeId());
				bizPmSettleCategoryDetail.setSettleStatus(ConstantUtils.PM_SETTLE_STATUS_10);
				bizPmSettleCategoryDetail.setSettleCategory(cateGoryDetailParam.get("settleCategory").toString());
				bizPmSettleCategoryDetail.setSettleStatusDatetime(new Date());
				bizPmSettleCategoryDetail.setRelatedBusinessId(businessRewardPunish.getId());
				bizPmSettleCategoryDetail.setSettleRole(ConstantUtils.SETTLE_ROLE_1);
				bizPmSettleCategoryDetail.setPmSettleCategorySummaryId(bizPmSettleCategorySummary.getId());
			}
			if(businessRewardPunish.getRewardPunishType().equals("1")){
				bizPmSettleCategoryDetail.setSettleAmount(bizBusinessRewardPunish.getRewardPunishAmount());
			}else if(businessRewardPunish.getRewardPunishType().equals("2")){
				if(bizBusinessRewardPunish.getRewardPunishAmount()>0){
					bizPmSettleCategoryDetail.setSettleAmount(0-bizBusinessRewardPunish.getRewardPunishAmount());
				}else{
					bizPmSettleCategoryDetail.setSettleAmount(bizBusinessRewardPunish.getRewardPunishAmount());
				}

			}
			bizPmSettleCategoryDetailService.save(bizPmSettleCategoryDetail);
		}
		String result=null;

		if(isEditSettleBill == 0){
			if(businessRewardPunish.getRelatedBusinessType().equals("1")){
                if("0".equals(isNewSettleBill)){
                    result="redirect:" + Global.getAdminPath()
                            + "/proIndustryPmSettle/proIndustryPmSettle/openMidwaySettleInfoPage?orderId="
                            + bizBusinessRewardPunish.getRelatedBusinessIdInt();
                }else {
                    result="redirect:" + Global.getAdminPath()
                            + "/newProIndustryPmSettle/openMidwaySettleInfoPage?orderId="
                            + bizBusinessRewardPunish.getRelatedBusinessIdInt();
                }

			}else if(businessRewardPunish.getRelatedBusinessType().equals("2")){

                midwaySettleBillParam.put("orderId", businessRewardPunish.getRelatedBusinessIdInt());
                midwaySettleBillParam.put("settleBillType", "1");
                BizPmPreIndustrySettleBill settleBillComplete = bizPmPreIndustrySettleBillService.queryPmPreIndustrySettleBillByParam(midwaySettleBillParam);
                String isNewSettleBillComplete = settleBillComplete.getIsNewSettleBill();
                if("0".equals(isNewSettleBillComplete)){
                    result="redirect:" + Global.getAdminPath()
                            + "/proIndustryPmSettle/proIndustryPmSettle/openCompleteSettleInfoPage?orderId="
                            + bizBusinessRewardPunish.getRelatedBusinessIdInt();
                }else {
                    result="redirect:" + Global.getAdminPath()
                            + "/newProIndustryPmSettle/openCompleteSettleInfoPage?orderId="
                            + bizBusinessRewardPunish.getRelatedBusinessIdInt();
                }

			}
		}else if(isEditSettleBill == 1){
			BizBusinessStatusLog statusLog = new BizBusinessStatusLog();
			statusLog.setBusinessType(BusinessLogConstantUtil.PRE_MANAGER_SETTLE_TYPE_3600);
			statusLog.setBusinessOnlyMarkInt(settleBill.getId());
			statusLog.setBusinessStatus(settleBill.getStatus());
			statusLog.setStatusDatetime(new Date());
			String empId = UserUtils.getUser1().getEmpId();
			if (null != empId) {
				statusLog.setBusinessEmployeeId(Integer.parseInt(empId));
			}
			statusLog.setBusinessRemarks("修改结算单");
			bizBusinessStatusLogService.save(statusLog);

			result="redirect:" + Global.getAdminPath()
					+ "/bizPmPreIndustrySettleBill/bizPmPreIndustrySettleBill/editSettleBill?id="
					+ settleBill.getId();
			result="redirect:" + Global.getAdminPath()
					+ "/bizPmPreIndustrySettleBill/bizPmPreIndustrySettleBill/editSettleBill?id="
					+ settleBill.getId();
		}
		return result;
	}


	@RequestMapping(value = "openPmGuaranteeMoney")
	public String openPmGuaranteeMoney(Integer orderId, Model model,
									   HttpServletRequest request, HttpServletResponse response){
		Order2 order2 = orderService2.get(orderId);
		BizPmGuaranteeMoneyCnfgSnap gmcs = bizPmGuaranteeMoneyCnfgSnapService.findGmc(orderId);
		Map<String,Object> logParam = new HashMap<String,Object>();
		logParam.put("orderId", orderId);
		logParam.put("pmEmPloyeeId", gmcs.getPmEmployeeId());
		BizPmGuaranteeMoneyLog moneyLog = bizPmGuaranteeMoneyLogService.findByParam(logParam);
		model.addAttribute("gmcs",gmcs);
		model.addAttribute("moneyLog",moneyLog);
		model.addAttribute("order2",order2);
		return "modules/proIndustryPmSettle/pmGuaranteeMoney";
	}


	@RequestMapping(value = "openCommissionLog")
	public String openCommissionLog(BizQcLongwayCommissionLog bizQcLongwayCommissionLog,Integer isEditSettleBill,Model model,
									HttpServletRequest request, HttpServletResponse response){
		Order2 order2 = orderService2.get(bizQcLongwayCommissionLog.getOrderId());
		BizQcLongwayCommissionLog commissionLog = bizQcLongwayCommissionLogService.queryCommissionLogByParam(bizQcLongwayCommissionLog);
		model.addAttribute("order2",order2);
		model.addAttribute("commissionLog",commissionLog);
		model.addAttribute("isEditSettleBill",isEditSettleBill);
		return "modules/proIndustryPmSettle/commissionLog";
	}


	@RequestMapping(value = "saveCommissionLog")
	public String saveCommissionLog(BizQcLongwayCommissionLog bizQcLongwayCommissionLog,Integer isEditSettleBill,Model model,
									HttpServletRequest request, HttpServletResponse response){
		BizQcLongwayCommissionLog commissionLog = bizQcLongwayCommissionLogService.queryCommissionLogByParam(bizQcLongwayCommissionLog);
		if(commissionLog == null){
			commissionLog = new BizQcLongwayCommissionLog();
		}
		commissionLog.setOrderId(bizQcLongwayCommissionLog.getOrderId());
		commissionLog.setLongwayCommissionEmployeeId(bizQcLongwayCommissionLog.getLongwayCommissionEmployeeId());
		commissionLog.setLongwayCommissionType(bizQcLongwayCommissionLog.getLongwayCommissionType());
		commissionLog.setCommissionAmount(bizQcLongwayCommissionLog.getCommissionAmount());
		commissionLog.setRemarks(bizQcLongwayCommissionLog.getRemarks());
		bizQcLongwayCommissionLogService.save(commissionLog);
		BizPmPreIndustrySettleBill settleBill = new BizPmPreIndustrySettleBill();
		if(isEditSettleBill == 1){

			Map<String,Object> settleBillParam = new HashMap<String,Object>();
			settleBillParam.put("orderId", bizQcLongwayCommissionLog.getOrderId());
			settleBillParam.put("settleBillType", 2);
			settleBill = bizPmPreIndustrySettleBillService.queryPmPreIndustrySettleBillByParam(settleBillParam);
			settleBill.setCompleteLongwayCommissionAmount(bizQcLongwayCommissionLog.getCommissionAmount());
            settleBill = bizPmPreIndustrySettleBillService.completedDataEncapsulation(settleBill,bizQcLongwayCommissionLog.getOrderId());
			bizPmPreIndustrySettleBillService.save(settleBill);

			Map<String,Object> cateGorySummeryParam = new HashMap<String,Object>();
			cateGorySummeryParam.put("orderId", settleBill.getOrderId());
			cateGorySummeryParam.put("pmEmployeeId", settleBill.getPmEmployeeId());
			cateGorySummeryParam.put("pmSettleBillId", settleBill.getId());
			cateGorySummeryParam.put("settleCategory", ConstantUtils.PM_SETTLE_CATEGORY_1001);
			BizPmSettleCategorySummary bizPmSettleCategorySummary = bizPmSettleCategorySummaryService.queryCateGrrySummaryByParam(cateGorySummeryParam);
			if(bizPmSettleCategorySummary == null || bizPmSettleCategorySummary.getId() == null){
				bizPmSettleCategorySummary = new BizPmSettleCategorySummary();
				bizPmSettleCategorySummary.setOrderId(settleBill.getOrderId());
				bizPmSettleCategorySummary.setPmEmployeeId(settleBill.getPmEmployeeId());
				bizPmSettleCategorySummary.setSettleCategory(ConstantUtils.PM_SETTLE_CATEGORY_1001);
				bizPmSettleCategorySummary.setSettleStatus(ConstantUtils.PM_SETTLE_STATUS_10);
				bizPmSettleCategorySummary.setSettleStatusDatetime(new Date());
				bizPmSettleCategorySummary.setSettleRole(ConstantUtils.SETTLE_ROLE_1);
			}
			bizPmSettleCategorySummary.setSettleAmount(bizQcLongwayCommissionLog.getCommissionAmount());
			bizPmSettleCategorySummaryService.save(bizPmSettleCategorySummary);

			Map<String,Object> cateGoryDetailParam = new HashMap<String,Object>();
			cateGoryDetailParam.put("orderId", settleBill.getOrderId());
			cateGoryDetailParam.put("pmEmployeeId", settleBill.getPmEmployeeId());
			cateGoryDetailParam.put("settleCategory", ConstantUtils.PM_SETTLE_CATEGORY_1001);
			BizPmSettleCategoryDetail bizPmSettleCategoryDetail = bizPmSettleCategoryDetailService.querySettleCategoryDetailByParam(cateGoryDetailParam);
			if(bizPmSettleCategoryDetail ==null || bizPmSettleCategoryDetail.getId() == null){
				bizPmSettleCategoryDetail = new BizPmSettleCategoryDetail();
				bizPmSettleCategoryDetail.setOrderId(settleBill.getOrderId());
				bizPmSettleCategoryDetail.setPmEmployeeId(settleBill.getPmEmployeeId());
				bizPmSettleCategoryDetail.setSettleStatus(ConstantUtils.PM_SETTLE_STATUS_10);
				bizPmSettleCategoryDetail.setSettleCategory(ConstantUtils.PM_SETTLE_CATEGORY_1001);
				bizPmSettleCategoryDetail.setSettleStatusDatetime(new Date());
				bizPmSettleCategoryDetail.setRelatedBusinessId(commissionLog.getId());
				bizPmSettleCategoryDetail.setSettleRole(ConstantUtils.SETTLE_ROLE_1);
				bizPmSettleCategoryDetail.setPmSettleCategorySummaryId(bizPmSettleCategorySummary.getId());
			}
			bizPmSettleCategoryDetail.setSettleAmount(bizQcLongwayCommissionLog.getCommissionAmount());
			bizPmSettleCategoryDetailService.save(bizPmSettleCategoryDetail);
		}
		String result= null;
        String isNewSettleBill = settleBill.getIsNewSettleBill();
		if(isEditSettleBill == 0){
            if("0".equals(isNewSettleBill)){
                result = "redirect:" + Global.getAdminPath()+ "/proIndustryPmSettle/proIndustryPmSettle/openCompleteSettleInfoPage?orderId="+ bizQcLongwayCommissionLog.getOrderId();
            }else {
                result = "redirect:" + Global.getAdminPath()+ "/newProIndustryPmSettle/openCompleteSettleInfoPage?orderId="+ bizQcLongwayCommissionLog.getOrderId();
            }

		}else if(isEditSettleBill == 1){
			BizBusinessStatusLog statusLog = new BizBusinessStatusLog();
			statusLog.setBusinessType(BusinessLogConstantUtil.PRE_MANAGER_SETTLE_TYPE_3600);
			statusLog.setBusinessOnlyMarkInt(settleBill.getId());
			statusLog.setBusinessStatus(settleBill.getStatus());
			statusLog.setStatusDatetime(new Date());
			String empId = UserUtils.getUser1().getEmpId();
			if (null != empId) {
				statusLog.setBusinessEmployeeId(Integer.parseInt(empId));
			}
			statusLog.setBusinessRemarks("修改结算单");
			bizBusinessStatusLogService.save(statusLog);

			result="redirect:" + Global.getAdminPath()
					+ "/bizPmPreIndustrySettleBill/bizPmPreIndustrySettleBill/editSettleBill?id="
					+ settleBill.getId();
		}
		return result;
	}


	@RequestMapping(value = "createMidwaySettleBille")
	public String createMidwaySettleBille(BizPmPreIndustrySettleBill bizPmPreIndustrySettleBill,
										  RedirectAttributes redirectAttributes, Model model, HttpServletRequest request,
										  HttpServletResponse response) {
        bizPmPreIndustrySettleBill.setIsNewSettleBill("0");
		String result = bizPmPreIndustrySettleBillService.createMidwaySettleBille(bizPmPreIndustrySettleBill);
		if (result.equals("0")) {
			addMessage(redirectAttributes, "结算单生成成功！");
		} else if (result.equals("1")) {
			addMessage(redirectAttributes, "结算单已生成");
		}
		return "redirect:" + Global.getAdminPath()
				+ "/proIndustryPmSettle/proIndustryPmSettle/queryProIndustryPmMidwaySettleList";
	}


	@RequestMapping(value = "createCompleteSettleBille")
	public String createCompleteSettleBille(BizPmPreIndustrySettleBill bizPmPreIndustrySettleBill,
											RedirectAttributes redirectAttributes, Model model, HttpServletRequest request,
											HttpServletResponse response){
        bizPmPreIndustrySettleBill.setIsNewSettleBill("0");
		String result = bizPmPreIndustrySettleBillService.createCompleteSettleBille(bizPmPreIndustrySettleBill);
		if (result.equals("0")) {
			addMessage(redirectAttributes, "结算单生成成功！");
		} else if (result.equals("1")) {
			addMessage(redirectAttributes, "结算单已生成");
		}
		return "redirect:" + Global.getAdminPath()
				+ "/proIndustryPmSettle/proIndustryPmSettle/queryProIndustryPmCompleteSettleList";
	}

}
