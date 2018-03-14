package cn.damei.entity.modules;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.constantUtils.BusinessLogConstantUtil;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.DateUtils;
import cn.damei.common.utils.SendMsgBusinessType;
import cn.damei.common.utils.StringUtils;
import cn.damei.dao.modules.BizAssessRewardPunishDao;
import cn.damei.dao.modules.BizOrderFinanceCollectionDao;
import cn.damei.dao.modules.BizBusinessStatusLogDao;
import cn.damei.dao.modules.BizOrderFinishProjectBillDao;
import cn.damei.dao.modules.BizPrePmSettleFinDao;
import cn.damei.dao.modules.BizQcBillDao;
import cn.damei.dao.modules.BizSynDataDao;
import cn.damei.dao.modules.BizEmployeeDao;
import cn.damei.dao.modules.OrderDao;
import cn.damei.dao.modules.BizPhoneMsgDao;
import cn.damei.dao.modules.BizPmSettleBillDao;
import cn.damei.dao.modules.BizPmSettleCategoryDetailDao;
import cn.damei.dao.modules.BizPmSettleCategorySummaryDao;
import cn.damei.service.modules.BizSeiralnumService;
import cn.damei.common.utils.UserUtils;

import net.sf.json.JSONObject;

@Service
public class QuarzBizPrePmSettleFinService {

	@Autowired
	private BizSynDataDao bizSynDataDao;
	@Autowired
	private BizPrePmSettleFinDao bizPrePmSettleFinDao;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private BizOrderFinanceCollectionDao bizOrderFinanceCollectionDao;
	@Autowired
	private BizQcBillDao bizQcBillDao;
	@Autowired
	private BizPmSettleBillDao bizPmSettleBillDao;
	@Autowired
	private BizPmSettleCategoryDetailDao bizPmSettleCategoryDetailDao;
	@Autowired
	private BizPmSettleCategorySummaryDao bizPmSettleCategorySummaryDao;

	@Autowired
	private BizSeiralnumService bizSeiralnumService;

	@Autowired
	private BizAssessRewardPunishDao bizAssessRewardPunishDao;

	@Autowired
	private BizBusinessStatusLogDao logDao;
	@Autowired
	private BizOrderFinishProjectBillDao bizOrderFinishProjectBillDao;
	@Autowired
	private BizEmployeeDao bizEmployeeDao;
	@Autowired
	private BizPhoneMsgDao bizPhoneMsgDao;


	@Transactional
	public void updateBizPrePmData(BizSynData data) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String businessData = data.getBusinessData().replaceAll("[\\[\\]]", "");
		JSONObject jb = JSONObject.fromObject(businessData);
		String time = jb.getString("time");
		String amount = jb.getString("amount");
		String orderNumber = jb.getString("orderId");
		String paymentStatus = jb.getString("paymentStatus");
		Integer orderId = orderDao.getIdByOrderNumber(orderNumber);
		if (orderId == null) {
			data.setSynStatus("201");
		} else {
			BizPrePmSettleFin bizPrePmSettleFin = new BizPrePmSettleFin();
			bizPrePmSettleFin.setReceiveMoneyDatetime(sdf.parse(time));
			bizPrePmSettleFin.setOrderNumber(orderNumber);
			bizPrePmSettleFin.setOrderId(orderId);
			if (data.getBusinessType().equals("401")) {
				bizPrePmSettleFin.setReceiveMoneyType("1");
			} else if (data.getBusinessType().equals("402")) {
				bizPrePmSettleFin.setReceiveMoneyType("2");
			}

			bizPrePmSettleFin.setReceiveMoneyAmount(Double.valueOf(amount));
			bizPrePmSettleFin.setCollectionStatus(paymentStatus);
			bizPrePmSettleFin.preInsert();

			List<BizPrePmSettleFin> bizPrePmSettleFinedList = bizPrePmSettleFinDao
					.getBinPrePmByOrderIdAndType(bizPrePmSettleFin);
			if (bizPrePmSettleFinedList != null && bizPrePmSettleFinedList.size() > 0) {
				for (BizPrePmSettleFin bizPrePmSettleFined : bizPrePmSettleFinedList) {
					bizPrePmSettleFined.preUpdate();
					bizPrePmSettleFinDao.delete(bizPrePmSettleFined);
				}
			}
			bizPrePmSettleFinDao.insert(bizPrePmSettleFin);


			Map<String, Object> param = new HashMap<String, Object>();
			param.put("orderId", orderId);
			param.put("collectionType", bizPrePmSettleFin.getReceiveMoneyType());
			int collectionCount = bizOrderFinanceCollectionDao.checkIsExistByParam(param);
			if (collectionCount == 0) {

				BizOrderFinanceCollection orderFinanceCollection = new BizOrderFinanceCollection();
				orderFinanceCollection.setOrderId(bizPrePmSettleFin.getOrderId());
				orderFinanceCollection.setCollectionType(bizPrePmSettleFin.getReceiveMoneyType());
				orderFinanceCollection.setCollectionAmount(bizPrePmSettleFin.getReceiveMoneyAmount());
				orderFinanceCollection.setCollectionDatetime(bizPrePmSettleFin.getReceiveMoneyDatetime());
				orderFinanceCollection.setCollectionStatus(bizPrePmSettleFin.getCollectionStatus());
				orderFinanceCollection.setCollectionRemarks("系统自动操作");
				orderFinanceCollection.setCollectionOperatorDatetime(date);
				orderFinanceCollection.preInsert();
				bizOrderFinanceCollectionDao.insert(orderFinanceCollection);
				if (orderFinanceCollection.getCollectionType().equals("1")) {
					Order order = orderDao.get(String.valueOf(orderId));
					BizEmployee bizEmp = bizEmployeeDao.get(String.valueOf(order.getItemManagerId()));
					BizPhoneMsg ddMsg = new BizPhoneMsg();
					ddMsg.setId(null);
					ddMsg.setReceiveEmployeeId(order.getItemManagerId());
					ddMsg.setMsgContent("订单（" + order.getCommunityName() + "-" + order.getBuildNumber() + "-"
							+ order.getBuildUnit() + "-" + order.getBuildRoom() + "-" + order.getCustomerName()
							+ "-" + order.getCustomerPhone() + "）家的二期款系统已收款完成了，请您知晓。");
					ddMsg.setReceivePhone(bizEmp.getPhone());
					ddMsg.setMsgGenerateDatetime(DateUtils.addDate(new Date(), 0));
					ddMsg.setMsgTosendDatetime(null);
					ddMsg.setMsgSendedDatetime(null);
					ddMsg.setMsgStatus("0");
					ddMsg.setRelatedBusinessType(SendMsgBusinessType.RELATED_BUSINESS_TYPE_992000);
					ddMsg.setRelatedBusinessIdInt(orderId);
					ddMsg.setRelatedBusinessIdVarchar("");
					ddMsg.preInsert();
					bizPhoneMsgDao.insert(ddMsg);
					erqiMoney(orderId, date);
				} else if (orderFinanceCollection.getCollectionType().equals("2")) {
					weikuanMoney(orderId, date);
				}
				
			}else{
				BizOrderFinanceCollection orderFinanceCollection = new BizOrderFinanceCollection();
				orderFinanceCollection.setOrderId(bizPrePmSettleFin.getOrderId());
				orderFinanceCollection.setCollectionType(bizPrePmSettleFin.getReceiveMoneyType());
				orderFinanceCollection.setCollectionAmount(bizPrePmSettleFin.getReceiveMoneyAmount());
				orderFinanceCollection.setCollectionDatetime(bizPrePmSettleFin.getReceiveMoneyDatetime());
				orderFinanceCollection.setCollectionStatus(bizPrePmSettleFin.getCollectionStatus());
				orderFinanceCollection.preUpdate();
				bizOrderFinanceCollectionDao.update(orderFinanceCollection);
			}


			data.setSynStatus("3");
		}
		data.setBusinessData(businessData);
		data.preUpdate();
		bizSynDataDao.update(data);
	}

	public void erqiMoney(Integer orderId, Date date) {

		int settleCheckNodeCount = bizQcBillDao.checkSettleCheckNodeByOrderId(orderId);
		if (settleCheckNodeCount > 0) {

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
				List<BizPmSettleCategoryDetail> list = bizPmSettleCategoryDetailDao
						.queryCateGoryAmountByCondition(map1);

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
						summary.setCreateBy(null);
						summary.setCreateDate(date);
						summary.setUpdateBy(null);
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
				updateMap.put("updateBy", null);
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
				updateMap2.put("updateBy", null);
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
				updateMap3.put("updateBy", null);
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
						settleBill.setMidwayAuxiliaryMaterialsSettleAmount(
								bill.getMidwayAuxiliaryMaterialsSettleAmount());
						settleBill.setMidwayRewardAmount(bill.getMidwayRewardAmount());
						settleBill.setMidwayPunishAmount(bill.getMidwayPunishAmount());
						settleBill.setMidwayInspectionRewardAmount(bill.getMidwayInspectionRewardAmount());
						settleBill.setMidwayInspectionPunishAmount(bill.getMidwayInspectionPunishAmount());
						settleBill.setTotalAmount(bill.getTotalAmount());
						settleBill.setStatus(ConstantUtils.PM_SETTLE_STATUS_30);
						settleBill.setCreateBy(null);
						settleBill.setCreateDate(date);
						settleBill.setUpdateBy(null);
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

	public void weikuanMoney(Integer orderId, Date date) {
		BizOrderFinishProjectBill orderFinishbill = bizOrderFinishProjectBillDao.getByOrderID(orderId);
		if (orderFinishbill != null && (Integer.valueOf(orderFinishbill.getStatus()).intValue()>= 3)) {

			SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("orderId", orderId);
			param.put("settleRole", ConstantUtils.SETTLE_ROLE_1);
			param.put("settleBillType", ConstantUtils.PM_SETTLE_BILL_TYPE_2);
			int count = bizPmSettleBillDao.queryPmSettleBillByParam(param);
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
				List<BizPmSettleCategoryDetail> list = bizPmSettleCategoryDetailDao
						.queryCateGoryAmountByCondition(map1);
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
						summary.setCreateBy(null);
						summary.setCreateDate(date);
						summary.setUpdateBy(null);
						summary.setUpdateDate(date);
						summary.setSettleRole(ConstantUtils.SETTLE_ROLE_1);
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
				updateMap.put("updateBy", null);
				List<String> settleCategoryListUpdateMap = new ArrayList<String>();

				settleCategoryListUpdateMap.add(ConstantUtils.PM_SETTLE_CATEGORY_5);
				settleCategoryListUpdateMap.add(ConstantUtils.PM_SETTLE_CATEGORY_6);
				settleCategoryListUpdateMap.add(ConstantUtils.PM_SETTLE_CATEGORY_11);
				settleCategoryListUpdateMap.add(ConstantUtils.PM_SETTLE_CATEGORY_1002);
				settleCategoryListUpdateMap.add(ConstantUtils.PM_SETTLE_CATEGORY_1003);
				settleCategoryListUpdateMap.add(ConstantUtils.PM_SETTLE_CATEGORY_1012);
				settleCategoryListUpdateMap.add(ConstantUtils.PM_SETTLE_CATEGORY_1013);
				updateMap.put("settleCategoryList", settleCategoryListUpdateMap);
				bizPmSettleCategoryDetailDao.updateRelateSummary(updateMap);


				Map<String, Object> updateMap2 = new HashMap<String, Object>();
				updateMap2.put("orderId", orderId);
				updateMap2.put("sign", "02");
				updateMap2.put("settleCategory", ConstantUtils.PM_SETTLE_CATEGORY_4);
				updateMap2.put("settleStatus", ConstantUtils.PM_SETTLE_STATUS_20);
				updateMap2.put("newSettleStatus", ConstantUtils.PM_SETTLE_STATUS_30);
				updateMap2.put("settleRole", ConstantUtils.SETTLE_ROLE_1);
				updateMap2.put("settleStatusDatetime", date);
				updateMap2.put("updateDate", date);
				updateMap2.put("updateBy", null);
				bizPmSettleCategoryDetailDao.updateRelateSummaryCategory(updateMap2);


				Map<String, Object> updateMap3 = new HashMap<String, Object>();
				updateMap3.put("orderId", orderId);
				updateMap3.put("sign", "2");
				updateMap3.put("settleCategory", ConstantUtils.PM_SETTLE_CATEGORY_12);
				updateMap3.put("settleStatus", ConstantUtils.PM_SETTLE_STATUS_20);
				updateMap3.put("newSettleStatus", ConstantUtils.PM_SETTLE_STATUS_30);
				updateMap3.put("settleRole", ConstantUtils.SETTLE_ROLE_1);
				updateMap3.put("settleStatusDatetime", date);
				updateMap3.put("updateDate", date);
				updateMap3.put("updateBy", null);
				bizPmSettleCategoryDetailDao.updateRelateSummaryCategory(updateMap3);


				Map<String, Object> map2 = new HashMap<String, Object>();
				map2.put("orderId", orderId);
				map2.put("settleStatus", ConstantUtils.PM_SETTLE_STATUS_30);
				map2.put("settleRole", ConstantUtils.SETTLE_ROLE_1);
				List<BizPmSettleBill> billList = bizPmSettleCategorySummaryDao
						.queryCateGorySummaryLastByCondition(map2);
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
						settleBill.setCreateBy(null);
						settleBill.setCreateDate(date);
						settleBill.setUpdateBy(null);
						settleBill.setUpdateDate(date);
						settleBill.setSettleRole(ConstantUtils.SETTLE_ROLE_1);
						settleBillList.add(settleBill);
					}
					bizPmSettleBillDao.insertBatch(settleBillList);
				}


				Map<String, Object> map3 = new HashMap<String, Object>();
				map3.put("orderId", orderId);
				map3.put("settleStatus", ConstantUtils.PM_SETTLE_STATUS_30);
				map3.put("settleBillType", ConstantUtils.PM_SETTLE_BILL_TYPE_2);
				map3.put("settleRole", ConstantUtils.SETTLE_ROLE_1);
				bizPmSettleCategorySummaryDao.updateRelate(map3);

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
}
