
package cn.damei.service.modules;

import java.util.*;
import cn.damei.common.service.CrudService2;
import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.StringUtils;
import cn.damei.dao.modules.BizPmSettleCategoryDetailDao;
import cn.damei.dao.modules.BizPmSettleCategorySummaryDao;
import cn.damei.dao.modules.BizPmSettleSummaryBillDao;
import cn.damei.entity.modules.BizPmSettleSummaryBill;
import cn.damei.entity.modules.User;
import cn.damei.common.utils.BizDictUtils;
import cn.damei.common.utils.UserUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.constantUtils.BusinessLogConstantUtil;
import cn.damei.common.persistence.Page;
import cn.damei.entity.modules.BizMaterialSelfbuyVo;
import cn.damei.entity.modules.BizPmSettleBill;
import cn.damei.entity.modules.InspectorPunish;
import cn.damei.entity.modules.Ownpay;
import cn.damei.dao.modules.BizBusinessStatusLogDao;
import cn.damei.entity.modules.BizBusinessStatusLog;
import cn.damei.dao.modules.BizPmSettleBillDao;


@Service
@Transactional(readOnly = true)
public class BizPmSettleBillService extends CrudService2<BizPmSettleBillDao, BizPmSettleBill> {

	@Autowired
	private BizSeiralnumService bizSeiralnumService;

	@Autowired
	private BizPmSettleSummaryBillDao bizPmSettleSummaryBillDao;

	@Autowired
	private BizPmSettleCategoryDetailDao bizPmSettleCategoryDetailDao;

	@Autowired
	private BizPmSettleCategorySummaryDao bizPmSettleCategorySummaryDao;
	
	@Autowired
	private BizBusinessStatusLogDao logDao;

	public BizPmSettleBill get(Integer id) {
		return super.get(id);
	}

	public List<BizPmSettleBill> findList(BizPmSettleBill bizPmSettleBill) {
		return super.findList(bizPmSettleBill);
	}

	public Page<BizPmSettleBill> findPage(Page<BizPmSettleBill> page, BizPmSettleBill bizPmSettleBill) {
		return super.findPage(page, bizPmSettleBill);
	}

	@Transactional(readOnly = false)
	public void save(BizPmSettleBill bizPmSettleBill) {
		super.save(bizPmSettleBill);
	}

	@Transactional(readOnly = false)
	public void delete(BizPmSettleBill bizPmSettleBill) {
		super.delete(bizPmSettleBill);
	}

	public Page<BizPmSettleBill> findPmSettleBillList(Page<BizPmSettleBill> page, BizPmSettleBill entity) {
		entity.setPage(page);
		page.setList(dao.findPmSettleBillList(entity));
		return page;
	}

	public List<BizPmSettleBill> findPmSettleBillList(BizPmSettleBill entity){
		return dao.findPmSettleBillList(entity);
	}

	public Page<BizPmSettleBill> findPmCommissionSettle(Page<BizPmSettleBill> page, BizPmSettleBill entity) {
		entity.setPage(page);
		page.setList(dao.findPmCommissionSettle(entity));
		return page;
	}

	public Page<BizPmSettleBill> findPmSettleBillListPbc(Page<BizPmSettleBill> page, BizPmSettleBill entity) {
		entity.setPage(page);
		page.setList(dao.findPmSettleBillListPbc(entity));
		return page;
	}

	public Page<BizPmSettleBill> findSettleBillList(Page<BizPmSettleBill> page, BizPmSettleBill entity) {
		entity.setStatus(ConstantUtils.PM_SETTLE_STATUS_50);
		entity.setSettleRole(ConstantUtils.SETTLE_ROLE_1);
		entity.setPage(page);
		page.setList(dao.findSettleBillList(entity));
		return page;
	}

	public Page<BizPmSettleBill> findSettleBillListPbc(Page<BizPmSettleBill> page, BizPmSettleBill entity) {
		entity.setStatus(ConstantUtils.PM_SETTLE_STATUS_50);
		entity.setSettleRole(ConstantUtils.SETTLE_ROLE_2);
		entity.setPage(page);
		page.setList(dao.findSettleBillListPbc(entity));
		return page;
	}

	public Integer queryCountByCondition(Integer storeId, String settleMonth, String settleRole) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", ConstantUtils.PM_SETTLE_STATUS_50);
		map.put("storeId", storeId);
		map.put("settleMonth", settleMonth);
		map.put("settleRole", settleRole);
		return dao.queryCountByCondition(map);
	}

	public int queryPmSettleBillByParam(Map<String, Object> param) {
		return dao.queryPmSettleBillByParam(param);
	}

	public Integer queryBillCountByCondition(Integer storeId, String settleRole) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", ConstantUtils.PM_SETTLE_STATUS_30);
		map.put("storeId", storeId);
		map.put("settleRole", settleRole);
		return dao.queryBillCountByCondition(map);
	}


	@Transactional(readOnly = false)
	public void createSettleSummaryBill(Integer storeId, String settleMonth,String orderIds) {

			Date date = new Date();
			User user = UserUtils.getUser();


			Map<String, Object> map = new HashMap<String, Object>();
			map.put("status", ConstantUtils.PM_SETTLE_STATUS_30);
			map.put("storeId", storeId);
			map.put("settleRole", ConstantUtils.SETTLE_ROLE_1);
			List<String> list = new ArrayList<String>();
			String[] orderIdArr = orderIds.split(",");
			for(String orderId : orderIdArr){
				list.add(orderId);
			}
			map.put("list",list);
			List<BizPmSettleSummaryBill> summaryBillList = dao.queryPmSettleBill(map);
			List<BizPmSettleBill> billList = new ArrayList<BizPmSettleBill>();
			for (BizPmSettleSummaryBill summaryBill : summaryBillList) {
				summaryBill.setPmSettleSummaryBillCode(bizSeiralnumService.getDateSequence("YJ"));
				summaryBill.setSettleMonth(settleMonth);
				summaryBill.setStatus(ConstantUtils.PM_SETTLE_STATUS_50);
				summaryBill.setCreateBy(user);
				summaryBill.setCreateDate(date);
				summaryBill.setUpdateDate(date);
				summaryBill.setUpdateBy(user);
				summaryBill.setSettleRole(ConstantUtils.SETTLE_ROLE_1);
				bizPmSettleSummaryBillDao.insert(summaryBill);
				String[] ids = summaryBill.getSettleBillIds().split(",");
				for (String id : ids) {
					BizPmSettleBill bill = new BizPmSettleBill();
					bill.setId(Integer.parseInt(id));
					bill.setPmSettleSummaryBillId(summaryBill.getId());
					billList.add(bill);
				}
				

				BizBusinessStatusLog bizBusinessStatusLog = new BizBusinessStatusLog();
				bizBusinessStatusLog.setBusinessType(BusinessLogConstantUtil.BUSINESS_TYPE_305);
				bizBusinessStatusLog.setBusinessOnlyMarkInt(Integer.valueOf(summaryBill.getId()));
				bizBusinessStatusLog.setBusinessStatus(ConstantUtils.PM_SETTLE_STATUS_50);
				bizBusinessStatusLog.setStatusDatetime(new Date());
				if (StringUtils.isNotBlank(UserUtils.getUser().getEmpId())){
					bizBusinessStatusLog.setBusinessEmployeeId(Integer.valueOf(UserUtils.getUser().getEmpId()));
				}
				bizBusinessStatusLog.setStatusDatetime(new Date());
				bizBusinessStatusLog.preInsert();
				logDao.insert(bizBusinessStatusLog);
			}



			dao.updateBatchByRelate(billList);


			BizPmSettleBill settleBill = new BizPmSettleBill();
			settleBill.setNewStatus(ConstantUtils.PM_SETTLE_STATUS_50);
			settleBill.setSettleDatetime(date);
			settleBill.setUpdateBy(user);
			settleBill.setUpdateDate(date);
			settleBill.setStoreId(storeId);
			settleBill.setStatus(ConstantUtils.PM_SETTLE_STATUS_30);
			settleBill.setSettleRole(ConstantUtils.SETTLE_ROLE_1);
			settleBill.setList(list);
			bizPmSettleCategoryDetailDao.updateStatus(settleBill);


			BizPmSettleBill settle = new BizPmSettleBill();
			settle.setNewStatus(ConstantUtils.PM_SETTLE_STATUS_50);
			settle.setSettleDatetime(date);
			settle.setUpdateBy(user);
			settle.setUpdateDate(date);
			settle.setStoreId(storeId);
			settle.setStatus(ConstantUtils.PM_SETTLE_STATUS_30);
			settle.setSettleRole(ConstantUtils.SETTLE_ROLE_1);
		    settle.setList(list);
			bizPmSettleCategorySummaryDao.updateStatus(settle);


			BizPmSettleBill bill = new BizPmSettleBill();
			bill.setNewStatus(ConstantUtils.PM_SETTLE_STATUS_50);
			bill.setUpdateBy(user);
			bill.setUpdateDate(date);
			bill.setStoreId(storeId);
			bill.setSettleMonth(settleMonth);
			bill.setStatus(ConstantUtils.PM_SETTLE_STATUS_30);
			bill.setSettleRole(ConstantUtils.SETTLE_ROLE_1);
		    bill.setList(list);
			dao.updateBatchByCondition(bill);
			
			

	}


	@Transactional(readOnly = false)
	public void createSettleSummaryBillPbc(Integer storeId, String settleMonth) {
		try {
			Date date = new Date();
			User user = UserUtils.getUser();


			Map<String, Object> map = new HashMap<String, Object>();
			map.put("status", ConstantUtils.PM_SETTLE_STATUS_30);
			map.put("storeId", storeId);
			map.put("settleRole", ConstantUtils.SETTLE_ROLE_2);
			List<BizPmSettleSummaryBill> summaryBillList = dao.queryPmSettleBillPbc(map);
			List<BizPmSettleBill> billList = new ArrayList<BizPmSettleBill>();
			for (BizPmSettleSummaryBill summaryBill : summaryBillList) {
				summaryBill.setPmSettleSummaryBillCode(bizSeiralnumService.getDateSequence("YJ"));
				summaryBill.setSettleMonth(settleMonth);
				summaryBill.setStatus(ConstantUtils.PM_SETTLE_STATUS_50);
				summaryBill.setCreateBy(user);
				summaryBill.setCreateDate(date);
				summaryBill.setUpdateDate(date);
				summaryBill.setUpdateBy(user);
				summaryBill.setSettleRole(ConstantUtils.SETTLE_ROLE_2);
				bizPmSettleSummaryBillDao.insert(summaryBill);
				String[] ids = summaryBill.getSettleBillIds().split(",");
				for (String id : ids) {
					BizPmSettleBill bill = new BizPmSettleBill();
					bill.setId(Integer.parseInt(id));
					bill.setPmSettleSummaryBillId(summaryBill.getId());
					billList.add(bill);
				}
			}


			dao.updateBatchByRelate(billList);


			BizPmSettleBill settleBill = new BizPmSettleBill();
			settleBill.setNewStatus(ConstantUtils.PM_SETTLE_STATUS_50);
			settleBill.setSettleDatetime(date);
			settleBill.setUpdateBy(user);
			settleBill.setUpdateDate(date);
			settleBill.setStoreId(storeId);
			settleBill.setStatus(ConstantUtils.PM_SETTLE_STATUS_30);
			settleBill.setSettleRole(ConstantUtils.SETTLE_ROLE_2);
			bizPmSettleCategoryDetailDao.updateStatus(settleBill);


			BizPmSettleBill settle = new BizPmSettleBill();
			settle.setNewStatus(ConstantUtils.PM_SETTLE_STATUS_50);
			settle.setSettleDatetime(date);
			settle.setUpdateBy(user);
			settle.setUpdateDate(date);
			settle.setStoreId(storeId);
			settle.setStatus(ConstantUtils.PM_SETTLE_STATUS_30);
			settle.setSettleRole(ConstantUtils.SETTLE_ROLE_2);
			bizPmSettleCategorySummaryDao.updateStatus(settle);


			BizPmSettleBill bill = new BizPmSettleBill();
			bill.setNewStatus(ConstantUtils.PM_SETTLE_STATUS_50);
			bill.setUpdateBy(user);
			bill.setUpdateDate(date);
			bill.setStoreId(storeId);
			bill.setSettleMonth(settleMonth);
			bill.setStatus(ConstantUtils.PM_SETTLE_STATUS_30);
			bill.setSettleRole(ConstantUtils.SETTLE_ROLE_2);
			dao.updateBatchByCondition(bill);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public HSSFWorkbook exportExcel(BizPmSettleBill bill) {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("订单月度工程结算单");


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
		sheet.setColumnWidth(25, 3000);
		sheet.setColumnWidth(26, 3000);

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


		HSSFRow rowTitle = sheet.createRow(0);
		HSSFCell headCell0 = rowTitle.createCell(0);
		headCell0.setCellStyle(headStyle);
		headCell0.setCellValue("序号");

		HSSFCell headCell1 = rowTitle.createCell(1);
		headCell1.setCellStyle(headStyle);
		headCell1.setCellValue("门店");

		HSSFCell headCell2 = rowTitle.createCell(2);
		headCell2.setCellStyle(headStyle);
		headCell2.setCellValue("区域");

		HSSFCell headCell3 = rowTitle.createCell(3);
		headCell3.setCellStyle(headStyle);
		headCell3.setCellValue("月度");

		HSSFCell headCell4 = rowTitle.createCell(4);
		headCell4.setCellStyle(headStyle);
		headCell4.setCellValue("客户姓名");

		HSSFCell headCell5 = rowTitle.createCell(5);
		headCell5.setCellStyle(headStyle);
		headCell5.setCellValue("电话");

		HSSFCell headCell6 = rowTitle.createCell(6);
		headCell6.setCellStyle(headStyle);
		headCell6.setCellValue("地址");

		HSSFCell headCell7 = rowTitle.createCell(7);
		headCell7.setCellStyle(headStyle);
		headCell7.setCellValue("项目经理");

		HSSFCell headCell8 = rowTitle.createCell(8);
		headCell8.setCellStyle(headStyle);
		headCell8.setCellValue("中期提成");

		HSSFCell headCell9 = rowTitle.createCell(9);
		headCell9.setCellStyle(headStyle);
		headCell9.setCellValue("竣工提成");

		HSSFCell headCell10 = rowTitle.createCell(10);
		headCell10.setCellStyle(headStyle);
		headCell10.setCellValue("自主支配");

		HSSFCell headCell11 = rowTitle.createCell(11);
		headCell11.setCellStyle(headStyle);
		headCell11.setCellValue("标化辅料");

		HSSFCell headCell12 = rowTitle.createCell(12);
		headCell12.setCellStyle(headStyle);
		headCell12.setCellValue("中期质检罚款");

		HSSFCell headCell13 = rowTitle.createCell(13);
		headCell13.setCellStyle(headStyle);
		headCell13.setCellValue("竣工质检罚款");

		HSSFCell headCell14 = rowTitle.createCell(14);
		headCell14.setCellStyle(headStyle);
		headCell14.setCellValue("中期奖励");
		
		HSSFCell headCell15 = rowTitle.createCell(15);
		headCell15.setCellStyle(headStyle);
		headCell15.setCellValue("竣工奖励");
		
		HSSFCell headCell16 = rowTitle.createCell(16);
		headCell16.setCellStyle(headStyle);
		headCell16.setCellValue("中期扣款");
		
		HSSFCell headCell17 = rowTitle.createCell(17);
		headCell17.setCellStyle(headStyle);
		headCell17.setCellValue("竣工扣款");

		HSSFCell headCell18 = rowTitle.createCell(18);
		headCell18.setCellStyle(headStyle);
		headCell18.setCellValue("中期巡检奖励");

		HSSFCell headCell19 = rowTitle.createCell(19);
		headCell19.setCellStyle(headStyle);
		headCell19.setCellValue("竣工巡检奖励");

		HSSFCell headCell20 = rowTitle.createCell(20);
		headCell20.setCellStyle(headStyle);
		headCell20.setCellValue("中期巡检罚款");

		HSSFCell headCell21 = rowTitle.createCell(21);
		headCell21.setCellStyle(headStyle);
		headCell21.setCellValue("竣工巡检罚款");

		HSSFCell headCell22 = rowTitle.createCell(22);
		headCell22.setCellStyle(headStyle);
		headCell22.setCellValue("中期任务包材料结算");

		HSSFCell headCell23 = rowTitle.createCell(23);
		headCell23.setCellStyle(headStyle);
		headCell23.setCellValue("竣工任务包材料结算");

		HSSFCell headCell24 = rowTitle.createCell(24);
		headCell24.setCellStyle(headStyle);
		headCell24.setCellValue("自采材料");

		HSSFCell headCell25 = rowTitle.createCell(25);
		headCell25.setCellStyle(headStyle);
		headCell25.setCellValue("质保金");

		HSSFCell headCell26 = rowTitle.createCell(26);
		headCell26.setCellStyle(headStyle);
		headCell26.setCellValue("合计");


		bill.setStatus(ConstantUtils.PM_SETTLE_STATUS_50);
		bill.setSettleRole(ConstantUtils.SETTLE_ROLE_1);
		List<BizPmSettleBill> list = dao.findSettleBillList(bill);
		if (CollectionUtils.isNotEmpty(list)) {
			for (int i = 0; i < list.size(); i++) {
				BizPmSettleBill vo = list.get(i);
				HSSFRow row = sheet.createRow(i + 1);

				HSSFCell cell0 = row.createCell(0);
				cell0.setCellStyle(columnStyle);
				cell0.setCellValue(i + 1);

				HSSFCell cell1 = row.createCell(1);
				cell1.setCellStyle(columnStyle);
				if (vo.getStoreId() != null) {
					cell1.setCellValue(BizDictUtils.getStoreLabel(vo.getStoreId() + "", ""));
				}

				HSSFCell cell2 = row.createCell(2);
				cell2.setCellStyle(columnStyle);
				if (StringUtils.isNoneBlank(vo.getEnginDepartName())) {
					cell2.setCellValue(vo.getEnginDepartName());
				}

				HSSFCell cell3 = row.createCell(3);
				cell3.setCellStyle(columnStyle);
				if (StringUtils.isNoneBlank(vo.getSettleMonth())) {
					cell3.setCellValue(vo.getSettleMonth());
				}

				HSSFCell cell4 = row.createCell(4);
				cell4.setCellStyle(columnStyle);
				if (StringUtils.isNoneBlank(vo.getCustomerName())) {
					cell4.setCellValue(vo.getCustomerName());
				}

				HSSFCell cell5 = row.createCell(5);
				cell5.setCellStyle(columnStyle);
				if (StringUtils.isNoneBlank(vo.getCustomerPhone())) {
					cell5.setCellValue(vo.getCustomerPhone());
				}

				HSSFCell cell6 = row.createCell(6);
				cell6.setCellStyle(columnStyle);
				if (StringUtils.isNoneBlank(vo.getCustomerMessage())) {
					cell6.setCellValue(vo.getCustomerMessage());
				}

				HSSFCell cell7 = row.createCell(7);
				cell7.setCellStyle(columnStyle);
				if (StringUtils.isNoneBlank(vo.getItemManager())) {
					cell7.setCellValue(vo.getItemManager());
				}

				HSSFCell cell8 = row.createCell(8);
				cell8.setCellStyle(columnStyle);
				if (vo.getMidwayCommissionAmount() != null) {
					cell8.setCellValue(vo.getMidwayCommissionAmount());
				}

				HSSFCell cell9 = row.createCell(9);
				cell9.setCellStyle(columnStyle);
				if (vo.getCompleteCommissionAmount() != null) {
					cell9.setCellValue(vo.getCompleteCommissionAmount());
				}

				HSSFCell cell10 = row.createCell(10);
				cell10.setCellStyle(columnStyle);
				if (vo.getOwnpayAmount() != null) {
					cell10.setCellValue(vo.getOwnpayAmount());
				}

				HSSFCell cell11 = row.createCell(11);
				cell11.setCellStyle(columnStyle);
				if (vo.getMaterialsStandardAmount() != null) {
					cell11.setCellValue(vo.getMaterialsStandardAmount());
				}

				HSSFCell cell12 = row.createCell(12);
				cell12.setCellStyle(columnStyle);
				if (vo.getMidwayQcCheckPunishAmount() != null) {
					cell12.setCellValue(vo.getMidwayQcCheckPunishAmount());
				}

				HSSFCell cell13 = row.createCell(13);
				cell13.setCellStyle(columnStyle);
				if (vo.getCompletQcCheckPunishAmount() != null) {
					cell13.setCellValue(vo.getCompletQcCheckPunishAmount());
				}

				HSSFCell cell14 = row.createCell(14);
				cell14.setCellStyle(columnStyle);
				if (vo.getMidwayRewardAmount() != null) {
					cell14.setCellValue(vo.getMidwayRewardAmount());
				}
				
				HSSFCell cell15 = row.createCell(15);
				cell15.setCellStyle(columnStyle);
				if (vo.getCompleteRewardAmount() != null) {
					cell15.setCellValue(vo.getCompleteRewardAmount());
				}
				
				HSSFCell cell16 = row.createCell(16);
				cell16.setCellStyle(columnStyle);
				if (vo.getMidwayPunishAmount() != null) {
					cell16.setCellValue(vo.getMidwayPunishAmount());
				}
				
				HSSFCell cell17 = row.createCell(17);
				cell17.setCellStyle(columnStyle);
				if (vo.getCompletePunishAmount() != null) {
					cell17.setCellValue(vo.getCompletePunishAmount());
				}
				HSSFCell cell18 = row.createCell(18);
				cell18.setCellStyle(columnStyle);
				if (vo.getMidwayInspectionRewardAmount() != null) {
					cell18.setCellValue(vo.getMidwayInspectionRewardAmount());
				}

				HSSFCell cell19 = row.createCell(19);
				cell19.setCellStyle(columnStyle);
				if (vo.getCompleteInspectionRewardAmount() != null) {
					cell19.setCellValue(vo.getCompleteInspectionRewardAmount());
				}

				HSSFCell cell20 = row.createCell(20);
				cell20.setCellStyle(columnStyle);
				if (vo.getMidwayInspectionPunishAmount() != null) {
					cell20.setCellValue(vo.getMidwayInspectionPunishAmount());
				}

				HSSFCell cell21 = row.createCell(21);
				cell21.setCellStyle(columnStyle);
				if (vo.getCompleteInspectionPunishAmount() != null) {
					cell21.setCellValue(vo.getCompleteInspectionPunishAmount());
				}

				HSSFCell cell22 = row.createCell(22);
				cell22.setCellStyle(columnStyle);
				if (vo.getMidwayAuxiliaryMaterialsSettleAmount() != null) {
					cell22.setCellValue(vo.getMidwayAuxiliaryMaterialsSettleAmount());
				}

				HSSFCell cell23= row.createCell(23);
				cell23.setCellStyle(columnStyle);
				if (vo.getCompleteAuxiliaryMaterialsSettleAmount() != null) {
					cell23.setCellValue(vo.getCompleteAuxiliaryMaterialsSettleAmount());
				}

				HSSFCell cell24 = row.createCell(24);
				cell24.setCellStyle(columnStyle);
				if (vo.getMaterialSelfbuyReimburseAmount() != null) {
					cell24.setCellValue(vo.getMaterialSelfbuyReimburseAmount());
				}

				HSSFCell cell25 = row.createCell(25);
				cell25.setCellStyle(columnStyle);
				if (vo.getGuaranteeMoneyAmount() != null) {
					cell25.setCellValue(vo.getGuaranteeMoneyAmount());
				}

				HSSFCell cell26 = row.createCell(26);
				cell26.setCellStyle(columnStyle);
				if (vo.getTotalAmount() != null) {
					cell26.setCellValue(vo.getTotalAmount());
				}
			}
		}
		return wb;
	}

	public HSSFWorkbook exportExcel2(BizPmSettleBill bill) {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("月度工程结算明细");

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
		sheet.setColumnWidth(25, 3000);
		sheet.setColumnWidth(26, 3000);

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


		HSSFRow rowTitle = sheet.createRow(0);
		HSSFCell headCell0 = rowTitle.createCell(0);
		headCell0.setCellStyle(headStyle);
		headCell0.setCellValue("序号");

		HSSFCell headCell1 = rowTitle.createCell(1);
		headCell1.setCellStyle(headStyle);
		headCell1.setCellValue("门店");

		HSSFCell headCell2 = rowTitle.createCell(2);
		headCell2.setCellStyle(headStyle);
		headCell2.setCellValue("订单号");

		HSSFCell headCell3 = rowTitle.createCell(3);
		headCell3.setCellStyle(headStyle);
		headCell3.setCellValue("客户姓名");

		HSSFCell headCell4 = rowTitle.createCell(4);
		headCell4.setCellStyle(headStyle);
		headCell4.setCellValue("电话");

		HSSFCell headCell5 = rowTitle.createCell(5);
		headCell5.setCellStyle(headStyle);
		headCell5.setCellValue("地址");

		HSSFCell headCell6 = rowTitle.createCell(6);
		headCell6.setCellStyle(headStyle);
		headCell6.setCellValue("项目经理");

		HSSFCell headCell7 = rowTitle.createCell(7);
		headCell7.setCellStyle(headStyle);
		headCell7.setCellValue("项目经理手机号");

		HSSFCell headCell8 = rowTitle.createCell(8);
		headCell8.setCellStyle(headStyle);
		headCell8.setCellValue("中期提成");

		HSSFCell headCell9 = rowTitle.createCell(9);
		headCell9.setCellStyle(headStyle);
		headCell9.setCellValue("竣工提成");

		HSSFCell headCell10 = rowTitle.createCell(10);
		headCell10.setCellStyle(headStyle);
		headCell10.setCellValue("自主支配");

		HSSFCell headCell11 = rowTitle.createCell(11);
		headCell11.setCellStyle(headStyle);
		headCell11.setCellValue("标化辅料");

		HSSFCell headCell12 = rowTitle.createCell(12);
		headCell12.setCellStyle(headStyle);
		headCell12.setCellValue("中期质检罚款");

		HSSFCell headCell13 = rowTitle.createCell(13);
		headCell13.setCellStyle(headStyle);
		headCell13.setCellValue("竣工质检罚款");
		
		HSSFCell headCell14 = rowTitle.createCell(14);
		headCell14.setCellStyle(headStyle);
		headCell14.setCellValue("中期奖励");
		
		HSSFCell headCell15 = rowTitle.createCell(15);
		headCell15.setCellStyle(headStyle);
		headCell15.setCellValue("竣工奖励");
		
		HSSFCell headCell16 = rowTitle.createCell(16);
		headCell16.setCellStyle(headStyle);
		headCell16.setCellValue("中期扣款");
		
		HSSFCell headCell17 = rowTitle.createCell(17);
		headCell17.setCellStyle(headStyle);
		headCell17.setCellValue("竣工扣款");

		HSSFCell headCell18 = rowTitle.createCell(18);
		headCell18.setCellStyle(headStyle);
		headCell18.setCellValue("中期巡检奖励");

		HSSFCell headCell19 = rowTitle.createCell(19);
		headCell19.setCellStyle(headStyle);
		headCell19.setCellValue("竣工巡检奖励");

		HSSFCell headCell20 = rowTitle.createCell(20);
		headCell20.setCellStyle(headStyle);
		headCell20.setCellValue("中期巡检罚款");

		HSSFCell headCell21 = rowTitle.createCell(21);
		headCell21.setCellStyle(headStyle);
		headCell21.setCellValue("竣工巡检罚款");

		HSSFCell headCell22 = rowTitle.createCell(22);
		headCell22.setCellStyle(headStyle);
		headCell22.setCellValue("中期任务包材料结算");

		HSSFCell headCell23 = rowTitle.createCell(23);
		headCell23.setCellStyle(headStyle);
		headCell23.setCellValue("竣工任务包材料结算");

		HSSFCell headCell24 = rowTitle.createCell(24);
		headCell24.setCellStyle(headStyle);
		headCell24.setCellValue("自采材料");

		HSSFCell headCell25 = rowTitle.createCell(25);
		headCell25.setCellStyle(headStyle);
		headCell25.setCellValue("质保金");

		HSSFCell headCell26 = rowTitle.createCell(26);
		headCell26.setCellStyle(headStyle);
		headCell26.setCellValue("合计");

		bill.setStatus(ConstantUtils.PM_SETTLE_STATUS_30);
		bill.setSettleRole(ConstantUtils.SETTLE_ROLE_1);
		List<BizPmSettleBill> list = dao.findPmSettleBillList(bill);
		if (CollectionUtils.isNotEmpty(list)) {
			for (int i = 0; i < list.size(); i++) {
				BizPmSettleBill vo = list.get(i);
				HSSFRow row = sheet.createRow(i + 1);

				HSSFCell cell0 = row.createCell(0);
				cell0.setCellStyle(columnStyle);
				cell0.setCellValue(i + 1);

				HSSFCell cell1 = row.createCell(1);
				cell1.setCellStyle(columnStyle);
				if (vo.getStoreId() != null) {
					cell1.setCellValue(BizDictUtils.getStoreLabel(vo.getStoreId() + "", ""));
				}

				HSSFCell cell2 = row.createCell(2);
				cell2.setCellStyle(columnStyle);
				if (StringUtils.isNoneBlank(vo.getOrderNumber())) {
					cell2.setCellValue(vo.getOrderNumber());
				}

				HSSFCell cell3 = row.createCell(3);
				cell3.setCellStyle(columnStyle);
				if (StringUtils.isNoneBlank(vo.getCustomerName())) {
					cell3.setCellValue(vo.getCustomerName());
				}

				HSSFCell cell4 = row.createCell(4);
				cell4.setCellStyle(columnStyle);
				if (StringUtils.isNoneBlank(vo.getCustomerPhone())) {
					cell4.setCellValue(vo.getCustomerPhone());
				}

				HSSFCell cell5 = row.createCell(5);
				cell5.setCellStyle(columnStyle);
				if (StringUtils.isNoneBlank(vo.getCustomerMessage())) {
					cell5.setCellValue(vo.getCustomerMessage());
				}

				HSSFCell cell6 = row.createCell(6);
				cell6.setCellStyle(columnStyle);
				if (StringUtils.isNoneBlank(vo.getItemManager())) {
					cell6.setCellValue(vo.getItemManager());
				}

				HSSFCell cell7 = row.createCell(7);
				cell7.setCellStyle(columnStyle);
				if (StringUtils.isNoneBlank(vo.getItemManagerPhone())) {
					cell7.setCellValue(vo.getItemManagerPhone());
				}

				HSSFCell cell8 = row.createCell(8);
				cell8.setCellStyle(columnStyle);
				if (vo.getMidwayCommissionAmount() != null) {
					cell8.setCellValue(vo.getMidwayCommissionAmount());
				}

				HSSFCell cell9 = row.createCell(9);
				cell9.setCellStyle(columnStyle);
				if (vo.getCompleteCommissionAmount() != null) {
					cell9.setCellValue(vo.getCompleteCommissionAmount());
				}

				HSSFCell cell10 = row.createCell(10);
				cell10.setCellStyle(columnStyle);
				if (vo.getOwnpayAmount() != null) {
					cell10.setCellValue(vo.getOwnpayAmount());
				}

				HSSFCell cell11 = row.createCell(11);
				cell11.setCellStyle(columnStyle);
				if (vo.getMaterialsStandardAmount() != null) {
					cell11.setCellValue(vo.getMaterialsStandardAmount());
				}

				HSSFCell cell12 = row.createCell(12);
				cell12.setCellStyle(columnStyle);
				if (vo.getMidwayQcCheckPunishAmount() != null) {
					cell12.setCellValue(vo.getMidwayQcCheckPunishAmount());
				}

				HSSFCell cell13 = row.createCell(13);
				cell13.setCellStyle(columnStyle);
				if (vo.getCompletQcCheckPunishAmount() != null) {
					cell13.setCellValue(vo.getCompletQcCheckPunishAmount());
				}
				
				HSSFCell cell14 = row.createCell(14);
				cell14.setCellStyle(columnStyle);
				if (vo.getMidwayRewardAmount() != null) {
					cell14.setCellValue(vo.getMidwayRewardAmount());
				}

				HSSFCell cell15 = row.createCell(15);
				cell15.setCellStyle(columnStyle);
				if (vo.getCompleteRewardAmount() != null) {
					cell15.setCellValue(vo.getCompleteRewardAmount());
				}

				HSSFCell cell16 = row.createCell(16);
				cell16.setCellStyle(columnStyle);
				if (vo.getMidwayPunishAmount() != null) {
					cell16.setCellValue(vo.getMidwayPunishAmount());
				}

				HSSFCell cell17 = row.createCell(17);
				cell17.setCellStyle(columnStyle);
				if (vo.getCompletePunishAmount() != null) {
					cell17.setCellValue(vo.getCompletePunishAmount());
				}

				HSSFCell cell18 = row.createCell(18);
				cell18.setCellStyle(columnStyle);
				if (vo.getMidwayInspectionRewardAmount() != null) {
					cell18.setCellValue(vo.getMidwayInspectionRewardAmount());
				}

				HSSFCell cell19 = row.createCell(19);
				cell19.setCellStyle(columnStyle);
				if (vo.getCompleteInspectionRewardAmount() != null) {
					cell19.setCellValue(vo.getCompleteInspectionRewardAmount());
				}

				HSSFCell cell20 = row.createCell(20);
				cell20.setCellStyle(columnStyle);
				if (vo.getMidwayInspectionPunishAmount() != null) {
					cell20.setCellValue(vo.getMidwayInspectionPunishAmount());
				}

				HSSFCell cell21 = row.createCell(21);
				cell21.setCellStyle(columnStyle);
				if (vo.getCompleteInspectionPunishAmount() != null) {
					cell21.setCellValue(vo.getCompleteInspectionPunishAmount());
				}

				HSSFCell cell22 = row.createCell(22);
				cell22.setCellStyle(columnStyle);
				if (vo.getMidwayAuxiliaryMaterialsSettleAmount() != null) {
					cell22.setCellValue(vo.getMidwayAuxiliaryMaterialsSettleAmount());
				}

				HSSFCell cell23= row.createCell(23);
				cell23.setCellStyle(columnStyle);
				if (vo.getCompleteAuxiliaryMaterialsSettleAmount() != null) {
					cell23.setCellValue(vo.getCompleteAuxiliaryMaterialsSettleAmount());
				}

				HSSFCell cell24 = row.createCell(24);
				cell24.setCellStyle(columnStyle);
				if (vo.getMaterialSelfbuyReimburseAmount() != null) {
					cell24.setCellValue(vo.getMaterialSelfbuyReimburseAmount());
				}

				HSSFCell cell25 = row.createCell(25);
				cell25.setCellStyle(columnStyle);
				if (vo.getGuaranteeMoneyAmount() != null) {
					cell25.setCellValue(vo.getGuaranteeMoneyAmount());
				}

				HSSFCell cell26 = row.createCell(26);
				cell26.setCellStyle(columnStyle);
				if (vo.getTotalAmount() != null) {
					cell26.setCellValue(vo.getTotalAmount());
				}
			}
		}
		return wb;
	}


	public HSSFWorkbook exportExcelPbc(BizPmSettleBill bill) {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("订单月度工程结算单");


		sheet.setColumnWidth(0, 2000);
		sheet.setColumnWidth(1, 2000);
		sheet.setColumnWidth(2, 3000);
		sheet.setColumnWidth(3, 2000);
		sheet.setColumnWidth(4, 2000);
		sheet.setColumnWidth(5, 3000);
		sheet.setColumnWidth(6, 5000);
		sheet.setColumnWidth(7, 3000);
		sheet.setColumnWidth(8, 3000);
		sheet.setColumnWidth(9, 3500);
		sheet.setColumnWidth(10, 3000);
		sheet.setColumnWidth(11, 3500);
		sheet.setColumnWidth(12, 3000);

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


		HSSFRow rowTitle = sheet.createRow(0);
		HSSFCell headCell0 = rowTitle.createCell(0);
		headCell0.setCellStyle(headStyle);
		headCell0.setCellValue("序号");

		HSSFCell headCell1 = rowTitle.createCell(1);
		headCell1.setCellStyle(headStyle);
		headCell1.setCellValue("门店");

		HSSFCell headCell2 = rowTitle.createCell(2);
		headCell2.setCellStyle(headStyle);
		headCell2.setCellValue("区域");

		HSSFCell headCell3 = rowTitle.createCell(3);
		headCell3.setCellStyle(headStyle);
		headCell3.setCellValue("月度");

		HSSFCell headCell4 = rowTitle.createCell(4);
		headCell4.setCellStyle(headStyle);
		headCell4.setCellValue("客户姓名");

		HSSFCell headCell5 = rowTitle.createCell(5);
		headCell5.setCellStyle(headStyle);
		headCell5.setCellValue("电话");

		HSSFCell headCell6 = rowTitle.createCell(6);
		headCell6.setCellStyle(headStyle);
		headCell6.setCellValue("地址");

		HSSFCell headCell7 = rowTitle.createCell(7);
		headCell7.setCellStyle(headStyle);
		headCell7.setCellValue("质检员");

		HSSFCell headCell8 = rowTitle.createCell(8);
		headCell8.setCellStyle(headStyle);
		headCell8.setCellValue("中期提成");

		HSSFCell headCell9 = rowTitle.createCell(9);
		headCell9.setCellStyle(headStyle);
		headCell9.setCellValue("中期远程费提成");

		HSSFCell headCell10 = rowTitle.createCell(10);
		headCell10.setCellStyle(headStyle);
		headCell10.setCellValue("竣工提成");

		HSSFCell headCell11 = rowTitle.createCell(11);
		headCell11.setCellStyle(headStyle);
		headCell11.setCellValue("竣工远程费提成");

		HSSFCell headCell12 = rowTitle.createCell(12);
		headCell12.setCellStyle(headStyle);
		headCell12.setCellValue("合计");


		bill.setStatus(ConstantUtils.PM_SETTLE_STATUS_50);
		bill.setSettleRole(ConstantUtils.SETTLE_ROLE_2);
		List<BizPmSettleBill> list = dao.findSettleBillListPbc(bill);
		if (CollectionUtils.isNotEmpty(list)) {
			for (int i = 0; i < list.size(); i++) {
				BizPmSettleBill vo = list.get(i);
				HSSFRow row = sheet.createRow(i + 1);

				HSSFCell cell0 = row.createCell(0);
				cell0.setCellStyle(columnStyle);
				cell0.setCellValue(i + 1);

				HSSFCell cell1 = row.createCell(1);
				cell1.setCellStyle(columnStyle);
				if (vo.getStoreId() != null) {
					cell1.setCellValue(BizDictUtils.getStoreLabel(vo.getStoreId() + "", ""));
				}

				HSSFCell cell2 = row.createCell(2);
				cell2.setCellStyle(columnStyle);
				if (StringUtils.isNoneBlank(vo.getEnginDepartName())) {
					cell2.setCellValue(vo.getEnginDepartName());
				}

				HSSFCell cell3 = row.createCell(3);
				cell3.setCellStyle(columnStyle);
				if (StringUtils.isNoneBlank(vo.getSettleMonth())) {
					cell3.setCellValue(vo.getSettleMonth());
				}

				HSSFCell cell4 = row.createCell(4);
				cell4.setCellStyle(columnStyle);
				if (StringUtils.isNoneBlank(vo.getCustomerName())) {
					cell4.setCellValue(vo.getCustomerName());
				}

				HSSFCell cell5 = row.createCell(5);
				cell5.setCellStyle(columnStyle);
				if (StringUtils.isNoneBlank(vo.getCustomerPhone())) {
					cell5.setCellValue(vo.getCustomerPhone());
				}

				HSSFCell cell6 = row.createCell(6);
				cell6.setCellStyle(columnStyle);
				if (StringUtils.isNoneBlank(vo.getCustomerMessage())) {
					cell6.setCellValue(vo.getCustomerMessage());
				}

				HSSFCell cell7 = row.createCell(7);
				cell7.setCellStyle(columnStyle);
				if (StringUtils.isNoneBlank(vo.getOrderInspector())) {
					cell7.setCellValue(vo.getOrderInspector());
				}

				HSSFCell cell8 = row.createCell(8);
				cell8.setCellStyle(columnStyle);
				if (vo.getQcMidwayCommissionAmount() != null) {
					cell8.setCellValue(vo.getQcMidwayCommissionAmount());
				}

				HSSFCell cell9 = row.createCell(9);
				cell9.setCellStyle(columnStyle);
				if (vo.getQcMidwayLongwayAmount() != null) {
					cell9.setCellValue(vo.getQcMidwayLongwayAmount());
				}

				HSSFCell cell10 = row.createCell(10);
				cell10.setCellStyle(columnStyle);
				if (vo.getQcCompleteCommissionAmount() != null) {
					cell10.setCellValue(vo.getQcCompleteCommissionAmount());
				}

				HSSFCell cell11 = row.createCell(11);
				cell11.setCellStyle(columnStyle);
				if (vo.getQcCompleteLongwayAmount() != null) {
					cell11.setCellValue(vo.getQcCompleteLongwayAmount());
				}

				HSSFCell cell12 = row.createCell(12);
				cell12.setCellStyle(columnStyle);
				if (vo.getTotalAmount() != null) {
					cell12.setCellValue(vo.getTotalAmount());
				}
			}
		}
		return wb;
	}


	public List<Ownpay> findOwnpayAmount(Integer id) {
		return dao.findOwnpayAmount(id);
	}


	public List<InspectorPunish> findInspector(InspectorPunish inspectorPunish) {
		return dao.findInspector(inspectorPunish);
	}


	public List<BizMaterialSelfbuyVo> querySelfbuyMaterial(Map<String,Object> param) {
		return dao.querySelfbuyMaterial(param);
	}
    
	public BizPmSettleBill queryPmSettleBillInfoByParam(Map<String,Object> param){
		return dao.queryPmSettleBillInfoByParam(param);
	}
	@Transactional(readOnly = false)
	public void insertBatch(List<BizPmSettleBill> list){
		dao.insertBatch(list);
	}

    public BizPmSettleBill findPmSettleBillInfoByParam(Map<String,Object> param){
    	return dao.findPmSettleBillInfoByParam(param);
    }
}