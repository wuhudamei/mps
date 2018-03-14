package cn.damei.service.modules;

import java.util.Date;
import java.util.List;

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

import cn.damei.common.constantUtils.BizAttendBatchConstantUtil;
import cn.damei.common.constantUtils.BizAttendBillConstantUtil;
import cn.damei.common.constantUtils.BusinessLogConstantUtil;
import cn.damei.common.service.CrudService2;
import cn.damei.common.utils.StringUtils;
import cn.damei.dao.modules.BizAttendBatchDao;
import cn.damei.entity.modules.BizAttendBatch;
import cn.damei.entity.modules.BizAttendBill;
import cn.damei.entity.modules.BizBusinessStatusLog;
import cn.damei.common.utils.UserUtils;


@Service
@Transactional(readOnly = true)
public class BizAttendBatchService extends CrudService2<BizAttendBatchDao, BizAttendBatch> {
	
	@Autowired
	private SysSequenceService sysSequenceService;
	@Autowired
	private BizAttendBillService bizAttendBillService;
	
	@Autowired
	private BizBusinessStatusLogService bizBusinessStatusLogService;
	
	@Transactional
	public int saveBatch(BizAttendBatch bizAttendBatch){
			
		BizAttendBatch bizAttendBatch2 = new BizAttendBatch();
			
			String sequence = sysSequenceService.getSequence(BizAttendBatchConstantUtil.KQPC_NO);

			String kqpcNo = sequence.substring(0,4);

			String No = sequence.substring(4);

			String date = BizAttendBillConstantUtil.getDate(new Date());

			bizAttendBatch2.setAttendBatchCode(kqpcNo+date+No);

			bizAttendBatch2.setStatus(BizAttendBatchConstantUtil.BATCH_AUDIT);

			bizAttendBatch2.setStatusDatetime(new Date());

			bizAttendBatch2.setBatchDatetime(new Date());

			bizAttendBatch2.setAttendBillCount(bizAttendBatch.getAttendBillCount());

			bizAttendBatch2.setAttendBatchMonth(bizAttendBatch.getAttendBatchMonth());

			bizAttendBatch2.setStoreId(bizAttendBatch.getStoreId());

			bizAttendBatch2.setEnginDepartId(bizAttendBatch.getEnginDepartId());
			bizAttendBatch2.setCreateBy(UserUtils.getUser());
			bizAttendBatch2.setCreateDate(new Date());

			int batchOperatorEmployeeId=0;
			try {
				batchOperatorEmployeeId = Integer.parseInt(UserUtils.getUser().getId());
			} catch (NumberFormatException e) {}

			bizAttendBatch2.setBatchOperatorEmployeeId(batchOperatorEmployeeId);
			
			dao.insert(bizAttendBatch2);
			

			BizBusinessStatusLog bizBusinessStatusLog = new BizBusinessStatusLog();
			bizBusinessStatusLog.setBusinessEmployeeId(bizAttendBatch2.getBatchOperatorEmployeeId());
			bizBusinessStatusLog.setBusinessType(BusinessLogConstantUtil.BUSINESS_TYPE_801);
			bizBusinessStatusLog.setBusinessOnlyMarkInt(bizAttendBatch2.getId());
			bizBusinessStatusLog.setBusinessStatus(BusinessLogConstantUtil.ATTEND_BATCH_STAUS_80);
			bizBusinessStatusLog.setStatusDatetime(new Date());
			bizBusinessStatusLog.setBusinessRemarks("批次生成成功");
			
			bizBusinessStatusLogService.save(bizBusinessStatusLog);
			
			return bizAttendBatch2.getId();
	}

	public HSSFWorkbook exportExcel(Integer id) {
		
		List<BizAttendBill> bizAttendBillList = bizAttendBillService.findBizAttendBillListByBatchId(id);
		
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("考勤信息");
		

				HSSFFont font = wb.createFont();
				font.setColor(HSSFFont.COLOR_NORMAL);
				font.setFontName("黑体");
				font.setFontHeightInPoints((short)10);
				font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				

				HSSFCellStyle columnHeadStyle = wb.createCellStyle();
				columnHeadStyle.setFont(font);
				columnHeadStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				columnHeadStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				columnHeadStyle.setLocked(true);
				columnHeadStyle.setWrapText(true);
				columnHeadStyle.setLeftBorderColor(HSSFColor.BLACK.index);
				columnHeadStyle.setBorderLeft((short) 1);
				columnHeadStyle.setRightBorderColor(HSSFColor.BLACK.index);
				columnHeadStyle.setBorderRight((short) 1);
				columnHeadStyle.setTopBorderColor(HSSFColor.BLACK.index);
				columnHeadStyle.setBorderTop((short) 1);
				columnHeadStyle.setBottomBorderColor(HSSFColor.BLACK.index);
				columnHeadStyle.setBorderBottom((short) 1);
				columnHeadStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				columnHeadStyle.setBottomBorderColor(HSSFColor.BLACK.index);
				columnHeadStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
				columnHeadStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);		
				
				

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
				

				sheet.setColumnWidth(0, 2000);
				sheet.setColumnWidth(1, 4000);
				sheet.setColumnWidth(2, 2000);
				sheet.setColumnWidth(3, 2000);
				sheet.setColumnWidth(4, 2000);
				sheet.setColumnWidth(5, 2000);
				sheet.setColumnWidth(6, 2000);
				sheet.setColumnWidth(7, 2000);
				sheet.setColumnWidth(8, 2000);
				sheet.setColumnWidth(9, 2000);
				sheet.setColumnWidth(10, 2000);
				sheet.setColumnWidth(11, 2000);
				sheet.setColumnWidth(12, 2000);
				sheet.setColumnWidth(13, 3000);
				sheet.setColumnWidth(14, 3000);
				sheet.setColumnWidth(15, 2000);
				

				HSSFRow rowTitle = sheet.createRow(0);
				rowTitle.setHeightInPoints(30);
				HSSFCell cell = rowTitle.createCell(0);
				cell.setCellStyle(columnHeadStyle);
				cell.setCellValue(new HSSFRichTextString("工程部项目经理考勤报表"));
				for(int i=0;i<15;i++){
					HSSFCell cella = rowTitle.createCell(i+1);
					cella.setCellStyle(columnHeadStyle);
				}

				sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 15));
				

				HSSFRow rowTitle2 = sheet.createRow(1);
				
				HSSFCell headCell0 = rowTitle2.createCell(0);
				headCell0.setCellStyle(columnHeadStyle);
				headCell0.setCellValue("序号");
				
				HSSFCell headCell1 = rowTitle2.createCell(1);
				headCell1.setCellStyle(columnHeadStyle);
				headCell1.setCellValue("员工编号");
				
				HSSFCell headCell2 = rowTitle2.createCell(2);
				headCell2.setCellStyle(columnHeadStyle);
				headCell2.setCellValue("姓名");
				
				HSSFCell headCell3 = rowTitle2.createCell(3);
				headCell3.setCellStyle(columnHeadStyle);
				headCell3.setCellValue("部门");
				
				HSSFCell headCell4 = rowTitle2.createCell(4);
				headCell4.setCellStyle(columnHeadStyle);
				headCell4.setCellValue("岗位");
				
				HSSFCell headCell5 = rowTitle2.createCell(5);
				headCell5.setCellStyle(columnHeadStyle);
				headCell5.setCellValue("性别");
				
				HSSFCell headCell6 = rowTitle2.createCell(6);
				headCell6.setCellStyle(columnHeadStyle);
				headCell6.setCellValue("底薪标准");
				
				HSSFCell headCell7 = rowTitle2.createCell(7);
				headCell7.setCellStyle(columnHeadStyle);
				headCell7.setCellValue("应出勤天数");
				
				HSSFCell headCell8 = rowTitle2.createCell(8);
				headCell8.setCellStyle(columnHeadStyle);
				headCell8.setCellValue("实际出勤天数");
				
				HSSFCell headCell9 = rowTitle2.createCell(9);
				headCell9.setCellStyle(columnHeadStyle);
				headCell9.setCellValue("事假");
				
				HSSFCell headCell10 = rowTitle2.createCell(10);
				headCell10.setCellStyle(columnHeadStyle);
				headCell10.setCellValue("病假");
				
				HSSFCell headCell11 = rowTitle2.createCell(11);
				headCell11.setCellStyle(columnHeadStyle);
				headCell11.setCellValue("年假");
				
				HSSFCell headCell12 = rowTitle2.createCell(12);
				headCell12.setCellStyle(columnHeadStyle);
				headCell12.setCellValue("全勤");
				
				HSSFCell headCell13 = rowTitle2.createCell(13);
				headCell13.setCellStyle(columnHeadStyle);
				headCell13.setCellValue("半勤");
				
				HSSFCell headCell14 = rowTitle2.createCell(14);
				headCell14.setCellStyle(columnHeadStyle);
				headCell14.setCellValue("补休");
				
				HSSFCell headCell15 = rowTitle2.createCell(15);
				headCell15.setCellStyle(columnHeadStyle);
				headCell15.setCellValue("备注");
				
		

		for(int i=0;i<bizAttendBillList.size();i++){
			
			BizAttendBill bizAttendBill = bizAttendBillList.get(i);
			HSSFRow row = sheet.createRow(i+2);
			
			HSSFCell cell0 = row.createCell(0);
			cell0.setCellStyle(columnStyle);
			cell0.setCellValue(i+1);
			
			HSSFCell cell1 = row.createCell(1);
			cell1.setCellStyle(columnStyle);
			if(StringUtils.isNoneBlank(bizAttendBill.getNo())){
				cell1.setCellValue(bizAttendBill.getNo());
			}

			HSSFCell cell2 = row.createCell(2);
			cell2.setCellStyle(columnStyle);
			if(null!=bizAttendBill.getManagerName()){
				cell2.setCellValue(bizAttendBill.getManagerName());
			}
			
			HSSFCell cell3 = row.createCell(3);
			cell3.setCellStyle(columnStyle);
			cell3.setCellValue("工程部");
			
			HSSFCell cell4 = row.createCell(4);
			cell4.setCellStyle(columnStyle);
			if(StringUtils.isNoneBlank(bizAttendBill.getRole())){
				cell4.setCellValue(bizAttendBill.getRole());
			}
			
			HSSFCell cell5 = row.createCell(5);
			cell5.setCellStyle(columnStyle);
			if(StringUtils.isNoneBlank(bizAttendBill.getSex2())){
				cell5.setCellValue(bizAttendBill.getSex2());
			}
			
			HSSFCell cell6 = row.createCell(6);
			cell6.setCellStyle(columnStyle);
			if(bizAttendBill.getBasicSalary()==null){
				cell6.setCellValue("");
			}else{
				cell6.setCellValue(bizAttendBill.getBasicSalary());
			}
			
			HSSFCell cell7 = row.createCell(7);
			cell7.setCellStyle(columnStyle);
			if(bizAttendBill.getAttendDaysMust()==null){
				cell7.setCellValue("");
			}else{
				cell7.setCellValue(bizAttendBill.getAttendDaysMust());
			}
			
			HSSFCell cell8 = row.createCell(8);
			cell8.setCellStyle(columnStyle);
			if(bizAttendBill.getAttendDaysTotal()==null){
				cell8.setCellValue("");
			}else{
				cell8.setCellValue(bizAttendBill.getAttendDaysTotal());
			}
			
			HSSFCell cell9 = row.createCell(9);
			cell9.setCellStyle(columnStyle);
			if(bizAttendBill.getLeaveDaysThing()==null){
				cell9.setCellValue("");
			}else{
				cell9.setCellValue(bizAttendBill.getLeaveDaysThing());
			}
			
			HSSFCell cell10 = row.createCell(10);
			cell10.setCellStyle(columnStyle);
			if(bizAttendBill.getLeaveDaysSick()==null){
				cell10.setCellValue("");
			}else{
				cell10.setCellValue(bizAttendBill.getLeaveDaysSick());
			}
			
			HSSFCell cell11 = row.createCell(11);
			cell11.setCellStyle(columnStyle);
			if(bizAttendBill.getLeaveDaysAnnual()==null){
				cell11.setCellValue("");
			}else{
				cell11.setCellValue(bizAttendBill.getLeaveDaysAnnual());
			}
			
			HSSFCell cell12 = row.createCell(12);
			cell12.setCellStyle(columnStyle);
			if(bizAttendBill.getAttendDaysWhole()==null){
				cell12.setCellValue("");
			}else{
				cell12.setCellValue(bizAttendBill.getAttendDaysWhole());
			}
			
			HSSFCell cell13 = row.createCell(13);
			cell13.setCellStyle(columnStyle);
			if(bizAttendBill.getAttendDaysHalf()==null){
				cell13.setCellValue("");
			}else{
				cell13.setCellValue(bizAttendBill.getAttendDaysHalf());
			}
			
			HSSFCell cell14 = row.createCell(14);
			cell14.setCellStyle(columnStyle);
			if(bizAttendBill.getRestDays()==null){
				cell14.setCellValue("");
			}else{
				cell14.setCellValue(bizAttendBill.getRestDays());
			}
			
			HSSFCell cell15 = row.createCell(15);
			cell15.setCellStyle(columnStyle);
			if(StringUtils.isNoneBlank(bizAttendBill.getAttendRemarks())){
				cell15.setCellValue(bizAttendBill.getAttendRemarks());
			}
		}
		return wb;
	}
	
}