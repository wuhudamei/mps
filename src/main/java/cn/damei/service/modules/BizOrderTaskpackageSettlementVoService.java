package cn.damei.service.modules;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.constantUtils.BusinessLogConstantUtil;
import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.common.utils.Collections3;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.StringUtils;
import cn.damei.service.mobile.Manager.BizEvalRewardTaskpackService;
import cn.damei.dao.mobile.Manager.BizOrderTaskpackageProcedureDao;
import cn.damei.entity.mobile.Manager.BizOrderTaskpackageProcedure;
import cn.damei.dao.mobile.Manager.GuaranteeMoneyDao;
import cn.damei.entity.mobile.Manager.GuaranteeMoney;
import cn.damei.dao.mobile.Manager.OrderTaskpackageSettlementDao;
import cn.damei.entity.mobile.Manager.OrderTaskpackageSettlement;
import cn.damei.entity.mobile.Worker.OrderTaskpackageSettlementDetail;
import cn.damei.service.mobile.Worker.OrderTaskpackageSettlementDetailService;
import cn.damei.dao.modules.BizBusinessStatusLogDao;
import cn.damei.entity.modules.BizBusinessStatusLog;
import cn.damei.dao.modules.BizOrderTaskpackageDao;
import cn.damei.dao.modules.BizOrderTaskpackagePaymentDao;
import cn.damei.entity.modules.BizOrderTaskpackagePayment;
import cn.damei.dao.modules.BizOrderTaskpackagePaymentDetailDao;
import cn.damei.entity.modules.BizOrderTaskpackagePaymentDetail;
import cn.damei.dao.modules.BizOrderTaskpackageSettleMentCancleLogDao;
import cn.damei.dao.modules.BizOrderTaskpackageSettlementDao;
import cn.damei.dao.modules.BizOrderTaskpackageSettlementUpdateLogDao;
import cn.damei.dao.modules.BizOrderTaskpackageSettlementVoDao;
import cn.damei.entity.modules.BizOrderTaskpackageSettleMentCancleLog;
import cn.damei.entity.modules.BizOrderTaskpackageSettlement;
import cn.damei.entity.modules.BizOrderTaskpackageSettlementUpdateLog;
import cn.damei.entity.modules.BizOrderTaskpackageSettlementVo;
import cn.damei.dao.modules.BizPmSettleCategoryDetailDao;
import cn.damei.dao.modules.BizGuaranteeMoneyBalanceDao;
import cn.damei.entity.modules.BizGuaranteeMoneyBalance;
import cn.damei.entity.modules.OrderTaskpackage;
import cn.damei.entity.modules.PmSettleCategoryDetail;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;
import cn.damei.entity.modules.BizTaskPackageTemplat;
import cn.damei.dao.modules.AllotWorkerGroupDao;

@Service
@Transactional(readOnly = true)
public class BizOrderTaskpackageSettlementVoService
		extends CrudService2<BizOrderTaskpackageSettlementVoDao, BizOrderTaskpackageSettlementVo> {

	@Autowired
	private BizOrderTaskpackagePaymentDao bizOrderTaskpackagePaymentDao;
	@Autowired
	private BizOrderTaskpackagePaymentDetailDao bizOrderTaskpackagePaymentDetailDao;
	@Autowired
	private BizOrderTaskpackageDao bizOrderTaskpackageDao;
	@Autowired
	private BizOrderTaskpackageSettlementDao bizOrderTaskpackageSettlementDao;
	@Autowired
	private BizOrderTaskpackageSettleMentCancleLogDao bizOrderTaskpackageSettleMentCancleLogDao;
	@Autowired
	private BizOrderTaskpackageSettlementUpdateLogDao bizOrderTaskpackageSettlementUpdateLogDao;
	@Autowired
	private InspectorConfirmService inspectorConfirmService;

	public BizOrderTaskpackageSettlementVo get(Integer id) {
		return super.get(id);
	}

	public BizOrderTaskpackageSettlementVo findSettlementById(Integer id) {

		return dao.get(id);
	}

	public BizOrderTaskpackageSettlementVo queryEntityByOrderTaskpackageId(Integer orderTaskpackageId) {
		return dao.queryEntityByOrderTaskpackageId(orderTaskpackageId);
	}

	public BizOrderTaskpackageSettlementVo querySettlementByOrderTaskpackageId(Integer orderTaskpackageId) {
		return dao.querySettlementByOrderTaskpackageId(orderTaskpackageId);
	}

	@Autowired
	private BizTaskPackageTemplatService bizTaskPackageTemplatService;
	@Autowired
	private OrderTaskpackageSettlementDetailService orderTaskpackageSettlementDetailService;
	@Autowired
	private BizOrderTaskpackageSettlementService bizOrderTaskpackageSettlementService;
	/*
	 * @Autowired private BizOrderTaskpackagePaymentService
	 * bizOrderTaskpackagePaymentService;
	 */
	/*
	 * @Autowired private BizOrderTaskpackagePaymentDetailService
	 * bizOrderTaskpackagePaymentDetailService;
	 */
	@Autowired
	private BizOrderTaskpackageProcedureDao orderTaskpackageProcedureDao;
	@Autowired
	private OrderTaskpackageSettlementDao orderTaskpackageSettlementDao;
	@Autowired
	private GuaranteeMoneyDao guaranteeMoneyDao;
	@Autowired
	private BizEvalRewardTaskpackService bizEvalRewardTaskpackService;
	@Autowired
	private BizBusinessStatusLogDao bizBusinessStatusLogDao;
	@Autowired
	private BizGuaranteeMoneyBalanceDao bizGuaranteeMoneyBalanceDao;
	@Autowired
	private BizPmSettleCategoryDetailDao bizPmSettleCategoryDetailDao;

	/**
	 * 审核通过
	 * 
	 * @param orderTaskpackageId
	 */
	@Transactional(readOnly = false)
	public void updateStatusDate(Integer orderTaskpackageId, String statusName, String status) {

		User user = UserUtils.getUser();
		Date date = new Date();
		DecimalFormat df = new DecimalFormat("#.00");

		OrderTaskpackage orderTaskackage = allotWorkerGroupDao.findTargetPackageById(orderTaskpackageId);
		orderTaskackage.setPackageStateId(status);
		orderTaskackage.setPackageStatename(statusName);
		allotWorkerGroupDao.updatePackage(orderTaskackage);

		// 更新结算单
		Double rewardAmount = bizEvalRewardTaskpackService.queryRewardAmount(orderTaskpackageId);
		BizOrderTaskpackageSettlementVo vo = dao.queryEntityByOrderTaskpackageId(orderTaskpackageId);
		vo.setUpdateBy(user);
		vo.setUpdateDate(date);
		vo.setStatus(ConstantUtils.SETTLEMENT_STATUS_80);
		vo.setStatusDate(date);
		vo.setSettlementAmount(
				Double.parseDouble(df.format((vo.getSettlementAmount() == null ? 0 : vo.getSettlementAmount())
						+ (rewardAmount == null ? 0 : rewardAmount))));
		vo.setRewardAmount(rewardAmount);
		dao.update(vo);

		// 添加状态日志信息
		BizBusinessStatusLog statusLog = new BizBusinessStatusLog();
		statusLog.setBusinessType(BusinessLogConstantUtil.BUSINESS_TYPE_601);
		statusLog.setBusinessOnlyMarkInt(vo.getOrderTaskpackageId());
		statusLog.setBusinessStatus(ConstantUtils.SETTLEMENT_STATUS_80);
		statusLog.setStatusDatetime(new Date());
		if (StringUtils.isNoneBlank(user.getEmpId())) {
			statusLog.setBusinessEmployeeId(Integer.parseInt(user.getEmpId()));
		}
		statusLog.setBusinessRemarks("工人结算单通过");
		statusLog.preInsert();
		bizBusinessStatusLogDao.insert(statusLog);
		// 更新结算明细单
		OrderTaskpackageSettlementDetail detail = new OrderTaskpackageSettlementDetail();
		detail.setOrderTaskpackageId(orderTaskpackageId);
		detail.setSettlementId(vo.getId());
		detail.setIsLeader("1");
		OrderTaskpackageSettlementDetail settlementDetail = orderTaskpackageSettlementDetailService
				.queryEntityByCondition(detail);
		settlementDetail.setUpdateBy(user);
		settlementDetail.setUpdateDate(date);
		settlementDetail.setPaymentAmount(Double.parseDouble(
				df.format((settlementDetail.getPaymentAmount() == null ? 0 : settlementDetail.getPaymentAmount())
						+ (rewardAmount == null ? 0 : rewardAmount))));
		orderTaskpackageSettlementDetailService.update(settlementDetail);

		// 生成付款单和付款明细
		// 1.根据任务包查询是否分首款和尾款
		Integer taskTackageTempId = orderTaskackage.getTaskTackageTempId();
		BizTaskPackageTemplat bizTaskPackageTemplat = bizTaskPackageTemplatService.get(taskTackageTempId + "");
		BizOrderTaskpackageSettlement settlement = bizOrderTaskpackageSettlementService
				.findByOrderTaskpackageId(orderTaskpackageId);
		List<OrderTaskpackageSettlementDetail> settlementDetails = orderTaskpackageSettlementDetailService
				.findByOrderTaskpackageId(orderTaskpackageId);

		if ("100".equals(bizTaskPackageTemplat.getAdvancePaymentRates())) {// 只有首款
			BizOrderTaskpackagePayment payment = getPayment(settlement);
			payment.setOrderTaskpackagePaymentType(ConstantUtils.PAYMENT_TYPE_0);
			// 首款 --状态设为 15-已审核通过待打款
			// payment.setStatus(ConstantUtils.PAYMENT_STATUS_15);
			payment.setPaymentRates(1.0);
			payment.preInsert();

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("orderTaskpackageSettlementId", payment.getOrderTaskpackageSettlementId());
			paramMap.put("orderTaskpackagPaymentType", payment.getOrderTaskpackagePaymentType());
			int count = bizOrderTaskpackagePaymentDao.checkPaymentIsExistByParam(paramMap);
			if (count == 0) {
				bizOrderTaskpackagePaymentDao.insert(payment);
				BizOrderTaskpackagePaymentDetail paymentDetail = new BizOrderTaskpackagePaymentDetail();
				paymentDetail.setAmount(payment.getAmount());
				paymentDetail.setStatus(ConstantUtils.PAYMENT_DETAIL_STATUS_0);
				paymentDetail.setStatusDatetime(new Date());
				paymentDetail.setEmployeeId(settlementDetail.getEmployeeId());
				paymentDetail.setOrderTaskpackagePaymentId(payment.getId());
				bizOrderTaskpackagePaymentDetailDao.insert(paymentDetail);

				/*
				 * List<BizOrderTaskpackagePayment> existPayments =
				 * bizOrderTaskpackagePaymentDao
				 * .findPaymentBySettlementId(settlement.getId());
				 * List<BizOrderTaskpackagePaymentDetail> paymentDetails =
				 * getDetails(settlementDetails); for
				 * (BizOrderTaskpackagePaymentDetail
				 * bizOrderTaskpackagePaymentDetail : paymentDetails) {
				 * bizOrderTaskpackagePaymentDetail.setOrderTaskpackagePaymentId
				 * (existPayments.get(0).getId());
				 * bizOrderTaskpackagePaymentDetailDao.insert(
				 * bizOrderTaskpackagePaymentDetail); }
				 */
			}

			/*
			 * List<BizOrderTaskpackagePayment> existPayments =
			 * bizOrderTaskpackagePaymentDao
			 * .findPaymentBySettlementId(settlement.getId());
			 * List<BizOrderTaskpackagePaymentDetail> paymentDetails =
			 * getDetails(settlementDetails); for
			 * (BizOrderTaskpackagePaymentDetail
			 * bizOrderTaskpackagePaymentDetail : paymentDetails) {
			 * bizOrderTaskpackagePaymentDetail.setOrderTaskpackagePaymentId(
			 * existPayments.get(0).getId());
			 * bizOrderTaskpackagePaymentDetailService.insert(
			 * bizOrderTaskpackagePaymentDetail);
			 */
			// bizOrderTaskpackagePaymentDetail.preInsert(); --批量
			// }
			// bizOrderTaskpackagePaymentDetailService.insertList(paymentDetails);--批量

		} else {// 分收尾款

			BizOrderTaskpackagePayment payment1 = getPayment(settlement);
			Integer advance = Integer.parseInt(bizTaskPackageTemplat.getAdvancePaymentRates());
			payment1.setAmount(settlement.getWorkerGroupSettleAmount() * advance / 100);
			payment1.setOrderTaskpackagePaymentType(ConstantUtils.PAYMENT_TYPE_0);
			// 首款 --状态设为 15-已审核通过待打款
			// payment1.setStatus(ConstantUtils.PAYMENT_STATUS_15);
			payment1.setPaymentRates(advance / 100.0);
			payment1.preInsert();

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("orderTaskpackageSettlementId", payment1.getOrderTaskpackageSettlementId());
			paramMap.put("orderTaskpackagPaymentType", payment1.getOrderTaskpackagePaymentType());
			int count = bizOrderTaskpackagePaymentDao.checkPaymentIsExistByParam(paramMap);
			if (count == 0) {
				bizOrderTaskpackagePaymentDao.insert(payment1);
				BizOrderTaskpackagePaymentDetail paymentDetail = new BizOrderTaskpackagePaymentDetail();
				paymentDetail.setAmount(payment1.getAmount());
				paymentDetail.setStatus(ConstantUtils.PAYMENT_DETAIL_STATUS_0);
				paymentDetail.setStatusDatetime(new Date());
				paymentDetail.setEmployeeId(settlementDetail.getEmployeeId());
				paymentDetail.setOrderTaskpackagePaymentId(payment1.getId());
				bizOrderTaskpackagePaymentDetailDao.insert(paymentDetail);
			}

			BizOrderTaskpackagePayment payment2 = getPayment(settlement);
			Integer rest = Integer.parseInt(bizTaskPackageTemplat.getRestPaymentRates());
			payment2.setAmount(settlement.getWorkerGroupSettleAmount() * rest / 100);
			payment2.setOrderTaskpackagePaymentType(ConstantUtils.PAYMENT_TYPE_1);

			// 判断尾款是否已经约检通过，若约检通过则设置尾款状态为15-已审核通过待打款，否则为10-已生成付款单
			Integer num = bizOrderTaskpackagePaymentDao.getBalPmtCheckNode(orderTaskackage);
			if (num > 0) {
				payment2.setStatus(ConstantUtils.PAYMENT_STATUS_15);
			} else {
				payment2.setStatus(ConstantUtils.PAYMENT_STATUS_10);
			}

			payment2.setPaymentRates(rest / 100.0);
			payment2.preInsert();

			Map<String, Object> paramMap2 = new HashMap<String, Object>();
			paramMap2.put("orderTaskpackageSettlementId", payment2.getOrderTaskpackageSettlementId());
			paramMap2.put("orderTaskpackagPaymentType", payment2.getOrderTaskpackagePaymentType());
			int count2 = bizOrderTaskpackagePaymentDao.checkPaymentIsExistByParam(paramMap2);
			if (count2 == 0) {
				bizOrderTaskpackagePaymentDao.insert(payment2);

				BizOrderTaskpackagePaymentDetail paymentDetail = new BizOrderTaskpackagePaymentDetail();
				paymentDetail.setAmount(payment2.getAmount());
				paymentDetail.setStatus(ConstantUtils.PAYMENT_DETAIL_STATUS_0);
				paymentDetail.setStatusDatetime(new Date());
				paymentDetail.setEmployeeId(settlementDetail.getEmployeeId());
				paymentDetail.setOrderTaskpackagePaymentId(payment2.getId());
				bizOrderTaskpackagePaymentDetailDao.insert(paymentDetail);
			}

			/*List<BizOrderTaskpackagePayment> existPayments = bizOrderTaskpackagePaymentDao
					.findPaymentBySettlementId(settlement.getId());
			for (BizOrderTaskpackagePayment payment : existPayments) {
				List<BizOrderTaskpackagePaymentDetail> paymentDetails = getDetails(settlementDetails);
				for (BizOrderTaskpackagePaymentDetail bizOrderTaskpackagePaymentDetail : paymentDetails) {
					bizOrderTaskpackagePaymentDetail.setOrderTaskpackagePaymentId(payment.getId());
					bizOrderTaskpackagePaymentDetail
							.setAmount(bizOrderTaskpackagePaymentDetail.getAmount() * payment.getPaymentRates());
					bizOrderTaskpackagePaymentDetailDao.insert(bizOrderTaskpackagePaymentDetail);
				}
			}*/

		}

		if ("2".equals(settlement.getSettleStyle())) {// 结算方式是包工
			// 生成项目经理材料结算类目明细
			PmSettleCategoryDetail details = new PmSettleCategoryDetail();
			details.setOrderId(Integer.valueOf(orderTaskackage.getOrderId()));
			details.setPmEmployeeId(Integer.valueOf(orderTaskackage.getItemManagerId()));
			details.setSettleCategory(ConstantUtils.PM_SETTLE_CATEGORY_12);
			details.setSettleAmount(settlement.getPmMaterialsSettleAmount());
			details.setSettleStatus(ConstantUtils.PM_SETTLE_STATUS_10);
			details.setSettleStatusTime(date);
			details.setSettleRemark("");
			details.setRelatedBussinessId(settlement.getId());
			details.setCreateBy(user);
			details.setCreateDate(date);
			details.setUpdateBy(user);
			details.setUpdateDate(date);
			details.setDelFlag("0");
			details.setSettleRole(ConstantUtils.SETTLE_ROLE_1);
			inspectorConfirmService.saveDetail(details);
		}

	}

	/**
	 * 驳回
	 * 
	 * @param orderTaskpackageId
	 * @param reason
	 * @param date
	 */
	@Autowired

	private AllotWorkerGroupDao allotWorkerGroupDao;

	@Transactional(readOnly = false)
	public void updateRefusedReason(Integer orderTaskpackageId, String reason, Date date, String statusName,
			String status) {
		// 根据任务包的id修改任务包的状态
		// OrderTaskpackage orderTaskackage =
		// allotWorkerGroupService.findTargetPackageById(orderTaskpackageId);
		OrderTaskpackage orderTaskackage = allotWorkerGroupDao.findTargetPackageById(orderTaskpackageId);
		orderTaskackage.setPackageStateId(status);
		orderTaskackage.setPackageStatename(statusName);
		allotWorkerGroupDao.updatePackage(orderTaskackage);
		// 跟新结算单 日期 驳回理由
		dao.updateRefusedReason(orderTaskpackageId, reason, date, ConstantUtils.SETTLEMENT_STATUS_70);
		// 添加状态日志信息
		BizBusinessStatusLog statusLog = new BizBusinessStatusLog();
		statusLog.setBusinessType(BusinessLogConstantUtil.BUSINESS_TYPE_601);
		statusLog.setBusinessOnlyMarkInt(orderTaskpackageId);
		statusLog.setBusinessStatus(ConstantUtils.SETTLEMENT_STATUS_70);
		statusLog.setStatusDatetime(new Date());
		User user = UserUtils.getUser();
		if (StringUtils.isNoneBlank(user.getEmpId())) {
			statusLog.setBusinessEmployeeId(Integer.parseInt(user.getEmpId()));
		}
		statusLog.setBusinessRemarks("工人结算单驳回");
		statusLog.preInsert();
		bizBusinessStatusLogDao.insert(statusLog);
	}

	@Autowired
	private BizSeiralnumService bizSeiralnumService;

	// 封装付款单
	public BizOrderTaskpackagePayment getPayment(BizOrderTaskpackageSettlement settlement) {

		BizOrderTaskpackagePayment payment = new BizOrderTaskpackagePayment();
		payment.setStoreId(settlement.getStoreid());
		payment.setAmount(settlement.getWorkerGroupSettleAmount());
		payment.setOrderTaskpackageSettlementId(settlement.getId());
		payment.setOrderTaskpackagePaymentCode(bizSeiralnumService.getDateSequence("FK"));
		// payment.setStatus(ConstantUtils.PAYMENT_STATUS_10); //---10-已生成付款单
		payment.setStatus(ConstantUtils.PAYMENT_STATUS_15); // ---15 已审核通过待打款
		payment.setStatusDatetime(new Date());
		payment.setGeneratedDatetime(new Date());
		return payment;
	}

	// 封装付款明细
	public List<BizOrderTaskpackagePaymentDetail> getDetails(List<OrderTaskpackageSettlementDetail> settlementDetails) {

		List<BizOrderTaskpackagePaymentDetail> list = new ArrayList<BizOrderTaskpackagePaymentDetail>();
		for (OrderTaskpackageSettlementDetail settlementDetail : settlementDetails) {
			BizOrderTaskpackagePaymentDetail paymentDetail = new BizOrderTaskpackagePaymentDetail();
			paymentDetail.setAmount(settlementDetail.getPaymentAmount());
			paymentDetail.setStatus(ConstantUtils.PAYMENT_DETAIL_STATUS_0);
			paymentDetail.setStatusDatetime(new Date());
			paymentDetail.setEmployeeId(settlementDetail.getEmployeeId());
			list.add(paymentDetail);
		}
		return list;

	}

	public Page<BizOrderTaskpackageSettlementVo> findSettlementPage(Page<BizOrderTaskpackageSettlementVo> page,
			BizOrderTaskpackageSettlementVo entity) {
		entity.setPage(page);
		page.setList(dao.findSettlementList(entity));
		return page;
	}

	public Page<BizOrderTaskpackageSettlementVo> querySettleCancel(Page<BizOrderTaskpackageSettlementVo> page,
			BizOrderTaskpackageSettlementVo entity) {
		entity.setPage(page);
		page.setList(dao.querySettleCancel(entity));
		return page;
	}

	@Transactional(readOnly = false)
	public void updateSettlement(OrderTaskpackageSettlement settlement) {
		Date date = new Date();
		// 1.更新工程清单
		if (!Collections3.isEmpty(settlement.getOrderTaskProcedure())) {
			for (BizOrderTaskpackageProcedure pro : settlement.getOrderTaskProcedure()) {
				BizOrderTaskpackageProcedure procedure = orderTaskpackageProcedureDao.get(pro.getId());
				procedure.setRecheckNumber(pro.getRecheckNumber());
				procedure.setSettlementNumber(pro.getSettlementNumber());
				procedure.setLaborAuxiliaryMaterialsSettleAmount(pro.getLaborAuxiliaryMaterialsSettleAmount());
				procedure.setLaborSettleAmount(pro.getLaborSettleAmount());
				procedure.setAuxiliaryMaterialsSettleAmount(pro.getAuxiliaryMaterialsSettleAmount());
				procedure.setUpdateDate(date);
				procedure.setModifyNumberReason(pro.getModifyNumberReason());
				orderTaskpackageProcedureDao.update(procedure);
			}
		}

		// 2.更新结算单
		OrderTaskpackageSettlement taskSettlement = orderTaskpackageSettlementDao.get(settlement.getId());
		// 添加结算单更新日志
		User user = UserUtils.getUser();
		BizOrderTaskpackageSettlementUpdateLog bizOrderTaskpackageSettlementUpdateLog = new BizOrderTaskpackageSettlementUpdateLog();
		bizOrderTaskpackageSettlementUpdateLog.setOrderTaskpackageSettlementId(settlement.getId());
		bizOrderTaskpackageSettlementUpdateLog.setAmountOld(taskSettlement.getSettlementAmount());
		bizOrderTaskpackageSettlementUpdateLog.setAmountNew(settlement.getSettlementAmount());
		if (user != null && user.getEmpId() != null && user.getEmpId().equals("")) {
			bizOrderTaskpackageSettlementUpdateLog.setOperatorEmployeeId(Integer.parseInt(user.getEmpId()));
		}
		bizOrderTaskpackageSettlementUpdateLog.setOperateDatetime(date);
		bizOrderTaskpackageSettlementUpdateLog.preInsert();

		taskSettlement.setGuaranteeMoneyAmount(settlement.getGuaranteeMoneyAmount());
		taskSettlement.setSettlementAmount(settlement.getSettlementAmount());

		taskSettlement.setSettleStyle(settlement.getSettleStyle());
		taskSettlement.setLaborAuxiliaryMaterialsSettleAmount(settlement.getLaborAuxiliaryMaterialsSettleAmount());
		taskSettlement.setLaborSettleAmount(settlement.getLaborSettleAmount());
		taskSettlement.setAuxiliaryMaterialsSettleAmount(settlement.getAuxiliaryMaterialsSettleAmount());
		taskSettlement.setPmMaterialsSettleAmount(settlement.getPmMaterialsSettleAmount());
		taskSettlement.setWorkerGroupSettleAmount(settlement.getWorkerGroupSettleAmount());

		taskSettlement.setCompanyDeductAmount(settlement.getCompanyDeductAmount());
		taskSettlement.setCompanyDeductReason(settlement.getCompanyDeductReason());
		taskSettlement.setQcPunishMoneyAmount(settlement.getQcPunishMoneyAmount());
		taskSettlement.setUpdateDate(date);
		orderTaskpackageSettlementDao.update(taskSettlement);
		bizOrderTaskpackageSettlementUpdateLogDao.insert(bizOrderTaskpackageSettlementUpdateLog);
		// 3.修改质保金并修改质保金余额信息
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderTaskpackageId", taskSettlement.getOrderTaskpackageId());
		map.put("settlementId", settlement.getId());
		GuaranteeMoney money = guaranteeMoneyDao.queryGuarnteeMoney(map);
		BizGuaranteeMoneyBalance bizGuaranteeMoneyBalance = null;
		if (money != null) {
			bizGuaranteeMoneyBalance = bizGuaranteeMoneyBalanceDao
					.findGuaranteeMoneyBalanceByEmployeeId(money.getEmployeeId());
		}
		if (bizGuaranteeMoneyBalance != null) {
			if (money != null) {
				if (money.getGuaranteeMoneyAmount() == null) {
					money.setGuaranteeMoneyAmount(0.0);
				}
				if (bizGuaranteeMoneyBalance.getGuaranteeMoneyBalance() - money.getGuaranteeMoneyAmount() < 0) {
					bizGuaranteeMoneyBalance.setGuaranteeMoneyBalance(0d);
				} else {
					bizGuaranteeMoneyBalance.setGuaranteeMoneyBalance(
							bizGuaranteeMoneyBalance.getGuaranteeMoneyBalance() - money.getGuaranteeMoneyAmount());
				}
				bizGuaranteeMoneyBalance.setGuaranteeMoneyAmountPaidSettle(
						bizGuaranteeMoneyBalance.getGuaranteeMoneyAmountPaidSettle() - money.getGuaranteeMoneyAmount());
			}
		}
		if (money != null) {
			money.setGuaranteeMoneyAmount(
					settlement.getGuaranteeMoneyAmount() == null ? 0d : settlement.getGuaranteeMoneyAmount());
			money.setGuaranteeMoneyAmountTotal(
					money.getGuaranteeMoneyAmount() + (settlement.getGuaranteeMoneyAmountTotal() == null ? 0d
							: settlement.getGuaranteeMoneyAmountTotal()));
			money.setDeductTime(date);
			money.setUpdateDate(date);
			if (bizGuaranteeMoneyBalance == null) {
				bizGuaranteeMoneyBalance = new BizGuaranteeMoneyBalance();
				bizGuaranteeMoneyBalance.setEmployeeId(money.getEmployeeId());
				bizGuaranteeMoneyBalance.setGuaranteeMoneyBalance(money.getGuaranteeMoneyAmountTotal());
				bizGuaranteeMoneyBalance.setGuaranteeMoneyAmountPaidSettle(money.getGuaranteeMoneyAmountTotal());
			} else {
				bizGuaranteeMoneyBalance.setGuaranteeMoneyAmountPaidSettle(
						bizGuaranteeMoneyBalance.getGuaranteeMoneyAmountPaidSettle() + money.getGuaranteeMoneyAmount());
				bizGuaranteeMoneyBalance.setGuaranteeMoneyBalance(
						bizGuaranteeMoneyBalance.getGuaranteeMoneyBalance() + money.getGuaranteeMoneyAmount());
			}
			money.setGuaranteeMoneyBalance(bizGuaranteeMoneyBalance.getGuaranteeMoneyBalance());
			guaranteeMoneyDao.updateGuaranteeMoney(money);
			if (bizGuaranteeMoneyBalance.getId() == null) {
				bizGuaranteeMoneyBalance.preInsert();
				bizGuaranteeMoneyBalanceDao.insert(bizGuaranteeMoneyBalance);
			} else {
				bizGuaranteeMoneyBalance.preUpdate();
				bizGuaranteeMoneyBalanceDao.update(bizGuaranteeMoneyBalance);
			}
		}

	}

	public Page<BizOrderTaskpackageSettlementVo> findSettlementAllPage(Page<BizOrderTaskpackageSettlementVo> page,
			BizOrderTaskpackageSettlementVo entity) {
		entity.setPage(page);
		if (StringUtils.isNoneBlank(entity.getStatus())) {
			List<String> list = new ArrayList<String>();
			String[] status = entity.getStatus().split(",");
			for (String s : status) {
				list.add(s);
			}
			entity.setSettlementStatus(list);
		}
		List<BizOrderTaskpackageSettlementVo> settleList = dao.findSettlementAllList(entity);
		Map<String,Object> param = new HashMap<String,Object>();
		List<Integer> ids = new ArrayList<Integer>();
		if(settleList != null && settleList.size()>0){
			for(BizOrderTaskpackageSettlementVo vo : settleList){
				ids.add(vo.getId());
			}
			if(ids.size()>0){
				param.put("settlementIds", ids);
				List<BizOrderTaskpackagePayment> payList = bizOrderTaskpackagePaymentDao.findPaymentBySettlementIds(param);
				if(payList != null && payList.size()>0){
					for(BizOrderTaskpackageSettlementVo settle : settleList){
						for(BizOrderTaskpackagePayment payment : payList){
							if(settle.getId().equals(payment.getOrderTaskpackageSettlementId())){
								if(payment.getOrderTaskpackagePaymentType().equals("0")){
									settle.setAdvanceAmount(payment.getAmount());
								}
								if(payment.getOrderTaskpackagePaymentType().equals("1")){
									settle.setRestAmount(payment.getAmount());
								}
							}
						}
					}
				}
			}	
		}
		
		
		page.setList(settleList);
		return page;
	}

	public Date queryConfirmSalaryTime(Integer id) {
		// TODO Auto-generated method stub
		return dao.queryConfirmSalaryTime(id);
	}

	public Page<BizOrderTaskpackageSettlementVo> findSettlementTaskPage(Page<BizOrderTaskpackageSettlementVo> page,
			BizOrderTaskpackageSettlementVo entity) {
		entity.setPage(page);
		page.setList(dao.findSettlementTaskList(entity));
		return page;
	}

	/**
	 * 结算单撤回
	 * 
	 * @return
	 */
	@Transactional(readOnly = false)
	public String settleCancel(Integer id, String operateRemarks) {
		String result = "success";
		// 更新结算单对应的付款单、付款单明细的状态
		List<BizOrderTaskpackagePayment> paymentList = bizOrderTaskpackagePaymentDao.findPaymentBySettlementId(id);
		if (paymentList != null) {
			/*
			 * if (ConstantUtils.PAYMENT_STATUS_93.equals(paymentList.get(0).
			 * getStatus())) { result = "repeat"; return result; }
			 */
			for (BizOrderTaskpackagePayment payment : paymentList) {
				payment.setStatus(ConstantUtils.PAYMENT_STATUS_93);
				payment.setStatusDatetime(new Date());
				payment.preUpdate();
				bizOrderTaskpackagePaymentDao.update(payment);

				List<BizOrderTaskpackagePaymentDetail> parmentDetailList = bizOrderTaskpackagePaymentDetailDao
						.findByPaymentId(payment.getId());
				if (parmentDetailList != null) {
					for (BizOrderTaskpackagePaymentDetail paymentDetail : parmentDetailList) {
						paymentDetail.setStatus(ConstantUtils.PAYMENT_DETAIL_STATUS_93);
						paymentDetail.setStatusDatetime(new Date());
						paymentDetail.preUpdate();
						bizOrderTaskpackagePaymentDetailDao.update(paymentDetail);
					}
				}
			}
		}

		// 更新结算单状态
		BizOrderTaskpackageSettlement bizOrderTaskpackageSettlement = bizOrderTaskpackageSettlementDao.get(id);
		bizOrderTaskpackageSettlement.setStatus(ConstantUtils.SETTLEMENT_STATUS_65);
		bizOrderTaskpackageSettlement.preUpdate();
		bizOrderTaskpackageSettlementDao.update(bizOrderTaskpackageSettlement);

		// 更新任务包状态
		OrderTaskpackage orderTaskackage = allotWorkerGroupDao
				.findTargetPackageById(bizOrderTaskpackageSettlement.getOrderTaskpackageId());
		orderTaskackage.setPackageStateId("120");
		orderTaskackage.setPackageStatename("工人已确认分配金额");
		orderTaskackage.preUpdate();
		allotWorkerGroupDao.updatePackage(orderTaskackage);

		if ("2".equals(bizOrderTaskpackageSettlement.getSettleStyle())) {// 包工
			// 更新项目经理材料结算类目
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("orderId", orderTaskackage.getOrderId());
			param.put("settleStatus", ConstantUtils.PM_SETTLE_STATUS_15);
			param.put("settleCategory", ConstantUtils.PM_SETTLE_CATEGORY_12);
			param.put("relatedBusinessId", bizOrderTaskpackageSettlement.getId());
			bizPmSettleCategoryDetailDao.updateStatusByParam(param);
		}

		// 添加结算单撤回日志
		BizOrderTaskpackageSettleMentCancleLog bizOrderTaskpackageSettleMentCancleLog = new BizOrderTaskpackageSettleMentCancleLog();
		bizOrderTaskpackageSettleMentCancleLog.setOperateType("1");
		bizOrderTaskpackageSettleMentCancleLog.setBizOrderTaskpackageSettlemenId(id);
		bizOrderTaskpackageSettleMentCancleLog.setOperateRemarks(operateRemarks);
		bizOrderTaskpackageSettleMentCancleLog.preInsert();
		bizOrderTaskpackageSettleMentCancleLogDao.insert(bizOrderTaskpackageSettleMentCancleLog);
		return result;
	}
}
