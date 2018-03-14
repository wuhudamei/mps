package cn.damei.common.utils;

import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import cn.damei.entity.modules.PaymentDetailSplitForExcel;

public class ExportFinanceExcel {
	
	public static void main(String[] args) {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("结算明细表-大美装饰管理平台");
		// 定义样式
		HSSFCellStyle cellStyleCenter = ExportFileNameUtils.initColumnHeadStyle(wb);// 表头样工
		HSSFCellStyle cellStyleRight = ExportFileNameUtils.initColumnCenterstyle(wb);// 单元格样式
		HSSFCellStyle cellStyleLeft = ExportFileNameUtils.initColumnCenterstyle(wb);
		cellStyleRight.setAlignment(HSSFCellStyle.ALIGN_RIGHT);// 右对齐
		cellStyleLeft.setAlignment(HSSFCellStyle.ALIGN_LEFT);// 左对齐
		
		// 设置列宽
		sheet.setColumnWidth(0, 2000);//序号
		sheet.setColumnWidth(1, 4000);//客户名称
		sheet.setColumnWidth(2, 4000);//电话
		sheet.setColumnWidth(3, 8000);//地址
		sheet.setColumnWidth(4, 3000);//合同面积（M²）
		sheet.setColumnWidth(5, 4000);//项目经理
		sheet.setColumnWidth(6, 4000);//工人
		sheet.setColumnWidth(7, 4000);//铲墙皮
		sheet.setColumnWidth(8, 4000);//铲墙皮
		sheet.setColumnWidth(9, 4000);//水电
		sheet.setColumnWidth(10, 4000);//瓦工
		sheet.setColumnWidth(11, 3000);//油工
		sheet.setColumnWidth(12, 3000);//木工
		sheet.setColumnWidth(13, 3000);//首款
		sheet.setColumnWidth(14, 3000);//尾款
		sheet.setColumnWidth(15, 3000);//首款
		sheet.setColumnWidth(16, 3000);//尾款
		sheet.setColumnWidth(17, 3000);//首款
		sheet.setColumnWidth(18, 3000);//尾款
		sheet.setColumnWidth(19, 3000);//首款
		sheet.setColumnWidth(20, 3000);//尾款
	
		try {  
            HSSFRow row = null;
            HSSFCell cell = null; 
            // ---------------------------1.初始化带边框的表头------------------------------  
            for (int i = 0; i < 16; i++) {
            	row = sheet.createRow(i);  
            	if(i==0){
            		row.setHeight((short) 800);
            	}
                
                for (int j = 0; j <= 16; j++) {  
                    cell = row.createCell(j);  
                    cell.setCellStyle(cellStyleCenter);  
                }  
            }  
            // ---------------------------2.指定单元格填充数据------------------------------  
            cell = sheet.getRow(0).getCell(0);  
            cell.setCellValue(new HSSFRichTextString("2016产业工人结算明细表"));
            cell = sheet.getRow(1).getCell(0);  
            cell.setCellValue(new HSSFRichTextString("序号"));  
            cell = sheet.getRow(1).getCell(1);  
            cell.setCellValue(new HSSFRichTextString("客户名称"));  
            cell = sheet.getRow(1).getCell(2);  
            cell.setCellValue(new HSSFRichTextString("电话"));  
            cell = sheet.getRow(1).getCell(3);  
            cell.setCellValue(new HSSFRichTextString("地址"));  
            cell = sheet.getRow(1).getCell(4);  
            cell.setCellValue(new HSSFRichTextString("合同面积（M²）"));  
            cell = sheet.getRow(1).getCell(5);  
            cell.setCellValue(new HSSFRichTextString("项目经理"));  
            cell = sheet.getRow(1).getCell(6);  
            cell.setCellValue(new HSSFRichTextString("工人"));  
            cell = sheet.getRow(1).getCell(7);  
            cell.setCellValue(new HSSFRichTextString("营销保护"));
            cell = sheet.getRow(1).getCell(8);  
            cell.setCellValue(new HSSFRichTextString("铲墙皮"));  
            cell = sheet.getRow(1).getCell(9);  
            cell.setCellValue(new HSSFRichTextString("水电"));
            cell = sheet.getRow(1).getCell(11);  
            cell.setCellValue(new HSSFRichTextString("瓦工"));
            cell = sheet.getRow(1).getCell(13);  
            cell.setCellValue(new HSSFRichTextString("油工"));
            cell = sheet.getRow(1).getCell(15);  
            cell.setCellValue(new HSSFRichTextString("木工"));
            cell = sheet.getRow(2).getCell(9);  
            cell.setCellValue(new HSSFRichTextString("首款"));
            cell = sheet.getRow(2).getCell(10);  
            cell.setCellValue(new HSSFRichTextString("尾款"));
            cell = sheet.getRow(2).getCell(11);  
            cell.setCellValue(new HSSFRichTextString("首款"));
            cell = sheet.getRow(2).getCell(12);  
            cell.setCellValue(new HSSFRichTextString("尾款"));
            cell = sheet.getRow(2).getCell(13);  
            cell.setCellValue(new HSSFRichTextString("首款"));
            cell = sheet.getRow(2).getCell(14);  
            cell.setCellValue(new HSSFRichTextString("尾款"));
            cell = sheet.getRow(2).getCell(15);  
            cell.setCellValue(new HSSFRichTextString("首款"));
            cell = sheet.getRow(2).getCell(16);  
            cell.setCellValue(new HSSFRichTextString("尾款"));
            // ---------------------------3.合并单元格------------------------------  
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 18));// 开始行，结束行，开始列，结束列  
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 0, 0));  
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 1, 1));  
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 2, 2));  
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 3, 3));  
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 4, 4));
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 5, 5));
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 6, 6));
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 7, 7));
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 8, 8));
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 9, 10));
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 11, 12));
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 13, 14));
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 15, 16));
        
            FileOutputStream fileOut = new FileOutputStream("d:\\2016产业工人结算汇总表.xls");  
            wb.write(fileOut);  
            fileOut.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }
	}
	
	
	public static HSSFWorkbook exportFinance(List<PaymentDetailSplitForExcel> list){
		
		HSSFWorkbook wb = new HSSFWorkbook();// 创建一个Excel文件
		HSSFSheet sheet = wb.createSheet("结算明细表");// 创建一个Excel的Sheet
		// 定义样式
		HSSFCellStyle cellStyleCenter = ExportFileNameUtils.initColumnHeadStyle(wb);// 表头样工
		HSSFCellStyle cellStyleRight = ExportFileNameUtils.initColumnCenterstyle(wb);// 单元格样式
		HSSFCellStyle cellStyleLeft = ExportFileNameUtils.initColumnCenterstyle(wb);
		cellStyleRight.setAlignment(HSSFCellStyle.ALIGN_RIGHT);// 右对齐
		cellStyleLeft.setAlignment(HSSFCellStyle.ALIGN_LEFT);// 左对齐
		
		// 设置列宽
		sheet.setColumnWidth(0, 2000);//序号
		sheet.setColumnWidth(1, 4000);//订单编号
		sheet.setColumnWidth(2, 4000);//客户名称
		sheet.setColumnWidth(3, 4000);//电话
		sheet.setColumnWidth(4, 8000);//地址
		sheet.setColumnWidth(5, 3000);//合同面积（M²）
		sheet.setColumnWidth(6, 4000);//项目经理
		sheet.setColumnWidth(7, 4000);//工人
		sheet.setColumnWidth(8, 4000);//铲墙皮
		sheet.setColumnWidth(9, 4000);//铲墙皮
		sheet.setColumnWidth(10, 4000);//水电
		sheet.setColumnWidth(11, 4000);//瓦工
		sheet.setColumnWidth(12, 3000);//油工
		sheet.setColumnWidth(13, 3000);//木工
		sheet.setColumnWidth(14, 3000);//首款
		sheet.setColumnWidth(15, 3000);//尾款
		sheet.setColumnWidth(16, 3000);//首款
		sheet.setColumnWidth(17, 3000);//尾款
		sheet.setColumnWidth(18, 3000);//首款
		sheet.setColumnWidth(19, 3000);//尾款
		sheet.setColumnWidth(20, 3000);//首款
		sheet.setColumnWidth(21, 3000);//尾款
		
		try {  
            HSSFRow row = null;
            HSSFCell cell = null; 
            // ---------------------------1.初始化带边框的表头------------------------------  
            for (int i = 0; i < 3; i++) {
            	row = sheet.createRow(i);  
            	if(i==0){
            		row.setHeight((short) 800);
            	}
                for (int j = 0; j <= 19; j++) {
                    cell = row.createCell(j);  
                    cell.setCellStyle(cellStyleCenter);  
                }  
            }  
            // ---------------------------2.指定单元格填充数据------------------------------  
            cell = sheet.getRow(0).getCell(0);  
            cell.setCellValue(new HSSFRichTextString("产业工人结算明细表"));
            cell = sheet.getRow(1).getCell(0);  
            cell.setCellValue(new HSSFRichTextString("序号"));  
            cell = sheet.getRow(1).getCell(1); 
            
            cell.setCellValue(new HSSFRichTextString("订单编号"));  
            cell = sheet.getRow(1).getCell(2);
            
            cell.setCellValue(new HSSFRichTextString("客户名称"));  
            cell = sheet.getRow(1).getCell(3);  
            cell.setCellValue(new HSSFRichTextString("电话"));  
            cell = sheet.getRow(1).getCell(4);  
            cell.setCellValue(new HSSFRichTextString("地址"));  
            cell = sheet.getRow(1).getCell(5);  
            cell.setCellValue(new HSSFRichTextString("合同面积（M²）"));  
            cell = sheet.getRow(1).getCell(6);  
            cell.setCellValue(new HSSFRichTextString("项目经理"));  
            cell = sheet.getRow(1).getCell(7);  
            cell.setCellValue(new HSSFRichTextString("工人"));  
            cell = sheet.getRow(1).getCell(8);  
            cell.setCellValue(new HSSFRichTextString("营销保护"));
            cell = sheet.getRow(1).getCell(8+1);  
            cell.setCellValue(new HSSFRichTextString("铲墙皮"));  
            cell = sheet.getRow(1).getCell(9+1);  
            cell.setCellValue(new HSSFRichTextString("水电"));
            cell = sheet.getRow(1).getCell(11+1);  
            cell.setCellValue(new HSSFRichTextString("瓦工"));
            cell = sheet.getRow(1).getCell(13+1);  
            cell.setCellValue(new HSSFRichTextString("油工"));
            cell = sheet.getRow(1).getCell(15+1);  
            cell.setCellValue(new HSSFRichTextString("木工"));
            cell = sheet.getRow(1).getCell(17+1);
            cell.setCellValue(new HSSFRichTextString("特殊任务包"));
            cell = sheet.getRow(2).getCell(9+1);  
            cell.setCellValue(new HSSFRichTextString("首款"));
            cell = sheet.getRow(2).getCell(10+1);  
            cell.setCellValue(new HSSFRichTextString("尾款"));
            cell = sheet.getRow(2).getCell(11+1);  
            cell.setCellValue(new HSSFRichTextString("首款"));
            cell = sheet.getRow(2).getCell(12+1);  
            cell.setCellValue(new HSSFRichTextString("尾款"));
            cell = sheet.getRow(2).getCell(13+1);  
            cell.setCellValue(new HSSFRichTextString("首款"));
            cell = sheet.getRow(2).getCell(14+1);  
            cell.setCellValue(new HSSFRichTextString("尾款"));
            cell = sheet.getRow(2).getCell(15+1);  
            cell.setCellValue(new HSSFRichTextString("首款"));
            cell = sheet.getRow(2).getCell(16+1);  
            cell.setCellValue(new HSSFRichTextString("尾款"));
            cell = sheet.getRow(2).getCell(17+1);
            cell.setCellValue(new HSSFRichTextString("首款"));
            cell = sheet.getRow(2).getCell(18+1);
            cell.setCellValue(new HSSFRichTextString("尾款"));
            // ---------------------------3.合并单元格------------------------------  
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 19));// 开始行，结束行，开始列，结束列  
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
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 10, 11));
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 12, 13));
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 14, 15));
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 16, 17));
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 18, 19));
        
           for (int i = 0; i < list.size(); i++) {
        	   row = sheet.createRow(i+3);
        	   for(int j=0; j< 18;j++){
        		   row.createCell(0).setCellValue(i+1);
        		   row.createCell(1).setCellValue(list.get(i).getOrderNumber());
        		   row.createCell(2).setCellValue(list.get(i).getCustomerName());
        		   row.createCell(3).setCellValue(list.get(i).getCustomerPhone());
        		   row.createCell(4).setCellValue(list.get(i).getCustomerAddress());
        		   row.createCell(5).setCellValue(list.get(i).getContractArea());
        		   row.createCell(6).setCellValue(list.get(i).getItemManager());
        		   row.createCell(7).setCellValue(list.get(i).getWorkerName());
        		   if("营销保护类".equals(list.get(i).getPackageType())){
        			   row.createCell(8).setCellValue(list.get(i).getAdvancePayment());
        		   }else if("拆除类".equals(list.get(i).getPackageType())){//铲墙皮
        			   row.createCell(9).setCellValue(list.get(i).getAdvancePayment());
        		   }else if("水电类".equals(list.get(i).getPackageType())){//水电
        			   if(list.get(i).getAdvancePayment() == null){
   							row.createCell(10).setCellValue("");
   							row.createCell(11).setCellValue(list.get(i).getRestPayment());
   						}else{
   							row.createCell(10).setCellValue(list.get(i).getAdvancePayment());
   							row.createCell(11).setCellValue("");
   						}
        		   }else if("瓦工类".equals(list.get(i).getPackageType())){
        			   if(list.get(i).getAdvancePayment() == null){
  							row.createCell(12).setCellValue("");
  							row.createCell(13).setCellValue(list.get(i).getRestPayment());
  						}else{
  							row.createCell(12).setCellValue(list.get(i).getAdvancePayment());
  							row.createCell(13).setCellValue("");
  						}
        		   }else if("油工类".equals(list.get(i).getPackageType())){
        			   if(list.get(i).getAdvancePayment() == null){
  							row.createCell(14).setCellValue("");
  							row.createCell(15).setCellValue(list.get(i).getRestPayment());
  						}else{
  							row.createCell(14).setCellValue(list.get(i).getAdvancePayment());
  							row.createCell(15).setCellValue("");
  						}
        		   }else if("木工类".equals(list.get(i).getPackageType())){
        			   if(list.get(i).getAdvancePayment() == null){
  							row.createCell(16).setCellValue("");
  							row.createCell(17).setCellValue(list.get(i).getRestPayment());
  						}else{
  							row.createCell(16).setCellValue(list.get(i).getAdvancePayment());
  							row.createCell(17).setCellValue("");
  						}
        		   }else if("特殊任务包".equals(list.get(i).getPackageType())){
                       if(list.get(i).getAdvancePayment() == null){
                           row.createCell(18).setCellValue("");
                           row.createCell(19).setCellValue(list.get(i).getRestPayment());
                       }else{
                           row.createCell(18).setCellValue(list.get(i).getAdvancePayment());
                           row.createCell(19).setCellValue("");
                       }
                   }
        	   }
           }
        } catch (Exception e) {  
            e.printStackTrace();  
        }
		return wb;
	}
	
	
	
	
}
