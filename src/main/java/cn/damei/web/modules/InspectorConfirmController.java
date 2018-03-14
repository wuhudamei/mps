/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.web.modules;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import cn.damei.common.utils.PicRootName;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.mobile.Inspector.CheckConfirm;
import cn.damei.service.mobile.Inspector.CheckConfirmService;
import cn.damei.entity.modules.BizAssessRewardPunish;
import cn.damei.service.modules.BizAssessRewardPunishService;
import cn.damei.entity.modules.ReportCheckDetailsPic;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.BizOrderMaterialsStandard;
import cn.damei.entity.modules.BizMaterialsStandardReceiveBill;
import cn.damei.service.modules.BizOrderTaskpackagePaymentService;
import cn.damei.entity.modules.PmMaterialsSettleInfo;
import cn.damei.service.modules.PmMaterialsSettleInfoService;
import cn.damei.entity.modules.BizPmOwnpayCnfgSnap;
import cn.damei.entity.modules.BizPmStarCommissionCnfgSnap;
import cn.damei.entity.modules.InspectorConfirm;
import cn.damei.entity.modules.Payment;
import cn.damei.entity.modules.QcBillCheckItemInfo;
import cn.damei.service.modules.InspectorConfirmService;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;

/**
 * 结算员审核验收单Controller
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/settlementPaymentManagement/checkAccept/confirm")
public class InspectorConfirmController extends BaseController {

	@Autowired
	private BizOrderTaskpackagePaymentService bizOrderTaskpackagePaymentService;
	@Autowired
	private InspectorConfirmService inspectorConfirmService;
	@Autowired
	private CheckConfirmService checkConfirmService;
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	@Autowired
	private PmMaterialsSettleInfoService pmMaterialsSettleInfoService;
	@Autowired
	private BizAssessRewardPunishService bizAssessRewardPunishService;

	@ModelAttribute
	public InspectorConfirm get(@RequestParam(required = false) String id) {
		InspectorConfirm entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = inspectorConfirmService.get(id);
		}
		if (entity == null) {
			entity = new InspectorConfirm();
		}
		return entity;
	}

	@RequiresPermissions("confirm:confirm:view")
	@RequestMapping(value = { "list", "" })
	public String list(InspectorConfirm inspectorConfirm, HttpServletRequest request, HttpServletResponse response,
					   Model model) {

		User user = UserUtils.getUser();
		if (null == inspectorConfirm.getEnginDepartId()) {
			if (null != UserUtils.getUser().getEmpId()) {
				List<Integer> list = bizEmployeeService2
						.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if (list != null && list.size() > 0) {
					inspectorConfirm.setEnginDepartIds(list);
				} else {
					inspectorConfirm.setEnginDepartIds(null);
				}
			} else {
				inspectorConfirm.setEnginDepartIds(null);
			}
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(inspectorConfirm.getEnginDepartId());
			inspectorConfirm.setEnginDepartIds(list);
		}
		// 过滤门店
		if (null == inspectorConfirm.getStoreId()) {
			if (null != user.getStoreId()) {
				inspectorConfirm.setStoreId(user.getStoreId());
			}
		}
		if (StringUtils.isBlank(user.getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}

		// 过滤工程模式
		if (StringUtils.isBlank(inspectorConfirm.getProjectMode())) {
			if (null != user.getEmpId()) {
				BizEmployee2 be = bizEmployeeService2.get(Integer.parseInt(user.getEmpId()));
				if (StringUtils.isBlank(be.getProjectMode())) {
					model.addAttribute("gongcheng", true);
				} else {
					if (be.getProjectMode().equals("3")) {
						model.addAttribute("gongcheng", true);
					} else {
						inspectorConfirm.setProjectMode(be.getProjectMode());
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
						inspectorConfirm.setProjectMode(be.getProjectMode());
					}
				}
			} else {
				model.addAttribute("gongcheng", true);
			}
		}
		model.addAttribute("employeeId", UserUtils.getUser().getEmpId());
		model.addAttribute("inspectorConfirm", inspectorConfirm);
		return "modules/settlementPaymentManagement/checkAccept/confirm/confirmList";
	}

	@RequiresPermissions("confirm:confirm:view")
	@RequestMapping(value = { "list2", "" })
	public String list2(InspectorConfirm inspectorConfirm, HttpServletRequest request, HttpServletResponse response,
						Model model) {
		User user = UserUtils.getUser();

		if (null == inspectorConfirm.getEnginDepartId()) {
			if (null != UserUtils.getUser().getEmpId()) {
				List<Integer> list = bizEmployeeService2
						.findEngineIdsByEmpId(Integer.parseInt(UserUtils.getUser().getEmpId()));
				if (list != null && list.size() > 0) {
					inspectorConfirm.setEnginDepartIds(list);
				} else {
					inspectorConfirm.setEnginDepartIds(null);
				}
			} else {
				inspectorConfirm.setEnginDepartIds(null);
			}
		} else {
			List<Integer> list = new ArrayList<>();
			list.add(inspectorConfirm.getEnginDepartId());
			inspectorConfirm.setEnginDepartIds(list);
		}

		// 过滤门店
		if (null == inspectorConfirm.getStoreId()) {
			if (null != user.getStoreId()) {
				inspectorConfirm.setStoreId(user.getStoreId());
			}
		}
		if (StringUtils.isBlank(user.getStoreId())) {
			model.addAttribute("storeDropEnable", true);
		}
		// 过滤工程模式
		if (StringUtils.isBlank(inspectorConfirm.getProjectMode())) {
			if (StringUtils.isBlank(user.getProjectMode()) || user.getProjectMode().equals("3")) {
				model.addAttribute("gongcheng", true);
			} else {
				inspectorConfirm.setProjectMode(user.getProjectMode());
			}
		} else {
			if (StringUtils.isBlank(user.getProjectMode()) || user.getProjectMode().equals("3")) {
				model.addAttribute("gongcheng", true);
			} else {
				inspectorConfirm.setProjectMode(user.getProjectMode());
			}
		}
		inspectorConfirm.setIsRecheck("0");
		inspectorConfirm.setQcBillType("1");
		inspectorConfirm.setStatus("10");

		Page<InspectorConfirm> page = inspectorConfirmService.findPage(new Page<InspectorConfirm>(request, response),
				inspectorConfirm);
		model.addAttribute("page", page);
		model.addAttribute("employeeId", UserUtils.getUser().getEmpId());
		model.addAttribute("inspectorConfirm", inspectorConfirm);
		return "modules/settlementPaymentManagement/checkAccept/confirm/confirmList";
	}

/*	*//**
	 * 查看验收图片
	 *
	 * @param qcBillId
	 * @param model
	 * @return
	 * @throws Exception
	 *//*
	@RequiresPermissions("confirm:confirm:view")
	@RequestMapping(value = "pic")
	public String pic(Integer qcBillId, Model model) throws Exception {

		ReportCheckDetailsPic reportCheckDetailsPic = new ReportCheckDetailsPic();
		reportCheckDetailsPic.setBusinessType("3");
		reportCheckDetailsPic.setBusinessIdInt(qcBillId);
		// 验收图片
		List<ReportCheckDetailsPic> picList = inspectorConfirmService.findPic(reportCheckDetailsPic);

		model.addAttribute("picList", picList);
		model.addAttribute("baseUrl", PicRootName.picPrefixName());

		return "modules/settlementPaymentManagement/checkAccept/confirm/pic";
	}*/

	/**
	 * Ajax查看验收图片
	 *
	 * @param qcBillId
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("confirm:confirm:view")
	@RequestMapping(value = "/pic")
	@ResponseBody
	public Map<Object, Object> pic(Integer qcBillId, Model model) throws Exception {

		ReportCheckDetailsPic reportCheckDetailsPic = new ReportCheckDetailsPic();
		reportCheckDetailsPic.setBusinessType("3");
		reportCheckDetailsPic.setBusinessIdInt(qcBillId);
		// 验收图片
		List<ReportCheckDetailsPic> picList = inspectorConfirmService.findPic(reportCheckDetailsPic);

		model.addAttribute("picList", picList);
		model.addAttribute("baseUrl", PicRootName.picPrefixName());
		Map<Object, Object> mapObject = new HashMap<Object, Object>();
		mapObject.put("mapObject", picList);
		return mapObject;
	}

	/**
	 * 查看尾款付款单
	 *
	 * @param qcBillId
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("confirm:confirm:view")
	@RequestMapping(value = "payment")
	public String payment(Integer qcBillId, Model model) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		// map.put("status", ConstantUtils.PAYMENT_STATUS_10);
		// map.put("qcStatus", "10");
		map.put("qcType", "1");
		map.put("ischeck", "0");
		map.put("orderTaskpackagePaymentType", ConstantUtils.PAYMENT_TYPE_1);
		map.put("qcBillId", qcBillId);
		// map.put("cnrStatus", "1");//任务包和之间节点关系状态--启用
		List<Payment> paymentList = bizOrderTaskpackagePaymentService.findPayments(map);
		// List<Payment> paymentList = null;
		model.addAttribute("paymentList", paymentList);
		return "modules/settlementPaymentManagement/checkAccept/confirm/paymentDetails";
	}

	/**
	 * 项目经理中期提成明细
	 *
	 * @return
	 */
	@RequestMapping(value = "managerAmortizationDetail")
	public String managerAmortizationDetail(Integer orderId, Integer qcBillId, Model model) {
		BizPmStarCommissionCnfgSnap bizPmStarCommissionCnfgSnap = inspectorConfirmService
				.queryManagerCommissionByOrderId(orderId);
		CheckConfirm settlementCount = checkConfirmService.findSettlement(qcBillId);
		if (settlementCount.getManagerCount() != 0) {
			// 中期提成
			Double commissionAmount = bizPmStarCommissionCnfgSnap.getCommissionAmount()
					* bizPmStarCommissionCnfgSnap.getCommissionRateMidway();
			// 标化材料扣款金额
			List<BizMaterialsStandardReceiveBill> list = inspectorConfirmService.findThree(orderId);
			BizMaterialsStandardReceiveBill details3 = new BizMaterialsStandardReceiveBill();
			details3.setReceiveBillAmount(0.0);
			if (list != null && list.size() > 0) {
				for (BizMaterialsStandardReceiveBill bill : list) {
					details3.setReceiveBillAmount(details3.getReceiveBillAmount() + bill.getReceiveBillAmount());
				}
			}

			if (details3.getReceiveBillAmount() < 0) {
				details3.setReceiveBillAmount((0 - details3.getReceiveBillAmount()));
			}
			List<BizOrderMaterialsStandard> materialsStandardList = inspectorConfirmService
					.queryMaterialsStandardByOrderId(orderId);
			// 自主支配金额
			Double managerOwnpay = inspectorConfirmService.queryManagerOwnpay(orderId);
			List<BizPmOwnpayCnfgSnap> pmOwnpayCnfgSnapList = inspectorConfirmService
					.queryPmOwnpayCnfgSnapByOrderId(orderId);
			// 中期罚款
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orderId", orderId);
			map.put("settleCategory", ConstantUtils.PM_SETTLE_CATEGORY_4);
			map.put("settleStatus", 10);
			map.put("settleRole", ConstantUtils.SETTLE_ROLE_1);
			Double managerPenalty = inspectorConfirmService.queryManangerPenalty(map);

			List<QcBillCheckItemInfo> pmPunishList = inspectorConfirmService.queryPmPunishByParam(map);
			if (managerPenalty < 0) {
				managerPenalty = 0 - managerPenalty;
			}
			// 中期任务包材料结算总金额
			double pmMaterialsSettleAmount = 0.00;
			List<PmMaterialsSettleInfo> pmMaterials = pmMaterialsSettleInfoService.queryPmMaterialsByOrderId(orderId);
			if (pmMaterials == null) {
				pmMaterials = new ArrayList<PmMaterialsSettleInfo>();
			}
			if (pmMaterials != null && pmMaterials.size() > 0) {
				for (PmMaterialsSettleInfo info : pmMaterials) {
					pmMaterialsSettleAmount = pmMaterialsSettleAmount + info.getPmMaterialsSettleAmount();
				}
			}

			// 中期奖励金额
			double pmRewardAmount = 0.0;
			BizAssessRewardPunish rewardPunish = new BizAssessRewardPunish();
			rewardPunish.setRelatedBusinessIdInt(orderId);
			rewardPunish.setRewardPunishTargetEmployeeId(bizPmStarCommissionCnfgSnap.getPmEmployeeId());
			rewardPunish.setRewardPunishTargetEmployeeType("1");
			rewardPunish.setRewardPunishTargetType("10");
			rewardPunish.setIsRewardOrPunish("1");
			rewardPunish.setIsMonthInspection("0");
			rewardPunish.setRewardPunishStatus("1");
			pmRewardAmount = bizAssessRewardPunishService.queryTotalAmountByParam(rewardPunish);
			// 中期扣款金额
			double pmPunishAmount = 0.0;
			rewardPunish.setIsRewardOrPunish("2");
			pmPunishAmount = bizAssessRewardPunishService.queryTotalAmountByParam(rewardPunish);

			//巡检
			rewardPunish.setIsMonthInspection("1");
			//中期巡检奖励
			double pmInspectionRewardAmount = 0.0;
			rewardPunish.setIsRewardOrPunish("1");
			pmInspectionRewardAmount = bizAssessRewardPunishService.queryTotalAmountByParam(rewardPunish);
			//中期巡检罚款
			double pmInspectionPunishAmount = 0.0;
			rewardPunish.setIsRewardOrPunish("2");
			pmInspectionPunishAmount = bizAssessRewardPunishService.queryTotalAmountByParam(rewardPunish);

			double settleAmount = commissionAmount + managerOwnpay - managerPenalty - details3.getReceiveBillAmount()
					+ pmMaterialsSettleAmount + pmRewardAmount - pmPunishAmount + pmInspectionRewardAmount - pmInspectionPunishAmount;

			model.addAttribute("pmMaterialsSettleAmount", pmMaterialsSettleAmount);
			model.addAttribute("pmMaterials", pmMaterials);
			model.addAttribute("commissionAmount", commissionAmount);
			model.addAttribute("settleAmount", settleAmount);
			model.addAttribute("details3", details3);
			model.addAttribute("managerOwnpay", managerOwnpay);
			model.addAttribute("managerPenalty", managerPenalty);
			model.addAttribute("materialsStandardList", materialsStandardList);
			model.addAttribute("pmOwnpayCnfgSnapList", pmOwnpayCnfgSnapList);
			model.addAttribute("pmPunishList", pmPunishList);
			model.addAttribute("pmRewardAmount", pmRewardAmount);
			model.addAttribute("pmPunishAmount", pmPunishAmount);
			model.addAttribute("pmInspectionRewardAmount", pmInspectionRewardAmount);
			model.addAttribute("pmInspectionPunishAmount", pmInspectionPunishAmount);
		}

		model.addAttribute("bizPmStarCommissionCnfgSnap", bizPmStarCommissionCnfgSnap);
		model.addAttribute("settlementCount", settlementCount);
		return "modules/settlementPaymentManagement/checkAccept/confirm/managerAmortizationDetail";
	}

	/**
	 * 约检验收单审核--通过
	 *
	 * @param qcBillId
	 * @param
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 * @throws Exception
	 */
	// @RequiresPermissions("confirm:confirm:view")
	@RequestMapping(value = "pass")
	public @ResponseBody String pass(Integer orderId, Integer qcBillId, Integer qcCheckNodeId,
									 RedirectAttributes redirectAttributes) throws Exception {
		String result = "0";
		try {
			result = inspectorConfirmService.pass(orderId, qcBillId, qcCheckNodeId);
		} catch (Exception e) {
			result = "1";
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	/**
	 * 约检验收单审核--驳回
	 *
	 * @param qcBillId
	 * @param
	 * @return
	 */
	@RequiresPermissions("confirm:confirm:view")
	@RequestMapping(value = "reject")
	public String reject(Integer qcBillId, String reason, RedirectAttributes redirectAttributes) {

		InspectorConfirm inspectorConfirm = new InspectorConfirm();
		inspectorConfirm.setQcBillId(qcBillId);
		inspectorConfirm.setStatus("20");
		inspectorConfirm.setReviewStatus("0");
		inspectorConfirm.setReviewRemark(reason);
		inspectorConfirm.setReviewDatetime(new Date());
		// 更新约检验收单
		inspectorConfirmService.updateQcBill(inspectorConfirm);

		addMessage(redirectAttributes, "约检验收单审核驳回");
		return "redirect:" + Global.getAdminPath() + "/settlementPaymentManagement/checkAccept/confirm/list2?repage";
	}

}