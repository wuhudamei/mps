package cn.damei.service.mobile.Manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.dao.mobile.Manager.PmSettlementBudgetDao;
import cn.damei.entity.mobile.Manager.PmSettlementBudget;
import cn.damei.dao.modules.BizAssessRewardPunishDao;
import cn.damei.entity.modules.BizAssessRewardPunish;
import cn.damei.entity.modules.BizMaterialsStandardReceiveBill;
import cn.damei.dao.modules.PmMaterialsSettleInfoDao;
import cn.damei.entity.modules.PmMaterialsSettleInfo;
import cn.damei.dao.modules.BizPmGuaranteeMoneyCnfgSnapDao;
import cn.damei.entity.modules.BizPmGuaranteeMoneyCnfgSnap;
import cn.damei.entity.modules.BizPmStarCommissionCnfgSnap;
import cn.damei.dao.modules.BizGuaranteeMoneyBalanceDao;
import cn.damei.entity.modules.BizGuaranteeMoneyBalance;
import cn.damei.dao.modules.InspectorConfirmDao;

/**
 * 项目经理订单结算金额预览Service
 * @author hyh
 *
 */
@Service
@Transactional(readOnly = false)
public class PmSettlementBudgetService extends CrudService2<PmSettlementBudgetDao, PmSettlementBudget> {
	@Autowired
	private InspectorConfirmDao inspectorConfirmDao;
	@Autowired
	private PmSettlementBudgetDao pmSettlementBudgetDao;
	@Autowired
	private PmMaterialsSettleInfoDao pmMaterialsSettleInfoDao;
	@Autowired
	private BizAssessRewardPunishDao bizAssessRewardPunishDao;
	@Autowired
	private BizPmGuaranteeMoneyCnfgSnapDao bizPmGuaranteeMoneyCnfgSnapDao;
	@Autowired
	private BizGuaranteeMoneyBalanceDao bizGuaranteeMoneyBalanceDao;

	public List<PmSettlementBudget> queryPmSettlementBudgetByManagerId(Integer managerId) {
		List<PmSettlementBudget> list = pmSettlementBudgetDao.queryPmSettlementBudgetByManagerId(managerId);
		if (list != null && list.size() > 0) {
			for (PmSettlementBudget pmSettlementBudget : list) {
				if (pmSettlementBudget.getSettleBillType().equals("10")) {
					getMidwaySettlementBudgetAmount(pmSettlementBudget);
				} else if (pmSettlementBudget.getSettleBillType().equals("20")) {
					getCompleteSettlementBudgetAmount(pmSettlementBudget);
				}
			}
		}

		return list;
	}

	/**
	 * 中期预计结算
	 * 
	 * @param budget
	 */
	public void getMidwaySettlementBudgetAmount(PmSettlementBudget budget) {
		Integer orderId = budget.getOrderId();
		BizPmStarCommissionCnfgSnap bizPmStarCommissionCnfgSnap = inspectorConfirmDao
				.queryManagerCommissionByOrderId(orderId);
		// 中期提成
		Double commissionAmount = 0.0;
		if(bizPmStarCommissionCnfgSnap != null){
			commissionAmount = bizPmStarCommissionCnfgSnap.getCommissionAmount()
					* bizPmStarCommissionCnfgSnap.getCommissionRateMidway();
		}
		// 标化材料扣款金额
		List<BizMaterialsStandardReceiveBill> list = inspectorConfirmDao.findThree(orderId);
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
		// 自主支配金额
		Double managerOwnpay = inspectorConfirmDao.queryManagerOwnpay(orderId);
		// 中期罚款
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderId", orderId);
		map.put("settleCategory", ConstantUtils.PM_SETTLE_CATEGORY_4);
		map.put("settleStatus", 10);
		map.put("settleRole", ConstantUtils.SETTLE_ROLE_1);
		map.put("pmEmployeeId", budget.getPmEmployeeId());
		Double managerPenalty = inspectorConfirmDao.queryManangerPenalty(map);
		if (managerPenalty < 0) {
			managerPenalty = 0 - managerPenalty;
		}
		// 中期任务包材料结算总金额
		double pmMaterialsSettleAmount = 0.00;
		Map<String,Object> pmMaterialsParam = new HashMap<String,Object>();
		pmMaterialsParam.put("orderId", orderId);
		pmMaterialsParam.put("pmEmployeeId", budget.getPmEmployeeId());
		List<PmMaterialsSettleInfo> pmMaterials = pmMaterialsSettleInfoDao.queryPmMaterialsInfoByParam(pmMaterialsParam);
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
		pmRewardAmount = bizAssessRewardPunishDao.queryTotalAmountByParam(rewardPunish);
		// 中期扣款金额
		double pmPunishAmount = 0.0;
		rewardPunish.setIsRewardOrPunish("2");
		pmPunishAmount = bizAssessRewardPunishDao.queryTotalAmountByParam(rewardPunish);

		//巡检
		rewardPunish.setIsMonthInspection("1");
		// 中期巡检奖励金额
		double pmInspectionRewardAmount = 0.0;
		rewardPunish.setIsRewardOrPunish("1");
		pmInspectionRewardAmount = bizAssessRewardPunishDao.queryTotalAmountByParam(rewardPunish);
		// 中期巡检罚款金额
		double pmInspectionPunishAmount = 0.0;
		rewardPunish.setIsRewardOrPunish("2");
		pmInspectionPunishAmount = bizAssessRewardPunishDao.queryTotalAmountByParam(rewardPunish);

		double settleAmount = commissionAmount + managerOwnpay - managerPenalty - details3.getReceiveBillAmount()
				+ pmMaterialsSettleAmount + pmRewardAmount - pmPunishAmount + pmInspectionRewardAmount - pmInspectionPunishAmount;
		budget.setMidwayCommissionAmount(commissionAmount);
		budget.setOwnpayAmount(managerOwnpay);
		budget.setMaterialsStandardAmount(details3.getReceiveBillAmount());
		budget.setMidwayQcCheckPunishAmount(managerPenalty);
		budget.setMidwayRewardAmount(pmRewardAmount);
		budget.setMidwayPunishAmount(pmPunishAmount);
		budget.setMidwayInspectionRewardAmount(pmInspectionRewardAmount);
		budget.setMidwayInspectionPunishAmount(pmInspectionPunishAmount);
		budget.setMidwayAuxiliaryMaterialsSettleAmount(pmMaterialsSettleAmount);
		budget.setSettlementBudgetAmount(settleAmount);
	}

	/**
	 * 竣工预计结算
	 * 
	 * @param budget
	 */
	public void getCompleteSettlementBudgetAmount(PmSettlementBudget budget) {
		Integer orderId = budget.getOrderId();
		BizPmStarCommissionCnfgSnap bizPmStarCommissionCnfgSnap = inspectorConfirmDao
				.queryManagerCommissionByOrderId(orderId);
		// 竣工提成
		Double commissionAmount = 0.0;
		if(bizPmStarCommissionCnfgSnap != null){
			commissionAmount = bizPmStarCommissionCnfgSnap.getCommissionAmount()
					* bizPmStarCommissionCnfgSnap.getCommissionRateComplete();
		}
		// 质保金
		Double pmGuaranteeMoney = 0.0;
		BizPmGuaranteeMoneyCnfgSnap gmcs = bizPmGuaranteeMoneyCnfgSnapDao.findGmc(orderId);
		if (gmcs != null) {
			BizGuaranteeMoneyBalance bizGuaranteeMoneyBalance = bizGuaranteeMoneyBalanceDao
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

		// 自采材料报销金额
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("orderId", orderId);
		map1.put("settleCategory", ConstantUtils.PM_SETTLE_CATEGORY_11);
		map1.put("pmEmployeeId", budget.getPmEmployeeId());
		Double sinceMaterials = inspectorConfirmDao.queryManangerPenalty(map1);
		if (sinceMaterials < 0) {
			sinceMaterials = 0 - sinceMaterials;
		}

		double managerPenalty = 0.00;
		double pmMaterialsSettleAmount = 0.00;
		double pmRewardAmount = 0.0;
		double pmPunishAmount = 0.0;
		double pmInspectionRewardAmount = 0.0;
		double pmInspectionPunishAmount = 0.0;
		int count = dao.checkQcBillByOrderId(orderId);
		// 中期结算的约检节点已通过
		if (count > 0) {
			// 竣工罚款
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orderId", orderId);
			map.put("settleCategory", ConstantUtils.PM_SETTLE_CATEGORY_4);
			map.put("settleStatus", 10);
			map.put("settleRole", ConstantUtils.SETTLE_ROLE_1);
			map.put("pmEmployeeId", budget.getPmEmployeeId());
			managerPenalty = inspectorConfirmDao.queryManangerPenalty(map);
			if (managerPenalty < 0) {
				managerPenalty = 0 - managerPenalty;
			}

			// 竣工任务包材料结算总金额
			Map<String,Object> pmMaterialsParam = new HashMap<String,Object>();
			pmMaterialsParam.put("orderId", orderId);
			pmMaterialsParam.put("pmEmployeeId", budget.getPmEmployeeId());
			List<PmMaterialsSettleInfo> pmMaterials = pmMaterialsSettleInfoDao.queryPmMaterialsInfoByParam(pmMaterialsParam);
			if (pmMaterials != null && pmMaterials.size() > 0) {
				for (PmMaterialsSettleInfo info : pmMaterials) {
					pmMaterialsSettleAmount = pmMaterialsSettleAmount + info.getPmMaterialsSettleAmount();
				}
			}

			// 竣工奖励金额
			BizAssessRewardPunish rewardPunish = new BizAssessRewardPunish();
			rewardPunish.setRelatedBusinessIdInt(orderId);
			rewardPunish.setRewardPunishTargetEmployeeId(bizPmStarCommissionCnfgSnap.getPmEmployeeId());
			rewardPunish.setRewardPunishTargetEmployeeType("1");
			rewardPunish.setRewardPunishTargetType("10");
			rewardPunish.setIsRewardOrPunish("1");
			rewardPunish.setRewardPunishStatus("1");
			rewardPunish.setIsMonthInspection("0");
			rewardPunish.setRewardPunishTargetEmployeeId(budget.getPmEmployeeId());
			pmRewardAmount = bizAssessRewardPunishDao.queryTotalAmountByParam(rewardPunish);

			// 竣工扣款金额
			rewardPunish.setIsRewardOrPunish("2");
			pmPunishAmount = bizAssessRewardPunishDao.queryTotalAmountByParam(rewardPunish);

			// 巡检
			rewardPunish.setIsMonthInspection("1");
			// 竣工巡检奖励金额
			rewardPunish.setIsRewardOrPunish("1");
			pmInspectionRewardAmount = bizAssessRewardPunishDao.queryTotalAmountByParam(rewardPunish);
			// 竣工巡检罚款金额
			rewardPunish.setIsRewardOrPunish("2");
			pmInspectionPunishAmount = bizAssessRewardPunishDao.queryTotalAmountByParam(rewardPunish);
		}

		// 竣工提成合计金额
		Double settleAmount = commissionAmount + sinceMaterials - pmGuaranteeMoney - managerPenalty
				+ pmMaterialsSettleAmount + pmRewardAmount - pmPunishAmount + pmInspectionRewardAmount - pmInspectionPunishAmount;

		budget.setCompleteCommissionAmount(commissionAmount);
		budget.setCompletQcCheckPunishAmount(managerPenalty);
		budget.setCompleteRewardAmount(pmRewardAmount);
		budget.setCompletePunishAmount(pmPunishAmount);
		budget.setCompleteInspectionRewardAmount(pmInspectionRewardAmount);
		budget.setCompleteInspectionPunishAmount(pmInspectionPunishAmount);
		budget.setMaterialSelfbuyReimburseAmount(sinceMaterials);
		budget.setGuaranteeMoneyAmount(pmGuaranteeMoney);
		budget.setCompleteAuxiliaryMaterialsSettleAmount(pmMaterialsSettleAmount);
		budget.setSettlementBudgetAmount(settleAmount);
	}
	
	public int checkQcBillByOrderId(int orderId){
		return dao.checkQcBillByOrderId(orderId);
	}

}
