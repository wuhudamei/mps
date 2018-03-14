package cn.damei.common.utils;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;

import cn.damei.entity.modules.ScorOrderEmployee;

public class ExportScoreOrderEmployee {
	
	public static HSSFWorkbook exportScoreOrderEmployee(List<ScorOrderEmployee> list){
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("员工评分统计表");

		HSSFCellStyle cellStyleCenter = ExportFileNameUtils.initColumnHeadStyle(wb);
		HSSFCellStyle cellStyleRight = ExportFileNameUtils.initColumnCenterstyle(wb);
		HSSFCellStyle cellStyleLeft = ExportFileNameUtils.initColumnCenterstyle(wb);
		cellStyleRight.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		cellStyleLeft.setAlignment(HSSFCellStyle.ALIGN_LEFT);


		sheet.setColumnWidth(0, 2000);
		sheet.setColumnWidth(1, 2000);
		sheet.setColumnWidth(2, 3000);
		sheet.setColumnWidth(3, 3000);
		sheet.setColumnWidth(4, 3000);
		sheet.setColumnWidth(5, 3000);
		sheet.setColumnWidth(6, 5000);
		sheet.setColumnWidth(7, 3000);
		sheet.setColumnWidth(9, 3000);
		sheet.setColumnWidth(8, 3000);
		sheet.setColumnWidth(10,3000);
		
		try {  
            HSSFRow row = null;
            HSSFCell cell = null; 

            for (int i = 0; i < 3; i++) {
                row = sheet.createRow(i);  
                for (int j = 0; j < 11; j++) {  
                    cell = row.createCell(j);  
                    cell.setCellStyle(cellStyleCenter);  
                }  
            }  

            sheet.getRow(0).setHeight((short) 600);
            cell = sheet.getRow(0).getCell(0);  
            cell.setCellValue(new HSSFRichTextString("员工评分统计")); 
            cell = sheet.getRow(1).getCell(0);  
            cell.setCellValue(new HSSFRichTextString("序号"));  
            cell = sheet.getRow(1).getCell(1);  
            cell.setCellValue(new HSSFRichTextString("分公司"));  
            cell = sheet.getRow(1).getCell(2);  
            cell.setCellValue(new HSSFRichTextString("员工排名（好评数）"));  
            cell = sheet.getRow(1).getCell(3);  
            cell.setCellValue(new HSSFRichTextString("岗位"));  
            cell = sheet.getRow(1).getCell(4);  
            cell.setCellValue(new HSSFRichTextString("员工姓名"));  
            cell = sheet.getRow(1).getCell(5);  
            cell.setCellValue(new HSSFRichTextString("员工工号"));  
            cell = sheet.getRow(1).getCell(6);  
            cell.setCellValue(new HSSFRichTextString("员工电话"));  
            cell = sheet.getRow(1).getCell(7);  
            cell.setCellValue(new HSSFRichTextString("好评率"));  
            cell = sheet.getRow(1).getCell(8);  
            cell.setCellValue(new HSSFRichTextString("好评数"));
            cell = sheet.getRow(1).getCell(9);  
            cell.setCellValue(new HSSFRichTextString("差评数字"));
            cell = sheet.getRow(1).getCell(10);  
            cell.setCellValue(new HSSFRichTextString("综合评分"));

            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 10));
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
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 10, 10));
            for (int i= 0; i<list.size();i++) {
            	row = sheet.createRow(i+3);
					row.createCell(0).setCellValue(i+1);
					row.createCell(1).setCellValue(list.get(i).getName());
					row.createCell(2).setCellValue(i+1);
					row.createCell(3).setCellValue(list.get(i).getEmployeePost());
					row.createCell(4).setCellValue(list.get(i).getEmployeeName());
					row.createCell(5).setCellValue(list.get(i).getNo());
					row.createCell(6).setCellValue(list.get(i).getEmployeePhone());
					Integer goodRate = list.get(i).getGoodRate();
					if(goodRate==null){
						row.createCell(7).setCellValue("-");
					}else{
						row.createCell(7).setCellValue(goodRate);
						
					}
					row.createCell(8).setCellValue(list.get(i).getGoodNum());
					row.createCell(9).setCellValue(list.get(i).getBadNum());
					row.createCell(10).setCellValue(list.get(i).getAvgScore());
				}
        } catch (Exception e) {  
            e.printStackTrace();  
        }
		return wb;
	}

}
