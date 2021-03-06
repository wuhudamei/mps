
package cn.damei.service.modules;

import cn.damei.common.constantUtils.BusinessLogConstantUtil;
import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.DateUtils;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.MD5Utils;
import cn.damei.dao.mobile.Inspector.CheckConfirmDao;
import cn.damei.entity.mobile.Inspector.CheckConfirm;
import cn.damei.dao.modules.BizAssessRewardPunishDao;
import cn.damei.entity.modules.BizAssessRewardPunish;
import cn.damei.dao.modules.BizOrderFinanceCollectionDao;
import cn.damei.dao.modules.BizBusinessStatusLogDao;
import cn.damei.entity.modules.BizBusinessStatusLog;
import cn.damei.entity.modules.ReportCheckDetailsPic;
import cn.damei.entity.modules.BizQcLongwayCommissionCnfgSnap;
import cn.damei.dao.modules.BizQcLongwayCommissionLogDao;
import cn.damei.entity.modules.BizQcLongwayCommissionLog;
import cn.damei.entity.modules.BizQcStarCommissionCnfgSnap;
import cn.damei.dao.modules.BizQcStarCommissionLogDao;
import cn.damei.entity.modules.BizQcStarCommissionLog;
import cn.damei.dao.modules.BizSynDataDao;
import cn.damei.entity.modules.BizSynData;
import cn.damei.entity.modules.BizOrderMaterialsStandard;
import cn.damei.entity.modules.BizMaterialsStandardReceiveBill;
import cn.damei.dao.modules.OrderDao2;
import cn.damei.entity.modules.Order2;
import cn.damei.dao.modules.BizOrderTaskpackagePaymentDao;
import cn.damei.entity.modules.BizOrderTaskpackagePaymentVo;
import cn.damei.dao.modules.PmMaterialsSettleInfoDao;
import cn.damei.entity.modules.PmMaterialsSettleInfo;
import cn.damei.dao.modules.BizPmOwnpayLogDao;
import cn.damei.entity.modules.BizPmOwnpayLog;
import cn.damei.entity.modules.BizPmOwnpayCnfgSnap;
import cn.damei.dao.modules.BizPmSettleBillDao;
import cn.damei.entity.modules.BizPmSettleBill;
import cn.damei.dao.modules.BizPmSettleCategoryDetailDao;
import cn.damei.entity.modules.BizPmSettleCategoryDetail;
import cn.damei.dao.modules.BizPmSettleCategorySummaryDao;
import cn.damei.entity.modules.BizPmSettleCategorySummary;
import cn.damei.dao.modules.BizPmStarCommissionLogDao;
import cn.damei.entity.modules.BizPmStarCommissionLog;
import cn.damei.entity.modules.BizPmStarCommissionCnfgSnap;
import cn.damei.dao.modules.InspectorConfirmDao;
import cn.damei.entity.modules.InspectorConfirm;
import cn.damei.entity.modules.PmSettleCategoryDetail;
import cn.damei.entity.modules.QcBillCheckItemInfo;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.UserUtils;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
@Transactional(readOnly = true)
public class InspectorConfirmService extends CrudService<InspectorConfirmDao, InspectorConfirm> {

	@Autowired
	private CheckConfirmDao checkConfirmDao;
	@Autowired
	private BizOrderTaskpackagePaymentDao bizOrderTaskpackagePaymentDao;
	@Autowired
	private BizPmStarCommissionLogDao bizPmStarCommissionLogDao;
	@Autowired
	private BizPmOwnpayLogDao bizPmOwnpayLogDao;
	@Autowired
	private BizQcLongwayCommissionLogDao bizQcLongwayCommissionLogDao;
	@Autowired
	private BizQcStarCommissionLogDao bizQcStarCommissionLogDao;
	@Autowired
	private OrderDao2 orderDao2;
	@Autowired
	private BizSynDataDao bizSynDataDao;

	@Autowired
	private BizBusinessStatusLogDao logDao;
	@Autowired
	private BizAssessRewardPunishDao bizAssessRewardPunishDao;
	@Autowired
	private PmMaterialsSettleInfoDao pmMaterialsSettleInfoDao;

	@Autowired
	private BizOrderFinanceCollectionDao bizOrderFinanceCollectionDao;

	@Autowired
	private BizPmSettleBillDao bizPmSettleBillDao;
	@Autowired
	private BizPmSettleCategoryDetailDao bizPmSettleCategoryDetailDao;
	@Autowired
	private BizPmSettleCategorySummaryDao bizPmSettleCategorySummaryDao;
	@Autowired
	private BizSeiralnumService bizSeiralnumService;

	public InspectorConfirm get(String id) {
		return super.get(id);
	}

	public List<InspectorConfirm> findList(InspectorConfirm InspectorConfirm) {
		return super.findList(InspectorConfirm);
	}

	public Page<InspectorConfirm> findPage(Page<InspectorConfirm> page, InspectorConfirm InspectorConfirm) {
		return super.findPage(page, InspectorConfirm);
	}


	public List<ReportCheckDetailsPic> findPic(ReportCheckDetailsPic reportCheckDetailsPic) {
		return dao.findPic(reportCheckDetailsPic);
	}


	@Transactional(readOnly = false)
	public void updateQcBill(InspectorConfirm inspectorConfirm) {
		dao.updateQcBill(inspectorConfirm);
	}


	@Transactional(readOnly = false)
	public void saveDetail(PmSettleCategoryDetail details) {
		dao.saveDetail(details);
	}


	public BizPmStarCommissionCnfgSnap findFirst(Integer orderId) {
		return dao.findFirst(orderId);
	}

	public BizPmStarCommissionCnfgSnap queryManagerCommissionByOrderId(Integer orderId) {
		return dao.queryManagerCommissionByOrderId(orderId);
	}

	public BizPmStarCommissionCnfgSnap queryManagerCommissionByParam(Map<String, Object> param) {
		return dao.queryManagerCommissionByParam(param);
	}


	public double queryManagerOwnpay(Integer orderId) {
		return dao.queryManagerOwnpay(orderId);
	}


	public double queryManangerPenalty(Map<String, Object> map) {
		return dao.queryManangerPenalty(map);
	}


	public List<PmSettleCategoryDetail> findSecond(PmSettleCategoryDetail qcBreak) {
		return dao.findSecond(qcBreak);
	}


	@Transactional(readOnly = false)
	public void updateDetail(PmSettleCategoryDetail inspectorCheck) {
		dao.updateDetail(inspectorCheck);
	}


	public List<BizMaterialsStandardReceiveBill> findThree(Integer orderId) {
		return dao.findThree(orderId);
	}


	@Transactional(readOnly = false)
	public void updateMaterials(BizMaterialsStandardReceiveBill details3) {
		dao.updateMaterials(details3);
	}


	public List<BizPmOwnpayCnfgSnap> findFour(Integer orderId) {
		return dao.findFour(orderId);
	}


	@Transactional(readOnly = false)
	public void saveDetailAll(List<PmSettleCategoryDetail> list4) {
		dao.saveDetailAll(list4);
	}


	@Transactional(readOnly = false)
	public void updateDetailAll(PmSettleCategoryDetail qcBreak) {
		dao.updateDetailAll(qcBreak);

	}


	public BizQcLongwayCommissionCnfgSnap findFive(Integer orderId) {
		return dao.findFive(orderId);
	}


	public BizQcStarCommissionCnfgSnap findSix(Integer orderId) {
		return dao.findSix(orderId);
	}

	public InspectorConfirm findById(Integer qcBillId) {

		return dao.findById(qcBillId);
	}


	@Transactional(readOnly = false)
	public String pass(Integer orderId, Integer qcBillId, Integer qcCheckNodeId) throws Exception {
		String result = "0";
		DecimalFormat df = new DecimalFormat("#.00");
		Date date = new Date();
		User user = UserUtils.getUser();
		InspectorConfirm confirm = dao.findById(qcBillId);
		if (confirm.getStatus().equals("30")) {
			result = "2";
			return result;
		}
		Order2 order = orderDao2.get(orderId);
		if (order.getProjectMode().equals("1")) {
			BizPmStarCommissionCnfgSnap bizPmStarCommissionCnfgSnap = dao.findFirst(orderId);

			Double commissionAmount = bizPmStarCommissionCnfgSnap.getCommissionAmount()
					* bizPmStarCommissionCnfgSnap.getCommissionRateMidway();

			List<BizMaterialsStandardReceiveBill> list2 = dao.findThree(orderId);
			BizMaterialsStandardReceiveBill details3 = new BizMaterialsStandardReceiveBill();
			details3.setReceiveBillAmount(0.0);
			if (list2 != null && list2.size() > 0) {
				for (BizMaterialsStandardReceiveBill bill : list2) {
					details3.setReceiveBillAmount(details3.getReceiveBillAmount() + bill.getReceiveBillAmount());
				}
			}

			if (details3.getReceiveBillAmount() < 0) {
				details3.setReceiveBillAmount((0 - details3.getReceiveBillAmount()));
			}

			Double managerOwnpay = dao.queryManagerOwnpay(orderId);


			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orderId", orderId);
			map.put("settleCategory", ConstantUtils.PM_SETTLE_CATEGORY_4);
			map.put("settleStatus", 10);
			map.put("settleRole", ConstantUtils.SETTLE_ROLE_1);
			Double managerPenalty = dao.queryManangerPenalty(map);

			if (managerPenalty < 0) {
				managerPenalty = 0 - managerPenalty;
			}

			double pmMaterialsSettleAmount = 0.00;
			List<PmMaterialsSettleInfo> pmMaterials = pmMaterialsSettleInfoDao.queryPmMaterialsByOrderId(orderId);
			if (pmMaterials == null) {
				pmMaterials = new ArrayList<PmMaterialsSettleInfo>();
			}
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
			pmRewardAmount = bizAssessRewardPunishDao.queryTotalAmountByParam(rewardPunish);

			double pmPunishAmount = 0.0;
			rewardPunish.setIsRewardOrPunish("2");
			pmPunishAmount = bizAssessRewardPunishDao.queryTotalAmountByParam(rewardPunish);


			rewardPunish.setIsMonthInspection("1");

			double pmInspectionRewardAmount = 0.0;
			rewardPunish.setIsRewardOrPunish("1");
			pmInspectionRewardAmount = bizAssessRewardPunishDao.queryTotalAmountByParam(rewardPunish);

			double pmInspectionPunishAmount = 0.0;
			rewardPunish.setIsRewardOrPunish("2");
			pmInspectionPunishAmount = bizAssessRewardPunishDao.queryTotalAmountByParam(rewardPunish);

			double settleAmount = commissionAmount + managerOwnpay - managerPenalty - details3.getReceiveBillAmount()
					+ pmMaterialsSettleAmount + pmRewardAmount - pmPunishAmount + pmInspectionRewardAmount - pmInspectionPunishAmount;
			if (settleAmount < 0) {
				return result = "-1";
			}
		}

		Integer count = checkConfirmDao.findCheckNodeRel(qcBillId);
		if (count != 0) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("status", ConstantUtils.PAYMENT_STATUS_10);
			map.put("qcType", "1");
			map.put("ischeck", "0");
			map.put("orderTaskpackagePaymentType", ConstantUtils.PAYMENT_TYPE_1);
			map.put("qcBillId", qcBillId);
			map.put("cnrStatus", "1");
			List<BizOrderTaskpackagePaymentVo> list = bizOrderTaskpackagePaymentDao.queryPaymentForCheckByQcBillId(map);
			for (BizOrderTaskpackagePaymentVo bizOrderTaskpackagePaymentVo : list) {
				bizOrderTaskpackagePaymentDao.updateStatusByPaymentId(bizOrderTaskpackagePaymentVo.getId(),
						ConstantUtils.PAYMENT_STATUS_15);
			}
		}

		CheckConfirm settlementCount = checkConfirmDao.findSettlement(qcBillId);
		if (order.getProjectMode().equals("1")) {

			if (settlementCount.getManagerCount() != 0) {
				Map<String, Object> settleBillParam = new HashMap<String, Object>();
				settleBillParam.put("orderId", orderId);
				settleBillParam.put("settleRole", ConstantUtils.SETTLE_ROLE_1);
				settleBillParam.put("settleBillType", ConstantUtils.PM_SETTLE_BILL_TYPE_1);
				int settleBillCount = bizPmSettleBillDao.queryPmSettleBillByParam(settleBillParam);
				if (settleBillCount == 0) {



					BizPmStarCommissionCnfgSnap bizPmStarCommissionCnfgSnap = dao.findFirst(orderId);
					if (null != bizPmStarCommissionCnfgSnap) {

						BizPmStarCommissionLog bizPmStarCommissionLog = new BizPmStarCommissionLog();
						bizPmStarCommissionLog.setOrderId(orderId);
						bizPmStarCommissionLog.setPmEmployeeId(bizPmStarCommissionCnfgSnap.getPmEmployeeId());
						bizPmStarCommissionLog.setStarLevel(bizPmStarCommissionCnfgSnap.getStarLever());
						bizPmStarCommissionLog.setCommissionNode(ConstantUtils.COMMISSION_NODE_1);
						bizPmStarCommissionLog.setCommissionAmount(bizPmStarCommissionCnfgSnap.getCommissionAmount());
						bizPmStarCommissionLog.setCommissionRate(bizPmStarCommissionCnfgSnap.getCommissionRateMidway());
						bizPmStarCommissionLog.setCommissionDatetime(date);
						bizPmStarCommissionLog.setCreateBy(user);
						bizPmStarCommissionLog.setCreateDate(date);
						bizPmStarCommissionLog.setUpdateBy(user);
						bizPmStarCommissionLog.setUpdateDate(date);
						bizPmStarCommissionLog.setDelFlag("0");
						Integer id = bizPmStarCommissionLogDao.insert1(bizPmStarCommissionLog);

						PmSettleCategoryDetail details = new PmSettleCategoryDetail();
						details.setOrderId(orderId);
						details.setPmEmployeeId(bizPmStarCommissionCnfgSnap.getPmEmployeeId());
						details.setSettleCategory(ConstantUtils.PM_SETTLE_CATEGORY_3);
						Double price = bizPmStarCommissionCnfgSnap.getCommissionAmount()
								* bizPmStarCommissionCnfgSnap.getCommissionRateMidway();
						price = Double.parseDouble(df.format(price));
						details.setSettleAmount(price);
						details.setSettleStatus(ConstantUtils.PM_SETTLE_STATUS_20);
						details.setSettleStatusTime(date);
						details.setSettleRemark("");
						details.setRelatedBussinessId(bizPmStarCommissionLog.getId());
						details.setCreateBy(user);
						details.setCreateDate(date);
						details.setUpdateBy(user);
						details.setUpdateDate(date);
						details.setDelFlag("0");
						details.setSettleRole(ConstantUtils.SETTLE_ROLE_1);
						dao.saveDetail(details);
					}


					PmSettleCategoryDetail qcBreak = new PmSettleCategoryDetail();
					qcBreak.setOrderId(orderId);
					qcBreak.setSettleStatus(ConstantUtils.PM_SETTLE_STATUS_10);
					qcBreak.setSettleCategory(ConstantUtils.PM_SETTLE_CATEGORY_4);
					qcBreak.setSettleStatusTime(date);
					qcBreak.setLastSettleStatus(ConstantUtils.PM_SETTLE_STATUS_20);
					qcBreak.setUpdateBy(user);
					qcBreak.setUpdateDate(date);
					qcBreak.setSettleRole(ConstantUtils.SETTLE_ROLE_1);
					dao.updateDetailAll(qcBreak);


					PmSettleCategoryDetail PmMaterials = new PmSettleCategoryDetail();
					PmMaterials.setOrderId(orderId);
					PmMaterials.setSettleStatus(ConstantUtils.PM_SETTLE_STATUS_10);
					PmMaterials.setSettleCategory(ConstantUtils.PM_SETTLE_CATEGORY_12);
					PmMaterials.setSettleStatusTime(date);
					PmMaterials.setLastSettleStatus(ConstantUtils.PM_SETTLE_STATUS_20);
					PmMaterials.setUpdateBy(user);
					PmMaterials.setUpdateDate(date);
					PmMaterials.setSettleRole(ConstantUtils.SETTLE_ROLE_1);
					dao.updateDetailAll(PmMaterials);




					List<BizMaterialsStandardReceiveBill> list = dao.findThree(orderId);
					if (list != null && list.size() > 0) {
						Double receiveBillAmount = 0d;
						String relatedBussinessId = null;
						Integer pmEmployeeId = null;
						for (BizMaterialsStandardReceiveBill bill : list) {
							if (bill.getReceiveBillAmount() == null) {
								bill.setReceiveBillAmount(0d);
							}
							receiveBillAmount = receiveBillAmount + bill.getReceiveBillAmount();
							if (relatedBussinessId == null) {
								relatedBussinessId = String.valueOf(bill.getId());
							} else {
								relatedBussinessId = relatedBussinessId + "," + String.valueOf(bill.getId());
							}
							if (pmEmployeeId == null) {
								pmEmployeeId = bill.getReceiveEmployeeId();
							}
							bill.setIsSettled("1");
							bill.setUpdateBy(user);
							bill.setUpdateDate(date);
							dao.updateMaterials(bill);
						}
						PmSettleCategoryDetail details = new PmSettleCategoryDetail();
						details.setOrderId(orderId);
						details.setPmEmployeeId(pmEmployeeId);
						details.setSettleCategory(ConstantUtils.PM_SETTLE_CATEGORY_1);
						details.setSettleAmount(-receiveBillAmount);
						details.setSettleStatus(ConstantUtils.PM_SETTLE_STATUS_20);
						details.setSettleStatusTime(date);
						details.setSettleRemark("");
						details.setRelatedBusinessIdVarchar(relatedBussinessId);
						details.setCreateBy(user);
						details.setCreateDate(date);
						details.setUpdateBy(user);
						details.setUpdateDate(date);
						details.setDelFlag("0");
						details.setSettleRole(ConstantUtils.SETTLE_ROLE_1);
						dao.saveDetail(details);
					}




					List<BizPmOwnpayCnfgSnap> details4 = dao.findFour(orderId);
					List<PmSettleCategoryDetail> list4 = new ArrayList<PmSettleCategoryDetail>();
					if (null != details4 && details4.size() > 0) {
						for (BizPmOwnpayCnfgSnap a : details4) {

							BizPmOwnpayLog bizPmOwnpayLog = new BizPmOwnpayLog();
							bizPmOwnpayLog.setStoreId(a.getStoreId());
							bizPmOwnpayLog.setOrderId(a.getOrderId());
							bizPmOwnpayLog.setIsOldNew(a.getIsOldNew());
							bizPmOwnpayLog.setProjectMode(a.getProjectMode());
							bizPmOwnpayLog.setOwnpayName(a.getOwnpayName());
							bizPmOwnpayLog.setUnit(a.getUnit());
							bizPmOwnpayLog.setAmount(a.getAmount());
							bizPmOwnpayLog.setCreateBy(user);
							bizPmOwnpayLog.setCreateDate(date);
							bizPmOwnpayLog.setUpdateBy(user);
							bizPmOwnpayLog.setUpdateDate(date);
							bizPmOwnpayLog.setDelFlag("0");

							Integer id = bizPmOwnpayLogDao.insert1(bizPmOwnpayLog);

							PmSettleCategoryDetail details = new PmSettleCategoryDetail();
							details.setOrderId(orderId);
							details.setPmEmployeeId(a.getItemManagerId());
							details.setSettleCategory(ConstantUtils.PM_SETTLE_CATEGORY_2);
							details.setSettleAmount(a.getAmount());
							details.setSettleStatus(ConstantUtils.PM_SETTLE_STATUS_20);
							details.setSettleStatusTime(date);
							details.setSettleRemark("");
							details.setRelatedBussinessId(bizPmOwnpayLog.getId());
							details.setCreateBy(user);
							details.setCreateDate(date);
							details.setUpdateBy(user);
							details.setUpdateDate(date);
							details.setDelFlag("0");
							details.setSettleRole(ConstantUtils.SETTLE_ROLE_1);
							list4.add(details);
						}
						dao.saveDetailAll(list4);
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
							details.setSettleCategory(ConstantUtils.PM_SETTLE_CATEGORY_907);
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
							dao.saveDetailAll(list5);
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
							details.setSettleCategory(ConstantUtils.PM_SETTLE_CATEGORY_908);
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
							dao.saveDetailAll(list6);
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
							details.setSettleCategory(ConstantUtils.PM_SETTLE_CATEGORY_912);
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
							dao.saveDetailAll(list7);
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
							details.setSettleCategory(ConstantUtils.PM_SETTLE_CATEGORY_913);
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
							dao.saveDetailAll(list8);
						}

					}
					if (updateList.size() > 0) {
						bizAssessRewardPunishDao.updateRewardPunishStatus(updateList);
					}


					Map<String, Object> param = new HashMap<String, Object>();
					param.put("orderId", orderId);
					param.put("collectionType", 1);
					List<String> collectionStatus = new ArrayList<String>();
					collectionStatus.add("10");
					collectionStatus.add("20");
					collectionStatus.add("30");
					param.put("collectionStatus", collectionStatus);
					int collectionCount = bizOrderFinanceCollectionDao.checkIsExistByParam(param);
					if (collectionCount > 0) {
						erqiMoney(orderId, date, user);
					}

					BizBusinessStatusLog bizBusinessStatusLog = new BizBusinessStatusLog();
					bizBusinessStatusLog.setBusinessType(BusinessLogConstantUtil.BUSINESS_TYPE_302);
					bizBusinessStatusLog.setBusinessOnlyMarkInt(orderId);
					bizBusinessStatusLog.setBusinessStatus("30");
					bizBusinessStatusLog.setStatusDatetime(date);
					if (StringUtils.isNotBlank(user.getEmpId())) {
						bizBusinessStatusLog.setBusinessEmployeeId(Integer.valueOf(user.getEmpId()));
					}
					bizBusinessStatusLog.setStatusDatetime(new Date());
					bizBusinessStatusLog.preInsert();
					logDao.insert(bizBusinessStatusLog);

				}
			}

			if (settlementCount.getInspectorCount() != 0) {



				BizQcLongwayCommissionCnfgSnap bizQcLongwayCommissionCnfgSnap = dao.findFive(orderId);

				if (bizQcLongwayCommissionCnfgSnap != null) {

					BizQcLongwayCommissionLog bizQcLongwayCommissionLog = new BizQcLongwayCommissionLog();
					bizQcLongwayCommissionLog.setOrderId(bizQcLongwayCommissionCnfgSnap.getOrderId());
					bizQcLongwayCommissionLog
							.setLongwayCommissionEmployeeId(bizQcLongwayCommissionCnfgSnap.getPmEmployeeId());
					bizQcLongwayCommissionLog.setStarLevel(bizQcLongwayCommissionCnfgSnap.getStarLevel());
					bizQcLongwayCommissionLog.setCommissionNode(ConstantUtils.COMMISSION_NODE_1);
					bizQcLongwayCommissionLog.setCommissionAmount(bizQcLongwayCommissionCnfgSnap.getCommissionAmount());
					bizQcLongwayCommissionLog
							.setCommissionRate(bizQcLongwayCommissionCnfgSnap.getCommissionRateMidway());
					bizQcLongwayCommissionLog.setCommissionDatetime(date);
					bizQcLongwayCommissionLog.setCreateBy(user);
					bizQcLongwayCommissionLog.setCreateDate(date);
					bizQcLongwayCommissionLog.setUpdateBy(user);
					bizQcLongwayCommissionLog.setUpdateDate(date);
					bizQcLongwayCommissionLog.setDelFlag("0");
					bizQcLongwayCommissionLog.setLongwayCommissionType("20");
					Integer id = bizQcLongwayCommissionLogDao.insert1(bizQcLongwayCommissionLog);



					PmSettleCategoryDetail details = new PmSettleCategoryDetail();
					details.setOrderId(orderId);
					details.setPmEmployeeId(bizQcLongwayCommissionCnfgSnap.getPmEmployeeId());
					details.setSettleCategory(ConstantUtils.QC_SETTLE_CATEGORY_9);
					details.setSettleAmount(
							Double.parseDouble(df.format(bizQcLongwayCommissionCnfgSnap.getCommissionAmount()
									* bizQcLongwayCommissionCnfgSnap.getCommissionRateMidway())));
					details.setSettleStatus(ConstantUtils.PM_SETTLE_STATUS_20);
					details.setSettleStatusTime(date);
					details.setSettleRemark("");
					details.setRelatedBussinessId(bizQcLongwayCommissionLog.getId());
					details.setCreateBy(user);
					details.setCreateDate(date);
					details.setUpdateBy(user);
					details.setUpdateDate(date);
					details.setDelFlag("0");
					details.setSettleRole(ConstantUtils.SETTLE_ROLE_2);
					dao.saveDetail(details);
				}


				BizQcStarCommissionCnfgSnap bizQcStarCommissionCnfgSnap = dao.findSix(orderId);
				if (bizQcStarCommissionCnfgSnap != null) {

					BizQcStarCommissionLog bizQcStarCommissionLog = new BizQcStarCommissionLog();
					bizQcStarCommissionLog.setOrderId(bizQcStarCommissionCnfgSnap.getOrderId());
					bizQcStarCommissionLog.setQcEmployeeId(bizQcStarCommissionCnfgSnap.getPmEmployeeId());
					bizQcStarCommissionLog.setStarLevel(bizQcStarCommissionCnfgSnap.getStarLevel());
					bizQcStarCommissionLog.setCommissionNode(ConstantUtils.COMMISSION_NODE_1);
					bizQcStarCommissionLog.setCommissionAmount(bizQcStarCommissionCnfgSnap.getCommissionAmount());
					bizQcStarCommissionLog.setCommissionRate(bizQcStarCommissionCnfgSnap.getCommissionRateMidway());
					bizQcStarCommissionLog.setCommissionDatetime(date);
					bizQcStarCommissionLog.preInsert();
					bizQcStarCommissionLog.setCreateBy(user);
					bizQcStarCommissionLog.setCreateDate(date);
					bizQcStarCommissionLog.setUpdateBy(user);
					bizQcStarCommissionLog.setUpdateDate(date);
					bizQcStarCommissionLog.setDelFlag("0");
					Integer id = bizQcStarCommissionLogDao.insert1(bizQcStarCommissionLog);



					PmSettleCategoryDetail details = new PmSettleCategoryDetail();
					details.setOrderId(orderId);
					details.setPmEmployeeId(bizQcStarCommissionCnfgSnap.getPmEmployeeId());
					details.setSettleCategory(ConstantUtils.QC_SETTLE_CATEGORY_7);
					details.setSettleAmount(
							Double.parseDouble(df.format(bizQcStarCommissionCnfgSnap.getCommissionAmount()
									* bizQcStarCommissionCnfgSnap.getCommissionRateMidway())));
					details.setSettleStatus(ConstantUtils.PM_SETTLE_STATUS_20);
					details.setSettleStatusTime(date);
					details.setSettleRemark("");
					details.setRelatedBussinessId(bizQcStarCommissionLog.getId());
					details.setCreateBy(user);
					details.setCreateDate(date);
					details.setUpdateBy(user);
					details.setUpdateDate(date);
					details.setDelFlag("0");
					details.setSettleRole(ConstantUtils.SETTLE_ROLE_2);
					dao.saveDetail(details);
				}

			}
		}




		InspectorConfirm inspectorConfirm = dao.findById(qcBillId);
		if(inspectorConfirm!=null) {
			inspectorConfirm.setStatus("30");
			inspectorConfirm.setReviewStatus("1");
			inspectorConfirm.setReviewDatetime(date);

			dao.updateQcBill(inspectorConfirm);
		}
		if (inspectorConfirm != null && inspectorConfirm.getQcCheckNodeName().contains("涂饰工程及基装验收")) {


			Map<String, String> jsonMap = new HashMap<String, String>();
			jsonMap.put("time", DateUtils.formatDateTime(date));
			jsonMap.put("orderId", order.getOrderNumber());
			jsonMap.put("type", "2");
			String key = MD5Utils.MD5Secret(jsonMap);
			jsonMap.put("key", key);
			BizSynData bizSynData = new BizSynData();
			bizSynData.setDataDirection(ConstantUtils.DATA_DIRECTION_2);
			bizSynData.setBusinessType(ConstantUtils.BUSINESS_TYPE_302);
			bizSynData.setBusinessOnlyMarkInt(inspectorConfirm.getOrderId());
			bizSynData.setBusinessData(JSONObject.fromObject(jsonMap).toString());
			bizSynData.setSynStatus(ConstantUtils.SYN_STATUS_4);
			bizSynData.setIsAutoSyn(ConstantUtils.IS_AUTO_SYN_1);
			bizSynData.preInsert();
			bizSynDataDao.insert(bizSynData);
		}
		return result;
	}

	public List<BizOrderMaterialsStandard> queryMaterialsStandardByOrderId(Integer orderId) {
		return dao.queryMaterialsStandardByOrderId(orderId);
	}

	public List<BizPmOwnpayCnfgSnap> queryPmOwnpayCnfgSnapByOrderId(Integer orderId) {
		return dao.queryPmOwnpayCnfgSnapByOrderId(orderId);
	}

	public List<QcBillCheckItemInfo> queryPmPunishByParam(Map<String, Object> param) {
		return dao.queryPmPunishByParam(param);
	}

	public void erqiMoney(Integer orderId, Date date, User user) {

		SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
		Map<String, Object> settleBillParam = new HashMap<String, Object>();
		settleBillParam.put("orderId", orderId);
		settleBillParam.put("settleRole", ConstantUtils.SETTLE_ROLE_1);
		settleBillParam.put("settleBillType", ConstantUtils.PM_SETTLE_BILL_TYPE_1);
		int settleBillCount = bizPmSettleBillDao.queryPmSettleBillByParam(settleBillParam);
		if (settleBillCount == 0) {

			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("orderId", orderId);
			map1.put("settleStatus", ConstantUtils.PM_SETTLE_STATUS_20);
			map1.put("settleRole", ConstantUtils.SETTLE_ROLE_1);
			List<String> settleCategoryListMap = new ArrayList<String>();
			settleCategoryListMap.add(ConstantUtils.PM_SETTLE_CATEGORY_1);
			settleCategoryListMap.add(ConstantUtils.PM_SETTLE_CATEGORY_2);
			settleCategoryListMap.add(ConstantUtils.PM_SETTLE_CATEGORY_3);
			settleCategoryListMap.add(ConstantUtils.PM_SETTLE_CATEGORY_4);
			settleCategoryListMap.add(ConstantUtils.PM_SETTLE_CATEGORY_12);
			settleCategoryListMap.add(ConstantUtils.PM_SETTLE_CATEGORY_907);
			settleCategoryListMap.add(ConstantUtils.PM_SETTLE_CATEGORY_908);
			settleCategoryListMap.add(ConstantUtils.PM_SETTLE_CATEGORY_912);
			settleCategoryListMap.add(ConstantUtils.PM_SETTLE_CATEGORY_913);
			map1.put("settleCategoryList", settleCategoryListMap);
			List<BizPmSettleCategoryDetail> list = bizPmSettleCategoryDetailDao.queryCateGoryAmountByCondition(map1);

			if (CollectionUtils.isNotEmpty(list)) {
				List<BizPmSettleCategorySummary> summaryList = new ArrayList<BizPmSettleCategorySummary>();
				for (BizPmSettleCategoryDetail detail : list) {
					BizPmSettleCategorySummary summary = new BizPmSettleCategorySummary();
					summary.setOrderId(orderId);
					summary.setPmEmployeeId(detail.getPmEmployeeId());
					if (ConstantUtils.PM_SETTLE_CATEGORY_4.equals(detail.getSettleCategory())) {
						summary.setSettleCategory(ConstantUtils.PM_SETTLE_CATEGORY_401);
					} else if (ConstantUtils.PM_SETTLE_CATEGORY_12.equals(detail.getSettleCategory())) {
						summary.setSettleCategory(ConstantUtils.PM_SETTLE_CATEGORY_121);
					} else {
						summary.setSettleCategory(detail.getSettleCategory());
					}
					summary.setSettleAmount(detail.getSettleAmount());
					summary.setSettleStatus(ConstantUtils.PM_SETTLE_STATUS_30);
					summary.setSettleStatusDatetime(date);
					summary.setSettleRole(ConstantUtils.SETTLE_ROLE_1);
					summary.setCreateBy(user);
					summary.setCreateDate(date);
					summary.setUpdateBy(user);
					summary.setUpdateDate(date);
					summaryList.add(summary);
				}
				bizPmSettleCategorySummaryDao.insertBatch(summaryList);
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
			settleCategoryListUpdateMap.add(ConstantUtils.PM_SETTLE_CATEGORY_1);
			settleCategoryListUpdateMap.add(ConstantUtils.PM_SETTLE_CATEGORY_2);
			settleCategoryListUpdateMap.add(ConstantUtils.PM_SETTLE_CATEGORY_3);
			settleCategoryListUpdateMap.add(ConstantUtils.PM_SETTLE_CATEGORY_907);
			settleCategoryListUpdateMap.add(ConstantUtils.PM_SETTLE_CATEGORY_908);
			settleCategoryListUpdateMap.add(ConstantUtils.PM_SETTLE_CATEGORY_912);
			settleCategoryListUpdateMap.add(ConstantUtils.PM_SETTLE_CATEGORY_913);
			updateMap.put("settleCategoryList", settleCategoryListUpdateMap);
			bizPmSettleCategoryDetailDao.updateRelateSummary(updateMap);


			Map<String, Object> updateMap2 = new HashMap<String, Object>();
			updateMap2.put("orderId", orderId);
			updateMap2.put("sign", "01");
			updateMap2.put("settleCategory", ConstantUtils.PM_SETTLE_CATEGORY_4);
			updateMap2.put("settleStatus", ConstantUtils.PM_SETTLE_STATUS_20);
			updateMap2.put("newSettleStatus", ConstantUtils.PM_SETTLE_STATUS_30);
			updateMap2.put("settleRole", ConstantUtils.SETTLE_ROLE_1);
			updateMap2.put("settleStatusDatetime", date);
			updateMap2.put("updateDate", date);
			updateMap2.put("updateBy", user);
			bizPmSettleCategoryDetailDao.updateRelateSummaryCategory(updateMap2);


			Map<String, Object> updateMap3 = new HashMap<String, Object>();
			updateMap3.put("orderId", orderId);
			updateMap3.put("sign", "1");
			updateMap3.put("settleCategory", ConstantUtils.PM_SETTLE_CATEGORY_12);
			updateMap3.put("settleStatus", ConstantUtils.PM_SETTLE_STATUS_20);
			updateMap3.put("newSettleStatus", ConstantUtils.PM_SETTLE_STATUS_30);
			updateMap3.put("settleRole", ConstantUtils.SETTLE_ROLE_1);
			updateMap3.put("settleStatusDatetime", date);
			updateMap3.put("updateDate", date);
			updateMap3.put("updateBy", user);
			bizPmSettleCategoryDetailDao.updateRelateSummaryCategory(updateMap3);


			Map<String, Object> map2 = new HashMap<String, Object>();
			map2.put("orderId", orderId);
			map2.put("settleStatus", ConstantUtils.PM_SETTLE_STATUS_30);
			map2.put("settleRole", ConstantUtils.SETTLE_ROLE_1);
			List<BizPmSettleBill> billList = bizPmSettleCategorySummaryDao.queryCateGorySummaryMidByCondition(map2);
			if (CollectionUtils.isNotEmpty(billList)) {
				List<BizPmSettleBill> settleBillList = new ArrayList<BizPmSettleBill>();
				for (BizPmSettleBill bill : billList) {
					BizPmSettleBill settleBill = new BizPmSettleBill();
					settleBill.setBizPmSettleBillCode(bizSeiralnumService.getDateSequence("GJ"));
					settleBill.setSettleBillType(ConstantUtils.PM_SETTLE_BILL_TYPE_1);
					settleBill.setOrderId(orderId);
					settleBill.setPmEmployeeId(bill.getPmEmployeeId());
					settleBill.setProjectMode(bill.getProjectMode());
					settleBill.setSettleMonth(format.format(date));
					settleBill.setSettleDatetime(date);
					settleBill.setMidwayCommissionAmount(bill.getMidwayCommissionAmount());
					settleBill.setOwnpayAmount(bill.getOwnpayAmount());
					settleBill.setMaterialsStandardAmount(bill.getMaterialsStandardAmount());
					settleBill.setMidwayQcCheckPunishAmount(bill.getMidwayQcCheckPunishAmount());
					settleBill.setMidwayAuxiliaryMaterialsSettleAmount(bill.getMidwayAuxiliaryMaterialsSettleAmount());
					settleBill.setMidwayRewardAmount(bill.getMidwayRewardAmount());
					settleBill.setMidwayPunishAmount(bill.getMidwayPunishAmount());
					settleBill.setMidwayInspectionRewardAmount(bill.getMidwayInspectionRewardAmount());
					settleBill.setMidwayInspectionPunishAmount(bill.getMidwayInspectionPunishAmount());
					settleBill.setTotalAmount(bill.getTotalAmount());
					settleBill.setStatus(ConstantUtils.PM_SETTLE_STATUS_30);
					settleBill.setCreateBy(user);
					settleBill.setCreateDate(date);
					settleBill.setUpdateBy(user);
					settleBill.setUpdateDate(date);
					settleBill.setSettleRole(ConstantUtils.SETTLE_ROLE_1);
					settleBillList.add(settleBill);
				}
				bizPmSettleBillDao.insertBatch(settleBillList);
			}


			Map<String, Object> map3 = new HashMap<String, Object>();
			map3.put("orderId", orderId);
			map3.put("settleStatus", ConstantUtils.PM_SETTLE_STATUS_30);
			map3.put("settleBillType", ConstantUtils.PM_SETTLE_BILL_TYPE_1);
			map3.put("settleRole", ConstantUtils.SETTLE_ROLE_1);
			bizPmSettleCategorySummaryDao.updateRelate(map3);

			Map<String, Object> map4 = new HashMap<String, Object>();
			map4.put("orderId", orderId);
			map4.put("settleStatus", ConstantUtils.PM_SETTLE_STATUS_30);
			List<String> settleCategoryList = new ArrayList<String>();
			settleCategoryList.add(ConstantUtils.PM_SETTLE_CATEGORY_907);
			settleCategoryList.add(ConstantUtils.PM_SETTLE_CATEGORY_908);
			settleCategoryList.add(ConstantUtils.PM_SETTLE_CATEGORY_912);
			settleCategoryList.add(ConstantUtils.PM_SETTLE_CATEGORY_913);
			map4.put("settleCategorys", settleCategoryList);
			map4.put("settleStage", 1);
			map4.put("settleType", 2);
			bizAssessRewardPunishDao.updateByParam(map4);

			BizBusinessStatusLog bizBusinessStatusLog = new BizBusinessStatusLog();
			bizBusinessStatusLog.setBusinessType(BusinessLogConstantUtil.BUSINESS_TYPE_304);
			bizBusinessStatusLog.setBusinessOnlyMarkInt(orderId);
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