
package cn.damei.service.modules;

import java.util.List;

import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.utils.StringUtils;
import cn.damei.common.utils.BizDictUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.damei.common.persistence.Page;
import cn.damei.common.service.CrudService2;
import cn.damei.entity.modules.BizPmSettleSummaryBill;
import cn.damei.dao.modules.BizPmSettleSummaryBillDao;


@Service
@Transactional(readOnly = true)
public class BizPmSettleSummaryBillService extends CrudService2<BizPmSettleSummaryBillDao, BizPmSettleSummaryBill> {

	public BizPmSettleSummaryBill get(Integer id) {
		return super.get(id);
	}
	
	public List<BizPmSettleSummaryBill> findList(BizPmSettleSummaryBill bizPmSettleSummaryBill) {
		return super.findList(bizPmSettleSummaryBill);
	}
	
	public Page<BizPmSettleSummaryBill> findPage(Page<BizPmSettleSummaryBill> page, BizPmSettleSummaryBill bizPmSettleSummaryBill) {
		return super.findPage(page, bizPmSettleSummaryBill);
	}
	
	@Transactional(readOnly = false)
	public void save(BizPmSettleSummaryBill bizPmSettleSummaryBill) {
		super.save(bizPmSettleSummaryBill);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizPmSettleSummaryBill bizPmSettleSummaryBill) {
		super.delete(bizPmSettleSummaryBill);
	}

	public Page<BizPmSettleSummaryBill> findSummaryBillPage(Page<BizPmSettleSummaryBill> page, BizPmSettleSummaryBill entity) {
		entity.setStatus(ConstantUtils.PM_SETTLE_STATUS_50);
		entity.setSettleRole(ConstantUtils.SETTLE_ROLE_1);
		entity.setPage(page);
		page.setList(dao.findSummaryBillList(entity));
		return page;
	}

	public Page<BizPmSettleSummaryBill> findSummaryBillPagePbc(Page<BizPmSettleSummaryBill> page, BizPmSettleSummaryBill entity) {
		entity.setStatus(ConstantUtils.PM_SETTLE_STATUS_50);
		entity.setSettleRole(ConstantUtils.SETTLE_ROLE_2);
		entity.setPage(page);
		page.setList(dao.findSummaryBillListPbc(entity));
		return page;
	}


	public HSSFWorkbook exportExcel(BizPmSettleSummaryBill bill) {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("项目经理月度工程结算单");


		sheet.setColumnWidth(0, 2000);
		sheet.setColumnWidth(1, 2000);
		sheet.setColumnWidth(2, 3000);
		sheet.setColumnWidth(3, 2000);
		sheet.setColumnWidth(4, 3000);
		sheet.setColumnWidth(5, 3000);
		sheet.setColumnWidth(6, 3000);
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
		headCell4.setCellValue("项目经理");

		HSSFCell headCell5 = rowTitle.createCell(5);
		headCell5.setCellStyle(headStyle);
		headCell5.setCellValue("电话");

		HSSFCell headCell6 = rowTitle.createCell(6);
		headCell6.setCellStyle(headStyle);
		headCell6.setCellValue("中期提成");

		HSSFCell headCell7 = rowTitle.createCell(7);
		headCell7.setCellStyle(headStyle);
		headCell7.setCellValue("竣工提成");

		HSSFCell headCell8 = rowTitle.createCell(8);
		headCell8.setCellStyle(headStyle);
		headCell8.setCellValue("自主支配");

		HSSFCell headCell9 = rowTitle.createCell(9);
		headCell9.setCellStyle(headStyle);
		headCell9.setCellValue("标化辅料");

		HSSFCell headCell10 = rowTitle.createCell(10);
		headCell10.setCellStyle(headStyle);
		headCell10.setCellValue("中期质检罚款");

		HSSFCell headCell11 = rowTitle.createCell(11);
		headCell11.setCellStyle(headStyle);
		headCell11.setCellValue("竣工质检罚款");
		
		HSSFCell headCell12 = rowTitle.createCell(12);
		headCell12.setCellStyle(headStyle);
		headCell12.setCellValue("中期奖励");
		
		HSSFCell headCell13 = rowTitle.createCell(13);
		headCell13.setCellStyle(headStyle);
		headCell13.setCellValue("竣工奖励");
		
		HSSFCell headCell14 = rowTitle.createCell(14);
		headCell14.setCellStyle(headStyle);
		headCell14.setCellValue("中期扣款");
		
		HSSFCell headCell15 = rowTitle.createCell(15);
		headCell15.setCellStyle(headStyle);
		headCell15.setCellValue("竣工扣款");
		
		
		HSSFCell headCell16 = rowTitle.createCell(16);
		headCell16.setCellStyle(headStyle);
		headCell16.setCellValue("中期任务包材料结算");
		
		HSSFCell headCell17 = rowTitle.createCell(17);
		headCell17.setCellStyle(headStyle);
		headCell17.setCellValue("竣工任务包材料结算");
		
		HSSFCell headCell18 = rowTitle.createCell(18);
		headCell18.setCellStyle(headStyle);
		headCell18.setCellValue("自采材料");

		HSSFCell headCell19 = rowTitle.createCell(19);
		headCell19.setCellStyle(headStyle);
		headCell19.setCellValue("质保金");

		HSSFCell headCell20 = rowTitle.createCell(20);
		headCell20.setCellStyle(headStyle);
		headCell20.setCellValue("合计");


		bill.setStatus(ConstantUtils.PM_SETTLE_STATUS_50);
		bill.setSettleRole(ConstantUtils.SETTLE_ROLE_1);
		List<BizPmSettleSummaryBill> list = dao.findSummaryBillList(bill);
		if(CollectionUtils.isNotEmpty(list)){
			for(int i=0;i<list.size();i++){
				BizPmSettleSummaryBill vo = list.get(i);
				HSSFRow row = sheet.createRow(i+1);

				HSSFCell cell0 = row.createCell(0);
				cell0.setCellStyle(columnStyle);
				cell0.setCellValue(i+1);

				HSSFCell cell1 = row.createCell(1);
				cell1.setCellStyle(columnStyle);
				if(vo.getStoreId() != null){
					cell1.setCellValue(BizDictUtils.getStoreLabel(vo.getStoreId() + "", ""));
				}

				HSSFCell cell2 = row.createCell(2);
				cell2.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(vo.getEnginDepartName())){
					cell2.setCellValue(vo.getEnginDepartName());
				}

				HSSFCell cell3 = row.createCell(3);
				cell3.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(vo.getSettleMonth())){
					cell3.setCellValue(vo.getSettleMonth());
				}

				HSSFCell cell4 = row.createCell(4);
				cell4.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(vo.getItemManager())){
					cell4.setCellValue(vo.getItemManager());
				}

				HSSFCell cell5 = row.createCell(5);
				cell5.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(vo.getItemManagerPhone())){
					cell5.setCellValue(vo.getItemManagerPhone());
				}

				HSSFCell cell6 = row.createCell(6);
				cell6.setCellStyle(columnStyle);
				if(vo.getMidwayCommissionAmount() != null){
					cell6.setCellValue(vo.getMidwayCommissionAmount());
				}


				HSSFCell cell7 = row.createCell(7);
				cell7.setCellStyle(columnStyle);
				if(vo.getCompleteCommissionAmount() != null){
					cell7.setCellValue(vo.getCompleteCommissionAmount());
				}

				HSSFCell cell8 = row.createCell(8);
				cell8.setCellStyle(columnStyle);
				if(vo.getOwnpayAmount() != null){
					cell8.setCellValue(vo.getOwnpayAmount());
				}

				HSSFCell cell9 = row.createCell(9);
				cell9.setCellStyle(columnStyle);
				if(vo.getMaterialsStandardAmount() != null){
					cell9.setCellValue(vo.getMaterialsStandardAmount());
				}

				HSSFCell cell10 = row.createCell(10);
				cell10.setCellStyle(columnStyle);
				if(vo.getMidwayQcCheckPunishAmount() != null){
					cell10.setCellValue(vo.getMidwayQcCheckPunishAmount());
				}

				HSSFCell cell11 = row.createCell(11);
				cell11.setCellStyle(columnStyle);
				if(vo.getCompletQcCheckPunishAmount() != null){
					cell11.setCellValue(vo.getCompletQcCheckPunishAmount());
				}
				
				HSSFCell cell12 = row.createCell(12);
				cell12.setCellStyle(columnStyle);
				if(vo.getMidwayRewardAmount() != null){
					cell12.setCellValue(vo.getMidwayRewardAmount());
				}
				
				HSSFCell cell13 = row.createCell(13);
				cell13.setCellStyle(columnStyle);
				if(vo.getCompleteRewardAmount() != null){
					cell13.setCellValue(vo.getCompleteRewardAmount());
				}
				
				HSSFCell cell14 = row.createCell(14);
				cell14.setCellStyle(columnStyle);
				if(vo.getMidwayPunishAmount() != null){
					cell14.setCellValue(vo.getMidwayPunishAmount());
				}
				
				HSSFCell cell15 = row.createCell(15);
				cell15.setCellStyle(columnStyle);
				if(vo.getCompletePunishAmount() != null){
					cell15.setCellValue(vo.getCompletePunishAmount());
				}
				
				HSSFCell cell16 = row.createCell(16);
				cell16.setCellStyle(columnStyle);
				if(vo.getMidwayAuxiliaryMaterialsSettleAmount() != null){
					cell16.setCellValue(vo.getMidwayAuxiliaryMaterialsSettleAmount());
				}
				
				HSSFCell cell17 = row.createCell(17);
				cell17.setCellStyle(columnStyle);
				if(vo.getCompleteAuxiliaryMaterialsSettleAmount() != null){
					cell17.setCellValue(vo.getCompleteAuxiliaryMaterialsSettleAmount());
				}
				
				HSSFCell cell18 = row.createCell(18);
				cell18.setCellStyle(columnStyle);
				if(vo.getMaterialSelfbuyReimburseAmount() != null){
					cell18.setCellValue(vo.getMaterialSelfbuyReimburseAmount());
				}

				HSSFCell cell19 = row.createCell(19);
				cell19.setCellStyle(columnStyle);
				if(vo.getGuaranteeMoneyAmount() != null){
					cell19.setCellValue(vo.getGuaranteeMoneyAmount());
				}

				HSSFCell cell20 = row.createCell(20);
				cell20.setCellStyle(columnStyle);
				if(vo.getTotalAmount() != null){
					cell20.setCellValue(vo.getTotalAmount());
				}
			}
		}
		return wb;
	}


	public HSSFWorkbook exportExcelPbc(BizPmSettleSummaryBill bill) {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("质检员月度工程结算单");


		sheet.setColumnWidth(0, 2000);
		sheet.setColumnWidth(1, 2000);
		sheet.setColumnWidth(2, 3000);
		sheet.setColumnWidth(3, 2000);
		sheet.setColumnWidth(4, 3000);
		sheet.setColumnWidth(5, 3000);
		sheet.setColumnWidth(6, 3000);
		sheet.setColumnWidth(7, 3500);
		sheet.setColumnWidth(8, 3000);
		sheet.setColumnWidth(9, 3500);
		sheet.setColumnWidth(10, 3000);

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
		headCell4.setCellValue("质检员");

		HSSFCell headCell5 = rowTitle.createCell(5);
		headCell5.setCellStyle(headStyle);
		headCell5.setCellValue("电话");

		HSSFCell headCell6 = rowTitle.createCell(6);
		headCell6.setCellStyle(headStyle);
		headCell6.setCellValue("中期提成");

		HSSFCell headCell7 = rowTitle.createCell(7);
		headCell7.setCellStyle(headStyle);
		headCell7.setCellValue("中期远程费提成");

		HSSFCell headCell8 = rowTitle.createCell(8);
		headCell8.setCellStyle(headStyle);
		headCell8.setCellValue("竣工提成");

		HSSFCell headCell9 = rowTitle.createCell(9);
		headCell9.setCellStyle(headStyle);
		headCell9.setCellValue("竣工远程费提成");

		HSSFCell headCell10 = rowTitle.createCell(10);
		headCell10.setCellStyle(headStyle);
		headCell10.setCellValue("合计");


		bill.setStatus(ConstantUtils.PM_SETTLE_STATUS_50);
		bill.setSettleRole(ConstantUtils.SETTLE_ROLE_2);
		List<BizPmSettleSummaryBill> list = dao.findSummaryBillListPbc(bill);
		if(CollectionUtils.isNotEmpty(list)){
			for(int i=0;i<list.size();i++){
				BizPmSettleSummaryBill vo = list.get(i);
				HSSFRow row = sheet.createRow(i+1);

				HSSFCell cell0 = row.createCell(0);
				cell0.setCellStyle(columnStyle);
				cell0.setCellValue(i+1);

				HSSFCell cell1 = row.createCell(1);
				cell1.setCellStyle(columnStyle);
				if(vo.getStoreId() != null){
					cell1.setCellValue(BizDictUtils.getStoreLabel(vo.getStoreId() + "", ""));
				}

				HSSFCell cell2 = row.createCell(2);
				cell2.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(vo.getEnginDepartName())){
					cell2.setCellValue(vo.getEnginDepartName());
				}

				HSSFCell cell3 = row.createCell(3);
				cell3.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(vo.getSettleMonth())){
					cell3.setCellValue(vo.getSettleMonth());
				}

				HSSFCell cell4 = row.createCell(4);
				cell4.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(vo.getOrderInspector())){
					cell4.setCellValue(vo.getOrderInspector());
				}

				HSSFCell cell5 = row.createCell(5);
				cell5.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(vo.getOrderInspectorPhone())){
					cell5.setCellValue(vo.getOrderInspectorPhone());
				}

				HSSFCell cell6 = row.createCell(6);
				cell6.setCellStyle(columnStyle);
				if(vo.getQcMidwayCommissionAmount() != null){
					cell6.setCellValue(vo.getQcMidwayCommissionAmount());
				}


				HSSFCell cell7 = row.createCell(7);
				cell7.setCellStyle(columnStyle);
				if(vo.getQcMidwayLongwayAmount() != null){
					cell7.setCellValue(vo.getQcMidwayLongwayAmount());
				}

				HSSFCell cell8 = row.createCell(8);
				cell8.setCellStyle(columnStyle);
				if(vo.getQcCompleteCommissionAmount() != null){
					cell8.setCellValue(vo.getQcCompleteCommissionAmount());
				}

				HSSFCell cell9 = row.createCell(9);
				cell9.setCellStyle(columnStyle);
				if(vo.getQcCompleteLongwayAmount() != null){
					cell9.setCellValue(vo.getQcCompleteLongwayAmount());
				}

				HSSFCell cell10 = row.createCell(10);
				cell10.setCellStyle(columnStyle);
				if(vo.getTotalAmount() != null){
					cell10.setCellValue(vo.getTotalAmount());
				}
			}
		}
		return wb;
	}
}