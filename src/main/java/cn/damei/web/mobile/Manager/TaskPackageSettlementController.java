package cn.damei.web.mobile.Manager;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import cn.damei.entity.mobile.Inspector.EvaluateWorker;
import cn.damei.service.mobile.Inspector.InspectorEvaluateWorkerService;
import cn.damei.service.mobile.Manager.BizEvalRewardTaskpackService;
import cn.damei.service.mobile.Manager.BizEvalTaskpackRoleIndexScoreService;
import cn.damei.service.mobile.Manager.BizEvalTaskpackRoleScoreService;
import cn.damei.service.mobile.Manager.BizEvalTaskpackScoreService;
import cn.damei.entity.mobile.Manager.OrderSignVo;
import cn.damei.service.mobile.Manager.SignService;
import cn.damei.entity.mobile.Manager.SettlementEvalStore;
import cn.damei.entity.modules.BizEvalActivityIndex;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.PicRootName;
import cn.damei.common.web.BaseController;
import cn.damei.common.SessionUtils;
import cn.damei.entity.mobile.Manager.AuxiliaryMaterialsVo;
import cn.damei.service.mobile.Manager.AuxiliaryApplyService;
import cn.damei.entity.mobile.Manager.BizOrderTaskpackageProcedure;
import cn.damei.service.mobile.Manager.BizOrderTaskpackageProcedureService;
import cn.damei.entity.mobile.Manager.GuaranteeMoney;
import cn.damei.service.mobile.Manager.GuaranteeMoneyService;
import cn.damei.entity.mobile.Manager.Manager;
import cn.damei.entity.mobile.Manager.OrderTaskpackagePic;
import cn.damei.service.mobile.Manager.OrderTaskpackagePicService;
import cn.damei.entity.mobile.Manager.TaskPackage;
import cn.damei.service.mobile.Manager.TaskPackageService;
import cn.damei.entity.mobile.Manager.EmpTaskpackageSettlement;
import cn.damei.entity.mobile.Manager.OrderTaskpackageSettlement;
import cn.damei.entity.mobile.Manager.OrderTaskpackageVo;
import cn.damei.service.mobile.Manager.OrderTaskpackageSettlementService;
import cn.damei.entity.mobile.Worker.SettlementAuxiliary;
import cn.damei.entity.mobile.Worker.TaskPackagePicture;
import cn.damei.service.mobile.Worker.SettlementAuxiliaryService;
import cn.damei.service.mobile.Worker.TaskPackagePictureService;
import cn.damei.entity.mobile.Worker.WorkTaskPackage;
import cn.damei.service.mobile.Worker.WorkTaskPackageService;
import cn.damei.entity.mobile.home.CheckBreak;
import cn.damei.entity.mobile.home.CheckItem;
import cn.damei.entity.mobile.home.Report;
import cn.damei.service.mobile.home.HomeReportService;
import cn.damei.entity.modules.BizOrderTaskpackageSettlement;
import cn.damei.service.modules.BizOrderTaskpackageSettlementService;
import cn.damei.entity.modules.BizGuaranteeMoneyBalance;
import cn.damei.service.modules.BizGuaranteeMoneyBalanceService;
import cn.damei.entity.modules.BizTaskPackageAuxiliaryMaterials;
import cn.damei.entity.modules.BizTaskPackageTemplat;
import cn.damei.service.modules.BizTaskPackageAuxiliaryMaterialsService;
import cn.damei.service.modules.BizTaskPackageTemplatService;

@Controller
@RequestMapping(value = "${adminPath}/app/manager")
public class TaskPackageSettlementController extends BaseController {

	@Autowired
	private TaskPackageService packageService;
	@Autowired
	private HomeReportService reportService;
	@Autowired
	private WorkTaskPackageService workTaskPackageService;
	@Autowired
	private BizOrderTaskpackageSettlementService bizOrderTaskpackageSettlementService;

	@Autowired
	private OrderTaskpackageSettlementService settlementService;

	@Autowired
	private BizOrderTaskpackageProcedureService bizOrderTaskpackageProcedureService;

	@Autowired
	private AuxiliaryApplyService auxiliaryApplyService;

	@Autowired
	private GuaranteeMoneyService guaranteeMoneyService;

	@Autowired
	private OrderTaskpackagePicService orderTaskpackagePicService;

	@Autowired
	private BizTaskPackageTemplatService taskPackageTemplatService;

	@Autowired
	private BizTaskPackageAuxiliaryMaterialsService bizTaskPackageAuxiliaryMaterialsService;

	@Autowired
	private TaskPackagePictureService taskPackagePictureService;

	@Autowired
	private SignService signService;

	@Autowired
	private BizEvalTaskpackRoleIndexScoreService bizEvalTaskpackRoleIndexScoreService;

	@Autowired
	private InspectorEvaluateWorkerService inspectorEvaluateWorkerService;

	@Autowired
	private BizEvalTaskpackRoleScoreService bizEvalTaskpackRoleScoreService;

	@Autowired
	private BizEvalRewardTaskpackService bizEvalRewardTaskpackService;

	@Autowired
	private BizEvalTaskpackScoreService bizEvalTaskpackScoreService;

	@Autowired
	private BizGuaranteeMoneyBalanceService bizGuaranteeMoneyBalanceService;

	// 页面的跳转
	@RequestMapping(value = "taskPackageManager")
	public String taskPackageManager(HttpServletRequest request) {

		String index = (String) request.getSession().getAttribute("index");
		request.getSession().removeAttribute("index");

		if (null != index) {
			if ("0".equals(index)) {
				// 下面的
				return "mobile/modules/Manager/task_manager";
			} else if ("1".equals(index)) {
				// 上面的
				return "mobile/modules/Manager/manager_index";
			} else {

				logger.warn("辅料申请路径参数index有误  index:" + index);
				return "mobile/modules/Manager/manager_index";
			}

		} else {

			logger.warn("辅料申请路径参数为空");
			return "mobile/modules/Manager/task_manager";
		}

	}

	// 查询项目经理名下的所有需要结算的任务包
	@RequestMapping(value = "packageSettlementList")
	public String packageSettlementList(String index, TaskPackage pack, Model model, HttpServletRequest request) {

		if ("0".equals(index) || "1".equals(index)) {
			request.getSession().setAttribute("index", index);
		} else {

			logger.warn("任务包结算访问参数有误 ,无法识别路径参数: index :" + index);

		}

		// 已登录的项目经理
		Manager manager = SessionUtils.getManagerSession(request);
		// 查询已登陆项目经理名下，任务包状态为【已申请完工、质检已复核】的任务包
		List<TaskPackage> list = packageService.queryTaskPackageByNoState(manager.getId());
		model.addAttribute("list", list);
		return "mobile/modules/Manager/task_budget";
	}

	// 页面的跳转
	@RequestMapping(value = "taskPackageMap")
	public String taskPackageMap(TaskPackage pack, Model model, HttpServletRequest request) {
		OrderSignVo signVo = signService.get(pack.getOrderId());
		String[] split = signVo.getMapAddress().split(",");
		pack.setLon(split[0]);
		pack.setLat(split[1]);
		model.addAttribute("pack", pack);
		return "mobile/modules/Manager/task_sign_null_page";
	}

	// 到任务包确认验收页面
	@RequestMapping(value = "taskpackageConfirm")
	public String taskpackageConfirm(TaskPackage pack, Model model) throws Exception {

		OrderTaskpackageVo vo = settlementService.queryTaskpackageSettlement(Integer.parseInt(pack.getId()));

		OrderTaskpackageSettlement settle = settlementService
				.queryTaskpackageSettlementByOrderTaskpackageId(Integer.parseInt(pack.getId()));
		if (settle != null && settle.getId() != null) {
			settle = new OrderTaskpackageSettlement();
		}
		// 如果扣除质保金为是，则查询是否已经扣除过两次
		if (ConstantUtils.IS_QUALITY_GUARANTEE_YES.equals(vo.getIsQualityGuarantee())) {
			/*int count = guaranteeMoneyService.queryGuaranteeMoneyCount(vo.getTaskPackageTemplatId(), vo.getGroupId(),
					null);*/
			// 计算累积质保金
			Double guaranteeMoneySum = guaranteeMoneyService.queryGuaranteeMoneySum(vo.getGroupId(), null);
			vo.setGuaranteeMoneyAmountTotal(guaranteeMoneySum + "");
			BizGuaranteeMoneyBalance bizGuaranteeMoneyBalance = bizGuaranteeMoneyBalanceService
					.findGuaranteeMoneyBalanceByEmployeeId(vo.getGroupId());
			if (bizGuaranteeMoneyBalance == null) {
				bizGuaranteeMoneyBalance = new BizGuaranteeMoneyBalance();
			}
			vo.setGuaranteeMoneyBalance(bizGuaranteeMoneyBalance.getGuaranteeMoneyBalance());
			//2017-11-23  工人质保金改为从第一个任务包就扣除质保金
			vo.setGualityGuaranteeType(3);
			/*if (count < 2) {
				vo.setGualityGuaranteeType(2);
			} else {
				vo.setGualityGuaranteeType(3);
			}*/
		} else {
			vo.setGualityGuaranteeType(1);
		}

		// 工程清单列表
		List<BizOrderTaskpackageProcedure> taskProcedureList = bizOrderTaskpackageProcedureService
				.queryOrderTaskpackageProcedure(Integer.parseInt(pack.getId()));

		// 工人组列表
		List<EmpTaskpackageSettlement> empList = settlementService
				.queryTaskpackageEmpDetail(Integer.parseInt(vo.getEmpGroupid()));

		// 辅料列表
		List<AuxiliaryMaterialsVo> list = auxiliaryApplyService.queryAuxiliaryMaterialList(vo.getOrderId(),
				vo.getTaskPackageTemplatId());

		// 沙子水泥列表
		List<AuxiliaryMaterialsVo> sandList = auxiliaryApplyService.querySandMaterialList(vo.getOrderId(),
				vo.getTaskPackageTemplatId());

		// 质检罚款
		Double qcAmount = settlementService.queryQcWorkerPublishAmountTotal(Integer.parseInt(pack.getId()));

		// 评价工人
		EvaluateWorker evaluateWorker = new EvaluateWorker();
		evaluateWorker.setEvalRoleType(ConstantUtils.EVAL_ROLE_TYPE_1);
		evaluateWorker.setOrderTaskpackageId(Integer.parseInt(pack.getId()));
		List<BizEvalActivityIndex> bizEvalIndexList = inspectorEvaluateWorkerService
				.findEvalActivityIndex(evaluateWorker);
		String isExist = "0";
		OrderTaskpackageSettlement orderTaskpackageSettlement = settlementService
				.queryTaskpackageSettlementByOrderTaskpackageId(Integer.parseInt(pack.getId()));
		if (orderTaskpackageSettlement != null && orderTaskpackageSettlement.getId() != null) {
			isExist = "1";
		}
		List<BizTaskPackageAuxiliaryMaterials> taskPacks = bizTaskPackageAuxiliaryMaterialsService
				.checkTaskPackageByTemplateId(vo.getTaskPackageTemplatId());
		String checkTaskPack = "0";
		if (taskPacks != null && taskPacks.size() > 0) {// 是水泥沙子任务包
			checkTaskPack = "1";
		}
		model.addAttribute("checkTaskPack", checkTaskPack);
		model.addAttribute("isExist", isExist);
		model.addAttribute("sandList", sandList);
		model.addAttribute("bizEvalIndexList", bizEvalIndexList);
		model.addAttribute("qcAmount", qcAmount);
		model.addAttribute("orderTaskpackageVo", vo);
		model.addAttribute("empList", empList);
		model.addAttribute("taskProcedureList", taskProcedureList);
		model.addAttribute("auxiliaryList", list);
		model.addAttribute("root", PicRootName.picPrefixName());
		model.addAttribute("settle", settle);
		return "mobile/modules/Manager/taskpackage_confirm";
	}

	/*
	 * // 辅料用量列表
	 * 
	 * @RequestMapping(value="auxiliaryCountList") public String
	 * auxiliaryCountList(TaskPackage pack, Model model){
	 * List<AuxiliaryMaterialsVo> list =
	 * auxiliaryApplyService.queryAuxiliaryMaterialList(pack.getOrderId(),
	 * pack.getTaskPackageTemplatId()); model.addAttribute("auxiliaryList",
	 * list); return "mobile/modules/Manager/auxiliary_count"; }
	 */

	// 施工图照片
	@RequestMapping(value = "viewPhone")
	public String viewPhone(Integer orderTaskpackageId, Model model) throws Exception {
		String root = PicRootName.picPrefixName();
		List<OrderTaskpackagePic> list = orderTaskpackagePicService
				.queryOrderTaskpackagePicByOrderTaskpackageId(orderTaskpackageId);
		model.addAttribute("orderTaskpackagePicList", list);
		model.addAttribute("root", root);
		return "mobile/modules/Manager/phone";
	}

	// 结算单确认验收
	@RequestMapping(value = "orderTaskpackageSettlement", method = RequestMethod.POST)
	@ResponseBody
	public String orderTaskpackageSettlement(OrderTaskpackageSettlement settlement, String checkedDate,
			String isExist) throws Exception {
		String result = null;
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			settlement.setCheckDate(format.parse(checkedDate));
			result = settlementService.orderTaskpackageSettlementConfirm(settlement, isExist);
		} catch (Exception e) {
			result = "error";
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	// 提交工程量
	@RequestMapping(value = "submitGongcheng", method = RequestMethod.POST)
	@ResponseBody
	public String submitGongcheng(OrderTaskpackageSettlement settlement, String checkedDate) throws Exception {
		String result = null;
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			settlement.setCheckDate(format.parse(checkedDate));
			result = settlementService.submitGongcheng(settlement);
		} catch (Exception e) {
			result = "error";
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	// 结算单管理
	@RequestMapping(value = "taskBudgetManage")
	public String taskBudgetManage(Model model) {
		List<BizTaskPackageTemplat> templatList = taskPackageTemplatService.queryTaskpackageTemplat();
		List<TaskPackage> taskList = packageService.queryTaskPackageStateList(ConstantUtils.TASKPACKAGE_STATUS);
		model.addAttribute("templatList", templatList);
		model.addAttribute("taskList", taskList);
		return "mobile/modules/Manager/task_budget_manage";
	}

	@RequestMapping(value = "orderPackageList", method = RequestMethod.POST)
	public @ResponseBody List<TaskPackage> orderPackageList(HttpServletRequest request, Integer taskPackageTemplatId,
			String stateId, Integer orderType) {
		// 已登录的项目经理
		Manager manager = SessionUtils.getManagerSession(request);
		// 查询项目经理下所有的任务包
		return packageService.queryOrderPackageList(taskPackageTemplatId, stateId, orderType, manager.getId());
	}

	// 到任务包确认验收页面
	@RequestMapping(value = "taskpackageConfirmAgain")
	public String taskpackageConfirmAgain(OrderTaskpackageSettlement settlement, Model model) throws Exception {

		// 订单任务包
		OrderTaskpackageVo vo = settlementService.queryTaskpackageSettlement(settlement.getOrderTaskpackageId());

		// 结算单
		OrderTaskpackageSettlement taskSettlement = settlementService.get(settlement.getId());

		// 如果扣除质保金为是，则查询是否已经扣除过两次
		if (ConstantUtils.IS_QUALITY_GUARANTEE_YES.equals(vo.getIsQualityGuarantee())) {
			/*int count = guaranteeMoneyService.queryGuaranteeMoneyCount(vo.getTaskPackageTemplatId(), vo.getGroupId(),
					settlement.getId());*/
			// 计算累积质保金
			Double guaranteeMoneySum = guaranteeMoneyService.queryGuaranteeMoneySum(vo.getGroupId(),
					settlement.getId());
			vo.setGuaranteeMoneyAmountTotal(guaranteeMoneySum + "");
			BizGuaranteeMoneyBalance bizGuaranteeMoneyBalance = bizGuaranteeMoneyBalanceService
					.findGuaranteeMoneyBalanceByEmployeeId(vo.getGroupId());
			if (bizGuaranteeMoneyBalance == null || bizGuaranteeMoneyBalance.getId() == null) {
				bizGuaranteeMoneyBalance = new BizGuaranteeMoneyBalance();
			} else {
				if (bizGuaranteeMoneyBalance.getGuaranteeMoneyBalance()
						- Double.parseDouble(vo.getGuaranteeMoneyAmount()) < 0) {
					bizGuaranteeMoneyBalance.setGuaranteeMoneyBalance(0d);
				} else {
					bizGuaranteeMoneyBalance
							.setGuaranteeMoneyBalance(bizGuaranteeMoneyBalance.getGuaranteeMoneyBalance()
									- Double.parseDouble(vo.getGuaranteeMoneyAmount()));
				}

			}
			vo.setGuaranteeMoneyBalance(bizGuaranteeMoneyBalance.getGuaranteeMoneyBalance());
			//2017-11-23  工人质保金改为从第一个任务包就扣除质保金
			vo.setGualityGuaranteeType(3);
			/*if (count < 2) {
				vo.setGualityGuaranteeType(2);
			} else {
				vo.setGualityGuaranteeType(3);
			}*/
		} else {
			vo.setGualityGuaranteeType(1);
		}

		// 工程清单列表
		List<BizOrderTaskpackageProcedure> taskProcedureList = bizOrderTaskpackageProcedureService
				.queryOrderTaskpackageProcedure(settlement.getOrderTaskpackageId());

		// 工人组列表
		List<EmpTaskpackageSettlement> empList = settlementService.queryUpdateTaskpackageEmpDetail(
				Integer.parseInt(vo.getEmpGroupid()), settlement.getOrderTaskpackageId(), settlement.getId());

		// 辅料列表
		List<AuxiliaryMaterialsVo> list = auxiliaryApplyService.queryUsedAuxiliaryMaterialList(vo.getOrderId(),
				vo.getTaskPackageTemplatId(), settlement.getOrderTaskpackageId());

		// 沙子水泥列表
		List<AuxiliaryMaterialsVo> sandList = auxiliaryApplyService.queryUsedSandMaterialList(vo.getOrderId(),
				vo.getTaskPackageTemplatId(), settlement.getOrderTaskpackageId());

		// 质检罚款
		Double qcAmount = settlementService.queryQcWorkerPublishAmountTotal(settlement.getOrderTaskpackageId());

		// 评价工人
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("evalRoleType", ConstantUtils.EVAL_ROLE_TYPE_101);
		map.put("relatedBusinessId", settlement.getOrderTaskpackageId());
		map.put("evalType", "1");
		List<SettlementEvalStore> evalStoreList = bizEvalTaskpackRoleIndexScoreService.querySettlementEval(map);
		if (CollectionUtils.isNotEmpty(evalStoreList)) {
			for (SettlementEvalStore store : evalStoreList) {
				Double selectCount = store.getGotScore() / (store.getEvalTotalScore() / 5);
				store.setSelectCount(selectCount.intValue());
			}
		}
		String evalFeedback = bizEvalTaskpackRoleScoreService.queryEvalFeedback(map);

		// 评价奖励金额
		Double rewardAmount = bizEvalRewardTaskpackService.queryRewardAmount(settlement.getOrderTaskpackageId());

		List<BizTaskPackageAuxiliaryMaterials> taskPacks = bizTaskPackageAuxiliaryMaterialsService
				.checkTaskPackageByTemplateId(vo.getTaskPackageTemplatId());
		String checkTaskPack = "0";
		if (taskPacks != null && taskPacks.size() > 0) {// 是水泥沙子任务包
			checkTaskPack = "1";
		}
		model.addAttribute("checkTaskPack", checkTaskPack);
		model.addAttribute("rewardAmount", rewardAmount);
		model.addAttribute("evalFeedback", evalFeedback);
		model.addAttribute("evalStoreList", evalStoreList);
		model.addAttribute("qcAmount", qcAmount);
		model.addAttribute("taskSettlement", taskSettlement);
		model.addAttribute("orderTaskpackageVo", vo);
		model.addAttribute("empList", empList);
		model.addAttribute("taskProcedureList", taskProcedureList);
		model.addAttribute("auxiliaryList", list);
		model.addAttribute("sandList", sandList);
		model.addAttribute("root", PicRootName.picPrefixName());
		return "mobile/modules/Manager/taskpackage_confirm_update";
	}

	// 结算单再次确认验收
	@RequestMapping(value = "orderTaskpackageSettlementAgain", method = RequestMethod.POST)
	@ResponseBody
	public String orderTaskpackageSettlementAgain(OrderTaskpackageSettlement settlement, String checkedDate) throws Exception {
		String flag = "error";
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			settlement.setCheckDate(format.parse(checkedDate));
			flag = settlementService.orderTaskpackageSettlementAgainConfirm(settlement);
		} catch (Exception e) {
			flag = "error";
			e.printStackTrace();
			throw e;
		}
		return flag;
	}

	// 到任务包已复核后确认验收页面
	@RequestMapping(value = "taskpackageConfirmRecheck")
	public String taskpackageConfirmRecheck(Integer taskPackageId, Model model) throws Exception {
		// 订单任务包
		OrderTaskpackageVo vo = settlementService.queryTaskpackageSettlement(taskPackageId);

		// 结算单
		OrderTaskpackageSettlement taskSettlement = settlementService
				.queryTaskpackageSettlementByOrderTaskpackageId(taskPackageId);

		// 如果扣除质保金为是，则查询是否已经扣除过两次
		if (ConstantUtils.IS_QUALITY_GUARANTEE_YES.equals(vo.getIsQualityGuarantee())) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orderTaskpackageId", taskSettlement.getOrderTaskpackageId());
			map.put("settlementId", taskSettlement.getId());
			GuaranteeMoney guaranteeMoney = guaranteeMoneyService.queryGuarnteeMoney(map);
			/*int count = guaranteeMoneyService.queryGuaranteeMoneyCount(vo.getTaskPackageTemplatId(), vo.getGroupId(),
					taskSettlement.getId());*/
			// 计算累积质保金
			Double guaranteeMoneySum = guaranteeMoneyService.queryGuaranteeMoneySum(vo.getGroupId(),
					taskSettlement.getId());
			vo.setGuaranteeMoneyAmountTotal(guaranteeMoneySum + "");
			BizGuaranteeMoneyBalance bizGuaranteeMoneyBalance = bizGuaranteeMoneyBalanceService
					.findGuaranteeMoneyBalanceByEmployeeId(vo.getGroupId());
			if (guaranteeMoney != null && guaranteeMoney.getId() != null) {// 此任务包的工人已上缴质保金
				if (bizGuaranteeMoneyBalance == null) {
					bizGuaranteeMoneyBalance = new BizGuaranteeMoneyBalance();
					bizGuaranteeMoneyBalance.setGuaranteeMoneyBalance(guaranteeMoney.getGuaranteeMoneyAmountTotal());
				} else {
					if (bizGuaranteeMoneyBalance.getGuaranteeMoneyBalance()
							- guaranteeMoney.getGuaranteeMoneyAmount() < 0) {
						bizGuaranteeMoneyBalance.setGuaranteeMoneyBalance(0d);
					} else {
						bizGuaranteeMoneyBalance
								.setGuaranteeMoneyBalance(bizGuaranteeMoneyBalance.getGuaranteeMoneyBalance()
										- guaranteeMoney.getGuaranteeMoneyAmount());
					}

				}
			} else {// 此任务包的工人没有上缴质保金
				if (bizGuaranteeMoneyBalance == null) {
					bizGuaranteeMoneyBalance = new BizGuaranteeMoneyBalance();
				}
			}
			vo.setGuaranteeMoneyBalance(bizGuaranteeMoneyBalance.getGuaranteeMoneyBalance());
			//2017-11-23  工人质保金改为从第一个任务包就扣除质保金
			vo.setGualityGuaranteeType(3);
			/*if (count < 2) {
				vo.setGualityGuaranteeType(2);
			} else {
				vo.setGualityGuaranteeType(3);
			}*/
		} else {
			vo.setGualityGuaranteeType(1);
		}

		// 工程清单列表
		List<BizOrderTaskpackageProcedure> taskProcedureList = bizOrderTaskpackageProcedureService
				.queryOrderTaskpackageProcedure(taskPackageId);

		// 工人组列表
		List<EmpTaskpackageSettlement> empList = settlementService.queryUpdateTaskpackageEmpDetail(
				Integer.parseInt(vo.getEmpGroupid()), taskPackageId, taskSettlement.getId());

		// 辅料列表

		List<AuxiliaryMaterialsVo> list = auxiliaryApplyService.queryUsedAuxiliaryMaterialList(vo.getOrderId(),
				vo.getTaskPackageTemplatId(), taskPackageId);

		// 沙子水泥列表
		List<AuxiliaryMaterialsVo> sandList = auxiliaryApplyService.queryUsedSandMaterialList(vo.getOrderId(),
				vo.getTaskPackageTemplatId(), taskPackageId);

		// 质检罚款
		Double qcAmount = settlementService.queryQcWorkerPublishAmountTotal(taskPackageId);

		// 评价工人
		// 判断是否已评价
		Map<String, Object> rewardMap = new HashMap<String, Object>();
		rewardMap.put("relatedBusinessId", taskPackageId);
		rewardMap.put("evalType", "1");
		// rewardMap.put("evalStatus", ConstantUtils.EVAL_STATUS_1);
		Integer rewardCount = bizEvalTaskpackScoreService.queryCountByMap(rewardMap);

		if (rewardCount > 0) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("relatedBusinessId", taskPackageId);
			map.put("evalType", "1");
			map.put("evalRoleType", ConstantUtils.EVAL_ROLE_TYPE_101);
			List<SettlementEvalStore> evalStoreList = bizEvalTaskpackRoleIndexScoreService.querySettlementEval(map);
			if (CollectionUtils.isNotEmpty(evalStoreList)) {
				for (SettlementEvalStore store : evalStoreList) {
					Double selectCount = store.getGotScore() / (store.getEvalTotalScore() / 5);
					store.setSelectCount(selectCount.intValue());
				}
			}
			String evalFeedback = bizEvalTaskpackRoleScoreService.queryEvalFeedback(map);

			model.addAttribute("evalFeedback", evalFeedback);
			model.addAttribute("evalStoreList", evalStoreList);
		} else {
			// 评价工人
			EvaluateWorker evaluateWorker = new EvaluateWorker();
			evaluateWorker.setEvalRoleType(ConstantUtils.EVAL_ROLE_TYPE_1);
			evaluateWorker.setOrderTaskpackageId(taskPackageId);
			List<BizEvalActivityIndex> bizEvalIndexList = inspectorEvaluateWorkerService
					.findEvalActivityIndex(evaluateWorker);
			model.addAttribute("bizEvalIndexList", bizEvalIndexList);
		}

		// 评价奖励金额
		Double rewardAmount = bizEvalRewardTaskpackService.queryRewardAmount(taskPackageId);

		String isExist = "0";
		OrderTaskpackageSettlement orderTaskpackageSettlement = settlementService
				.queryTaskpackageSettlementByOrderTaskpackageId(taskPackageId);
		if (orderTaskpackageSettlement != null && orderTaskpackageSettlement.getId() != null
				&& orderTaskpackageSettlement.getSettlementAmount() != null) {
			isExist = "1";
		}
		List<BizTaskPackageAuxiliaryMaterials> taskPacks = bizTaskPackageAuxiliaryMaterialsService
				.checkTaskPackageByTemplateId(vo.getTaskPackageTemplatId());
		String checkTaskPack = "0";
		if (taskPacks != null && taskPacks.size() > 0) {// 是水泥沙子任务包
			checkTaskPack = "1";
		}
		model.addAttribute("checkTaskPack", checkTaskPack);

		model.addAttribute("isExist", isExist);
		model.addAttribute("rewardCount", rewardCount);
		model.addAttribute("rewardAmount", rewardAmount);
		model.addAttribute("qcAmount", qcAmount);
		model.addAttribute("taskSettlement", taskSettlement);
		model.addAttribute("orderTaskpackageVo", vo);
		model.addAttribute("empList", empList);
		model.addAttribute("taskProcedureList", taskProcedureList);
		model.addAttribute("sandList", sandList);
		model.addAttribute("auxiliaryList", list);
		model.addAttribute("root", PicRootName.picPrefixName());
		return "mobile/modules/Manager/taskpackage_confirm_recheck";
	}

	// 结算单已复核确认验收
	@RequestMapping(value = "orderTaskpackageSettlementRecheck", method = RequestMethod.POST)
	@ResponseBody
	public String orderTaskpackageSettlementRecheck(OrderTaskpackageSettlement settlement, String checkedDate,
			String isExist) {
		String flag = "error";
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			settlement.setCheckDate(format.parse(checkedDate));
			flag = settlementService.orderTaskpackageSettlementRecheck(settlement, isExist);
		} catch (Exception e) {
			flag = "error";
			e.printStackTrace();
			throw new RuntimeException();
		}

		return flag;
	}

	// 提交工程量
	@RequestMapping(value = "submitGongchengUpdate", method = RequestMethod.POST)
	@ResponseBody
	public String submitGongchengUpdate(OrderTaskpackageSettlement settlement, String checkedDate) throws Exception {
		String flag = "error";
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			settlement.setCheckDate(format.parse(checkedDate));
			settlementService.submitGongchengUpdate(settlement);
			flag = "success";
		} catch (Exception e) {
			flag = "error";
			e.printStackTrace();
			throw e;
		}
		return flag;
	}

	/**
	 * 结算单详情
	 */
	@RequestMapping(value = "account")
	public String account(Integer id, Integer settleStyle, Model model, HttpServletRequest request) {
		DecimalFormat df = new DecimalFormat("#.00");
		double budgetTotalMoney = 0d;
		double settleTotalMoney = 0d;
		double a = 0d;
		WorkTaskPackage workTaskPackage = workTaskPackageService.findTaskPackageById(id);
		BizOrderTaskpackageSettlement bizOrderTaskpackageSettlement = bizOrderTaskpackageSettlementService
				.findByOrderTaskpackageId(id);
		List<BizOrderTaskpackageProcedure> procedures = bizOrderTaskpackageProcedureService
				.queryOrderTaskpackageProcedure(id);
		for (BizOrderTaskpackageProcedure procedure : procedures) {
			double b = 0d;
			// 结算方式settleStyle=1，包工包料
			if (settleStyle == 0 || settleStyle == 1) {
				if (null != procedure.getSettlementNumber()) {
					a = procedure.getSettlementNumber() * procedure.getSynthesizePrice();// 实际每道工序的价格

				} else {
					a = procedure.getRealNumber() * procedure.getSynthesizePrice();
				}
				b = procedure.getBudgetNumber() * procedure.getSynthesizePrice();// 预计每道工序的价格
				procedure.setBudgetTotal(b);
				
			// 结算方式settleStyle=2，包工
			} else {
				if (null != procedure.getSettlementNumber()) {
					a = procedure.getSettlementNumber() * procedure.getLaborPrice();// 实际每道工序的人工费价格
				} else {
					a = procedure.getRealNumber() * procedure.getLaborPrice();// 实际每道工序的人工费价格
				}
				b = procedure.getBudgetNumber() * procedure.getLaborPrice();// 预计每道工序的人工费价格
				procedure.setBudgetTotal(b);
			}
			settleTotalMoney = settleTotalMoney + a;// 工料费结算总金额/人工费结算总金额
			budgetTotalMoney = budgetTotalMoney + b;// 工料费预算总金额/人工费预算总金额
		}

		// 评价工人
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("relatedBusinessId", bizOrderTaskpackageSettlement.getOrderTaskpackageId());
		map.put("evalType", "1");
		map.put("evalRoleType", ConstantUtils.EVAL_ROLE_TYPE_101);
		List<SettlementEvalStore> evalStoreList = bizEvalTaskpackRoleIndexScoreService.querySettlementEval(map);
		if (CollectionUtils.isNotEmpty(evalStoreList)) {
			for (SettlementEvalStore store : evalStoreList) {
				Double selectCount = store.getGotScore() / (store.getEvalTotalScore() / 5);
				store.setSelectCount(selectCount.intValue());
			}
		}
		String evalFeedback = bizEvalTaskpackRoleScoreService.queryEvalFeedback(map);
		settleTotalMoney = Double.parseDouble(df.format(settleTotalMoney));
		budgetTotalMoney = Double.parseDouble(df.format(budgetTotalMoney));
		model.addAttribute("settleTotalMoney", settleTotalMoney);
		model.addAttribute("budgetTotalMoney", budgetTotalMoney);
		model.addAttribute("procedures", procedures);
		model.addAttribute("workTaskPackage", workTaskPackage);
		model.addAttribute("bizOrderTaskpackageSettlement", bizOrderTaskpackageSettlement);
		model.addAttribute("evalStoreList", evalStoreList);
		model.addAttribute("evalFeedback", evalFeedback);
		
		if (settleStyle == 0 || settleStyle == 1) { // 包工包料
			return "mobile/modules/Manager/account";
		} else { // 包工
			return "mobile/modules/Manager/account_pkgwork";
		}
	}

	@RequestMapping(value = "accountDetails")
	public String accountDetails(Integer id, Integer settleStyle, Model model, HttpServletRequest request) {

		double budgetTotalMoney = 0;
		double realTotalMoney = 0;

		List<BizOrderTaskpackageProcedure> procedures = bizOrderTaskpackageProcedureService
				.queryOrderTaskpackageProcedure(id);

		if (procedures != null && procedures.size() > 0) {
			for (BizOrderTaskpackageProcedure procedure : procedures) {
				double a = 0d;
				double b = 0d;
				if (settleStyle == 0 || settleStyle == 1) { // 包工包料
					if (null != procedure.getSettlementNumber()) {
						a = procedure.getSettlementNumber() * procedure.getSynthesizePrice();// 实际每道工序的价格
					} else {
						a = procedure.getRealNumber() * procedure.getSynthesizePrice();
					}
					b = procedure.getBudgetNumber() * procedure.getSynthesizePrice();// 预计每道工序的价格
					
				} else {// 包工
					if (null != procedure.getSettlementNumber()) {
						a = procedure.getSettlementNumber() * procedure.getLaborPrice();// 实际每道工序的人工费价格
					} else {
						a = procedure.getRealNumber() * procedure.getLaborPrice();// 实际每道工序的人工费价格
					}
					b = procedure.getBudgetNumber() * procedure.getLaborPrice();// 预计每道工序的价格
				}
				procedure.setBudgetTotal(b);
				realTotalMoney = realTotalMoney + a;// 实际总价
				budgetTotalMoney = budgetTotalMoney + b;// 预计总价
			}
		}
		model.addAttribute("id", id);
		model.addAttribute("procedures", procedures);
		model.addAttribute("realTotalMoney", realTotalMoney);
		model.addAttribute("budgetTotalMoney", budgetTotalMoney);
		
		if (settleStyle == 0 || settleStyle == 1) { // 包工包料
			return "mobile/modules/Manager/account_details";
		} else { // 包工
			return "mobile/modules/Manager/account_pkgwork_details";
		}
		
	}

	@Autowired
	private SettlementAuxiliaryService settlementAuxiliaryService;

	// 辅料详情
	@RequestMapping(value = "auxiliaryDetails")
	public String auxiliaryDetails(Integer id, Model model, HttpServletRequest request) {
		double tatolPrice = 0;
		List<SettlementAuxiliary> auxiliarys = settlementAuxiliaryService.findAuxiliaryListForSettlement(id);
		for (SettlementAuxiliary settlementAuxiliary : auxiliarys) {
			tatolPrice = settlementAuxiliary.getPrice() + tatolPrice;
		}
		model.addAttribute("id", id);
		model.addAttribute("tatolPrice", tatolPrice);
		model.addAttribute("auxiliarys", auxiliarys);
		return "mobile/modules/Manager/auxiliary_details";
	}

	// 沙子水泥详情
	@RequestMapping(value = "sandDetails")
	public String sandDetails(Integer id, Model model, HttpServletRequest request) {
		double sandTatolPrice = 0;
		// 根据任务包id查询沙子水泥
		List<SettlementAuxiliary> sands = settlementAuxiliaryService.findSandListForSettlement(id);

		for (SettlementAuxiliary settlementAuxiliary : sands) {
			sandTatolPrice = settlementAuxiliary.getPrice() + sandTatolPrice;
		}

		model.addAttribute("id", id);
		model.addAttribute("sandTatolPrice", sandTatolPrice);
		model.addAttribute("sands", sands);
		return "mobile/modules/Manager/sand_details";
	}

	/**
	 * 质检罚款详情
	 * 
	 * @param id
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "qcPunishMoneyDetails")
	public String qcPunishMoneyDetails(Integer id, Model model, HttpServletRequest request) {

		List<Report> reports = reportService.queryQcBillList(id);
		// List<List<CheckItem>> items = new ArrayList<List<CheckItem>>(); ;
		for (Report report : reports) {
			for (CheckItem checkItem : report.getCheckItemList()) {
				List<CheckBreak> checkBreakList = reportService.queryCheckBreaks(checkItem.getQcBillItemId());
				checkItem.setCheckBreakList(checkBreakList);
			}
		}
		model.addAttribute("reports", reports);
		return "mobile/modules/Manager/punish_details";
	}

	// 图片
	@RequestMapping(value = "seePhoto")
	public String seePhoto(Integer id,Integer settleStyle, Model model, HttpServletRequest request) throws IOException {
		// 根据任务包查询图片
		List<TaskPackagePicture> pictures = taskPackagePictureService.findPicturesByPackageId(id);
		String baseUrl = PicRootName.picPrefixName();
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("id", id);
		model.addAttribute("pictures", pictures);
		model.addAttribute("settleStyle", settleStyle);
		return "mobile/modules/Manager/photo";
	}

}
