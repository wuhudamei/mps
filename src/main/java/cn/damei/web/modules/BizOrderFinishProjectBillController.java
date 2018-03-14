package cn.damei.web.modules;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.constantUtils.BusinessLogConstantUtil;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.DateUtils;
import cn.damei.common.utils.MessagePushType;
import cn.damei.common.utils.SendMsgBusinessType;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.web.BaseController;
import cn.damei.common.MD5Utils;
import cn.damei.entity.mobile.Worker.Message;
import cn.damei.service.mobile.Worker.MessageService;
import cn.damei.dao.modules.BizAssessRewardPunishDao;
import cn.damei.entity.modules.BizAssessRewardPunish;
import cn.damei.service.modules.BizAssessRewardPunishService;
import cn.damei.service.modules.BizOrderFinanceCollectionService;
import cn.damei.dao.modules.BizBusinessStatusLogDao;
import cn.damei.entity.modules.BizBusinessStatusLog;
import cn.damei.entity.modules.BizCompletedAudit;
import cn.damei.entity.modules.BizOrderFinishProjectBill;
import cn.damei.service.modules.BizCompletedAuditService;
import cn.damei.service.modules.BizOrderFinishProjectBillService;
import cn.damei.entity.modules.BizQcLongwayCommissionCnfgSnap;
import cn.damei.service.modules.BizQcLongwayCommissionCnfgSnapService;
import cn.damei.entity.modules.BizQcLongwayCommissionLog;
import cn.damei.service.modules.BizQcLongwayCommissionLogService;
import cn.damei.entity.modules.BizQcStarCommissionCnfgSnap;
import cn.damei.service.modules.BizQcStarCommissionCnfgSnapService;
import cn.damei.entity.modules.BizQcStarCommissionLog;
import cn.damei.service.modules.BizQcStarCommissionLogService;
import cn.damei.entity.modules.BizSynData;
import cn.damei.service.modules.BizSynDataService;
import cn.damei.entity.modules.BizEmployee2;
import cn.damei.service.modules.BizEmployeeService2;
import cn.damei.entity.modules.BizMessagegroup;
import cn.damei.service.modules.BizMessagegroupService;
import cn.damei.entity.modules.BizPhoneMsg;
import cn.damei.service.modules.BizPhoneMsgService;
import cn.damei.entity.modules.PmMaterialsSettleInfo;
import cn.damei.service.modules.PmMaterialsSettleInfoService;
import cn.damei.entity.modules.BizPmGuaranteeMoneyLog;
import cn.damei.service.modules.BizPmGuaranteeMoneyLogService;
import cn.damei.entity.modules.BizPmGuaranteeMoneyCnfgSnap;
import cn.damei.service.modules.BizPmGuaranteeMoneyCnfgSnapService;
import cn.damei.entity.modules.BizPmSettleBill;
import cn.damei.service.modules.BizPmSettleBillService;
import cn.damei.entity.modules.BizPmSettleCategoryDetail;
import cn.damei.service.modules.BizPmSettleCategoryDetailService;
import cn.damei.entity.modules.BizPmSettleCategorySummary;
import cn.damei.service.modules.BizPmSettleCategorySummaryService;
import cn.damei.entity.modules.BizPmStarCommissionLog;
import cn.damei.service.modules.BizPmStarCommissionLogService;
import cn.damei.entity.modules.BizPmStarCommissionCnfgSnap;
import cn.damei.service.modules.BizPmStarCommissionCnfgSnapService;
import cn.damei.entity.modules.BizGuaranteeMoneyBalance;
import cn.damei.service.modules.BizGuaranteeMoneyBalanceService;
import cn.damei.service.modules.BizSeiralnumService;
import cn.damei.dao.modules.InspectorConfirmDao;
import cn.damei.entity.modules.PmSettleCategoryDetail;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;

import net.sf.json.JSONObject;


@Controller
@RequestMapping(value = "${adminPath}/bizorderfinishprojectbill/bizOrderFinishProjectBill")
public class BizOrderFinishProjectBillController extends BaseController {

	@Autowired
	private BizPmGuaranteeMoneyLogService bizPmGuaranteeMoneyLogService;
	@Autowired
	private BizOrderFinishProjectBillService bizOrderFinishProjectBillService;
	@Autowired
	private BizCompletedAuditService bizCompletedAuditService;
	@Autowired
	private BizMessagegroupService bizMessagegroupService;
	@Autowired
	private BizEmployeeService2 bizEmployeeService2;
	@Autowired
	private BizPhoneMsgService bizPhoneMsgService;
	@Autowired
	private BizPmSettleCategoryDetailService bizPmSettleCategoryDetailService;
	@Autowired
	private BizPmGuaranteeMoneyCnfgSnapService bizPmGuaranteeMoneyCnfgSnapService;
	@Autowired
	private BizPmStarCommissionCnfgSnapService bizPmStarCommissionCnfgSnapService;
	@Autowired
	private MessageService messageService;
	@Autowired
	private BizQcLongwayCommissionCnfgSnapService bizQcLongwayCommissionCnfgSnapService;
	@Autowired
	private BizQcStarCommissionCnfgSnapService bizQcStarCommissionCnfgSnapService;
	@Autowired
	private BizQcStarCommissionLogService bizQcStarCommissionLogService;
	@Autowired
	private BizQcLongwayCommissionLogService bizQcLongwayCommissionLogService;
	@Autowired
	private BizPmStarCommissionLogService bizPmStarCommissionLogService;
	@Autowired
	private BizSynDataService bizSynDataService;
	@Autowired
	private BizGuaranteeMoneyBalanceService bizGuaranteeMoneyBalanceService;
	@Autowired
	private BizPmSettleBillService bizPmSettleBillService;

	@Autowired
	private BizBusinessStatusLogDao logDao;

	@Autowired
	private BizAssessRewardPunishDao bizAssessRewardPunishDao;
	@Autowired
	private InspectorConfirmDao inspectorConfirmDao;
	@Autowired
	private PmMaterialsSettleInfoService pmMaterialsSettleInfoService;
	@Autowired
	private BizAssessRewardPunishService bizAssessRewardPunishService;

	@Autowired
	private BizOrderFinanceCollectionService bizOrderFinanceCollectionService;
	@Autowired
	private BizPmSettleCategorySummaryService bizPmSettleCategorySummaryService;
	@Autowired
	private BizSeiralnumService bizSeiralnumService;

	DecimalFormat df = new DecimalFormat("#.00");

	@ModelAttribute
	public BizOrderFinishProjectBill get(@RequestParam(required = false) Integer id) {
		BizOrderFinishProjectBill entity = null;
		if (StringUtils.isNotBlank(id + "")) {

			entity = bizOrderFinishProjectBillService.get(id);
		}
		if (entity == null) {
			entity = new BizOrderFinishProjectBill();
		}
		return entity;
	}

	@RequestMapping(value = { "preUpdate", "" })
	public String preUpdate(BizOrderFinishProjectBill bizOrderFinishProjectBill, HttpServletRequest request,
			HttpServletResponse response, Model model, Integer orderID) {
		BizOrderFinishProjectBill projectBill = null;
		if (orderID != null) {
			projectBill = bizOrderFinishProjectBillService.getByOrderID(Integer.valueOf(orderID));
		}
		if(projectBill != null){
			projectBill.setOrderId(orderID);
		}
		model.addAttribute("orderFinishProjectBill", projectBill);
		return "modules/bizcompleted/updateDate";
	}


	@ResponseBody
	@RequestMapping(value = "updateByDate")
	public String updateByDate(HttpServletRequest request, HttpServletResponse response, Model model, String id,
			String orderID, String realFinishProjectDate) {
		String result = "0";
		if (!id.equals("")) {
			result = bizOrderFinishProjectBillService.updateByDate(Integer.valueOf(id), realFinishProjectDate);

			bizOrderFinishProjectBillService.updateOrderById(Integer.valueOf(orderID), realFinishProjectDate);

		} else {
			result = "1";
		}

		return result;
	}


	@ResponseBody
	@RequestMapping(value = "auditSucess")
	public String auditSucess(String orderID,String realFinishProjectDate) throws Exception {
		BizCompletedAudit order = null;
		String result = "0";
		Date date = new Date();
		User user = UserUtils.getUser();
		if (!orderID.equals("")) {
			order = bizCompletedAuditService.get(Integer.valueOf(orderID));
			if (order.getProjectMode().equals("1")) {
				result = checkSettleAmount(Integer.valueOf(orderID));
				if (result.equals("-1")) {
					return result;
				}
			}
			Map<String, Object> pmSettleBillMap = new HashMap<String, Object>();
			pmSettleBillMap.put("orderId", orderID);
			pmSettleBillMap.put("settleRole", 1);
			pmSettleBillMap.put("settleBillType", 1);
			int pmSettleCount = bizPmSettleBillService.queryPmSettleBillByParam(pmSettleBillMap);
			if (pmSettleCount == 0 && order.getProjectMode().equals("1")) {
				return result = "4";
			}
			if (!order.getOrderStatusNumber().equals(ConstantUtils.ORDERSTATUS_300_VALUE)) {
				return result = "3";
			}
			Integer jiesuan = null;
			if (UserUtils.getUser().getEmpId() != null && !UserUtils.getUser().getEmpId().equals("")) {
				jiesuan = Integer.valueOf(UserUtils.getUser().getEmpId());
			}

			result = bizOrderFinishProjectBillService.updateByOrderID(Integer.valueOf(orderID), jiesuan);

			bizOrderFinishProjectBillService.updateOrderById(Integer.valueOf(orderID), realFinishProjectDate);
		}

		if (result.equals("0")) {
			result = bizCompletedAuditService.updateOrderStatus(ConstantUtils.ORDERSTATUS_340_VALUE,
					ConstantUtils.ORDERSTATUS_340_VALUE_REMARK, Integer.valueOf(orderID));

			Map<String, String> jsonMap = new HashMap<String, String>();
			jsonMap.put("time", DateUtils.formatDateTime(new Date()));
			jsonMap.put("orderId", order.getOrderNumber());
			jsonMap.put("type", "3");
			String key = MD5Utils.MD5Secret(jsonMap);
			jsonMap.put("key", key);
			BizSynData bizSynData = new BizSynData();
			bizSynData.setDataDirection(ConstantUtils.DATA_DIRECTION_2);
			bizSynData.setBusinessType(ConstantUtils.BUSINESS_TYPE_303);
			bizSynData.setBusinessOnlyMarkInt(Integer.parseInt(orderID));
			bizSynData.setBusinessData(JSONObject.fromObject(jsonMap).toString());
			bizSynData.setSynStatus(ConstantUtils.SYN_STATUS_4);
			bizSynData.setIsAutoSyn(ConstantUtils.IS_AUTO_SYN_1);
			bizSynData.preInsert();
			bizSynDataService.insert(bizSynData);


			BizBusinessStatusLog bizBusinessStatusLog = new BizBusinessStatusLog();
			bizBusinessStatusLog.setBusinessType(BusinessLogConstantUtil.BUSINESS_TYPE_303);
			bizBusinessStatusLog.setBusinessOnlyMarkInt(Integer.valueOf(orderID));
			bizBusinessStatusLog.setBusinessStatus(ConstantUtils.ORDERSTATUS_340_VALUE);
			bizBusinessStatusLog.setStatusDatetime(new Date());
			bizBusinessStatusLog.setBusinessRemarks("结算员通过竣工");
			if (StringUtils.isNotBlank(UserUtils.getUser().getEmpId())) {
				bizBusinessStatusLog.setBusinessEmployeeId(Integer.valueOf(UserUtils.getUser().getEmpId()));
			}
			bizBusinessStatusLog.setStatusDatetime(new Date());
			bizBusinessStatusLog.preInsert();
			logDao.insert(bizBusinessStatusLog);
		}
		if (null != order) {
			logger.info("发送对象：项目经理手机号：" + order.getEmployeePhone());
			List<Integer> list = new ArrayList<Integer>();
			BizMessagegroup bizMessagegroup = bizMessagegroupService.getByStoreId(order.getStoreId().toString(), "7");
			List<BizEmployee2> employeelist = null;
			if (null != bizMessagegroup) {
				String[] str = bizMessagegroup.getEmployees().split(",");
				for (String id : str) {
					list.add(Integer.valueOf(id));
				}
				employeelist = bizEmployeeService2.getById(list);
				if (list.size() > 0 && employeelist.size() > 0) {
					for (BizEmployee2 employee : employeelist) {
						BizPhoneMsg ddMsg = new BizPhoneMsg();
						ddMsg.setId(null);
						ddMsg.setReceiveEmployeeId(order.getEmpId());
						ddMsg.setMsgContent("订单（" + order.getCommunityName() + "-" + order.getBuildNumber() + "-"
								+ order.getBuildUnit() + "-" + order.getBuildRoom() + "-" + order.getCustomerName()
								+ "-" + order.getCustomerPhone() + "），结算员（" + employee.getRealname() + "-"
								+ employee.getPhone() + "），竣工申请结算员审核通过，请登录APP查看详情。");
						ddMsg.setReceivePhone(order.getEmpPhone().trim());
						ddMsg.setMsgGenerateDatetime(DateUtils.addDate(new Date(), 0));
						ddMsg.setMsgTosendDatetime(null);
						ddMsg.setMsgSendedDatetime(null);
						ddMsg.setMsgStatus("0");
						ddMsg.setRelatedBusinessType(SendMsgBusinessType.RELATED_BUSINESS_TYPE_500201);
						ddMsg.setRelatedBusinessIdInt(Integer.valueOf(order.getId()));
						ddMsg.setRelatedBusinessIdVarchar("");
						bizPhoneMsgService.save(ddMsg);
					}
				}
			}

			Message message = new Message();
			message.setMsgTitle("结算员竣工审核通过");
			message.setMsgTime(new Date());
			message.setMsgContent(
					"订单（" + order.getCommunityName() + "-" + order.getBuildNumber() + "-" + order.getBuildUnit() + "-"
							+ order.getBuildRoom() + "-" + order.getCustomerName() + "-" + order.getCustomerPhone()
							+ "），结算员（" + user.getName() + "-" + user.getPhone() + "），竣工申请结算员审核通过，请登录APP查看详情。");
			message.setMsgType(MessagePushType.MSG_TYPE_1);
			message.setBusiType(MessagePushType.MESSAGE_PUSH_TYPE_102001002);
			message.setEmployeeId(order.getItemManagerId());
			message.setBusiIdInt(Integer.valueOf(orderID));
			messageService.insert(message);
		}

		Integer orderId = Integer.parseInt(orderID);

		try {
			if (order.getProjectMode().equals("1")) {




				BizPmStarCommissionCnfgSnap bizPmStarCommissionCnfgSnap = bizPmStarCommissionCnfgSnapService
						.findSccs(orderId);
				BizEmployee2 manager = bizEmployeeService2.get(order.getItemManagerId());
				if (null != bizPmStarCommissionCnfgSnap) {

					BizPmStarCommissionLog bizPmStarCommissionLog = new BizPmStarCommissionLog();
					bizPmStarCommissionLog.setOrderId(orderId);
					bizPmStarCommissionLog.setPmEmployeeId(bizPmStarCommissionCnfgSnap.getPmEmployeeId());
					bizPmStarCommissionLog.setStarLevel(bizPmStarCommissionCnfgSnap.getStarLever());
					bizPmStarCommissionLog.setCommissionNode(ConstantUtils.COMMISSION_NODE_2);
					bizPmStarCommissionLog.setCommissionAmount(bizPmStarCommissionCnfgSnap.getCommissionAmount());
					bizPmStarCommissionLog.setCommissionRate(bizPmStarCommissionCnfgSnap.getCommissionRateComplete());
					bizPmStarCommissionLog.setCommissionDatetime(new Date());
					bizPmStarCommissionLog.preInsert();
					Integer id = bizPmStarCommissionLogService.insert1(bizPmStarCommissionLog);

					BizPmSettleCategoryDetail details = new BizPmSettleCategoryDetail();
					details.setOrderId(orderId);
					details.setPmEmployeeId(bizPmStarCommissionCnfgSnap.getPmEmployeeId());
					details.setSettleCategory(ConstantUtils.PM_SETTLE_CATEGORY_5);
					Double price = bizPmStarCommissionCnfgSnap.getCommissionAmount()
							* bizPmStarCommissionCnfgSnap.getCommissionRateComplete();
					price = Double.parseDouble(df.format(price));
					details.setSettleAmount(price);
					details.setSettleStatus(ConstantUtils.PM_SETTLE_STATUS_20);
					details.setSettleStatusDatetime(new Date());

					details.setRelatedBusinessId(bizPmStarCommissionLog.getId());
					details.setSettleRole(ConstantUtils.SETTLE_ROLE_1);
					details.preInsert();
					bizPmSettleCategoryDetailService.insert(details);
				}

				BizPmGuaranteeMoneyCnfgSnap gmcs = bizPmGuaranteeMoneyCnfgSnapService.findGmc(orderId);
				if (gmcs != null) {



					Double money = 0.0;
					BizPmGuaranteeMoneyLog log = bizPmGuaranteeMoneyLogService.findByEmployeeId(gmcs.getPmEmployeeId());

					BizGuaranteeMoneyBalance bizGuaranteeMoneyBalance = bizGuaranteeMoneyBalanceService
							.findGuaranteeMoneyBalanceByEmployeeId(gmcs.getPmEmployeeId());
					if (bizGuaranteeMoneyBalance == null || bizGuaranteeMoneyBalance.getId() == null) {
						bizGuaranteeMoneyBalance = new BizGuaranteeMoneyBalance();
					}
					if (log != null) {
						money = bizGuaranteeMoneyBalance.getGuaranteeMoneyBalance();

					}
					BizPmGuaranteeMoneyLog gml = new BizPmGuaranteeMoneyLog();
					gml.setPmEmployeeId(gmcs.getPmEmployeeId());
					gml.setOrderId(gmcs.getOrderId());
					gml.setTakeoffDatetime(new Date());
					Double total = money + gmcs.getGuaranteeMoneyPerOrder();
					Double takeoffAmount = 0.00;
					if (total >= gmcs.getGuaranteeMoneyMax()) {
						takeoffAmount = gmcs.getGuaranteeMoneyMax() - money;
						if (takeoffAmount < 0) {
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

					BizPmSettleCategoryDetail details = new BizPmSettleCategoryDetail();
					if (gml.getTakeoffAmount() != null) {
						details.setSettleAmount(0 - gml.getTakeoffAmount());
					}
					details.setOrderId(gmcs.getOrderId());
					details.setPmEmployeeId(gmcs.getPmEmployeeId());
					details.setSettleCategory(ConstantUtils.PM_SETTLE_CATEGORY_6);
					details.setSettleStatus(ConstantUtils.PM_SETTLE_STATUS_20);
					details.setSettleStatusDatetime(new Date());
					details.setRelatedBusinessId(gml.getId());
					details.setSettleRole(ConstantUtils.SETTLE_ROLE_1);
					details.preInsert();
					bizPmSettleCategoryDetailService.insert(details);
				}


				List<BizAssessRewardPunish> updateList = new ArrayList<BizAssessRewardPunish>();
				Map<String, Object> rewarPunishParam = new HashMap<String, Object>();
				rewarPunishParam.put("relatedBusinessIdInt", orderId);
				rewarPunishParam.put("rewardPunishTargetEmployeeType", 1);
				rewarPunishParam.put("rewardPunishTargetType", 10);
				rewarPunishParam.put("isRewardOrPunish", 1);
				rewarPunishParam.put("isMonthInspection", 0);
				rewarPunishParam.put("rewardPunishStatus", 1);
				List<PmSettleCategoryDetail> list5 = new ArrayList<PmSettleCategoryDetail>();
				List<BizAssessRewardPunish> pmRewardList = bizAssessRewardPunishDao
						.queryAssessRewardPunishByParam(rewarPunishParam);
				if (pmRewardList != null && pmRewardList.size() > 0) {
					for (BizAssessRewardPunish pmReward : pmRewardList) {
						PmSettleCategoryDetail details = new PmSettleCategoryDetail();
						details.setOrderId(pmReward.getRelatedBusinessIdInt());
						details.setPmEmployeeId(pmReward.getRewardPunishTargetEmployeeId());
						details.setSettleCategory(ConstantUtils.PM_SETTLE_CATEGORY_1002);
						details.setSettleAmount(pmReward.getRewardPunishAmount());
						details.setSettleStatus(ConstantUtils.PM_SETTLE_STATUS_20);
						details.setSettleStatusTime(date);
						details.setSettleRemark("");
						details.setRelatedBussinessId(pmReward.getId());
						details.setCreateBy(user);
						details.setCreateDate(date);
						details.setUpdateBy(user);
						details.setUpdateDate(date);
						details.setDelFlag("0");
						details.setSettleRole(ConstantUtils.SETTLE_ROLE_1);

						list5.add(details);

						pmReward.setRewardPunishStatus("2");
						pmReward.setStatusDescribe("已关联结算");
						pmReward.setStatusDatetime(date);
						if (user.getEmpId() != null) {
							pmReward.setStatusOperator(Integer.valueOf(user.getEmpId()));
						}

						pmReward.setUpdateBy(user);
						pmReward.setUpdateDate(date);
						updateList.add(pmReward);
					}
					if (list5.size() > 0) {
						inspectorConfirmDao.saveDetailAll(list5);
					}

				}

				rewarPunishParam.put("isRewardOrPunish", 2);
				List<BizAssessRewardPunish> pmPunishList = bizAssessRewardPunishDao
						.queryAssessRewardPunishByParam(rewarPunishParam);
				List<PmSettleCategoryDetail> list6 = new ArrayList<PmSettleCategoryDetail>();
				if (pmPunishList != null && pmPunishList.size() > 0) {
					for (BizAssessRewardPunish pmPunish : pmPunishList) {
						PmSettleCategoryDetail details = new PmSettleCategoryDetail();
						details.setOrderId(pmPunish.getRelatedBusinessIdInt());
						details.setPmEmployeeId(pmPunish.getRewardPunishTargetEmployeeId());
						details.setSettleCategory(ConstantUtils.PM_SETTLE_CATEGORY_1003);
						if (pmPunish.getRewardPunishAmount() > 0) {
							details.setSettleAmount(0 - pmPunish.getRewardPunishAmount());
						} else {
							details.setSettleAmount(pmPunish.getRewardPunishAmount());
						}
						details.setSettleStatus(ConstantUtils.PM_SETTLE_STATUS_20);
						details.setSettleStatusTime(date);
						details.setSettleRemark("");
						details.setRelatedBussinessId(pmPunish.getId());
						details.setCreateBy(user);
						details.setCreateDate(date);
						details.setUpdateBy(user);
						details.setUpdateDate(date);
						details.setDelFlag("0");
						details.setSettleRole(ConstantUtils.SETTLE_ROLE_1);
						list6.add(details);

						pmPunish.setRewardPunishStatus("2");
						pmPunish.setStatusDescribe("已关联结算");
						pmPunish.setStatusDatetime(date);
						if (user.getEmpId() != null) {
							pmPunish.setStatusOperator(Integer.valueOf(user.getEmpId()));
						}

						pmPunish.setUpdateBy(user);
						pmPunish.setUpdateDate(date);
						updateList.add(pmPunish);
					}
					if (list6.size() > 0) {
						inspectorConfirmDao.saveDetailAll(list6);
					}
				}


				rewarPunishParam.put("isMonthInspection", 1);

				rewarPunishParam.put("isRewardOrPunish", 1);
				List<PmSettleCategoryDetail> list7 = new ArrayList<PmSettleCategoryDetail>();
				List<BizAssessRewardPunish> pmInspectionRewardList = bizAssessRewardPunishDao
						.queryAssessRewardPunishByParam(rewarPunishParam);
				if (pmInspectionRewardList != null && pmInspectionRewardList.size() > 0) {
					for (BizAssessRewardPunish pmInspectionReward : pmInspectionRewardList) {
						PmSettleCategoryDetail details = new PmSettleCategoryDetail();
						details.setOrderId(pmInspectionReward.getRelatedBusinessIdInt());
						details.setPmEmployeeId(pmInspectionReward.getRewardPunishTargetEmployeeId());
						details.setSettleCategory(ConstantUtils.PM_SETTLE_CATEGORY_1012);
						details.setSettleAmount(pmInspectionReward.getRewardPunishAmount());
						details.setSettleStatus(ConstantUtils.PM_SETTLE_STATUS_20);
						details.setSettleStatusTime(date);
						details.setSettleRemark("");
						details.setRelatedBussinessId(pmInspectionReward.getId());
						details.setCreateBy(user);
						details.setCreateDate(date);
						details.setUpdateBy(user);
						details.setUpdateDate(date);
						details.setDelFlag("0");
						details.setSettleRole(ConstantUtils.SETTLE_ROLE_1);

						list7.add(details);

						pmInspectionReward.setRewardPunishStatus("2");
						pmInspectionReward.setStatusDescribe("已关联结算");
						pmInspectionReward.setStatusDatetime(date);
						if (user.getEmpId() != null) {
							pmInspectionReward.setStatusOperator(Integer.valueOf(user.getEmpId()));
						}

						pmInspectionReward.setUpdateBy(user);
						pmInspectionReward.setUpdateDate(date);
						updateList.add(pmInspectionReward);
					}
					if (list7.size() > 0) {
						inspectorConfirmDao.saveDetailAll(list7);
					}

				}


				rewarPunishParam.put("isRewardOrPunish", 2);
				List<PmSettleCategoryDetail> list8 = new ArrayList<PmSettleCategoryDetail>();
				List<BizAssessRewardPunish> pmInspectionPunishList = bizAssessRewardPunishDao
						.queryAssessRewardPunishByParam(rewarPunishParam);
				if (pmInspectionPunishList != null && pmInspectionPunishList.size() > 0) {
					for (BizAssessRewardPunish pmInspectionPunish : pmInspectionPunishList) {
						PmSettleCategoryDetail details = new PmSettleCategoryDetail();
						details.setOrderId(pmInspectionPunish.getRelatedBusinessIdInt());
						details.setPmEmployeeId(pmInspectionPunish.getRewardPunishTargetEmployeeId());
						details.setSettleCategory(ConstantUtils.PM_SETTLE_CATEGORY_1013);
						if (pmInspectionPunish.getRewardPunishAmount() > 0) {
							details.setSettleAmount(0 - pmInspectionPunish.getRewardPunishAmount());
						} else {
							details.setSettleAmount(pmInspectionPunish.getRewardPunishAmount());
						}
						details.setSettleStatus(ConstantUtils.PM_SETTLE_STATUS_20);
						details.setSettleStatusTime(date);
						details.setSettleRemark("");
						details.setRelatedBussinessId(pmInspectionPunish.getId());
						details.setCreateBy(user);
						details.setCreateDate(date);
						details.setUpdateBy(user);
						details.setUpdateDate(date);
						details.setDelFlag("0");
						details.setSettleRole(ConstantUtils.SETTLE_ROLE_1);

						list8.add(details);

						pmInspectionPunish.setRewardPunishStatus("2");
						pmInspectionPunish.setStatusDescribe("已关联结算");
						pmInspectionPunish.setStatusDatetime(date);
						if (user.getEmpId() != null) {
							pmInspectionPunish.setStatusOperator(Integer.valueOf(user.getEmpId()));
						}

						pmInspectionPunish.setUpdateBy(user);
						pmInspectionPunish.setUpdateDate(date);
						updateList.add(pmInspectionPunish);
					}
					if (list8.size() > 0) {
						inspectorConfirmDao.saveDetailAll(list8);
					}

				}

				if (updateList.size() > 0) {
					bizAssessRewardPunishDao.updateRewardPunishStatus(updateList);
				}


				Map<String, Object> map = new HashMap<String, Object>();
				map.put("orderId", orderId);
				map.put("settleStatus", ConstantUtils.PM_SETTLE_STATUS_10);
				map.put("settleCategory", ConstantUtils.PM_SETTLE_CATEGORY_4);
				map.put("settleStatus20", ConstantUtils.PM_SETTLE_STATUS_20);
				bizPmSettleCategoryDetailService.updateStatusByOrderId(map);


				Map<String, Object> map1 = new HashMap<String, Object>();
				map1.put("orderId", orderId);
				map1.put("settleStatus", ConstantUtils.PM_SETTLE_STATUS_10);
				map1.put("settleCategory", ConstantUtils.PM_SETTLE_CATEGORY_11);
				map1.put("settleStatus20", ConstantUtils.PM_SETTLE_STATUS_20);
				bizPmSettleCategoryDetailService.updateStatusByOrderId(map1);


				Map<String, Object> map2 = new HashMap<String, Object>();
				map2.put("orderId", orderId);
				map2.put("settleStatus", ConstantUtils.PM_SETTLE_STATUS_10);
				map2.put("settleCategory", ConstantUtils.PM_SETTLE_CATEGORY_12);
				map2.put("settleStatus20", ConstantUtils.PM_SETTLE_STATUS_20);
				bizPmSettleCategoryDetailService.updateStatusByOrderId(map2);

				Map<String, Object> param = new HashMap<String, Object>();
				param.put("orderId", orderId);
				param.put("collectionType", 2);
				List<String> collectionStatus = new ArrayList<String>();
				collectionStatus.add("10");
				collectionStatus.add("20");
				collectionStatus.add("30");
				param.put("collectionStatus", collectionStatus);
				int collectionCount = bizOrderFinanceCollectionService.checkIsExistByParam(param);
				if (collectionCount > 0) {
					weikuanMoney(orderId, date,user);
				}


				BizQcLongwayCommissionCnfgSnap bizQcLongwayCommissionCnfgSnap = bizQcLongwayCommissionCnfgSnapService
						.findBqlccsByOrderId(orderId);
				BizEmployee2 bizEmployee = bizEmployeeService2.get(order.getOrderInspectorId());
				if (bizQcLongwayCommissionCnfgSnap != null) {

					BizQcLongwayCommissionLog bizQcLongwayCommissionLog = new BizQcLongwayCommissionLog();
					bizQcLongwayCommissionLog.setOrderId(bizQcLongwayCommissionCnfgSnap.getOrderId());
					bizQcLongwayCommissionLog
							.setLongwayCommissionEmployeeId(bizQcLongwayCommissionCnfgSnap.getPmEmployeeId());
					bizQcLongwayCommissionLog.setStarLevel(bizEmployee.getStar());
					bizQcLongwayCommissionLog.setCommissionNode(ConstantUtils.COMMISSION_NODE_2);

					bizQcLongwayCommissionLog.setCommissionAmount(bizQcLongwayCommissionCnfgSnap.getCommissionAmount());
					bizQcLongwayCommissionLog
							.setCommissionRate(bizQcLongwayCommissionCnfgSnap.getCommissionRateComplete());
					bizQcLongwayCommissionLog.setCommissionDatetime(new Date());
					bizQcLongwayCommissionLog.setLongwayCommissionType("20");
					bizQcLongwayCommissionLog.preInsert();
					Integer id = bizQcLongwayCommissionLogService.insert1(bizQcLongwayCommissionLog);

					BizPmSettleCategoryDetail details = new BizPmSettleCategoryDetail();
					details.setOrderId(bizQcLongwayCommissionCnfgSnap.getOrderId());
					details.setPmEmployeeId(bizQcLongwayCommissionCnfgSnap.getPmEmployeeId());
					details.setSettleAmount(
							Double.parseDouble(df.format(bizQcLongwayCommissionCnfgSnap.getCommissionAmount()
									* bizQcLongwayCommissionCnfgSnap.getCommissionRateComplete())));
					details.setSettleStatus(ConstantUtils.PM_SETTLE_STATUS_20);
					details.setSettleStatusDatetime(new Date());
					details.setSettleRole(ConstantUtils.SETTLE_ROLE_2);
					details.setSettleCategory(ConstantUtils.QC_SETTLE_CATEGORY_10);
					details.setRelatedBusinessId(bizQcLongwayCommissionLog.getId());
					details.preInsert();
					bizPmSettleCategoryDetailService.insert(details);
				}

				BizQcStarCommissionCnfgSnap bizQcStarCommissionCnfgSnap = bizQcStarCommissionCnfgSnapService
						.findBqsccsByOrderId(orderId);
				if (bizQcStarCommissionCnfgSnap != null) {

					BizQcStarCommissionLog bizQcStarCommissionLog = new BizQcStarCommissionLog();
					bizQcStarCommissionLog.setOrderId(bizQcStarCommissionCnfgSnap.getOrderId());
					bizQcStarCommissionLog.setQcEmployeeId(bizQcStarCommissionCnfgSnap.getPmEmployeeId());
					bizQcStarCommissionLog.setStarLevel(bizQcStarCommissionCnfgSnap.getStarLevel());
					bizQcStarCommissionLog.setCommissionNode(ConstantUtils.COMMISSION_NODE_2);

					bizQcStarCommissionLog.setCommissionAmount(bizQcStarCommissionCnfgSnap.getCommissionAmount());
					bizQcStarCommissionLog.setCommissionRate(bizQcStarCommissionCnfgSnap.getCommissionRateComplete());
					bizQcStarCommissionLog.setCommissionDatetime(new Date());
					bizQcStarCommissionLog.preInsert();
					Integer id = bizQcStarCommissionLogService.insert1(bizQcStarCommissionLog);

					BizPmSettleCategoryDetail details = new BizPmSettleCategoryDetail();
					details.setOrderId(bizQcStarCommissionCnfgSnap.getOrderId());
					details.setPmEmployeeId(bizQcStarCommissionCnfgSnap.getPmEmployeeId());
					details.setSettleAmount(
							Double.parseDouble(df.format(bizQcStarCommissionCnfgSnap.getCommissionAmount()
									* bizQcStarCommissionCnfgSnap.getCommissionRateComplete())));
					details.setSettleStatus(ConstantUtils.PM_SETTLE_STATUS_20);
					details.setSettleStatusDatetime(new Date());
					details.setSettleRole(ConstantUtils.SETTLE_ROLE_2);
					details.setSettleCategory(ConstantUtils.QC_SETTLE_CATEGORY_8);
					details.setRelatedBusinessId(bizQcStarCommissionLog.getId());
					details.preInsert();
					bizPmSettleCategoryDetailService.insert(details);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("返回状态码：" + result);
		return result;
	}


	@ResponseBody
	@RequestMapping(value = "auditFail")
	public String auditFail(HttpServletRequest request, HttpServletResponse response, Model model, String orderID,
			String remarks) {
		logger.info("订单编号：" + orderID + "\t\t 备注：" + remarks);
		BizCompletedAudit order = null;
		String result = "0";
		if (!orderID.equals("")) {

			order = bizCompletedAuditService.get(Integer.valueOf(orderID));
			if (!order.getOrderStatusNumber().equals(ConstantUtils.ORDERSTATUS_300_VALUE)) {
				return result = "3";
			}
			result = bizOrderFinishProjectBillService.updateByOrderIDOrFail(Integer.valueOf(orderID), remarks,
					Integer.valueOf(UserUtils.getUser().getEmpId() == null ? "0" : UserUtils.getUser().getEmpId()));
		}

		if (result.equals("0")) {
			result = bizCompletedAuditService.updateOrderStatus(ConstantUtils.ORDERSTATUS_330_VALUE,
					ConstantUtils.ORDERSTATUS_330_VALUE_REMARK, Integer.valueOf(orderID));
		}

		if (null != order) {
			logger.info("发送对象：项目经理手机号：" + order.getEmployeePhone());
			List<Integer> list = new ArrayList<Integer>();
			BizMessagegroup bizMessagegroup = bizMessagegroupService.getByStoreId(order.getStoreId().toString(), "7");
			List<BizEmployee2> employeelist = null;
			if (null != bizMessagegroup) {
				String[] str = bizMessagegroup.getEmployees().split(",");
				for (String id : str) {
					list.add(Integer.valueOf(id));
				}
				employeelist = bizEmployeeService2.getById(list);
				if (list.size() > 0 && employeelist.size() > 0) {
					for (BizEmployee2 employee : employeelist) {
						BizPhoneMsg ddMsg = new BizPhoneMsg();
						ddMsg.setId(null);
						ddMsg.setReceiveEmployeeId(order.getEmpId());
						ddMsg.setMsgContent("订单（" + order.getCommunityName() + "-" + order.getBuildNumber() + "-"
								+ order.getBuildUnit() + "-" + order.getBuildRoom() + "-" + order.getCustomerName()
								+ "-" + order.getCustomerPhone() + "），结算员（" + employee.getRealname() + "-"
								+ employee.getPhone() + "），竣工申请结算员审核不通过，驳回原因（" + remarks + "），请及时登录APP重新提交。");
						ddMsg.setReceivePhone(order.getEmpPhone().trim());
						ddMsg.setMsgGenerateDatetime(DateUtils.addDate(new Date(), 0));
						ddMsg.setMsgTosendDatetime(null);
						ddMsg.setMsgSendedDatetime(null);
						ddMsg.setMsgStatus("0");
						ddMsg.setRelatedBusinessType(SendMsgBusinessType.RELATED_BUSINESS_TYPE_500202);
						ddMsg.setRelatedBusinessIdInt(Integer.valueOf(order.getId()));
						ddMsg.setRelatedBusinessIdVarchar("");
						bizPhoneMsgService.save(ddMsg);
					}
				}
			}

			User user = UserUtils.getUser();
			Message message = new Message();
			message.setMsgTitle("结算员竣工审核不通过");
			message.setMsgTime(new Date());
			message.setMsgContent("订单（" + order.getCommunityName() + "-" + order.getBuildNumber() + "-"
					+ order.getBuildUnit() + "-" + order.getBuildRoom() + "-" + order.getCustomerName() + "-"
					+ order.getCustomerPhone() + "），结算员（" + user.getName() + "-" + user.getPhone()
					+ "），竣工申请结算员审核不通过，驳回原因（" + remarks + "），请及时登录APP重新提交。");
			message.setMsgType(MessagePushType.MSG_TYPE_1);
			message.setBusiType(MessagePushType.MESSAGE_PUSH_TYPE_102001001);
			message.setEmployeeId(order.getItemManagerId());
			message.setBusiIdInt(Integer.valueOf(orderID));
			messageService.insert(message);

		}

		logger.info("返回状态码：" + result);
		return result;
	}

	public String checkSettleAmount(Integer orderId) {
		BizPmStarCommissionCnfgSnap bizPmStarCommissionCnfgSnap = inspectorConfirmDao
				.queryManagerCommissionByOrderId(orderId);

		Double commissionAmount = bizPmStarCommissionCnfgSnap.getCommissionAmount()
				* bizPmStarCommissionCnfgSnap.getCommissionRateComplete();

		Double pmGuaranteeMoney = 0.0;
		BizPmGuaranteeMoneyCnfgSnap gmcs = bizPmGuaranteeMoneyCnfgSnapService.findGmc(orderId);
		if(gmcs != null){
			BizGuaranteeMoneyBalance bizGuaranteeMoneyBalance = bizGuaranteeMoneyBalanceService.findGuaranteeMoneyBalanceByEmployeeId(gmcs.getPmEmployeeId());
			if(bizGuaranteeMoneyBalance == null){
				bizGuaranteeMoneyBalance = new BizGuaranteeMoneyBalance();
			}
			Double total = bizGuaranteeMoneyBalance.getGuaranteeMoneyBalance()+gmcs.getGuaranteeMoneyPerOrder();
			if(total >= gmcs.getGuaranteeMoneyMax()){
				pmGuaranteeMoney=gmcs.getGuaranteeMoneyMax()-bizGuaranteeMoneyBalance.getGuaranteeMoneyBalance();
				if(pmGuaranteeMoney<0){
					pmGuaranteeMoney = 0.00;
				}
			}else{
            	pmGuaranteeMoney=gmcs.getGuaranteeMoneyPerOrder();
            }			
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderId", orderId);
		map.put("settleCategory", ConstantUtils.PM_SETTLE_CATEGORY_4);
		map.put("settleStatus", 10);
		map.put("settleRole", ConstantUtils.SETTLE_ROLE_1);
		Double managerPenalty = inspectorConfirmDao.queryManangerPenalty(map);
		if (managerPenalty < 0) {
			managerPenalty = 0 - managerPenalty;
		}

		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("orderId", orderId);
		map1.put("settleCategory", ConstantUtils.PM_SETTLE_CATEGORY_11);
		Double sinceMaterials = inspectorConfirmDao.queryManangerPenalty(map1);
		if (sinceMaterials < 0) {
			sinceMaterials = 0 - sinceMaterials;
		}

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("orderId", orderId);
		param.put("pmEmployeeId", bizPmStarCommissionCnfgSnap.getPmEmployeeId());


		double pmMaterialsSettleAmount = 0.00;
		List<PmMaterialsSettleInfo> pmMaterials = pmMaterialsSettleInfoService.queryPmMaterialsByOrderId(orderId);
		if (pmMaterials != null && pmMaterials.size() > 0) {
			for (PmMaterialsSettleInfo info : pmMaterials) {
				pmMaterialsSettleAmount = pmMaterialsSettleAmount + info.getPmMaterialsSettleAmount();
			}
		}


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

		double pmPunishAmount = 0.0;
		rewardPunish.setIsRewardOrPunish("2");
		pmPunishAmount = bizAssessRewardPunishService.queryTotalAmountByParam(rewardPunish);


		rewardPunish.setIsMonthInspection("1");

		double pmInspectionRewardAmount = 0.0;
		rewardPunish.setIsRewardOrPunish("1");
		pmInspectionRewardAmount = bizAssessRewardPunishService.queryTotalAmountByParam(rewardPunish);

		double pmInspectionPunishAmount = 0.0;
		rewardPunish.setIsRewardOrPunish("2");
		pmInspectionPunishAmount = bizAssessRewardPunishService.queryTotalAmountByParam(rewardPunish);


		Double settleAmount = commissionAmount + sinceMaterials - pmGuaranteeMoney - managerPenalty
				+ pmMaterialsSettleAmount + pmRewardAmount - pmPunishAmount + pmInspectionRewardAmount - pmInspectionPunishAmount;
		if (settleAmount < 0) {
			return "-1";
		} else {
			return "0";
		}
	}

	public void weikuanMoney(Integer orderId, Date date,User user) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("orderId", orderId);
		param.put("settleRole", ConstantUtils.SETTLE_ROLE_1);
		param.put("settleBillType", ConstantUtils.PM_SETTLE_BILL_TYPE_2);
		int count = bizPmSettleBillService.queryPmSettleBillByParam(param);
		if (count == 0) {


			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("orderId", orderId);
			map1.put("settleStatus", ConstantUtils.PM_SETTLE_STATUS_20);
			map1.put("settleRole", ConstantUtils.SETTLE_ROLE_1);
			List<String> settleCategoryListMap = new ArrayList<String>();
			settleCategoryListMap.add(ConstantUtils.PM_SETTLE_CATEGORY_4);
			settleCategoryListMap.add(ConstantUtils.PM_SETTLE_CATEGORY_5);
			settleCategoryListMap.add(ConstantUtils.PM_SETTLE_CATEGORY_6);
			settleCategoryListMap.add(ConstantUtils.PM_SETTLE_CATEGORY_11);
			settleCategoryListMap.add(ConstantUtils.PM_SETTLE_CATEGORY_12);
			settleCategoryListMap.add(ConstantUtils.PM_SETTLE_CATEGORY_1002);
			settleCategoryListMap.add(ConstantUtils.PM_SETTLE_CATEGORY_1003);
			settleCategoryListMap.add(ConstantUtils.PM_SETTLE_CATEGORY_1012);
			settleCategoryListMap.add(ConstantUtils.PM_SETTLE_CATEGORY_1013);
			map1.put("settleCategoryList", settleCategoryListMap);
			List<BizPmSettleCategoryDetail> list = bizPmSettleCategoryDetailService.queryCateGoryAmountByCondition(map1);
			if (CollectionUtils.isNotEmpty(list)) {
				List<BizPmSettleCategorySummary> summaryList = new ArrayList<BizPmSettleCategorySummary>();
				for (BizPmSettleCategoryDetail detail : list) {
					BizPmSettleCategorySummary summary = new BizPmSettleCategorySummary();
					summary.setOrderId(orderId);
					summary.setPmEmployeeId(detail.getPmEmployeeId());
					if (ConstantUtils.PM_SETTLE_CATEGORY_4.equals(detail.getSettleCategory())) {
						summary.setSettleCategory(ConstantUtils.PM_SETTLE_CATEGORY_402);
					} else if (ConstantUtils.PM_SETTLE_CATEGORY_12.equals(detail.getSettleCategory())) {
						summary.setSettleCategory(ConstantUtils.PM_SETTLE_CATEGORY_122);
					} else {
						summary.setSettleCategory(detail.getSettleCategory());
					}
					summary.setSettleAmount(detail.getSettleAmount());
					summary.setSettleStatus(ConstantUtils.PM_SETTLE_STATUS_30);
					summary.setSettleStatusDatetime(date);
					summary.setCreateBy(user);
					summary.setCreateDate(date);
					summary.setUpdateBy(user);
					summary.setUpdateDate(date);
					summary.setSettleRole(ConstantUtils.SETTLE_ROLE_1);
					summaryList.add(summary);
				}
				bizPmSettleCategorySummaryService.insertBatch(summaryList);
			}



			Map<String, Object> updateMap = new HashMap<String, Object>();
			updateMap.put("orderId", orderId);
			updateMap.put("settleStatus", ConstantUtils.PM_SETTLE_STATUS_20);
			updateMap.put("newSettleStatus", ConstantUtils.PM_SETTLE_STATUS_30);
			updateMap.put("settleRole", ConstantUtils.SETTLE_ROLE_1);
			updateMap.put("settleStatusDatetime", date);
			updateMap.put("updateDate", date);
			updateMap.put("updateBy", user);
			List<String> settleCategoryListUpdateMap = new ArrayList<String>();

			settleCategoryListUpdateMap.add(ConstantUtils.PM_SETTLE_CATEGORY_5);
			settleCategoryListUpdateMap.add(ConstantUtils.PM_SETTLE_CATEGORY_6);
			settleCategoryListUpdateMap.add(ConstantUtils.PM_SETTLE_CATEGORY_11);
			settleCategoryListUpdateMap.add(ConstantUtils.PM_SETTLE_CATEGORY_1002);
			settleCategoryListUpdateMap.add(ConstantUtils.PM_SETTLE_CATEGORY_1003);
			settleCategoryListUpdateMap.add(ConstantUtils.PM_SETTLE_CATEGORY_1012);
			settleCategoryListUpdateMap.add(ConstantUtils.PM_SETTLE_CATEGORY_1013);
			updateMap.put("settleCategoryList", settleCategoryListUpdateMap);
			bizPmSettleCategoryDetailService.updateRelateSummary(updateMap);


			Map<String, Object> updateMap2 = new HashMap<String, Object>();
			updateMap2.put("orderId", orderId);
			updateMap2.put("sign", "02");
			updateMap2.put("settleCategory", ConstantUtils.PM_SETTLE_CATEGORY_4);
			updateMap2.put("settleStatus", ConstantUtils.PM_SETTLE_STATUS_20);
			updateMap2.put("newSettleStatus", ConstantUtils.PM_SETTLE_STATUS_30);
			updateMap2.put("settleRole", ConstantUtils.SETTLE_ROLE_1);
			updateMap2.put("settleStatusDatetime", date);
			updateMap2.put("updateDate", date);
			updateMap2.put("updateBy", user);
			bizPmSettleCategoryDetailService.updateRelateSummaryCategory(updateMap2);


			Map<String, Object> updateMap3 = new HashMap<String, Object>();
			updateMap3.put("orderId", orderId);
			updateMap3.put("sign", "2");
			updateMap3.put("settleCategory", ConstantUtils.PM_SETTLE_CATEGORY_12);
			updateMap3.put("settleStatus", ConstantUtils.PM_SETTLE_STATUS_20);
			updateMap3.put("newSettleStatus", ConstantUtils.PM_SETTLE_STATUS_30);
			updateMap3.put("settleRole", ConstantUtils.SETTLE_ROLE_1);
			updateMap3.put("settleStatusDatetime", date);
			updateMap3.put("updateDate", date);
			updateMap3.put("updateBy", user);
			bizPmSettleCategoryDetailService.updateRelateSummaryCategory(updateMap3);


			Map<String, Object> map2 = new HashMap<String, Object>();
			map2.put("orderId", orderId);
			map2.put("settleStatus", ConstantUtils.PM_SETTLE_STATUS_30);
			map2.put("settleRole", ConstantUtils.SETTLE_ROLE_1);
			List<BizPmSettleBill> billList = bizPmSettleCategorySummaryService.queryCateGorySummaryLastByCondition(map2);
			if (CollectionUtils.isNotEmpty(billList)) {
				List<BizPmSettleBill> settleBillList = new ArrayList<BizPmSettleBill>();
				for (BizPmSettleBill bill : billList) {
					BizPmSettleBill settleBill = new BizPmSettleBill();
					settleBill.setBizPmSettleBillCode(bizSeiralnumService.getDateSequence("GJ"));
					settleBill.setSettleBillType(ConstantUtils.PM_SETTLE_BILL_TYPE_2);
					settleBill.setOrderId(orderId);
					settleBill.setPmEmployeeId(bill.getPmEmployeeId());
					settleBill.setProjectMode(bill.getProjectMode());
					settleBill.setSettleMonth(format.format(date));
					settleBill.setSettleDatetime(date);
					settleBill.setCompletQcCheckPunishAmount(bill.getCompletQcCheckPunishAmount());
					settleBill.setCompleteCommissionAmount(bill.getCompleteCommissionAmount());
					settleBill.setGuaranteeMoneyAmount(bill.getGuaranteeMoneyAmount());
					settleBill.setMaterialSelfbuyReimburseAmount(bill.getMaterialSelfbuyReimburseAmount());
					settleBill.setCompleteAuxiliaryMaterialsSettleAmount(
							bill.getCompleteAuxiliaryMaterialsSettleAmount());
					settleBill.setCompleteRewardAmount(bill.getCompleteRewardAmount());
					settleBill.setCompletePunishAmount(bill.getCompletePunishAmount());
					settleBill.setCompleteInspectionRewardAmount(bill.getCompleteInspectionRewardAmount());
					settleBill.setCompleteInspectionPunishAmount(bill.getCompleteInspectionPunishAmount());
					settleBill.setTotalAmount(bill.getTotalAmount());
					settleBill.setStatus(ConstantUtils.PM_SETTLE_STATUS_30);
					settleBill.setCreateBy(user);
					settleBill.setCreateDate(date);
					settleBill.setUpdateBy(user);
					settleBill.setUpdateDate(date);
					settleBill.setSettleRole(ConstantUtils.SETTLE_ROLE_1);
					settleBillList.add(settleBill);
				}
				bizPmSettleBillService.insertBatch(settleBillList);
			}


			Map<String, Object> map3 = new HashMap<String, Object>();
			map3.put("orderId", orderId);
			map3.put("settleStatus", ConstantUtils.PM_SETTLE_STATUS_30);
			map3.put("settleBillType", ConstantUtils.PM_SETTLE_BILL_TYPE_2);
			map3.put("settleRole", ConstantUtils.SETTLE_ROLE_1);
			bizPmSettleCategorySummaryService.updateRelate(map3);

			Map<String, Object> map4 = new HashMap<String, Object>();
			map4.put("orderId", orderId);
			map4.put("settleStatus", ConstantUtils.PM_SETTLE_STATUS_30);
			List<String> settleCategoryList = new ArrayList<String>();
			settleCategoryList.add(ConstantUtils.PM_SETTLE_CATEGORY_1002);
			settleCategoryList.add(ConstantUtils.PM_SETTLE_CATEGORY_1003);
			settleCategoryList.add(ConstantUtils.PM_SETTLE_CATEGORY_1012);
			settleCategoryList.add(ConstantUtils.PM_SETTLE_CATEGORY_1013);
			map4.put("settleCategorys", settleCategoryList);
			map4.put("settleStage", 2);
			map4.put("settleType", 2);
			bizAssessRewardPunishDao.updateByParam(map4);


			BizBusinessStatusLog bizBusinessStatusLog = new BizBusinessStatusLog();
			bizBusinessStatusLog.setBusinessType(BusinessLogConstantUtil.BUSINESS_TYPE_305);
			bizBusinessStatusLog.setBusinessOnlyMarkInt(Integer.valueOf(orderId));
			bizBusinessStatusLog.setBusinessStatus(ConstantUtils.PM_SETTLE_STATUS_30);
			bizBusinessStatusLog.setStatusDatetime(new Date());
			if (StringUtils.isNotBlank(UserUtils.getUser().getEmpId())) {
				bizBusinessStatusLog.setBusinessEmployeeId(Integer.valueOf(UserUtils.getUser().getEmpId()));
			}
			bizBusinessStatusLog.setStatusDatetime(new Date());
			bizBusinessStatusLog.preInsert();
			logDao.insert(bizBusinessStatusLog);
		}
	}
}
