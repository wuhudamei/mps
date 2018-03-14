package cn.damei.common.utils;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import cn.damei.entity.modules.BizOrderTaskpackagePaymentDetailMergeTxtVo;

public class ExportSWIFTExcel {
	
	public HSSFWorkbook ExportSWIFTExcel(List<BizOrderTaskpackagePaymentDetailMergeTxtVo> list){
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("对外支付");
		

		HSSFCellStyle columnHeadStyle = wb.createCellStyle();
		
		columnHeadStyle.setBottomBorderColor(HSSFColor.BLACK.index);
		columnHeadStyle.setFillForegroundColor(HSSFColor.YELLOW.index);
		columnHeadStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);	
		

		HSSFFont font = wb.createFont();
		font.setFontHeightInPoints((short) 9);
		font.setColor(HSSFColor.BLACK.index);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontName("宋体");
		columnHeadStyle.setFont(font);
		

		HSSFCellStyle columnHeadStyle1 = wb.createCellStyle();
		columnHeadStyle1.setFillForegroundColor(HSSFColor.WHITE.index);
		columnHeadStyle1.setFont(font);

		HSSFCellStyle columnHeadStyle2 = wb.createCellStyle();
		columnHeadStyle2.setFillForegroundColor(HSSFColor.WHITE.index);
		HSSFFont font1 = wb.createFont();
		font1.setFontHeightInPoints((short) 9);
		font1.setColor(HSSFColor.BLACK.index);
		font1.setFontName("宋体");
		columnHeadStyle2.setFont(font1);
				

				
				

		sheet.setColumnWidth(0, 5500);
		sheet.setColumnWidth(1, 2000);
		sheet.setColumnWidth(2, 3100);
		sheet.setColumnWidth(3, 5500);
		sheet.setColumnWidth(4, 3000);
		sheet.setColumnWidth(5, 4000);
		sheet.setColumnWidth(6, 3000);
		sheet.setColumnWidth(7, 3000);
		sheet.setColumnWidth(8, 3000);
		sheet.setColumnWidth(9, 5000);
		sheet.setColumnWidth(10,6000);
		sheet.setColumnWidth(11,5000);
		sheet.setColumnWidth(12,2800);
		sheet.setColumnWidth(13,4000);
		sheet.setColumnWidth(14,4000);
		sheet.setColumnWidth(15,3000);
		sheet.setColumnWidth(16,8000);
		try {  
            HSSFRow row = null;
            HSSFCell cell = null; 
            

            for (int i = 0; i < 5; i++) {
                row = sheet.createRow(i);
                if(i == 4){
                	row.setHeight((short)(450));
                }
                for (int j = 0; j < 17; j++) {  
                    cell = row.createCell(j);  
                }  
            }  

           

            cell = sheet.getRow(0).getCell(0);
            cell.setCellStyle(columnHeadStyle1);
            cell.setCellValue(new HSSFRichTextString("文件类型:"));
            cell = sheet.getRow(0).getCell(1); 
            cell.setCellStyle(columnHeadStyle2);
            cell.setCellValue(new HSSFRichTextString("FTExternalTransferBatch"));
            
            cell = sheet.getRow(1).getCell(0); 
            cell.setCellStyle(columnHeadStyle1);
            cell.setCellValue(new HSSFRichTextString("标题:"));
            cell = sheet.getRow(1).getCell(1);
            cell.setCellStyle(columnHeadStyle2);
            cell.setCellValue(new HSSFRichTextString("对外支付"));
            
            cell = sheet.getRow(2).getCell(0);  
            cell.setCellValue(new HSSFRichTextString("(标注颜色的列为必填项)"));
            
            

            

            HSSFRow row2 = sheet.getRow(4);
            
            HSSFCell headCell0 = row2.createCell(0);
    		headCell0.setCellStyle(columnHeadStyle);
    		headCell0.setCellValue("付款账号");
            
            
            
            
            
            
            
            
            cell = sheet.getRow(4).getCell(1);  
            cell.setCellStyle(columnHeadStyle);
            cell.setCellValue(new HSSFRichTextString("币种"));  
            
            cell = sheet.getRow(4).getCell(2);
            cell.setCellStyle(columnHeadStyle);
            cell.setCellValue(new HSSFRichTextString("本/他行标识"));  
            
            cell = sheet.getRow(4).getCell(3);  
            cell.setCellStyle(columnHeadStyle);
            cell.setCellValue(new HSSFRichTextString("收款人账号"));  
            cell = sheet.getRow(4).getCell(4);  
            cell.setCellStyle(columnHeadStyle);
            cell.setCellValue(new HSSFRichTextString("收款人姓名"));  
            cell = sheet.getRow(4).getCell(5);  
            cell.setCellStyle(columnHeadStyle);
            cell.setCellValue(new HSSFRichTextString("收款单位编号"));  
            cell = sheet.getRow(4).getCell(6);
            cell.setCellStyle(columnHeadStyle);
            cell.setCellValue(new HSSFRichTextString("支付方式"));  
            cell = sheet.getRow(4).getCell(7);
            cell.setCellStyle(columnHeadStyle);
            cell.setCellValue(new HSSFRichTextString("支付金额"));  
            cell = sheet.getRow(4).getCell(8);
            cell.setCellStyle(columnHeadStyle1);
            cell.setCellValue(new HSSFRichTextString("支付联行号"));
            cell = sheet.getRow(4).getCell(9);
            cell.setCellStyle(columnHeadStyle);
            cell.setCellValue(new HSSFRichTextString("开户网点名称"));
            cell = sheet.getRow(4).getCell(10);  
            cell.setCellStyle(columnHeadStyle1);
            cell.setCellValue(new HSSFRichTextString("开户地所在省/市/自治区"));
            cell = sheet.getRow(4).getCell(11);
            cell.setCellStyle(columnHeadStyle1);
            cell.setCellValue(new HSSFRichTextString("开户地所在市"));
            cell = sheet.getRow(4).getCell(12); 
            cell.setCellStyle(columnHeadStyle);
            cell.setCellValue(new HSSFRichTextString("支付时效"));
            cell = sheet.getRow(4).getCell(13); 
            cell.setCellStyle(columnHeadStyle1);
            cell.setCellValue(new HSSFRichTextString("预约支付日期"));
            cell = sheet.getRow(4).getCell(14); 
            cell.setCellStyle(columnHeadStyle1);
            cell.setCellValue(new HSSFRichTextString("预约支付时间"));
            cell = sheet.getRow(4).getCell(15);  
            cell.setCellStyle(columnHeadStyle1);
            cell.setCellValue(new HSSFRichTextString("摘要"));
            cell = sheet.getRow(4).getCell(16);  
            cell.setCellStyle(columnHeadStyle1);
            cell.setCellValue(new HSSFRichTextString("备注"));

           
            for (int i= 0; i<list.size();i++) {
            		row = sheet.createRow(i+5);
            		
            		row.setHeight((short) 450);
            		
					HSSFCell createCell0 = row.createCell(0);
					createCell0.setCellValue(ConstantUtils.ZHONGXIN_COMPANY_ACCOUNT);
					
					HSSFCell createCell = row.createCell(1);
					createCell.setCellValue("人民币");
					createCell.setCellStyle(columnHeadStyle2);
					
					if(ConstantUtils.ZHONGXIN_BANK_CODE.equals(list.get(i).getBankNo())){
						HSSFCell createCell2 = row.createCell(2);
						createCell.setCellStyle(columnHeadStyle2);
						createCell2.setCellValue("中信银行");
					}else{
						HSSFCell createCell2 = row.createCell(2);
						createCell2.setCellStyle(columnHeadStyle2);
						createCell2.setCellValue("其他银行账户");
					}
					row.createCell(3).setCellValue(list.get(i).getBankCardNo());
					row.createCell(4).setCellValue(list.get(i).getRealName());
					row.createCell(5).setCellValue("123");
					row.createCell(6).setCellValue("大额支付");
					row.createCell(7).setCellValue(list.get(i).getAmount());
					row.createCell(8).setCellValue("");
					row.createCell(9).setCellValue(list.get(i).getBankName());
					row.createCell(10).setCellValue("");
					row.createCell(11).setCellValue("");
					row.createCell(12).setCellValue("当日");
					row.createCell(13).setCellValue("");
					row.createCell(14).setCellValue("");
					row.createCell(15).setCellValue("工程款");
					row.createCell(16).setCellValue("");
				}
        } catch (Exception e) {  
            e.printStackTrace();  
        }
		return wb;
	}
    
}
