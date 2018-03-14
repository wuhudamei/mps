package cn.damei.web.modules;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.damei.common.config.Global;
import cn.damei.common.persistence.Page;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.MessagePushType;
import cn.damei.common.utils.SendMsgBusinessType;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.mobile.Manager.EmployeeRewardDetail;
import cn.damei.service.mobile.Manager.BizEvalRewardTaskpackService;
import cn.damei.entity.mobile.Manager.EvalScore;
import cn.damei.service.mobile.Manager.BizEvalTaskpackScoreService;
import cn.damei.entity.mobile.Manager.BizOrderTaskpackageProcedure;
import cn.damei.service.mobile.Manager.BizOrderTaskpackageProcedureService;
import cn.damei.service.mobile.Manager.GuaranteeMoneyService;
import cn.damei.entity.mobile.Manager.OrderTaskpackageSettlement;
import cn.damei.service.mobile.Manager.OrderTaskpackageSettlementService;
import cn.damei.entity.mobile.Worker.Message;
import cn.damei.service.mobile.Worker.MessageService;
import cn.damei.entity.mobile.Worker.OrderTaskpackageSettlementDetail;
import cn.damei.entity.mobile.Worker.SettlementAuxiliary;
import cn.damei.service.mobile.Worker.OrderTaskpackageSettlementDetailService;
import cn.damei.service.mobile.Worker.SettlementAuxiliaryService;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.Order2;
import cn.damei.service.modules.OrderService2;
import cn.damei.service.modules.BizOrderTaskpackageService;
import cn.damei.entity.modules.BizOrderTaskpackagePaymentVo;
import cn.damei.service.modules.BizOrderTaskpackagePaymentService;
import cn.damei.entity.modules.BizOrderTaskpackageSettlement;
import cn.damei.entity.modules.BizOrderTaskpackageSettlementVo;
import cn.damei.entity.modules.Guarantee;
import cn.damei.service.modules.BizOrderTaskpackageSettlementService;
import cn.damei.service.modules.BizOrderTaskpackageSettlementVoService;
import cn.damei.service.modules.GuaranteeService;
import cn.damei.entity.modules.BizPhoneMsg;
import cn.damei.service.modules.BizPhoneMsgService;
import cn.damei.entity.modules.BizGuaranteeMoneyBalance;
import cn.damei.service.modules.BizGuaranteeMoneyBalanceService;
import cn.damei.entity.modules.OrderTaskpackage;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;
import cn.damei.service.modules.AllotWorkerGroupService;

@Controller
@RequestMapping(value = "${adminPath}/ordertaskpackagesettlement/bizOrderTaskpackageSettlementVo")
public class BizOrderTaskpackageSettlementVoController extends BaseController {

	@Autowired
	private BizOrderTaskpackageSettlementVoService bizOrderTaskpackageSettlementVoService;
	@Autowired
	private AllotWorkerGroupService allotWorkerGroupService;
	@Autowired
	private BizEmployeeService2 bizEmployeeService;
	@Autowired
	private BizOrderTaskpackageProcedureService bizOrderTaskpackageProcedureService;
	@Autowired
	private SettlementAuxiliaryService settlementAuxiliaryService;
	@Autowired
	private OrderTaskpackageSettlementDetailService orderTaskpackageSettlementDetailService;
	@Autowired
	private GuaranteeService guaranteeService;
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	@Autowired
	private OrderService2 orderService2;
	@Autowired
	private GuaranteeMoneyService guaranteeMoneyService;
	@Autowired
	private BizOrderTaskpackageSettlementService bizOrderTaskpackageSettlementService;
	@Autowired
	private MessageService messageService;
	@Autowired
	private OrderTaskpackageSettlementService settlementService;
	@Autowired
	private BizEvalRewardTaskpackService bizEvalRewardTaskpackService;
	@Autowired
	private BizPhoneMsgService bizPhoneMsgService;
	@Autowired
	private BizGuaranteeMoneyBalanceService bizGuaranteeMoneyBalanceService;
	@Autowired
	private BizOrderTaskpackageService bizOrderTaskpackageService;
	@Autowired
	private BizOrderTaskpackagePaymentService bizOrderTaskpackagePaymentService;
	@Autowired
	private BizEvalTaskpackScoreService bizEvalTaskpackScoreService;

	@ModelAttribute
	public BizOrderTaskpackageSettlementVo get(@RequestParam(required = false) Integer id) {
		BizOrderTaskpackageSettlementVo entity = null;
		if (id != null) {
			entity = bizOrderTaskpackageSettlementVoService.get(id);
		}
		if (entity == null) {
			entity = new BizOrderTaskpackageSettlementVo();
		}
		return entity;
	}

	@RequiresPermissions("ordertaskpackagesettlement:bizOrderTaskpackageSettlementVo:view")
	@RequestMapping(value = "settleOrderListPage")
	public String settleOrderListPage(BizOrderTaskpackageSettlementVo bizOrderTaskpackageSettlementVo,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		if (null == bizOrderTaskpackageSettlementVo.getEnginDepartId()) {
			if (null != UserUtils.getUser().getEmpId()) {
				List<Integer> list = bizEmployeeService2
						.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if (list != null && list.size() > 0) {
					bizOrderTaskpackageSettlementVo.setEnginDepartIds(list);
				} else {
					bizOrderTaskpackageSettlementVo.setEnginDepartIds(null);
				}
			} else {
				bizOrderTaskpackageSettlementVo.setEnginDepartIds(null);
			}
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(bizOrderTaskpackageSettlementVo.getEnginDepartId());
			bizOrderTaskpackageSettlementVo.setEnginDepartIds(list);
		}
		if (UserUtils.getUser().getStoreId() != null) {
			// 当前登录用户门店
			bizOrderTaskpackageSettlementVo.setStoreid(Integer.parseInt(UserUtils.getUser().getStoreId()));
		} else {
			// 门店是总部的查询所有部门信息
			if (bizOrderTaskpackageSettlementVo.getStoreid() != null
					&& bizOrderTaskpackageSettlementVo.getStoreid() == 1) {
				// 总部
				bizOrderTaskpackageSettlementVo.setStoreid(null);
			}
		}
		model.addAttribute("employeeId", UserUtils.getUser().getEmpId());
		return "modules/ordertaskpackagesettlement/bizOrderTaskpackageSettlementVoList";
	}

	@RequiresPermissions("ordertaskpackagesettlement:bizOrderTaskpackageSettlementVo:view")
	@RequestMapping(value = "settleOrderList")
	public String settleOrderList(BizOrderTaskpackageSettlementVo bizOrderTaskpackageSettlementVo,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		if (null == bizOrderTaskpackageSettlementVo.getEnginDepartId()) {
			if (null != UserUtils.getUser().getEmpId()) {
				List<Integer> list = bizEmployeeService2
						.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if (list != null && list.size() > 0) {
					bizOrderTaskpackageSettlementVo.setEnginDepartIds(list);
				} else {
					bizOrderTaskpackageSettlementVo.setEnginDepartIds(null);
				}
			} else {
				bizOrderTaskpackageSettlementVo.setEnginDepartIds(null);
			}
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(bizOrderTaskpackageSettlementVo.getEnginDepartId());
			bizOrderTaskpackageSettlementVo.setEnginDepartIds(list);
		}
		if (UserUtils.getUser().getStoreId() != null) {
			// 当前登录用户门店
			bizOrderTaskpackageSettlementVo.setStoreid(Integer.parseInt(UserUtils.getUser().getStoreId()));
		} else {
			// 门店是总部的查询所有部门信息
			if (bizOrderTaskpackageSettlementVo.getStoreid() != null
					&& bizOrderTaskpackageSettlementVo.getStoreid() == 1) {
				// 总部
				bizOrderTaskpackageSettlementVo.setStoreid(null);
			}
		}

		// 过滤工程模式
		if (StringUtils.isBlank(bizOrderTaskpackageSettlementVo.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizOrderTaskpackageSettlementVo.setProjectMode(be.getProjectMode());
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
						bizOrderTaskpackageSettlementVo.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}

		Page<BizOrderTaskpackageSettlementVo> page = bizOrderTaskpackageSettlementVoService.findPage(
				new Page<BizOrderTaskpackageSettlementVo>(request, response), bizOrderTaskpackageSettlementVo);
		model.addAttribute("page", page);
		model.addAttribute("employeeId", UserUtils.getUser().getEmpId());
		model.addAttribute("bizOrderTaskpackageSettlementVo", bizOrderTaskpackageSettlementVo);
		return "modules/ordertaskpackagesettlement/bizOrderTaskpackageSettlementVoList";
	}

	@RequestMapping(value = "updateTaskpackageStatus")
	public @ResponseBody String updateTaskpackageStatus(Integer orderTaskpackageId, String status, String reason) throws UnsupportedEncodingException {
		// 根据任务包id查询项目经理手机号
		String result="0";
		OrderTaskpackage orderTaskpackage = allotWorkerGroupService.findTargetPackageById(orderTaskpackageId);
		BizOrderTaskpackageSettlement settlement = bizOrderTaskpackageSettlementService
				.findByOrderTaskpackageId(orderTaskpackageId);
		Order2 order = orderService2.findOrderById(Integer.parseInt(orderTaskpackage.getOrderId()));
		BizEmployee2 employee = bizEmployeeService
				.findEmployeeById(Integer.parseInt(orderTaskpackage.getItemManagerId()));
		User user = UserUtils.getUser();
		BizOrderTaskpackageSettlementVo bizOrderTaskpackageSettlementVo = bizOrderTaskpackageSettlementVoService
				.querySettlementByOrderTaskpackageId(orderTaskpackageId);
		if (!bizOrderTaskpackageSettlementVo.getPackageStateid().equals("120")) {
			result = "1";
		} else {
			if ("130".equals(status)) {
				// 结算单审核驳回
				bizOrderTaskpackageSettlementVoService.updateRefusedReason(orderTaskpackageId, reason, new Date(),
						ConstantUtils.ORDER_TASKPACKAGE_STATUSNAME_130, status);
				// 订单（小区名-楼号-单元号-门牌号-客户姓名-手机号）的任务包（任务包名称），结算员（结算员姓名-手机号）结算员驳回，驳回原因：（驳回原因），请及时登录APP查看详情。
				String content = "订单（" + order.getCommunityName() + "-" + order.getBuildNumber() + "-"
						+ order.getBuildUnit() + "-" + order.getBuildRoom() + "-" + order.getCustomerName() + "-"
						+ order.getCustomerPhone() + "）的任务包（" + orderTaskpackage.getPackageName() + "），结算员（"
						+ user.getName() + "-" + user.getPhone() + "）结算员驳回，驳回原因：（" + reason + "），请及时登录APP查看详情。";
				BizPhoneMsg phoneMsg = new BizPhoneMsg();
				phoneMsg.setReceiveEmployeeId(employee.getId());
				phoneMsg.setReceivePhone(employee.getPhone());
				phoneMsg.setMsgContent(content);
				phoneMsg.setMsgGenerateDatetime(new Date());
				phoneMsg.setMsgStatus(ConstantUtils.SEND_MSG_STATUS_0);
				phoneMsg.setRelatedBusinessIdInt(settlement.getId());
				phoneMsg.setRelatedBusinessType(SendMsgBusinessType.RELATED_BUSINESS_TYPE_201002);
				phoneMsg.preInsert();
				bizPhoneMsgService.insert(phoneMsg);

				Message message = new Message();
				message.setMsgTitle("结算员审核驳回");
				message.setMsgTime(new Date());
				message.setMsgContent(content);
				message.setMsgType(MessagePushType.MSG_TYPE_1);
				message.setBusiType(MessagePushType.MESSAGE_PUSH_TYPE_100008002);
				message.setEmployeeId(employee.getId());
				message.setBusiIdInt(settlement.getId());
				messageService.insert(message);

			} else {
				// 结算单审核通过 --修改结算单信息
				bizOrderTaskpackageSettlementVoService.updateStatusDate(orderTaskpackageId,
						ConstantUtils.ORDER_TASKPACKAGE_STATUSNAME_140, status);

				// 订单（小区名-楼号-单元号-门牌号-客户姓名-手机号）的任务包（任务包名称），结算员（结算员姓名-手机号）结算员任务包审核通过，请登录APP查看详情。
				String content = "订单（" + order.getCommunityName() + "-" + order.getBuildNumber() + "-"
						+ order.getBuildUnit() + "-" + order.getBuildRoom() + "-" + order.getCustomerName() + "-"
						+ order.getCustomerPhone() + "）的任务包（" + orderTaskpackage.getPackageName() + "），结算员（"
						+ user.getName() + "-" + user.getPhone() + "）结算员任务包审核通过，请登录APP查看详情。";
				Message message = new Message();
				message.setMsgTitle("结算员审核通过");
				message.setMsgTime(new Date());
				message.setMsgContent(content);
				message.setMsgType(MessagePushType.MSG_TYPE_1);
				message.setBusiType(MessagePushType.MESSAGE_PUSH_TYPE_100008001);
				message.setEmployeeId(employee.getId());
				message.setBusiIdInt(settlement.getId());
				messageService.insert(message);
			}
		}
		/*return "redirect:" + Global.getAdminPath()
				+ "/ordertaskpackagesettlement/bizOrderTaskpackageSettlementVo/settleOrderList";*/
		return result;
	}

	/**
	 * @param id
	 *            结算单id
	 * @return
	 */
	@RequiresPermissions("ordertaskpackagesettlement:bizOrderTaskpackageSettlementVo:view")
	@RequestMapping(value = "details")
	public String details(Integer id, Model model) {
		DecimalFormat df = new DecimalFormat("#.00");
		double totalMoney = 0;
		double totalPrice = 0;
		double sandTotalPrice = 0;
		BizOrderTaskpackageSettlementVo bizOrderTaskpackageSettlementVo = bizOrderTaskpackageSettlementVoService
				.findSettlementById(id);

		Integer packageId = bizOrderTaskpackageSettlementVo.getOrderTaskpackageId();

		List<BizOrderTaskpackageProcedure> procedures = bizOrderTaskpackageProcedureService
				.queryOrderTaskpackageProcedure(packageId);
		for (BizOrderTaskpackageProcedure procedure : procedures) {
			procedure.setBudgetTotal(procedure.getBudgetNumber() * procedure.getSynthesizePrice());
			totalMoney = totalMoney + procedure.getLaborAuxiliaryMaterialsBudgetAmount();
		}
		totalMoney = Double.parseDouble(df.format(totalMoney));
		List<SettlementAuxiliary> auxiliarys = settlementAuxiliaryService.findAuxiliaryListForSettlement(packageId);
		for (SettlementAuxiliary settlementAuxiliary : auxiliarys) {
			totalPrice = settlementAuxiliary.getPrice() + totalPrice;
		}
		totalPrice = Double.parseDouble(df.format(totalPrice));

		List<SettlementAuxiliary> sands = settlementAuxiliaryService.findSandListForSettlement(packageId);
		for (SettlementAuxiliary settlementAuxiliary : sands) {
			sandTotalPrice = settlementAuxiliary.getPrice() + sandTotalPrice;
		}

		// 根据结算id查找质保金
		Guarantee guarantee = guaranteeService.findGuaranteeBySettlementId(id);
		List<OrderTaskpackageSettlementDetail> payments = orderTaskpackageSettlementDetailService
				.findByOrderTaskpackageId(packageId);
		for (OrderTaskpackageSettlementDetail orderTaskpackageSettlementDetail : payments) {
			BizEmployee2 employee = bizEmployeeService2.get(orderTaskpackageSettlementDetail.getEmployeeId());
			orderTaskpackageSettlementDetail.setEmployeeName(employee.getRealname());
		}

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("relatedBusinessId", bizOrderTaskpackageSettlementVo.getOrderTaskpackageId());
		param.put("evalType", "1");
		EvalScore bizEvalTaskpackScore = bizEvalTaskpackScoreService.queryEvalScoreByBusinessId(param);

		// 奖励金额
		EmployeeRewardDetail employeeRewardDetail = bizEvalRewardTaskpackService.queryEmployeeRewardDetail(packageId);

		model.addAttribute("payments", payments);
		model.addAttribute("totalPrice", totalPrice);
		model.addAttribute("auxiliarys", auxiliarys);
		model.addAttribute("sandTotalPrice", sandTotalPrice);
		model.addAttribute("sands", sands);
		model.addAttribute("procedures", procedures);
		model.addAttribute("totalMoney", totalMoney);
		model.addAttribute("employeeRewardDetail", employeeRewardDetail);
		model.addAttribute("bizOrderTaskpackageSettlementVo", bizOrderTaskpackageSettlementVo);
		model.addAttribute("guarantee", guarantee);
		model.addAttribute("bizEvalTaskpackScore", bizEvalTaskpackScore);
		return "modules/ordertaskpackagesettlement/details2";
	}

	@RequiresPermissions("ordertaskpackagesettlement:bizOrderTaskpackageSettlementVo:view")
	@RequestMapping(value = "settlementList")
	public String settlementList(BizOrderTaskpackageSettlementVo bizOrderTaskpackageSettlementVo,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		// 过滤门店
		if (bizOrderTaskpackageSettlementVo.getStoreid() == null) {
			String storeId = UserUtils.getUser().getStoreId();
			if (StringUtils.isBlank(storeId)) {
				bizOrderTaskpackageSettlementVo.setStoreid(null);
			} else {
				bizOrderTaskpackageSettlementVo.setStoreid(Integer.parseInt(storeId));
			}
		}
		if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}

		// 过滤工程模式
		if (StringUtils.isBlank(bizOrderTaskpackageSettlementVo.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizOrderTaskpackageSettlementVo.setProjectMode(be.getProjectMode());
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
						bizOrderTaskpackageSettlementVo.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}
		return "modules/ordertaskpackagesettlement/bizOrderTaskpackageSettlementList";
	}

	@RequiresPermissions("ordertaskpackagesettlement:bizOrderTaskpackageSettlementVo:view")
	@RequestMapping(value = "settlementLoadList")
	public String settlementLoadList(BizOrderTaskpackageSettlementVo bizOrderTaskpackageSettlementVo,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		// 过滤门店
		if (bizOrderTaskpackageSettlementVo.getStoreid() == null) {
			String storeId = UserUtils.getUser().getStoreId();
			if (StringUtils.isBlank(storeId)) {
				bizOrderTaskpackageSettlementVo.setStoreid(null);
			} else {
				bizOrderTaskpackageSettlementVo.setStoreid(Integer.parseInt(storeId));
			}
		}
		if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}

		// 过滤工程模式
		if (StringUtils.isBlank(bizOrderTaskpackageSettlementVo.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizOrderTaskpackageSettlementVo.setProjectMode(be.getProjectMode());
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
						bizOrderTaskpackageSettlementVo.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}

		// 区域
		if (bizOrderTaskpackageSettlementVo.getEnginDepartId() == null) {
			if (StringUtils.isNoneBlank(UserUtils.getUser().getEmpId())) {
				List<Integer> list = bizEmployeeService2
						.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if (CollectionUtils.isNotEmpty(list)) {
					bizOrderTaskpackageSettlementVo.setEnginDepartIds(list);
				} else {
					bizOrderTaskpackageSettlementVo.setEnginDepartIds(null);
				}
			} else {
				bizOrderTaskpackageSettlementVo.setEnginDepartIds(null);
			}
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(bizOrderTaskpackageSettlementVo.getEnginDepartId());
			bizOrderTaskpackageSettlementVo.setEnginDepartIds(list);
		}
		Page<BizOrderTaskpackageSettlementVo> page = bizOrderTaskpackageSettlementVoService.findSettlementPage(
				new Page<BizOrderTaskpackageSettlementVo>(request, response), bizOrderTaskpackageSettlementVo);
		model.addAttribute("page", page);
		return "modules/ordertaskpackagesettlement/bizOrderTaskpackageSettlementList";
	}

	@RequiresPermissions("ordertaskpackagesettlement:bizOrderTaskpackageSettlementVo:view")
	@RequestMapping(value = "toUpdateSettlement")
	public String toUpdateSettlement(Integer id, Model model) {

		BizOrderTaskpackageSettlementVo bizOrderTaskpackageSettlementVo = bizOrderTaskpackageSettlementVoService
				.findSettlementById(id);
		List<BizOrderTaskpackageProcedure> procedures = bizOrderTaskpackageProcedureService
				.queryOrderTaskpackageProcedure(bizOrderTaskpackageSettlementVo.getOrderTaskpackageId());

		if (ConstantUtils.IS_QUALITY_GUARANTEE_YES.equals(bizOrderTaskpackageSettlementVo.getIsQualityGuarantee())) {
			/*int count = guaranteeMoneyService.queryGuaranteeMoneyCount(
					bizOrderTaskpackageSettlementVo.getTaskPackageTemplatId(),
					bizOrderTaskpackageSettlementVo.getGroupId(), id);*/
			// 计算累积质保金
			Double guaranteeMoneySum = guaranteeMoneyService
					.queryGuaranteeMoneySum(bizOrderTaskpackageSettlementVo.getGroupId(), id);
			bizOrderTaskpackageSettlementVo.setGuaranteeMoneyAmountTotal(guaranteeMoneySum);
			// 查询质保金余额
			BizGuaranteeMoneyBalance bizGuaranteeMoneyBalance = bizGuaranteeMoneyBalanceService
					.findGuaranteeMoneyBalanceByEmployeeId(bizOrderTaskpackageSettlementVo.getGroupId());
			if (bizGuaranteeMoneyBalance == null) {
				bizGuaranteeMoneyBalance = new BizGuaranteeMoneyBalance();
			}
			if (bizOrderTaskpackageSettlementVo.getGuaranteeMoneyAmount() == null) {
				bizOrderTaskpackageSettlementVo.setGuaranteeMoneyAmount(0.0);
			}
			if (bizGuaranteeMoneyBalance.getGuaranteeMoneyBalance()
					- bizOrderTaskpackageSettlementVo.getGuaranteeMoneyAmount() < 0) {
				bizOrderTaskpackageSettlementVo.setGuaranteeMoneyBalance(0d);
			} else {
				bizOrderTaskpackageSettlementVo
						.setGuaranteeMoneyBalance(bizGuaranteeMoneyBalance.getGuaranteeMoneyBalance());
			}
			//2017-11-23  工人质保金改为从第一个任务包就扣除质保金
			bizOrderTaskpackageSettlementVo.setGualityGuaranteeType(3);
			/*if (count < 2) {
				bizOrderTaskpackageSettlementVo.setGualityGuaranteeType(2);
			} else {
				bizOrderTaskpackageSettlementVo.setGualityGuaranteeType(3);
			}*/
		} else {
			bizOrderTaskpackageSettlementVo.setGualityGuaranteeType(1);
		}

		// 质检罚款
		Double qcAmount = settlementService
				.queryQcWorkerPublishAmountTotal(bizOrderTaskpackageSettlementVo.getOrderTaskpackageId());

		model.addAttribute("qcAmount", qcAmount);
		model.addAttribute("procedures", procedures);
		model.addAttribute("bizOrderTaskpackageSettlementVo", bizOrderTaskpackageSettlementVo);
		return "modules/ordertaskpackagesettlement/bizOrderTaskpackageSettlementForm";
	}

	@RequiresPermissions("ordertaskpackagesettlement:bizOrderTaskpackageSettlementVo:edit")
	@RequestMapping(value = "updateSettlement")
	public String updateSettlement(OrderTaskpackageSettlement settlement, RedirectAttributes redirectAttributes) {
		bizOrderTaskpackageSettlementVoService.updateSettlement(settlement);
		addMessage(redirectAttributes, "任务包工序清单修改成功");
		return "redirect:" + Global.getAdminPath()
				+ "/ordertaskpackagesettlement/bizOrderTaskpackageSettlementVo/settlementLoadList";
	}

	@RequiresPermissions("ordertaskpackagesettlement:bizOrderTaskpackageSettlementVo:view")
	@RequestMapping(value = "settlementDetailList")
	public String settlementDetailList(BizOrderTaskpackageSettlementVo bizOrderTaskpackageSettlementVo, Model model) {
		if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}

		model.addAttribute("bizOrderTaskpackageSettlementVo", bizOrderTaskpackageSettlementVo);
		return "modules/ordertaskpackagesettlement/bizOrderTaskpackageSettlementAllList";
	}

	@RequiresPermissions("ordertaskpackagesettlement:bizOrderTaskpackageSettlementVo:view")
	@RequestMapping(value = "settlementDetailLoadList")
	public String settlementDetailLoadList(BizOrderTaskpackageSettlementVo bizOrderTaskpackageSettlementVo,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		// 过滤门店
		if (bizOrderTaskpackageSettlementVo.getStoreid() == null) {
			String storeId = UserUtils.getUser().getStoreId();
			if (StringUtils.isBlank(storeId)) {
				bizOrderTaskpackageSettlementVo.setStoreid(null);
			} else {
				bizOrderTaskpackageSettlementVo.setStoreid(Integer.parseInt(storeId));
			}
		}
		if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}

		Page<BizOrderTaskpackageSettlementVo> page = bizOrderTaskpackageSettlementVoService.findSettlementAllPage(
				new Page<BizOrderTaskpackageSettlementVo>(request, response), bizOrderTaskpackageSettlementVo);
		model.addAttribute("page", page);
		model.addAttribute("bizOrderTaskpackageSettlementVo", bizOrderTaskpackageSettlementVo);
		return "modules/ordertaskpackagesettlement/bizOrderTaskpackageSettlementAllList";
	}

	@RequiresPermissions("ordertaskpackagesettlement:bizOrderTaskpackageSettlementVo:view")
	@RequestMapping(value = "settlementTaskList")
	public String settlementTaskList(BizOrderTaskpackageSettlementVo bizOrderTaskpackageSettlementVo, Model model) {
		User user = UserUtils.getUser();
		// 过滤门店
		if (bizOrderTaskpackageSettlementVo.getStoreid() == null) {
			String storeId = UserUtils.getUser().getStoreId();
			if (StringUtils.isBlank(storeId)) {
				bizOrderTaskpackageSettlementVo.setStoreid(null);
			} else {
				bizOrderTaskpackageSettlementVo.setStoreid(Integer.parseInt(storeId));
			}
		}
		if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}

		// 过滤工程模式
		if (StringUtils.isBlank(bizOrderTaskpackageSettlementVo.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizOrderTaskpackageSettlementVo.setProjectMode(be.getProjectMode());
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
						bizOrderTaskpackageSettlementVo.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}

		// 区域
		if (bizOrderTaskpackageSettlementVo.getEnginDepartId() == null) {
			if (StringUtils.isNoneBlank(UserUtils.getUser().getEmpId())) {
				List<Integer> list = bizEmployeeService2
						.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if (CollectionUtils.isNotEmpty(list)) {
					bizOrderTaskpackageSettlementVo.setEnginDepartIds(list);
				} else {
					bizOrderTaskpackageSettlementVo.setEnginDepartIds(null);
				}
			} else {
				bizOrderTaskpackageSettlementVo.setEnginDepartIds(null);
			}
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(bizOrderTaskpackageSettlementVo.getEnginDepartId());
			bizOrderTaskpackageSettlementVo.setEnginDepartIds(list);
		}

		model.addAttribute("bizOrderTaskpackageSettlementVo", bizOrderTaskpackageSettlementVo);
		return "modules/ordertaskpackagesettlement/bizOrderTaskpackageSettlementTaskList";
	}

	@RequiresPermissions("ordertaskpackagesettlement:bizOrderTaskpackageSettlementVo:view")
	@RequestMapping(value = "settlementTaskLoadList")
	public String settlementTaskLoadList(BizOrderTaskpackageSettlementVo bizOrderTaskpackageSettlementVo,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		// 过滤门店
		if (bizOrderTaskpackageSettlementVo.getStoreid() == null) {
			String storeId = UserUtils.getUser().getStoreId();
			if (StringUtils.isBlank(storeId)) {
				bizOrderTaskpackageSettlementVo.setStoreid(null);
			} else {
				bizOrderTaskpackageSettlementVo.setStoreid(Integer.parseInt(storeId));
			}
		}
		if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}

		// 过滤工程模式
		if (StringUtils.isBlank(bizOrderTaskpackageSettlementVo.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizOrderTaskpackageSettlementVo.setProjectMode(be.getProjectMode());
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
						bizOrderTaskpackageSettlementVo.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}

		// 区域
		if (bizOrderTaskpackageSettlementVo.getEnginDepartId() == null) {
			if (StringUtils.isNoneBlank(UserUtils.getUser().getEmpId())) {
				List<Integer> list = bizEmployeeService2
						.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if (CollectionUtils.isNotEmpty(list)) {
					bizOrderTaskpackageSettlementVo.setEnginDepartIds(list);
				} else {
					bizOrderTaskpackageSettlementVo.setEnginDepartIds(null);
				}
			} else {
				bizOrderTaskpackageSettlementVo.setEnginDepartIds(null);
			}
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(bizOrderTaskpackageSettlementVo.getEnginDepartId());
			bizOrderTaskpackageSettlementVo.setEnginDepartIds(list);
		}

		Page<BizOrderTaskpackageSettlementVo> page = bizOrderTaskpackageSettlementVoService.findSettlementTaskPage(
				new Page<BizOrderTaskpackageSettlementVo>(request, response), bizOrderTaskpackageSettlementVo);
		model.addAttribute("page", page);
		model.addAttribute("bizOrderTaskpackageSettlementVo", bizOrderTaskpackageSettlementVo);
		return "modules/ordertaskpackagesettlement/bizOrderTaskpackageSettlementTaskList";
	}

	/**
	 * 查询可撤回的结算单
	 * 
	 * @param bizOrderTaskpackageSettlementVo
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "querySettleCancelList")
	public String querySettleCancelList(BizOrderTaskpackageSettlementVo bizOrderTaskpackageSettlementVo,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		// 过滤门店
		if (bizOrderTaskpackageSettlementVo.getStoreid() == null) {
			String storeId = UserUtils.getUser().getStoreId();
			if (StringUtils.isBlank(storeId)) {
				bizOrderTaskpackageSettlementVo.setStoreid(null);
			} else {
				bizOrderTaskpackageSettlementVo.setStoreid(Integer.parseInt(storeId));
			}
		}
		if (StringUtils.isBlank(UserUtils.getUser().getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}

		// 过滤工程模式
		if (StringUtils.isBlank(bizOrderTaskpackageSettlementVo.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						bizOrderTaskpackageSettlementVo.setProjectMode(be.getProjectMode());
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
						bizOrderTaskpackageSettlementVo.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}

		// 区域
		if (bizOrderTaskpackageSettlementVo.getEnginDepartId() == null) {
			if (StringUtils.isNoneBlank(UserUtils.getUser().getEmpId())) {
				List<Integer> list = bizEmployeeService2
						.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if (CollectionUtils.isNotEmpty(list)) {
					bizOrderTaskpackageSettlementVo.setEnginDepartIds(list);
				} else {
					bizOrderTaskpackageSettlementVo.setEnginDepartIds(null);
				}
			} else {
				bizOrderTaskpackageSettlementVo.setEnginDepartIds(null);
			}
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(bizOrderTaskpackageSettlementVo.getEnginDepartId());
			bizOrderTaskpackageSettlementVo.setEnginDepartIds(list);
		}
		Page<BizOrderTaskpackageSettlementVo> page = bizOrderTaskpackageSettlementVoService.querySettleCancel(
				new Page<BizOrderTaskpackageSettlementVo>(request, response), bizOrderTaskpackageSettlementVo);
		model.addAttribute("page", page);
		model.addAttribute("bizOrderTaskpackageSettlementVo", bizOrderTaskpackageSettlementVo);
		return "modules/ordertaskpackagesettlement/bizOrderTaskpackageSettlementCancelList";
	}

	/**
	 * 结算单撤回
	 * 
	 * @return
	 */
	@RequestMapping(value = "settleCancel")
	public @ResponseBody String settleCancel(Integer id, String operateRemarks) {
		String result = null;
		try {
			result = bizOrderTaskpackageSettlementVoService.settleCancel(id, operateRemarks);
		} catch (Exception e) {
			result = "error";
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@RequestMapping(value = "findPaymentVoBySettlementId")
	public String findPaymentVoBySettlementId(Integer id, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		List<BizOrderTaskpackagePaymentVo> list = bizOrderTaskpackagePaymentService.findPaymentVoBySettlementId(id);
		model.addAttribute("list", list);
		return "modules/ordertaskpackagepayment/bizOrderTaskpackageCancelPaymentList";
	}
}
