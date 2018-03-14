package cn.damei.common.utils;

import java.io.FileOutputStream;
import java.util.List;

import cn.damei.entity.modules.BizOrderInstallItemProblemVo;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;



public class ExportInstallItemProblem {
	public static void main(String[] args) throws Exception {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("结算明细表");

		HSSFCellStyle cellStyleCenter = ExportFileNameUtils.initColumnHeadStyle(wb);
		HSSFCellStyle cellStyleRight = ExportFileNameUtils.initColumnCenterstyle(wb);
		HSSFCellStyle cellStyleLeft = ExportFileNameUtils.initColumnCenterstyle(wb);
		cellStyleRight.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		cellStyleLeft.setAlignment(HSSFCellStyle.ALIGN_LEFT);


		sheet.setColumnWidth(0, 2000);
		sheet.setColumnWidth(1, 4000);
		sheet.setColumnWidth(2, 4000);
		sheet.setColumnWidth(3, 4000);
		sheet.setColumnWidth(4, 4000);
		sheet.setColumnWidth(5, 8000);
		sheet.setColumnWidth(6, 2000);
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
		sheet.setColumnWidth(19, 5000);
		sheet.setColumnWidth(20, 3000);
		sheet.setColumnWidth(21, 5000);
		
		try {  
            HSSFRow row = null;
            HSSFCell cell = null; 

            for (int i = 0; i < 22; i++) {
                row = sheet.createRow(i);  
                for (int j = 0; j < 22; j++) {  
                    cell = row.createCell(j);  
                    cell.setCellStyle(cellStyleCenter);  
                }  
            }  

            sheet.getRow(0).setHeight((short) 600);
            cell = sheet.getRow(0).getCell(0);  
            cell.setCellValue(new HSSFRichTextString("主材安装问题明细")); 
            cell = sheet.getRow(1).getCell(0);  
            cell.setCellValue(new HSSFRichTextString("序号"));  
            cell = sheet.getRow(1).getCell(1);  
            cell.setCellValue(new HSSFRichTextString("客户"));  
            cell = sheet.getRow(1).getCell(2);  
            cell.setCellValue(new HSSFRichTextString("电话"));  
            cell = sheet.getRow(1).getCell(3);  
            cell.setCellValue(new HSSFRichTextString("地址"));  
            cell = sheet.getRow(1).getCell(4);  
            cell.setCellValue(new HSSFRichTextString("计划开工"));  
            cell = sheet.getRow(1).getCell(5);  
            cell.setCellValue(new HSSFRichTextString("计划竣工"));  
            cell = sheet.getRow(1).getCell(6);  
            cell.setCellValue(new HSSFRichTextString("设计师"));  
            cell = sheet.getRow(1).getCell(7);  
            cell.setCellValue(new HSSFRichTextString("电话"));  
            cell = sheet.getRow(1).getCell(8);  
            cell.setCellValue(new HSSFRichTextString("项目经理"));
            cell = sheet.getRow(1).getCell(9);  
            cell.setCellValue(new HSSFRichTextString("电话"));
            cell = sheet.getRow(1).getCell(10);  
            cell.setCellValue(new HSSFRichTextString("监理"));
            cell = sheet.getRow(1).getCell(11);  
            cell.setCellValue(new HSSFRichTextString("电话"));
            cell = sheet.getRow(1).getCell(12);  
            cell.setCellValue(new HSSFRichTextString("问题分类"));
            cell = sheet.getRow(1).getCell(13);  
            cell.setCellValue(new HSSFRichTextString("问题描述"));
            cell = sheet.getRow(1).getCell(14);  
            cell.setCellValue(new HSSFRichTextString("是否影响工期"));
            cell = sheet.getRow(1).getCell(15);  
            cell.setCellValue(new HSSFRichTextString("影响工期天数"));
            cell = sheet.getRow(1).getCell(16);  
            cell.setCellValue(new HSSFRichTextString("项目经理上报时间"));
            cell = sheet.getRow(1).getCell(17);  
            cell.setCellValue(new HSSFRichTextString("工程部处理方式"));
            cell = sheet.getRow(1).getCell(18);  
            cell.setCellValue(new HSSFRichTextString("工程部处理说明"));
            cell = sheet.getRow(1).getCell(19);  
            cell.setCellValue(new HSSFRichTextString("工程部处理日期"));
            cell = sheet.getRow(1).getCell(20);  
            cell.setCellValue(new HSSFRichTextString("材料部说明"));
            cell = sheet.getRow(1).getCell(21);  
            cell.setCellValue(new HSSFRichTextString("材料部处理日期"));

            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 21));
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
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 11, 11));
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 12, 12));
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 13, 13));
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 13, 13));
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 14, 14));
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 15, 15));
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 16, 16));
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 17, 17));
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 18, 18));
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 19, 19));
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 20, 20));
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 21, 21));
            FileOutputStream fileOut = new FileOutputStream("d:\\主材安装问题明细表.xls");  
            wb.write(fileOut);  
            fileOut.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }
    } 
	
	public static HSSFWorkbook exportInstallItemProblem(List<BizOrderInstallItemProblemVo> list){
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("主材安装问题明细表");

		HSSFCellStyle cellStyleCenter = ExportFileNameUtils.initColumnHeadStyle(wb);
		HSSFCellStyle cellStyleRight = ExportFileNameUtils.initColumnCenterstyle(wb);
		HSSFCellStyle cellStyleLeft = ExportFileNameUtils.initColumnCenterstyle(wb);
		cellStyleRight.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		cellStyleLeft.setAlignment(HSSFCellStyle.ALIGN_LEFT);


		sheet.setColumnWidth(0, 2000);
		sheet.setColumnWidth(1, 3000);
		sheet.setColumnWidth(2, 4000);
		sheet.setColumnWidth(3, 6000);
		sheet.setColumnWidth(4, 4000);
		sheet.setColumnWidth(5, 4000);
		sheet.setColumnWidth(6, 3000);
		sheet.setColumnWidth(7, 4000);
		sheet.setColumnWidth(8, 3000);
		sheet.setColumnWidth(9, 4000);
		sheet.setColumnWidth(10, 3000);
		sheet.setColumnWidth(11, 4000);
		sheet.setColumnWidth(12, 4000);
		sheet.setColumnWidth(13, 3000);
		sheet.setColumnWidth(14, 3000);
		sheet.setColumnWidth(15, 3000);
		sheet.setColumnWidth(16, 5000);
		sheet.setColumnWidth(17, 3000);
		sheet.setColumnWidth(18, 5000);

		
		try {  
            HSSFRow row = null;
            HSSFCell cell = null; 

            for (int i = 0; i < 3; i++) {
                row = sheet.createRow(i);  
                for (int j = 0; j < 19; j++) {  
                    cell = row.createCell(j);  
                    cell.setCellStyle(cellStyleCenter);  
                }  
            }  

            sheet.getRow(0).setHeight((short) 600);
            cell = sheet.getRow(0).getCell(0);  
            cell.setCellValue(new HSSFRichTextString("主材安装问题明细")); 
            cell = sheet.getRow(1).getCell(0);  
            cell.setCellValue(new HSSFRichTextString("序号"));  
            cell = sheet.getRow(1).getCell(1);  
            cell.setCellValue(new HSSFRichTextString("客户"));  
            cell = sheet.getRow(1).getCell(2);  
            cell.setCellValue(new HSSFRichTextString("电话"));  
            cell = sheet.getRow(1).getCell(3);  
            cell.setCellValue(new HSSFRichTextString("地址"));  
            cell = sheet.getRow(1).getCell(4);  
            cell.setCellValue(new HSSFRichTextString("计划开工"));  
            cell = sheet.getRow(1).getCell(5);  
            cell.setCellValue(new HSSFRichTextString("计划竣工"));  
            cell = sheet.getRow(1).getCell(6);  
            cell.setCellValue(new HSSFRichTextString("设计师"));  
            cell = sheet.getRow(1).getCell(7);  
            cell.setCellValue(new HSSFRichTextString("电话"));  
            cell = sheet.getRow(1).getCell(8);  
            cell.setCellValue(new HSSFRichTextString("项目经理"));
            cell = sheet.getRow(1).getCell(9);  
            cell.setCellValue(new HSSFRichTextString("电话"));
            cell = sheet.getRow(1).getCell(10);  
            cell.setCellValue(new HSSFRichTextString("质检员"));
            cell = sheet.getRow(1).getCell(11);  
            cell.setCellValue(new HSSFRichTextString("电话"));
            cell = sheet.getRow(1).getCell(12);  
            cell.setCellValue(new HSSFRichTextString("问题分类"));
            cell = sheet.getRow(1).getCell(13);  
            cell.setCellValue(new HSSFRichTextString("问题描述"));
            cell = sheet.getRow(1).getCell(14);  
            cell.setCellValue(new HSSFRichTextString("是否影响工期"));
            cell = sheet.getRow(1).getCell(15);  
            cell.setCellValue(new HSSFRichTextString("影响工期天数"));
            cell = sheet.getRow(1).getCell(16);  
            cell.setCellValue(new HSSFRichTextString("项目经理上报时间"));
            cell = sheet.getRow(1).getCell(17);  
            cell.setCellValue(new HSSFRichTextString("材料部说明"));
            cell = sheet.getRow(1).getCell(18);  
            cell.setCellValue(new HSSFRichTextString("材料部处理日期"));


            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 18));
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
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 11, 11));
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 12, 12));
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 13, 13));
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 13, 13));
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 14, 14));
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 15, 15));
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 16, 16));
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 17, 17));
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 18, 18));
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 19, 19));
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 20, 20));
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 21, 21));
            
            for (int i= 0; i<list.size();i++) {
            	row = sheet.createRow(i+3);

					row.createCell(0).setCellValue(i+1);
					row.createCell(1).setCellValue(list.get(i).getCustomerName());
					row.createCell(2).setCellValue(list.get(i).getCustomerPhone());
					row.createCell(3).setCellValue(list.get(i).getCommunityName()+"-"+list.get(i).getBuildNumber()+"-"+list.get(i).getBuildUnit()+"-"+list.get(i).getBuildRoom());
					row.createCell(4).setCellValue(DateUtils.formatDate(list.get(i).getContractStartDate()));
					row.createCell(5).setCellValue(DateUtils.formatDate(list.get(i).getContractEndDate()));
					row.createCell(6).setCellValue(list.get(i).getDesignerName());
					row.createCell(7).setCellValue(list.get(i).getDesignerPhone());
					row.createCell(8).setCellValue(list.get(i).getItemManager());
					row.createCell(9).setCellValue(list.get(i).getItemManagerPhone());
					row.createCell(10).setCellValue(list.get(i).getOrderInspector());
					row.createCell(11).setCellValue(list.get(i).getInspectorPhone());
					row.createCell(12).setCellValue(list.get(i).getProblemTypeName());
					row.createCell(13).setCellValue(list.get(i).getProblemDescribe());
					if("1".equals(list.get(i).getIsDelay())) {
						row.createCell(14).setCellValue("是");
						row.createCell(15).setCellValue(list.get(i).getDelayDays()+"天");
					}else {
						row.createCell(14).setCellValue("否");
						row.createCell(15).setCellValue("0天");
					}
					
					row.createCell(16).setCellValue(DateUtils.formatDateTime(list.get(i).getCreateDate()));
					

					
					if(list.get(i).getMaterialNote() != null){
						row.createCell(17).setCellValue(list.get(i).getMaterialNote());
					}else {
						row.createCell(17).setCellValue("");
					}
					
					if(list.get(i).getMaterialCreateDate() != null){
						row.createCell(18).setCellValue(DateUtils.formatDateTime(list.get(i).getMaterialCreateDate()));
					}else {
						row.createCell(18).setCellValue("");
					}
				}

        } catch (Exception e) {  
            e.printStackTrace();  
        }
		return wb;
	}
	
	
}
