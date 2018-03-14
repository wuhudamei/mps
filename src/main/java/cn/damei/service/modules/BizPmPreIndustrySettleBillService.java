package cn.damei.service.modules;

import cn.damei.common.constantUtils.BusinessLogConstantUtil;
import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.entity.mobile.Worker.SettlementAuxiliary;
import cn.damei.dao.modules.BizBusinessRewardPunishDao;
import cn.damei.entity.modules.*;
import cn.damei.dao.modules.BizOrderChangeDao;
import cn.damei.dao.modules.BizOrderContractSettleDao;
import cn.damei.dao.modules.BizOrderMaterialCarryCostDao;
import cn.damei.dao.modules.BizPmPreIndustrySettleBillDao;
import cn.damei.entity.modules.BizPmPreIndustrySettleBill;
import cn.damei.dao.modules.BizPmPreIndustrySettleSummaryBillDao;
import cn.damei.entity.modules.BizPmPreIndustrySettleSummaryBill;
import cn.damei.dao.modules.BizBusinessStatusLogDao;
import cn.damei.dao.modules.BizQcLongwayCommissionLogDao;
import cn.damei.entity.modules.BizQcLongwayCommissionLog;
import cn.damei.entity.modules.BizOrderMaterialsStandard;
import cn.damei.dao.modules.BizOrderTaskpackagePaymentDao;
import cn.damei.entity.modules.BizOrderTaskpackagePaymentVo;
import cn.damei.dao.modules.BizPmGuaranteeMoneyLogDao;
import cn.damei.entity.modules.BizPmGuaranteeMoneyLog;
import cn.damei.dao.modules.BizPmSettleCategoryDetailDao;
import cn.damei.entity.modules.BizPmSettleCategoryDetail;
import cn.damei.dao.modules.BizPmSettleCategorySummaryDao;
import cn.damei.entity.modules.BizPmSettleCategorySummary;
import cn.damei.dao.modules.ProIndustryPmSettleDao;
import cn.damei.entity.modules.MainPanel;
import cn.damei.dao.modules.InspectorConfirmDao;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.BizDictUtils;
import cn.damei.common.utils.UserUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;


@Service
@Transactional(readOnly = true)
public class BizPmPreIndustrySettleBillService
		extends CrudService2<BizPmPreIndustrySettleBillDao, BizPmPreIndustrySettleBill> {

	@Autowired
	private BizOrderContractSettleDao bizOrderContractSettleDao;

	@Autowired
	private InspectorConfirmDao inspectorConfirmDao;

	@Autowired
	private BizOrderChangeDao bizOrderChangeDao;

	@Autowired
	private ProIndustryPmSettleDao proIndustryPmSettleDao;

	@Autowired
	private BizOrderTaskpackagePaymentDao bizOrderTaskpackagePaymentDao;

	@Autowired
	private BizBusinessRewardPunishDao bizBusinessRewardPunishDao;

	@Autowired
	private BizOrderMaterialCarryCostDao bizOrderMaterialCarryCostDao;

	@Autowired
	private BizPmSettleCategoryDetailDao bizPmSettleCategoryDetailDao;

	@Autowired
	private BizPmSettleCategorySummaryDao bizPmSettleCategorySummaryDao;

	@Autowired
	private BizSeiralnumService bizSeiralnumService;

	@Autowired
	private BizPmGuaranteeMoneyLogDao bizPmGuaranteeMoneyLogDao;

	@Autowired
	private BizQcLongwayCommissionLogDao bizQcLongwayCommissionLogDao;

	@Autowired
	private BizPmPreIndustrySettleSummaryBillDao bizPmPreIndustrySettleSummaryBillDao;
	
	@Autowired
	private BizBusinessStatusLogDao bizBusinessStatusLogDao;

	@Autowired
    private BizPmPreIndustrySettleBillDao bizPmPreIndustrySettleBillDao;




	public BizPmPreIndustrySettleBill midDataEncapsulation(BizPmPreIndustrySettleBill settleBill){
        if(settleBill.getIsNewSettleBill().equals("0")){
            Double contractSettleAmount = (settleBill.getContractAmount()+settleBill.getMidwayBasicworkAddAmount()
                    +settleBill.getMidwayMaterialsAuxiliaryAmount()+settleBill.getMidwaySandCementAmount()+settleBill.getMidwayMaterialsStandardAmount()
                    +settleBill.getMidwaySwitchPanelAmount()+settleBill.getMidwayWorkerSalaryAmount())*(settleBill.getMidwayContractSettleRate()/100);

            settleBill.setContractSettleAmount(contractSettleAmount);

            Double realSettleAmount = contractSettleAmount+settleBill.getRewardAmount()+settleBill.getMidwayMaterialCarryCostAmount()
                    +settleBill.getOrderChangeAddAmount()+settleBill.getMidwayQcCheckPunishAmount()
                    +settleBill.getOrderChangeReduceAmount()+settleBill.getPunishAmount();
            settleBill.setRealSettleAmount(realSettleAmount);
        }else {
            Double contractSettleAmount = (
                    settleBill.getContractAmount()
                            +settleBill.getMidwayBasicworkAddAmount()
                            +settleBill.getMidwayMaterialsAuxiliaryAmount()
                            +settleBill.getMidwaySandCementAmount()
                            +settleBill.getMidwayMaterialsStandardAmount()
                            +settleBill.getMidwaySwitchPanelAmount()
                            +settleBill.getMidwayWorkerSalaryAmount()
                            +settleBill.getRewardAmount()
                            +settleBill.getMidwayMaterialCarryCostAmount()
                            +settleBill.getOrderChangeAddAmount()
                            +settleBill.getMidwayQcCheckPunishAmount()
                            +settleBill.getOrderChangeReduceAmount()
                            +settleBill.getPunishAmount()
                            +settleBill.getCompleteGuaranteeMoneyAmount()
            )
                    *(settleBill.getMidwayContractSettleRate()/100);

            settleBill.setContractSettleAmount(contractSettleAmount);

            settleBill.setRealSettleAmount(contractSettleAmount);
        }
        return settleBill;
    }


    public BizPmPreIndustrySettleBill completedDataEncapsulation(BizPmPreIndustrySettleBill settleBill,Integer orderId){
        Map<String,Object> midwaySettleBillParam = new HashMap<String,Object>();
        midwaySettleBillParam.put("orderId", orderId);
        midwaySettleBillParam.put("settleBillType", 1);
        BizPmPreIndustrySettleBill midwaySettleBill = bizPmPreIndustrySettleBillDao.queryPmPreIndustrySettleBillByParam(midwaySettleBillParam);
        if(settleBill.getIsNewSettleBill().equals(0)){
            Double contractSettleAmount = settleBill.getContractAmount() - midwaySettleBill.getContractSettleAmount();
            settleBill.setContractSettleAmount(contractSettleAmount);
            Double realSettleAmount = contractSettleAmount+settleBill.getCompleteLongwayCommissionAmount()
                    +settleBill.getOrderChangeAddAmount()+settleBill.getRewardAmount()+settleBill.getMidwayQcCheckPunishAmount()
                    +settleBill.getOrderChangeReduceAmount()+settleBill.getPunishAmount()
                    +settleBill.getCompleteGuaranteeMoneyAmount();
            settleBill.setRealSettleAmount(realSettleAmount);
        }else {
            Double contractSettleAmount =  (midwaySettleBill.getContractSettleAmount()/midwaySettleBill.getMidwayContractSettleRate()*100 -midwaySettleBill.getContractAmount() + settleBill.getContractAmount());
            contractSettleAmount = (contractSettleAmount - 0.01) * (100 - midwaySettleBill.getMidwayContractSettleRate())/100;
            settleBill.setContractSettleAmount(contractSettleAmount);
            Double realSettleAmount =
                    contractSettleAmount
                            +settleBill.getCompleteLongwayCommissionAmount()
                            +settleBill.getOrderChangeAddAmount()
                            +settleBill.getRewardAmount()
                            +settleBill.getMidwayQcCheckPunishAmount()
                            +settleBill.getOrderChangeReduceAmount()
                            +settleBill.getPunishAmount();
            settleBill.setRealSettleAmount(realSettleAmount);
        }
        return settleBill;
    }





	@Transactional(readOnly = false)
	public String createMidwaySettleBille(BizPmPreIndustrySettleBill bizPmPreIndustrySettleBill) {
		Integer orderId = bizPmPreIndustrySettleBill.getOrderId();
		Integer pmEmployeeId = bizPmPreIndustrySettleBill.getPmEmployeeId();
		String result = "0";
		Date date = new Date();
		User user = UserUtils.getUser();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("orderId", orderId);
		param.put("settleBillType", 1);
		int count = dao.queryCountPmPreIndustrySettleBillByParam(param);
		if (count > 0) {
			result = "1";
			return result;
		}

		List<BizPmSettleCategoryDetail> detailList = new ArrayList<BizPmSettleCategoryDetail>();

		Map<String, Object> settParam = new HashMap<String, Object>();
		settParam.put("orderId", orderId);
		settParam.put("settleStage", 10);
		BizOrderContractSettle contractSettle = bizOrderContractSettleDao.findOrderContractSettleByParam(settParam);
		if (contractSettle != null) {
			BizPmSettleCategoryDetail detail = new BizPmSettleCategoryDetail();
			detail.setOrderId(orderId);
			detail.setPmEmployeeId(pmEmployeeId);
			detail.setSettleStatus(ConstantUtils.PM_SETTLE_STATUS_10);
			detail.setSettleCategory(ConstantUtils.PM_SETTLE_CATEGORY_900);
			detail.setSettleStatusDatetime(date);
			detail.setSettleAmount(contractSettle.getContractAmount());
			detail.setRelatedBusinessId(contractSettle.getId());
			detail.setCreateBy(user);
			detail.setCreateDate(date);
			detail.setUpdateBy(user);
			detail.setUpdateDate(date);
			detail.setDelFlag("0");
			detail.setSettleRole(ConstantUtils.SETTLE_ROLE_1);
			detailList.add(detail);
		}

		Map<String, Object> bizOrderChangeParam = new HashMap<String, Object>();
		bizOrderChangeParam.put("orderId", orderId);
		bizOrderChangeParam.put("changeType", ConstantUtils.change_type_10);
		BizOrderChange bizOrderChange = bizOrderChangeDao.queryOrderChangeByParam(bizOrderChangeParam);
		if (bizOrderChange != null) {
			BizPmSettleCategoryDetail detail = new BizPmSettleCategoryDetail();
			detail.setOrderId(orderId);
			detail.setPmEmployeeId(pmEmployeeId);
			detail.setSettleStatus(ConstantUtils.PM_SETTLE_STATUS_10);
			detail.setSettleCategory(ConstantUtils.PM_SETTLE_CATEGORY_901);
			detail.setSettleStatusDatetime(date);
			detail.setSettleAmount(bizOrderChange.getChangeAccountAmount());
			detail.setRelatedBusinessId(bizOrderChange.getId());
			detail.setCreateBy(user);
			detail.setCreateDate(date);
			detail.setUpdateBy(user);
			detail.setUpdateDate(date);
			detail.setDelFlag("0");
			detail.setSettleRole(ConstantUtils.SETTLE_ROLE_1);
			detailList.add(detail);
		}


		Map<String, Object> auMaterParam = new HashMap<String, Object>();
		auMaterParam.put("orderId", orderId);
		auMaterParam.put("isSandCement", 0);
		List<SettlementAuxiliary> auxiliaryList = proIndustryPmSettleDao.queryAuxiliaryInfoByParam(auMaterParam);
		if (auxiliaryList != null && auxiliaryList.size() > 0) {
			Double auMaterAmount = 0.00;
			String str = null;
			for (SettlementAuxiliary settlementAuxiliary : auxiliaryList) {
				auMaterAmount = auMaterAmount + settlementAuxiliary.getPrice();
				if (str == null) {
					str = settlementAuxiliary.getId().toString();
				} else {
					str = str + "," + settlementAuxiliary.getId().toString();
				}
			}
			if (auMaterAmount > 0) {
				auMaterAmount = 0 - auMaterAmount;
			}
			BizPmSettleCategoryDetail detail = new BizPmSettleCategoryDetail();
			detail.setOrderId(orderId);
			detail.setPmEmployeeId(pmEmployeeId);
			detail.setSettleStatus(ConstantUtils.PM_SETTLE_STATUS_10);
			detail.setSettleCategory(ConstantUtils.PM_SETTLE_CATEGORY_902);
			detail.setSettleStatusDatetime(date);
			detail.setSettleAmount(auMaterAmount);
			detail.setRelatedBusinessIdVarchar(str);
			detail.setCreateBy(user);
			detail.setCreateDate(date);
			detail.setUpdateBy(user);
			detail.setUpdateDate(date);
			detail.setDelFlag("0");
			detail.setSettleRole(ConstantUtils.SETTLE_ROLE_1);
			detailList.add(detail);
		}


		Map<String, Object> sandMaterParam = new HashMap<String, Object>();
		sandMaterParam.put("orderId", orderId);
		sandMaterParam.put("isSandCement", 1);
		List<SettlementAuxiliary> sandList = proIndustryPmSettleDao.queryAuxiliaryInfoByParam(sandMaterParam);
		if (sandList != null && sandList.size() > 0) {
			Double auMaterAmount = 0.00;
			String str = null;
			for (SettlementAuxiliary settlementAuxiliary : sandList) {
				auMaterAmount = auMaterAmount + settlementAuxiliary.getPrice();
				if (str == null) {
					str = settlementAuxiliary.getId().toString();
				} else {
					str = str + "," + settlementAuxiliary.getId().toString();
				}
			}
			if (auMaterAmount > 0) {
				auMaterAmount = 0 - auMaterAmount;
			}
			BizPmSettleCategoryDetail detail = new BizPmSettleCategoryDetail();
			detail.setOrderId(orderId);
			detail.setPmEmployeeId(pmEmployeeId);
			detail.setSettleStatus(ConstantUtils.PM_SETTLE_STATUS_10);
			detail.setSettleCategory(ConstantUtils.PM_SETTLE_CATEGORY_903);
			detail.setSettleStatusDatetime(date);
			detail.setSettleAmount(auMaterAmount);
			detail.setRelatedBusinessIdVarchar(str);
			detail.setCreateBy(user);
			detail.setCreateDate(date);
			detail.setUpdateBy(user);
			detail.setUpdateDate(date);
			detail.setDelFlag("0");
			detail.setSettleRole(ConstantUtils.SETTLE_ROLE_1);
			detailList.add(detail);
		}


		List<BizOrderMaterialsStandard> materialsStandardList = inspectorConfirmDao
				.queryMaterialsStandardByOrderId(orderId);
		if (materialsStandardList != null && materialsStandardList.size() > 0) {
			Double auMaterAmount = 0.00;
			String str = null;
			for (BizOrderMaterialsStandard standard : materialsStandardList) {
				auMaterAmount = auMaterAmount + standard.getMaterialsAmount();
				if (str == null) {
					str = standard.getId().toString();
				} else {
					str = str + "," + standard.getId().toString();
				}
			}
			if (auMaterAmount > 0) {
				auMaterAmount = 0 - auMaterAmount;
			}
			BizPmSettleCategoryDetail detail = new BizPmSettleCategoryDetail();
			detail.setOrderId(orderId);
			detail.setPmEmployeeId(pmEmployeeId);
			detail.setSettleStatus(ConstantUtils.PM_SETTLE_STATUS_10);
			detail.setSettleCategory(ConstantUtils.PM_SETTLE_CATEGORY_904);
			detail.setSettleStatusDatetime(date);
			detail.setSettleAmount(auMaterAmount);
			detail.setRelatedBusinessIdVarchar(str);
			detail.setCreateBy(user);
			detail.setCreateDate(date);
			detail.setUpdateBy(user);
			detail.setUpdateDate(date);
			detail.setDelFlag("0");
			detail.setSettleRole(ConstantUtils.SETTLE_ROLE_1);
			detailList.add(detail);
		}


		List<MainPanel> mainPanelList = proIndustryPmSettleDao.queryMainPanelListByOrderId(orderId);
		if (mainPanelList != null && mainPanelList.size() > 0) {
			Double auMaterAmount = 0.00;
			String str = null;
			for (MainPanel mainPanel : mainPanelList) {
				auMaterAmount = auMaterAmount + mainPanel.getTotalPrice();
				if (str == null) {
					str = mainPanel.getPurchaseId().toString();
				} else {
					str = str + "," + mainPanel.getPurchaseId().toString();
				}
			}
			if (auMaterAmount > 0) {
				auMaterAmount = 0 - auMaterAmount;
			}
			BizPmSettleCategoryDetail detail = new BizPmSettleCategoryDetail();
			detail.setOrderId(orderId);
			detail.setPmEmployeeId(pmEmployeeId);
			detail.setSettleStatus(ConstantUtils.PM_SETTLE_STATUS_10);
			detail.setSettleCategory(ConstantUtils.PM_SETTLE_CATEGORY_905);
			detail.setSettleStatusDatetime(date);
			detail.setSettleAmount(auMaterAmount);
			detail.setRelatedBusinessIdVarchar(str);
			detail.setCreateBy(user);
			detail.setCreateDate(date);
			detail.setUpdateBy(user);
			detail.setUpdateDate(date);
			detail.setDelFlag("0");
			detail.setSettleRole(ConstantUtils.SETTLE_ROLE_1);
			detailList.add(detail);
		}


		List<BizOrderTaskpackagePaymentVo> paymentList = bizOrderTaskpackagePaymentDao.findPaymentVoByOrderId(orderId);
		if (paymentList != null && paymentList.size() > 0) {
			Double auMaterAmount = 0.00;
			String str = null;
			for (BizOrderTaskpackagePaymentVo payment : paymentList) {
				auMaterAmount = auMaterAmount + payment.getAmount();
				if (str == null) {
					str = payment.getId().toString();
				} else {
					str = str + "," + payment.getId().toString();
				}
			}
			if (auMaterAmount > 0) {
				auMaterAmount = 0 - auMaterAmount;
			}
			BizPmSettleCategoryDetail detail = new BizPmSettleCategoryDetail();
			detail.setOrderId(orderId);
			detail.setPmEmployeeId(pmEmployeeId);
			detail.setSettleStatus(ConstantUtils.PM_SETTLE_STATUS_10);
			detail.setSettleCategory(ConstantUtils.PM_SETTLE_CATEGORY_906);
			detail.setSettleStatusDatetime(date);
			detail.setSettleAmount(auMaterAmount);
			detail.setRelatedBusinessIdVarchar(str);
			detail.setCreateBy(user);
			detail.setCreateDate(date);
			detail.setUpdateBy(user);
			detail.setUpdateDate(date);
			detail.setDelFlag("0");
			detail.setSettleRole(ConstantUtils.SETTLE_ROLE_1);
			detailList.add(detail);
		}


		Map<String, Object> rewardParam = new HashMap<String, Object>();
		rewardParam.put("orderId", orderId);
		rewardParam.put("employeeType", 2);
		rewardParam.put("relatedBusinessType", 1);
		rewardParam.put("rewardPunishType", 1);
		BizBusinessRewardPunish bizBusinessReward = bizBusinessRewardPunishDao
				.queryBusinessRewardPunishByParam(rewardParam);
		if (bizBusinessReward != null) {
			BizPmSettleCategoryDetail detail = new BizPmSettleCategoryDetail();
			detail.setOrderId(orderId);
			detail.setPmEmployeeId(pmEmployeeId);
			detail.setSettleStatus(ConstantUtils.PM_SETTLE_STATUS_10);
			detail.setSettleCategory(ConstantUtils.PM_SETTLE_CATEGORY_907);
			detail.setSettleStatusDatetime(date);
			detail.setSettleAmount(bizBusinessReward.getRewardPunishAmount());
			detail.setRelatedBusinessId(bizBusinessReward.getId());
			detail.setCreateBy(user);
			detail.setCreateDate(date);
			detail.setUpdateBy(user);
			detail.setUpdateDate(date);
			detail.setDelFlag("0");
			detail.setSettleRole(ConstantUtils.SETTLE_ROLE_1);
			detailList.add(detail);
		}


		Map<String, Object> punishParam = new HashMap<String, Object>();
		punishParam.put("orderId", orderId);
		punishParam.put("employeeType", 2);
		punishParam.put("relatedBusinessType", 1);
		punishParam.put("rewardPunishType", 2);
		BizBusinessRewardPunish bizBusinessPunish = bizBusinessRewardPunishDao
				.queryBusinessRewardPunishByParam(punishParam);
		if (bizBusinessPunish != null) {
			BizPmSettleCategoryDetail detail = new BizPmSettleCategoryDetail();
			detail.setOrderId(orderId);
			detail.setPmEmployeeId(pmEmployeeId);
			detail.setSettleStatus(ConstantUtils.PM_SETTLE_STATUS_10);
			detail.setSettleCategory(ConstantUtils.PM_SETTLE_CATEGORY_908);
			detail.setSettleStatusDatetime(date);
			detail.setSettleAmount((0 - bizBusinessPunish.getRewardPunishAmount()));
			detail.setRelatedBusinessId(bizBusinessPunish.getId());
			detail.setCreateBy(user);
			detail.setCreateDate(date);
			detail.setUpdateBy(user);
			detail.setUpdateDate(date);
			detail.setDelFlag("0");
			detail.setSettleRole(ConstantUtils.SETTLE_ROLE_1);
			detailList.add(detail);
		}


		BizOrderMaterialCarryCost bizOrderMaterialCarryCost = bizOrderMaterialCarryCostDao
				.queryOrderMaterialCarryCostByOrderId(orderId);
		if (bizOrderMaterialCarryCost != null) {
			BizPmSettleCategoryDetail detail = new BizPmSettleCategoryDetail();
			detail.setOrderId(orderId);
			detail.setPmEmployeeId(pmEmployeeId);
			detail.setSettleStatus(ConstantUtils.PM_SETTLE_STATUS_10);
			detail.setSettleCategory(ConstantUtils.PM_SETTLE_CATEGORY_909);
			detail.setSettleStatusDatetime(date);
			detail.setSettleAmount(bizOrderMaterialCarryCost.getAccountAmount());
			detail.setRelatedBusinessId(bizOrderMaterialCarryCost.getId());
			detail.setCreateBy(user);
			detail.setCreateDate(date);
			detail.setUpdateBy(user);
			detail.setUpdateDate(date);
			detail.setDelFlag("0");
			detail.setSettleRole(ConstantUtils.SETTLE_ROLE_1);
			detailList.add(detail);
		}


		Map<String, Object> changeAddParam = new HashMap<String, Object>();
		changeAddParam.put("orderId", orderId);
		changeAddParam.put("changeType", ConstantUtils.change_type_20);
		BizOrderChange addChange = bizOrderChangeDao.queryOrderChangeByParam(changeAddParam);
		if (addChange != null) {
			BizPmSettleCategoryDetail detail = new BizPmSettleCategoryDetail();
			detail.setOrderId(orderId);
			detail.setPmEmployeeId(pmEmployeeId);
			detail.setSettleStatus(ConstantUtils.PM_SETTLE_STATUS_10);
			detail.setSettleCategory(ConstantUtils.PM_SETTLE_CATEGORY_910);
			detail.setSettleStatusDatetime(date);
			detail.setSettleAmount(addChange.getChangeAccountAmount());
			detail.setRelatedBusinessId(addChange.getId());
			detail.setCreateBy(user);
			detail.setCreateDate(date);
			detail.setUpdateBy(user);
			detail.setUpdateDate(date);
			detail.setDelFlag("0");
			detail.setSettleRole(ConstantUtils.SETTLE_ROLE_1);
			detailList.add(detail);
		}


		Map<String, Object> changeRecuteParam = new HashMap<String, Object>();
		changeRecuteParam.put("orderId", orderId);
		changeRecuteParam.put("changeType", ConstantUtils.change_type_30);
		BizOrderChange recuteChange = bizOrderChangeDao.queryOrderChangeByParam(changeRecuteParam);
		if (recuteChange != null) {
			BizPmSettleCategoryDetail detail = new BizPmSettleCategoryDetail();
			detail.setOrderId(orderId);
			detail.setPmEmployeeId(pmEmployeeId);
			detail.setSettleStatus(ConstantUtils.PM_SETTLE_STATUS_10);
			detail.setSettleCategory(ConstantUtils.PM_SETTLE_CATEGORY_911);
			detail.setSettleStatusDatetime(date);
			detail.setSettleAmount((0 - recuteChange.getChangeAccountAmount()));
			detail.setRelatedBusinessId(recuteChange.getId());
			detail.setCreateBy(user);
			detail.setCreateDate(date);
			detail.setUpdateBy(user);
			detail.setUpdateDate(date);
			detail.setDelFlag("0");
			detail.setSettleRole(ConstantUtils.SETTLE_ROLE_1);
			detailList.add(detail);
		}

		bizPmSettleCategoryDetailDao.insertBatch(detailList);


		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("orderId", orderId);
		map1.put("settleStatus", ConstantUtils.PM_SETTLE_STATUS_10);
		map1.put("settleRole", ConstantUtils.SETTLE_ROLE_1);
		List<String> settleCategoryListMap = new ArrayList<String>();
		settleCategoryListMap.add(ConstantUtils.PM_SETTLE_CATEGORY_900);
		settleCategoryListMap.add(ConstantUtils.PM_SETTLE_CATEGORY_901);
		settleCategoryListMap.add(ConstantUtils.PM_SETTLE_CATEGORY_902);
		settleCategoryListMap.add(ConstantUtils.PM_SETTLE_CATEGORY_903);
		settleCategoryListMap.add(ConstantUtils.PM_SETTLE_CATEGORY_904);
		settleCategoryListMap.add(ConstantUtils.PM_SETTLE_CATEGORY_905);
		settleCategoryListMap.add(ConstantUtils.PM_SETTLE_CATEGORY_906);
		settleCategoryListMap.add(ConstantUtils.PM_SETTLE_CATEGORY_907);
		settleCategoryListMap.add(ConstantUtils.PM_SETTLE_CATEGORY_908);
		settleCategoryListMap.add(ConstantUtils.PM_SETTLE_CATEGORY_909);
		settleCategoryListMap.add(ConstantUtils.PM_SETTLE_CATEGORY_910);
		settleCategoryListMap.add(ConstantUtils.PM_SETTLE_CATEGORY_911);
		settleCategoryListMap.add(ConstantUtils.PM_SETTLE_CATEGORY_4);
		map1.put("settleCategoryList", settleCategoryListMap);
		List<BizPmSettleCategoryDetail> list = bizPmSettleCategoryDetailDao.queryCateGoryAmountByCondition(map1);
		if (CollectionUtils.isNotEmpty(list)) {
			List<BizPmSettleCategorySummary> summaryList = new ArrayList<BizPmSettleCategorySummary>();
			for (BizPmSettleCategoryDetail detail : list) {
				BizPmSettleCategorySummary summary = new BizPmSettleCategorySummary();
				summary.setOrderId(orderId);
				summary.setPmEmployeeId(detail.getPmEmployeeId());
				summary.setSettleCategory(detail.getSettleCategory());
				summary.setSettleAmount(detail.getSettleAmount());
				summary.setSettleStatus(ConstantUtils.PM_SETTLE_STATUS_10);
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
		updateMap.put("settleStatus", ConstantUtils.PM_SETTLE_STATUS_10);
		updateMap.put("newSettleStatus", ConstantUtils.PM_SETTLE_STATUS_10);
		updateMap.put("settleRole", ConstantUtils.SETTLE_ROLE_1);
		updateMap.put("settleStatusDatetime", date);
		updateMap.put("updateDate", date);
		updateMap.put("updateBy", user);
		List<String> settleCategoryListUpdateMap = new ArrayList<String>();
		settleCategoryListUpdateMap.add(ConstantUtils.PM_SETTLE_CATEGORY_900);
		settleCategoryListUpdateMap.add(ConstantUtils.PM_SETTLE_CATEGORY_901);
		settleCategoryListUpdateMap.add(ConstantUtils.PM_SETTLE_CATEGORY_902);
		settleCategoryListUpdateMap.add(ConstantUtils.PM_SETTLE_CATEGORY_903);
		settleCategoryListUpdateMap.add(ConstantUtils.PM_SETTLE_CATEGORY_904);
		settleCategoryListUpdateMap.add(ConstantUtils.PM_SETTLE_CATEGORY_905);
		settleCategoryListUpdateMap.add(ConstantUtils.PM_SETTLE_CATEGORY_906);
		settleCategoryListUpdateMap.add(ConstantUtils.PM_SETTLE_CATEGORY_907);
		settleCategoryListUpdateMap.add(ConstantUtils.PM_SETTLE_CATEGORY_908);
		settleCategoryListUpdateMap.add(ConstantUtils.PM_SETTLE_CATEGORY_909);
		settleCategoryListUpdateMap.add(ConstantUtils.PM_SETTLE_CATEGORY_910);
		settleCategoryListUpdateMap.add(ConstantUtils.PM_SETTLE_CATEGORY_911);
		settleCategoryListUpdateMap.add(ConstantUtils.PM_SETTLE_CATEGORY_4);
		updateMap.put("settleCategoryList", settleCategoryListUpdateMap);
		bizPmSettleCategoryDetailDao.updateRelateSummary(updateMap);


		SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
		bizPmPreIndustrySettleBill.setPmPreIndustrySettleBillCode(bizSeiralnumService.getDateSequence("GJ"));
		bizPmPreIndustrySettleBill.setSettleMonth(format.format(date));
		bizPmPreIndustrySettleBill.setSettleDatetime(date);
		bizPmPreIndustrySettleBill.setStatus(ConstantUtils.PM_SETTLE_STATUS_10);
		bizPmPreIndustrySettleBill.setStatusDatetime(date);
		bizPmPreIndustrySettleBill.setStatusDescribe("创建结算单");
		if (user.getEmpId() != null) {
			bizPmPreIndustrySettleBill.setStatusOperatorEmployeeId(Integer.valueOf(user.getEmpId()));
		}
		bizPmPreIndustrySettleBill.preInsert();
		dao.insert(bizPmPreIndustrySettleBill);

		Map<String, Object> updateSumarySettBill = new HashMap<String, Object>();
		updateSumarySettBill.put("orderId", orderId);
		updateSumarySettBill.put("pmEmployeeId", pmEmployeeId);
		updateSumarySettBill.put("settleRole", "1");
		updateSumarySettBill.put("settleStatus", "10");
		updateSumarySettBill.put("settleBillId", bizPmPreIndustrySettleBill.getId());
		bizPmSettleCategorySummaryDao.updateCateGorySummaryPmSettleBillId(updateSumarySettBill);
		
		
		BizBusinessStatusLog statusLog = new BizBusinessStatusLog();
		statusLog.setBusinessType(BusinessLogConstantUtil.PRE_MANAGER_SETTLE_TYPE_3300);
		statusLog.setBusinessOnlyMarkInt(bizPmPreIndustrySettleBill.getId());
		statusLog.setBusinessStatus(ConstantUtils.PM_SETTLE_STATUS_10);
		statusLog.setStatusDatetime(new Date());
		String empId = UserUtils.getUser1().getEmpId();
		if (null != empId) {
		statusLog.setBusinessEmployeeId(Integer.parseInt(empId));
		}
		statusLog.setBusinessRemarks("创建结算单");
		statusLog.preInsert();
		bizBusinessStatusLogDao.insert(statusLog);
		return result;

	}


	@Transactional(readOnly = false)
	public String createCompleteSettleBille(BizPmPreIndustrySettleBill bizPmPreIndustrySettleBill) {
		Integer orderId = bizPmPreIndustrySettleBill.getOrderId();
		Integer pmEmployeeId = bizPmPreIndustrySettleBill.getPmEmployeeId();
		String result = "0";
		Date date = new Date();
		User user = UserUtils.getUser();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("orderId", orderId);
		param.put("settleBillType", 2);
		int count = dao.queryCountPmPreIndustrySettleBillByParam(param);
		if (count > 0) {
			result = "1";
			return result;
		}

		List<BizPmSettleCategoryDetail> detailList = new ArrayList<BizPmSettleCategoryDetail>();

		Map<String, Object> settParam = new HashMap<String, Object>();
		settParam.put("orderId", orderId);
		settParam.put("settleStage", 20);
		BizOrderContractSettle contractSettle = bizOrderContractSettleDao.findOrderContractSettleByParam(settParam);
		if (contractSettle != null) {
			BizPmSettleCategoryDetail detail = new BizPmSettleCategoryDetail();
			detail.setOrderId(orderId);
			detail.setPmEmployeeId(pmEmployeeId);
			detail.setSettleStatus(ConstantUtils.PM_SETTLE_STATUS_10);
			detail.setSettleCategory(ConstantUtils.PM_SETTLE_CATEGORY_1000);
			detail.setSettleStatusDatetime(date);
			detail.setSettleAmount(contractSettle.getContractAmount());
			detail.setRelatedBusinessId(contractSettle.getId());
			detail.setCreateBy(user);
			detail.setCreateDate(date);
			detail.setUpdateBy(user);
			detail.setUpdateDate(date);
			detail.setDelFlag("0");
			detail.setSettleRole(ConstantUtils.SETTLE_ROLE_1);
			detailList.add(detail);
		}

		Map<String, Object> logParam = new HashMap<String, Object>();
		logParam.put("orderId", orderId);
		logParam.put("pmEmPloyeeId", pmEmployeeId);
		BizPmGuaranteeMoneyLog moneyLog = bizPmGuaranteeMoneyLogDao.findByParam(logParam);
		if (moneyLog != null) {
			BizPmSettleCategoryDetail detail = new BizPmSettleCategoryDetail();
			detail.setOrderId(orderId);
			detail.setPmEmployeeId(pmEmployeeId);
			detail.setSettleStatus(ConstantUtils.PM_SETTLE_STATUS_10);
			detail.setSettleCategory(ConstantUtils.PM_SETTLE_CATEGORY_6);
			detail.setSettleStatusDatetime(date);
			if (moneyLog.getTakeoffAmount() != null) {
				detail.setSettleAmount(0 - moneyLog.getTakeoffAmount());
			}
			detail.setRelatedBusinessId(moneyLog.getId());
			detail.setCreateBy(user);
			detail.setCreateDate(date);
			detail.setUpdateBy(user);
			detail.setUpdateDate(date);
			detail.setDelFlag("0");
			detail.setSettleRole(ConstantUtils.SETTLE_ROLE_1);
			detailList.add(detail);
		}

		BizQcLongwayCommissionLog bizQcLongwayCommissionLog = new BizQcLongwayCommissionLog();
		bizQcLongwayCommissionLog.setOrderId(orderId);
		bizQcLongwayCommissionLog.setLongwayCommissionType("10");
		bizQcLongwayCommissionLog.setLongwayCommissionEmployeeId(pmEmployeeId);
		BizQcLongwayCommissionLog commissionLog = bizQcLongwayCommissionLogDao
				.queryCommissionLogByParam(bizQcLongwayCommissionLog);
		if (commissionLog != null) {
			BizPmSettleCategoryDetail detail = new BizPmSettleCategoryDetail();
			detail.setOrderId(orderId);
			detail.setPmEmployeeId(pmEmployeeId);
			detail.setSettleStatus(ConstantUtils.PM_SETTLE_STATUS_10);
			detail.setSettleCategory(ConstantUtils.PM_SETTLE_CATEGORY_1001);
			detail.setSettleStatusDatetime(date);
			detail.setSettleAmount(commissionLog.getCommissionAmount());
			detail.setRelatedBusinessId(commissionLog.getId());
			detail.setCreateBy(user);
			detail.setCreateDate(date);
			detail.setUpdateBy(user);
			detail.setUpdateDate(date);
			detail.setDelFlag("0");
			detail.setSettleRole(ConstantUtils.SETTLE_ROLE_1);
			detailList.add(detail);
		}

		Map<String, Object> changeAddParam = new HashMap<String, Object>();
		changeAddParam.put("orderId", orderId);
		changeAddParam.put("changeType", ConstantUtils.change_type_40);
		BizOrderChange addChange = bizOrderChangeDao.queryOrderChangeByParam(changeAddParam);
		if (addChange != null) {
			BizPmSettleCategoryDetail detail = new BizPmSettleCategoryDetail();
			detail.setOrderId(orderId);
			detail.setPmEmployeeId(pmEmployeeId);
			detail.setSettleStatus(ConstantUtils.PM_SETTLE_STATUS_10);
			detail.setSettleCategory(ConstantUtils.PM_SETTLE_CATEGORY_1004);
			detail.setSettleStatusDatetime(date);
			detail.setSettleAmount(addChange.getChangeAccountAmount());
			detail.setRelatedBusinessId(addChange.getId());
			detail.setCreateBy(user);
			detail.setCreateDate(date);
			detail.setUpdateBy(user);
			detail.setUpdateDate(date);
			detail.setDelFlag("0");
			detail.setSettleRole(ConstantUtils.SETTLE_ROLE_1);
			detailList.add(detail);
		}


		Map<String, Object> changeRecuteParam = new HashMap<String, Object>();
		changeRecuteParam.put("orderId", orderId);
		changeRecuteParam.put("changeType", ConstantUtils.change_type_50);
		BizOrderChange recuteChange = bizOrderChangeDao.queryOrderChangeByParam(changeRecuteParam);
		if (recuteChange != null) {
			BizPmSettleCategoryDetail detail = new BizPmSettleCategoryDetail();
			detail.setOrderId(orderId);
			detail.setPmEmployeeId(pmEmployeeId);
			detail.setSettleStatus(ConstantUtils.PM_SETTLE_STATUS_10);
			detail.setSettleCategory(ConstantUtils.PM_SETTLE_CATEGORY_1005);
			detail.setSettleStatusDatetime(date);
			detail.setSettleAmount(recuteChange.getChangeAccountAmount());
			detail.setRelatedBusinessId(recuteChange.getId());
			detail.setCreateBy(user);
			detail.setCreateDate(date);
			detail.setUpdateBy(user);
			detail.setUpdateDate(date);
			detail.setDelFlag("0");
			detail.setSettleRole(ConstantUtils.SETTLE_ROLE_1);
			detailList.add(detail);
		}


		Map<String, Object> rewardParam = new HashMap<String, Object>();
		rewardParam.put("orderId", orderId);
		rewardParam.put("employeeType", 2);
		rewardParam.put("relatedBusinessType", 2);
		rewardParam.put("rewardPunishType", 1);
		BizBusinessRewardPunish bizBusinessReward = bizBusinessRewardPunishDao
				.queryBusinessRewardPunishByParam(rewardParam);
		if (bizBusinessReward != null) {
			BizPmSettleCategoryDetail detail = new BizPmSettleCategoryDetail();
			detail.setOrderId(orderId);
			detail.setPmEmployeeId(pmEmployeeId);
			detail.setSettleStatus(ConstantUtils.PM_SETTLE_STATUS_10);
			detail.setSettleCategory(ConstantUtils.PM_SETTLE_CATEGORY_1002);
			detail.setSettleStatusDatetime(date);
			detail.setSettleAmount(bizBusinessReward.getRewardPunishAmount());
			detail.setRelatedBusinessId(bizBusinessReward.getId());
			detail.setCreateBy(user);
			detail.setCreateDate(date);
			detail.setUpdateBy(user);
			detail.setUpdateDate(date);
			detail.setDelFlag("0");
			detail.setSettleRole(ConstantUtils.SETTLE_ROLE_1);
			detailList.add(detail);
		}


		Map<String, Object> punishParam = new HashMap<String, Object>();
		punishParam.put("orderId", orderId);
		punishParam.put("employeeType", 2);
		punishParam.put("relatedBusinessType", 2);
		punishParam.put("rewardPunishType", 2);
		BizBusinessRewardPunish bizBusinessPunish = bizBusinessRewardPunishDao
				.queryBusinessRewardPunishByParam(punishParam);
		if (bizBusinessPunish != null) {
			BizPmSettleCategoryDetail detail = new BizPmSettleCategoryDetail();
			detail.setOrderId(orderId);
			detail.setPmEmployeeId(pmEmployeeId);
			detail.setSettleStatus(ConstantUtils.PM_SETTLE_STATUS_10);
			detail.setSettleCategory(ConstantUtils.PM_SETTLE_CATEGORY_1003);
			detail.setSettleStatusDatetime(date);
			detail.setSettleAmount(bizBusinessPunish.getRewardPunishAmount());
			detail.setRelatedBusinessId(bizBusinessPunish.getId());
			detail.setCreateBy(user);
			detail.setCreateDate(date);
			detail.setUpdateBy(user);
			detail.setUpdateDate(date);
			detail.setDelFlag("0");
			detail.setSettleRole(ConstantUtils.SETTLE_ROLE_1);
			detailList.add(detail);
		}
		bizPmSettleCategoryDetailDao.insertBatch(detailList);


		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("orderId", orderId);
		map1.put("settleStatus", ConstantUtils.PM_SETTLE_STATUS_10);
		map1.put("settleRole", ConstantUtils.SETTLE_ROLE_1);
		List<String> settleCategoryListMap = new ArrayList<String>();
		settleCategoryListMap.add(ConstantUtils.PM_SETTLE_CATEGORY_1000);
		settleCategoryListMap.add(ConstantUtils.PM_SETTLE_CATEGORY_1001);
		settleCategoryListMap.add(ConstantUtils.PM_SETTLE_CATEGORY_1002);
		settleCategoryListMap.add(ConstantUtils.PM_SETTLE_CATEGORY_1003);
		settleCategoryListMap.add(ConstantUtils.PM_SETTLE_CATEGORY_1004);
		settleCategoryListMap.add(ConstantUtils.PM_SETTLE_CATEGORY_1005);
		settleCategoryListMap.add(ConstantUtils.PM_SETTLE_CATEGORY_4);
		settleCategoryListMap.add(ConstantUtils.PM_SETTLE_CATEGORY_6);
		map1.put("settleCategoryList", settleCategoryListMap);
		List<BizPmSettleCategoryDetail> list = bizPmSettleCategoryDetailDao.queryCateGoryAmountByCondition(map1);
		if (CollectionUtils.isNotEmpty(list)) {
			List<BizPmSettleCategorySummary> summaryList = new ArrayList<BizPmSettleCategorySummary>();
			for (BizPmSettleCategoryDetail detail : list) {
				BizPmSettleCategorySummary summary = new BizPmSettleCategorySummary();
				summary.setOrderId(orderId);
				summary.setPmEmployeeId(detail.getPmEmployeeId());
				summary.setSettleCategory(detail.getSettleCategory());
				summary.setSettleAmount(detail.getSettleAmount());
				summary.setSettleStatus(ConstantUtils.PM_SETTLE_STATUS_10);
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
		updateMap.put("settleStatus", ConstantUtils.PM_SETTLE_STATUS_10);
		updateMap.put("newSettleStatus", ConstantUtils.PM_SETTLE_STATUS_10);
		updateMap.put("settleRole", ConstantUtils.SETTLE_ROLE_1);
		updateMap.put("settleStatusDatetime", date);
		updateMap.put("updateDate", date);
		updateMap.put("updateBy", user);
		List<String> settleCategoryListUpdateMap = new ArrayList<String>();
		settleCategoryListUpdateMap.add(ConstantUtils.PM_SETTLE_CATEGORY_1000);
		settleCategoryListUpdateMap.add(ConstantUtils.PM_SETTLE_CATEGORY_1001);
		settleCategoryListUpdateMap.add(ConstantUtils.PM_SETTLE_CATEGORY_1002);
		settleCategoryListUpdateMap.add(ConstantUtils.PM_SETTLE_CATEGORY_1003);
		settleCategoryListUpdateMap.add(ConstantUtils.PM_SETTLE_CATEGORY_1004);
		settleCategoryListUpdateMap.add(ConstantUtils.PM_SETTLE_CATEGORY_1005);
		settleCategoryListUpdateMap.add(ConstantUtils.PM_SETTLE_CATEGORY_4);
		settleCategoryListUpdateMap.add(ConstantUtils.PM_SETTLE_CATEGORY_6);
		updateMap.put("settleCategoryList", settleCategoryListUpdateMap);
		bizPmSettleCategoryDetailDao.updateRelateSummary(updateMap);


		SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
		bizPmPreIndustrySettleBill.setPmPreIndustrySettleBillCode(bizSeiralnumService.getDateSequence("GJ"));
		bizPmPreIndustrySettleBill.setSettleMonth(format.format(date));
		bizPmPreIndustrySettleBill.setSettleDatetime(date);
		bizPmPreIndustrySettleBill.setStatus(ConstantUtils.PM_SETTLE_STATUS_10);
		bizPmPreIndustrySettleBill.setStatusDatetime(date);
		bizPmPreIndustrySettleBill.setStatusDescribe("创建结算单");
		if (user.getEmpId() != null) {
			bizPmPreIndustrySettleBill.setStatusOperatorEmployeeId(Integer.valueOf(user.getEmpId()));
		}
		bizPmPreIndustrySettleBill.preInsert();
		dao.insert(bizPmPreIndustrySettleBill);

		Map<String, Object> updateSumarySettBill = new HashMap<String, Object>();
		updateSumarySettBill.put("orderId", orderId);
		updateSumarySettBill.put("pmEmployeeId", pmEmployeeId);
		updateSumarySettBill.put("settleRole", "1");
		updateSumarySettBill.put("settleStatus", "10");
		updateSumarySettBill.put("settleBillId", bizPmPreIndustrySettleBill.getId());
		bizPmSettleCategorySummaryDao.updateCateGorySummaryPmSettleBillId(updateSumarySettBill);
		
		BizBusinessStatusLog statusLog = new BizBusinessStatusLog();
		statusLog.setBusinessType(BusinessLogConstantUtil.PRE_MANAGER_SETTLE_TYPE_3300);
		statusLog.setBusinessOnlyMarkInt(bizPmPreIndustrySettleBill.getId());
		statusLog.setBusinessStatus(ConstantUtils.PM_SETTLE_STATUS_10);
		statusLog.setStatusDatetime(new Date());
		String empId = UserUtils.getUser1().getEmpId();
		if (null != empId) {
		statusLog.setBusinessEmployeeId(Integer.parseInt(empId));
		}
		statusLog.setBusinessRemarks("创建结算单");
		statusLog.preInsert();
		bizBusinessStatusLogDao.insert(statusLog);
		return result;
	}

	public BizPmPreIndustrySettleBill queryPmPreIndustrySettleBillByParam(Map<String, Object> param) {
		return dao.queryPmPreIndustrySettleBillByParam(param);
	}

	public Page<BizPmPreIndustrySettleBill> findPage(Page<BizPmPreIndustrySettleBill> page,
			BizPmPreIndustrySettleBill bizPmPreIndustrySettleBill) {
		return super.findPage(page, bizPmPreIndustrySettleBill);
	}

	public List<BizPmPreIndustrySettleBill> findList(BizPmPreIndustrySettleBill bizPmPreIndustrySettleBill) {
		return dao.findList(bizPmPreIndustrySettleBill);
	}

	@Transactional(readOnly = false)
	public void sendingSettleBill(Integer id) {
		Date date = new Date();
		BizPmPreIndustrySettleBill bizPmPreIndustrySettleBill = dao.get(id);
		String oldStatus = bizPmPreIndustrySettleBill.getStatus();
		String status = null;
		String statusDescribe = null;
		if (oldStatus.equals(ConstantUtils.PM_SETTLE_STATUS_10)) {
			status = ConstantUtils.PM_SETTLE_STATUS_35;
			statusDescribe="已下发给项目经理";
		} else if (oldStatus.equals(ConstantUtils.PM_SETTLE_STATUS_45)) {
			status = ConstantUtils.PM_SETTLE_STATUS_38;
			statusDescribe="已重新下发给项目经理";
		}
		bizPmPreIndustrySettleBill.setStatus(status);
		bizPmPreIndustrySettleBill.setStatusDescribe(statusDescribe);
		;
		bizPmPreIndustrySettleBill.setStatusDatetime(date);
		bizPmPreIndustrySettleBill.preUpdate();
		dao.update(bizPmPreIndustrySettleBill);

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("settleStatus", status);
		param.put("settleStatusDatetime", date);
		param.put("settleBillId", bizPmPreIndustrySettleBill.getId());
		param.put("orderId", bizPmPreIndustrySettleBill.getOrderId());
		dao.updateSettleCategorySummaryStatusByParam(param);

		Map<String, Object> param2 = new HashMap<String, Object>();
		param2.put("settleStatus", status);
		param2.put("settleStatusDatetime", date);
		param2.put("settleBillId", bizPmPreIndustrySettleBill.getId());
		param2.put("orderId", bizPmPreIndustrySettleBill.getOrderId());
		dao.updateSettleCategoryStatusByParam(param2);
		
		BizBusinessStatusLog statusLog = new BizBusinessStatusLog();
		if( ConstantUtils.PM_SETTLE_STATUS_35.equals( status ) ){
			statusLog.setBusinessType(BusinessLogConstantUtil.PRE_MANAGER_SETTLE_TYPE_3400);
		}else if( ConstantUtils.PM_SETTLE_STATUS_38.equals( status ) ){
			statusLog.setBusinessType(BusinessLogConstantUtil.PRE_MANAGER_SETTLE_TYPE_3700);
		}
		statusLog.setBusinessOnlyMarkInt(bizPmPreIndustrySettleBill.getId());
		statusLog.setBusinessStatus(status);
		statusLog.setStatusDatetime(new Date());
		String empId = UserUtils.getUser1().getEmpId();
		if (null != empId) {
		statusLog.setBusinessEmployeeId(Integer.parseInt(empId));
		}
		statusLog.setBusinessRemarks(statusDescribe);
		statusLog.preInsert();
		bizBusinessStatusLogDao.insert(statusLog);

	}

	public int queryCountByParam(Map<String, Object> param) {
		return dao.queryCountByParam(param);
	}

	public int querySummaryBillCountByParam(Map<String, Object> param) {
		return dao.querySummaryBillCountByParam(param);
	}


	@Transactional(readOnly = false)
	public void createMonthlySettle(String ids, String settleMonth, String storeId) {
		Date date = new Date();
		User user = UserUtils.getUser();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", ConstantUtils.PM_SETTLE_STATUS_40);
		map.put("storeId", storeId);
		List<String> list = new ArrayList<String>();
		for (String idStr : ids.split(",")) {
			list.add(idStr);
		}
		map.put("list", list);
		List<BizPmPreIndustrySettleBill> settleBillList = new ArrayList<BizPmPreIndustrySettleBill>();
		List<BizPmPreIndustrySettleSummaryBill> summaryBillList = dao.querySettleSummaryBillByParam(map);
		for (BizPmPreIndustrySettleSummaryBill summaryBill : summaryBillList) {
			summaryBill.setPmPreIndustrySettleSummaryBill(bizSeiralnumService.getDateSequence("YJ"));
			summaryBill.setSettleMonth(settleMonth);
			summaryBill.setStatus(ConstantUtils.PM_SETTLE_STATUS_50);
			summaryBill.setStatusDescribe("已生成结算单");
			summaryBill.setStatusDatetime(date);
			summaryBill.setStatusDescribe(user.getEmpId());
			summaryBill.setCreateBy(user);
			summaryBill.setCreateDate(date);
			summaryBill.setUpdateDate(date);
			summaryBill.setUpdateBy(user);
			bizPmPreIndustrySettleSummaryBillDao.insert(summaryBill);

			String[] settBillsIds = summaryBill.getSettleBillId().split(",");
			for (String id : settBillsIds) {
				BizPmPreIndustrySettleBill bill = new BizPmPreIndustrySettleBill();
				bill.setId(Integer.parseInt(id));
				bill.setPmPreIndustrySettleSummaryBillId(summaryBill.getId());
				settleBillList.add(bill);
				
				BizBusinessStatusLog statusLog = new BizBusinessStatusLog();
				statusLog.setBusinessType(BusinessLogConstantUtil.PRE_MANAGER_SETTLE_TYPE_3900);
				statusLog.setBusinessOnlyMarkInt(Integer.valueOf(id));
				statusLog.setBusinessStatus(ConstantUtils.PM_SETTLE_STATUS_50);
				statusLog.setStatusDatetime(new Date());
				String empId = UserUtils.getUser1().getEmpId();
				if (null != empId) {
				statusLog.setBusinessEmployeeId(Integer.parseInt(empId));
				}
				statusLog.setBusinessRemarks("已生成月度结算单");
				statusLog.preInsert();
				bizBusinessStatusLogDao.insert(statusLog);
			}
		}

		dao.updateBatchByRelate(settleBillList);


		Map<String, Object> updateSettleBillParam = new HashMap<String, Object>();
		updateSettleBillParam.put("status", ConstantUtils.PM_SETTLE_STATUS_50);
		updateSettleBillParam.put("updateSettleBilllist", list);
		updateSettleBillParam.put("statusOperatorEmployeeId", user.getEmpId());
		updateSettleBillParam.put("statusDatetime", date);
		updateSettleBillParam.put("statusDescribe", "已生成结算单");
		dao.updateBatchSettleBillStatus(updateSettleBillParam);


		Map<String, Object> CateGorgSummaryMap = new HashMap<String, Object>();
		CateGorgSummaryMap.put("status", ConstantUtils.PM_SETTLE_STATUS_50);
		CateGorgSummaryMap.put("statusDatetime", date);
		CateGorgSummaryMap.put("updateSettleBilllist", list);
		dao.updateBatchCateGorgSummaryStatus(CateGorgSummaryMap);


		Map<String, Object> CateGorgMap = new HashMap<String, Object>();
		CateGorgMap.put("status", ConstantUtils.PM_SETTLE_STATUS_50);
		CateGorgMap.put("statusDatetime", date);
		CateGorgMap.put("updateSettleBilllist", list);
		dao.updateBatchCateGorgStatus(CateGorgMap);
		
		
	}

	public HSSFWorkbook exportPmSettleBill(BizPmPreIndustrySettleBill bizPmPreIndustrySettleBill) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		HSSFWorkbook wb = new HSSFWorkbook();

		HSSFCellStyle columnStyle = wb.createCellStyle();
		columnStyle.setLeftBorderColor(HSSFColor.BLACK.index);
		columnStyle.setBorderLeft((short) 1);
		columnStyle.setRightBorderColor(HSSFColor.BLACK.index);
		columnStyle.setBorderRight((short) 1);
		columnStyle.setTopBorderColor(HSSFColor.BLACK.index);
		columnStyle.setBorderTop((short) 1);
		columnStyle.setBottomBorderColor(HSSFColor.BLACK.index);
		columnStyle.setBorderBottom((short) 1);
		columnStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		HSSFCellStyle headStyle = wb.createCellStyle();
		headStyle.setLeftBorderColor(HSSFColor.BLACK.index);
		headStyle.setBorderLeft((short) 1);
		headStyle.setRightBorderColor(HSSFColor.BLACK.index);
		headStyle.setBorderRight((short) 1);
		headStyle.setTopBorderColor(HSSFColor.BLACK.index);
		headStyle.setBorderTop((short) 1);
		headStyle.setBottomBorderColor(HSSFColor.BLACK.index);
		headStyle.setBorderBottom((short) 1);
		headStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		headStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		HSSFSheet sheet = wb.createSheet("中期结算单");

		sheet.setColumnWidth(0, 2000);
		sheet.setColumnWidth(1, 2000);
		sheet.setColumnWidth(2, 3000);
		sheet.setColumnWidth(3, 2000);
		sheet.setColumnWidth(4, 2000);
		sheet.setColumnWidth(5, 3000);
		sheet.setColumnWidth(6, 5000);
		sheet.setColumnWidth(7, 3000);
		sheet.setColumnWidth(8, 3000);
		sheet.setColumnWidth(9, 3000);
		sheet.setColumnWidth(10, 3000);
		sheet.setColumnWidth(11, 3000);
		sheet.setColumnWidth(12, 3000);
		sheet.setColumnWidth(13, 3000);
		sheet.setColumnWidth(14, 3000);
		sheet.setColumnWidth(15, 3000);
		sheet.setColumnWidth(16, 3000);
		sheet.setColumnWidth(17, 3000);
		sheet.setColumnWidth(18, 3000);
		sheet.setColumnWidth(19, 3000);
		sheet.setColumnWidth(20, 3000);
		sheet.setColumnWidth(21, 3000);
		sheet.setColumnWidth(22, 3000);
		sheet.setColumnWidth(23, 3000);
		sheet.setColumnWidth(24, 3000);


		HSSFRow rowTitle = sheet.createRow(0);
		HSSFCell headCell0 = rowTitle.createCell(0);
		headCell0.setCellStyle(headStyle);
		headCell0.setCellValue("序号");

		HSSFCell headCell1 = rowTitle.createCell(1);
		headCell1.setCellStyle(headStyle);
		headCell1.setCellValue("门店");

		HSSFCell headCell221 = rowTitle.createCell(2);
		headCell221.setCellStyle(headStyle);
		headCell221.setCellValue("生成月度结算时间");

		HSSFCell headCell2 = rowTitle.createCell(3);
		headCell2.setCellStyle(headStyle);
		headCell2.setCellValue("月度");

		HSSFCell headCell3 = rowTitle.createCell(4);
		headCell3.setCellStyle(headStyle);
		headCell3.setCellValue("客户姓名");

		HSSFCell headCell4 = rowTitle.createCell(5);
		headCell4.setCellStyle(headStyle);
		headCell4.setCellValue("电话");

		HSSFCell headCell5 = rowTitle.createCell(6);
		headCell5.setCellStyle(headStyle);
		headCell5.setCellValue("地址");

		HSSFCell headCell6 = rowTitle.createCell(7);
		headCell6.setCellStyle(headStyle);
		headCell6.setCellValue("项目经理");

		HSSFCell headCell7 = rowTitle.createCell(8);
		headCell7.setCellStyle(headStyle);
		headCell7.setCellValue("结算单状态");

		HSSFCell headCell8 = rowTitle.createCell(9);
		headCell8.setCellStyle(headStyle);
		headCell8.setCellValue("结算阶段");

		HSSFCell headCell9 = rowTitle.createCell(10);
		headCell9.setCellStyle(headStyle);
		headCell9.setCellValue("承包总价");

		HSSFCell headCell10 = rowTitle.createCell(11);
		headCell10.setCellStyle(headStyle);
		headCell10.setCellValue("基装增项");

		HSSFCell headCell11 = rowTitle.createCell(12);
		headCell11.setCellStyle(headStyle);
		headCell11.setCellValue("辅料用量扣款");

		HSSFCell headCell12 = rowTitle.createCell(13);
		headCell12.setCellStyle(headStyle);
		headCell12.setCellValue("沙子水泥扣款");

		HSSFCell headCell13 = rowTitle.createCell(14);
		headCell13.setCellStyle(headStyle);
		headCell13.setCellValue("标化材料扣款");

		HSSFCell headCell14 = rowTitle.createCell(15);
		headCell14.setCellStyle(headStyle);
		headCell14.setCellValue("开关面板扣款");

		HSSFCell headCell15 = rowTitle.createCell(16);
		headCell15.setCellStyle(headStyle);
		headCell15.setCellValue("工人人工费扣款");

		HSSFCell headCell16 = rowTitle.createCell(17);
		headCell16.setCellStyle(headStyle);
		headCell16.setCellValue("中期质检罚款");

		HSSFCell headCell17 = rowTitle.createCell(18);
		headCell17.setCellStyle(headStyle);
		headCell17.setCellValue("中期奖励");

		HSSFCell headCell18 = rowTitle.createCell(19);
		headCell18.setCellStyle(headStyle);
		headCell18.setCellValue("中期扣款");

		HSSFCell headCell19 = rowTitle.createCell(20);
		headCell19.setCellStyle(headStyle);
		headCell19.setCellValue("材料搬运及运输费");

		HSSFCell headCell20 = rowTitle.createCell(21);
		headCell20.setCellStyle(headStyle);
		headCell20.setCellValue("中期变更增项");

		HSSFCell headCell21 = rowTitle.createCell(22);
		headCell21.setCellStyle(headStyle);
		headCell21.setCellValue("中期变更减项");

		HSSFCell headCell22 = rowTitle.createCell(23);
		headCell22.setCellStyle(headStyle);
		headCell22.setCellValue("中期承包价结算金额");

		HSSFCell headCell23 = rowTitle.createCell(24);
		headCell23.setCellStyle(headStyle);
		headCell23.setCellValue("中期实际结算金额");

		HSSFSheet sheet2 = wb.createSheet("竣工结算单");

		sheet2.setColumnWidth(0, 2000);
		sheet2.setColumnWidth(1, 2000);
		sheet2.setColumnWidth(2, 3000);
		sheet2.setColumnWidth(3, 2000);
		sheet2.setColumnWidth(4, 2000);
		sheet2.setColumnWidth(5, 3000);
		sheet2.setColumnWidth(6, 5000);
		sheet2.setColumnWidth(7, 3000);
		sheet2.setColumnWidth(8, 3000);
		sheet2.setColumnWidth(9, 3000);
		sheet2.setColumnWidth(10, 3000);
		sheet2.setColumnWidth(11, 3000);
		sheet2.setColumnWidth(12, 3000);
		sheet2.setColumnWidth(13, 3000);
		sheet2.setColumnWidth(14, 3000);
		sheet2.setColumnWidth(15, 3000);
		sheet2.setColumnWidth(16, 3000);
		sheet2.setColumnWidth(17, 3000);
		sheet2.setColumnWidth(18, 3000);
		sheet2.setColumnWidth(19, 3000);


		HSSFRow rowTitle2 = sheet2.createRow(0);
		HSSFCell head2Cell0 = rowTitle2.createCell(0);
		head2Cell0.setCellStyle(headStyle);
		head2Cell0.setCellValue("序号");

		HSSFCell head2Cell1 = rowTitle2.createCell(1);
		head2Cell1.setCellStyle(headStyle);
		head2Cell1.setCellValue("门店");

		HSSFCell head2Cell22 = rowTitle2.createCell(2);
		head2Cell22.setCellStyle(headStyle);
		head2Cell22.setCellValue("生成月度结算时间");

		HSSFCell head2Cell2 = rowTitle2.createCell(3);
		head2Cell2.setCellStyle(headStyle);
		head2Cell2.setCellValue("月度");

		HSSFCell head2Cell3 = rowTitle2.createCell(4);
		head2Cell3.setCellStyle(headStyle);
		head2Cell3.setCellValue("客户姓名");

		HSSFCell head2Cell4 = rowTitle2.createCell(5);
		head2Cell4.setCellStyle(headStyle);
		head2Cell4.setCellValue("电话");

		HSSFCell head2Cell5 = rowTitle2.createCell(6);
		head2Cell5.setCellStyle(headStyle);
		head2Cell5.setCellValue("地址");

		HSSFCell head2Cell6 = rowTitle2.createCell(7);
		head2Cell6.setCellStyle(headStyle);
		head2Cell6.setCellValue("项目经理");

		HSSFCell head2Cell7 = rowTitle2.createCell(8);
		head2Cell7.setCellStyle(headStyle);
		head2Cell7.setCellValue("结算单状态");

		HSSFCell head2Cell8 = rowTitle2.createCell(9);
		head2Cell8.setCellStyle(headStyle);
		head2Cell8.setCellValue("结算阶段");

		HSSFCell head2Cell9 = rowTitle2.createCell(10);
		head2Cell9.setCellStyle(headStyle);
		head2Cell9.setCellValue("承包总价");

		HSSFCell head2Cell10 = rowTitle2.createCell(11);
		head2Cell10.setCellStyle(headStyle);
		head2Cell10.setCellValue("竣工质检罚款");

		HSSFCell head2Cell11 = rowTitle2.createCell(12);
		head2Cell11.setCellStyle(headStyle);
		head2Cell11.setCellValue("质保金");

		HSSFCell head2Cell12 = rowTitle2.createCell(13);
		head2Cell12.setCellStyle(headStyle);
		head2Cell12.setCellValue("远程费");

		HSSFCell head2Cell13 = rowTitle2.createCell(14);
		head2Cell13.setCellStyle(headStyle);
		head2Cell13.setCellValue("竣工奖励");

		HSSFCell head2Cell14 = rowTitle2.createCell(15);
		head2Cell14.setCellStyle(headStyle);
		head2Cell14.setCellValue("竣工扣款");

		HSSFCell head2Cell15 = rowTitle2.createCell(16);
		head2Cell15.setCellStyle(headStyle);
		head2Cell15.setCellValue("竣工变更增项");

		HSSFCell head2Cell16 = rowTitle2.createCell(17);
		head2Cell16.setCellStyle(headStyle);
		head2Cell16.setCellValue("竣工变更减项");

		HSSFCell head2Cell17 = rowTitle2.createCell(18);
		head2Cell17.setCellStyle(headStyle);
		head2Cell17.setCellValue("竣工承包价结算金额");

		HSSFCell head2Cell18 = rowTitle2.createCell(19);
		head2Cell18.setCellStyle(headStyle);
		head2Cell18.setCellValue("竣工实发结算金额");

		List<BizPmPreIndustrySettleBill> settleBillList = dao.findList(bizPmPreIndustrySettleBill);
		if (CollectionUtils.isNotEmpty(settleBillList)) {
			int a = 0;
			int b = 0;
			for (int i = 0; i < settleBillList.size(); i++) {
				BizPmPreIndustrySettleBill settleBill = settleBillList.get(i);
				if (settleBill.getSettleBillType().equals("1")) {
					HSSFRow row = sheet.createRow(a + 1);
					HSSFCell cell0 = row.createCell(0);
					cell0.setCellStyle(columnStyle);
					cell0.setCellValue(a + 1);

					HSSFCell cell1 = row.createCell(1);
					cell1.setCellStyle(columnStyle);
					if (settleBill.getStoreId() != null) {
						cell1.setCellValue(BizDictUtils.getStoreLabel(settleBill.getStoreId() + "", ""));
					}

					HSSFCell cell221 = row.createCell(2);
					if(settleBill.getCreateMonthDate() != null){
						cell221.setCellValue(sdf.format(settleBill.getCreateMonthDate()));
					}

					HSSFCell cell2 = row.createCell(3);
					cell2.setCellStyle(columnStyle);
					if (settleBill.getSettleMonth() != null) {
						cell2.setCellValue(settleBill.getSettleMonth());
					}

					HSSFCell cell3 = row.createCell(4);
					cell3.setCellStyle(columnStyle);
					if (settleBill.getCustomerName() != null) {
						cell3.setCellValue(settleBill.getCustomerName());
					}

					HSSFCell cell4 = row.createCell(5);
					cell4.setCellStyle(columnStyle);
					if (settleBill.getCustomerPhone() != null) {
						cell4.setCellValue(settleBill.getCustomerPhone());
					}

					HSSFCell cell5 = row.createCell(6);
					cell5.setCellStyle(columnStyle);
					if (settleBill.getCommunityName() != null && settleBill.getBuildNumber() != null
							&& settleBill.getBuildRoom() != null && settleBill.getBuildUnit() != null) {
						cell5.setCellValue(settleBill.getCommunityName() + "-" + settleBill.getBuildNumber() + "-"
								+ settleBill.getBuildUnit() + "-" + settleBill.getBuildRoom());
					}

					HSSFCell cell6 = row.createCell(7);
					cell6.setCellStyle(columnStyle);
					if (settleBill.getItemCustomer() != null) {
						cell6.setCellValue(settleBill.getItemCustomer());
					}

					HSSFCell cell7 = row.createCell(8);
					cell7.setCellStyle(columnStyle);
					if (settleBill.getStatus() != null) {
						if (settleBill.getStatus().equals("10")) {
							cell7.setCellValue("已创建");
						} else if (settleBill.getStatus().equals("35")) {
							cell7.setCellValue("已下发给项目经理");
						} else if (settleBill.getStatus().equals("38")) {
							cell7.setCellValue("已重新下发给项目经理");
						} else if (settleBill.getStatus().equals("40")) {
							cell7.setCellValue("项目经理已同意结算金额");
						} else if (settleBill.getStatus().equals("45")) {
							cell7.setCellValue("项目经理已拒绝结算金额");
						} else if (settleBill.getStatus().equals("50")) {
							cell7.setCellValue("已生成月度结算单");
						}
					}

					HSSFCell cell8 = row.createCell(9);
					cell8.setCellStyle(columnStyle);
					if (settleBill.getSettleBillType() != null) {
						if (settleBill.getSettleBillType().equals("1")) {
							cell8.setCellValue("中期结算");
						} else if (settleBill.getSettleBillType().equals("2")) {
							cell8.setCellValue("竣工结算");
						}

					}

					HSSFCell cell9 = row.createCell(10);
					cell9.setCellStyle(columnStyle);
					if (settleBill.getContractAmount() != null) {
						cell9.setCellValue(settleBill.getContractAmount());
					}

					HSSFCell cell10 = row.createCell(11);
					cell10.setCellStyle(columnStyle);
					if (settleBill.getMidwayBasicworkAddAmount() != null) {
						cell10.setCellValue(settleBill.getMidwayBasicworkAddAmount());
					}

					HSSFCell cell11 = row.createCell(12);
					cell11.setCellStyle(columnStyle);
					if (settleBill.getMidwayMaterialsAuxiliaryAmount() != null) {
						cell11.setCellValue(settleBill.getMidwayMaterialsAuxiliaryAmount());
					}

					HSSFCell cell12 = row.createCell(13);
					cell12.setCellStyle(columnStyle);
					if (settleBill.getMidwaySandCementAmount() != null) {
						cell12.setCellValue(settleBill.getMidwaySandCementAmount());
					}

					HSSFCell cell13 = row.createCell(14);
					cell13.setCellStyle(columnStyle);
					if (settleBill.getMidwayMaterialsStandardAmount() != null) {
						cell13.setCellValue(settleBill.getMidwayMaterialsStandardAmount());
					}

					HSSFCell cell14 = row.createCell(15);
					cell14.setCellStyle(columnStyle);
					if (settleBill.getMidwaySwitchPanelAmount() != null) {
						cell14.setCellValue(settleBill.getMidwaySwitchPanelAmount());
					}

					HSSFCell cell15 = row.createCell(16);
					cell15.setCellStyle(columnStyle);
					if (settleBill.getMidwayWorkerSalaryAmount() != null) {
						cell15.setCellValue(settleBill.getMidwayWorkerSalaryAmount());
					}

					HSSFCell cell16 = row.createCell(17);
					cell16.setCellStyle(columnStyle);
					if (settleBill.getMidwayQcCheckPunishAmount() != null) {
						cell16.setCellValue(settleBill.getMidwayQcCheckPunishAmount());
					}

					HSSFCell cell17 = row.createCell(18);
					cell17.setCellStyle(columnStyle);
					if (settleBill.getRewardAmount() != null) {
						cell17.setCellValue(settleBill.getRewardAmount());
					}

					HSSFCell cell18 = row.createCell(19);
					cell18.setCellStyle(columnStyle);
					if (settleBill.getPunishAmount() != null) {
						cell18.setCellValue(settleBill.getPunishAmount());
					}

					HSSFCell cell119 = row.createCell(20);
					cell119.setCellStyle(columnStyle);
					if (settleBill.getMidwayMaterialCarryCostAmount() != null) {
						cell119.setCellValue(settleBill.getMidwayMaterialCarryCostAmount());
					}

					HSSFCell cell20 = row.createCell(21);
					cell20.setCellStyle(columnStyle);
					if (settleBill.getOrderChangeAddAmount() != null) {
						cell20.setCellValue(settleBill.getOrderChangeAddAmount());
					}

					HSSFCell cell21 = row.createCell(22);
					cell21.setCellStyle(columnStyle);
					if (settleBill.getOrderChangeReduceAmount() != null) {
						cell21.setCellValue(settleBill.getOrderChangeReduceAmount());
					}

					HSSFCell cell22 = row.createCell(23);
					cell22.setCellStyle(columnStyle);
					if (settleBill.getContractSettleAmount() != null) {
						cell22.setCellValue(settleBill.getContractSettleAmount());
					}

					HSSFCell cell23 = row.createCell(24);
					cell23.setCellStyle(columnStyle);
					if (settleBill.getRealSettleAmount() != null) {
						cell23.setCellValue(settleBill.getRealSettleAmount());
					}
					a = a + 1;
				} else if (settleBill.getSettleBillType().equals("2")) {
					HSSFRow row = sheet2.createRow(b + 1);
					HSSFCell cell0 = row.createCell(0);
					cell0.setCellStyle(columnStyle);
					cell0.setCellValue(b + 1);

					HSSFCell cell1 = row.createCell(1);
					cell1.setCellStyle(columnStyle);
					if (settleBill.getStoreId() != null) {
						cell1.setCellValue(BizDictUtils.getStoreLabel(settleBill.getStoreId() + "", ""));
					}

					HSSFCell cell221 = row.createCell(2);
					if(settleBill.getCreateMonthDate() != null){
						cell221.setCellValue(sdf.format(settleBill.getCreateMonthDate()));
					}

					HSSFCell cell2 = row.createCell(3);
					cell2.setCellStyle(columnStyle);
					if (settleBill.getSettleMonth() != null) {
						cell2.setCellValue(settleBill.getSettleMonth());
					}

					HSSFCell cell3 = row.createCell(4);
					cell3.setCellStyle(columnStyle);
					if (settleBill.getCustomerName() != null) {
						cell3.setCellValue(settleBill.getCustomerName());
					}

					HSSFCell cell4 = row.createCell(5);
					cell4.setCellStyle(columnStyle);
					if (settleBill.getCustomerPhone() != null) {
						cell4.setCellValue(settleBill.getCustomerPhone());
					}

					HSSFCell cell5 = row.createCell(6);
					cell5.setCellStyle(columnStyle);
					if (settleBill.getCommunityName() != null && settleBill.getBuildNumber() != null
							&& settleBill.getBuildRoom() != null && settleBill.getBuildUnit() != null) {
						cell5.setCellValue(settleBill.getCommunityName() + "-" + settleBill.getBuildNumber() + "-"
								+ settleBill.getBuildUnit() + "-" + settleBill.getBuildRoom());
					}

					HSSFCell cell6 = row.createCell(7);
					cell6.setCellStyle(columnStyle);
					if (settleBill.getItemCustomer() != null) {
						cell6.setCellValue(settleBill.getItemCustomer());
					}

					HSSFCell cell7 = row.createCell(8);
					cell7.setCellStyle(columnStyle);
					if (settleBill.getStatus() != null) {
						if (settleBill.getStatus().equals("10")) {
							cell7.setCellValue("已创建");
						} else if (settleBill.getStatus().equals("35")) {
							cell7.setCellValue("已下发给项目经理");
						} else if (settleBill.getStatus().equals("38")) {
							cell7.setCellValue("已重新下发给项目经理");
						} else if (settleBill.getStatus().equals("40")) {
							cell7.setCellValue("项目经理已同意结算金额");
						} else if (settleBill.getStatus().equals("45")) {
							cell7.setCellValue("项目经理已拒绝结算金额");
						} else if (settleBill.getStatus().equals("50")) {
							cell7.setCellValue("已生成月度结算单");
						}
					}

					HSSFCell cell8 = row.createCell(9);
					cell8.setCellStyle(columnStyle);
					if (settleBill.getSettleBillType() != null) {
						if (settleBill.getSettleBillType().equals("1")) {
							cell8.setCellValue("中期结算");
						} else if (settleBill.getSettleBillType().equals("2")) {
							cell8.setCellValue("竣工结算");
						}

					}

					HSSFCell cell9 = row.createCell(10);
					cell9.setCellStyle(columnStyle);
					if (settleBill.getContractAmount() != null) {
						cell9.setCellValue(settleBill.getContractAmount());
					}

					HSSFCell cell10 = row.createCell(11);
					cell10.setCellStyle(columnStyle);
					if (settleBill.getMidwayQcCheckPunishAmount() != null) {
						cell10.setCellValue(settleBill.getMidwayQcCheckPunishAmount());
					}

					HSSFCell cell11 = row.createCell(12);
					cell11.setCellStyle(columnStyle);
					if (settleBill.getCompleteGuaranteeMoneyAmount() != null) {
						cell11.setCellValue(settleBill.getCompleteGuaranteeMoneyAmount());
					}

					HSSFCell cell12 = row.createCell(13);
					cell12.setCellStyle(columnStyle);
					if (settleBill.getCompleteLongwayCommissionAmount() != null) {
						cell12.setCellValue(settleBill.getCompleteLongwayCommissionAmount());
					}

					HSSFCell cell13 = row.createCell(14);
					cell13.setCellStyle(columnStyle);
					if (settleBill.getRewardAmount() != null) {
						cell13.setCellValue(settleBill.getRewardAmount());
					}

					HSSFCell cell14 = row.createCell(15);
					cell14.setCellStyle(columnStyle);
					if (settleBill.getPunishAmount() != null) {
						cell14.setCellValue(settleBill.getPunishAmount());
					}

					HSSFCell cell15 = row.createCell(16);
					cell15.setCellStyle(columnStyle);
					if (settleBill.getOrderChangeAddAmount() != null) {
						cell15.setCellValue(settleBill.getOrderChangeAddAmount());
					}

					HSSFCell cell16 = row.createCell(17);
					cell16.setCellStyle(columnStyle);
					if (settleBill.getOrderChangeReduceAmount() != null) {
						cell16.setCellValue(settleBill.getOrderChangeReduceAmount());
					}

					HSSFCell cell17 = row.createCell(18);
					cell17.setCellStyle(columnStyle);
					if (settleBill.getContractSettleAmount() != null) {
						cell17.setCellValue(settleBill.getContractSettleAmount());
					}

					HSSFCell cell18 = row.createCell(19);
					cell18.setCellStyle(columnStyle);
					if (settleBill.getRealSettleAmount() != null) {
						cell18.setCellValue(settleBill.getRealSettleAmount());
					}
					b = b + 1;
				}
			}
		}
		return wb;
	}

}
