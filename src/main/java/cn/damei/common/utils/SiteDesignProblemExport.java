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

import cn.damei.entity.modules.SiteDesignProblem;

public class SiteDesignProblemExport {

	public  HSSFWorkbook exportExcel(List<SiteDesignProblem> list) {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("工程设计问题上报");
		

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
		sheet.setColumnWidth(7, 8000);
		sheet.setColumnWidth(8, 8000);
		sheet.setColumnWidth(9, 4000);
		sheet.setColumnWidth(10, 6000);
		sheet.setColumnWidth(11, 6000);
		sheet.setColumnWidth(12, 6000);
		sheet.setColumnWidth(13, 2000);
		sheet.setColumnWidth(14, 2000);
		sheet.setColumnWidth(15, 8000);
		sheet.setColumnWidth(16, 3000);
		
		

		HSSFRow rowTitle = sheet.createRow(0);
		rowTitle.setHeightInPoints(16);
		HSSFCell cell = rowTitle.createCell(0);
		cell.setCellStyle(columnHeadStyle);
		cell.setCellValue(new HSSFRichTextString("工程设计问题上报"));
		for(int i=0;i<16;i++){
			HSSFCell cella = rowTitle.createCell(i+1);
			cella.setCellStyle(columnHeadStyle);
		}

		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 16));
		

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
		headCell3.setCellValue("小区名字");
		
		HSSFCell headCell4 = rowTitle2.createCell(4);
		headCell4.setCellStyle(columnHeadStyle);
		headCell4.setCellValue("客户");
		
		HSSFCell headCell5 = rowTitle2.createCell(5);
		headCell5.setCellStyle(columnHeadStyle);
		headCell5.setCellValue("项目经理");
		
		HSSFCell headCell6 = rowTitle2.createCell(6);
		headCell6.setCellStyle(columnHeadStyle);
		headCell6.setCellValue("设计师");
		
		
		HSSFCell headCell7 = rowTitle2.createCell(7);
		headCell7.setCellStyle(columnHeadStyle);
		headCell7.setCellValue("问题类别");
		
		HSSFCell headCell8 = rowTitle2.createCell(8);
		headCell8.setCellStyle(columnHeadStyle);
		headCell8.setCellValue("具体情况说明（必填）");
		
		HSSFCell headCell9 = rowTitle2.createCell(9);
		headCell9.setCellStyle(columnHeadStyle);
		headCell9.setCellValue("责任人");
		
		HSSFCell headCell10 = rowTitle2.createCell(10);
		headCell10.setCellStyle(columnHeadStyle);
		headCell10.setCellValue("节点时间");
		
		
		HSSFCell headCell11 = rowTitle2.createCell(11);
		headCell11.setCellStyle(columnHeadStyle);
		
		
		HSSFCell headCell12 = rowTitle2.createCell(12);
		headCell12.setCellStyle(columnHeadStyle);
		
		
		HSSFCell headCell13 = rowTitle2.createCell(13);
		headCell13.setCellStyle(columnHeadStyle);
		headCell13.setCellValue("处罚标准");
		
		HSSFCell headCell14 = rowTitle2.createCell(14);
		headCell14.setCellStyle(columnHeadStyle);
		
		
		HSSFCell headCell15 = rowTitle2.createCell(15);
		headCell15.setCellStyle(columnHeadStyle);
	
	
		
		
	
		
		
		
		
		HSSFRow rowTitle3 = sheet.createRow(2);
		for(int i=0;i<16;i++){
			HSSFCell cella = rowTitle3.createCell(i);
			cella.setCellStyle(columnHeadStyle);
			if(i==10){
				cella.setCellValue("项目经理上报问题");
			}
			if(i==11){
				cella.setCellValue("期望完成时间");
			}
			if(i==12){
				cella.setCellValue("实际完成时间");
			}
			
			if(i==13){
				cella.setCellValue("扣分");
			}
			if(i==14){
				cella.setCellValue("罚款");
			}
			if(i==15){
				cella.setCellValue("备注");
			}
		}
		

		sheet.addMergedRegion(new CellRangeAddress(1, 2, 0, 0));
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 1, 1));
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 2, 2));
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 3, 3));
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 4, 4));
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 5, 5));
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 6, 6));
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 7, 7));
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 8, 8));
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 9, 9));
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 10, 12));
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 13, 15));
		

		
		

		
		if(CollectionUtils.isNotEmpty(list)){
			for(int i=0;i<list.size();i++){
				SiteDesignProblem order = list.get(i);
				HSSFRow row = sheet.createRow(i+3);
				HSSFCell cell1 = row.createCell(0);
				cell1.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(order.getStoreName())){
					cell1.setCellValue(order.getStoreName());
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
				if(StringUtils.isNotBlank(order.getEnginName())){
					cell3.setCellValue(order.getEnginName());
				}
				
				HSSFCell cell4 = row.createCell(3);
				cell4.setCellStyle(columnStyle);
				if(null!=order.getCommunityName()){
					cell4.setCellValue(order.getCommunityName());
				}
				
				HSSFCell cell5 = row.createCell(4);
				cell5.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(order.getCustomerName())){
					cell5.setCellValue(order.getCustomerName());
				}
				
				HSSFCell cell6 = row.createCell(5);
				cell6.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(order.getItemManager())){
					cell6.setCellValue(order.getItemManager());
				}
				
				HSSFCell cell7 = row.createCell(6);
				cell7.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(order.getDesignerName())){
					cell7.setCellValue(order.getDesignerName());
				}
				
				HSSFCell cell8 = row.createCell(7);
				cell8.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(order.getProblemTypeName())){
					cell8.setCellValue(order.getProblemTypeName());
				}
				
				HSSFCell cell9 = row.createCell(8);
				cell9.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(order.getProblemDescribe())){
					cell9.setCellValue(order.getProblemDescribe());
				}
				
				HSSFCell cell10 = row.createCell(9);
				cell10.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(order.getInchargeName())){
					cell10.setCellValue(order.getInchargeName());
				}
				
				
				HSSFCell cell12 = row.createCell(10);
				cell12.setCellStyle(columnStyle);
				if(null != order.getCreateDate()){
					cell12.setCellValue(DateFormatUtils.format(order.getCreateDate(), "yyyy-MM-dd HH:mm:ss"));
				}
				
				HSSFCell cell13 = row.createCell(11);
				cell13.setCellStyle(columnStyle);
				if(null!=order.getExpectSolveDatetime()){
					cell13.setCellValue(DateFormatUtils.format(order.getExpectSolveDatetime(), "yyyy-MM-dd"));
				}
				
				HSSFCell cell14 = row.createCell(12);
				cell14.setCellStyle(columnStyle);
				if(null!=order.getMaterialCreateDate()){
					cell14.setCellValue(DateFormatUtils.format(order.getMaterialCreateDate(), "yyyy-MM-dd HH:mm:ss"));
				}
				
				HSSFCell cell15 = row.createCell(13);
				cell15.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(order.getPunishScore())){
					cell15.setCellValue(order.getPunishScore());
				}
				
				HSSFCell cell16 = row.createCell(14);
				cell16.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(order.getPunishMoney())){
					cell16.setCellValue(order.getPunishMoney());
				}
				HSSFCell cell17 = row.createCell(15);
				cell17.setCellStyle(columnStyle);
				if(StringUtils.isNoneBlank(order.getPunishRemarks())){
					cell17.setCellValue(order.getPunishRemarks());
					
				}
			
			}
		}
		
		return wb;
	}
}
