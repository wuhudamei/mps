package cn.damei.common.utils;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
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

import cn.damei.entity.modules.BizConfirmStartOrder;
import cn.damei.entity.modules.BizConfirmStartService;

public class ExportConfirmStart {
@Autowired
private BizConfirmStartService dao;



	
	public  HSSFWorkbook exportExcel(List<BizConfirmStartOrder> list) {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("确认开工信息");
		

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
		sheet.setColumnWidth(1, 2000);
		sheet.setColumnWidth(2, 5000);
		sheet.setColumnWidth(3, 6000);
		sheet.setColumnWidth(4, 6000);
		sheet.setColumnWidth(5, 4000);
		sheet.setColumnWidth(6, 4000);
		sheet.setColumnWidth(7, 4000);
		sheet.setColumnWidth(8, 4000);
		sheet.setColumnWidth(9, 4000);
		sheet.setColumnWidth(10, 4000);
		sheet.setColumnWidth(11, 4000);
		sheet.setColumnWidth(12, 4000);
		sheet.setColumnWidth(13, 3000);
		sheet.setColumnWidth(14, 3000);
		sheet.setColumnWidth(15, 3000);
		sheet.setColumnWidth(16, 3000);
		sheet.setColumnWidth(17, 3000);
		sheet.setColumnWidth(18, 3000);
		sheet.setColumnWidth(19, 3000);
		sheet.setColumnWidth(20, 3000);
		

		HSSFRow rowTitle = sheet.createRow(0);
		rowTitle.setHeightInPoints(20);
		HSSFCell cell = rowTitle.createCell(0);
		cell.setCellStyle(columnHeadStyle);
		cell.setCellValue(new HSSFRichTextString("确认开工信息"));
		for(int i=0;i<20;i++){
			HSSFCell cella = rowTitle.createCell(i+1);
			cella.setCellStyle(columnHeadStyle);
		}

		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 20));
		

		HSSFRow rowTitle2 = sheet.createRow(1);
		
		HSSFCell headCell0 = rowTitle2.createCell(0);
		headCell0.setCellStyle(columnHeadStyle);
		headCell0.setCellValue("门店");
		
		HSSFCell headCell1 = rowTitle2.createCell(1);
		headCell1.setCellStyle(columnHeadStyle);
		headCell1.setCellValue("工程模式");
		
		HSSFCell headCell2 = rowTitle2.createCell(2);
		headCell2.setCellStyle(columnHeadStyle);
		headCell2.setCellValue("区域");
		
		HSSFCell headCell3 = rowTitle2.createCell(3);
		headCell3.setCellStyle(columnHeadStyle);
		headCell3.setCellValue("提交时间");
		
		HSSFCell headCell4 = rowTitle2.createCell(4);
		headCell4.setCellStyle(columnHeadStyle);
		headCell4.setCellValue("订单编号");
		
		HSSFCell headCell5 = rowTitle2.createCell(5);
		headCell5.setCellStyle(columnHeadStyle);
		headCell5.setCellValue("客户");
		
		HSSFCell headCell6 = rowTitle2.createCell(6);
		headCell6.setCellStyle(columnHeadStyle);
		
		
		HSSFCell headCell7 = rowTitle2.createCell(7);
		headCell7.setCellStyle(columnHeadStyle);
		headCell7.setCellValue("项目经理");
		
		HSSFCell headCell8 = rowTitle2.createCell(8);
		headCell8.setCellStyle(columnHeadStyle);
		
		
		HSSFCell headCell9 = rowTitle2.createCell(9);
		headCell9.setCellStyle(columnHeadStyle);
		headCell9.setCellValue("设计师");
		
		HSSFCell headCell10 = rowTitle2.createCell(10);
		headCell10.setCellStyle(columnHeadStyle);
		
		
		HSSFCell headCell11 = rowTitle2.createCell(11);
		headCell11.setCellStyle(columnHeadStyle);
		headCell11.setCellValue("质检员");
		
		HSSFCell headCell12 = rowTitle2.createCell(12);
		headCell12.setCellStyle(columnHeadStyle);
		
		
		HSSFCell headCell13 = rowTitle2.createCell(13);
		headCell13.setCellStyle(columnHeadStyle);
		headCell13.setCellValue("合同开工日期");
		
		HSSFCell headCell14 = rowTitle2.createCell(14);
		headCell14.setCellStyle(columnHeadStyle);
		headCell14.setCellValue("实际开工日期");
		
		HSSFCell headCell15 = rowTitle2.createCell(15);
		headCell15.setCellStyle(columnHeadStyle);
		headCell15.setCellValue("延期天数");
		
		HSSFCell headCell16 = rowTitle2.createCell(16);
		headCell16.setCellStyle(columnHeadStyle);
		headCell16.setCellValue("开工客户签字");
		
		HSSFCell headCell17 = rowTitle2.createCell(17);
		headCell17.setCellStyle(columnHeadStyle);
		headCell17.setCellValue("开工延期类型");
		
		HSSFCell headCell18 = rowTitle2.createCell(18);
		headCell18.setCellStyle(columnHeadStyle);
		headCell18.setCellValue("开工延期说明");
		
		HSSFCell headCell19 = rowTitle2.createCell(19);
		headCell19.setCellStyle(columnHeadStyle);
		headCell19.setCellValue("自装延期天数");
		
		HSSFCell headCell20 = rowTitle2.createCell(20);
		headCell20.setCellStyle(columnHeadStyle);
		headCell20.setCellValue("自装客户签字");
		
		
		
		HSSFRow rowTitle3 = sheet.createRow(2);
		for(int i=0;i<20;i++){
			HSSFCell cella = rowTitle3.createCell(i);
			cella.setCellStyle(columnHeadStyle);
			if(i==5 || i == 7 || i == 9 || i == 11){
				cella.setCellValue("姓名");
			}
			if(i==6 || i == 8 || i == 10 || i == 12){
				cella.setCellValue("手机号");
			}
		}
		

		sheet.addMergedRegion(new CellRangeAddress(1, 2, 0, 0));
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 1, 1));
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 2, 2));
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 3, 3));
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 4, 4));
		
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 5, 6));
		
		
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 7, 8));
		
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 9, 10));
	
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 11, 12));
		
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 13, 13));
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 14, 14));
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 15, 15));
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 16, 16));
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 17, 17));
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 18, 18));
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 19, 19));
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 20, 20));
		
		

		
		
		if(CollectionUtils.isNotEmpty(list)){
			for(int i=0;i<list.size();i++){
				BizConfirmStartOrder order = list.get(i);
				HSSFRow row = sheet.createRow(i+3);
				
		
				
				HSSFCell cell1 = row.createCell(0);
				cell1.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(order.getName())){
					cell1.setCellValue(order.getName());
				}

				HSSFCell cell2 = row.createCell(1);
				cell2.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(order.getProjectMode())){
					if("1".equals(order.getProjectMode())){
						cell2.setCellValue("产业");
					}else{
						cell2.setCellValue("传统");
					}
				}
	
				HSSFCell cell3 = row.createCell(2);
				cell3.setCellStyle(columnStyle);
				if(StringUtils.isNotBlank(order.getEnginDepartName())){
					cell3.setCellValue(order.getEnginDepartName());
				}
				
				HSSFCell cell4 = row.createCell(3);
				cell4.setCellStyle(columnStyle);
				if(null!=order.getStaCreateDate()){
					cell4.setCellValue(DateFormatUtils.format(order.getStaCreateDate(), "yyyy-MM-dd  HH:mm:ss"));
				}
				
				HSSFCell cell5 = row.createCell(4);
				cell5.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(order.getOrderNumber())){
					cell5.setCellValue(order.getOrderNumber());
				}
				
				HSSFCell cell6 = row.createCell(5);
				cell6.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(order.getCustomerName())){
					cell6.setCellValue(order.getCustomerName());
				}
				
				HSSFCell cell7 = row.createCell(6);
				cell7.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(order.getCustomerPhone())){
					cell7.setCellValue(order.getCustomerPhone());
				}
				
				HSSFCell cell8 = row.createCell(7);
				cell8.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(order.getManagerRealName())){
					cell8.setCellValue(order.getManagerRealName());
				}
				
				HSSFCell cell9 = row.createCell(8);
				cell9.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(order.getManagerPhone())){
					cell9.setCellValue(order.getManagerPhone());
				}
				
				HSSFCell cell10 = row.createCell(9);
				cell10.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(order.getDesignerName())){
					cell10.setCellValue(order.getDesignerName());
				}
				
				HSSFCell cell11 = row.createCell(10);
				cell11.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(order.getDesignerPhone())){
					cell11.setCellValue(order.getDesignerPhone());
				}
				
				HSSFCell cell12 = row.createCell(11);
				cell12.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(order.getInspectorName())){
					cell12.setCellValue(order.getInspectorName());
				}
				
				HSSFCell cell13 = row.createCell(12);
				cell13.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(order.getInspectorPhone())){
					cell13.setCellValue(order.getInspectorPhone());
				}
				
				HSSFCell cell14 = row.createCell(13);
				cell14.setCellStyle(columnStyle);
				if(null!=order.getContractStartDate()){
					cell14.setCellValue(DateFormatUtils.format(order.getContractStartDate(), "yyyy-MM-dd"));
				}
				
				HSSFCell cell15 = row.createCell(14);
				cell15.setCellStyle(columnStyle);
				if(null != order.getActualStartDate()){
					cell15.setCellValue(DateFormatUtils.format(order.getActualStartDate(), "yyyy-MM-dd"));
				}
				
				HSSFCell cell16 = row.createCell(15);
				cell16.setCellStyle(columnStyle);
				if(null != order.getDeplayDays()){
					cell16.setCellValue(order.getDeplayDays());
				}
				HSSFCell cell17 = row.createCell(16);
				cell17.setCellStyle(columnStyle);
				if(null != order.getDicIsNeedSign()){
					if(order.getDicIsNeedSign().equals("0")){
						cell17.setCellValue("否");
					}else{
						cell17.setCellValue("是");
					}
					
				}
				HSSFCell cell18 = row.createCell(17);
				cell18.setCellStyle(columnStyle);
				if(null != order.getDelayType()){
					if(order.getDelayType().equals("0")){
						cell18.setCellValue("客户原因");
					}else{
						cell18.setCellValue("公司原因");
					}
				}
				HSSFCell cell19 = row.createCell(18);
				cell19.setCellStyle(columnStyle);
				if(null != order.getStaRemarks()){
					cell19.setCellValue(order.getStaRemarks());
				}
				HSSFCell cell20 = row.createCell(19);
				cell20.setCellStyle(columnStyle);
				if(null != order.getStaSelfDecorateDelayDays()){
					cell20.setCellValue(order.getStaSelfDecorateDelayDays());
				}
				HSSFCell cell21 = row.createCell(20);
				cell21.setCellStyle(columnStyle);
				if(null != order.getDicIsNeedSign()){
					if(order.getDicIsNeedSign().equals("1")){
						cell21.setCellValue("是");
					}else{
						cell21.setCellValue("否");
					}
					
				}
				
			}
		}
		
		return wb;
	}

}
