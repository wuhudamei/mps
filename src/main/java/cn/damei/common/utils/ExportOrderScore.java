package cn.damei.common.utils;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;

import cn.damei.entity.modules.ScoreOrderQuery;

public class ExportOrderScore {
	
	public static HSSFWorkbook exportOrderScore(List<ScoreOrderQuery> list){
            HSSFWorkbook wb = new HSSFWorkbook();
    		HSSFSheet sheet = wb.createSheet("订单评分统计表");

    		HSSFCellStyle cellStyleCenter = ExportFileNameUtils.initColumnHeadStyle(wb);
    		HSSFCellStyle cellStyleRight = ExportFileNameUtils.initColumnCenterstyle(wb);
    		HSSFCellStyle cellStyleLeft = ExportFileNameUtils.initColumnCenterstyle(wb);
    		cellStyleRight.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
    		cellStyleLeft.setAlignment(HSSFCellStyle.ALIGN_LEFT);


    		sheet.setColumnWidth(0, 3000);
    		sheet.setColumnWidth(1, 3000);
    		sheet.setColumnWidth(2, 3000);
    		sheet.setColumnWidth(3, 3000);
    		sheet.setColumnWidth(4, 3000);
    		sheet.setColumnWidth(5, 3000);
    		sheet.setColumnWidth(6, 5000);
    		sheet.setColumnWidth(7, 10000);
    		sheet.setColumnWidth(8, 6000);
    		sheet.setColumnWidth(9, 10000);
    		sheet.setColumnWidth(10,6000);
    		
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
                cell.setCellValue(new HSSFRichTextString("订单评分统计")); 
                cell = sheet.getRow(1).getCell(0);  
                cell.setCellValue(new HSSFRichTextString("序号"));  
                cell = sheet.getRow(1).getCell(1);  
                cell.setCellValue(new HSSFRichTextString("分公司"));  
                cell = sheet.getRow(1).getCell(2);  
                cell.setCellValue(new HSSFRichTextString("评分分类"));  
                cell = sheet.getRow(1).getCell(3);  
                cell.setCellValue(new HSSFRichTextString("分值"));  
                cell = sheet.getRow(1).getCell(4);  
                cell.setCellValue(new HSSFRichTextString("订单号"));  
                cell = sheet.getRow(1).getCell(5);  
                cell.setCellValue(new HSSFRichTextString("客户姓名"));  
                cell = sheet.getRow(1).getCell(6);  
                cell.setCellValue(new HSSFRichTextString("客户电话"));  
                cell = sheet.getRow(1).getCell(7);  
                cell.setCellValue(new HSSFRichTextString("订单地址"));  
                cell = sheet.getRow(1).getCell(8);  
                cell.setCellValue(new HSSFRichTextString("干系"));
                cell = sheet.getRow(1).getCell(9);  
                cell.setCellValue(new HSSFRichTextString("评价内容"));
                cell = sheet.getRow(1).getCell(10);  
                cell.setCellValue(new HSSFRichTextString("评价时间"));

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
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (int i= 0; i<list.size();i++) {
            	row = sheet.createRow(i+3);
					row.createCell(0).setCellValue(i+1);
					row.createCell(1).setCellValue(list.get(i).getName());
					row.createCell(2).setCellValue(list.get(i).getTypeName());
					row.createCell(3).setCellValue(list.get(i).getScoreValue());
					row.createCell(4).setCellValue(list.get(i).getOrderNumber());
					row.createCell(5).setCellValue(list.get(i).getCustomerName());
					row.createCell(6).setCellValue(list.get(i).getCustomerPhone());
					row.createCell(7).setCellValue(list.get(i).getProvince()+list.get(i).getCity()+list.get(i).getCounty()+list.get(i).getDetailAddress());
					row.createCell(8).setCellValue(list.get(i).getStakeholder());
					row.createCell(9).setCellValue(list.get(i).getScoreContent());
					row.createCell(10).setCellValue(sf.format(list.get(i).getScoreTime()));
				}
        } catch (Exception e) {  
            e.printStackTrace();  
        }
		return wb;
	}

}
