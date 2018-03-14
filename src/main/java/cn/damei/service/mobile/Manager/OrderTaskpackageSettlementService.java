package cn.damei.service.mobile.Manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.service.CrudService2;
import cn.damei.common.utils.Collections3;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.MessagePushType;
import cn.damei.common.utils.SendMsgBusinessType;
import cn.damei.dao.mobile.Inspector.InspectorEvaluateWorkerDao;
import cn.damei.entity.mobile.Inspector.BizEvalScore;
import cn.damei.entity.mobile.Inspector.BizEvalScoreRole;
import cn.damei.entity.mobile.Inspector.EvalRewardTaskpack;
import cn.damei.dao.mobile.Manager.BizEvalTaskpackRoleIndexScoreDao;
import cn.damei.entity.mobile.Manager.EvalScoreRoleIndex;
import cn.damei.dao.mobile.Manager.BizEvalTaskpackRoleScoreDao;
import cn.damei.entity.mobile.Manager.EvalScoreRole;
import cn.damei.dao.mobile.Manager.BizEvalTaskpackScoreDao;
import cn.damei.entity.mobile.Manager.EvalScore;
import cn.damei.dao.mobile.Manager.BizOrderTaskpackageProcedureDao;
import cn.damei.entity.mobile.Manager.BizOrderTaskpackageProcedure;
import cn.damei.dao.mobile.Manager.GuaranteeMoneyDao;
import cn.damei.entity.mobile.Manager.GuaranteeMoney;
import cn.damei.entity.mobile.Manager.AppOrder;
import cn.damei.dao.mobile.Manager.OrderTaskpackageAuxiliaryMaterialsDao;
import cn.damei.entity.mobile.Manager.OrderTaskpackageAuxiliaryMaterials;
import cn.damei.dao.mobile.Manager.BizOrderTaskpackageSettlementDetailDao;
import cn.damei.entity.mobile.Manager.BizOrderTaskpackageSettlementDetail;
import cn.damei.dao.mobile.Manager.TaskPackageDao;
import cn.damei.entity.mobile.Manager.TaskPackage;
import cn.damei.dao.mobile.Manager.OrderTaskpackageSettlementDao;
import cn.damei.entity.mobile.Manager.EmpTaskpackageSettlement;
import cn.damei.entity.mobile.Manager.OrderTaskpackageSettlement;
import cn.damei.entity.mobile.Manager.OrderTaskpackageVo;
import cn.damei.dao.modules.BizEvalRewardStarDao;
import cn.damei.dao.modules.BizProjectChangeBillDao;
import cn.damei.entity.modules.BizMsg;
import cn.damei.dao.modules.BizBizEmployeegroupVoDao;
import cn.damei.entity.modules.BizEmployeegroupVO;
import cn.damei.entity.modules.BizEvalActivityIndex;
import cn.damei.dao.modules.BizPhoneMsgDao;
import cn.damei.entity.modules.BizPhoneMsg;
import cn.damei.dao.modules.BizGuaranteeMoneyBalanceDao;
import cn.damei.entity.modules.BizGuaranteeMoneyBalance;
import cn.damei.service.modules.BizSeiralnumService;


@Service
@Transactional(readOnly = true)
public class OrderTaskpackageSettlementService
		extends CrudService2<OrderTaskpackageSettlementDao, OrderTaskpackageSettlement> {

	@Autowired
	private BizOrderTaskpackageProcedureDao orderTaskpackageProcedureDao;

	@Autowired
	private BizOrderTaskpackageSettlementDetailDao settlementDetailDao;

	@Autowired
	private OrderTaskpackageAuxiliaryMaterialsDao orderTaskpackageAuxiliaryMaterialsDao;

	@Autowired
	private GuaranteeMoneyDao guaranteeMoneyDao;

	@Autowired
	private BizSeiralnumService bizSeiralnumService;

	@Autowired
	private TaskPackageDao taskPackageDao;

	@Autowired
	private BizPhoneMsgDao bizPhoneMsgDao;

	@Autowired
	private BizProjectChangeBillDao bizProjectChangeBillDao;

	@Autowired
	private BizEvalTaskpackScoreDao bizEvalTaskpackScoreDao;
	
	@Autowired
	private BizEvalRewardStarDao bizEvalRewardStarDao;

	@Autowired
	private BizEvalTaskpackRoleScoreDao bizEvalTaskpackRoleScoreDao;

	@Autowired
	private BizEvalTaskpackRoleIndexScoreDao bizEvalTaskpackRoleIndexScoreDao;

	@Autowired
	private AppOrderService appOrderService;

	@Autowired
	private BizGuaranteeMoneyBalanceDao bizGuaranteeMoneyBalanceDao;
	
	@Autowired
	private InspectorEvaluateWorkerDao inspectorEvaluateWorkerDao;
	
	@Autowired
	private BizBizEmployeegroupVoDao bizBizEmployeegroupVoDao;


	public OrderTaskpackageVo queryTaskpackageSettlement(Integer id) {
		return dao.queryTaskpackageSettlement(id);
	}


	public List<EmpTaskpackageSettlement> queryTaskpackageEmpDetail(Integer groupId) {
		return dao.queryTaskpackageEmpDetail(groupId);
	}


	public OrderTaskpackageSettlement queryTaskpackageSettlementByOrderTaskpackageId(Integer orderTaskpackageId) {
		return dao.queryTaskpackageSettlementByOrderTaskpackageId(orderTaskpackageId);
	}

	@Transactional(readOnly = false)
	public void update(OrderTaskpackageSettlement orderTaskpackageSettlement) {
		dao.update(orderTaskpackageSettlement);
	}


	public List<EmpTaskpackageSettlement> queryUpdateTaskpackageEmpDetail(Integer groupId, Integer orderTaskpackageId,
			Integer settlementId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("groupId", groupId);
		map.put("orderTaskpackageId", orderTaskpackageId);
		map.put("settlementId", settlementId);
		return dao.queryUpdateTaskpackageEmpDetail(map);
	}


	public Double queryQcWorkerPublishAmountTotal(Integer orderTaskpackageId) {
		return dao.queryQcWorkerPublishAmountTotal(orderTaskpackageId);
	}


	@Transactional(readOnly = false)
	public String orderTaskpackageSettlementConfirm(OrderTaskpackageSettlement settlement, String isExist) {

		
		Date date = new Date();
		OrderTaskpackageSettlement sett = dao
				.queryTaskpackageSettlementByOrderTaskpackageId(settlement.getOrderTaskpackageId());
		if (sett == null) {

			TaskPackage task = taskPackageDao.get(String.valueOf(settlement.getOrderTaskpackageId()));
			task.setPackageStateId(ConstantUtils.ORDER_TASK_STATUS_100_VALUE);
			task.setPackageStatename(ConstantUtils.ORDER_TASK_STATUS_100_VALUE_REMARK);
			task.setUpdateDate(date);
			taskPackageDao.update(task);


			settlement.setSettlementNo(bizSeiralnumService.getDateSequence("JS"));
			settlement.setStatus(ConstantUtils.SETTLEMENT_STATUS_50);
			settlement.setStatusDate(date);
			settlement.setCreateDate(date);
			settlement.setUpdateDate(date);
			settlement.setIsNeedRecheck(ConstantUtils.SETTLEMENT_IS_NEED_RECHECK_0);
			settlement.setStoreId(task.getStoreId());
			dao.insert(settlement);


			if (!Collections3.isEmpty(settlement.getOrderTaskProcedure())) {
				for (BizOrderTaskpackageProcedure pro : settlement.getOrderTaskProcedure()) {
					BizOrderTaskpackageProcedure procedure = orderTaskpackageProcedureDao.get(pro.getId());
					procedure.setRemarks(pro.getRemarks());
					procedure.setRealNumber(pro.getRealNumber());
					procedure.setSettlementNumber(pro.getRealNumber());
					procedure.setLaborAuxiliaryMaterialsSettleAmount(pro.getLaborAuxiliaryMaterialsSettleAmount());
					procedure.setLaborSettleAmount(pro.getLaborSettleAmount());
					procedure.setAuxiliaryMaterialsSettleAmount(pro.getAuxiliaryMaterialsSettleAmount());
					procedure.setUpdateDate(date);
					orderTaskpackageProcedureDao.update(procedure);
				}
			}


			if (!Collections3.isEmpty(settlement.getAuxiliaryMaterials())) {
				for (OrderTaskpackageAuxiliaryMaterials auxiliary : settlement.getAuxiliaryMaterials()) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("orderTaskpackageId", settlement.getOrderTaskpackageId());
					map.put("auxiliaryMaterialsNo", auxiliary.getAuxiliaryMaterialsNo());
					int count = orderTaskpackageAuxiliaryMaterialsDao.queryCountByTaskIdAndAuxiliaryNo(map);
					if (count == 0) {
						auxiliary.setOrderId(settlement.getOrderId());
						auxiliary.setOrderTaskpackageId(settlement.getOrderTaskpackageId());
						orderTaskpackageAuxiliaryMaterialsDao.insert(auxiliary);
					}
				}
			}


			if (!Collections3.isEmpty(settlement.getSandMaterials())) {
				for (OrderTaskpackageAuxiliaryMaterials auxiliary : settlement.getSandMaterials()) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("orderTaskpackageId", settlement.getOrderTaskpackageId());
					map.put("auxiliaryMaterialsNo", auxiliary.getAuxiliaryMaterialsNo());
					int count = orderTaskpackageAuxiliaryMaterialsDao.queryCountByTaskIdAndAuxiliaryNo(map);
					if (count == 0) {
						auxiliary.setOrderId(settlement.getOrderId());
						auxiliary.setOrderTaskpackageId(settlement.getOrderTaskpackageId());
						orderTaskpackageAuxiliaryMaterialsDao.insert(auxiliary);
					}
				}
			}

			OrderTaskpackageSettlement taskSettlement = dao
					.queryTaskpackageSettlementByNo(settlement.getSettlementNo());


			GuaranteeMoney guaranteeMoney = guaranteeMoneyDao
					.queryGuarnteeMoneyByTaskId(settlement.getOrderTaskpackageId());
			if (guaranteeMoney == null) {
				GuaranteeMoney money = new GuaranteeMoney();
				money.setOrderTaskpackageId(settlement.getOrderTaskpackageId());
				money.setSettlementId(taskSettlement.getId());
				money.setTaskpackageTemplatId(settlement.getTaskPackageTemplatId());
				money.setEmployeeId(settlement.getGroupId());
				money.setEmployeegroupId(settlement.getEmpGroupid());
				money.setGuaranteeMoneyAmount(
						settlement.getGuaranteeMoneyAmount() == null ? 0d : settlement.getGuaranteeMoneyAmount());
				money.setGuaranteeMoneyAmountTotal(
						money.getGuaranteeMoneyAmount() + (settlement.getGuaranteeMoneyAmountTotal() == null ? 0d
								: settlement.getGuaranteeMoneyAmountTotal()));
				if (settlement.getGualityGuaranteeType() == 1) {
					money.setIsDeduct(ConstantUtils.IS_QUALITY_GUARANTEE_NO);
				} else {
					money.setIsDeduct(ConstantUtils.IS_QUALITY_GUARANTEE_YES);
				}
				money.setDeductTime(date);
				money.setCreateDate(date);
				money.setUpdateDate(date);

				BizGuaranteeMoneyBalance bizGuaranteeMoneyBalance = bizGuaranteeMoneyBalanceDao
						.findGuaranteeMoneyBalanceByEmployeeId(settlement.getGroupId());
				if (bizGuaranteeMoneyBalance == null || bizGuaranteeMoneyBalance.getId() == null) {
					bizGuaranteeMoneyBalance = new BizGuaranteeMoneyBalance();
					bizGuaranteeMoneyBalance.setEmployeeId(settlement.getGroupId());
					bizGuaranteeMoneyBalance.setGuaranteeMoneyAmountPaidSettle(money.getGuaranteeMoneyAmountTotal());
					bizGuaranteeMoneyBalance.setGuaranteeMoneyBalance(money.getGuaranteeMoneyAmountTotal());
				} else {
					bizGuaranteeMoneyBalance.setGuaranteeMoneyAmountPaidSettle(
							bizGuaranteeMoneyBalance.getGuaranteeMoneyAmountPaidSettle()
									+ money.getGuaranteeMoneyAmount());
					bizGuaranteeMoneyBalance.setGuaranteeMoneyBalance(
							bizGuaranteeMoneyBalance.getGuaranteeMoneyBalance() + money.getGuaranteeMoneyAmount());
				}
				money.setGuaranteeMoneyBalance(bizGuaranteeMoneyBalance.getGuaranteeMoneyBalance());
				guaranteeMoneyDao.insert(money);
				if (bizGuaranteeMoneyBalance.getId() == null) {
					bizGuaranteeMoneyBalance.preInsert();
					bizGuaranteeMoneyBalanceDao.insert(bizGuaranteeMoneyBalance);
				} else {
					bizGuaranteeMoneyBalance.preUpdate();
					bizGuaranteeMoneyBalanceDao.update(bizGuaranteeMoneyBalance);
				}

			}


			if (!Collections3.isEmpty(settlement.getSettlementDetail())) {
				for (BizOrderTaskpackageSettlementDetail detail : settlement.getSettlementDetail()) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("orderTaskpackageId", settlement.getOrderTaskpackageId());
					map.put("employeeId", detail.getEmployeeId());
					BizOrderTaskpackageSettlementDetail settlementDetail = settlementDetailDao
							.querySettlementDetailByTaskId(map);
					if (settlementDetail == null) {
						detail.setOrderTaskpackageId(settlement.getOrderTaskpackageId());
						detail.setSettlementId(taskSettlement.getId());
						detail.setStatus(ConstantUtils.SETTLEMENT_DETAIL_STATUS_2);
						detail.setPaymentTime(date);
						detail.setCreateDate(date);
						detail.setUpdateDate(date);
						settlementDetailDao.insert(detail);
					}
				}
			}


			if (CollectionUtils.isNotEmpty(settlement.getBizEvalActivityIndexList())) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("relatedBusinessId", settlement.getOrderTaskpackageId());
				map.put("evalType", "1");
				map.put("groupLeaderEmployeeId", task.getGroupId());
				map.put("evalStatus", ConstantUtils.EVAL_STATUS_1);

				Integer count = bizEvalTaskpackScoreDao.queryByCondition(map);
				if (count == 0) {

					EvalScore bizEvalTaskpackScore = new EvalScore();
					bizEvalTaskpackScore.setRelatedBusinessId(settlement.getOrderTaskpackageId());
					bizEvalTaskpackScore.setEvalType("1");
					bizEvalTaskpackScore.setGroupLeaderEmployeeId(task.getGroupId());
					bizEvalTaskpackScore.setEvalStatus(ConstantUtils.EVAL_STATUS_1);
					bizEvalTaskpackScore.setStatusDatetime(date);
					bizEvalTaskpackScore.setEvalStartDatetime(date);
					bizEvalTaskpackScore.setCreateDate(date);
					bizEvalTaskpackScore.setUpdateDate(date);
					bizEvalTaskpackScoreDao.insert(bizEvalTaskpackScore);

					Double totalScore = 0d;
					for (BizEvalActivityIndex bizEvalActivityIndex : settlement.getBizEvalActivityIndexList()) {
						totalScore = totalScore + (bizEvalActivityIndex.getEvalTotalScore() / 5)
								* bizEvalActivityIndex.getSelectCount();
					}


					List<BizEvalActivityIndex> activityIndexList= inspectorEvaluateWorkerDao.queryEvalActivityIndexByPackageId(settlement.getOrderTaskpackageId());
					String  managerType=null;
					String  pqcType=null;
					String  custemerType=null;
					if(activityIndexList != null && activityIndexList.size()>0){
						for(BizEvalActivityIndex activityIndex : activityIndexList){
							if(activityIndex.getEvalRoleType().equals("1")){
								managerType="1";
								EvalScoreRole bizEvalTaskpackRoleScore = new EvalScoreRole();
								bizEvalTaskpackRoleScore.setEvalScoreId(bizEvalTaskpackScore.getId());
								bizEvalTaskpackRoleScore.setEvalRoleType(ConstantUtils.EVAL_ROLE_TYPE_101);
								bizEvalTaskpackRoleScore.setEvalByEmployeeId(task.getItemManagerId());
								bizEvalTaskpackRoleScore.setGotScore(totalScore);
								bizEvalTaskpackRoleScore.setEvalFeedback(settlement.getEvalFeedback());
								bizEvalTaskpackRoleScore.setEvalDatetime(date);
								bizEvalTaskpackRoleScore.setCreateDate(date);
								bizEvalTaskpackRoleScore.setUpdateDate(date);
								bizEvalTaskpackRoleScore.setEvalStatus(ConstantUtils.EVAL_ROLE_STATUS_1);
								bizEvalTaskpackRoleScore.setEvalCycleHours(activityIndex.getEvalCycleHours());
								bizEvalTaskpackRoleScoreDao.insert(bizEvalTaskpackRoleScore);

								List<EvalScoreRoleIndex> bizEvalTaskpackRoleIndexScoreList = new ArrayList<EvalScoreRoleIndex>();
								for (BizEvalActivityIndex evalActivityIndex : settlement.getBizEvalActivityIndexList()) {
									EvalScoreRoleIndex bizEvalTaskpackRoleIndexScore = new EvalScoreRoleIndex();
									bizEvalTaskpackRoleIndexScore.setEvalScoreRoleId(bizEvalTaskpackRoleScore.getId());
									bizEvalTaskpackRoleIndexScore.setEvalActivityIndexId(evalActivityIndex.getId());
									bizEvalTaskpackRoleIndexScore.setGotScore(
											(evalActivityIndex.getEvalTotalScore() / 5) * evalActivityIndex.getSelectCount());
									bizEvalTaskpackRoleIndexScore.setCreateDate(date);
									bizEvalTaskpackRoleIndexScore.setUpdateDate(date);
									bizEvalTaskpackRoleIndexScoreList.add(bizEvalTaskpackRoleIndexScore);
								}
								bizEvalTaskpackRoleIndexScoreDao.insertBatch(bizEvalTaskpackRoleIndexScoreList);
								
							}else if(activityIndex.getEvalRoleType().equals("2")){
								pqcType="2";
								if(pqcType!=null&&pqcType.equals("2")){
									EvalScoreRole bizEvalTaskpackRoleScore = new EvalScoreRole();
									bizEvalTaskpackRoleScore.setEvalScoreId(bizEvalTaskpackScore.getId());
									bizEvalTaskpackRoleScore.setEvalRoleType(ConstantUtils.EVAL_ROLE_TYPE_201);
									bizEvalTaskpackRoleScore.setEvalByEmployeeId(task.getInspectorId());
									bizEvalTaskpackRoleScore.setGotScore(0d);
									bizEvalTaskpackRoleScore.setEvalFeedback(settlement.getEvalFeedback());
									bizEvalTaskpackRoleScore.setEvalDatetime(date);
									bizEvalTaskpackRoleScore.setCreateDate(date);
									bizEvalTaskpackRoleScore.setUpdateDate(date);
									bizEvalTaskpackRoleScore.setEvalStatus(ConstantUtils.EVAL_ROLE_STATUS_0);
									bizEvalTaskpackRoleScore.setEvalCycleHours(activityIndex.getEvalCycleHours());
									bizEvalTaskpackRoleScoreDao.insert(bizEvalTaskpackRoleScore);
								}
								
							}else if(activityIndex.getEvalRoleType().equals("3")){
								custemerType="3";
								EvalScoreRole bizEvalTaskpackRoleScore = new EvalScoreRole();
								if(custemerType!=null&&custemerType.equals("3")){
									bizEvalTaskpackRoleScore.setEvalScoreId(bizEvalTaskpackScore.getId());
									bizEvalTaskpackRoleScore.setEvalRoleType(ConstantUtils.EVAL_ROLE_TYPE_301);
									bizEvalTaskpackRoleScore.setEvalByEmployeeId(task.getItemManagerId());
									bizEvalTaskpackRoleScore.setGotScore(0d);
									bizEvalTaskpackRoleScore.setEvalFeedback(settlement.getEvalFeedback());
									bizEvalTaskpackRoleScore.setEvalDatetime(date);
									bizEvalTaskpackRoleScore.setCreateDate(date);
									bizEvalTaskpackRoleScore.setUpdateDate(date);
									bizEvalTaskpackRoleScore.setEvalStatus(ConstantUtils.EVAL_ROLE_STATUS_0);
									bizEvalTaskpackRoleScore.setEvalCycleHours(activityIndex.getEvalCycleHours());
									bizEvalTaskpackRoleScoreDao.insert(bizEvalTaskpackRoleScore);
								}
							}
						}
					}
					
					BizEvalScoreRole  scoreBean = new BizEvalScoreRole();
					scoreBean.setEvalScoreId(bizEvalTaskpackScore.getId());
					scoreBean.setManagerType(managerType);
					scoreBean.setPqcType(pqcType);
					scoreBean.setCustemerType(custemerType);
					scoreBean.setEvalStatus(ConstantUtils.EVAL_ROLE_STATUS_1);
					BizEvalScoreRole  evalTaskpackRoleScore = inspectorEvaluateWorkerDao.isEndEvaluate(scoreBean);
					if(null!=evalTaskpackRoleScore && null != evalTaskpackRoleScore.getGotScore() && evalTaskpackRoleScore.getGotScore()>0){


						BizEvalScore evalTaskpackScore = new BizEvalScore();
						evalTaskpackScore.setId(Integer.valueOf(bizEvalTaskpackScore.getId()));
						evalTaskpackScore.setGotScore(evalTaskpackRoleScore.getGotScore());
						evalTaskpackScore.setEvalStatus(ConstantUtils.EVAL_STATUS_2);
						evalTaskpackScore.setUpdateDate(date);
						evalTaskpackScore.setStatusDatetime(date);
						
						bizEvalTaskpackScore.setGroupLeaderEmployeeId(task.getGroupId());
						

					
						List<BizEmployeegroupVO>listqqq =bizBizEmployeegroupVoDao.getSumAvg(task.getGroupId());
              			
              			if(listqqq.size()==0){
              				bizBizEmployeegroupVoDao.insertStarLog(0,evalTaskpackRoleScore.getGotScore(),task.getGroupId());	
              			}else{
              				for(BizEmployeegroupVO bb:listqqq){
                  				if(bb.getCiShu()!=0 && bb.getCiShu()!=null && bb.getGotScore()!=null){
    	              				double beforeScore=bb.getGotScore()/bb.getCiShu();
    	              				double afterScore=(evalTaskpackRoleScore.getGotScore()+bb.getGotScore())/(bb.getCiShu()+1);
    	              				List<BizEmployeegroupVO>listSelect= bizBizEmployeegroupVoDao.selectStarLog(settlement.getOrderTaskpackageId());
    	              				if(listSelect.size()==0){
    	              					inspectorEvaluateWorkerDao.updateEvalTaskpackScore(evalTaskpackScore);
    	              					bizBizEmployeegroupVoDao.insertStarLog(beforeScore,afterScore,task.getGroupId());	
    	              					}
                  					}
                  			}
              			}
              			inspectorEvaluateWorkerDao.updateEvalTaskpackScore(evalTaskpackScore);
              			bizBizEmployeegroupVoDao.updateStar(task.getGroupId());
              			bizBizEmployeegroupVoDao.updateStarGroup(task.getGroupId());

		                Map<String, Object> rewardMap = new HashMap<String, Object>();
		                rewardMap.put("orderTaskpackId", settlement.getOrderTaskpackageId());
		                rewardMap.put("gotScore", evalTaskpackRoleScore.getGotScore());
		                Double rewardAmount = bizEvalRewardStarDao.queryEvalRewardStarByMap(rewardMap);

		                EvalRewardTaskpack evalRewardTaskpack = new EvalRewardTaskpack();
						evalRewardTaskpack.setOrderTaskpackageId(settlement.getOrderTaskpackageId());
						evalRewardTaskpack.setGroupLeaderEmployeeId(settlement.getGroupId());
						evalRewardTaskpack.setRewardAmount(rewardAmount);
						evalRewardTaskpack.setRewardDatetime(new Date());
						evalRewardTaskpack.preInsert();
						inspectorEvaluateWorkerDao.insertEvalRewardTaskpack(evalRewardTaskpack);
					}
				}
			} else {
				Integer evalCount = inspectorEvaluateWorkerDao.checkEvalActivityByOrderTaskpackage(settlement.getOrderTaskpackageId());
				if(evalCount == null || evalCount == 0){
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("relatedBusinessId", settlement.getOrderTaskpackageId());
					map.put("evalType", "1");
					map.put("groupLeaderEmployeeId", task.getGroupId());
					map.put("evalStatus", ConstantUtils.EVAL_STATUS_2);
					
					Integer count = bizEvalTaskpackScoreDao.queryByCondition(map);
					if (count == 0) {
						EvalScore bizEvalTaskpackScore = new EvalScore();
						bizEvalTaskpackScore.setRelatedBusinessId(settlement.getOrderTaskpackageId());
						bizEvalTaskpackScore.setEvalType("1");
						bizEvalTaskpackScore.setGroupLeaderEmployeeId(task.getGroupId());
						bizEvalTaskpackScore.setEvalStatus(ConstantUtils.EVAL_STATUS_2);
						bizEvalTaskpackScore.setGotScore(0d);
						bizEvalTaskpackScore.setStatusDatetime(date);
						bizEvalTaskpackScore.setEvalStartDatetime(date);
						bizEvalTaskpackScore.setCreateDate(date);
						bizEvalTaskpackScore.setUpdateDate(date);
						bizEvalTaskpackScoreDao.insert(bizEvalTaskpackScore);


						EvalScoreRole bizEvalTaskpackRoleScoreManager = new EvalScoreRole();
						bizEvalTaskpackRoleScoreManager.setEvalScoreId(bizEvalTaskpackScore.getId());
						bizEvalTaskpackRoleScoreManager.setEvalRoleType(ConstantUtils.EVAL_ROLE_TYPE_101);
						bizEvalTaskpackRoleScoreManager.setEvalByEmployeeId(task.getItemManagerId());
						bizEvalTaskpackRoleScoreManager.setGotScore(0d);
						bizEvalTaskpackRoleScoreManager.setEvalDatetime(date);
						bizEvalTaskpackRoleScoreManager.setCreateDate(date);
						bizEvalTaskpackRoleScoreManager.setUpdateDate(date);
						bizEvalTaskpackRoleScoreManager.setEvalStatus(ConstantUtils.EVAL_ROLE_STATUS_1);
						bizEvalTaskpackRoleScoreDao.insert(bizEvalTaskpackRoleScoreManager);

						AppOrder appOrder = appOrderService
								.queryOrderByOrderTaskpackageId(settlement.getOrderTaskpackageId());

						EvalScoreRole bizEvalTaskpackRoleScoreInspector = new EvalScoreRole();
						bizEvalTaskpackRoleScoreInspector.setEvalScoreId(bizEvalTaskpackScore.getId());
						bizEvalTaskpackRoleScoreInspector.setEvalRoleType(ConstantUtils.EVAL_ROLE_TYPE_201);
						bizEvalTaskpackRoleScoreInspector.setEvalByEmployeeId(appOrder.getOrderInspectorId());
						bizEvalTaskpackRoleScoreInspector.setGotScore(0d);
						bizEvalTaskpackRoleScoreInspector.setEvalDatetime(date);
						bizEvalTaskpackRoleScoreInspector.setCreateDate(date);
						bizEvalTaskpackRoleScoreInspector.setUpdateDate(date);
						bizEvalTaskpackRoleScoreInspector.setEvalStatus(ConstantUtils.EVAL_ROLE_STATUS_1);
						bizEvalTaskpackRoleScoreDao.insert(bizEvalTaskpackRoleScoreInspector);


						EvalScoreRole bizEvalTaskpackRoleScoreCustomer = new EvalScoreRole();
						bizEvalTaskpackRoleScoreCustomer.setEvalScoreId(bizEvalTaskpackScore.getId());
						bizEvalTaskpackRoleScoreCustomer.setEvalRoleType(ConstantUtils.EVAL_ROLE_TYPE_301);
						bizEvalTaskpackRoleScoreCustomer.setEvalByCusPhone(appOrder.getCustomerPhone());
						bizEvalTaskpackRoleScoreCustomer.setGotScore(0d);
						bizEvalTaskpackRoleScoreCustomer.setEvalDatetime(date);
						bizEvalTaskpackRoleScoreCustomer.setCreateDate(date);
						bizEvalTaskpackRoleScoreCustomer.setUpdateDate(date);
						bizEvalTaskpackRoleScoreCustomer.setEvalStatus(ConstantUtils.EVAL_ROLE_STATUS_1);
						bizEvalTaskpackRoleScoreDao.insert(bizEvalTaskpackRoleScoreCustomer);
					}
				}else{
					EvalScore bizEvalTaskpackScore = new EvalScore();
					bizEvalTaskpackScore.setRelatedBusinessId(settlement.getOrderTaskpackageId());
					bizEvalTaskpackScore.setEvalType("1");
					bizEvalTaskpackScore.setGroupLeaderEmployeeId(task.getGroupId());
					bizEvalTaskpackScore.setEvalStatus(ConstantUtils.EVAL_STATUS_1);
					bizEvalTaskpackScore.setStatusDatetime(date);
					bizEvalTaskpackScore.setEvalStartDatetime(date);
					bizEvalTaskpackScore.setCreateDate(date);
					bizEvalTaskpackScore.setUpdateDate(date);
					bizEvalTaskpackScoreDao.insert(bizEvalTaskpackScore);
				}
				
			}


			TaskPackage pack = taskPackageDao.querySmsMessageToGroup(settlement.getOrderTaskpackageId());
			String content = "订单（" + pack.getCustomerMessage() + "-" + pack.getCustomerName() + "-"
					+ pack.getCustomerPhone() + "）的任务包（" + pack.getPackageName() + "），项目经理（" + pack.getManagerName()
					+ "-" + pack.getManagerPhone() + "），已分配薪酬，请及时在【确认薪酬】中确认。";
			BizPhoneMsg msg = new BizPhoneMsg();
			msg.setReceiveEmployeeId(pack.getGroupId());
			msg.setReceivePhone(pack.getGroupPhone());
			msg.setMsgContent(content);
			msg.setMsgGenerateDatetime(date);
			msg.setMsgStatus(ConstantUtils.SEND_MSG_STATUS_0);
			msg.setRelatedBusinessType(SendMsgBusinessType.SEND_MSG_BUSINESS_TYPE_200803);
			msg.setRelatedBusinessIdInt(pack.getSettlementId());
			bizPhoneMsgDao.insert(msg);


			BizMsg bizMsg = new BizMsg();
			bizMsg.setMsgTitle("确认薪酬");
			bizMsg.setMsgTime(date);
			bizMsg.setMsgContent(content);
			bizMsg.setMsgType(MessagePushType.MSG_TYPE_1);
			bizMsg.setBusiType(MessagePushType.MESSAGE_PUSH_TYPE_105001002);
			bizMsg.setBusiIdInt(pack.getSettlementId());
			bizMsg.setEmployeeId(pack.getGroupId());
			bizProjectChangeBillDao.saveBizMsg(bizMsg);
			return "success";
		} else {
			return "repeat";
		}

	}


	@Transactional(readOnly = false)
	public String orderTaskpackageSettlementAgainConfirm(OrderTaskpackageSettlement settlement) {

		Date date = new Date();
		OrderTaskpackageSettlement taskpackageSettlement = dao.get(settlement.getId());
		if (ConstantUtils.SETTLEMENT_STATUS_50.equals(taskpackageSettlement.getStatus())) {
			return "repeat";
		}

		if (!ConstantUtils.SETTLEMENT_IS_NEED_RECHECK_1.equals(settlement.getIsNeedRecheck())) {
			if (!Collections3.isEmpty(settlement.getOrderTaskProcedure())) {
				for (BizOrderTaskpackageProcedure pro : settlement.getOrderTaskProcedure()) {
					BizOrderTaskpackageProcedure procedure = orderTaskpackageProcedureDao.get(pro.getId());
					procedure.setRemarks(pro.getRemarks());
					procedure.setRealNumber(pro.getRealNumber());
					procedure.setSettlementNumber(pro.getRealNumber());
					procedure.setLaborAuxiliaryMaterialsSettleAmount(pro.getLaborAuxiliaryMaterialsSettleAmount());
					procedure.setLaborSettleAmount(pro.getLaborSettleAmount());
					procedure.setAuxiliaryMaterialsSettleAmount(pro.getAuxiliaryMaterialsSettleAmount());
					procedure.setUpdateDate(date);
					orderTaskpackageProcedureDao.update(procedure);
				}
			}
		}



		taskpackageSettlement.setCheckDate(settlement.getCheckDate());
		taskpackageSettlement.setIsQualified(settlement.getIsQualified());
		taskpackageSettlement.setIsDelay(settlement.getIsDelay());
		taskpackageSettlement.setDelayDays(settlement.getDelayDays());
		taskpackageSettlement.setDelayAmerce(settlement.getDelayAmerce());
		taskpackageSettlement.setIsManagePunish(settlement.getIsManagePunish());
		taskpackageSettlement.setPunishAmerce(settlement.getPunishAmerce());
		taskpackageSettlement.setPunishReason(settlement.getPunishReason());
		taskpackageSettlement.setAuxiliaryMaterialsAmount(settlement.getAuxiliaryMaterialsAmount());
		taskpackageSettlement.setGuaranteeMoneyAmount(settlement.getGuaranteeMoneyAmount());
		taskpackageSettlement.setQcPunishMoneyAmount(settlement.getQcPunishMoneyAmount());
		taskpackageSettlement.setSettlementAmount(settlement.getSettlementAmount());
		
		taskpackageSettlement.setSettleStyle(settlement.getSettleStyle());
		taskpackageSettlement.setLaborAuxiliaryMaterialsSettleAmount(settlement.getLaborAuxiliaryMaterialsSettleAmount());
		taskpackageSettlement.setLaborSettleAmount(settlement.getLaborSettleAmount());
		taskpackageSettlement.setAuxiliaryMaterialsSettleAmount(settlement.getAuxiliaryMaterialsSettleAmount());
		taskpackageSettlement.setPmMaterialsSettleAmount(settlement.getPmMaterialsSettleAmount());
		taskpackageSettlement.setWorkerGroupSettleAmount(settlement.getWorkerGroupSettleAmount());
		
		taskpackageSettlement.setStatus(ConstantUtils.SETTLEMENT_STATUS_50);
		taskpackageSettlement.setStatusDate(date);
		taskpackageSettlement.setUpdateDate(date);
		taskpackageSettlement.setSandCementAmount(settlement.getSandCementAmount());
		dao.update(taskpackageSettlement);


		if (!Collections3.isEmpty(settlement.getAuxiliaryMaterials())) {
			for (OrderTaskpackageAuxiliaryMaterials auxiliary : settlement.getAuxiliaryMaterials()) {
				if (auxiliary.getId() != null) {
					orderTaskpackageAuxiliaryMaterialsDao.updateOrderTaskpackageAuxiliaryMaterials(auxiliary);
				} else {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("orderTaskpackageId", settlement.getOrderTaskpackageId());
					map.put("auxiliaryMaterialsNo", auxiliary.getAuxiliaryMaterialsNo());
					int count = orderTaskpackageAuxiliaryMaterialsDao.queryCountByTaskIdAndAuxiliaryNo(map);
					if (count == 0) {
						auxiliary.setOrderId(settlement.getOrderId());
						auxiliary.setOrderTaskpackageId(settlement.getOrderTaskpackageId());
						orderTaskpackageAuxiliaryMaterialsDao.insert(auxiliary);
					}
				}
			}
		}


		if (!Collections3.isEmpty(settlement.getSandMaterials())) {
			for (OrderTaskpackageAuxiliaryMaterials auxiliary : settlement.getSandMaterials()) {
				if (auxiliary.getId() != null) {
					orderTaskpackageAuxiliaryMaterialsDao.updateOrderTaskpackageAuxiliaryMaterials(auxiliary);
				} else {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("orderTaskpackageId", settlement.getOrderTaskpackageId());
					map.put("auxiliaryMaterialsNo", auxiliary.getAuxiliaryMaterialsNo());
					int count = orderTaskpackageAuxiliaryMaterialsDao.queryCountByTaskIdAndAuxiliaryNo(map);
					if (count == 0) {
						auxiliary.setOrderId(settlement.getOrderId());
						auxiliary.setOrderTaskpackageId(settlement.getOrderTaskpackageId());
						orderTaskpackageAuxiliaryMaterialsDao.insert(auxiliary);
					}
				}
			}
		}


		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderTaskpackageId", settlement.getOrderTaskpackageId());
		map.put("settlementId", settlement.getId());
		GuaranteeMoney money = guaranteeMoneyDao.queryGuarnteeMoney(map);
		
		if (money != null) {
			BizGuaranteeMoneyBalance bizGuaranteeMoneyBalance = bizGuaranteeMoneyBalanceDao
					.findGuaranteeMoneyBalanceByEmployeeId(settlement.getGroupId());
			if (bizGuaranteeMoneyBalance != null) {
				bizGuaranteeMoneyBalance.setGuaranteeMoneyAmountPaidSettle(
						bizGuaranteeMoneyBalance.getGuaranteeMoneyAmountPaidSettle()
								- money.getGuaranteeMoneyAmount());
				if (bizGuaranteeMoneyBalance.getGuaranteeMoneyBalance() - money.getGuaranteeMoneyAmount() < 0) {
					bizGuaranteeMoneyBalance.setGuaranteeMoneyBalance(0d);
				} else {
					bizGuaranteeMoneyBalance.setGuaranteeMoneyBalance(
							bizGuaranteeMoneyBalance.getGuaranteeMoneyBalance() - money.getGuaranteeMoneyAmount());
				}

			}
			money.setGuaranteeMoneyAmount(
					settlement.getGuaranteeMoneyAmount() == null ? 0d : settlement.getGuaranteeMoneyAmount());
			money.setGuaranteeMoneyAmountTotal(
					money.getGuaranteeMoneyAmount() + (settlement.getGuaranteeMoneyAmountTotal() == null ? 0d
							: settlement.getGuaranteeMoneyAmountTotal()));
			money.setDeductTime(date);
			money.setUpdateDate(date);
			if (bizGuaranteeMoneyBalance == null || bizGuaranteeMoneyBalance.getId() == null ) {
				bizGuaranteeMoneyBalance = new BizGuaranteeMoneyBalance();
				bizGuaranteeMoneyBalance.setEmployeeId(settlement.getGroupId());
				bizGuaranteeMoneyBalance.setGuaranteeMoneyBalance(money.getGuaranteeMoneyAmountTotal());
				bizGuaranteeMoneyBalance.setGuaranteeMoneyAmountPaidSettle(money.getGuaranteeMoneyAmountTotal());
			} else {
				bizGuaranteeMoneyBalance.setGuaranteeMoneyBalance(
						bizGuaranteeMoneyBalance.getGuaranteeMoneyBalance() + money.getGuaranteeMoneyAmount());
				bizGuaranteeMoneyBalance.setGuaranteeMoneyAmountPaidSettle(
						bizGuaranteeMoneyBalance.getGuaranteeMoneyAmountPaidSettle()
								+ money.getGuaranteeMoneyAmount());
			}
			money.setGuaranteeMoneyBalance(bizGuaranteeMoneyBalance.getGuaranteeMoneyBalance());
			guaranteeMoneyDao.updateGuaranteeMoney(money);
			
			if (bizGuaranteeMoneyBalance.getId() == null) {
				bizGuaranteeMoneyBalance.preInsert();
				bizGuaranteeMoneyBalanceDao.insert(bizGuaranteeMoneyBalance);
			}else{
				bizGuaranteeMoneyBalance.preUpdate();
				bizGuaranteeMoneyBalanceDao.update(bizGuaranteeMoneyBalance);
			}
			
		}


		if (!Collections3.isEmpty(settlement.getSettlementDetail())) {
			for (BizOrderTaskpackageSettlementDetail detail : settlement.getSettlementDetail()) {
				detail.setUpdateDate(date);
				detail.setStatus(ConstantUtils.SETTLEMENT_DETAIL_STATUS_2);
				detail.setPaymentTime(date);
				settlementDetailDao.updateSettlementDetail(detail);
			}
		}


		TaskPackage task = taskPackageDao.get(String.valueOf(settlement.getOrderTaskpackageId()));
		if (ConstantUtils.SETTLEMENT_IS_NEED_RECHECK_1.equals(settlement.getIsNeedRecheck())) {
			task.setPackageStateId(ConstantUtils.ORDER_TASK_STATUS_105_VALUE);
			task.setPackageStatename(ConstantUtils.ORDER_TASK_STATUS_105_VALUE_REMARK);
		} else {
			task.setPackageStateId(ConstantUtils.ORDER_TASK_STATUS_100_VALUE);
			task.setPackageStatename(ConstantUtils.ORDER_TASK_STATUS_100_VALUE_REMARK);
		}
		task.setUpdateDate(date);
		taskPackageDao.update(task);


		TaskPackage pack = taskPackageDao.querySmsMessageToGroup(settlement.getOrderTaskpackageId());
		String content = "订单（" + pack.getCustomerMessage() + "-" + pack.getCustomerName() + "-"
				+ pack.getCustomerPhone() + "）的任务包（" + pack.getPackageName() + "），项目经理（" + pack.getManagerName() + "-"
				+ pack.getManagerPhone() + "），已分配薪酬，请及时在【确认薪酬】中确认。";
		BizPhoneMsg msg = new BizPhoneMsg();
		msg.setReceiveEmployeeId(pack.getGroupId());
		msg.setReceivePhone(pack.getGroupPhone());
		msg.setMsgContent(content);
		msg.setMsgGenerateDatetime(date);
		msg.setMsgStatus(ConstantUtils.SEND_MSG_STATUS_0);
		msg.setRelatedBusinessType(SendMsgBusinessType.SEND_MSG_BUSINESS_TYPE_200803);
		msg.setRelatedBusinessIdInt(pack.getSettlementId());
		bizPhoneMsgDao.insert(msg);


		BizMsg bizMsg = new BizMsg();
		bizMsg.setMsgTitle("确认薪酬");
		bizMsg.setMsgTime(date);
		bizMsg.setMsgContent(content);
		bizMsg.setMsgType(MessagePushType.MSG_TYPE_1);
		bizMsg.setBusiType(MessagePushType.MESSAGE_PUSH_TYPE_105001002);
		bizMsg.setBusiIdInt(pack.getSettlementId());
		bizMsg.setEmployeeId(pack.getGroupId());
		bizProjectChangeBillDao.saveBizMsg(bizMsg);
		return "success";

	}


	@Transactional(readOnly = false)
	public String orderTaskpackageSettlementRecheck(OrderTaskpackageSettlement settlement, String isExist) {

		String result = "success";

		Date date = new Date();

		OrderTaskpackageSettlement taskpackageSettlement = dao.get(settlement.getId());
		if (ConstantUtils.SETTLEMENT_STATUS_50.equals(taskpackageSettlement.getStatus())) {
			return "repeat";
		}
		if (taskpackageSettlement == null || taskpackageSettlement.getSettlementAmount() == null
				|| !taskpackageSettlement.getStatus().equals(ConstantUtils.SETTLEMENT_STATUS_50)) {
			taskpackageSettlement.setCheckDate(settlement.getCheckDate());
			taskpackageSettlement.setIsQualified(settlement.getIsQualified());
			taskpackageSettlement.setIsDelay(settlement.getIsDelay());
			taskpackageSettlement.setDelayDays(settlement.getDelayDays());
			taskpackageSettlement.setDelayAmerce(settlement.getDelayAmerce());
			taskpackageSettlement.setIsManagePunish(settlement.getIsManagePunish());
			taskpackageSettlement.setPunishAmerce(settlement.getPunishAmerce());
			taskpackageSettlement.setPunishReason(settlement.getPunishReason());
			taskpackageSettlement.setAuxiliaryMaterialsAmount(settlement.getAuxiliaryMaterialsAmount());
			taskpackageSettlement.setGuaranteeMoneyAmount(settlement.getGuaranteeMoneyAmount());
			taskpackageSettlement.setQcPunishMoneyAmount(settlement.getQcPunishMoneyAmount());
			taskpackageSettlement.setSettlementAmount(settlement.getSettlementAmount());
			
			taskpackageSettlement.setSettleStyle(settlement.getSettleStyle());
			taskpackageSettlement.setLaborAuxiliaryMaterialsSettleAmount(settlement.getLaborAuxiliaryMaterialsSettleAmount());
			taskpackageSettlement.setLaborSettleAmount(settlement.getLaborSettleAmount());
			taskpackageSettlement.setAuxiliaryMaterialsSettleAmount(settlement.getAuxiliaryMaterialsSettleAmount());
			taskpackageSettlement.setPmMaterialsSettleAmount(settlement.getPmMaterialsSettleAmount());
			taskpackageSettlement.setWorkerGroupSettleAmount(settlement.getWorkerGroupSettleAmount());
			
			taskpackageSettlement.setStatus(ConstantUtils.SETTLEMENT_STATUS_50);
			taskpackageSettlement.setStatusDate(date);
			taskpackageSettlement.setUpdateDate(date);
			taskpackageSettlement.setSandCementAmount(settlement.getSandCementAmount());
			dao.update(taskpackageSettlement);


			if (!Collections3.isEmpty(settlement.getAuxiliaryMaterials())) {
				for (OrderTaskpackageAuxiliaryMaterials auxiliary : settlement.getAuxiliaryMaterials()) {
					if (auxiliary.getId() != null) {
						orderTaskpackageAuxiliaryMaterialsDao.updateOrderTaskpackageAuxiliaryMaterials(auxiliary);
					} else {
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("orderTaskpackageId", settlement.getOrderTaskpackageId());
						map.put("auxiliaryMaterialsNo", auxiliary.getAuxiliaryMaterialsNo());
						int count = orderTaskpackageAuxiliaryMaterialsDao.queryCountByTaskIdAndAuxiliaryNo(map);
						if (count == 0) {
							auxiliary.setOrderId(settlement.getOrderId());
							auxiliary.setOrderTaskpackageId(settlement.getOrderTaskpackageId());
							orderTaskpackageAuxiliaryMaterialsDao.insert(auxiliary);
						}
					}
				}
			}

			if (!Collections3.isEmpty(settlement.getSandMaterials())) {
				for (OrderTaskpackageAuxiliaryMaterials auxiliary : settlement.getSandMaterials()) {
					if (auxiliary.getId() != null) {
						orderTaskpackageAuxiliaryMaterialsDao.updateOrderTaskpackageAuxiliaryMaterials(auxiliary);
					} else {
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("orderTaskpackageId", settlement.getOrderTaskpackageId());
						map.put("auxiliaryMaterialsNo", auxiliary.getAuxiliaryMaterialsNo());
						int count = orderTaskpackageAuxiliaryMaterialsDao.queryCountByTaskIdAndAuxiliaryNo(map);
						if (count == 0) {
							auxiliary.setOrderId(settlement.getOrderId());
							auxiliary.setOrderTaskpackageId(settlement.getOrderTaskpackageId());
							orderTaskpackageAuxiliaryMaterialsDao.insert(auxiliary);
						}
					}
				}
			}


			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orderTaskpackageId", settlement.getOrderTaskpackageId());
			map.put("settlementId", settlement.getId());
			GuaranteeMoney money = guaranteeMoneyDao.queryGuarnteeMoney(map);
			BizGuaranteeMoneyBalance bizGuaranteeMoneyBalance = bizGuaranteeMoneyBalanceDao
					.findGuaranteeMoneyBalanceByEmployeeId(settlement.getGroupId());

			if (money != null) {
				if (bizGuaranteeMoneyBalance != null) {
					bizGuaranteeMoneyBalance.setGuaranteeMoneyAmountPaidSettle(
							bizGuaranteeMoneyBalance.getGuaranteeMoneyAmountPaidSettle()
									- money.getGuaranteeMoneyAmount());
					if (bizGuaranteeMoneyBalance.getGuaranteeMoneyBalance() - money.getGuaranteeMoneyAmount() < 0) {
						bizGuaranteeMoneyBalance.setGuaranteeMoneyBalance(0d);
					} else {
						bizGuaranteeMoneyBalance.setGuaranteeMoneyBalance(
								bizGuaranteeMoneyBalance.getGuaranteeMoneyBalance() - money.getGuaranteeMoneyAmount());
					}

				}
				money.setGuaranteeMoneyAmount(
						settlement.getGuaranteeMoneyAmount() == null ? 0d : settlement.getGuaranteeMoneyAmount());
				money.setGuaranteeMoneyAmountTotal(
						money.getGuaranteeMoneyAmount() + (settlement.getGuaranteeMoneyAmountTotal() == null ? 0d
								: settlement.getGuaranteeMoneyAmountTotal()));
				money.setDeductTime(date);
				money.setUpdateDate(date);
				if (bizGuaranteeMoneyBalance == null ||bizGuaranteeMoneyBalance.getId() == null) {
					bizGuaranteeMoneyBalance = new BizGuaranteeMoneyBalance();
					bizGuaranteeMoneyBalance.setEmployeeId(settlement.getGroupId());
					bizGuaranteeMoneyBalance.setGuaranteeMoneyBalance(money.getGuaranteeMoneyAmountTotal());
					bizGuaranteeMoneyBalance.setGuaranteeMoneyAmountPaidSettle(money.getGuaranteeMoneyAmountTotal());
				} else {
					bizGuaranteeMoneyBalance.setGuaranteeMoneyBalance(
							bizGuaranteeMoneyBalance.getGuaranteeMoneyBalance() + money.getGuaranteeMoneyAmount());
					bizGuaranteeMoneyBalance.setGuaranteeMoneyAmountPaidSettle(
							bizGuaranteeMoneyBalance.getGuaranteeMoneyAmountPaidSettle()
									+ money.getGuaranteeMoneyAmount());
				}
				money.setGuaranteeMoneyBalance(bizGuaranteeMoneyBalance.getGuaranteeMoneyBalance());
				guaranteeMoneyDao.updateGuaranteeMoney(money);
				if (bizGuaranteeMoneyBalance.getId() == null) {
					bizGuaranteeMoneyBalance.preInsert();
					bizGuaranteeMoneyBalanceDao.insert(bizGuaranteeMoneyBalance);
				}else{
					bizGuaranteeMoneyBalance.preUpdate();
					bizGuaranteeMoneyBalanceDao.update(bizGuaranteeMoneyBalance);
				}
			} else {
				money = new GuaranteeMoney();
				money.setOrderTaskpackageId(settlement.getOrderTaskpackageId());
				money.setSettlementId(settlement.getId());
				money.setTaskpackageTemplatId(settlement.getTaskPackageTemplatId());
				money.setEmployeeId(settlement.getGroupId());
				money.setEmployeegroupId(settlement.getEmpGroupid());
				money.setGuaranteeMoneyAmount(
						settlement.getGuaranteeMoneyAmount() == null ? 0d : settlement.getGuaranteeMoneyAmount());
				money.setGuaranteeMoneyAmountTotal(
						money.getGuaranteeMoneyAmount() + (settlement.getGuaranteeMoneyAmountTotal() == null ? 0d
								: settlement.getGuaranteeMoneyAmountTotal()));
				if (settlement.getGualityGuaranteeType() == 1) {
					money.setIsDeduct(ConstantUtils.IS_QUALITY_GUARANTEE_NO);
				} else {
					money.setIsDeduct(ConstantUtils.IS_QUALITY_GUARANTEE_YES);
				}
				money.setDeductTime(date);
				money.setCreateDate(date);
				money.setUpdateDate(date);
				
				if (bizGuaranteeMoneyBalance == null ||bizGuaranteeMoneyBalance.getId() == null) {
					bizGuaranteeMoneyBalance = new BizGuaranteeMoneyBalance();
					bizGuaranteeMoneyBalance.setEmployeeId(settlement.getGroupId());
					bizGuaranteeMoneyBalance.setGuaranteeMoneyBalance(money.getGuaranteeMoneyAmountTotal());
					bizGuaranteeMoneyBalance.setGuaranteeMoneyAmountPaidSettle(money.getGuaranteeMoneyAmountTotal());
				} else {
					bizGuaranteeMoneyBalance.setGuaranteeMoneyBalance(
							bizGuaranteeMoneyBalance.getGuaranteeMoneyBalance() + money.getGuaranteeMoneyAmount());
					bizGuaranteeMoneyBalance.setGuaranteeMoneyAmountPaidSettle(
							bizGuaranteeMoneyBalance.getGuaranteeMoneyAmountPaidSettle()
									+ money.getGuaranteeMoneyAmount());
				}
				money.setGuaranteeMoneyBalance(bizGuaranteeMoneyBalance.getGuaranteeMoneyBalance());
				guaranteeMoneyDao.insert(money);
				if (bizGuaranteeMoneyBalance.getId() == null) {
					bizGuaranteeMoneyBalance.preInsert();
					bizGuaranteeMoneyBalanceDao.insert(bizGuaranteeMoneyBalance);
				}else{
					bizGuaranteeMoneyBalance.preUpdate();
					bizGuaranteeMoneyBalanceDao.update(bizGuaranteeMoneyBalance);
				}
			}


			if (!Collections3.isEmpty(settlement.getSettlementDetail())) {
				for (BizOrderTaskpackageSettlementDetail detail : settlement.getSettlementDetail()) {
					BizOrderTaskpackageSettlementDetail settlementDetail = settlementDetailDao.get(detail.getId());
					if (settlementDetail != null) {
						settlementDetail.setPaymentAmount(detail.getPaymentAmount());
						settlementDetail.setStatus(ConstantUtils.SETTLEMENT_DETAIL_STATUS_2);
						settlementDetail.setPaymentTime(date);
						settlementDetail.setRemarks(detail.getRemarks());
						settlementDetail.setUpdateDate(date);
						settlementDetailDao.updateSettlementDetail(settlementDetail);
					} else {
						settlementDetail = new BizOrderTaskpackageSettlementDetail();
						settlementDetail.setOrderTaskpackageId(settlement.getOrderTaskpackageId());
						settlementDetail.setSettlementId(settlement.getId());
						settlementDetail.setEmployeeId(detail.getEmployeeId());
						settlementDetail.setIsLeader(detail.getIsLeader());
						settlementDetail.setPaymentAmount(detail.getPaymentAmount());
						settlementDetail.setStatus(ConstantUtils.SETTLEMENT_DETAIL_STATUS_2);
						settlementDetail.setPaymentTime(date);
						settlementDetail.setRemarks(detail.getRemarks());
						settlementDetail.setCreateDate(date);
						settlementDetail.setUpdateDate(date);
						settlementDetailDao.insert(settlementDetail);
					}
				}
			}


			TaskPackage task = taskPackageDao.get(String.valueOf(settlement.getOrderTaskpackageId()));
			task.setPackageStateId(ConstantUtils.ORDER_TASK_STATUS_105_VALUE);
			task.setPackageStatename(ConstantUtils.ORDER_TASK_STATUS_105_VALUE_REMARK);
			task.setUpdateDate(date);
			taskPackageDao.update(task);


						if (CollectionUtils.isNotEmpty(settlement.getBizEvalActivityIndexList())) {
							Map<String, Object> map1 = new HashMap<String, Object>();
							map1.put("relatedBusinessId", settlement.getOrderTaskpackageId());


							Integer count = bizEvalTaskpackScoreDao.queryByCondition(map1);
							if (count == 0) {

								EvalScore bizEvalTaskpackScore = new EvalScore();
								bizEvalTaskpackScore.setRelatedBusinessId(settlement.getOrderTaskpackageId());
								bizEvalTaskpackScore.setEvalType("1");
								bizEvalTaskpackScore.setGroupLeaderEmployeeId(task.getGroupId());
								bizEvalTaskpackScore.setEvalStatus(ConstantUtils.EVAL_STATUS_1);
								bizEvalTaskpackScore.setStatusDatetime(date);
								bizEvalTaskpackScore.setEvalStartDatetime(date);
								bizEvalTaskpackScore.setCreateDate(date);
								bizEvalTaskpackScore.setUpdateDate(date);
								bizEvalTaskpackScoreDao.insert(bizEvalTaskpackScore);

								Double totalScore = 0d;
								for (BizEvalActivityIndex bizEvalActivityIndex : settlement.getBizEvalActivityIndexList()) {
									totalScore = totalScore + (bizEvalActivityIndex.getEvalTotalScore() / 5)
											* bizEvalActivityIndex.getSelectCount();
								}
								
								

								List<BizEvalActivityIndex> activityIndexList= inspectorEvaluateWorkerDao.queryEvalActivityIndexByPackageId(settlement.getOrderTaskpackageId());
								String  managerType=null;
								String  pqcType=null;
								String  custemerType=null;
								if(activityIndexList != null && activityIndexList.size()>0){
									for(BizEvalActivityIndex activityIndex : activityIndexList){
										if(activityIndex.getEvalRoleType().equals("1")){
											managerType="1";
											EvalScoreRole bizEvalTaskpackRoleScore = new EvalScoreRole();
											bizEvalTaskpackRoleScore.setEvalScoreId(bizEvalTaskpackScore.getId());
											bizEvalTaskpackRoleScore.setEvalRoleType(ConstantUtils.EVAL_ROLE_TYPE_101);
											bizEvalTaskpackRoleScore.setEvalByEmployeeId(task.getItemManagerId());
											bizEvalTaskpackRoleScore.setGotScore(totalScore);
											bizEvalTaskpackRoleScore.setEvalFeedback(settlement.getEvalFeedback());
											bizEvalTaskpackRoleScore.setEvalDatetime(date);
											bizEvalTaskpackRoleScore.setCreateDate(date);
											bizEvalTaskpackRoleScore.setUpdateDate(date);
											bizEvalTaskpackRoleScore.setEvalStatus(ConstantUtils.EVAL_ROLE_STATUS_1);
											bizEvalTaskpackRoleScore.setEvalCycleHours(activityIndex.getEvalCycleHours());
											bizEvalTaskpackRoleScoreDao.insert(bizEvalTaskpackRoleScore);

											List<EvalScoreRoleIndex> bizEvalTaskpackRoleIndexScoreList = new ArrayList<EvalScoreRoleIndex>();
											for (BizEvalActivityIndex evalActivityIndex : settlement.getBizEvalActivityIndexList()) {
												EvalScoreRoleIndex bizEvalTaskpackRoleIndexScore = new EvalScoreRoleIndex();
												bizEvalTaskpackRoleIndexScore.setEvalScoreRoleId(bizEvalTaskpackRoleScore.getId());
												bizEvalTaskpackRoleIndexScore.setEvalActivityIndexId(evalActivityIndex.getId());
												bizEvalTaskpackRoleIndexScore.setGotScore(
														(evalActivityIndex.getEvalTotalScore() / 5) * evalActivityIndex.getSelectCount());
												bizEvalTaskpackRoleIndexScore.setCreateDate(date);
												bizEvalTaskpackRoleIndexScore.setUpdateDate(date);
												bizEvalTaskpackRoleIndexScoreList.add(bizEvalTaskpackRoleIndexScore);
											}
											bizEvalTaskpackRoleIndexScoreDao.insertBatch(bizEvalTaskpackRoleIndexScoreList);
											
										}else if(activityIndex.getEvalRoleType().equals("2")){
											pqcType="2";
											if(pqcType!=null&&pqcType.equals("2")){
												EvalScoreRole bizEvalTaskpackRoleScore = new EvalScoreRole();
												bizEvalTaskpackRoleScore.setEvalScoreId(bizEvalTaskpackScore.getId());
												bizEvalTaskpackRoleScore.setEvalRoleType(ConstantUtils.EVAL_ROLE_TYPE_201);
												bizEvalTaskpackRoleScore.setEvalByEmployeeId(task.getInspectorId());
												bizEvalTaskpackRoleScore.setGotScore(0d);
												bizEvalTaskpackRoleScore.setEvalFeedback(settlement.getEvalFeedback());
												bizEvalTaskpackRoleScore.setEvalDatetime(date);
												bizEvalTaskpackRoleScore.setCreateDate(date);
												bizEvalTaskpackRoleScore.setUpdateDate(date);
												bizEvalTaskpackRoleScore.setEvalStatus(ConstantUtils.EVAL_ROLE_STATUS_0);
												bizEvalTaskpackRoleScore.setEvalCycleHours(activityIndex.getEvalCycleHours());
												bizEvalTaskpackRoleScoreDao.insert(bizEvalTaskpackRoleScore);
											}
											
										}else if(activityIndex.getEvalRoleType().equals("3")){
											custemerType="3";
											EvalScoreRole bizEvalTaskpackRoleScore = new EvalScoreRole();
											if(custemerType!=null&&custemerType.equals("3")){
												bizEvalTaskpackRoleScore.setEvalScoreId(bizEvalTaskpackScore.getId());
												bizEvalTaskpackRoleScore.setEvalRoleType(ConstantUtils.EVAL_ROLE_TYPE_301);
												bizEvalTaskpackRoleScore.setEvalByEmployeeId(task.getItemManagerId());
												bizEvalTaskpackRoleScore.setGotScore(0d);
												bizEvalTaskpackRoleScore.setEvalFeedback(settlement.getEvalFeedback());
												bizEvalTaskpackRoleScore.setEvalDatetime(date);
												bizEvalTaskpackRoleScore.setCreateDate(date);
												bizEvalTaskpackRoleScore.setUpdateDate(date);
												bizEvalTaskpackRoleScore.setEvalStatus(ConstantUtils.EVAL_ROLE_STATUS_0);
												bizEvalTaskpackRoleScore.setEvalCycleHours(activityIndex.getEvalCycleHours());
												bizEvalTaskpackRoleScoreDao.insert(bizEvalTaskpackRoleScore);
											}
										}
									}
								}
			
								BizEvalScoreRole  scoreBean = new BizEvalScoreRole();
								scoreBean.setEvalScoreId(bizEvalTaskpackScore.getId());
								scoreBean.setManagerType(managerType);
								scoreBean.setPqcType(pqcType);
								scoreBean.setCustemerType(custemerType);
								scoreBean.setEvalStatus(ConstantUtils.EVAL_ROLE_STATUS_1);
								BizEvalScoreRole  evalTaskpackRoleScore = inspectorEvaluateWorkerDao.isEndEvaluate(scoreBean);
								if(null!=evalTaskpackRoleScore && !evalTaskpackRoleScore.getGotScore().equals("") && evalTaskpackRoleScore.getGotScore()>0){


									
									BizEvalScore evalTaskpackScore = new BizEvalScore();
									evalTaskpackScore.setId(Integer.valueOf(bizEvalTaskpackScore.getId()));
									evalTaskpackScore.setGotScore(evalTaskpackRoleScore.getGotScore());
									evalTaskpackScore.setEvalStatus(ConstantUtils.EVAL_STATUS_2);
									evalTaskpackScore.setUpdateDate(date);
									evalTaskpackScore.setStatusDatetime(date);
									

									bizEvalTaskpackScore.setGroupLeaderEmployeeId(task.getGroupId());
									
									
									List<BizEmployeegroupVO>listqqq =bizBizEmployeegroupVoDao.getSumAvg(task.getGroupId());
			              			
			              			if(listqqq.size()==0){
			              				bizBizEmployeegroupVoDao.insertStarLog(0,evalTaskpackRoleScore.getGotScore(),task.getGroupId());	
			              			}else{
			              				for(BizEmployeegroupVO bb:listqqq){
				              				if(bb.getCiShu()!=0 && bb.getCiShu()!=null && bb.getGotScore()!=null){
					              				double beforeScore=bb.getGotScore()/bb.getCiShu();
					              				double afterScore=(evalTaskpackRoleScore.getGotScore()+bb.getGotScore())/(bb.getCiShu()+1);
					              				List<BizEmployeegroupVO>listSelect= bizBizEmployeegroupVoDao.selectStarLog(settlement.getOrderTaskpackageId());
					              				if(listSelect.size()==0){
					              					inspectorEvaluateWorkerDao.updateEvalTaskpackScore(evalTaskpackScore);
					              					bizBizEmployeegroupVoDao.insertStarLog(beforeScore,afterScore,task.getGroupId());	
					              					}
				              					}
				              			}
			              			}
			              			inspectorEvaluateWorkerDao.updateEvalTaskpackScore(evalTaskpackScore);
			              			bizBizEmployeegroupVoDao.updateStar(task.getGroupId());
			              			bizBizEmployeegroupVoDao.updateStarGroup(task.getGroupId());
			              			

					                Map<String, Object> rewardMap = new HashMap<String, Object>();
					                rewardMap.put("orderTaskpackId", settlement.getOrderTaskpackageId());
					                rewardMap.put("gotScore", evalTaskpackRoleScore.getGotScore());
					                Double rewardAmount = bizEvalRewardStarDao.queryEvalRewardStarByMap(rewardMap);

					                EvalRewardTaskpack evalRewardTaskpack = new EvalRewardTaskpack();
									evalRewardTaskpack.setOrderTaskpackageId(settlement.getOrderTaskpackageId());
									evalRewardTaskpack.setGroupLeaderEmployeeId(settlement.getGroupId());
									evalRewardTaskpack.setRewardAmount(rewardAmount);
									evalRewardTaskpack.setRewardDatetime(new Date());
									evalRewardTaskpack.preInsert();
									inspectorEvaluateWorkerDao.insertEvalRewardTaskpack(evalRewardTaskpack);
								}
							}
						} else {
							Integer evalCount = inspectorEvaluateWorkerDao.checkEvalActivityByOrderTaskpackage(settlement.getOrderTaskpackageId());
							if(evalCount == null || evalCount == 0){
								Map<String, Object> map1 = new HashMap<String, Object>();
								map1.put("relatedBusinessId", settlement.getOrderTaskpackageId());
								map1.put("evalType", "1");
								map1.put("groupLeaderEmployeeId", task.getGroupId());
								map1.put("evalStatus", ConstantUtils.EVAL_STATUS_2);
								Integer count = bizEvalTaskpackScoreDao.queryByCondition(map1);
								if (count == 0) {
									EvalScore bizEvalTaskpackScore = new EvalScore();
									bizEvalTaskpackScore.setRelatedBusinessId(settlement.getOrderTaskpackageId());
									bizEvalTaskpackScore.setEvalType("1");
									bizEvalTaskpackScore.setGroupLeaderEmployeeId(task.getGroupId());
									bizEvalTaskpackScore.setEvalStatus(ConstantUtils.EVAL_STATUS_2);
									bizEvalTaskpackScore.setGotScore(0d);
									bizEvalTaskpackScore.setStatusDatetime(date);
									bizEvalTaskpackScore.setEvalStartDatetime(date);
									bizEvalTaskpackScore.setCreateDate(date);
									bizEvalTaskpackScore.setUpdateDate(date);
									bizEvalTaskpackScoreDao.insert(bizEvalTaskpackScore);


									EvalScoreRole bizEvalTaskpackRoleScoreManager = new EvalScoreRole();
									bizEvalTaskpackRoleScoreManager.setEvalScoreId(bizEvalTaskpackScore.getId());
									bizEvalTaskpackRoleScoreManager.setEvalRoleType(ConstantUtils.EVAL_ROLE_TYPE_101);
									bizEvalTaskpackRoleScoreManager.setEvalByEmployeeId(task.getItemManagerId());
									bizEvalTaskpackRoleScoreManager.setGotScore(0d);
									bizEvalTaskpackRoleScoreManager.setEvalDatetime(date);
									bizEvalTaskpackRoleScoreManager.setCreateDate(date);
									bizEvalTaskpackRoleScoreManager.setUpdateDate(date);
									bizEvalTaskpackRoleScoreManager.setEvalStatus(ConstantUtils.EVAL_ROLE_STATUS_1);
									bizEvalTaskpackRoleScoreDao.insert(bizEvalTaskpackRoleScoreManager);

									AppOrder appOrder = appOrderService
											.queryOrderByOrderTaskpackageId(settlement.getOrderTaskpackageId());

									EvalScoreRole bizEvalTaskpackRoleScoreInspector = new EvalScoreRole();
									bizEvalTaskpackRoleScoreInspector.setEvalScoreId(bizEvalTaskpackScore.getId());
									bizEvalTaskpackRoleScoreInspector.setEvalRoleType(ConstantUtils.EVAL_ROLE_TYPE_201);
									bizEvalTaskpackRoleScoreInspector.setEvalByEmployeeId(appOrder.getOrderInspectorId());
									bizEvalTaskpackRoleScoreInspector.setGotScore(0d);
									bizEvalTaskpackRoleScoreInspector.setEvalDatetime(date);
									bizEvalTaskpackRoleScoreInspector.setCreateDate(date);
									bizEvalTaskpackRoleScoreInspector.setUpdateDate(date);
									bizEvalTaskpackRoleScoreInspector.setEvalStatus(ConstantUtils.EVAL_ROLE_STATUS_1);
									bizEvalTaskpackRoleScoreDao.insert(bizEvalTaskpackRoleScoreInspector);


									EvalScoreRole bizEvalTaskpackRoleScoreCustomer = new EvalScoreRole();
									bizEvalTaskpackRoleScoreCustomer.setEvalScoreId(bizEvalTaskpackScore.getId());
									bizEvalTaskpackRoleScoreCustomer.setEvalRoleType(ConstantUtils.EVAL_ROLE_TYPE_301);
									bizEvalTaskpackRoleScoreCustomer.setEvalByCusPhone(appOrder.getCustomerPhone());
									bizEvalTaskpackRoleScoreCustomer.setGotScore(0d);
									bizEvalTaskpackRoleScoreCustomer.setEvalDatetime(date);
									bizEvalTaskpackRoleScoreCustomer.setCreateDate(date);
									bizEvalTaskpackRoleScoreCustomer.setUpdateDate(date);
									bizEvalTaskpackRoleScoreCustomer.setEvalStatus(ConstantUtils.EVAL_ROLE_STATUS_1);
									bizEvalTaskpackRoleScoreDao.insert(bizEvalTaskpackRoleScoreCustomer);
								}
						}else{
								Map<String, Object> map1 = new HashMap<String, Object>();
								map1.put("relatedBusinessId", settlement.getOrderTaskpackageId());
								map1.put("evalType", "1");
								map1.put("groupLeaderEmployeeId", task.getGroupId());
								Integer count = bizEvalTaskpackScoreDao.queryByCondition(map1);
								if(count == 0){
									EvalScore bizEvalTaskpackScore = new EvalScore();
									bizEvalTaskpackScore.setRelatedBusinessId(settlement.getOrderTaskpackageId());
									bizEvalTaskpackScore.setEvalType("1");
									bizEvalTaskpackScore.setGroupLeaderEmployeeId(task.getGroupId());
									bizEvalTaskpackScore.setEvalStatus(ConstantUtils.EVAL_STATUS_1);
									bizEvalTaskpackScore.setStatusDatetime(date);
									bizEvalTaskpackScore.setEvalStartDatetime(date);
									bizEvalTaskpackScore.setCreateDate(date);
									bizEvalTaskpackScore.setUpdateDate(date);
									bizEvalTaskpackScoreDao.insert(bizEvalTaskpackScore);
								}
							
								
						}
							
					}

			TaskPackage pack = taskPackageDao.querySmsMessageToGroup(settlement.getOrderTaskpackageId());
			String content = "订单（" + pack.getCustomerMessage() + "-" + pack.getCustomerName() + "-"
					+ pack.getCustomerPhone() + "）的任务包（" + pack.getPackageName() + "），项目经理（" + pack.getManagerName()
					+ "-" + pack.getManagerPhone() + "），已分配薪酬，请及时在【确认薪酬】中确认。";
			BizPhoneMsg msg = new BizPhoneMsg();
			msg.setReceiveEmployeeId(pack.getGroupId());
			msg.setReceivePhone(pack.getGroupPhone());
			msg.setMsgContent(content);
			msg.setMsgGenerateDatetime(date);
			msg.setMsgStatus(ConstantUtils.SEND_MSG_STATUS_0);
			msg.setRelatedBusinessType(SendMsgBusinessType.SEND_MSG_BUSINESS_TYPE_200803);
			msg.setRelatedBusinessIdInt(pack.getSettlementId());
			bizPhoneMsgDao.insert(msg);


			BizMsg bizMsg = new BizMsg();
			bizMsg.setMsgTitle("确认薪酬");
			bizMsg.setMsgTime(date);
			bizMsg.setMsgContent(content);
			bizMsg.setMsgType(MessagePushType.MSG_TYPE_1);
			bizMsg.setBusiType(MessagePushType.MESSAGE_PUSH_TYPE_105001002);
			bizMsg.setBusiIdInt(pack.getSettlementId());
			bizMsg.setEmployeeId(pack.getGroupId());
			bizProjectChangeBillDao.saveBizMsg(bizMsg);
		} else {
			result = "repeat";
		}

		return result;
	}


	@Transactional(readOnly = false)
	public String submitGongcheng(OrderTaskpackageSettlement settlement) {

		Date date = new Date();
		OrderTaskpackageSettlement sett = dao
				.queryTaskpackageSettlementByOrderTaskpackageId(settlement.getOrderTaskpackageId());
		if (sett == null) {

			TaskPackage task = taskPackageDao.get(settlement.getOrderTaskpackageId() + "");
			task.setPackageStateId(ConstantUtils.ORDER_TASK_STATUS_90_VALUE);
			task.setPackageStatename(ConstantUtils.ORDER_TASK_STATUS_90_VALUE_REMARK);
			task.setUpdateDate(date);
			taskPackageDao.update(task);


			OrderTaskpackageSettlement taskpackageSettlement = new OrderTaskpackageSettlement();
			taskpackageSettlement.setSettlementNo(bizSeiralnumService.getDateSequence("JS"));
			taskpackageSettlement.setOrderTaskpackageId(settlement.getOrderTaskpackageId());
			taskpackageSettlement.setCheckDate(settlement.getCheckDate());
			taskpackageSettlement.setIsQualified(settlement.getIsQualified());
			taskpackageSettlement.setIsDelay(settlement.getIsDelay());
			taskpackageSettlement.setDelayDays(settlement.getDelayDays());
			taskpackageSettlement.setDelayAmerce(settlement.getDelayAmerce());
			taskpackageSettlement.setIsManagePunish(settlement.getIsManagePunish());
			taskpackageSettlement.setPunishAmerce(settlement.getPunishAmerce());
			taskpackageSettlement.setPunishReason(settlement.getPunishReason());
			taskpackageSettlement.setStatus(ConstantUtils.SETTLEMENT_STATUS_10);
			taskpackageSettlement.setStatusDate(date);
			taskpackageSettlement.setCreateDate(date);
			taskpackageSettlement.setUpdateDate(date);
			taskpackageSettlement.setIsNeedRecheck(ConstantUtils.SETTLEMENT_IS_NEED_RECHECK_1);
			taskpackageSettlement.setStoreId(task.getStoreId());
			dao.insert(taskpackageSettlement);


			if (!Collections3.isEmpty(settlement.getOrderTaskProcedure())) {
				for (BizOrderTaskpackageProcedure pro : settlement.getOrderTaskProcedure()) {
					BizOrderTaskpackageProcedure procedure = orderTaskpackageProcedureDao.get(pro.getId());
					procedure.setRemarks(pro.getRemarks());
					procedure.setRealNumber(pro.getRealNumber());
					procedure.setSettlementNumber(pro.getRealNumber());
					procedure.setLaborAuxiliaryMaterialsSettleAmount(pro.getLaborAuxiliaryMaterialsSettleAmount());
					procedure.setLaborSettleAmount(pro.getLaborSettleAmount());
					procedure.setAuxiliaryMaterialsSettleAmount(pro.getAuxiliaryMaterialsSettleAmount());
					procedure.setUpdateDate(date);
					orderTaskpackageProcedureDao.update(procedure);
				}
			}


			TaskPackage taskPackage = taskPackageDao.querySmsMessage(settlement.getOrderTaskpackageId());
			String content = "订单（" + taskPackage.getCustomerMessage() + "-" + taskPackage.getCustomerName() + "-"
					+ taskPackage.getCustomerPhone() + "）的任务包（" + taskPackage.getPackageName() + "），项目经理（"
					+ taskPackage.getManagerName() + "-" + taskPackage.getManagerPhone() + "），项目经理验收任务包超定额，请及时进质检复核。";
			BizPhoneMsg msg = new BizPhoneMsg();
			msg.setReceiveEmployeeId(taskPackage.getInspectorId());
			msg.setReceivePhone(taskPackage.getInspectorPhone());
			msg.setMsgContent(content);
			msg.setMsgGenerateDatetime(date);
			msg.setMsgStatus(ConstantUtils.SEND_MSG_STATUS_0);
			msg.setRelatedBusinessType(SendMsgBusinessType.SEND_MSG_BUSINESS_TYPE_200801);
			msg.setRelatedBusinessIdInt(taskPackage.getSettlementId());
			bizPhoneMsgDao.insert(msg);
			return "success";
		} else {
			return "repeat";
		}

	}


	@Transactional(readOnly = false)
	public void submitGongchengUpdate(OrderTaskpackageSettlement settlement) {

		Date date = new Date();

		if (!Collections3.isEmpty(settlement.getOrderTaskProcedure())) {
			for (BizOrderTaskpackageProcedure pro : settlement.getOrderTaskProcedure()) {
				BizOrderTaskpackageProcedure procedure = orderTaskpackageProcedureDao.get(pro.getId());
				procedure.setRemarks(pro.getRemarks());
				procedure.setRealNumber(pro.getRealNumber());
				procedure.setSettlementNumber(pro.getRealNumber());
				procedure.setLaborAuxiliaryMaterialsSettleAmount(pro.getLaborAuxiliaryMaterialsSettleAmount());
				procedure.setLaborSettleAmount(pro.getLaborSettleAmount());
				procedure.setAuxiliaryMaterialsSettleAmount(pro.getAuxiliaryMaterialsSettleAmount());
				procedure.setUpdateDate(date);
				orderTaskpackageProcedureDao.update(procedure);
			}
		}


		OrderTaskpackageSettlement taskpackageSettlement = dao.get(settlement.getId());
		taskpackageSettlement.setCheckDate(settlement.getCheckDate());
		taskpackageSettlement.setIsQualified(settlement.getIsQualified());
		taskpackageSettlement.setIsDelay(settlement.getIsDelay());
		taskpackageSettlement.setDelayDays(settlement.getDelayDays());
		taskpackageSettlement.setDelayAmerce(settlement.getDelayAmerce());
		taskpackageSettlement.setIsManagePunish(settlement.getIsManagePunish());
		taskpackageSettlement.setPunishAmerce(settlement.getPunishAmerce());
		taskpackageSettlement.setPunishReason(settlement.getPunishReason());
		taskpackageSettlement.setStatus(ConstantUtils.SETTLEMENT_STATUS_10);
		taskpackageSettlement.setStatusDate(date);
		taskpackageSettlement.setUpdateDate(date);
		taskpackageSettlement.setIsNeedRecheck(ConstantUtils.SETTLEMENT_IS_NEED_RECHECK_1);
		dao.update(taskpackageSettlement);


		TaskPackage task = taskPackageDao.get(settlement.getOrderTaskpackageId() + "");
		task.setPackageStateId(ConstantUtils.ORDER_TASK_STATUS_90_VALUE);
		task.setPackageStatename(ConstantUtils.ORDER_TASK_STATUS_90_VALUE_REMARK);
		task.setUpdateDate(date);
		taskPackageDao.update(task);


		TaskPackage taskPackage = taskPackageDao.querySmsMessage(settlement.getOrderTaskpackageId());
		String content = "订单（" + taskPackage.getCustomerMessage() + "-" + taskPackage.getCustomerName() + "-"
				+ taskPackage.getCustomerPhone() + "）的任务包（" + taskPackage.getPackageName() + "），项目经理（"
				+ taskPackage.getManagerName() + "-" + taskPackage.getManagerPhone() + "），项目经理验收任务包超定额，请及时进质检复核。";
		BizPhoneMsg msg = new BizPhoneMsg();
		msg.setReceiveEmployeeId(taskPackage.getInspectorId());
		msg.setReceivePhone(taskPackage.getInspectorPhone());
		msg.setMsgContent(content);
		msg.setMsgGenerateDatetime(date);
		msg.setMsgStatus(ConstantUtils.SEND_MSG_STATUS_0);
		msg.setRelatedBusinessType(SendMsgBusinessType.SEND_MSG_BUSINESS_TYPE_200801);
		msg.setRelatedBusinessIdInt(taskPackage.getSettlementId());
		bizPhoneMsgDao.insert(msg);

	}
}