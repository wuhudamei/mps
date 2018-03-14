/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.entity.modules;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.constantUtils.BusinessLogConstantUtil;
import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.StringUtils;
import cn.damei.dao.modules.ErqiDao;
import cn.damei.dao.modules.BizAssessRewardPunishDao;
import cn.damei.dao.modules.BizOrderFinanceCollectionDao;
import cn.damei.dao.modules.BizBusinessStatusLogDao;
import cn.damei.dao.modules.BizPrePmSettleFinDao;
import cn.damei.dao.modules.BizPmSettleBillDao;
import cn.damei.dao.modules.BizPmSettleCategoryDetailDao;
import cn.damei.dao.modules.BizPmSettleCategorySummaryDao;
import cn.damei.service.modules.BizSeiralnumService;
import cn.damei.common.utils.BizDictUtils;
import cn.damei.common.utils.UserUtils;

/**
 * 结算二期款Service
 * 
 * @author 梅浩
 * @version 2016-12-28
 */
@Service
@Transactional(readOnly = true)
public class ErqiMoneyService extends CrudService<ErqiDao, ErqiEntity> {

	@Autowired
	private BizPmSettleCategoryDetailDao bizPmSettleCategoryDetailDao;

	@Autowired
	private BizPmSettleCategorySummaryDao bizPmSettleCategorySummaryDao;

	@Autowired
	private BizSeiralnumService bizSeiralnumService;

	@Autowired
	private BizPmSettleBillDao bizPmSettleBillDao;
	
	@Autowired
	private BizBusinessStatusLogDao logDao;
	@Autowired
	private BizAssessRewardPunishDao bizAssessRewardPunishDao;

	@Autowired
	private BizOrderFinanceCollectionDao bizOrderFinanceCollectionDao;
	@Autowired
	private BizPrePmSettleFinDao bizPrePmSettleFinDao;
	
	public ErqiEntity get(String id) {
		return super.get(id);
	}

	public List<ErqiEntity> findList(ErqiEntity ErqiEntity) {
		return super.findList(ErqiEntity);
	}

	public Page<ErqiEntity> findPage(Page<ErqiEntity> page, ErqiEntity ErqiEntity) {
		return super.findPage(page, ErqiEntity);
	}

	@Transactional(readOnly = false)
	public void save(ErqiEntity ErqiEntity) {
		super.save(ErqiEntity);
	}

	@Transactional(readOnly = false)
	public void delete(ErqiEntity ErqiEntity) {
		super.delete(ErqiEntity);
	}

	/**
	 * 项目经理确认已收二期款
	 * 
	 * @param orderId
	 */
	@Transactional(readOnly = false)
	public String confirmCheckSecondMoney(Integer orderId) {
		String result = "0";
		Date date = new Date();
		User user = UserUtils.getUser();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("orderId", orderId);
		param.put("settleRole", ConstantUtils.SETTLE_ROLE_1);
		param.put("settleBillType", ConstantUtils.PM_SETTLE_BILL_TYPE_1);
		int count = bizPmSettleBillDao.queryPmSettleBillByParam(param);
		if (count > 0) {
			result = "1";
			return result;
		}
		// 1.新增结算类目汇总
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

		// 2.更新结算类目明细关联的结算类目汇总id(之前新增时该字段为空，所以更新该字段)
		// 更新标化辅材、自主支配、中期提成、中期奖励、中期扣款的结算类目明细
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

		// 更新质检罚款的结算类目明细
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

		// 更新项目经理材料结算类目明细
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

		// 3.新增结算单
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

		// 4.更新结算类目汇总关联的结算单id(之前新增时该字段为空，所以更新该字段)
		Map<String, Object> map3 = new HashMap<String, Object>();
		map3.put("orderId", orderId);
		map3.put("settleStatus", ConstantUtils.PM_SETTLE_STATUS_30);
		map3.put("settleBillType", ConstantUtils.PM_SETTLE_BILL_TYPE_1);
		map3.put("settleRole", ConstantUtils.SETTLE_ROLE_1);
		bizPmSettleCategorySummaryDao.updateRelate(map3);
		
		Map<String,Object> map4 = new HashMap<String,Object>();
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
		
		//保存订单财务二期款收款表信息
		BizOrderFinanceCollection orderFinanceCollection = new BizOrderFinanceCollection();
		BizPrePmSettleFin bizPrePmSettleFin = new BizPrePmSettleFin();
		orderFinanceCollection.setOrderId(orderId);
		orderFinanceCollection.setCollectionType("1");
		orderFinanceCollection.setCollectionStatus("40");
		bizPrePmSettleFin.setOrderId(orderId);
		bizPrePmSettleFin.setReceiveMoneyType("1");
		List<BizPrePmSettleFin> prePmSettleFinList = bizPrePmSettleFinDao.getBinPrePmByOrderIdAndType(bizPrePmSettleFin);
		if(prePmSettleFinList != null && prePmSettleFinList.size()>0){
			orderFinanceCollection.setCollectionAmount(prePmSettleFinList.get(0).getReceiveMoneyAmount());
			orderFinanceCollection.setCollectionDatetime(prePmSettleFinList.get(0).getReceiveMoneyDatetime());
		}else{
			orderFinanceCollection.setCollectionAmount(0.00);
			orderFinanceCollection.setCollectionDatetime(null);
		}
		orderFinanceCollection.setCollectionRemarks("人工手动确认已交款");
		if(user.getEmpId() != null){
			orderFinanceCollection.setCollectionOperatorEmployeeId(Integer.valueOf(user.getEmpId()));
		}
		orderFinanceCollection.setCollectionOperatorDatetime(date);
		orderFinanceCollection.preInsert();
		bizOrderFinanceCollectionDao.insert(orderFinanceCollection);
		
		//保存财务确认二期款时间
		BizBusinessStatusLog bizBusinessStatusLog = new BizBusinessStatusLog();
		bizBusinessStatusLog.setBusinessType(BusinessLogConstantUtil.BUSINESS_TYPE_304);
		bizBusinessStatusLog.setBusinessOnlyMarkInt(Integer.valueOf(orderId));
		bizBusinessStatusLog.setBusinessStatus(ConstantUtils.PM_SETTLE_STATUS_30);
		bizBusinessStatusLog.setStatusDatetime(new Date());
		if (StringUtils.isNotBlank(UserUtils.getUser().getEmpId())){
			bizBusinessStatusLog.setBusinessEmployeeId(Integer.valueOf(UserUtils.getUser().getEmpId()));
		}
		bizBusinessStatusLog.setStatusDatetime(new Date());
		bizBusinessStatusLog.preInsert();
		logDao.insert(bizBusinessStatusLog);
		return result;
	}

	/**
	 * 批量项目经理确认已收二期款
	 * 
	 * @param orderId
	 */
	@Transactional(readOnly = false)
	public String confirmCheckSecondMoneys(List<Integer> orderIds) {
		Date date = new Date();
		String result = "0";
		for (Integer orderId : orderIds) {

			User user = UserUtils.getUser();
			SimpleDateFormat format = new SimpleDateFormat("yyyyMM");

			Map<String, Object> param = new HashMap<String, Object>();
			param.put("orderId", orderId);
			param.put("settleRole", ConstantUtils.SETTLE_ROLE_1);
			param.put("settleBillType", ConstantUtils.PM_SETTLE_BILL_TYPE_1);
			int count = bizPmSettleBillDao.queryPmSettleBillByParam(param);
			if (count > 0) {
				result = "1";
				return result;
			}
			
			// 1.新增结算类目汇总
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

			// 2.更新结算类目明细关联的结算类目汇总id(之前新增时该字段为空，所以更新该字段)
			// 更新标化辅材、自主支配、中期提成的结算类目明细
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

			// 更新质检罚款的结算类目明细
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

			// 更新项目经理材料结算类目明细
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

			// 3.新增结算单
			Map<String, Object> map2 = new HashMap<String, Object>();
			map2.put("orderId", orderId);
			map2.put("settleStatus", ConstantUtils.PM_SETTLE_STATUS_30);
			/*
			 * map2.put("settleCategory", ConstantUtils.PM_SETTLE_CATEGORY_401);
			 */
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

			// 4.更新结算类目汇总关联的结算单id(之前新增时该字段为空，所以更新该字段)
			Map<String, Object> map3 = new HashMap<String, Object>();
			map3.put("orderId", orderId);
			map3.put("settleStatus", ConstantUtils.PM_SETTLE_STATUS_30);
			map3.put("settleBillType", ConstantUtils.PM_SETTLE_BILL_TYPE_1);
			map3.put("settleRole", ConstantUtils.SETTLE_ROLE_1);
			bizPmSettleCategorySummaryDao.updateRelate(map3);
			
			Map<String,Object> map4 = new HashMap<String,Object>();
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
			
			//保存订单财务二期款收款表信息
			BizOrderFinanceCollection orderFinanceCollection = new BizOrderFinanceCollection();
			BizPrePmSettleFin bizPrePmSettleFin = new BizPrePmSettleFin();
			orderFinanceCollection.setOrderId(orderId);
			orderFinanceCollection.setCollectionType("1");
			orderFinanceCollection.setCollectionStatus("40");
			bizPrePmSettleFin.setOrderId(orderId);
			bizPrePmSettleFin.setReceiveMoneyType("1");
			List<BizPrePmSettleFin> prePmSettleFinList = bizPrePmSettleFinDao.getBinPrePmByOrderIdAndType(bizPrePmSettleFin);
			if(prePmSettleFinList != null && prePmSettleFinList.size()>0){
				orderFinanceCollection.setCollectionAmount(prePmSettleFinList.get(0).getReceiveMoneyAmount());
				orderFinanceCollection.setCollectionDatetime(prePmSettleFinList.get(0).getReceiveMoneyDatetime());
			}else{
				orderFinanceCollection.setCollectionAmount(0.00);
				orderFinanceCollection.setCollectionDatetime(null);
			}
			orderFinanceCollection.setCollectionRemarks("人工手动确认已交款");
			if(user.getEmpId() != null){
				orderFinanceCollection.setCollectionOperatorEmployeeId(Integer.valueOf(user.getEmpId()));
			}
			orderFinanceCollection.setCollectionOperatorDatetime(date);
			orderFinanceCollection.preInsert();
			bizOrderFinanceCollectionDao.insert(orderFinanceCollection);
			
			//保存财务确认二期款时间
			BizBusinessStatusLog bizBusinessStatusLog = new BizBusinessStatusLog();
			bizBusinessStatusLog.setBusinessType(BusinessLogConstantUtil.BUSINESS_TYPE_304);
			bizBusinessStatusLog.setBusinessOnlyMarkInt(Integer.valueOf(orderId));
			bizBusinessStatusLog.setBusinessStatus(ConstantUtils.PM_SETTLE_STATUS_30);
			bizBusinessStatusLog.setStatusDatetime(new Date());
			if (StringUtils.isNotBlank(UserUtils.getUser().getEmpId())){
				bizBusinessStatusLog.setBusinessEmployeeId(Integer.valueOf(UserUtils.getUser().getEmpId()));
			}
			bizBusinessStatusLog.setStatusDatetime(new Date());
			bizBusinessStatusLog.preInsert();
			logDao.insert(bizBusinessStatusLog);
		}
		return result;
	}

	/**
	 * 批量质检员确认已收二期款
	 * 
	 * @param orderId
	 */
	@Transactional(readOnly = false)
	public void confirmCheckSecondMoneyPqcs(List<Integer> orderIds) {
		try {
			Date date = new Date();

			for (Integer orderId : orderIds) {

				User user = UserUtils.getUser();
				SimpleDateFormat format = new SimpleDateFormat("yyyyMM");

				// 1.新增结算类目汇总
				Map<String, Object> map1 = new HashMap<String, Object>();
				map1.put("orderId", orderId);
				map1.put("settleStatus", ConstantUtils.PM_SETTLE_STATUS_20);
				map1.put("settleRole", ConstantUtils.SETTLE_ROLE_2);
				List<String> settleCategoryListMap = new ArrayList<String>();
				settleCategoryListMap.add(ConstantUtils.PM_SETTLE_CATEGORY_7);
				settleCategoryListMap.add(ConstantUtils.PM_SETTLE_CATEGORY_9);
				map1.put("settleCategoryList", settleCategoryListMap);
				List<BizPmSettleCategoryDetail> list = bizPmSettleCategoryDetailDao
						.queryCateGoryAmountByCondition(map1);
				if (CollectionUtils.isNotEmpty(list)) {
					List<BizPmSettleCategorySummary> summaryList = new ArrayList<BizPmSettleCategorySummary>();
					for (BizPmSettleCategoryDetail detail : list) {
						BizPmSettleCategorySummary summary = new BizPmSettleCategorySummary();
						summary.setOrderId(orderId);
						summary.setPmEmployeeId(detail.getPmEmployeeId());
						summary.setSettleCategory(detail.getSettleCategory());
						summary.setSettleAmount(detail.getSettleAmount());
						summary.setSettleStatus(ConstantUtils.PM_SETTLE_STATUS_30);
						summary.setSettleStatusDatetime(date);
						summary.setSettleRole(ConstantUtils.SETTLE_ROLE_2);
						summary.setCreateBy(user);
						summary.setCreateDate(date);
						summary.setUpdateBy(user);
						summary.setUpdateDate(date);
						summaryList.add(summary);
					}
					bizPmSettleCategorySummaryDao.insertBatch(summaryList);
				}

				// 2.更新结算类目明细关联的结算类目汇总id
				Map<String, Object> updateMap = new HashMap<String, Object>();
				updateMap.put("orderId", orderId);
				List<String> settleCategoryList = new ArrayList<String>();
				settleCategoryList.add(ConstantUtils.PM_SETTLE_CATEGORY_7);
				settleCategoryList.add(ConstantUtils.PM_SETTLE_CATEGORY_9);
				updateMap.put("settleCategoryList", settleCategoryList);
				updateMap.put("settleStatus", ConstantUtils.PM_SETTLE_STATUS_20);
				updateMap.put("newSettleStatus", ConstantUtils.PM_SETTLE_STATUS_30);
				updateMap.put("settleRole", ConstantUtils.SETTLE_ROLE_2);
				updateMap.put("settleStatusDatetime", date);
				updateMap.put("updateDate", date);
				updateMap.put("updateBy", user);
				bizPmSettleCategoryDetailDao.updateRelateSummaryPbc(updateMap);

				// 3.新增结算单
				Map<String, Object> map2 = new HashMap<String, Object>();
				map2.put("orderId", orderId);
				map2.put("settleStatus", ConstantUtils.PM_SETTLE_STATUS_30);
				List<String> categoryList = new ArrayList<String>();
				categoryList.add(ConstantUtils.PM_SETTLE_CATEGORY_7);
				categoryList.add(ConstantUtils.PM_SETTLE_CATEGORY_9);
				map2.put("settleCategoryList", categoryList);
				map2.put("settleRole", ConstantUtils.SETTLE_ROLE_2);
				List<BizPmSettleBill> billList = bizPmSettleCategorySummaryDao
						.queryCateGorySummaryMidByConditionPbc(map2);
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
						settleBill.setQcMidwayCommissionAmount(bill.getQcMidwayCommissionAmount());
						settleBill.setQcMidwayLongwayAmount(bill.getQcMidwayLongwayAmount());
						settleBill.setTotalAmount(bill.getTotalAmount());
						settleBill.setStatus(ConstantUtils.PM_SETTLE_STATUS_30);
						settleBill.setCreateBy(user);
						settleBill.setCreateDate(date);
						settleBill.setUpdateBy(user);
						settleBill.setUpdateDate(date);
						settleBill.setSettleRole(ConstantUtils.SETTLE_ROLE_2);
						settleBillList.add(settleBill);
					}
					bizPmSettleBillDao.insertBatch(settleBillList);
				}

				// 4.更新结算类目汇总关联的结算单id(之前新增时该字段为空，所以更新该字段)
				Map<String, Object> map3 = new HashMap<String, Object>();
				map3.put("orderId", orderId);
				map3.put("settleStatus", ConstantUtils.PM_SETTLE_STATUS_30);
				map3.put("settleBillType", ConstantUtils.PM_SETTLE_BILL_TYPE_1);
				map3.put("settleRole", ConstantUtils.SETTLE_ROLE_2);
				bizPmSettleCategorySummaryDao.updateRelate(map3);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 质检员确认已收二期款
	 * 
	 * @param orderId
	 */
	@Transactional(readOnly = false)
	public void confirmCheckSecondMoneyPqc(Integer orderId) {
		try {
			Date date = new Date();
			User user = UserUtils.getUser();
			SimpleDateFormat format = new SimpleDateFormat("yyyyMM");

			// 1.新增结算类目汇总
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("orderId", orderId);
			map1.put("settleStatus", ConstantUtils.PM_SETTLE_STATUS_20);
			map1.put("settleRole", ConstantUtils.SETTLE_ROLE_2);
			List<String> settleCategoryListMap = new ArrayList<String>();
			settleCategoryListMap.add(ConstantUtils.PM_SETTLE_CATEGORY_7);
			settleCategoryListMap.add(ConstantUtils.PM_SETTLE_CATEGORY_9);
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
					summary.setSettleStatus(ConstantUtils.PM_SETTLE_STATUS_30);
					summary.setSettleStatusDatetime(date);
					summary.setSettleRole(ConstantUtils.SETTLE_ROLE_2);
					summary.setCreateBy(user);
					summary.setCreateDate(date);
					summary.setUpdateBy(user);
					summary.setUpdateDate(date);
					summaryList.add(summary);
				}
				bizPmSettleCategorySummaryDao.insertBatch(summaryList);
			}

			// 2.更新结算类目明细关联的结算类目汇总id
			Map<String, Object> updateMap = new HashMap<String, Object>();
			updateMap.put("orderId", orderId);
			List<String> settleCategoryList = new ArrayList<String>();
			settleCategoryList.add(ConstantUtils.PM_SETTLE_CATEGORY_7);
			settleCategoryList.add(ConstantUtils.PM_SETTLE_CATEGORY_9);
			updateMap.put("settleCategoryList", settleCategoryList);
			updateMap.put("settleStatus", ConstantUtils.PM_SETTLE_STATUS_20);
			updateMap.put("newSettleStatus", ConstantUtils.PM_SETTLE_STATUS_30);
			updateMap.put("settleRole", ConstantUtils.SETTLE_ROLE_2);
			updateMap.put("settleStatusDatetime", date);
			updateMap.put("updateDate", date);
			updateMap.put("updateBy", user);
			bizPmSettleCategoryDetailDao.updateRelateSummaryPbc(updateMap);

			// 3.新增结算单
			Map<String, Object> map2 = new HashMap<String, Object>();
			map2.put("orderId", orderId);
			map2.put("settleStatus", ConstantUtils.PM_SETTLE_STATUS_30);
			List<String> categoryList = new ArrayList<String>();
			categoryList.add(ConstantUtils.PM_SETTLE_CATEGORY_7);
			categoryList.add(ConstantUtils.PM_SETTLE_CATEGORY_9);
			map2.put("settleCategoryList", categoryList);
			map2.put("settleRole", ConstantUtils.SETTLE_ROLE_2);
			List<BizPmSettleBill> billList = bizPmSettleCategorySummaryDao.queryCateGorySummaryMidByConditionPbc(map2);
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
					settleBill.setQcMidwayCommissionAmount(bill.getQcMidwayCommissionAmount());
					settleBill.setQcMidwayLongwayAmount(bill.getQcMidwayLongwayAmount());
					settleBill.setTotalAmount(bill.getTotalAmount());
					settleBill.setStatus(ConstantUtils.PM_SETTLE_STATUS_30);
					settleBill.setCreateBy(user);
					settleBill.setCreateDate(date);
					settleBill.setUpdateBy(user);
					settleBill.setUpdateDate(date);
					settleBill.setSettleRole(ConstantUtils.SETTLE_ROLE_2);
					settleBillList.add(settleBill);
				}
				bizPmSettleBillDao.insertBatch(settleBillList);
			}

			// 4.更新结算类目汇总关联的结算单id(之前新增时该字段为空，所以更新该字段)
			Map<String, Object> map3 = new HashMap<String, Object>();
			map3.put("orderId", orderId);
			map3.put("settleStatus", ConstantUtils.PM_SETTLE_STATUS_30);
			map3.put("settleBillType", ConstantUtils.PM_SETTLE_BILL_TYPE_1);
			map3.put("settleRole", ConstantUtils.SETTLE_ROLE_2);
			bizPmSettleCategorySummaryDao.updateRelate(map3);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public HSSFWorkbook exportExcel(ErqiEntity erqiEntity) {

		List<ErqiEntity> erqiEntityList = dao.findList(erqiEntity);
		// 时分秒
		SimpleDateFormat f2 = new SimpleDateFormat("yyyy-MM-dd");

		HSSFWorkbook wb = new HSSFWorkbook();// 创建一个Excel文件
		HSSFSheet sheet = wb.createSheet("二期款信息");// 创建一个Excel的Sheet

		// 设置字体
		HSSFFont font = wb.createFont();
		font.setColor(HSSFFont.COLOR_NORMAL);// 字体颜色
		font.setFontName("黑体");// 字体
		font.setFontHeightInPoints((short) 10);// 字体高度
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 宽度

		// 单元格样式--标题
		HSSFCellStyle columnHeadStyle = wb.createCellStyle();
		columnHeadStyle.setFont(font);
		columnHeadStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
		columnHeadStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
		columnHeadStyle.setLocked(true);
		columnHeadStyle.setWrapText(true);
		columnHeadStyle.setLeftBorderColor(HSSFColor.BLACK.index);// 左边框的颜色
		columnHeadStyle.setBorderLeft((short) 1);// 边框的大小
		columnHeadStyle.setRightBorderColor(HSSFColor.BLACK.index);// 右边框的颜色
		columnHeadStyle.setBorderRight((short) 1);// 边框的大小
		columnHeadStyle.setTopBorderColor(HSSFColor.BLACK.index);// 上边框的颜色
		columnHeadStyle.setBorderTop((short) 1);// 边框的大小
		columnHeadStyle.setBottomBorderColor(HSSFColor.BLACK.index);// 下边框的颜色
		columnHeadStyle.setBorderBottom((short) 1);// 边框的大小
		columnHeadStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框为粗体
		columnHeadStyle.setBottomBorderColor(HSSFColor.BLACK.index); // 设置单元格的边框颜色
		columnHeadStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);// 设置单元格的背景颜色（单元格的样式会覆盖列或行的样式）
		columnHeadStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		// 单元格样式
		HSSFCellStyle columnStyle = wb.createCellStyle();
		columnStyle.setLeftBorderColor(HSSFColor.BLACK.index); // 左边框线的颜色
		columnStyle.setBorderLeft((short) 1);// 左边框线的大小
		columnStyle.setRightBorderColor(HSSFColor.BLACK.index); // 右边框线的颜色
		columnStyle.setBorderRight((short) 1);// 右边框线的大小
		columnStyle.setTopBorderColor(HSSFColor.BLACK.index); // 上边框线的颜色
		columnStyle.setBorderTop((short) 1);// 上边框线的大小
		columnStyle.setBottomBorderColor(HSSFColor.BLACK.index); // 下边框线的颜色
		columnStyle.setBorderBottom((short) 1);// 下边框线的大小
		columnStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		// 单元格宽度
		sheet.setColumnWidth(0, 4500);
		sheet.setColumnWidth(1, 4500);
		sheet.setColumnWidth(2, 4500);
		sheet.setColumnWidth(3, 4500);
		sheet.setColumnWidth(4, 5500);
		sheet.setColumnWidth(5, 4500);
		sheet.setColumnWidth(6, 4500);
		sheet.setColumnWidth(7, 4500);
		sheet.setColumnWidth(8, 4500);
		sheet.setColumnWidth(9, 4500);

		// 标题---订单信息
		HSSFRow rowTitle = sheet.createRow(0);
		rowTitle.setHeightInPoints(30);
		HSSFCell cell = rowTitle.createCell(0);
		cell.setCellStyle(columnHeadStyle);
		cell.setCellValue(new HSSFRichTextString("确认二期款"));
		for (int i = 0; i < 10; i++) {
			HSSFCell cella = rowTitle.createCell(i + 1);
			cella.setCellStyle(columnHeadStyle);
		}
		// 合并单元格--开始行，结束行，开始列，结束列
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 10));//

		// 标题
		HSSFRow rowTitle2 = sheet.createRow(1);

		HSSFCell headCell0 = rowTitle2.createCell(0);
		headCell0.setCellStyle(columnHeadStyle);
		headCell0.setCellValue("序号");

		HSSFCell headCell1 = rowTitle2.createCell(1);
		headCell1.setCellStyle(columnHeadStyle);
		headCell1.setCellValue("门店");

		HSSFCell headCell2 = rowTitle2.createCell(2);
		headCell2.setCellStyle(columnHeadStyle);
		headCell2.setCellValue("订单号");

		HSSFCell headCell3 = rowTitle2.createCell(3);
		headCell3.setCellStyle(columnHeadStyle);
		headCell3.setCellValue("客户姓名");

		HSSFCell headCell4 = rowTitle2.createCell(4);
		headCell4.setCellStyle(columnHeadStyle);
		headCell4.setCellValue("客户地址");

		HSSFCell headCell5 = rowTitle2.createCell(5);
		headCell5.setCellStyle(columnHeadStyle);
		headCell5.setCellValue("项目经理");

		HSSFCell headCell6 = rowTitle2.createCell(6);
		headCell6.setCellStyle(columnHeadStyle);
		headCell6.setCellValue("交款金额");

		HSSFCell headCell7 = rowTitle2.createCell(7);
		headCell7.setCellStyle(columnHeadStyle);
		headCell7.setCellValue("交款时间");

		HSSFCell headCell8 = rowTitle2.createCell(8);
		headCell8.setCellStyle(columnHeadStyle);
		headCell8.setCellValue("申请约检日期");

		HSSFCell headCell9 = rowTitle2.createCell(9);
		headCell9.setCellStyle(columnHeadStyle);
		headCell9.setCellValue("质检员");

		HSSFCell headCell10 = rowTitle2.createCell(10);
		headCell10.setCellStyle(columnHeadStyle);
		headCell10.setCellValue("质检验收日期");

		// 数据
		for (int i = 0; i < erqiEntityList.size(); i++) {

			ErqiEntity erqiEntitys = erqiEntityList.get(i);
			HSSFRow row = sheet.createRow(i + 2);

			HSSFCell cell0 = row.createCell(0);
			cell0.setCellStyle(columnStyle);
			cell0.setCellValue(i + 1);

			HSSFCell cell1 = row.createCell(1);
			cell1.setCellStyle(columnStyle);
			if (erqiEntitys.getStoreId() != null) {
				cell1.setCellValue(BizDictUtils.getStoreLabel(erqiEntitys.getStoreId() + "", ""));
			} 

			HSSFCell cell2 = row.createCell(2);
			cell2.setCellStyle(columnStyle);
			cell2.setCellValue(erqiEntitys.getOrderNumber());

			HSSFCell cell3 = row.createCell(3);
			cell3.setCellStyle(columnStyle);
			cell3.setCellValue(erqiEntitys.getCustomerName());

			HSSFCell cell4 = row.createCell(4);
			cell4.setCellStyle(columnStyle);
			cell4.setCellValue(erqiEntitys.getCommunityName() + "-" + erqiEntitys.getBuildNumber() + "-"
					+ erqiEntitys.getBuildUnit() + "-" + erqiEntitys.getBuildRoom());

			HSSFCell cell5 = row.createCell(5);
			cell5.setCellStyle(columnStyle);
			if (erqiEntitys.getItemManagerName() != null) {
				cell5.setCellValue(erqiEntitys.getItemManagerName());
			} else {
				cell5.setCellValue("");
			}

			HSSFCell cell6 = row.createCell(6);
			cell6.setCellStyle(columnStyle);
			if (erqiEntitys.getPaymentAmount() != null) {
				cell6.setCellValue(erqiEntitys.getPaymentAmount());
			} else {
				cell6.setCellValue(0);
			}

			HSSFCell cell7 = row.createCell(7);
			cell7.setCellStyle(columnStyle);
			if (erqiEntitys.getPaymentDate() != null) {
				String paymentDate = f2.format(erqiEntitys.getPaymentDate());
				cell7.setCellValue(paymentDate);
			} else {
				cell7.setCellValue("");
			}

			HSSFCell cell8 = row.createCell(8);
			cell8.setCellStyle(columnStyle);

			if (erqiEntitys.getApplyCheckDate() != null) {
				String applyCheckDate = f2.format(erqiEntitys.getApplyCheckDate());
				cell8.setCellValue(applyCheckDate);
			} else {
				cell8.setCellValue("");
			}

			HSSFCell cell9 = row.createCell(9);
			cell9.setCellStyle(columnStyle);
			if (erqiEntitys.getInspectName() != null) {
				cell9.setCellValue(erqiEntitys.getInspectName());
			} else {
				cell9.setCellValue("");
			}

			HSSFCell cell10 = row.createCell(10);
			cell10.setCellStyle(columnStyle);
			if (erqiEntitys.getYanshouDate() != null) {
				String yanshouDate = f2.format(erqiEntitys.getYanshouDate());
				cell10.setCellValue(yanshouDate);
			} else {
				cell10.setCellValue("");
			}

		}
		return wb;
	}
}